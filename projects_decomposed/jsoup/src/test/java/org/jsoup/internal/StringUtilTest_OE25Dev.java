package org.jsoup.internal;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.jsoup.internal.StringUtil.normaliseWhitespace;
import static org.jsoup.internal.StringUtil.resolve;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest_OE25Dev {

@Test
    public void join_1_oe() {
        assertEquals("", StringUtil.join(Collections.singletonList(""), " "));
    }

@Test
    public void join_2_oe() {
        // removed other assertion
        assertEquals("one", StringUtil.join(Collections.singletonList("one"), " "));
    }

@Test
    public void join_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("one two three", StringUtil.join(Arrays.asList("one", "two", "three"), " "));
    }

@Test public void padding_1_oe() {
        assertEquals("", StringUtil.padding(0));
        }

@Test public void padding_2_oe() {
        // removed other assertion
        assertEquals(" ", StringUtil.padding(1));
        }

@Test public void padding_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("  ", StringUtil.padding(2));
        }

@Test public void padding_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("               ", StringUtil.padding(15));
        }

@Test public void padding_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" ",StringUtil.padding(45));// we default to tap out at 30 assertEquals("",StringUtil.padding(0,-1));
        }

@Test public void padding_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("                    ", StringUtil.padding(20, -1));
        }

@Test public void padding_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        assertEquals("                     ", StringUtil.padding(21, -1));
        }

@Test public void padding_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        assertEquals("                              ", StringUtil.padding(30, -1));
        }

@Test public void padding_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        // removed other assertion
        assertEquals("                                             ", StringUtil.padding(45, -1));
        }

@Test public void padding_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        // removed other assertion
        // removed other assertion

        // we tap out at 0 for this test
        assertEquals("", StringUtil.padding(0, 0));
        }

@Test public void padding_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        // removed other assertion
        // removed other assertion

        // we tap out at 0 for this test
        // removed other assertion

        // as memoization is escaped, setting zero for max padding will not allow any requested width
        assertEquals("", StringUtil.padding(21, 0));
        }

@Test public void padding_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        // removed other assertion
        // removed other assertion

        // we tap out at 0 for this test
        // removed other assertion

        // as memoization is escaped, setting zero for max padding will not allow any requested width
        // removed other assertion

        // we tap out at 30 for these tests making > 30 use 30
        assertEquals("", StringUtil.padding(0, 30));
        }

@Test public void padding_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        // removed other assertion
        // removed other assertion

        // we tap out at 0 for this test
        // removed other assertion

        // as memoization is escaped, setting zero for max padding will not allow any requested width
        // removed other assertion

        // we tap out at 30 for these tests making > 30 use 30
        // removed other assertion
        assertEquals(" ", StringUtil.padding(1, 30));
        }

@Test public void padding_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        // removed other assertion
        // removed other assertion

        // we tap out at 0 for this test
        // removed other assertion

        // as memoization is escaped, setting zero for max padding will not allow any requested width
        // removed other assertion

        // we tap out at 30 for these tests making > 30 use 30
        // removed other assertion
        // removed other assertion
        assertEquals("  ", StringUtil.padding(2, 30));
        }

@Test public void padding_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        // removed other assertion
        // removed other assertion

        // we tap out at 0 for this test
        // removed other assertion

        // as memoization is escaped, setting zero for max padding will not allow any requested width
        // removed other assertion

        // we tap out at 30 for these tests making > 30 use 30
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("               ", StringUtil.padding(15, 30));
        }

@Test public void padding_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        // removed other assertion
        // removed other assertion

        // we tap out at 0 for this test
        // removed other assertion

        // as memoization is escaped, setting zero for max padding will not allow any requested width
        // removed other assertion

        // we tap out at 30 for these tests making > 30 use 30
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("                              ", StringUtil.padding(45, 30));
        }

@Test public void padding_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        // removed other assertion
        // removed other assertion

        // we tap out at 0 for this test
        // removed other assertion

        // as memoization is escaped, setting zero for max padding will not allow any requested width
        // removed other assertion

        // we tap out at 30 for these tests making > 30 use 30
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // max applies regardless of memoized
        assertEquals(5, StringUtil.padding(20, 5).length());
        }

@Test public void paddingInACan_1_oe() {
        String[] padding = StringUtil.padding;
        assertEquals(21, padding.length);
        }

@Test public void paddingInACan_2_oe() {
        String[] padding = StringUtil.padding;
        // removed other assertion
        for (int i = 0; i < padding.length; i++) {
            assertEquals(i, padding[i].length());
        }
        }

@Test public void isBlank_1_oe() {
        assertTrue(StringUtil.isBlank(null));
        }

@Test public void isBlank_2_oe() {
        // removed other assertion
        assertTrue(StringUtil.isBlank(""));
        }

@Test public void isBlank_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtil.isBlank("      "));
        }

@Test public void isBlank_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtil.isBlank("   \r\n  "));
        }

@Test public void isBlank_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(StringUtil.isBlank("hello"));
        }

@Test public void isBlank_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtil.isBlank("   hello   "));
        }

@Test public void isNumeric_1_oe() {
        assertFalse(StringUtil.isNumeric(null));
        }

@Test public void isNumeric_2_oe() {
        // removed other assertion
        assertFalse(StringUtil.isNumeric(" "));
        }

@Test public void isNumeric_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtil.isNumeric("123 546"));
        }

@Test public void isNumeric_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtil.isNumeric("hello"));
        }

@Test public void isNumeric_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtil.isNumeric("123.334"));
        }

@Test public void isNumeric_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(StringUtil.isNumeric("1"));
        }

@Test public void isNumeric_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(StringUtil.isNumeric("1234"));
        }

@Test public void isWhitespace_1_oe() {
        assertTrue(StringUtil.isWhitespace('\t'));
        }

@Test public void isWhitespace_2_oe() {
        // removed other assertion
        assertTrue(StringUtil.isWhitespace('\n'));
        }

@Test public void isWhitespace_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtil.isWhitespace('\r'));
        }

@Test public void isWhitespace_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtil.isWhitespace('\f'));
        }

@Test public void isWhitespace_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtil.isWhitespace(' '));
        }

@Test public void isWhitespace_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(StringUtil.isWhitespace('\u00a0'));
        }

@Test public void isWhitespace_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtil.isWhitespace('\u2000'));
        }

@Test public void isWhitespace_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(StringUtil.isWhitespace('\u3000'));
        }

@Test public void normaliseWhiteSpace_1_oe() {
        assertEquals(" ", normaliseWhitespace("    \r \n \r\n"));
        }

@Test public void normaliseWhiteSpace_2_oe() {
        // removed other assertion
        assertEquals(" hello there ", normaliseWhitespace("   hello   \r \n  there    \n"));
        }

@Test public void normaliseWhiteSpace_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("hello", normaliseWhitespace("hello"));
        }

@Test public void normaliseWhiteSpace_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hello there", normaliseWhitespace("hello\nthere"));
        }

@Test public void normaliseWhiteSpaceHandlesHighSurrogates_1_oe() {
        String test71540chars = "\ud869\udeb2\u304b\u309a  1";
        String test71540charsExpectedSingleWhitespace = "\ud869\udeb2\u304b\u309a 1";

        assertEquals(test71540charsExpectedSingleWhitespace, normaliseWhitespace(test71540chars));
        }

@Test public void normaliseWhiteSpaceHandlesHighSurrogates_2_oe() {
        String test71540chars = "\ud869\udeb2\u304b\u309a  1";
        String test71540charsExpectedSingleWhitespace = "\ud869\udeb2\u304b\u309a 1";

        // removed other assertion
        String extractedText = Jsoup.parse(test71540chars).text();
        assertEquals(test71540charsExpectedSingleWhitespace, extractedText);
        }

@Test public void resolvesRelativeUrls_1_oe() {
        assertEquals("http://example.com/one/two?three", resolve("http://example.com", "./one/two?three"));
        }

@Test public void resolvesRelativeUrls_2_oe() {
        // removed other assertion
        assertEquals("http://example.com/one/two?three", resolve("http://example.com?one", "./one/two?three"));
        }

@Test public void resolvesRelativeUrls_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/one/two?three#four", resolve("http://example.com", "./one/two?three#four"));
        }

@Test public void resolvesRelativeUrls_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("https://example.com/one", resolve("http://example.com/", "https://example.com/one"));
        }

@Test public void resolvesRelativeUrls_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/one/two.html", resolve("http://example.com/two/", "../one/two.html"));
        }

@Test public void resolvesRelativeUrls_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("https://example2.com/one", resolve("https://example.com/", "//example2.com/one"));
        }

@Test public void resolvesRelativeUrls_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("https://example.com:8080/one", resolve("https://example.com:8080", "./one"));
        }

@Test public void resolvesRelativeUrls_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("https://example2.com/one", resolve("http://example.com/", "https://example2.com/one"));
        }

@Test public void resolvesRelativeUrls_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("https://example.com/one", resolve("wrong", "https://example.com/one"));
        }

@Test public void resolvesRelativeUrls_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("https://example.com/one", resolve("https://example.com/one", ""));
        }

@Test public void resolvesRelativeUrls_11_oe() {
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
        assertEquals("", resolve("wrong", "also wrong"));
        }

@Test public void resolvesRelativeUrls_12_oe() {
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
        assertEquals("ftp://example.com/one", resolve("ftp://example.com/two/", "../one"));
        }

@Test public void resolvesRelativeUrls_13_oe() {
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
        assertEquals("ftp://example.com/one/two.c", resolve("ftp://example.com/one/", "./two.c"));
        }

@Test public void resolvesRelativeUrls_14_oe() {
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
        assertEquals("ftp://example.com/one/two.c", resolve("ftp://example.com/one/", "two.c"));
        }

@Test public void resolvesRelativeUrls_15_oe() {
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
        // examples taken from rfc3986 section 5.4.2
        assertEquals("http://example.com/g", resolve("http://example.com/b/c/d;p?q", "../../../g"));
        }

@Test public void resolvesRelativeUrls_16_oe() {
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
        // examples taken from rfc3986 section 5.4.2
        // removed other assertion
        assertEquals("http://example.com/g", resolve("http://example.com/b/c/d;p?q", "../../../../g"));
        }

@Test public void resolvesRelativeUrls_17_oe() {
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
        // examples taken from rfc3986 section 5.4.2
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/g", resolve("http://example.com/b/c/d;p?q", "/./g"));
        }

@Test public void resolvesRelativeUrls_18_oe() {
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
        // examples taken from rfc3986 section 5.4.2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/g", resolve("http://example.com/b/c/d;p?q", "/../g"));
        }

@Test public void resolvesRelativeUrls_19_oe() {
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
        // examples taken from rfc3986 section 5.4.2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/b/c/g.", resolve("http://example.com/b/c/d;p?q", "g."));
        }

@Test public void resolvesRelativeUrls_20_oe() {
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
        // examples taken from rfc3986 section 5.4.2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/b/c/.g", resolve("http://example.com/b/c/d;p?q", ".g"));
        }

@Test public void resolvesRelativeUrls_21_oe() {
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
        // examples taken from rfc3986 section 5.4.2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/b/c/g..", resolve("http://example.com/b/c/d;p?q", "g.."));
        }

@Test public void resolvesRelativeUrls_22_oe() {
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
        // examples taken from rfc3986 section 5.4.2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/b/c/..g", resolve("http://example.com/b/c/d;p?q", "..g"));
        }

@Test public void resolvesRelativeUrls_23_oe() {
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
        // examples taken from rfc3986 section 5.4.2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/b/g", resolve("http://example.com/b/c/d;p?q", "./../g"));
        }

@Test public void resolvesRelativeUrls_24_oe() {
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
        // examples taken from rfc3986 section 5.4.2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/b/c/g/", resolve("http://example.com/b/c/d;p?q", "./g/."));
        }

@Test public void resolvesRelativeUrls_25_oe() {
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
        // examples taken from rfc3986 section 5.4.2
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
        assertEquals("http://example.com/b/c/g/h", resolve("http://example.com/b/c/d;p?q", "g/./h"));
        }

@Test public void resolvesRelativeUrls_26_oe() {
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
        // examples taken from rfc3986 section 5.4.2
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
        assertEquals("http://example.com/b/c/h", resolve("http://example.com/b/c/d;p?q", "g/../h"));
        }

@Test public void resolvesRelativeUrls_27_oe() {
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
        // examples taken from rfc3986 section 5.4.2
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
        assertEquals("http://example.com/b/c/g;x=1/y", resolve("http://example.com/b/c/d;p?q", "g;x=1/./y"));
        }

@Test public void resolvesRelativeUrls_28_oe() {
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
        // examples taken from rfc3986 section 5.4.2
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
        assertEquals("http://example.com/b/c/y", resolve("http://example.com/b/c/d;p?q", "g;x=1/../y"));
        }

@Test public void resolvesRelativeUrls_29_oe() {
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
        // examples taken from rfc3986 section 5.4.2
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
        assertEquals("http://example.com/b/c/g?y/./x", resolve("http://example.com/b/c/d;p?q", "g?y/./x"));
        }

@Test public void resolvesRelativeUrls_30_oe() {
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
        // examples taken from rfc3986 section 5.4.2
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
        assertEquals("http://example.com/b/c/g?y/../x", resolve("http://example.com/b/c/d;p?q", "g?y/../x"));
        }

@Test public void resolvesRelativeUrls_31_oe() {
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
        // examples taken from rfc3986 section 5.4.2
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
        assertEquals("http://example.com/b/c/g#s/./x", resolve("http://example.com/b/c/d;p?q", "g#s/./x"));
        }

@Test public void resolvesRelativeUrls_32_oe() {
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
        // examples taken from rfc3986 section 5.4.2
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
        assertEquals("http://example.com/b/c/g#s/../x", resolve("http://example.com/b/c/d;p?q", "g#s/../x"));
        }

@Test void stripsControlCharsFromUrls_1_oe() {
        // should resovle to an absolute url:
        assertEquals("foo:bar", resolve("\nhttps://\texample.com/", "\r\nfo\to:ba\br"));
        }

@Test void allowsSpaceInUrl_1_oe() {
        assertEquals("https://example.com/foo bar/", resolve("HTTPS://example.com/example/", "../foo bar/"));
        }

@Test
    void isAscii_1_oe() {
        assertTrue(StringUtil.isAscii(""));
    }

@Test
    void isAscii_2_oe() {
        // removed other assertion
        assertTrue(StringUtil.isAscii("example.com"));
    }

@Test
    void isAscii_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtil.isAscii("One Two"));
    }

@Test
    void isAscii_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtil.isAscii("🧔"));
    }

@Test
    void isAscii_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtil.isAscii("测试"));
    }

@Test
    void isAscii_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(StringUtil.isAscii("测试.com"));
    }

}
