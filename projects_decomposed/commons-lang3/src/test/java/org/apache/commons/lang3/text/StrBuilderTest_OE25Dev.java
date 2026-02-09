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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.CharBuffer;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link org.apache.commons.lang3.text.StrBuilder}.
 */
@Deprecated
public class StrBuilderTest_OE25Dev {

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    private static class MockReadable implements Readable {

        private final CharBuffer src;

        MockReadable(final String src) {
            this.src = CharBuffer.wrap(src);
        }

        @Override
        public int read(final CharBuffer cb) throws IOException {
            return src.read(cb);
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

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

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    static final StrMatcher A_NUMBER_MATCHER = new StrMatcher() {
        @Override
        public int isMatch(final char[] buffer, int pos, final int bufferStart, final int bufferEnd) {
            if (buffer[pos] == 'A') {
                pos++;
                if (pos < bufferEnd && buffer[pos] >= '0' && buffer[pos] <= '9') {
                    return 2;
                }
            }
            return 0;
        }
    };

    //-----------------------------------------------------------------------

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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @Test
    public void testConstructors_1_oe() {
        final StrBuilder sb0 = new StrBuilder();
        assertEquals(32, sb0.capacity());
    }

    @Test
    public void testConstructors_2_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        assertEquals(0, sb0.length());
    }

    @Test
    public void testConstructors_3_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb0.size());
    }

    @Test
    public void testConstructors_4_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        assertEquals(32, sb1.capacity());
    }

    @Test
    public void testConstructors_5_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        assertEquals(0, sb1.length());
    }

    @Test
    public void testConstructors_6_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb1.size());
    }

    @Test
    public void testConstructors_7_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        assertEquals(32, sb2.capacity());
    }

    @Test
    public void testConstructors_8_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        assertEquals(0, sb2.length());
    }

    @Test
    public void testConstructors_9_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb2.size());
    }

    @Test
    public void testConstructors_10_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        assertEquals(32, sb3.capacity());
    }

    @Test
    public void testConstructors_11_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        assertEquals(0, sb3.length());
    }

    @Test
    public void testConstructors_12_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb3.size());
    }

    @Test
    public void testConstructors_13_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        assertEquals(1, sb4.capacity());
    }

    @Test
    public void testConstructors_14_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        assertEquals(0, sb4.length());
    }

    @Test
    public void testConstructors_15_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb4.size());
    }

    @Test
    public void testConstructors_16_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb5 = new StrBuilder(null);
        assertEquals(32, sb5.capacity());
    }

    @Test
    public void testConstructors_17_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb5 = new StrBuilder(null);
        // removed other assertion
        assertEquals(0, sb5.length());
    }

    @Test
    public void testConstructors_18_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb5 = new StrBuilder(null);
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb5.size());
    }

    @Test
    public void testConstructors_19_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb5 = new StrBuilder(null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb6 = new StrBuilder("");
        assertEquals(32, sb6.capacity());
    }

    @Test
    public void testConstructors_20_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb5 = new StrBuilder(null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb6 = new StrBuilder("");
        // removed other assertion
        assertEquals(0, sb6.length());
    }

    @Test
    public void testConstructors_21_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb5 = new StrBuilder(null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb6 = new StrBuilder("");
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb6.size());
    }

    @Test
    public void testConstructors_22_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb5 = new StrBuilder(null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb6 = new StrBuilder("");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb7 = new StrBuilder("foo");
        assertEquals(35, sb7.capacity());
    }

    @Test
    public void testConstructors_23_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb5 = new StrBuilder(null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb6 = new StrBuilder("");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb7 = new StrBuilder("foo");
        // removed other assertion
        assertEquals(3, sb7.length());
    }

    @Test
    public void testConstructors_24_oe() {
        final StrBuilder sb0 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb3 = new StrBuilder(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb4 = new StrBuilder(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb5 = new StrBuilder(null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb6 = new StrBuilder("");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrBuilder sb7 = new StrBuilder("foo");
        // removed other assertion
        // removed other assertion
        assertEquals(3, sb7.size());
    }

    @Test
    public void testChaining_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertSame(sb, sb.setNewLineText(null));
    }

    @Test
    public void testChaining_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        assertSame(sb, sb.setNullText(null));
    }

    @Test
    public void testChaining_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        assertSame(sb, sb.setLength(1));
    }

    @Test
    public void testChaining_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(sb, sb.ensureCapacity(0));
    }

    @Test
    public void testChaining_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(sb, sb.minimizeCapacity());
    }

    @Test
    public void testChaining_7_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(sb, sb.clear());
    }

    @Test
    public void testChaining_8_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(sb, sb.reverse());
    }

    @Test
    public void testChaining_9_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(sb, sb.trim());
    }

    @Test
    public void testReadFromReader_1_oe() throws Exception {
        String s = "";
        for (int i = 0; i < 100; ++i) {
            final StrBuilder sb = new StrBuilder();
            final int len = sb.readFrom(new StringReader(s));

            assertEquals(s.length(), len);
    }
    }

    @Test
    public void testReadFromReader_2_oe() throws Exception {
        String s = "";
        for (int i = 0; i < 100; ++i) {
            final StrBuilder sb = new StrBuilder();
            final int len = sb.readFrom(new StringReader(s));

            // removed other assertion
            assertEquals(s, sb.toString());
    }
    }

    @Test
    public void testReadFromReaderAppendsToEnd_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("Test");
        sb.readFrom(new StringReader(" 123"));
        assertEquals("Test 123", sb.toString());
    }

    @Test
    public void testReadFromCharBuffer_1_oe() throws Exception {
        String s = "";
        for (int i = 0; i < 100; ++i) {
            final StrBuilder sb = new StrBuilder();
            final int len = sb.readFrom(CharBuffer.wrap(s));

            assertEquals(s.length(), len);
    }
    }

    @Test
    public void testReadFromCharBuffer_2_oe() throws Exception {
        String s = "";
        for (int i = 0; i < 100; ++i) {
            final StrBuilder sb = new StrBuilder();
            final int len = sb.readFrom(CharBuffer.wrap(s));

            // removed other assertion
            assertEquals(s, sb.toString());
    }
    }

    @Test
    public void testReadFromCharBufferAppendsToEnd_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("Test");
        sb.readFrom(CharBuffer.wrap(" 123"));
        assertEquals("Test 123", sb.toString());
    }

    @Test
    public void testReadFromReadable_1_oe() throws Exception {
        String s = "";
        for (int i = 0; i < 100; ++i) {
            final StrBuilder sb = new StrBuilder();
            final int len = sb.readFrom(new MockReadable(s));

            assertEquals(s.length(), len);
    }
    }

    @Test
    public void testReadFromReadable_2_oe() throws Exception {
        String s = "";
        for (int i = 0; i < 100; ++i) {
            final StrBuilder sb = new StrBuilder();
            final int len = sb.readFrom(new MockReadable(s));

            // removed other assertion
            assertEquals(s, sb.toString());
    }
    }

    @Test
    public void testReadFromReadableAppendsToEnd_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("Test");
        sb.readFrom(new MockReadable(" 123"));
        assertEquals("Test 123", sb.toString());
    }

    @Test
    public void testGetSetNewLineText_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertNull(sb.getNewLineText());
    }

    @Test
    public void testGetSetNewLineText_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.setNewLineText("#");
        assertEquals("#", sb.getNewLineText());
    }

    @Test
    public void testGetSetNewLineText_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.setNewLineText("#");
        // removed other assertion

        sb.setNewLineText("");
        assertEquals("", sb.getNewLineText());
    }

    @Test
    public void testGetSetNewLineText_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.setNewLineText("#");
        // removed other assertion

        sb.setNewLineText("");
        // removed other assertion

        sb.setNewLineText(null);
        assertNull(sb.getNewLineText());
    }

    @Test
    public void testGetSetNullText_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertNull(sb.getNullText());
    }

    @Test
    public void testGetSetNullText_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.setNullText("null");
        assertEquals("null", sb.getNullText());
    }

    @Test
    public void testGetSetNullText_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.setNullText("null");
        // removed other assertion

        sb.setNullText("");
        assertNull(sb.getNullText());
    }

    @Test
    public void testGetSetNullText_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.setNullText("null");
        // removed other assertion

        sb.setNullText("");
        // removed other assertion

        sb.setNullText("NULL");
        assertEquals("NULL", sb.getNullText());
    }

    @Test
    public void testGetSetNullText_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.setNullText("null");
        // removed other assertion

        sb.setNullText("");
        // removed other assertion

        sb.setNullText("NULL");
        // removed other assertion

        sb.setNullText(null);
        assertNull(sb.getNullText());
    }

    @Test
    public void testCapacityAndLength_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(32, sb.capacity());
    }

    @Test
    public void testCapacityAndLength_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        assertEquals(0, sb.length());
    }

    @Test
    public void testCapacityAndLength_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb.size());
    }

    @Test
    public void testCapacityAndLength_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        assertEquals(0, sb.capacity());
    }

    @Test
    public void testCapacityAndLength_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        assertEquals(0, sb.length());
    }

    @Test
    public void testCapacityAndLength_7_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb.size());
    }

    @Test
    public void testCapacityAndLength_8_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_9_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        assertTrue(sb.capacity() >= 32);
    }

    @Test
    public void testCapacityAndLength_10_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        assertEquals(0, sb.length());
    }

    @Test
    public void testCapacityAndLength_11_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb.size());
    }

    @Test
    public void testCapacityAndLength_12_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_13_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        assertTrue(sb.capacity() >= 32);
    }

    @Test
    public void testCapacityAndLength_14_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        assertEquals(3, sb.length());
    }

    @Test
    public void testCapacityAndLength_15_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        assertEquals(3, sb.size());
    }

    @Test
    public void testCapacityAndLength_16_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_17_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        assertTrue(sb.capacity() >= 32);
    }

    @Test
    public void testCapacityAndLength_18_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        assertEquals(0, sb.length());
    }

    @Test
    public void testCapacityAndLength_19_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb.size());
    }

    @Test
    public void testCapacityAndLength_20_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_21_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        assertTrue(sb.capacity() > 32);
    }

    @Test
    public void testCapacityAndLength_22_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        assertEquals(33, sb.length());
    }

    @Test
    public void testCapacityAndLength_23_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        assertEquals(33, sb.size());
    }

    @Test
    public void testCapacityAndLength_24_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_25_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        assertTrue(sb.capacity() > 16);
    }

    @Test
    public void testCapacityAndLength_26_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        assertEquals(33, sb.length());
    }

    @Test
    public void testCapacityAndLength_27_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        assertEquals(33, sb.size());
    }

    @Test
    public void testCapacityAndLength_28_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_29_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        assertEquals(33, sb.capacity());
    }

    @Test
    public void testCapacityAndLength_30_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        assertEquals(33, sb.length());
    }

    @Test
    public void testCapacityAndLength_31_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        assertEquals(33, sb.size());
    }

    @Test
    public void testCapacityAndLength_32_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_33_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb.setLength(-1), "setLength(-1) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testCapacityAndLength_34_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        assertEquals(33, sb.capacity());
    }

    @Test
    public void testCapacityAndLength_35_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        assertEquals(33, sb.length());
    }

    @Test
    public void testCapacityAndLength_36_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        assertEquals(33, sb.size());
    }

    @Test
    public void testCapacityAndLength_37_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_38_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        assertTrue(sb.capacity() >= 16);
    }

    @Test
    public void testCapacityAndLength_39_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        assertEquals(16, sb.length());
    }

    @Test
    public void testCapacityAndLength_40_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        assertEquals(16, sb.size());
    }

    @Test
    public void testCapacityAndLength_41_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1234567890123456", sb.toString());
    }

    @Test
    public void testCapacityAndLength_42_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_43_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(32);
        assertTrue(sb.capacity() >= 32);
    }

    @Test
    public void testCapacityAndLength_44_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(32);
        // removed other assertion
        assertEquals(32, sb.length());
    }

    @Test
    public void testCapacityAndLength_45_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(32);
        // removed other assertion
        // removed other assertion
        assertEquals(32, sb.size());
    }

    @Test
    public void testCapacityAndLength_46_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1234567890123456\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0", sb.toString());
    }

    @Test
    public void testCapacityAndLength_47_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.isEmpty());
    }

    @Test
    public void testCapacityAndLength_48_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(0);
        assertTrue(sb.capacity() >= 32);
    }

    @Test
    public void testCapacityAndLength_49_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(0);
        // removed other assertion
        assertEquals(0, sb.length());
    }

    @Test
    public void testCapacityAndLength_50_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(0);
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb.size());
    }

    @Test
    public void testCapacityAndLength_51_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("123456789012345678901234567890123");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.ensureCapacity(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.minimizeCapacity();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb.setLength(33);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(16);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(32);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.setLength(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(sb.isEmpty());
    }

    @Test
    public void testLength_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(0, sb.length());
    }

    @Test
    public void testLength_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("Hello");
        assertEquals(5, sb.length());
    }

    @Test
    public void testSetLength_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("Hello");
        sb.setLength(2);  // shorten
        assertEquals("He", sb.toString());
    }

    @Test
    public void testSetLength_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("Hello");
        sb.setLength(2);  // shorten
        // removed other assertion
        sb.setLength(2);  // no change
        assertEquals("He", sb.toString());
    }

    @Test
    public void testSetLength_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("Hello");
        sb.setLength(2);  // shorten
        // removed other assertion
        sb.setLength(2);  // no change
        // removed other assertion
        sb.setLength(3);  // lengthen
        assertEquals("He\0", sb.toString());
    }

    @Test
    public void testSetLength_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("Hello");
        sb.setLength(2);  // shorten
        // removed other assertion
        sb.setLength(2);  // no change
        // removed other assertion
        sb.setLength(3);  // lengthen
        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb.setLength(-1), "setLength(-1) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testCapacity_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(sb.buffer.length, sb.capacity());
    }

    @Test
    public void testCapacity_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("HelloWorldHelloWorldHelloWorldHelloWorld");
        assertEquals(sb.buffer.length, sb.capacity());
    }

    @Test
    public void testEnsureCapacity_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.ensureCapacity(2);
        assertTrue(sb.capacity() >= 2);
    }

    @Test
    public void testEnsureCapacity_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.ensureCapacity(2);
        // removed other assertion

        sb.ensureCapacity(-1);
        assertTrue(sb.capacity() >= 0);
    }

    @Test
    public void testEnsureCapacity_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.ensureCapacity(2);
        // removed other assertion

        sb.ensureCapacity(-1);
        // removed other assertion

        sb.append("HelloWorld");
        sb.ensureCapacity(40);
        assertTrue(sb.capacity() >= 40);
    }

    @Test
    public void testMinimizeCapacity_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.minimizeCapacity();
        assertEquals(0, sb.capacity());
    }

    @Test
    public void testMinimizeCapacity_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.minimizeCapacity();
        // removed other assertion

        sb.append("HelloWorld");
        sb.minimizeCapacity();
        assertEquals(10, sb.capacity());
    }

    @Test
    public void testSize_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(0, sb.size());
    }

    @Test
    public void testSize_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("Hello");
        assertEquals(5, sb.size());
    }

    @Test
    public void testIsEmpty_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertTrue(sb.isEmpty());
    }

    @Test
    public void testIsEmpty_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("Hello");
        assertFalse(sb.isEmpty());
    }

    @Test
    public void testIsEmpty_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("Hello");
        // removed other assertion

        sb.clear();
        assertTrue(sb.isEmpty());
    }

    @Test
    public void testClear_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("Hello");
        sb.clear();
        assertEquals(0, sb.length());
    }

    @Test
    public void testClear_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("Hello");
        sb.clear();
        // removed other assertion
        assertTrue(sb.buffer.length >= 5);
    }

    @Test
    public void testCharAt_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertThrows( IndexOutOfBoundsException.class, () -> sb.charAt(0), "charAt(0) expected IndexOutOfBoundsException");
    }

    @Test
    public void testCharAt_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        assertThrows( IndexOutOfBoundsException.class, () -> sb.charAt(-1), "charAt(-1) expected IndexOutOfBoundsException");
    }

    @Test
    public void testCharAt_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        sb.append("foo");
        assertEquals('f', sb.charAt(0));
    }

    @Test
    public void testCharAt_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        assertEquals('o', sb.charAt(1));
    }

    @Test
    public void testCharAt_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        // removed other assertion
        assertEquals('o', sb.charAt(2));
    }

    @Test
    public void testCharAt_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IndexOutOfBoundsException.class, () -> sb.charAt(-1), "charAt(-1) expected IndexOutOfBoundsException");
    }

    @Test
    public void testCharAt_7_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IndexOutOfBoundsException.class, () -> sb.charAt(3), "charAt(3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testSetCharAt_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertThrows( IndexOutOfBoundsException.class, () -> sb.setCharAt(0, 'f'), "setCharAt(0, ) expected IndexOutOfBoundsException");
    }

    @Test
    public void testSetCharAt_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        assertThrows( IndexOutOfBoundsException.class, () -> sb.setCharAt(-1, 'f'), "setCharAt(-1, ) expected IndexOutOfBoundsException");
    }

    @Test
    public void testSetCharAt_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        sb.append("foo");
        sb.setCharAt(0, 'b');
        sb.setCharAt(1, 'a');
        sb.setCharAt(2, 'r');
        assertThrows( IndexOutOfBoundsException.class, () -> sb.setCharAt(3, '!'), "setCharAt(3, ) expected IndexOutOfBoundsException");
    }

    @Test
    public void testSetCharAt_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        sb.append("foo");
        sb.setCharAt(0, 'b');
        sb.setCharAt(1, 'a');
        sb.setCharAt(2, 'r');
        // removed other assertion
        assertEquals("bar", sb.toString());
    }

    @Test
    public void testDeleteCharAt_1_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.deleteCharAt(0);
        assertEquals("bc", sb.toString());
    }

    @Test
    public void testDeleteCharAt_2_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.deleteCharAt(0);
        // removed other assertion

        assertThrows(IndexOutOfBoundsException.class, () -> sb.deleteCharAt(1000));
    }

    @Test
    public void testToCharArray_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(ArrayUtils.EMPTY_CHAR_ARRAY, sb.toCharArray());
    }

    @Test
    public void testToCharArray_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        char[] a = sb.toCharArray();
        assertNotNull(a, "toCharArray() result is null");
    }

    @Test
    public void testToCharArray_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        char[] a = sb.toCharArray();
        // removed other assertion
        assertEquals(0, a.length, "toCharArray() result is too large");
    }

    @Test
    public void testToCharArray_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        char[] a = sb.toCharArray();
        // removed other assertion
        // removed other assertion

        sb.append("junit");
        a = sb.toCharArray();
        assertEquals(5, a.length, "toCharArray() result incorrect length");
    }

    @Test
    public void testToCharArray_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        char[] a = sb.toCharArray();
        // removed other assertion
        // removed other assertion

        sb.append("junit");
        a = sb.toCharArray();
        // removed other assertion
        assertArrayEquals("junit".toCharArray(), a, "toCharArray() result does not match");
    }

    @Test
    public void testToCharArrayIntInt_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(ArrayUtils.EMPTY_CHAR_ARRAY, sb.toCharArray(0, 0));
    }

    @Test
    public void testToCharArrayIntInt_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        char[] a = sb.toCharArray(0, 20); // too large test
        assertEquals(5, a.length, "toCharArray(int, int) result incorrect length");
    }

    @Test
    public void testToCharArrayIntInt_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        char[] a = sb.toCharArray(0, 20); // too large test
        // removed other assertion
        assertArrayEquals("junit".toCharArray(), a, "toCharArray(int, int) result does not match");
    }

    @Test
    public void testToCharArrayIntInt_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        char[] a = sb.toCharArray(0, 20); // too large test
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        assertEquals(4, a.length, "toCharArray(int, int) result incorrect length");
    }

    @Test
    public void testToCharArrayIntInt_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        char[] a = sb.toCharArray(0, 20); // too large test
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        // removed other assertion
        assertArrayEquals("juni".toCharArray(), a, "toCharArray(int, int) result does not match");
    }

    @Test
    public void testToCharArrayIntInt_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        char[] a = sb.toCharArray(0, 20); // too large test
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        assertEquals(4, a.length, "toCharArray(int, int) result incorrect length");
    }

    @Test
    public void testToCharArrayIntInt_7_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        char[] a = sb.toCharArray(0, 20); // too large test
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        // removed other assertion
        assertArrayEquals("juni".toCharArray(), a, "toCharArray(int, int) result does not match");
    }

    @Test
    public void testToCharArrayIntInt_8_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        char[] a = sb.toCharArray(0, 20); // too large test
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 1);
        assertNotNull(a, "toCharArray(int, int) result is null");
    }

    @Test
    public void testToCharArrayIntInt_9_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        char[] a = sb.toCharArray(0, 20); // too large test
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 1);
        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb.toCharArray(-1, 5), "no string index out of bound on -1");
    }

    @Test
    public void testToCharArrayIntInt_10_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        char[] a = sb.toCharArray(0, 20); // too large test
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 4);
        // removed other assertion
        // removed other assertion

        a = sb.toCharArray(0, 1);
        // removed other assertion

        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb.toCharArray(6, 5), "no string index out of bound on -1");
    }

    @Test
    public void testGetChars_1_oe ( ) {
        final StrBuilder sb = new StrBuilder();

        char[] input = new char[10];
        char[] a = sb.getChars(input);
        assertSame (input, a);
    }

    @Test
    public void testGetChars_2_oe ( ) {
        final StrBuilder sb = new StrBuilder();

        char[] input = new char[10];
        char[] a = sb.getChars(input);
        // removed other assertion
        assertArrayEquals(new char[10], a);
    }

    @Test
    public void testGetChars_3_oe ( ) {
        final StrBuilder sb = new StrBuilder();

        char[] input = new char[10];
        char[] a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        sb.append("junit");
        a = sb.getChars(input);
        assertSame(input, a);
    }

    @Test
    public void testGetChars_4_oe ( ) {
        final StrBuilder sb = new StrBuilder();

        char[] input = new char[10];
        char[] a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        sb.append("junit");
        a = sb.getChars(input);
        // removed other assertion
        assertArrayEquals(new char[]{'j', 'u', 'n', 'i', 't', 0, 0, 0, 0, 0}, a);
    }

    @Test
    public void testGetChars_5_oe ( ) {
        final StrBuilder sb = new StrBuilder();

        char[] input = new char[10];
        char[] a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        sb.append("junit");
        a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        a = sb.getChars(null);
        assertNotSame(input, a);
    }

    @Test
    public void testGetChars_6_oe ( ) {
        final StrBuilder sb = new StrBuilder();

        char[] input = new char[10];
        char[] a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        sb.append("junit");
        a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        a = sb.getChars(null);
        // removed other assertion
        assertEquals(5, a.length);
    }

    @Test
    public void testGetChars_7_oe ( ) {
        final StrBuilder sb = new StrBuilder();

        char[] input = new char[10];
        char[] a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        sb.append("junit");
        a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        a = sb.getChars(null);
        // removed other assertion
        // removed other assertion
        assertArrayEquals("junit".toCharArray(), a);
    }

    @Test
    public void testGetChars_8_oe ( ) {
        final StrBuilder sb = new StrBuilder();

        char[] input = new char[10];
        char[] a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        sb.append("junit");
        a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        a = sb.getChars(null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        input = new char[5];
        a = sb.getChars(input);
        assertSame(input, a);
    }

    @Test
    public void testGetChars_9_oe ( ) {
        final StrBuilder sb = new StrBuilder();

        char[] input = new char[10];
        char[] a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        sb.append("junit");
        a = sb.getChars(input);
        // removed other assertion
        // removed other assertion

        a = sb.getChars(null);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        input = new char[5];
        a = sb.getChars(input);
        // removed other assertion

        input = new char[4];
        a = sb.getChars(input);
        assertNotSame(input, a);
    }

    @Test
    public void testGetCharsIntIntCharArrayInt_1_oe( ) {
        final StrBuilder sb = new StrBuilder();

        sb.append("junit");
        final char[] a = new char[5];
        sb.getChars(0, 5, a, 0);
        assertArrayEquals(new char[]{'j', 'u', 'n', 'i', 't'}, a);
    }

    @Test
    public void testGetCharsIntIntCharArrayInt_2_oe( ) {
        final StrBuilder sb = new StrBuilder();

        sb.append("junit");
        final char[] a = new char[5];
        sb.getChars(0, 5, a, 0);
        // removed other assertion

        final char[] b = new char[5];
        sb.getChars(0, 2, b, 3);
        assertArrayEquals(new char[]{0, 0, 0, 'j', 'u'}, b);
    }

    @Test
    public void testGetCharsIntIntCharArrayInt_3_oe( ) {
        final StrBuilder sb = new StrBuilder();

        sb.append("junit");
        final char[] a = new char[5];
        sb.getChars(0, 5, a, 0);
        // removed other assertion

        final char[] b = new char[5];
        sb.getChars(0, 2, b, 3);
        // removed other assertion

        assertThrows(IndexOutOfBoundsException.class, () -> sb.getChars(-1, 0, b, 0));
    }

    @Test
    public void testGetCharsIntIntCharArrayInt_4_oe( ) {
        final StrBuilder sb = new StrBuilder();

        sb.append("junit");
        final char[] a = new char[5];
        sb.getChars(0, 5, a, 0);
        // removed other assertion

        final char[] b = new char[5];
        sb.getChars(0, 2, b, 3);
        // removed other assertion

        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> sb.getChars(0, -1, b, 0));
    }

    @Test
    public void testGetCharsIntIntCharArrayInt_5_oe( ) {
        final StrBuilder sb = new StrBuilder();

        sb.append("junit");
        final char[] a = new char[5];
        sb.getChars(0, 5, a, 0);
        // removed other assertion

        final char[] b = new char[5];
        sb.getChars(0, 2, b, 3);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> sb.getChars(0, 20, b, 0));
    }

    @Test
    public void testGetCharsIntIntCharArrayInt_6_oe( ) {
        final StrBuilder sb = new StrBuilder();

        sb.append("junit");
        final char[] a = new char[5];
        sb.getChars(0, 5, a, 0);
        // removed other assertion

        final char[] b = new char[5];
        sb.getChars(0, 2, b, 3);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> sb.getChars(4, 2, b, 0));
    }

    @Test
    public void testDeleteIntInt_1_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.delete(0, 1);
        assertEquals("bc", sb.toString());
    }

    @Test
    public void testDeleteIntInt_2_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(1, 2);
        assertEquals("b", sb.toString());
    }

    @Test
    public void testDeleteIntInt_3_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(1, 2);
        // removed other assertion
        sb.delete(0, 1);
        assertEquals("", sb.toString());
    }

    @Test
    public void testDeleteIntInt_4_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(1, 2);
        // removed other assertion
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(0, 1000);
        assertEquals("", sb.toString());
    }

    @Test
    public void testDeleteIntInt_5_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(1, 2);
        // removed other assertion
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(0, 1000);
        // removed other assertion

        assertThrows(IndexOutOfBoundsException.class, () -> sb.delete(1, 2));
    }

    @Test
    public void testDeleteIntInt_6_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(1, 2);
        // removed other assertion
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(0, 1000);
        // removed other assertion

        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> sb.delete(-1, 1));
    }

    @Test
    public void testDeleteIntInt_7_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(1, 2);
        // removed other assertion
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(0, 1000);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> new StrBuilder("anything").delete(2, 1));
    }

    @Test
    public void testDeleteAll_char_1_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll('X');
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testDeleteAll_char_2_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll('X');
        // removed other assertion
        sb.deleteAll('a');
        assertEquals("bcbccb", sb.toString());
    }

    @Test
    public void testDeleteAll_char_3_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll('X');
        // removed other assertion
        sb.deleteAll('a');
        // removed other assertion
        sb.deleteAll('c');
        assertEquals("bbb", sb.toString());
    }

    @Test
    public void testDeleteAll_char_4_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll('X');
        // removed other assertion
        sb.deleteAll('a');
        // removed other assertion
        sb.deleteAll('c');
        // removed other assertion
        sb.deleteAll('b');
        assertEquals("", sb.toString());
    }

    @Test
    public void testDeleteAll_char_5_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll('X');
        // removed other assertion
        sb.deleteAll('a');
        // removed other assertion
        sb.deleteAll('c');
        // removed other assertion
        sb.deleteAll('b');
        // removed other assertion

        sb = new StrBuilder("");
        sb.deleteAll('b');
        assertEquals("", sb.toString());
    }

    @Test
    public void testDeleteFirst_char_1_oe() {
        StrBuilder sb = new StrBuilder("abcba");
        sb.deleteFirst('X');
        assertEquals("abcba", sb.toString());
    }

    @Test
    public void testDeleteFirst_char_2_oe() {
        StrBuilder sb = new StrBuilder("abcba");
        sb.deleteFirst('X');
        // removed other assertion
        sb.deleteFirst('a');
        assertEquals("bcba", sb.toString());
    }

    @Test
    public void testDeleteFirst_char_3_oe() {
        StrBuilder sb = new StrBuilder("abcba");
        sb.deleteFirst('X');
        // removed other assertion
        sb.deleteFirst('a');
        // removed other assertion
        sb.deleteFirst('c');
        assertEquals("bba", sb.toString());
    }

    @Test
    public void testDeleteFirst_char_4_oe() {
        StrBuilder sb = new StrBuilder("abcba");
        sb.deleteFirst('X');
        // removed other assertion
        sb.deleteFirst('a');
        // removed other assertion
        sb.deleteFirst('c');
        // removed other assertion
        sb.deleteFirst('b');
        assertEquals("ba", sb.toString());
    }

    @Test
    public void testDeleteFirst_char_5_oe() {
        StrBuilder sb = new StrBuilder("abcba");
        sb.deleteFirst('X');
        // removed other assertion
        sb.deleteFirst('a');
        // removed other assertion
        sb.deleteFirst('c');
        // removed other assertion
        sb.deleteFirst('b');
        // removed other assertion

        sb = new StrBuilder("");
        sb.deleteFirst('b');
        assertEquals("", sb.toString());
    }

    @Test
    public void testDeleteAll_String_1_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll((String) null);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testDeleteAll_String_2_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll((String) null);
        // removed other assertion
        sb.deleteAll("");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testDeleteAll_String_3_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll((String) null);
        // removed other assertion
        sb.deleteAll("");
        // removed other assertion

        sb.deleteAll("X");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testDeleteAll_String_4_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll((String) null);
        // removed other assertion
        sb.deleteAll("");
        // removed other assertion

        sb.deleteAll("X");
        // removed other assertion
        sb.deleteAll("a");
        assertEquals("bcbccb", sb.toString());
    }

    @Test
    public void testDeleteAll_String_5_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll((String) null);
        // removed other assertion
        sb.deleteAll("");
        // removed other assertion

        sb.deleteAll("X");
        // removed other assertion
        sb.deleteAll("a");
        // removed other assertion
        sb.deleteAll("c");
        assertEquals("bbb", sb.toString());
    }

    @Test
    public void testDeleteAll_String_6_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll((String) null);
        // removed other assertion
        sb.deleteAll("");
        // removed other assertion

        sb.deleteAll("X");
        // removed other assertion
        sb.deleteAll("a");
        // removed other assertion
        sb.deleteAll("c");
        // removed other assertion
        sb.deleteAll("b");
        assertEquals("", sb.toString());
    }

    @Test
    public void testDeleteAll_String_7_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll((String) null);
        // removed other assertion
        sb.deleteAll("");
        // removed other assertion

        sb.deleteAll("X");
        // removed other assertion
        sb.deleteAll("a");
        // removed other assertion
        sb.deleteAll("c");
        // removed other assertion
        sb.deleteAll("b");
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.deleteAll("bc");
        assertEquals("acba", sb.toString());
    }

    @Test
    public void testDeleteAll_String_8_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteAll((String) null);
        // removed other assertion
        sb.deleteAll("");
        // removed other assertion

        sb.deleteAll("X");
        // removed other assertion
        sb.deleteAll("a");
        // removed other assertion
        sb.deleteAll("c");
        // removed other assertion
        sb.deleteAll("b");
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.deleteAll("bc");
        // removed other assertion

        sb = new StrBuilder("");
        sb.deleteAll("bc");
        assertEquals("", sb.toString());
    }

    @Test
    public void testDeleteFirst_String_1_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteFirst((String) null);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testDeleteFirst_String_2_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteFirst((String) null);
        // removed other assertion
        sb.deleteFirst("");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testDeleteFirst_String_3_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteFirst((String) null);
        // removed other assertion
        sb.deleteFirst("");
        // removed other assertion

        sb.deleteFirst("X");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testDeleteFirst_String_4_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteFirst((String) null);
        // removed other assertion
        sb.deleteFirst("");
        // removed other assertion

        sb.deleteFirst("X");
        // removed other assertion
        sb.deleteFirst("a");
        assertEquals("bcbccba", sb.toString());
    }

    @Test
    public void testDeleteFirst_String_5_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteFirst((String) null);
        // removed other assertion
        sb.deleteFirst("");
        // removed other assertion

        sb.deleteFirst("X");
        // removed other assertion
        sb.deleteFirst("a");
        // removed other assertion
        sb.deleteFirst("c");
        assertEquals("bbccba", sb.toString());
    }

    @Test
    public void testDeleteFirst_String_6_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteFirst((String) null);
        // removed other assertion
        sb.deleteFirst("");
        // removed other assertion

        sb.deleteFirst("X");
        // removed other assertion
        sb.deleteFirst("a");
        // removed other assertion
        sb.deleteFirst("c");
        // removed other assertion
        sb.deleteFirst("b");
        assertEquals("bccba", sb.toString());
    }

    @Test
    public void testDeleteFirst_String_7_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteFirst((String) null);
        // removed other assertion
        sb.deleteFirst("");
        // removed other assertion

        sb.deleteFirst("X");
        // removed other assertion
        sb.deleteFirst("a");
        // removed other assertion
        sb.deleteFirst("c");
        // removed other assertion
        sb.deleteFirst("b");
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.deleteFirst("bc");
        assertEquals("abccba", sb.toString());
    }

    @Test
    public void testDeleteFirst_String_8_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.deleteFirst((String) null);
        // removed other assertion
        sb.deleteFirst("");
        // removed other assertion

        sb.deleteFirst("X");
        // removed other assertion
        sb.deleteFirst("a");
        // removed other assertion
        sb.deleteFirst("c");
        // removed other assertion
        sb.deleteFirst("b");
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.deleteFirst("bc");
        // removed other assertion

        sb = new StrBuilder("");
        sb.deleteFirst("bc");
        assertEquals("", sb.toString());
    }

    @Test
    public void testDeleteAll_StrMatcher_1_oe() {
        StrBuilder sb = new StrBuilder("A0xA1A2yA3");
        sb.deleteAll((StrMatcher) null);
        assertEquals("A0xA1A2yA3", sb.toString());
    }

    @Test
    public void testDeleteAll_StrMatcher_2_oe() {
        StrBuilder sb = new StrBuilder("A0xA1A2yA3");
        sb.deleteAll((StrMatcher) null);
        // removed other assertion
        sb.deleteAll(A_NUMBER_MATCHER);
        assertEquals("xy", sb.toString());
    }

    @Test
    public void testDeleteAll_StrMatcher_3_oe() {
        StrBuilder sb = new StrBuilder("A0xA1A2yA3");
        sb.deleteAll((StrMatcher) null);
        // removed other assertion
        sb.deleteAll(A_NUMBER_MATCHER);
        // removed other assertion

        sb = new StrBuilder("Ax1");
        sb.deleteAll(A_NUMBER_MATCHER);
        assertEquals("Ax1", sb.toString());
    }

    @Test
    public void testDeleteAll_StrMatcher_4_oe() {
        StrBuilder sb = new StrBuilder("A0xA1A2yA3");
        sb.deleteAll((StrMatcher) null);
        // removed other assertion
        sb.deleteAll(A_NUMBER_MATCHER);
        // removed other assertion

        sb = new StrBuilder("Ax1");
        sb.deleteAll(A_NUMBER_MATCHER);
        // removed other assertion

        sb = new StrBuilder("");
        sb.deleteAll(A_NUMBER_MATCHER);
        assertEquals("", sb.toString());
    }

    @Test
    public void testDeleteFirst_StrMatcher_1_oe() {
        StrBuilder sb = new StrBuilder("A0xA1A2yA3");
        sb.deleteFirst((StrMatcher) null);
        assertEquals("A0xA1A2yA3", sb.toString());
    }

    @Test
    public void testDeleteFirst_StrMatcher_2_oe() {
        StrBuilder sb = new StrBuilder("A0xA1A2yA3");
        sb.deleteFirst((StrMatcher) null);
        // removed other assertion
        sb.deleteFirst(A_NUMBER_MATCHER);
        assertEquals("xA1A2yA3", sb.toString());
    }

    @Test
    public void testDeleteFirst_StrMatcher_3_oe() {
        StrBuilder sb = new StrBuilder("A0xA1A2yA3");
        sb.deleteFirst((StrMatcher) null);
        // removed other assertion
        sb.deleteFirst(A_NUMBER_MATCHER);
        // removed other assertion

        sb = new StrBuilder("Ax1");
        sb.deleteFirst(A_NUMBER_MATCHER);
        assertEquals("Ax1", sb.toString());
    }

    @Test
    public void testDeleteFirst_StrMatcher_4_oe() {
        StrBuilder sb = new StrBuilder("A0xA1A2yA3");
        sb.deleteFirst((StrMatcher) null);
        // removed other assertion
        sb.deleteFirst(A_NUMBER_MATCHER);
        // removed other assertion

        sb = new StrBuilder("Ax1");
        sb.deleteFirst(A_NUMBER_MATCHER);
        // removed other assertion

        sb = new StrBuilder("");
        sb.deleteFirst(A_NUMBER_MATCHER);
        assertEquals("", sb.toString());
    }

    @Test
    public void testReplace_int_int_String_1_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.replace(0, 1, "d");
        assertEquals("dbc", sb.toString());
    }

    @Test
    public void testReplace_int_int_String_2_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.replace(0, 1, "d");
        // removed other assertion
        sb.replace(0, 1, "aaa");
        assertEquals("aaabc", sb.toString());
    }

    @Test
    public void testReplace_int_int_String_3_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.replace(0, 1, "d");
        // removed other assertion
        sb.replace(0, 1, "aaa");
        // removed other assertion
        sb.replace(0, 3, "");
        assertEquals("bc", sb.toString());
    }

    @Test
    public void testReplace_int_int_String_4_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.replace(0, 1, "d");
        // removed other assertion
        sb.replace(0, 1, "aaa");
        // removed other assertion
        sb.replace(0, 3, "");
        // removed other assertion
        sb.replace(1, 2, null);
        assertEquals("b", sb.toString());
    }

    @Test
    public void testReplace_int_int_String_5_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.replace(0, 1, "d");
        // removed other assertion
        sb.replace(0, 1, "aaa");
        // removed other assertion
        sb.replace(0, 3, "");
        // removed other assertion
        sb.replace(1, 2, null);
        // removed other assertion
        sb.replace(1, 1000, "text");
        assertEquals("btext", sb.toString());
    }

    @Test
    public void testReplace_int_int_String_6_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.replace(0, 1, "d");
        // removed other assertion
        sb.replace(0, 1, "aaa");
        // removed other assertion
        sb.replace(0, 3, "");
        // removed other assertion
        sb.replace(1, 2, null);
        // removed other assertion
        sb.replace(1, 1000, "text");
        // removed other assertion
        sb.replace(0, 1000, "text");
        assertEquals("text", sb.toString());
    }

    @Test
    public void testReplace_int_int_String_7_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.replace(0, 1, "d");
        // removed other assertion
        sb.replace(0, 1, "aaa");
        // removed other assertion
        sb.replace(0, 3, "");
        // removed other assertion
        sb.replace(1, 2, null);
        // removed other assertion
        sb.replace(1, 1000, "text");
        // removed other assertion
        sb.replace(0, 1000, "text");
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder("atext");
        sb1.replace(1, 1, "ny");
        assertEquals("anytext", sb1.toString());
    }

    @Test
    public void testReplace_int_int_String_8_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.replace(0, 1, "d");
        // removed other assertion
        sb.replace(0, 1, "aaa");
        // removed other assertion
        sb.replace(0, 3, "");
        // removed other assertion
        sb.replace(1, 2, null);
        // removed other assertion
        sb.replace(1, 1000, "text");
        // removed other assertion
        sb.replace(0, 1000, "text");
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder("atext");
        sb1.replace(1, 1, "ny");
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> sb1.replace(2, 1, "anything"));
    }

    @Test
    public void testReplace_int_int_String_9_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.replace(0, 1, "d");
        // removed other assertion
        sb.replace(0, 1, "aaa");
        // removed other assertion
        sb.replace(0, 3, "");
        // removed other assertion
        sb.replace(1, 2, null);
        // removed other assertion
        sb.replace(1, 1000, "text");
        // removed other assertion
        sb.replace(0, 1000, "text");
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder("atext");
        sb1.replace(1, 1, "ny");
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder();
        assertThrows(IndexOutOfBoundsException.class, () -> sb2.replace(1, 2, "anything"));
    }

    @Test
    public void testReplace_int_int_String_10_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        sb.replace(0, 1, "d");
        // removed other assertion
        sb.replace(0, 1, "aaa");
        // removed other assertion
        sb.replace(0, 3, "");
        // removed other assertion
        sb.replace(1, 2, null);
        // removed other assertion
        sb.replace(1, 1000, "text");
        // removed other assertion
        sb.replace(0, 1000, "text");
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder("atext");
        sb1.replace(1, 1, "ny");
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> sb2.replace(-1, 1, "anything"));
    }

    @Test
    public void testReplaceAll_char_char_1_oe() {
        final StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll('x', 'y');
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_char_char_2_oe() {
        final StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll('x', 'y');
        // removed other assertion
        sb.replaceAll('a', 'd');
        assertEquals("dbcbccbd", sb.toString());
    }

    @Test
    public void testReplaceAll_char_char_3_oe() {
        final StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll('x', 'y');
        // removed other assertion
        sb.replaceAll('a', 'd');
        // removed other assertion
        sb.replaceAll('b', 'e');
        assertEquals("dececced", sb.toString());
    }

    @Test
    public void testReplaceAll_char_char_4_oe() {
        final StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll('x', 'y');
        // removed other assertion
        sb.replaceAll('a', 'd');
        // removed other assertion
        sb.replaceAll('b', 'e');
        // removed other assertion
        sb.replaceAll('c', 'f');
        assertEquals("defeffed", sb.toString());
    }

    @Test
    public void testReplaceAll_char_char_5_oe() {
        final StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll('x', 'y');
        // removed other assertion
        sb.replaceAll('a', 'd');
        // removed other assertion
        sb.replaceAll('b', 'e');
        // removed other assertion
        sb.replaceAll('c', 'f');
        // removed other assertion
        sb.replaceAll('d', 'd');
        assertEquals("defeffed", sb.toString());
    }

    @Test
    public void testReplaceFirst_char_char_1_oe() {
        final StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst('x', 'y');
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_char_char_2_oe() {
        final StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst('x', 'y');
        // removed other assertion
        sb.replaceFirst('a', 'd');
        assertEquals("dbcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_char_char_3_oe() {
        final StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst('x', 'y');
        // removed other assertion
        sb.replaceFirst('a', 'd');
        // removed other assertion
        sb.replaceFirst('b', 'e');
        assertEquals("decbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_char_char_4_oe() {
        final StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst('x', 'y');
        // removed other assertion
        sb.replaceFirst('a', 'd');
        // removed other assertion
        sb.replaceFirst('b', 'e');
        // removed other assertion
        sb.replaceFirst('c', 'f');
        assertEquals("defbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_char_char_5_oe() {
        final StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst('x', 'y');
        // removed other assertion
        sb.replaceFirst('a', 'd');
        // removed other assertion
        sb.replaceFirst('b', 'e');
        // removed other assertion
        sb.replaceFirst('c', 'f');
        // removed other assertion
        sb.replaceFirst('d', 'd');
        assertEquals("defbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_String_String_1_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((String) null, null);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_String_String_2_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((String) null, null);
        // removed other assertion
        sb.replaceAll((String) null, "anything");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_String_String_3_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((String) null, null);
        // removed other assertion
        sb.replaceAll((String) null, "anything");
        // removed other assertion
        sb.replaceAll("", null);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_String_String_4_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((String) null, null);
        // removed other assertion
        sb.replaceAll((String) null, "anything");
        // removed other assertion
        sb.replaceAll("", null);
        // removed other assertion
        sb.replaceAll("", "anything");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_String_String_5_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((String) null, null);
        // removed other assertion
        sb.replaceAll((String) null, "anything");
        // removed other assertion
        sb.replaceAll("", null);
        // removed other assertion
        sb.replaceAll("", "anything");
        // removed other assertion

        sb.replaceAll("x", "y");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_String_String_6_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((String) null, null);
        // removed other assertion
        sb.replaceAll((String) null, "anything");
        // removed other assertion
        sb.replaceAll("", null);
        // removed other assertion
        sb.replaceAll("", "anything");
        // removed other assertion

        sb.replaceAll("x", "y");
        // removed other assertion
        sb.replaceAll("a", "d");
        assertEquals("dbcbccbd", sb.toString());
    }

    @Test
    public void testReplaceAll_String_String_7_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((String) null, null);
        // removed other assertion
        sb.replaceAll((String) null, "anything");
        // removed other assertion
        sb.replaceAll("", null);
        // removed other assertion
        sb.replaceAll("", "anything");
        // removed other assertion

        sb.replaceAll("x", "y");
        // removed other assertion
        sb.replaceAll("a", "d");
        // removed other assertion
        sb.replaceAll("d", null);
        assertEquals("bcbccb", sb.toString());
    }

    @Test
    public void testReplaceAll_String_String_8_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((String) null, null);
        // removed other assertion
        sb.replaceAll((String) null, "anything");
        // removed other assertion
        sb.replaceAll("", null);
        // removed other assertion
        sb.replaceAll("", "anything");
        // removed other assertion

        sb.replaceAll("x", "y");
        // removed other assertion
        sb.replaceAll("a", "d");
        // removed other assertion
        sb.replaceAll("d", null);
        // removed other assertion
        sb.replaceAll("cb", "-");
        assertEquals("b-c-", sb.toString());
    }

    @Test
    public void testReplaceAll_String_String_9_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((String) null, null);
        // removed other assertion
        sb.replaceAll((String) null, "anything");
        // removed other assertion
        sb.replaceAll("", null);
        // removed other assertion
        sb.replaceAll("", "anything");
        // removed other assertion

        sb.replaceAll("x", "y");
        // removed other assertion
        sb.replaceAll("a", "d");
        // removed other assertion
        sb.replaceAll("d", null);
        // removed other assertion
        sb.replaceAll("cb", "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceAll("b", "xbx");
        assertEquals("axbxcxbxa", sb.toString());
    }

    @Test
    public void testReplaceAll_String_String_10_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((String) null, null);
        // removed other assertion
        sb.replaceAll((String) null, "anything");
        // removed other assertion
        sb.replaceAll("", null);
        // removed other assertion
        sb.replaceAll("", "anything");
        // removed other assertion

        sb.replaceAll("x", "y");
        // removed other assertion
        sb.replaceAll("a", "d");
        // removed other assertion
        sb.replaceAll("d", null);
        // removed other assertion
        sb.replaceAll("cb", "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceAll("b", "xbx");
        // removed other assertion

        sb = new StrBuilder("bb");
        sb.replaceAll("b", "xbx");
        assertEquals("xbxxbx", sb.toString());
    }

    @Test
    public void testReplaceFirst_String_String_1_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((String) null, null);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_String_String_2_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((String) null, null);
        // removed other assertion
        sb.replaceFirst((String) null, "anything");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_String_String_3_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((String) null, null);
        // removed other assertion
        sb.replaceFirst((String) null, "anything");
        // removed other assertion
        sb.replaceFirst("", null);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_String_String_4_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((String) null, null);
        // removed other assertion
        sb.replaceFirst((String) null, "anything");
        // removed other assertion
        sb.replaceFirst("", null);
        // removed other assertion
        sb.replaceFirst("", "anything");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_String_String_5_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((String) null, null);
        // removed other assertion
        sb.replaceFirst((String) null, "anything");
        // removed other assertion
        sb.replaceFirst("", null);
        // removed other assertion
        sb.replaceFirst("", "anything");
        // removed other assertion

        sb.replaceFirst("x", "y");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_String_String_6_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((String) null, null);
        // removed other assertion
        sb.replaceFirst((String) null, "anything");
        // removed other assertion
        sb.replaceFirst("", null);
        // removed other assertion
        sb.replaceFirst("", "anything");
        // removed other assertion

        sb.replaceFirst("x", "y");
        // removed other assertion
        sb.replaceFirst("a", "d");
        assertEquals("dbcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_String_String_7_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((String) null, null);
        // removed other assertion
        sb.replaceFirst((String) null, "anything");
        // removed other assertion
        sb.replaceFirst("", null);
        // removed other assertion
        sb.replaceFirst("", "anything");
        // removed other assertion

        sb.replaceFirst("x", "y");
        // removed other assertion
        sb.replaceFirst("a", "d");
        // removed other assertion
        sb.replaceFirst("d", null);
        assertEquals("bcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_String_String_8_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((String) null, null);
        // removed other assertion
        sb.replaceFirst((String) null, "anything");
        // removed other assertion
        sb.replaceFirst("", null);
        // removed other assertion
        sb.replaceFirst("", "anything");
        // removed other assertion

        sb.replaceFirst("x", "y");
        // removed other assertion
        sb.replaceFirst("a", "d");
        // removed other assertion
        sb.replaceFirst("d", null);
        // removed other assertion
        sb.replaceFirst("cb", "-");
        assertEquals("b-ccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_String_String_9_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((String) null, null);
        // removed other assertion
        sb.replaceFirst((String) null, "anything");
        // removed other assertion
        sb.replaceFirst("", null);
        // removed other assertion
        sb.replaceFirst("", "anything");
        // removed other assertion

        sb.replaceFirst("x", "y");
        // removed other assertion
        sb.replaceFirst("a", "d");
        // removed other assertion
        sb.replaceFirst("d", null);
        // removed other assertion
        sb.replaceFirst("cb", "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceFirst("b", "xbx");
        assertEquals("axbxcba", sb.toString());
    }

    @Test
    public void testReplaceFirst_String_String_10_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((String) null, null);
        // removed other assertion
        sb.replaceFirst((String) null, "anything");
        // removed other assertion
        sb.replaceFirst("", null);
        // removed other assertion
        sb.replaceFirst("", "anything");
        // removed other assertion

        sb.replaceFirst("x", "y");
        // removed other assertion
        sb.replaceFirst("a", "d");
        // removed other assertion
        sb.replaceFirst("d", null);
        // removed other assertion
        sb.replaceFirst("cb", "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceFirst("b", "xbx");
        // removed other assertion

        sb = new StrBuilder("bb");
        sb.replaceFirst("b", "xbx");
        assertEquals("xbxb", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_1_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_2_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_3_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), null);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_4_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), "anything");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_5_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceAll(StrMatcher.charMatcher('x'), "y");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_6_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceAll(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('a'), "d");
        assertEquals("dbcbccbd", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_7_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceAll(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('d'), null);
        assertEquals("bcbccb", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_8_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceAll(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('d'), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.stringMatcher("cb"), "-");
        assertEquals("b-c-", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_9_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceAll(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('d'), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.stringMatcher("cb"), "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceAll(StrMatcher.charMatcher('b'), "xbx");
        assertEquals("axbxcxbxa", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_10_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceAll(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('d'), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.stringMatcher("cb"), "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceAll(StrMatcher.charMatcher('b'), "xbx");
        // removed other assertion

        sb = new StrBuilder("bb");
        sb.replaceAll(StrMatcher.charMatcher('b'), "xbx");
        assertEquals("xbxxbx", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_11_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceAll(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('d'), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.stringMatcher("cb"), "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceAll(StrMatcher.charMatcher('b'), "xbx");
        // removed other assertion

        sb = new StrBuilder("bb");
        sb.replaceAll(StrMatcher.charMatcher('b'), "xbx");
        // removed other assertion

        sb = new StrBuilder("A1-A2A3-A4");
        sb.replaceAll(A_NUMBER_MATCHER, "***");
        assertEquals("***-******-***", sb.toString());
    }

    @Test
    public void testReplaceAll_StrMatcher_String_12_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceAll((StrMatcher) null, null);
        // removed other assertion
        sb.replaceAll((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceAll(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceAll(StrMatcher.charMatcher('d'), null);
        // removed other assertion
        sb.replaceAll(StrMatcher.stringMatcher("cb"), "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceAll(StrMatcher.charMatcher('b'), "xbx");
        // removed other assertion

        sb = new StrBuilder("bb");
        sb.replaceAll(StrMatcher.charMatcher('b'), "xbx");
        // removed other assertion

        sb = new StrBuilder("A1-A2A3-A4");
        sb.replaceAll(A_NUMBER_MATCHER, "***");
        // removed other assertion

        sb = new StrBuilder("Dear X, hello X.");
        sb.replaceAll(StrMatcher.stringMatcher("X"), "012345678901234567");
        assertEquals("Dear 012345678901234567, hello 012345678901234567.", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_1_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_2_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        // removed other assertion
        sb.replaceFirst((StrMatcher) null, "anything");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_3_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        // removed other assertion
        sb.replaceFirst((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), null);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_4_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        // removed other assertion
        sb.replaceFirst((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), "anything");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_5_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        // removed other assertion
        sb.replaceFirst((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceFirst(StrMatcher.charMatcher('x'), "y");
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_6_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        // removed other assertion
        sb.replaceFirst((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceFirst(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('a'), "d");
        assertEquals("dbcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_7_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        // removed other assertion
        sb.replaceFirst((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceFirst(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('d'), null);
        assertEquals("bcbccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_8_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        // removed other assertion
        sb.replaceFirst((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceFirst(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('d'), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.stringMatcher("cb"), "-");
        assertEquals("b-ccba", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_9_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        // removed other assertion
        sb.replaceFirst((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceFirst(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('d'), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.stringMatcher("cb"), "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceFirst(StrMatcher.charMatcher('b'), "xbx");
        assertEquals("axbxcba", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_10_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        // removed other assertion
        sb.replaceFirst((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceFirst(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('d'), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.stringMatcher("cb"), "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceFirst(StrMatcher.charMatcher('b'), "xbx");
        // removed other assertion

        sb = new StrBuilder("bb");
        sb.replaceFirst(StrMatcher.charMatcher('b'), "xbx");
        assertEquals("xbxb", sb.toString());
    }

    @Test
    public void testReplaceFirst_StrMatcher_String_11_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replaceFirst((StrMatcher) null, null);
        // removed other assertion
        sb.replaceFirst((StrMatcher) null, "anything");
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.noneMatcher(), "anything");
        // removed other assertion

        sb.replaceFirst(StrMatcher.charMatcher('x'), "y");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('a'), "d");
        // removed other assertion
        sb.replaceFirst(StrMatcher.charMatcher('d'), null);
        // removed other assertion
        sb.replaceFirst(StrMatcher.stringMatcher("cb"), "-");
        // removed other assertion

        sb = new StrBuilder("abcba");
        sb.replaceFirst(StrMatcher.charMatcher('b'), "xbx");
        // removed other assertion

        sb = new StrBuilder("bb");
        sb.replaceFirst(StrMatcher.charMatcher('b'), "xbx");
        // removed other assertion

        sb = new StrBuilder("A1-A2A3-A4");
        sb.replaceFirst(A_NUMBER_MATCHER, "***");
        assertEquals("***-A2A3-A4", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryMatcher_1_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replace(null, "x", 0, sb.length(), -1);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryMatcher_2_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replace(null, "x", 0, sb.length(), -1);
        // removed other assertion

        sb.replace(StrMatcher.charMatcher('a'), "x", 0, sb.length(), -1);
        assertEquals("xbcbccbx", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryMatcher_3_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replace(null, "x", 0, sb.length(), -1);
        // removed other assertion

        sb.replace(StrMatcher.charMatcher('a'), "x", 0, sb.length(), -1);
        // removed other assertion

        sb.replace(StrMatcher.stringMatcher("cb"), "x", 0, sb.length(), -1);
        assertEquals("xbxcxx", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryMatcher_4_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replace(null, "x", 0, sb.length(), -1);
        // removed other assertion

        sb.replace(StrMatcher.charMatcher('a'), "x", 0, sb.length(), -1);
        // removed other assertion

        sb.replace(StrMatcher.stringMatcher("cb"), "x", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("A1-A2A3-A4");
        sb.replace(A_NUMBER_MATCHER, "***", 0, sb.length(), -1);
        assertEquals("***-******-***", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryMatcher_5_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replace(null, "x", 0, sb.length(), -1);
        // removed other assertion

        sb.replace(StrMatcher.charMatcher('a'), "x", 0, sb.length(), -1);
        // removed other assertion

        sb.replace(StrMatcher.stringMatcher("cb"), "x", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("A1-A2A3-A4");
        sb.replace(A_NUMBER_MATCHER, "***", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder();
        sb.replace(A_NUMBER_MATCHER, "***", 0, sb.length(), -1);
        assertEquals("", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryReplace_1_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "cb", 0, sb.length(), -1);
        assertEquals("abcbccba", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryReplace_2_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "cb", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "-", 0, sb.length(), -1);
        assertEquals("ab-c-a", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryReplace_3_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "cb", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "+++", 0, sb.length(), -1);
        assertEquals("ab+++c+++a", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryReplace_4_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "cb", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "+++", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "", 0, sb.length(), -1);
        assertEquals("abca", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryReplace_5_oe() {
        StrBuilder sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "cb", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "+++", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), "", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("abcbccba");
        sb.replace(StrMatcher.stringMatcher("cb"), null, 0, sb.length(), -1);
        assertEquals("abca", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_1_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        assertEquals("-x--y-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_2_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        assertEquals("aax--y-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_3_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        assertEquals("aax--y-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_4_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        assertEquals("aax--y-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_5_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        assertEquals("aaxa-ay-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_6_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 5, sb.length(), -1);
        assertEquals("aaxaa-y-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_7_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 5, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 6, sb.length(), -1);
        assertEquals("aaxaaaay-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_8_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 5, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 6, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 7, sb.length(), -1);
        assertEquals("aaxaaaay-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_9_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 5, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 6, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 7, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 8, sb.length(), -1);
        assertEquals("aaxaaaay-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_10_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 5, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 6, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 7, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 8, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 9, sb.length(), -1);
        assertEquals("aaxaaaayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_11_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 5, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 6, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 7, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 8, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 9, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 10, sb.length(), -1);
        assertEquals("aaxaaaayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_12_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 5, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 6, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 7, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 8, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 9, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 10, sb.length(), -1);
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder("aaxaaaayaa");
        assertThrows( IndexOutOfBoundsException.class, () -> sb1.replace(StrMatcher.stringMatcher("aa"), "-", 11, sb1.length(), -1));
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_13_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 5, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 6, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 7, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 8, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 9, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 10, sb.length(), -1);
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder("aaxaaaayaa");
        // removed other assertion
        assertEquals("aaxaaaayaa", sb1.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_14_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 5, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 6, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 7, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 8, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 9, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 10, sb.length(), -1);
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder("aaxaaaayaa");
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder("aaxaaaayaa");
        assertThrows( IndexOutOfBoundsException.class, () -> sb2.replace(StrMatcher.stringMatcher("aa"), "-", -1, sb2.length(), -1));
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_15_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 1, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 2, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 3, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 4, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 5, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 6, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 7, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 8, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 9, sb.length(), -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 10, sb.length(), -1);
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder("aaxaaaayaa");
        // removed other assertion
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder("aaxaaaayaa");
        // removed other assertion
        assertEquals("aaxaaaayaa", sb2.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_1_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        assertEquals("aaxaaaayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_2_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        assertEquals("-xaaaayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_3_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        assertEquals("-xaaaayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_4_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 4, -1);
        assertEquals("-xaaaayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_5_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 4, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 5, -1);
        assertEquals("-x-aayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_6_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 4, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 5, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 6, -1);
        assertEquals("-x-aayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_7_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 4, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 5, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 6, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 7, -1);
        assertEquals("-x--yaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_8_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 4, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 5, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 6, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 7, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 8, -1);
        assertEquals("-x--yaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_9_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 4, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 5, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 6, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 7, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 8, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 9, -1);
        assertEquals("-x--yaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_10_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 4, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 5, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 6, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 7, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 8, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 9, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        assertEquals("-x--y-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_11_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 4, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 5, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 6, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 7, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 8, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 9, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 1000, -1);
        assertEquals("-x--y-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_12_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 4, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 5, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 6, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 7, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 8, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 9, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 1000, -1);
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder("aaxaaaayaa");
        assertThrows( IndexOutOfBoundsException.class, () -> sb1.replace(StrMatcher.stringMatcher("aa"), "-", 2, 1, -1));
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_13_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 0, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 2, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 3, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 4, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 5, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 6, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 7, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 8, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 9, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 1000, -1);
        // removed other assertion

        final StrBuilder sb1 = new StrBuilder("aaxaaaayaa");
        // removed other assertion
        assertEquals("aaxaaaayaa", sb1.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryCount_1_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        assertEquals("-x--y-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryCount_2_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 0);
        assertEquals("aaxaaaayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryCount_3_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 0);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 1);
        assertEquals("-xaaaayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryCount_4_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 0);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 2);
        assertEquals("-x-aayaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryCount_5_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 0);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 2);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 3);
        assertEquals("-x--yaa", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryCount_6_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 0);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 2);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 3);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 4);
        assertEquals("-x--y-", sb.toString());
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryCount_7_oe() {
        StrBuilder sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, -1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 0);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 1);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 2);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 3);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 4);
        // removed other assertion

        sb = new StrBuilder("aaxaaaayaa");
        sb.replace(StrMatcher.stringMatcher("aa"), "-", 0, 10, 5);
        assertEquals("-x--y-", sb.toString());
    }

    @Test
    public void testReverse_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals("", sb.reverse().toString());
    }

    @Test
    public void testReverse_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.clear().append(true);
        assertEquals("eurt", sb.reverse().toString());
    }

    @Test
    public void testTrim_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals("", sb.reverse().toString());
    }

    @Test
    public void testTrim_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.clear().append(" \u0000 ");
        assertEquals("", sb.trim().toString());
    }

    @Test
    public void testTrim_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.clear().append(" \u0000 ");
        // removed other assertion

        sb.clear().append(" \u0000 a b c");
        assertEquals("a b c", sb.trim().toString());
    }

    @Test
    public void testTrim_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.clear().append(" \u0000 ");
        // removed other assertion

        sb.clear().append(" \u0000 a b c");
        // removed other assertion

        sb.clear().append("a b c \u0000 ");
        assertEquals("a b c", sb.trim().toString());
    }

    @Test
    public void testTrim_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.clear().append(" \u0000 ");
        // removed other assertion

        sb.clear().append(" \u0000 a b c");
        // removed other assertion

        sb.clear().append("a b c \u0000 ");
        // removed other assertion

        sb.clear().append(" \u0000 a b c \u0000 ");
        assertEquals("a b c", sb.trim().toString());
    }

    @Test
    public void testTrim_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.clear().append(" \u0000 ");
        // removed other assertion

        sb.clear().append(" \u0000 a b c");
        // removed other assertion

        sb.clear().append("a b c \u0000 ");
        // removed other assertion

        sb.clear().append(" \u0000 a b c \u0000 ");
        // removed other assertion

        sb.clear().append("a b c");
        assertEquals("a b c", sb.trim().toString());
    }

    @Test
    public void testStartsWith_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertFalse(sb.startsWith("a"));
    }

    @Test
    public void testStartsWith_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        assertFalse(sb.startsWith(null));
    }

    @Test
    public void testStartsWith_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        assertTrue(sb.startsWith(""));
    }

    @Test
    public void testStartsWith_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        assertTrue(sb.startsWith("a"));
    }

    @Test
    public void testStartsWith_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        // removed other assertion
        assertTrue(sb.startsWith("ab"));
    }

    @Test
    public void testStartsWith_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        // removed other assertion
        // removed other assertion
        assertTrue(sb.startsWith("abc"));
    }

    @Test
    public void testStartsWith_7_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.startsWith("cba"));
    }

    @Test
    public void testEndsWith_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertFalse(sb.endsWith("a"));
    }

    @Test
    public void testEndsWith_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        assertFalse(sb.endsWith("c"));
    }

    @Test
    public void testEndsWith_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        assertTrue(sb.endsWith(""));
    }

    @Test
    public void testEndsWith_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.endsWith(null));
    }

    @Test
    public void testEndsWith_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        assertTrue(sb.endsWith("c"));
    }

    @Test
    public void testEndsWith_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        // removed other assertion
        assertTrue(sb.endsWith("bc"));
    }

    @Test
    public void testEndsWith_7_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        // removed other assertion
        // removed other assertion
        assertTrue(sb.endsWith("abc"));
    }

    @Test
    public void testEndsWith_8_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.endsWith("cba"));
    }

    @Test
    public void testEndsWith_9_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.endsWith("abcd"));
    }

    @Test
    public void testEndsWith_10_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.endsWith(" abc"));
    }

    @Test
    public void testEndsWith_11_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        sb.append("abc");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.endsWith("abc "));
    }

    @Test
    public void testSubSequenceIntInt_1_oe() {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        assertThrows(IndexOutOfBoundsException.class, () -> sb.subSequence(-1, 5));
    }

    @Test
    public void testSubSequenceIntInt_2_oe() {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        // removed other assertion

        // End index is negative
        assertThrows(IndexOutOfBoundsException.class, () -> sb.subSequence(2, -1));
    }

    @Test
    public void testSubSequenceIntInt_3_oe() {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        // removed other assertion

        // End index is negative
        // removed other assertion

        // End index greater than length()
        assertThrows(IndexOutOfBoundsException.class, () -> sb.subSequence(2, sb.length() + 1));
    }

    @Test
    public void testSubSequenceIntInt_4_oe() {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        // removed other assertion

        // End index is negative
        // removed other assertion

        // End index greater than length()
        // removed other assertion

        // Start index greater then end index
        assertThrows(IndexOutOfBoundsException.class, () -> sb.subSequence(3, 2));
    }

    @Test
    public void testSubSequenceIntInt_5_oe() {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        // removed other assertion

        // End index is negative
        // removed other assertion

        // End index greater than length()
        // removed other assertion

        // Start index greater then end index
        // removed other assertion

        // Normal cases
        assertEquals ("hello", sb.subSequence(0, 5));
    }

    @Test
    public void testSubSequenceIntInt_6_oe() {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        // removed other assertion

        // End index is negative
        // removed other assertion

        // End index greater than length()
        // removed other assertion

        // Start index greater then end index
        // removed other assertion

        // Normal cases
        // removed other assertion
        assertEquals ("hello goodbye".subSequence(0, 6), sb.subSequence(0, 6));
    }

    @Test
    public void testSubSequenceIntInt_7_oe() {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        // removed other assertion

        // End index is negative
        // removed other assertion

        // End index greater than length()
        // removed other assertion

        // Start index greater then end index
        // removed other assertion

        // Normal cases
        // removed other assertion
        // removed other assertion
        assertEquals ("goodbye", sb.subSequence(6, 13));
    }

    @Test
    public void testSubSequenceIntInt_8_oe() {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        // removed other assertion

        // End index is negative
        // removed other assertion

        // End index greater than length()
        // removed other assertion

        // Start index greater then end index
        // removed other assertion

        // Normal cases
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals ("hello goodbye".subSequence(6, 13), sb.subSequence(6, 13));
    }

    @Test
    public void testSubstringInt_1_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        assertEquals ("goodbye", sb.substring(6));
    }

    @Test
    public void testSubstringInt_2_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        assertEquals ("hello goodbye".substring(6), sb.substring(6));
    }

    @Test
    public void testSubstringInt_3_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion
        assertEquals ("hello goodbye", sb.substring(0));
    }

    @Test
    public void testSubstringInt_4_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals ("hello goodbye".substring(0), sb.substring(0));
    }

    @Test
    public void testSubstringInt_5_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> sb.substring(-1));
    }

    @Test
    public void testSubstringInt_6_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows(IndexOutOfBoundsException.class, () -> sb.substring(15));
    }

    @Test
    public void testSubstringIntInt_1_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        assertEquals ("hello", sb.substring(0, 5));
    }

    @Test
    public void testSubstringIntInt_2_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        assertEquals ("hello goodbye".substring(0, 6), sb.substring(0, 6));
    }

    @Test
    public void testSubstringIntInt_3_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion

        assertEquals ("goodbye", sb.substring(6, 13));
    }

    @Test
    public void testSubstringIntInt_4_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals ("hello goodbye".substring(6, 13), sb.substring(6, 13));
    }

    @Test
    public void testSubstringIntInt_5_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals ("goodbye", sb.substring(6, 20));
    }

    @Test
    public void testSubstringIntInt_6_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertThrows(IndexOutOfBoundsException.class, () -> sb.substring(-1, 5));
    }

    @Test
    public void testSubstringIntInt_7_oe() {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> sb.substring(15, 20));
    }

    @Test
    public void testMidString_1_oe() {
        final StrBuilder sb = new StrBuilder("hello goodbye hello");
        assertEquals("goodbye", sb.midString(6, 7));
    }

    @Test
    public void testMidString_2_oe() {
        final StrBuilder sb = new StrBuilder("hello goodbye hello");
        // removed other assertion
        assertEquals("hello", sb.midString(0, 5));
    }

    @Test
    public void testMidString_3_oe() {
        final StrBuilder sb = new StrBuilder("hello goodbye hello");
        // removed other assertion
        // removed other assertion
        assertEquals("hello", sb.midString(-5, 5));
    }

    @Test
    public void testMidString_4_oe() {
        final StrBuilder sb = new StrBuilder("hello goodbye hello");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", sb.midString(0, -1));
    }

    @Test
    public void testMidString_5_oe() {
        final StrBuilder sb = new StrBuilder("hello goodbye hello");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", sb.midString(20, 2));
    }

    @Test
    public void testMidString_6_oe() {
        final StrBuilder sb = new StrBuilder("hello goodbye hello");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hello", sb.midString(14, 22));
    }

    @Test
    public void testRightString_1_oe() {
        final StrBuilder sb = new StrBuilder("left right");
        assertEquals("right", sb.rightString(5));
    }

    @Test
    public void testRightString_2_oe() {
        final StrBuilder sb = new StrBuilder("left right");
        // removed other assertion
        assertEquals("", sb.rightString(0));
    }

    @Test
    public void testRightString_3_oe() {
        final StrBuilder sb = new StrBuilder("left right");
        // removed other assertion
        // removed other assertion
        assertEquals("", sb.rightString(-5));
    }

    @Test
    public void testRightString_4_oe() {
        final StrBuilder sb = new StrBuilder("left right");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("left right", sb.rightString(15));
    }

    @Test
    public void testLeftString_1_oe() {
        final StrBuilder sb = new StrBuilder("left right");
        assertEquals("left", sb.leftString(4));
    }

    @Test
    public void testLeftString_2_oe() {
        final StrBuilder sb = new StrBuilder("left right");
        // removed other assertion
        assertEquals("", sb.leftString(0));
    }

    @Test
    public void testLeftString_3_oe() {
        final StrBuilder sb = new StrBuilder("left right");
        // removed other assertion
        // removed other assertion
        assertEquals("", sb.leftString(-5));
    }

    @Test
    public void testLeftString_4_oe() {
        final StrBuilder sb = new StrBuilder("left right");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("left right", sb.leftString(15));
    }

    @Test
    public void testContains_char_1_oe() {
        final StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        assertTrue(sb.contains('a'));
    }

    @Test
    public void testContains_char_2_oe() {
        final StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        assertTrue(sb.contains('o'));
    }

    @Test
    public void testContains_char_3_oe() {
        final StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        // removed other assertion
        assertTrue(sb.contains('z'));
    }

    @Test
    public void testContains_char_4_oe() {
        final StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.contains('1'));
    }

    @Test
    public void testContains_String_1_oe() {
        final StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        assertTrue(sb.contains("a"));
    }

    @Test
    public void testContains_String_2_oe() {
        final StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        assertTrue(sb.contains("pq"));
    }

    @Test
    public void testContains_String_3_oe() {
        final StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        // removed other assertion
        assertTrue(sb.contains("z"));
    }

    @Test
    public void testContains_String_4_oe() {
        final StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.contains("zyx"));
    }

    @Test
    public void testContains_String_5_oe() {
        final StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.contains((String) null));
    }

    @Test
    public void testContains_StrMatcher_1_oe() {
        StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        assertTrue(sb.contains(StrMatcher.charMatcher('a')));
    }

    @Test
    public void testContains_StrMatcher_2_oe() {
        StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        assertTrue(sb.contains(StrMatcher.stringMatcher("pq")));
    }

    @Test
    public void testContains_StrMatcher_3_oe() {
        StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        // removed other assertion
        assertTrue(sb.contains(StrMatcher.charMatcher('z')));
    }

    @Test
    public void testContains_StrMatcher_4_oe() {
        StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.contains(StrMatcher.stringMatcher("zy")));
    }

    @Test
    public void testContains_StrMatcher_5_oe() {
        StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(sb.contains((StrMatcher) null));
    }

    @Test
    public void testContains_StrMatcher_6_oe() {
        StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb = new StrBuilder();
        assertFalse(sb.contains(A_NUMBER_MATCHER));
    }

    @Test
    public void testContains_StrMatcher_7_oe() {
        StrBuilder sb = new StrBuilder("abcdefghijklmnopqrstuvwxyz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb = new StrBuilder();
        // removed other assertion
        sb.append("B A1 C");
        assertTrue(sb.contains(A_NUMBER_MATCHER));
    }

    @Test
    public void testIndexOf_char_1_oe() {
        final StrBuilder sb = new StrBuilder("abab");
        assertEquals(0, sb.indexOf('a'));
    }

    @Test
    public void testIndexOf_char_2_oe() {
        final StrBuilder sb = new StrBuilder("abab");
        // removed other assertion

        // should work like String#indexOf
        assertEquals("abab".indexOf('a'), sb.indexOf('a'));
    }

    @Test
    public void testIndexOf_char_3_oe() {
        final StrBuilder sb = new StrBuilder("abab");
        // removed other assertion

        // should work like String#indexOf
        // removed other assertion

        assertEquals(1, sb.indexOf('b'));
    }

    @Test
    public void testIndexOf_char_4_oe() {
        final StrBuilder sb = new StrBuilder("abab");
        // removed other assertion

        // should work like String#indexOf
        // removed other assertion

        // removed other assertion
        assertEquals("abab".indexOf('b'), sb.indexOf('b'));
    }

    @Test
    public void testIndexOf_char_5_oe() {
        final StrBuilder sb = new StrBuilder("abab");
        // removed other assertion

        // should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.indexOf('z'));
    }

    @Test
    public void testIndexOf_char_int_1_oe() {
        StrBuilder sb = new StrBuilder("abab");
        assertEquals(0, sb.indexOf('a', -1));
    }

    @Test
    public void testIndexOf_char_int_2_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        assertEquals(0, sb.indexOf('a', 0));
    }

    @Test
    public void testIndexOf_char_int_3_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.indexOf('a', 1));
    }

    @Test
    public void testIndexOf_char_int_4_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf('a', 4));
    }

    @Test
    public void testIndexOf_char_int_5_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf('a', 5));
    }

    @Test
    public void testIndexOf_char_int_6_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#indexOf
        assertEquals("abab".indexOf('a', 1), sb.indexOf('a', 1));
    }

    @Test
    public void testIndexOf_char_int_7_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#indexOf
        // removed other assertion

        assertEquals(3, sb.indexOf('b', 2));
    }

    @Test
    public void testIndexOf_char_int_8_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#indexOf
        // removed other assertion

        // removed other assertion
        assertEquals("abab".indexOf('b', 2), sb.indexOf('b', 2));
    }

    @Test
    public void testIndexOf_char_int_9_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.indexOf('z', 2));
    }

    @Test
    public void testIndexOf_char_int_10_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb = new StrBuilder("xyzabc");
        assertEquals(2, sb.indexOf('z', 0));
    }

    @Test
    public void testIndexOf_char_int_11_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb = new StrBuilder("xyzabc");
        // removed other assertion
        assertEquals(-1, sb.indexOf('z', 3));
    }

    @Test
    public void testLastIndexOf_char_1_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        assertEquals (2, sb.lastIndexOf('a'));
    }

    @Test
    public void testLastIndexOf_char_2_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        assertEquals ("abab".lastIndexOf('a'), sb.lastIndexOf('a'));
    }

    @Test
    public void testLastIndexOf_char_3_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        assertEquals(3, sb.lastIndexOf('b'));
    }

    @Test
    public void testLastIndexOf_char_4_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        assertEquals ("abab".lastIndexOf('b'), sb.lastIndexOf('b'));
    }

    @Test
    public void testLastIndexOf_char_5_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals (-1, sb.lastIndexOf('z'));
    }

    @Test
    public void testLastIndexOf_char_int_1_oe() {
        StrBuilder sb = new StrBuilder("abab");
        assertEquals(-1, sb.lastIndexOf('a', -1));
    }

    @Test
    public void testLastIndexOf_char_int_2_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        assertEquals(0, sb.lastIndexOf('a', 0));
    }

    @Test
    public void testLastIndexOf_char_int_3_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb.lastIndexOf('a', 1));
    }

    @Test
    public void testLastIndexOf_char_int_4_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#lastIndexOf
        assertEquals("abab".lastIndexOf('a', 1), sb.lastIndexOf('a', 1));
    }

    @Test
    public void testLastIndexOf_char_int_5_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#lastIndexOf
        // removed other assertion

        assertEquals(1, sb.lastIndexOf('b', 2));
    }

    @Test
    public void testLastIndexOf_char_int_6_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        assertEquals("abab".lastIndexOf('b', 2), sb.lastIndexOf('b', 2));
    }

    @Test
    public void testLastIndexOf_char_int_7_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.lastIndexOf('z', 2));
    }

    @Test
    public void testLastIndexOf_char_int_8_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb = new StrBuilder("xyzabc");
        assertEquals(2, sb.lastIndexOf('z', sb.length()));
    }

    @Test
    public void testLastIndexOf_char_int_9_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb = new StrBuilder("xyzabc");
        // removed other assertion
        assertEquals(-1, sb.lastIndexOf('z', 1));
    }

    @Test
    public void testIndexOf_String_1_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        assertEquals(0, sb.indexOf("a"));
    }

    @Test
    public void testIndexOf_String_2_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#indexOf
        assertEquals("abab".indexOf("a"), sb.indexOf("a"));
    }

    @Test
    public void testIndexOf_String_3_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        assertEquals(0, sb.indexOf("ab"));
    }

    @Test
    public void testIndexOf_String_4_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        assertEquals("abab".indexOf("ab"), sb.indexOf("ab"));
    }

    @Test
    public void testIndexOf_String_5_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        assertEquals(1, sb.indexOf("b"));
    }

    @Test
    public void testIndexOf_String_6_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        assertEquals("abab".indexOf("b"), sb.indexOf("b"));
    }

    @Test
    public void testIndexOf_String_7_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(1, sb.indexOf("ba"));
    }

    @Test
    public void testIndexOf_String_8_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("abab".indexOf("ba"), sb.indexOf("ba"));
    }

    @Test
    public void testIndexOf_String_9_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.indexOf("z"));
    }

    @Test
    public void testIndexOf_String_10_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertEquals(-1, sb.indexOf((String) null));
    }

    @Test
    public void testIndexOf_String_int_1_oe() {
        StrBuilder sb = new StrBuilder("abab");
        assertEquals(0, sb.indexOf("a", -1));
    }

    @Test
    public void testIndexOf_String_int_2_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        assertEquals(0, sb.indexOf("a", 0));
    }

    @Test
    public void testIndexOf_String_int_3_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.indexOf("a", 1));
    }

    @Test
    public void testIndexOf_String_int_4_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.indexOf("a", 2));
    }

    @Test
    public void testIndexOf_String_int_5_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf("a", 3));
    }

    @Test
    public void testIndexOf_String_int_6_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf("a", 4));
    }

    @Test
    public void testIndexOf_String_int_7_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf("a", 5));
    }

    @Test
    public void testIndexOf_String_int_8_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.indexOf("abcdef", 0));
    }

    @Test
    public void testIndexOf_String_int_9_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, sb.indexOf("", 0));
    }

    @Test
    public void testIndexOf_String_int_10_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(1, sb.indexOf("", 1));
    }

    @Test
    public void testIndexOf_String_int_11_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        assertEquals ("abab".indexOf("a", 1), sb.indexOf("a", 1));
    }

    @Test
    public void testIndexOf_String_int_12_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        // removed other assertion

        assertEquals(2, sb.indexOf("ab", 1));
    }

    @Test
    public void testIndexOf_String_int_13_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        assertEquals("abab".indexOf("ab", 1), sb.indexOf("ab", 1));
    }

    @Test
    public void testIndexOf_String_int_14_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        assertEquals(3, sb.indexOf("b", 2));
    }

    @Test
    public void testIndexOf_String_int_15_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        assertEquals("abab".indexOf("b", 2), sb.indexOf("b", 2));
    }

    @Test
    public void testIndexOf_String_int_16_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(1, sb.indexOf("ba", 1));
    }

    @Test
    public void testIndexOf_String_int_17_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("abab".indexOf("ba", 2), sb.indexOf("ba", 2));
    }

    @Test
    public void testIndexOf_String_int_18_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.indexOf("z", 2));
    }

    @Test
    public void testIndexOf_String_int_19_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb = new StrBuilder("xyzabc");
        assertEquals(2, sb.indexOf("za", 0));
    }

    @Test
    public void testIndexOf_String_int_20_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb = new StrBuilder("xyzabc");
        // removed other assertion
        assertEquals(-1, sb.indexOf("za", 3));
    }

    @Test
    public void testIndexOf_String_int_21_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        //should work like String#indexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb = new StrBuilder("xyzabc");
        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.indexOf((String) null, 2));
    }

    @Test
    public void testLastIndexOf_String_1_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        assertEquals(2, sb.lastIndexOf("a"));
    }

    @Test
    public void testLastIndexOf_String_2_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        assertEquals("abab".lastIndexOf("a"), sb.lastIndexOf("a"));
    }

    @Test
    public void testLastIndexOf_String_3_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        assertEquals(2, sb.lastIndexOf("ab"));
    }

    @Test
    public void testLastIndexOf_String_4_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        assertEquals("abab".lastIndexOf("ab"), sb.lastIndexOf("ab"));
    }

    @Test
    public void testLastIndexOf_String_5_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        assertEquals(3, sb.lastIndexOf("b"));
    }

    @Test
    public void testLastIndexOf_String_6_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        assertEquals("abab".lastIndexOf("b"), sb.lastIndexOf("b"));
    }

    @Test
    public void testLastIndexOf_String_7_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(1, sb.lastIndexOf("ba"));
    }

    @Test
    public void testLastIndexOf_String_8_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("abab".lastIndexOf("ba"), sb.lastIndexOf("ba"));
    }

    @Test
    public void testLastIndexOf_String_9_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.lastIndexOf("z"));
    }

    @Test
    public void testLastIndexOf_String_10_oe() {
        final StrBuilder sb = new StrBuilder("abab");

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertEquals(-1, sb.lastIndexOf((String) null));
    }

    @Test
    public void testLastIndexOf_String_int_1_oe() {
        StrBuilder sb = new StrBuilder("abab");
        assertEquals(-1, sb.lastIndexOf("a", -1));
    }

    @Test
    public void testLastIndexOf_String_int_2_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        assertEquals(0, sb.lastIndexOf("a", 0));
    }

    @Test
    public void testLastIndexOf_String_int_3_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb.lastIndexOf("a", 1));
    }

    @Test
    public void testLastIndexOf_String_int_4_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.lastIndexOf("a", 2));
    }

    @Test
    public void testLastIndexOf_String_int_5_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.lastIndexOf("a", 3));
    }

    @Test
    public void testLastIndexOf_String_int_6_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.lastIndexOf("a", 4));
    }

    @Test
    public void testLastIndexOf_String_int_7_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.lastIndexOf("a", 5));
    }

    @Test
    public void testLastIndexOf_String_int_8_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.lastIndexOf("abcdef", 3));
    }

    @Test
    public void testLastIndexOf_String_int_9_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("abab".lastIndexOf("", 3), sb.lastIndexOf("", 3));
    }

    @Test
    public void testLastIndexOf_String_int_10_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("abab".lastIndexOf("", 1), sb.lastIndexOf("", 1));
    }

    @Test
    public void testLastIndexOf_String_int_11_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        assertEquals("abab".lastIndexOf("a", 1), sb.lastIndexOf("a", 1));
    }

    @Test
    public void testLastIndexOf_String_int_12_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        // removed other assertion

        assertEquals(0, sb.lastIndexOf("ab", 1));
    }

    @Test
    public void testLastIndexOf_String_int_13_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        assertEquals("abab".lastIndexOf("ab", 1), sb.lastIndexOf("ab", 1));
    }

    @Test
    public void testLastIndexOf_String_int_14_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        assertEquals(1, sb.lastIndexOf("b", 2));
    }

    @Test
    public void testLastIndexOf_String_int_15_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        assertEquals("abab".lastIndexOf("b", 2), sb.lastIndexOf("b", 2));
    }

    @Test
    public void testLastIndexOf_String_int_16_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(1, sb.lastIndexOf("ba", 2));
    }

    @Test
    public void testLastIndexOf_String_int_17_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("abab".lastIndexOf("ba", 2), sb.lastIndexOf("ba", 2));
    }

    @Test
    public void testLastIndexOf_String_int_18_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.lastIndexOf("z", 2));
    }

    @Test
    public void testLastIndexOf_String_int_19_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb = new StrBuilder("xyzabc");
        assertEquals(2, sb.lastIndexOf("za", sb.length()));
    }

    @Test
    public void testLastIndexOf_String_int_20_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb = new StrBuilder("xyzabc");
        // removed other assertion
        assertEquals(-1, sb.lastIndexOf("za", 1));
    }

    @Test
    public void testLastIndexOf_String_int_21_oe() {
        StrBuilder sb = new StrBuilder("abab");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        //should work like String#lastIndexOf
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        sb = new StrBuilder("xyzabc");
        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.lastIndexOf((String) null, 2));
    }

    @Test
    public void testIndexOf_StrMatcher_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(-1, sb.indexOf((StrMatcher) null));
    }

    @Test
    public void testIndexOf_StrMatcher_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('a')));
    }

    @Test
    public void testIndexOf_StrMatcher_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        assertEquals(0, sb.indexOf(StrMatcher.charMatcher('a')));
    }

    @Test
    public void testIndexOf_StrMatcher_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        assertEquals(1, sb.indexOf(StrMatcher.charMatcher('b')));
    }

    @Test
    public void testIndexOf_StrMatcher_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.indexOf(StrMatcher.spaceMatcher()));
    }

    @Test
    public void testIndexOf_StrMatcher_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, sb.indexOf(StrMatcher.charMatcher('d')));
    }

    @Test
    public void testIndexOf_StrMatcher_7_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.noneMatcher()));
    }

    @Test
    public void testIndexOf_StrMatcher_8_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf((StrMatcher) null));
    }

    @Test
    public void testIndexOf_StrMatcher_9_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction");
        assertEquals(6, sb.indexOf(A_NUMBER_MATCHER));
    }

    @Test
    public void testIndexOf_StrMatcher_int_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(-1, sb.indexOf((StrMatcher) null, 2));
    }

    @Test
    public void testIndexOf_StrMatcher_int_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('a'), 2));
    }

    @Test
    public void testIndexOf_StrMatcher_int_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('a'), 0));
    }

    @Test
    public void testIndexOf_StrMatcher_int_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        assertEquals(0, sb.indexOf(StrMatcher.charMatcher('a'), -2));
    }

    @Test
    public void testIndexOf_StrMatcher_int_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        assertEquals(0, sb.indexOf(StrMatcher.charMatcher('a'), 0));
    }

    @Test
    public void testIndexOf_StrMatcher_int_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('a'), 2));
    }

    @Test
    public void testIndexOf_StrMatcher_int_7_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('a'), 20));
    }

    @Test
    public void testIndexOf_StrMatcher_int_8_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(1, sb.indexOf(StrMatcher.charMatcher('b'), -1));
    }

    @Test
    public void testIndexOf_StrMatcher_int_9_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(1, sb.indexOf(StrMatcher.charMatcher('b'), 0));
    }

    @Test
    public void testIndexOf_StrMatcher_int_10_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(1, sb.indexOf(StrMatcher.charMatcher('b'), 1));
    }

    @Test
    public void testIndexOf_StrMatcher_int_11_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, sb.indexOf(StrMatcher.charMatcher('b'), 2));
    }

    @Test
    public void testIndexOf_StrMatcher_int_12_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, sb.indexOf(StrMatcher.charMatcher('b'), 3));
    }

    @Test
    public void testIndexOf_StrMatcher_int_13_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('b'), 4));
    }

    @Test
    public void testIndexOf_StrMatcher_int_14_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('b'), 5));
    }

    @Test
    public void testIndexOf_StrMatcher_int_15_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.charMatcher('b'), 6));
    }

    @Test
    public void testIndexOf_StrMatcher_int_16_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(2, sb.indexOf(StrMatcher.spaceMatcher(), -2));
    }

    @Test
    public void testIndexOf_StrMatcher_int_17_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(2, sb.indexOf(StrMatcher.spaceMatcher(), 0));
    }

    @Test
    public void testIndexOf_StrMatcher_int_18_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.indexOf(StrMatcher.spaceMatcher(), 2));
    }

    @Test
    public void testIndexOf_StrMatcher_int_19_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.spaceMatcher(), 4));
    }

    @Test
    public void testIndexOf_StrMatcher_int_20_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf(StrMatcher.spaceMatcher(), 20));
    }

    @Test
    public void testIndexOf_StrMatcher_int_21_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.indexOf(StrMatcher.noneMatcher(), 0));
    }

    @Test
    public void testIndexOf_StrMatcher_int_22_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, sb.indexOf((StrMatcher) null, 0));
    }

    @Test
    public void testIndexOf_StrMatcher_int_23_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction with A2");
        assertEquals(6, sb.indexOf(A_NUMBER_MATCHER, 5));
    }

    @Test
    public void testIndexOf_StrMatcher_int_24_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction with A2");
        // removed other assertion
        assertEquals(6, sb.indexOf(A_NUMBER_MATCHER, 6));
    }

    @Test
    public void testIndexOf_StrMatcher_int_25_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction with A2");
        // removed other assertion
        // removed other assertion
        assertEquals(23, sb.indexOf(A_NUMBER_MATCHER, 7));
    }

    @Test
    public void testIndexOf_StrMatcher_int_26_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction with A2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(23, sb.indexOf(A_NUMBER_MATCHER, 22));
    }

    @Test
    public void testIndexOf_StrMatcher_int_27_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction with A2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(23, sb.indexOf(A_NUMBER_MATCHER, 23));
    }

    @Test
    public void testIndexOf_StrMatcher_int_28_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction with A2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.indexOf(A_NUMBER_MATCHER, 24));
    }

    @Test
    public void testLastIndexOf_StrMatcher_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(-1, sb.lastIndexOf((StrMatcher) null));
    }

    @Test
    public void testLastIndexOf_StrMatcher_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        assertEquals(-1, sb.lastIndexOf(StrMatcher.charMatcher('a')));
    }

    @Test
    public void testLastIndexOf_StrMatcher_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        assertEquals(0, sb.lastIndexOf(StrMatcher.charMatcher('a')));
    }

    @Test
    public void testLastIndexOf_StrMatcher_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        assertEquals(3, sb.lastIndexOf(StrMatcher.charMatcher('b')));
    }

    @Test
    public void testLastIndexOf_StrMatcher_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.lastIndexOf(StrMatcher.spaceMatcher()));
    }

    @Test
    public void testLastIndexOf_StrMatcher_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, sb.lastIndexOf(StrMatcher.charMatcher('d')));
    }

    @Test
    public void testLastIndexOf_StrMatcher_7_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.lastIndexOf(StrMatcher.noneMatcher()));
    }

    @Test
    public void testLastIndexOf_StrMatcher_8_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.lastIndexOf((StrMatcher) null));
    }

    @Test
    public void testLastIndexOf_StrMatcher_9_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction");
        assertEquals(6, sb.lastIndexOf(A_NUMBER_MATCHER));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(-1, sb.lastIndexOf((StrMatcher) null, 2));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        assertEquals(-1, sb.lastIndexOf(StrMatcher.charMatcher('a'), 2));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_3_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.lastIndexOf(StrMatcher.charMatcher('a'), 0));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_4_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, sb.lastIndexOf(StrMatcher.charMatcher('a'), -1));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_5_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        assertEquals(-1, sb.lastIndexOf(StrMatcher.charMatcher('a'), -2));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_6_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        assertEquals(0, sb.lastIndexOf(StrMatcher.charMatcher('a'), 0));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_7_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb.lastIndexOf(StrMatcher.charMatcher('a'), 2));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_8_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, sb.lastIndexOf(StrMatcher.charMatcher('a'), 20));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_9_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.lastIndexOf(StrMatcher.charMatcher('b'), -1));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_10_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, sb.lastIndexOf(StrMatcher.charMatcher('b'), 0));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_11_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(1, sb.lastIndexOf(StrMatcher.charMatcher('b'), 1));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_12_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, sb.lastIndexOf(StrMatcher.charMatcher('b'), 2));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_13_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, sb.lastIndexOf(StrMatcher.charMatcher('b'), 3));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_14_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, sb.lastIndexOf(StrMatcher.charMatcher('b'), 4));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_15_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, sb.lastIndexOf(StrMatcher.charMatcher('b'), 5));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_16_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, sb.lastIndexOf(StrMatcher.charMatcher('b'), 6));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_17_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.lastIndexOf(StrMatcher.spaceMatcher(), -2));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_18_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, sb.lastIndexOf(StrMatcher.spaceMatcher(), 0));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_19_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.lastIndexOf(StrMatcher.spaceMatcher(), 2));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_20_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.lastIndexOf(StrMatcher.spaceMatcher(), 4));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_21_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, sb.lastIndexOf(StrMatcher.spaceMatcher(), 20));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_22_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(-1, sb.lastIndexOf(StrMatcher.noneMatcher(), 0));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_23_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, sb.lastIndexOf((StrMatcher) null, 0));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_24_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction with A2");
        assertEquals(-1, sb.lastIndexOf(A_NUMBER_MATCHER, 5));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_25_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction with A2");
        // removed other assertion
        assertEquals(-1,sb.lastIndexOf(A_NUMBER_MATCHER,6));// A matches,1 is outside bounds assertEquals(6,sb.lastIndexOf(A_NUMBER_MATCHER,7));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_26_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction with A2");
        // removed other assertion
        // removed other assertion
        assertEquals(6, sb.lastIndexOf(A_NUMBER_MATCHER, 22));
    }

    @Test
    public void testLastIndexOf_StrMatcher_int_27_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb.append("ab bd");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        sb.append(" A1 junction with A2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,sb.lastIndexOf(A_NUMBER_MATCHER,23));// A matches,2 is outside bounds assertEquals(23,sb.lastIndexOf(A_NUMBER_MATCHER,24));
    }

    @Test
    public void testAsTokenizer_1_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        assertEquals(2, tokens1.length);
    }

    @Test
    public void testAsTokenizer_2_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        assertEquals("a", tokens1[0]);
    }

    @Test
    public void testAsTokenizer_3_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        assertEquals("b", tokens1[1]);
    }

    @Test
    public void testAsTokenizer_4_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, t.size());
    }

    @Test
    public void testAsTokenizer_5_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        assertEquals(2, tokens2.length);
    }

    @Test
    public void testAsTokenizer_6_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        assertEquals("a", tokens2[0]);
    }

    @Test
    public void testAsTokenizer_7_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        assertEquals("b", tokens2[1]);
    }

    @Test
    public void testAsTokenizer_8_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, t.size());
    }

    @Test
    public void testAsTokenizer_9_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a", t.next());
    }

    @Test
    public void testAsTokenizer_11_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        t.reset();
        final String[] tokens3 = t.getTokenArray();
        assertEquals(4, tokens3.length);
    }

    @Test
    public void testAsTokenizer_12_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        t.reset();
        final String[] tokens3 = t.getTokenArray();
        // removed other assertion
        assertEquals("a", tokens3[0]);
    }

    @Test
    public void testAsTokenizer_13_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        t.reset();
        final String[] tokens3 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        assertEquals("b", tokens3[1]);
    }

    @Test
    public void testAsTokenizer_14_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        t.reset();
        final String[] tokens3 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", tokens3[2]);
    }

    @Test
    public void testAsTokenizer_15_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        t.reset();
        final String[] tokens3 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("d", tokens3[3]);
    }

    @Test
    public void testAsTokenizer_16_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        t.reset();
        final String[] tokens3 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, t.size());
    }

    @Test
    public void testAsTokenizer_17_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        t.reset();
        final String[] tokens3 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a", t.next());
    }

    @Test
    public void testAsTokenizer_21_oe() {
        // from Javadoc
        final StrBuilder b = new StrBuilder();
        b.append("a b ");
        final StrTokenizer t = b.asTokenizer();

        final String[] tokens1 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        b.append("c d ");
        final String[] tokens2 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        t.reset();
        final String[] tokens3 = t.getTokenArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("a b c d ", t.getContent());
    }

    @Test
    public void testAsReader_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        assertTrue(reader.ready());
    }

    @Test
    public void testAsReader_2_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        assertEquals(9, reader.read(buf));
    }

    @Test
    public void testAsReader_7_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, reader.skip(-1));
    }

    @Test
    public void testAsReader_8_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(reader.markSupported());
    }

    @Test
    public void testAsReader_9_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        assertEquals('s', reader.read());
    }

    @Test
    public void testAsReader_10_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        assertEquals(3, reader.read(array, 0, 3));
    }

    @Test
    public void testAsReader_14_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        assertEquals(1, reader.read(array, 1, 1));
    }

    @Test
    public void testAsReader_18_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, reader.skip(2));
    }

    @Test
    public void testAsReader_20_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(reader.ready());
    }

    @Test
    public void testAsReader_21_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        assertTrue(reader.ready());
    }

    @Test
    public void testAsReader_22_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        assertThrows(IndexOutOfBoundsException.class, () -> r.read(arr, -1, 0));
    }

    @Test
    public void testAsReader_23_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> r.read(arr, 0, -1));
    }

    @Test
    public void testAsReader_24_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> r.read(arr, 100, 1));
    }

    @Test
    public void testAsReader_25_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> r.read(arr, 0, 100));
    }

    @Test
    public void testAsReader_26_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> r.read(arr, Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testAsReader_27_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, r.read(arr, 0, 0));
    }

    @Test
    public void testAsReader_28_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, arr[0]);
    }

    @Test
    public void testAsReader_29_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0, arr[1]);
    }

    @Test
    public void testAsReader_30_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, arr[2]);
    }

    @Test
    public void testAsReader_31_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        r.skip(9);
        assertEquals(-1, r.read(arr, 0, 1));
    }

    @Test
    public void testAsReader_32_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("some text");
        Reader reader = sb.asReader();
        // removed other assertion
        final char[] buf = new char[40];
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader = sb.asReader();
        // removed other assertion
        reader.mark(-1);
        char[] array = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        reader.close();
        // removed other assertion

        final Reader r = sb.asReader();
        final char[] arr = new char[3];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        r.skip(9);
        // removed other assertion

        r.reset();
        array = new char[30];
        assertEquals(9, r.read(array, 0, 30));
    }

    @Test
    public void testAsWriter_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("base");
        final Writer writer = sb.asWriter();

        writer.write('l');
        assertEquals("basel", sb.toString());
    }

    @Test
    public void testAsWriter_2_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("base");
        final Writer writer = sb.asWriter();

        writer.write('l');
        // removed other assertion

        writer.write(new char[] {'i', 'n'});
        assertEquals("baselin", sb.toString());
    }

    @Test
    public void testAsWriter_3_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("base");
        final Writer writer = sb.asWriter();

        writer.write('l');
        // removed other assertion

        writer.write(new char[] {'i', 'n'});
        // removed other assertion

        writer.write(new char[] {'n', 'e', 'r'}, 1, 2);
        assertEquals("baseliner", sb.toString());
    }

    @Test
    public void testAsWriter_4_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("base");
        final Writer writer = sb.asWriter();

        writer.write('l');
        // removed other assertion

        writer.write(new char[] {'i', 'n'});
        // removed other assertion

        writer.write(new char[] {'n', 'e', 'r'}, 1, 2);
        // removed other assertion

        writer.write(" rout");
        assertEquals("baseliner rout", sb.toString());
    }

    @Test
    public void testAsWriter_5_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("base");
        final Writer writer = sb.asWriter();

        writer.write('l');
        // removed other assertion

        writer.write(new char[] {'i', 'n'});
        // removed other assertion

        writer.write(new char[] {'n', 'e', 'r'}, 1, 2);
        // removed other assertion

        writer.write(" rout");
        // removed other assertion

        writer.write("ping that server", 1, 3);
        assertEquals("baseliner routing", sb.toString());
    }

    @Test
    public void testAsWriter_6_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("base");
        final Writer writer = sb.asWriter();

        writer.write('l');
        // removed other assertion

        writer.write(new char[] {'i', 'n'});
        // removed other assertion

        writer.write(new char[] {'n', 'e', 'r'}, 1, 2);
        // removed other assertion

        writer.write(" rout");
        // removed other assertion

        writer.write("ping that server", 1, 3);
        // removed other assertion

        writer.flush();  // no effect
        assertEquals("baseliner routing", sb.toString());
    }

    @Test
    public void testAsWriter_7_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("base");
        final Writer writer = sb.asWriter();

        writer.write('l');
        // removed other assertion

        writer.write(new char[] {'i', 'n'});
        // removed other assertion

        writer.write(new char[] {'n', 'e', 'r'}, 1, 2);
        // removed other assertion

        writer.write(" rout");
        // removed other assertion

        writer.write("ping that server", 1, 3);
        // removed other assertion

        writer.flush();  // no effect
        // removed other assertion

        writer.close();  // no effect
        assertEquals("baseliner routing", sb.toString());
    }

    @Test
    public void testAsWriter_8_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("base");
        final Writer writer = sb.asWriter();

        writer.write('l');
        // removed other assertion

        writer.write(new char[] {'i', 'n'});
        // removed other assertion

        writer.write(new char[] {'n', 'e', 'r'}, 1, 2);
        // removed other assertion

        writer.write(" rout");
        // removed other assertion

        writer.write("ping that server", 1, 3);
        // removed other assertion

        writer.flush();  // no effect
        // removed other assertion

        writer.close();  // no effect
        // removed other assertion

        writer.write(" hi");  // works after close
        assertEquals("baseliner routing hi", sb.toString());
    }

    @Test
    public void testAsWriter_9_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("base");
        final Writer writer = sb.asWriter();

        writer.write('l');
        // removed other assertion

        writer.write(new char[] {'i', 'n'});
        // removed other assertion

        writer.write(new char[] {'n', 'e', 'r'}, 1, 2);
        // removed other assertion

        writer.write(" rout");
        // removed other assertion

        writer.write("ping that server", 1, 3);
        // removed other assertion

        writer.flush();  // no effect
        // removed other assertion

        writer.close();  // no effect
        // removed other assertion

        writer.write(" hi");  // works after close
        // removed other assertion

        sb.setLength(4);  // mix and match
        writer.write('d');
        assertEquals("based", sb.toString());
    }

    @Test
    public void testEqualsIgnoreCase_1_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        assertTrue(sb1.equalsIgnoreCase(sb1));
    }

    @Test
    public void testEqualsIgnoreCase_2_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        assertTrue(sb1.equalsIgnoreCase(sb2));
    }

    @Test
    public void testEqualsIgnoreCase_3_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        assertTrue(sb2.equalsIgnoreCase(sb2));
    }

    @Test
    public void testEqualsIgnoreCase_4_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        assertFalse(sb1.equalsIgnoreCase(sb2));
    }

    @Test
    public void testEqualsIgnoreCase_5_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion

        sb2.append("ABC");
        assertTrue(sb1.equalsIgnoreCase(sb2));
    }

    @Test
    public void testEqualsIgnoreCase_6_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion

        sb2.append("ABC");
        // removed other assertion

        sb2.clear().append("abc");
        assertTrue(sb1.equalsIgnoreCase(sb2));
    }

    @Test
    public void testEqualsIgnoreCase_7_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion

        sb2.append("ABC");
        // removed other assertion

        sb2.clear().append("abc");
        // removed other assertion
        assertTrue(sb1.equalsIgnoreCase(sb1));
    }

    @Test
    public void testEqualsIgnoreCase_8_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion

        sb2.append("ABC");
        // removed other assertion

        sb2.clear().append("abc");
        // removed other assertion
        // removed other assertion
        assertTrue(sb2.equalsIgnoreCase(sb2));
    }

    @Test
    public void testEqualsIgnoreCase_9_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion

        sb2.append("ABC");
        // removed other assertion

        sb2.clear().append("abc");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb2.clear().append("aBc");
        assertTrue(sb1.equalsIgnoreCase(sb2));
    }

    @Test
    public void testEquals_1_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        assertTrue(sb1.equals(sb2));
    }

    @Test
    public void testEquals_2_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        assertTrue(sb1.equals(sb1));
    }

    @Test
    public void testEquals_3_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        assertTrue(sb2.equals(sb2));
    }

    @Test
    public void testEquals_4_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(sb1, (Object) sb2);
    }

    @Test
    public void testEquals_5_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        assertFalse(sb1.equals(sb2));
    }

    @Test
    public void testEquals_6_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion
        assertNotEquals(sb1, (Object) sb2);
    }

    @Test
    public void testEquals_7_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion
        // removed other assertion

        sb2.append("ABC");
        assertFalse(sb1.equals(sb2));
    }

    @Test
    public void testEquals_8_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion
        // removed other assertion

        sb2.append("ABC");
        // removed other assertion
        assertNotEquals(sb1, (Object) sb2);
    }

    @Test
    public void testEquals_9_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion
        // removed other assertion

        sb2.append("ABC");
        // removed other assertion
        // removed other assertion

        sb2.clear().append("abc");
        assertTrue(sb1.equals(sb2));
    }

    @Test
    public void testEquals_10_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion
        // removed other assertion

        sb2.append("ABC");
        // removed other assertion
        // removed other assertion

        sb2.clear().append("abc");
        // removed other assertion
        assertEquals(sb1, (Object) sb2);
    }

    @Test
    public void testEquals_11_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion
        // removed other assertion

        sb2.append("ABC");
        // removed other assertion
        // removed other assertion

        sb2.clear().append("abc");
        // removed other assertion
        // removed other assertion

        assertNotEquals(sb1, Integer.valueOf(1));
    }

    @Test
    public void testEquals_12_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        sb1.append("abc");
        // removed other assertion
        // removed other assertion

        sb2.append("ABC");
        // removed other assertion
        // removed other assertion

        sb2.clear().append("abc");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertNotEquals("abc", sb1);
    }

    @Test
    public void test_LANG_1131_EqualsWithNullStrBuilder_1_oe() {
        final StrBuilder sb = new StrBuilder();
        final StrBuilder other = null;
        assertFalse(sb.equals(other));
    }

    @Test
    public void testHashCode_1_oe() {
        final StrBuilder sb = new StrBuilder();
        final int hc1a = sb.hashCode();
        final int hc1b = sb.hashCode();
        assertEquals(0, hc1a);
    }

    @Test
    public void testHashCode_2_oe() {
        final StrBuilder sb = new StrBuilder();
        final int hc1a = sb.hashCode();
        final int hc1b = sb.hashCode();
        // removed other assertion
        assertEquals(hc1a, hc1b);
    }

    @Test
    public void testHashCode_3_oe() {
        final StrBuilder sb = new StrBuilder();
        final int hc1a = sb.hashCode();
        final int hc1b = sb.hashCode();
        // removed other assertion
        // removed other assertion

        sb.append("abc");
        final int hc2a = sb.hashCode();
        final int hc2b = sb.hashCode();
        assertTrue(hc2a != 0);
    }

    @Test
    public void testHashCode_4_oe() {
        final StrBuilder sb = new StrBuilder();
        final int hc1a = sb.hashCode();
        final int hc1b = sb.hashCode();
        // removed other assertion
        // removed other assertion

        sb.append("abc");
        final int hc2a = sb.hashCode();
        final int hc2b = sb.hashCode();
        // removed other assertion
        assertEquals(hc2a, hc2b);
    }

    @Test
    public void testToString_1_oe() {
        final StrBuilder sb = new StrBuilder("abc");
        assertEquals("abc", sb.toString());
    }

    @Test
    public void testToStringBuffer_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(new StringBuffer().toString(), sb.toStringBuffer().toString());
    }

    @Test
    public void testToStringBuffer_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        assertEquals(new StringBuffer("junit").toString(), sb.toStringBuffer().toString());
    }

    @Test
    public void testToStringBuilder_1_oe() {
        final StrBuilder sb = new StrBuilder();
        assertEquals(new StringBuilder().toString(), sb.toStringBuilder().toString());
    }

    @Test
    public void testToStringBuilder_2_oe() {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion

        sb.append("junit");
        assertEquals(new StringBuilder("junit").toString(), sb.toStringBuilder().toString());
    }

    @Test
    public void testLang294_1_oe() {
        final StrBuilder sb = new StrBuilder("\n%BLAH%\nDo more stuff\neven more stuff\n%BLAH%\n");
        sb.deleteAll("\n%BLAH%");
        assertEquals("\nDo more stuff\neven more stuff\n", sb.toString());
    }

    @Test
    public void testIndexOfLang294_1_oe() {
        final StrBuilder sb = new StrBuilder("onetwothree");
        sb.deleteFirst("three");
        assertEquals(-1, sb.indexOf("three"));
    }

    @Test
    public void testLang295_1_oe() {
        final StrBuilder sb = new StrBuilder("onetwothree");
        sb.deleteFirst("three");
        assertFalse(sb.contains('h'), "The contains(char) method is looking beyond the end of the string");
    }

    @Test
    public void testLang295_2_oe() {
        final StrBuilder sb = new StrBuilder("onetwothree");
        sb.deleteFirst("three");
        // removed other assertion
        assertEquals(-1, sb.indexOf('h'), "The indexOf(char) method is looking beyond the end of the string");
    }

    @Test
    public void testLang412Right_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight(null, 10, '*');
        assertEquals("**********", sb.toString(), "Failed to invoke appendFixedWidthPadRight correctly");
    }

    @Test
    public void testLang412Left_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft(null, 10, '*');
        assertEquals("**********", sb.toString(), "Failed to invoke appendFixedWidthPadLeft correctly");
    }

    @Test
    public void testAsBuilder_1_oe() {
        final StrBuilder sb = new StrBuilder().appendAll("Lorem", " ", "ipsum", " ", "dolor");
        assertEquals(sb.toString(), sb.build());
    }

    @Test
    public void testAppendCharBuffer_1_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final CharBuffer buf = CharBuffer.allocate(10);
        buf.append("0123456789");
        buf.flip();
        sb1.append(buf);
        assertEquals("0123456789", sb1.toString());
    }

    @Test
    public void testAppendCharBuffer_2_oe() {
        final StrBuilder sb1 = new StrBuilder();
        final CharBuffer buf = CharBuffer.allocate(10);
        buf.append("0123456789");
        buf.flip();
        sb1.append(buf);
        // removed other assertion

        final StrBuilder sb2 = new StrBuilder();
        sb2.append(buf, 1, 8);
        assertEquals("12345678", sb2.toString());
    }

    @Test
    public void testAppendToWriter_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("1234567890");
        final StringWriter writer = new StringWriter();
        writer.append("Test ");

        sb.appendTo(writer);

        assertEquals("Test 1234567890", writer.toString());
    }

    @Test
    public void testAppendToStringBuilder_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("1234567890");
        final StringBuilder builder = new StringBuilder("Test ");

        sb.appendTo(builder);

        assertEquals("Test 1234567890", builder.toString());
    }

    @Test
    public void testAppendToStringBuffer_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("1234567890");
        final StringBuffer buffer = new StringBuffer("Test ");

        sb.appendTo(buffer);

        assertEquals("Test 1234567890", buffer.toString());
    }

    @Test
    public void testAppendToCharBuffer_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("1234567890");
        final String text = "Test ";
        final CharBuffer buffer = CharBuffer.allocate(sb.size() + text.length());
        buffer.put(text);

        sb.appendTo(buffer);

        buffer.flip();
        assertEquals("Test 1234567890", buffer.toString());
    }

}
