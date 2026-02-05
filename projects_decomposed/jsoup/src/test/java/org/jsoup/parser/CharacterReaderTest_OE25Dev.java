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

    static String BufferBuster(String content) {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < maxBufferLen)
            builder.append(content);
        return builder.toString();
    }

    @Test public void consume_1_oe() {
        CharacterReader r = new CharacterReader("one");
        assertEquals(0, r.pos());
        }

    @Test public void consume_2_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        assertEquals('o', r.current());
        }

    @Test public void consume_3_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        assertEquals('o', r.consume());
        }

    @Test public void consume_4_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, r.pos());
        }

    @Test public void consume_5_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('n', r.current());
        }

    @Test public void consume_6_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, r.pos());
        }

    @Test public void consume_7_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('n', r.consume());
        }

    @Test public void consume_8_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('e', r.consume());
        }

    @Test public void consume_9_oe() {
        CharacterReader r = new CharacterReader("one");
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

    @Test public void consume_10_oe() {
        CharacterReader r = new CharacterReader("one");
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

    @Test public void consume_11_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
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

    @Test public void consume_12_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
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

    @Test public void unconsume_1_oe() {
        CharacterReader r = new CharacterReader("one");
        assertEquals('o', r.consume());
        }

    @Test public void unconsume_2_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        assertEquals('n', r.current());
        }

    @Test public void unconsume_3_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        assertEquals('o', r.current());
        }

    @Test public void unconsume_4_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        assertEquals('o', r.consume());
        }

    @Test public void unconsume_5_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        assertEquals('n', r.consume());
        }

    @Test public void unconsume_6_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals('e', r.consume());
        }

    @Test public void unconsume_7_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.isEmpty());
        }

    @Test public void unconsume_8_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        assertFalse(r.isEmpty());
        }

    @Test public void unconsume_9_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion
        assertEquals('e', r.current());
        }

    @Test public void unconsume_10_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion
        // removed other assertion
        assertEquals('e', r.consume());
        }

    @Test public void unconsume_11_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.isEmpty());
        }

    @Test public void unconsume_12_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(CharacterReader.EOF, r.consume());
        }

    @Test public void unconsume_13_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        r.unconsume(); // read past, so have to eat again
        assertTrue(r.isEmpty());
        }

    @Test public void unconsume_14_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        r.unconsume(); // read past, so have to eat again
        // removed other assertion
        r.unconsume();
        assertFalse(r.isEmpty());
        }

    @Test public void unconsume_15_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        r.unconsume(); // read past, so have to eat again
        // removed other assertion
        r.unconsume();
        // removed other assertion

        assertEquals('e', r.consume());
        }

    @Test public void unconsume_16_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        r.unconsume(); // read past, so have to eat again
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        assertTrue(r.isEmpty());
        }

    @Test public void unconsume_17_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        r.unconsume(); // read past, so have to eat again
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(CharacterReader.EOF, r.consume());
        }

    @Test public void unconsume_18_oe() {
        CharacterReader r = new CharacterReader("one");
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.unconsume();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        r.unconsume(); // read past, so have to eat again
        // removed other assertion
        r.unconsume();
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(r.isEmpty());
        }

    @Test public void mark_1_oe() {
        CharacterReader r = new CharacterReader("one");
        r.consume();
        r.mark();
        assertEquals(1, r.pos());
        }

    @Test public void mark_2_oe() {
        CharacterReader r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        assertEquals('n', r.consume());
        }

    @Test public void mark_3_oe() {
        CharacterReader r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        // removed other assertion
        assertEquals('e', r.consume());
        }

    @Test public void mark_4_oe() {
        CharacterReader r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.isEmpty());
        }

    @Test public void mark_5_oe() {
        CharacterReader r = new CharacterReader("one");
        r.consume();
        r.mark();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.rewindToMark();
        assertEquals(1, r.pos());
        }

    @Test public void mark_6_oe() {
        CharacterReader r = new CharacterReader("one");
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

    @Test public void mark_7_oe() {
        CharacterReader r = new CharacterReader("one");
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

    @Test public void mark_8_oe() {
        CharacterReader r = new CharacterReader("one");
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

    @Test public void consumeToEnd_1_oe() {
        String in = "one two three";
        CharacterReader r = new CharacterReader(in);
        String toEnd = r.consumeToEnd();
        assertEquals(in, toEnd);
        }

    @Test public void consumeToEnd_2_oe() {
        String in = "one two three";
        CharacterReader r = new CharacterReader(in);
        String toEnd = r.consumeToEnd();
        // removed other assertion
        assertTrue(r.isEmpty());
        }

    @Test public void nextIndexOfChar_1_oe() {
        String in = "blah blah";
        CharacterReader r = new CharacterReader(in);

        assertEquals(-1, r.nextIndexOf('x'));
        }

    @Test public void nextIndexOfChar_2_oe() {
        String in = "blah blah";
        CharacterReader r = new CharacterReader(in);

        // removed other assertion
        assertEquals(3, r.nextIndexOf('h'));
        }

    @Test public void nextIndexOfChar_3_oe() {
        String in = "blah blah";
        CharacterReader r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        String pull = r.consumeTo('h');
        assertEquals("bla", pull);
        }

    @Test public void nextIndexOfChar_4_oe() {
        String in = "blah blah";
        CharacterReader r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        String pull = r.consumeTo('h');
        // removed other assertion
        r.consume();
        assertEquals(2, r.nextIndexOf('l'));
        }

    @Test public void nextIndexOfChar_5_oe() {
        String in = "blah blah";
        CharacterReader r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        String pull = r.consumeTo('h');
        // removed other assertion
        r.consume();
        // removed other assertion
        assertEquals(" blah", r.consumeToEnd());
        }

    @Test public void nextIndexOfChar_6_oe() {
        String in = "blah blah";
        CharacterReader r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        String pull = r.consumeTo('h');
        // removed other assertion
        r.consume();
        // removed other assertion
        // removed other assertion
        assertEquals(-1, r.nextIndexOf('x'));
        }

    @Test public void nextIndexOfString_1_oe() {
        String in = "One Two something Two Three Four";
        CharacterReader r = new CharacterReader(in);

        assertEquals(-1, r.nextIndexOf("Foo"));
        }

    @Test public void nextIndexOfString_2_oe() {
        String in = "One Two something Two Three Four";
        CharacterReader r = new CharacterReader(in);

        // removed other assertion
        assertEquals(4, r.nextIndexOf("Two"));
        }

    @Test public void nextIndexOfString_3_oe() {
        String in = "One Two something Two Three Four";
        CharacterReader r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        assertEquals("One Two ", r.consumeTo("something"));
        }

    @Test public void nextIndexOfString_4_oe() {
        String in = "One Two something Two Three Four";
        CharacterReader r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, r.nextIndexOf("Two"));
        }

    @Test public void nextIndexOfString_5_oe() {
        String in = "One Two something Two Three Four";
        CharacterReader r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("something Two Three Four", r.consumeToEnd());
        }

    @Test public void nextIndexOfString_6_oe() {
        String in = "One Two something Two Three Four";
        CharacterReader r = new CharacterReader(in);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, r.nextIndexOf("Two"));
        }

    @Test public void nextIndexOfUnmatched_1_oe() {
        CharacterReader r = new CharacterReader("<[[one]]");
        assertEquals(-1, r.nextIndexOf("]]>"));
        }

    @Test public void consumeToChar_1_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        assertEquals("One ", r.consumeTo('T'));
        }

    @Test public void consumeToChar_2_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        assertEquals("",r.consumeTo('T'));// on Two assertEquals('T',r.consume());
        }

    @Test public void consumeToChar_3_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        assertEquals("wo ", r.consumeTo('T'));
        }

    @Test public void consumeToChar_4_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('T', r.consume());
        }

    @Test public void consumeToChar_5_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("hree", r.consumeTo('T')); // consume to end;
        }

    @Test public void consumeToString_1_oe() {
        CharacterReader r = new CharacterReader("One Two Two Four");
        assertEquals("One ", r.consumeTo("Two"));
        }

    @Test public void consumeToString_2_oe() {
        CharacterReader r = new CharacterReader("One Two Two Four");
        // removed other assertion
        assertEquals('T', r.consume());
        }

    @Test public void consumeToString_3_oe() {
        CharacterReader r = new CharacterReader("One Two Two Four");
        // removed other assertion
        // removed other assertion
        assertEquals("wo ", r.consumeTo("Two"));
        }

    @Test public void consumeToString_4_oe() {
        CharacterReader r = new CharacterReader("One Two Two Four");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('T', r.consume());
        }

    @Test public void consumeToString_5_oe() {
        CharacterReader r = new CharacterReader("One Two Two Four");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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

    @Test public void advance_1_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        assertEquals('O', r.consume());
        }

    @Test public void advance_2_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        r.advance();
        assertEquals('e', r.consume());
        }

    @Test public void consumeToAny_1_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        assertEquals("One ", r.consumeToAny('&', ';'));
        }

    @Test public void consumeToAny_2_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        // removed other assertion
        assertTrue(r.matches('&'));
        }

    @Test public void consumeToAny_3_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        // removed other assertion
        // removed other assertion
        assertTrue(r.matches("&bar;"));
        }

    @Test public void consumeToAny_4_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('&', r.consume());
        }

    @Test public void consumeToAny_5_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("bar", r.consumeToAny('&', ';'));
        }

    @Test public void consumeToAny_6_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(';', r.consume());
        }

    @Test public void consumeToAny_7_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" qux", r.consumeToAny('&', ';'));
        }

    @Test public void consumeLetterSequence_1_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        assertEquals("One", r.consumeLetterSequence());
        }

    @Test public void consumeLetterSequence_2_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        // removed other assertion
        assertEquals(" &", r.consumeTo("bar;"));
        }

    @Test public void consumeLetterSequence_3_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        // removed other assertion
        // removed other assertion
        assertEquals("bar", r.consumeLetterSequence());
        }

    @Test public void consumeLetterSequence_4_oe() {
        CharacterReader r = new CharacterReader("One &bar; qux");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("; qux", r.consumeToEnd());
        }

    @Test public void consumeLetterThenDigitSequence_1_oe() {
        CharacterReader r = new CharacterReader("One12 Two &bar; qux");
        assertEquals("One12", r.consumeLetterThenDigitSequence());
        }

    @Test public void consumeLetterThenDigitSequence_2_oe() {
        CharacterReader r = new CharacterReader("One12 Two &bar; qux");
        // removed other assertion
        assertEquals(' ', r.consume());
        }

    @Test public void consumeLetterThenDigitSequence_3_oe() {
        CharacterReader r = new CharacterReader("One12 Two &bar; qux");
        // removed other assertion
        // removed other assertion
        assertEquals("Two", r.consumeLetterThenDigitSequence());
        }

    @Test public void consumeLetterThenDigitSequence_4_oe() {
        CharacterReader r = new CharacterReader("One12 Two &bar; qux");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" &bar; qux", r.consumeToEnd());
        }

    @Test public void matches_1_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        assertTrue(r.matches('O'));
        }

    @Test public void matches_2_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        assertTrue(r.matches("One Two Three"));
        }

    @Test public void matches_3_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        assertTrue(r.matches("One"));
        }

    @Test public void matches_4_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matches("one"));
        }

    @Test public void matches_5_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('O', r.consume());
        }

    @Test public void matches_6_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matches("One"));
        }

    @Test public void matches_7_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.matches("ne Two Three"));
        }

    @Test public void matches_8_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matches("ne Two Three Four"));
        }

    @Test public void matches_9_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
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

    @Test public void matches_10_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
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

    @Test public void matches_11_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
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

    @Test
    public void matchesIgnoreCase_1_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        assertTrue(r.matchesIgnoreCase("O"));
    }

    @Test
    public void matchesIgnoreCase_2_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        assertTrue(r.matchesIgnoreCase("o"));
    }

    @Test
    public void matchesIgnoreCase_3_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        assertTrue(r.matches('O'));
    }

    @Test
    public void matchesIgnoreCase_4_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matches('o'));
    }

    @Test
    public void matchesIgnoreCase_5_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.matchesIgnoreCase("One Two Three"));
    }

    @Test
    public void matchesIgnoreCase_6_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.matchesIgnoreCase("ONE two THREE"));
    }

    @Test
    public void matchesIgnoreCase_7_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.matchesIgnoreCase("One"));
    }

    @Test
    public void matchesIgnoreCase_8_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.matchesIgnoreCase("one"));
    }

    @Test
    public void matchesIgnoreCase_9_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('O', r.consume());
    }

    @Test
    public void matchesIgnoreCase_10_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matchesIgnoreCase("One"));
    }

    @Test
    public void matchesIgnoreCase_11_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.matchesIgnoreCase("NE Two Three"));
    }

    @Test
    public void matchesIgnoreCase_12_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matchesIgnoreCase("ne Two Three Four"));
    }

    @Test
    public void matchesIgnoreCase_13_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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

    @Test
    public void matchesIgnoreCase_14_oe() {
        CharacterReader r = new CharacterReader("One Two Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matchesIgnoreCase("ne"));
    }

    @Test public void containsIgnoreCase_1_oe() {
        CharacterReader r = new CharacterReader("One TWO three");
        assertTrue(r.containsIgnoreCase("two"));
        }

    @Test public void containsIgnoreCase_2_oe() {
        CharacterReader r = new CharacterReader("One TWO three");
        // removed other assertion
        assertTrue(r.containsIgnoreCase("three"));
        }

    @Test public void containsIgnoreCase_3_oe() {
        CharacterReader r = new CharacterReader("One TWO three");
        // removed other assertion
        // removed other assertion
        // weird one: does not find one, because it scans for consistent case only
        assertFalse(r.containsIgnoreCase("one"));
        }

    @Test void containsIgnoreCaseBuffer_1_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        assertTrue(r.containsIgnoreCase("</title>"));
        }

    @Test void containsIgnoreCaseBuffer_2_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        assertFalse(r.containsIgnoreCase("</not>"));
        }

    @Test void containsIgnoreCaseBuffer_3_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        assertFalse(r.containsIgnoreCase("</not>"));// cached,but we only test functionally here assertTrue(r.containsIgnoreCase("</title>"));
        }

    @Test void containsIgnoreCaseBuffer_4_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        assertTrue(r.containsIgnoreCase("</title>"));
        }

    @Test void containsIgnoreCaseBuffer_5_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        // removed other assertion
        r.consumeTo("<p>");
        assertTrue(r.matches("<p>"));
        }

    @Test void containsIgnoreCaseBuffer_6_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        // removed other assertion
        r.consumeTo("<p>");
        // removed other assertion

        assertTrue(r.containsIgnoreCase("</title>"));
        }

    @Test void containsIgnoreCaseBuffer_7_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        // removed other assertion
        r.consumeTo("<p>");
        // removed other assertion

        // removed other assertion
        assertTrue(r.containsIgnoreCase("</title>"));
        }

    @Test void containsIgnoreCaseBuffer_8_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        // removed other assertion
        r.consumeTo("<p>");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(r.containsIgnoreCase("</not>"));
        }

    @Test void containsIgnoreCaseBuffer_9_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        // removed other assertion
        r.consumeTo("<p>");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.containsIgnoreCase("</not>"));
        }

    @Test void containsIgnoreCaseBuffer_10_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        // removed other assertion
        r.consumeTo("<p>");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        r.consumeTo("</TITLE>");
        r.consumeTo("<p>");
        assertTrue(r.matches("<p>"));
        }

    @Test void containsIgnoreCaseBuffer_11_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        // removed other assertion
        r.consumeTo("<p>");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        r.consumeTo("</TITLE>");
        r.consumeTo("<p>");
        // removed other assertion
        assertFalse(r.containsIgnoreCase("</title>"));// because we haven't buffered up yet,we don't know r.consumeTo("<foo>");
        }

    @Test void containsIgnoreCaseBuffer_12_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        // removed other assertion
        r.consumeTo("<p>");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        r.consumeTo("</TITLE>");
        r.consumeTo("<p>");
        // removed other assertion
        // removed other assertion
        assertFalse(r.matches("<foo>"));// buffer underrun r.consumeTo("<foo>");
        }

    @Test void containsIgnoreCaseBuffer_13_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        // removed other assertion
        r.consumeTo("<p>");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        r.consumeTo("</TITLE>");
        r.consumeTo("<p>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.matches("<foo>"));// cross the buffer assertTrue(r.containsIgnoreCase("</TITLE>"));
        }

    @Test void containsIgnoreCaseBuffer_14_oe() {
        String html = "<p><p><p></title><p></TITLE><p>" + BufferBuster("Foo Bar Qux ") + "<foo><bar></title>";
        CharacterReader r = new CharacterReader(html);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        r.consumeTo("</title>");
        // removed other assertion
        r.consumeTo("<p>");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        r.consumeTo("</TITLE>");
        r.consumeTo("<p>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(r.containsIgnoreCase("</title>"));
        }

    @Test public void matchesAny_1_oe() {
        char[] scan = {' ', '\n', '\t'};
        CharacterReader r = new CharacterReader("One\nTwo\tThree");
        assertFalse(r.matchesAny(scan));
        }

    @Test public void matchesAny_2_oe() {
        char[] scan = {' ', '\n', '\t'};
        CharacterReader r = new CharacterReader("One\nTwo\tThree");
        // removed other assertion
        assertEquals("One", r.consumeToAny(scan));
        }

    @Test public void matchesAny_3_oe() {
        char[] scan = {' ', '\n', '\t'};
        CharacterReader r = new CharacterReader("One\nTwo\tThree");
        // removed other assertion
        // removed other assertion
        assertTrue(r.matchesAny(scan));
        }

    @Test public void matchesAny_4_oe() {
        char[] scan = {' ', '\n', '\t'};
        CharacterReader r = new CharacterReader("One\nTwo\tThree");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('\n', r.consume());
        }

    @Test public void matchesAny_5_oe() {
        char[] scan = {' ', '\n', '\t'};
        CharacterReader r = new CharacterReader("One\nTwo\tThree");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(r.matchesAny(scan));
        }

    @Test public void cachesStrings_1_oe() {
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
        }

    @Test public void cachesStrings_2_oe() {
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

        // removed other assertion
        assertEquals("Check", two);
        }

    @Test public void cachesStrings_3_oe() {
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

        // removed other assertion
        // removed other assertion
        assertEquals("Check", three);
        }

    @Test public void cachesStrings_4_oe() {
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("CHOKE", four);
        }

    @Test public void cachesStrings_5_oe() {
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(one, two);
        }

    @Test public void cachesStrings_6_oe() {
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(two, three);
        }

    @Test public void cachesStrings_7_oe() {
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(three, four);
        }

    @Test public void cachesStrings_8_oe() {
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(four, five);
        }

    @Test public void cachesStrings_9_oe() {
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
    public void rangeEquals_1_oe() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE");
        assertTrue(r.rangeEquals(0, 5, "Check"));
    }

    @Test
    public void rangeEquals_2_oe() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE");
        // removed other assertion
        assertFalse(r.rangeEquals(0, 5, "CHOKE"));
    }

    @Test
    public void rangeEquals_3_oe() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE");
        // removed other assertion
        // removed other assertion
        assertFalse(r.rangeEquals(0, 5, "Chec"));
    }

    @Test
    public void rangeEquals_4_oe() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(r.rangeEquals(6, 5, "Check"));
    }

    @Test
    public void rangeEquals_5_oe() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(r.rangeEquals(6, 5, "Chuck"));
    }

    @Test
    public void rangeEquals_6_oe() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(r.rangeEquals(12, 5, "Check"));
    }

    @Test
    public void rangeEquals_7_oe() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(r.rangeEquals(12, 5, "Cheeky"));
    }

    @Test
    public void rangeEquals_8_oe() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(r.rangeEquals(18, 5, "CHOKE"));
    }

    @Test
    public void rangeEquals_9_oe() {
        CharacterReader r = new CharacterReader("Check\tCheck\tCheck\tCHOKE");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(r.rangeEquals(18, 5, "CHIKE"));
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

    @Test public void bufferUp_1_oe() {
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
        }
        }

    @Test public void bufferUp_2_oe() {
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
            // removed other assertion
            assertEquals('!', r.current());
        }
        }

    @Test public void bufferUp_3_oe() {
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
            // removed other assertion
            // removed other assertion
            r.advance();
        }

        assertTrue(r.isEmpty());
        }

    @Test public void canEnableAndDisableLineNumberTracking_1_oe() {
        CharacterReader reader = new CharacterReader("Hello!");
        assertFalse(reader.isTrackNewlines());
        }

    @Test public void canEnableAndDisableLineNumberTracking_2_oe() {
        CharacterReader reader = new CharacterReader("Hello!");
        // removed other assertion
        reader.trackNewlines(true);
        assertTrue(reader.isTrackNewlines());
        }

    @Test public void canEnableAndDisableLineNumberTracking_3_oe() {
        CharacterReader reader = new CharacterReader("Hello!");
        // removed other assertion
        reader.trackNewlines(true);
        // removed other assertion
        reader.trackNewlines(false);
        assertFalse(reader.isTrackNewlines());
        }

    @Test public void canTrackNewlines_1_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        assertFalse(noTrack.isTrackNewlines());
        }

    @Test public void canTrackNewlines_2_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        assertTrue(track.isTrackNewlines());
        }

    @Test public void canTrackNewlines_3_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        assertEquals(0, noTrack.pos());
        }

    @Test public void canTrackNewlines_4_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        assertEquals(1, noTrack.lineNumber());
        }

    @Test public void canTrackNewlines_5_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        assertEquals(1, noTrack.columnNumber());
        }

    @Test public void canTrackNewlines_6_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        assertEquals(12, noTrack.pos());
        }

    @Test public void canTrackNewlines_7_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        assertEquals(1, noTrack.lineNumber());
        }

    @Test public void canTrackNewlines_8_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        assertEquals(13, noTrack.columnNumber());
        }

    @Test public void canTrackNewlines_9_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1:13", noTrack.cursorPos());
        }

    @Test public void canTrackNewlines_10_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        assertEquals(32778, noTrack.pos());
        }

    @Test public void canTrackNewlines_11_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        assertEquals(1, noTrack.lineNumber());
        }

    @Test public void canTrackNewlines_12_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        assertEquals(noTrack.pos()+1, noTrack.columnNumber());
        }

    @Test public void canTrackNewlines_13_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1:32779", noTrack.cursorPos());
        }

    @Test public void canTrackNewlines_14_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        assertEquals(0, track.pos());
        }

    @Test public void canTrackNewlines_15_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        assertEquals(1, track.lineNumber());
        }

    @Test public void canTrackNewlines_16_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        assertEquals(1, track.columnNumber());
        }

    @Test public void canTrackNewlines_17_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        assertEquals(1, track.lineNumber());
        }

    @Test public void canTrackNewlines_18_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        assertEquals(6, track.columnNumber());
        }

    @Test public void canTrackNewlines_19_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        assertEquals(2, track.lineNumber());
        }

    @Test public void canTrackNewlines_20_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        assertEquals(1, track.columnNumber());
        }

    @Test public void canTrackNewlines_21_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        assertEquals("<bar>", track.consumeTo('\n'));
        }

    @Test public void canTrackNewlines_22_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(2, track.lineNumber());
        }

    @Test public void canTrackNewlines_23_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(6, track.columnNumber());
        }

    @Test public void canTrackNewlines_24_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("\n", track.consumeTo("<qux>"));
        }

    @Test public void canTrackNewlines_25_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(12, track.pos());
        }

    @Test public void canTrackNewlines_26_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(3, track.lineNumber());
        }

    @Test public void canTrackNewlines_27_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, track.columnNumber());
        }

    @Test public void canTrackNewlines_28_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3:1", track.cursorPos());
        }

    @Test public void canTrackNewlines_29_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("<qux>", track.consumeTo('\n'));
        }

    @Test public void canTrackNewlines_30_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3:6", track.cursorPos());
        }

    @Test public void canTrackNewlines_31_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!track.matches("[foo]"))
            track.consumeTo("[foo]");
        assertEquals(32778, track.pos());
        }

    @Test public void canTrackNewlines_32_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!track.matches("[foo]"))
            track.consumeTo("[foo]");
        // removed other assertion
        assertEquals(4, track.lineNumber());
        }

    @Test public void canTrackNewlines_33_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!track.matches("[foo]"))
            track.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        assertEquals(32761, track.columnNumber());
        }

    @Test public void canTrackNewlines_34_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!track.matches("[foo]"))
            track.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4:32761", track.cursorPos());
        }

    @Test public void canTrackNewlines_35_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!track.matches("[foo]"))
            track.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        track.consumeTo('\n');
        assertEquals("4:32766", track.cursorPos());
        }

    @Test public void canTrackNewlines_36_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!track.matches("[foo]"))
            track.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        track.consumeTo('\n');
        // removed other assertion

        track.consumeTo("[bar]");
        assertEquals(5, track.lineNumber());
        }

    @Test public void canTrackNewlines_37_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!track.matches("[foo]"))
            track.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        track.consumeTo('\n');
        // removed other assertion

        track.consumeTo("[bar]");
        // removed other assertion
        assertEquals("5:1", track.cursorPos());
        }

    @Test public void canTrackNewlines_38_oe() {
        StringBuilder builder = new StringBuilder();
        builder.append("<foo>\n<bar>\n<qux>\n");
        while (builder.length() < maxBufferLen)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        builder.append("[foo]\n[bar]");
        String content = builder.toString();

        CharacterReader noTrack = new CharacterReader(content);
        // removed other assertion
        CharacterReader track = new CharacterReader(content);
        track.trackNewlines(true);
        // removed other assertion

        // check that no tracking works as expected (pos is 0 indexed, line number stays at 1, col is pos+1)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        noTrack.consumeTo("<qux>");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!noTrack.matches("[foo]"))
            noTrack.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // and the line numbers: "<foo>\n<bar>\n<qux>\n"
        // removed other assertion
        // removed other assertion
        // removed other assertion

        track.consumeTo('\n');
        // removed other assertion
        // removed other assertion
        track.consume();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // get over the buffer
        while (!track.matches("[foo]"))
            track.consumeTo("[foo]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        track.consumeTo('\n');
        // removed other assertion

        track.consumeTo("[bar]");
        // removed other assertion
        // removed other assertion
        track.consumeToEnd();
        assertEquals("5:6", track.cursorPos());
        }

    @Test public void countsColumnsOverBufferWhenNoNewlines_1_oe() {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < maxBufferLen * 4)
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        String content = builder.toString();
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        assertEquals("1:1", reader.cursorPos());
        }

    @Test public void countsColumnsOverBufferWhenNoNewlines_2_oe() {
        StringBuilder builder = new StringBuilder();
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

    @Test public void countsColumnsOverBufferWhenNoNewlines_3_oe() {
        StringBuilder builder = new StringBuilder();
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

    @Test public void countsColumnsOverBufferWhenNoNewlines_4_oe() {
        StringBuilder builder = new StringBuilder();
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

    @Test public void linenumbersAgreeWithEditor_1_oe() throws IOException {
        String content = ParseTest.getFileAsString(ParseTest.getFile("/htmltests/large.html"));
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        String scan = "<p>VESTIBULUM"; // near the end of the file
        while (!reader.matches(scan))
            reader.consumeTo(scan);

        assertEquals(280218, reader.pos());
        }

    @Test public void linenumbersAgreeWithEditor_2_oe() throws IOException {
        String content = ParseTest.getFileAsString(ParseTest.getFile("/htmltests/large.html"));
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        String scan = "<p>VESTIBULUM"; // near the end of the file
        while (!reader.matches(scan))
            reader.consumeTo(scan);

        // removed other assertion
        assertEquals(1002, reader.lineNumber());
        }

    @Test public void linenumbersAgreeWithEditor_3_oe() throws IOException {
        String content = ParseTest.getFileAsString(ParseTest.getFile("/htmltests/large.html"));
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        String scan = "<p>VESTIBULUM"; // near the end of the file
        while (!reader.matches(scan))
            reader.consumeTo(scan);

        // removed other assertion
        // removed other assertion
        assertEquals(1, reader.columnNumber());
        }

    @Test public void linenumbersAgreeWithEditor_4_oe() throws IOException {
        String content = ParseTest.getFileAsString(ParseTest.getFile("/htmltests/large.html"));
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        String scan = "<p>VESTIBULUM"; // near the end of the file
        while (!reader.matches(scan))
            reader.consumeTo(scan);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.consumeTo(' ');
        assertEquals(1002, reader.lineNumber());
        }

    @Test public void linenumbersAgreeWithEditor_5_oe() throws IOException {
        String content = ParseTest.getFileAsString(ParseTest.getFile("/htmltests/large.html"));
        CharacterReader reader = new CharacterReader(content);
        reader.trackNewlines(true);

        String scan = "<p>VESTIBULUM"; // near the end of the file
        while (!reader.matches(scan))
            reader.consumeTo(scan);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        reader.consumeTo(' ');
        // removed other assertion
        assertEquals(14, reader.columnNumber());
        }

}
