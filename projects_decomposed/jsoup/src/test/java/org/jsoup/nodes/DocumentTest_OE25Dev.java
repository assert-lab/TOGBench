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

    @Test public void testHtmlAndXmlSyntax() {
        String h = "<!DOCTYPE html><body><img async checked='checked' src='&<>\"'>&lt;&gt;&amp;&quot;<foo />bar";
        Document doc = Jsoup.parse(h);

        doc.outputSettings().syntax(Syntax.html);
        assertEquals("<!doctype html>\n" + "<html>\n" + " <head></head>\n" + " <body>\n" + " <img async checked src=\"&amp;<>&quot;\">&lt;&gt;&amp;\"<foo />bar\n" + " </body>\n" + "</html>",doc.html());

        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        assertEquals("<!DOCTYPE html>\n" + "<html>\n" + " <head></head>\n" + " <body>\n" + " <img async=\"\" checked=\"checked\" src=\"&amp;&lt;>&quot;\" />&lt;&gt;&amp;\"<foo />bar\n" + " </body>\n" + "</html>",doc.html());
    }

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


}
