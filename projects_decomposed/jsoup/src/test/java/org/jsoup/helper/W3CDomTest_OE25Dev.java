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

    @Test
    void canDisableNamespaces() throws XPathExpressionException {
        W3CDom w3c = new W3CDom();
        assertTrue(w3c.namespaceAware());

        w3c.namespaceAware(false);
        assertFalse(w3c.namespaceAware());

        String html = "<html xmlns='http://www.w3.org/1999/xhtml'><body id='One'><div>hello</div></body></html>";
        Document dom = w3c.fromJsoup(Jsoup.parse(html));
        NodeList nodeList = xpath(dom, "//body");// no ns, so needs no prefix
        assertEquals("div", nodeList.item(0).getLocalName());
    }

    private NodeList xpath(Document w3cDoc, String query) throws XPathExpressionException {
        XPathExpression xpath = XPathFactory.newInstance().newXPath().compile(query);
        return ((NodeList) xpath.evaluate(w3cDoc, XPathConstants.NODE));
    }

    @Test
    public void testRoundTripDoctype() {
        // TODO - not super happy with this output - but plain DOM doesn't let it out, and don't want to rebuild the writer
        // because we have Saxon on the test classpath, the transformer will change to that, and so case may change (e.g. Java base in META, Saxon is meta for HTML)
        String base = "<!DOCTYPE html><p>One</p>";
        assertEqualsIgnoreCase("<!DOCTYPE html SYSTEM \"about:legacy-compat\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body><p>One</p></body></html>",
            output(base, true));
        assertEqualsIgnoreCase("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE html SYSTEM \"about:legacy-compat\"><html><head/><body><p>One</p></body></html>", output(base, false));

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
        assertEqualsIgnoreCase("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body></body></html>", output(publicDoc, true));
        // different impls will have different XML formatting. OpenJDK 13 default gives this: <body /> but others have <body/>, so just check start
        assertTrue(output(publicDoc, false).startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE html PUBLIC"));

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";
        assertEqualsIgnoreCase("<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body></body></html>", output(systemDoc, true));
        assertEqualsIgnoreCase("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE html SYSTEM \"exampledtdfile.dtd\"><html><head/><body/></html>", output(systemDoc, false));

        String legacyDoc = "<!DOCTYPE html SYSTEM \"about:legacy-compat\">";
        assertEqualsIgnoreCase("<!DOCTYPE html SYSTEM \"about:legacy-compat\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body></body></html>", output(legacyDoc, true));
        assertEqualsIgnoreCase("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE html SYSTEM \"about:legacy-compat\"><html><head/><body/></html>", output(legacyDoc, false));

        String noDoctype = "<p>One</p>";
        assertEqualsIgnoreCase("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body><p>One</p></body></html>", output(noDoctype, true));
        assertEqualsIgnoreCase("<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><head/><body><p>One</p></body></html>", output(noDoctype, false));
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

    @Test public void convertsElementsAndMaintainsSource() {
        org.jsoup.nodes.Document jdoc = Jsoup.parse("<body><div><p>One</div><div><p>Two");
        W3CDom w3CDom = new W3CDom();
        Element jDiv = jdoc.selectFirst("div");
        assertNotNull(jDiv);
        Document doc = w3CDom.fromJsoup(jDiv);
        Node div = w3CDom.contextNode(doc);

        assertEquals("div", div.getLocalName());
        assertEquals(jDiv, div.getUserData(W3CDom.SourceProperty));

        Node textNode = div.getFirstChild().getFirstChild();
        assertEquals("One", textNode.getTextContent());
        assertEquals(Node.TEXT_NODE, textNode.getNodeType());

        org.jsoup.nodes.TextNode jText = (TextNode) jDiv.childNode(0).childNode(0);
        assertEquals(jText, textNode.getUserData(W3CDom.SourceProperty));
    }


}
