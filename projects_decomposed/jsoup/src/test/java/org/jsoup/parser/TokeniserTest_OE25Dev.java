package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.jsoup.parser.CharacterReader.maxBufferLen;
import static org.junit.jupiter.api.Assertions.*;

public class TokeniserTest_OE25Dev {

@Test
    public void bufferUpInAttributeVal_1_oe() {
        // https://github.com/jhy/jsoup/issues/967

        // check each double, singlem, unquoted impls
        String[] quotes = {"\"", "'", ""};
        for (String quote : quotes) {
            String preamble = "<img src=" + quote;
            String tail = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
            StringBuilder sb = new StringBuilder(preamble);

            final int charsToFillBuffer = maxBufferLen - preamble.length();
            for (int i = 0; i < charsToFillBuffer; i++) {
                sb.append('a');
            }

            sb.append('X'); // First character to cross character buffer boundary
            sb.append(tail).append(quote).append(">\n");

            String html = sb.toString();
            Document doc = Jsoup.parse(html);
            String src = doc.select("img").attr("src");

            assertTrue(src.contains("X"), "Handles for quote " + quote);
    }
    }

@Test
    public void bufferUpInAttributeVal_2_oe() {
        // https://github.com/jhy/jsoup/issues/967

        // check each double, singlem, unquoted impls
        String[] quotes = {"\"", "'", ""};
        for (String quote : quotes) {
            String preamble = "<img src=" + quote;
            String tail = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
            StringBuilder sb = new StringBuilder(preamble);

            final int charsToFillBuffer = maxBufferLen - preamble.length();
            for (int i = 0; i < charsToFillBuffer; i++) {
                sb.append('a');
            }

            sb.append('X'); // First character to cross character buffer boundary
            sb.append(tail).append(quote).append(">\n");

            String html = sb.toString();
            Document doc = Jsoup.parse(html);
            String src = doc.select("img").attr("src");

            // removed other assertion
            assertTrue(src.contains(tail));
    }
    }

@Test public void handleSuperLargeTagNames_1_oe() {
        // unlikely, but valid. so who knows.

        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("LargeTagName");
        } while (sb.length() < maxBufferLen);
        String tag = sb.toString();
        String html = "<" + tag + ">One</" + tag + ">";

        Document doc = Parser.htmlParser().settings(ParseSettings.preserveCase).parseInput(html, "");
        Elements els = doc.select(tag);
        assertEquals(1, els.size());
        }

@Test public void handleSuperLargeTagNames_2_oe() {
        // unlikely, but valid. so who knows.

        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("LargeTagName");
        } while (sb.length() < maxBufferLen);
        String tag = sb.toString();
        String html = "<" + tag + ">One</" + tag + ">";

        Document doc = Parser.htmlParser().settings(ParseSettings.preserveCase).parseInput(html, "");
        Elements els = doc.select(tag);
        // removed other assertion
        Element el = els.first();
        assertNotNull(el);
        }

@Test public void handleSuperLargeTagNames_3_oe() {
        // unlikely, but valid. so who knows.

        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("LargeTagName");
        } while (sb.length() < maxBufferLen);
        String tag = sb.toString();
        String html = "<" + tag + ">One</" + tag + ">";

        Document doc = Parser.htmlParser().settings(ParseSettings.preserveCase).parseInput(html, "");
        Elements els = doc.select(tag);
        // removed other assertion
        Element el = els.first();
        // removed other assertion
        assertEquals("One", el.text());
        }

@Test public void handleSuperLargeTagNames_4_oe() {
        // unlikely, but valid. so who knows.

        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("LargeTagName");
        } while (sb.length() < maxBufferLen);
        String tag = sb.toString();
        String html = "<" + tag + ">One</" + tag + ">";

        Document doc = Parser.htmlParser().settings(ParseSettings.preserveCase).parseInput(html, "");
        Elements els = doc.select(tag);
        // removed other assertion
        Element el = els.first();
        // removed other assertion
        // removed other assertion
        assertEquals(tag, el.tagName());
        }

@Test public void handleSuperLargeAttributeName_1_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("LargAttributeName");
        } while (sb.length() < maxBufferLen);
        String attrName = sb.toString();
        String html = "<p " + attrName + "=foo>One</p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.getElementsByAttribute(attrName);
        assertEquals(1, els.size());
        }

@Test public void handleSuperLargeAttributeName_2_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("LargAttributeName");
        } while (sb.length() < maxBufferLen);
        String attrName = sb.toString();
        String html = "<p " + attrName + "=foo>One</p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.getElementsByAttribute(attrName);
        // removed other assertion
        Element el = els.first();
        assertNotNull(el);
        }

@Test public void handleSuperLargeAttributeName_3_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("LargAttributeName");
        } while (sb.length() < maxBufferLen);
        String attrName = sb.toString();
        String html = "<p " + attrName + "=foo>One</p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.getElementsByAttribute(attrName);
        // removed other assertion
        Element el = els.first();
        // removed other assertion
        assertEquals("One", el.text());
        }

@Test public void handleSuperLargeAttributeName_4_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("LargAttributeName");
        } while (sb.length() < maxBufferLen);
        String attrName = sb.toString();
        String html = "<p " + attrName + "=foo>One</p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.getElementsByAttribute(attrName);
        // removed other assertion
        Element el = els.first();
        // removed other assertion
        // removed other assertion
        Attribute attribute = el.attributes().asList().get(0);
        assertEquals(attrName.toLowerCase(), attribute.getKey());
        }

@Test public void handleSuperLargeAttributeName_5_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("LargAttributeName");
        } while (sb.length() < maxBufferLen);
        String attrName = sb.toString();
        String html = "<p " + attrName + "=foo>One</p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.getElementsByAttribute(attrName);
        // removed other assertion
        Element el = els.first();
        // removed other assertion
        // removed other assertion
        Attribute attribute = el.attributes().asList().get(0);
        // removed other assertion
        assertEquals("foo", attribute.getValue());
        }

@Test public void handleLargeText_1_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("A Large Amount of Text");
        } while (sb.length() < maxBufferLen);
        String text = sb.toString();
        String html = "<p>" + text + "</p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p");
        assertEquals(1, els.size());
        }

@Test public void handleLargeText_2_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("A Large Amount of Text");
        } while (sb.length() < maxBufferLen);
        String text = sb.toString();
        String html = "<p>" + text + "</p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p");
        // removed other assertion
        Element el = els.first();

        assertNotNull(el);
        }

@Test public void handleLargeText_3_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("A Large Amount of Text");
        } while (sb.length() < maxBufferLen);
        String text = sb.toString();
        String html = "<p>" + text + "</p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p");
        // removed other assertion
        Element el = els.first();

        // removed other assertion
        assertEquals(text, el.text());
        }

@Test public void handleLargeComment_1_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a comment ");
        } while (sb.length() < maxBufferLen);
        String comment = sb.toString();
        String html = "<p><!-- " + comment + " --></p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p");
        assertEquals(1, els.size());
        }

@Test public void handleLargeComment_2_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a comment ");
        } while (sb.length() < maxBufferLen);
        String comment = sb.toString();
        String html = "<p><!-- " + comment + " --></p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p");
        // removed other assertion
        Element el = els.first();

        assertNotNull(el);
        }

@Test public void handleLargeComment_3_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a comment ");
        } while (sb.length() < maxBufferLen);
        String comment = sb.toString();
        String html = "<p><!-- " + comment + " --></p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p");
        // removed other assertion
        Element el = els.first();

        // removed other assertion
        Comment child = (Comment) el.childNode(0);
        assertEquals(" " + comment + " ", child.getData());
        }

@Test public void handleLargeCdata_1_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a lot of CDATA <><><><>");
        } while (sb.length() < maxBufferLen);
        String cdata = sb.toString();
        String html = "<p><![CDATA[" + cdata + "]]></p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p");
        assertEquals(1, els.size());
        }

@Test public void handleLargeCdata_2_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a lot of CDATA <><><><>");
        } while (sb.length() < maxBufferLen);
        String cdata = sb.toString();
        String html = "<p><![CDATA[" + cdata + "]]></p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p");
        // removed other assertion
        Element el = els.first();

        assertNotNull(el);
        }

@Test public void handleLargeCdata_3_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a lot of CDATA <><><><>");
        } while (sb.length() < maxBufferLen);
        String cdata = sb.toString();
        String html = "<p><![CDATA[" + cdata + "]]></p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p");
        // removed other assertion
        Element el = els.first();

        // removed other assertion
        TextNode child = (TextNode) el.childNode(0);
        assertEquals(cdata, el.text());
        }

@Test public void handleLargeCdata_4_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a lot of CDATA <><><><>");
        } while (sb.length() < maxBufferLen);
        String cdata = sb.toString();
        String html = "<p><![CDATA[" + cdata + "]]></p>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p");
        // removed other assertion
        Element el = els.first();

        // removed other assertion
        TextNode child = (TextNode) el.childNode(0);
        // removed other assertion
        assertEquals(cdata, child.getWholeText());
        }

@Test public void handleLargeTitle_1_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a long title");
        } while (sb.length() < maxBufferLen);
        String title = sb.toString();
        String html = "<title>" + title + "</title>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("title");
        assertEquals(1, els.size());
        }

@Test public void handleLargeTitle_2_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a long title");
        } while (sb.length() < maxBufferLen);
        String title = sb.toString();
        String html = "<title>" + title + "</title>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("title");
        // removed other assertion
        Element el = els.first();

        assertNotNull(el);
        }

@Test public void handleLargeTitle_3_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a long title");
        } while (sb.length() < maxBufferLen);
        String title = sb.toString();
        String html = "<title>" + title + "</title>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("title");
        // removed other assertion
        Element el = els.first();

        // removed other assertion
        TextNode child = (TextNode) el.childNode(0);
        assertEquals(title, el.text());
        }

@Test public void handleLargeTitle_4_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a long title");
        } while (sb.length() < maxBufferLen);
        String title = sb.toString();
        String html = "<title>" + title + "</title>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("title");
        // removed other assertion
        Element el = els.first();

        // removed other assertion
        TextNode child = (TextNode) el.childNode(0);
        // removed other assertion
        assertEquals(title, child.getWholeText());
        }

@Test public void handleLargeTitle_5_oe() {
        StringBuilder sb = new StringBuilder(maxBufferLen);
        do {
            sb.append("Quite a long title");
        } while (sb.length() < maxBufferLen);
        String title = sb.toString();
        String html = "<title>" + title + "</title>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("title");
        // removed other assertion
        Element el = els.first();

        // removed other assertion
        TextNode child = (TextNode) el.childNode(0);
        // removed other assertion
        // removed other assertion
        assertEquals(title, doc.title());
        }

@Test public void cp1252Entities_1_oe() {
        assertEquals("\u20ac", Jsoup.parse("&#0128;").text());
        }

@Test public void cp1252Entities_2_oe() {
        // removed other assertion
        assertEquals("\u201a", Jsoup.parse("&#0130;").text());
        }

@Test public void cp1252Entities_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("\u20ac", Jsoup.parse("&#x80;").text());
        }

@Test public void cp1252EntitiesProduceError_1_oe() {
        Parser parser = new Parser(new HtmlTreeBuilder());
        parser.setTrackErrors(10);
        assertEquals("\u20ac", parser.parseInput("<html><body>&#0128;</body></html>", "").text());
        }

@Test public void cp1252EntitiesProduceError_2_oe() {
        Parser parser = new Parser(new HtmlTreeBuilder());
        parser.setTrackErrors(10);
        // removed other assertion
        assertEquals(1, parser.getErrors().size());
        }

@Test public void cp1252SubstitutionTable_1_oe() {
        for (int i = 0; i < Tokeniser.win1252Extensions.length; i++) {
            String s = new String(new byte[]{ (byte) (i + Tokeniser.win1252ExtensionsStart) }, Charset.forName("Windows-1252"));
            assertEquals(1, s.length());
        }
        }

@Test public void cp1252SubstitutionTable_2_oe() {
        for (int i = 0; i < Tokeniser.win1252Extensions.length; i++) {
            String s = new String(new byte[]{ (byte) (i + Tokeniser.win1252ExtensionsStart) }, Charset.forName("Windows-1252"));
            // removed other assertion

            // some of these characters are illegal
            if (s.charAt(0) == '\ufffd') { continue; }

            assertEquals(s.charAt(0), Tokeniser.win1252Extensions[i], "At: " + i);
        }
        }

@Test public void canParseVeryLongBogusComment_1_oe() {
        StringBuilder commentData = new StringBuilder(maxBufferLen);
        do {
            commentData.append("blah blah blah blah ");
        } while (commentData.length() < maxBufferLen);
        String expectedCommentData = commentData.toString();
        String testMarkup = "<html><body><!" + expectedCommentData + "></body></html>";
        Parser parser = new Parser(new HtmlTreeBuilder());

        Document doc = parser.parseInput(testMarkup, "");

        Node commentNode = doc.body().childNode(0);
        assertTrue(commentNode instanceof Comment, "Expected comment node");
        }

@Test public void canParseVeryLongBogusComment_2_oe() {
        StringBuilder commentData = new StringBuilder(maxBufferLen);
        do {
            commentData.append("blah blah blah blah ");
        } while (commentData.length() < maxBufferLen);
        String expectedCommentData = commentData.toString();
        String testMarkup = "<html><body><!" + expectedCommentData + "></body></html>";
        Parser parser = new Parser(new HtmlTreeBuilder());

        Document doc = parser.parseInput(testMarkup, "");

        Node commentNode = doc.body().childNode(0);
        // removed other assertion
        assertEquals(expectedCommentData, ((Comment)commentNode).getData());
        }

@Test public void canParseCdataEndingAtEdgeOfBuffer_1_oe() {
        String cdataStart = "<![CDATA[";
        String cdataEnd = "]]>";
        int bufLen = maxBufferLen - cdataStart.length() - 1;    // also breaks with -2, but not with -3 or 0
        char[] cdataContentsArray = new char[bufLen];
        Arrays.fill(cdataContentsArray, 'x');
        String cdataContents = new String(cdataContentsArray);
        String testMarkup = cdataStart + cdataContents + cdataEnd;
        Parser parser = new Parser(new HtmlTreeBuilder());

        Document doc = parser.parseInput(testMarkup, "");

        Node cdataNode = doc.body().childNode(0);
        assertTrue(cdataNode instanceof CDataNode, "Expected CDATA node");
        }

@Test public void canParseCdataEndingAtEdgeOfBuffer_2_oe() {
        String cdataStart = "<![CDATA[";
        String cdataEnd = "]]>";
        int bufLen = maxBufferLen - cdataStart.length() - 1;    // also breaks with -2, but not with -3 or 0
        char[] cdataContentsArray = new char[bufLen];
        Arrays.fill(cdataContentsArray, 'x');
        String cdataContents = new String(cdataContentsArray);
        String testMarkup = cdataStart + cdataContents + cdataEnd;
        Parser parser = new Parser(new HtmlTreeBuilder());

        Document doc = parser.parseInput(testMarkup, "");

        Node cdataNode = doc.body().childNode(0);
        // removed other assertion
        assertEquals(cdataContents, ((CDataNode)cdataNode).text());
        }

}
