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

class SimpleTextParserTest_OE25Dev {

    private static final double EPS = 1e-20;

    private static final int EOF = -1;

    @Test
    void testMaxStringLength_defaultValue() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        Assertions.assertEquals(1024, p.getMaxStringLength());
    }

    @Test
    void testMaxStringLength_illegalArg() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.setMaxStringLength(-1);
        }, IllegalArgumentException.class, "Maximum string length cannot be less than zero; was -1");
    }

    @Test
    void testGetCurrentTokenAsDouble() {
        // arrange
        final SimpleTextParser p = parser("1e-4\n+5\n-4.001");

        // act/assert
        p.nextLine();
        Assertions.assertEquals(1e-4, p.getCurrentTokenAsDouble(), EPS);

        p.nextLine();
        Assertions.assertEquals(5.0, p.getCurrentTokenAsDouble(), EPS);

        p.nextLine();
        Assertions.assertEquals(-4.001, p.getCurrentTokenAsDouble(), EPS);
    }

    @Test
    void testGetCurrentTokenAsDouble_failures() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsDouble();
        }, IllegalStateException.class, "No token has been read from the character stream");

        p.next(SimpleTextParser::isNotNewLinePart);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsDouble();
        }, IllegalStateException.class,
                "Parsing failed at line 1, column 1: expected double but found [abc]");

        p.nextAlphanumeric();
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsDouble();
        }, IllegalStateException.class,
                "Parsing failed at line 1, column 4: expected double but found end of line");

        p.discardLine()
            .next(c -> c != 'a');
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsDouble();
        }, IllegalStateException.class,
                "Parsing failed at line 2, column 1: expected double but found [1.1.1]");

        p.next(Character::isDigit);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsDouble();
        }, IllegalStateException.class,
                "Parsing failed at line 2, column 6: expected double but found empty token followed by [a]");

        p.nextLine();
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsDouble();
        }, IllegalStateException.class,
                "Parsing failed at line 2, column 6: expected double but found [a]");

        p.nextLine();
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsDouble();
        }, IllegalStateException.class,
                "Parsing failed at line 2, column 7: expected double but found end of content");
    }

    @Test
    void testGetCurrentTokenAsDouble_includedNumberFormatExceptionOnFailure() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.nextLine();

        // act/assert
        final Throwable exc = Assertions.assertThrows(IllegalStateException.class, () -> p.getCurrentTokenAsDouble());
        Assertions.assertEquals(NumberFormatException.class, exc.getCause().getClass());
    }

    @Test
    void testGetCurrentTokenAsInt() {
        // arrange
        final SimpleTextParser p = parser("0\n+5\n-401");

        // act/assert
        p.nextLine();
        Assertions.assertEquals(0, p.getCurrentTokenAsInt());

        p.nextLine();
        Assertions.assertEquals(5, p.getCurrentTokenAsInt());

        p.nextLine();
        Assertions.assertEquals(-401, p.getCurrentTokenAsInt());
    }

    @Test
    void testGetCurrentTokenAsInt_failures() {
        // arrange
        final SimpleTextParser p = parser("abc\n1.1.1a");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsInt();
        }, IllegalStateException.class, "No token has been read from the character stream");

        p.next(SimpleTextParser::isNotNewLinePart);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsInt();
        }, IllegalStateException.class,
                "Parsing failed at line 1, column 1: expected integer but found [abc]");

        p.nextAlphanumeric();
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsInt();
        }, IllegalStateException.class,
                "Parsing failed at line 1, column 4: expected integer but found end of line");

        p.discardLine()
            .next(c -> c != 'a');
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsInt();
        }, IllegalStateException.class,
                "Parsing failed at line 2, column 1: expected integer but found [1.1.1]");

        p.next(Character::isDigit);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsInt();
        }, IllegalStateException.class,
                "Parsing failed at line 2, column 6: expected integer but found empty token followed by [a]");

        p.nextLine();
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsInt();
        }, IllegalStateException.class,
                "Parsing failed at line 2, column 6: expected integer but found [a]");

        p.nextLine();
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.getCurrentTokenAsInt();
        }, IllegalStateException.class,
                "Parsing failed at line 2, column 7: expected integer but found end of content");
    }

    @Test
    void testGetCurrentTokenAsInt_includedNumberFormatExceptionOnFailure() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.nextLine();

        // act/assert
        final Throwable exc = Assertions.assertThrows(IllegalStateException.class, () -> p.getCurrentTokenAsInt());
        Assertions.assertEquals(NumberFormatException.class, exc.getCause().getClass());
    }

    @Test
    void testNext_lenArg_invalidArg() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.setMaxStringLength(2);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.next(-1);
        }, IllegalArgumentException.class, "Requested string length cannot be negative; was -1");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.next(3);
        }, IllegalArgumentException.class, "Requested string length of 3 exceeds maximum value of 2");
    }

    @Test
    void testNext_predicateArg_exceedsMaxStringLength() {
        // arrange
        final SimpleTextParser p = parser("abcdef");
        p.setMaxStringLength(4);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.next(c -> !Character.isWhitespace(c));
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: string length exceeds maximum value of 4");
    }

    @Test
    void testPeek_lenArg_invalidArg() {
        // arrange
        final SimpleTextParser p = parser("abcdef");
        p.setMaxStringLength(4);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.peek(-1);
        }, IllegalArgumentException.class, "Requested string length cannot be negative; was -1");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.peek(6);
        }, IllegalArgumentException.class, "Requested string length of 6 exceeds maximum value of 4");
    }

    @Test
    void testPeek_predicateArg_exceedsMaxStringLength() {
        // arrange
        final SimpleTextParser p = parser("\n  abcdefg");
        p.setMaxStringLength(4);
        p.discardLine()
            .discard(SimpleTextParser::isWhitespace);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.peek(SimpleTextParser::isNotWhitespace);
        }, IllegalStateException.class, "Parsing failed at line 2, column 3: string length exceeds maximum value of 4");
    }

    @Test
    void testMatch() {
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
    void testMatch_failure() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.match("empty");
        }, IllegalStateException.class, "No token has been read from the character stream");

        p.next(1);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.match("b");
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected [b] but found [a]");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.match("A");
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected [A] but found [a]");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.match(null);
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected [null] but found [a]");
    }

    @Test
    void testMatch_ignoreCase() {
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
    void testMatchIgnoreCase_failure() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.matchIgnoreCase("empty");
        }, IllegalStateException.class, "No token has been read from the character stream");

        p.next(1);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.matchIgnoreCase("b");
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected [b] but found [a]");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.match(null);
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected [null] but found [a]");
    }

    @Test
    void testTryMatch() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        Assertions.assertTrue(p.tryMatch("abc"));

        Assertions.assertFalse(p.tryMatch("ab"));
        Assertions.assertFalse(p.tryMatch(""));
        Assertions.assertFalse(p.tryMatch(null));

        Assertions.assertFalse(p.tryMatch("ABC"));
        Assertions.assertFalse(p.tryMatch("aBc"));

        p.next(1);
        Assertions.assertTrue(p.tryMatch(null));
    }

    @Test
    void testTryMatch_noToken() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.tryMatch("empty");
        }, IllegalStateException.class, "No token has been read from the character stream");
    }

    @Test
    void testTryMatchIgnoreCase() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(3);

        Assertions.assertTrue(p.tryMatchIgnoreCase("abc"));
        Assertions.assertTrue(p.tryMatchIgnoreCase("ABC"));
        Assertions.assertTrue(p.tryMatchIgnoreCase("aBc"));

        Assertions.assertFalse(p.tryMatch("ab"));
        Assertions.assertFalse(p.tryMatch(""));
        Assertions.assertFalse(p.tryMatch(null));

        p.next(1);
        Assertions.assertTrue(p.tryMatch(null));
    }

    @Test
    void testTryMatchIgnoreCase_noToken() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.tryMatchIgnoreCase("empty");
        }, IllegalStateException.class, "No token has been read from the character stream");
    }

    @Test
    void testChoose() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        Assertions.assertEquals(0, p.choose("a"));

        Assertions.assertEquals(0, p.choose("a", "b", "c"));
        Assertions.assertEquals(2, p.choose("c", "b", "a"));

        p.next(1);

        Assertions.assertEquals(0, p.choose("b"));

        Assertions.assertEquals(1, p.choose("a", "b", "c"));
        Assertions.assertEquals(1, p.choose("c", "b", "a"));
    }

    @Test
    void testChoose_failure() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.choose("X");
        }, IllegalStateException.class, "No token has been read from the character stream");

        p.next(1);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.choose("X");
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected one of [X] but found [a]");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.choose("X", "Y", "Z");
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected one of [X, Y, Z] but found [a]");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.choose("A");
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected one of [A] but found [a]");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.choose();
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected one of [] but found [a]");
    }

    @Test
    void testChooseIgnoreCase() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        Assertions.assertEquals(0, p.chooseIgnoreCase("A"));

        Assertions.assertEquals(0, p.chooseIgnoreCase("A", "b", "C"));
        Assertions.assertEquals(2, p.chooseIgnoreCase("C", "b", "A"));

        p.next(1);

        Assertions.assertEquals(0, p.chooseIgnoreCase("b"));

        Assertions.assertEquals(1, p.chooseIgnoreCase("A", "b", "C"));
        Assertions.assertEquals(1, p.chooseIgnoreCase("C", "b", "A"));
    }

    @Test
    void testChooseIgnoreCase_failure() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.chooseIgnoreCase("X");
        }, IllegalStateException.class, "No token has been read from the character stream");

        p.next(1);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.chooseIgnoreCase("X");
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected one of [X] but found [a]");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.chooseIgnoreCase("X", "Y", "Z");
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected one of [X, Y, Z] but found [a]");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.chooseIgnoreCase();
        }, IllegalStateException.class, "Parsing failed at line 1, column 1: expected one of [] but found [a]");
    }

    @Test
    void testTryChoose() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        Assertions.assertEquals(0, p.tryChoose("a"));

        Assertions.assertEquals(0, p.tryChoose("a", "b", "c"));
        Assertions.assertEquals(2, p.tryChoose("c", "b", "a"));

        p.next(1);

        Assertions.assertEquals(0, p.tryChoose("b"));

        Assertions.assertEquals(1, p.tryChoose("a", "b", "c"));
        Assertions.assertEquals(1, p.tryChoose("c", "b", "a"));

        Assertions.assertEquals(-1, p.tryChoose("A", "B", "C"));
        Assertions.assertEquals(-1, p.tryChoose());
        Assertions.assertEquals(-1, p.tryChoose((String) null));
    }

    @Test
    void testTryChoose_noToken() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.tryChoose("X");
        }, IllegalStateException.class, "No token has been read from the character stream");
    }

    @Test
    void testTryChooseIgnoreCase() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        p.next(1);

        Assertions.assertEquals(0, p.tryChooseIgnoreCase("a"));

        Assertions.assertEquals(0, p.tryChooseIgnoreCase("A", "B", "C"));
        Assertions.assertEquals(2, p.tryChooseIgnoreCase("C", "b", "A"));

        p.next(1);

        Assertions.assertEquals(0, p.tryChooseIgnoreCase("B"));

        Assertions.assertEquals(1, p.tryChooseIgnoreCase("a", "B", "c"));
        Assertions.assertEquals(1, p.tryChooseIgnoreCase("c", "b", "a"));

        Assertions.assertEquals(-1, p.tryChooseIgnoreCase("X", "Y", "Z"));
        Assertions.assertEquals(-1, p.tryChooseIgnoreCase());
        Assertions.assertEquals(-1, p.tryChooseIgnoreCase((String) null));
    }

    @Test
    void testTryChooseIgnoreCase_noToken() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.tryChooseIgnoreCase("X");
        }, IllegalStateException.class, "No token has been read from the character stream");
    }

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
    void testUnexpectedToken_causeArg() {
        // arrange
        final SimpleTextParser p = parser("abc");
        final Exception cause = new Exception("test");

        // act/assert
        p.nextLine();

        final IllegalStateException exc = p.unexpectedToken("test", cause);
        Assertions.assertEquals("Parsing failed at line 1,column 1: expected test but found [abc]",exc.getMessage());
        Assertions.assertSame(cause, exc.getCause());
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

    @Test
    void testTokenError() {
        // arrange
        final SimpleTextParser p = parser("a\nbc");
        p.nextLine();
        p.next(1);
        p.readChar();

        // act/assert
        final IllegalStateException exc = p.tokenError("test message");

        Assertions.assertEquals("Parsing failed at line 2, column 1: test message", exc.getMessage());
        Assertions.assertNull(exc.getCause());
    }

    @Test
    void testTokenError_noTokenSet() {
        // arrange
        final SimpleTextParser p = parser("ab\nc");
        p.readChar();

        // act/assert
        final IllegalStateException exc = p.tokenError("test message");

        Assertions.assertEquals("Parsing failed at line 1, column 2: test message", exc.getMessage());
        Assertions.assertNull(exc.getCause());
    }

    @Test
    void testTokenError_withCause() {
        // arrange
        SimpleTextParser p = parser("a\nbc");
        p.nextLine();
        p.next(1);
        p.readChar();

        final Exception cause = new Exception("test");

        // act/assert
        final IllegalStateException exc = p.tokenError("test message", cause);

        Assertions.assertEquals("Parsing failed at line 2, column 1: test message", exc.getMessage());
        Assertions.assertSame(cause, exc.getCause());
    }

    @Test
    void testParseError_currentLineCol() {
        // arrange
        final SimpleTextParser p = parser("a\nbc");
        p.discard(ch -> ch != 'b');

        // act
        final IllegalStateException exc = p.parseError("test message");

        Assertions.assertEquals("Parsing failed at line 2, column 1: test message", exc.getMessage());
        Assertions.assertNull(exc.getCause());
    }

    @Test
    void testParseError_currentLineCol_withCause() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.readChar();
        final Exception cause = new Exception("test");

        // act
        final IllegalStateException exc = p.parseError("test message", cause);

        Assertions.assertEquals("Parsing failed at line 1, column 2: test message", exc.getMessage());
        Assertions.assertSame(cause, exc.getCause());
    }

    @Test
    void testParseError_givenLineCol() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act
        final IllegalStateException exc = p.parseError(5, 6, "test message");

        Assertions.assertEquals("Parsing failed at line 5, column 6: test message", exc.getMessage());
        Assertions.assertNull(exc.getCause());
    }

    @Test
    void testParseError_givenLineCol_withCause() {
        // arrange
        final SimpleTextParser p = parser("abc");
        final Exception cause = new Exception("test");

        // act
        final IllegalStateException exc = p.parseError(5, 6, "test message", cause);

        Assertions.assertEquals("Parsing failed at line 5, column 6: test message", exc.getMessage());
        Assertions.assertSame(cause, exc.getCause());
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
    void testCharacterSequence_1_oe_1_oe() {
        // act/assert
                final SimpleTextParser parser = parser("");
        final String expected = "";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    Assertions.assertEquals(expectedChar, parser.peekChar(), msg);
    }
    }

    @Test
    void testCharacterSequence_1_oe_2_oe() {
        // act/assert
                final SimpleTextParser parser = parser("");
        final String expected = "";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    Assertions.assertEquals(expectedChar, parser.peekChar(), msg);
    }
    }

    @Test
    void testCharacterSequence_1_oe_3_oe() {
        // act/assert
                final SimpleTextParser parser = parser("");
        final String expected = "";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    Assertions.assertTrue(parser.hasMoreCharacters());
    }
    }

    @Test
    void testCharacterSequence_1_oe_4_oe() {
        // act/assert
                final SimpleTextParser parser = parser("");
        final String expected = "";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    Assertions.assertEquals(expectedChar, parser.readChar(), msg);
    }
    }

    @Test
    void testCharacterSequence_1_oe_5_oe() {
        // act/assert
                final SimpleTextParser parser = parser("");
        final String expected = "";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                Assertions.assertFalse(parser.hasMoreCharacters());
    }

    @Test
    void testCharacterSequence_1_oe_6_oe() {
        // act/assert
                final SimpleTextParser parser = parser("");
        final String expected = "";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                // removed other assertion
                Assertions.assertEquals(-1, parser.peekChar());
    }

    @Test
    void testCharacterSequence_1_oe_7_oe() {
        // act/assert
                final SimpleTextParser parser = parser("");
        final String expected = "";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                // removed other assertion
                // removed other assertion
                Assertions.assertEquals(-1, parser.peekChar());
    }

    @Test
    void testCharacterSequence_1_oe_8_oe() {
        // act/assert
                final SimpleTextParser parser = parser("");
        final String expected = "";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                // removed other assertion
                // removed other assertion
                // removed other assertion
                Assertions.assertEquals(-1, parser.readChar());
    }

    @Test
    void testCharacterSequence_2_oe_1_oe() {
        // act/assert
        // removed other assertion
                final SimpleTextParser parser = parser("abc def");
        final String expected = "abc def";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    Assertions.assertEquals(expectedChar, parser.peekChar(), msg);
    }
    }

    @Test
    void testCharacterSequence_2_oe_2_oe() {
        // act/assert
        // removed other assertion
                final SimpleTextParser parser = parser("abc def");
        final String expected = "abc def";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    Assertions.assertEquals(expectedChar, parser.peekChar(), msg);
    }
    }

    @Test
    void testCharacterSequence_2_oe_3_oe() {
        // act/assert
        // removed other assertion
                final SimpleTextParser parser = parser("abc def");
        final String expected = "abc def";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    Assertions.assertTrue(parser.hasMoreCharacters());
    }
    }

    @Test
    void testCharacterSequence_2_oe_4_oe() {
        // act/assert
        // removed other assertion
                final SimpleTextParser parser = parser("abc def");
        final String expected = "abc def";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    Assertions.assertEquals(expectedChar, parser.readChar(), msg);
    }
    }

    @Test
    void testCharacterSequence_2_oe_5_oe() {
        // act/assert
        // removed other assertion
                final SimpleTextParser parser = parser("abc def");
        final String expected = "abc def";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                Assertions.assertFalse(parser.hasMoreCharacters());
    }

    @Test
    void testCharacterSequence_2_oe_6_oe() {
        // act/assert
        // removed other assertion
                final SimpleTextParser parser = parser("abc def");
        final String expected = "abc def";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                // removed other assertion
                Assertions.assertEquals(-1, parser.peekChar());
    }

    @Test
    void testCharacterSequence_2_oe_7_oe() {
        // act/assert
        // removed other assertion
                final SimpleTextParser parser = parser("abc def");
        final String expected = "abc def";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                // removed other assertion
                // removed other assertion
                Assertions.assertEquals(-1, parser.peekChar());
    }

    @Test
    void testCharacterSequence_2_oe_8_oe() {
        // act/assert
        // removed other assertion
                final SimpleTextParser parser = parser("abc def");
        final String expected = "abc def";
        char expectedChar;
                String msg;
                for (int i = 0; i < expected.length(); ++i) {
                    expectedChar = expected.charAt(i);
        
                    msg = "Failed at index " + i + ":";
        
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                // removed other assertion
                // removed other assertion
                // removed other assertion
                Assertions.assertEquals(-1, parser.readChar());
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = 'a';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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

                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testCharacterPosition_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser(
                "a b\n" +
                "\r\n" +
                "d \r" +
                "e");

        // act/assert
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_4_oe_1_oe() {
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
                final int expected = ' ';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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

                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 3;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testCharacterPosition_5_oe_2_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 3;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_6_oe_1_oe() {
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
                final int expected = 'b';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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

                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 4;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testCharacterPosition_7_oe_2_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 4;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_8_oe_1_oe() {
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
                final int expected = '\n';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testCharacterPosition_9_oe_1_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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

                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_10_oe_1_oe() {
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
                final int expected = '\r';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testCharacterPosition_11_oe_1_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testCharacterPosition_11_oe_2_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_12_oe_1_oe() {
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
                final int expected = '\n';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testCharacterPosition_13_oe_1_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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

                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_14_oe_1_oe() {
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
                final int expected = 'd';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testCharacterPosition_15_oe_1_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testCharacterPosition_15_oe_2_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_16_oe_1_oe() {
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
                final int expected = ' ';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testCharacterPosition_17_oe_1_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 3;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testCharacterPosition_17_oe_2_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 3;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_18_oe_1_oe() {
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
                final int expected = '\r';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testCharacterPosition_19_oe_1_oe() {
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

                final SimpleTextParser parser = p;
        final int line = 4;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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

                final SimpleTextParser parser = p;
        final int line = 4;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_20_oe_1_oe() {
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

        // removed other assertion
                final int expected = 'e';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testCharacterPosition_21_oe_1_oe() {
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

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p;
        final int line = 4;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testCharacterPosition_21_oe_2_oe() {
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

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p;
        final int line = 4;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_22_oe_1_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final int expected = EOF;
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testCharacterPosition_givenPosition_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testCharacterPosition_givenPosition_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testCharacterPosition_givenPosition_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
        // removed other assertion

        p.setLineNumber(10);
        p.setColumnNumber(3);

                final SimpleTextParser parser = p;
        final int line = 10;
        final int col = 3;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testCharacterPosition_givenPosition_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abc\rdef");

        // act/assert
        // removed other assertion

        p.setLineNumber(10);
        p.setColumnNumber(3);

                final SimpleTextParser parser = p;
        final int line = 10;
        final int col = 3;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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

                final SimpleTextParser parser = p;
        final int line = 11;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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

                final SimpleTextParser parser = p;
        final int line = 11;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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

                final SimpleTextParser parser = p;
        final int line = 11;
        final int col = 4;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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

                final SimpleTextParser parser = p;
        final int line = 11;
        final int col = 4;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testHasMoreCharacters_3_oe_1_oe() {
        // arrange
        final SimpleTextParser empty = parser("");
        final SimpleTextParser nonEmpty = parser("a");

        // act/assert
        // removed other assertion

        // removed other assertion
                final int expected = 'a';
        final int actual = nonEmpty.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final int expected = 'a';
        final int actual = singleLine.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final int expected = 'a';
        final int actual = multiLine.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testHasMoreCharactersOnLine_8_oe_1_oe() {
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
                final int expected = '\r';
        final int actual = multiLine.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testHasMoreCharactersOnLine_10_oe_1_oe() {
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
                final int expected = '\n';
        final int actual = multiLine.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testHasMoreCharactersOnLine_12_oe_1_oe() {
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
                final int expected = 'b';
        final int actual = multiLine.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testHasMoreCharactersOnLine_14_oe_1_oe() {
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
                final int expected = '\r';
        final int actual = multiLine.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testHasMoreCharactersOnLine_16_oe_1_oe() {
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

        // removed other assertion
                final int expected = 'c';
        final int actual = multiLine.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testHasMoreCharactersOnLine_18_oe_1_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final int expected = '\n';
        final int actual = multiLine.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testHasMoreCharactersOnLine_20_oe_1_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final int expected = '\n';
        final int actual = multiLine.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testHasMoreCharactersOnLine_22_oe_1_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final int expected = EOF;
        final int actual = multiLine.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testBasicTokenMethods_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser = p;
        final String token = null;
        final int line = -1;
        final int col = -1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testBasicTokenMethods_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser = p;
        final String token = null;
        final int line = -1;
        final int col = -1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testBasicTokenMethods_1_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser = p;
        final String token = null;
        final int line = -1;
        final int col = -1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testBasicTokenMethods_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(1);
        final String token = "a";
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testBasicTokenMethods_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(1);
        final String token = "a";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testBasicTokenMethods_3_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(1);
        final String token = "a";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testBasicTokenMethods_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(3);
        final String token = "bcd";
        final int line = 1;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
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

                final SimpleTextParser parser = p.next(3);
        final String token = "bcd";
        final int line = 1;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testBasicTokenMethods_5_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(3);
        final String token = "bcd";
        final int line = 1;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testBasicTokenMethods_7_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(5);
        final String token = "ef\r\n\r";
        final int line = 1;
        final int col = 5;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
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

                final SimpleTextParser parser = p.next(5);
        final String token = "ef\r\n\r";
        final int line = 1;
        final int col = 5;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testBasicTokenMethods_7_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(5);
        final String token = "ef\r\n\r";
        final int line = 1;
        final int col = 5;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
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

                final SimpleTextParser parser = p.next(0);
        final String token = "";
        final int line = 3;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testBasicTokenMethods_9_oe_2_oe() {
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

                final SimpleTextParser parser = p.next(0);
        final String token = "";
        final int line = 3;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
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

                final SimpleTextParser parser = p.next(0);
        final String token = "";
        final int line = 3;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testBasicTokenMethods_11_oe_1_oe() {
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

                final SimpleTextParser parser = p.next(1);
        final String token = " ";
        final int line = 3;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testBasicTokenMethods_11_oe_2_oe() {
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

                final SimpleTextParser parser = p.next(1);
        final String token = " ";
        final int line = 3;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
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

                final SimpleTextParser parser = p.next(1);
        final String token = " ";
        final int line = 3;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testBasicTokenMethods_13_oe_1_oe() {
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

                final SimpleTextParser parser = p.next(3);
        final String token = "ghi";
        final int line = 3;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testBasicTokenMethods_13_oe_2_oe() {
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

                final SimpleTextParser parser = p.next(3);
        final String token = "ghi";
        final int line = 3;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testBasicTokenMethods_13_oe_3_oe() {
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

                final SimpleTextParser parser = p.next(3);
        final String token = "ghi";
        final int line = 3;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testBasicTokenMethods_15_oe_1_oe() {
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

                final SimpleTextParser parser = p.next(1);
        final String token = null;
        final int line = 3;
        final int col = 5;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testBasicTokenMethods_15_oe_2_oe() {
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

                final SimpleTextParser parser = p.next(1);
        final String token = null;
        final int line = 3;
        final int col = 5;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testBasicTokenMethods_15_oe_3_oe() {
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

                final SimpleTextParser parser = p.next(1);
        final String token = null;
        final int line = 3;
        final int col = 5;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_lenArg_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser = p.next(0);
        final String token = "";
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_lenArg_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser = p.next(0);
        final String token = "";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_lenArg_1_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
                final SimpleTextParser parser = p.next(0);
        final String token = "";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_lenArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser = p.next(4);
        final String token = "abcd";
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_lenArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser = p.next(4);
        final String token = "abcd";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_lenArg_2_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser = p.next(4);
        final String token = "abcd";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_lenArg_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.next(6);
        final String token = "ef\r\n\r ";
        final int line = 1;
        final int col = 5;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_lenArg_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.next(6);
        final String token = "ef\r\n\r ";
        final int line = 1;
        final int col = 5;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_lenArg_3_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.next(6);
        final String token = "ef\r\n\r ";
        final int line = 1;
        final int col = 5;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_lenArg_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.next(100);
        final String token = "ghi";
        final int line = 3;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_lenArg_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.next(100);
        final String token = "ghi";
        final int line = 3;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_lenArg_4_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.next(100);
        final String token = "ghi";
        final int line = 3;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_lenArg_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(0);
        final String token = null;
        final int line = 3;
        final int col = 5;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_lenArg_5_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(0);
        final String token = null;
        final int line = 3;
        final int col = 5;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_lenArg_5_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(0);
        final String token = null;
        final int line = 3;
        final int col = 5;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_lenArg_6_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.next(100);
        final String token = null;
        final int line = 3;
        final int col = 5;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_lenArg_6_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.next(100);
        final String token = null;
        final int line = 3;
        final int col = 5;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_lenArg_6_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.next(100);
        final String token = null;
        final int line = 3;
        final int col = 5;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_1_oe_1_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 0);
        final String token = "";
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_lenArg_1_oe_2_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 0);
        final String token = "";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_1_oe_3_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 0);
        final String token = "";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_2_oe_1_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 5);
        final String token = "a\\bcd";
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_lenArg_2_oe_2_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 5);
        final String token = "a\\bcd";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_2_oe_3_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 5);
        final String token = "a\\bcd";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_3_oe_1_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 3);
        final String token = "ef\r";
        final int line = 1;
        final int col = 6;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_lenArg_3_oe_2_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 3);
        final String token = "ef\r";
        final int line = 1;
        final int col = 6;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_3_oe_3_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 3);
        final String token = "ef\r";
        final int line = 1;
        final int col = 6;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_4_oe_1_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 100);
        final String token = " ghij";
        final int line = 3;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_lenArg_4_oe_2_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 100);
        final String token = " ghij";
        final int line = 3;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
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
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 100);
        final String token = " ghij";
        final int line = 3;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_5_oe_1_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 0);
        final String token = null;
        final int line = 6;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_lenArg_5_oe_2_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 0);
        final String token = null;
        final int line = 6;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_5_oe_3_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 0);
        final String token = null;
        final int line = 6;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_6_oe_1_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 100);
        final String token = null;
        final int line = 6;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_lenArg_6_oe_2_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 100);
        final String token = null;
        final int line = 6;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_lenArg_6_oe_3_oe() {
        // arrange
        final char cont = '\\';
        final SimpleTextParser p = parser("a\\bcdef\\\r\n\r ghi\\\n\\\n\\\rj");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, 100);
        final String token = null;
        final int line = 6;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_predicateArg_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
                final SimpleTextParser parser = p.next(c -> false);
        final String token = "";
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
                final SimpleTextParser parser = p.next(c -> false);
        final String token = "";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_predicateArg_1_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
                final SimpleTextParser parser = p.next(c -> false);
        final String token = "";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_predicateArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser = p.next(Character::isAlphabetic);
        final String token = "a";
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser = p.next(Character::isAlphabetic);
        final String token = "a";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_predicateArg_2_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser = p.next(Character::isAlphabetic);
        final String token = "a";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_predicateArg_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.next(Character::isAlphabetic);
        final String token = "";
        final int line = 1;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.next(Character::isAlphabetic);
        final String token = "";
        final int line = 1;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_predicateArg_3_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.next(Character::isAlphabetic);
        final String token = "";
        final int line = 1;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_predicateArg_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "\n ";
        final int line = 1;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "\n ";
        final int line = 1;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_predicateArg_4_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "\n ";
        final int line = 1;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
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
                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "";
        final int line = 2;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_5_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "";
        final int line = 2;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_predicateArg_5_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "";
        final int line = 2;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_predicateArg_6_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(Character::isDigit);
        final String token = "012";
        final int line = 2;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_6_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(Character::isDigit);
        final String token = "012";
        final int line = 2;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_predicateArg_6_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.next(Character::isDigit);
        final String token = "012";
        final int line = 2;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
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
                final SimpleTextParser parser = p.next(Character::isDigit);
        final String token = "";
        final int line = 2;
        final int col = 5;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_7_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.next(Character::isDigit);
        final String token = "";
        final int line = 2;
        final int col = 5;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_predicateArg_7_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.next(Character::isDigit);
        final String token = "";
        final int line = 2;
        final int col = 5;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_predicateArg_8_oe_1_oe() {
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

                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "\r\n";
        final int line = 2;
        final int col = 5;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_8_oe_2_oe() {
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

                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "\r\n";
        final int line = 2;
        final int col = 5;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_predicateArg_8_oe_3_oe() {
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

                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "\r\n";
        final int line = 2;
        final int col = 5;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
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
                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "";
        final int line = 3;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_9_oe_2_oe() {
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
                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "";
        final int line = 3;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
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
                final SimpleTextParser parser = p.next(Character::isWhitespace);
        final String token = "";
        final int line = 3;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_predicateArg_10_oe_1_oe() {
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

                final SimpleTextParser parser = p.next(c -> true);
        final String token = "def";
        final int line = 3;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_10_oe_2_oe() {
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

                final SimpleTextParser parser = p.next(c -> true);
        final String token = "def";
        final int line = 3;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
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

                final SimpleTextParser parser = p.next(c -> true);
        final String token = "def";
        final int line = 3;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNext_predicateArg_11_oe_1_oe() {
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

        // removed other assertion
                final SimpleTextParser parser = p.next(c -> true);
        final String token = null;
        final int line = 3;
        final int col = 4;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNext_predicateArg_11_oe_2_oe() {
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

        // removed other assertion
                final SimpleTextParser parser = p.next(c -> true);
        final String token = null;
        final int line = 3;
        final int col = 4;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNext_predicateArg_11_oe_3_oe() {
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

        // removed other assertion
                final SimpleTextParser parser = p.next(c -> true);
        final String token = null;
        final int line = 3;
        final int col = 4;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_1_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, c -> false);
        final String token = "";
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_1_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, c -> false);
        final String token = "";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_1_oe_3_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, c -> false);
        final String token = "";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_2_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isAlphabetic);
        final String token = "a";
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_2_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isAlphabetic);
        final String token = "a";
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_2_oe_3_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isAlphabetic);
        final String token = "a";
        final int line = 2;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_3_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isAlphabetic);
        final String token = "";
        final int line = 2;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_3_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isAlphabetic);
        final String token = "";
        final int line = 2;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_3_oe_3_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isAlphabetic);
        final String token = "";
        final int line = 2;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_4_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "\n ";
        final int line = 2;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_4_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "\n ";
        final int line = 2;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_4_oe_3_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "\n ";
        final int line = 2;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
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
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "";
        final int line = 3;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_5_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "";
        final int line = 3;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_5_oe_3_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "";
        final int line = 3;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_6_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isDigit);
        final String token = "012";
        final int line = 3;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_6_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isDigit);
        final String token = "012";
        final int line = 3;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_6_oe_3_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("|\na\n 0|\r\n|\r12\r\nd|ef");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isDigit);
        final String token = "012";
        final int line = 3;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
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
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isDigit);
        final String token = "";
        final int line = 5;
        final int col = 3;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_7_oe_2_oe() {
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
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isDigit);
        final String token = "";
        final int line = 5;
        final int col = 3;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_7_oe_3_oe() {
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
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isDigit);
        final String token = "";
        final int line = 5;
        final int col = 3;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_8_oe_1_oe() {
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

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "\r\n";
        final int line = 5;
        final int col = 3;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_8_oe_2_oe() {
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

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "\r\n";
        final int line = 5;
        final int col = 3;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_8_oe_3_oe() {
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

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "\r\n";
        final int line = 5;
        final int col = 3;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
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
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "";
        final int line = 6;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_9_oe_2_oe() {
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
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "";
        final int line = 6;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
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
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, Character::isWhitespace);
        final String token = "";
        final int line = 6;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_10_oe_1_oe() {
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

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, c -> true);
        final String token = "d|ef";
        final int line = 6;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_10_oe_2_oe() {
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

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, c -> true);
        final String token = "d|ef";
        final int line = 6;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
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

                final SimpleTextParser parser = p.nextWithLineContinuation(cont, c -> true);
        final String token = "d|ef";
        final int line = 6;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_11_oe_1_oe() {
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

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, c -> true);
        final String token = null;
        final int line = 6;
        final int col = 5;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_11_oe_2_oe() {
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

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, c -> true);
        final String token = null;
        final int line = 6;
        final int col = 5;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextWithLineContinuation_predicateArg_11_oe_3_oe() {
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

        // removed other assertion
                final SimpleTextParser parser = p.nextWithLineContinuation(cont, c -> true);
        final String token = null;
        final int line = 6;
        final int col = 5;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextLine_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
                final SimpleTextParser parser = p.nextLine();
        final String token = "a";
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextLine_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
                final SimpleTextParser parser = p.nextLine();
        final String token = "a";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextLine_1_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
                final SimpleTextParser parser = p.nextLine();
        final String token = "a";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextLine_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser = p.nextLine();
        final String token = " 012";
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextLine_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser = p.nextLine();
        final String token = " 012";
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextLine_2_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

                final SimpleTextParser parser = p.nextLine();
        final String token = " 012";
        final int line = 2;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextLine_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
                final SimpleTextParser parser = p.nextLine();
        final String token = "ef";
        final int line = 3;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextLine_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
                final SimpleTextParser parser = p.nextLine();
        final String token = "ef";
        final int line = 3;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextLine_3_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
                final SimpleTextParser parser = p.nextLine();
        final String token = "ef";
        final int line = 3;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
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

                final SimpleTextParser parser = p.nextLine();
        final String token = "";
        final int line = 4;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextLine_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
        // removed other assertion

                final SimpleTextParser parser = p.nextLine();
        final String token = "";
        final int line = 4;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextLine_4_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
        // removed other assertion

                final SimpleTextParser parser = p.nextLine();
        final String token = "";
        final int line = 4;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextLine_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
        // removed other assertion

        // removed other assertion

                final SimpleTextParser parser = p.nextLine();
        final String token = "x";
        final int line = 5;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextLine_5_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
        // removed other assertion

        // removed other assertion

                final SimpleTextParser parser = p.nextLine();
        final String token = "x";
        final int line = 5;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextLine_5_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
        // removed other assertion

        // removed other assertion

                final SimpleTextParser parser = p.nextLine();
        final String token = "x";
        final int line = 5;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextLine_6_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
        // removed other assertion

        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextLine();
        final String token = null;
        final int line = 5;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextLine_6_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\n 012\r\ndef\n\nx");

        // act/assert
        // removed other assertion

        // removed other assertion

        p.readChar();
        // removed other assertion

        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextLine();
        final String token = null;
        final int line = 5;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
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
                final SimpleTextParser parser = p.nextLine();
        final String token = null;
        final int line = 5;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextAlphanumeric_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "a10Fd";
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextAlphanumeric_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "a10Fd";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextAlphanumeric_1_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "a10Fd";
        final int line = 1;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextAlphanumeric_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

                final int expected = ';';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testNextAlphanumeric_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "X23456789";
        final int line = 1;
        final int col = 7;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextAlphanumeric_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "X23456789";
        final int line = 1;
        final int col = 7;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextAlphanumeric_3_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "X23456789";
        final int line = 1;
        final int col = 7;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextAlphanumeric_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final int expected = '-';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testNextAlphanumeric_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "0";
        final int line = 1;
        final int col = 17;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
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
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "0";
        final int line = 1;
        final int col = 17;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextAlphanumeric_5_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "0";
        final int line = 1;
        final int col = 17;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextAlphanumeric_6_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "";
        final int line = 1;
        final int col = 18;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
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

                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "";
        final int line = 1;
        final int col = 18;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextAlphanumeric_6_oe_3_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "";
        final int line = 1;
        final int col = 18;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextAlphanumeric_7_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a10Fd;X23456789-0\ny");

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

                final int expected = '\n';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testNextAlphanumeric_8_oe_1_oe() {
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
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "y";
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextAlphanumeric_8_oe_2_oe() {
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
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "y";
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
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
                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = "y";
        final int line = 2;
        final int col = 1;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testNextAlphanumeric_9_oe_1_oe() {
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
        // removed other assertion

                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = null;
        final int line = 2;
        final int col = 2;
        Assertions.assertEquals(token, parser.getCurrentToken(), "Unexpected token");
    }

    @Test
    void testNextAlphanumeric_9_oe_2_oe() {
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
        // removed other assertion

                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = null;
        final int line = 2;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(line, parser.getCurrentTokenLineNumber(), "Unexpected token line number");
    }

    @Test
    void testNextAlphanumeric_9_oe_3_oe() {
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
        // removed other assertion

                final SimpleTextParser parser = p.nextAlphanumeric();
        final String token = null;
        final int line = 2;
        final int col = 2;
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(col, parser.getCurrentTokenColumnNumber(), "Unexpected token column number");
    }

    @Test
    void testDiscard_lenArg_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
                final int expected = '\n';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscard_lenArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscard_lenArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(0);
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = 'a';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = '2';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 5;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 5;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 5;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 5;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 5;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 5;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_1_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
                final int expected = '\n';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_2_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardWithLineContinuation_lenArg_2_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\n|a|\r\n,b|\n|\r c\r\n12.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, 0);
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = '|';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = '1';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 6;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 6;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 8;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 8;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 8;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 8;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 8;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 8;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscard_predicateArg_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
                final int expected = 'a';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscard_predicateArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscard_predicateArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("\na,b c\r\n12.3\rdef\n");

        // act/assert
        p.discard(c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = ' ';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 4;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 4;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = ' ';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 4;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 4;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = 'c';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 5;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 5;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = 'd';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 4;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 4;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 5;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 5;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 5;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 5;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_1_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
                final int expected = 'a';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_2_oe_1_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardWithLineContinuation_predicateArg_2_oe_2_oe() {
        // arrange
        final char cont = '|';
        final SimpleTextParser p = parser("\na,|\r\nb |c\r\n1|\r|\n2.3\rdef\n");

        // act/assert
        p.discardWithLineContinuation(cont, c -> Character.isWhitespace(c));
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = ' ';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = ' ';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = '|';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 3;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 3;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = 'd';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 7;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 7;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 8;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 8;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 8;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 8;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWhitespace_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardWhitespace_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWhitespace_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
                final int expected = 'a';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardWhitespace_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 4;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardWhitespace_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 4;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWhitespace_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
                final int expected = 'b';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardWhitespace_5_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 6;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardWhitespace_5_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 6;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWhitespace_6_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
                final int expected = 'c';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardWhitespace_7_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 7;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardWhitespace_7_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 7;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardWhitespace_8_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardWhitespace();
        // removed other assertion
                final int expected = EOF;
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardLineWhitespace_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardLineWhitespace_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLineWhitespace_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
        // removed other assertion
                final int expected = 'a';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 3;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardLineWhitespace_3_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 3;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLineWhitespace_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
                final int expected = '\n';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 3;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardLineWhitespace_5_oe_2_oe() {
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 3;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLineWhitespace_6_oe_1_oe() {
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
                final int expected = '\n';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardLineWhitespace_7_oe_1_oe() {
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLineWhitespace_8_oe_1_oe() {
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
        // removed other assertion
                final int expected = '\r';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardLineWhitespace_9_oe_1_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardLineWhitespace_9_oe_2_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLineWhitespace_10_oe_1_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
                final int expected = '\n';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardLineWhitespace_11_oe_1_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 4;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardLineWhitespace_11_oe_2_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 4;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLineWhitespace_12_oe_1_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
                final int expected = 'b';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardLineWhitespace_13_oe_1_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 6;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardLineWhitespace_13_oe_2_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 6;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLineWhitespace_14_oe_1_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();
        // removed other assertion
                final int expected = 'c';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardLineWhitespace_15_oe_1_oe() {
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
        // removed other assertion
        // removed other assertion

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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 7;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardLineWhitespace_15_oe_2_oe() {
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
        // removed other assertion
        // removed other assertion

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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 7;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardLineWhitespace_16_oe_1_oe() {
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
        // removed other assertion
        // removed other assertion

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
        // removed other assertion
                final int expected = EOF;
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardNewLineSequence_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardNewLineSequence_1_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardNewLineSequence_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
        // removed other assertion
                final int expected = 'a';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardNewLineSequence_3_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();

        p.discardNewLineSequence();
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardNewLineSequence_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b\rc");

        // act/assert
        p.discardNewLineSequence();
        // removed other assertion
        // removed other assertion

        p.discardLineWhitespace();

        p.discardNewLineSequence();
        // removed other assertion
                final int expected = '\r';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardNewLineSequence_5_oe_1_oe() {
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardNewLineSequence_6_oe_1_oe() {
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
                final int expected = ' ';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardNewLineSequence_7_oe_1_oe() {
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 4;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardNewLineSequence_7_oe_2_oe() {
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 4;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardNewLineSequence_8_oe_1_oe() {
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
                final int expected = 'b';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardNewLineSequence_9_oe_1_oe() {
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
                final SimpleTextParser parser = p;
        final int line = 4;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 4;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardNewLineSequence_10_oe_1_oe() {
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
        // removed other assertion
                final int expected = 'c';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardNewLineSequence_11_oe_1_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardNewLineSequence();
                final SimpleTextParser parser = p;
        final int line = 4;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardNewLineSequence_11_oe_2_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardNewLineSequence();
                final SimpleTextParser parser = p;
        final int line = 4;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testDiscardNewLineSequence_12_oe_1_oe() {
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
        // removed other assertion
        // removed other assertion

        p.discardNewLineSequence();
        // removed other assertion
                final int expected = EOF;
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardLine_1_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
                final int expected = '\r';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testDiscardLine_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testDiscardLine_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("a\t\n\r\n   b c");

        // act/assert
        p.discardLine();
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 2;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = ' ';
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 7;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 7;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 7;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 3;
        final int col = 7;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final int expected = EOF;
        final int actual = p.peekChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testPeek_lenArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testPeek_lenArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
    }

    @Test
    void testPeek_lenArg_4_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testPeek_lenArg_4_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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

                final int expected = 'b';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testPeek_predicateArg_2_oe_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
    }

    @Test
    void testPeek_predicateArg_2_oe_2_oe() {
        // arrange
        final SimpleTextParser p = parser("abcdef\r\n\r ghi");

        // act/assert
        // removed other assertion
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 1;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        Assertions.assertEquals(line, parser.getLineNumber(), "Unexpected line number");
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
                final SimpleTextParser parser = p;
        final int line = 1;
        final int col = 2;
        // removed other assertion
                Assertions.assertEquals(col, parser.getColumnNumber(), "Unexpected column number");
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

                final int expected = 'b';
        final int actual = p.readChar();
        final String expectedStr = describeChar(expected);
                final String actualStr = describeChar(actual);
        
                Assertions.assertEquals(expected, actual, "Expected [" + expectedStr + "] but was [" + actualStr + "];");
    }

    @Test
    void testCharacterPredicates_1_oe_1_oe() {
        // act/assert
                final IntPredicate pred = SimpleTextParser::isWhitespace;
        final String chars = " \t\n\r";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to match [" + describeChar(ch) + "]";
                    Assertions.assertTrue(pred.test(ch), msg);
    }
    }

    @Test
    void testCharacterPredicates_2_oe_1_oe() {
        // act/assert
        // removed other assertion
                final IntPredicate pred = SimpleTextParser::isWhitespace;
        final String chars = "abcABC<>,./?:;'\"[]{}`~!@#$%^&*()_+-=";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to not match [" + describeChar(ch) + "]";
                    Assertions.assertFalse(pred.test(ch), msg);
    }
    }

    @Test
    void testCharacterPredicates_3_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

                final IntPredicate pred = SimpleTextParser::isNotWhitespace;
        final String chars = "abcABC<>,./?:;'\"[]{}`~!@#$%^&*()_+-=";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to match [" + describeChar(ch) + "]";
                    Assertions.assertTrue(pred.test(ch), msg);
    }
    }

    @Test
    void testCharacterPredicates_4_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final IntPredicate pred = SimpleTextParser::isNotWhitespace;
        final String chars = " \t\n\r";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to not match [" + describeChar(ch) + "]";
                    Assertions.assertFalse(pred.test(ch), msg);
    }
    }

    @Test
    void testCharacterPredicates_5_oe_1_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final IntPredicate pred = SimpleTextParser::isLineWhitespace;
        final String chars = " \t";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to match [" + describeChar(ch) + "]";
                    Assertions.assertTrue(pred.test(ch), msg);
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
                final IntPredicate pred = SimpleTextParser::isLineWhitespace;
        final String chars = "\n\rabcABC<>,./?:;'\"[]{}`~!@#$%^&*()_+-=";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to not match [" + describeChar(ch) + "]";
                    Assertions.assertFalse(pred.test(ch), msg);
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

                final IntPredicate pred = SimpleTextParser::isNewLinePart;
        final String chars = "\n\r";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to match [" + describeChar(ch) + "]";
                    Assertions.assertTrue(pred.test(ch), msg);
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
                final IntPredicate pred = SimpleTextParser::isNewLinePart;
        final String chars = " \tabcABC<>,./?:;'\"[]{}`~!@#$%^&*()_+-=";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to not match [" + describeChar(ch) + "]";
                    Assertions.assertFalse(pred.test(ch), msg);
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

                final IntPredicate pred = SimpleTextParser::isNotNewLinePart;
        final String chars = " \tabcABC<>,./?:;'\"[]{}`~!@#$%^&*()_+-=";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to match [" + describeChar(ch) + "]";
                    Assertions.assertTrue(pred.test(ch), msg);
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
                final IntPredicate pred = SimpleTextParser::isNotNewLinePart;
        final String chars = "\n\r";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to not match [" + describeChar(ch) + "]";
                    Assertions.assertFalse(pred.test(ch), msg);
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

                final IntPredicate pred = SimpleTextParser::isAlphanumeric;
        final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to match [" + describeChar(ch) + "]";
                    Assertions.assertTrue(pred.test(ch), msg);
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
                final IntPredicate pred = SimpleTextParser::isAlphanumeric;
        final String chars = " \t\n\r./?:;'\\\"[]{}`~!@#$%^&*()_+-=";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to not match [" + describeChar(ch) + "]";
                    Assertions.assertFalse(pred.test(ch), msg);
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

                final IntPredicate pred = SimpleTextParser::isNotAlphanumeric;
        final String chars = " \t\n\r./?:;'\\\"[]{}`~!@#$%^&*()_+-=";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to match [" + describeChar(ch) + "]";
                    Assertions.assertTrue(pred.test(ch), msg);
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
                final IntPredicate pred = SimpleTextParser::isNotAlphanumeric;
        final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to not match [" + describeChar(ch) + "]";
                    Assertions.assertFalse(pred.test(ch), msg);
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

                final IntPredicate pred = SimpleTextParser::isIntegerPart;
        final String chars = "0123456789+-";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to match [" + describeChar(ch) + "]";
                    Assertions.assertTrue(pred.test(ch), msg);
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
                final IntPredicate pred = SimpleTextParser::isIntegerPart;
        final String chars = " \t\n\r./?:;'\\\"[]{}`~!@#$%^&*()_=abcdeABCDE";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to not match [" + describeChar(ch) + "]";
                    Assertions.assertFalse(pred.test(ch), msg);
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

                final IntPredicate pred = SimpleTextParser::isDecimalPart;
        final String chars = "0123456789+-.eE";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to match [" + describeChar(ch) + "]";
                    Assertions.assertTrue(pred.test(ch), msg);
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
                final IntPredicate pred = SimpleTextParser::isDecimalPart;
        final String chars = " \t\n\r/?:;'\\\"[]{}`~!@#$%^&*()_=abcdABCD";
        for (char ch : chars.toCharArray()) {
                    final String msg = "Expected predicate to not match [" + describeChar(ch) + "]";
                    Assertions.assertFalse(pred.test(ch), msg);
    }
    }

}
