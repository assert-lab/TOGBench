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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.CharBuffer;
import java.util.Locale;

import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Equals/IndexOf methods
 */
public class StringUtilsEqualsIndexOfTest_OE25Dev  {
    private static final String BAR = "bar";
    /**
     * Supplementary character U+20000
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    private static final String CharU20000 = "\uD840\uDC00";
    /**
     * Supplementary character U+20001
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    private static final String CharU20001 = "\uD840\uDC01";

    private static final String FOO = "foo";

    private static final String FOOBAR = "foobar";

    private static final String[] FOOBAR_SUB_ARRAY = new String[] {"ob", "ba"};

    // The purpose of this class is to test StringUtils#equals(CharSequence, CharSequence)
    // with a CharSequence implementation whose equals(Object) override requires that the
    // other object be an instance of CustomCharSequence, even though, as char sequences,
    // `seq` may equal the other object.
    private static class CustomCharSequence implements CharSequence {
        private final CharSequence seq;

        CustomCharSequence(final CharSequence seq) {
            this.seq = seq;
        }

        @Override
        public char charAt(final int index) {
            return seq.charAt(index);
        }

        @Override
        public int length() {
            return seq.length();
        }

        @Override
        public CharSequence subSequence(final int start, final int end) {
            return new CustomCharSequence(seq.subSequence(start, end));
        }

        @Override
        public boolean equals(final Object obj) {
            if (!(obj instanceof CustomCharSequence)) {
                return false;
            }
            final CustomCharSequence other = (CustomCharSequence) obj;
            return seq.equals(other.seq);
        }

        @Override
        public int hashCode() {
            return seq.hashCode();
        }

        @Override
        public String toString() {
            return seq.toString();
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */

    @Test
    // Non-overlapping test
    public void testLANG1241_1() {
        //                                          0  3  6
        assertEquals(0, StringUtils.ordinalIndexOf("abaabaab", "ab", 1));
        assertEquals(3, StringUtils.ordinalIndexOf("abaabaab", "ab", 2));
        assertEquals(6, StringUtils.ordinalIndexOf("abaabaab", "ab", 3));
    }

    @Test
    // Overlapping matching test
    public void testLANG1241_2() {
        //                                          0 2 4
        assertEquals(0, StringUtils.ordinalIndexOf("abababa", "aba", 1));
        assertEquals(2, StringUtils.ordinalIndexOf("abababa", "aba", 2));
        assertEquals(4, StringUtils.ordinalIndexOf("abababa", "aba", 3));
        assertEquals(0, StringUtils.ordinalIndexOf("abababab", "abab", 1));
        assertEquals(2, StringUtils.ordinalIndexOf("abababab", "abab", 2));
        assertEquals(4, StringUtils.ordinalIndexOf("abababab", "abab", 3));
    }


}
