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
    public void testCapacityAndLength_33_oe() throws Exception {
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

        try {
    sb.setLength(-1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: setLength(-1) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSetLength_4_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        sb.append("Hello");
        sb.setLength(2);  // shorten
        // removed other assertion
        sb.setLength(2);  // no change
        // removed other assertion
        sb.setLength(3);  // lengthen
        // removed other assertion

        try {
    sb.setLength(-1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: setLength(-1) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testCharAt_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        try {
    sb.charAt(0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: charAt(0) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testCharAt_2_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        try {
    sb.charAt(-1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: charAt(-1) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testCharAt_6_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    sb.charAt(-1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: charAt(-1) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testCharAt_7_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    sb.charAt(3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: charAt(3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSetCharAt_1_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        try {
    sb.setCharAt(0, 'f');
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: setCharAt(0, ) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSetCharAt_2_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        try {
    sb.setCharAt(-1, 'f');
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: setCharAt(-1, ) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSetCharAt_3_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        // removed other assertion
        // removed other assertion
        sb.append("foo");
        sb.setCharAt(0, 'b');
        sb.setCharAt(1, 'a');
        sb.setCharAt(2, 'r');
        try {
    sb.setCharAt(3, '!');
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: setCharAt(3, ) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testDeleteCharAt_2_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("abc");
        sb.deleteCharAt(0);
        // removed other assertion

        try {
    sb.deleteCharAt(1000);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testToCharArrayIntInt_9_oe() throws Exception {
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

        try {
    sb.toCharArray(-1, 5);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: no string index out of bound on -1");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testToCharArrayIntInt_10_oe() throws Exception {
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

        try {
    sb.toCharArray(6, 5);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: no string index out of bound on -1");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testGetCharsIntIntCharArrayInt_3_oe( ) throws Exception {
        final StrBuilder sb = new StrBuilder();

        sb.append("junit");
        final char[] a = new char[5];
        sb.getChars(0, 5, a, 0);
        // removed other assertion

        final char[] b = new char[5];
        sb.getChars(0, 2, b, 3);
        // removed other assertion

        try {
    sb.getChars(-1, 0, b, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testGetCharsIntIntCharArrayInt_4_oe( ) throws Exception {
        final StrBuilder sb = new StrBuilder();

        sb.append("junit");
        final char[] a = new char[5];
        sb.getChars(0, 5, a, 0);
        // removed other assertion

        final char[] b = new char[5];
        sb.getChars(0, 2, b, 3);
        // removed other assertion

        // removed other assertion
        try {
    sb.getChars(0, -1, b, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testGetCharsIntIntCharArrayInt_5_oe( ) throws Exception {
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
        try {
    sb.getChars(0, 20, b, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testGetCharsIntIntCharArrayInt_6_oe( ) throws Exception {
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
        try {
    sb.getChars(4, 2, b, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testDeleteIntInt_5_oe() throws Exception {
        final StrBuilder sb = new StrBuilder("abc");
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(1, 2);
        // removed other assertion
        sb.delete(0, 1);
        // removed other assertion
        sb.delete(0, 1000);
        // removed other assertion

        try {
    sb.delete(1, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testDeleteIntInt_6_oe() throws Exception {
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
        try {
    sb.delete(-1, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testDeleteIntInt_7_oe() throws Exception {
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
        try {
    new StrBuilder("anything").delete(2, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testReplace_int_int_String_8_oe() throws Exception {
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
        try {
    sb1.replace(2, 1, "anything");
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testReplace_int_int_String_9_oe() throws Exception {
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
        try {
    sb2.replace(1, 2, "anything");
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testReplace_int_int_String_10_oe() throws Exception {
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
        try {
    sb2.replace(-1, 1, "anything");
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_12_oe() throws Exception {
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
        try {
    sb1.replace(StrMatcher.stringMatcher("aa"), "-", 11, sb1.length(), -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryStartIndex_14_oe() throws Exception {
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
        try {
    sb2.replace(StrMatcher.stringMatcher("aa"), "-", -1, sb2.length(), -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testReplace_StrMatcher_String_int_int_int_VaryEndIndex_12_oe() throws Exception {
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
        try {
    sb1.replace(StrMatcher.stringMatcher("aa"), "-", 2, 1, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSubSequenceIntInt_1_oe() throws Exception {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        try {
    sb.subSequence(-1, 5);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSubSequenceIntInt_2_oe() throws Exception {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        // removed other assertion

        // End index is negative
        try {
    sb.subSequence(2, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSubSequenceIntInt_3_oe() throws Exception {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        // removed other assertion

        // End index is negative
        // removed other assertion

        // End index greater than length()
        try {
    sb.subSequence(2, sb.length() + 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSubSequenceIntInt_4_oe() throws Exception {
       final StrBuilder sb = new StrBuilder ("hello goodbye");
       // Start index is negative
        // removed other assertion

        // End index is negative
        // removed other assertion

        // End index greater than length()
        // removed other assertion

        // Start index greater then end index
        try {
    sb.subSequence(3, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSubstringInt_5_oe() throws Exception {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    sb.substring(-1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSubstringInt_6_oe() throws Exception {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    sb.substring(15);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSubstringIntInt_6_oe() throws Exception {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        try {
    sb.substring(-1, 5);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testSubstringIntInt_7_oe() throws Exception {
        final StrBuilder sb = new StrBuilder ("hello goodbye");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        try {
    sb.substring(15, 20);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
        try {
    r.read(arr, -1, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
        try {
    r.read(arr, 0, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
        try {
    r.read(arr, 100, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
        try {
    r.read(arr, 0, 100);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
        try {
    r.read(arr, Integer.MAX_VALUE, Integer.MAX_VALUE);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

}
