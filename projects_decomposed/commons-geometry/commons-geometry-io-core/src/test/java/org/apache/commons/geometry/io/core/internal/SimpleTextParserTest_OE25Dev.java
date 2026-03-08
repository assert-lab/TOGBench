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
package org.apache.commons.geometry.io.core.internal;

import java.io.Reader;
import java.io.StringReader;
import java.util.function.IntPredicate;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class SimpleTextParserTest_OE25Dev {

    private static final double EPS = 1e-20;

    private static final int EOF = -1;

    @Test
    void testUnexpectedToken() {
        // arrange
        final SimpleTextParser p = parser("abc\ndef");

        // act/assert
        Assertions.assertEquals("Parsing failed at line 1,column 1: expected test but found no current token",p.unexpectedToken("test").getMessage());

        p.nextAlphanumeric();
        Assertions.assertEquals("Parsing failed at line 1,column 1: expected test but found [abc]",p.unexpectedToken("test").getMessage());

        p.nextAlphanumeric();
        Assertions.assertEquals("Parsing failed at line 1,column 4: expected test but found end of line",p.unexpectedToken("test").getMessage());

        p.discardLine();

        p.next(SimpleTextParser::isWhitespace);
        Assertions.assertEquals("Parsing failed at line 2,column 1: expected test but found empty token followed by [d]",p.unexpectedToken("test").getMessage());

        p.next(3).next(10);
        Assertions.assertEquals("Parsing failed at line 2,column 4: expected test but found end of content",p.unexpectedToken("test").getMessage());
    }

    @Test
    void testUnexpectedToken_ioError() {
        // arrange
        final FailBuffer b = new FailBuffer(new StringReader("abc"));
        final SimpleTextParser p = new SimpleTextParser(b);

        // act/assert
        b.setFail(false);
        p.next(SimpleTextParser::isDecimalPart);
        b.setFail(true);
        Assertions.assertEquals("Parsing failed at line 1,column 1: expected test but found empty token",p.unexpectedToken("test").getMessage());

        b.setFail(false);
        p.nextAlphanumeric();
        b.setFail(true);
        Assertions.assertEquals("Parsing failed at line 1,column 1: expected test but found [abc]",p.unexpectedToken("test").getMessage());

        b.setFail(false);
        p.nextAlphanumeric();
        b.setFail(true);
        Assertions.assertEquals("Parsing failed at line 1,column 4: expected test but found no current token",p.unexpectedToken("test").getMessage());
    }

    private static SimpleTextParser parser(final String content) {
        final StringReader reader = new StringReader(content);

        return new SimpleTextParser(reader);
    }

    private static void assertCharacterSequence(final SimpleTextParser parser, final String expected) {
        char expectedChar;
        String msg;
        for (int i = 0; i < expected.length(); ++i) {
            expectedChar = expected.charAt(i);

            msg = "Failed at index " + i + ":";

            Assertions.assertEquals(expectedChar, parser.peekChar(), msg);
            Assertions.assertEquals(expectedChar, parser.peekChar(), msg);

            Assertions.assertTrue(parser.hasMoreCharacters());
            Assertions.assertEquals(expectedChar, parser.readChar(), msg);
        }

        Assertions.assertFalse(parser.hasMoreCharacters());
        Assertions.assertEquals(-1, parser.peekChar());
        Assertions.assertEquals(-1, parser.peekChar());
        Assertions.assertEquals(-1, parser.readChar());
    }

    private static void assertChar(final int expected, final int actual) {
        final String expectedStr = describeChar(expected);
        final String actualStr = describeChar(actual);

        Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    private static void assertMatchesAll(final IntPredicate pred, final String chars) {
        for (char ch : chars.toCharArray()) {
            final String msg = "Expected predicate to match [" + describeChar(ch) + "]";
            Assertions.assertTrue(pred.test(ch), msg);
        }
    }

    private static void assertDoesNotMatchAny(final IntPredicate pred, final String chars) {
        for (char ch : chars.toCharArray()) {
            final String msg = "Expected predicate to not match [" + describeChar(ch) + "]";
            Assertions.assertFalse(pred.test(ch), msg);
        }
    }

    private static String describeChar(final int ch) {
        switch (ch) {
        case '\n':
            return "\\n";
        case '\r':
            return "\\r";
        case '\t':
            return "\\t";
        case EOF:
            return "EOF";
        default:
            return String.valueOf((char) ch);
        }
    }

    private static void assertPosition(final SimpleTextParser parser, final int line, final int col) {
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
        Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    private static void assertToken(final SimpleTextParser parser, final String token, final int line, final int col) {
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
        Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
        Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    private static final class FailBuffer extends CharReadBuffer {

        private boolean fail;

        FailBuffer(final Reader in) {
            super(in);
        }

        public void setFail(final boolean fail) {
            this.fail = fail;
        }

        @Override
        public boolean hasMoreCharacters() {
            checkFail();
            return super.hasMoreCharacters();
        }

        private void checkFail() {
            if (fail) {
                throw new IllegalStateException("test failure");
            }
        }
    }

    @Test
    void testMaxStringLength_defaultValue_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        Assertions.assertEquals(1024, p.getMaxStringLength());
    }

    @Test
    void testHasMoreCharacters_1_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser nonEmpty = parser("a");

        // act/assert
        Assertions.assertFalse(empty.hasMoreCharacters());
    }

    @Test
    void testHasMoreCharacters_2_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser nonEmpty = parser("a");

        // act/assert
        // removed other assertion

        Assertions.assertTrue(nonEmpty.hasMoreCharacters());
    }

    @Test
    void testHasMoreCharactersOnLine_1_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser singleLine = parser("a");
        final SimpleTextParser multiLine = parser("a\r\nb\rc\n\n");

        // act/assert
        Assertions.assertFalse(empty.hasMoreCharactersOnLine());
    }

    @Test
    void testHasMoreCharactersOnLine_2_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser singleLine = parser("a");
        final SimpleTextParser multiLine = parser("a\r\nb\rc\n\n");

        // act/assert
        // removed other assertion

        Assertions.assertTrue(singleLine.hasMoreCharactersOnLine());
    }

    @Test
    void testHasMoreCharactersOnLine_5_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser singleLine = parser("a");
        final SimpleTextParser multiLine = parser("a\r\nb\rc\n\n");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(multiLine.hasMoreCharactersOnLine());
    }

    @Test
    void testHasMoreCharactersOnLine_11_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser singleLine = parser("a");
        final SimpleTextParser multiLine = parser("a\r\nb\rc\n\n");

        // act/assert
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

        Assertions.assertTrue(multiLine.hasMoreCharactersOnLine());
    }

    @Test
    void testHasMoreCharactersOnLine_15_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser singleLine = parser("a");
        final SimpleTextParser multiLine = parser("a\r\nb\rc\n\n");

        // act/assert
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

        Assertions.assertTrue(multiLine.hasMoreCharactersOnLine());
    }

    @Test
    void testBasicTokenMethods_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        Assertions.assertFalse(p.hasNonEmptyToken());
    }

    @Test
    void testBasicTokenMethods_10_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(p.hasNonEmptyToken());
    }

    @Test
    void testBasicTokenMethods_16_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
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
        Assertions.assertFalse(p.hasNonEmptyToken());
    }

    @Test
    void testGetCurrentTokenAsDouble_1_oe() {
        // arrange
        final SimpleTextParser p = parser("1e-4\n+5\n-4.001");

        // act/assert
        p.nextLine();
        Assertions.assertEquals(1e-4, p.getCurrentTokenAsDouble(), EPS);
    }

    @Test
    void testGetCurrentTokenAsDouble_2_oe() {
        // arrange
        final SimpleTextParser p = parser("1e-4\n+5\n-4.001");

        // act/assert
        p.nextLine();
        // removed other assertion

        p.nextLine();
        Assertions.assertEquals(5.0, p.getCurrentTokenAsDouble(), EPS);
    }

    @Test
    void testGetCurrentTokenAsDouble_3_oe() {
        // arrange
        final SimpleTextParser p = parser("1e-4\n+5\n-4.001");

        // act/assert
        p.nextLine();
        // removed other assertion

        p.nextLine();
        // removed other assertion

        p.nextLine();
        Assertions.assertEquals(-4.001, p.getCurrentTokenAsDouble(), EPS);
    }

    @Test
    void testGetCurrentTokenAsDouble_includedNumberFormatExceptionOnFailure_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.nextLine();

        // act/assert
        try {
    p.getCurrentTokenAsDouble();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testGetCurrentTokenAsInt_1_oe() {
        // arrange
        final SimpleTextParser p = parser("0\n+5\n-401");

        // act/assert
        p.nextLine();
        Assertions.assertEquals(0, p.getCurrentTokenAsInt());
    }

    @Test
    void testGetCurrentTokenAsInt_2_oe() {
        // arrange
        final SimpleTextParser p = parser("0\n+5\n-401");

        // act/assert
        p.nextLine();
        // removed other assertion

        p.nextLine();
        Assertions.assertEquals(5, p.getCurrentTokenAsInt());
    }

    @Test
    void testGetCurrentTokenAsInt_3_oe() {
        // arrange
        final SimpleTextParser p = parser("0\n+5\n-401");

        // act/assert
        p.nextLine();
        // removed other assertion

        p.nextLine();
        // removed other assertion

        p.nextLine();
        Assertions.assertEquals(-401, p.getCurrentTokenAsInt());
    }

    @Test
    void testGetCurrentTokenAsInt_includedNumberFormatExceptionOnFailure_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.nextLine();

        // act/assert
        try {
    p.getCurrentTokenAsInt();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testPeek_lenArg_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        Assertions.assertEquals("", p.peek(0));
    }

    @Test
    void testPeek_lenArg_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals("", p.peek(0));
    }

    @Test
    void testPeek_lenArg_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        Assertions.assertEquals("bcde", p.peek(4));
    }

    @Test
    void testPeek_lenArg_7_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals("bcdef\r", p.peek(6));
    }

    @Test
    void testPeek_lenArg_9_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals("bcdef\r\n\r ghi", p.peek(100));
    }

    @Test
    void testPeek_lenArg_12_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        p.discard(c -> true);

        Assertions.assertNull(p.peek(0));
    }

    @Test
    void testPeek_lenArg_13_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        p.discard(c -> true);

        // removed other assertion
        Assertions.assertNull(p.peek(100));
    }

    @Test
    void testPeek_predicateArg_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        Assertions.assertEquals("", p.peek(c -> false));
    }

    @Test
    void testPeek_predicateArg_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        p.readChar();

        Assertions.assertEquals("bcdef", p.peek(SimpleTextParser::isAlphanumeric));
    }

    @Test
    void testPeek_predicateArg_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals("bcdef\r\n\r ghi", p.peek(c -> true));
    }

    @Test
    void testPeek_predicateArg_8_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        p.discard(c -> true);

        Assertions.assertNull(p.peek(c -> true));
    }

    @Test
    void testPeek_predicateArg_9_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        p.discard(c -> true);

        // removed other assertion
        Assertions.assertNull(p.peek(c -> false));
    }

    @Test
    void testMatch_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        p.next(1)
            .match("a")
            .next(100)
            .match("bcdef");

        Assertions.assertFalse(p.hasMoreCharacters());
    }

    @Test
    void testMatch_ignoreCase_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        p.next(1)
            .matchIgnoreCase("A")
            .next(100)
            .matchIgnoreCase("BcdEF");

        Assertions.assertFalse(p.hasMoreCharacters());
    }

    @Test
    void testTryMatch_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        Assertions.assertTrue(p.tryMatch("abc"));
    }

    @Test
    void testTryMatch_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion

        Assertions.assertFalse(p.tryMatch("ab"));
    }

    @Test
    void testTryMatch_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(p.tryMatch(""));
    }

    @Test
    void testTryMatch_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(p.tryMatch(null));
    }

    @Test
    void testTryMatch_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(p.tryMatch("ABC"));
    }

    @Test
    void testTryMatch_6_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(p.tryMatch("aBc"));
    }

    @Test
    void testTryMatch_7_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);
        Assertions.assertTrue(p.tryMatch(null));
    }

    @Test
    void testTryMatchIgnoreCase_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        Assertions.assertTrue(p.tryMatchIgnoreCase("abc"));
    }

    @Test
    void testTryMatchIgnoreCase_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion
        Assertions.assertTrue(p.tryMatchIgnoreCase("ABC"));
    }

    @Test
    void testTryMatchIgnoreCase_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(p.tryMatchIgnoreCase("aBc"));
    }

    @Test
    void testTryMatchIgnoreCase_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(p.tryMatch("ab"));
    }

    @Test
    void testTryMatchIgnoreCase_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(p.tryMatch(""));
    }

    @Test
    void testTryMatchIgnoreCase_6_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(p.tryMatch(null));
    }

    @Test
    void testTryMatchIgnoreCase_7_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.next(1);
        Assertions.assertTrue(p.tryMatch(null));
    }

    @Test
    void testChoose_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        Assertions.assertEquals(0, p.choose("a"));
    }

    @Test
    void testChoose_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        Assertions.assertEquals(0, p.choose("a", "b", "c"));
    }

    @Test
    void testChoose_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, p.choose("c", "b", "a"));
    }

    @Test
    void testChoose_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        Assertions.assertEquals(0, p.choose("b"));
    }

    @Test
    void testChoose_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        Assertions.assertEquals(1, p.choose("a", "b", "c"));
    }

    @Test
    void testChoose_6_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, p.choose("c", "b", "a"));
    }

    @Test
    void testChooseIgnoreCase_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        Assertions.assertEquals(0, p.chooseIgnoreCase("A"));
    }

    @Test
    void testChooseIgnoreCase_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        Assertions.assertEquals(0, p.chooseIgnoreCase("A", "b", "C"));
    }

    @Test
    void testChooseIgnoreCase_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, p.chooseIgnoreCase("C", "b", "A"));
    }

    @Test
    void testChooseIgnoreCase_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        Assertions.assertEquals(0, p.chooseIgnoreCase("b"));
    }

    @Test
    void testChooseIgnoreCase_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        Assertions.assertEquals(1, p.chooseIgnoreCase("A", "b", "C"));
    }

    @Test
    void testChooseIgnoreCase_6_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, p.chooseIgnoreCase("C", "b", "A"));
    }

    @Test
    void testTryChoose_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        Assertions.assertEquals(0, p.tryChoose("a"));
    }

    @Test
    void testTryChoose_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        Assertions.assertEquals(0, p.tryChoose("a", "b", "c"));
    }

    @Test
    void testTryChoose_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, p.tryChoose("c", "b", "a"));
    }

    @Test
    void testTryChoose_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        Assertions.assertEquals(0, p.tryChoose("b"));
    }

    @Test
    void testTryChoose_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        Assertions.assertEquals(1, p.tryChoose("a", "b", "c"));
    }

    @Test
    void testTryChoose_6_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, p.tryChoose("c", "b", "a"));
    }

    @Test
    void testTryChoose_7_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, p.tryChoose("A", "B", "C"));
    }

    @Test
    void testTryChoose_8_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-1, p.tryChoose());
    }

    @Test
    void testTryChoose_9_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, p.tryChoose((String) null));
    }

    @Test
    void testTryChooseIgnoreCase_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        Assertions.assertEquals(0, p.tryChooseIgnoreCase("a"));
    }

    @Test
    void testTryChooseIgnoreCase_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        Assertions.assertEquals(0, p.tryChooseIgnoreCase("A", "B", "C"));
    }

    @Test
    void testTryChooseIgnoreCase_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, p.tryChooseIgnoreCase("C", "b", "A"));
    }

    @Test
    void testTryChooseIgnoreCase_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        Assertions.assertEquals(0, p.tryChooseIgnoreCase("B"));
    }

    @Test
    void testTryChooseIgnoreCase_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        Assertions.assertEquals(1, p.tryChooseIgnoreCase("a", "B", "c"));
    }

    @Test
    void testTryChooseIgnoreCase_6_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, p.tryChooseIgnoreCase("c", "b", "a"));
    }

    @Test
    void testTryChooseIgnoreCase_7_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, p.tryChooseIgnoreCase("X", "Y", "Z"));
    }

    @Test
    void testTryChooseIgnoreCase_8_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-1, p.tryChooseIgnoreCase());
    }

    @Test
    void testTryChooseIgnoreCase_9_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.next(1);

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, p.tryChooseIgnoreCase((String) null));
    }

    @Test
    void testUnexpectedToken_causeArg_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        final Exception cause = new Exception("test");

        // act/assert
        p.nextLine();

        final IllegalStateException exc = p.unexpectedToken("test", cause);
        // removed other assertion
        Assertions.assertSame(cause, exc.getCause());
    }

    @Test
    void testTokenError_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\nbc");
        p.nextLine();
        p.next(1);
        p.readChar();

        // act/assert
        final IllegalStateException exc = p.tokenError("test message");

        Assertions.assertEquals("Parsing failed at line 2, column 1: test message", exc.getMessage());
    }

    @Test
    void testTokenError_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\nbc");
        p.nextLine();
        p.next(1);
        p.readChar();

        // act/assert
        final IllegalStateException exc = p.tokenError("test message");

        // removed other assertion
        Assertions.assertNull(exc.getCause());
    }

    @Test
    void testTokenError_noTokenSet_1_oe() {
        // arrange
        final SimpleTextParser p = parser("ab\nc");
        p.readChar();

        // act/assert
        final IllegalStateException exc = p.tokenError("test message");

        Assertions.assertEquals("Parsing failed at line 1, column 2: test message", exc.getMessage());
    }

    @Test
    void testTokenError_noTokenSet_2_oe() {
        // arrange
        final SimpleTextParser p = parser("ab\nc");
        p.readChar();

        // act/assert
        final IllegalStateException exc = p.tokenError("test message");

        // removed other assertion
        Assertions.assertNull(exc.getCause());
    }

    @Test
    void testTokenError_withCause_1_oe() {
        // arrange
        SimpleTextParser p = parser("a\nbc");
        p.nextLine();
        p.next(1);
        p.readChar();

        final Exception cause = new Exception("test");

        // act/assert
        final IllegalStateException exc = p.tokenError("test message", cause);

        Assertions.assertEquals("Parsing failed at line 2, column 1: test message", exc.getMessage());
    }

    @Test
    void testTokenError_withCause_2_oe() {
        // arrange
        SimpleTextParser p = parser("a\nbc");
        p.nextLine();
        p.next(1);
        p.readChar();

        final Exception cause = new Exception("test");

        // act/assert
        final IllegalStateException exc = p.tokenError("test message", cause);

        // removed other assertion
        Assertions.assertSame(cause, exc.getCause());
    }

    @Test
    void testParseError_currentLineCol_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\nbc");
        p.discard(ch -> ch != 'b');

        // act
        final IllegalStateException exc = p.parseError("test message");

        Assertions.assertEquals("Parsing failed at line 2, column 1: test message", exc.getMessage());
    }

    @Test
    void testParseError_currentLineCol_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\nbc");
        p.discard(ch -> ch != 'b');

        // act
        final IllegalStateException exc = p.parseError("test message");

        // removed other assertion
        Assertions.assertNull(exc.getCause());
    }

    @Test
    void testParseError_currentLineCol_withCause_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.readChar();
        final Exception cause = new Exception("test");

        // act
        final IllegalStateException exc = p.parseError("test message", cause);

        Assertions.assertEquals("Parsing failed at line 1, column 2: test message", exc.getMessage());
    }

    @Test
    void testParseError_currentLineCol_withCause_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.readChar();
        final Exception cause = new Exception("test");

        // act
        final IllegalStateException exc = p.parseError("test message", cause);

        // removed other assertion
        Assertions.assertSame(cause, exc.getCause());
    }

    @Test
    void testParseError_givenLineCol_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act
        final IllegalStateException exc = p.parseError(5, 6, "test message");

        Assertions.assertEquals("Parsing failed at line 5, column 6: test message", exc.getMessage());
    }

    @Test
    void testParseError_givenLineCol_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act
        final IllegalStateException exc = p.parseError(5, 6, "test message");

        // removed other assertion
        Assertions.assertNull(exc.getCause());
    }

    @Test
    void testParseError_givenLineCol_withCause_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        final Exception cause = new Exception("test");

        // act
        final IllegalStateException exc = p.parseError(5, 6, "test message", cause);

        Assertions.assertEquals("Parsing failed at line 5, column 6: test message", exc.getMessage());
    }

    @Test
    void testParseError_givenLineCol_withCause_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        final Exception cause = new Exception("test");

        // act
        final IllegalStateException exc = p.parseError(5, 6, "test message", cause);

        // removed other assertion
        Assertions.assertSame(cause, exc.getCause());
    }

    @Test
    void testCharacterSequence_1_oe_1_oe() {
        // act/assert
                final SimpleTextParser parser0 = parser("");
        final String expected0 = "";
        char expectedChar0;
                String msg0;
                for (int i0 = 0; i0 < expected0.length(); ++i0) {
                    expectedChar0 = expected0.charAt(i0);
        
                    msg0 = "Failed at index " + i0 + ":";
        
                    Assertions.assertEquals(expectedChar0, parser0.peekChar(), msg0);
    }
    }

    @Test
    void testCharacterSequence_1_oe_2_oe() {
        // act/assert
                final SimpleTextParser parser0 = parser("");
        final String expected0 = "";
        char expectedChar0;
                String msg0;
                for (int i0 = 0; i0 < expected0.length(); ++i0) {
                    expectedChar0 = expected0.charAt(i0);
        
                    msg0 = "Failed at index " + i0 + ":";
        
                    // removed other assertion
                    Assertions.assertEquals(expectedChar0, parser0.peekChar(), msg0);
    }
    }

    @Test
    void testCharacterSequence_1_oe_3_oe() {
        // act/assert
                final SimpleTextParser parser0 = parser("");
        final String expected0 = "";
        char expectedChar0;
                String msg0;
                for (int i0 = 0; i0 < expected0.length(); ++i0) {
                    expectedChar0 = expected0.charAt(i0);
        
                    msg0 = "Failed at index " + i0 + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    Assertions.assertTrue(parser0.hasMoreCharacters());
    }
    }

    @Test
    void testCharacterSequence_1_oe_4_oe() {
        // act/assert
                final SimpleTextParser parser0 = parser("");
        final String expected0 = "";
        char expectedChar0;
                String msg0;
                for (int i0 = 0; i0 < expected0.length(); ++i0) {
                    expectedChar0 = expected0.charAt(i0);
        
                    msg0 = "Failed at index " + i0 + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    Assertions.assertEquals(expectedChar0, parser0.readChar(), msg0);
    }
    }

    @Test
    void testCharacterSequence_1_oe_5_oe() {
        // act/assert
                final SimpleTextParser parser0 = parser("");
        final String expected0 = "";
        char expectedChar0;
                String msg0;
                for (int i0 = 0; i0 < expected0.length(); ++i0) {
                    expectedChar0 = expected0.charAt(i0);
        
                    msg0 = "Failed at index " + i0 + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                Assertions.assertFalse(parser0.hasMoreCharacters());
    }

    @Test
    void testCharacterSequence_1_oe_6_oe() {
        // act/assert
                final SimpleTextParser parser0 = parser("");
        final String expected0 = "";
        char expectedChar0;
                String msg0;
                for (int i0 = 0; i0 < expected0.length(); ++i0) {
                    expectedChar0 = expected0.charAt(i0);
        
                    msg0 = "Failed at index " + i0 + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                // removed other assertion
                Assertions.assertEquals(-1, parser0.peekChar());
    }

    @Test
    void testCharacterSequence_1_oe_7_oe() {
        // act/assert
                final SimpleTextParser parser0 = parser("");
        final String expected0 = "";
        char expectedChar0;
                String msg0;
                for (int i0 = 0; i0 < expected0.length(); ++i0) {
                    expectedChar0 = expected0.charAt(i0);
        
                    msg0 = "Failed at index " + i0 + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                // removed other assertion
                // removed other assertion
                Assertions.assertEquals(-1, parser0.peekChar());
    }

    @Test
    void testCharacterSequence_1_oe_8_oe() {
        // act/assert
                final SimpleTextParser parser0 = parser("");
        final String expected0 = "";
        char expectedChar0;
                String msg0;
                for (int i0 = 0; i0 < expected0.length(); ++i0) {
                    expectedChar0 = expected0.charAt(i0);
        
                    msg0 = "Failed at index " + i0 + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                // removed other assertion
                // removed other assertion
                // removed other assertion
                Assertions.assertEquals(-1, parser0.readChar());
    }

    @Test
    void testCharacterSequence_2_oe_3_oe() {
        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = parser("abc def");
        final String expected0 = "abc def";
        char expectedChar0;
                String msg0;
                for (int i0 = 0; i0 < expected0.length(); ++i0) {
                    expectedChar0 = expected0.charAt(i0);
        
                    msg0 = "Failed at index " + i0 + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    Assertions.assertTrue(parser0.hasMoreCharacters());
    }
    }

    @Test
    void testCharacterSequence_2_oe_4_oe() {
        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = parser("abc def");
        final String expected0 = "abc def";
        char expectedChar0;
                String msg0;
                for (int i0 = 0; i0 < expected0.length(); ++i0) {
                    expectedChar0 = expected0.charAt(i0);
        
                    msg0 = "Failed at index " + i0 + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    Assertions.assertEquals(expectedChar0, parser0.readChar(), msg0);
    }
    }

    @Test
    void testCharacterPosition_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser(
                "a b\n" +
                "\r\n" +
                "d \r" +
                "e");

        // act/assert
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testCharacterPosition_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser(
                "a b\n" +
                "\r\n" +
                "d \r" +
                "e");

        // act/assert
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser(
                "a b\n" +
                "\r\n" +
                "d \r" +
                "e");

        // act/assert
        // removed other assertion
                final int expected0 = 'a';
        final int actual0 = p.readChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testCharacterPosition_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser(
                "a b\n" +
                "\r\n" +
                "d \r" +
                "e");

        // act/assert
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testCharacterPosition_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser(
                "a b\n" +
                "\r\n" +
                "d \r" +
                "e");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 3;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testCharacterPosition_7_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser(
                "a b\n" +
                "\r\n" +
                "d \r" +
                "e");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 4;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testCharacterPosition_9_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser(
                "a b\n" +
                "\r\n" +
                "d \r" +
                "e");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_13_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser(
                "a b\n" +
                "\r\n" +
                "d \r" +
                "e");

        // act/assert
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

                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_19_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser(
                "a b\n" +
                "\r\n" +
                "d \r" +
                "e");

        // act/assert
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
        // removed other assertion

                final SimpleTextParser parser0 = p;
        final int line0 = 4;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_givenPosition_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testCharacterPosition_givenPosition_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_givenPosition_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
        // removed other assertion

        p.setLineNumber(10);
        p.setColumnNumber(3);

                final SimpleTextParser parser0 = p;
        final int line0 = 10;
        final int col0 = 3;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testCharacterPosition_givenPosition_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
        // removed other assertion

        p.setLineNumber(10);
        p.setColumnNumber(3);

                final SimpleTextParser parser0 = p;
        final int line0 = 10;
        final int col0 = 3;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_givenPosition_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
        // removed other assertion

        p.setLineNumber(10);
        p.setColumnNumber(3);

        // removed other assertion

        p.discard(4);

                final SimpleTextParser parser0 = p;
        final int line0 = 11;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testCharacterPosition_givenPosition_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
        // removed other assertion

        p.setLineNumber(10);
        p.setColumnNumber(3);

        // removed other assertion

        p.discard(4);

                final SimpleTextParser parser0 = p;
        final int line0 = 11;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_givenPosition_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
        // removed other assertion

        p.setLineNumber(10);
        p.setColumnNumber(3);

        // removed other assertion

        p.discard(4);

        // removed other assertion

        p.discard(3);

                final SimpleTextParser parser0 = p;
        final int line0 = 11;
        final int col0 = 4;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testCharacterPosition_givenPosition_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
        // removed other assertion

        p.setLineNumber(10);
        p.setColumnNumber(3);

        // removed other assertion

        p.discard(4);

        // removed other assertion

        p.discard(3);

                final SimpleTextParser parser0 = p;
        final int line0 = 11;
        final int col0 = 4;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testHasMoreCharacters_3_oe_1_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser nonEmpty = parser("a");

        // act/assert
        // removed other assertion

        // removed other assertion
                final int expected0 = 'a';
        final int actual0 = nonEmpty.readChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testHasMoreCharactersOnLine_3_oe_1_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser singleLine = parser("a");
        final SimpleTextParser multiLine = parser("a\r\nb\rc\n\n");

        // act/assert
        // removed other assertion

        // removed other assertion
                final int expected0 = 'a';
        final int actual0 = singleLine.readChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testHasMoreCharactersOnLine_6_oe_1_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser singleLine = parser("a");
        final SimpleTextParser multiLine = parser("a\r\nb\rc\n\n");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final int expected0 = 'a';
        final int actual0 = multiLine.readChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testBasicTokenMethods_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser0 = p;
        final String token0 = null;
        final int line0 = -1;
        final int col0 = -1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testBasicTokenMethods_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser0 = p;
        final String token0 = null;
        final int line0 = -1;
        final int col0 = -1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testBasicTokenMethods_1_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser0 = p;
        final String token0 = null;
        final int line0 = -1;
        final int col0 = -1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testBasicTokenMethods_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.next(1);
        final String token0 = "a";
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testBasicTokenMethods_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.next(1);
        final String token0 = "a";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testBasicTokenMethods_3_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.next(1);
        final String token0 = "a";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testBasicTokenMethods_5_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.next(3);
        final String token0 = "bcd";
        final int line0 = 1;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testBasicTokenMethods_7_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.next(5);
        final String token0 = "ef\r\n\r";
        final int line0 = 1;
        final int col0 = 5;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testBasicTokenMethods_9_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.next(0);
        final String token0 = "";
        final int line0 = 3;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testBasicTokenMethods_9_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.next(0);
        final String token0 = "";
        final int line0 = 3;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testBasicTokenMethods_11_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
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

                final SimpleTextParser parser0 = p.next(1);
        final String token0 = " ";
        final int line0 = 3;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNext_lenArg_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser0 = p.next(0);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNext_lenArg_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser0 = p.next(0);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNext_lenArg_1_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser0 = p.next(0);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNext_lenArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = p.next(4);
        final String token0 = "abcd";
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNext_lenArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = p.next(4);
        final String token0 = "abcd";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNext_lenArg_2_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = p.next(4);
        final String token0 = "abcd";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNext_lenArg_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser0 = p.next(6);
        final String token0 = "ef\r\n\r ";
        final int line0 = 1;
        final int col0 = 5;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_1_oe_1_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, 0);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNextWithLineContinuation_lenArg_1_oe_2_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, 0);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_1_oe_3_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, 0);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_2_oe_1_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, 5);
        final String token0 = "a\\bcd";
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNextWithLineContinuation_lenArg_2_oe_2_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, 5);
        final String token0 = "a\\bcd";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_2_oe_3_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, 5);
        final String token0 = "a\\bcd";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_3_oe_2_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, 3);
        final String token0 = "ef\r";
        final int line0 = 1;
        final int col0 = 6;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_4_oe_3_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, 100);
        final String token0 = " ghij";
        final int line0 = 3;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNext_predicateArg_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
                final SimpleTextParser parser0 = p.next(c -> false);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNext_predicateArg_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
                final SimpleTextParser parser0 = p.next(c -> false);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNext_predicateArg_1_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
                final SimpleTextParser parser0 = p.next(c -> false);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNext_predicateArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser0 = p.next(Character::isAlphabetic);
        final String token0 = "a";
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNext_predicateArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser0 = p.next(Character::isAlphabetic);
        final String token0 = "a";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNext_predicateArg_2_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser0 = p.next(Character::isAlphabetic);
        final String token0 = "a";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNext_predicateArg_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.next(Character::isAlphabetic);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNext_predicateArg_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.next(Character::isWhitespace);
        final String token0 = "\n ";
        final int line0 = 1;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNext_predicateArg_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.next(Character::isWhitespace);
        final String token0 = "";
        final int line0 = 2;
        final int col0 = 2;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNext_predicateArg_7_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.next(Character::isDigit);
        final String token0 = "";
        final int line0 = 2;
        final int col0 = 5;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNext_predicateArg_9_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.next(Character::isWhitespace);
        final String token0 = "";
        final int line0 = 3;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNext_predicateArg_9_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.next(Character::isWhitespace);
        final String token0 = "";
        final int line0 = 3;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNext_predicateArg_10_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.next(c -> true);
        final String token0 = "def";
        final int line0 = 3;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_1_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, c -> false);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_1_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, c -> false);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_1_oe_3_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, c -> false);
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_2_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, Character::isAlphabetic);
        final String token0 = "a";
        final int line0 = 2;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_2_oe_3_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, Character::isAlphabetic);
        final String token0 = "a";
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_5_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token0 = "";
        final int line0 = 3;
        final int col0 = 2;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_7_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, Character::isDigit);
        final String token0 = "";
        final int line0 = 5;
        final int col0 = 3;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_9_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token0 = "";
        final int line0 = 6;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_9_oe_3_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token0 = "";
        final int line0 = 6;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_10_oe_3_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.nextWithLineContinuation(cont, c -> true);
        final String token0 = "d|ef";
        final int line0 = 6;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextLine_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
                final SimpleTextParser parser0 = p.nextLine();
        final String token0 = "a";
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNextLine_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
                final SimpleTextParser parser0 = p.nextLine();
        final String token0 = "a";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNextLine_1_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
                final SimpleTextParser parser0 = p.nextLine();
        final String token0 = "a";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextLine_2_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser0 = p.nextLine();
        final String token0 = " 012";
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextLine_3_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
                final SimpleTextParser parser0 = p.nextLine();
        final String token0 = "ef";
        final int line0 = 3;
        final int col0 = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextLine_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
        // removed other assertion

                final SimpleTextParser parser0 = p.nextLine();
        final String token0 = "";
        final int line0 = 4;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNextLine_6_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
        // removed other assertion

        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.nextLine();
        final String token0 = null;
        final int line0 = 5;
        final int col0 = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextAlphanumeric_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
                final SimpleTextParser parser0 = p.nextAlphanumeric();
        final String token0 = "a10Fd";
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(token0, parser0.getCurrentToken(), "Unexpected token0");
    }

    @Test
    void testNextAlphanumeric_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
                final SimpleTextParser parser0 = p.nextAlphanumeric();
        final String token0 = "a10Fd";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNextAlphanumeric_1_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
                final SimpleTextParser parser0 = p.nextAlphanumeric();
        final String token0 = "a10Fd";
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testNextAlphanumeric_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.nextAlphanumeric();
        final String token0 = "X23456789";
        final int line0 = 1;
        final int col0 = 7;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNextAlphanumeric_5_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.nextAlphanumeric();
        final String token0 = "0";
        final int line0 = 1;
        final int col0 = 17;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNextAlphanumeric_6_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser0 = p.nextAlphanumeric();
        final String token0 = "";
        final int line0 = 1;
        final int col0 = 18;
        // removed other assertion
                Assertions.assertEquals(line0, parser0.getCurrentTokenLineNumber(), "Unexpected token0 line0 number");
    }

    @Test
    void testNextAlphanumeric_8_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p.nextAlphanumeric();
        final String token0 = "y";
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col0, parser0.getCurrentTokenColumnNumber(), "Unexpected token0 column number");
    }

    @Test
    void testDiscard_lenArg_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
                final int expected0 = '\n';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_lenArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_lenArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_lenArg_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
                final int expected0 = 'a';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_lenArg_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_lenArg_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_lenArg_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
                final int expected0 = '2';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_lenArg_6_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 2;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_lenArg_6_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_lenArg_7_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
        // removed other assertion

        p.discard(100);
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_lenArg_8_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
        // removed other assertion

        p.discard(100);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 5;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_lenArg_8_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
        // removed other assertion

        p.discard(100);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 5;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_lenArg_9_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
        // removed other assertion

        p.discard(100);
        // removed other assertion
        // removed other assertion

        p.discard(0);
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_lenArg_10_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
        // removed other assertion

        p.discard(100);
        // removed other assertion
        // removed other assertion

        p.discard(0);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 5;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_lenArg_10_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
        // removed other assertion

        p.discard(100);
        // removed other assertion
        // removed other assertion

        p.discard(0);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 5;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_lenArg_11_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
        // removed other assertion

        p.discard(100);
        // removed other assertion
        // removed other assertion

        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(100);
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_lenArg_12_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
        // removed other assertion

        p.discard(100);
        // removed other assertion
        // removed other assertion

        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(100);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 5;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_lenArg_12_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(1);
        // removed other assertion
        // removed other assertion

        p.discard(8);
        // removed other assertion
        // removed other assertion

        p.discard(100);
        // removed other assertion
        // removed other assertion

        p.discard(0);
        // removed other assertion
        // removed other assertion

        p.discard(100);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 5;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_1_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
                final int expected0 = '\n';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_2_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_2_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_3_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
                final int expected0 = '|';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_4_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_4_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_5_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
                final int expected0 = '1';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_6_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 6;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_6_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 6;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_7_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_8_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 8;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_8_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 8;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_9_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 0);
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_10_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 8;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_10_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 8;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_11_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_12_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 8;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_12_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 1);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 8);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, 100);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 8;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_predicateArg_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
                final int expected0 = 'a';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_predicateArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_predicateArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_predicateArg_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
                final int expected0 = ' ';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_predicateArg_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 4;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_predicateArg_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 4;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_predicateArg_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
                final int expected0 = ' ';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_predicateArg_6_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 4;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_predicateArg_6_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 4;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_predicateArg_7_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
                final int expected0 = 'c';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_predicateArg_8_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 5;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_predicateArg_8_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 5;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_predicateArg_9_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> c != 'd');
                final int expected0 = 'd';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_predicateArg_10_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> c != 'd');
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 4;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_predicateArg_10_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> c != 'd');
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 4;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_predicateArg_11_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discard(c -> true);
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_predicateArg_12_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discard(c -> true);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 5;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_predicateArg_12_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discard(c -> true);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 5;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_predicateArg_13_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discard(c -> true);
        // removed other assertion
        // removed other assertion

        p.discard(c -> true);
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscard_predicateArg_14_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discard(c -> true);
        // removed other assertion
        // removed other assertion

        p.discard(c -> true);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 5;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscard_predicateArg_14_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discard(c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discard(c -> true);
        // removed other assertion
        // removed other assertion

        p.discard(c -> true);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 5;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_1_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
                final int expected0 = 'a';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_2_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_2_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_3_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
                final int expected0 = ' ';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_4_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 2;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_4_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_5_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
                final int expected0 = ' ';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_6_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 2;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_6_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_7_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
                final int expected0 = '|';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_8_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 3;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_8_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 3;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_9_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> c != 'd');
                final int expected0 = 'd';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_10_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> c != 'd');
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 7;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_10_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> c != 'd');
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 7;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_11_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> true);
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_12_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> true);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 8;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_12_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> true);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 8;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_13_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> true);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> true);
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_14_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> true);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> true);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 8;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_14_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> !Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isDigit(c)); // should not advance
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> c != 'd');
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> true);
        // removed other assertion
        // removed other assertion

        p.discardWithLineContinuation(cont, c -> true);
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 8;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWhitespace_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardWhitespace_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWhitespace_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
                final int expected0 = 'a';
        final int actual0 = p.readChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardLineWhitespace_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardLineWhitespace_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLineWhitespace_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
        // removed other assertion
                final int expected0 = 'a';
        final int actual0 = p.readChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardLineWhitespace_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 3;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardLineWhitespace_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 3;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardLineWhitespace_7_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardNewLineSequence_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardNewLineSequence_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardNewLineSequence_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
        // removed other assertion
                final int expected0 = 'a';
        final int actual0 = p.readChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardNewLineSequence_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();

        p.discardNewLineSequence();
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardNewLineSequence_5_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();

        p.discardNewLineSequence();
        // removed other assertion
        // removed other assertion

        p.discardNewLineSequence();
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardNewLineSequence_9_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();

        p.discardNewLineSequence();
        // removed other assertion
        // removed other assertion

        p.discardNewLineSequence();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();

        p.discardNewLineSequence();
        // removed other assertion
        // removed other assertion

        p.discardNewLineSequence();
                final SimpleTextParser parser0 = p;
        final int line0 = 4;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLine_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
                final int expected0 = '\r';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardLine_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardLine_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 2;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLine_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
                final int expected0 = ' ';
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardLine_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardLine_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLine_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 7;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardLine_5_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 7;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLine_6_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testDiscardLine_7_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 7;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testDiscardLine_7_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
                final SimpleTextParser parser0 = p;
        final int line0 = 3;
        final int col0 = 7;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLine_8_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
        // removed other assertion

        p.discardLine();
        // removed other assertion
                final int expected0 = EOF;
        final int actual0 = p.peekChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testPeek_lenArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testPeek_lenArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testPeek_lenArg_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testPeek_lenArg_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testPeek_lenArg_6_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testPeek_lenArg_6_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testPeek_lenArg_8_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testPeek_lenArg_8_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testPeek_lenArg_10_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testPeek_lenArg_10_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testPeek_lenArg_11_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final int expected0 = 'b';
        final int actual0 = p.readChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testPeek_predicateArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testPeek_predicateArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 1;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testPeek_predicateArg_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testPeek_predicateArg_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testPeek_predicateArg_6_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        Assertions.assertEquals(line0, parser0.getLineNumber(), "Unexpected line0 number");
    }

    @Test
    void testPeek_predicateArg_6_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser0 = p;
        final int line0 = 1;
        final int col0 = 2;
        // removed other assertion
                Assertions.assertEquals(col0, parser0.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testPeek_predicateArg_7_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        p.readChar();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final int expected0 = 'b';
        final int actual0 = p.readChar();
        final String expectedStr0 = describeChar(expected0);
                final String actualStr0 = describeChar(actual0);
        
                Assertions.assertEquals(expected0, actual0, "Expected [" + expectedStr0 + "] but was [" + actualStr0 + "];");
    }

    @Test
    void testCharacterPredicates_1_oe_1_oe() {
        // act/assert
                final IntPredicate pred0 = SimpleTextParser::isWhitespace;
        final String chars0 = " \t\n\r";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to match [" + describeChar(ch0) + "]";
                    Assertions.assertTrue(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_2_oe_1_oe() {
        // act/assert
        // removed other assertion
                final IntPredicate pred0 = SimpleTextParser::isWhitespace;
        final String chars0 = "abcABC<>,./?:;'\"[]{}`~!@#$%^&*()_+-=";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to not match [" + describeChar(ch0) + "]";
                    Assertions.assertFalse(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_3_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

                final IntPredicate pred0 = SimpleTextParser::isNotWhitespace;
        final String chars0 = "abcABC<>,./?:;'\"[]{}`~!@#$%^&*()_+-=";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to match [" + describeChar(ch0) + "]";
                    Assertions.assertTrue(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_4_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final IntPredicate pred0 = SimpleTextParser::isNotWhitespace;
        final String chars0 = " \t\n\r";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to not match [" + describeChar(ch0) + "]";
                    Assertions.assertFalse(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_5_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final IntPredicate pred0 = SimpleTextParser::isLineWhitespace;
        final String chars0 = " \t";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to match [" + describeChar(ch0) + "]";
                    Assertions.assertTrue(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_6_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final IntPredicate pred0 = SimpleTextParser::isLineWhitespace;
        final String chars0 = "\n\rabcABC<>,./?:;'\"[]{}`~!@#$%^&*()_+-=";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to not match [" + describeChar(ch0) + "]";
                    Assertions.assertFalse(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_7_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final IntPredicate pred0 = SimpleTextParser::isNewLinePart;
        final String chars0 = "\n\r";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to match [" + describeChar(ch0) + "]";
                    Assertions.assertTrue(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_8_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final IntPredicate pred0 = SimpleTextParser::isNewLinePart;
        final String chars0 = " \tabcABC<>,./?:;'\"[]{}`~!@#$%^&*()_+-=";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to not match [" + describeChar(ch0) + "]";
                    Assertions.assertFalse(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_9_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final IntPredicate pred0 = SimpleTextParser::isNotNewLinePart;
        final String chars0 = " \tabcABC<>,./?:;'\"[]{}`~!@#$%^&*()_+-=";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to match [" + describeChar(ch0) + "]";
                    Assertions.assertTrue(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_10_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final IntPredicate pred0 = SimpleTextParser::isNotNewLinePart;
        final String chars0 = "\n\r";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to not match [" + describeChar(ch0) + "]";
                    Assertions.assertFalse(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_11_oe_1_oe() {
        // act/assert
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

                final IntPredicate pred0 = SimpleTextParser::isAlphanumeric;
        final String chars0 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to match [" + describeChar(ch0) + "]";
                    Assertions.assertTrue(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_12_oe_1_oe() {
        // act/assert
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
                final IntPredicate pred0 = SimpleTextParser::isAlphanumeric;
        final String chars0 = " \t\n\r./?:;'\\\"[]{}`~!@#$%^&*()_+-=";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to not match [" + describeChar(ch0) + "]";
                    Assertions.assertFalse(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_13_oe_1_oe() {
        // act/assert
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

                final IntPredicate pred0 = SimpleTextParser::isNotAlphanumeric;
        final String chars0 = " \t\n\r./?:;'\\\"[]{}`~!@#$%^&*()_+-=";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to match [" + describeChar(ch0) + "]";
                    Assertions.assertTrue(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_14_oe_1_oe() {
        // act/assert
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
                final IntPredicate pred0 = SimpleTextParser::isNotAlphanumeric;
        final String chars0 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to not match [" + describeChar(ch0) + "]";
                    Assertions.assertFalse(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_15_oe_1_oe() {
        // act/assert
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

                final IntPredicate pred0 = SimpleTextParser::isIntegerPart;
        final String chars0 = "0123456789+-";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to match [" + describeChar(ch0) + "]";
                    Assertions.assertTrue(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_16_oe_1_oe() {
        // act/assert
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
                final IntPredicate pred0 = SimpleTextParser::isIntegerPart;
        final String chars0 = " \t\n\r./?:;'\\\"[]{}`~!@#$%^&*()_=abcdeABCDE";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to not match [" + describeChar(ch0) + "]";
                    Assertions.assertFalse(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_17_oe_1_oe() {
        // act/assert
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

                final IntPredicate pred0 = SimpleTextParser::isDecimalPart;
        final String chars0 = "0123456789+-.eE";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to match [" + describeChar(ch0) + "]";
                    Assertions.assertTrue(pred0.test(ch0), msg0);
    }
    }

    @Test
    void testCharacterPredicates_18_oe_1_oe() {
        // act/assert
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
                final IntPredicate pred0 = SimpleTextParser::isDecimalPart;
        final String chars0 = " \t\n\r/?:;'\\\"[]{}`~!@#$%^&*()_=abcdABCD";
        for (char ch0 : chars0.toCharArray()) {
                    final String msg0 = "Expected predicate to not match [" + describeChar(ch0) + "]";
                    Assertions.assertFalse(pred0.test(ch0), msg0);
    }
    }

@Test
    void testMaxStringLength_illegalArg_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        try {
     p.setMaxStringLength(-1);
    fail("Expected IllegalArgumentException with message: " + "Maximum string length cannot be less than zero; was -1");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testGetCurrentTokenAsDouble_failures_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        try {
     p.getCurrentTokenAsDouble();
    fail("Expected IllegalStateException with message: " + "No token has been read from the character stream");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsDouble_failures_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        try {
     p.getCurrentTokenAsDouble();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected double but found [abc]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsDouble_failures_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        // removed other assertion

        p.nextAlphanumeric();
        try {
     p.getCurrentTokenAsDouble();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 4: expected double but found end of line");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsDouble_failures_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        // removed other assertion

        p.nextAlphanumeric();
        // removed other assertion

        p.discardLine()
            .next(c -> c != 'a');
        try {
     p.getCurrentTokenAsDouble();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 2, column 1: expected double but found [1.1.1]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsDouble_failures_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        // removed other assertion

        p.nextAlphanumeric();
        // removed other assertion

        p.discardLine()
            .next(c -> c != 'a');
        // removed other assertion

        p.next(Character::isDigit);
        try {
     p.getCurrentTokenAsDouble();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 2, column 6: expected double but found empty token followed by [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsDouble_failures_6_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        // removed other assertion

        p.nextAlphanumeric();
        // removed other assertion

        p.discardLine()
            .next(c -> c != 'a');
        // removed other assertion

        p.next(Character::isDigit);
        // removed other assertion

        p.nextLine();
        try {
     p.getCurrentTokenAsDouble();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 2, column 6: expected double but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsDouble_failures_7_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        // removed other assertion

        p.nextAlphanumeric();
        // removed other assertion

        p.discardLine()
            .next(c -> c != 'a');
        // removed other assertion

        p.next(Character::isDigit);
        // removed other assertion

        p.nextLine();
        // removed other assertion

        p.nextLine();
        try {
     p.getCurrentTokenAsDouble();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 2, column 7: expected double but found end of content");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsInt_failures_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        try {
     p.getCurrentTokenAsInt();
    fail("Expected IllegalStateException with message: " + "No token has been read from the character stream");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsInt_failures_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        try {
     p.getCurrentTokenAsInt();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected integer but found [abc]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsInt_failures_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        // removed other assertion

        p.nextAlphanumeric();
        try {
     p.getCurrentTokenAsInt();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 4: expected integer but found end of line");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsInt_failures_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        // removed other assertion

        p.nextAlphanumeric();
        // removed other assertion

        p.discardLine()
            .next(c -> c != 'a');
        try {
     p.getCurrentTokenAsInt();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 2, column 1: expected integer but found [1.1.1]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsInt_failures_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        // removed other assertion

        p.nextAlphanumeric();
        // removed other assertion

        p.discardLine()
            .next(c -> c != 'a');
        // removed other assertion

        p.next(Character::isDigit);
        try {
     p.getCurrentTokenAsInt();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 2, column 6: expected integer but found empty token followed by [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsInt_failures_6_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        // removed other assertion

        p.nextAlphanumeric();
        // removed other assertion

        p.discardLine()
            .next(c -> c != 'a');
        // removed other assertion

        p.next(Character::isDigit);
        // removed other assertion

        p.nextLine();
        try {
     p.getCurrentTokenAsInt();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 2, column 6: expected integer but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testGetCurrentTokenAsInt_failures_7_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        // removed other assertion

        p.next(SimpleTextParser::isNotNewLinePart);
        // removed other assertion

        p.nextAlphanumeric();
        // removed other assertion

        p.discardLine()
            .next(c -> c != 'a');
        // removed other assertion

        p.next(Character::isDigit);
        // removed other assertion

        p.nextLine();
        // removed other assertion

        p.nextLine();
        try {
     p.getCurrentTokenAsInt();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 2, column 7: expected integer but found end of content");
} catch (IllegalStateException e) {
}
    }

@Test
    void testNext_lenArg_invalidArg_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.setMaxStringLength(2);

        // act/assert
        try {
     p.next(-1);
    fail("Expected IllegalArgumentException with message: " + "Requested string length cannot be negative; was -1");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testNext_lenArg_invalidArg_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.setMaxStringLength(2);

        // act/assert
        // removed other assertion

        try {
     p.next(3);
    fail("Expected IllegalArgumentException with message: " + "Requested string length of 3 exceeds maximum value of 2");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testNext_predicateArg_exceedsMaxStringLength_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");
        p.setMaxStringLength(4);

        // act/assert
        try {
     p.next(c -> !Character.isWhitespace(c));
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: string length exceeds maximum value of 4");
} catch (IllegalStateException e) {
}
    }

@Test
    void testPeek_lenArg_invalidArg_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");
        p.setMaxStringLength(4);

        // act/assert
        try {
     p.peek(-1);
    fail("Expected IllegalArgumentException with message: " + "Requested string length cannot be negative; was -1");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testPeek_lenArg_invalidArg_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");
        p.setMaxStringLength(4);

        // act/assert
        // removed other assertion

        try {
     p.peek(6);
    fail("Expected IllegalArgumentException with message: " + "Requested string length of 6 exceeds maximum value of 4");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testPeek_predicateArg_exceedsMaxStringLength_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\n  abcdefg");
        p.setMaxStringLength(4);
        p.discardLine()
            .discard(SimpleTextParser::isWhitespace);

        // act/assert
        try {
     p.peek(SimpleTextParser::isNotWhitespace);
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 2, column 3: string length exceeds maximum value of 4");
} catch (IllegalStateException e) {
}
    }

@Test
    void testMatch_failure_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        try {
     p.match("empty");
    fail("Expected IllegalStateException with message: " + "No token has been read from the character stream");
} catch (IllegalStateException e) {
}
    }

@Test
    void testMatch_failure_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        // removed other assertion

        p.next(1);
        try {
     p.match("b");
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected [b] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testMatch_failure_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        // removed other assertion

        p.next(1);
        // removed other assertion

        try {
     p.match("A");
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected [A] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testMatch_failure_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        // removed other assertion

        p.next(1);
        // removed other assertion

        // removed other assertion

        try {
     p.match(null);
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected [null] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testMatchIgnoreCase_failure_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        try {
     p.matchIgnoreCase("empty");
    fail("Expected IllegalStateException with message: " + "No token has been read from the character stream");
} catch (IllegalStateException e) {
}
    }

@Test
    void testMatchIgnoreCase_failure_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        // removed other assertion

        p.next(1);
        try {
     p.matchIgnoreCase("b");
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected [b] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testMatchIgnoreCase_failure_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        // removed other assertion

        p.next(1);
        // removed other assertion

        try {
     p.match(null);
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected [null] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testTryMatch_noToken_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        try {
     p.tryMatch("empty");
    fail("Expected IllegalStateException with message: " + "No token has been read from the character stream");
} catch (IllegalStateException e) {
}
    }

@Test
    void testTryMatchIgnoreCase_noToken_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        try {
     p.tryMatchIgnoreCase("empty");
    fail("Expected IllegalStateException with message: " + "No token has been read from the character stream");
} catch (IllegalStateException e) {
}
    }

@Test
    void testChoose_failure_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        try {
     p.choose("X");
    fail("Expected IllegalStateException with message: " + "No token has been read from the character stream");
} catch (IllegalStateException e) {
}
    }

@Test
    void testChoose_failure_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        // removed other assertion

        p.next(1);
        try {
     p.choose("X");
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected one of [X] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testChoose_failure_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        // removed other assertion

        p.next(1);
        // removed other assertion

        try {
     p.choose("X", "Y", "Z");
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected one of [X, Y, Z] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testChoose_failure_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        // removed other assertion

        p.next(1);
        // removed other assertion

        // removed other assertion

        try {
     p.choose("A");
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected one of [A] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testChoose_failure_5_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        // removed other assertion

        p.next(1);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
     p.choose();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected one of [] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testChooseIgnoreCase_failure_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        try {
     p.chooseIgnoreCase("X");
    fail("Expected IllegalStateException with message: " + "No token has been read from the character stream");
} catch (IllegalStateException e) {
}
    }

@Test
    void testChooseIgnoreCase_failure_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        // removed other assertion

        p.next(1);
        try {
     p.chooseIgnoreCase("X");
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected one of [X] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testChooseIgnoreCase_failure_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        // removed other assertion

        p.next(1);
        // removed other assertion

        try {
     p.chooseIgnoreCase("X", "Y", "Z");
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected one of [X, Y, Z] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testChooseIgnoreCase_failure_4_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        // removed other assertion

        p.next(1);
        // removed other assertion

        // removed other assertion

        try {
     p.chooseIgnoreCase();
    fail("Expected IllegalStateException with message: " + "Parsing failed at line 1, column 1: expected one of [] but found [a]");
} catch (IllegalStateException e) {
}
    }

@Test
    void testTryChoose_noToken_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        try {
     p.tryChoose("X");
    fail("Expected IllegalStateException with message: " + "No token has been read from the character stream");
} catch (IllegalStateException e) {
}
    }

@Test
    void testTryChooseIgnoreCase_noToken_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        try {
     p.tryChooseIgnoreCase("X");
    fail("Expected IllegalStateException with message: " + "No token has been read from the character stream");
} catch (IllegalStateException e) {
}
    }

}
