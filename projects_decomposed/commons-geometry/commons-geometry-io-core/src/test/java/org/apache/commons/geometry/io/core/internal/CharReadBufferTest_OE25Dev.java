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


}
