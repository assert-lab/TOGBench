package org.jsoup.internal;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.jsoup.internal.StringUtil.normaliseWhitespace;
import static org.jsoup.internal.StringUtil.resolve;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest_OE25Dev {

    @Test public void padding() {
        assertEquals("", StringUtil.padding(0));
        assertEquals(" ", StringUtil.padding(1));
        assertEquals("  ", StringUtil.padding(2));
        assertEquals("               ", StringUtil.padding(15));
        assertEquals("                              ", StringUtil.padding(45)); // we default to tap out at 30

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
        assertEquals("", StringUtil.padding(0, -1));
        assertEquals("                    ", StringUtil.padding(20, -1));

        // this test escapes memoization and continues through
        assertEquals("                     ", StringUtil.padding(21, -1));

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        assertEquals("                              ", StringUtil.padding(30, -1));
        assertEquals("                                             ", StringUtil.padding(45, -1));

        // we tap out at 0 for this test
        assertEquals("", StringUtil.padding(0, 0));

        // as memoization is escaped, setting zero for max padding will not allow any requested width
        assertEquals("", StringUtil.padding(21, 0));

        // we tap out at 30 for these tests making > 30 use 30
        assertEquals("", StringUtil.padding(0, 30));
        assertEquals(" ", StringUtil.padding(1, 30));
        assertEquals("  ", StringUtil.padding(2, 30));
        assertEquals("               ", StringUtil.padding(15, 30));
        assertEquals("                              ", StringUtil.padding(45, 30));

        // max applies regardless of memoized
        assertEquals(5, StringUtil.padding(20, 5).length());
    }

    @Test public void paddingInACan() {
        String[] padding = StringUtil.padding;
        assertEquals(21, padding.length);
        for (int i = 0; i < padding.length; i++) {
            assertEquals(i, padding[i].length());
        }
    }

    @Test public void isBlank() {
        assertTrue(StringUtil.isBlank(null));
        assertTrue(StringUtil.isBlank(""));
        assertTrue(StringUtil.isBlank("      "));
        assertTrue(StringUtil.isBlank("   \r\n  "));

        assertFalse(StringUtil.isBlank("hello"));
        assertFalse(StringUtil.isBlank("   hello   "));
    }

    @Test public void isNumeric() {
        assertFalse(StringUtil.isNumeric(null));
        assertFalse(StringUtil.isNumeric(" "));
        assertFalse(StringUtil.isNumeric("123 546"));
        assertFalse(StringUtil.isNumeric("hello"));
        assertFalse(StringUtil.isNumeric("123.334"));

        assertTrue(StringUtil.isNumeric("1"));
        assertTrue(StringUtil.isNumeric("1234"));
    }

    @Test public void isWhitespace() {
        assertTrue(StringUtil.isWhitespace('\t'));
        assertTrue(StringUtil.isWhitespace('\n'));
        assertTrue(StringUtil.isWhitespace('\r'));
        assertTrue(StringUtil.isWhitespace('\f'));
        assertTrue(StringUtil.isWhitespace(' '));

        assertFalse(StringUtil.isWhitespace('\u00a0'));
        assertFalse(StringUtil.isWhitespace('\u2000'));
        assertFalse(StringUtil.isWhitespace('\u3000'));
    }

    @Test public void normaliseWhiteSpace() {
        assertEquals(" ", normaliseWhitespace("    \r \n \r\n"));
        assertEquals(" hello there ", normaliseWhitespace("   hello   \r \n  there    \n"));
        assertEquals("hello", normaliseWhitespace("hello"));
        assertEquals("hello there", normaliseWhitespace("hello\nthere"));
    }

    @Test public void normaliseWhiteSpaceHandlesHighSurrogates() {
        String test71540chars = "\ud869\udeb2\u304b\u309a  1";
        String test71540charsExpectedSingleWhitespace = "\ud869\udeb2\u304b\u309a 1";

        assertEquals(test71540charsExpectedSingleWhitespace, normaliseWhitespace(test71540chars));
        String extractedText = Jsoup.parse(test71540chars).text();
        assertEquals(test71540charsExpectedSingleWhitespace, extractedText);
    }

    @Test public void resolvesRelativeUrls() {
        assertEquals("http://example.com/one/two?three", resolve("http://example.com", "./one/two?three"));
        assertEquals("http://example.com/one/two?three", resolve("http://example.com?one", "./one/two?three"));
        assertEquals("http://example.com/one/two?three#four", resolve("http://example.com", "./one/two?three#four"));
        assertEquals("https://example.com/one", resolve("http://example.com/", "https://example.com/one"));
        assertEquals("http://example.com/one/two.html", resolve("http://example.com/two/", "../one/two.html"));
        assertEquals("https://example2.com/one", resolve("https://example.com/", "//example2.com/one"));
        assertEquals("https://example.com:8080/one", resolve("https://example.com:8080", "./one"));
        assertEquals("https://example2.com/one", resolve("http://example.com/", "https://example2.com/one"));
        assertEquals("https://example.com/one", resolve("wrong", "https://example.com/one"));
        assertEquals("https://example.com/one", resolve("https://example.com/one", ""));
        assertEquals("", resolve("wrong", "also wrong"));
        assertEquals("ftp://example.com/one", resolve("ftp://example.com/two/", "../one"));
        assertEquals("ftp://example.com/one/two.c", resolve("ftp://example.com/one/", "./two.c"));
        assertEquals("ftp://example.com/one/two.c", resolve("ftp://example.com/one/", "two.c"));
        // examples taken from rfc3986 section 5.4.2
        assertEquals("http://example.com/g", resolve("http://example.com/b/c/d;p?q", "../../../g"));
        assertEquals("http://example.com/g", resolve("http://example.com/b/c/d;p?q", "../../../../g"));
        assertEquals("http://example.com/g", resolve("http://example.com/b/c/d;p?q", "/./g"));
        assertEquals("http://example.com/g", resolve("http://example.com/b/c/d;p?q", "/../g"));
        assertEquals("http://example.com/b/c/g.", resolve("http://example.com/b/c/d;p?q", "g."));
        assertEquals("http://example.com/b/c/.g", resolve("http://example.com/b/c/d;p?q", ".g"));
        assertEquals("http://example.com/b/c/g..", resolve("http://example.com/b/c/d;p?q", "g.."));
        assertEquals("http://example.com/b/c/..g", resolve("http://example.com/b/c/d;p?q", "..g"));
        assertEquals("http://example.com/b/g", resolve("http://example.com/b/c/d;p?q", "./../g"));
        assertEquals("http://example.com/b/c/g/", resolve("http://example.com/b/c/d;p?q", "./g/."));
        assertEquals("http://example.com/b/c/g/h", resolve("http://example.com/b/c/d;p?q", "g/./h"));
        assertEquals("http://example.com/b/c/h", resolve("http://example.com/b/c/d;p?q", "g/../h"));
        assertEquals("http://example.com/b/c/g;x=1/y", resolve("http://example.com/b/c/d;p?q", "g;x=1/./y"));
        assertEquals("http://example.com/b/c/y", resolve("http://example.com/b/c/d;p?q", "g;x=1/../y"));
        assertEquals("http://example.com/b/c/g?y/./x", resolve("http://example.com/b/c/d;p?q", "g?y/./x"));
        assertEquals("http://example.com/b/c/g?y/../x", resolve("http://example.com/b/c/d;p?q", "g?y/../x"));
        assertEquals("http://example.com/b/c/g#s/./x", resolve("http://example.com/b/c/d;p?q", "g#s/./x"));
        assertEquals("http://example.com/b/c/g#s/../x", resolve("http://example.com/b/c/d;p?q", "g#s/../x"));
    }

    @Test void stripsControlCharsFromUrls() {
        // should resovle to an absolute url:
        assertEquals("foo:bar", resolve("\nhttps://\texample.com/", "\r\nfo\to:ba\br"));
    }

    @Test void allowsSpaceInUrl() {
        assertEquals("https://example.com/foo bar/", resolve("HTTPS://example.com/example/", "../foo bar/"));
    }

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

    @Test public void padding() {
        assertEquals_1_oe("", StringUtil.padding(0));
        }

    @Test public void padding() {
        assertEquals_2_oe("", StringUtil.padding(0));
        assertEquals(" ", StringUtil.padding(1));
        }

    @Test public void padding() {
        assertEquals_3_oe("", StringUtil.padding(0));
        // removed other assertion
        assertEquals("  ", StringUtil.padding(2));
        }

    @Test public void padding() {
        assertEquals_4_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        assertEquals("               ", StringUtil.padding(15));
        }

    @Test public void padding() {
        assertEquals_5_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("                              ", StringUtil.padding(45)); // we default to tap out at 30;
        }

    @Test public void padding() {
        assertEquals_6_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
        assertEquals("", StringUtil.padding(0, -1));
        }

    @Test public void padding() {
        assertEquals_7_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
        // removed other assertion
        assertEquals("                    ", StringUtil.padding(20, -1));
        }

    @Test public void padding() {
        assertEquals_8_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        assertEquals("                     ", StringUtil.padding(21, -1));
        }

    @Test public void padding() {
        assertEquals_9_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        assertEquals("                              ", StringUtil.padding(30, -1));
        }

    @Test public void padding() {
        assertEquals_10_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
        // removed other assertion
        // removed other assertion

        // this test escapes memoization and continues through
        // removed other assertion

        // this test escapes memoization and using unlimited length (-1) will allow requested spaces
        // removed other assertion
        assertEquals("                                             ", StringUtil.padding(45, -1));
        }

    @Test public void padding() {
        assertEquals_11_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
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

    @Test public void padding() {
        assertEquals_12_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
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

    @Test public void padding() {
        assertEquals_13_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
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

    @Test public void padding() {
        assertEquals_14_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
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

    @Test public void padding() {
        assertEquals_15_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
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

    @Test public void padding() {
        assertEquals_16_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
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

    @Test public void padding() {
        assertEquals_17_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
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

    @Test public void padding() {
        assertEquals_18_oe("", StringUtil.padding(0));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // memoization is up to 21 blocks (0 to 20 spaces) and exits early before min checks making maxPaddingWidth unused
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

    @Test public void isBlank() {
        assertTrue_1_oe(StringUtil.isBlank(null));
        }

    @Test public void isBlank() {
        assertTrue_2_oe(StringUtil.isBlank(null));
        assertTrue(StringUtil.isBlank(""));
        }

    @Test public void isBlank() {
        assertTrue_3_oe(StringUtil.isBlank(null));
        // removed other assertion
        assertTrue(StringUtil.isBlank("      "));
        }

    @Test public void isBlank() {
        assertTrue_4_oe(StringUtil.isBlank(null));
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtil.isBlank("   \r\n  "));
        }

    @Test public void isBlank() {
        assertTrue_5_oe(StringUtil.isBlank(null));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(StringUtil.isBlank("hello"));
        }

    @Test public void isBlank() {
        assertTrue_6_oe(StringUtil.isBlank(null));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtil.isBlank("   hello   "));
        }

    @Test public void isWhitespace() {
        assertTrue_1_oe(StringUtil.isWhitespace('\t'));
        }

    @Test public void isWhitespace() {
        assertTrue_2_oe(StringUtil.isWhitespace('\t'));
        assertTrue(StringUtil.isWhitespace('\n'));
        }

    @Test public void isWhitespace() {
        assertTrue_3_oe(StringUtil.isWhitespace('\t'));
        // removed other assertion
        assertTrue(StringUtil.isWhitespace('\r'));
        }

    @Test public void isWhitespace() {
        assertTrue_4_oe(StringUtil.isWhitespace('\t'));
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtil.isWhitespace('\f'));
        }

    @Test public void isWhitespace() {
        assertTrue_5_oe(StringUtil.isWhitespace('\t'));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(StringUtil.isWhitespace(' '));
        }

    @Test public void isWhitespace() {
        assertTrue_6_oe(StringUtil.isWhitespace('\t'));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(StringUtil.isWhitespace('\u00a0'));
        }

    @Test public void isWhitespace() {
        assertTrue_7_oe(StringUtil.isWhitespace('\t'));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(StringUtil.isWhitespace('\u2000'));
        }

    @Test public void isWhitespace() {
        assertTrue_8_oe(StringUtil.isWhitespace('\t'));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(StringUtil.isWhitespace('\u3000'));
        }

    @Test public void normaliseWhiteSpaceHandlesHighSurrogates() {
        String test71540chars = "\ud869\udeb2\u304b\u309a  1";
        String test71540charsExpectedSingleWhitespace = "\ud869\udeb2\u304b\u309a 1";

        assertEquals_1_oe(test71540charsExpectedSingleWhitespace, normaliseWhitespace(test71540chars));
        }

    @Test public void normaliseWhiteSpaceHandlesHighSurrogates() {
        String test71540chars = "\ud869\udeb2\u304b\u309a  1";
        String test71540charsExpectedSingleWhitespace = "\ud869\udeb2\u304b\u309a 1";

        assertEquals_2_oe(test71540charsExpectedSingleWhitespace, normaliseWhitespace(test71540chars));
        String extractedText = Jsoup.parse(test71540chars).text();
        assertEquals(test71540charsExpectedSingleWhitespace, extractedText);
        }

    @Test void stripsControlCharsFromUrls() {
        // should resovle to an absolute url:
        assertEquals_1_oe("foo:bar", resolve("\nhttps://\texample.com/", "\r\nfo\to:ba\br"));
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
