package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.integration.servlets.FileServlet;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 Functional tests for the Position tracking behavior (across nodes, treebuilder, etc.)
 */
class PositionTest_OE25Dev {
    static Parser TrackingParser = Parser.htmlParser().setTrackPosition(true);

    @Test void parserTrackDefaults_1_oe() {
        Parser htmlParser = Parser.htmlParser();
        assertFalse(htmlParser.isTrackPosition());
        }

    @Test void parserTrackDefaults_2_oe() {
        Parser htmlParser = Parser.htmlParser();
        // removed other assertion
        htmlParser.setTrackPosition(true);
        assertTrue(htmlParser.isTrackPosition());
        }

    @Test void parserTrackDefaults_3_oe() {
        Parser htmlParser = Parser.htmlParser();
        // removed other assertion
        htmlParser.setTrackPosition(true);
        // removed other assertion

        Parser xmlParser = Parser.htmlParser();
        assertFalse(xmlParser.isTrackPosition());
        }

    @Test void parserTrackDefaults_4_oe() {
        Parser htmlParser = Parser.htmlParser();
        // removed other assertion
        htmlParser.setTrackPosition(true);
        // removed other assertion

        Parser xmlParser = Parser.htmlParser();
        // removed other assertion
        xmlParser.setTrackPosition(true);
        assertTrue(xmlParser.isTrackPosition());
        }

    @Test void tracksPosition_1_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        assertNotNull(text);
        }

    @Test void tracksPosition_2_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        assertNotNull(now);
        }

    @Test void tracksPosition_3_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        assertNotNull(comment);
        }

    @Test void tracksPosition_4_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        assertFalse(body.sourceRange().isTracked());
        }

    @Test void tracksPosition_5_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        assertEquals("1,1:0-2,12:19", pRange.toString());
        }

    @Test void tracksPosition_6_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        assertFalse(pEndRange.isTracked());
        }

    @Test void tracksPosition_7_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        assertTrue(pStart.isTracked());
        }

    @Test void tracksPosition_8_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        assertEquals(0, pStart.pos());
        }

    @Test void tracksPosition_9_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        assertEquals(1, pStart.columnNumber());
        }

    @Test void tracksPosition_10_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, pStart.lineNumber());
        }

    @Test void tracksPosition_11_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1,1:0", pStart.toString());
        }

    @Test void tracksPosition_12_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        assertTrue(pStart.isTracked());
        }

    @Test void tracksPosition_13_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        assertEquals(19, pEnd.pos());
        }

    @Test void tracksPosition_14_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        assertEquals(12, pEnd.columnNumber());
        }

    @Test void tracksPosition_15_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, pEnd.lineNumber());
        }

    @Test void tracksPosition_16_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2,12:19", pEnd.toString());
        }

    @Test void tracksPosition_17_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("3,1:20", span.sourceRange().start().toString());
        }

    @Test void tracksPosition_18_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("3,7:26", span.sourceRange().end().toString());
        }

    @Test void tracksPosition_19_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // span end tag
        Range spanEnd = span.endSourceRange();
        assertTrue(spanEnd.isTracked());
        }

    @Test void tracksPosition_20_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // span end tag
        Range spanEnd = span.endSourceRange();
        // removed other assertion
        assertEquals("5,14:52-5,21:59", spanEnd.toString());
        }

    @Test void tracksPosition_21_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // span end tag
        Range spanEnd = span.endSourceRange();
        // removed other assertion
        // removed other assertion

        String wholeText = text.getWholeText();
        assertEquals("Hello\n ®\n there ©.", wholeText);
        }

    @Test void tracksPosition_22_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // span end tag
        Range spanEnd = span.endSourceRange();
        // removed other assertion
        // removed other assertion

        String wholeText = text.getWholeText();
        // removed other assertion
        String textOrig = "Hello\n &reg;\n there &copy.";
        Range textRange = text.sourceRange();
        assertEquals(textRange.end().pos() -  textRange.start().pos(), textOrig.length());
        }

    @Test void tracksPosition_23_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // span end tag
        Range spanEnd = span.endSourceRange();
        // removed other assertion
        // removed other assertion

        String wholeText = text.getWholeText();
        // removed other assertion
        String textOrig = "Hello\n &reg;\n there &copy.";
        Range textRange = text.sourceRange();
        // removed other assertion
        assertEquals("3,7:26", textRange.start().toString());
        }

    @Test void tracksPosition_24_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // span end tag
        Range spanEnd = span.endSourceRange();
        // removed other assertion
        // removed other assertion

        String wholeText = text.getWholeText();
        // removed other assertion
        String textOrig = "Hello\n &reg;\n there &copy.";
        Range textRange = text.sourceRange();
        // removed other assertion
        // removed other assertion
        assertEquals("5,14:52", textRange.end().toString());
        }

    @Test void tracksPosition_25_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // span end tag
        Range spanEnd = span.endSourceRange();
        // removed other assertion
        // removed other assertion

        String wholeText = text.getWholeText();
        // removed other assertion
        String textOrig = "Hello\n &reg;\n there &copy.";
        Range textRange = text.sourceRange();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("6,2:66", comment.sourceRange().start().toString());
        }

    @Test void tracksPosition_26_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        // removed other assertion
        TextNode now = (TextNode) span.nextSibling();
        // removed other assertion
        Comment comment = (Comment) now.nextSibling();
        // removed other assertion

        // removed other assertion

        Range pRange = p.sourceRange();
        // removed other assertion

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        // removed other assertion

        Range.Position pStart = pRange.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Range.Position pEnd = pRange.end();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // span end tag
        Range spanEnd = span.endSourceRange();
        // removed other assertion
        // removed other assertion

        String wholeText = text.getWholeText();
        // removed other assertion
        String textOrig = "Hello\n &reg;\n there &copy.";
        Range textRange = text.sourceRange();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("6,18:82", comment.sourceRange().end().toString());
        }

    @Test void tracksMarkup_1_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        assertNotNull(doctype);
        }

    @Test void tracksMarkup_2_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        // removed other assertion
        assertEquals("html", doctype.name());
        }

    @Test void tracksMarkup_3_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        // removed other assertion
        // removed other assertion
        assertEquals("1,1:0-2,6:15", doctype.sourceRange().toString());
        }

    @Test void tracksMarkup_4_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        assertNotNull(titleText);
        }

    @Test void tracksMarkup_5_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        // removed other assertion
        assertEquals("jsoup ©\n2022", title.text());
        }

    @Test void tracksMarkup_6_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        // removed other assertion
        // removed other assertion
        assertEquals(titleText.getWholeText(), title.text());
        }

    @Test void tracksMarkup_7_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3,1:16-3,8:23", title.sourceRange().toString());
        }

    @Test void tracksMarkup_8_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3,8:23-4,5:40", titleText.sourceRange().toString());
        }

    @Test void tracksMarkup_9_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        CDataNode cdata = (CDataNode) doc.body().childNode(1);
        assertEquals("\n<jsoup>\n", cdata.text());
        }

    @Test void tracksMarkup_10_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        CDataNode cdata = (CDataNode) doc.body().childNode(1);
        // removed other assertion
        assertEquals("5,1:55-7,4:76", cdata.sourceRange().toString());
        }

    @Test void tracksDataNodes_1_oe() {
        String html = "<head>\n<script>foo;\nbar()\n5 <= 4;</script>";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element script = doc.expectFirst("script");
        assertNotNull(script);
        }

    @Test void tracksDataNodes_2_oe() {
        String html = "<head>\n<script>foo;\nbar()\n5 <= 4;</script>";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element script = doc.expectFirst("script");
        // removed other assertion
        assertEquals("2,1:7-2,9:15", script.sourceRange().toString());
        }

    @Test void tracksDataNodes_3_oe() {
        String html = "<head>\n<script>foo;\nbar()\n5 <= 4;</script>";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element script = doc.expectFirst("script");
        // removed other assertion
        // removed other assertion
        DataNode data = (DataNode) script.firstChild();
        assertNotNull(data);
        }

    @Test void tracksDataNodes_4_oe() {
        String html = "<head>\n<script>foo;\nbar()\n5 <= 4;</script>";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element script = doc.expectFirst("script");
        // removed other assertion
        // removed other assertion
        DataNode data = (DataNode) script.firstChild();
        // removed other assertion
        assertEquals("2,9:15-4,8:33", data.sourceRange().toString());
        }

    @Test void tracksXml_1_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1,1:0-1,39:38", decl.sourceRange().toString());
        }

    @Test void tracksXml_2_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        // removed other assertion

        DocumentType doctype = (DocumentType) doc.childNode(2);
        assertEquals("2,1:39-2,16:54", doctype.sourceRange().toString());
        }

    @Test void tracksXml_3_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        // removed other assertion

        DocumentType doctype = (DocumentType) doc.childNode(2);
        // removed other assertion

        Element rss = doc.firstElementChild();
        assertNotNull(rss);
        }

    @Test void tracksXml_4_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        // removed other assertion

        DocumentType doctype = (DocumentType) doc.childNode(2);
        // removed other assertion

        Element rss = doc.firstElementChild();
        // removed other assertion
        assertEquals("3,1:55-3,14:68", rss.sourceRange().toString());
        }

    @Test void tracksXml_5_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        // removed other assertion

        DocumentType doctype = (DocumentType) doc.childNode(2);
        // removed other assertion

        Element rss = doc.firstElementChild();
        // removed other assertion
        // removed other assertion
        assertEquals("5,1:73-5,7:79", rss.endSourceRange().toString());
        }

    @Test void tracksXml_6_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        // removed other assertion

        DocumentType doctype = (DocumentType) doc.childNode(2);
        // removed other assertion

        Element rss = doc.firstElementChild();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TextNode text = (TextNode) rss.firstChild();
        assertNotNull(text);
        }

    @Test void tracksXml_7_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        // removed other assertion

        DocumentType doctype = (DocumentType) doc.childNode(2);
        // removed other assertion

        Element rss = doc.firstElementChild();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TextNode text = (TextNode) rss.firstChild();
        // removed other assertion
        assertEquals("3,14:68-5,1:73", text.sourceRange().toString());
        }

    @Test void tracksXml_8_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        // removed other assertion

        DocumentType doctype = (DocumentType) doc.childNode(2);
        // removed other assertion

        Element rss = doc.firstElementChild();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TextNode text = (TextNode) rss.firstChild();
        // removed other assertion
        // removed other assertion

        Comment comment = (Comment) rss.nextSibling().nextSibling();
        assertEquals("6,1:80-6,17:96", comment.sourceRange().toString());
        }

    @Test void tracksFromFetch_1_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");
        assertNotNull(firstP);
        }

    @Test void tracksFromFetch_2_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");
        // removed other assertion
        assertEquals("4,1:53-4,4:56", firstP.sourceRange().toString());
        }

    @Test void tracksFromFetch_3_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");
        // removed other assertion
        // removed other assertion

        Element p = doc.expectFirst("#xy");
        assertNotNull(p);
        }

    @Test void tracksFromFetch_4_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");
        // removed other assertion
        // removed other assertion

        Element p = doc.expectFirst("#xy");
        // removed other assertion
        assertEquals("1000,1:279646-1000,10:279655", p.sourceRange().toString());
        }

    @Test void tracksFromFetch_5_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");
        // removed other assertion
        // removed other assertion

        Element p = doc.expectFirst("#xy");
        // removed other assertion
        // removed other assertion
        assertEquals("1000,567:280212-1000,571:280216", p.endSourceRange().toString());
        }

    @Test void tracksFromFetch_6_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");
        // removed other assertion
        // removed other assertion

        Element p = doc.expectFirst("#xy");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TextNode text = (TextNode) p.firstChild();
        assertNotNull(text);
        }

    @Test void tracksFromFetch_7_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");
        // removed other assertion
        // removed other assertion

        Element p = doc.expectFirst("#xy");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TextNode text = (TextNode) p.firstChild();
        // removed other assertion
        assertEquals("1000,10:279655-1000,357:280002", text.sourceRange().toString());
        }

    @Test void tracksFromXmlFetch_1_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/test-rss.xml");
        Document doc = Jsoup.connect(url).parser(Parser.xmlParser().setTrackPosition(true)).get();

        Element item = doc.expectFirst("item + item");
        assertNotNull(item);
        }

    @Test void tracksFromXmlFetch_2_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/test-rss.xml");
        Document doc = Jsoup.connect(url).parser(Parser.xmlParser().setTrackPosition(true)).get();

        Element item = doc.expectFirst("item + item");
        // removed other assertion
        assertEquals("13,5:496-13,11:502", item.sourceRange().toString());
        }

    @Test void tracksFromXmlFetch_3_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/test-rss.xml");
        Document doc = Jsoup.connect(url).parser(Parser.xmlParser().setTrackPosition(true)).get();

        Element item = doc.expectFirst("item + item");
        // removed other assertion
        // removed other assertion
        assertEquals("17,5:779-17,12:786", item.endSourceRange().toString());
        }

}