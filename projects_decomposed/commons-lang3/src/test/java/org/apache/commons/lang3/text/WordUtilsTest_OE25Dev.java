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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for WordUtils class.
 */
@Deprecated
public class WordUtilsTest_OE25Dev {

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    @Test
    public void testLANG1292() {
        // Prior to fix, this was throwing StringIndexOutOfBoundsException
        WordUtils.wrap("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 70);
    }

    @Test
    public void testLANG1397() {
        // Prior to fix, this was throwing StringIndexOutOfBoundsException
        WordUtils.wrap("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "
            + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "
            + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Integer.MAX_VALUE);
    }

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new WordUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        final Constructor<?>[] cons = WordUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        final Constructor<?>[] cons = WordUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        final Constructor<?>[] cons = WordUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(WordUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        final Constructor<?>[] cons = WordUtils.class.getDeclaredConstructors();
        assertFalse(Modifier.isFinal(WordUtils.class.getModifiers()));
    }

    @Test
    public void testWrap_StringInt_1_oe() {
        assertNull(WordUtils.wrap(null, 20));
    }

    @Test
    public void testWrap_StringInt_2_oe() {
        assertNull(WordUtils.wrap(null, -1));
    }

    @Test
    public void testWrap_StringInt_3_oe() {

        assertEquals("", WordUtils.wrap("", 20));
    }

    @Test
    public void testWrap_StringInt_4_oe() {

        assertEquals("", WordUtils.wrap("", -1));
    }

    @Test
    public void testWrap_StringInt_5_oe() {


        final String systemNewLine = System.lineSeparator();
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of" + systemNewLine + "text that is going"
            + systemNewLine + "to be wrapped after" + systemNewLine + "20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20));
    }

    @Test
    public void testWrap_StringInt_6_oe() {


        final String systemNewLine = System.lineSeparator();
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of" + systemNewLine + "text that is going"
            + systemNewLine + "to be wrapped after" + systemNewLine + "20 columns.";

        input = "Click here to jump to the commons website - https://commons.apache.org";
        expected = "Click here to jump" + systemNewLine + "to the commons" + systemNewLine
            + "website -" + systemNewLine + "https://commons.apache.org";
        assertEquals(expected, WordUtils.wrap(input, 20));
    }

    @Test
    public void testWrap_StringInt_7_oe() {


        final String systemNewLine = System.lineSeparator();
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of" + systemNewLine + "text that is going"
            + systemNewLine + "to be wrapped after" + systemNewLine + "20 columns.";

        input = "Click here to jump to the commons website - https://commons.apache.org";
        expected = "Click here to jump" + systemNewLine + "to the commons" + systemNewLine
            + "website -" + systemNewLine + "https://commons.apache.org";

        input = "Click here, https://commons.apache.org, to jump to the commons website";
        expected = "Click here," + systemNewLine + "https://commons.apache.org," + systemNewLine
            + "to jump to the" + systemNewLine + "commons website";
        assertEquals(expected, WordUtils.wrap(input, 20));
    }

    @Test
    public void testWrap_StringInt_8_oe() {


        final String systemNewLine = System.lineSeparator();
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of" + systemNewLine + "text that is going"
            + systemNewLine + "to be wrapped after" + systemNewLine + "20 columns.";

        input = "Click here to jump to the commons website - https://commons.apache.org";
        expected = "Click here to jump" + systemNewLine + "to the commons" + systemNewLine
            + "website -" + systemNewLine + "https://commons.apache.org";

        input = "Click here, https://commons.apache.org, to jump to the commons website";
        expected = "Click here," + systemNewLine + "https://commons.apache.org," + systemNewLine
            + "to jump to the" + systemNewLine + "commons website";

        input = "word1             word2                        word3";
        expected = "word1  " + systemNewLine + "word2  " + systemNewLine + "word3";
        assertEquals(expected, WordUtils.wrap(input, 7));
    }

    @Test
    public void testWrap_StringIntStringBoolean_1_oe() {
        assertNull(WordUtils.wrap(null, 20, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_2_oe() {
        assertNull(WordUtils.wrap(null, 20, "\n", true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_3_oe() {
        assertNull(WordUtils.wrap(null, 20, null, true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_4_oe() {
        assertNull(WordUtils.wrap(null, 20, null, false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_5_oe() {
        assertNull(WordUtils.wrap(null, -1, null, true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_6_oe() {
        assertNull(WordUtils.wrap(null, -1, null, false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_7_oe() {

        assertEquals("", WordUtils.wrap("", 20, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_8_oe() {

        assertEquals("", WordUtils.wrap("", 20, "\n", true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_9_oe() {

        assertEquals("", WordUtils.wrap("", 20, null, false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_10_oe() {

        assertEquals("", WordUtils.wrap("", 20, null, true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_11_oe() {

        assertEquals("", WordUtils.wrap("", -1, null, false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_12_oe() {

        assertEquals("", WordUtils.wrap("", -1, null, true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_13_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_14_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_15_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20, "<br />", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_16_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20, "<br />", true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_17_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        assertEquals(expected, WordUtils.wrap(input, 6, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_18_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";
        assertEquals(expected, WordUtils.wrap(input, 2, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_19_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";
        assertEquals(expected, WordUtils.wrap(input, -1, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_20_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20, null, false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_21_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20, null, true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_22_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";

        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_23_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";

        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_24_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";

        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";

        input = "Here is\tone line of text that is going to be wrapped after 20 columns.";
        expected = "Here is\tone line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_25_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";

        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";

        input = "Here is\tone line of text that is going to be wrapped after 20 columns.";
        expected = "Here is\tone line of\ntext that is going\nto be wrapped after\n20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_26_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";

        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";

        input = "Here is\tone line of text that is going to be wrapped after 20 columns.";
        expected = "Here is\tone line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of\ttext that is going to be wrapped after 20 columns.";
        expected = "Here is one line\nof\ttext that is\ngoing to be wrapped\nafter 20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_27_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";

        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";

        input = "Here is\tone line of text that is going to be wrapped after 20 columns.";
        expected = "Here is\tone line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of\ttext that is going to be wrapped after 20 columns.";
        expected = "Here is one line\nof\ttext that is\ngoing to be wrapped\nafter 20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_28_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";

        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";

        input = "Here is\tone line of text that is going to be wrapped after 20 columns.";
        expected = "Here is\tone line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of\ttext that is going to be wrapped after 20 columns.";
        expected = "Here is one line\nof\ttext that is\ngoing to be wrapped\nafter 20 columns.";

        input = "Click here to jump to the commons website - https://commons.apache.org";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttps://commons.apache.org";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_29_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";

        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";

        input = "Here is\tone line of text that is going to be wrapped after 20 columns.";
        expected = "Here is\tone line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of\ttext that is going to be wrapped after 20 columns.";
        expected = "Here is one line\nof\ttext that is\ngoing to be wrapped\nafter 20 columns.";

        input = "Click here to jump to the commons website - https://commons.apache.org";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttps://commons.apache.org";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttps://commons.apac\nhe.org";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", true));
    }

    @Test
    public void testWrap_StringIntStringBoolean_30_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";

        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";

        input = "Here is\tone line of text that is going to be wrapped after 20 columns.";
        expected = "Here is\tone line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of\ttext that is going to be wrapped after 20 columns.";
        expected = "Here is one line\nof\ttext that is\ngoing to be wrapped\nafter 20 columns.";

        input = "Click here to jump to the commons website - https://commons.apache.org";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttps://commons.apache.org";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttps://commons.apac\nhe.org";

        input = "Click here, https://commons.apache.org, to jump to the commons website";
        expected = "Click here,\nhttps://commons.apache.org,\nto jump to the\ncommons website";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", false));
    }

    @Test
    public void testWrap_StringIntStringBoolean_31_oe() {


        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of<br />text that is going<br />to be wrapped after<br />20 columns.";

        input = "Here is one line";
        expected = "Here\nis one\nline";
        expected = "Here\nis\none\nline";

        final String systemNewLine = System.lineSeparator();
        input = "Here is one line of text that is going to be wrapped after 20 columns.";
        expected = "Here is one line of" + systemNewLine + "text that is going" + systemNewLine
            + "to be wrapped after" + systemNewLine + "20 columns.";

        input = " Here:  is  one  line  of  text  that  is  going  to  be  wrapped  after  20  columns.";
        expected = "Here:  is  one  line\nof  text  that  is \ngoing  to  be \nwrapped  after  20 \ncolumns.";

        input = "Here is\tone line of text that is going to be wrapped after 20 columns.";
        expected = "Here is\tone line of\ntext that is going\nto be wrapped after\n20 columns.";

        input = "Here is one line of\ttext that is going to be wrapped after 20 columns.";
        expected = "Here is one line\nof\ttext that is\ngoing to be wrapped\nafter 20 columns.";

        input = "Click here to jump to the commons website - https://commons.apache.org";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttps://commons.apache.org";
        expected = "Click here to jump\nto the commons\nwebsite -\nhttps://commons.apac\nhe.org";

        input = "Click here, https://commons.apache.org, to jump to the commons website";
        expected = "Click here,\nhttps://commons.apache.org,\nto jump to the\ncommons website";
        expected = "Click here,\nhttps://commons.apac\nhe.org, to jump to\nthe commons website";
        assertEquals(expected, WordUtils.wrap(input, 20, "\n", true));
    }

    @Test
    public void testWrap_StringIntStringBooleanString_1_oe() {

        String input = "flammable/inflammable";
        String expected = "flammable/inflammable";
        assertEquals(expected, WordUtils.wrap(input, 30, "\n", false, "/"));
    }

    @Test
    public void testWrap_StringIntStringBooleanString_2_oe() {

        String input = "flammable/inflammable";
        String expected = "flammable/inflammable";

        expected = "flammable\ninflammable";
        assertEquals(expected, WordUtils.wrap(input, 2, "\n", false, "/"));
    }

    @Test
    public void testWrap_StringIntStringBooleanString_3_oe() {

        String input = "flammable/inflammable";
        String expected = "flammable/inflammable";

        expected = "flammable\ninflammable";

        expected = "flammable\ninflammab\nle";
        assertEquals(expected, WordUtils.wrap(input, 9, "\n", true, "/"));
    }

    @Test
    public void testWrap_StringIntStringBooleanString_4_oe() {

        String input = "flammable/inflammable";
        String expected = "flammable/inflammable";

        expected = "flammable\ninflammable";

        expected = "flammable\ninflammab\nle";

        expected = "flammable\ninflammable";
        assertEquals(expected, WordUtils.wrap(input, 15, "\n", true, "/"));
    }

    @Test
    public void testWrap_StringIntStringBooleanString_5_oe() {

        String input = "flammable/inflammable";
        String expected = "flammable/inflammable";

        expected = "flammable\ninflammable";

        expected = "flammable\ninflammab\nle";

        expected = "flammable\ninflammable";

        input = "flammableinflammable";
        expected = "flammableinflam\nmable";
        assertEquals(expected, WordUtils.wrap(input, 15, "\n", true, "/"));
    }

    @Test
    public void testCapitalize_String_1_oe() {
        assertNull(WordUtils.capitalize(null));
    }

    @Test
    public void testCapitalize_String_2_oe() {
        assertEquals("", WordUtils.capitalize(""));
    }

    @Test
    public void testCapitalize_String_3_oe() {
        assertEquals("  ", WordUtils.capitalize("  "));
    }

    @Test
    public void testCapitalize_String_4_oe() {

        assertEquals("I", WordUtils.capitalize("I") );
    }

    @Test
    public void testCapitalize_String_5_oe() {

        assertEquals("I", WordUtils.capitalize("i") );
    }

    @Test
    public void testCapitalize_String_6_oe() {

        assertEquals("I Am Here 123", WordUtils.capitalize("i am here 123") );
    }

    @Test
    public void testCapitalize_String_7_oe() {

        assertEquals("I Am Here 123", WordUtils.capitalize("I Am Here 123") );
    }

    @Test
    public void testCapitalize_String_8_oe() {

        assertEquals("I Am HERE 123", WordUtils.capitalize("i am HERE 123") );
    }

    @Test
    public void testCapitalize_String_9_oe() {

        assertEquals("I AM HERE 123", WordUtils.capitalize("I AM HERE 123") );
    }

    @Test
    public void testCapitalizeWithDelimiters_String_1_oe() {
        assertNull(WordUtils.capitalize(null, null));
    }

    @Test
    public void testCapitalizeWithDelimiters_String_2_oe() {
        assertEquals("", WordUtils.capitalize(""));
    }

    @Test
    public void testCapitalizeWithDelimiters_String_3_oe() {
        assertEquals("  ", WordUtils.capitalize("  "));
    }

    @Test
    public void testCapitalizeWithDelimiters_String_4_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I", WordUtils.capitalize("I", chars) );
    }

    @Test
    public void testCapitalizeWithDelimiters_String_5_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I", WordUtils.capitalize("i", chars) );
    }

    @Test
    public void testCapitalizeWithDelimiters_String_6_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I-Am Here+123", WordUtils.capitalize("i-am here+123", chars) );
    }

    @Test
    public void testCapitalizeWithDelimiters_String_7_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I Am+Here-123", WordUtils.capitalize("I Am+Here-123", chars) );
    }

    @Test
    public void testCapitalizeWithDelimiters_String_8_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I+Am-HERE 123", WordUtils.capitalize("i+am-HERE 123", chars) );
    }

    @Test
    public void testCapitalizeWithDelimiters_String_9_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I-AM HERE+123", WordUtils.capitalize("I-AM HERE+123", chars) );
    }

    @Test
    public void testCapitalizeWithDelimiters_String_10_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        chars = new char[] {'.'};
        assertEquals("I aM.Fine", WordUtils.capitalize("i aM.fine", chars) );
    }

    @Test
    public void testCapitalizeWithDelimiters_String_11_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        chars = new char[] {'.'};
        assertEquals("I Am.fine", WordUtils.capitalize("i am.fine", null) );
    }

    @Test
    public void testCapitalizeFully_String_1_oe() {
        assertNull(WordUtils.capitalizeFully(null));
    }

    @Test
    public void testCapitalizeFully_String_2_oe() {
        assertEquals("", WordUtils.capitalizeFully(""));
    }

    @Test
    public void testCapitalizeFully_String_3_oe() {
        assertEquals("  ", WordUtils.capitalizeFully("  "));
    }

    @Test
    public void testCapitalizeFully_String_4_oe() {

        assertEquals("I", WordUtils.capitalizeFully("I") );
    }

    @Test
    public void testCapitalizeFully_String_5_oe() {

        assertEquals("I", WordUtils.capitalizeFully("i") );
    }

    @Test
    public void testCapitalizeFully_String_6_oe() {

        assertEquals("I Am Here 123", WordUtils.capitalizeFully("i am here 123") );
    }

    @Test
    public void testCapitalizeFully_String_7_oe() {

        assertEquals("I Am Here 123", WordUtils.capitalizeFully("I Am Here 123") );
    }

    @Test
    public void testCapitalizeFully_String_8_oe() {

        assertEquals("I Am Here 123", WordUtils.capitalizeFully("i am HERE 123") );
    }

    @Test
    public void testCapitalizeFully_String_9_oe() {

        assertEquals("I Am Here 123", WordUtils.capitalizeFully("I AM HERE 123") );
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_1_oe() {
        assertNull(WordUtils.capitalizeFully(null, null));
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_2_oe() {
        assertEquals("", WordUtils.capitalizeFully(""));
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_3_oe() {
        assertEquals("  ", WordUtils.capitalizeFully("  "));
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_4_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I", WordUtils.capitalizeFully("I", chars) );
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_5_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I", WordUtils.capitalizeFully("i", chars) );
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_6_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I-Am Here+123", WordUtils.capitalizeFully("i-am here+123", chars) );
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_7_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I Am+Here-123", WordUtils.capitalizeFully("I Am+Here-123", chars) );
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_8_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I+Am-Here 123", WordUtils.capitalizeFully("i+am-HERE 123", chars) );
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_9_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("I-Am Here+123", WordUtils.capitalizeFully("I-AM HERE+123", chars) );
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_10_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        chars = new char[] {'.'};
        assertEquals("I am.Fine", WordUtils.capitalizeFully("i aM.fine", chars) );
    }

    @Test
    public void testCapitalizeFullyWithDelimiters_String_11_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        chars = new char[] {'.'};
        assertEquals("I Am.fine", WordUtils.capitalizeFully("i am.fine", null) );
    }

    @Test
    public void testContainsAllWords_StringString_1_oe() {
        assertFalse(WordUtils.containsAllWords(null, (String) null));
    }

    @Test
    public void testContainsAllWords_StringString_2_oe() {
        assertFalse(WordUtils.containsAllWords(null, ""));
    }

    @Test
    public void testContainsAllWords_StringString_3_oe() {
        assertFalse(WordUtils.containsAllWords(null, "ab"));
    }

    @Test
    public void testContainsAllWords_StringString_4_oe() {

        assertFalse(WordUtils.containsAllWords("", (String) null));
    }

    @Test
    public void testContainsAllWords_StringString_5_oe() {

        assertFalse(WordUtils.containsAllWords("", ""));
    }

    @Test
    public void testContainsAllWords_StringString_6_oe() {

        assertFalse(WordUtils.containsAllWords("", "ab"));
    }

    @Test
    public void testContainsAllWords_StringString_7_oe() {


        assertFalse(WordUtils.containsAllWords("foo", (String) null));
    }

    @Test
    public void testContainsAllWords_StringString_8_oe() {


        assertFalse(WordUtils.containsAllWords("bar", ""));
    }

    @Test
    public void testContainsAllWords_StringString_9_oe() {


        assertFalse(WordUtils.containsAllWords("zzabyycdxx", "by"));
    }

    @Test
    public void testContainsAllWords_StringString_10_oe() {


        assertTrue(WordUtils.containsAllWords("lorem ipsum dolor sit amet", "ipsum", "lorem", "dolor"));
    }

    @Test
    public void testContainsAllWords_StringString_11_oe() {


        assertFalse(WordUtils.containsAllWords("lorem ipsum dolor sit amet", "ipsum", null, "lorem", "dolor"));
    }

    @Test
    public void testContainsAllWords_StringString_12_oe() {


        assertFalse(WordUtils.containsAllWords("lorem ipsum null dolor sit amet", "ipsum", null, "lorem", "dolor"));
    }

    @Test
    public void testContainsAllWords_StringString_13_oe() {


        assertFalse(WordUtils.containsAllWords("ab", "b"));
    }

    @Test
    public void testContainsAllWords_StringString_14_oe() {


        assertFalse(WordUtils.containsAllWords("ab", "z"));
    }

    @Test
    public void testUncapitalize_String_1_oe() {
        assertNull(WordUtils.uncapitalize(null));
    }

    @Test
    public void testUncapitalize_String_2_oe() {
        assertEquals("", WordUtils.uncapitalize(""));
    }

    @Test
    public void testUncapitalize_String_3_oe() {
        assertEquals("  ", WordUtils.uncapitalize("  "));
    }

    @Test
    public void testUncapitalize_String_4_oe() {

        assertEquals("i", WordUtils.uncapitalize("I") );
    }

    @Test
    public void testUncapitalize_String_5_oe() {

        assertEquals("i", WordUtils.uncapitalize("i") );
    }

    @Test
    public void testUncapitalize_String_6_oe() {

        assertEquals("i am here 123", WordUtils.uncapitalize("i am here 123") );
    }

    @Test
    public void testUncapitalize_String_7_oe() {

        assertEquals("i am here 123", WordUtils.uncapitalize("I Am Here 123") );
    }

    @Test
    public void testUncapitalize_String_8_oe() {

        assertEquals("i am hERE 123", WordUtils.uncapitalize("i am HERE 123") );
    }

    @Test
    public void testUncapitalize_String_9_oe() {

        assertEquals("i aM hERE 123", WordUtils.uncapitalize("I AM HERE 123") );
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_1_oe() {
        assertNull(WordUtils.uncapitalize(null, null));
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_2_oe() {
        assertEquals("", WordUtils.uncapitalize(""));
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_3_oe() {
        assertEquals("  ", WordUtils.uncapitalize("  "));
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_4_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("i", WordUtils.uncapitalize("I", chars) );
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_5_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("i", WordUtils.uncapitalize("i", chars) );
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_6_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("i am-here+123", WordUtils.uncapitalize("i am-here+123", chars) );
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_7_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("i+am here-123", WordUtils.uncapitalize("I+Am Here-123", chars) );
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_8_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("i-am+hERE 123", WordUtils.uncapitalize("i-am+HERE 123", chars) );
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_9_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        assertEquals("i aM-hERE+123", WordUtils.uncapitalize("I AM-HERE+123", chars) );
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_10_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        chars = new char[] {'.'};
        assertEquals("i AM.fINE", WordUtils.uncapitalize("I AM.FINE", chars) );
    }

    @Test
    public void testUncapitalizeWithDelimiters_String_11_oe() {

        char[] chars = new char[] { '-', '+', ' ', '@' };
        chars = new char[] {'.'};
        assertEquals("i aM.FINE", WordUtils.uncapitalize("I AM.FINE", null) );
    }

    @Test
    public void testInitials_String_1_oe() {
        assertNull(WordUtils.initials(null));
    }

    @Test
    public void testInitials_String_2_oe() {
        assertEquals("", WordUtils.initials(""));
    }

    @Test
    public void testInitials_String_3_oe() {
        assertEquals("", WordUtils.initials("  "));
    }

    @Test
    public void testInitials_String_4_oe() {

        assertEquals("I", WordUtils.initials("I"));
    }

    @Test
    public void testInitials_String_5_oe() {

        assertEquals("i", WordUtils.initials("i"));
    }

    @Test
    public void testInitials_String_6_oe() {

        assertEquals("BJL", WordUtils.initials("Ben John Lee"));
    }

    @Test
    public void testInitials_String_7_oe() {

        assertEquals("BJL", WordUtils.initials("   Ben \n   John\tLee\t"));
    }

    @Test
    public void testInitials_String_8_oe() {

        assertEquals("BJ", WordUtils.initials("Ben J.Lee"));
    }

    @Test
    public void testInitials_String_9_oe() {

        assertEquals("BJ.L", WordUtils.initials(" Ben   John  . Lee"));
    }

    @Test
    public void testInitials_String_10_oe() {

        assertEquals("iah1", WordUtils.initials("i am here 123"));
    }

    @Test
    public void testInitials_String_charArray_1_oe() {
        char[] array = null;
        assertNull(WordUtils.initials(null, array));
    }

    @Test
    public void testInitials_String_charArray_2_oe() {
        char[] array = null;
        assertEquals("", WordUtils.initials("", array));
    }

    @Test
    public void testInitials_String_charArray_3_oe() {
        char[] array = null;
        assertEquals("", WordUtils.initials("  ", array));
    }

    @Test
    public void testInitials_String_charArray_4_oe() {
        char[] array = null;
        assertEquals("I", WordUtils.initials("I", array));
    }

    @Test
    public void testInitials_String_charArray_5_oe() {
        char[] array = null;
        assertEquals("i", WordUtils.initials("i", array));
    }

    @Test
    public void testInitials_String_charArray_6_oe() {
        char[] array = null;
        assertEquals("S", WordUtils.initials("SJC", array));
    }

    @Test
    public void testInitials_String_charArray_7_oe() {
        char[] array = null;
        assertEquals("BJL", WordUtils.initials("Ben John Lee", array));
    }

    @Test
    public void testInitials_String_charArray_8_oe() {
        char[] array = null;
        assertEquals("BJL", WordUtils.initials("   Ben \n   John\tLee\t", array));
    }

    @Test
    public void testInitials_String_charArray_9_oe() {
        char[] array = null;
        assertEquals("BJ", WordUtils.initials("Ben J.Lee", array));
    }

    @Test
    public void testInitials_String_charArray_10_oe() {
        char[] array = null;
        assertEquals("BJ.L", WordUtils.initials(" Ben   John  . Lee", array));
    }

    @Test
    public void testInitials_String_charArray_11_oe() {
        char[] array = null;
        assertEquals("KO", WordUtils.initials("Kay O'Murphy", array));
    }

    @Test
    public void testInitials_String_charArray_12_oe() {
        char[] array = null;
        assertEquals("iah1", WordUtils.initials("i am here 123", array));
    }

    @Test
    public void testInitials_String_charArray_13_oe() {
        char[] array = null;

        array = new char[0];
        assertNull(WordUtils.initials(null, array));
    }

    @Test
    public void testInitials_String_charArray_14_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials("", array));
    }

    @Test
    public void testInitials_String_charArray_15_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials("  ", array));
    }

    @Test
    public void testInitials_String_charArray_16_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials("I", array));
    }

    @Test
    public void testInitials_String_charArray_17_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials("i", array));
    }

    @Test
    public void testInitials_String_charArray_18_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials("SJC", array));
    }

    @Test
    public void testInitials_String_charArray_19_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials("Ben John Lee", array));
    }

    @Test
    public void testInitials_String_charArray_20_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials("   Ben \n   John\tLee\t", array));
    }

    @Test
    public void testInitials_String_charArray_21_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials("Ben J.Lee", array));
    }

    @Test
    public void testInitials_String_charArray_22_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials(" Ben   John  . Lee", array));
    }

    @Test
    public void testInitials_String_charArray_23_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials("Kay O'Murphy", array));
    }

    @Test
    public void testInitials_String_charArray_24_oe() {
        char[] array = null;

        array = new char[0];
        assertEquals("", WordUtils.initials("i am here 123", array));
    }

    @Test
    public void testInitials_String_charArray_25_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertNull(WordUtils.initials(null, array));
    }

    @Test
    public void testInitials_String_charArray_26_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("", WordUtils.initials("", array));
    }

    @Test
    public void testInitials_String_charArray_27_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("", WordUtils.initials("  ", array));
    }

    @Test
    public void testInitials_String_charArray_28_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("I", WordUtils.initials("I", array));
    }

    @Test
    public void testInitials_String_charArray_29_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("i", WordUtils.initials("i", array));
    }

    @Test
    public void testInitials_String_charArray_30_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("S", WordUtils.initials("SJC", array));
    }

    @Test
    public void testInitials_String_charArray_31_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("BJL", WordUtils.initials("Ben John Lee", array));
    }

    @Test
    public void testInitials_String_charArray_32_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("BJ", WordUtils.initials("Ben J.Lee", array));
    }

    @Test
    public void testInitials_String_charArray_33_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("B\nJ", WordUtils.initials("   Ben \n   John\tLee\t", array));
    }

    @Test
    public void testInitials_String_charArray_34_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("BJ.L", WordUtils.initials(" Ben   John  . Lee", array));
    }

    @Test
    public void testInitials_String_charArray_35_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("KO", WordUtils.initials("Kay O'Murphy", array));
    }

    @Test
    public void testInitials_String_charArray_36_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();
        assertEquals("iah1", WordUtils.initials("i am here 123", array));
    }

    @Test
    public void testInitials_String_charArray_37_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertNull(WordUtils.initials(null, array));
    }

    @Test
    public void testInitials_String_charArray_38_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertEquals("", WordUtils.initials("", array));
    }

    @Test
    public void testInitials_String_charArray_39_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertEquals("", WordUtils.initials("  ", array));
    }

    @Test
    public void testInitials_String_charArray_40_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertEquals("I", WordUtils.initials("I", array));
    }

    @Test
    public void testInitials_String_charArray_41_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertEquals("i", WordUtils.initials("i", array));
    }

    @Test
    public void testInitials_String_charArray_42_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertEquals("S", WordUtils.initials("SJC", array));
    }

    @Test
    public void testInitials_String_charArray_43_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertEquals("BJL", WordUtils.initials("Ben John Lee", array));
    }

    @Test
    public void testInitials_String_charArray_44_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertEquals("BJL", WordUtils.initials("Ben J.Lee", array));
    }

    @Test
    public void testInitials_String_charArray_45_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertEquals("BJL", WordUtils.initials(" Ben   John  . Lee", array));
    }

    @Test
    public void testInitials_String_charArray_46_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertEquals("KO", WordUtils.initials("Kay O'Murphy", array));
    }

    @Test
    public void testInitials_String_charArray_47_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();
        assertEquals("iah1", WordUtils.initials("i am here 123", array));
    }

    @Test
    public void testInitials_String_charArray_48_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertNull(WordUtils.initials(null, array));
    }

    @Test
    public void testInitials_String_charArray_49_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertEquals("", WordUtils.initials("", array));
    }

    @Test
    public void testInitials_String_charArray_50_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertEquals("", WordUtils.initials("  ", array));
    }

    @Test
    public void testInitials_String_charArray_51_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertEquals("I", WordUtils.initials("I", array));
    }

    @Test
    public void testInitials_String_charArray_52_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertEquals("i", WordUtils.initials("i", array));
    }

    @Test
    public void testInitials_String_charArray_53_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertEquals("S", WordUtils.initials("SJC", array));
    }

    @Test
    public void testInitials_String_charArray_54_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertEquals("BJL", WordUtils.initials("Ben John Lee", array));
    }

    @Test
    public void testInitials_String_charArray_55_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertEquals("BJL", WordUtils.initials("Ben J.Lee", array));
    }

    @Test
    public void testInitials_String_charArray_56_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertEquals("BJL", WordUtils.initials(" Ben   John  . Lee", array));
    }

    @Test
    public void testInitials_String_charArray_57_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertEquals("KOM", WordUtils.initials("Kay O'Murphy", array));
    }

    @Test
    public void testInitials_String_charArray_58_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();
        assertEquals("iah1", WordUtils.initials("i am here 123", array));
    }

    @Test
    public void testInitials_String_charArray_59_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertNull(WordUtils.initials(null, array));
    }

    @Test
    public void testInitials_String_charArray_60_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertEquals("", WordUtils.initials("", array));
    }

    @Test
    public void testInitials_String_charArray_61_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertEquals(" ", WordUtils.initials("  ", array));
    }

    @Test
    public void testInitials_String_charArray_62_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertEquals("", WordUtils.initials("I", array));
    }

    @Test
    public void testInitials_String_charArray_63_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertEquals("i", WordUtils.initials("i", array));
    }

    @Test
    public void testInitials_String_charArray_64_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertEquals("C", WordUtils.initials("SJC", array));
    }

    @Test
    public void testInitials_String_charArray_65_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertEquals("Bh", WordUtils.initials("Ben John Lee", array));
    }

    @Test
    public void testInitials_String_charArray_66_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertEquals("B.", WordUtils.initials("Ben J.Lee", array));
    }

    @Test
    public void testInitials_String_charArray_67_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertEquals(" h", WordUtils.initials(" Ben   John  . Lee", array));
    }

    @Test
    public void testInitials_String_charArray_68_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertEquals("K", WordUtils.initials("Kay O'Murphy", array));
    }

    @Test
    public void testInitials_String_charArray_69_oe() {
        char[] array = null;

        array = new char[0];

        array = " ".toCharArray();

        array = " .".toCharArray();

        array = " .'".toCharArray();

        array = "SIJo1".toCharArray();
        assertEquals("i2", WordUtils.initials("i am here 123", array));
    }

    @Test
    public void testSwapCase_String_1_oe() {
        assertNull(WordUtils.swapCase(null));
    }

    @Test
    public void testSwapCase_String_2_oe() {
        assertEquals("", WordUtils.swapCase(""));
    }

    @Test
    public void testSwapCase_String_3_oe() {
        assertEquals("  ", WordUtils.swapCase("  "));
    }

    @Test
    public void testSwapCase_String_4_oe() {

        assertEquals("i", WordUtils.swapCase("I") );
    }

    @Test
    public void testSwapCase_String_5_oe() {

        assertEquals("I", WordUtils.swapCase("i") );
    }

    @Test
    public void testSwapCase_String_6_oe() {

        assertEquals("I AM HERE 123", WordUtils.swapCase("i am here 123") );
    }

    @Test
    public void testSwapCase_String_7_oe() {

        assertEquals("i aM hERE 123", WordUtils.swapCase("I Am Here 123") );
    }

    @Test
    public void testSwapCase_String_8_oe() {

        assertEquals("I AM here 123", WordUtils.swapCase("i am HERE 123") );
    }

    @Test
    public void testSwapCase_String_9_oe() {

        assertEquals("i am here 123", WordUtils.swapCase("I AM HERE 123") );
    }

    @Test
    public void testSwapCase_String_10_oe() {


        final String test = "This String contains a TitleCase character: \u01C8";
        final String expect = "tHIS sTRING CONTAINS A tITLEcASE CHARACTER: \u01C9";
        assertEquals(expect, WordUtils.swapCase(test));
    }

}
