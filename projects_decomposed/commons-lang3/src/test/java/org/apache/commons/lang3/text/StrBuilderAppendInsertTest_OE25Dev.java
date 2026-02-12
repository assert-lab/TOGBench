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
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link org.apache.commons.lang3.text.StrBuilder}.
 */
@Deprecated
public class StrBuilderAppendInsertTest_OE25Dev {

    /** The system line separator. */
    private static final String SEP = System.lineSeparator();

    /** Test subclass of Object, with a toString method. */
    private static final Object FOO = new Object() {
        @Override
        public String toString() {
            return "foo";
        }
    };

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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    // See: https://issues.apache.org/jira/browse/LANG-299

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

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @Test
    public void testAppend_String_int_int_3_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo", 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        try {
    sb1.append("bar", -1, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], -1,) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_String_int_int_4_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo", 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        try {
    sb1.append("bar", 3, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 3,) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_String_int_int_5_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo", 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        try {
    sb1.append("bar", 1, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[],, -1) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_String_int_int_6_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo", 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append("bar", 1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 1, 3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_String_int_int_7_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo", 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append("bar", -1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], -1, 3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_String_int_int_8_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo", 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append("bar", 4, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 4, 0) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuilder_int_int_3_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        try {
    sb1.append(new StringBuilder("bar"), -1, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(StringBuilder, -1,) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuilder_int_int_4_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        try {
    sb1.append(new StringBuilder("bar"), 3, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(StringBuilder, 3,) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuilder_int_int_5_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StringBuilder("bar"), 1, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(StringBuilder,, -1) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuilder_int_int_6_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StringBuilder("bar"), 1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(StringBuilder, 1, 3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuilder_int_int_7_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StringBuilder("bar"), -1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(StringBuilder, -1, 3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuilder_int_int_8_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StringBuilder("bar"), 4, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(StringBuilder, 4, 0) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuffer_int_int_3_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        try {
    sb1.append(new StringBuffer("bar"), -1, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], -1,) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuffer_int_int_4_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        try {
    sb1.append(new StringBuffer("bar"), 3, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 3,) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuffer_int_int_5_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StringBuffer("bar"), 1, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[],, -1) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuffer_int_int_6_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StringBuffer("bar"), 1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 1, 3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuffer_int_int_7_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StringBuffer("bar"), -1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], -1, 3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StringBuffer_int_int_8_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StringBuffer("bar"), 4, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 4, 0) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StrBuilder_int_int_3_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        try {
    sb1.append(new StrBuilder("bar"), -1, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], -1,) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StrBuilder_int_int_4_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        try {
    sb1.append(new StrBuilder("bar"), 3, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 3,) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StrBuilder_int_int_5_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StrBuilder("bar"), 1, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[],, -1) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StrBuilder_int_int_6_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StrBuilder("bar"), 1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 1, 3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StrBuilder_int_int_7_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StrBuilder("bar"), -1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], -1, 3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_StrBuilder_int_int_8_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new StrBuilder("bar"), 4, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 4, 0) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_CharArray_int_int_3_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[]{'f', 'o', 'o'}, 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        try {
    sb1.append(new char[]{'b', 'a', 'r'}, -1, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], -1,) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_CharArray_int_int_4_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[]{'f', 'o', 'o'}, 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        try {
    sb1.append(new char[]{'b', 'a', 'r'}, 3, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 3,) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_CharArray_int_int_5_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[]{'f', 'o', 'o'}, 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new char[]{'b', 'a', 'r'}, 1, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[],, -1) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_CharArray_int_int_6_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[]{'f', 'o', 'o'}, 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new char[]{'b', 'a', 'r'}, 1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 1, 3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_CharArray_int_int_7_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[]{'f', 'o', 'o'}, 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new char[]{'b', 'a', 'r'}, -1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], -1, 3) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testAppend_CharArray_int_int_8_oe() throws Exception {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[]{'f', 'o', 'o'}, 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb1.append(new char[]{'b', 'a', 'r'}, 4, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: append(char[], 4, 0) expected IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_2_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, FOO);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, Object) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_3_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, FOO);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, Object) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_7_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, "foo");
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, String) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_8_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, "foo");
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, String) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_12_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, new char[]{'f', 'o', 'o'});
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, char[]) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_13_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, new char[]{'f', 'o', 'o'});
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, char[]) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_18_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, char[], 3, 3) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_19_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, char[], 3, 3) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_22_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        try {
    sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, -1, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(0, char[], -1, 3) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_23_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 10, 3);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(0, char[], 10, 3) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_24_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(0, char[], 0, -1) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_25_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 10);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(0, char[], 0, 10) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_29_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, true);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, boolean) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_30_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, true);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, boolean) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_34_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, true);
        // removed other assertion

        sb.insert(0, false);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, '!');
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, char) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_35_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, true);
        // removed other assertion

        sb.insert(0, false);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, '!');
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, char) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_38_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, true);
        // removed other assertion

        sb.insert(0, false);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '!');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, int) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_39_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, true);
        // removed other assertion

        sb.insert(0, false);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '!');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, int) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_42_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, true);
        // removed other assertion

        sb.insert(0, false);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '!');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '0');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, 1L);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, long) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_43_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, true);
        // removed other assertion

        sb.insert(0, false);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '!');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '0');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, 1L);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, long) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_46_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, true);
        // removed other assertion

        sb.insert(0, false);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '!');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '0');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, 1L);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, 2.3F);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, float) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_47_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, true);
        // removed other assertion

        sb.insert(0, false);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '!');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '0');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, 1L);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, 2.3F);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, float) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_50_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, true);
        // removed other assertion

        sb.insert(0, false);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '!');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '0');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, 1L);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, 2.3F);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, 4.5D);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, double) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsert_51_oe() throws Exception {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, new char[0]);
        // removed other assertion

        sb.insert(0, new char[]{'f', 'o', 'o'});
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, null, 0, 0);
        // removed other assertion

        sb.insert(0, new char[0], 0, 0);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 0);
        // removed other assertion

        sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, true);
        // removed other assertion

        sb.insert(0, false);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '!');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, '0');
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, 1L);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, 2.3F);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, 4.5D);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, double) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertWithNullText_2_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, FOO);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, Object) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertWithNullText_3_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, FOO);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, Object) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertWithNullText_7_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        try {
    sb.insert(-1, "foo");
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(-1, String) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertWithNullText_8_oe() throws Exception {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        // removed other assertion

        sb.clear();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        try {
    sb.insert(7, "foo");
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: insert(7, String) expected StringIndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

}
