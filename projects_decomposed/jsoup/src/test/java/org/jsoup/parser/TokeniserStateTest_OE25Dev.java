package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokeniserStateTest_OE25Dev {

    final char[] whiteSpace = { '\t', '\n', '\r', '\f', ' ' };
    final char[] quote = { '\'', '"' };

    @Test
    public void testUnconsumeAfterBufferUp() {
        // test for after consume() a bufferUp occurs (look-forward) but then attempts to unconsume. Would throw a "No buffer left to unconsume"
        String triggeringSnippet = "<title>One <span>Two";
        char[] padding = new char[CharacterReader.readAheadLimit - triggeringSnippet.length() + 8]; // The "<span" part must be just at the limit. The "containsIgnoreCase" scan does a bufferUp, losing the unconsume
        Arrays.fill(padding, ' ');
        String paddedSnippet = String.valueOf(padding) + triggeringSnippet;
        ParseErrorList errorList = ParseErrorList.tracking(1);
        Parser.parseFragment(paddedSnippet, null, "", errorList);
        // just asserting we don't get a WTF on unconsume
    }


}
