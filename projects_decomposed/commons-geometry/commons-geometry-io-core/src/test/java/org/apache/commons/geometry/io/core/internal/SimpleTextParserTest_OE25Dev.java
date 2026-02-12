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
    void testMaxStringLength_illegalArg() {
        // arrange
        final SimpleTextParser p = parser("abc");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.setMaxStringLength(-1);
        }, IllegalArgumentException.class, "Maximum string length cannot be less than zero; was -1");
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
    void testTryMatch_noToken() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.tryMatch("empty");
        }, IllegalStateException.class, "No token has been read from the character stream");
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
    void testTryChoose_noToken() {
        // arrange
        final SimpleTextParser p = parser("abcdef");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            p.tryChoose("X");
        }, IllegalStateException.class, "No token has been read from the character stream");
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
    void testGetCurrentTokenAsDouble_includedNumberFormatExceptionOnFailure_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.nextLine();

        // act/assert
        final Throwable exc = Assertions.assertThrows(IllegalStateException.class, () -> p.getCurrentTokenAsDouble());
    }

    @Test
    void testGetCurrentTokenAsInt_includedNumberFormatExceptionOnFailure_1_oe() {
        // arrange
        final SimpleTextParser p = parser("abc");
        p.nextLine();

        // act/assert
        final Throwable exc = Assertions.assertThrows(IllegalStateException.class, () -> p.getCurrentTokenAsInt());
    }

}
