package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.helper.ValidationException;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.QueryParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tests for Element (DOM stuff mostly).

 @author Jonathan Hedley */
public class ElementTest_OE25Dev {
    private String reference = "<div id=div1><p>Hello</p><p>Another <b>element</b></p><div id=div2><img src=foo.png></div></div>";

    private static void validateScriptContents(String src, Element el) {
        assertEquals("",el.text());// it's not text assertEquals("",el.ownText());
        assertEquals("", el.wholeText());
        assertEquals(src, el.html());
        assertEquals(src, el.data());
    }

    private static void validateXmlScriptContents(Element el) {
        assertEquals("var foo = 5 < 2; var bar = 1 && 2;", el.text());
        assertEquals("var foo = 5 < 2; var bar = 1 && 2;", el.ownText());
        assertEquals("var foo = 5 < 2;\nvar bar = 1 && 2;", el.wholeText());
        assertEquals("var foo = 5 &lt; 2;\nvar bar = 1 &amp;&amp; 2;", el.html());
        assertEquals("", el.data());
    }

    @Test
    public void insertChildrenArgumentValidation() {
        Document doc = Jsoup.parse("<div id=1>Text <p>One</p> Text <p>Two</p></div><div id=2></div>");
        Element div1 = doc.select("div").get(0);
        Element div2 = doc.select("div").get(1);
        List<Node> children = div1.childNodes();

        try {
            div2.insertChildren(6, children);
            fail();
        } catch (IllegalArgumentException e) {
        }

        try {
            div2.insertChildren(-5, children);
            fail();
        } catch (IllegalArgumentException e) {
        }

        try {
            div2.insertChildren(0, (Collection<? extends Node>) null);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    private static Stream<Document.OutputSettings> testOutputSettings() {
        return Stream.of(
            new Document.OutputSettings().prettyPrint(true).indentAmount(4),
            new Document.OutputSettings().prettyPrint(true).indentAmount(1),
            new Document.OutputSettings().prettyPrint(true).indentAmount(4).outline(true),
            new Document.OutputSettings().prettyPrint(false)
        );
    }

@Test
    public void testId_1_oe() {
        Document doc = Jsoup.parse("<div id=Foo>");
        Element el = doc.selectFirst("div");
        assertEquals("Foo", el.id());
    }

@Test
    public void testSetId_1_oe() {
        Document doc = Jsoup.parse("<div id=Boo>");
        Element el = doc.selectFirst("div");
        el.id("Foo");
        assertEquals("Foo", el.id());
    }

@Test
    public void getElementsByTagName_1_oe() {
        Document doc = Jsoup.parse(reference);
        List<Element> divs = doc.getElementsByTag("div");
        assertEquals(2, divs.size());
    }

@Test
    public void getElementsByTagName_2_oe() {
        Document doc = Jsoup.parse(reference);
        List<Element> divs = doc.getElementsByTag("div");
        // removed other assertion
        assertEquals("div1", divs.get(0).id());
    }

@Test
    public void getElementsByTagName_3_oe() {
        Document doc = Jsoup.parse(reference);
        List<Element> divs = doc.getElementsByTag("div");
        // removed other assertion
        // removed other assertion
        assertEquals("div2", divs.get(1).id());
    }

@Test
    public void getElementsByTagName_4_oe() {
        Document doc = Jsoup.parse(reference);
        List<Element> divs = doc.getElementsByTag("div");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> ps = doc.getElementsByTag("p");
        assertEquals(2, ps.size());
    }

@Test
    public void getElementsByTagName_5_oe() {
        Document doc = Jsoup.parse(reference);
        List<Element> divs = doc.getElementsByTag("div");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> ps = doc.getElementsByTag("p");
        // removed other assertion
        assertEquals("Hello", ((TextNode) ps.get(0).childNode(0)).getWholeText());
    }

@Test
    public void getElementsByTagName_6_oe() {
        Document doc = Jsoup.parse(reference);
        List<Element> divs = doc.getElementsByTag("div");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> ps = doc.getElementsByTag("p");
        // removed other assertion
        // removed other assertion
        assertEquals("Another ", ((TextNode) ps.get(1).childNode(0)).getWholeText());
    }

@Test
    public void getElementsByTagName_7_oe() {
        Document doc = Jsoup.parse(reference);
        List<Element> divs = doc.getElementsByTag("div");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> ps = doc.getElementsByTag("p");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        List<Element> ps2 = doc.getElementsByTag("P");
        assertEquals(ps, ps2);
    }

@Test
    public void getElementsByTagName_8_oe() {
        Document doc = Jsoup.parse(reference);
        List<Element> divs = doc.getElementsByTag("div");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> ps = doc.getElementsByTag("p");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        List<Element> ps2 = doc.getElementsByTag("P");
        // removed other assertion

        List<Element> imgs = doc.getElementsByTag("img");
        assertEquals("foo.png", imgs.get(0).attr("src"));
    }

@Test
    public void getElementsByTagName_9_oe() {
        Document doc = Jsoup.parse(reference);
        List<Element> divs = doc.getElementsByTag("div");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> ps = doc.getElementsByTag("p");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        List<Element> ps2 = doc.getElementsByTag("P");
        // removed other assertion

        List<Element> imgs = doc.getElementsByTag("img");
        // removed other assertion

        List<Element> empty = doc.getElementsByTag("wtf");
        assertEquals(0, empty.size());
    }

@Test
    public void getNamespacedElementsByTag_1_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div>");
        Elements els = doc.getElementsByTag("abc:def");
        assertEquals(1, els.size());
    }

@Test
    public void getNamespacedElementsByTag_2_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div>");
        Elements els = doc.getElementsByTag("abc:def");
        // removed other assertion
        assertEquals("1", els.first().id());
    }

@Test
    public void getNamespacedElementsByTag_3_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div>");
        Elements els = doc.getElementsByTag("abc:def");
        // removed other assertion
        // removed other assertion
        assertEquals("abc:def", els.first().tagName());
    }

@Test
    public void testGetElementById_1_oe() {
        Document doc = Jsoup.parse(reference);
        Element div = doc.getElementById("div1");
        assertEquals("div1", div.id());
    }

@Test
    public void testGetElementById_2_oe() {
        Document doc = Jsoup.parse(reference);
        Element div = doc.getElementById("div1");
        // removed other assertion
        assertNull(doc.getElementById("none"));
    }

@Test
    public void testGetElementById_3_oe() {
        Document doc = Jsoup.parse(reference);
        Element div = doc.getElementById("div1");
        // removed other assertion
        // removed other assertion

        Document doc2 = Jsoup.parse("<div id=1><div id=2><p>Hello <span id=2>world!</span></p></div></div>");
        Element div2 = doc2.getElementById("2");
        assertEquals("div",div2.tagName());// not the span Element span = div2.child(0).getElementById("2");// called from <p> context should be span assertEquals("span",span.tagName());
    }

@Test
    public void testGetText_1_oe() {
        Document doc = Jsoup.parse(reference);
        assertEquals("Hello Another element", doc.text());
    }

@Test
    public void testGetText_2_oe() {
        Document doc = Jsoup.parse(reference);
        // removed other assertion
        assertEquals("Another element", doc.getElementsByTag("p").get(1).text());
    }

@Test
    public void testGetChildText_1_oe() {
        Document doc = Jsoup.parse("<p>Hello <b>there</b> now");
        Element p = doc.select("p").first();
        assertEquals("Hello there now", p.text());
    }

@Test
    public void testGetChildText_2_oe() {
        Document doc = Jsoup.parse("<p>Hello <b>there</b> now");
        Element p = doc.select("p").first();
        // removed other assertion
        assertEquals("Hello now", p.ownText());
    }

@Test
    public void testNormalisesText_1_oe() {
        String h = "<p>Hello<p>There.</p> \n <p>Here <b>is</b> \n s<b>om</b>e text.";
        Document doc = Jsoup.parse(h);
        String text = doc.text();
        assertEquals("Hello There. Here is some text.", text);
    }

@Test
    public void testKeepsPreText_1_oe() {
        String h = "<p>Hello \n \n there.</p> <div><pre>  What's \n\n  that?</pre>";
        Document doc = Jsoup.parse(h);
        assertEquals("Hello there.   What's \n\n  that?", doc.text());
    }

@Test
    public void testKeepsPreTextInCode_1_oe() {
        String h = "<pre><code>code\n\ncode</code></pre>";
        Document doc = Jsoup.parse(h);
        assertEquals("code\n\ncode", doc.text());
    }

@Test
    public void testKeepsPreTextInCode_2_oe() {
        String h = "<pre><code>code\n\ncode</code></pre>";
        Document doc = Jsoup.parse(h);
        // removed other assertion
        assertEquals("<pre><code>code\n\ncode</code></pre>", doc.body().html());
    }

@Test
    public void testKeepsPreTextAtDepth_1_oe() {
        String h = "<pre><code><span><b>code\n\ncode</b></span></code></pre>";
        Document doc = Jsoup.parse(h);
        assertEquals("code\n\ncode", doc.text());
    }

@Test
    public void testKeepsPreTextAtDepth_2_oe() {
        String h = "<pre><code><span><b>code\n\ncode</b></span></code></pre>";
        Document doc = Jsoup.parse(h);
        // removed other assertion
        assertEquals("<pre><code><span><b>code\n\ncode</b></span></code></pre>", doc.body().html());
    }

@Test
    public void testBrHasSpace_1_oe() {
        Document doc = Jsoup.parse("<p>Hello<br>there</p>");
        assertEquals("Hello there", doc.text());
    }

@Test
    public void testBrHasSpace_2_oe() {
        Document doc = Jsoup.parse("<p>Hello<br>there</p>");
        // removed other assertion
        assertEquals("Hello there", doc.select("p").first().ownText());
    }

@Test
    public void testBrHasSpace_3_oe() {
        Document doc = Jsoup.parse("<p>Hello<br>there</p>");
        // removed other assertion
        // removed other assertion

        doc = Jsoup.parse("<p>Hello <br> there</p>");
        assertEquals("Hello there", doc.text());
    }

@Test
    public void testBrHasSpaceCaseSensitive_1_oe() {
        Document doc = Jsoup.parse("<p>Hello<br>there<BR>now</p>", Parser.htmlParser().settings(ParseSettings.preserveCase));
        assertEquals("Hello there now", doc.text());
    }

@Test
    public void testBrHasSpaceCaseSensitive_2_oe() {
        Document doc = Jsoup.parse("<p>Hello<br>there<BR>now</p>", Parser.htmlParser().settings(ParseSettings.preserveCase));
        // removed other assertion
        assertEquals("Hello there now", doc.select("p").first().ownText());
    }

@Test
    public void testBrHasSpaceCaseSensitive_3_oe() {
        Document doc = Jsoup.parse("<p>Hello<br>there<BR>now</p>", Parser.htmlParser().settings(ParseSettings.preserveCase));
        // removed other assertion
        // removed other assertion

        doc = Jsoup.parse("<p>Hello <br> there <BR> now</p>");
        assertEquals("Hello there now", doc.text());
    }

@Test
    public void testWholeText_1_oe() {
        Document doc = Jsoup.parse("<p> Hello\nthere &nbsp;  </p>");
        assertEquals(" Hello\nthere    ", doc.wholeText());
    }

@Test
    public void testWholeText_2_oe() {
        Document doc = Jsoup.parse("<p> Hello\nthere &nbsp;  </p>");
        // removed other assertion

        doc = Jsoup.parse("<p>Hello  \n  there</p>");
        assertEquals("Hello  \n  there", doc.wholeText());
    }

@Test
    public void testWholeText_3_oe() {
        Document doc = Jsoup.parse("<p> Hello\nthere &nbsp;  </p>");
        // removed other assertion

        doc = Jsoup.parse("<p>Hello  \n  there</p>");
        // removed other assertion

        doc = Jsoup.parse("<p>Hello  <div>\n  there</div></p>");
        assertEquals("Hello  \n  there", doc.wholeText());
    }

@Test
    public void testGetSiblings_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        assertEquals("there", p.text());
    }

@Test
    public void testGetSiblings_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        // removed other assertion
        assertEquals("Hello", p.previousElementSibling().text());
    }

@Test
    public void testGetSiblings_3_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        // removed other assertion
        // removed other assertion
        assertEquals("this", p.nextElementSibling().text());
    }

@Test
    public void testGetSiblings_4_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Hello", p.firstElementSibling().text());
    }

@Test
    public void testGetSiblings_5_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("element", p.lastElementSibling().text());
    }

@Test
    public void testGetSiblingsWithDuplicateContent_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        assertEquals("there", p.text());
    }

@Test
    public void testGetSiblingsWithDuplicateContent_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        // removed other assertion
        assertEquals("Hello", p.previousElementSibling().text());
    }

@Test
    public void testGetSiblingsWithDuplicateContent_3_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        // removed other assertion
        // removed other assertion
        assertEquals("this", p.nextElementSibling().text());
    }

@Test
    public void testGetSiblingsWithDuplicateContent_4_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("this", p.nextElementSibling().nextElementSibling().text());
    }

@Test
    public void testGetSiblingsWithDuplicateContent_5_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("is", p.nextElementSibling().nextElementSibling().nextElementSibling().text());
    }

@Test
    public void testGetSiblingsWithDuplicateContent_6_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Hello", p.firstElementSibling().text());
    }

@Test
    public void testGetSiblingsWithDuplicateContent_7_oe() {
        Document doc = Jsoup.parse("<div><p>Hello<p id=1>there<p>this<p>this<p>is<p>an<p id=last>element</div>");
        Element p = doc.getElementById("1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("element", p.lastElementSibling().text());
    }

@Test
    public void testFirstElementSiblingOnOrphan_1_oe() {
        Element p = new Element("p");
        assertSame(p, p.firstElementSibling());
    }

@Test
    public void testFirstElementSiblingOnOrphan_2_oe() {
        Element p = new Element("p");
        // removed other assertion
        assertSame(p, p.lastElementSibling());
    }

@Test
    public void testFirstAndLastSiblings_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        Element one = div.child(0);
        Element two = div.child(1);
        Element three = div.child(2);

        assertSame(one, one.firstElementSibling());
    }

@Test
    public void testFirstAndLastSiblings_2_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        Element one = div.child(0);
        Element two = div.child(1);
        Element three = div.child(2);

        // removed other assertion
        assertSame(one, two.firstElementSibling());
    }

@Test
    public void testFirstAndLastSiblings_3_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        Element one = div.child(0);
        Element two = div.child(1);
        Element three = div.child(2);

        // removed other assertion
        // removed other assertion
        assertSame(three, three.lastElementSibling());
    }

@Test
    public void testFirstAndLastSiblings_4_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        Element one = div.child(0);
        Element two = div.child(1);
        Element three = div.child(2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(three, two.lastElementSibling());
    }

@Test
    public void testGetParents_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello <span>there</span></div>");
        Element span = doc.select("span").first();
        Elements parents = span.parents();

        assertEquals(4, parents.size());
    }

@Test
    public void testGetParents_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello <span>there</span></div>");
        Element span = doc.select("span").first();
        Elements parents = span.parents();

        // removed other assertion
        assertEquals("p", parents.get(0).tagName());
    }

@Test
    public void testGetParents_3_oe() {
        Document doc = Jsoup.parse("<div><p>Hello <span>there</span></div>");
        Element span = doc.select("span").first();
        Elements parents = span.parents();

        // removed other assertion
        // removed other assertion
        assertEquals("div", parents.get(1).tagName());
    }

@Test
    public void testGetParents_4_oe() {
        Document doc = Jsoup.parse("<div><p>Hello <span>there</span></div>");
        Element span = doc.select("span").first();
        Elements parents = span.parents();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("body", parents.get(2).tagName());
    }

@Test
    public void testGetParents_5_oe() {
        Document doc = Jsoup.parse("<div><p>Hello <span>there</span></div>");
        Element span = doc.select("span").first();
        Elements parents = span.parents();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("html", parents.get(3).tagName());
    }

@Test
    public void testElementSiblingIndex_1_oe() {
        Document doc = Jsoup.parse("<div><p>One</p>...<p>Two</p>...<p>Three</p>");
        Elements ps = doc.select("p");
        assertEquals(0, ps.get(0).elementSiblingIndex());
    }

@Test
    public void testElementSiblingIndex_2_oe() {
        Document doc = Jsoup.parse("<div><p>One</p>...<p>Two</p>...<p>Three</p>");
        Elements ps = doc.select("p");
        // removed other assertion
        assertEquals(1, ps.get(1).elementSiblingIndex());
    }

@Test
    public void testElementSiblingIndex_3_oe() {
        Document doc = Jsoup.parse("<div><p>One</p>...<p>Two</p>...<p>Three</p>");
        Elements ps = doc.select("p");
        // removed other assertion
        // removed other assertion
        assertEquals(2, ps.get(2).elementSiblingIndex());
    }

@Test
    public void testElementSiblingIndexSameContent_1_oe() {
        Document doc = Jsoup.parse("<div><p>One</p>...<p>One</p>...<p>One</p>");
        Elements ps = doc.select("p");
        assertEquals(0, ps.get(0).elementSiblingIndex());
    }

@Test
    public void testElementSiblingIndexSameContent_2_oe() {
        Document doc = Jsoup.parse("<div><p>One</p>...<p>One</p>...<p>One</p>");
        Elements ps = doc.select("p");
        // removed other assertion
        assertEquals(1, ps.get(1).elementSiblingIndex());
    }

@Test
    public void testElementSiblingIndexSameContent_3_oe() {
        Document doc = Jsoup.parse("<div><p>One</p>...<p>One</p>...<p>One</p>");
        Elements ps = doc.select("p");
        // removed other assertion
        // removed other assertion
        assertEquals(2, ps.get(2).elementSiblingIndex());
    }

@Test
    public void testGetElementsWithClass_1_oe() {
        Document doc = Jsoup.parse("<div class='mellow yellow'><span class=mellow>Hello <b class='yellow'>Yellow!</b></span><p>Empty</p></div>");

        List<Element> els = doc.getElementsByClass("mellow");
        assertEquals(2, els.size());
    }

@Test
    public void testGetElementsWithClass_2_oe() {
        Document doc = Jsoup.parse("<div class='mellow yellow'><span class=mellow>Hello <b class='yellow'>Yellow!</b></span><p>Empty</p></div>");

        List<Element> els = doc.getElementsByClass("mellow");
        // removed other assertion
        assertEquals("div", els.get(0).tagName());
    }

@Test
    public void testGetElementsWithClass_3_oe() {
        Document doc = Jsoup.parse("<div class='mellow yellow'><span class=mellow>Hello <b class='yellow'>Yellow!</b></span><p>Empty</p></div>");

        List<Element> els = doc.getElementsByClass("mellow");
        // removed other assertion
        // removed other assertion
        assertEquals("span", els.get(1).tagName());
    }

@Test
    public void testGetElementsWithClass_4_oe() {
        Document doc = Jsoup.parse("<div class='mellow yellow'><span class=mellow>Hello <b class='yellow'>Yellow!</b></span><p>Empty</p></div>");

        List<Element> els = doc.getElementsByClass("mellow");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> els2 = doc.getElementsByClass("yellow");
        assertEquals(2, els2.size());
    }

@Test
    public void testGetElementsWithClass_5_oe() {
        Document doc = Jsoup.parse("<div class='mellow yellow'><span class=mellow>Hello <b class='yellow'>Yellow!</b></span><p>Empty</p></div>");

        List<Element> els = doc.getElementsByClass("mellow");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> els2 = doc.getElementsByClass("yellow");
        // removed other assertion
        assertEquals("div", els2.get(0).tagName());
    }

@Test
    public void testGetElementsWithClass_6_oe() {
        Document doc = Jsoup.parse("<div class='mellow yellow'><span class=mellow>Hello <b class='yellow'>Yellow!</b></span><p>Empty</p></div>");

        List<Element> els = doc.getElementsByClass("mellow");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> els2 = doc.getElementsByClass("yellow");
        // removed other assertion
        // removed other assertion
        assertEquals("b", els2.get(1).tagName());
    }

@Test
    public void testGetElementsWithClass_7_oe() {
        Document doc = Jsoup.parse("<div class='mellow yellow'><span class=mellow>Hello <b class='yellow'>Yellow!</b></span><p>Empty</p></div>");

        List<Element> els = doc.getElementsByClass("mellow");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> els2 = doc.getElementsByClass("yellow");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> none = doc.getElementsByClass("solo");
        assertEquals(0, none.size());
    }

@Test
    public void testGetElementsWithAttribute_1_oe() {
        Document doc = Jsoup.parse("<div style='bold'><p title=qux><p><b style></b></p></div>");
        List<Element> els = doc.getElementsByAttribute("style");
        assertEquals(2, els.size());
    }

@Test
    public void testGetElementsWithAttribute_2_oe() {
        Document doc = Jsoup.parse("<div style='bold'><p title=qux><p><b style></b></p></div>");
        List<Element> els = doc.getElementsByAttribute("style");
        // removed other assertion
        assertEquals("div", els.get(0).tagName());
    }

@Test
    public void testGetElementsWithAttribute_3_oe() {
        Document doc = Jsoup.parse("<div style='bold'><p title=qux><p><b style></b></p></div>");
        List<Element> els = doc.getElementsByAttribute("style");
        // removed other assertion
        // removed other assertion
        assertEquals("b", els.get(1).tagName());
    }

@Test
    public void testGetElementsWithAttribute_4_oe() {
        Document doc = Jsoup.parse("<div style='bold'><p title=qux><p><b style></b></p></div>");
        List<Element> els = doc.getElementsByAttribute("style");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Element> none = doc.getElementsByAttribute("class");
        assertEquals(0, none.size());
    }

@Test
    public void testGetElementsWithAttributeDash_1_oe() {
        Document doc = Jsoup.parse("<meta http-equiv=content-type value=utf8 id=1> <meta name=foo content=bar id=2> <div http-equiv=content-type value=utf8 id=3>");
        Elements meta = doc.select("meta[http-equiv=content-type], meta[charset]");
        assertEquals(1, meta.size());
    }

@Test
    public void testGetElementsWithAttributeDash_2_oe() {
        Document doc = Jsoup.parse("<meta http-equiv=content-type value=utf8 id=1> <meta name=foo content=bar id=2> <div http-equiv=content-type value=utf8 id=3>");
        Elements meta = doc.select("meta[http-equiv=content-type], meta[charset]");
        // removed other assertion
        assertEquals("1", meta.first().id());
    }

@Test
    public void testGetElementsWithAttributeValue_1_oe() {
        Document doc = Jsoup.parse("<div style='bold'><p><p><b style></b></p></div>");
        List<Element> els = doc.getElementsByAttributeValue("style", "bold");
        assertEquals(1, els.size());
    }

@Test
    public void testGetElementsWithAttributeValue_2_oe() {
        Document doc = Jsoup.parse("<div style='bold'><p><p><b style></b></p></div>");
        List<Element> els = doc.getElementsByAttributeValue("style", "bold");
        // removed other assertion
        assertEquals("div", els.get(0).tagName());
    }

@Test
    public void testGetElementsWithAttributeValue_3_oe() {
        Document doc = Jsoup.parse("<div style='bold'><p><p><b style></b></p></div>");
        List<Element> els = doc.getElementsByAttributeValue("style", "bold");
        // removed other assertion
        // removed other assertion

        List<Element> none = doc.getElementsByAttributeValue("style", "none");
        assertEquals(0, none.size());
    }

@Test
    public void testClassDomMethods_1_oe() {
        Document doc = Jsoup.parse("<div><span class=' mellow yellow '>Hello <b>Yellow</b></span></div>");
        List<Element> els = doc.getElementsByAttribute("class");
        Element span = els.get(0);
        assertEquals("mellow yellow", span.className());
    }

@Test
    public void testClassDomMethods_2_oe() {
        Document doc = Jsoup.parse("<div><span class=' mellow yellow '>Hello <b>Yellow</b></span></div>");
        List<Element> els = doc.getElementsByAttribute("class");
        Element span = els.get(0);
        // removed other assertion
        assertTrue(span.hasClass("mellow"));
    }

@Test
    public void testClassDomMethods_3_oe() {
        Document doc = Jsoup.parse("<div><span class=' mellow yellow '>Hello <b>Yellow</b></span></div>");
        List<Element> els = doc.getElementsByAttribute("class");
        Element span = els.get(0);
        // removed other assertion
        // removed other assertion
        assertTrue(span.hasClass("yellow"));
    }

@Test
    public void testClassDomMethods_4_oe() {
        Document doc = Jsoup.parse("<div><span class=' mellow yellow '>Hello <b>Yellow</b></span></div>");
        List<Element> els = doc.getElementsByAttribute("class");
        Element span = els.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Set<String> classes = span.classNames();
        assertEquals(2, classes.size());
    }

@Test
    public void testClassDomMethods_5_oe() {
        Document doc = Jsoup.parse("<div><span class=' mellow yellow '>Hello <b>Yellow</b></span></div>");
        List<Element> els = doc.getElementsByAttribute("class");
        Element span = els.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Set<String> classes = span.classNames();
        // removed other assertion
        assertTrue(classes.contains("mellow"));
    }

@Test
    public void testClassDomMethods_6_oe() {
        Document doc = Jsoup.parse("<div><span class=' mellow yellow '>Hello <b>Yellow</b></span></div>");
        List<Element> els = doc.getElementsByAttribute("class");
        Element span = els.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Set<String> classes = span.classNames();
        // removed other assertion
        // removed other assertion
        assertTrue(classes.contains("yellow"));
    }

@Test
    public void testClassDomMethods_7_oe() {
        Document doc = Jsoup.parse("<div><span class=' mellow yellow '>Hello <b>Yellow</b></span></div>");
        List<Element> els = doc.getElementsByAttribute("class");
        Element span = els.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Set<String> classes = span.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("", doc.className());
    }

@Test
    public void testClassDomMethods_8_oe() {
        Document doc = Jsoup.parse("<div><span class=' mellow yellow '>Hello <b>Yellow</b></span></div>");
        List<Element> els = doc.getElementsByAttribute("class");
        Element span = els.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Set<String> classes = span.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        classes = doc.classNames();
        assertEquals(0, classes.size());
    }

@Test
    public void testClassDomMethods_9_oe() {
        Document doc = Jsoup.parse("<div><span class=' mellow yellow '>Hello <b>Yellow</b></span></div>");
        List<Element> els = doc.getElementsByAttribute("class");
        Element span = els.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Set<String> classes = span.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        classes = doc.classNames();
        // removed other assertion
        assertFalse(doc.hasClass("mellow"));
    }

@Test
    public void testHasClassDomMethods_1_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        assertTrue(hasClass);
    }

@Test
    public void testHasClassDomMethods_2_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        assertTrue(hasClass);
    }

@Test
    public void testHasClassDomMethods_3_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "toto ");
        hasClass = el.hasClass("toto");
        assertTrue(hasClass);
    }

@Test
    public void testHasClassDomMethods_4_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "\ttoto ");
        hasClass = el.hasClass("toto");
        assertTrue(hasClass);
    }

@Test
    public void testHasClassDomMethods_5_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "\ttoto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "  toto ");
        hasClass = el.hasClass("toto");
        assertTrue(hasClass);
    }

@Test
    public void testHasClassDomMethods_6_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "\ttoto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "  toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "ab");
        hasClass = el.hasClass("toto");
        assertFalse(hasClass);
    }

@Test
    public void testHasClassDomMethods_7_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "\ttoto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "  toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "ab");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "     ");
        hasClass = el.hasClass("toto");
        assertFalse(hasClass);
    }

@Test
    public void testHasClassDomMethods_8_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "\ttoto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "  toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "ab");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "     ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "tototo");
        hasClass = el.hasClass("toto");
        assertFalse(hasClass);
    }

@Test
    public void testHasClassDomMethods_9_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "\ttoto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "  toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "ab");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "     ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "tototo");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "raulpismuth  ");
        hasClass = el.hasClass("raulpismuth");
        assertTrue(hasClass);
    }

@Test
    public void testHasClassDomMethods_10_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "\ttoto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "  toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "ab");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "     ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "tototo");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "raulpismuth  ");
        hasClass = el.hasClass("raulpismuth");
        // removed other assertion

        attribs.put("class", " abcd  raulpismuth efgh ");
        hasClass = el.hasClass("raulpismuth");
        assertTrue(hasClass);
    }

@Test
    public void testHasClassDomMethods_11_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "\ttoto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "  toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "ab");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "     ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "tototo");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "raulpismuth  ");
        hasClass = el.hasClass("raulpismuth");
        // removed other assertion

        attribs.put("class", " abcd  raulpismuth efgh ");
        hasClass = el.hasClass("raulpismuth");
        // removed other assertion

        attribs.put("class", " abcd efgh raulpismuth");
        hasClass = el.hasClass("raulpismuth");
        assertTrue(hasClass);
    }

@Test
    public void testHasClassDomMethods_12_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "toto");
        boolean hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", " toto");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "\ttoto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "  toto ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "ab");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "     ");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "tototo");
        hasClass = el.hasClass("toto");
        // removed other assertion

        attribs.put("class", "raulpismuth  ");
        hasClass = el.hasClass("raulpismuth");
        // removed other assertion

        attribs.put("class", " abcd  raulpismuth efgh ");
        hasClass = el.hasClass("raulpismuth");
        // removed other assertion

        attribs.put("class", " abcd efgh raulpismuth");
        hasClass = el.hasClass("raulpismuth");
        // removed other assertion

        attribs.put("class", " abcd efgh raulpismuth ");
        hasClass = el.hasClass("raulpismuth");
        assertTrue(hasClass);
    }

@Test
    public void testClassUpdates_1_oe() {
        Document doc = Jsoup.parse("<div class='mellow yellow'></div>");
        Element div = doc.select("div").first();

        div.addClass("green");
        assertEquals("mellow yellow green", div.className());
    }

@Test
    public void testClassUpdates_2_oe() {
        Document doc = Jsoup.parse("<div class='mellow yellow'></div>");
        Element div = doc.select("div").first();

        div.addClass("green");
        // removed other assertion
        div.removeClass("red"); // noop
        div.removeClass("yellow");
        assertEquals("mellow green", div.className());
    }

@Test
    public void testClassUpdates_3_oe() {
        Document doc = Jsoup.parse("<div class='mellow yellow'></div>");
        Element div = doc.select("div").first();

        div.addClass("green");
        // removed other assertion
        div.removeClass("red"); // noop
        div.removeClass("yellow");
        // removed other assertion
        div.toggleClass("green").toggleClass("red");
        assertEquals("mellow red", div.className());
    }

@Test
    public void testOuterHtml_1_oe() {
        Document doc = Jsoup.parse("<div title='Tags &amp;c.'><img src=foo.png><p><!-- comment -->Hello<p>there");
        assertEquals("<html><head></head><body><div title=\"Tags &amp;c.\"><img src=\"foo.png\"><p><!-- comment -->Hello</p><p>there</p></div></body></html>",TextUtil.stripNewlines(doc.outerHtml()));
    }

@Test
    public void testInnerHtml_1_oe() {
        Document doc = Jsoup.parse("<div>\n <p>Hello</p> </div>");
        assertEquals("<p>Hello</p>", doc.getElementsByTag("div").get(0).html());
    }

@Test
    public void testFormatHtml_1_oe() {
        Document doc = Jsoup.parse("<title>Format test</title><div><p>Hello <span>jsoup <span>users</span></span></p><p>Good.</p></div>");
        assertEquals("<html>\n <head>\n  <title>Format test</title>\n </head>\n <body>\n  <div>\n   <p>Hello <span>jsoup <span>users</span></span></p>\n   <p>Good.</p>\n  </div>\n </body>\n</html>", doc.html());
    }

@Test
    public void testFormatOutline_1_oe() {
        Document doc = Jsoup.parse("<title>Format test</title><div><p>Hello <span>jsoup <span>users</span></span></p><p>Good.</p></div>");
        doc.outputSettings().outline(true);
        assertEquals("<html>\n <head>\n  <title>Format test</title>\n </head>\n <body>\n  <div>\n   <p>\n    Hello \n    <span>\n     jsoup \n     <span>users</span>\n    </span>\n   </p>\n   <p>Good.</p>\n  </div>\n </body>\n</html>", doc.html());
    }

@Test
    public void testSetIndent_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello\nthere</p></div>");
        doc.outputSettings().indentAmount(0);
        assertEquals("<html>\n<head></head>\n<body>\n<div>\n<p>Hello there</p>\n</div>\n</body>\n</html>", doc.html());
    }

@Test void testIndentLevel_1_oe() {
        // deep to test default and extended max
        StringBuilder divs = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            divs.append("<div>");
        }
        divs.append("Foo");
        Document doc = Jsoup.parse(divs.toString());
        Document.OutputSettings settings = doc.outputSettings();

        int defaultMax = 30;
        assertEquals(defaultMax, settings.maxPaddingWidth());
        }

@Test void testIndentLevel_2_oe() {
        // deep to test default and extended max
        StringBuilder divs = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            divs.append("<div>");
        }
        divs.append("Foo");
        Document doc = Jsoup.parse(divs.toString());
        Document.OutputSettings settings = doc.outputSettings();

        int defaultMax = 30;
        // removed other assertion
        String html = doc.html();
        assertTrue(html.contains(" <div>\n" + " Foo\n" + " </div>"));
        }

@Test void testIndentLevel_3_oe() {
        // deep to test default and extended max
        StringBuilder divs = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            divs.append("<div>");
        }
        divs.append("Foo");
        Document doc = Jsoup.parse(divs.toString());
        Document.OutputSettings settings = doc.outputSettings();

        int defaultMax = 30;
        // removed other assertion
        String html = doc.html();
        // removed other assertion

        settings.maxPaddingWidth(32);
        assertEquals(32, settings.maxPaddingWidth());
        }

@Test void testIndentLevel_4_oe() {
        // deep to test default and extended max
        StringBuilder divs = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            divs.append("<div>");
        }
        divs.append("Foo");
        Document doc = Jsoup.parse(divs.toString());
        Document.OutputSettings settings = doc.outputSettings();

        int defaultMax = 30;
        // removed other assertion
        String html = doc.html();
        // removed other assertion

        settings.maxPaddingWidth(32);
        // removed other assertion
        html = doc.html();
        assertTrue(html.contains(" <div>\n" + " Foo\n" + " </div>"));
        }

@Test void testIndentLevel_5_oe() {
        // deep to test default and extended max
        StringBuilder divs = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            divs.append("<div>");
        }
        divs.append("Foo");
        Document doc = Jsoup.parse(divs.toString());
        Document.OutputSettings settings = doc.outputSettings();

        int defaultMax = 30;
        // removed other assertion
        String html = doc.html();
        // removed other assertion

        settings.maxPaddingWidth(32);
        // removed other assertion
        html = doc.html();
        // removed other assertion

        settings.maxPaddingWidth(-1);
        assertEquals(-1, settings.maxPaddingWidth());
        }

@Test void testIndentLevel_6_oe() {
        // deep to test default and extended max
        StringBuilder divs = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            divs.append("<div>");
        }
        divs.append("Foo");
        Document doc = Jsoup.parse(divs.toString());
        Document.OutputSettings settings = doc.outputSettings();

        int defaultMax = 30;
        // removed other assertion
        String html = doc.html();
        // removed other assertion

        settings.maxPaddingWidth(32);
        // removed other assertion
        html = doc.html();
        // removed other assertion

        settings.maxPaddingWidth(-1);
        // removed other assertion
        html = doc.html();
        assertTrue(html.contains(" <div>\n" + " Foo\n" + " </div>"));
        }

@Test
    public void testNotPretty_1_oe() {
        Document doc = Jsoup.parse("<div>   \n<p>Hello\n there\n</p></div>");
        doc.outputSettings().prettyPrint(false);
        assertEquals("<html><head></head><body><div>   \n<p>Hello\n there\n</p></div></body></html>", doc.html());
    }

@Test
    public void testNotPretty_2_oe() {
        Document doc = Jsoup.parse("<div>   \n<p>Hello\n there\n</p></div>");
        doc.outputSettings().prettyPrint(false);
        // removed other assertion

        Element div = doc.select("div").first();
        assertEquals("   \n<p>Hello\n there\n</p>", div.html());
    }

@Test
    public void testNotPrettyWithEnDashBody_1_oe() {
        String html = "<div><span>1:15</span>&ndash;<span>2:15</span>&nbsp;p.m.</div>";
        Document document = Jsoup.parse(html);
        document.outputSettings().prettyPrint(false);

        assertEquals("<div><span>1:15</span>–<span>2:15</span>&nbsp;p.m.</div>", document.body().html());
    }

@Test
    public void testPrettyWithEnDashBody_1_oe() {
        String html = "<div><span>1:15</span>&ndash;<span>2:15</span>&nbsp;p.m.</div>";
        Document document = Jsoup.parse(html);

        assertEquals("<div>\n <span>1:15</span>–<span>2:15</span>&nbsp;p.m.\n</div>", document.body().html());
    }

@Test
    public void testPrettyAndOutlineWithEnDashBody_1_oe() {
        String html = "<div><span>1:15</span>&ndash;<span>2:15</span>&nbsp;p.m.</div>";
        Document document = Jsoup.parse(html);
        document.outputSettings().outline(true);

        assertEquals("<div>\n <span>1:15</span>\n –\n <span>2:15</span>\n &nbsp;p.m.\n</div>", document.body().html());
    }

@Test
    public void testBasicFormats_1_oe() {
        String html = "<span>0</span>.<div><span>1</span>-<span>2</span><p><span>3</span>-<span>4</span><div>5</div>";
        Document doc = Jsoup.parse(html);
        assertEquals("<span>0</span>.\n" + "<div>\n" + " <span>1</span>-<span>2</span>\n" + " <p><span>3</span>-<span>4</span></p>\n" + " <div>\n" + " 5\n" + " </div>\n" + "</div>",doc.body().html());
    }

@Test
    public void testEmptyElementFormatHtml_1_oe() {
        // don't put newlines into empty blocks
        Document doc = Jsoup.parse("<section><div></div></section>");
        assertEquals("<section>\n <div></div>\n</section>", doc.select("section").first().outerHtml());
    }

@Test
    public void testNoIndentOnScriptAndStyle_1_oe() {
        // don't newline+indent closing </script> and </style> tags
        Document doc = Jsoup.parse("<script>one\ntwo</script>\n<style>three\nfour</style>");
        assertEquals("<script>one\ntwo</script>\n<style>three\nfour</style>", doc.head().html());
    }

@Test
    public void testContainerOutput_1_oe() {
        Document doc = Jsoup.parse("<title>Hello there</title> <div><p>Hello</p><p>there</p></div> <div>Another</div>");
        assertEquals("<title>Hello there</title>", doc.select("title").first().outerHtml());
    }

@Test
    public void testContainerOutput_2_oe() {
        Document doc = Jsoup.parse("<title>Hello there</title> <div><p>Hello</p><p>there</p></div> <div>Another</div>");
        // removed other assertion
        assertEquals("<div>\n <p>Hello</p>\n <p>there</p>\n</div>", doc.select("div").first().outerHtml());
    }

@Test
    public void testContainerOutput_3_oe() {
        Document doc = Jsoup.parse("<title>Hello there</title> <div><p>Hello</p><p>there</p></div> <div>Another</div>");
        // removed other assertion
        // removed other assertion
        assertEquals("<div>\n <p>Hello</p>\n <p>there</p>\n</div>\n<div>\n Another\n</div>", doc.select("body").first().html());
    }

@Test
    public void testSetText_1_oe() {
        String h = "<div id=1>Hello <p>there <b>now</b></p></div>";
        Document doc = Jsoup.parse(h);
        assertEquals("Hello there now",doc.text());// need to sort out node whitespace assertEquals("there now",doc.select("p").get(0).text());
    }

@Test
    public void testSetText_2_oe() {
        String h = "<div id=1>Hello <p>there <b>now</b></p></div>";
        Document doc = Jsoup.parse(h);
        // removed other assertion

        Element div = doc.getElementById("1").text("Gone");
        assertEquals("Gone", div.text());
    }

@Test
    public void testSetText_3_oe() {
        String h = "<div id=1>Hello <p>there <b>now</b></p></div>";
        Document doc = Jsoup.parse(h);
        // removed other assertion

        Element div = doc.getElementById("1").text("Gone");
        // removed other assertion
        assertEquals(0, doc.select("p").size());
    }

@Test
    public void testAddNewElement_1_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.appendElement("p").text("there");
        div.appendElement("P").attr("CLASS", "second").text("now");
        // manually specifying tag and attributes should maintain case based on parser settings
        assertEquals("<html><head></head><body><div id=\"1\"><p>Hello</p><p>there</p><p class=\"second\">now</p></div></body></html>",TextUtil.stripNewlines(doc.html()));
    }

@Test
    public void testAddNewElement_2_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.appendElement("p").text("there");
        div.appendElement("P").attr("CLASS", "second").text("now");
        // manually specifying tag and attributes should maintain case based on parser settings
        // removed other assertion

        // check sibling index (with short circuit on reindexChildren):
        Elements ps = doc.select("p");
        for (int i = 0; i < ps.size(); i++) {
            assertEquals(i, ps.get(i).siblingIndex);
    }
    }

@Test
    public void testAddBooleanAttribute_1_oe() {
        Element div = new Element(Tag.valueOf("div"), "");

        div.attr("true", true);

        div.attr("false", "value");
        div.attr("false", false);

        assertTrue(div.hasAttr("true"));
    }

@Test
    public void testAddBooleanAttribute_2_oe() {
        Element div = new Element(Tag.valueOf("div"), "");

        div.attr("true", true);

        div.attr("false", "value");
        div.attr("false", false);

        // removed other assertion
        assertEquals("", div.attr("true"));
    }

@Test
    public void testAddBooleanAttribute_3_oe() {
        Element div = new Element(Tag.valueOf("div"), "");

        div.attr("true", true);

        div.attr("false", "value");
        div.attr("false", false);

        // removed other assertion
        // removed other assertion

        List<Attribute> attributes = div.attributes().asList();
        assertEquals(1, attributes.size(), "There should be one attribute");
    }

@Test
    public void testAddBooleanAttribute_4_oe() {
        Element div = new Element(Tag.valueOf("div"), "");

        div.attr("true", true);

        div.attr("false", "value");
        div.attr("false", false);

        // removed other assertion
        // removed other assertion

        List<Attribute> attributes = div.attributes().asList();
        // removed other assertion
        assertFalse(div.hasAttr("false"));
    }

@Test
    public void testAddBooleanAttribute_5_oe() {
        Element div = new Element(Tag.valueOf("div"), "");

        div.attr("true", true);

        div.attr("false", "value");
        div.attr("false", false);

        // removed other assertion
        // removed other assertion

        List<Attribute> attributes = div.attributes().asList();
        // removed other assertion
        // removed other assertion

        assertEquals("<div true></div>", div.outerHtml());
    }

@Test
    public void testAppendRowToTable_1_oe() {
        Document doc = Jsoup.parse("<table><tr><td>1</td></tr></table>");
        Element table = doc.select("tbody").first();
        table.append("<tr><td>2</td></tr>");

        assertEquals("<table><tbody><tr><td>1</td></tr><tr><td>2</td></tr></tbody></table>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testPrependRowToTable_1_oe() {
        Document doc = Jsoup.parse("<table><tr><td>1</td></tr></table>");
        Element table = doc.select("tbody").first();
        table.prepend("<tr><td>2</td></tr>");

        assertEquals("<table><tbody><tr><td>2</td></tr><tr><td>1</td></tr></tbody></table>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testPrependRowToTable_2_oe() {
        Document doc = Jsoup.parse("<table><tr><td>1</td></tr></table>");
        Element table = doc.select("tbody").first();
        table.prepend("<tr><td>2</td></tr>");

        // removed other assertion

        // check sibling index (reindexChildren):
        Elements ps = doc.select("tr");
        for (int i = 0; i < ps.size(); i++) {
            assertEquals(i, ps.get(i).siblingIndex);
    }
    }

@Test
    public void testPrependElement_1_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.prependElement("p").text("Before");
        assertEquals("Before", div.child(0).text());
    }

@Test
    public void testPrependElement_2_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.prependElement("p").text("Before");
        // removed other assertion
        assertEquals("Hello", div.child(1).text());
    }

@Test
    public void testAddNewText_1_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.appendText(" there & now >");
        assertEquals ("Hello there & now >", div.text());
    }

@Test
    public void testAddNewText_2_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.appendText(" there & now >");
        // removed other assertion
        assertEquals("<p>Hello</p> there &amp; now &gt;", TextUtil.stripNewlines(div.html()));
    }

@Test
    public void testPrependText_1_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.prependText("there & now > ");
        assertEquals("there & now > Hello", div.text());
    }

@Test
    public void testPrependText_2_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.prependText("there & now > ");
        // removed other assertion
        assertEquals("there &amp; now &gt; <p>Hello</p>", TextUtil.stripNewlines(div.html()));
    }

@Test
    public void testThrowsOnAddNullText_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> { Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>"); Element div = doc.getElementById("1"); div.appendText(null); });
    }

@Test
    public void testThrowsOnPrependNullText_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> { Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>"); Element div = doc.getElementById("1"); div.prependText(null); });
    }

@Test
    public void testAddNewHtml_1_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.append("<p>there</p><p>now</p>");
        assertEquals("<p>Hello</p><p>there</p><p>now</p>", TextUtil.stripNewlines(div.html()));
    }

@Test
    public void testAddNewHtml_2_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.append("<p>there</p><p>now</p>");
        // removed other assertion

        // check sibling index (no reindexChildren):
        Elements ps = doc.select("p");
        for (int i = 0; i < ps.size(); i++) {
            assertEquals(i, ps.get(i).siblingIndex);
    }
    }

@Test
    public void testPrependNewHtml_1_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.prepend("<p>there</p><p>now</p>");
        assertEquals("<p>there</p><p>now</p><p>Hello</p>", TextUtil.stripNewlines(div.html()));
    }

@Test
    public void testPrependNewHtml_2_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.prepend("<p>there</p><p>now</p>");
        // removed other assertion

        // check sibling index (reindexChildren):
        Elements ps = doc.select("p");
        for (int i = 0; i < ps.size(); i++) {
            assertEquals(i, ps.get(i).siblingIndex);
    }
    }

@Test
    public void testSetHtml_1_oe() {
        Document doc = Jsoup.parse("<div id=1><p>Hello</p></div>");
        Element div = doc.getElementById("1");
        div.html("<p>there</p><p>now</p>");
        assertEquals("<p>there</p><p>now</p>", TextUtil.stripNewlines(div.html()));
    }

@Test
    public void testSetHtmlTitle_1_oe() {
        Document doc = Jsoup.parse("<html><head id=2><title id=1></title></head></html>");

        Element title = doc.getElementById("1");
        title.html("good");
        assertEquals("good", title.html());
    }

@Test
    public void testSetHtmlTitle_2_oe() {
        Document doc = Jsoup.parse("<html><head id=2><title id=1></title></head></html>");

        Element title = doc.getElementById("1");
        title.html("good");
        // removed other assertion
        title.html("<i>bad</i>");
        assertEquals("&lt;i&gt;bad&lt;/i&gt;", title.html());
    }

@Test
    public void testSetHtmlTitle_3_oe() {
        Document doc = Jsoup.parse("<html><head id=2><title id=1></title></head></html>");

        Element title = doc.getElementById("1");
        title.html("good");
        // removed other assertion
        title.html("<i>bad</i>");
        // removed other assertion

        Element head = doc.getElementById("2");
        head.html("<title><i>bad</i></title>");
        assertEquals("<title>&lt;i&gt;bad&lt;/i&gt;</title>", head.html());
    }

@Test
    public void testWrap_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>There</p></div>");
        Element p = doc.select("p").first();
        p.wrap("<div class='head'></div>");
        assertEquals("<div><div class=\"head\"><p>Hello</p></div><p>There</p></div>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testWrap_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>There</p></div>");
        Element p = doc.select("p").first();
        p.wrap("<div class='head'></div>");
        // removed other assertion

        Element ret = p.wrap("<div><div class=foo></div><p>What?</p></div>");
        assertEquals("<div><div class=\"head\"><div><div class=\"foo\"><p>Hello</p></div><p>What?</p></div></div><p>There</p></div>",TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testWrap_3_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>There</p></div>");
        Element p = doc.select("p").first();
        p.wrap("<div class='head'></div>");
        // removed other assertion

        Element ret = p.wrap("<div><div class=foo></div><p>What?</p></div>");
        // removed other assertion

        assertEquals(ret, p);
    }

@Test
    public void testWrapNoop_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div>");
        Node p = doc.select("p").first();
        Node wrapped = p.wrap("Some junk");
        assertSame(p, wrapped);
    }

@Test
    public void testWrapNoop_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div>");
        Node p = doc.select("p").first();
        Node wrapped = p.wrap("Some junk");
        // removed other assertion
        assertEquals("<div><p>Hello</p></div>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testWrapOnOrphan_1_oe() {
        Element orphan = new Element("span").text("Hello!");
        assertFalse(orphan.hasParent());
    }

@Test
    public void testWrapOnOrphan_2_oe() {
        Element orphan = new Element("span").text("Hello!");
        // removed other assertion
        Element wrapped = orphan.wrap("<div></div> There!");
        assertSame(orphan, wrapped);
    }

@Test
    public void testWrapOnOrphan_3_oe() {
        Element orphan = new Element("span").text("Hello!");
        // removed other assertion
        Element wrapped = orphan.wrap("<div></div> There!");
        // removed other assertion
        assertTrue(orphan.hasParent());// should now be in the DIV assertNotNull(orphan.parent());
    }

@Test
    public void testWrapOnOrphan_4_oe() {
        Element orphan = new Element("span").text("Hello!");
        // removed other assertion
        Element wrapped = orphan.wrap("<div></div> There!");
        // removed other assertion
        // removed other assertion
        assertEquals("div", orphan.parent().tagName());
    }

@Test
    public void testWrapOnOrphan_5_oe() {
        Element orphan = new Element("span").text("Hello!");
        // removed other assertion
        Element wrapped = orphan.wrap("<div></div> There!");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("<div>\n <span>Hello!</span>\n</div>", orphan.parent().outerHtml());
    }

@Test
    public void testWrapArtificialStructure_1_oe() {
        // div normally couldn't get into a p, but explicitly want to wrap
        Document doc = Jsoup.parse("<p>Hello <i>there</i> now.");
        Element i = doc.selectFirst("i");
        i.wrap("<div id=id1></div> quite");
        assertEquals("div", i.parent().tagName());
    }

@Test
    public void testWrapArtificialStructure_2_oe() {
        // div normally couldn't get into a p, but explicitly want to wrap
        Document doc = Jsoup.parse("<p>Hello <i>there</i> now.");
        Element i = doc.selectFirst("i");
        i.wrap("<div id=id1></div> quite");
        // removed other assertion
        assertEquals("<p>Hello <div id=\"id1\"><i>there</i></div> quite now.</p>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void before_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>There</p></div>");
        Element p1 = doc.select("p").first();
        p1.before("<div>one</div><div>two</div>");
        assertEquals("<div><div>one</div><div>two</div><p>Hello</p><p>There</p></div>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void before_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>There</p></div>");
        Element p1 = doc.select("p").first();
        p1.before("<div>one</div><div>two</div>");
        // removed other assertion

        doc.select("p").last().before("<p>Three</p><!-- four -->");
        assertEquals("<div><div>one</div><div>two</div><p>Hello</p><p>Three</p><!-- four --><p>There</p></div>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void after_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>There</p></div>");
        Element p1 = doc.select("p").first();
        p1.after("<div>one</div><div>two</div>");
        assertEquals("<div><p>Hello</p><div>one</div><div>two</div><p>There</p></div>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void after_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>There</p></div>");
        Element p1 = doc.select("p").first();
        p1.after("<div>one</div><div>two</div>");
        // removed other assertion

        doc.select("p").last().after("<p>Three</p><!-- four -->");
        assertEquals("<div><p>Hello</p><div>one</div><div>two</div><p>There</p><p>Three</p><!-- four --></div>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testWrapWithRemainder_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div>");
        Element p = doc.select("p").first();
        p.wrap("<div class='head'></div><p>There!</p>");
        assertEquals("<div><div class=\"head\"><p>Hello</p></div><p>There!</p></div>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testWrapWithSimpleRemainder_1_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        Element p = doc.selectFirst("p");
        Element body = p.parent();
        assertNotNull(body);
    }

@Test
    public void testWrapWithSimpleRemainder_2_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        Element p = doc.selectFirst("p");
        Element body = p.parent();
        // removed other assertion
        assertEquals("body", body.tagName());
    }

@Test
    public void testWrapWithSimpleRemainder_3_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        Element p = doc.selectFirst("p");
        Element body = p.parent();
        // removed other assertion
        // removed other assertion

        p.wrap("<div></div> There");
        Element div = p.parent();
        assertNotNull(div);
    }

@Test
    public void testWrapWithSimpleRemainder_4_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        Element p = doc.selectFirst("p");
        Element body = p.parent();
        // removed other assertion
        // removed other assertion

        p.wrap("<div></div> There");
        Element div = p.parent();
        // removed other assertion
        assertEquals("div", div.tagName());
    }

@Test
    public void testWrapWithSimpleRemainder_5_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        Element p = doc.selectFirst("p");
        Element body = p.parent();
        // removed other assertion
        // removed other assertion

        p.wrap("<div></div> There");
        Element div = p.parent();
        // removed other assertion
        // removed other assertion
        assertSame(div, p.parent());
    }

@Test
    public void testWrapWithSimpleRemainder_6_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        Element p = doc.selectFirst("p");
        Element body = p.parent();
        // removed other assertion
        // removed other assertion

        p.wrap("<div></div> There");
        Element div = p.parent();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(body, div.parent());
    }

@Test
    public void testWrapWithSimpleRemainder_7_oe() {
        Document doc = Jsoup.parse("<p>Hello");
        Element p = doc.selectFirst("p");
        Element body = p.parent();
        // removed other assertion
        // removed other assertion

        p.wrap("<div></div> There");
        Element div = p.parent();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("<div><p>Hello</p></div> There", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testHasText_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p></p></div>");
        Element div = doc.select("div").first();
        Elements ps = doc.select("p");

        assertTrue(div.hasText());
    }

@Test
    public void testHasText_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p></p></div>");
        Element div = doc.select("div").first();
        Elements ps = doc.select("p");

        // removed other assertion
        assertTrue(ps.first().hasText());
    }

@Test
    public void testHasText_3_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p></p></div>");
        Element div = doc.select("div").first();
        Elements ps = doc.select("p");

        // removed other assertion
        // removed other assertion
        assertFalse(ps.last().hasText());
    }

@Test
    public void dataset_1_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        assertEquals(2, dataset.size());
    }

@Test
    public void dataset_2_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        assertEquals("jsoup", dataset.get("name"));
    }

@Test
    public void dataset_3_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        assertEquals("jar", dataset.get("package"));
    }

@Test
    public void dataset_4_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        // removed other assertion

        dataset.put("name", "jsoup updated");
        dataset.put("language", "java");
        dataset.remove("package");

        assertEquals(2, dataset.size());
    }

@Test
    public void dataset_5_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        // removed other assertion

        dataset.put("name", "jsoup updated");
        dataset.put("language", "java");
        dataset.remove("package");

        // removed other assertion
        assertEquals(4, attributes.size());
    }

@Test
    public void dataset_6_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        // removed other assertion

        dataset.put("name", "jsoup updated");
        dataset.put("language", "java");
        dataset.remove("package");

        // removed other assertion
        // removed other assertion
        assertEquals("jsoup updated", attributes.get("data-name"));
    }

@Test
    public void dataset_7_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        // removed other assertion

        dataset.put("name", "jsoup updated");
        dataset.put("language", "java");
        dataset.remove("package");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("jsoup updated", dataset.get("name"));
    }

@Test
    public void dataset_8_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        // removed other assertion

        dataset.put("name", "jsoup updated");
        dataset.put("language", "java");
        dataset.remove("package");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("java", attributes.get("data-language"));
    }

@Test
    public void dataset_9_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        // removed other assertion

        dataset.put("name", "jsoup updated");
        dataset.put("language", "java");
        dataset.remove("package");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("java", dataset.get("language"));
    }

@Test
    public void dataset_10_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        // removed other assertion

        dataset.put("name", "jsoup updated");
        dataset.put("language", "java");
        dataset.remove("package");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        attributes.put("data-food", "bacon");
        assertEquals(3, dataset.size());
    }

@Test
    public void dataset_11_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        // removed other assertion

        dataset.put("name", "jsoup updated");
        dataset.put("language", "java");
        dataset.remove("package");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        attributes.put("data-food", "bacon");
        // removed other assertion
        assertEquals("bacon", dataset.get("food"));
    }

@Test
    public void dataset_12_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        // removed other assertion

        dataset.put("name", "jsoup updated");
        dataset.put("language", "java");
        dataset.remove("package");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        attributes.put("data-food", "bacon");
        // removed other assertion
        // removed other assertion

        attributes.put("data-", "empty");
        assertNull(dataset.get(""));// data- is not a data attribute;
    }

@Test
    public void dataset_13_oe() {
        Document doc = Jsoup.parse("<div id=1 data-name=jsoup class=new data-package=jar>Hello</div><p id=2>Hello</p>");
        Element div = doc.select("div").first();
        Map<String, String> dataset = div.dataset();
        Attributes attributes = div.attributes();

        // size, get, set, add, remove
        // removed other assertion
        // removed other assertion
        // removed other assertion

        dataset.put("name", "jsoup updated");
        dataset.put("language", "java");
        dataset.remove("package");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        attributes.put("data-food", "bacon");
        // removed other assertion
        // removed other assertion

        attributes.put("data-", "empty");
        // removed other assertion
        Element p = doc.select("p").first();
        assertEquals(0, p.dataset().size());
    }

@Test
    public void parentlessToString_1_oe() {
        Document doc = Jsoup.parse("<img src='foo'>");
        Element img = doc.select("img").first();
        assertEquals("<img src=\"foo\">", img.toString());
    }

@Test
    public void parentlessToString_2_oe() {
        Document doc = Jsoup.parse("<img src='foo'>");
        Element img = doc.select("img").first();
        // removed other assertion

        img.remove(); // lost its parent
        assertEquals("<img src=\"foo\">", img.toString());
    }

@Test
    public void orphanDivToString_1_oe() {
        Element orphan = new Element("div").id("foo").text("Hello");
        assertEquals("<div id=\"foo\">\n Hello\n</div>", orphan.toString());
    }

@Test
    public void testClone_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p><span>Two</div>");

        Element p = doc.select("p").get(1);
        Element clone = p.clone();

        assertNotNull(clone.parentNode);// should be a cloned document just containing this clone assertEquals(1,clone.parentNode.childNodeSize());
    }

@Test
    public void testClone_2_oe() {
        Document doc = Jsoup.parse("<div><p>One<p><span>Two</div>");

        Element p = doc.select("p").get(1);
        Element clone = p.clone();

        // removed other assertion
        assertSame(clone.ownerDocument(), clone.parentNode);
    }

@Test
    public void testClone_3_oe() {
        Document doc = Jsoup.parse("<div><p>One<p><span>Two</div>");

        Element p = doc.select("p").get(1);
        Element clone = p.clone();

        // removed other assertion
        // removed other assertion

        assertEquals(0, clone.siblingIndex);
    }

@Test
    public void testClone_4_oe() {
        Document doc = Jsoup.parse("<div><p>One<p><span>Two</div>");

        Element p = doc.select("p").get(1);
        Element clone = p.clone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(1, p.siblingIndex);
    }

@Test
    public void testClone_5_oe() {
        Document doc = Jsoup.parse("<div><p>One<p><span>Two</div>");

        Element p = doc.select("p").get(1);
        Element clone = p.clone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNotNull(p.parent());
    }

@Test
    public void testClone_6_oe() {
        Document doc = Jsoup.parse("<div><p>One<p><span>Two</div>");

        Element p = doc.select("p").get(1);
        Element clone = p.clone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        clone.append("<span>Three");
        assertEquals("<p><span>Two</span><span>Three</span></p>", TextUtil.stripNewlines(clone.outerHtml()));
    }

@Test
    public void testClone_7_oe() {
        Document doc = Jsoup.parse("<div><p>One<p><span>Two</div>");

        Element p = doc.select("p").get(1);
        Element clone = p.clone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        clone.append("<span>Three");
        // removed other assertion
        assertEquals("<div><p>One</p><p><span>Two</span></p></div>",TextUtil.stripNewlines(doc.body().html()));// not modified doc.body().appendChild(clone);// adopt assertNotNull(clone.parent());
    }

@Test
    public void testClone_8_oe() {
        Document doc = Jsoup.parse("<div><p>One<p><span>Two</div>");

        Element p = doc.select("p").get(1);
        Element clone = p.clone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        clone.append("<span>Three");
        // removed other assertion
        // removed other assertion
        assertEquals("<div><p>One</p><p><span>Two</span></p></div><p><span>Two</span><span>Three</span></p>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testClonesClassnames_1_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        assertEquals(2, classes.size());
    }

@Test
    public void testClonesClassnames_2_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        assertTrue(classes.contains("one"));
    }

@Test
    public void testClonesClassnames_3_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        // removed other assertion
        assertTrue(classes.contains("two"));
    }

@Test
    public void testClonesClassnames_4_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element copy = div.clone();
        Set<String> copyClasses = copy.classNames();
        assertEquals(2, copyClasses.size());
    }

@Test
    public void testClonesClassnames_5_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element copy = div.clone();
        Set<String> copyClasses = copy.classNames();
        // removed other assertion
        assertTrue(copyClasses.contains("one"));
    }

@Test
    public void testClonesClassnames_6_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element copy = div.clone();
        Set<String> copyClasses = copy.classNames();
        // removed other assertion
        // removed other assertion
        assertTrue(copyClasses.contains("two"));
    }

@Test
    public void testClonesClassnames_7_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element copy = div.clone();
        Set<String> copyClasses = copy.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        copyClasses.add("three");
        copyClasses.remove("one");

        assertTrue(classes.contains("one"));
    }

@Test
    public void testClonesClassnames_8_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element copy = div.clone();
        Set<String> copyClasses = copy.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        copyClasses.add("three");
        copyClasses.remove("one");

        // removed other assertion
        assertFalse(classes.contains("three"));
    }

@Test
    public void testClonesClassnames_9_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element copy = div.clone();
        Set<String> copyClasses = copy.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        copyClasses.add("three");
        copyClasses.remove("one");

        // removed other assertion
        // removed other assertion
        assertFalse(copyClasses.contains("one"));
    }

@Test
    public void testClonesClassnames_10_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element copy = div.clone();
        Set<String> copyClasses = copy.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        copyClasses.add("three");
        copyClasses.remove("one");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(copyClasses.contains("three"));
    }

@Test
    public void testClonesClassnames_11_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element copy = div.clone();
        Set<String> copyClasses = copy.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        copyClasses.add("three");
        copyClasses.remove("one");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("", div.html());
    }

@Test
    public void testClonesClassnames_12_oe() {
        Document doc = Jsoup.parse("<div class='one two'></div>");
        Element div = doc.select("div").first();
        Set<String> classes = div.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element copy = div.clone();
        Set<String> copyClasses = copy.classNames();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        copyClasses.add("three");
        copyClasses.remove("one");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", copy.html());
    }

@Test
    public void testShallowClone_1_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        assertEquals(1, d.childNodeSize());
    }

@Test
    public void testShallowClone_2_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        assertEquals(0, d2.childNodeSize());
    }

@Test
    public void testShallowClone_3_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        // removed other assertion

        assertEquals(1, p.childNodeSize());
    }

@Test
    public void testShallowClone_4_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, p2.childNodeSize());
    }

@Test
    public void testShallowClone_5_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("", p2.text());
    }

@Test
    public void testShallowClone_6_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("One", t2.text());
    }

@Test
    public void testShallowClone_7_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("two", p2.className());
    }

@Test
    public void testShallowClone_8_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        p2.removeClass("two");
        assertEquals("two", p.className());
    }

@Test
    public void testShallowClone_9_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        p2.removeClass("two");
        // removed other assertion

        d2.append("<p id=3>Three");
        assertEquals(1, d2.childNodeSize());
    }

@Test
    public void testShallowClone_10_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        p2.removeClass("two");
        // removed other assertion

        d2.append("<p id=3>Three");
        // removed other assertion
        assertEquals("Three", d2.text());
    }

@Test
    public void testShallowClone_11_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        p2.removeClass("two");
        // removed other assertion

        d2.append("<p id=3>Three");
        // removed other assertion
        // removed other assertion
        assertEquals("One", d.text());
    }

@Test
    public void testShallowClone_12_oe() {
        String base = "http://example.com/";
        Document doc = Jsoup.parse("<div id=1 class=one><p id=2 class=two>One", base);
        Element d = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        TextNode t = p.textNodes().get(0);

        Element d2 = d.shallowClone();
        Element p2 = p.shallowClone();
        TextNode t2 = (TextNode) t.shallowClone();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        p2.removeClass("two");
        // removed other assertion

        d2.append("<p id=3>Three");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(base, d2.baseUri());
    }

@Test
    public void testTagNameSet_1_oe() {
        Document doc = Jsoup.parse("<div><i>Hello</i>");
        doc.select("i").first().tagName("em");
        assertEquals(0, doc.select("i").size());
    }

@Test
    public void testTagNameSet_2_oe() {
        Document doc = Jsoup.parse("<div><i>Hello</i>");
        doc.select("i").first().tagName("em");
        // removed other assertion
        assertEquals(1, doc.select("em").size());
    }

@Test
    public void testTagNameSet_3_oe() {
        Document doc = Jsoup.parse("<div><i>Hello</i>");
        doc.select("i").first().tagName("em");
        // removed other assertion
        // removed other assertion
        assertEquals("<em>Hello</em>", doc.select("div").first().html());
    }

@Test
    public void testHtmlContainsOuter_1_oe() {
        Document doc = Jsoup.parse("<title>Check</title> <div>Hello there</div>");
        doc.outputSettings().indentAmount(0);
        assertTrue(doc.html().contains(doc.select("title").outerHtml()));
    }

@Test
    public void testHtmlContainsOuter_2_oe() {
        Document doc = Jsoup.parse("<title>Check</title> <div>Hello there</div>");
        doc.outputSettings().indentAmount(0);
        // removed other assertion
        assertTrue(doc.html().contains(doc.select("div").outerHtml()));
    }

@Test
    public void testGetTextNodes_1_oe() {
        Document doc = Jsoup.parse("<p>One <span>Two</span> Three <br> Four</p>");
        List<TextNode> textNodes = doc.select("p").first().textNodes();

        assertEquals(3, textNodes.size());
    }

@Test
    public void testGetTextNodes_2_oe() {
        Document doc = Jsoup.parse("<p>One <span>Two</span> Three <br> Four</p>");
        List<TextNode> textNodes = doc.select("p").first().textNodes();

        // removed other assertion
        assertEquals("One ", textNodes.get(0).text());
    }

@Test
    public void testGetTextNodes_3_oe() {
        Document doc = Jsoup.parse("<p>One <span>Two</span> Three <br> Four</p>");
        List<TextNode> textNodes = doc.select("p").first().textNodes();

        // removed other assertion
        // removed other assertion
        assertEquals(" Three ", textNodes.get(1).text());
    }

@Test
    public void testGetTextNodes_4_oe() {
        Document doc = Jsoup.parse("<p>One <span>Two</span> Three <br> Four</p>");
        List<TextNode> textNodes = doc.select("p").first().textNodes();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" Four", textNodes.get(2).text());
    }

@Test
    public void testGetTextNodes_5_oe() {
        Document doc = Jsoup.parse("<p>One <span>Two</span> Three <br> Four</p>");
        List<TextNode> textNodes = doc.select("p").first().textNodes();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, doc.select("br").first().textNodes().size());
    }

@Test
    public void testManipulateTextNodes_1_oe() {
        Document doc = Jsoup.parse("<p>One <span>Two</span> Three <br> Four</p>");
        Element p = doc.select("p").first();
        List<TextNode> textNodes = p.textNodes();

        textNodes.get(1).text(" three-more ");
        textNodes.get(2).splitText(3).text("-ur");

        assertEquals("One Two three-more Fo-ur", p.text());
    }

@Test
    public void testManipulateTextNodes_2_oe() {
        Document doc = Jsoup.parse("<p>One <span>Two</span> Three <br> Four</p>");
        Element p = doc.select("p").first();
        List<TextNode> textNodes = p.textNodes();

        textNodes.get(1).text(" three-more ");
        textNodes.get(2).splitText(3).text("-ur");

        // removed other assertion
        assertEquals("One three-more Fo-ur", p.ownText());
    }

@Test
    public void testManipulateTextNodes_3_oe() {
        Document doc = Jsoup.parse("<p>One <span>Two</span> Three <br> Four</p>");
        Element p = doc.select("p").first();
        List<TextNode> textNodes = p.textNodes();

        textNodes.get(1).text(" three-more ");
        textNodes.get(2).splitText(3).text("-ur");

        // removed other assertion
        // removed other assertion
        assertEquals(4, p.textNodes().size()); // grew because of split;
    }

@Test
    public void testGetDataNodes_1_oe() {
        Document doc = Jsoup.parse("<script>One Two</script> <style>Three Four</style> <p>Fix Six</p>");
        Element script = doc.select("script").first();
        Element style = doc.select("style").first();
        Element p = doc.select("p").first();

        List<DataNode> scriptData = script.dataNodes();
        assertEquals(1, scriptData.size());
    }

@Test
    public void testGetDataNodes_2_oe() {
        Document doc = Jsoup.parse("<script>One Two</script> <style>Three Four</style> <p>Fix Six</p>");
        Element script = doc.select("script").first();
        Element style = doc.select("style").first();
        Element p = doc.select("p").first();

        List<DataNode> scriptData = script.dataNodes();
        // removed other assertion
        assertEquals("One Two", scriptData.get(0).getWholeData());
    }

@Test
    public void testGetDataNodes_3_oe() {
        Document doc = Jsoup.parse("<script>One Two</script> <style>Three Four</style> <p>Fix Six</p>");
        Element script = doc.select("script").first();
        Element style = doc.select("style").first();
        Element p = doc.select("p").first();

        List<DataNode> scriptData = script.dataNodes();
        // removed other assertion
        // removed other assertion

        List<DataNode> styleData = style.dataNodes();
        assertEquals(1, styleData.size());
    }

@Test
    public void testGetDataNodes_4_oe() {
        Document doc = Jsoup.parse("<script>One Two</script> <style>Three Four</style> <p>Fix Six</p>");
        Element script = doc.select("script").first();
        Element style = doc.select("style").first();
        Element p = doc.select("p").first();

        List<DataNode> scriptData = script.dataNodes();
        // removed other assertion
        // removed other assertion

        List<DataNode> styleData = style.dataNodes();
        // removed other assertion
        assertEquals("Three Four", styleData.get(0).getWholeData());
    }

@Test
    public void testGetDataNodes_5_oe() {
        Document doc = Jsoup.parse("<script>One Two</script> <style>Three Four</style> <p>Fix Six</p>");
        Element script = doc.select("script").first();
        Element style = doc.select("style").first();
        Element p = doc.select("p").first();

        List<DataNode> scriptData = script.dataNodes();
        // removed other assertion
        // removed other assertion

        List<DataNode> styleData = style.dataNodes();
        // removed other assertion
        // removed other assertion

        List<DataNode> pData = p.dataNodes();
        assertEquals(0, pData.size());
    }

@Test
    public void elementIsNotASiblingOfItself_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div>");
        Element p2 = doc.select("p").get(1);

        assertEquals("Two", p2.text());
    }

@Test
    public void elementIsNotASiblingOfItself_2_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div>");
        Element p2 = doc.select("p").get(1);

        // removed other assertion
        Elements els = p2.siblingElements();
        assertEquals(2, els.size());
    }

@Test
    public void elementIsNotASiblingOfItself_3_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div>");
        Element p2 = doc.select("p").get(1);

        // removed other assertion
        Elements els = p2.siblingElements();
        // removed other assertion
        assertEquals("<p>One</p>", els.get(0).outerHtml());
    }

@Test
    public void elementIsNotASiblingOfItself_4_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div>");
        Element p2 = doc.select("p").get(1);

        // removed other assertion
        Elements els = p2.siblingElements();
        // removed other assertion
        // removed other assertion
        assertEquals("<p>Three</p>", els.get(1).outerHtml());
    }

@Test
    public void testChildThrowsIndexOutOfBoundsOnMissing_1_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p></div>");
        Element div = doc.select("div").first();

        assertEquals(2, div.children().size());
    }

@Test
    public void testChildThrowsIndexOutOfBoundsOnMissing_2_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p></div>");
        Element div = doc.select("div").first();

        // removed other assertion
        assertEquals("One", div.child(0).text());
    }

@Test
    public void moveByAppend_1_oe() {
        // test for https://github.com/jhy/jsoup/issues/239
        // can empty an element and append its children to another element
        Document doc = Jsoup.parse("<div id=1>Text <p>One</p> Text <p>Two</p></div><div id=2></div>");
        Element div1 = doc.select("div").get(0);
        Element div2 = doc.select("div").get(1);

        assertEquals(4, div1.childNodeSize());
    }

@Test
    public void moveByAppend_2_oe() {
        // test for https://github.com/jhy/jsoup/issues/239
        // can empty an element and append its children to another element
        Document doc = Jsoup.parse("<div id=1>Text <p>One</p> Text <p>Two</p></div><div id=2></div>");
        Element div1 = doc.select("div").get(0);
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        List<Node> children = div1.childNodes();
        assertEquals(4, children.size());
    }

@Test
    public void moveByAppend_3_oe() {
        // test for https://github.com/jhy/jsoup/issues/239
        // can empty an element and append its children to another element
        Document doc = Jsoup.parse("<div id=1>Text <p>One</p> Text <p>Two</p></div><div id=2></div>");
        Element div1 = doc.select("div").get(0);
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        List<Node> children = div1.childNodes();
        // removed other assertion

        div2.insertChildren(0, children);

        assertEquals(4,children.size());// children is NOT backed by div1.childNodes but a wrapper,so should still be 4(but re-parented)assertEquals(0,div1.childNodeSize());
    }

@Test
    public void moveByAppend_4_oe() {
        // test for https://github.com/jhy/jsoup/issues/239
        // can empty an element and append its children to another element
        Document doc = Jsoup.parse("<div id=1>Text <p>One</p> Text <p>Two</p></div><div id=2></div>");
        Element div1 = doc.select("div").get(0);
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        List<Node> children = div1.childNodes();
        // removed other assertion

        div2.insertChildren(0, children);

        // removed other assertion
        assertEquals(4, div2.childNodeSize());
    }

@Test
    public void moveByAppend_5_oe() {
        // test for https://github.com/jhy/jsoup/issues/239
        // can empty an element and append its children to another element
        Document doc = Jsoup.parse("<div id=1>Text <p>One</p> Text <p>Two</p></div><div id=2></div>");
        Element div1 = doc.select("div").get(0);
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        List<Node> children = div1.childNodes();
        // removed other assertion

        div2.insertChildren(0, children);

        // removed other assertion
        // removed other assertion
        assertEquals("<div id=\"1\"></div>\n<div id=\"2\">\n Text \n <p>One</p> Text \n <p>Two</p>\n</div>",doc.body().html());
    }

@Test
    public void insertChildrenAtPosition_1_oe() {
        Document doc = Jsoup.parse("<div id=1>Text1 <p>One</p> Text2 <p>Two</p></div><div id=2>Text3 <p>Three</p></div>");
        Element div1 = doc.select("div").get(0);
        Elements p1s = div1.select("p");
        Element div2 = doc.select("div").get(1);

        assertEquals(2, div2.childNodeSize());
    }

@Test
    public void insertChildrenAtPosition_2_oe() {
        Document doc = Jsoup.parse("<div id=1>Text1 <p>One</p> Text2 <p>Two</p></div><div id=2>Text3 <p>Three</p></div>");
        Element div1 = doc.select("div").get(0);
        Elements p1s = div1.select("p");
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        div2.insertChildren(-1, p1s);
        assertEquals(2,div1.childNodeSize());// moved two out;
    }

@Test
    public void insertChildrenAtPosition_3_oe() {
        Document doc = Jsoup.parse("<div id=1>Text1 <p>One</p> Text2 <p>Two</p></div><div id=2>Text3 <p>Three</p></div>");
        Element div1 = doc.select("div").get(0);
        Elements p1s = div1.select("p");
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        div2.insertChildren(-1, p1s);
        // removed other assertion
        assertEquals(4,div2.childNodeSize());
    }

@Test
    public void insertChildrenAtPosition_4_oe() {
        Document doc = Jsoup.parse("<div id=1>Text1 <p>One</p> Text2 <p>Two</p></div><div id=2>Text3 <p>Three</p></div>");
        Element div1 = doc.select("div").get(0);
        Elements p1s = div1.select("p");
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        div2.insertChildren(-1, p1s);
        // removed other assertion
        // removed other assertion
        assertEquals(3,p1s.get(1).siblingIndex());// should be last;
    }

@Test
    public void insertChildrenAtPosition_5_oe() {
        Document doc = Jsoup.parse("<div id=1>Text1 <p>One</p> Text2 <p>Two</p></div><div id=2>Text3 <p>Three</p></div>");
        Element div1 = doc.select("div").get(0);
        Elements p1s = div1.select("p");
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        div2.insertChildren(-1, p1s);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        List<Node> els = new ArrayList<>();
        Element el1 = new Element(Tag.valueOf("span"), "").text("Span1");
        Element el2 = new Element(Tag.valueOf("span"), "").text("Span2");
        TextNode tn1 = new TextNode("Text4");
        els.add(el1);
        els.add(el2);
        els.add(tn1);

        assertNull(el1.parent());
    }

@Test
    public void insertChildrenAtPosition_6_oe() {
        Document doc = Jsoup.parse("<div id=1>Text1 <p>One</p> Text2 <p>Two</p></div><div id=2>Text3 <p>Three</p></div>");
        Element div1 = doc.select("div").get(0);
        Elements p1s = div1.select("p");
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        div2.insertChildren(-1, p1s);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        List<Node> els = new ArrayList<>();
        Element el1 = new Element(Tag.valueOf("span"), "").text("Span1");
        Element el2 = new Element(Tag.valueOf("span"), "").text("Span2");
        TextNode tn1 = new TextNode("Text4");
        els.add(el1);
        els.add(el2);
        els.add(tn1);

        // removed other assertion
        div2.insertChildren(-2, els);
        assertEquals(div2, el1.parent());
    }

@Test
    public void insertChildrenAtPosition_7_oe() {
        Document doc = Jsoup.parse("<div id=1>Text1 <p>One</p> Text2 <p>Two</p></div><div id=2>Text3 <p>Three</p></div>");
        Element div1 = doc.select("div").get(0);
        Elements p1s = div1.select("p");
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        div2.insertChildren(-1, p1s);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        List<Node> els = new ArrayList<>();
        Element el1 = new Element(Tag.valueOf("span"), "").text("Span1");
        Element el2 = new Element(Tag.valueOf("span"), "").text("Span2");
        TextNode tn1 = new TextNode("Text4");
        els.add(el1);
        els.add(el2);
        els.add(tn1);

        // removed other assertion
        div2.insertChildren(-2, els);
        // removed other assertion
        assertEquals(7, div2.childNodeSize());
    }

@Test
    public void insertChildrenAtPosition_8_oe() {
        Document doc = Jsoup.parse("<div id=1>Text1 <p>One</p> Text2 <p>Two</p></div><div id=2>Text3 <p>Three</p></div>");
        Element div1 = doc.select("div").get(0);
        Elements p1s = div1.select("p");
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        div2.insertChildren(-1, p1s);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        List<Node> els = new ArrayList<>();
        Element el1 = new Element(Tag.valueOf("span"), "").text("Span1");
        Element el2 = new Element(Tag.valueOf("span"), "").text("Span2");
        TextNode tn1 = new TextNode("Text4");
        els.add(el1);
        els.add(el2);
        els.add(tn1);

        // removed other assertion
        div2.insertChildren(-2, els);
        // removed other assertion
        // removed other assertion
        assertEquals(3, el1.siblingIndex());
    }

@Test
    public void insertChildrenAtPosition_9_oe() {
        Document doc = Jsoup.parse("<div id=1>Text1 <p>One</p> Text2 <p>Two</p></div><div id=2>Text3 <p>Three</p></div>");
        Element div1 = doc.select("div").get(0);
        Elements p1s = div1.select("p");
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        div2.insertChildren(-1, p1s);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        List<Node> els = new ArrayList<>();
        Element el1 = new Element(Tag.valueOf("span"), "").text("Span1");
        Element el2 = new Element(Tag.valueOf("span"), "").text("Span2");
        TextNode tn1 = new TextNode("Text4");
        els.add(el1);
        els.add(el2);
        els.add(tn1);

        // removed other assertion
        div2.insertChildren(-2, els);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, el2.siblingIndex());
    }

@Test
    public void insertChildrenAtPosition_10_oe() {
        Document doc = Jsoup.parse("<div id=1>Text1 <p>One</p> Text2 <p>Two</p></div><div id=2>Text3 <p>Three</p></div>");
        Element div1 = doc.select("div").get(0);
        Elements p1s = div1.select("p");
        Element div2 = doc.select("div").get(1);

        // removed other assertion
        div2.insertChildren(-1, p1s);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        List<Node> els = new ArrayList<>();
        Element el1 = new Element(Tag.valueOf("span"), "").text("Span1");
        Element el2 = new Element(Tag.valueOf("span"), "").text("Span2");
        TextNode tn1 = new TextNode("Text4");
        els.add(el1);
        els.add(el2);
        els.add(tn1);

        // removed other assertion
        div2.insertChildren(-2, els);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, tn1.siblingIndex());
    }

@Test
    public void insertChildrenAsCopy_1_oe() {
        Document doc = Jsoup.parse("<div id=1>Text <p>One</p> Text <p>Two</p></div><div id=2></div>");
        Element div1 = doc.select("div").get(0);
        Element div2 = doc.select("div").get(1);
        Elements ps = doc.select("p").clone();
        ps.first().text("One cloned");
        div2.insertChildren(-1, ps);

        assertEquals(4,div1.childNodeSize());// not moved -- cloned assertEquals(2,div2.childNodeSize());
    }

@Test
    public void insertChildrenAsCopy_2_oe() {
        Document doc = Jsoup.parse("<div id=1>Text <p>One</p> Text <p>Two</p></div><div id=2></div>");
        Element div1 = doc.select("div").get(0);
        Element div2 = doc.select("div").get(1);
        Elements ps = doc.select("p").clone();
        ps.first().text("One cloned");
        div2.insertChildren(-1, ps);

        // removed other assertion
        assertEquals("<div id=\"1\">Text <p>One</p> Text <p>Two</p></div><div id=\"2\"><p>One cloned</p><p>Two</p></div>",TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testCssPath_1_oe() {
        Document doc = Jsoup.parse("<div id=\"id1\">A</div><div>B</div><div class=\"c1 c2\">C</div>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);
        assertEquals(divA.cssSelector(), "#id1");
    }

@Test
    public void testCssPath_2_oe() {
        Document doc = Jsoup.parse("<div id=\"id1\">A</div><div>B</div><div class=\"c1 c2\">C</div>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);
        // removed other assertion
        assertEquals(divB.cssSelector(), "html > body > div:nth-child(2)");
    }

@Test
    public void testCssPath_3_oe() {
        Document doc = Jsoup.parse("<div id=\"id1\">A</div><div>B</div><div class=\"c1 c2\">C</div>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);
        // removed other assertion
        // removed other assertion
        assertEquals(divC.cssSelector(), "html > body > div.c1.c2");
    }

@Test
    public void testCssPath_4_oe() {
        Document doc = Jsoup.parse("<div id=\"id1\">A</div><div>B</div><div class=\"c1 c2\">C</div>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertSame(divA, doc.select(divA.cssSelector()).first());
    }

@Test
    public void testCssPath_5_oe() {
        Document doc = Jsoup.parse("<div id=\"id1\">A</div><div>B</div><div class=\"c1 c2\">C</div>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertSame(divB, doc.select(divB.cssSelector()).first());
    }

@Test
    public void testCssPath_6_oe() {
        Document doc = Jsoup.parse("<div id=\"id1\">A</div><div>B</div><div class=\"c1 c2\">C</div>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertSame(divC, doc.select(divC.cssSelector()).first());
    }

@Test
    public void testCssPathDuplicateIds_1_oe() {
        // https://github.com/jhy/jsoup/issues/1147 - multiple elements with same ID, use the non-ID form
        Document doc = Jsoup.parse("<article><div id=dupe>A</div><div id=dupe>B</div><div id=dupe class=c1>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);

        assertEquals(divA.cssSelector(), "html > body > article > div:nth-child(1)");
    }

@Test
    public void testCssPathDuplicateIds_2_oe() {
        // https://github.com/jhy/jsoup/issues/1147 - multiple elements with same ID, use the non-ID form
        Document doc = Jsoup.parse("<article><div id=dupe>A</div><div id=dupe>B</div><div id=dupe class=c1>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);

        // removed other assertion
        assertEquals(divB.cssSelector(), "html > body > article > div:nth-child(2)");
    }

@Test
    public void testCssPathDuplicateIds_3_oe() {
        // https://github.com/jhy/jsoup/issues/1147 - multiple elements with same ID, use the non-ID form
        Document doc = Jsoup.parse("<article><div id=dupe>A</div><div id=dupe>B</div><div id=dupe class=c1>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);

        // removed other assertion
        // removed other assertion
        assertEquals(divC.cssSelector(), "html > body > article > div.c1");
    }

@Test
    public void testCssPathDuplicateIds_4_oe() {
        // https://github.com/jhy/jsoup/issues/1147 - multiple elements with same ID, use the non-ID form
        Document doc = Jsoup.parse("<article><div id=dupe>A</div><div id=dupe>B</div><div id=dupe class=c1>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertSame(divA, doc.select(divA.cssSelector()).first());
    }

@Test
    public void testCssPathDuplicateIds_5_oe() {
        // https://github.com/jhy/jsoup/issues/1147 - multiple elements with same ID, use the non-ID form
        Document doc = Jsoup.parse("<article><div id=dupe>A</div><div id=dupe>B</div><div id=dupe class=c1>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertSame(divB, doc.select(divB.cssSelector()).first());
    }

@Test
    public void testCssPathDuplicateIds_6_oe() {
        // https://github.com/jhy/jsoup/issues/1147 - multiple elements with same ID, use the non-ID form
        Document doc = Jsoup.parse("<article><div id=dupe>A</div><div id=dupe>B</div><div id=dupe class=c1>");
        Element divA = doc.select("div").get(0);
        Element divB = doc.select("div").get(1);
        Element divC = doc.select("div").get(2);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertSame(divC, doc.select(divC.cssSelector()).first());
    }

@Test
    public void testClassNames_1_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        assertEquals("c1 c2", div.className());
    }

@Test
    public void testClassNames_2_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        // removed other assertion

        final Set<String> set1 = div.classNames();
        final Object[] arr1 = set1.toArray();
        assertEquals(2, arr1.length);
    }

@Test
    public void testClassNames_3_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        // removed other assertion

        final Set<String> set1 = div.classNames();
        final Object[] arr1 = set1.toArray();
        // removed other assertion
        assertEquals("c1", arr1[0]);
    }

@Test
    public void testClassNames_4_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        // removed other assertion

        final Set<String> set1 = div.classNames();
        final Object[] arr1 = set1.toArray();
        // removed other assertion
        // removed other assertion
        assertEquals("c2", arr1[1]);
    }

@Test
    public void testClassNames_5_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        // removed other assertion

        final Set<String> set1 = div.classNames();
        final Object[] arr1 = set1.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Changes to the set should not be reflected in the Elements getters
        set1.add("c3");
        assertEquals(2, div.classNames().size());
    }

@Test
    public void testClassNames_6_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        // removed other assertion

        final Set<String> set1 = div.classNames();
        final Object[] arr1 = set1.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Changes to the set should not be reflected in the Elements getters
        set1.add("c3");
        // removed other assertion
        assertEquals("c1 c2", div.className());
    }

@Test
    public void testClassNames_7_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        // removed other assertion

        final Set<String> set1 = div.classNames();
        final Object[] arr1 = set1.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Changes to the set should not be reflected in the Elements getters
        set1.add("c3");
        // removed other assertion
        // removed other assertion

        // Update the class names to a fresh set
        final Set<String> newSet = new LinkedHashSet<>(3);
        newSet.addAll(set1);
        newSet.add("c3");

        div.classNames(newSet);

        assertEquals("c1 c2 c3", div.className());
    }

@Test
    public void testClassNames_8_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        // removed other assertion

        final Set<String> set1 = div.classNames();
        final Object[] arr1 = set1.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Changes to the set should not be reflected in the Elements getters
        set1.add("c3");
        // removed other assertion
        // removed other assertion

        // Update the class names to a fresh set
        final Set<String> newSet = new LinkedHashSet<>(3);
        newSet.addAll(set1);
        newSet.add("c3");

        div.classNames(newSet);

        // removed other assertion

        final Set<String> set2 = div.classNames();
        final Object[] arr2 = set2.toArray();
        assertEquals(3, arr2.length);
    }

@Test
    public void testClassNames_9_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        // removed other assertion

        final Set<String> set1 = div.classNames();
        final Object[] arr1 = set1.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Changes to the set should not be reflected in the Elements getters
        set1.add("c3");
        // removed other assertion
        // removed other assertion

        // Update the class names to a fresh set
        final Set<String> newSet = new LinkedHashSet<>(3);
        newSet.addAll(set1);
        newSet.add("c3");

        div.classNames(newSet);

        // removed other assertion

        final Set<String> set2 = div.classNames();
        final Object[] arr2 = set2.toArray();
        // removed other assertion
        assertEquals("c1", arr2[0]);
    }

@Test
    public void testClassNames_10_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        // removed other assertion

        final Set<String> set1 = div.classNames();
        final Object[] arr1 = set1.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Changes to the set should not be reflected in the Elements getters
        set1.add("c3");
        // removed other assertion
        // removed other assertion

        // Update the class names to a fresh set
        final Set<String> newSet = new LinkedHashSet<>(3);
        newSet.addAll(set1);
        newSet.add("c3");

        div.classNames(newSet);

        // removed other assertion

        final Set<String> set2 = div.classNames();
        final Object[] arr2 = set2.toArray();
        // removed other assertion
        // removed other assertion
        assertEquals("c2", arr2[1]);
    }

@Test
    public void testClassNames_11_oe() {
        Document doc = Jsoup.parse("<div class=\"c1 c2\">C</div>");
        Element div = doc.select("div").get(0);

        // removed other assertion

        final Set<String> set1 = div.classNames();
        final Object[] arr1 = set1.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Changes to the set should not be reflected in the Elements getters
        set1.add("c3");
        // removed other assertion
        // removed other assertion

        // Update the class names to a fresh set
        final Set<String> newSet = new LinkedHashSet<>(3);
        newSet.addAll(set1);
        newSet.add("c3");

        div.classNames(newSet);

        // removed other assertion

        final Set<String> set2 = div.classNames();
        final Object[] arr2 = set2.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c3", arr2[2]);
    }

@Test
    public void testHashAndEqualsAndValue_1_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        assertEquals(8, els.size());
    }

@Test
    public void testHashAndEqualsAndValue_2_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        assertEquals(e0, e0);
    }

@Test
    public void testHashAndEqualsAndValue_3_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        assertTrue(e0.hasSameValue(e1));
    }

@Test
    public void testHashAndEqualsAndValue_4_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        assertTrue(e0.hasSameValue(e4));
    }

@Test
    public void testHashAndEqualsAndValue_5_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(e0.hasSameValue(e5));
    }

@Test
    public void testHashAndEqualsAndValue_6_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(e0, e2);
    }

@Test
    public void testHashAndEqualsAndValue_7_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(e0.hasSameValue(e2));
    }

@Test
    public void testHashAndEqualsAndValue_8_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(e0.hasSameValue(e3));
    }

@Test
    public void testHashAndEqualsAndValue_9_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(e0.hasSameValue(e6));
    }

@Test
    public void testHashAndEqualsAndValue_10_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(e0.hasSameValue(e7));
    }

@Test
    public void testHashAndEqualsAndValue_11_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(e0.hashCode(), e0.hashCode());
    }

@Test
    public void testHashAndEqualsAndValue_12_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertNotEquals(e0.hashCode(), (e2.hashCode()));
    }

@Test
    public void testHashAndEqualsAndValue_13_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNotEquals(e0.hashCode(), (e3).hashCode());
    }

@Test
    public void testHashAndEqualsAndValue_14_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(e0.hashCode(), (e6).hashCode());
    }

@Test
    public void testHashAndEqualsAndValue_15_oe() {
        // .equals and hashcode are identity. value is content.

        String doc1 = "<div id=1><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>" +
            "<div id=2><p class=one>One</p><p class=one>One</p><p class=one>Two</p><p class=two>One</p></div>";

        Document doc = Jsoup.parse(doc1);
        Elements els = doc.select("p");

        /*
        for (Element el : els) {
            System.out.println(el.hashCode() + " - " + el.outerHtml());
        }

        0 1534787905 - <p class="one">One</p>
        1 1534787905 - <p class="one">One</p>
        2 1539683239 - <p class="one">Two</p>
        3 1535455211 - <p class="two">One</p>
        4 1534787905 - <p class="one">One</p>
        5 1534787905 - <p class="one">One</p>
        6 1539683239 - <p class="one">Two</p>
        7 1535455211 - <p class="two">One</p>
        */
        // removed other assertion
        Element e0 = els.get(0);
        Element e1 = els.get(1);
        Element e2 = els.get(2);
        Element e3 = els.get(3);
        Element e4 = els.get(4);
        Element e5 = els.get(5);
        Element e6 = els.get(6);
        Element e7 = els.get(7);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(e0.hashCode(), (e7).hashCode());
    }

@Test
    public void testRelativeUrls_1_oe() {
        String html = "<body><a href='./one.html'>One</a> <a href='two.html'>two</a> <a href='../three.html'>Three</a> <a href='//example2.com/four/'>Four</a> <a href='https://example2.com/five/'>Five</a> <a>Six</a> <a href=''>Seven</a>";
        Document doc = Jsoup.parse(html, "http://example.com/bar/");
        Elements els = doc.select("a");

        assertEquals("http://example.com/bar/one.html", els.get(0).absUrl("href"));
    }

@Test
    public void testRelativeUrls_2_oe() {
        String html = "<body><a href='./one.html'>One</a> <a href='two.html'>two</a> <a href='../three.html'>Three</a> <a href='//example2.com/four/'>Four</a> <a href='https://example2.com/five/'>Five</a> <a>Six</a> <a href=''>Seven</a>";
        Document doc = Jsoup.parse(html, "http://example.com/bar/");
        Elements els = doc.select("a");

        // removed other assertion
        assertEquals("http://example.com/bar/two.html", els.get(1).absUrl("href"));
    }

@Test
    public void testRelativeUrls_3_oe() {
        String html = "<body><a href='./one.html'>One</a> <a href='two.html'>two</a> <a href='../three.html'>Three</a> <a href='//example2.com/four/'>Four</a> <a href='https://example2.com/five/'>Five</a> <a>Six</a> <a href=''>Seven</a>";
        Document doc = Jsoup.parse(html, "http://example.com/bar/");
        Elements els = doc.select("a");

        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/three.html", els.get(2).absUrl("href"));
    }

@Test
    public void testRelativeUrls_4_oe() {
        String html = "<body><a href='./one.html'>One</a> <a href='two.html'>two</a> <a href='../three.html'>Three</a> <a href='//example2.com/four/'>Four</a> <a href='https://example2.com/five/'>Five</a> <a>Six</a> <a href=''>Seven</a>";
        Document doc = Jsoup.parse(html, "http://example.com/bar/");
        Elements els = doc.select("a");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example2.com/four/", els.get(3).absUrl("href"));
    }

@Test
    public void testRelativeUrls_5_oe() {
        String html = "<body><a href='./one.html'>One</a> <a href='two.html'>two</a> <a href='../three.html'>Three</a> <a href='//example2.com/four/'>Four</a> <a href='https://example2.com/five/'>Five</a> <a>Six</a> <a href=''>Seven</a>";
        Document doc = Jsoup.parse(html, "http://example.com/bar/");
        Elements els = doc.select("a");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("https://example2.com/five/", els.get(4).absUrl("href"));
    }

@Test
    public void testRelativeUrls_6_oe() {
        String html = "<body><a href='./one.html'>One</a> <a href='two.html'>two</a> <a href='../three.html'>Three</a> <a href='//example2.com/four/'>Four</a> <a href='https://example2.com/five/'>Five</a> <a>Six</a> <a href=''>Seven</a>";
        Document doc = Jsoup.parse(html, "http://example.com/bar/");
        Elements els = doc.select("a");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", els.get(5).absUrl("href"));
    }

@Test
    public void testRelativeUrls_7_oe() {
        String html = "<body><a href='./one.html'>One</a> <a href='two.html'>two</a> <a href='../three.html'>Three</a> <a href='//example2.com/four/'>Four</a> <a href='https://example2.com/five/'>Five</a> <a>Six</a> <a href=''>Seven</a>";
        Document doc = Jsoup.parse(html, "http://example.com/bar/");
        Elements els = doc.select("a");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/bar/", els.get(6).absUrl("href"));
    }

@Test
    public void testRelativeIdnUrls_1_oe() {
        String idn = "https://www.测试.测试/";
        String idnFoo = idn + "foo.html?bar";

        Document doc = Jsoup.parse("<a href=''>One</a><a href='/bar.html?qux'>Two</a>", idnFoo);
        Elements els = doc.select("a");
        Element one = els.get(0);
        Element two = els.get(1);
        String hrefOne = one.absUrl("href");
        String hrefTwo = two.absUrl("href");
        assertEquals(idnFoo, hrefOne);
    }

@Test
    public void testRelativeIdnUrls_2_oe() {
        String idn = "https://www.测试.测试/";
        String idnFoo = idn + "foo.html?bar";

        Document doc = Jsoup.parse("<a href=''>One</a><a href='/bar.html?qux'>Two</a>", idnFoo);
        Elements els = doc.select("a");
        Element one = els.get(0);
        Element two = els.get(1);
        String hrefOne = one.absUrl("href");
        String hrefTwo = two.absUrl("href");
        // removed other assertion
        assertEquals("https://www.测试.测试/bar.html?qux", hrefTwo);
    }

@Test
    public void appendMustCorrectlyMoveChildrenInsideOneParentElement_1_oe() {
        Document doc = new Document("");
        Element body = doc.appendElement("body");
        body.appendElement("div1");
        body.appendElement("div2");
        final Element div3 = body.appendElement("div3");
        div3.text("Check");
        final Element div4 = body.appendElement("div4");

        ArrayList<Element> toMove = new ArrayList<>();
        toMove.add(div3);
        toMove.add(div4);

        body.insertChildren(0, toMove);

        String result = doc.toString().replaceAll("\\s+", "");
        assertEquals("<body><div3>Check</div3><div4></div4><div1></div1><div2></div2></body>", result);
    }

@Test
    public void testHashcodeIsStableWithContentChanges_1_oe() {
        Element root = new Element(Tag.valueOf("root"), "");

        HashSet<Element> set = new HashSet<>();
        // Add root node:
        set.add(root);

        root.appendChild(new Element(Tag.valueOf("a"), ""));
        assertTrue(set.contains(root));
    }

@Test
    public void testNamespacedElements_1_oe() {
        // Namespaces with ns:tag in HTML must be translated to ns|tag in CSS.
        String html = "<html><body><fb:comments /></body></html>";
        Document doc = Jsoup.parse(html, "http://example.com/bar/");
        Elements els = doc.select("fb|comments");
        assertEquals(1, els.size());
    }

@Test
    public void testNamespacedElements_2_oe() {
        // Namespaces with ns:tag in HTML must be translated to ns|tag in CSS.
        String html = "<html><body><fb:comments /></body></html>";
        Document doc = Jsoup.parse(html, "http://example.com/bar/");
        Elements els = doc.select("fb|comments");
        // removed other assertion
        assertEquals("html > body > fb|comments", els.get(0).cssSelector());
    }

@Test
    public void testChainedRemoveAttributes_1_oe() {
        String html = "<a one two three four>Text</a>";
        Document doc = Jsoup.parse(html);
        Element a = doc.select("a").first();
        a
            .removeAttr("zero")
            .removeAttr("one")
            .removeAttr("two")
            .removeAttr("three")
            .removeAttr("four")
            .removeAttr("five");
        assertEquals("<a>Text</a>", a.outerHtml());
    }

@Test
    public void testLoopedRemoveAttributes_1_oe() {
        String html = "<a one two three four>Text</a><p foo>Two</p>";
        Document doc = Jsoup.parse(html);
        for (Element el : doc.getAllElements()) {
            el.clearAttributes();
        }

        assertEquals("<a>Text</a>\n<p>Two</p>", doc.body().html());
    }

@Test
    public void testIs_1_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        assertTrue(p.is("p"));
    }

@Test
    public void testIs_2_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        assertFalse(p.is("div"));
    }

@Test
    public void testIs_3_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        assertTrue(p.is("p:has(a)"));
    }

@Test
    public void testIs_4_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(p.is("a"));// does not descend assertTrue(p.is("p:first-child"));
    }

@Test
    public void testIs_5_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(p.is("p:last-child"));
    }

@Test
    public void testIs_6_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(p.is("*"));
    }

@Test
    public void testIs_7_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(p.is("div p"));
    }

@Test
    public void testIs_8_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element q = doc.select("p").last();
        assertTrue(q.is("p"));
    }

@Test
    public void testIs_9_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element q = doc.select("p").last();
        // removed other assertion
        assertTrue(q.is("p ~ p"));
    }

@Test
    public void testIs_10_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element q = doc.select("p").last();
        // removed other assertion
        // removed other assertion
        assertTrue(q.is("p + p"));
    }

@Test
    public void testIs_11_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element q = doc.select("p").last();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(q.is("p:last-child"));
    }

@Test
    public void testIs_12_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element q = doc.select("p").last();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(q.is("p a"));
    }

@Test
    public void testIs_13_oe() {
        String html = "<div><p>One <a class=big>Two</a> Three</p><p>Another</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element q = doc.select("p").last();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(q.is("a"));
    }

@Test
    public void testEvalMethods_1_oe() {
        Document doc = Jsoup.parse("<div><p>One <a class=big>Two</a> Three</p><p>Another</p>");
        Element p = doc.selectFirst(QueryParser.parse(("p")));
        assertEquals("One Three", p.ownText());
    }

@Test
    public void testEvalMethods_2_oe() {
        Document doc = Jsoup.parse("<div><p>One <a class=big>Two</a> Three</p><p>Another</p>");
        Element p = doc.selectFirst(QueryParser.parse(("p")));
        // removed other assertion

        assertTrue(p.is(QueryParser.parse("p")));
    }

@Test
    public void testEvalMethods_3_oe() {
        Document doc = Jsoup.parse("<div><p>One <a class=big>Two</a> Three</p><p>Another</p>");
        Element p = doc.selectFirst(QueryParser.parse(("p")));
        // removed other assertion

        // removed other assertion
        Evaluator aEval = QueryParser.parse("a");
        assertFalse(p.is(aEval));
    }

@Test
    public void testEvalMethods_4_oe() {
        Document doc = Jsoup.parse("<div><p>One <a class=big>Two</a> Three</p><p>Another</p>");
        Element p = doc.selectFirst(QueryParser.parse(("p")));
        // removed other assertion

        // removed other assertion
        Evaluator aEval = QueryParser.parse("a");
        // removed other assertion

        Element a = p.selectFirst(aEval);
        assertEquals("div", a.closest(QueryParser.parse("div:has( > p)")).tagName());
    }

@Test
    public void testEvalMethods_5_oe() {
        Document doc = Jsoup.parse("<div><p>One <a class=big>Two</a> Three</p><p>Another</p>");
        Element p = doc.selectFirst(QueryParser.parse(("p")));
        // removed other assertion

        // removed other assertion
        Evaluator aEval = QueryParser.parse("a");
        // removed other assertion

        Element a = p.selectFirst(aEval);
        // removed other assertion
        Element body = p.closest(QueryParser.parse("body"));
        assertEquals("body", body.nodeName());
    }

@Test
    public void testClosest_1_oe() {
        String html = "<article>\n" +
            "  <div id=div-01>Here is div-01\n" +
            "    <div id=div-02>Here is div-02\n" +
            "      <div id=div-03>Here is div-03</div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</article>";

        Document doc = Jsoup.parse(html);
        Element el = doc.selectFirst("#div-03");
        assertEquals("Here is div-03", el.text());
    }

@Test
    public void testClosest_2_oe() {
        String html = "<article>\n" +
            "  <div id=div-01>Here is div-01\n" +
            "    <div id=div-02>Here is div-02\n" +
            "      <div id=div-03>Here is div-03</div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</article>";

        Document doc = Jsoup.parse(html);
        Element el = doc.selectFirst("#div-03");
        // removed other assertion
        assertEquals("div-03", el.id());
    }

@Test
    public void testClosest_3_oe() {
        String html = "<article>\n" +
            "  <div id=div-01>Here is div-01\n" +
            "    <div id=div-02>Here is div-02\n" +
            "      <div id=div-03>Here is div-03</div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</article>";

        Document doc = Jsoup.parse(html);
        Element el = doc.selectFirst("#div-03");
        // removed other assertion
        // removed other assertion

        assertEquals("div-02", el.closest("#div-02").id());
    }

@Test
    public void testClosest_4_oe() {
        String html = "<article>\n" +
            "  <div id=div-01>Here is div-01\n" +
            "    <div id=div-02>Here is div-02\n" +
            "      <div id=div-03>Here is div-03</div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</article>";

        Document doc = Jsoup.parse(html);
        Element el = doc.selectFirst("#div-03");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(el,el.closest("div div"));// closest div in a div is itself assertEquals("div-01",el.closest("article > div").id());
    }

@Test
    public void testClosest_5_oe() {
        String html = "<article>\n" +
            "  <div id=div-01>Here is div-01\n" +
            "    <div id=div-02>Here is div-02\n" +
            "      <div id=div-03>Here is div-03</div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</article>";

        Document doc = Jsoup.parse(html);
        Element el = doc.selectFirst("#div-03");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("article", el.closest(":not(div)").tagName());
    }

@Test
    public void testClosest_6_oe() {
        String html = "<article>\n" +
            "  <div id=div-01>Here is div-01\n" +
            "    <div id=div-02>Here is div-02\n" +
            "      <div id=div-03>Here is div-03</div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</article>";

        Document doc = Jsoup.parse(html);
        Element el = doc.selectFirst("#div-03");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(el.closest("p"));
    }

@Test
    public void elementByTagName_1_oe() {
        Element a = new Element("P");
        assertEquals("P", a.tagName());
    }

@Test
    public void testChildrenElements_1_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        assertEquals(2, docChildren.size());
    }

@Test
    public void testChildrenElements_2_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        assertEquals("<p><a>One</a></p>", docChildren.get(0).outerHtml());
    }

@Test
    public void testChildrenElements_3_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        assertEquals("<p><a>Two</a></p>", docChildren.get(1).outerHtml());
    }

@Test
    public void testChildrenElements_4_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, div.childNodes().size());
    }

@Test
    public void testChildrenElements_5_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Three", div.childNodes().get(2).outerHtml());
    }

@Test
    public void testChildrenElements_6_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(1, p.children().size());
    }

@Test
    public void testChildrenElements_7_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("One", p.children().text());
    }

@Test
    public void testChildrenElements_8_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(0, span.children().size());
    }

@Test
    public void testChildrenElements_9_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(1, span.childNodes().size());
    }

@Test
    public void testChildrenElements_10_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("Four", span.childNodes().get(0).outerHtml());
    }

@Test
    public void testChildrenElements_11_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, foo.children().size());
    }

@Test
    public void testChildrenElements_12_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, foo.childNodes().size());
    }

@Test
    public void testChildrenElements_13_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0, img.children().size());
    }

@Test
    public void testChildrenElements_14_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Element p = doc.select("p").first();
        Element span = doc.select("span").first();
        Element foo = doc.select("foo").first();
        Element img = doc.select("img").first();

        Elements docChildren = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, img.childNodes().size());
    }

@Test
    public void testShadowElementsAreUpdated_1_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        assertEquals(2,els.size());// the two Ps;
    }

@Test
    public void testShadowElementsAreUpdated_2_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        // removed other assertion
        assertEquals(3,nodes.size());// the "Three" textnode;
    }

@Test
    public void testShadowElementsAreUpdated_3_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        // removed other assertion
        // removed other assertion
        Element p3 = new Element("p").text("P3");
        Element p4 = new Element("p").text("P4");
        div.insertChildren(1, p3);
        div.insertChildren(3, p4);
        Elements els2 = div.children();

        // first els should not have changed
        assertEquals(2, els.size());
    }

@Test
    public void testShadowElementsAreUpdated_4_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        // removed other assertion
        // removed other assertion
        Element p3 = new Element("p").text("P3");
        Element p4 = new Element("p").text("P4");
        div.insertChildren(1, p3);
        div.insertChildren(3, p4);
        Elements els2 = div.children();

        // first els should not have changed
        // removed other assertion
        assertEquals(4, els2.size());
    }

@Test
    public void testShadowElementsAreUpdated_5_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        // removed other assertion
        // removed other assertion
        Element p3 = new Element("p").text("P3");
        Element p4 = new Element("p").text("P4");
        div.insertChildren(1, p3);
        div.insertChildren(3, p4);
        Elements els2 = div.children();

        // first els should not have changed
        // removed other assertion
        // removed other assertion

        assertEquals("<p><a>One</a></p>\n" + "<p>P3</p>\n" + "<p><a>Two</a></p>\n" + "<p>P4</p>Three",div.html());
    }

@Test
    public void testShadowElementsAreUpdated_6_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        // removed other assertion
        // removed other assertion
        Element p3 = new Element("p").text("P3");
        Element p4 = new Element("p").text("P4");
        div.insertChildren(1, p3);
        div.insertChildren(3, p4);
        Elements els2 = div.children();

        // first els should not have changed
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("P3", els2.get(1).text());
    }

@Test
    public void testShadowElementsAreUpdated_7_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        // removed other assertion
        // removed other assertion
        Element p3 = new Element("p").text("P3");
        Element p4 = new Element("p").text("P4");
        div.insertChildren(1, p3);
        div.insertChildren(3, p4);
        Elements els2 = div.children();

        // first els should not have changed
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("P4", els2.get(3).text());
    }

@Test
    public void testShadowElementsAreUpdated_8_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        // removed other assertion
        // removed other assertion
        Element p3 = new Element("p").text("P3");
        Element p4 = new Element("p").text("P4");
        div.insertChildren(1, p3);
        div.insertChildren(3, p4);
        Elements els2 = div.children();

        // first els should not have changed
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        p3.after("<span>Another</span");

        Elements els3 = div.children();
        assertEquals(5, els3.size());
    }

@Test
    public void testShadowElementsAreUpdated_9_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        // removed other assertion
        // removed other assertion
        Element p3 = new Element("p").text("P3");
        Element p4 = new Element("p").text("P4");
        div.insertChildren(1, p3);
        div.insertChildren(3, p4);
        Elements els2 = div.children();

        // first els should not have changed
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        p3.after("<span>Another</span");

        Elements els3 = div.children();
        // removed other assertion
        assertEquals("span", els3.get(2).tagName());
    }

@Test
    public void testShadowElementsAreUpdated_10_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        // removed other assertion
        // removed other assertion
        Element p3 = new Element("p").text("P3");
        Element p4 = new Element("p").text("P4");
        div.insertChildren(1, p3);
        div.insertChildren(3, p4);
        Elements els2 = div.children();

        // first els should not have changed
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        p3.after("<span>Another</span");

        Elements els3 = div.children();
        // removed other assertion
        // removed other assertion
        assertEquals("Another", els3.get(2).text());
    }

@Test
    public void testShadowElementsAreUpdated_11_oe() {
        String html = "<div><p><a>One</a></p><p><a>Two</a></p>Three</div><span>Four</span><foo></foo><img>";
        Document doc = Jsoup.parse(html);
        Element div = doc.select("div").first();
        Elements els = div.children();
        List<Node> nodes = div.childNodes();

        // removed other assertion
        // removed other assertion
        Element p3 = new Element("p").text("P3");
        Element p4 = new Element("p").text("P4");
        div.insertChildren(1, p3);
        div.insertChildren(3, p4);
        Elements els2 = div.children();

        // first els should not have changed
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        p3.after("<span>Another</span");

        Elements els3 = div.children();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("<p><a>One</a></p>\n" + "<p>P3</p><span>Another</span>\n" + "<p><a>Two</a></p>\n" + "<p>P4</p>Three",div.html());
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_1_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        assertEquals("SomeText AnotherText", p.className());
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_2_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        assertTrue(p.classNames().contains("SomeText"));
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_3_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        assertTrue(p.classNames().contains("AnotherText"));
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_4_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(p.hasClass("SomeText"));
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_5_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(p.hasClass("sometext"));
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_6_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(p.hasClass("AnotherText"));
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_7_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(p.hasClass("anothertext"));
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_8_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element p1 = doc.select(".SomeText").first();
        Element p2 = doc.select(".sometext").first();
        Element p3 = doc.select("[class=SomeText AnotherText]").first();
        Element p4 = doc.select("[Class=SomeText AnotherText]").first();
        Element p5 = doc.select("[class=sometext anothertext]").first();
        Element p6 = doc.select("[class=SomeText AnotherText]").first();
        Element p7 = doc.select("[class^=sometext]").first();
        Element p8 = doc.select("[class$=nothertext]").first();
        Element p9 = doc.select("[class^=sometext]").first();
        Element p10 = doc.select("[class$=AnotherText]").first();

        assertEquals("One", p1.text());
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_9_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element p1 = doc.select(".SomeText").first();
        Element p2 = doc.select(".sometext").first();
        Element p3 = doc.select("[class=SomeText AnotherText]").first();
        Element p4 = doc.select("[Class=SomeText AnotherText]").first();
        Element p5 = doc.select("[class=sometext anothertext]").first();
        Element p6 = doc.select("[class=SomeText AnotherText]").first();
        Element p7 = doc.select("[class^=sometext]").first();
        Element p8 = doc.select("[class$=nothertext]").first();
        Element p9 = doc.select("[class^=sometext]").first();
        Element p10 = doc.select("[class$=AnotherText]").first();

        // removed other assertion
        assertEquals(p1, p2);
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_10_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element p1 = doc.select(".SomeText").first();
        Element p2 = doc.select(".sometext").first();
        Element p3 = doc.select("[class=SomeText AnotherText]").first();
        Element p4 = doc.select("[Class=SomeText AnotherText]").first();
        Element p5 = doc.select("[class=sometext anothertext]").first();
        Element p6 = doc.select("[class=SomeText AnotherText]").first();
        Element p7 = doc.select("[class^=sometext]").first();
        Element p8 = doc.select("[class$=nothertext]").first();
        Element p9 = doc.select("[class^=sometext]").first();
        Element p10 = doc.select("[class$=AnotherText]").first();

        // removed other assertion
        // removed other assertion
        assertEquals(p1, p3);
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_11_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element p1 = doc.select(".SomeText").first();
        Element p2 = doc.select(".sometext").first();
        Element p3 = doc.select("[class=SomeText AnotherText]").first();
        Element p4 = doc.select("[Class=SomeText AnotherText]").first();
        Element p5 = doc.select("[class=sometext anothertext]").first();
        Element p6 = doc.select("[class=SomeText AnotherText]").first();
        Element p7 = doc.select("[class^=sometext]").first();
        Element p8 = doc.select("[class$=nothertext]").first();
        Element p9 = doc.select("[class^=sometext]").first();
        Element p10 = doc.select("[class$=AnotherText]").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(p1, p4);
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_12_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element p1 = doc.select(".SomeText").first();
        Element p2 = doc.select(".sometext").first();
        Element p3 = doc.select("[class=SomeText AnotherText]").first();
        Element p4 = doc.select("[Class=SomeText AnotherText]").first();
        Element p5 = doc.select("[class=sometext anothertext]").first();
        Element p6 = doc.select("[class=SomeText AnotherText]").first();
        Element p7 = doc.select("[class^=sometext]").first();
        Element p8 = doc.select("[class$=nothertext]").first();
        Element p9 = doc.select("[class^=sometext]").first();
        Element p10 = doc.select("[class$=AnotherText]").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(p1, p5);
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_13_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element p1 = doc.select(".SomeText").first();
        Element p2 = doc.select(".sometext").first();
        Element p3 = doc.select("[class=SomeText AnotherText]").first();
        Element p4 = doc.select("[Class=SomeText AnotherText]").first();
        Element p5 = doc.select("[class=sometext anothertext]").first();
        Element p6 = doc.select("[class=SomeText AnotherText]").first();
        Element p7 = doc.select("[class^=sometext]").first();
        Element p8 = doc.select("[class$=nothertext]").first();
        Element p9 = doc.select("[class^=sometext]").first();
        Element p10 = doc.select("[class$=AnotherText]").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(p1, p6);
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_14_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element p1 = doc.select(".SomeText").first();
        Element p2 = doc.select(".sometext").first();
        Element p3 = doc.select("[class=SomeText AnotherText]").first();
        Element p4 = doc.select("[Class=SomeText AnotherText]").first();
        Element p5 = doc.select("[class=sometext anothertext]").first();
        Element p6 = doc.select("[class=SomeText AnotherText]").first();
        Element p7 = doc.select("[class^=sometext]").first();
        Element p8 = doc.select("[class$=nothertext]").first();
        Element p9 = doc.select("[class^=sometext]").first();
        Element p10 = doc.select("[class$=AnotherText]").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(p1, p7);
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_15_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element p1 = doc.select(".SomeText").first();
        Element p2 = doc.select(".sometext").first();
        Element p3 = doc.select("[class=SomeText AnotherText]").first();
        Element p4 = doc.select("[Class=SomeText AnotherText]").first();
        Element p5 = doc.select("[class=sometext anothertext]").first();
        Element p6 = doc.select("[class=SomeText AnotherText]").first();
        Element p7 = doc.select("[class^=sometext]").first();
        Element p8 = doc.select("[class$=nothertext]").first();
        Element p9 = doc.select("[class^=sometext]").first();
        Element p10 = doc.select("[class$=AnotherText]").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(p1, p8);
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_16_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element p1 = doc.select(".SomeText").first();
        Element p2 = doc.select(".sometext").first();
        Element p3 = doc.select("[class=SomeText AnotherText]").first();
        Element p4 = doc.select("[Class=SomeText AnotherText]").first();
        Element p5 = doc.select("[class=sometext anothertext]").first();
        Element p6 = doc.select("[class=SomeText AnotherText]").first();
        Element p7 = doc.select("[class^=sometext]").first();
        Element p8 = doc.select("[class$=nothertext]").first();
        Element p9 = doc.select("[class^=sometext]").first();
        Element p10 = doc.select("[class$=AnotherText]").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(p1, p9);
    }

@Test
    public void classNamesAndAttributeNameIsCaseInsensitive_17_oe() {
        String html = "<p Class='SomeText AnotherText'>One</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element p1 = doc.select(".SomeText").first();
        Element p2 = doc.select(".sometext").first();
        Element p3 = doc.select("[class=SomeText AnotherText]").first();
        Element p4 = doc.select("[Class=SomeText AnotherText]").first();
        Element p5 = doc.select("[class=sometext anothertext]").first();
        Element p6 = doc.select("[class=SomeText AnotherText]").first();
        Element p7 = doc.select("[class^=sometext]").first();
        Element p8 = doc.select("[class$=nothertext]").first();
        Element p9 = doc.select("[class^=sometext]").first();
        Element p10 = doc.select("[class$=AnotherText]").first();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(p1, p10);
    }

@Test
    public void testAppendTo_1_oe() {
        String parentHtml = "<div class='a'></div>";
        String childHtml = "<div class='b'></div><p>Two</p>";

        Document parentDoc = Jsoup.parse(parentHtml);
        Element parent = parentDoc.body();
        Document childDoc = Jsoup.parse(childHtml);

        Element div = childDoc.select("div").first();
        Element p = childDoc.select("p").first();
        Element appendTo1 = div.appendTo(parent);
        assertEquals(div, appendTo1);
    }

@Test
    public void testAppendTo_2_oe() {
        String parentHtml = "<div class='a'></div>";
        String childHtml = "<div class='b'></div><p>Two</p>";

        Document parentDoc = Jsoup.parse(parentHtml);
        Element parent = parentDoc.body();
        Document childDoc = Jsoup.parse(childHtml);

        Element div = childDoc.select("div").first();
        Element p = childDoc.select("p").first();
        Element appendTo1 = div.appendTo(parent);
        // removed other assertion

        Element appendTo2 = p.appendTo(div);
        assertEquals(p, appendTo2);
    }

@Test
    public void testAppendTo_3_oe() {
        String parentHtml = "<div class='a'></div>";
        String childHtml = "<div class='b'></div><p>Two</p>";

        Document parentDoc = Jsoup.parse(parentHtml);
        Element parent = parentDoc.body();
        Document childDoc = Jsoup.parse(childHtml);

        Element div = childDoc.select("div").first();
        Element p = childDoc.select("p").first();
        Element appendTo1 = div.appendTo(parent);
        // removed other assertion

        Element appendTo2 = p.appendTo(div);
        // removed other assertion

        assertEquals("<div class=\"a\"></div>\n<div class=\"b\">\n <p>Two</p>\n</div>", parentDoc.body().html());
    }

@Test
    public void testAppendTo_4_oe() {
        String parentHtml = "<div class='a'></div>";
        String childHtml = "<div class='b'></div><p>Two</p>";

        Document parentDoc = Jsoup.parse(parentHtml);
        Element parent = parentDoc.body();
        Document childDoc = Jsoup.parse(childHtml);

        Element div = childDoc.select("div").first();
        Element p = childDoc.select("p").first();
        Element appendTo1 = div.appendTo(parent);
        // removed other assertion

        Element appendTo2 = p.appendTo(div);
        // removed other assertion

        // removed other assertion
        assertEquals("", childDoc.body().html()); // got moved out;
    }

@Test
    public void testNormalizesNbspInText_1_oe() {
        String escaped = "You can't always get what you&nbsp;want.";
        String withNbsp = "You can't always get what you want."; // there is an nbsp char in there
        Document doc = Jsoup.parse("<p>" + escaped);
        Element p = doc.select("p").first();
        assertEquals("You can't always get what you want.",p.text());// text is normalized assertEquals("<p>" + escaped + "</p>",p.outerHtml());// html / whole text keeps &nbsp;
    }

@Test
    public void testNormalizesNbspInText_2_oe() {
        String escaped = "You can't always get what you&nbsp;want.";
        String withNbsp = "You can't always get what you want."; // there is an nbsp char in there
        Document doc = Jsoup.parse("<p>" + escaped);
        Element p = doc.select("p").first();
        // removed other assertion
        assertEquals(withNbsp, p.textNodes().get(0).getWholeText());
    }

@Test
    public void testNormalizesNbspInText_3_oe() {
        String escaped = "You can't always get what you&nbsp;want.";
        String withNbsp = "You can't always get what you want."; // there is an nbsp char in there
        Document doc = Jsoup.parse("<p>" + escaped);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        assertEquals(160, withNbsp.charAt(29));
    }

@Test
    public void testNormalizesNbspInText_4_oe() {
        String escaped = "You can't always get what you&nbsp;want.";
        String withNbsp = "You can't always get what you want."; // there is an nbsp char in there
        Document doc = Jsoup.parse("<p>" + escaped);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element matched = doc.select("p:contains(get what you want)").first();
        assertEquals("p", matched.nodeName());
    }

@Test
    public void testNormalizesNbspInText_5_oe() {
        String escaped = "You can't always get what you&nbsp;want.";
        String withNbsp = "You can't always get what you want."; // there is an nbsp char in there
        Document doc = Jsoup.parse("<p>" + escaped);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element matched = doc.select("p:contains(get what you want)").first();
        // removed other assertion
        assertTrue(matched.is(":containsOwn(get what you want)"));
    }

@Test
    public void testNormalizesInvisiblesInText_1_oe() {
        String escaped = "This&shy;is&#x200b;one&shy;long&shy;word";
        String decoded = "This\u00ADis\u200Bone\u00ADlong\u00ADword"; // browser would not display those soft hyphens / other chars, so we don't want them in the text

        Document doc = Jsoup.parse("<p>" + escaped);
        Element p = doc.select("p").first();
        doc.outputSettings().charset("ascii"); // so that the outer html is easier to see with escaped invisibles
        assertEquals("Thisisonelongword",p.text());// text is normalized assertEquals("<p>" + escaped + "</p>",p.outerHtml());// html / whole text keeps &shy etc;
    }

@Test
    public void testNormalizesInvisiblesInText_2_oe() {
        String escaped = "This&shy;is&#x200b;one&shy;long&shy;word";
        String decoded = "This\u00ADis\u200Bone\u00ADlong\u00ADword"; // browser would not display those soft hyphens / other chars, so we don't want them in the text

        Document doc = Jsoup.parse("<p>" + escaped);
        Element p = doc.select("p").first();
        doc.outputSettings().charset("ascii"); // so that the outer html is easier to see with escaped invisibles
        // removed other assertion
        assertEquals(decoded, p.textNodes().get(0).getWholeText());
    }

@Test
    public void testNormalizesInvisiblesInText_3_oe() {
        String escaped = "This&shy;is&#x200b;one&shy;long&shy;word";
        String decoded = "This\u00ADis\u200Bone\u00ADlong\u00ADword"; // browser would not display those soft hyphens / other chars, so we don't want them in the text

        Document doc = Jsoup.parse("<p>" + escaped);
        Element p = doc.select("p").first();
        doc.outputSettings().charset("ascii"); // so that the outer html is easier to see with escaped invisibles
        // removed other assertion
        // removed other assertion

        Element matched = doc.select("p:contains(Thisisonelongword)").first(); // really just oneloneword, no invisibles
        assertEquals("p", matched.nodeName());
    }

@Test
    public void testNormalizesInvisiblesInText_4_oe() {
        String escaped = "This&shy;is&#x200b;one&shy;long&shy;word";
        String decoded = "This\u00ADis\u200Bone\u00ADlong\u00ADword"; // browser would not display those soft hyphens / other chars, so we don't want them in the text

        Document doc = Jsoup.parse("<p>" + escaped);
        Element p = doc.select("p").first();
        doc.outputSettings().charset("ascii"); // so that the outer html is easier to see with escaped invisibles
        // removed other assertion
        // removed other assertion

        Element matched = doc.select("p:contains(Thisisonelongword)").first(); // really just oneloneword, no invisibles
        // removed other assertion
        assertTrue(matched.is(":containsOwn(Thisisonelongword)"));
    }

@Test
    public void testRemoveBeforeIndex_1_oe() {
        Document doc = Jsoup.parse(
            "<html><body><div><p>before1</p><p>before2</p><p>XXX</p><p>after1</p><p>after2</p></div></body></html>",
            "");
        Element body = doc.select("body").first();
        Elements elems = body.select("p:matchesOwn(XXX)");
        Element xElem = elems.first();
        Elements beforeX = xElem.parent().getElementsByIndexLessThan(xElem.elementSiblingIndex());

        for (Element p : beforeX) {
            p.remove();
        }

        assertEquals("<body><div><p>XXX</p><p>after1</p><p>after2</p></div></body>", TextUtil.stripNewlines(body.outerHtml()));
    }

@Test
    public void testRemoveAfterIndex_1_oe() {
        Document doc2 = Jsoup.parse(
            "<html><body><div><p>before1</p><p>before2</p><p>XXX</p><p>after1</p><p>after2</p></div></body></html>",
            "");
        Element body = doc2.select("body").first();
        Elements elems = body.select("p:matchesOwn(XXX)");
        Element xElem = elems.first();
        Elements afterX = xElem.parent().getElementsByIndexGreaterThan(xElem.elementSiblingIndex());

        for (Element p : afterX) {
            p.remove();
        }

        assertEquals("<body><div><p>before1</p><p>before2</p><p>XXX</p></div></body>", TextUtil.stripNewlines(body.outerHtml()));
    }

@Test
    public void whiteSpaceClassElement_1_oe() {
        Tag tag = Tag.valueOf("a");
        Attributes attribs = new Attributes();
        Element el = new Element(tag, "", attribs);

        attribs.put("class", "abc ");
        boolean hasClass = el.hasClass("ab");
        assertFalse(hasClass);
    }

@Test
    public void testNextElementSiblingAfterClone_1_oe() {
        // via https://github.com/jhy/jsoup/issues/951
        String html = "<!DOCTYPE html><html lang=\"en\"><head></head><body><div>Initial element</div></body></html>";
        String expectedText = "New element";
        String cloneExpect = "New element in clone";

        Document original = Jsoup.parse(html);
        Document clone = original.clone();

        Element originalElement = original.body().child(0);
        originalElement.after("<div>" + expectedText + "</div>");
        Element originalNextElementSibling = originalElement.nextElementSibling();
        Element originalNextSibling = (Element) originalElement.nextSibling();
        assertEquals(expectedText, originalNextElementSibling.text());
    }

@Test
    public void testNextElementSiblingAfterClone_2_oe() {
        // via https://github.com/jhy/jsoup/issues/951
        String html = "<!DOCTYPE html><html lang=\"en\"><head></head><body><div>Initial element</div></body></html>";
        String expectedText = "New element";
        String cloneExpect = "New element in clone";

        Document original = Jsoup.parse(html);
        Document clone = original.clone();

        Element originalElement = original.body().child(0);
        originalElement.after("<div>" + expectedText + "</div>");
        Element originalNextElementSibling = originalElement.nextElementSibling();
        Element originalNextSibling = (Element) originalElement.nextSibling();
        // removed other assertion
        assertEquals(expectedText, originalNextSibling.text());
    }

@Test
    public void testNextElementSiblingAfterClone_3_oe() {
        // via https://github.com/jhy/jsoup/issues/951
        String html = "<!DOCTYPE html><html lang=\"en\"><head></head><body><div>Initial element</div></body></html>";
        String expectedText = "New element";
        String cloneExpect = "New element in clone";

        Document original = Jsoup.parse(html);
        Document clone = original.clone();

        Element originalElement = original.body().child(0);
        originalElement.after("<div>" + expectedText + "</div>");
        Element originalNextElementSibling = originalElement.nextElementSibling();
        Element originalNextSibling = (Element) originalElement.nextSibling();
        // removed other assertion
        // removed other assertion

        Element cloneElement = clone.body().child(0);
        cloneElement.after("<div>" + cloneExpect + "</div>");
        Element cloneNextElementSibling = cloneElement.nextElementSibling();
        Element cloneNextSibling = (Element) cloneElement.nextSibling();
        assertEquals(cloneExpect, cloneNextElementSibling.text());
    }

@Test
    public void testNextElementSiblingAfterClone_4_oe() {
        // via https://github.com/jhy/jsoup/issues/951
        String html = "<!DOCTYPE html><html lang=\"en\"><head></head><body><div>Initial element</div></body></html>";
        String expectedText = "New element";
        String cloneExpect = "New element in clone";

        Document original = Jsoup.parse(html);
        Document clone = original.clone();

        Element originalElement = original.body().child(0);
        originalElement.after("<div>" + expectedText + "</div>");
        Element originalNextElementSibling = originalElement.nextElementSibling();
        Element originalNextSibling = (Element) originalElement.nextSibling();
        // removed other assertion
        // removed other assertion

        Element cloneElement = clone.body().child(0);
        cloneElement.after("<div>" + cloneExpect + "</div>");
        Element cloneNextElementSibling = cloneElement.nextElementSibling();
        Element cloneNextSibling = (Element) cloneElement.nextSibling();
        // removed other assertion
        assertEquals(cloneExpect, cloneNextSibling.text());
    }

@Test
    public void testRemovingEmptyClassAttributeWhenLastClassRemoved_1_oe() {
        // https://github.com/jhy/jsoup/issues/947
        Document doc = Jsoup.parse("<img class=\"one two\" />");
        Element img = doc.select("img").first();
        img.removeClass("one");
        img.removeClass("two");
        assertFalse(doc.body().html().contains("class=\"\""));
    }

@Test
    public void booleanAttributeOutput_1_oe() {
        Document doc = Jsoup.parse("<img src=foo noshade='' nohref async=async autofocus=false>");
        Element img = doc.selectFirst("img");

        assertEquals("<img src=\"foo\" noshade nohref async autofocus=\"false\">", img.outerHtml());
    }

@Test
    public void textHasSpaceAfterBlockTags_1_oe() {
        Document doc = Jsoup.parse("<div>One</div>Two");
        assertEquals("One Two", doc.text());
    }

@Test
    public void textHasSpaceBetweenDivAndCenterTags_1_oe() {
        Document doc = Jsoup.parse("<div>One</div><div>Two</div><center>Three</center><center>Four</center>");
        assertEquals("One Two Three Four", doc.text());
    }

@Test
    public void testNextElementSiblings_1_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        assertNotNull(elementSiblings);
    }

@Test
    public void testNextElementSiblings_2_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        assertEquals(2, elementSiblings.size());
    }

@Test
    public void testNextElementSiblings_3_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        assertEquals("b", elementSiblings.get(0).id());
    }

@Test
    public void testNextElementSiblings_4_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", elementSiblings.get(1).id());
    }

@Test
    public void testNextElementSiblings_5_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("b");
        List<Element> elementSiblings1 = element1.nextElementSiblings();
        assertNotNull(elementSiblings1);
    }

@Test
    public void testNextElementSiblings_6_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("b");
        List<Element> elementSiblings1 = element1.nextElementSiblings();
        // removed other assertion
        assertEquals(1, elementSiblings1.size());
    }

@Test
    public void testNextElementSiblings_7_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("b");
        List<Element> elementSiblings1 = element1.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        assertEquals("c", elementSiblings1.get(0).id());
    }

@Test
    public void testNextElementSiblings_8_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("b");
        List<Element> elementSiblings1 = element1.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element2 = doc.getElementById("c");
        List<Element> elementSiblings2 = element2.nextElementSiblings();
        assertEquals(0, elementSiblings2.size());
    }

@Test
    public void testNextElementSiblings_9_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("b");
        List<Element> elementSiblings1 = element1.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element2 = doc.getElementById("c");
        List<Element> elementSiblings2 = element2.nextElementSiblings();
        // removed other assertion

        Element ul = doc.getElementById("ul");
        List<Element> elementSiblings3 = ul.nextElementSiblings();
        assertNotNull(elementSiblings3);
    }

@Test
    public void testNextElementSiblings_10_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("b");
        List<Element> elementSiblings1 = element1.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element2 = doc.getElementById("c");
        List<Element> elementSiblings2 = element2.nextElementSiblings();
        // removed other assertion

        Element ul = doc.getElementById("ul");
        List<Element> elementSiblings3 = ul.nextElementSiblings();
        // removed other assertion
        assertEquals(1, elementSiblings3.size());
    }

@Test
    public void testNextElementSiblings_11_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("b");
        List<Element> elementSiblings1 = element1.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element2 = doc.getElementById("c");
        List<Element> elementSiblings2 = element2.nextElementSiblings();
        // removed other assertion

        Element ul = doc.getElementById("ul");
        List<Element> elementSiblings3 = ul.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        assertEquals("div", elementSiblings3.get(0).id());
    }

@Test
    public void testNextElementSiblings_12_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul> Not An Element but a node" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("a");
        Elements elementSiblings = element.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("b");
        List<Element> elementSiblings1 = element1.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element2 = doc.getElementById("c");
        List<Element> elementSiblings2 = element2.nextElementSiblings();
        // removed other assertion

        Element ul = doc.getElementById("ul");
        List<Element> elementSiblings3 = ul.nextElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element div = doc.getElementById("div");
        List<Element> elementSiblings4 = div.nextElementSiblings();
        assertEquals(0, elementSiblings4.size());
    }

@Test
    public void testPreviousElementSiblings_1_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul>" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("b");
        Elements elementSiblings = element.previousElementSiblings();
        assertNotNull(elementSiblings);
    }

@Test
    public void testPreviousElementSiblings_2_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul>" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("b");
        Elements elementSiblings = element.previousElementSiblings();
        // removed other assertion
        assertEquals(1, elementSiblings.size());
    }

@Test
    public void testPreviousElementSiblings_3_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul>" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("b");
        Elements elementSiblings = element.previousElementSiblings();
        // removed other assertion
        // removed other assertion
        assertEquals("a", elementSiblings.get(0).id());
    }

@Test
    public void testPreviousElementSiblings_4_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul>" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("b");
        Elements elementSiblings = element.previousElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("a");
        List<Element> elementSiblings1 = element1.previousElementSiblings();
        assertEquals(0, elementSiblings1.size());
    }

@Test
    public void testPreviousElementSiblings_5_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul>" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("b");
        Elements elementSiblings = element.previousElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("a");
        List<Element> elementSiblings1 = element1.previousElementSiblings();
        // removed other assertion

        Element element2 = doc.getElementById("c");
        List<Element> elementSiblings2 = element2.previousElementSiblings();
        assertNotNull(elementSiblings2);
    }

@Test
    public void testPreviousElementSiblings_6_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul>" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("b");
        Elements elementSiblings = element.previousElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("a");
        List<Element> elementSiblings1 = element1.previousElementSiblings();
        // removed other assertion

        Element element2 = doc.getElementById("c");
        List<Element> elementSiblings2 = element2.previousElementSiblings();
        // removed other assertion
        assertEquals(2, elementSiblings2.size());
    }

@Test
    public void testPreviousElementSiblings_7_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul>" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("b");
        Elements elementSiblings = element.previousElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("a");
        List<Element> elementSiblings1 = element1.previousElementSiblings();
        // removed other assertion

        Element element2 = doc.getElementById("c");
        List<Element> elementSiblings2 = element2.previousElementSiblings();
        // removed other assertion
        // removed other assertion
        assertEquals("b", elementSiblings2.get(0).id());
    }

@Test
    public void testPreviousElementSiblings_8_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul>" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("b");
        Elements elementSiblings = element.previousElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("a");
        List<Element> elementSiblings1 = element1.previousElementSiblings();
        // removed other assertion

        Element element2 = doc.getElementById("c");
        List<Element> elementSiblings2 = element2.previousElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a", elementSiblings2.get(1).id());
    }

@Test
    public void testPreviousElementSiblings_9_oe() {
        Document doc = Jsoup.parse("<ul id='ul'>" +
            "<li id='a'>a</li>" +
            "<li id='b'>b</li>" +
            "<li id='c'>c</li>" +
            "</ul>" +
            "<div id='div'>" +
            "<li id='d'>d</li>" +
            "</div>");

        Element element = doc.getElementById("b");
        Elements elementSiblings = element.previousElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element element1 = doc.getElementById("a");
        List<Element> elementSiblings1 = element1.previousElementSiblings();
        // removed other assertion

        Element element2 = doc.getElementById("c");
        List<Element> elementSiblings2 = element2.previousElementSiblings();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Element ul = doc.getElementById("ul");
        List<Element> elementSiblings3 = ul.previousElementSiblings();
        assertEquals(0, elementSiblings3.size());
    }

@Test
    public void testClearAttributes_1_oe() {
        Element el = new Element("a").attr("href", "http://example.com").text("Hello");
        assertEquals("<a href=\"http://example.com\">Hello</a>", el.outerHtml());
    }

@Test
    public void testClearAttributes_2_oe() {
        Element el = new Element("a").attr("href", "http://example.com").text("Hello");
        // removed other assertion
        Element el2 = el.clearAttributes(); // really just force testing the return type is Element
        assertSame(el, el2);
    }

@Test
    public void testClearAttributes_3_oe() {
        Element el = new Element("a").attr("href", "http://example.com").text("Hello");
        // removed other assertion
        Element el2 = el.clearAttributes(); // really just force testing the return type is Element
        // removed other assertion
        assertEquals("<a>Hello</a>", el2.outerHtml());
    }

@Test
    public void testRemoveAttr_1_oe() {
        Element el = new Element("a")
            .attr("href", "http://example.com")
            .attr("id", "1")
            .text("Hello");
        assertEquals("<a href=\"http://example.com\" id=\"1\">Hello</a>", el.outerHtml());
    }

@Test
    public void testRemoveAttr_2_oe() {
        Element el = new Element("a")
            .attr("href", "http://example.com")
            .attr("id", "1")
            .text("Hello");
        // removed other assertion
        Element el2 = el.removeAttr("href"); // really just force testing the return type is Element
        assertSame(el, el2);
    }

@Test
    public void testRemoveAttr_3_oe() {
        Element el = new Element("a")
            .attr("href", "http://example.com")
            .attr("id", "1")
            .text("Hello");
        // removed other assertion
        Element el2 = el.removeAttr("href"); // really just force testing the return type is Element
        // removed other assertion
        assertEquals("<a id=\"1\">Hello</a>", el2.outerHtml());
    }

@Test
    public void testRoot_1_oe() {
        Element el = new Element("a");
        el.append("<span>Hello</span>");
        assertEquals("<a><span>Hello</span></a>", el.outerHtml());
    }

@Test
    public void testRoot_2_oe() {
        Element el = new Element("a");
        el.append("<span>Hello</span>");
        // removed other assertion
        Element span = el.selectFirst("span");
        assertNotNull(span);
    }

@Test
    public void testRoot_3_oe() {
        Element el = new Element("a");
        el.append("<span>Hello</span>");
        // removed other assertion
        Element span = el.selectFirst("span");
        // removed other assertion
        Element el2 = span.root();
        assertSame(el, el2);
    }

@Test
    public void testRoot_4_oe() {
        Element el = new Element("a");
        el.append("<span>Hello</span>");
        // removed other assertion
        Element span = el.selectFirst("span");
        // removed other assertion
        Element el2 = span.root();
        // removed other assertion

        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        assertSame(doc, div.root());
    }

@Test
    public void testRoot_5_oe() {
        Element el = new Element("a");
        el.append("<span>Hello</span>");
        // removed other assertion
        Element span = el.selectFirst("span");
        // removed other assertion
        Element el2 = span.root();
        // removed other assertion

        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        // removed other assertion
        assertSame(doc, div.ownerDocument());
    }

@Test
    public void testTraverse_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        assertNotNull(div);
    }

@Test
    public void testTraverse_2_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        // removed other assertion
        final AtomicInteger counter = new AtomicInteger(0);

        Element div2 = div.traverse(new NodeVisitor() {

            @Override
            public void head(Node node, int depth) {
                counter.incrementAndGet();
            }

            @Override
            public void tail(Node node, int depth) {

            }
        });

        assertEquals(7, counter.get());
    }

@Test
    public void testTraverse_3_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        // removed other assertion
        final AtomicInteger counter = new AtomicInteger(0);

        Element div2 = div.traverse(new NodeVisitor() {

            @Override
            public void head(Node node, int depth) {
                counter.incrementAndGet();
            }

            @Override
            public void tail(Node node, int depth) {

            }
        });

        // removed other assertion
        assertEquals(div2, div);
    }

@Test void testTraverseLambda_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        assertNotNull(div);
        }

@Test void testTraverseLambda_2_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        // removed other assertion
        final AtomicInteger counter = new AtomicInteger(0);

        Element div2 = div.traverse((node, depth) -> counter.incrementAndGet());

        assertEquals(7, counter.get());
        }

@Test void testTraverseLambda_3_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        // removed other assertion
        final AtomicInteger counter = new AtomicInteger(0);

        Element div2 = div.traverse((node, depth) -> counter.incrementAndGet());

        // removed other assertion
        assertEquals(div2, div);
        }

@Test
    public void testFilterCallReturnsElement_1_oe() {
        // doesn't actually test the filter so much as the return type for Element. See node.nodeFilter for an actual test
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        assertNotNull(div);
    }

@Test
    public void testFilterCallReturnsElement_2_oe() {
        // doesn't actually test the filter so much as the return type for Element. See node.nodeFilter for an actual test
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three");
        Element div = doc.selectFirst("div");
        // removed other assertion
        Element div2 = div.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                return FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        });

        assertSame(div, div2);
    }

@Test void testFilterAsLambda_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p id=2>Two<p>Three");
        doc.filter((node, depth) -> node.attr("id").equals("2")
            ? NodeFilter.FilterResult.REMOVE
            : NodeFilter.FilterResult.CONTINUE);

        assertEquals("<div><p>One</p><p>Three</p></div>", TextUtil.stripNewlines(doc.body().html()));
        }

@Test void testForEach_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><div>There</div><div id=1>Gone<p></div>");
        doc.forEach(el -> {
            if (el.id().equals("1"))
                el.remove();
            else if (el.text().equals("There")) {
                el.text("There Now");
                el.append("<p>Another</p>");
            }
        });
        assertEquals("<div><p>Hello</p></div><div>There Now<p>Another</p></div>", TextUtil.stripNewlines(doc.body().html()));
        }

@Test
    public void doesntDeleteZWJWhenNormalizingText_1_oe() {
        String text = "\uD83D\uDC69\u200D\uD83D\uDCBB\uD83E\uDD26\uD83C\uDFFB\u200D\u2642\uFE0F";

        Document doc = Jsoup.parse("<p>" + text + "</p><div>One&zwj;Two</div>");
        Element p = doc.selectFirst("p");
        Element d = doc.selectFirst("div");

        assertEquals(12, p.text().length());
    }

@Test
    public void doesntDeleteZWJWhenNormalizingText_2_oe() {
        String text = "\uD83D\uDC69\u200D\uD83D\uDCBB\uD83E\uDD26\uD83C\uDFFB\u200D\u2642\uFE0F";

        Document doc = Jsoup.parse("<p>" + text + "</p><div>One&zwj;Two</div>");
        Element p = doc.selectFirst("p");
        Element d = doc.selectFirst("div");

        // removed other assertion
        assertEquals(text, p.text());
    }

@Test
    public void doesntDeleteZWJWhenNormalizingText_3_oe() {
        String text = "\uD83D\uDC69\u200D\uD83D\uDCBB\uD83E\uDD26\uD83C\uDFFB\u200D\u2642\uFE0F";

        Document doc = Jsoup.parse("<p>" + text + "</p><div>One&zwj;Two</div>");
        Element p = doc.selectFirst("p");
        Element d = doc.selectFirst("div");

        // removed other assertion
        // removed other assertion
        assertEquals(7, d.text().length());
    }

@Test
    public void doesntDeleteZWJWhenNormalizingText_4_oe() {
        String text = "\uD83D\uDC69\u200D\uD83D\uDCBB\uD83E\uDD26\uD83C\uDFFB\u200D\u2642\uFE0F";

        Document doc = Jsoup.parse("<p>" + text + "</p><div>One&zwj;Two</div>");
        Element p = doc.selectFirst("p");
        Element d = doc.selectFirst("div");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("One\u200DTwo", d.text());
    }

@Test
    public void doesntDeleteZWJWhenNormalizingText_5_oe() {
        String text = "\uD83D\uDC69\u200D\uD83D\uDCBB\uD83E\uDD26\uD83C\uDFFB\u200D\u2642\uFE0F";

        Document doc = Jsoup.parse("<p>" + text + "</p><div>One&zwj;Two</div>");
        Element p = doc.selectFirst("p");
        Element d = doc.selectFirst("div");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Element found = doc.selectFirst("div:contains(One\u200DTwo)");
        assertTrue(found.hasSameValue(d));
    }

@Test
    public void testReparentSeperateNodes_1_oe() {
        String html = "<div><p>One<p>Two";
        Document doc = Jsoup.parse(html);
        Element new1 = new Element("p").text("Three");
        Element new2 = new Element("p").text("Four");

        doc.body().insertChildren(-1, new1, new2);
        assertEquals("<div><p>One</p><p>Two</p></div><p>Three</p><p>Four</p>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testReparentSeperateNodes_2_oe() {
        String html = "<div><p>One<p>Two";
        Document doc = Jsoup.parse(html);
        Element new1 = new Element("p").text("Three");
        Element new2 = new Element("p").text("Four");

        doc.body().insertChildren(-1, new1, new2);
        // removed other assertion

        // note that these get moved from the above - as not copied
        doc.body().insertChildren(0, new1, new2);
        assertEquals("<p>Three</p><p>Four</p><div><p>One</p><p>Two</p></div>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testReparentSeperateNodes_3_oe() {
        String html = "<div><p>One<p>Two";
        Document doc = Jsoup.parse(html);
        Element new1 = new Element("p").text("Three");
        Element new2 = new Element("p").text("Four");

        doc.body().insertChildren(-1, new1, new2);
        // removed other assertion

        // note that these get moved from the above - as not copied
        doc.body().insertChildren(0, new1, new2);
        // removed other assertion

        doc.body().insertChildren(0, new2.clone(), new1.clone());
        assertEquals("<p>Four</p><p>Three</p><p>Three</p><p>Four</p><div><p>One</p><p>Two</p></div>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testReparentSeperateNodes_4_oe() {
        String html = "<div><p>One<p>Two";
        Document doc = Jsoup.parse(html);
        Element new1 = new Element("p").text("Three");
        Element new2 = new Element("p").text("Four");

        doc.body().insertChildren(-1, new1, new2);
        // removed other assertion

        // note that these get moved from the above - as not copied
        doc.body().insertChildren(0, new1, new2);
        // removed other assertion

        doc.body().insertChildren(0, new2.clone(), new1.clone());
        // removed other assertion

        // shifted to end
        doc.body().appendChild(new1);
        assertEquals("<p>Four</p><p>Three</p><p>Four</p><div><p>One</p><p>Two</p></div><p>Three</p>", TextUtil.stripNewlines(doc.body().html()));
    }

@Test
    public void testNotActuallyAReparent_1_oe() {
        // prep
        String html = "<div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element new1 = new Element("p").text("One");
        Element new2 = new Element("p").text("Two");
        div.addChildren(new1, new2);

        assertEquals("<div><p>One</p><p>Two</p></div>", TextUtil.stripNewlines(div.outerHtml()));
    }

@Test
    public void testNotActuallyAReparent_2_oe() {
        // prep
        String html = "<div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element new1 = new Element("p").text("One");
        Element new2 = new Element("p").text("Two");
        div.addChildren(new1, new2);

        // removed other assertion

        // and the issue setup:
        Element new3 = new Element("p").text("Three");
        Element wrap = new Element("nav");
        wrap.addChildren(0, new1, new3);

        assertEquals("<nav><p>One</p><p>Three</p></nav>", TextUtil.stripNewlines(wrap.outerHtml()));
    }

@Test
    public void testNotActuallyAReparent_3_oe() {
        // prep
        String html = "<div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element new1 = new Element("p").text("One");
        Element new2 = new Element("p").text("Two");
        div.addChildren(new1, new2);

        // removed other assertion

        // and the issue setup:
        Element new3 = new Element("p").text("Three");
        Element wrap = new Element("nav");
        wrap.addChildren(0, new1, new3);

        // removed other assertion
        div.addChildren(wrap);
        // now should be that One moved into wrap, leaving Two in div.

        assertEquals("<div><p>Two</p><nav><p>One</p><p>Three</p></nav></div>", TextUtil.stripNewlines(div.outerHtml()));
    }

@Test
    public void testNotActuallyAReparent_4_oe() {
        // prep
        String html = "<div>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        Element new1 = new Element("p").text("One");
        Element new2 = new Element("p").text("Two");
        div.addChildren(new1, new2);

        // removed other assertion

        // and the issue setup:
        Element new3 = new Element("p").text("Three");
        Element wrap = new Element("nav");
        wrap.addChildren(0, new1, new3);

        // removed other assertion
        div.addChildren(wrap);
        // now should be that One moved into wrap, leaving Two in div.

        // removed other assertion
        assertEquals("<div><p>Two</p><nav><p>One</p><p>Three</p></nav></div>", TextUtil.stripNewlines(div.outerHtml()));
    }

@Test
    public void testChildSizeWithMixedContent_1_oe() {
        Document doc = Jsoup.parse("<table><tbody>\n<tr>\n<td>15:00</td>\n<td>sport</td>\n</tr>\n</tbody></table>");
        Element row = doc.selectFirst("table tbody tr");
        assertEquals(2, row.childrenSize());
    }

@Test
    public void testChildSizeWithMixedContent_2_oe() {
        Document doc = Jsoup.parse("<table><tbody>\n<tr>\n<td>15:00</td>\n<td>sport</td>\n</tr>\n</tbody></table>");
        Element row = doc.selectFirst("table tbody tr");
        // removed other assertion
        assertEquals(5, row.childNodeSize());
    }

@Test
    public void isBlock_1_oe() {
        String html = "<div><p><span>Hello</span>";
        Document doc = Jsoup.parse(html);
        assertTrue(doc.selectFirst("div").isBlock());
    }

@Test
    public void isBlock_2_oe() {
        String html = "<div><p><span>Hello</span>";
        Document doc = Jsoup.parse(html);
        // removed other assertion
        assertTrue(doc.selectFirst("p").isBlock());
    }

@Test
    public void isBlock_3_oe() {
        String html = "<div><p><span>Hello</span>";
        Document doc = Jsoup.parse(html);
        // removed other assertion
        // removed other assertion
        assertFalse(doc.selectFirst("span").isBlock());
    }

@Test
    public void testScriptTextHtmlSetAsData_1_oe() {
        String src = "var foo = 5 < 2;\nvar bar = 1 && 2;";
        String html = "<script>" + src + "</script>";
        Document doc = Jsoup.parse(html);
        Element el = doc.selectFirst("script");
        assertNotNull(el);
    }

@Test
    public void testScriptTextHtmlSetAsData_2_oe() {
        String src = "var foo = 5 < 2;\nvar bar = 1 && 2;";
        String html = "<script>" + src + "</script>";
        Document doc = Jsoup.parse(html);
        Element el = doc.selectFirst("script");
        // removed other assertion

        validateScriptContents(src, el);

        src = "var foo = 4 < 2;\nvar bar > 1 && 2;";
        el.html(src);
        validateScriptContents(src, el);

        // special case for .text (in HTML; in XML will just be regular text)
        el.text(src);
        validateScriptContents(src, el);

        // XML, no special treatment, get escaped correctly
        Document xml = Parser.xmlParser().parseInput(html, "");
        Element xEl = xml.selectFirst("script");
        assertNotNull(xEl);
    }

@Test
    public void testScriptTextHtmlSetAsData_3_oe() {
        String src = "var foo = 5 < 2;\nvar bar = 1 && 2;";
        String html = "<script>" + src + "</script>";
        Document doc = Jsoup.parse(html);
        Element el = doc.selectFirst("script");
        // removed other assertion

        validateScriptContents(src, el);

        src = "var foo = 4 < 2;\nvar bar > 1 && 2;";
        el.html(src);
        validateScriptContents(src, el);

        // special case for .text (in HTML; in XML will just be regular text)
        el.text(src);
        validateScriptContents(src, el);

        // XML, no special treatment, get escaped correctly
        Document xml = Parser.xmlParser().parseInput(html, "");
        Element xEl = xml.selectFirst("script");
        // removed other assertion
        src = "var foo = 5 < 2;\nvar bar = 1 && 2;";
        String escaped = "var foo = 5 &lt; 2;\nvar bar = 1 &amp;&amp; 2;";
        validateXmlScriptContents(xEl);
        xEl.text(src);
        validateXmlScriptContents(xEl);
        xEl.html(src);
        validateXmlScriptContents(xEl);

        assertEquals("<script>var foo = 4 < 2;\nvar bar > 1 && 2;</script>", el.outerHtml());
    }

@Test
    public void testScriptTextHtmlSetAsData_4_oe() {
        String src = "var foo = 5 < 2;\nvar bar = 1 && 2;";
        String html = "<script>" + src + "</script>";
        Document doc = Jsoup.parse(html);
        Element el = doc.selectFirst("script");
        // removed other assertion

        validateScriptContents(src, el);

        src = "var foo = 4 < 2;\nvar bar > 1 && 2;";
        el.html(src);
        validateScriptContents(src, el);

        // special case for .text (in HTML; in XML will just be regular text)
        el.text(src);
        validateScriptContents(src, el);

        // XML, no special treatment, get escaped correctly
        Document xml = Parser.xmlParser().parseInput(html, "");
        Element xEl = xml.selectFirst("script");
        // removed other assertion
        src = "var foo = 5 < 2;\nvar bar = 1 && 2;";
        String escaped = "var foo = 5 &lt; 2;\nvar bar = 1 &amp;&amp; 2;";
        validateXmlScriptContents(xEl);
        xEl.text(src);
        validateXmlScriptContents(xEl);
        xEl.html(src);
        validateXmlScriptContents(xEl);

        // removed other assertion
        assertEquals("<script>" + escaped + "</script>", xEl.outerHtml()); // escaped in xml as no special treatment;
    }

@Test
    public void testShallowCloneToString_1_oe() {
        // https://github.com/jhy/jsoup/issues/1410
        Document doc = Jsoup.parse("<p><i>Hello</i></p>");
        Element p = doc.selectFirst("p");
        Element i = doc.selectFirst("i");
        String pH = p.shallowClone().toString();
        String iH = i.shallowClone().toString();

        assertEquals("<p></p>",pH);// shallow,so no I assertEquals("<i></i>",iH);
    }

@Test
    public void testShallowCloneToString_2_oe() {
        // https://github.com/jhy/jsoup/issues/1410
        Document doc = Jsoup.parse("<p><i>Hello</i></p>");
        Element p = doc.selectFirst("p");
        Element i = doc.selectFirst("i");
        String pH = p.shallowClone().toString();
        String iH = i.shallowClone().toString();

        // removed other assertion

        assertEquals(p.outerHtml(), p.toString());
    }

@Test
    public void testShallowCloneToString_3_oe() {
        // https://github.com/jhy/jsoup/issues/1410
        Document doc = Jsoup.parse("<p><i>Hello</i></p>");
        Element p = doc.selectFirst("p");
        Element i = doc.selectFirst("i");
        String pH = p.shallowClone().toString();
        String iH = i.shallowClone().toString();

        // removed other assertion

        // removed other assertion
        assertEquals(i.outerHtml(), i.toString());
    }

@Test
    public void styleHtmlRoundTrips_1_oe() {
        String styleContents = "foo < bar > qux {color:white;}";
        String html = "<head><style>" + styleContents + "</style></head>";
        Document doc = Jsoup.parse(html);

        Element head = doc.head();
        Element style = head.selectFirst("style");
        assertNotNull(style);
    }

@Test
    public void styleHtmlRoundTrips_2_oe() {
        String styleContents = "foo < bar > qux {color:white;}";
        String html = "<head><style>" + styleContents + "</style></head>";
        Document doc = Jsoup.parse(html);

        Element head = doc.head();
        Element style = head.selectFirst("style");
        // removed other assertion
        assertEquals(styleContents, style.html());
    }

@Test
    public void styleHtmlRoundTrips_3_oe() {
        String styleContents = "foo < bar > qux {color:white;}";
        String html = "<head><style>" + styleContents + "</style></head>";
        Document doc = Jsoup.parse(html);

        Element head = doc.head();
        Element style = head.selectFirst("style");
        // removed other assertion
        // removed other assertion
        style.html(styleContents);
        assertEquals(styleContents, style.html());
    }

@Test
    public void styleHtmlRoundTrips_4_oe() {
        String styleContents = "foo < bar > qux {color:white;}";
        String html = "<head><style>" + styleContents + "</style></head>";
        Document doc = Jsoup.parse(html);

        Element head = doc.head();
        Element style = head.selectFirst("style");
        // removed other assertion
        // removed other assertion
        style.html(styleContents);
        // removed other assertion
        assertEquals("", style.text());
    }

@Test
    public void styleHtmlRoundTrips_5_oe() {
        String styleContents = "foo < bar > qux {color:white;}";
        String html = "<head><style>" + styleContents + "</style></head>";
        Document doc = Jsoup.parse(html);

        Element head = doc.head();
        Element style = head.selectFirst("style");
        // removed other assertion
        // removed other assertion
        style.html(styleContents);
        // removed other assertion
        // removed other assertion
        style.text(styleContents); // pushes the HTML, not the Text
        assertEquals("", style.text());
    }

@Test
    public void styleHtmlRoundTrips_6_oe() {
        String styleContents = "foo < bar > qux {color:white;}";
        String html = "<head><style>" + styleContents + "</style></head>";
        Document doc = Jsoup.parse(html);

        Element head = doc.head();
        Element style = head.selectFirst("style");
        // removed other assertion
        // removed other assertion
        style.html(styleContents);
        // removed other assertion
        // removed other assertion
        style.text(styleContents); // pushes the HTML, not the Text
        // removed other assertion
        assertEquals(styleContents, style.html());
    }

@Test
    public void moveChildren_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div><div></div>");
        Elements divs = doc.select("div");
        Element a = divs.get(0);
        Element b = divs.get(1);

        b.insertChildren(-1, a.childNodes());

        assertEquals("<div></div>\n<div>\n <p>One</p>\n <p>Two</p>\n <p>Three</p>\n</div>",doc.body().html());
    }

@Test
    public void moveChildrenToOuter_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div><div></div>");
        Elements divs = doc.select("div");
        Element a = divs.get(0);
        Element b = doc.body();

        b.insertChildren(-1, a.childNodes());

        assertEquals("<div></div>\n<div></div>\n<p>One</p>\n<p>Two</p>\n<p>Three</p>",doc.body().html());
    }

@Test
    public void appendChildren_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div><div><p>Four</div>");
        Elements divs = doc.select("div");
        Element a = divs.get(0);
        Element b = divs.get(1);

        b.appendChildren(a.childNodes());

        assertEquals("<div></div>\n<div>\n <p>Four</p>\n <p>One</p>\n <p>Two</p>\n <p>Three</p>\n</div>",doc.body().html());
    }

@Test
    public void prependChildren_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div><div><p>Four</div>");
        Elements divs = doc.select("div");
        Element a = divs.get(0);
        Element b = divs.get(1);

        b.prependChildren(a.childNodes());

        assertEquals("<div></div>\n<div>\n <p>One</p>\n <p>Two</p>\n <p>Three</p>\n <p>Four</p>\n</div>",doc.body().html());
    }

@Test
    public void loopMoveChildren_1_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div><div><p>Four</div>");
        Elements divs = doc.select("div");
        Element a = divs.get(0);
        Element b = divs.get(1);

        Element outer = b.parent();
        assertNotNull(outer);
    }

@Test
    public void loopMoveChildren_2_oe() {
        Document doc = Jsoup.parse("<div><p>One<p>Two<p>Three</div><div><p>Four</div>");
        Elements divs = doc.select("div");
        Element a = divs.get(0);
        Element b = divs.get(1);

        Element outer = b.parent();
        // removed other assertion
        for (Node node : a.childNodes()) {
            outer.appendChild(node);
        }

        assertEquals("<div></div>\n<div>\n <p>Four</p>\n</div>\n<p>One</p>\n<p>Two</p>\n<p>Three</p>",doc.body().html());
    }

@Test
    public void accessorsDoNotVivifyAttributes_1_oe() throws NoSuchFieldException, IllegalAccessException {
        // internally, we don't want to create empty Attribute objects unless actually used for something
        Document doc = Jsoup.parse("<div><p><a href=foo>One</a>");
        Element div = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        Element a = doc.selectFirst("a");

        // should not create attributes
        assertEquals("", div.attr("href"));
    }

@Test
    public void accessorsDoNotVivifyAttributes_2_oe() throws NoSuchFieldException, IllegalAccessException {
        // internally, we don't want to create empty Attribute objects unless actually used for something
        Document doc = Jsoup.parse("<div><p><a href=foo>One</a>");
        Element div = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        Element a = doc.selectFirst("a");

        // should not create attributes
        // removed other assertion
        p.removeAttr("href");

        Elements hrefs = doc.select("[href]");
        assertEquals(1, hrefs.size());
    }

@Test
    public void accessorsDoNotVivifyAttributes_3_oe() throws NoSuchFieldException, IllegalAccessException {
        // internally, we don't want to create empty Attribute objects unless actually used for something
        Document doc = Jsoup.parse("<div><p><a href=foo>One</a>");
        Element div = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        Element a = doc.selectFirst("a");

        // should not create attributes
        // removed other assertion
        p.removeAttr("href");

        Elements hrefs = doc.select("[href]");
        // removed other assertion

        assertFalse(div.hasAttributes());
    }

@Test
    public void accessorsDoNotVivifyAttributes_4_oe() throws NoSuchFieldException, IllegalAccessException {
        // internally, we don't want to create empty Attribute objects unless actually used for something
        Document doc = Jsoup.parse("<div><p><a href=foo>One</a>");
        Element div = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        Element a = doc.selectFirst("a");

        // should not create attributes
        // removed other assertion
        p.removeAttr("href");

        Elements hrefs = doc.select("[href]");
        // removed other assertion

        // removed other assertion
        assertFalse(p.hasAttributes());
    }

@Test
    public void accessorsDoNotVivifyAttributes_5_oe() throws NoSuchFieldException, IllegalAccessException {
        // internally, we don't want to create empty Attribute objects unless actually used for something
        Document doc = Jsoup.parse("<div><p><a href=foo>One</a>");
        Element div = doc.selectFirst("div");
        Element p = doc.selectFirst("p");
        Element a = doc.selectFirst("a");

        // should not create attributes
        // removed other assertion
        p.removeAttr("href");

        Elements hrefs = doc.select("[href]");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(a.hasAttributes());
    }

@Test
    public void childNodesAccessorDoesNotVivify_1_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        assertFalse(p.hasChildNodes());
    }

@Test
    public void childNodesAccessorDoesNotVivify_2_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion

        assertEquals(0, p.childNodeSize());
    }

@Test
    public void childNodesAccessorDoesNotVivify_3_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion

        // removed other assertion
        assertEquals(0, p.childrenSize());
    }

@Test
    public void childNodesAccessorDoesNotVivify_4_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion

        // removed other assertion
        // removed other assertion

        List<Node> childNodes = p.childNodes();
        assertEquals(0, childNodes.size());
    }

@Test
    public void childNodesAccessorDoesNotVivify_5_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion

        // removed other assertion
        // removed other assertion

        List<Node> childNodes = p.childNodes();
        // removed other assertion

        Elements children = p.children();
        assertEquals(0, children.size());
    }

@Test
    public void childNodesAccessorDoesNotVivify_6_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion

        // removed other assertion
        // removed other assertion

        List<Node> childNodes = p.childNodes();
        // removed other assertion

        Elements children = p.children();
        // removed other assertion

        assertFalse(p.hasChildNodes());
    }

@Test void emptyChildrenElementsIsModifiable_1_oe() {
        // using unmodifiable empty in childElementList as short circuit, but people may be modifying Elements.
        Element p = new Element("p");
        Elements els = p.children();
        assertEquals(0, els.size());
        }

@Test void emptyChildrenElementsIsModifiable_2_oe() {
        // using unmodifiable empty in childElementList as short circuit, but people may be modifying Elements.
        Element p = new Element("p");
        Elements els = p.children();
        // removed other assertion
        els.add(new Element("a"));
        assertEquals(1, els.size());
        }

@Test public void attributeSizeDoesNotAutoVivify_1_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        assertNotNull(p);
        }

@Test public void attributeSizeDoesNotAutoVivify_2_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion
        assertFalse(p.hasAttributes());
        }

@Test public void attributeSizeDoesNotAutoVivify_3_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion
        // removed other assertion
        assertEquals(0, p.attributesSize());
        }

@Test public void attributeSizeDoesNotAutoVivify_4_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(p.hasAttributes());
        }

@Test public void attributeSizeDoesNotAutoVivify_5_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.attr("foo", "bar");
        assertEquals(1, p.attributesSize());
        }

@Test public void attributeSizeDoesNotAutoVivify_6_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.attr("foo", "bar");
        // removed other assertion
        assertTrue(p.hasAttributes());
        }

@Test public void attributeSizeDoesNotAutoVivify_7_oe() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.selectFirst("p");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.attr("foo", "bar");
        // removed other assertion
        // removed other assertion

        p.removeAttr("foo");
        assertEquals(0, p.attributesSize());
        }

@Test void clonedElementsHaveOwnerDocsAndIndependentSettings_1_oe() {
        // https://github.com/jhy/jsoup/issues/763
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        assertNotNull(div);
        }

@Test void clonedElementsHaveOwnerDocsAndIndependentSettings_2_oe() {
        // https://github.com/jhy/jsoup/issues/763
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        // removed other assertion
        Node text = div.childNode(0);
        assertNotNull(text);
        }

@Test void clonedElementsHaveOwnerDocsAndIndependentSettings_3_oe() {
        // https://github.com/jhy/jsoup/issues/763
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        // removed other assertion
        Node text = div.childNode(0);
        // removed other assertion

        Element divClone = div.clone();
        Document docClone = divClone.ownerDocument();
        assertNotNull(docClone);
        }

@Test void clonedElementsHaveOwnerDocsAndIndependentSettings_4_oe() {
        // https://github.com/jhy/jsoup/issues/763
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        // removed other assertion
        Node text = div.childNode(0);
        // removed other assertion

        Element divClone = div.clone();
        Document docClone = divClone.ownerDocument();
        // removed other assertion
        assertFalse(docClone.outputSettings().prettyPrint());
        }

@Test void clonedElementsHaveOwnerDocsAndIndependentSettings_5_oe() {
        // https://github.com/jhy/jsoup/issues/763
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        // removed other assertion
        Node text = div.childNode(0);
        // removed other assertion

        Element divClone = div.clone();
        Document docClone = divClone.ownerDocument();
        // removed other assertion
        // removed other assertion
        assertNotSame(doc, docClone);
        }

@Test void clonedElementsHaveOwnerDocsAndIndependentSettings_6_oe() {
        // https://github.com/jhy/jsoup/issues/763
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        // removed other assertion
        Node text = div.childNode(0);
        // removed other assertion

        Element divClone = div.clone();
        Document docClone = divClone.ownerDocument();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(docClone, divClone.childNode(0).ownerDocument());
        }

@Test void clonedElementsHaveOwnerDocsAndIndependentSettings_7_oe() {
        // https://github.com/jhy/jsoup/issues/763
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        // removed other assertion
        Node text = div.childNode(0);
        // removed other assertion

        Element divClone = div.clone();
        Document docClone = divClone.ownerDocument();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // the cloned text has same owner doc as the cloned div

        doc.outputSettings().prettyPrint(true);
        assertTrue(doc.outputSettings().prettyPrint());
        }

@Test void clonedElementsHaveOwnerDocsAndIndependentSettings_8_oe() {
        // https://github.com/jhy/jsoup/issues/763
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        // removed other assertion
        Node text = div.childNode(0);
        // removed other assertion

        Element divClone = div.clone();
        Document docClone = divClone.ownerDocument();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // the cloned text has same owner doc as the cloned div

        doc.outputSettings().prettyPrint(true);
        // removed other assertion
        assertFalse(docClone.outputSettings().prettyPrint());
        }

@Test void clonedElementsHaveOwnerDocsAndIndependentSettings_9_oe() {
        // https://github.com/jhy/jsoup/issues/763
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        // removed other assertion
        Node text = div.childNode(0);
        // removed other assertion

        Element divClone = div.clone();
        Document docClone = divClone.ownerDocument();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // the cloned text has same owner doc as the cloned div

        doc.outputSettings().prettyPrint(true);
        // removed other assertion
        // removed other assertion
        assertEquals(1, docClone.children().size()); // check did not get the second div as the owner's children;
        }

@Test void clonedElementsHaveOwnerDocsAndIndependentSettings_10_oe() {
        // https://github.com/jhy/jsoup/issues/763
        Document doc = Jsoup.parse("<div>Text</div><div>Two</div>");
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        // removed other assertion
        Node text = div.childNode(0);
        // removed other assertion

        Element divClone = div.clone();
        Document docClone = divClone.ownerDocument();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // the cloned text has same owner doc as the cloned div

        doc.outputSettings().prettyPrint(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(divClone, docClone.child(0)); // note not the head or the body -- not normalized;
        }

@ParameterizedTest
    @MethodSource("testOutputSettings")
    void prettySerializationRoundTrips_1_oe(Document.OutputSettings settings) {
        // https://github.com/jhy/jsoup/issues/1688
        // tests that repeated html() and parse() does not accumulate errant spaces / newlines
        Document doc = Jsoup.parse("<div>\nFoo\n<p>\nBar\nqux</p></div>\n<script>\n alert('Hello!');\n</script>");
        doc.outputSettings(settings);
        String html = doc.html();
        Document doc2 = Jsoup.parse(html);
        doc2.outputSettings(settings);
        String html2 = doc2.html();

        assertEquals(html, html2);
    }

@Test void prettyPrintScriptsDoesNotGrowOnRepeat_1_oe() {
        Document doc = Jsoup.parse("<div>\nFoo\n<p>\nBar\nqux</p></div>\n<script>\n alert('Hello!');\n</script>");
        Document.OutputSettings settings = doc.outputSettings();
        settings
            .prettyPrint(true)
            .outline(true)
            .indentAmount(4)
            ;

        String html = doc.html();
        Document doc2 = Jsoup.parse(html);
        doc2.outputSettings(settings);
        String html2 = doc2.html();
        assertEquals(html, html2);
        }

@Test void elementBrText_1_oe() {
        // testcase for https://github.com/jhy/jsoup/issues/1437
        String html = "<p>Hello<br>World</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        assertNotNull(p);
        }

@Test void elementBrText_2_oe() {
        // testcase for https://github.com/jhy/jsoup/issues/1437
        String html = "<p>Hello<br>World</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        assertEquals(html, p.outerHtml());
        }

@Test void elementBrText_3_oe() {
        // testcase for https://github.com/jhy/jsoup/issues/1437
        String html = "<p>Hello<br>World</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        assertEquals("Hello World", p.text());
        }

@Test void elementBrText_4_oe() {
        // testcase for https://github.com/jhy/jsoup/issues/1437
        String html = "<p>Hello<br>World</p>";
        Document doc = Jsoup.parse(html);
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Hello\nWorld", p.wholeText());
        }

@Test void preformatFlowsToChildTextNodes_1_oe() {
        // https://github.com/jhy/jsoup/issues/1776
        String html = "<div><pre>One\n<span>\nTwo</span>\n <span>  \nThree</span>\n <span>Four <span>Five</span>\n  Six\n</pre>";
        Document doc = Jsoup.parse(html);
        doc.outputSettings().indentAmount(2).prettyPrint(true);

        Element div = doc.selectFirst("div");
        assertNotNull(div);
        }

@Test void preformatFlowsToChildTextNodes_2_oe() {
        // https://github.com/jhy/jsoup/issues/1776
        String html = "<div><pre>One\n<span>\nTwo</span>\n <span>  \nThree</span>\n <span>Four <span>Five</span>\n  Six\n</pre>";
        Document doc = Jsoup.parse(html);
        doc.outputSettings().indentAmount(2).prettyPrint(true);

        Element div = doc.selectFirst("div");
        // removed other assertion
        String actual = div.outerHtml();
        String expect = "<div>\n" +
            "  <pre>One\n" +
            "<span>\n" +
            "Two</span>\n" +
            " <span>  \n" +
            "Three</span>\n" +
            " <span>Four <span>Five</span>\n" +
            "  Six\n" +
            "</span></pre>\n" +
            "</div>";
        assertEquals(expect, actual);
        }

@Test void preformatFlowsToChildTextNodes_3_oe() {
        // https://github.com/jhy/jsoup/issues/1776
        String html = "<div><pre>One\n<span>\nTwo</span>\n <span>  \nThree</span>\n <span>Four <span>Five</span>\n  Six\n</pre>";
        Document doc = Jsoup.parse(html);
        doc.outputSettings().indentAmount(2).prettyPrint(true);

        Element div = doc.selectFirst("div");
        // removed other assertion
        String actual = div.outerHtml();
        String expect = "<div>\n" +
            "  <pre>One\n" +
            "<span>\n" +
            "Two</span>\n" +
            " <span>  \n" +
            "Three</span>\n" +
            " <span>Four <span>Five</span>\n" +
            "  Six\n" +
            "</span></pre>\n" +
            "</div>";
        // removed other assertion

        String expectText = "One\n" +
            "\n" +
            "Two\n" +
            "   \n" +
            "Three\n" +
            " Four Five\n" +
            "  Six\n";
        assertEquals(expectText, div.wholeText());
        }

@Test void preformatFlowsToChildTextNodes_4_oe() {
        // https://github.com/jhy/jsoup/issues/1776
        String html = "<div><pre>One\n<span>\nTwo</span>\n <span>  \nThree</span>\n <span>Four <span>Five</span>\n  Six\n</pre>";
        Document doc = Jsoup.parse(html);
        doc.outputSettings().indentAmount(2).prettyPrint(true);

        Element div = doc.selectFirst("div");
        // removed other assertion
        String actual = div.outerHtml();
        String expect = "<div>\n" +
            "  <pre>One\n" +
            "<span>\n" +
            "Two</span>\n" +
            " <span>  \n" +
            "Three</span>\n" +
            " <span>Four <span>Five</span>\n" +
            "  Six\n" +
            "</span></pre>\n" +
            "</div>";
        // removed other assertion

        String expectText = "One\n" +
            "\n" +
            "Two\n" +
            "   \n" +
            "Three\n" +
            " Four Five\n" +
            "  Six\n";
        // removed other assertion

        String expectOwn = "One\n" +
            "\n" +
            " \n" +
            " ";
        assertEquals(expectOwn, div.child(0).wholeOwnText());
        }

@Test void testExpectFirst_1_oe() {
        Document doc = Jsoup.parse("<p>One</p><p>Two <span>Three</span> <span>Four</span>");

        Element span = doc.expectFirst("span");
        assertEquals("Three", span.text());
        }

@Test void testExpectFirst_2_oe() {
        Document doc = Jsoup.parse("<p>One</p><p>Two <span>Three</span> <span>Four</span>");

        Element span = doc.expectFirst("span");
        // removed other assertion

        assertNull(doc.selectFirst("div"));
        }

@Test void testExpectFirst_3_oe() {
        Document doc = Jsoup.parse("<p>One</p><p>Two <span>Three</span> <span>Four</span>");

        Element span = doc.expectFirst("span");
        // removed other assertion

        // removed other assertion
        boolean threw = false;
        try {
            Element div = doc.expectFirst("div");
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        assertTrue(threw);
        }

@Test void testExpectFirstMessage_1_oe() {
        Document doc = Jsoup.parse("<p>One</p><p>Two <span>Three</span> <span>Four</span>");
        boolean threw = false;
        Element p = doc.expectFirst("P");
        try {
            Element span = p.expectFirst("span.doesNotExist");
        } catch (ValidationException e) {
            threw = true;
            assertEquals("No elements matched the query 'span.doesNotExist' on element 'p'.", e.getMessage());
        }
        }

@Test void testExpectFirstMessage_2_oe() {
        Document doc = Jsoup.parse("<p>One</p><p>Two <span>Three</span> <span>Four</span>");
        boolean threw = false;
        Element p = doc.expectFirst("P");
        try {
            Element span = p.expectFirst("span.doesNotExist");
        } catch (ValidationException e) {
            threw = true;
            // removed other assertion
        }
        assertTrue(threw);
        }

@Test void testExpectFirstMessageDoc_1_oe() {
        Document doc = Jsoup.parse("<p>One</p><p>Two <span>Three</span> <span>Four</span>");
        boolean threw = false;
        Element p = doc.expectFirst("P");
        try {
            Element span = doc.expectFirst("span.doesNotExist");
        } catch (ValidationException e) {
            threw = true;
            assertEquals("No elements matched the query 'span.doesNotExist' in the document.", e.getMessage());
        }
        }

@Test void testExpectFirstMessageDoc_2_oe() {
        Document doc = Jsoup.parse("<p>One</p><p>Two <span>Three</span> <span>Four</span>");
        boolean threw = false;
        Element p = doc.expectFirst("P");
        try {
            Element span = doc.expectFirst("span.doesNotExist");
        } catch (ValidationException e) {
            threw = true;
            // removed other assertion
        }
        assertTrue(threw);
        }

@Test void spanRunsMaintainSpace_1_oe() {
        // https://github.com/jhy/jsoup/issues/1787
        Document doc = Jsoup.parse("<p><span>One</span>\n<span>Two</span>\n<span>Three</span></p>");
        String text = "One Two Three";
        Element body = doc.body();
        assertEquals(text, body.text());
        }

@Test void spanRunsMaintainSpace_2_oe() {
        // https://github.com/jhy/jsoup/issues/1787
        Document doc = Jsoup.parse("<p><span>One</span>\n<span>Two</span>\n<span>Three</span></p>");
        String text = "One Two Three";
        Element body = doc.body();
        // removed other assertion

        Element p = doc.expectFirst("p");
        String html = p.html();
        p.html(html);
        assertEquals(text, body.text());
        }

@Test void spanRunsMaintainSpace_3_oe() {
        // https://github.com/jhy/jsoup/issues/1787
        Document doc = Jsoup.parse("<p><span>One</span>\n<span>Two</span>\n<span>Three</span></p>");
        String text = "One Two Three";
        Element body = doc.body();
        // removed other assertion

        Element p = doc.expectFirst("p");
        String html = p.html();
        p.html(html);
        // removed other assertion

        assertEquals("<p><span>One</span> <span>Two</span> <span>Three</span></p>", body.html());
        }

@Test void doctypeIsPrettyPrinted_1_oe() {
        // resolves underlying issue raised in https://github.com/jhy/jsoup/pull/1664
        Document doc1 = Jsoup.parse("<!--\nlicense\n-->\n \n<!doctype html>\n<html>");
        Document doc2 = Jsoup.parse("\n  <!doctype html><html>");
        Document doc3 = Jsoup.parse("<!doctype html>\n<html>");
        Document doc4 = Jsoup.parse("\n<!doctype html>\n<html>");
        Document doc5 = Jsoup.parse("\n<!--\n comment \n -->  <!doctype html>\n<html>");
        Document doc6 = Jsoup.parse("<!--\n comment \n -->  <!doctype html>\n<html>");

        assertEquals("<!--\nlicense\n-->\n<!doctype html>\n<html>\n <head></head>\n <body></body>\n</html>", doc1.html());
        }

@Test void doctypeIsPrettyPrinted_2_oe() {
        // resolves underlying issue raised in https://github.com/jhy/jsoup/pull/1664
        Document doc1 = Jsoup.parse("<!--\nlicense\n-->\n \n<!doctype html>\n<html>");
        Document doc2 = Jsoup.parse("\n  <!doctype html><html>");
        Document doc3 = Jsoup.parse("<!doctype html>\n<html>");
        Document doc4 = Jsoup.parse("\n<!doctype html>\n<html>");
        Document doc5 = Jsoup.parse("\n<!--\n comment \n -->  <!doctype html>\n<html>");
        Document doc6 = Jsoup.parse("<!--\n comment \n -->  <!doctype html>\n<html>");

        // removed other assertion
        doc1.outputSettings().prettyPrint(false);
        assertEquals("<!--\nlicense\n--><!doctype html>\n<html><head></head><body></body></html>", doc1.html());
        }

@Test void doctypeIsPrettyPrinted_3_oe() {
        // resolves underlying issue raised in https://github.com/jhy/jsoup/pull/1664
        Document doc1 = Jsoup.parse("<!--\nlicense\n-->\n \n<!doctype html>\n<html>");
        Document doc2 = Jsoup.parse("\n  <!doctype html><html>");
        Document doc3 = Jsoup.parse("<!doctype html>\n<html>");
        Document doc4 = Jsoup.parse("\n<!doctype html>\n<html>");
        Document doc5 = Jsoup.parse("\n<!--\n comment \n -->  <!doctype html>\n<html>");
        Document doc6 = Jsoup.parse("<!--\n comment \n -->  <!doctype html>\n<html>");

        // removed other assertion
        doc1.outputSettings().prettyPrint(false);
        // removed other assertion
        // note that the whitespace between the comment and the doctype is not retained, in Initial state

        assertEquals("<!doctype html>\n<html>\n <head></head>\n <body></body>\n</html>", doc2.html());
        }

@Test void doctypeIsPrettyPrinted_4_oe() {
        // resolves underlying issue raised in https://github.com/jhy/jsoup/pull/1664
        Document doc1 = Jsoup.parse("<!--\nlicense\n-->\n \n<!doctype html>\n<html>");
        Document doc2 = Jsoup.parse("\n  <!doctype html><html>");
        Document doc3 = Jsoup.parse("<!doctype html>\n<html>");
        Document doc4 = Jsoup.parse("\n<!doctype html>\n<html>");
        Document doc5 = Jsoup.parse("\n<!--\n comment \n -->  <!doctype html>\n<html>");
        Document doc6 = Jsoup.parse("<!--\n comment \n -->  <!doctype html>\n<html>");

        // removed other assertion
        doc1.outputSettings().prettyPrint(false);
        // removed other assertion
        // note that the whitespace between the comment and the doctype is not retained, in Initial state

        // removed other assertion
        assertEquals("<!doctype html>\n<html>\n <head></head>\n <body></body>\n</html>", doc3.html());
        }

@Test void doctypeIsPrettyPrinted_5_oe() {
        // resolves underlying issue raised in https://github.com/jhy/jsoup/pull/1664
        Document doc1 = Jsoup.parse("<!--\nlicense\n-->\n \n<!doctype html>\n<html>");
        Document doc2 = Jsoup.parse("\n  <!doctype html><html>");
        Document doc3 = Jsoup.parse("<!doctype html>\n<html>");
        Document doc4 = Jsoup.parse("\n<!doctype html>\n<html>");
        Document doc5 = Jsoup.parse("\n<!--\n comment \n -->  <!doctype html>\n<html>");
        Document doc6 = Jsoup.parse("<!--\n comment \n -->  <!doctype html>\n<html>");

        // removed other assertion
        doc1.outputSettings().prettyPrint(false);
        // removed other assertion
        // note that the whitespace between the comment and the doctype is not retained, in Initial state

        // removed other assertion
        // removed other assertion
        assertEquals("<!doctype html>\n<html>\n <head></head>\n <body></body>\n</html>", doc4.html());
        }

@Test void doctypeIsPrettyPrinted_6_oe() {
        // resolves underlying issue raised in https://github.com/jhy/jsoup/pull/1664
        Document doc1 = Jsoup.parse("<!--\nlicense\n-->\n \n<!doctype html>\n<html>");
        Document doc2 = Jsoup.parse("\n  <!doctype html><html>");
        Document doc3 = Jsoup.parse("<!doctype html>\n<html>");
        Document doc4 = Jsoup.parse("\n<!doctype html>\n<html>");
        Document doc5 = Jsoup.parse("\n<!--\n comment \n -->  <!doctype html>\n<html>");
        Document doc6 = Jsoup.parse("<!--\n comment \n -->  <!doctype html>\n<html>");

        // removed other assertion
        doc1.outputSettings().prettyPrint(false);
        // removed other assertion
        // note that the whitespace between the comment and the doctype is not retained, in Initial state

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("<!--\n comment \n -->\n<!doctype html>\n<html>\n <head></head>\n <body></body>\n</html>", doc5.html());
        }

@Test void doctypeIsPrettyPrinted_7_oe() {
        // resolves underlying issue raised in https://github.com/jhy/jsoup/pull/1664
        Document doc1 = Jsoup.parse("<!--\nlicense\n-->\n \n<!doctype html>\n<html>");
        Document doc2 = Jsoup.parse("\n  <!doctype html><html>");
        Document doc3 = Jsoup.parse("<!doctype html>\n<html>");
        Document doc4 = Jsoup.parse("\n<!doctype html>\n<html>");
        Document doc5 = Jsoup.parse("\n<!--\n comment \n -->  <!doctype html>\n<html>");
        Document doc6 = Jsoup.parse("<!--\n comment \n -->  <!doctype html>\n<html>");

        // removed other assertion
        doc1.outputSettings().prettyPrint(false);
        // removed other assertion
        // note that the whitespace between the comment and the doctype is not retained, in Initial state

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("<!--\n comment \n -->\n<!doctype html>\n<html>\n <head></head>\n <body></body>\n</html>", doc6.html());
        }

@Test void textnodeInBlockIndent_1_oe() {
        String html ="<div>\n{{ msg }} \n </div>\n<div>\n{{ msg }} \n </div>";
        Document doc = Jsoup.parse(html);
        assertEquals("<div>\n {{ msg }}\n</div>\n<div>\n {{ msg }}\n</div>", doc.body().html());
        }

@Test void stripTrailing_1_oe() {
        String html = "<p> This <span>is </span>fine. </p>";
        Document doc = Jsoup.parse(html);
        assertEquals("<p>This <span>is </span>fine.</p>", doc.body().html());
        }

@Test void elementIndentAndSpaceTrims_1_oe() {
        String html = "<body><div> <p> One Two </p> <a>  Hello </a><p>\nSome text \n</p>\n </div>";
        Document doc = Jsoup.parse(html);
        assertEquals("<div>\n" + " <p>One Two</p> <a> Hello </a>\n" + " <p>Some text</p>\n" + "</div>",doc.body().html());
        }

@Test void divAInlineable_1_oe() {
        String html = "<body><div> <a>Text</a>";
        Document doc = Jsoup.parse(html);
        assertEquals("<div><a>Text</a>\n</div>", doc.body().html());
        }

}