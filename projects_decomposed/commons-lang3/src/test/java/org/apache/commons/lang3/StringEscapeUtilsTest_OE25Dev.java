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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.NumericEntityEscaper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for {@link StringEscapeUtils}.
 */
@Deprecated
public class StringEscapeUtilsTest_OE25Dev {
    private static final String FOO = "foo";

    /**
     * Tests https://issues.apache.org/jira/browse/LANG-421
     */

    private void assertEscapeJava(final String escaped, final String original) throws IOException {
        assertEscapeJava(null, escaped, original);
    }

    private void assertEscapeJava(String message, final String expected, final String original) throws IOException {
        final String converted = StringEscapeUtils.escapeJava(original);
        message = "escapeJava(String) failed" + (message == null ? "" : (": " + message));
        assertEquals(expected, converted, message);

        final StringWriter writer = new StringWriter();
        StringEscapeUtils.ESCAPE_JAVA.translate(original, writer);
        assertEquals(expected, writer.toString());
    }

    private void assertUnescapeJava(final String unescaped, final String original) throws IOException {
        assertUnescapeJava(null, unescaped, original);
    }

    private void assertUnescapeJava(final String message, final String unescaped, final String original) throws IOException {
        final String expected = unescaped;
        final String actual = StringEscapeUtils.unescapeJava(original);

        assertEquals(expected,actual,"unescape(String)failed" +(message == null ? "" :(": " + message))+ ": expected '" + StringEscapeUtils.escapeJava(expected)+ "' actual '" + StringEscapeUtils.escapeJava(actual)+ "'");

        final StringWriter writer = new StringWriter();
        StringEscapeUtils.UNESCAPE_JAVA.translate(original, writer);
        assertEquals(unescaped, writer.toString());

    }


    // HTML and XML
    //--------------------------------------------------------------

    private static final String[][] HTML_ESCAPES = {
        {"no escaping", "plain text", "plain text"},
        {"no escaping", "plain text", "plain text"},
        {"empty string", "", ""},
        {"null", null, null},
        {"ampersand", "bread &amp; butter", "bread & butter"},
        {"quotes", "&quot;bread&quot; &amp; butter", "\"bread\" & butter"},
        {"final character only", "greater than &gt;", "greater than >"},
        {"first character only", "&lt; less than", "< less than"},
        {"apostrophe", "Huntington's chorea", "Huntington's chorea"},
        {"languages", "English,Fran&ccedil;ais,\u65E5\u672C\u8A9E (nihongo)", "English,Fran\u00E7ais,\u65E5\u672C\u8A9E (nihongo)"},
        {"8-bit ascii shouldn't number-escape", "\u0080\u009F", "\u0080\u009F"},
    };

    /**
     * Tests Supplementary characters.
     * <p>
     * From http://www.w3.org/International/questions/qa-escapes
     * </p>
     * <blockquote>
     * Supplementary characters are those Unicode characters that have code points higher than the characters in
     * the Basic Multilingual Plane (BMP). In UTF-16 a supplementary character is encoded using two 16-bit surrogate code points from the
     * BMP. Because of this, some people think that supplementary characters need to be represented using two escapes, but this is incorrect
     * - you must use the single, code point value for that character. For example, use &amp;&#35;x233B4&#59; rather than
     * &amp;&#35;xD84C&#59;&amp;&#35;xDFB4&#59;.
     * </blockquote>
     * @see <a href="http://www.w3.org/International/questions/qa-escapes">Using character escapes in markup and CSS</a>
     * @see <a href="https://issues.apache.org/jira/browse/LANG-728">LANG-728</a>
     */

    /**
     * Reverse of the above.
     *
     * @see <a href="https://issues.apache.org/jira/browse/LANG-729">LANG-729</a>
     */

    // Tests issue #38569
    // https://issues.apache.org/bugzilla/show_bug.cgi?id=38569

    @Test
    public void testEscapeCsvWriter() throws Exception {
        checkCsvEscapeWriter("foo.bar",            "foo.bar");
        checkCsvEscapeWriter("\"foo,bar\"",        "foo,bar");
        checkCsvEscapeWriter("\"foo\nbar\"",       "foo\nbar");
        checkCsvEscapeWriter("\"foo\rbar\"",       "foo\rbar");
        checkCsvEscapeWriter("\"foo\"\"bar\"",     "foo\"bar");
        checkCsvEscapeWriter("foo\uD84C\uDFB4bar", "foo\uD84C\uDFB4bar");
        checkCsvEscapeWriter("", null);
        checkCsvEscapeWriter("", "");
    }

    private void checkCsvEscapeWriter(final String expected, final String value) throws IOException {
        final StringWriter writer = new StringWriter();
        StringEscapeUtils.ESCAPE_CSV.translate(value, writer);
        assertEquals(expected, writer.toString());
    }

    @Test
    public void testUnescapeCsvWriter() throws Exception {
        checkCsvUnescapeWriter("foo.bar",            "foo.bar");
        checkCsvUnescapeWriter("foo,bar",            "\"foo,bar\"");
        checkCsvUnescapeWriter("foo\nbar",           "\"foo\nbar\"");
        checkCsvUnescapeWriter("foo\rbar",           "\"foo\rbar\"");
        checkCsvUnescapeWriter("foo\"bar",           "\"foo\"\"bar\"");
        checkCsvUnescapeWriter("foo\uD84C\uDFB4bar", "foo\uD84C\uDFB4bar");
        checkCsvUnescapeWriter("", null);
        checkCsvUnescapeWriter("", "");

        checkCsvUnescapeWriter("\"foo.bar\"",        "\"foo.bar\"");
    }

    private void checkCsvUnescapeWriter(final String expected, final String value) throws IOException {
        final StringWriter writer = new StringWriter();
        StringEscapeUtils.UNESCAPE_CSV.translate(value, writer);
        assertEquals(expected, writer.toString());
    }

    /**
     * Tests // https://issues.apache.org/jira/browse/LANG-480
     */

    /**
     * Tests https://issues.apache.org/jira/browse/LANG-339
     */

    /**
     * Tests https://issues.apache.org/jira/browse/LANG-708
     *
     * @throws IOException
     *             if an I/O error occurs
     */

    /**
     * Tests https://issues.apache.org/jira/browse/LANG-720
     */

    /**
     * Tests https://issues.apache.org/jira/browse/LANG-911
     */

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new StringEscapeUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        // removed other assertion
        final Constructor<?>[] cons = StringEscapeUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        // removed other assertion
        final Constructor<?>[] cons = StringEscapeUtils.class.getDeclaredConstructors();
        // removed other assertion
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        // removed other assertion
        final Constructor<?>[] cons = StringEscapeUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        assertTrue(Modifier.isPublic(StringEscapeUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        // removed other assertion
        final Constructor<?>[] cons = StringEscapeUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Modifier.isFinal(StringEscapeUtils.class.getModifiers()));
    }

    @Test
    public void testEscapeJava_1_oe() throws IOException {
        assertNull(StringEscapeUtils.escapeJava(null));
    }

    @Test
    public void testEscapeJava_2_oe() throws IOException {
        // removed other assertion
        try {
    StringEscapeUtils.ESCAPE_JAVA.translate(null, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testEscapeJava_3_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        try {
    StringEscapeUtils.ESCAPE_JAVA.translate("", null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testEscapeJavaWithSlash_1_oe() {
        final String input = "String with a slash (/) in it";

        final String expected = input;
        final String actual = StringEscapeUtils.escapeJava(input);

        /*
         * In 2.4 StringEscapeUtils.escapeJava(String) escapes '/' characters, which are not a valid character to escape
         * in a Java string.
         */
        assertEquals(expected, actual);
    }

    @Test
    public void testUnescapeJava_1_oe() throws IOException {
        assertNull(StringEscapeUtils.unescapeJava(null));
    }

    @Test
    public void testUnescapeJava_2_oe() throws IOException {
        // removed other assertion
        try {
    StringEscapeUtils.UNESCAPE_JAVA.translate(null, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testUnescapeJava_3_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        try {
    StringEscapeUtils.UNESCAPE_JAVA.translate("", null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testUnescapeJava_4_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    StringEscapeUtils.unescapeJava("\\u02-3");
    fail("RuntimeException");
} catch (RuntimeException e) {
}
    }

    @Test
    public void testEscapeEcmaScript_1_oe() {
        assertNull(StringEscapeUtils.escapeEcmaScript(null));
    }

    @Test
    public void testEscapeEcmaScript_2_oe() throws Exception {
        // removed other assertion
        try {
    StringEscapeUtils.ESCAPE_ECMASCRIPT.translate(null, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testEscapeEcmaScript_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    StringEscapeUtils.ESCAPE_ECMASCRIPT.translate("", null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testEscapeEcmaScript_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("He didn\\'t say, \\\"stop!\\\"", StringEscapeUtils.escapeEcmaScript("He didn't say, \"stop!\""));
    }

    @Test
    public void testEscapeEcmaScript_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("document.getElementById(\\\"test\\\").value = \\'<script>alert(\\'aaa\\');<\\/script>\\';",StringEscapeUtils.escapeEcmaScript("document.getElementById(\"test\").value = '<script>alert('aaa');</script>';"));
    }

    @Test
    public void testUnescapeEcmaScript_1_oe() {
        assertNull(StringEscapeUtils.escapeEcmaScript(null));
    }

    @Test
    public void testUnescapeEcmaScript_2_oe() throws Exception {
        // removed other assertion
        try {
    StringEscapeUtils.UNESCAPE_ECMASCRIPT.translate(null, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testUnescapeEcmaScript_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    StringEscapeUtils.UNESCAPE_ECMASCRIPT.translate("", null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testUnescapeEcmaScript_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("He didn't say, \"stop!\"", StringEscapeUtils.unescapeEcmaScript("He didn\\'t say, \\\"stop!\\\""));
    }

    @Test
    public void testUnescapeEcmaScript_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("document.getElementById(\"test\").value = '<script>alert('aaa');</script>';",StringEscapeUtils.unescapeEcmaScript("document.getElementById(\\\"test\\\").value = \\'<script>alert(\\'aaa\\');<\\/script>\\';"));
    }

    @Test
    public void testEscapeHtml_1_oe() throws IOException {
        for (final String[] element : HTML_ESCAPES) {
            final String message = element[0];
            final String expected = element[1];
            final String original = element[2];
            assertEquals(expected, StringEscapeUtils.escapeHtml4(original), message);
    }
    }

    @Test
    public void testEscapeHtml_2_oe() throws IOException {
        for (final String[] element : HTML_ESCAPES) {
            final String message = element[0];
            final String expected = element[1];
            final String original = element[2];
            // removed other assertion
            final StringWriter sw = new StringWriter();
            StringEscapeUtils.ESCAPE_HTML4.translate(original, sw);
            final String actual = original == null ? null : sw.toString();
            assertEquals(expected, actual, message);
    }
    }

    @Test
    public void testUnescapeHtml4_1_oe() throws IOException {
        for (final String[] element : HTML_ESCAPES) {
            final String message = element[0];
            final String expected = element[2];
            final String original = element[1];
            assertEquals(expected, StringEscapeUtils.unescapeHtml4(original), message);
    }
    }

    @Test
    public void testUnescapeHtml4_2_oe() throws IOException {
        for (final String[] element : HTML_ESCAPES) {
            final String message = element[0];
            final String expected = element[2];
            final String original = element[1];
            // removed other assertion

            final StringWriter sw = new StringWriter();
            StringEscapeUtils.UNESCAPE_HTML4.translate(original, sw);
            final String actual = original == null ? null : sw.toString();
            assertEquals(expected, actual, message);
    }
    }

    @Test
    public void testUnescapeHtml4_3_oe() throws IOException {
        for (final String[] element : HTML_ESCAPES) {
            final String message = element[0];
            final String expected = element[2];
            final String original = element[1];
            // removed other assertion

            final StringWriter sw = new StringWriter();
            StringEscapeUtils.UNESCAPE_HTML4.translate(original, sw);
            final String actual = original == null ? null : sw.toString();
            // removed other assertion
        }
        // \u00E7 is a cedilla (c with wiggle under)
        // note that the test string must be 7-bit-clean (Unicode escaped) or else it will compile incorrectly
        // on some locales
        assertEquals("Fran\u00E7ais", StringEscapeUtils.unescapeHtml4("Fran\u00E7ais"), "funny chars pass through OK");
    }

    @Test
    public void testUnescapeHtml4_4_oe() throws IOException {
        for (final String[] element : HTML_ESCAPES) {
            final String message = element[0];
            final String expected = element[2];
            final String original = element[1];
            // removed other assertion

            final StringWriter sw = new StringWriter();
            StringEscapeUtils.UNESCAPE_HTML4.translate(original, sw);
            final String actual = original == null ? null : sw.toString();
            // removed other assertion
        }
        // \u00E7 is a cedilla (c with wiggle under)
        // note that the test string must be 7-bit-clean (Unicode escaped) or else it will compile incorrectly
        // on some locales
        // removed other assertion

        assertEquals("Hello&;World", StringEscapeUtils.unescapeHtml4("Hello&;World"));
    }

    @Test
    public void testUnescapeHtml4_5_oe() throws IOException {
        for (final String[] element : HTML_ESCAPES) {
            final String message = element[0];
            final String expected = element[2];
            final String original = element[1];
            // removed other assertion

            final StringWriter sw = new StringWriter();
            StringEscapeUtils.UNESCAPE_HTML4.translate(original, sw);
            final String actual = original == null ? null : sw.toString();
            // removed other assertion
        }
        // \u00E7 is a cedilla (c with wiggle under)
        // note that the test string must be 7-bit-clean (Unicode escaped) or else it will compile incorrectly
        // on some locales
        // removed other assertion

        // removed other assertion
        assertEquals("Hello&#;World", StringEscapeUtils.unescapeHtml4("Hello&#;World"));
    }

    @Test
    public void testUnescapeHtml4_6_oe() throws IOException {
        for (final String[] element : HTML_ESCAPES) {
            final String message = element[0];
            final String expected = element[2];
            final String original = element[1];
            // removed other assertion

            final StringWriter sw = new StringWriter();
            StringEscapeUtils.UNESCAPE_HTML4.translate(original, sw);
            final String actual = original == null ? null : sw.toString();
            // removed other assertion
        }
        // \u00E7 is a cedilla (c with wiggle under)
        // note that the test string must be 7-bit-clean (Unicode escaped) or else it will compile incorrectly
        // on some locales
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("Hello&# ;World", StringEscapeUtils.unescapeHtml4("Hello&# ;World"));
    }

    @Test
    public void testUnescapeHtml4_7_oe() throws IOException {
        for (final String[] element : HTML_ESCAPES) {
            final String message = element[0];
            final String expected = element[2];
            final String original = element[1];
            // removed other assertion

            final StringWriter sw = new StringWriter();
            StringEscapeUtils.UNESCAPE_HTML4.translate(original, sw);
            final String actual = original == null ? null : sw.toString();
            // removed other assertion
        }
        // \u00E7 is a cedilla (c with wiggle under)
        // note that the test string must be 7-bit-clean (Unicode escaped) or else it will compile incorrectly
        // on some locales
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Hello&##;World", StringEscapeUtils.unescapeHtml4("Hello&##;World"));
    }

    @Test
    public void testUnescapeHexCharsHtml_1_oe() {
        // Simple easy to grok test
        assertEquals("\u0080\u009F", StringEscapeUtils.unescapeHtml4("&#x80;&#x9F;"), "hex number unescape");
    }

    @Test
    public void testUnescapeHexCharsHtml_2_oe() {
        // Simple easy to grok test
        // removed other assertion
        assertEquals("\u0080\u009F", StringEscapeUtils.unescapeHtml4("&#X80;&#X9F;"), "hex number unescape");
    }

    @Test
    public void testUnescapeHexCharsHtml_3_oe() {
        // Simple easy to grok test
        // removed other assertion
        // removed other assertion
        // Test all Character values:
        for (char i = Character.MIN_VALUE; i < Character.MAX_VALUE; i++) {
            final Character c1 = Character.valueOf(i);
            final Character c2 = Character.valueOf((char) (i+1));
            final String expected = c1.toString() + c2.toString();
            final String escapedC1 = "&#x" + Integer.toHexString((c1.charValue())) + ";";
            final String escapedC2 = "&#x" + Integer.toHexString((c2.charValue())) + ";";
            assertEquals(expected, StringEscapeUtils.unescapeHtml4(escapedC1 + escapedC2), "hex number unescape index " + (int) i);
    }
    }

    @Test
    public void testUnescapeUnknownEntity_1_oe() {
        assertEquals("&zzzz;", StringEscapeUtils.unescapeHtml4("&zzzz;"));
    }

    @Test
    public void testEscapeHtmlVersions_1_oe() {
        assertEquals("&Beta;", StringEscapeUtils.escapeHtml4("\u0392"));
    }

    @Test
    public void testEscapeHtmlVersions_2_oe() {
        // removed other assertion
        assertEquals("\u0392", StringEscapeUtils.unescapeHtml4("&Beta;"));
    }

    @Test
    public void testEscapeXml_1_oe() throws Exception {
        assertEquals("&lt;abc&gt;", StringEscapeUtils.escapeXml("<abc>"));
    }

    @Test
    public void testEscapeXml_2_oe() throws Exception {
        // removed other assertion
        assertEquals("<abc>", StringEscapeUtils.unescapeXml("&lt;abc&gt;"));
    }

    @Test
    public void testEscapeXml_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        assertEquals("\u00A1", StringEscapeUtils.escapeXml("\u00A1"), "XML should not escape >0x7f values");
    }

    @Test
    public void testEscapeXml_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("\u00A0", StringEscapeUtils.unescapeXml("&#160;"), "XML should be able to unescape >0x7f values");
    }

    @Test
    public void testEscapeXml_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("\u00A0",StringEscapeUtils.unescapeXml("&#0160;"),"XML should be able to unescape >0x7f values with one leading 0");
    }

    @Test
    public void testEscapeXml_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("\u00A0",StringEscapeUtils.unescapeXml("&#00160;"),"XML should be able to unescape >0x7f values with two leading 0s");
    }

    @Test
    public void testEscapeXml_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("\u00A0",StringEscapeUtils.unescapeXml("&#000160;"),"XML should be able to unescape >0x7f values with three leading 0s");
    }

    @Test
    public void testEscapeXml_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("ain't", StringEscapeUtils.unescapeXml("ain&apos;t"));
    }

    @Test
    public void testEscapeXml_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("ain&apos;t", StringEscapeUtils.escapeXml("ain't"));
    }

    @Test
    public void testEscapeXml_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", StringEscapeUtils.escapeXml(""));
    }

    @Test
    public void testEscapeXml_11_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringEscapeUtils.escapeXml(null));
    }

    @Test
    public void testEscapeXml_12_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringEscapeUtils.unescapeXml(null));
    }

    @Test
    public void testEscapeXml_13_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        StringWriter sw = new StringWriter();
        StringEscapeUtils.ESCAPE_XML.translate("<abc>", sw);
        assertEquals("&lt;abc&gt;", sw.toString(), "XML was escaped incorrectly");
    }

    @Test
    public void testEscapeXml_14_oe() throws Exception {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        StringWriter sw = new StringWriter();
        StringEscapeUtils.ESCAPE_XML.translate("<abc>", sw);
        // removed other assertion

        sw = new StringWriter();
        StringEscapeUtils.UNESCAPE_XML.translate("&lt;abc&gt;", sw);
        assertEquals("<abc>", sw.toString(), "XML was unescaped incorrectly");
    }

    @Test
    public void testEscapeXml10_1_oe() {
        assertEquals("a&lt;b&gt;c&quot;d&apos;e&amp;f", StringEscapeUtils.escapeXml10("a<b>c\"d'e&f"));
    }

    @Test
    public void testEscapeXml10_2_oe() {
        // removed other assertion
        assertEquals("a\tb\rc\nd", StringEscapeUtils.escapeXml10("a\tb\rc\nd"), "XML 1.0 should not escape \t \n \r");
    }

    @Test
    public void testEscapeXml10_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("ab",StringEscapeUtils.escapeXml10("a\u0000\u0001\u0008\u000b\u000c\u000e\u001fb"),"XML 1.0 should omit most #x0-x8 | #xb | #xc | #xe-#x19");
    }

    @Test
    public void testEscapeXml10_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a\ufffdb",StringEscapeUtils.escapeXml10("a\ufffd\ufffe\uffffb"),"XML 1.0 should omit #xfffe | #xffff");
    }

    @Test
    public void testEscapeXml10_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a\u007e&#127;&#132;\u0085&#134;&#159;\u00a0b",StringEscapeUtils.escapeXml10("a\u007e\u007f\u0084\u0085\u0086\u009f\u00a0b"),"XML 1.0 should escape #x7f-#x84 | #x86 - #x9f,for XML 1.1 compatibility");
    }

    @Test
    public void testEscapeXml11_1_oe() {
        assertEquals("a&lt;b&gt;c&quot;d&apos;e&amp;f", StringEscapeUtils.escapeXml11("a<b>c\"d'e&f"));
    }

    @Test
    public void testEscapeXml11_2_oe() {
        // removed other assertion
        assertEquals("a\tb\rc\nd", StringEscapeUtils.escapeXml11("a\tb\rc\nd"), "XML 1.1 should not escape \t \n \r");
    }

    @Test
    public void testEscapeXml11_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("ab", StringEscapeUtils.escapeXml11("a\u0000b"), "XML 1.1 should omit #x0");
    }

    @Test
    public void testEscapeXml11_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a&#1;&#8;&#11;&#12;&#14;&#31;b",StringEscapeUtils.escapeXml11("a\u0001\u0008\u000b\u000c\u000e\u001fb"),"XML 1.1 should escape #x1-x8 | #xb | #xc | #xe-#x19");
    }

    @Test
    public void testEscapeXml11_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a\u007e&#127;&#132;\u0085&#134;&#159;\u00a0b",StringEscapeUtils.escapeXml11("a\u007e\u007f\u0084\u0085\u0086\u009f\u00a0b"),"XML 1.1 should escape #x7F-#x84 | #x86-#x9F");
    }

    @Test
    public void testEscapeXml11_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a\ufffdb",StringEscapeUtils.escapeXml11("a\ufffd\ufffe\uffffb"),"XML 1.1 should omit #xfffe | #xffff");
    }

    @Test
    public void testEscapeXmlSupplementaryCharacters_1_oe() {
        final CharSequenceTranslator escapeXml =
            StringEscapeUtils.ESCAPE_XML.with( NumericEntityEscaper.between(0x7f, Integer.MAX_VALUE) );

        assertEquals("&#144308;",escapeXml.translate("\uD84C\uDFB4"),"Supplementary character must be represented using a single escape");
    }

    @Test
    public void testEscapeXmlSupplementaryCharacters_2_oe() {
        final CharSequenceTranslator escapeXml =
            StringEscapeUtils.ESCAPE_XML.with( NumericEntityEscaper.between(0x7f, Integer.MAX_VALUE) );

        // removed other assertion

        assertEquals("a b c &#144308;",escapeXml.translate("a b c \uD84C\uDFB4"),"Supplementary characters mixed with basic characters should be encoded correctly");
    }

    @Test
    public void testEscapeXmlAllCharacters_1_oe() {
        // http://www.w3.org/TR/xml/#charsets says:
        // Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF] /* any Unicode character,
        // excluding the surrogate blocks, FFFE, and FFFF. */
        final CharSequenceTranslator escapeXml = StringEscapeUtils.ESCAPE_XML
                .with(NumericEntityEscaper.below(9), NumericEntityEscaper.between(0xB, 0xC), NumericEntityEscaper.between(0xE, 0x19),
                        NumericEntityEscaper.between(0xD800, 0xDFFF), NumericEntityEscaper.between(0xFFFE, 0xFFFF), NumericEntityEscaper.above(0x110000));

        assertEquals("&#0;&#1;&#2;&#3;&#4;&#5;&#6;&#7;&#8;", escapeXml.translate("\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008"));
    }

    @Test
    public void testEscapeXmlAllCharacters_2_oe() {
        // http://www.w3.org/TR/xml/#charsets says:
        // Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF] /* any Unicode character,
        // excluding the surrogate blocks, FFFE, and FFFF. */
        final CharSequenceTranslator escapeXml = StringEscapeUtils.ESCAPE_XML
                .with(NumericEntityEscaper.below(9), NumericEntityEscaper.between(0xB, 0xC), NumericEntityEscaper.between(0xE, 0x19),
                        NumericEntityEscaper.between(0xD800, 0xDFFF), NumericEntityEscaper.between(0xFFFE, 0xFFFF), NumericEntityEscaper.above(0x110000));

        // removed other assertion
        assertEquals("\t",escapeXml.translate("\t"));// 0x9 assertEquals("\n",escapeXml.translate("\n"));// 0xA assertEquals("&#11;&#12;",escapeXml.translate("\u000B\u000C"));
    }

    @Test
    public void testEscapeXmlAllCharacters_3_oe() {
        // http://www.w3.org/TR/xml/#charsets says:
        // Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF] /* any Unicode character,
        // excluding the surrogate blocks, FFFE, and FFFF. */
        final CharSequenceTranslator escapeXml = StringEscapeUtils.ESCAPE_XML
                .with(NumericEntityEscaper.below(9), NumericEntityEscaper.between(0xB, 0xC), NumericEntityEscaper.between(0xE, 0x19),
                        NumericEntityEscaper.between(0xD800, 0xDFFF), NumericEntityEscaper.between(0xFFFE, 0xFFFF), NumericEntityEscaper.above(0x110000));

        // removed other assertion
        // removed other assertion
        assertEquals("\r",escapeXml.translate("\r"));// 0xD assertEquals("Hello World! Ain&apos;t this great?",escapeXml.translate("Hello World! Ain't this great?"));
    }

    @Test
    public void testEscapeXmlAllCharacters_4_oe() {
        // http://www.w3.org/TR/xml/#charsets says:
        // Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF] /* any Unicode character,
        // excluding the surrogate blocks, FFFE, and FFFF. */
        final CharSequenceTranslator escapeXml = StringEscapeUtils.ESCAPE_XML
                .with(NumericEntityEscaper.below(9), NumericEntityEscaper.between(0xB, 0xC), NumericEntityEscaper.between(0xE, 0x19),
                        NumericEntityEscaper.between(0xD800, 0xDFFF), NumericEntityEscaper.between(0xFFFE, 0xFFFF), NumericEntityEscaper.above(0x110000));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("&#14;&#15;&#24;&#25;", escapeXml.translate("\u000E\u000F\u0018\u0019"));
    }

    @Test
    public void testUnescapeXmlSupplementaryCharacters_1_oe() {
        assertEquals("\uD84C\uDFB4",StringEscapeUtils.unescapeXml("&#144308;"),"Supplementary character must be represented using a single escape");
    }

    @Test
    public void testUnescapeXmlSupplementaryCharacters_2_oe() {
        // removed other assertion

        assertEquals("a b c \uD84C\uDFB4",StringEscapeUtils.unescapeXml("a b c &#144308;"),"Supplementary characters mixed with basic characters should be decoded correctly");
    }

    @Test
    public void testStandaloneAmphersand_1_oe() {
        assertEquals("<P&O>", StringEscapeUtils.unescapeHtml4("&lt;P&O&gt;"));
    }

    @Test
    public void testStandaloneAmphersand_2_oe() {
        // removed other assertion
        assertEquals("test & <", StringEscapeUtils.unescapeHtml4("test & &lt;"));
    }

    @Test
    public void testStandaloneAmphersand_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("<P&O>", StringEscapeUtils.unescapeXml("&lt;P&O&gt;"));
    }

    @Test
    public void testStandaloneAmphersand_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("test & <", StringEscapeUtils.unescapeXml("test & &lt;"));
    }

    @Test
    public void testLang313_1_oe() {
        assertEquals("& &", StringEscapeUtils.unescapeHtml4("& &amp;"));
    }

    @Test
    public void testEscapeCsvString_1_oe() {
        assertEquals("foo.bar",            StringEscapeUtils.escapeCsv("foo.bar"));
    }

    @Test
    public void testEscapeCsvString_2_oe() {
        // removed other assertion
        assertEquals("\"foo,bar\"",        StringEscapeUtils.escapeCsv("foo,bar"));
    }

    @Test
    public void testEscapeCsvString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("\"foo\nbar\"",       StringEscapeUtils.escapeCsv("foo\nbar"));
    }

    @Test
    public void testEscapeCsvString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("\"foo\rbar\"",       StringEscapeUtils.escapeCsv("foo\rbar"));
    }

    @Test
    public void testEscapeCsvString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("\"foo\"\"bar\"",     StringEscapeUtils.escapeCsv("foo\"bar"));
    }

    @Test
    public void testEscapeCsvString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo\uD84C\uDFB4bar", StringEscapeUtils.escapeCsv("foo\uD84C\uDFB4bar"));
    }

    @Test
    public void testEscapeCsvString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("",   StringEscapeUtils.escapeCsv(""));
    }

    @Test
    public void testEscapeCsvString_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringEscapeUtils.escapeCsv(null));
    }

    @Test
    public void testEscapeCsvIllegalStateException_1_oe() throws Exception {
        final StringWriter writer = new StringWriter();
        try {
    StringEscapeUtils.ESCAPE_CSV.translate("foo", -1, writer);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testUnescapeCsvString_1_oe() {
        assertEquals("foo.bar",              StringEscapeUtils.unescapeCsv("foo.bar"));
    }

    @Test
    public void testUnescapeCsvString_2_oe() {
        // removed other assertion
        assertEquals("foo,bar",              StringEscapeUtils.unescapeCsv("\"foo,bar\""));
    }

    @Test
    public void testUnescapeCsvString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("foo\nbar",             StringEscapeUtils.unescapeCsv("\"foo\nbar\""));
    }

    @Test
    public void testUnescapeCsvString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo\rbar",             StringEscapeUtils.unescapeCsv("\"foo\rbar\""));
    }

    @Test
    public void testUnescapeCsvString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo\"bar",             StringEscapeUtils.unescapeCsv("\"foo\"\"bar\""));
    }

    @Test
    public void testUnescapeCsvString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo\uD84C\uDFB4bar",   StringEscapeUtils.unescapeCsv("foo\uD84C\uDFB4bar"));
    }

    @Test
    public void testUnescapeCsvString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("",   StringEscapeUtils.unescapeCsv(""));
    }

    @Test
    public void testUnescapeCsvString_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(StringEscapeUtils.unescapeCsv(null));
    }

    @Test
    public void testUnescapeCsvString_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("\"foo.bar\"",          StringEscapeUtils.unescapeCsv("\"foo.bar\""));
    }

    @Test
    public void testUnescapeCsvIllegalStateException_1_oe() throws Exception {
        final StringWriter writer = new StringWriter();
        try {
    StringEscapeUtils.UNESCAPE_CSV.translate("foo", -1, writer);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testEscapeHtmlHighUnicode_1_oe() {
        // this is the utf8 representation of the character:
        // COUNTING ROD UNIT DIGIT THREE
        // in Unicode
        // codepoint: U+1D362
        final byte[] data = new byte[] { (byte) 0xF0, (byte) 0x9D, (byte) 0x8D, (byte) 0xA2 };

        final String original = new String(data, StandardCharsets.UTF_8);

        final String escaped = StringEscapeUtils.escapeHtml4( original );
        assertEquals(original, escaped, "High Unicode should not have been escaped");
    }

    @Test
    public void testEscapeHtmlHighUnicode_2_oe() {
        // this is the utf8 representation of the character:
        // COUNTING ROD UNIT DIGIT THREE
        // in Unicode
        // codepoint: U+1D362
        final byte[] data = new byte[] { (byte) 0xF0, (byte) 0x9D, (byte) 0x8D, (byte) 0xA2 };

        final String original = new String(data, StandardCharsets.UTF_8);

        final String escaped = StringEscapeUtils.escapeHtml4( original );
        // removed other assertion

        final String unescaped = StringEscapeUtils.unescapeHtml4( escaped );
        assertEquals(original, unescaped, "High Unicode should have been unchanged");
    }

    @Test
    public void testEscapeHiragana_1_oe() {
        // Some random Japanese Unicode characters
        final String original = "\u304B\u304C\u3068";
        final String escaped = StringEscapeUtils.escapeHtml4(original);
        assertEquals(original,escaped,"Hiragana character Unicode behavior should not be being escaped by escapeHtml4");
    }

    @Test
    public void testEscapeHiragana_2_oe() {
        // Some random Japanese Unicode characters
        final String original = "\u304B\u304C\u3068";
        final String escaped = StringEscapeUtils.escapeHtml4(original);
        // removed other assertion

        final String unescaped = StringEscapeUtils.unescapeHtml4( escaped );

        assertEquals(escaped, unescaped, "Hiragana character Unicode behavior has changed - expected no unescaping");
    }

    @Test
    public void testLang708_1_oe() throws IOException {
        final byte[] inputBytes = Files.readAllBytes(Paths.get("src/test/resources/lang-708-input.txt"));
        final String input = new String(inputBytes, StandardCharsets.UTF_8);
        final String escaped = StringEscapeUtils.escapeEcmaScript(input);
        // just the end:
        assertTrue(escaped.endsWith("}]"), escaped);
    }

    @Test
    public void testLang708_2_oe() throws IOException {
        final byte[] inputBytes = Files.readAllBytes(Paths.get("src/test/resources/lang-708-input.txt"));
        final String input = new String(inputBytes, StandardCharsets.UTF_8);
        final String escaped = StringEscapeUtils.escapeEcmaScript(input);
        // just the end:
        // removed other assertion
        // a little more:
        assertTrue(escaped.endsWith("\"valueCode\\\":\\\"\\\"}]"), escaped);
    }

    @Test
    public void testLang720_1_oe() {
        final String input = "\ud842\udfb7" + "A";
        final String escaped = StringEscapeUtils.escapeXml(input);
        assertEquals(input, escaped);
    }

    @Test
    public void testLang911_1_oe() {
        final String bellsTest = "\ud83d\udc80\ud83d\udd14";
        final String value = StringEscapeUtils.escapeJava(bellsTest);
        final String valueTest = StringEscapeUtils.unescapeJava(value);
        assertEquals(bellsTest, valueTest);
    }

    @Test
    public void testEscapeJson_1_oe() {
        assertNull(StringEscapeUtils.escapeJson(null));
    }

    @Test
    public void testEscapeJson_2_oe() throws Exception {
        // removed other assertion
        try {
    StringEscapeUtils.ESCAPE_JSON.translate(null, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testEscapeJson_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    StringEscapeUtils.ESCAPE_JSON.translate("", null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testEscapeJson_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("He didn't say, \\\"stop!\\\"", StringEscapeUtils.escapeJson("He didn't say, \"stop!\""));
    }

    @Test
    public void testEscapeJson_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final String expected = "\\\"foo\\\" isn't \\\"bar\\\". specials: \\b\\r\\n\\f\\t\\\\\\/";
        final String input ="\"foo\" isn't \"bar\". specials: \b\r\n\f\t\\/";

        assertEquals(expected, StringEscapeUtils.escapeJson(input));
    }

    @Test
    public void testUnescapeJson_1_oe() {
        assertNull(StringEscapeUtils.unescapeJson(null));
    }

    @Test
    public void testUnescapeJson_2_oe() throws Exception {
        // removed other assertion
        try {
    StringEscapeUtils.UNESCAPE_JSON.translate(null, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testUnescapeJson_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    StringEscapeUtils.UNESCAPE_JSON.translate("", null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testUnescapeJson_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("He didn't say, \"stop!\"", StringEscapeUtils.unescapeJson("He didn't say, \\\"stop!\\\""));
    }

    @Test
    public void testUnescapeJson_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final String expected ="\"foo\" isn't \"bar\". specials: \b\r\n\f\t\\/";
        final String input = "\\\"foo\\\" isn't \\\"bar\\\". specials: \\b\\r\\n\\f\\t\\\\\\/";

        assertEquals(expected, StringEscapeUtils.unescapeJson(input));
    }

    @Test
    public void testEscapeJava_5_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final String escaped0 = FOO;
        final String original0 = FOO;
        assertEscapeJava(null, escaped0, original0);
    }

    @Test
    public void testEscapeJava_9_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String escaped0 = "\\\\\\b\\t\\r";
        final String original0 = "\\\b\t\r";
        assertEscapeJava(null, escaped0, original0);
    }

    @Test
    public void testEscapeJava_10_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String escaped0 = "\\u1234";
        final String original0 = "\u1234";
        assertEscapeJava(null, escaped0, original0);
    }

    @Test
    public void testEscapeJava_11_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String escaped0 = "\\u0234";
        final String original0 = "\u0234";
        assertEscapeJava(null, escaped0, original0);
    }

    @Test
    public void testEscapeJava_12_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String escaped0 = "\\u00EF";
        final String original0 = "\u00ef";
        assertEscapeJava(null, escaped0, original0);
    }

    @Test
    public void testEscapeJava_13_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String escaped0 = "\\u0001";
        final String original0 = "\u0001";
        assertEscapeJava(null, escaped0, original0);
    }

    @Test
    public void testEscapeJava_15_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final String escaped0 = "He didn't say, \\\"stop!\\\"";
        final String original0 = "He didn't say, \"stop!\"";
        assertEscapeJava(null, escaped0, original0);
    }

    @Test
    public void testEscapeJava_17_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final String escaped0 = "\\uABCD\\u1234\\u012C";
        final String original0 = "\uABCD\u1234\u012C";
        assertEscapeJava(null, escaped0, original0);
    }

    @Test
    public void testUnescapeJava_5_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final String unescaped0 = "";
        final String original0 = "";
        assertUnescapeJava(null, unescaped0, original0);
    }

    @Test
    public void testUnescapeJava_6_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final String unescaped0 = "test";
        final String original0 = "test";
        assertUnescapeJava(null, unescaped0, original0);
    }

    @Test
    public void testUnescapeJava_7_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final String unescaped0 = "\ntest\b";
        final String original0 = "\\ntest\\b";
        assertUnescapeJava(null, unescaped0, original0);
    }

    @Test
    public void testUnescapeJava_8_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String unescaped0 = "\u123425foo\ntest\b";
        final String original0 = "\\u123425foo\\ntest\\b";
        assertUnescapeJava(null, unescaped0, original0);
    }

    @Test
    public void testUnescapeJava_9_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String unescaped0 = "'\foo\teste\r";
        final String original0 = "\\'\\foo\\teste\\r";
        assertUnescapeJava(null, unescaped0, original0);
    }

    @Test
    public void testUnescapeJava_10_oe_1_oe() throws IOException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final String unescaped0 = "";
        final String original0 = "\\";
        assertUnescapeJava(null, unescaped0, original0);
    }

}
