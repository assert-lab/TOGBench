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
    public void testAppendNewLine_1_oe() {
        StrBuilder sb = new StrBuilder("---");
        sb.appendNewLine().append("+++");
        assertEquals("---" + SEP + "+++", sb.toString());
    }

    @Test
    public void testAppendNewLine_2_oe() {
        StrBuilder sb = new StrBuilder("---");
        sb.appendNewLine().append("+++");
        // removed other assertion

        sb = new StrBuilder("---");
        sb.setNewLineText("#").appendNewLine().setNewLineText(null).appendNewLine();
        assertEquals("---#" + SEP, sb.toString());
    }

    @Test
    public void testAppendWithNullText_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendWithNullText_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL");
        // removed other assertion

        sb.appendNull();
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppendWithNullText_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL");
        // removed other assertion

        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        assertEquals("NULLNULL", sb.toString());
    }

    @Test
    public void testAppendWithNullText_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL");
        // removed other assertion

        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        assertEquals("NULLNULLfoo", sb.toString());
    }

    @Test
    public void testAppendWithNullText_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL");
        // removed other assertion

        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        // removed other assertion

        sb.append((String) null);
        assertEquals("NULLNULLfooNULL", sb.toString());
    }

    @Test
    public void testAppendWithNullText_6_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL");
        // removed other assertion

        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        // removed other assertion

        sb.append((String) null);
        // removed other assertion

        sb.append("");
        assertEquals("NULLNULLfooNULL", sb.toString());
    }

    @Test
    public void testAppendWithNullText_7_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL");
        // removed other assertion

        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        // removed other assertion

        sb.append((String) null);
        // removed other assertion

        sb.append("");
        // removed other assertion

        sb.append("bar");
        assertEquals("NULLNULLfooNULLbar", sb.toString());
    }

    @Test
    public void testAppendWithNullText_8_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL");
        // removed other assertion

        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        // removed other assertion

        sb.append((String) null);
        // removed other assertion

        sb.append("");
        // removed other assertion

        sb.append("bar");
        // removed other assertion

        sb.append((StringBuffer) null);
        assertEquals("NULLNULLfooNULLbarNULL", sb.toString());
    }

    @Test
    public void testAppendWithNullText_9_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL");
        // removed other assertion

        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        // removed other assertion

        sb.append((String) null);
        // removed other assertion

        sb.append("");
        // removed other assertion

        sb.append("bar");
        // removed other assertion

        sb.append((StringBuffer) null);
        // removed other assertion

        sb.append(new StringBuffer("baz"));
        assertEquals("NULLNULLfooNULLbarNULLbaz", sb.toString());
    }

    @Test
    public void testAppend_Object_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendNull();
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppend_Object_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppend_Object_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_Object_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        // removed other assertion

        sb.append((StringBuffer) null);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_Object_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        // removed other assertion

        sb.append((StringBuffer) null);
        // removed other assertion

        sb.append(new StringBuffer("baz"));
        assertEquals("foobaz", sb.toString());
    }

    @Test
    public void testAppend_Object_6_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        // removed other assertion

        sb.append((StringBuffer) null);
        // removed other assertion

        sb.append(new StringBuffer("baz"));
        // removed other assertion

        sb.append(new StrBuilder("yes"));
        assertEquals("foobazyes", sb.toString());
    }

    @Test
    public void testAppend_Object_7_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        // removed other assertion

        sb.append((StringBuffer) null);
        // removed other assertion

        sb.append(new StringBuffer("baz"));
        // removed other assertion

        sb.append(new StrBuilder("yes"));
        // removed other assertion

        sb.append((CharSequence) "Seq");
        assertEquals("foobazyesSeq", sb.toString());
    }

    @Test
    public void testAppend_Object_8_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendNull();
        // removed other assertion

        sb.append((Object) null);
        // removed other assertion

        sb.append(FOO);
        // removed other assertion

        sb.append((StringBuffer) null);
        // removed other assertion

        sb.append(new StringBuffer("baz"));
        // removed other assertion

        sb.append(new StrBuilder("yes"));
        // removed other assertion

        sb.append((CharSequence) "Seq");
        // removed other assertion

        sb.append(new StringBuilder("bld")); // Check it supports StringBuilder
        assertEquals("foobazyesSeqbld", sb.toString());
    }

    @Test
    public void testAppend_StringBuilder_1_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null);
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppend_StringBuilder_2_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"));
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StringBuilder_3_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"));
        // removed other assertion

        sb.append(new StringBuilder(""));
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StringBuilder_4_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"));
        // removed other assertion

        sb.append(new StringBuilder(""));
        // removed other assertion

        sb.append(new StringBuilder("bar"));
        assertEquals("foobar", sb.toString());
    }

    @Test
    public void testAppend_String_1_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null);
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppend_String_2_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo");
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_String_3_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo");
        // removed other assertion

        sb.append("");
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_String_4_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo");
        // removed other assertion

        sb.append("");
        // removed other assertion

        sb.append("bar");
        assertEquals("foobar", sb.toString());
    }

    @Test
    public void testAppend_String_int_int_1_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppend_String_int_int_2_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo", 0, 3);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_String_int_int_3_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo", 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append("bar", -1, 1), "append(char[], -1,) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_String_int_int_4_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo", 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append("bar", 3, 1), "append(char[], 3,) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_String_int_int_5_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("foo", 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append("bar", 1, -1), "append(char[],, -1) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_String_int_int_6_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append("bar", 1, 3), "append(char[], 1, 3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_String_int_int_7_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append("bar", -1, 3), "append(char[], -1, 3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_String_int_int_8_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append("bar", 4, 0), "append(char[], 4, 0) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_String_int_int_9_oe() {
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

        // removed other assertion

        sb.append("bar", 3, 0);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_String_int_int_10_oe() {
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

        // removed other assertion

        sb.append("bar", 3, 0);
        // removed other assertion

        sb.append("abcbardef", 3, 3);
        assertEquals("foobar", sb.toString());
    }

    @Test
    public void testAppend_String_int_int_11_oe() {
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

        // removed other assertion

        sb.append("bar", 3, 0);
        // removed other assertion

        sb.append("abcbardef", 3, 3);
        // removed other assertion

        sb.append((CharSequence) "abcbardef", 4, 3);
        assertEquals("foobarard", sb.toString());
    }

    @Test
    public void testAppend_StringBuilder_int_int_1_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppend_StringBuilder_int_int_2_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StringBuilder_int_int_3_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuilder("bar"), -1, 1), "append(StringBuilder, -1,) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuilder_int_int_4_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuilder("bar"), 3, 1), "append(StringBuilder, 3,) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuilder_int_int_5_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuilder("bar"), 1, -1), "append(StringBuilder,, -1) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuilder_int_int_6_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuilder("bar"), 1, 3), "append(StringBuilder, 1, 3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuilder_int_int_7_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuilder("bar"), -1, 3), "append(StringBuilder, -1, 3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuilder_int_int_8_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuilder("bar"), 4, 0), "append(StringBuilder, 4, 0) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuilder_int_int_9_oe() {
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

        // removed other assertion

        sb.append(new StringBuilder("bar"), 3, 0);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StringBuilder_int_int_10_oe() {
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

        // removed other assertion

        sb.append(new StringBuilder("bar"), 3, 0);
        // removed other assertion

        sb.append(new StringBuilder("abcbardef"), 3, 3);
        assertEquals("foobar", sb.toString());
    }

    @Test
    public void testAppend_StringBuilder_int_int_11_oe() {
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

        // removed other assertion

        sb.append(new StringBuilder("bar"), 3, 0);
        // removed other assertion

        sb.append(new StringBuilder("abcbardef"), 3, 3);
        // removed other assertion

        sb.append( new StringBuilder("abcbardef"), 4, 3);
        assertEquals("foobarard", sb.toString());
    }

    @Test
    public void testAppend_StringBuffer_1_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null);
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppend_StringBuffer_2_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"));
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StringBuffer_3_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"));
        // removed other assertion

        sb.append(new StringBuffer(""));
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StringBuffer_4_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"));
        // removed other assertion

        sb.append(new StringBuffer(""));
        // removed other assertion

        sb.append(new StringBuffer("bar"));
        assertEquals("foobar", sb.toString());
    }

    @Test
    public void testAppend_StringBuffer_int_int_1_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppend_StringBuffer_int_int_2_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"), 0, 3);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StringBuffer_int_int_3_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuffer("bar"), -1, 1), "append(char[], -1,) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuffer_int_int_4_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuffer("bar"), 3, 1), "append(char[], 3,) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuffer_int_int_5_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StringBuffer) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StringBuffer("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuffer("bar"), 1, -1), "append(char[],, -1) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuffer_int_int_6_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuffer("bar"), 1, 3), "append(char[], 1, 3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuffer_int_int_7_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuffer("bar"), -1, 3), "append(char[], -1, 3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuffer_int_int_8_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StringBuffer("bar"), 4, 0), "append(char[], 4, 0) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StringBuffer_int_int_9_oe() {
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

        // removed other assertion

        sb.append(new StringBuffer("bar"), 3, 0);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StringBuffer_int_int_10_oe() {
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

        // removed other assertion

        sb.append(new StringBuffer("bar"), 3, 0);
        // removed other assertion

        sb.append(new StringBuffer("abcbardef"), 3, 3);
        assertEquals("foobar", sb.toString());
    }

    @Test
    public void testAppend_StrBuilder_1_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null);
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppend_StrBuilder_2_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"));
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StrBuilder_3_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"));
        // removed other assertion

        sb.append(new StrBuilder(""));
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StrBuilder_4_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"));
        // removed other assertion

        sb.append(new StrBuilder(""));
        // removed other assertion

        sb.append(new StrBuilder("bar"));
        assertEquals("foobar", sb.toString());
    }

    @Test
    public void testAppend_StrBuilder_int_int_1_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppend_StrBuilder_int_int_2_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"), 0, 3);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StrBuilder_int_int_3_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StrBuilder("bar"), -1, 1), "append(char[], -1,) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StrBuilder_int_int_4_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StrBuilder("bar"), 3, 1), "append(char[], 3,) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StrBuilder_int_int_5_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((StrBuilder) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new StrBuilder("foo"), 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StrBuilder("bar"), 1, -1), "append(char[],, -1) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StrBuilder_int_int_6_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StrBuilder("bar"), 1, 3), "append(char[], 1, 3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StrBuilder_int_int_7_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StrBuilder("bar"), -1, 3), "append(char[], -1, 3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StrBuilder_int_int_8_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new StrBuilder("bar"), 4, 0), "append(char[], 4, 0) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_StrBuilder_int_int_9_oe() {
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

        // removed other assertion

        sb.append(new StrBuilder("bar"), 3, 0);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_StrBuilder_int_int_10_oe() {
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

        // removed other assertion

        sb.append(new StrBuilder("bar"), 3, 0);
        // removed other assertion

        sb.append(new StrBuilder("abcbardef"), 3, 3);
        assertEquals("foobar", sb.toString());
    }

    @Test
    public void testAppend_CharArray_1_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null);
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppend_CharArray_2_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[0]);
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppend_CharArray_3_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[0]);
        // removed other assertion

        sb.append(new char[]{'f', 'o', 'o'});
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_CharArray_int_int_1_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        assertEquals("NULL", sb.toString());
    }

    @Test
    public void testAppend_CharArray_int_int_2_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[]{'f', 'o', 'o'}, 0, 3);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_CharArray_int_int_3_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[]{'f', 'o', 'o'}, 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new char[]{'b', 'a', 'r'}, -1, 1), "append(char[], -1,) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_CharArray_int_int_4_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[]{'f', 'o', 'o'}, 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new char[]{'b', 'a', 'r'}, 3, 1), "append(char[], 3,) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_CharArray_int_int_5_oe() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((char[]) null, 0, 1);
        // removed other assertion

        sb = new StrBuilder();
        sb.append(new char[]{'f', 'o', 'o'}, 0, 3);
        // removed other assertion

        final StrBuilder sb1 = sb;
        // removed other assertion

        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new char[]{'b', 'a', 'r'}, 1, -1), "append(char[],, -1) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_CharArray_int_int_6_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new char[]{'b', 'a', 'r'}, 1, 3), "append(char[], 1, 3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_CharArray_int_int_7_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new char[]{'b', 'a', 'r'}, -1, 3), "append(char[], -1, 3) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_CharArray_int_int_8_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb1.append(new char[]{'b', 'a', 'r'}, 4, 0), "append(char[], 4, 0) expected IndexOutOfBoundsException");
    }

    @Test
    public void testAppend_CharArray_int_int_9_oe() {
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

        // removed other assertion

        sb.append(new char[]{'b', 'a', 'r'}, 3, 0);
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppend_CharArray_int_int_10_oe() {
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

        // removed other assertion

        sb.append(new char[]{'b', 'a', 'r'}, 3, 0);
        // removed other assertion

        sb.append(new char[]{'a', 'b', 'c', 'b', 'a', 'r', 'd', 'e', 'f'}, 3, 3);
        assertEquals("foobar", sb.toString());
    }

    @Test
    public void testAppend_Boolean_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append(true);
        assertEquals("true", sb.toString());
    }

    @Test
    public void testAppend_Boolean_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append(true);
        // removed other assertion

        sb.append(false);
        assertEquals("truefalse", sb.toString());
    }

    @Test
    public void testAppend_Boolean_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append(true);
        // removed other assertion

        sb.append(false);
        // removed other assertion

        sb.append('!');
        assertEquals("truefalse!", sb.toString());
    }

    @Test
    public void testAppend_PrimitiveNumber_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append(0);
        assertEquals("0", sb.toString());
    }

    @Test
    public void testAppend_PrimitiveNumber_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append(0);
        // removed other assertion

        sb.append(1L);
        assertEquals("01", sb.toString());
    }

    @Test
    public void testAppend_PrimitiveNumber_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append(0);
        // removed other assertion

        sb.append(1L);
        // removed other assertion

        sb.append(2.3f);
        assertEquals("012.3", sb.toString());
    }

    @Test
    public void testAppend_PrimitiveNumber_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append(0);
        // removed other assertion

        sb.append(1L);
        // removed other assertion

        sb.append(2.3f);
        // removed other assertion

        sb.append(4.5d);
        assertEquals("012.34.5", sb.toString());
    }

    @Test
    public void testAppendln_FormattedString_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final String str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("Hello %s", "Alice");
        assertEquals("Hello Alice" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_FormattedString_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final String str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("Hello %s", "Alice");
        // removed other assertion
        assertEquals(2,count[0]);// appendNewLine()calls append(String)assertEquals(1,count[1]);
    }

    @Test
    public void testAppendln_Object_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendln((Object) null);
        assertEquals("" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_Object_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendln((Object) null);
        // removed other assertion

        sb.appendln(FOO);
        assertEquals(SEP + "foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_Object_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendln((Object) null);
        // removed other assertion

        sb.appendln(FOO);
        // removed other assertion

        sb.appendln(Integer.valueOf(6));
        assertEquals(SEP + "foo" + SEP + "6" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_String_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final String str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo");
        assertEquals("foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_String_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final String str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo");
        // removed other assertion
        assertEquals(2,count[0]);// appendNewLine()calls append(String)assertEquals(1,count[1]);
    }

    @Test
    public void testAppendln_String_int_int_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final String str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo", 0, 3);
        assertEquals("foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_String_int_int_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final String str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo", 0, 3);
        // removed other assertion
        assertEquals(1, count[0]);
    }

    @Test
    public void testAppendln_String_int_int_3_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final String str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo", 0, 3);
        // removed other assertion
        // removed other assertion
        assertEquals(1, count[1]);
    }

    @Test
    public void testAppendln_StringBuffer_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuffer str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuffer("foo"));
        assertEquals("foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_StringBuffer_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuffer str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuffer("foo"));
        // removed other assertion
        assertEquals(1, count[0]);
    }

    @Test
    public void testAppendln_StringBuffer_3_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuffer str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuffer("foo"));
        // removed other assertion
        // removed other assertion
        assertEquals(1, count[1]);
    }

    @Test
    public void testAppendln_StringBuilder_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuilder str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuilder("foo"));
        assertEquals("foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_StringBuilder_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuilder str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuilder("foo"));
        // removed other assertion
        assertEquals(1, count[0]);
    }

    @Test
    public void testAppendln_StringBuilder_3_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuilder str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuilder("foo"));
        // removed other assertion
        // removed other assertion
        assertEquals(1, count[1]);
    }

    @Test
    public void testAppendln_StringBuffer_int_int_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuffer str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuffer("foo"), 0, 3);
        assertEquals("foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_StringBuffer_int_int_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuffer str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuffer("foo"), 0, 3);
        // removed other assertion
        assertEquals(1, count[0]);
    }

    @Test
    public void testAppendln_StringBuffer_int_int_3_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuffer str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuffer("foo"), 0, 3);
        // removed other assertion
        // removed other assertion
        assertEquals(1, count[1]);
    }

    @Test
    public void testAppendln_StringBuilder_int_int_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuilder str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuilder("foo"), 0, 3);
        assertEquals("foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_StringBuilder_int_int_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuilder str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuilder("foo"), 0, 3);
        // removed other assertion
        assertEquals(1, count[0]);
    }

    @Test
    public void testAppendln_StringBuilder_int_int_3_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StringBuilder str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StringBuilder("foo"), 0, 3);
        // removed other assertion
        // removed other assertion
        assertEquals(1, count[1]);
    }

    @Test
    public void testAppendln_StrBuilder_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StrBuilder str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StrBuilder("foo"));
        assertEquals("foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_StrBuilder_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StrBuilder str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StrBuilder("foo"));
        // removed other assertion
        assertEquals(1, count[0]);
    }

    @Test
    public void testAppendln_StrBuilder_3_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StrBuilder str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StrBuilder("foo"));
        // removed other assertion
        // removed other assertion
        assertEquals(1, count[1]);
    }

    @Test
    public void testAppendln_StrBuilder_int_int_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StrBuilder str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StrBuilder("foo"), 0, 3);
        assertEquals("foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_StrBuilder_int_int_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StrBuilder str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StrBuilder("foo"), 0, 3);
        // removed other assertion
        assertEquals(1, count[0]);
    }

    @Test
    public void testAppendln_StrBuilder_int_int_3_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final StrBuilder str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln(new StrBuilder("foo"), 0, 3);
        // removed other assertion
        // removed other assertion
        assertEquals(1, count[1]);
    }

    @Test
    public void testAppendln_CharArray_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final char[] str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo".toCharArray());
        assertEquals("foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_CharArray_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final char[] str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo".toCharArray());
        // removed other assertion
        assertEquals(1, count[0]);
    }

    @Test
    public void testAppendln_CharArray_3_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final char[] str) {
                count[0]++;
                return super.append(str);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo".toCharArray());
        // removed other assertion
        // removed other assertion
        assertEquals(1, count[1]);
    }

    @Test
    public void testAppendln_CharArray_int_int_1_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final char[] str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo".toCharArray(), 0, 3);
        assertEquals("foo" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_CharArray_int_int_2_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final char[] str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo".toCharArray(), 0, 3);
        // removed other assertion
        assertEquals(1, count[0]);
    }

    @Test
    public void testAppendln_CharArray_int_int_3_oe() {
        final int[] count = new int[2];
        final StrBuilder sb = new StrBuilder() {
            private static final long serialVersionUID = 1L;

            @Override
            public StrBuilder append(final char[] str, final int startIndex, final int length) {
                count[0]++;
                return super.append(str, startIndex, length);
            }
            @Override
            public StrBuilder appendNewLine() {
                count[1]++;
                return super.appendNewLine();
            }
        };
        sb.appendln("foo".toCharArray(), 0, 3);
        // removed other assertion
        // removed other assertion
        assertEquals(1, count[1]);
    }

    @Test
    public void testAppendln_Boolean_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendln(true);
        assertEquals("true" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_Boolean_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendln(true);
        // removed other assertion

        sb.clear();
        sb.appendln(false);
        assertEquals("false" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_PrimitiveNumber_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendln(0);
        assertEquals("0" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_PrimitiveNumber_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendln(0);
        // removed other assertion

        sb.clear();
        sb.appendln(1L);
        assertEquals("1" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_PrimitiveNumber_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendln(0);
        // removed other assertion

        sb.clear();
        sb.appendln(1L);
        // removed other assertion

        sb.clear();
        sb.appendln(2.3f);
        assertEquals("2.3" + SEP, sb.toString());
    }

    @Test
    public void testAppendln_PrimitiveNumber_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendln(0);
        // removed other assertion

        sb.clear();
        sb.appendln(1L);
        // removed other assertion

        sb.clear();
        sb.appendln(2.3f);
        // removed other assertion

        sb.clear();
        sb.appendln(4.5d);
        assertEquals("4.5" + SEP, sb.toString());
    }

    @Test
    public void testAppendPadding_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("foo");
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppendPadding_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("foo");
        // removed other assertion

        sb.appendPadding(-1, '-');
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppendPadding_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("foo");
        // removed other assertion

        sb.appendPadding(-1, '-');
        // removed other assertion

        sb.appendPadding(0, '-');
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppendPadding_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("foo");
        // removed other assertion

        sb.appendPadding(-1, '-');
        // removed other assertion

        sb.appendPadding(0, '-');
        // removed other assertion

        sb.appendPadding(1, '-');
        assertEquals("foo-", sb.toString());
    }

    @Test
    public void testAppendPadding_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("foo");
        // removed other assertion

        sb.appendPadding(-1, '-');
        // removed other assertion

        sb.appendPadding(0, '-');
        // removed other assertion

        sb.appendPadding(1, '-');
        // removed other assertion

        sb.appendPadding(16, '-');
        assertEquals(20, sb.length());
    }

    @Test
    public void testAppendPadding_6_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.append("foo");
        // removed other assertion

        sb.appendPadding(-1, '-');
        // removed other assertion

        sb.appendPadding(0, '-');
        // removed other assertion

        sb.appendPadding(1, '-');
        // removed other assertion

        sb.appendPadding(16, '-');
        // removed other assertion
        //            12345678901234567890
        assertEquals("foo-----------------", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft("foo", -1, '-');
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 0, '-');
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 1, '-');
        assertEquals("o", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 2, '-');
        assertEquals("oo", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 3, '-');
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_6_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 4, '-');
        assertEquals("-foo", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_7_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 4, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 10, '-');
        assertEquals(10, sb.length());
    }

    @Test
    public void testAppendFixedWidthPadLeft_8_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 4, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 10, '-');
        // removed other assertion
        //            1234567890
        assertEquals("-------foo", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_9_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 4, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft("foo", 10, '-');
        // removed other assertion
        //            1234567890
        // removed other assertion

        sb.clear();
        sb.setNullText("null");
        sb.appendFixedWidthPadLeft(null, 5, '-');
        assertEquals("-null", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_int_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft(123, -1, '-');
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_int_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 0, '-');
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_int_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 1, '-');
        assertEquals("3", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_int_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 2, '-');
        assertEquals("23", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_int_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 3, '-');
        assertEquals("123", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_int_6_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 4, '-');
        assertEquals("-123", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadLeft_int_7_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 4, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 10, '-');
        assertEquals(10, sb.length());
    }

    @Test
    public void testAppendFixedWidthPadLeft_int_8_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadLeft(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 4, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadLeft(123, 10, '-');
        // removed other assertion
        //            1234567890
        assertEquals("-------123", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight("foo", -1, '-');
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 0, '-');
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 1, '-');
        assertEquals("f", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 2, '-');
        assertEquals("fo", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 3, '-');
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_6_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 4, '-');
        assertEquals("foo-", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_7_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 4, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 10, '-');
        assertEquals(10, sb.length());
    }

    @Test
    public void testAppendFixedWidthPadRight_8_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 4, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 10, '-');
        // removed other assertion
        //            1234567890
        assertEquals("foo-------", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_9_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight("foo", -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 4, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight("foo", 10, '-');
        // removed other assertion
        //            1234567890
        // removed other assertion

        sb.clear();
        sb.setNullText("null");
        sb.appendFixedWidthPadRight(null, 5, '-');
        assertEquals("null-", sb.toString());
    }

    @Test
    public void testLang299_1_oe() {
        final StrBuilder sb = new StrBuilder(1);
        sb.appendFixedWidthPadRight("foo", 1, '-');
        assertEquals("f", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_int_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight(123, -1, '-');
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_int_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 0, '-');
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_int_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 1, '-');
        assertEquals("1", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_int_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 2, '-');
        assertEquals("12", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_int_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 3, '-');
        assertEquals("123", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_int_6_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 4, '-');
        assertEquals("123-", sb.toString());
    }

    @Test
    public void testAppendFixedWidthPadRight_int_7_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 4, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 10, '-');
        assertEquals(10, sb.length());
    }

    @Test
    public void testAppendFixedWidthPadRight_int_8_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendFixedWidthPadRight(123, -1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 0, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 1, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 2, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 3, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 4, '-');
        // removed other assertion

        sb.clear();
        sb.appendFixedWidthPadRight(123, 10, '-');
        // removed other assertion
        //            1234567890
        assertEquals("123-------", sb.toString());
    }

    @Test
    public void testAppend_FormattedString_1_oe() {
        StrBuilder sb;

        sb = new StrBuilder();
        sb.append("Hi", (Object[]) null);
        assertEquals("Hi", sb.toString());
    }

    @Test
    public void testAppend_FormattedString_2_oe() {
        StrBuilder sb;

        sb = new StrBuilder();
        sb.append("Hi", (Object[]) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("Hi", "Alice");
        assertEquals("Hi", sb.toString());
    }

    @Test
    public void testAppend_FormattedString_3_oe() {
        StrBuilder sb;

        sb = new StrBuilder();
        sb.append("Hi", (Object[]) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("Hi", "Alice");
        // removed other assertion

        sb = new StrBuilder();
        sb.append("Hi %s", "Alice");
        assertEquals("Hi Alice", sb.toString());
    }

    @Test
    public void testAppend_FormattedString_4_oe() {
        StrBuilder sb;

        sb = new StrBuilder();
        sb.append("Hi", (Object[]) null);
        // removed other assertion

        sb = new StrBuilder();
        sb.append("Hi", "Alice");
        // removed other assertion

        sb = new StrBuilder();
        sb.append("Hi %s", "Alice");
        // removed other assertion

        sb = new StrBuilder();
        sb.append("Hi %s %,d", "Alice", 5000);
        // group separator depends on system locale
        final char groupingSeparator = DecimalFormatSymbols.getInstance().getGroupingSeparator();
        final String expected = "Hi Alice 5" + groupingSeparator + "000";
        assertEquals(expected, sb.toString());
    }

    @Test
    public void testAppendAll_Array_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendAll((Object[]) null);
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendAll_Array_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendAll((Object[]) null);
        // removed other assertion

        sb.clear();
        sb.appendAll();
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendAll_Array_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendAll((Object[]) null);
        // removed other assertion

        sb.clear();
        sb.appendAll();
        // removed other assertion

        sb.clear();
        sb.appendAll("foo", "bar", "baz");
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testAppendAll_Array_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendAll((Object[]) null);
        // removed other assertion

        sb.clear();
        sb.appendAll();
        // removed other assertion

        sb.clear();
        sb.appendAll("foo", "bar", "baz");
        // removed other assertion

        sb.clear();
        sb.appendAll("foo", "bar", "baz");
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testAppendAll_Collection_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendAll((Collection<?>) null);
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendAll_Collection_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendAll((Collection<?>) null);
        // removed other assertion

        sb.clear();
        sb.appendAll(Collections.EMPTY_LIST);
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendAll_Collection_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendAll((Collection<?>) null);
        // removed other assertion

        sb.clear();
        sb.appendAll(Collections.EMPTY_LIST);
        // removed other assertion

        sb.clear();
        sb.appendAll(Arrays.asList("foo", "bar", "baz"));
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testAppendAll_Iterator_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendAll((Iterator<?>) null);
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendAll_Iterator_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendAll((Iterator<?>) null);
        // removed other assertion

        sb.clear();
        sb.appendAll(Collections.EMPTY_LIST.iterator());
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendAll_Iterator_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendAll((Iterator<?>) null);
        // removed other assertion

        sb.clear();
        sb.appendAll(Collections.EMPTY_LIST.iterator());
        // removed other assertion

        sb.clear();
        sb.appendAll(Arrays.asList("foo", "bar", "baz").iterator());
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Array_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Object[]) null, ",");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Array_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Object[]) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(new Object[0], ",");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Array_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Object[]) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(new Object[0], ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(new Object[]{"foo", "bar", "baz"}, ",");
        assertEquals("foo,bar,baz", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Array_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Object[]) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(new Object[0], ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(new Object[]{"foo", "bar", "baz"}, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(new Object[]{"foo", "bar", "baz"}, null);
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Array_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Object[]) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(new Object[0], ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(new Object[]{"foo", "bar", "baz"}, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(new Object[]{"foo", "bar", "baz"}, null);
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(new Object[]{"foo", null, "baz"}, ",");
        assertEquals("foo,,baz", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Collection_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Collection<?>) null, ",");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Collection_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Collection<?>) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Collections.EMPTY_LIST, ",");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Collection_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Collection<?>) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Collections.EMPTY_LIST, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", "bar", "baz"), ",");
        assertEquals("foo,bar,baz", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Collection_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Collection<?>) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Collections.EMPTY_LIST, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", "bar", "baz"), ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", "bar", "baz"), null);
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Collection_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Collection<?>) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Collections.EMPTY_LIST, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", "bar", "baz"), ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", "bar", "baz"), null);
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", null, "baz"), ",");
        assertEquals("foo,,baz", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Iterator_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Iterator<?>) null, ",");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Iterator_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Iterator<?>) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Collections.EMPTY_LIST.iterator(), ",");
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Iterator_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Iterator<?>) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Collections.EMPTY_LIST.iterator(), ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", "bar", "baz").iterator(), ",");
        assertEquals("foo,bar,baz", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Iterator_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Iterator<?>) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Collections.EMPTY_LIST.iterator(), ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", "bar", "baz").iterator(), ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", "bar", "baz").iterator(), null);
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testAppendWithSeparators_Iterator_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendWithSeparators((Iterator<?>) null, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Collections.EMPTY_LIST.iterator(), ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", "bar", "baz").iterator(), ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", "bar", "baz").iterator(), null);
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", null, "baz").iterator(), ",");
        assertEquals("foo,,baz", sb.toString());
    }

    @Test
    public void testAppendWithSeparatorsWithNullText_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.appendWithSeparators(new Object[]{"foo", null, "baz"}, ",");
        assertEquals("foo,null,baz", sb.toString());
    }

    @Test
    public void testAppendWithSeparatorsWithNullText_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.appendWithSeparators(new Object[]{"foo", null, "baz"}, ",");
        // removed other assertion

        sb.clear();
        sb.appendWithSeparators(Arrays.asList("foo", null, "baz"), ",");
        assertEquals("foo,null,baz", sb.toString());
    }

    @Test
    public void testAppendSeparator_String_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(",");  // no effect
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendSeparator_String_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(",");  // no effect
        // removed other assertion
        sb.append("foo");
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppendSeparator_String_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(",");  // no effect
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        sb.appendSeparator(",");
        assertEquals("foo,", sb.toString());
    }

    @Test
    public void testAppendSeparator_String_String_1_oe() {
        final StrBuilder sb = new StrBuilder();
        final String startSeparator = "order by ";
        final String standardSeparator = ",";
        final String foo = "foo";
        sb.appendSeparator(null, null);
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendSeparator_String_String_2_oe() {
        final StrBuilder sb = new StrBuilder();
        final String startSeparator = "order by ";
        final String standardSeparator = ",";
        final String foo = "foo";
        sb.appendSeparator(null, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, null);
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendSeparator_String_String_3_oe() {
        final StrBuilder sb = new StrBuilder();
        final String startSeparator = "order by ";
        final String standardSeparator = ",";
        final String foo = "foo";
        sb.appendSeparator(null, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, startSeparator);
        assertEquals(startSeparator, sb.toString());
    }

    @Test
    public void testAppendSeparator_String_String_4_oe() {
        final StrBuilder sb = new StrBuilder();
        final String startSeparator = "order by ";
        final String standardSeparator = ",";
        final String foo = "foo";
        sb.appendSeparator(null, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, startSeparator);
        // removed other assertion
        sb.appendSeparator(null, null);
        assertEquals(startSeparator, sb.toString());
    }

    @Test
    public void testAppendSeparator_String_String_5_oe() {
        final StrBuilder sb = new StrBuilder();
        final String startSeparator = "order by ";
        final String standardSeparator = ",";
        final String foo = "foo";
        sb.appendSeparator(null, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, startSeparator);
        // removed other assertion
        sb.appendSeparator(null, null);
        // removed other assertion
        sb.appendSeparator(null, startSeparator);
        assertEquals(startSeparator, sb.toString());
    }

    @Test
    public void testAppendSeparator_String_String_6_oe() {
        final StrBuilder sb = new StrBuilder();
        final String startSeparator = "order by ";
        final String standardSeparator = ",";
        final String foo = "foo";
        sb.appendSeparator(null, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, startSeparator);
        // removed other assertion
        sb.appendSeparator(null, null);
        // removed other assertion
        sb.appendSeparator(null, startSeparator);
        // removed other assertion
        sb.append(foo);
        assertEquals(startSeparator + foo, sb.toString());
    }

    @Test
    public void testAppendSeparator_String_String_7_oe() {
        final StrBuilder sb = new StrBuilder();
        final String startSeparator = "order by ";
        final String standardSeparator = ",";
        final String foo = "foo";
        sb.appendSeparator(null, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, null);
        // removed other assertion
        sb.appendSeparator(standardSeparator, startSeparator);
        // removed other assertion
        sb.appendSeparator(null, null);
        // removed other assertion
        sb.appendSeparator(null, startSeparator);
        // removed other assertion
        sb.append(foo);
        // removed other assertion
        sb.appendSeparator(standardSeparator, startSeparator);
        assertEquals(startSeparator + foo + standardSeparator, sb.toString());
    }

    @Test
    public void testAppendSeparator_char_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(',');  // no effect
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendSeparator_char_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(',');  // no effect
        // removed other assertion
        sb.append("foo");
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppendSeparator_char_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(',');  // no effect
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        sb.appendSeparator(',');
        assertEquals("foo,", sb.toString());
    }

    @Test
    public void testAppendSeparator_char_char_1_oe() {
        final StrBuilder sb = new StrBuilder();
        final char startSeparator = ':';
        final char standardSeparator = ',';
        final String foo = "foo";
        sb.appendSeparator(standardSeparator, startSeparator);  // no effect
        assertEquals(String.valueOf(startSeparator), sb.toString());
    }

    @Test
    public void testAppendSeparator_char_char_2_oe() {
        final StrBuilder sb = new StrBuilder();
        final char startSeparator = ':';
        final char standardSeparator = ',';
        final String foo = "foo";
        sb.appendSeparator(standardSeparator, startSeparator);  // no effect
        // removed other assertion
        sb.append(foo);
        assertEquals(String.valueOf(startSeparator) + foo, sb.toString());
    }

    @Test
    public void testAppendSeparator_char_char_3_oe() {
        final StrBuilder sb = new StrBuilder();
        final char startSeparator = ':';
        final char standardSeparator = ',';
        final String foo = "foo";
        sb.appendSeparator(standardSeparator, startSeparator);  // no effect
        // removed other assertion
        sb.append(foo);
        // removed other assertion
        sb.appendSeparator(standardSeparator, startSeparator);
        assertEquals(String.valueOf(startSeparator) + foo + standardSeparator, sb.toString());
    }

    @Test
    public void testAppendSeparator_String_int_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(",", 0);  // no effect
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendSeparator_String_int_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(",", 0);  // no effect
        // removed other assertion
        sb.append("foo");
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppendSeparator_String_int_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(",", 0);  // no effect
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        sb.appendSeparator(",", 1);
        assertEquals("foo,", sb.toString());
    }

    @Test
    public void testAppendSeparator_String_int_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(",", 0);  // no effect
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        sb.appendSeparator(",", 1);
        // removed other assertion

        sb.appendSeparator(",", -1);  // no effect
        assertEquals("foo,", sb.toString());
    }

    @Test
    public void testAppendSeparator_char_int_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(',', 0);  // no effect
        assertEquals("", sb.toString());
    }

    @Test
    public void testAppendSeparator_char_int_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(',', 0);  // no effect
        // removed other assertion
        sb.append("foo");
        assertEquals("foo", sb.toString());
    }

    @Test
    public void testAppendSeparator_char_int_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(',', 0);  // no effect
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        sb.appendSeparator(',', 1);
        assertEquals("foo,", sb.toString());
    }

    @Test
    public void testAppendSeparator_char_int_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(',', 0);  // no effect
        // removed other assertion
        sb.append("foo");
        // removed other assertion
        sb.appendSeparator(',', 1);
        // removed other assertion

        sb.appendSeparator(',', -1);  // no effect
        assertEquals("foo,", sb.toString());
    }

    @Test
    public void testInsert_1_oe() {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_2_oe() {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, FOO), "insert(-1, Object) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_3_oe() {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, FOO), "insert(7, Object) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_4_oe() {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_5_oe() {

        final StrBuilder sb = new StrBuilder();
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testInsert_6_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_7_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, "foo"), "insert(-1, String) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_8_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, "foo"), "insert(7, String) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_9_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_10_oe() {

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
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testInsert_11_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_12_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, new char[]{'f', 'o', 'o'}), "insert(-1, char[]) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_13_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, new char[]{'f', 'o', 'o'}), "insert(7, char[]) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_14_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_15_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_16_oe() {

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
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testInsert_17_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_18_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3), "insert(-1, char[], 3, 3) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_19_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 3, 3), "insert(7, char[], 3, 3) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_20_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_21_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_22_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, -1, 3), "insert(0, char[], -1, 3) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_23_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 10, 3), "insert(0, char[], 10, 3) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_24_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, -1), "insert(0, char[], 0, -1) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_25_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(0, new char[]{'a', 'b', 'c', 'f', 'o', 'o', 'd', 'e', 'f'}, 0, 10), "insert(0, char[], 0, 10) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_26_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_27_oe() {

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
        assertEquals("foobarbaz", sb.toString());
    }

    @Test
    public void testInsert_28_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_29_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, true), "insert(-1, boolean) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_30_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, true), "insert(7, boolean) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_31_oe() {

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
        assertEquals("truebarbaz", sb.toString());
    }

    @Test
    public void testInsert_32_oe() {

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
        assertEquals("falsetruebarbaz", sb.toString());
    }

    @Test
    public void testInsert_33_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_34_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, '!'), "insert(-1, char) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_35_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, '!'), "insert(7, char) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_36_oe() {

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
        assertEquals("!barbaz", sb.toString());
    }

    @Test
    public void testInsert_37_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_38_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, 0), "insert(-1, int) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_39_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, 0), "insert(7, int) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_40_oe() {

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
        assertEquals("0barbaz", sb.toString());
    }

    @Test
    public void testInsert_41_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_42_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, 1L), "insert(-1, long) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_43_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, 1L), "insert(7, long) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_44_oe() {

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
        assertEquals("1barbaz", sb.toString());
    }

    @Test
    public void testInsert_45_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_46_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, 2.3F), "insert(-1, float) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_47_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, 2.3F), "insert(7, float) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_48_oe() {

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
        assertEquals("2.3barbaz", sb.toString());
    }

    @Test
    public void testInsert_49_oe() {

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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsert_50_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, 4.5D), "insert(-1, double) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_51_oe() {

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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, 4.5D), "insert(7, double) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsert_52_oe() {

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

        // removed other assertion

        sb.insert(0, 4.5D);
        assertEquals("4.5barbaz", sb.toString());
    }

    @Test
    public void testInsertWithNullText_1_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.append("barbaz");
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsertWithNullText_2_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.append("barbaz");
        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, FOO), "insert(-1, Object) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsertWithNullText_3_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, FOO), "insert(7, Object) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsertWithNullText_4_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        assertEquals("nullbarbaz", sb.toString());
    }

    @Test
    public void testInsertWithNullText_5_oe() {
        final StrBuilder sb = new StrBuilder();
        sb.setNullText("null");
        sb.append("barbaz");
        // removed other assertion

        // removed other assertion

        // removed other assertion

        sb.insert(0, (Object) null);
        // removed other assertion

        sb.insert(0, FOO);
        assertEquals("foonullbarbaz", sb.toString());
    }

    @Test
    public void testInsertWithNullText_6_oe() {
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
        assertEquals("barbaz", sb.toString());
    }

    @Test
    public void testInsertWithNullText_7_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(-1, "foo"), "insert(-1, String) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsertWithNullText_8_oe() {
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

        assertThrows( IndexOutOfBoundsException.class, () -> sb.insert(7, "foo"), "insert(7, String) expected StringIndexOutOfBoundsException");
    }

    @Test
    public void testInsertWithNullText_9_oe() {
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

        // removed other assertion

        sb.insert(0, (String) null);
        assertEquals("nullbarbaz", sb.toString());
    }

    @Test
    public void testInsertWithNullText_10_oe() {
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

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        assertEquals("foonullbarbaz", sb.toString());
    }

    @Test
    public void testInsertWithNullText_11_oe() {
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

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.insert(0, (char[]) null);
        assertEquals("nullfoonullbarbaz", sb.toString());
    }

    @Test
    public void testInsertWithNullText_12_oe() {
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

        // removed other assertion

        sb.insert(0, (String) null);
        // removed other assertion

        sb.insert(0, "foo");
        // removed other assertion

        sb.insert(0, (char[]) null);
        // removed other assertion

        sb.insert(0, null, 0, 0);
        assertEquals("nullnullfoonullbarbaz", sb.toString());
    }

}
