package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;
import java.util.List;
import java.util.stream.Stream;

import static org.jsoup.helper.W3CDom.XPathFactoryProperty;
import static org.junit.jupiter.api.Assertions.*;

public class XpathTest_OE25Dev {

    @Test
    public void supportsLocalname() {
        String xhtml = "<html xmlns='http://www.w3.org/1999/xhtml'><body id='One'><div>hello</div></body></html>";
        Document doc = Jsoup.parse(xhtml, Parser.xmlParser());
        Elements elements = doc.selectXpath("//*[local-name()='body']");
        assertEquals(1, elements.size());
        assertEquals("One", elements.first().id());
    }

    @Test
    public void canDitchNamespaces() {
        String xhtml = "<html xmlns='http://www.w3.org/1999/xhtml'><body id='One'><div>hello</div></body></html>";
        Document doc = Jsoup.parse(xhtml, Parser.xmlParser());
        doc.select("[xmlns]").removeAttr("xmlns");
        Elements elements = doc.selectXpath("//*[local-name()='body']");
        assertEquals(1, elements.size());

        elements = doc.selectXpath("//body");
        assertEquals(1, elements.size());
        assertEquals("One", elements.first().id());
    }

    private static Stream<Arguments> provideEvaluators() {
        String html = "<div id=1><div id=2><p class=foo>Hello</p></div></div><DIV id=3>";
        Document doc = Jsoup.parse(html);

        return Stream.of(
           Arguments.of(doc, "DIV", "//div"),
           Arguments.of(doc, "div > p.foo", "//div/p[@class]"),
           Arguments.of(doc, "div + div", "//div/following-sibling::div[1]"),
           Arguments.of(doc, "p:containsOwn(Hello)", "//p[contains(text(),\"Hello\")]")
        );
    }

    @Test
    public void canSupplyAlternateFactoryImpl() {
        // previously we had a test to load Saxon and do an XPath 2.0 query. But we know Saxon works and so that's
        // redundant - really just need to test that an alternate XPath factory can be used

        System.setProperty(XPathFactoryProperty, AlternateXpathFactory.class.getName());

        String xhtml = "<html xmlns='http://www.w3.org/1999/xhtml'><body id='One'><div>hello</div></body></html>";
        boolean threw = false;
        try {
            Document doc = Jsoup.parse(xhtml, Parser.xmlParser());
            Elements elements = doc.selectXpath("//*:body");

        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Sorry, no can do!"));
            threw = true;
        }
        assertTrue(threw);
        System.clearProperty(XPathFactoryProperty);
    }

    // minimal, no-op implementation class to verify users can load a factory to support XPath 2.0 etc
    public static class AlternateXpathFactory extends XPathFactory {
        public AlternateXpathFactory() {
            super();
        }

        @Override
        public boolean isObjectModelSupported(String objectModel) {
            return true;
        }

        @Override
        public void setFeature(String name, boolean value) throws XPathFactoryConfigurationException {

        }

        @Override
        public boolean getFeature(String name) throws XPathFactoryConfigurationException {
            return true;
        }

        @Override
        public void setXPathVariableResolver(XPathVariableResolver resolver) {

        }

        @Override
        public void setXPathFunctionResolver(XPathFunctionResolver resolver) {

        }

        @Override
        public XPath newXPath() {
            throw new IllegalArgumentException("Sorry, no can do!");
        }
    }

    @Test
    public void supportsXpath_1_oe() {
        String html = "<body><div><p>One</div><div><p>Two</div><div>Three</div>";
        Document doc = Jsoup.parse(html);

        Elements els = doc.selectXpath("//div/p");
        assertEquals(2, els.size());
    }

    @Test
    public void supportsXpath_2_oe() {
        String html = "<body><div><p>One</div><div><p>Two</div><div>Three</div>";
        Document doc = Jsoup.parse(html);

        Elements els = doc.selectXpath("//div/p");
        assertEquals("One", els.get(0).text());
    }

    @Test
    public void supportsXpath_3_oe() {
        String html = "<body><div><p>One</div><div><p>Two</div><div>Three</div>";
        Document doc = Jsoup.parse(html);

        Elements els = doc.selectXpath("//div/p");
        assertEquals("Two", els.get(1).text());
    }

    @Test public void supportsXpathFromElement_1_oe() {
        String html = "<body><div><p>One</div><div><p>Two</div><div>Three</div>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        assertNotNull(div);
        }

    @Test public void supportsXpathFromElement_2_oe() {
        String html = "<body><div><p>One</div><div><p>Two</div><div>Three</div>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        Element w3cDiv = div.selectXpath(".").first(); // self
        assertSame(div, w3cDiv);
        }

    @Test public void supportsXpathFromElement_3_oe() {
        String html = "<body><div><p>One</div><div><p>Two</div><div>Three</div>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        Element w3cDiv = div.selectXpath(".").first(); // self

        Elements els = div.selectXpath("p");
        assertEquals(1, els.size());
        }

    @Test public void supportsXpathFromElement_4_oe() {
        String html = "<body><div><p>One</div><div><p>Two</div><div>Three</div>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        Element w3cDiv = div.selectXpath(".").first(); // self

        Elements els = div.selectXpath("p");
        assertEquals("One", els.get(0).text());
        }

    @Test public void supportsXpathFromElement_5_oe() {
        String html = "<body><div><p>One</div><div><p>Two</div><div>Three</div>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        Element w3cDiv = div.selectXpath(".").first(); // self

        Elements els = div.selectXpath("p");
        assertEquals("p", els.get(0).tagName());
        }

    @Test public void supportsXpathFromElement_6_oe() {
        String html = "<body><div><p>One</div><div><p>Two</div><div>Three</div>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        Element w3cDiv = div.selectXpath(".").first(); // self

        Elements els = div.selectXpath("p");

        assertEquals(1,div.selectXpath("//body").size());// the whole document is visible on the div context assertEquals(1,doc.selectXpath("//body").size());
        }

    @Test public void emptyElementsIfNoResults_1_oe() {
        Document doc = Jsoup.parse("<p>One<p>Two");
        assertEquals(0, doc.selectXpath("//div").size());
        }

    @Test
    public void throwsSelectException_1_oe() {
        Document doc = Jsoup.parse("<p>One<p>Two");
        boolean threw = false;
        try {
            doc.selectXpath("//???");
        } catch (Selector.SelectorParseException e) {
            threw = true;
            assertTrue(e.getMessage().startsWith("Could not evaluate XPath query [//???]:"));
    }
    }

    @Test
    public void throwsSelectException_2_oe() {
        Document doc = Jsoup.parse("<p>One<p>Two");
        boolean threw = false;
        try {
            doc.selectXpath("//???");
        } catch (Selector.SelectorParseException e) {
            threw = true;
        }
        assertTrue(threw);
    }

@ParameterizedTest
    @MethodSource("provideEvaluators")
    void cssAndXpathEquivalents_1_oe(Document doc, String css, String xpath) {
        Elements fromCss = doc.select(css);
        Elements fromXpath = doc.selectXpath(xpath);

        assertTrue(fromCss.size() >= 1);
    }

@ParameterizedTest
    @MethodSource("provideEvaluators")
    void cssAndXpathEquivalents_2_oe(Document doc, String css, String xpath) {
        Elements fromCss = doc.select(css);
        Elements fromXpath = doc.selectXpath(xpath);

        assertTrue(fromXpath.size() >= 1);
    }

@ParameterizedTest
    @MethodSource("provideEvaluators")
    void cssAndXpathEquivalents_3_oe(Document doc, String css, String xpath) {
        Elements fromCss = doc.select(css);
        Elements fromXpath = doc.selectXpath(xpath);

        assertEquals(fromCss, fromXpath);
    }

@Test void canSelectTextNodes_1_oe() {
        String html = "<div><p>One<p><a>Two</a><p>Three and some more";
        Document doc = Jsoup.parse(html);

        List<TextNode> text = doc.selectXpath("//body//p//text()", TextNode.class);
        assertEquals(3, text.size());
        }

@Test void canSelectTextNodes_2_oe() {
        String html = "<div><p>One<p><a>Two</a><p>Three and some more";
        Document doc = Jsoup.parse(html);

        List<TextNode> text = doc.selectXpath("//body//p//text()", TextNode.class);
        assertEquals("One", text.get(0).text());
        }

@Test void canSelectTextNodes_3_oe() {
        String html = "<div><p>One<p><a>Two</a><p>Three and some more";
        Document doc = Jsoup.parse(html);

        List<TextNode> text = doc.selectXpath("//body//p//text()", TextNode.class);
        assertEquals("Two", text.get(1).text());
        }

@Test void canSelectTextNodes_4_oe() {
        String html = "<div><p>One<p><a>Two</a><p>Three and some more";
        Document doc = Jsoup.parse(html);

        List<TextNode> text = doc.selectXpath("//body//p//text()", TextNode.class);
        assertEquals("Three and some more", text.get(2).text());
        }

@Test void canSelectTextNodes_5_oe() {
        String html = "<div><p>One<p><a>Two</a><p>Three and some more";
        Document doc = Jsoup.parse(html);

        List<TextNode> text = doc.selectXpath("//body//p//text()", TextNode.class);

        List<Node> nodes = doc.selectXpath("//body//p//text()", Node.class);
        assertEquals(3, nodes.size());
        }

@Test void canSelectTextNodes_6_oe() {
        String html = "<div><p>One<p><a>Two</a><p>Three and some more";
        Document doc = Jsoup.parse(html);

        List<TextNode> text = doc.selectXpath("//body//p//text()", TextNode.class);

        List<Node> nodes = doc.selectXpath("//body//p//text()", Node.class);
        assertEquals("One", nodes.get(0).outerHtml());
        }

@Test void canSelectTextNodes_7_oe() {
        String html = "<div><p>One<p><a>Two</a><p>Three and some more";
        Document doc = Jsoup.parse(html);

        List<TextNode> text = doc.selectXpath("//body//p//text()", TextNode.class);

        List<Node> nodes = doc.selectXpath("//body//p//text()", Node.class);
        assertEquals("Two", nodes.get(1).outerHtml());
        }

@Test void canSelectTextNodes_8_oe() {
        String html = "<div><p>One<p><a>Two</a><p>Three and some more";
        Document doc = Jsoup.parse(html);

        List<TextNode> text = doc.selectXpath("//body//p//text()", TextNode.class);

        List<Node> nodes = doc.selectXpath("//body//p//text()", Node.class);
        assertEquals("Three and some more", nodes.get(2).outerHtml());
        }

@Test void selectByAttribute_1_oe() {
        Document doc = Jsoup.parse("<p><a href='/foo'>Foo</a><a href='/bar'>Bar</a><a>None</a>");
        List<String> hrefs = doc.selectXpath("//a[@href]").eachAttr("href");
        assertEquals(2, hrefs.size());
        }

@Test void selectByAttribute_2_oe() {
        Document doc = Jsoup.parse("<p><a href='/foo'>Foo</a><a href='/bar'>Bar</a><a>None</a>");
        List<String> hrefs = doc.selectXpath("//a[@href]").eachAttr("href");
        assertEquals("/foo", hrefs.get(0));
        }

@Test void selectByAttribute_3_oe() {
        Document doc = Jsoup.parse("<p><a href='/foo'>Foo</a><a href='/bar'>Bar</a><a>None</a>");
        List<String> hrefs = doc.selectXpath("//a[@href]").eachAttr("href");
        assertEquals("/bar", hrefs.get(1));
        }

@Test void selectOutsideOfElementTree_1_oe() {
        Document doc = Jsoup.parse("<p>One<p>Two<p>Three");
        Elements ps = doc.selectXpath("//p");
        assertEquals(3, ps.size());
        }

@Test void selectOutsideOfElementTree_2_oe() {
        Document doc = Jsoup.parse("<p>One<p>Two<p>Three");
        Elements ps = doc.selectXpath("//p");

        Element p1 = ps.get(0);
        assertEquals("One", p1.text());
        }

@Test void selectOutsideOfElementTree_3_oe() {
        Document doc = Jsoup.parse("<p>One<p>Two<p>Three");
        Elements ps = doc.selectXpath("//p");

        Element p1 = ps.get(0);

        Elements sibs = p1.selectXpath("following-sibling::p");
        assertEquals(2, sibs.size());
        }

@Test void selectOutsideOfElementTree_4_oe() {
        Document doc = Jsoup.parse("<p>One<p>Two<p>Three");
        Elements ps = doc.selectXpath("//p");

        Element p1 = ps.get(0);

        Elements sibs = p1.selectXpath("following-sibling::p");
        assertEquals("Two", sibs.get(0).text());
        }

@Test void selectOutsideOfElementTree_5_oe() {
        Document doc = Jsoup.parse("<p>One<p>Two<p>Three");
        Elements ps = doc.selectXpath("//p");

        Element p1 = ps.get(0);

        Elements sibs = p1.selectXpath("following-sibling::p");
        assertEquals("Three", sibs.get(1).text());
        }

@Test void selectAncestorsOnContextElement_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.selectFirst("p");
        assertNotNull(p);
        }

@Test void selectAncestorsOnContextElement_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.selectFirst("p");
        Elements chain = p.selectXpath("ancestor-or-self::*");
        assertEquals(4, chain.size());
        }

@Test void selectAncestorsOnContextElement_3_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.selectFirst("p");
        Elements chain = p.selectXpath("ancestor-or-self::*");
        assertEquals("html", chain.get(0).tagName());
        }

@Test void selectAncestorsOnContextElement_4_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.selectFirst("p");
        Elements chain = p.selectXpath("ancestor-or-self::*");
        assertEquals("p", chain.get(3).tagName());
        }

@Test
    public void notNamespaceAware_1_oe() {
        String xhtml = "<html xmlns='http://www.w3.org/1999/xhtml'><body id='One'><div>hello</div></body></html>";
        Document doc = Jsoup.parse(xhtml, Parser.xmlParser());
        Elements elements = doc.selectXpath("//body");
        assertEquals(1, elements.size());
    }

@Test
    public void notNamespaceAware_2_oe() {
        String xhtml = "<html xmlns='http://www.w3.org/1999/xhtml'><body id='One'><div>hello</div></body></html>";
        Document doc = Jsoup.parse(xhtml, Parser.xmlParser());
        Elements elements = doc.selectXpath("//body");
        assertEquals("One", elements.first().id());
    }

@Test
    public void supportsPrefixes_1_oe() {
        String xml = "<?xml version=\"1.0\"?>\n" +
            "<bk:book xmlns:bk='urn:loc.gov:books'\n" +
            "         xmlns:isbn='urn:ISBN:0-395-36341-6'>\n" +
            "    <bk:title>Cheaper by the Dozen</bk:title>\n" +
            "    <isbn:number>1568491379</isbn:number>\n" +
            "</bk:book>";
        Document doc = Jsoup.parse(xml, Parser.xmlParser());

        Elements elements = doc.selectXpath("//book/title");
        assertEquals(1, elements.size());
    }

@Test
    public void supportsPrefixes_2_oe() {
        String xml = "<?xml version=\"1.0\"?>\n" +
            "<bk:book xmlns:bk='urn:loc.gov:books'\n" +
            "         xmlns:isbn='urn:ISBN:0-395-36341-6'>\n" +
            "    <bk:title>Cheaper by the Dozen</bk:title>\n" +
            "    <isbn:number>1568491379</isbn:number>\n" +
            "</bk:book>";
        Document doc = Jsoup.parse(xml, Parser.xmlParser());

        Elements elements = doc.selectXpath("//book/title");
        assertEquals("Cheaper by the Dozen", elements.first().text());
    }

}
