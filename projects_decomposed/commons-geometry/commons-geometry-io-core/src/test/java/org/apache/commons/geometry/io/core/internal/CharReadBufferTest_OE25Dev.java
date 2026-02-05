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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.Random;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharReadBufferTest_OE25Dev {

    @Test
    void testCtor() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            new CharReadBuffer(null, 1, 1);
        }, NullPointerException.class, "Reader cannot be null");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            new CharReadBuffer(reader("a"), 0, 1);
        }, IllegalArgumentException.class, "Initial buffer capacity must be greater than 0; was 0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            new CharReadBuffer(reader("a"), 1, 0);
        }, IllegalArgumentException.class, "Min read value must be greater than 0; was 0");
    }

    @Test
    void testCharAt_invalidArg() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            buf.charAt(-1);
        }, IllegalArgumentException.class, "Character index cannot be negative; was -1");
    }

    @Test
    void testReadPeek_string_invalidArg() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("a"));
        final String msg = "Requested string length cannot be negative; was -1";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            buf.peekString(-1);
        }, IllegalArgumentException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            buf.readString(-1);
        }, IllegalArgumentException.class, msg);
    }

    @Test
    void testReadPeek_failure() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(failReader());
        final String msg = "IOException: read";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            buf.peekString(3);
        }, UncheckedIOException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            buf.readString(3);
        }, UncheckedIOException.class, msg);
    }

    @Test
    void testSkip_invalidArg() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("a"));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            buf.skip(-1);
        }, IllegalArgumentException.class, "Character skip count cannot be negative; was -1");
    }

    @Test
    void testSkip_failure() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(failReader());

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            buf.skip(10);
        }, UncheckedIOException.class, "IOException: skip");
    }

    private static Reader reader(final String content) {
        return new StringReader(content);
    }

    private static Reader failReader() {
        return new Reader() {

            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                throw new IOException("read");
            }

            @Override
            public long skip(final long skip) throws IOException {
                throw new IOException("skip");
            }

            @Override
            public void close() {
            }
        };
    }

    private static String repeat(final String str, final int count) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(str);
        }

        return sb.toString();
    }

    @Test
    void testHasMoreCharacters_1_oe() {
        // act/assert
        for (int s = 1; s < 10; s += 2) {
            Assertions.assertFalse(new CharReadBuffer(reader("")).hasMoreCharacters());
    }
    }

    @Test
    void testHasMoreCharacters_2_oe() {
        // act/assert
        for (int s = 1; s < 10; s += 2) {
            // removed other assertion
            Assertions.assertFalse(new CharReadBuffer(reader(""), s).hasMoreCharacters());
    }
    }

    @Test
    void testHasMoreCharacters_3_oe() {
        // act/assert
        for (int s = 1; s < 10; s += 2) {
            // removed other assertion
            // removed other assertion
            Assertions.assertFalse(new CharReadBuffer(reader(""), s, s).hasMoreCharacters());
    }
    }

    @Test
    void testHasMoreCharacters_4_oe() {
        // act/assert
        for (int s = 1; s < 10; s += 2) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        String str;
        for (int i = 1; i < 10; ++i) {
            str = repeat("a", i);

            for (int s = 1; s < 10; s += 2) {
                Assertions.assertTrue(new CharReadBuffer(reader(str)).hasMoreCharacters());
    }
    }
    }

    @Test
    void testHasMoreCharacters_5_oe() {
        // act/assert
        for (int s = 1; s < 10; s += 2) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        String str;
        for (int i = 1; i < 10; ++i) {
            str = repeat("a", i);

            for (int s = 1; s < 10; s += 2) {
                // removed other assertion
                Assertions.assertTrue(new CharReadBuffer(reader(str), s).hasMoreCharacters());
    }
    }
    }

    @Test
    void testHasMoreCharacters_6_oe() {
        // act/assert
        for (int s = 1; s < 10; s += 2) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        String str;
        for (int i = 1; i < 10; ++i) {
            str = repeat("a", i);

            for (int s = 1; s < 10; s += 2) {
                // removed other assertion
                // removed other assertion
                Assertions.assertTrue(new CharReadBuffer(reader(str), s, s).hasMoreCharacters());
    }
    }
    }

    @Test
    void testPeekRead_1_oe() {
        // arrange
        final String str = "abcdefg";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 1);

        final StringBuilder peek = new StringBuilder();
        final StringBuilder read = new StringBuilder();

        // act
        while (buf.hasMoreCharacters()) {
            peek.append((char) buf.peek());
            read.append((char) buf.read());
        }

        // assert
        Assertions.assertEquals(str, peek.toString());
    }

    @Test
    void testPeekRead_2_oe() {
        // arrange
        final String str = "abcdefg";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 1);

        final StringBuilder peek = new StringBuilder();
        final StringBuilder read = new StringBuilder();

        // act
        while (buf.hasMoreCharacters()) {
            peek.append((char) buf.peek());
            read.append((char) buf.read());
        }

        // assert
        // removed other assertion
        Assertions.assertEquals(str, read.toString());
    }

    @Test
    void testPeekRead_3_oe() {
        // arrange
        final String str = "abcdefg";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 1);

        final StringBuilder peek = new StringBuilder();
        final StringBuilder read = new StringBuilder();

        // act
        while (buf.hasMoreCharacters()) {
            peek.append((char) buf.peek());
            read.append((char) buf.read());
        }

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, buf.peek());
    }

    @Test
    void testPeekRead_4_oe() {
        // arrange
        final String str = "abcdefg";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 1);

        final StringBuilder peek = new StringBuilder();
        final StringBuilder read = new StringBuilder();

        // act
        while (buf.hasMoreCharacters()) {
            peek.append((char) buf.peek());
            read.append((char) buf.read());
        }

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-1, buf.read());
    }

    @Test
    void testCharAt_1_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

        // act/assert
        Assertions.assertEquals('a', buf.charAt(0));
    }

    @Test
    void testCharAt_2_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

        // act/assert
        // removed other assertion
        Assertions.assertEquals('b', buf.charAt(1));
    }

    @Test
    void testCharAt_3_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals('c', buf.charAt(2));
    }

    @Test
    void testCharAt_4_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals('d', buf.charAt(3));
    }

    @Test
    void testCharAt_5_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals('e', buf.charAt(4));
    }

    @Test
    void testCharAt_6_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals('f', buf.charAt(5));
    }

    @Test
    void testCharAt_7_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals('g', buf.charAt(6));
    }

    @Test
    void testCharAt_8_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals('h', buf.charAt(7));
    }

    @Test
    void testCharAt_9_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, buf.charAt(8));
    }

    @Test
    void testCharAt_10_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

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
        Assertions.assertEquals(-1, buf.charAt(9));
    }

    @Test
    void testCharAt_11_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 3);

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
        Assertions.assertEquals(-1, buf.charAt(10));
    }

    @Test
    void testReadPeek_string_1_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 50);

        // act/assert
        Assertions.assertEquals("", buf.peekString(0));
    }

    @Test
    void testReadPeek_string_2_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 50);

        // act/assert
        // removed other assertion
        Assertions.assertEquals("", buf.readString(0));
    }

    @Test
    void testReadPeek_string_3_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 50);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals("abc", buf.peekString(3));
    }

    @Test
    void testReadPeek_string_4_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 50);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals("abc", buf.readString(3));
    }

    @Test
    void testReadPeek_string_5_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 50);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals("defgh", buf.peekString(100));
    }

    @Test
    void testReadPeek_string_6_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 50);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals("defgh", buf.readString(100));
    }

    @Test
    void testReadPeek_string_7_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 50);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(null, buf.peekString(1));
    }

    @Test
    void testReadPeek_string_8_oe() {
        // arrange
        final String str = "abcdefgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(str), 50);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(null, buf.readString(1));
    }

    @Test
    void testReadPeek_tring_zeroLen_1_oe() {
        // act/assert
        Assertions.assertNull(new CharReadBuffer(reader("")).peekString(0));
    }

    @Test
    void testReadPeek_tring_zeroLen_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertNull(new CharReadBuffer(reader("")).readString(0));
    }

    @Test
    void testReadPeek_tring_zeroLen_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals("", new CharReadBuffer(reader("a")).peekString(0));
    }

    @Test
    void testReadPeek_tring_zeroLen_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals("", new CharReadBuffer(reader("a")).readString(0));
    }

    @Test
    void testSkip_1_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

        // act/assert
        Assertions.assertEquals(0, buf.skip(0));
    }

    @Test
    void testSkip_2_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

        // act/assert
        // removed other assertion
        Assertions.assertEquals("a", buf.peekString(1));
    }

    @Test
    void testSkip_3_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, buf.skip(1));
    }

    @Test
    void testSkip_4_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals("b", buf.peekString(1));
    }

    @Test
    void testSkip_5_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4, buf.skip(4));
    }

    @Test
    void testSkip_6_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals("f", buf.peekString(1));
    }

    @Test
    void testSkip_7_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, buf.skip(1));
    }

    @Test
    void testSkip_8_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals("g", buf.peekString(1));
    }

    @Test
    void testSkip_9_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, buf.skip(100));
    }

    @Test
    void testSkip_10_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

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
        Assertions.assertNull(buf.peekString(1));
    }

    @Test
    void testSkip_11_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

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

        Assertions.assertEquals(0, buf.skip(100));
    }

    @Test
    void testSkip_12_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("abcdefg"), 3);
        buf.peekString(2);

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
        Assertions.assertNull(buf.peekString(1));
    }

    @Test
    void testPushString_emptyReader_1_oe() {
        // arrange
        final String a = "abcd";
        final String b = "efgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(""), 1);

        // act
        buf.pushString(a);
        buf.pushString(b);

        // assert
        Assertions.assertTrue(buf.hasMoreCharacters());
    }

    @Test
    void testPushString_emptyReader_2_oe() {
        // arrange
        final String a = "abcd";
        final String b = "efgh";
        final CharReadBuffer buf = new CharReadBuffer(reader(""), 1);

        // act
        buf.pushString(a);
        buf.pushString(b);

        // assert
        // removed other assertion
        Assertions.assertEquals("efghabcd", buf.readString(8));
    }

    @Test
    void testPushString_nonEmptyReader_1_oe() {
        // arrange
        final String a = "abcd";
        final String b = "efgh";
        final CharReadBuffer buf = new CharReadBuffer(reader("ABCD"), 1);

        // act
        buf.pushString(a);
        buf.pushString(b);

        // assert
        Assertions.assertTrue(buf.hasMoreCharacters());
    }

    @Test
    void testPushString_nonEmptyReader_2_oe() {
        // arrange
        final String a = "abcd";
        final String b = "efgh";
        final CharReadBuffer buf = new CharReadBuffer(reader("ABCD"), 1);

        // act
        buf.pushString(a);
        buf.pushString(b);

        // assert
        // removed other assertion
        Assertions.assertEquals("efghabcdABCD", buf.readString(12));
    }

    @Test
    void testPush_emptyReader_1_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("ABCD"), 1);

        // act
        buf.push('a');
        buf.push('b');
        buf.push('c');
        buf.push('d');

        // assert
        Assertions.assertTrue(buf.hasMoreCharacters());
    }

    @Test
    void testPush_emptyReader_2_oe() {
        // arrange
        final CharReadBuffer buf = new CharReadBuffer(reader("ABCD"), 1);

        // act
        buf.push('a');
        buf.push('b');
        buf.push('c');
        buf.push('d');

        // assert
        // removed other assertion
        Assertions.assertEquals("dcbaABCD", buf.readString(8));
    }

    @Test
    void testAlternatingPushAndRead_1_oe() {
        // arrange
        final String str = repeat("abcdefghijlmnopqrstuvwxyz", 10);

        final CharReadBuffer buf = new CharReadBuffer(reader(str), 8);

        final Random rnd = new Random(1L);

        // act
        final StringBuilder result = new StringBuilder();
        String tmp;
        while (buf.hasMoreCharacters()) {
            buf.pushString("ABC");

            tmp = buf.readString(rnd.nextInt(10) + 4);

            result.append(tmp.charAt(3));

            buf.pushString(tmp.substring(4));
        }

        // assert
        Assertions.assertEquals(str, result.toString());
    }

}
