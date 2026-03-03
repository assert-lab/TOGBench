package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.parser.Tag;
import org.jsoup.select.NodeVisitor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tests Nodes

 @author Jonathan Hedley, jonathan@hedley.net */
public class NodeTest_OE25Dev {

    /*
    Test for an issue with Java's abs URL handler.
     */

    private Attributes getAttributesCaseInsensitive(Element element) {
        Attributes matches = new Attributes();
        for (Attribute attribute : element.attributes()) {
            if (attribute.getKey().equalsIgnoreCase("value")) {
                matches.put(attribute);
            }
        }
        return matches;
    }

    private Attributes singletonAttributes() {
        Attributes attributes = new Attributes();
        attributes.put("value", "bar");
        return attributes;
    }

    @Test public void handlesBaseUri_1_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        attribs.put("relHref", "/foo");
        attribs.put("absHref", "http://bar/qux");

        Element noBase = new Element(tag, "", attribs);
        assertEquals("",noBase.absUrl("relHref"));// with no base,should NOT fallback to href attrib,whatever it is;
        }

    @Test public void handlesBaseUri_2_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        attribs.put("relHref", "/foo");
        attribs.put("absHref", "http://bar/qux");

        Element noBase = new Element(tag, "", attribs);
        assertEquals("http://bar/qux",noBase.absUrl("absHref"));// no base but valid attrib,return attrib;
        }

    @Test public void handlesBaseUri_3_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        attribs.put("relHref", "/foo");
        attribs.put("absHref", "http://bar/qux");

        Element noBase = new Element(tag, "", attribs);
        Element withBase = new Element(tag,"http://foo/",attribs);
        assertEquals("http://foo/foo",withBase.absUrl("relHref"));// construct abs from base + rel;
        }

    @Test public void handlesBaseUri_4_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        attribs.put("relHref", "/foo");
        attribs.put("absHref", "http://bar/qux");

        Element noBase = new Element(tag, "", attribs);
        Element withBase = new Element(tag,"http://foo/",attribs);
        assertEquals("http://bar/qux",withBase.absUrl("absHref"));// href is abs,so returns that;
        }

    @Test public void handlesBaseUri_5_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        attribs.put("relHref", "/foo");
        attribs.put("absHref", "http://bar/qux");

        Element noBase = new Element(tag, "", attribs);
        Element withBase = new Element(tag,"http://foo/",attribs);
        assertEquals("",withBase.absUrl("noval"));
        }

    @Test public void handlesBaseUri_6_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        attribs.put("relHref", "/foo");
        attribs.put("absHref", "http://bar/qux");

        Element noBase = new Element(tag, "", attribs);
        Element withBase = new Element(tag,"http://foo/",attribs);

        Element dodgyBase = new Element(tag, "wtf://no-such-protocol/", attribs);
        assertEquals("http://bar/qux", dodgyBase.absUrl("absHref")); // base fails, but href good, so get that;
        }

    @Test public void handlesBaseUri_7_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        attribs.put("relHref", "/foo");
        attribs.put("absHref", "http://bar/qux");

        Element noBase = new Element(tag, "", attribs);
        Element withBase = new Element(tag,"http://foo/",attribs);

        Element dodgyBase = new Element(tag, "wtf://no-such-protocol/", attribs);
        assertEquals("", dodgyBase.absUrl("relHref")); // base fails, only rel href, so return nothing;
        }

    @Test public void setBaseUriIsRecursive_1_oe() {
        Document doc = Jsoup.parse("<div><p></p></div>");
        String baseUri = "https://jsoup.org";
        doc.setBaseUri(baseUri);

        assertEquals(baseUri, doc.baseUri());
        }

    @Test public void setBaseUriIsRecursive_2_oe() {
        Document doc = Jsoup.parse("<div><p></p></div>");
        String baseUri = "https://jsoup.org";
        doc.setBaseUri(baseUri);

        assertEquals(baseUri, doc.select("div").first().baseUri());
        }

    @Test public void setBaseUriIsRecursive_3_oe() {
        Document doc = Jsoup.parse("<div><p></p></div>");
        String baseUri = "https://jsoup.org";
        doc.setBaseUri(baseUri);

        assertEquals(baseUri, doc.select("p").first().baseUri());
        }

    @Test public void handlesAbsPrefix_1_oe() {
        Document doc = Jsoup.parse("<a href=/foo>Hello</a>", "https://jsoup.org/");
        Element a = doc.select("a").first();
        assertEquals("/foo", a.attr("href"));
        }

    @Test public void handlesAbsPrefix_2_oe() {
        Document doc = Jsoup.parse("<a href=/foo>Hello</a>", "https://jsoup.org/");
        Element a = doc.select("a").first();
        assertEquals("https://jsoup.org/foo", a.attr("abs:href"));
        }

    @Test public void handlesAbsPrefix_3_oe() {
        Document doc = Jsoup.parse("<a href=/foo>Hello</a>", "https://jsoup.org/");
        Element a = doc.select("a").first();
        assertTrue(a.hasAttr("abs:href"));
        }

    @Test public void handlesAbsOnImage_1_oe() {
        Document doc = Jsoup.parse("<p><img src=\"/rez/osi_logo.png\" /></p>", "https://jsoup.org/");
        Element img = doc.select("img").first();
        assertEquals("https://jsoup.org/rez/osi_logo.png", img.attr("abs:src"));
        }

    @Test public void handlesAbsOnImage_2_oe() {
        Document doc = Jsoup.parse("<p><img src=\"/rez/osi_logo.png\" /></p>", "https://jsoup.org/");
        Element img = doc.select("img").first();
        assertEquals(img.absUrl("src"), img.attr("abs:src"));
        }

    @Test public void handlesAbsPrefixOnHasAttr_1_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org/'>Two</a>");
        Element one = doc.select("#1").first();
        Element two = doc.select("#2").first();

        assertFalse(one.hasAttr("abs:href"));
        }

    @Test public void handlesAbsPrefixOnHasAttr_2_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org/'>Two</a>");
        Element one = doc.select("#1").first();
        Element two = doc.select("#2").first();

        assertTrue(one.hasAttr("href"));
        }

    @Test public void handlesAbsPrefixOnHasAttr_3_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org/'>Two</a>");
        Element one = doc.select("#1").first();
        Element two = doc.select("#2").first();

        assertEquals("", one.absUrl("href"));
        }

    @Test public void handlesAbsPrefixOnHasAttr_4_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org/'>Two</a>");
        Element one = doc.select("#1").first();
        Element two = doc.select("#2").first();


        assertTrue(two.hasAttr("abs:href"));
        }

    @Test public void handlesAbsPrefixOnHasAttr_5_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org/'>Two</a>");
        Element one = doc.select("#1").first();
        Element two = doc.select("#2").first();


        assertTrue(two.hasAttr("href"));
        }

    @Test public void handlesAbsPrefixOnHasAttr_6_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org/'>Two</a>");
        Element one = doc.select("#1").first();
        Element two = doc.select("#2").first();


        assertEquals("https://jsoup.org/", two.absUrl("href"));
        }

    @Test public void literalAbsPrefix_1_oe() {
        Document doc = Jsoup.parse("<a abs:href='odd'>One</a>");
        Element el = doc.select("a").first();
        assertTrue(el.hasAttr("abs:href"));
        }

    @Test public void literalAbsPrefix_2_oe() {
        Document doc = Jsoup.parse("<a abs:href='odd'>One</a>");
        Element el = doc.select("a").first();
        assertEquals("odd", el.attr("abs:href"));
        }

    @Test public void handleAbsOnFileUris_1_oe() {
        Document doc = Jsoup.parse("<a href='password'>One/a><a href='/var/log/messages'>Two</a>", "file:/etc/");
        Element one = doc.select("a").first();
        assertEquals("file:/etc/password", one.absUrl("href"));
        }

    @Test public void handleAbsOnFileUris_2_oe() {
        Document doc = Jsoup.parse("<a href='password'>One/a><a href='/var/log/messages'>Two</a>", "file:/etc/");
        Element one = doc.select("a").first();
        Element two = doc.select("a").get(1);
        assertEquals("file:/var/log/messages", two.absUrl("href"));
        }

    @Test
    public void handleAbsOnLocalhostFileUris_1_oe() {
        Document doc = Jsoup.parse("<a href='password'>One/a><a href='/var/log/messages'>Two</a>", "file://localhost/etc/");
        Element one = doc.select("a").first();
        assertEquals("file://localhost/etc/password", one.absUrl("href"));
    }

    @Test
    public void handlesAbsOnProtocolessAbsoluteUris_1_oe() {
        Document doc1 = Jsoup.parse("<a href='//example.net/foo'>One</a>", "http://example.com/");
        Document doc2 = Jsoup.parse("<a href='//example.net/foo'>One</a>", "https://example.com/");

        Element one = doc1.select("a").first();
        Element two = doc2.select("a").first();

        assertEquals("http://example.net/foo", one.absUrl("href"));
    }

    @Test
    public void handlesAbsOnProtocolessAbsoluteUris_2_oe() {
        Document doc1 = Jsoup.parse("<a href='//example.net/foo'>One</a>", "http://example.com/");
        Document doc2 = Jsoup.parse("<a href='//example.net/foo'>One</a>", "https://example.com/");

        Element one = doc1.select("a").first();
        Element two = doc2.select("a").first();

        assertEquals("https://example.net/foo", two.absUrl("href"));
    }

    @Test
    public void handlesAbsOnProtocolessAbsoluteUris_3_oe() {
        Document doc1 = Jsoup.parse("<a href='//example.net/foo'>One</a>", "http://example.com/");
        Document doc2 = Jsoup.parse("<a href='//example.net/foo'>One</a>", "https://example.com/");

        Element one = doc1.select("a").first();
        Element two = doc2.select("a").first();


        Document doc3 = Jsoup.parse("<img src=//www.google.com/images/errors/logo_sm.gif alt=Google>", "https://google.com");
        assertEquals("https://www.google.com/images/errors/logo_sm.gif", doc3.select("img").attr("abs:src"));
    }

    @Test public void absHandlesRelativeQuery_1_oe() {
        Document doc = Jsoup.parse("<a href='?foo'>One</a> <a href='bar.html?foo'>Two</a>", "https://jsoup.org/path/file?bar");

        Element a1 = doc.select("a").first();
        assertEquals("https://jsoup.org/path/file?foo", a1.absUrl("href"));
        }

    @Test public void absHandlesRelativeQuery_2_oe() {
        Document doc = Jsoup.parse("<a href='?foo'>One</a> <a href='bar.html?foo'>Two</a>", "https://jsoup.org/path/file?bar");

        Element a1 = doc.select("a").first();

        Element a2 = doc.select("a").get(1);
        assertEquals("https://jsoup.org/path/bar.html?foo", a2.absUrl("href"));
        }

    @Test public void absHandlesDotFromIndex_1_oe() {
        Document doc = Jsoup.parse("<a href='./one/two.html'>One</a>", "http://example.com");
        Element a1 = doc.select("a").first();
        assertEquals("http://example.com/one/two.html", a1.absUrl("href"));
        }

    @Test public void handlesAbsOnUnknownProtocols_1_oe() {

        String[] urls = {"mailto:example@example.com", "tel:867-5309"}; // mail has a handler, tel doesn't
        for (String url : urls) {
            Attributes attr = new Attributes().put("href", url);
            Element noBase = new Element(Tag.valueOf("a"), null, attr);
            assertEquals(url, noBase.absUrl("href"));
        }
        }

    @Test public void handlesAbsOnUnknownProtocols_2_oe() {

        String[] urls = {"mailto:example@example.com", "tel:867-5309"}; // mail has a handler, tel doesn't
        for (String url : urls) {
            Attributes attr = new Attributes().put("href", url);
            Element noBase = new Element(Tag.valueOf("a"), null, attr);

            Element withBase = new Element(Tag.valueOf("a"), "http://example.com/", attr);
            assertEquals(url, withBase.absUrl("href"));
        }
        }

    @Test public void testRemove_1_oe() {
        Document doc = Jsoup.parse("<p>One <span>two</span> three</p>");
        Element p = doc.select("p").first();
        p.childNode(0).remove();

        assertEquals("two three", p.text());
        }

    @Test public void testRemove_2_oe() {
        Document doc = Jsoup.parse("<p>One <span>two</span> three</p>");
        Element p = doc.select("p").first();
        p.childNode(0).remove();

        assertEquals("<span>two</span> three", TextUtil.stripNewlines(p.html()));
        }

    @Test public void testReplace_1_oe() {
        Document doc = Jsoup.parse("<p>One <span>two</span> three</p>");
        Element p = doc.select("p").first();
        Element insert = doc.createElement("em").text("foo");
        p.childNode(1).replaceWith(insert);

        assertEquals("One <em>foo</em> three", p.html());
        }

    @Test public void ownerDocument_1_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        Element p = doc.select("p").first();
        assertSame(p.ownerDocument(), doc);
        }

    @Test public void ownerDocument_2_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        Element p = doc.select("p").first();
        assertSame(doc.ownerDocument(), doc);
        }

    @Test public void ownerDocument_3_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        Element p = doc.select("p").first();
        assertNull(doc.parent());
        }

    @Test public void root_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.select("p").first();
        Node root = p.root();
        assertSame(doc, root);
        }

    @Test public void root_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.select("p").first();
        Node root = p.root();
        assertNull(root.parent());
        }

    @Test public void root_3_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.select("p").first();
        Node root = p.root();
        assertSame(doc.root(), doc);
        }

    @Test public void root_4_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.select("p").first();
        Node root = p.root();
        assertSame(doc.root(), doc.ownerDocument());
        }

    @Test public void root_5_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.select("p").first();
        Node root = p.root();

        Element standAlone = new Element(Tag.valueOf("p"), "");
        assertNull(standAlone.parent());
        }

    @Test public void root_6_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.select("p").first();
        Node root = p.root();

        Element standAlone = new Element(Tag.valueOf("p"), "");
        assertSame(standAlone.root(), standAlone);
        }

    @Test public void root_7_oe() {
        Document doc = Jsoup.parse("<div><p>Hello");
        Element p = doc.select("p").first();
        Node root = p.root();

        Element standAlone = new Element(Tag.valueOf("p"), "");
        assertNull(standAlone.ownerDocument());
        }

    @Test public void before_1_oe() {
        Document doc = Jsoup.parse("<p>One <b>two</b> three</p>");
        Element newNode = new Element(Tag.valueOf("em"), "");
        newNode.appendText("four");

        doc.select("b").first().before(newNode);
        assertEquals("<p>One <em>four</em><b>two</b> three</p>", doc.body().html());
        }

    @Test public void before_2_oe() {
        Document doc = Jsoup.parse("<p>One <b>two</b> three</p>");
        Element newNode = new Element(Tag.valueOf("em"), "");
        newNode.appendText("four");

        doc.select("b").first().before(newNode);

        doc.select("b").first().before("<i>five</i>");
        assertEquals("<p>One <em>four</em><i>five</i><b>two</b> three</p>", doc.body().html());
        }

    @Test public void after_1_oe() {
        Document doc = Jsoup.parse("<p>One <b>two</b> three</p>");
        Element newNode = new Element(Tag.valueOf("em"), "");
        newNode.appendText("four");

        doc.select("b").first().after(newNode);
        assertEquals("<p>One <b>two</b><em>four</em> three</p>", doc.body().html());
        }

    @Test public void after_2_oe() {
        Document doc = Jsoup.parse("<p>One <b>two</b> three</p>");
        Element newNode = new Element(Tag.valueOf("em"), "");
        newNode.appendText("four");

        doc.select("b").first().after(newNode);

        doc.select("b").first().after("<i>five</i>");
        assertEquals("<p>One <b>two</b><i>five</i><em>four</em> three</p>", doc.body().html());
        }

    @Test public void unwrap_1_oe() {
        Document doc = Jsoup.parse("<div>One <span>Two <b>Three</b></span> Four</div>");
        Element span = doc.select("span").first();
        Node twoText = span.childNode(0);
        Node node = span.unwrap();

        assertEquals("<div>One Two <b>Three</b> Four</div>", TextUtil.stripNewlines(doc.body().html()));
        }

    @Test public void unwrap_2_oe() {
        Document doc = Jsoup.parse("<div>One <span>Two <b>Three</b></span> Four</div>");
        Element span = doc.select("span").first();
        Node twoText = span.childNode(0);
        Node node = span.unwrap();

        assertTrue(node instanceof TextNode);
        }

    @Test public void unwrap_3_oe() {
        Document doc = Jsoup.parse("<div>One <span>Two <b>Three</b></span> Four</div>");
        Element span = doc.select("span").first();
        Node twoText = span.childNode(0);
        Node node = span.unwrap();

        assertEquals("Two ", ((TextNode) node).text());
        }

    @Test public void unwrap_4_oe() {
        Document doc = Jsoup.parse("<div>One <span>Two <b>Three</b></span> Four</div>");
        Element span = doc.select("span").first();
        Node twoText = span.childNode(0);
        Node node = span.unwrap();

        assertEquals(node, twoText);
        }

    @Test public void unwrap_5_oe() {
        Document doc = Jsoup.parse("<div>One <span>Two <b>Three</b></span> Four</div>");
        Element span = doc.select("span").first();
        Node twoText = span.childNode(0);
        Node node = span.unwrap();

        assertEquals(node.parent(), doc.select("div").first());
        }

    @Test public void unwrapNoChildren_1_oe() {
        Document doc = Jsoup.parse("<div>One <span></span> Two</div>");
        Element span = doc.select("span").first();
        Node node = span.unwrap();
        assertEquals("<div>One  Two</div>", TextUtil.stripNewlines(doc.body().html()));
        }

    @Test public void unwrapNoChildren_2_oe() {
        Document doc = Jsoup.parse("<div>One <span></span> Two</div>");
        Element span = doc.select("span").first();
        Node node = span.unwrap();
        assertNull(node);
        }

    @Test public void traverse_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><div>There</div>");
        final StringBuilder accum = new StringBuilder();
        doc.select("div").first().traverse(new NodeVisitor() {
            @Override
            public void head(Node node, int depth) {
                accum.append("<").append(node.nodeName()).append(">");
            }

            @Override
            public void tail(Node node, int depth) {
                accum.append("</").append(node.nodeName()).append(">");
            }
        });
        assertEquals("<div><p><#text></#text></p></div>", accum.toString());
        }

    @Test public void forEachNode_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><div>There</div><div id=1>Gone<p></div>");
        doc.forEachNode(node -> {
            if (node instanceof TextNode) {
                TextNode textNode = (TextNode) node;
                if (textNode.text().equals("There")) {
                    textNode.text("There Now");
                    textNode.after("<p>Another");
                }
            } else if (node.attr("id").equals("1"))
                node.remove();
        });
        assertEquals("<div><p>Hello</p></div><div>There Now<p>Another</p></div>", TextUtil.stripNewlines(doc.body().html()));
        }

    @Test public void orphanNodeReturnsNullForSiblingElements_1_oe() {
        Node node = new Element(Tag.valueOf("p"), "");
        Element el = new Element(Tag.valueOf("p"), "");

        assertEquals(0, node.siblingIndex());
        }

    @Test public void orphanNodeReturnsNullForSiblingElements_2_oe() {
        Node node = new Element(Tag.valueOf("p"), "");
        Element el = new Element(Tag.valueOf("p"), "");

        assertEquals(0, node.siblingNodes().size());
        }

    @Test public void orphanNodeReturnsNullForSiblingElements_3_oe() {
        Node node = new Element(Tag.valueOf("p"), "");
        Element el = new Element(Tag.valueOf("p"), "");


        assertNull(node.previousSibling());
        }

    @Test public void orphanNodeReturnsNullForSiblingElements_4_oe() {
        Node node = new Element(Tag.valueOf("p"), "");
        Element el = new Element(Tag.valueOf("p"), "");


        assertNull(node.nextSibling());
        }

    @Test public void orphanNodeReturnsNullForSiblingElements_5_oe() {
        Node node = new Element(Tag.valueOf("p"), "");
        Element el = new Element(Tag.valueOf("p"), "");



        assertEquals(0, el.siblingElements().size());
        }

    @Test public void orphanNodeReturnsNullForSiblingElements_6_oe() {
        Node node = new Element(Tag.valueOf("p"), "");
        Element el = new Element(Tag.valueOf("p"), "");



        assertNull(el.previousElementSibling());
        }

    @Test public void orphanNodeReturnsNullForSiblingElements_7_oe() {
        Node node = new Element(Tag.valueOf("p"), "");
        Element el = new Element(Tag.valueOf("p"), "");



        assertNull(el.nextElementSibling());
        }

    @Test public void nodeIsNotASiblingOfItself_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div>");
        Element p2 = doc.select("p").get(1);

        assertEquals("Two", p2.text());
        }

    @Test public void nodeIsNotASiblingOfItself_2_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div>");
        Element p2 = doc.select("p").get(1);

        List<Node> nodes = p2.siblingNodes();
        assertEquals(2, nodes.size());
        }

    @Test public void nodeIsNotASiblingOfItself_3_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div>");
        Element p2 = doc.select("p").get(1);

        List<Node> nodes = p2.siblingNodes();
        assertEquals("<p>One</p>", nodes.get(0).outerHtml());
        }

    @Test public void nodeIsNotASiblingOfItself_4_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div>");
        Element p2 = doc.select("p").get(1);

        List<Node> nodes = p2.siblingNodes();
        assertEquals("<p>Three</p>", nodes.get(1).outerHtml());
        }

    @Test public void childNodesCopy_1_oe() {
        Document doc = Jsoup.parse("<div id=1>Text 1 <p>One</p> Text 2 <p>Two<p>Three</div><div id=2>");
        Element div1 = doc.select("#1").first();
        Element div2 = doc.select("#2").first();
        List<Node> divChildren = div1.childNodesCopy();
        assertEquals(5, divChildren.size());
        }

    @Test public void childNodesCopy_2_oe() {
        Document doc = Jsoup.parse("<div id=1>Text 1 <p>One</p> Text 2 <p>Two<p>Three</div><div id=2>");
        Element div1 = doc.select("#1").first();
        Element div2 = doc.select("#2").first();
        List<Node> divChildren = div1.childNodesCopy();
        TextNode tn1 = (TextNode) div1.childNode(0);
        TextNode tn2 = (TextNode) divChildren.get(0);
        tn2.text("Text 1 updated");
        assertEquals("Text 1 ", tn1.text());
        }

    @Test public void childNodesCopy_3_oe() {
        Document doc = Jsoup.parse("<div id=1>Text 1 <p>One</p> Text 2 <p>Two<p>Three</div><div id=2>");
        Element div1 = doc.select("#1").first();
        Element div2 = doc.select("#2").first();
        List<Node> divChildren = div1.childNodesCopy();
        TextNode tn1 = (TextNode) div1.childNode(0);
        TextNode tn2 = (TextNode) divChildren.get(0);
        tn2.text("Text 1 updated");
        div2.insertChildren(-1, divChildren);
        assertEquals("<div id=\"1\">Text 1 <p>One</p> Text 2 <p>Two</p><p>Three</p></div><div id=\"2\">Text 1 updated" +"<p>One</p> Text 2 <p>Two</p><p>Three</p></div>",TextUtil.stripNewlines(doc.body().html()));
        }

    @Test public void supportsClone_1_oe() {
        Document doc = org.jsoup.Jsoup.parse("<div class=foo>Text</div>");
        Element el = doc.select("div").first();
        assertTrue(el.hasClass("foo"));
        }

    @Test public void supportsClone_2_oe() {
        Document doc = org.jsoup.Jsoup.parse("<div class=foo>Text</div>");
        Element el = doc.select("div").first();

        Element elClone = doc.clone().select("div").first();
        assertTrue(elClone.hasClass("foo"));
        }

    @Test public void supportsClone_3_oe() {
        Document doc = org.jsoup.Jsoup.parse("<div class=foo>Text</div>");
        Element el = doc.select("div").first();

        Element elClone = doc.clone().select("div").first();
        assertEquals("Text", elClone.text());
        }

    @Test public void supportsClone_4_oe() {
        Document doc = org.jsoup.Jsoup.parse("<div class=foo>Text</div>");
        Element el = doc.select("div").first();

        Element elClone = doc.clone().select("div").first();

        el.removeClass("foo");
        el.text("None");
        assertFalse(el.hasClass("foo"));
        }

    @Test public void supportsClone_5_oe() {
        Document doc = org.jsoup.Jsoup.parse("<div class=foo>Text</div>");
        Element el = doc.select("div").first();

        Element elClone = doc.clone().select("div").first();

        el.removeClass("foo");
        el.text("None");
        assertTrue(elClone.hasClass("foo"));
        }

    @Test public void supportsClone_6_oe() {
        Document doc = org.jsoup.Jsoup.parse("<div class=foo>Text</div>");
        Element el = doc.select("div").first();

        Element elClone = doc.clone().select("div").first();

        el.removeClass("foo");
        el.text("None");
        assertEquals("None", el.text());
        }

    @Test public void supportsClone_7_oe() {
        Document doc = org.jsoup.Jsoup.parse("<div class=foo>Text</div>");
        Element el = doc.select("div").first();

        Element elClone = doc.clone().select("div").first();

        el.removeClass("foo");
        el.text("None");
        assertEquals("Text", elClone.text());
        }

    @Test public void changingAttributeValueShouldReplaceExistingAttributeCaseInsensitive_1_oe() {
        Document document = Jsoup.parse("<INPUT id=\"foo\" NAME=\"foo\" VALUE=\"\">");
        Element inputElement = document.select("#foo").first();

        inputElement.attr("value","bar");

        assertEquals(singletonAttributes(), getAttributesCaseInsensitive(inputElement));
        }

    @Test void clonedNodesHaveOwnerDocsAndIndependentSettings_1_oe() {
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        assertNotNull(div);
        }

    @Test void clonedNodesHaveOwnerDocsAndIndependentSettings_2_oe() {
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        TextNode text = (TextNode) div.childNode(0);
        assertNotNull(text);
        }

    @Test void clonedNodesHaveOwnerDocsAndIndependentSettings_3_oe() {
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        TextNode text = (TextNode) div.childNode(0);

        TextNode textClone = text.clone();
        Document docClone = textClone.ownerDocument();
        assertNotNull(docClone);
        }

    @Test void clonedNodesHaveOwnerDocsAndIndependentSettings_4_oe() {
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        TextNode text = (TextNode) div.childNode(0);

        TextNode textClone = text.clone();
        Document docClone = textClone.ownerDocument();
        assertFalse(docClone.outputSettings().prettyPrint());
        }

    @Test void clonedNodesHaveOwnerDocsAndIndependentSettings_5_oe() {
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        TextNode text = (TextNode) div.childNode(0);

        TextNode textClone = text.clone();
        Document docClone = textClone.ownerDocument();
        assertNotSame(doc, docClone);
        }

    @Test void clonedNodesHaveOwnerDocsAndIndependentSettings_6_oe() {
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        TextNode text = (TextNode) div.childNode(0);

        TextNode textClone = text.clone();
        Document docClone = textClone.ownerDocument();

        doc.outputSettings().prettyPrint(true);
        assertTrue(doc.outputSettings().prettyPrint());
        }

    @Test void clonedNodesHaveOwnerDocsAndIndependentSettings_7_oe() {
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        TextNode text = (TextNode) div.childNode(0);

        TextNode textClone = text.clone();
        Document docClone = textClone.ownerDocument();

        doc.outputSettings().prettyPrint(true);
        assertFalse(docClone.outputSettings().prettyPrint());
        }

    @Test void clonedNodesHaveOwnerDocsAndIndependentSettings_8_oe() {
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        TextNode text = (TextNode) div.childNode(0);

        TextNode textClone = text.clone();
        Document docClone = textClone.ownerDocument();

        doc.outputSettings().prettyPrint(true);
        assertEquals(1, docClone.childNodes().size()); // check did not get the second div as the owner's children;
        }

    @Test void clonedNodesHaveOwnerDocsAndIndependentSettings_9_oe() {
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        TextNode text = (TextNode) div.childNode(0);

        TextNode textClone = text.clone();
        Document docClone = textClone.ownerDocument();

        doc.outputSettings().prettyPrint(true);
        assertEquals(textClone, docClone.childNode(0)); // note not the head or the body -- not normalized;
        }

    @Test
    void firstAndLastChild_1_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");
        assertNotNull(div);
    }

    @Test
    void firstAndLastChild_2_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");
        assertNotNull(a);
    }

    @Test
    void firstAndLastChild_3_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");

        TextNode first = (TextNode) div.firstChild();
        assertEquals("One ", first.text());
    }

    @Test
    void firstAndLastChild_4_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");

        TextNode first = (TextNode) div.firstChild();

        TextNode last = (TextNode) div.lastChild();
        assertEquals(" Three", last.text());
    }

    @Test
    void firstAndLastChild_5_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");

        TextNode first = (TextNode) div.firstChild();

        TextNode last = (TextNode) div.lastChild();

        assertNull(a.firstChild());
    }

    @Test
    void firstAndLastChild_6_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");

        TextNode first = (TextNode) div.firstChild();

        TextNode last = (TextNode) div.lastChild();

        assertNull(a.lastChild());
    }

    @Test
    void firstAndLastChild_7_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");

        TextNode first = (TextNode) div.firstChild();

        TextNode last = (TextNode) div.lastChild();


        Element firstEl = div.firstElementChild();
        assertEquals("span", firstEl.tagName());
    }

    @Test
    void firstAndLastChild_8_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");

        TextNode first = (TextNode) div.firstChild();

        TextNode last = (TextNode) div.lastChild();


        Element firstEl = div.firstElementChild();

        Element lastEl = div.lastElementChild();
        assertEquals("a", lastEl.tagName());
    }

    @Test
    void firstAndLastChild_9_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");

        TextNode first = (TextNode) div.firstChild();

        TextNode last = (TextNode) div.lastChild();


        Element firstEl = div.firstElementChild();

        Element lastEl = div.lastElementChild();

        assertNull(a.firstElementChild());
    }

    @Test
    void firstAndLastChild_10_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");

        TextNode first = (TextNode) div.firstChild();

        TextNode last = (TextNode) div.lastChild();


        Element firstEl = div.firstElementChild();

        Element lastEl = div.lastElementChild();

        assertNull(a.lastElementChild());
    }

    @Test
    void firstAndLastChild_11_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");

        TextNode first = (TextNode) div.firstChild();

        TextNode last = (TextNode) div.lastChild();


        Element firstEl = div.firstElementChild();

        Element lastEl = div.lastElementChild();


        assertNull(firstEl.firstElementChild());
    }

    @Test
    void firstAndLastChild_12_oe() {
        String html = "<div>One <span>Two</span> <a href></a> Three</div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element a = doc.selectFirst("a");

        TextNode first = (TextNode) div.firstChild();

        TextNode last = (TextNode) div.lastChild();


        Element firstEl = div.firstElementChild();

        Element lastEl = div.lastElementChild();


        assertNull(firstEl.lastElementChild());
    }

}
