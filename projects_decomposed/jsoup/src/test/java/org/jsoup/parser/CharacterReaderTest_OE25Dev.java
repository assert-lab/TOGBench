package org.jsoup.parser;

import org.jsoup.integration.ParseTest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for character reader.
 *
 * @author Jonathan Hedley, jonathan@hedley.net
 */
public class CharacterReaderTest_OE25Dev {
    public final static int maxBufferLen = CharacterReader.maxBufferLen;

    @Test public void consume() {
        CharacterReader r = new CharacterReader("one");
        assertEquals(0, r.pos());
        assertEquals('o', r.current());
        assertEquals('o', r.consume());
        assertEquals(1, r.pos());
        assertEquals('n', r.current());
        assertEquals(1, r.pos());
        assertEquals('n', r.consume());
        assertEquals('e', r.consume());
        assertTrue(r.isEmpty());
        assertEquals(CharacterReader.EOF, r.consume());
        assertTrue(r.isEmpty());
        assertEquals(CharacterReader.EOF, r.consume());
    }

    @Test public void unconsume() {
        CharacterReader r = new CharacterReader("one");
        assertEquals('o', r.consume());
        assertEquals('n', r.current());
        r.unconsume();
        assertEquals('o', r.current());

        assertEquals('o', r.consume());
        assertEquals('n', r.consume());
        assertEquals('e', r.consume());
        assertTrue(r.isEmpty());
        r.unconsume();
        assertFalse(r.isEmpty());
        assertEquals('e', r.current());
        assertEquals('e', r.consume());
        assertTrue(r.isEmpty());

        assertEquals(CharacterReader.EOF, r.consume());
        r.unconsume(); // read past, so have to eat again
        assertTrue(r.isEmpty());
        r.unconsume();
        assertFalse(r.isEmpty());

        assertEquals('e', r.consume());
        assertTrue(r.isEmpty());

        assertEquals(CharacterReader.EOF, r.consume());
        assertTrue(r.isEmpty());
    }

    @Test public void mark() {
        CharacterReader r = new CharacterReader("one");
        r.consume();
        r.mark();
        assertEquals(1, r.pos());
        assertEquals('n', r.consume());
        assertEquals('e', r.consume());
        assertTrue(r.isEmpty());
        r.rewindToMark();
        assertEquals(1, r.pos());
        assertEquals('n', r.consume());
        assertFalse(r.isEmpty());
        assertEquals(2, r.pos());
    }

    @Test public void consumeToEnd() {
        String in = "one two three";
        CharacterReader r = new CharacterReader(in);
        String toEnd = r.consumeToEnd();
        assertEquals(in, toEnd);
        assertTrue(r.isEmpty());
    }

    @Test public void nextIndexOfChar() {
        String in = "blah blah";
        CharacterReader r = new CharacterReader(in);

        assertEquals(-1, r.nextIndexOf('x'));
        assertEquals(3, r.nextIndexOf('h'));
        String pull = r.consumeTo('h');
        assertEquals("bla", pull);
        r.consume();
        assertEquals(2, r.nextIndexOf('l'));
        assertEquals(" blah", r.consumeToEnd());
        assertEquals(-1, r.nextIndexOf('x'));
    }

    @Test public void nextIndexOfString() {
        String in = "One Two something Two Three Four";
        CharacterReader r = new CharacterReader(in);

        assertEquals(-1, r.nextIndexOf("Foo"));
        assertEquals(4, r.nextIndexOf("Two"));
        assertEquals("One Two ", r.consumeTo("something"));
        assertEquals(10, r.nextIndexOf("Two"));
        assertEquals("something Two Three Four", r.consumeToEnd());
        assertEquals(-1, r.nextIndexOf("Two"));
    }

    @Test public void nextIndexOfUnmatched() {
        CharacterReader r = new CharacterReader("<[[one]]");
        assertEquals(-1, r.nextIndexOf("]]>"));
    }

    @Test public void consumeToChar() {
        CharacterReader r = new CharacterReader("One Two Three");
        assertEquals("One ", r.consumeTo('T'));
        assertEquals("", r.consumeTo('T')); // on Two
        assertEquals('T', r.consume());
        assertEquals("wo ", r.consumeTo('T'));
        assertEquals('T', r.consume());
        assertEquals("hree", r.consumeTo('T')); // consume to end
    }

    @Test public void consumeToString() {
        CharacterReader r = new CharacterReader("One Two Two Four");
        assertEquals("One ", r.consumeTo("Two"));
        assertEquals('T', r.consume());
        assertEquals("wo ", r.consumeTo("Two"));
        assertEquals('T', r.consume());
        // To handle strings straddling across buffers, consumeTo() may return the
        // data in multiple pieces near EOF.
        StringBuilder builder = new StringBuilder();
        String part;
        do {
            part = r.consumeTo("Qux");
            builder.append(part);
        } while (!part.isEmpty());
        assertEquals("wo Four", builder.toString());
    }

    @Test public void advance() {
        CharacterReader r = new CharacterReader("One Two Three");
        assertEquals('O', r.consume());
        r.advance();
        assertEquals('e', r.consume());
    }

    @Test public void consumeToAny() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        assertEquals("One ", r.consumeToAny('&', ';'));
        assertTrue(r.matches('&'));
        assertTrue(r.matches("&bar;"));
        assertEquals('&', r.consume());
        assertEquals("bar", r.consumeToAny('&', ';'));
        assertEquals(';', r.consume());
        assertEquals(" qux", r.consumeToAny('&', ';'));
    }

    @Test public void consumeLetterSequence() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        assertEquals("One", r.consumeLetterSequence());
        assertEquals(" &", r.consumeTo("bar;"));
        assertEquals("bar", r.consumeLetterSequence());
        assertEquals("; qux", r.consumeToEnd());
    }

    @Test public void consumeLetterThenDigitSequence() {
        CharacterReader r = new CharacterReader("One12 Two &bar; qux");
        assertEquals("One12", r.consumeLetterThenDigitSequence());
        assertEquals(' ', r.consume());
        assertEquals("Two", r.consumeLetterThenDigitSequence());
        assertEquals(" &bar; qux", r.consumeToEnd());
    }

    @Test public void matches() {
        CharacterReader r = new CharacterReader("One Two Three");
        assertTrue(r.matches('O'));
        assertTrue(r.matches("One Two Three"));
        assertTrue(r.matches("One"));
        assertFalse(r.matches("one"));
        assertEquals('O', r.consume());
        assertFalse(r.matches("One"));
        assertTrue(r.matches("ne Two Three"));
        assertFalse(r.matches("ne Two Three Four"));
        assertEquals("ne Two Three", r.consumeToEnd());
        assertFalse(r.matches("ne"));
        assertTrue(r.isEmpty());
    }

    @Test
    public void matchesIgnoreCase() {
        CharacterReader r = new CharacterReader("One Two Three");
        assertTrue(r.matchesIgnoreCase("O"));
        assertTrue(r.matchesIgnoreCase("o"));
        assertTrue(r.matches('O'));
        assertFalse(r.matches('o'));
        assertTrue(r.matchesIgnoreCase("One Two Three"));
        assertTrue(r.matchesIgnoreCase("ONE two THREE"));
        assertTrue(r.matchesIgnoreCase("One"));
        assertTrue(r.matchesIgnoreCase("one"));
        assertEquals('O', r.consume());
        assertFalse(r.matchesIgnoreCase("One"));
        assertTrue(r.matchesIgnoreCase("NE Two Three"));
        assertFalse(r.matchesIgnoreCase("ne Two Three Four"));
        assertEquals("ne Two Three", r.consumeToEnd());
        assertFalse(r.matchesIgnoreCase("ne"));
    }

    @Test public void containsIgnoreCase() {
        CharacterReader r = new CharacterReader("One TWO three");
        assertTrue(r.containsIgnoreCase("two"));
        assertTrue(r.containsIgnoreCase("three"));
        // weird one: does not find one, because it scans for consistent case only
        assertFalse(r.containsIgnoreCase("one"));
    }

    @Test void containsIgnoreCaseBuffer() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        assertTrue(r.containsIgnoreCase("</title>"));
        assertFalse(r.containsIgnoreCase("</not>"));
        assertFalse(r.containsIgnoreCase("</not>")); // cached, but we only test functionally here
        assertTrue(r.containsIgnoreCase("</title>"));
        r.consumeTo("</title>");
        assertTrue(r.containsIgnoreCase("</title>"));
        r.consumeTo("<p>");
        assertTrue(r.matches("<p>"));

        assertTrue(r.containsIgnoreCase("</title>"));
        assertTrue(r.containsIgnoreCase("</title>"));
        assertFalse(r.containsIgnoreCase("</not>"));
        assertFalse(r.containsIgnoreCase("</not>"));

        r.consumeTo("</TITLE>");
        r.consumeTo("<p>");
        assertTrue(r.matches("<p>"));
        assertFalse(r.containsIgnoreCase("</title>")); // because we haven't buffered up yet, we don't know
        r.consumeTo("<foo>");
        assertFalse(r.matches("<foo>")); // buffer underrun
        r.consumeTo("<foo>");
        assertTrue(r.matches("<foo>")); // cross the buffer
        assertTrue(r.containsIgnoreCase("</TITLE>"));
        assertTrue(r.containsIgnoreCase("</title>"));
    }

    static String BufferBuster(String content) {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < maxBufferLen)
            builder.append(content);
        return builder.toString();
    }

    @Test public void matchesAny() {
        char[] scan = {' ', '\n', '\t'};
        CharacterReader r = new CharacterReader("One\nTwo\tThree");
        assertFalse(r.matchesAny(scan));
        assertEquals("One", r.consumeToAny(scan));
        assertTrue(r.matchesAny(scan));
        assertEquals('\n', r.consume());
        assertFalse(r.matchesAny(scan));
    }

    @Test public void cachesStrings() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE\tA string that is longer than 16 chars");
        String one = r.consumeTo('\t');
        r.consume();
        String two = r.consumeTo('\t');
        r.consume();
        String three = r.consumeTo('\t');
        r.consume();
        String four = r.consumeTo('\t');
        r.consume();
        String five = r.consumeTo('\t');

        assertEquals("Check", one);
        assertEquals("Check", two);
        assertEquals("Check", three);
        assertEquals("CHOKE", four);
        assertSame(one, two);
        assertSame(two, three);
        assertNotSame(three, four);
        assertNotSame(four, five);
        assertEquals(five, "A string that is longer than 16 chars");
    }

    @Test
    public void rangeEquals() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE");
        assertTrue(r.rangeEquals(0, 5, "Check"));
        assertFalse(r.rangeEquals(0, 5, "CHOKE"));
        assertFalse(r.rangeEquals(0, 5, "Chec"));

        assertTrue(r.rangeEquals(6, 5, "Check"));
        assertFalse(r.rangeEquals(6, 5, "Chuck"));

        assertTrue(r.rangeEquals(12, 5, "Check"));
        assertFalse(r.rangeEquals(12, 5, "Cheeky"));

        assertTrue(r.rangeEquals(18, 5, "CHOKE"));
        assertFalse(r.rangeEquals(18, 5, "CHIKE"));
    }

    @Test public void bufferUp() {
        String note = "HelloThere"; // + ! = 11 chars
        int loopCount = 64;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < loopCount; i++) {
            sb.append(note);
            sb.append("!");
        }

        String s = sb.toString();
        BufferedReader br = new BufferedReader(new StringReader(s));

        CharacterReader r = new CharacterReader(br);
        for (int i = 0; i < loopCount; i++) {
            String pull = r.consumeTo('!');
            assertEquals(note, pull);
            assertEquals('!', r.current());
            r.advance();
        }

        assertTrue(r.isEmpty());
    }

    @Test public void canEnableAndDisableLineNumberTracking() {
        CharacterReader reader = new CharacterReader("Hello!");
        assertFalse(reader.isTrackNewlines());
        reader.trackNewlines(true);
        assertTrue(reader.isTrackNewlines());
        reader.trackNewlines(false);
        assertFalse(reader.isTrackNewlines());
    }

    @Test public void canTrackNewlines() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        assertFalse(noTrack.isTrackNewlines());
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        assertTrue(track.isTrackNewlines());

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        assertEquals(0, noTrack.pos());
        assertEquals(1, noTrack.lineNumber());
        assertEquals(1, noTrack.columnNumber());
        noTrack.consumeTo("<qux>");
        assertEquals(12, noTrack.pos());
        assertEquals(1, noTrack.lineNumber());
        assertEquals(13, noTrack.columnNumber());
        assertEquals("1:13", noTrack.cursorPos());
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        assertEquals(32778, noTrack.pos());
        assertEquals(1, noTrack.lineNumber());
        assertEquals(noTrack.pos()+1, noTrack.columnNumber());
        assertEquals("1:32779", noTrack.cursorPos());

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        assertEquals(0, track.pos());
        assertEquals(1, track.lineNumber());
        assertEquals(1, track.columnNumber());

        track.consumeTo('\n');
        assertEquals(1, track.lineNumber());
        assertEquals(6, track.columnNumber());
        track.consume();
        assertEquals(2, track.lineNumber());
        assertEquals(1, track.columnNumber());

        assertEquals("<bar>", track.consumeTo('\n'));
        assertEquals(2, track.lineNumber());
        assertEquals(6, track.columnNumber());

        assertEquals("\n", track.consumeTo("<qux>"));
        assertEquals(12, track.pos());
        assertEquals(3, track.lineNumber());
        assertEquals(1, track.columnNumber());
        assertEquals("3:1", track.cursorPos());
        assertEquals("<qux>", track.consumeTo('\n'));
        assertEquals("3:6", track.cursorPos());
        // get over the buffer
        while (!track.matches("[foo]"))
            track.consumeTo("[foo]");
        assertEquals(32778, track.pos());
        assertEquals(4, track.lineNumber());
        assertEquals(32761, track.columnNumber());
        assertEquals("4:32761", track.cursorPos());
        track.consumeTo('\n');
        assertEquals("4:32766", track.cursorPos());

        track.consumeTo("[bar]");
        assertEquals(5, track.lineNumber());
        assertEquals("5:1", track.cursorPos());
        track.consumeToEnd();
        assertEquals("5:6", track.cursorPos());
    }

    @Test public void countsColumnsOverBufferWhenNoNewlines() {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < maxBufferLen * 4)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        String content = builder.toString();
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        assertEquals("1:1", reader.cursorPos());
        while (!reader.isEmpty())
            reader.consume();
        assertEquals(131096, reader.pos());
        assertEquals(reader.pos() + 1, reader.columnNumber());
        assertEquals(1, reader.lineNumber());
    }

    @Test public void linenumbersAgreeWithEditor() throws IOException {
        String content = ParseTest.getFileAsString(ParseTest.getFile("/htmltests/large.html"));
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        String scan = "<p>VESTIBULUM"; // near the end of the file
        while (!reader.matches(scan))
            reader.consumeTo(scan);

        assertEquals(280218, reader.pos());
        assertEquals(1002, reader.lineNumber());
        assertEquals(1, reader.columnNumber());
        reader.consumeTo(' ');
        assertEquals(1002, reader.lineNumber());
        assertEquals(14, reader.columnNumber());
    }

    @Test public void consume() {
        CharacterReader_1_oe r = new CharacterReader("one");
        assertEquals(0, r.pos());
        }

    @Test public void consume() {
        CharacterReader_2_oe r = new CharacterReader("one");
        // removed other assertion
        assertEquals('o', r.current());
        }

    @Test public void consume() {
        CharacterReader_3_oe r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        assertEquals('o', r.consume());
        }

    @Test public void consume() {
        CharacterReader_4_oe r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, r.pos());
        }

    @Test public void consume() {
        CharacterReader_5_oe r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('n', r.current());
        }

    @Test public void consume() {
        CharacterReader_6_oe r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, r.pos());
        }

    @Test public void consume() {
        CharacterReader_7_oe r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('n', r.consume());
        }

    @Test public void consume() {
        CharacterReader_8_oe r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('e', r.consume());
        }

    @Test public void consume() {
        CharacterReader_9_oe r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.isEmpty());
        }

    @Test public void consume() {
        CharacterReader_10_oe r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CharacterReader.EOF, r.consume());
        }

    @Test public void consume() {
        CharacterReader_11_oe r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.isEmpty());
        }

    @Test public void consume() {
        CharacterReader_12_oe r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(CharacterReader.EOF, r.consume());
        }

    @Test public void mark() {
        CharacterReader_1_oe r = new CharacterReader("one");
        r.consume();
        r.mark();
        assertEquals(1, r.pos());
        }

    @Test public void mark() {
        CharacterReader_2_oe r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        assertEquals('n', r.consume());
        }

    @Test public void mark() {
        CharacterReader_3_oe r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        // removed other assertion
        assertEquals('e', r.consume());
        }

    @Test public void mark() {
        CharacterReader_4_oe r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.isEmpty());
        }

    @Test public void mark() {
        CharacterReader_5_oe r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.rewindToMark();
        assertEquals(1, r.pos());
        }

    @Test public void mark() {
        CharacterReader_6_oe r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.rewindToMark();
        // removed other assertion
        assertEquals('n', r.consume());
        }

    @Test public void mark() {
        CharacterReader_7_oe r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.rewindToMark();
        // removed other assertion
        // removed other assertion
        assertFalse(r.isEmpty());
        }

    @Test public void mark() {
        CharacterReader_8_oe r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.rewindToMark();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, r.pos());
        }

    @Test public void nextIndexOfChar() {
        String in = "blah blah";
        CharacterReader_1_oe r = new CharacterReader(in);

        assertEquals(-1, r.nextIndexOf('x'));
        }

    @Test public void nextIndexOfChar() {
        String in = "blah blah";
        CharacterReader_2_oe r = new CharacterReader(in);

        // removed other assertion
        assertEquals(3, r.nextIndexOf('h'));
        }

    @Test public void nextIndexOfChar() {
        String in = "blah blah";
        CharacterReader_3_oe r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        String pull = r.consumeTo('h');
        assertEquals("bla", pull);
        }

    @Test public void nextIndexOfChar() {
        String in = "blah blah";
        CharacterReader_4_oe r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        String pull = r.consumeTo('h');
        // removed other assertion
        r.consume();
        assertEquals(2, r.nextIndexOf('l'));
        }

    @Test public void nextIndexOfChar() {
        String in = "blah blah";
        CharacterReader_5_oe r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        String pull = r.consumeTo('h');
        // removed other assertion
        r.consume();
        // removed other assertion
        assertEquals(" blah", r.consumeToEnd());
        }

    @Test public void nextIndexOfChar() {
        String in = "blah blah";
        CharacterReader_6_oe r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        String pull = r.consumeTo('h');
        // removed other assertion
        r.consume();
        // removed other assertion
        // removed other assertion
        assertEquals(-1, r.nextIndexOf('x'));
        }

    @Test public void nextIndexOfUnmatched() {
        CharacterReader_1_oe r = new CharacterReader("<[[one]]");
        assertEquals(-1, r.nextIndexOf("]]>"));
        }

    @Test public void consumeToString() {
        CharacterReader_1_oe r = new CharacterReader("One Two Two Four");
        assertEquals("One ", r.consumeTo("Two"));
        }

    @Test public void consumeToString() {
        CharacterReader_2_oe r = new CharacterReader("One Two Two Four");
        // removed other assertion
        assertEquals('T', r.consume());
        }

    @Test public void consumeToString() {
        CharacterReader_3_oe r = new CharacterReader("One Two Two Four");
        // removed other assertion
        // removed other assertion
        assertEquals("wo ", r.consumeTo("Two"));
        }

    @Test public void consumeToString() {
        CharacterReader_4_oe r = new CharacterReader("One Two Two Four");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('T', r.consume());
        }

    @Test public void advance() {
        CharacterReader_1_oe r = new CharacterReader("One Two Three");
        assertEquals('O', r.consume());
        }

    @Test public void advance() {
        CharacterReader_2_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        r.advance();
        assertEquals('e', r.consume());
        }

    @Test public void consumeLetterSequence() {
        CharacterReader_1_oe r = new CharacterReader("One &bar; qux");
        assertEquals("One", r.consumeLetterSequence());
        }

    @Test public void consumeLetterSequence() {
        CharacterReader_2_oe r = new CharacterReader("One &bar; qux");
        // removed other assertion
        assertEquals(" &", r.consumeTo("bar;"));
        }

    @Test public void consumeLetterSequence() {
        CharacterReader_3_oe r = new CharacterReader("One &bar; qux");
        // removed other assertion
        // removed other assertion
        assertEquals("bar", r.consumeLetterSequence());
        }

    @Test public void consumeLetterSequence() {
        CharacterReader_4_oe r = new CharacterReader("One &bar; qux");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("; qux", r.consumeToEnd());
        }

    @Test public void matches() {
        CharacterReader_1_oe r = new CharacterReader("One Two Three");
        assertTrue(r.matches('O'));
        }

    @Test public void matches() {
        CharacterReader_2_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        assertTrue(r.matches("One Two Three"));
        }

    @Test public void matches() {
        CharacterReader_3_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        assertTrue(r.matches("One"));
        }

    @Test public void matches() {
        CharacterReader_4_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matches("one"));
        }

    @Test public void matches() {
        CharacterReader_5_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('O', r.consume());
        }

    @Test public void matches() {
        CharacterReader_6_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matches("One"));
        }

    @Test public void matches() {
        CharacterReader_7_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.matches("ne Two Three"));
        }

    @Test public void matches() {
        CharacterReader_8_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matches("ne Two Three Four"));
        }

    @Test public void matches() {
        CharacterReader_9_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ne Two Three", r.consumeToEnd());
        }

    @Test public void matches() {
        CharacterReader_10_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matches("ne"));
        }

    @Test public void matches() {
        CharacterReader_11_oe r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.isEmpty());
        }

    @Test public void containsIgnoreCase() {
        CharacterReader_1_oe r = new CharacterReader("One TWO three");
        assertTrue(r.containsIgnoreCase("two"));
        }

    @Test public void containsIgnoreCase() {
        CharacterReader_2_oe r = new CharacterReader("One TWO three");
        // removed other assertion
        assertTrue(r.containsIgnoreCase("three"));
        }

    @Test public void containsIgnoreCase() {
        CharacterReader_3_oe r = new CharacterReader("One TWO three");
        // removed other assertion
        // removed other assertion
        // weird one: does not find one, because it scans for consistent case only
        assertFalse(r.containsIgnoreCase("one"));
        }

    @Test public void cachesStrings() {
        CharacterReader_1_oe r = new CharacterReader("Check\tCheck\tCheck\tCHOKE\tA string that is longer than 16 chars");
        String one = r.consumeTo('\t');
        r.consume();
        String two = r.consumeTo('\t');
        r.consume();
        String three = r.consumeTo('\t');
        r.consume();
        String four = r.consumeTo('\t');
        r.consume();
        String five = r.consumeTo('\t');

        assertEquals("Check", one);
        }

    @Test public void cachesStrings() {
        CharacterReader_2_oe r = new CharacterReader("Check\tCheck\tCheck\tCHOKE\tA string that is longer than 16 chars");
        String one = r.consumeTo('\t');
        r.consume();
        String two = r.consumeTo('\t');
        r.consume();
        String three = r.consumeTo('\t');
        r.consume();
        String four = r.consumeTo('\t');
        r.consume();
        String five = r.consumeTo('\t');

        // removed other assertion
        assertEquals("Check", two);
        }

    @Test public void cachesStrings() {
        CharacterReader_3_oe r = new CharacterReader("Check\tCheck\tCheck\tCHOKE\tA string that is longer than 16 chars");
        String one = r.consumeTo('\t');
        r.consume();
        String two = r.consumeTo('\t');
        r.consume();
        String three = r.consumeTo('\t');
        r.consume();
        String four = r.consumeTo('\t');
        r.consume();
        String five = r.consumeTo('\t');

        // removed other assertion
        // removed other assertion
        assertEquals("Check", three);
        }

    @Test public void cachesStrings() {
        CharacterReader_4_oe r = new CharacterReader("Check\tCheck\tCheck\tCHOKE\tA string that is longer than 16 chars");
        String one = r.consumeTo('\t');
        r.consume();
        String two = r.consumeTo('\t');
        r.consume();
        String three = r.consumeTo('\t');
        r.consume();
        String four = r.consumeTo('\t');
        r.consume();
        String five = r.consumeTo('\t');

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("CHOKE", four);
        }

    @Test public void cachesStrings() {
        CharacterReader_5_oe r = new CharacterReader("Check\tCheck\tCheck\tCHOKE\tA string that is longer than 16 chars");
        String one = r.consumeTo('\t');
        r.consume();
        String two = r.consumeTo('\t');
        r.consume();
        String three = r.consumeTo('\t');
        r.consume();
        String four = r.consumeTo('\t');
        r.consume();
        String five = r.consumeTo('\t');

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(one, two);
        }

    @Test public void cachesStrings() {
        CharacterReader_6_oe r = new CharacterReader("Check\tCheck\tCheck\tCHOKE\tA string that is longer than 16 chars");
        String one = r.consumeTo('\t');
        r.consume();
        String two = r.consumeTo('\t');
        r.consume();
        String three = r.consumeTo('\t');
        r.consume();
        String four = r.consumeTo('\t');
        r.consume();
        String five = r.consumeTo('\t');

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(two, three);
        }

    @Test public void cachesStrings() {
        CharacterReader_7_oe r = new CharacterReader("Check\tCheck\tCheck\tCHOKE\tA string that is longer than 16 chars");
        String one = r.consumeTo('\t');
        r.consume();
        String two = r.consumeTo('\t');
        r.consume();
        String three = r.consumeTo('\t');
        r.consume();
        String four = r.consumeTo('\t');
        r.consume();
        String five = r.consumeTo('\t');

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(three, four);
        }

    @Test public void cachesStrings() {
        CharacterReader_8_oe r = new CharacterReader("Check\tCheck\tCheck\tCHOKE\tA string that is longer than 16 chars");
        String one = r.consumeTo('\t');
        r.consume();
        String two = r.consumeTo('\t');
        r.consume();
        String three = r.consumeTo('\t');
        r.consume();
        String four = r.consumeTo('\t');
        r.consume();
        String five = r.consumeTo('\t');

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(four, five);
        }

    @Test public void cachesStrings() {
        CharacterReader_9_oe r = new CharacterReader("Check\tCheck\tCheck\tCHOKE\tA string that is longer than 16 chars");
        String one = r.consumeTo('\t');
        r.consume();
        String two = r.consumeTo('\t');
        r.consume();
        String three = r.consumeTo('\t');
        r.consume();
        String four = r.consumeTo('\t');
        r.consume();
        String five = r.consumeTo('\t');

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(five, "A string that is longer than 16 chars");
        }

    @Test
    public void empty_1_oe() {
        CharacterReader r = new CharacterReader("One");
        assertTrue(r.matchConsume("One"));
    }

    @Test
    public void empty_2_oe() {
        CharacterReader r = new CharacterReader("One");
        // removed other assertion
        assertTrue(r.isEmpty());
    }

    @Test
    public void empty_3_oe() {
        CharacterReader r = new CharacterReader("One");
        // removed other assertion
        // removed other assertion

        r = new CharacterReader("Two");
        String two = r.consumeToEnd();
        assertEquals("Two", two);
    }

    @Test
    public void consumeToNonexistentEndWhenAtAnd_1_oe() {
        CharacterReader r = new CharacterReader("<!");
        assertTrue(r.matchConsume("<!"));
    }

    @Test
    public void consumeToNonexistentEndWhenAtAnd_2_oe() {
        CharacterReader r = new CharacterReader("<!");
        // removed other assertion
        assertTrue(r.isEmpty());
    }

    @Test
    public void consumeToNonexistentEndWhenAtAnd_3_oe() {
        CharacterReader r = new CharacterReader("<!");
        // removed other assertion
        // removed other assertion

        String after = r.consumeTo('>');
        assertEquals("", after);
    }

    @Test
    public void consumeToNonexistentEndWhenAtAnd_4_oe() {
        CharacterReader r = new CharacterReader("<!");
        // removed other assertion
        // removed other assertion

        String after = r.consumeTo('>');
        // removed other assertion

        assertTrue(r.isEmpty());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_1_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        assertEquals("How", r.consumeTo(' '));
    }

    @Test
    public void notEmptyAtBufferSplitPoint_2_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        assertFalse(r.isEmpty(), "Should not be empty");
    }

    @Test
    public void notEmptyAtBufferSplitPoint_3_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        assertEquals(' ', r.consume());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_4_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(r.isEmpty());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_5_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(4, r.pos());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_6_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('a', r.consume());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_7_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, r.pos());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_8_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('b', r.consume());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_9_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('o', r.consume());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_10_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('u', r.consume());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_11_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('t', r.consume());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_12_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(' ', r.consume());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_13_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('n', r.consume());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_14_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('o', r.consume());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_15_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('w', r.consume());
    }

    @Test
    public void notEmptyAtBufferSplitPoint_16_oe() {
        CharacterReader r = new CharacterReader(new StringReader("How about now"), 3);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.isEmpty());
    }

    @Test public void canEnableAndDisableLineNumberTracking() {
        CharacterReader_1_oe reader = new CharacterReader("Hello!");
        assertFalse(reader.isTrackNewlines());
        }

    @Test public void canEnableAndDisableLineNumberTracking() {
        CharacterReader_2_oe reader = new CharacterReader("Hello!");
        // removed other assertion
        reader.trackNewlines(true);
        assertTrue(reader.isTrackNewlines());
        }

    @Test public void canEnableAndDisableLineNumberTracking() {
        CharacterReader_3_oe reader = new CharacterReader("Hello!");
        // removed other assertion
        reader.trackNewlines(true);
        // removed other assertion
        reader.trackNewlines(false);
        assertFalse(reader.isTrackNewlines());
        }

    @Test public void countsColumnsOverBufferWhenNoNewlines() {
        StringBuilder_1_oe builder = new StringBuilder();
        while (builder.length() < maxBufferLen * 4)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        String content = builder.toString();
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        assertEquals("1:1", reader.cursorPos());
        }

    @Test public void countsColumnsOverBufferWhenNoNewlines() {
        StringBuilder_2_oe builder = new StringBuilder();
        while (builder.length() < maxBufferLen * 4)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        String content = builder.toString();
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        // removed other assertion
        while (!reader.isEmpty())
            reader.consume();
        assertEquals(131096, reader.pos());
        }

    @Test public void countsColumnsOverBufferWhenNoNewlines() {
        StringBuilder_3_oe builder = new StringBuilder();
        while (builder.length() < maxBufferLen * 4)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        String content = builder.toString();
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        // removed other assertion
        while (!reader.isEmpty())
            reader.consume();
        // removed other assertion
        assertEquals(reader.pos() + 1, reader.columnNumber());
        }

    @Test public void countsColumnsOverBufferWhenNoNewlines() {
        StringBuilder_4_oe builder = new StringBuilder();
        while (builder.length() < maxBufferLen * 4)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        String content = builder.toString();
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        // removed other assertion
        while (!reader.isEmpty())
            reader.consume();
        // removed other assertion
        // removed other assertion
        assertEquals(1, reader.lineNumber());
        }

}
