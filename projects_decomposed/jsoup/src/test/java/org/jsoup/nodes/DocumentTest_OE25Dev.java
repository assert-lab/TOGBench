package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.integration.ParseTest;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Document.OutputSettings.Syntax;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tests for Document.

 @author Jonathan Hedley, jonathan@hedley.net */
public class DocumentTest_OE25Dev {
    private static final String charsetUtf8 = "UTF-8";
    private static final String charsetIso8859 = "ISO-8859-1";

    private Document createHtmlDocument(String charset) {
        final Document doc = Document.createShell("");
        doc.head().appendElement("meta").attr("charset", charset);
        doc.head().appendElement("meta").attr("name", "charset").attr("content", charset);

        return doc;
    }

    private Document createXmlDocument(String version, String charset, boolean addDecl) {
        final Document doc = new Document("");
        doc.appendElement("root").text("node");
        doc.outputSettings().syntax(Syntax.xml);

        if(addDecl) {
            XmlDeclaration decl = new XmlDeclaration("xml", false);
            decl.attr("version", version);
            decl.attr("encoding", charset);
            doc.prependChild(decl);
        }

        return doc;
    }

@Test public void setTextPreservesDocumentStructure_1_oe() {
        Document doc = Jsoup.parse("<p>Hello</p>");
        doc.text("Replaced");
        assertEquals("Replaced", doc.text());
        }

@Test public void setTextPreservesDocumentStructure_2_oe() {
        Document doc = Jsoup.parse("<p>Hello</p>");
        doc.text("Replaced");
        // removed other assertion
        assertEquals("Replaced", doc.body().text());
        }

@Test public void setTextPreservesDocumentStructure_3_oe() {
        Document doc = Jsoup.parse("<p>Hello</p>");
        doc.text("Replaced");
        // removed other assertion
        // removed other assertion
        assertEquals(1, doc.select("head").size());
        }

@Test public void testTitles_1_oe() {
        Document noTitle = Jsoup.parse("<p>Hello</p>");
        Document withTitle = Jsoup.parse("<title>First</title><title>Ignore</title><p>Hello</p>");

        assertEquals("", noTitle.title());
        }

@Test public void testTitles_2_oe() {
        Document noTitle = Jsoup.parse("<p>Hello</p>");
        Document withTitle = Jsoup.parse("<title>First</title><title>Ignore</title><p>Hello</p>");

        // removed other assertion
        noTitle.title("Hello");
        assertEquals("Hello", noTitle.title());
        }

@Test public void testTitles_3_oe() {
        Document noTitle = Jsoup.parse("<p>Hello</p>");
        Document withTitle = Jsoup.parse("<title>First</title><title>Ignore</title><p>Hello</p>");

        // removed other assertion
        noTitle.title("Hello");
        // removed other assertion
        assertEquals("Hello", noTitle.select("title").first().text());
        }

@Test public void testTitles_4_oe() {
        Document noTitle = Jsoup.parse("<p>Hello</p>");
        Document withTitle = Jsoup.parse("<title>First</title><title>Ignore</title><p>Hello</p>");

        // removed other assertion
        noTitle.title("Hello");
        // removed other assertion
        // removed other assertion

        assertEquals("First", withTitle.title());
        }

@Test public void testTitles_5_oe() {
        Document noTitle = Jsoup.parse("<p>Hello</p>");
        Document withTitle = Jsoup.parse("<title>First</title><title>Ignore</title><p>Hello</p>");

        // removed other assertion
        noTitle.title("Hello");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        withTitle.title("Hello");
        assertEquals("Hello", withTitle.title());
        }

@Test public void testTitles_6_oe() {
        Document noTitle = Jsoup.parse("<p>Hello</p>");
        Document withTitle = Jsoup.parse("<title>First</title><title>Ignore</title><p>Hello</p>");

        // removed other assertion
        noTitle.title("Hello");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        withTitle.title("Hello");
        // removed other assertion
        assertEquals("Hello", withTitle.select("title").first().text());
        }

@Test public void testTitles_7_oe() {
        Document noTitle = Jsoup.parse("<p>Hello</p>");
        Document withTitle = Jsoup.parse("<title>First</title><title>Ignore</title><p>Hello</p>");

        // removed other assertion
        noTitle.title("Hello");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        withTitle.title("Hello");
        // removed other assertion
        // removed other assertion

        Document normaliseTitle = Jsoup.parse("<title>   Hello\nthere   \n   now   \n");
        assertEquals("Hello there now", normaliseTitle.title());
        }

@Test public void testOutputEncoding_1_oe() {
        Document doc = Jsoup.parse("<p title=π>π & < > </p>");
        // default is utf-8
        assertEquals("<p title=\"π\">π &amp; &lt; &gt;</p>", doc.body().html());
        }

@Test public void testOutputEncoding_2_oe() {
        Document doc = Jsoup.parse("<p title=π>π & < > </p>");
        // default is utf-8
        // removed other assertion
        assertEquals("UTF-8", doc.outputSettings().charset().name());
        }

@Test public void testOutputEncoding_3_oe() {
        Document doc = Jsoup.parse("<p title=π>π & < > </p>");
        // default is utf-8
        // removed other assertion
        // removed other assertion

        doc.outputSettings().charset("ascii");
        assertEquals(Entities.EscapeMode.base, doc.outputSettings().escapeMode());
        }

@Test public void testOutputEncoding_4_oe() {
        Document doc = Jsoup.parse("<p title=π>π & < > </p>");
        // default is utf-8
        // removed other assertion
        // removed other assertion

        doc.outputSettings().charset("ascii");
        // removed other assertion
        assertEquals("<p title=\"&#x3c0;\">&#x3c0; &amp; &lt; &gt;</p>", doc.body().html());
        }

@Test public void testOutputEncoding_5_oe() {
        Document doc = Jsoup.parse("<p title=π>π & < > </p>");
        // default is utf-8
        // removed other assertion
        // removed other assertion

        doc.outputSettings().charset("ascii");
        // removed other assertion
        // removed other assertion

        doc.outputSettings().escapeMode(Entities.EscapeMode.extended);
        assertEquals("<p title=\"&pi;\">&pi; &amp; &lt; &gt;</p>", doc.body().html());
        }

@Test public void testXhtmlReferences_1_oe() {
        Document doc = Jsoup.parse("&lt; &gt; &amp; &quot; &apos; &times;");
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        assertEquals("&lt; &gt; &amp; \" ' ×", doc.body().html());
        }

@Test public void testNormalisesStructure_1_oe() {
        Document doc = Jsoup.parse("<html><head><script>one</script><noscript><p>two</p></noscript></head><body><p>three</p></body><p>four</p></html>");
        assertEquals("<html><head><script>one</script><noscript>&lt;p&gt;two</noscript></head><body><p>three</p><p>four</p></body></html>", TextUtil.stripNewlines(doc.html()));
        }

@Test public void accessorsWillNormalizeStructure_1_oe() {
        Document doc = new Document("");
        assertEquals("", doc.html());
        }

@Test public void accessorsWillNormalizeStructure_2_oe() {
        Document doc = new Document("");
        // removed other assertion

        Element body = doc.body();
        assertEquals("body", body.tagName());
        }

@Test public void accessorsWillNormalizeStructure_3_oe() {
        Document doc = new Document("");
        // removed other assertion

        Element body = doc.body();
        // removed other assertion
        Element head = doc.head();
        assertEquals("head", head.tagName());
        }

@Test public void accessorsWillNormalizeStructure_4_oe() {
        Document doc = new Document("");
        // removed other assertion

        Element body = doc.body();
        // removed other assertion
        Element head = doc.head();
        // removed other assertion
        assertEquals("<html><head></head><body></body></html>", TextUtil.stripNewlines(doc.html()));
        }

@Test public void accessorsAreCaseInsensitive_1_oe() {
        Parser parser = Parser.htmlParser().settings(ParseSettings.preserveCase);
        Document doc = parser.parseInput("<!DOCTYPE html><HTML><HEAD><TITLE>SHOUTY</TITLE></HEAD><BODY>HELLO</BODY></HTML>", "");

        Element body = doc.body();
        assertEquals("BODY", body.tagName());
        }

@Test public void accessorsAreCaseInsensitive_2_oe() {
        Parser parser = Parser.htmlParser().settings(ParseSettings.preserveCase);
        Document doc = parser.parseInput("<!DOCTYPE html><HTML><HEAD><TITLE>SHOUTY</TITLE></HEAD><BODY>HELLO</BODY></HTML>", "");

        Element body = doc.body();
        // removed other assertion
        assertEquals("body", body.normalName());
        }

@Test public void accessorsAreCaseInsensitive_3_oe() {
        Parser parser = Parser.htmlParser().settings(ParseSettings.preserveCase);
        Document doc = parser.parseInput("<!DOCTYPE html><HTML><HEAD><TITLE>SHOUTY</TITLE></HEAD><BODY>HELLO</BODY></HTML>", "");

        Element body = doc.body();
        // removed other assertion
        // removed other assertion
        Element head = doc.head();
        assertEquals("HEAD", head.tagName());
        }

@Test public void accessorsAreCaseInsensitive_4_oe() {
        Parser parser = Parser.htmlParser().settings(ParseSettings.preserveCase);
        Document doc = parser.parseInput("<!DOCTYPE html><HTML><HEAD><TITLE>SHOUTY</TITLE></HEAD><BODY>HELLO</BODY></HTML>", "");

        Element body = doc.body();
        // removed other assertion
        // removed other assertion
        Element head = doc.head();
        // removed other assertion
        assertEquals("body", body.normalName());
        }

@Test public void accessorsAreCaseInsensitive_5_oe() {
        Parser parser = Parser.htmlParser().settings(ParseSettings.preserveCase);
        Document doc = parser.parseInput("<!DOCTYPE html><HTML><HEAD><TITLE>SHOUTY</TITLE></HEAD><BODY>HELLO</BODY></HTML>", "");

        Element body = doc.body();
        // removed other assertion
        // removed other assertion
        Element head = doc.head();
        // removed other assertion
        // removed other assertion

        Element root = doc.selectFirst("html");
        assertEquals("HTML", root.tagName());
        }

@Test public void accessorsAreCaseInsensitive_6_oe() {
        Parser parser = Parser.htmlParser().settings(ParseSettings.preserveCase);
        Document doc = parser.parseInput("<!DOCTYPE html><HTML><HEAD><TITLE>SHOUTY</TITLE></HEAD><BODY>HELLO</BODY></HTML>", "");

        Element body = doc.body();
        // removed other assertion
        // removed other assertion
        Element head = doc.head();
        // removed other assertion
        // removed other assertion

        Element root = doc.selectFirst("html");
        // removed other assertion
        assertEquals("html", root.normalName());
        }

@Test public void accessorsAreCaseInsensitive_7_oe() {
        Parser parser = Parser.htmlParser().settings(ParseSettings.preserveCase);
        Document doc = parser.parseInput("<!DOCTYPE html><HTML><HEAD><TITLE>SHOUTY</TITLE></HEAD><BODY>HELLO</BODY></HTML>", "");

        Element body = doc.body();
        // removed other assertion
        // removed other assertion
        Element head = doc.head();
        // removed other assertion
        // removed other assertion

        Element root = doc.selectFirst("html");
        // removed other assertion
        // removed other assertion
        assertEquals("SHOUTY", doc.title());
        }

@Test public void testClone_1_oe() {
        Document doc = Jsoup.parse("<title>Hello</title> <p>One<p>Two");
        Document clone = doc.clone();

        assertEquals("<html><head><title>Hello</title></head><body><p>One</p><p>Two</p></body></html>", TextUtil.stripNewlines(clone.html()));
        }

@Test public void testClone_2_oe() {
        Document doc = Jsoup.parse("<title>Hello</title> <p>One<p>Two");
        Document clone = doc.clone();

        // removed other assertion
        clone.title("Hello there");
        clone.expectFirst("p").text("One more").attr("id", "1");
        assertEquals("<html><head><title>Hello there</title></head><body><p id=\"1\">One more</p><p>Two</p></body></html>", TextUtil.stripNewlines(clone.html()));
        }

@Test public void testClone_3_oe() {
        Document doc = Jsoup.parse("<title>Hello</title> <p>One<p>Two");
        Document clone = doc.clone();

        // removed other assertion
        clone.title("Hello there");
        clone.expectFirst("p").text("One more").attr("id", "1");
        // removed other assertion
        assertEquals("<html><head><title>Hello</title></head><body><p>One</p><p>Two</p></body></html>", TextUtil.stripNewlines(doc.html()));
        }

@Test void testBasicIndent_1_oe() {
        Document doc = Jsoup.parse("<title>Hello</title> <p>One<p>Two");
        String expect = "<html>\n <head>\n  <title>Hello</title>\n </head>\n <body>\n  <p>One</p>\n  <p>Two</p>\n </body>\n</html>";
        assertEquals(expect, doc.html());
        }

@Test public void testClonesDeclarations_1_oe() {
        Document doc = Jsoup.parse("<!DOCTYPE html><html><head><title>Doctype test");
        Document clone = doc.clone();

        assertEquals(doc.html(), clone.html());
        }

@Test public void testClonesDeclarations_2_oe() {
        Document doc = Jsoup.parse("<!DOCTYPE html><html><head><title>Doctype test");
        Document clone = doc.clone();

        // removed other assertion
        assertEquals("<!doctype html><html><head><title>Doctype test</title></head><body></body></html>",TextUtil.stripNewlines(clone.html()));
        }

@Test public void testLocation_1_oe() throws IOException {
    	File in = ParseTest.getFile("/htmltests/yahoo-jp.html.gz");
        Document doc = Jsoup.parse(in, "UTF-8", "http://www.yahoo.co.jp/index.html");
        String location = doc.location();
        String baseUri = doc.baseUri();
        assertEquals("http://www.yahoo.co.jp/index.html",location);
    	}

@Test public void testLocation_2_oe() throws IOException {
    	File in = ParseTest.getFile("/htmltests/yahoo-jp.html.gz");
        Document doc = Jsoup.parse(in, "UTF-8", "http://www.yahoo.co.jp/index.html");
        String location = doc.location();
        String baseUri = doc.baseUri();
        // removed other assertion
        assertEquals("http://www.yahoo.co.jp/_ylh=X3oDMTB0NWxnaGxsBF9TAzIwNzcyOTYyNjUEdGlkAzEyBHRtcGwDZ2Ex/",baseUri);
    	}

@Test public void testLocation_3_oe() throws IOException {
    	File in = ParseTest.getFile("/htmltests/yahoo-jp.html.gz");
        Document doc = Jsoup.parse(in, "UTF-8", "http://www.yahoo.co.jp/index.html");
        String location = doc.location();
        String baseUri = doc.baseUri();
        // removed other assertion
        // removed other assertion
        in = ParseTest.getFile("/htmltests/nyt-article-1.html.gz");
        doc = Jsoup.parse(in, null, "http://www.nytimes.com/2010/07/26/business/global/26bp.html?hp");
        location = doc.location();
        baseUri = doc.baseUri();
        assertEquals("http://www.nytimes.com/2010/07/26/business/global/26bp.html?hp",location);
    	}

@Test public void testLocation_4_oe() throws IOException {
    	File in = ParseTest.getFile("/htmltests/yahoo-jp.html.gz");
        Document doc = Jsoup.parse(in, "UTF-8", "http://www.yahoo.co.jp/index.html");
        String location = doc.location();
        String baseUri = doc.baseUri();
        // removed other assertion
        // removed other assertion
        in = ParseTest.getFile("/htmltests/nyt-article-1.html.gz");
        doc = Jsoup.parse(in, null, "http://www.nytimes.com/2010/07/26/business/global/26bp.html?hp");
        location = doc.location();
        baseUri = doc.baseUri();
        // removed other assertion
        assertEquals("http://www.nytimes.com/2010/07/26/business/global/26bp.html?hp",baseUri);
    	}

@Test public void testLocationFromString_1_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        assertEquals("", doc.location());
        }

@Test public void testHtmlAndXmlSyntax_1_oe() {
        String h = "<!DOCTYPE html><body><img async checked='checked' src='&<>\"'>&lt;&gt;&amp;&quot;<foo />bar";
        Document doc = Jsoup.parse(h);

        doc.outputSettings().syntax(Syntax.html);
        assertEquals("<!doctype html>\n" + "<html>\n" + " <head></head>\n" + " <body>\n" + " <img async checked src=\"&amp;<>&quot;\">&lt;&gt;&amp;\"<foo />bar\n" + " </body>\n" + "</html>",doc.html());
        }

@Test public void testHtmlAndXmlSyntax_2_oe() {
        String h = "<!DOCTYPE html><body><img async checked='checked' src='&<>\"'>&lt;&gt;&amp;&quot;<foo />bar";
        Document doc = Jsoup.parse(h);

        doc.outputSettings().syntax(Syntax.html);
        // removed other assertion

        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        assertEquals("<!DOCTYPE html>\n" + "<html>\n" + " <head></head>\n" + " <body>\n" + " <img async=\"\" checked=\"checked\" src=\"&amp;&lt;>&quot;\" />&lt;&gt;&amp;\"<foo />bar\n" + " </body>\n" + "</html>",doc.html());
        }

@Test public void htmlParseDefaultsToHtmlOutputSyntax_1_oe() {
        Document doc = Jsoup.parse("x");
        assertEquals(Syntax.html, doc.outputSettings().syntax());
        }

@Test public void testHtmlAppendable_1_oe() {
    	String htmlContent = "<html><head><title>Hello</title></head><body><p>One</p><p>Two</p></body></html>";
    	Document document = Jsoup.parse(htmlContent);
    	OutputSettings outputSettings = new OutputSettings();

    	outputSettings.prettyPrint(false);
    	document.outputSettings(outputSettings);
    	assertEquals(htmlContent, document.html(new StringWriter()).toString());
    	}

@Test public void testOverflowClone_1_oe() {
        StringBuilder sb = new StringBuilder();
        sb.append("<head><base href='https://jsoup.org/'>");
        for (int i = 0; i < 100000; i++) {
            sb.append("<div>");
        }
        sb.append("<p>Hello <a href='/example.html'>there</a>");

        Document doc = Jsoup.parse(sb.toString());

        String expectedLink = "https://jsoup.org/example.html";
        assertEquals(expectedLink, doc.selectFirst("a").attr("abs:href"));
        }

@Test public void testOverflowClone_2_oe() {
        StringBuilder sb = new StringBuilder();
        sb.append("<head><base href='https://jsoup.org/'>");
        for (int i = 0; i < 100000; i++) {
            sb.append("<div>");
        }
        sb.append("<p>Hello <a href='/example.html'>there</a>");

        Document doc = Jsoup.parse(sb.toString());

        String expectedLink = "https://jsoup.org/example.html";
        // removed other assertion
        Document clone = doc.clone();
        doc.hasSameValue(clone);
        assertEquals(expectedLink, clone.selectFirst("a").attr("abs:href"));
        }

@Test public void DocumentsWithSameContentAreEqual_1_oe() {
        Document docA = Jsoup.parse("<div/>One");
        Document docB = Jsoup.parse("<div/>One");
        Document docC = Jsoup.parse("<div/>Two");

        assertNotEquals(docA, docB);
        }

@Test public void DocumentsWithSameContentAreEqual_2_oe() {
        Document docA = Jsoup.parse("<div/>One");
        Document docB = Jsoup.parse("<div/>One");
        Document docC = Jsoup.parse("<div/>Two");

        // removed other assertion
        assertEquals(docA, docA);
        }

@Test public void DocumentsWithSameContentAreEqual_3_oe() {
        Document docA = Jsoup.parse("<div/>One");
        Document docB = Jsoup.parse("<div/>One");
        Document docC = Jsoup.parse("<div/>Two");

        // removed other assertion
        // removed other assertion
        assertEquals(docA.hashCode(), docA.hashCode());
        }

@Test public void DocumentsWithSameContentAreEqual_4_oe() {
        Document docA = Jsoup.parse("<div/>One");
        Document docB = Jsoup.parse("<div/>One");
        Document docC = Jsoup.parse("<div/>Two");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(docA.hashCode(), docC.hashCode());
        }

@Test public void DocumentsWithSameContentAreVerifiable_1_oe() {
        Document docA = Jsoup.parse("<div/>One");
        Document docB = Jsoup.parse("<div/>One");
        Document docC = Jsoup.parse("<div/>Two");

        assertTrue(docA.hasSameValue(docB));
        }

@Test public void DocumentsWithSameContentAreVerifiable_2_oe() {
        Document docA = Jsoup.parse("<div/>One");
        Document docB = Jsoup.parse("<div/>One");
        Document docC = Jsoup.parse("<div/>Two");

        // removed other assertion
        assertFalse(docA.hasSameValue(docC));
        }

@Test
    public void testMetaCharsetUpdateUtf8_1_oe() {
        final Document doc = createHtmlDocument("changeThis");
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String htmlCharsetUTF8 = "<html>\n" +
                                        " <head>\n" +
                                        "  <meta charset=\"" + charsetUtf8 + "\">\n" +
                                        " </head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        assertEquals(htmlCharsetUTF8, doc.toString());
    }

@Test
    public void testMetaCharsetUpdateUtf8_2_oe() {
        final Document doc = createHtmlDocument("changeThis");
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String htmlCharsetUTF8 = "<html>\n" +
                                        " <head>\n" +
                                        "  <meta charset=\"" + charsetUtf8 + "\">\n" +
                                        " </head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        // removed other assertion

        Element selectedElement = doc.select("meta[charset]").first();
        assertEquals(charsetUtf8, doc.charset().name());
    }

@Test
    public void testMetaCharsetUpdateUtf8_3_oe() {
        final Document doc = createHtmlDocument("changeThis");
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String htmlCharsetUTF8 = "<html>\n" +
                                        " <head>\n" +
                                        "  <meta charset=\"" + charsetUtf8 + "\">\n" +
                                        " </head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        // removed other assertion

        Element selectedElement = doc.select("meta[charset]").first();
        // removed other assertion
        assertEquals(charsetUtf8, selectedElement.attr("charset"));
    }

@Test
    public void testMetaCharsetUpdateUtf8_4_oe() {
        final Document doc = createHtmlDocument("changeThis");
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String htmlCharsetUTF8 = "<html>\n" +
                                        " <head>\n" +
                                        "  <meta charset=\"" + charsetUtf8 + "\">\n" +
                                        " </head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        // removed other assertion

        Element selectedElement = doc.select("meta[charset]").first();
        // removed other assertion
        // removed other assertion
        assertEquals(doc.charset(), doc.outputSettings().charset());
    }

@Test
    public void testMetaCharsetUpdateIso8859_1_oe() {
        final Document doc = createHtmlDocument("changeThis");
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetIso8859));

        final String htmlCharsetISO = "<html>\n" +
                                        " <head>\n" +
                                        "  <meta charset=\"" + charsetIso8859 + "\">\n" +
                                        " </head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        assertEquals(htmlCharsetISO, doc.toString());
    }

@Test
    public void testMetaCharsetUpdateIso8859_2_oe() {
        final Document doc = createHtmlDocument("changeThis");
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetIso8859));

        final String htmlCharsetISO = "<html>\n" +
                                        " <head>\n" +
                                        "  <meta charset=\"" + charsetIso8859 + "\">\n" +
                                        " </head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        // removed other assertion

        Element selectedElement = doc.select("meta[charset]").first();
        assertEquals(charsetIso8859, doc.charset().name());
    }

@Test
    public void testMetaCharsetUpdateIso8859_3_oe() {
        final Document doc = createHtmlDocument("changeThis");
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetIso8859));

        final String htmlCharsetISO = "<html>\n" +
                                        " <head>\n" +
                                        "  <meta charset=\"" + charsetIso8859 + "\">\n" +
                                        " </head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        // removed other assertion

        Element selectedElement = doc.select("meta[charset]").first();
        // removed other assertion
        assertEquals(charsetIso8859, selectedElement.attr("charset"));
    }

@Test
    public void testMetaCharsetUpdateIso8859_4_oe() {
        final Document doc = createHtmlDocument("changeThis");
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetIso8859));

        final String htmlCharsetISO = "<html>\n" +
                                        " <head>\n" +
                                        "  <meta charset=\"" + charsetIso8859 + "\">\n" +
                                        " </head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        // removed other assertion

        Element selectedElement = doc.select("meta[charset]").first();
        // removed other assertion
        // removed other assertion
        assertEquals(doc.charset(), doc.outputSettings().charset());
    }

@Test
    public void testMetaCharsetUpdateNoCharset_1_oe() {
        final Document docNoCharset = Document.createShell("");
        docNoCharset.updateMetaCharsetElement(true);
        docNoCharset.charset(Charset.forName(charsetUtf8));

        assertEquals(charsetUtf8, docNoCharset.select("meta[charset]").first().attr("charset"));
    }

@Test
    public void testMetaCharsetUpdateNoCharset_2_oe() {
        final Document docNoCharset = Document.createShell("");
        docNoCharset.updateMetaCharsetElement(true);
        docNoCharset.charset(Charset.forName(charsetUtf8));

        // removed other assertion

        final String htmlCharsetUTF8 = "<html>\n" +
                                        " <head>\n" +
                                        "  <meta charset=\"" + charsetUtf8 + "\">\n" +
                                        " </head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        assertEquals(htmlCharsetUTF8, docNoCharset.toString());
    }

@Test
    public void testMetaCharsetUpdateDisabled_1_oe() {
        final Document docDisabled = Document.createShell("");

        final String htmlNoCharset = "<html>\n" +
                                        " <head></head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        assertEquals(htmlNoCharset, docDisabled.toString());
    }

@Test
    public void testMetaCharsetUpdateDisabled_2_oe() {
        final Document docDisabled = Document.createShell("");

        final String htmlNoCharset = "<html>\n" +
                                        " <head></head>\n" +
                                        " <body></body>\n" +
                                        "</html>";
        // removed other assertion
        assertNull(docDisabled.select("meta[charset]").first());
    }

@Test
    public void testMetaCharsetUpdateDisabledNoChanges_1_oe() {
        final Document doc = createHtmlDocument("dontTouch");

        final String htmlCharset = "<html>\n" +
                                    " <head>\n" +
                                    "  <meta charset=\"dontTouch\">\n" +
                                    "  <meta name=\"charset\" content=\"dontTouch\">\n" +
                                    " </head>\n" +
                                    " <body></body>\n" +
                                    "</html>";
        assertEquals(htmlCharset, doc.toString());
    }

@Test
    public void testMetaCharsetUpdateDisabledNoChanges_2_oe() {
        final Document doc = createHtmlDocument("dontTouch");

        final String htmlCharset = "<html>\n" +
                                    " <head>\n" +
                                    "  <meta charset=\"dontTouch\">\n" +
                                    "  <meta name=\"charset\" content=\"dontTouch\">\n" +
                                    " </head>\n" +
                                    " <body></body>\n" +
                                    "</html>";
        // removed other assertion

        Element selectedElement = doc.select("meta[charset]").first();
        assertNotNull(selectedElement);
    }

@Test
    public void testMetaCharsetUpdateDisabledNoChanges_3_oe() {
        final Document doc = createHtmlDocument("dontTouch");

        final String htmlCharset = "<html>\n" +
                                    " <head>\n" +
                                    "  <meta charset=\"dontTouch\">\n" +
                                    "  <meta name=\"charset\" content=\"dontTouch\">\n" +
                                    " </head>\n" +
                                    " <body></body>\n" +
                                    "</html>";
        // removed other assertion

        Element selectedElement = doc.select("meta[charset]").first();
        // removed other assertion
        assertEquals("dontTouch", selectedElement.attr("charset"));
    }

@Test
    public void testMetaCharsetUpdateDisabledNoChanges_4_oe() {
        final Document doc = createHtmlDocument("dontTouch");

        final String htmlCharset = "<html>\n" +
                                    " <head>\n" +
                                    "  <meta charset=\"dontTouch\">\n" +
                                    "  <meta name=\"charset\" content=\"dontTouch\">\n" +
                                    " </head>\n" +
                                    " <body></body>\n" +
                                    "</html>";
        // removed other assertion

        Element selectedElement = doc.select("meta[charset]").first();
        // removed other assertion
        // removed other assertion

        selectedElement = doc.select("meta[name=charset]").first();
        assertNotNull(selectedElement);
    }

@Test
    public void testMetaCharsetUpdateDisabledNoChanges_5_oe() {
        final Document doc = createHtmlDocument("dontTouch");

        final String htmlCharset = "<html>\n" +
                                    " <head>\n" +
                                    "  <meta charset=\"dontTouch\">\n" +
                                    "  <meta name=\"charset\" content=\"dontTouch\">\n" +
                                    " </head>\n" +
                                    " <body></body>\n" +
                                    "</html>";
        // removed other assertion

        Element selectedElement = doc.select("meta[charset]").first();
        // removed other assertion
        // removed other assertion

        selectedElement = doc.select("meta[name=charset]").first();
        // removed other assertion
        assertEquals("dontTouch", selectedElement.attr("content"));
    }

@Test
    public void testMetaCharsetUpdateEnabledAfterCharsetChange_1_oe() {
        final Document doc = createHtmlDocument("dontTouch");
        doc.charset(Charset.forName(charsetUtf8));

        Element selectedElement = doc.select("meta[charset]").first();
        assertEquals(charsetUtf8, selectedElement.attr("charset"));
    }

@Test
    public void testMetaCharsetUpdateEnabledAfterCharsetChange_2_oe() {
        final Document doc = createHtmlDocument("dontTouch");
        doc.charset(Charset.forName(charsetUtf8));

        Element selectedElement = doc.select("meta[charset]").first();
        // removed other assertion
        assertTrue(doc.select("meta[name=charset]").isEmpty());
    }

@Test
    public void testMetaCharsetUpdateCleanup_1_oe() {
        final Document doc = createHtmlDocument("dontTouch");
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String htmlCharsetUTF8 = "<html>\n" +
                                        " <head>\n" +
                                        "  <meta charset=\"" + charsetUtf8 + "\">\n" +
                                        " </head>\n" +
                                        " <body></body>\n" +
                                        "</html>";

        assertEquals(htmlCharsetUTF8, doc.toString());
    }

@Test
    public void testMetaCharsetUpdateXmlUtf8_1_oe() {
        final Document doc = createXmlDocument("1.0", "changeThis", true);
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String xmlCharsetUTF8 = "<?xml version=\"1.0\" encoding=\"" + charsetUtf8 + "\"?>\n" +
                                        "<root>\n" +
                                        " node\n" +
                                        "</root>";
        assertEquals(xmlCharsetUTF8, doc.toString());
    }

@Test
    public void testMetaCharsetUpdateXmlUtf8_2_oe() {
        final Document doc = createXmlDocument("1.0", "changeThis", true);
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String xmlCharsetUTF8 = "<?xml version=\"1.0\" encoding=\"" + charsetUtf8 + "\"?>\n" +
                                        "<root>\n" +
                                        " node\n" +
                                        "</root>";
        // removed other assertion

        XmlDeclaration selectedNode = (XmlDeclaration) doc.childNode(0);
        assertEquals(charsetUtf8, doc.charset().name());
    }

@Test
    public void testMetaCharsetUpdateXmlUtf8_3_oe() {
        final Document doc = createXmlDocument("1.0", "changeThis", true);
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String xmlCharsetUTF8 = "<?xml version=\"1.0\" encoding=\"" + charsetUtf8 + "\"?>\n" +
                                        "<root>\n" +
                                        " node\n" +
                                        "</root>";
        // removed other assertion

        XmlDeclaration selectedNode = (XmlDeclaration) doc.childNode(0);
        // removed other assertion
        assertEquals(charsetUtf8, selectedNode.attr("encoding"));
    }

@Test
    public void testMetaCharsetUpdateXmlUtf8_4_oe() {
        final Document doc = createXmlDocument("1.0", "changeThis", true);
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String xmlCharsetUTF8 = "<?xml version=\"1.0\" encoding=\"" + charsetUtf8 + "\"?>\n" +
                                        "<root>\n" +
                                        " node\n" +
                                        "</root>";
        // removed other assertion

        XmlDeclaration selectedNode = (XmlDeclaration) doc.childNode(0);
        // removed other assertion
        // removed other assertion
        assertEquals(doc.charset(), doc.outputSettings().charset());
    }

@Test
    public void testMetaCharsetUpdateXmlIso8859_1_oe() {
        final Document doc = createXmlDocument("1.0", "changeThis", true);
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetIso8859));

        final String xmlCharsetISO = "<?xml version=\"1.0\" encoding=\"" + charsetIso8859 + "\"?>\n" +
                                        "<root>\n" +
                                        " node\n" +
                                        "</root>";
        assertEquals(xmlCharsetISO, doc.toString());
    }

@Test
    public void testMetaCharsetUpdateXmlIso8859_2_oe() {
        final Document doc = createXmlDocument("1.0", "changeThis", true);
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetIso8859));

        final String xmlCharsetISO = "<?xml version=\"1.0\" encoding=\"" + charsetIso8859 + "\"?>\n" +
                                        "<root>\n" +
                                        " node\n" +
                                        "</root>";
        // removed other assertion

        XmlDeclaration selectedNode = (XmlDeclaration) doc.childNode(0);
        assertEquals(charsetIso8859, doc.charset().name());
    }

@Test
    public void testMetaCharsetUpdateXmlIso8859_3_oe() {
        final Document doc = createXmlDocument("1.0", "changeThis", true);
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetIso8859));

        final String xmlCharsetISO = "<?xml version=\"1.0\" encoding=\"" + charsetIso8859 + "\"?>\n" +
                                        "<root>\n" +
                                        " node\n" +
                                        "</root>";
        // removed other assertion

        XmlDeclaration selectedNode = (XmlDeclaration) doc.childNode(0);
        // removed other assertion
        assertEquals(charsetIso8859, selectedNode.attr("encoding"));
    }

@Test
    public void testMetaCharsetUpdateXmlIso8859_4_oe() {
        final Document doc = createXmlDocument("1.0", "changeThis", true);
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetIso8859));

        final String xmlCharsetISO = "<?xml version=\"1.0\" encoding=\"" + charsetIso8859 + "\"?>\n" +
                                        "<root>\n" +
                                        " node\n" +
                                        "</root>";
        // removed other assertion

        XmlDeclaration selectedNode = (XmlDeclaration) doc.childNode(0);
        // removed other assertion
        // removed other assertion
        assertEquals(doc.charset(), doc.outputSettings().charset());
    }

@Test
    public void testMetaCharsetUpdateXmlNoCharset_1_oe() {
        final Document doc = createXmlDocument("1.0", "none", false);
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String xmlCharsetUTF8 = "<?xml version=\"1.0\" encoding=\"" + charsetUtf8 + "\"?>\n" +
                                        "<root>\n" +
                                        " node\n" +
                                        "</root>";
        assertEquals(xmlCharsetUTF8, doc.toString());
    }

@Test
    public void testMetaCharsetUpdateXmlNoCharset_2_oe() {
        final Document doc = createXmlDocument("1.0", "none", false);
        doc.updateMetaCharsetElement(true);
        doc.charset(Charset.forName(charsetUtf8));

        final String xmlCharsetUTF8 = "<?xml version=\"1.0\" encoding=\"" + charsetUtf8 + "\"?>\n" +
                                        "<root>\n" +
                                        " node\n" +
                                        "</root>";
        // removed other assertion

        XmlDeclaration selectedNode = (XmlDeclaration) doc.childNode(0);
        assertEquals(charsetUtf8, selectedNode.attr("encoding"));
    }

@Test
    public void testMetaCharsetUpdateXmlDisabled_1_oe() {
        final Document doc = createXmlDocument("none", "none", false);

        final String xmlNoCharset = "<root>\n" +
                                    " node\n" +
                                    "</root>";
        assertEquals(xmlNoCharset, doc.toString());
    }

@Test
    public void testMetaCharsetUpdateXmlDisabledNoChanges_1_oe() {
        final Document doc = createXmlDocument("dontTouch", "dontTouch", true);

        final String xmlCharset = "<?xml version=\"dontTouch\" encoding=\"dontTouch\"?>\n" +
                                    "<root>\n" +
                                    " node\n" +
                                    "</root>";
        assertEquals(xmlCharset, doc.toString());
    }

@Test
    public void testMetaCharsetUpdateXmlDisabledNoChanges_2_oe() {
        final Document doc = createXmlDocument("dontTouch", "dontTouch", true);

        final String xmlCharset = "<?xml version=\"dontTouch\" encoding=\"dontTouch\"?>\n" +
                                    "<root>\n" +
                                    " node\n" +
                                    "</root>";
        // removed other assertion

        XmlDeclaration selectedNode = (XmlDeclaration) doc.childNode(0);
        assertEquals("dontTouch", selectedNode.attr("encoding"));
    }

@Test
    public void testMetaCharsetUpdateXmlDisabledNoChanges_3_oe() {
        final Document doc = createXmlDocument("dontTouch", "dontTouch", true);

        final String xmlCharset = "<?xml version=\"dontTouch\" encoding=\"dontTouch\"?>\n" +
                                    "<root>\n" +
                                    " node\n" +
                                    "</root>";
        // removed other assertion

        XmlDeclaration selectedNode = (XmlDeclaration) doc.childNode(0);
        // removed other assertion
        assertEquals("dontTouch", selectedNode.attr("version"));
    }

@Test
    public void testMetaCharsetUpdatedDisabledPerDefault_1_oe() {
        final Document doc = createHtmlDocument("none");
        assertFalse(doc.updateMetaCharsetElement());
    }

@Test
    public void testShiftJisRoundtrip_1_oe() throws Exception {
        String input =
                "<html>"
                        +   "<head>"
                        +     "<meta http-equiv=\"content-type\" content=\"text/html; charset=Shift_JIS\" />"
                        +   "</head>"
                        +   "<body>"
                        +     "before&nbsp;after"
                        +   "</body>"
                        + "</html>";
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.US_ASCII));

        Document doc = Jsoup.parse(is, null, "http://example.com");
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);

        String output = new String(doc.html().getBytes(doc.outputSettings().charset()), doc.outputSettings().charset());

        assertFalse(output.contains("?"), "Should not have contained a '?'.");
    }

@Test
    public void testShiftJisRoundtrip_2_oe() throws Exception {
        String input =
                "<html>"
                        +   "<head>"
                        +     "<meta http-equiv=\"content-type\" content=\"text/html; charset=Shift_JIS\" />"
                        +   "</head>"
                        +   "<body>"
                        +     "before&nbsp;after"
                        +   "</body>"
                        + "</html>";
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.US_ASCII));

        Document doc = Jsoup.parse(is, null, "http://example.com");
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);

        String output = new String(doc.html().getBytes(doc.outputSettings().charset()), doc.outputSettings().charset());

        // removed other assertion
        assertTrue(output.contains("&#xa0;")|| output.contains("&nbsp;"),"Should have contained a '&#xa0;' or a '&nbsp;'.");
    }

@Test public void parseAndHtmlOnDifferentThreads_1_oe() throws InterruptedException {
        String html = "<p>Alrighty then it's not \uD83D\uDCA9. <span>Next</span></p>"; // 💩
        String asci = "<p>Alrighty then it's not &#x1f4a9;. <span>Next</span></p>";

        final Document doc = Jsoup.parse(html);
        final String[] out = new String[1];
        final Elements p = doc.select("p");
        assertEquals(html, p.outerHtml());
        }

@Test public void parseAndHtmlOnDifferentThreads_2_oe() throws InterruptedException {
        String html = "<p>Alrighty then it's not \uD83D\uDCA9. <span>Next</span></p>"; // 💩
        String asci = "<p>Alrighty then it's not &#x1f4a9;. <span>Next</span></p>";

        final Document doc = Jsoup.parse(html);
        final String[] out = new String[1];
        final Elements p = doc.select("p");
        // removed other assertion

        Thread thread = new Thread(() -> {
            out[0] = p.outerHtml();
            doc.outputSettings().charset(StandardCharsets.US_ASCII);
        });
        thread.start();
        thread.join();

        assertEquals(html, out[0]);
        }

@Test public void parseAndHtmlOnDifferentThreads_3_oe() throws InterruptedException {
        String html = "<p>Alrighty then it's not \uD83D\uDCA9. <span>Next</span></p>"; // 💩
        String asci = "<p>Alrighty then it's not &#x1f4a9;. <span>Next</span></p>";

        final Document doc = Jsoup.parse(html);
        final String[] out = new String[1];
        final Elements p = doc.select("p");
        // removed other assertion

        Thread thread = new Thread(() -> {
            out[0] = p.outerHtml();
            doc.outputSettings().charset(StandardCharsets.US_ASCII);
        });
        thread.start();
        thread.join();

        // removed other assertion
        assertEquals(StandardCharsets.US_ASCII, doc.outputSettings().charset());
        }

@Test public void parseAndHtmlOnDifferentThreads_4_oe() throws InterruptedException {
        String html = "<p>Alrighty then it's not \uD83D\uDCA9. <span>Next</span></p>"; // 💩
        String asci = "<p>Alrighty then it's not &#x1f4a9;. <span>Next</span></p>";

        final Document doc = Jsoup.parse(html);
        final String[] out = new String[1];
        final Elements p = doc.select("p");
        // removed other assertion

        Thread thread = new Thread(() -> {
            out[0] = p.outerHtml();
            doc.outputSettings().charset(StandardCharsets.US_ASCII);
        });
        thread.start();
        thread.join();

        // removed other assertion
        // removed other assertion
        assertEquals(asci, p.outerHtml());
        }

@Test public void testDocumentTypeGet_1_oe() {
        String html = "\n\n<!-- comment -->  <!doctype html><p>One</p>";
        Document doc = Jsoup.parse(html);
        DocumentType documentType = doc.documentType();
        assertNotNull(documentType);
        }

@Test public void testDocumentTypeGet_2_oe() {
        String html = "\n\n<!-- comment -->  <!doctype html><p>One</p>";
        Document doc = Jsoup.parse(html);
        DocumentType documentType = doc.documentType();
        // removed other assertion
        assertEquals("html", documentType.name());
        }

@Test public void framesetSupportsBodyMethod_1_oe() {
        String html = "<html><head><title>Frame Test</title></head><frameset id=id><frame src=foo.html></frameset>";
        Document doc = Jsoup.parse(html);
        Element head = doc.head();
        assertNotNull(head);
        }

@Test public void framesetSupportsBodyMethod_2_oe() {
        String html = "<html><head><title>Frame Test</title></head><frameset id=id><frame src=foo.html></frameset>";
        Document doc = Jsoup.parse(html);
        Element head = doc.head();
        // removed other assertion
        assertEquals("Frame Test", doc.title());
        }

@Test public void framesetSupportsBodyMethod_3_oe() {
        String html = "<html><head><title>Frame Test</title></head><frameset id=id><frame src=foo.html></frameset>";
        Document doc = Jsoup.parse(html);
        Element head = doc.head();
        // removed other assertion
        // removed other assertion

        // Frameset docs per html5 spec have no body element - but instead a frameset elelemt
        assertNull(doc.selectFirst("body"));
        }

@Test public void framesetSupportsBodyMethod_4_oe() {
        String html = "<html><head><title>Frame Test</title></head><frameset id=id><frame src=foo.html></frameset>";
        Document doc = Jsoup.parse(html);
        Element head = doc.head();
        // removed other assertion
        // removed other assertion

        // Frameset docs per html5 spec have no body element - but instead a frameset elelemt
        // removed other assertion
        Element frameset = doc.selectFirst("frameset");
        assertNotNull(frameset);
        }

@Test public void framesetSupportsBodyMethod_5_oe() {
        String html = "<html><head><title>Frame Test</title></head><frameset id=id><frame src=foo.html></frameset>";
        Document doc = Jsoup.parse(html);
        Element head = doc.head();
        // removed other assertion
        // removed other assertion

        // Frameset docs per html5 spec have no body element - but instead a frameset elelemt
        // removed other assertion
        Element frameset = doc.selectFirst("frameset");
        // removed other assertion

        // the body() method returns body or frameset and does not otherwise modify the document
        // doing it in body() vs parse keeps the html close to original for round-trip option
        Element body = doc.body();
        assertNotNull(body);
        }

@Test public void framesetSupportsBodyMethod_6_oe() {
        String html = "<html><head><title>Frame Test</title></head><frameset id=id><frame src=foo.html></frameset>";
        Document doc = Jsoup.parse(html);
        Element head = doc.head();
        // removed other assertion
        // removed other assertion

        // Frameset docs per html5 spec have no body element - but instead a frameset elelemt
        // removed other assertion
        Element frameset = doc.selectFirst("frameset");
        // removed other assertion

        // the body() method returns body or frameset and does not otherwise modify the document
        // doing it in body() vs parse keeps the html close to original for round-trip option
        Element body = doc.body();
        // removed other assertion
        assertSame(frameset, body);
        }

@Test public void framesetSupportsBodyMethod_7_oe() {
        String html = "<html><head><title>Frame Test</title></head><frameset id=id><frame src=foo.html></frameset>";
        Document doc = Jsoup.parse(html);
        Element head = doc.head();
        // removed other assertion
        // removed other assertion

        // Frameset docs per html5 spec have no body element - but instead a frameset elelemt
        // removed other assertion
        Element frameset = doc.selectFirst("frameset");
        // removed other assertion

        // the body() method returns body or frameset and does not otherwise modify the document
        // doing it in body() vs parse keeps the html close to original for round-trip option
        Element body = doc.body();
        // removed other assertion
        // removed other assertion
        assertEquals("frame", body.child(0).tagName());
        }

@Test public void framesetSupportsBodyMethod_8_oe() {
        String html = "<html><head><title>Frame Test</title></head><frameset id=id><frame src=foo.html></frameset>";
        Document doc = Jsoup.parse(html);
        Element head = doc.head();
        // removed other assertion
        // removed other assertion

        // Frameset docs per html5 spec have no body element - but instead a frameset elelemt
        // removed other assertion
        Element frameset = doc.selectFirst("frameset");
        // removed other assertion

        // the body() method returns body or frameset and does not otherwise modify the document
        // doing it in body() vs parse keeps the html close to original for round-trip option
        Element body = doc.body();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNull(doc.selectFirst("body"));// did not vivify a body element;
        }

@Test public void framesetSupportsBodyMethod_9_oe() {
        String html = "<html><head><title>Frame Test</title></head><frameset id=id><frame src=foo.html></frameset>";
        Document doc = Jsoup.parse(html);
        Element head = doc.head();
        // removed other assertion
        // removed other assertion

        // Frameset docs per html5 spec have no body element - but instead a frameset elelemt
        // removed other assertion
        Element frameset = doc.selectFirst("frameset");
        // removed other assertion

        // the body() method returns body or frameset and does not otherwise modify the document
        // doing it in body() vs parse keeps the html close to original for round-trip option
        Element body = doc.body();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        String expected = "<html>\n" + " <head>\n" + " <title>Frame Test</title>\n" + " </head>\n" + " <frameset id=\"id\">\n" + " <frame src=\"foo.html\">\n" + " </frameset>\n" + "</html>";
        assertEquals(expected, doc.html());
        }

}
