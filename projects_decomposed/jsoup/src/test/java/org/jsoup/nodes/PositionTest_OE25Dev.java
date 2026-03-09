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

    @Test void parserTrackDefaults() {
        Parser htmlParser = Parser.htmlParser();
        assertFalse(htmlParser.isTrackPosition());
        htmlParser.setTrackPosition(true);
        assertTrue(htmlParser.isTrackPosition());

        Parser xmlParser = Parser.htmlParser();
        assertFalse(xmlParser.isTrackPosition());
        xmlParser.setTrackPosition(true);
        assertTrue(xmlParser.isTrackPosition());
    }

    @Test void tracksPosition() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        assertNotNull(text);
        TextNode now = (TextNode) span.nextSibling();
        assertNotNull(now);
        Comment comment = (Comment) now.nextSibling();
        assertNotNull(comment);

        assertFalse(body.sourceRange().isTracked());

        Range pRange = p.sourceRange();
        assertEquals("1,1:0-2,12:19", pRange.toString());

        // no explicit P closer
        Range pEndRange = p.endSourceRange();
        assertFalse(pEndRange.isTracked());

        Range.Position pStart = pRange.start();
        assertTrue(pStart.isTracked());
        assertEquals(0, pStart.pos());
        assertEquals(1, pStart.columnNumber());
        assertEquals(1, pStart.lineNumber());
        assertEquals("1,1:0", pStart.toString());

        Range.Position pEnd = pRange.end();
        assertTrue(pStart.isTracked());
        assertEquals(19, pEnd.pos());
        assertEquals(12, pEnd.columnNumber());
        assertEquals(2, pEnd.lineNumber());
        assertEquals("2,12:19", pEnd.toString());

        assertEquals("3,1:20", span.sourceRange().start().toString());
        assertEquals("3,7:26", span.sourceRange().end().toString());

        // span end tag
        Range spanEnd = span.endSourceRange();
        assertTrue(spanEnd.isTracked());
        assertEquals("5,14:52-5,21:59", spanEnd.toString());

        String wholeText = text.getWholeText();
        assertEquals("Hello\n ®\n there ©.", wholeText);
        String textOrig = "Hello\n &reg;\n there &copy.";
        Range textRange = text.sourceRange();
        assertEquals(textRange.end().pos() -  textRange.start().pos(), textOrig.length());
        assertEquals("3,7:26", textRange.start().toString());
        assertEquals("5,14:52", textRange.end().toString());

        assertEquals("6,2:66", comment.sourceRange().start().toString());
        assertEquals("6,18:82", comment.sourceRange().end().toString());
    }

    @Test void tracksMarkup() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        assertNotNull(doctype);
        assertEquals("html", doctype.name());
        assertEquals("1,1:0-2,6:15", doctype.sourceRange().toString());

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        assertNotNull(titleText);
        assertEquals("jsoup ©\n2022", title.text());
        assertEquals(titleText.getWholeText(), title.text());
        assertEquals("3,1:16-3,8:23", title.sourceRange().toString());
        assertEquals("3,8:23-4,5:40", titleText.sourceRange().toString());

        CDataNode cdata = (CDataNode) doc.body().childNode(1);
        assertEquals("\n<jsoup>\n", cdata.text());
        assertEquals("5,1:55-7,4:76", cdata.sourceRange().toString());
    }

    @Test void tracksDataNodes() {
        String html = "<head>\n<script>foo;\nbar()\n5 <= 4;</script>";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element script = doc.expectFirst("script");
        assertNotNull(script);
        assertEquals("2,1:7-2,9:15", script.sourceRange().toString());
        DataNode data = (DataNode) script.firstChild();
        assertNotNull(data);
        assertEquals("2,9:15-4,8:33", data.sourceRange().toString());
    }

    @Test void tracksXml() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1,1:0-1,39:38", decl.sourceRange().toString());

        DocumentType doctype = (DocumentType) doc.childNode(2);
        assertEquals("2,1:39-2,16:54", doctype.sourceRange().toString());

        Element rss = doc.firstElementChild();
        assertNotNull(rss);
        assertEquals("3,1:55-3,14:68", rss.sourceRange().toString());
        assertEquals("5,1:73-5,7:79", rss.endSourceRange().toString());

        TextNode text = (TextNode) rss.firstChild();
        assertNotNull(text);
        assertEquals("3,14:68-5,1:73", text.sourceRange().toString());

        Comment comment = (Comment) rss.nextSibling().nextSibling();
        assertEquals("6,1:80-6,17:96", comment.sourceRange().toString());
    }

    @Test void tracksFromFetch() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");
        assertNotNull(firstP);
        assertEquals("4,1:53-4,4:56", firstP.sourceRange().toString());

        Element p = doc.expectFirst("#xy");
        assertNotNull(p);
        assertEquals("1000,1:279646-1000,10:279655", p.sourceRange().toString());
        assertEquals("1000,567:280212-1000,571:280216", p.endSourceRange().toString());

        TextNode text = (TextNode) p.firstChild();
        assertNotNull(text);
        assertEquals("1000,10:279655-1000,357:280002", text.sourceRange().toString());
    }

    @Test void tracksFromXmlFetch() throws IOException {
        String url = FileServlet.urlTo("/htmltests/test-rss.xml");
        Document doc = Jsoup.connect(url).parser(Parser.xmlParser().setTrackPosition(true)).get();

        Element item = doc.expectFirst("item + item");
        assertNotNull(item);
        assertEquals("13,5:496-13,11:502", item.sourceRange().toString());
        assertEquals("17,5:779-17,12:786", item.endSourceRange().toString());
    }

    @Test void parserTrackDefaults_1_oe() {
        Parser htmlParser = Parser.htmlParser();
        assertFalse(htmlParser.isTrackPosition());
        }

    @Test void parserTrackDefaults_2_oe() {
        Parser htmlParser = Parser.htmlParser();
        htmlParser.setTrackPosition(true);
        assertTrue(htmlParser.isTrackPosition());
        }

    @Test void parserTrackDefaults_3_oe() {
        Parser htmlParser = Parser.htmlParser();
        htmlParser.setTrackPosition(true);

        Parser xmlParser = Parser.htmlParser();
        assertFalse(xmlParser.isTrackPosition());
        }

    @Test void parserTrackDefaults_4_oe() {
        Parser htmlParser = Parser.htmlParser();
        htmlParser.setTrackPosition(true);

        Parser xmlParser = Parser.htmlParser();
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
        TextNode now = (TextNode) span.nextSibling();
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
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();

        assertFalse(body.sourceRange().isTracked());
        }

    @Test void tracksPosition_5_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


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
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

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
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

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
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();
        assertEquals(0, pStart.pos());
        }

    @Test void tracksPosition_9_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();
        assertEquals(1, pStart.columnNumber());
        }

    @Test void tracksPosition_10_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();
        assertEquals(1, pStart.lineNumber());
        }

    @Test void tracksPosition_11_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();
        assertEquals("1,1:0", pStart.toString());
        }

    @Test void tracksPosition_12_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

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
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();
        assertEquals(19, pEnd.pos());
        }

    @Test void tracksPosition_14_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();
        assertEquals(12, pEnd.columnNumber());
        }

    @Test void tracksPosition_15_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();
        assertEquals(2, pEnd.lineNumber());
        }

    @Test void tracksPosition_16_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();
        assertEquals("2,12:19", pEnd.toString());
        }

    @Test void tracksPosition_17_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();

        assertEquals("3,1:20", span.sourceRange().start().toString());
        }

    @Test void tracksPosition_18_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();

        assertEquals("3,7:26", span.sourceRange().end().toString());
        }

    @Test void tracksPosition_19_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();


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
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();


        Range spanEnd = span.endSourceRange();
        assertEquals("5,14:52-5,21:59", spanEnd.toString());
        }

    @Test void tracksPosition_21_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();


        Range spanEnd = span.endSourceRange();

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
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();


        Range spanEnd = span.endSourceRange();

        String wholeText = text.getWholeText();
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
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();


        Range spanEnd = span.endSourceRange();

        String wholeText = text.getWholeText();
        String textOrig = "Hello\n &reg;\n there &copy.";
        Range textRange = text.sourceRange();
        assertEquals("3,7:26", textRange.start().toString());
        }

    @Test void tracksPosition_24_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();


        Range spanEnd = span.endSourceRange();

        String wholeText = text.getWholeText();
        String textOrig = "Hello\n &reg;\n there &copy.";
        Range textRange = text.sourceRange();
        assertEquals("5,14:52", textRange.end().toString());
        }

    @Test void tracksPosition_25_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();


        Range spanEnd = span.endSourceRange();

        String wholeText = text.getWholeText();
        String textOrig = "Hello\n &reg;\n there &copy.";
        Range textRange = text.sourceRange();

        assertEquals("6,2:66", comment.sourceRange().start().toString());
        }

    @Test void tracksPosition_26_oe() {
        String html = "<p id=1\n class=foo>\n<span>Hello\n &reg;\n there &copy.</span> now.\n <!-- comment --> ";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element body = doc.expectFirst("body");
        Element p = doc.expectFirst("p");
        Element span = doc.expectFirst("span");
        TextNode text = (TextNode) span.firstChild();
        TextNode now = (TextNode) span.nextSibling();
        Comment comment = (Comment) now.nextSibling();


        Range pRange = p.sourceRange();

        Range pEndRange = p.endSourceRange();

        Range.Position pStart = pRange.start();

        Range.Position pEnd = pRange.end();


        Range spanEnd = span.endSourceRange();

        String wholeText = text.getWholeText();
        String textOrig = "Hello\n &reg;\n there &copy.";
        Range textRange = text.sourceRange();

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
        assertEquals("html", doctype.name());
        }

    @Test void tracksMarkup_3_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();
        assertEquals("1,1:0-2,6:15", doctype.sourceRange().toString());
        }

    @Test void tracksMarkup_4_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        assertNotNull(titleText);
        }

    @Test void tracksMarkup_5_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        assertEquals("jsoup ©\n2022", title.text());
        }

    @Test void tracksMarkup_6_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        assertEquals(titleText.getWholeText(), title.text());
        }

    @Test void tracksMarkup_7_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        assertEquals("3,1:16-3,8:23", title.sourceRange().toString());
        }

    @Test void tracksMarkup_8_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();
        assertEquals("3,8:23-4,5:40", titleText.sourceRange().toString());
        }

    @Test void tracksMarkup_9_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();

        CDataNode cdata = (CDataNode) doc.body().childNode(1);
        assertEquals("\n<jsoup>\n", cdata.text());
        }

    @Test void tracksMarkup_10_oe() {
        String html = "<!doctype\nhtml>\n<title>jsoup &copy;\n2022</title><body>\n<![CDATA[\n<jsoup>\n]]>";
        Document doc = Jsoup.parse(html, TrackingParser);

        DocumentType doctype = doc.documentType();

        Element title = doc.expectFirst("title");
        TextNode titleText = (TextNode) title.firstChild();

        CDataNode cdata = (CDataNode) doc.body().childNode(1);
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
        assertEquals("2,1:7-2,9:15", script.sourceRange().toString());
        }

    @Test void tracksDataNodes_3_oe() {
        String html = "<head>\n<script>foo;\nbar()\n5 <= 4;</script>";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element script = doc.expectFirst("script");
        DataNode data = (DataNode) script.firstChild();
        assertNotNull(data);
        }

    @Test void tracksDataNodes_4_oe() {
        String html = "<head>\n<script>foo;\nbar()\n5 <= 4;</script>";
        Document doc = Jsoup.parse(html, TrackingParser);

        Element script = doc.expectFirst("script");
        DataNode data = (DataNode) script.firstChild();
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

        DocumentType doctype = (DocumentType) doc.childNode(2);
        assertEquals("2,1:39-2,16:54", doctype.sourceRange().toString());
        }

    @Test void tracksXml_3_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);

        DocumentType doctype = (DocumentType) doc.childNode(2);

        Element rss = doc.firstElementChild();
        assertNotNull(rss);
        }

    @Test void tracksXml_4_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);

        DocumentType doctype = (DocumentType) doc.childNode(2);

        Element rss = doc.firstElementChild();
        assertEquals("3,1:55-3,14:68", rss.sourceRange().toString());
        }

    @Test void tracksXml_5_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);

        DocumentType doctype = (DocumentType) doc.childNode(2);

        Element rss = doc.firstElementChild();
        assertEquals("5,1:73-5,7:79", rss.endSourceRange().toString());
        }

    @Test void tracksXml_6_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);

        DocumentType doctype = (DocumentType) doc.childNode(2);

        Element rss = doc.firstElementChild();

        TextNode text = (TextNode) rss.firstChild();
        assertNotNull(text);
        }

    @Test void tracksXml_7_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);

        DocumentType doctype = (DocumentType) doc.childNode(2);

        Element rss = doc.firstElementChild();

        TextNode text = (TextNode) rss.firstChild();
        assertEquals("3,14:68-5,1:73", text.sourceRange().toString());
        }

    @Test void tracksXml_8_oe() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!doctype html>\n<rss url=foo>\nXML\n</rss>\n<!-- comment -->";
        Document doc = Jsoup.parse(xml, Parser.xmlParser().setTrackPosition(true));

        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);

        DocumentType doctype = (DocumentType) doc.childNode(2);

        Element rss = doc.firstElementChild();

        TextNode text = (TextNode) rss.firstChild();

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
        assertEquals("4,1:53-4,4:56", firstP.sourceRange().toString());
        }

    @Test void tracksFromFetch_3_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");

        Element p = doc.expectFirst("#xy");
        assertNotNull(p);
        }

    @Test void tracksFromFetch_4_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");

        Element p = doc.expectFirst("#xy");
        assertEquals("1000,1:279646-1000,10:279655", p.sourceRange().toString());
        }

    @Test void tracksFromFetch_5_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");

        Element p = doc.expectFirst("#xy");
        assertEquals("1000,567:280212-1000,571:280216", p.endSourceRange().toString());
        }

    @Test void tracksFromFetch_6_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");

        Element p = doc.expectFirst("#xy");

        TextNode text = (TextNode) p.firstChild();
        assertNotNull(text);
        }

    @Test void tracksFromFetch_7_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Document doc = Jsoup.connect(url).parser(TrackingParser).get();

        Element firstP = doc.expectFirst("p");

        Element p = doc.expectFirst("#xy");

        TextNode text = (TextNode) p.firstChild();
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
        assertEquals("13,5:496-13,11:502", item.sourceRange().toString());
        }

    @Test void tracksFromXmlFetch_3_oe() throws IOException {
        String url = FileServlet.urlTo("/htmltests/test-rss.xml");
        Document doc = Jsoup.connect(url).parser(Parser.xmlParser().setTrackPosition(true)).get();

        Element item = doc.expectFirst("item + item");
        assertEquals("17,5:779-17,12:786", item.endSourceRange().toString());
        }

}