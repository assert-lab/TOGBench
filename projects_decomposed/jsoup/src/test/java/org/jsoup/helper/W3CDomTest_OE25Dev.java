package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.integration.ParseTest;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDomTest_OE25Dev {

    private static Document parseXml(String xml, boolean nameSpaceAware) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(nameSpaceAware);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver((publicId, systemId) -> {
                if (systemId.contains("about:legacy-compat")) { // <!doctype html>
                    return new InputSource(new StringReader(""));
                } else {
                    return null;
                }
            });
            Document dom = builder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
            dom.normalizeDocument();
            return dom;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private NodeList xpath(Document w3cDoc, String query) throws XPathExpressionException {
        XPathExpression xpath = XPathFactory.newInstance().newXPath().compile(query);
        return ((NodeList) xpath.evaluate(w3cDoc, XPathConstants.NODE));
    }

    private String output(String in, boolean modeHtml) {
        org.jsoup.nodes.Document jdoc = Jsoup.parse(in);
        Document w3c = W3CDom.convert(jdoc);

        Map<String, String> properties = modeHtml ? W3CDom.OutputHtml() : W3CDom.OutputXml();
        return TextUtil.normalizeSpaces(W3CDom.asString(w3c, properties));
    }

    private void assertEqualsIgnoreCase(String want, String have) {
        assertEquals(want.toLowerCase(Locale.ROOT), have.toLowerCase(Locale.ROOT));
    }

    @Test
    public void simpleConversion_1_oe() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        NodeList meta = wDoc.getElementsByTagName("META");
        assertEquals(0, meta.getLength());
    }

    @Test
    public void simpleConversion_2_oe() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        NodeList meta = wDoc.getElementsByTagName("META");

        String out = W3CDom.asString(wDoc, W3CDom.OutputXml());
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><head><title>W3c</title></head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script></invalid></body></html>";
        assertEquals(expected, TextUtil.stripNewlines(out));
    }

    @Test
    public void simpleConversion_3_oe() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        NodeList meta = wDoc.getElementsByTagName("META");

        String out = W3CDom.asString(wDoc, W3CDom.OutputXml());
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><head><title>W3c</title></head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script></invalid></body></html>";

        Document roundTrip = parseXml(out, true);
        assertEquals("Text", roundTrip.getElementsByTagName("p").item(0).getTextContent());
    }

    @Test
    public void simpleConversion_4_oe() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        NodeList meta = wDoc.getElementsByTagName("META");

        String out = W3CDom.asString(wDoc, W3CDom.OutputXml());
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><head><title>W3c</title></head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script></invalid></body></html>";

        Document roundTrip = parseXml(out, true);

        Map<String, String> properties = W3CDom.OutputXml();
        properties.put(OutputKeys.INDENT, "yes");
        String furtherOut = W3CDom.asString(wDoc, properties);
        assertTrue(furtherOut.length()> out.length());// wanted to assert formatting,but actual indentation is platform specific so breaks in CI;
    }

    @Test
    public void simpleConversion_5_oe() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        NodeList meta = wDoc.getElementsByTagName("META");

        String out = W3CDom.asString(wDoc, W3CDom.OutputXml());
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><head><title>W3c</title></head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script></invalid></body></html>";

        Document roundTrip = parseXml(out, true);

        Map<String, String> properties = W3CDom.OutputXml();
        properties.put(OutputKeys.INDENT, "yes");
        String furtherOut = W3CDom.asString(wDoc, properties);
        String furtherExpected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><head><title>W3c</title></head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script></invalid></body></html>";
        assertEquals(furtherExpected, TextUtil.stripNewlines(furtherOut)); // on windows, DOM will write newlines as \r\n;
    }

    @Test
    public void convertsGoogle_1_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html.gz");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(1);
        assertNull(htmlEl.getNamespaceURI());
    }

    @Test
    public void convertsGoogle_2_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html.gz");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(1);
        assertEquals("html", htmlEl.getLocalName());
    }

    @Test
    public void convertsGoogle_3_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html.gz");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(1);
        assertEquals("html", htmlEl.getNodeName());
    }

    @Test
    public void convertsGoogle_4_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html.gz");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(1);

        DocumentType doctype = wDoc.getDoctype();
        Node doctypeNode = wDoc.getChildNodes().item(0);
        assertSame(doctype, doctypeNode);
    }

    @Test
    public void convertsGoogle_5_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html.gz");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(1);

        DocumentType doctype = wDoc.getDoctype();
        Node doctypeNode = wDoc.getChildNodes().item(0);
        assertEquals("html", doctype.getName());
    }

    @Test
    public void convertsGoogle_6_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html.gz");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(1);

        DocumentType doctype = wDoc.getDoctype();
        Node doctypeNode = wDoc.getChildNodes().item(0);

        String xml = W3CDom.asString(wDoc, W3CDom.OutputXml());
        assertTrue(xml.contains("ipod"));
    }

    @Test
    public void convertsGoogle_7_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html.gz");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(1);

        DocumentType doctype = wDoc.getDoctype();
        Node doctypeNode = wDoc.getChildNodes().item(0);

        String xml = W3CDom.asString(wDoc, W3CDom.OutputXml());

        Document roundTrip = parseXml(xml, true);
        assertEquals("Images", roundTrip.getElementsByTagName("a").item(0).getTextContent());
    }

    @Test
    public void convertsGoogleLocation_1_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html.gz");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI());
    }

    @Test
    public void namespacePreservation_1_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
    }

    @Test
    public void namespacePreservation_2_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertEquals("html", htmlEl.getLocalName());
    }

    @Test
    public void namespacePreservation_3_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertEquals("html", htmlEl.getNodeName());
    }

    @Test
    public void namespacePreservation_4_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();
        assertEquals("http://www.w3.org/1999/xhtml", head.getNamespaceURI());
    }

    @Test
    public void namespacePreservation_5_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();
        assertEquals("head", head.getLocalName());
    }

    @Test
    public void namespacePreservation_6_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();
        assertEquals("head", head.getNodeName());
    }

    @Test
    public void namespacePreservation_7_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);
        assertEquals("Check", epubTitle.getTextContent());
    }

    @Test
    public void namespacePreservation_8_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);
        assertEquals("http://www.idpf.org/2007/ops", epubTitle.getNamespaceURI());
    }

    @Test
    public void namespacePreservation_9_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);
        assertEquals("title", epubTitle.getLocalName());
    }

    @Test
    public void namespacePreservation_10_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);
        assertEquals("epub:title", epubTitle.getNodeName());
    }

    @Test
    public void namespacePreservation_11_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();
        assertEquals("urn:test", xSection.getNamespaceURI());
    }

    @Test
    public void namespacePreservation_12_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();
        assertEquals("section", xSection.getLocalName());
    }

    @Test
    public void namespacePreservation_13_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();
        assertEquals("x:section", xSection.getNodeName());
    }

    @Test
    public void namespacePreservation_14_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();
        assertEquals("http://www.w3.org/2000/svg", svg.getNamespaceURI());
    }

    @Test
    public void namespacePreservation_15_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();
        assertEquals("svg", svg.getLocalName());
    }

    @Test
    public void namespacePreservation_16_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();
        assertEquals("svg", svg.getNodeName());
    }

    @Test
    public void namespacePreservation_17_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);
        assertEquals("http://www.w3.org/2000/svg", path.getNamespaceURI());
    }

    @Test
    public void namespacePreservation_18_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);
        assertEquals("path", path.getLocalName());
    }

    @Test
    public void namespacePreservation_19_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);
        assertEquals("path", path.getNodeName());
    }

    @Test
    public void namespacePreservation_20_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);

        Node clip = path.getChildNodes().item(1);
        assertEquals("http://example.com/clip", clip.getNamespaceURI());
    }

    @Test
    public void namespacePreservation_21_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);

        Node clip = path.getChildNodes().item(1);
        assertEquals("clip", clip.getLocalName());
    }

    @Test
    public void namespacePreservation_22_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);

        Node clip = path.getChildNodes().item(1);
        assertEquals("clip", clip.getNodeName());
    }

    @Test
    public void namespacePreservation_23_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);

        Node clip = path.getChildNodes().item(1);
        assertEquals("456", clip.getTextContent());
    }

    @Test
    public void namespacePreservation_24_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);

        Node clip = path.getChildNodes().item(1);

        Node picture = svg.getNextSibling().getNextSibling();
        assertEquals("http://www.w3.org/1999/xhtml", picture.getNamespaceURI());
    }

    @Test
    public void namespacePreservation_25_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);

        Node clip = path.getChildNodes().item(1);

        Node picture = svg.getNextSibling().getNextSibling();
        assertEquals("picture", picture.getLocalName());
    }

    @Test
    public void namespacePreservation_26_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);

        Node clip = path.getChildNodes().item(1);

        Node picture = svg.getNextSibling().getNextSibling();
        assertEquals("picture", picture.getNodeName());
    }

    @Test
    public void namespacePreservation_27_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);

        Node clip = path.getChildNodes().item(1);

        Node picture = svg.getNextSibling().getNextSibling();

        Node img = picture.getFirstChild();
        assertEquals("http://www.w3.org/1999/xhtml", img.getNamespaceURI());
    }

    @Test
    public void namespacePreservation_28_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);

        Node clip = path.getChildNodes().item(1);

        Node picture = svg.getNextSibling().getNextSibling();

        Node img = picture.getFirstChild();
        assertEquals("img", img.getLocalName());
    }

    @Test
    public void namespacePreservation_29_oe() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);

        Node head = htmlEl.getFirstChild().getNextSibling();

        Node epubTitle = htmlEl.getChildNodes().item(3).getChildNodes().item(3);

        Node xSection = epubTitle.getNextSibling().getNextSibling();

        Node svg = xSection.getNextSibling().getNextSibling();

        Node path = svg.getChildNodes().item(1);

        Node clip = path.getChildNodes().item(1);

        Node picture = svg.getNextSibling().getNextSibling();

        Node img = picture.getFirstChild();
        assertEquals("img", img.getNodeName());
    }

    @Test
    public void handlesInvalidAttributeNames_1_oe() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\""));// actually an attribute with key '"'. Correct per HTML5 spec,but w3c xml dom doesn't dig it assertTrue(body.hasAttr("name\""));
    }

    @Test
    public void handlesInvalidAttributeNames_2_oe() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();

        Document w3Doc = W3CDom.convert(jsoupDoc);
        String xml = W3CDom.asString(w3Doc, W3CDom.OutputXml());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><head/><body name=\"\" style=\"color: red\"/></html>", xml);
    }

    @Test
    public void htmlInputDocMaintainsHtmlAttributeNames_1_oe() {
        String html = "<!DOCTYPE html><html><head></head><body><p hành=\"1\" hình=\"2\">unicode attr names</p></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);

        Document w3Doc = W3CDom.convert(jsoupDoc);
        String out = W3CDom.asString(w3Doc, W3CDom.OutputHtml());
        String expected = "<!DOCTYPE html SYSTEM \"about:legacy-compat\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body><p hành=\"1\" hình=\"2\">unicode attr names</p></body></html>";
        assertEquals(expected, TextUtil.stripNewlines(out));
    }

    @Test
    public void xmlInputDocMaintainsHtmlAttributeNames_1_oe() {
        String html = "<!DOCTYPE html><html><head></head><body><p hành=\"1\" hình=\"2\">unicode attr names coerced</p></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        jsoupDoc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);

        Document w3Doc = W3CDom.convert(jsoupDoc);
        String out = W3CDom.asString(w3Doc, W3CDom.OutputHtml());
        String expected = "<!DOCTYPE html SYSTEM \"about:legacy-compat\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body><p hnh=\"2\">unicode attr names coerced</p></body></html>";
        assertEquals(expected, TextUtil.stripNewlines(out));
    }

    @Test
    public void handlesInvalidTagAsText_1_oe() {
        org.jsoup.nodes.Document jsoup = Jsoup.parse("<インセンティブで高収入！>Text <p>More</p>");

        Document w3Doc = W3CDom.convert(jsoup);
        String xml = W3CDom.asString(w3Doc, W3CDom.OutputXml());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><head/><body>&lt;インセンティブで高収入！&gt;Text <p>More</p></body></html>", xml);
    }

    @Test
    public void treatsUndeclaredNamespaceAsLocalName_1_oe() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();

        assertNull(htmlEl.getNamespaceURI());
    }

    @Test
    public void treatsUndeclaredNamespaceAsLocalName_2_oe() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();

        assertEquals("html", htmlEl.getLocalName());
    }

    @Test
    public void treatsUndeclaredNamespaceAsLocalName_3_oe() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();

        assertEquals("html", htmlEl.getNodeName());
    }

    @Test
    public void treatsUndeclaredNamespaceAsLocalName_4_oe() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();


        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertNull(fb.getNamespaceURI());
    }

    @Test
    public void treatsUndeclaredNamespaceAsLocalName_5_oe() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();


        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertEquals("like", fb.getLocalName());
    }

    @Test
    public void treatsUndeclaredNamespaceAsLocalName_6_oe() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();


        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertEquals("fb:like", fb.getNodeName());
    }

    @Test
    public void xmlnsXpathTest_1_oe() throws XPathExpressionException {
        W3CDom w3c = new W3CDom();
        String html = "<html><body><div>hello</div></body></html>";
        Document dom = w3c.fromJsoup(Jsoup.parse(html));
        NodeList nodeList = xpath(dom, "//body");// no ns, so needs no prefix
        assertEquals("div", nodeList.item(0).getLocalName());
    }

    @Test
    public void xmlnsXpathTest_2_oe() throws XPathExpressionException {
        W3CDom w3c = new W3CDom();
        String html = "<html><body><div>hello</div></body></html>";
        Document dom = w3c.fromJsoup(Jsoup.parse(html));
        NodeList nodeList = xpath(dom, "//body");// no ns, so needs no prefix

        html = "<html xmlns='http://www.w3.org/1999/xhtml'><body id='One'><div>hello</div></body></html>";
        dom = w3c.fromJsoup(Jsoup.parse(html));
        nodeList = xpath(dom, "//body");
        assertNull(nodeList);// no matches dom = w3c.fromJsoup(Jsoup.parse(html));
    }

@Test
    void canDisableNamespaces_1_oe() throws XPathExpressionException {
        W3CDom w3c = new W3CDom();
        assertTrue(w3c.namespaceAware());
    }

@Test
    void canDisableNamespaces_2_oe() throws XPathExpressionException {
        W3CDom w3c = new W3CDom();

        w3c.namespaceAware(false);
        assertFalse(w3c.namespaceAware());
    }

@Test
    void canDisableNamespaces_3_oe() throws XPathExpressionException {
        W3CDom w3c = new W3CDom();

        w3c.namespaceAware(false);

        String html = "<html xmlns='http://www.w3.org/1999/xhtml'><body id='One'><div>hello</div></body></html>";
        Document dom = w3c.fromJsoup(Jsoup.parse(html));
        NodeList nodeList = xpath(dom, "//body");// no ns, so needs no prefix
        assertEquals("div", nodeList.item(0).getLocalName());
    }

@Test
    public void testRoundTripDoctype_1_oe() {
        String base = "<!DOCTYPE html><p>One</p>";
        assertEqualsIgnoreCase("<!DOCTYPE html SYSTEM \"about:legacy-compat\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body><p>One</p></body></html>", output(base, true));
    }

@Test
    public void testRoundTripDoctype_2_oe() {
        String base = "<!DOCTYPE html><p>One</p>";
        assertEqualsIgnoreCase("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE html SYSTEM \"about:legacy-compat\"><html><head/><body><p>One</p></body></html>", output(base, false));
    }

@Test
    public void testRoundTripDoctype_3_oe() {
        String base = "<!DOCTYPE html><p>One</p>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
        assertEqualsIgnoreCase("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body></body></html>", output(publicDoc, true));
    }

@Test
    public void testRoundTripDoctype_4_oe() {
        String base = "<!DOCTYPE html><p>One</p>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
        assertTrue(output(publicDoc, false).startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE html PUBLIC"));
    }

@Test
    public void testRoundTripDoctype_5_oe() {
        String base = "<!DOCTYPE html><p>One</p>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";
        assertEqualsIgnoreCase("<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body></body></html>", output(systemDoc, true));
    }

@Test
    public void testRoundTripDoctype_6_oe() {
        String base = "<!DOCTYPE html><p>One</p>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";
        assertEqualsIgnoreCase("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE html SYSTEM \"exampledtdfile.dtd\"><html><head/><body/></html>", output(systemDoc, false));
    }

@Test
    public void testRoundTripDoctype_7_oe() {
        String base = "<!DOCTYPE html><p>One</p>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";

        String legacyDoc = "<!DOCTYPE html SYSTEM \"about:legacy-compat\">";
        assertEqualsIgnoreCase("<!DOCTYPE html SYSTEM \"about:legacy-compat\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body></body></html>", output(legacyDoc, true));
    }

@Test
    public void testRoundTripDoctype_8_oe() {
        String base = "<!DOCTYPE html><p>One</p>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";

        String legacyDoc = "<!DOCTYPE html SYSTEM \"about:legacy-compat\">";
        assertEqualsIgnoreCase("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE html SYSTEM \"about:legacy-compat\"><html><head/><body/></html>", output(legacyDoc, false));
    }

@Test
    public void testRoundTripDoctype_9_oe() {
        String base = "<!DOCTYPE html><p>One</p>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";

        String legacyDoc = "<!DOCTYPE html SYSTEM \"about:legacy-compat\">";

        String noDoctype = "<p>One</p>";
        assertEqualsIgnoreCase("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body><p>One</p></body></html>", output(noDoctype, true));
    }

@Test
    public void testRoundTripDoctype_10_oe() {
        String base = "<!DOCTYPE html><p>One</p>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";

        String legacyDoc = "<!DOCTYPE html SYSTEM \"about:legacy-compat\">";

        String noDoctype = "<p>One</p>";
        assertEqualsIgnoreCase("<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><head/><body><p>One</p></body></html>", output(noDoctype, false));
    }

@Test public void convertsElementsAndMaintainsSource_1_oe() {
        org.jsoup.nodes.Document jdoc = Jsoup.parse("<body><div><p>One</div><div><p>Two");
        W3CDom w3CDom = new W3CDom();
        Element jDiv = jdoc.selectFirst("div");
        assertNotNull(jDiv);
        }

@Test public void convertsElementsAndMaintainsSource_2_oe() {
        org.jsoup.nodes.Document jdoc = Jsoup.parse("<body><div><p>One</div><div><p>Two");
        W3CDom w3CDom = new W3CDom();
        Element jDiv = jdoc.selectFirst("div");
        Document doc = w3CDom.fromJsoup(jDiv);
        Node div = w3CDom.contextNode(doc);

        assertEquals("div", div.getLocalName());
        }

@Test public void convertsElementsAndMaintainsSource_3_oe() {
        org.jsoup.nodes.Document jdoc = Jsoup.parse("<body><div><p>One</div><div><p>Two");
        W3CDom w3CDom = new W3CDom();
        Element jDiv = jdoc.selectFirst("div");
        Document doc = w3CDom.fromJsoup(jDiv);
        Node div = w3CDom.contextNode(doc);

        assertEquals(jDiv, div.getUserData(W3CDom.SourceProperty));
        }

@Test public void convertsElementsAndMaintainsSource_4_oe() {
        org.jsoup.nodes.Document jdoc = Jsoup.parse("<body><div><p>One</div><div><p>Two");
        W3CDom w3CDom = new W3CDom();
        Element jDiv = jdoc.selectFirst("div");
        Document doc = w3CDom.fromJsoup(jDiv);
        Node div = w3CDom.contextNode(doc);


        Node textNode = div.getFirstChild().getFirstChild();
        assertEquals("One", textNode.getTextContent());
        }

@Test public void convertsElementsAndMaintainsSource_5_oe() {
        org.jsoup.nodes.Document jdoc = Jsoup.parse("<body><div><p>One</div><div><p>Two");
        W3CDom w3CDom = new W3CDom();
        Element jDiv = jdoc.selectFirst("div");
        Document doc = w3CDom.fromJsoup(jDiv);
        Node div = w3CDom.contextNode(doc);


        Node textNode = div.getFirstChild().getFirstChild();
        assertEquals(Node.TEXT_NODE, textNode.getNodeType());
        }

@Test public void convertsElementsAndMaintainsSource_6_oe() {
        org.jsoup.nodes.Document jdoc = Jsoup.parse("<body><div><p>One</div><div><p>Two");
        W3CDom w3CDom = new W3CDom();
        Element jDiv = jdoc.selectFirst("div");
        Document doc = w3CDom.fromJsoup(jDiv);
        Node div = w3CDom.contextNode(doc);


        Node textNode = div.getFirstChild().getFirstChild();

        org.jsoup.nodes.TextNode jText = (TextNode) jDiv.childNode(0).childNode(0);
        assertEquals(jText, textNode.getUserData(W3CDom.SourceProperty));
        }

}
