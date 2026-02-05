package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tests for ElementList.

 @author Jonathan Hedley, jonathan@hedley.net */
public class ElementsTest_OE25Dev {

@Test public void filter_1_oe() {
        String h = "<p>Excl</p><div class=headline><p>Hello</p><p>There</p></div><div class=headline><h1>Headline</h1></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select(".headline").select("p");
        assertEquals(2, els.size());
        }

@Test public void filter_2_oe() {
        String h = "<p>Excl</p><div class=headline><p>Hello</p><p>There</p></div><div class=headline><h1>Headline</h1></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select(".headline").select("p");
        // removed other assertion
        assertEquals("Hello", els.get(0).text());
        }

@Test public void filter_3_oe() {
        String h = "<p>Excl</p><div class=headline><p>Hello</p><p>There</p></div><div class=headline><h1>Headline</h1></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select(".headline").select("p");
        // removed other assertion
        // removed other assertion
        assertEquals("There", els.get(1).text());
        }

@Test public void attributes_1_oe() {
        String h = "<p title=foo><p title=bar><p class=foo><p class=bar>";
        Document doc = Jsoup.parse(h);
        Elements withTitle = doc.select("p[title]");
        assertEquals(2, withTitle.size());
        }

@Test public void attributes_2_oe() {
        String h = "<p title=foo><p title=bar><p class=foo><p class=bar>";
        Document doc = Jsoup.parse(h);
        Elements withTitle = doc.select("p[title]");
        // removed other assertion
        assertTrue(withTitle.hasAttr("title"));
        }

@Test public void attributes_3_oe() {
        String h = "<p title=foo><p title=bar><p class=foo><p class=bar>";
        Document doc = Jsoup.parse(h);
        Elements withTitle = doc.select("p[title]");
        // removed other assertion
        // removed other assertion
        assertFalse(withTitle.hasAttr("class"));
        }

@Test public void attributes_4_oe() {
        String h = "<p title=foo><p title=bar><p class=foo><p class=bar>";
        Document doc = Jsoup.parse(h);
        Elements withTitle = doc.select("p[title]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo", withTitle.attr("title"));
        }

@Test public void attributes_5_oe() {
        String h = "<p title=foo><p title=bar><p class=foo><p class=bar>";
        Document doc = Jsoup.parse(h);
        Elements withTitle = doc.select("p[title]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        withTitle.removeAttr("title");
        assertEquals(2,withTitle.size());// existing Elements are not reevaluated assertEquals(0,doc.select("p[title]").size());
        }

@Test public void attributes_6_oe() {
        String h = "<p title=foo><p title=bar><p class=foo><p class=bar>";
        Document doc = Jsoup.parse(h);
        Elements withTitle = doc.select("p[title]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        withTitle.removeAttr("title");
        // removed other assertion

        Elements ps = doc.select("p").attr("style", "classy");
        assertEquals(4, ps.size());
        }

@Test public void attributes_7_oe() {
        String h = "<p title=foo><p title=bar><p class=foo><p class=bar>";
        Document doc = Jsoup.parse(h);
        Elements withTitle = doc.select("p[title]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        withTitle.removeAttr("title");
        // removed other assertion

        Elements ps = doc.select("p").attr("style", "classy");
        // removed other assertion
        assertEquals("classy", ps.last().attr("style"));
        }

@Test public void attributes_8_oe() {
        String h = "<p title=foo><p title=bar><p class=foo><p class=bar>";
        Document doc = Jsoup.parse(h);
        Elements withTitle = doc.select("p[title]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        withTitle.removeAttr("title");
        // removed other assertion

        Elements ps = doc.select("p").attr("style", "classy");
        // removed other assertion
        // removed other assertion
        assertEquals("bar", ps.last().attr("class"));
        }

@Test public void hasAttr_1_oe() {
        Document doc = Jsoup.parse("<p title=foo><p title=bar><p class=foo><p class=bar>");
        Elements ps = doc.select("p");
        assertTrue(ps.hasAttr("class"));
        }

@Test public void hasAttr_2_oe() {
        Document doc = Jsoup.parse("<p title=foo><p title=bar><p class=foo><p class=bar>");
        Elements ps = doc.select("p");
        // removed other assertion
        assertFalse(ps.hasAttr("style"));
        }

@Test public void hasAbsAttr_1_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org'>Two</a>");
        Elements one = doc.select("#1");
        Elements two = doc.select("#2");
        Elements both = doc.select("a");
        assertFalse(one.hasAttr("abs:href"));
        }

@Test public void hasAbsAttr_2_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org'>Two</a>");
        Elements one = doc.select("#1");
        Elements two = doc.select("#2");
        Elements both = doc.select("a");
        // removed other assertion
        assertTrue(two.hasAttr("abs:href"));
        }

@Test public void hasAbsAttr_3_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org'>Two</a>");
        Elements one = doc.select("#1");
        Elements two = doc.select("#2");
        Elements both = doc.select("a");
        // removed other assertion
        // removed other assertion
        assertTrue(both.hasAttr("abs:href")); // hits on #2;
        }

@Test public void attr_1_oe() {
        Document doc = Jsoup.parse("<p title=foo><p title=bar><p class=foo><p class=bar>");
        String classVal = doc.select("p").attr("class");
        assertEquals("foo", classVal);
        }

@Test public void absAttr_1_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org'>Two</a>");
        Elements one = doc.select("#1");
        Elements two = doc.select("#2");
        Elements both = doc.select("a");

        assertEquals("", one.attr("abs:href"));
        }

@Test public void absAttr_2_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org'>Two</a>");
        Elements one = doc.select("#1");
        Elements two = doc.select("#2");
        Elements both = doc.select("a");

        // removed other assertion
        assertEquals("https://jsoup.org", two.attr("abs:href"));
        }

@Test public void absAttr_3_oe() {
        Document doc = Jsoup.parse("<a id=1 href='/foo'>One</a> <a id=2 href='https://jsoup.org'>Two</a>");
        Elements one = doc.select("#1");
        Elements two = doc.select("#2");
        Elements both = doc.select("a");

        // removed other assertion
        // removed other assertion
        assertEquals("https://jsoup.org", both.attr("abs:href"));
        }

@Test public void classes_1_oe() {
        Document doc = Jsoup.parse("<div><p class='mellow yellow'></p><p class='red green'></p>");

        Elements els = doc.select("p");
        assertTrue(els.hasClass("red"));
        }

@Test public void classes_2_oe() {
        Document doc = Jsoup.parse("<div><p class='mellow yellow'></p><p class='red green'></p>");

        Elements els = doc.select("p");
        // removed other assertion
        assertFalse(els.hasClass("blue"));
        }

@Test public void classes_3_oe() {
        Document doc = Jsoup.parse("<div><p class='mellow yellow'></p><p class='red green'></p>");

        Elements els = doc.select("p");
        // removed other assertion
        // removed other assertion
        els.addClass("blue");
        els.removeClass("yellow");
        els.toggleClass("mellow");

        assertEquals("blue", els.get(0).className());
        }

@Test public void classes_4_oe() {
        Document doc = Jsoup.parse("<div><p class='mellow yellow'></p><p class='red green'></p>");

        Elements els = doc.select("p");
        // removed other assertion
        // removed other assertion
        els.addClass("blue");
        els.removeClass("yellow");
        els.toggleClass("mellow");

        // removed other assertion
        assertEquals("red green blue mellow", els.get(1).className());
        }

@Test public void hasClassCaseInsensitive_1_oe() {
        Elements els = Jsoup.parse("<p Class=One>One <p class=Two>Two <p CLASS=THREE>THREE").select("p");
        Element one = els.get(0);
        Element two = els.get(1);
        Element thr = els.get(2);

        assertTrue(one.hasClass("One"));
        }

@Test public void hasClassCaseInsensitive_2_oe() {
        Elements els = Jsoup.parse("<p Class=One>One <p class=Two>Two <p CLASS=THREE>THREE").select("p");
        Element one = els.get(0);
        Element two = els.get(1);
        Element thr = els.get(2);

        // removed other assertion
        assertTrue(one.hasClass("ONE"));
        }

@Test public void hasClassCaseInsensitive_3_oe() {
        Elements els = Jsoup.parse("<p Class=One>One <p class=Two>Two <p CLASS=THREE>THREE").select("p");
        Element one = els.get(0);
        Element two = els.get(1);
        Element thr = els.get(2);

        // removed other assertion
        // removed other assertion

        assertTrue(two.hasClass("TWO"));
        }

@Test public void hasClassCaseInsensitive_4_oe() {
        Elements els = Jsoup.parse("<p Class=One>One <p class=Two>Two <p CLASS=THREE>THREE").select("p");
        Element one = els.get(0);
        Element two = els.get(1);
        Element thr = els.get(2);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(two.hasClass("Two"));
        }

@Test public void hasClassCaseInsensitive_5_oe() {
        Elements els = Jsoup.parse("<p Class=One>One <p class=Two>Two <p CLASS=THREE>THREE").select("p");
        Element one = els.get(0);
        Element two = els.get(1);
        Element thr = els.get(2);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(thr.hasClass("ThreE"));
        }

@Test public void hasClassCaseInsensitive_6_oe() {
        Elements els = Jsoup.parse("<p Class=One>One <p class=Two>Two <p CLASS=THREE>THREE").select("p");
        Element one = els.get(0);
        Element two = els.get(1);
        Element thr = els.get(2);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(thr.hasClass("three"));
        }

@Test public void text_1_oe() {
        String h = "<div><p>Hello<p>there<p>world</div>";
        Document doc = Jsoup.parse(h);
        assertEquals("Hello there world", doc.select("div > *").text());
        }

@Test public void hasText_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><div><p></p></div>");
        Elements divs = doc.select("div");
        assertTrue(divs.hasText());
        }

@Test public void hasText_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><div><p></p></div>");
        Elements divs = doc.select("div");
        // removed other assertion
        assertFalse(doc.select("div + div").hasText());
        }

@Test public void html_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><div><p>There</p></div>");
        Elements divs = doc.select("div");
        assertEquals("<p>Hello</p>\n<p>There</p>", divs.html());
        }

@Test public void outerHtml_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><div><p>There</p></div>");
        Elements divs = doc.select("div");
        assertEquals("<div><p>Hello</p></div><div><p>There</p></div>", TextUtil.stripNewlines(divs.outerHtml()));
        }

@Test public void setHtml_1_oe() {
        Document doc = Jsoup.parse("<p>One</p><p>Two</p><p>Three</p>");
        Elements ps = doc.select("p");

        ps.prepend("<b>Bold</b>").append("<i>Ital</i>");
        assertEquals("<p><b>Bold</b>Two<i>Ital</i></p>", TextUtil.stripNewlines(ps.get(1).outerHtml()));
        }

@Test public void setHtml_2_oe() {
        Document doc = Jsoup.parse("<p>One</p><p>Two</p><p>Three</p>");
        Elements ps = doc.select("p");

        ps.prepend("<b>Bold</b>").append("<i>Ital</i>");
        // removed other assertion

        ps.html("<span>Gone</span>");
        assertEquals("<p><span>Gone</span></p>", TextUtil.stripNewlines(ps.get(1).outerHtml()));
        }

@Test public void val_1_oe() {
        Document doc = Jsoup.parse("<input value='one' /><textarea>two</textarea>");
        Elements els = doc.select("input, textarea");
        assertEquals(2, els.size());
        }

@Test public void val_2_oe() {
        Document doc = Jsoup.parse("<input value='one' /><textarea>two</textarea>");
        Elements els = doc.select("input, textarea");
        // removed other assertion
        assertEquals("one", els.val());
        }

@Test public void val_3_oe() {
        Document doc = Jsoup.parse("<input value='one' /><textarea>two</textarea>");
        Elements els = doc.select("input, textarea");
        // removed other assertion
        // removed other assertion
        assertEquals("two", els.last().val());
        }

@Test public void val_4_oe() {
        Document doc = Jsoup.parse("<input value='one' /><textarea>two</textarea>");
        Elements els = doc.select("input, textarea");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        els.val("three");
        assertEquals("three", els.first().val());
        }

@Test public void val_5_oe() {
        Document doc = Jsoup.parse("<input value='one' /><textarea>two</textarea>");
        Elements els = doc.select("input, textarea");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        els.val("three");
        // removed other assertion
        assertEquals("three", els.last().val());
        }

@Test public void val_6_oe() {
        Document doc = Jsoup.parse("<input value='one' /><textarea>two</textarea>");
        Elements els = doc.select("input, textarea");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        els.val("three");
        // removed other assertion
        // removed other assertion
        assertEquals("<textarea>three</textarea>", els.last().outerHtml());
        }

@Test public void before_1_oe() {
        Document doc = Jsoup.parse("<p>This <a>is</a> <a>jsoup</a>.</p>");
        doc.select("a").before("<span>foo</span>");
        assertEquals("<p>This <span>foo</span><a>is</a> <span>foo</span><a>jsoup</a>.</p>", TextUtil.stripNewlines(doc.body().html()));
        }

@Test public void after_1_oe() {
        Document doc = Jsoup.parse("<p>This <a>is</a> <a>jsoup</a>.</p>");
        doc.select("a").after("<span>foo</span>");
        assertEquals("<p>This <a>is</a><span>foo</span> <a>jsoup</a><span>foo</span>.</p>", TextUtil.stripNewlines(doc.body().html()));
        }

@Test public void wrap_1_oe() {
        String h = "<p><b>This</b> is <b>jsoup</b></p>";
        Document doc = Jsoup.parse(h);
        doc.select("b").wrap("<i></i>");
        assertEquals("<p><i><b>This</b></i> is <i><b>jsoup</b></i></p>", doc.body().html());
        }

@Test public void wrapDiv_1_oe() {
        String h = "<p><b>This</b> is <b>jsoup</b>.</p> <p>How do you like it?</p>";
        Document doc = Jsoup.parse(h);
        doc.select("p").wrap("<div></div>");
        assertEquals("<div>\n <p><b>This</b> is <b>jsoup</b>.</p>\n</div>\n<div>\n <p>How do you like it?</p>\n</div>",doc.body().html());
        }

@Test public void unwrap_1_oe() {
        String h = "<div><font>One</font> <font><a href=\"/\">Two</a></font></div";
        Document doc = Jsoup.parse(h);
        doc.select("font").unwrap();
        assertEquals("<div>One <a href=\"/\">Two</a></div>", TextUtil.stripNewlines(doc.body().html()));
        }

@Test public void unwrapP_1_oe() {
        String h = "<p><a>One</a> Two</p> Three <i>Four</i> <p>Fix <i>Six</i></p>";
        Document doc = Jsoup.parse(h);
        doc.select("p").unwrap();
        assertEquals("<a>One</a> Two Three <i>Four</i> Fix <i>Six</i>", TextUtil.stripNewlines(doc.body().html()));
        }

@Test public void unwrapKeepsSpace_1_oe() {
        String h = "<p>One <span>two</span> <span>three</span> four</p>";
        Document doc = Jsoup.parse(h);
        doc.select("span").unwrap();
        assertEquals("<p>One two three four</p>", doc.body().html());
        }

@Test public void empty_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello <b>there</b></p> <p>now!</p></div>");
        doc.outputSettings().prettyPrint(false);

        doc.select("p").empty();
        assertEquals("<div><p></p> <p></p></div>", doc.body().html());
        }

@Test public void remove_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello <b>there</b></p> jsoup <p>now!</p></div>");
        doc.outputSettings().prettyPrint(false);

        doc.select("p").remove();
        assertEquals("<div> jsoup </div>", doc.body().html());
        }

@Test public void eq_1_oe() {
        String h = "<p>Hello<p>there<p>world";
        Document doc = Jsoup.parse(h);
        assertEquals("there", doc.select("p").eq(1).text());
        }

@Test public void eq_2_oe() {
        String h = "<p>Hello<p>there<p>world";
        Document doc = Jsoup.parse(h);
        // removed other assertion
        assertEquals("there", doc.select("p").get(1).text());
        }

@Test public void is_1_oe() {
        String h = "<p>Hello<p title=foo>there<p>world";
        Document doc = Jsoup.parse(h);
        Elements ps = doc.select("p");
        assertTrue(ps.is("[title=foo]"));
        }

@Test public void is_2_oe() {
        String h = "<p>Hello<p title=foo>there<p>world";
        Document doc = Jsoup.parse(h);
        Elements ps = doc.select("p");
        // removed other assertion
        assertFalse(ps.is("[title=bar]"));
        }

@Test public void parents_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><p>There</p>");
        Elements parents = doc.select("p").parents();

        assertEquals(3, parents.size());
        }

@Test public void parents_2_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><p>There</p>");
        Elements parents = doc.select("p").parents();

        // removed other assertion
        assertEquals("div", parents.get(0).tagName());
        }

@Test public void parents_3_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><p>There</p>");
        Elements parents = doc.select("p").parents();

        // removed other assertion
        // removed other assertion
        assertEquals("body", parents.get(1).tagName());
        }

@Test public void parents_4_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><p>There</p>");
        Elements parents = doc.select("p").parents();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("html", parents.get(2).tagName());
        }

@Test public void not_1_oe() {
        Document doc = Jsoup.parse("<div id=1><p>One</p></div> <div id=2><p><span>Two</span></p></div>");

        Elements div1 = doc.select("div").not(":has(p > span)");
        assertEquals(1, div1.size());
        }

@Test public void not_2_oe() {
        Document doc = Jsoup.parse("<div id=1><p>One</p></div> <div id=2><p><span>Two</span></p></div>");

        Elements div1 = doc.select("div").not(":has(p > span)");
        // removed other assertion
        assertEquals("1", div1.first().id());
        }

@Test public void not_3_oe() {
        Document doc = Jsoup.parse("<div id=1><p>One</p></div> <div id=2><p><span>Two</span></p></div>");

        Elements div1 = doc.select("div").not(":has(p > span)");
        // removed other assertion
        // removed other assertion

        Elements div2 = doc.select("div").not("#1");
        assertEquals(1, div2.size());
        }

@Test public void not_4_oe() {
        Document doc = Jsoup.parse("<div id=1><p>One</p></div> <div id=2><p><span>Two</span></p></div>");

        Elements div1 = doc.select("div").not(":has(p > span)");
        // removed other assertion
        // removed other assertion

        Elements div2 = doc.select("div").not("#1");
        // removed other assertion
        assertEquals("2", div2.first().id());
        }

@Test public void tagNameSet_1_oe() {
        Document doc = Jsoup.parse("<p>Hello <i>there</i> <i>now</i></p>");
        doc.select("i").tagName("em");

        assertEquals("<p>Hello <em>there</em> <em>now</em></p>", doc.body().html());
        }

@Test public void traverse_1_oe() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div><div>There</div>");
        final StringBuilder accum = new StringBuilder();
        doc.select("div").traverse(new NodeVisitor() {
            @Override
            public void head(Node node, int depth) {
                accum.append("<").append(node.nodeName()).append(">");
            }

            @Override
            public void tail(Node node, int depth) {
                accum.append("</").append(node.nodeName()).append(">");
            }
        });
        assertEquals("<div><p><#text></#text></p></div><div><#text></#text></div>", accum.toString());
        }

@Test public void forms_1_oe() {
        Document doc = Jsoup.parse("<form id=1><input name=q></form><div /><form id=2><input name=f></form>");
        Elements els = doc.select("form, div");
        assertEquals(3, els.size());
        }

@Test public void forms_2_oe() {
        Document doc = Jsoup.parse("<form id=1><input name=q></form><div /><form id=2><input name=f></form>");
        Elements els = doc.select("form, div");
        // removed other assertion

        List<FormElement> forms = els.forms();
        assertEquals(2, forms.size());
        }

@Test public void forms_3_oe() {
        Document doc = Jsoup.parse("<form id=1><input name=q></form><div /><form id=2><input name=f></form>");
        Elements els = doc.select("form, div");
        // removed other assertion

        List<FormElement> forms = els.forms();
        // removed other assertion
        assertNotNull(forms.get(0));
        }

@Test public void forms_4_oe() {
        Document doc = Jsoup.parse("<form id=1><input name=q></form><div /><form id=2><input name=f></form>");
        Elements els = doc.select("form, div");
        // removed other assertion

        List<FormElement> forms = els.forms();
        // removed other assertion
        // removed other assertion
        assertNotNull(forms.get(1));
        }

@Test public void forms_5_oe() {
        Document doc = Jsoup.parse("<form id=1><input name=q></form><div /><form id=2><input name=f></form>");
        Elements els = doc.select("form, div");
        // removed other assertion

        List<FormElement> forms = els.forms();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", forms.get(0).id());
        }

@Test public void forms_6_oe() {
        Document doc = Jsoup.parse("<form id=1><input name=q></form><div /><form id=2><input name=f></form>");
        Elements els = doc.select("form, div");
        // removed other assertion

        List<FormElement> forms = els.forms();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", forms.get(1).id());
        }

@Test public void comments_1_oe() {
        Document doc = Jsoup.parse("<!-- comment1 --><p><!-- comment2 --><p class=two><!-- comment3 -->");
        List<Comment> comments = doc.select("p").comments();
        assertEquals(2, comments.size());
        }

@Test public void comments_2_oe() {
        Document doc = Jsoup.parse("<!-- comment1 --><p><!-- comment2 --><p class=two><!-- comment3 -->");
        List<Comment> comments = doc.select("p").comments();
        // removed other assertion
        assertEquals(" comment2 ", comments.get(0).getData());
        }

@Test public void comments_3_oe() {
        Document doc = Jsoup.parse("<!-- comment1 --><p><!-- comment2 --><p class=two><!-- comment3 -->");
        List<Comment> comments = doc.select("p").comments();
        // removed other assertion
        // removed other assertion
        assertEquals(" comment3 ", comments.get(1).getData());
        }

@Test public void comments_4_oe() {
        Document doc = Jsoup.parse("<!-- comment1 --><p><!-- comment2 --><p class=two><!-- comment3 -->");
        List<Comment> comments = doc.select("p").comments();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Comment> comments1 = doc.select("p.two").comments();
        assertEquals(1, comments1.size());
        }

@Test public void comments_5_oe() {
        Document doc = Jsoup.parse("<!-- comment1 --><p><!-- comment2 --><p class=two><!-- comment3 -->");
        List<Comment> comments = doc.select("p").comments();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<Comment> comments1 = doc.select("p.two").comments();
        // removed other assertion
        assertEquals(" comment3 ", comments1.get(0).getData());
        }

@Test public void textNodes_1_oe() {
        Document doc = Jsoup.parse("One<p>Two<a>Three</a><p>Four</p>Five");
        List<TextNode> textNodes = doc.select("p").textNodes();
        assertEquals(2, textNodes.size());
        }

@Test public void textNodes_2_oe() {
        Document doc = Jsoup.parse("One<p>Two<a>Three</a><p>Four</p>Five");
        List<TextNode> textNodes = doc.select("p").textNodes();
        // removed other assertion
        assertEquals("Two", textNodes.get(0).text());
        }

@Test public void textNodes_3_oe() {
        Document doc = Jsoup.parse("One<p>Two<a>Three</a><p>Four</p>Five");
        List<TextNode> textNodes = doc.select("p").textNodes();
        // removed other assertion
        // removed other assertion
        assertEquals("Four", textNodes.get(1).text());
        }

@Test public void dataNodes_1_oe() {
        Document doc = Jsoup.parse("<p>One</p><script>Two</script><style>Three</style>");
        List<DataNode> dataNodes = doc.select("p, script, style").dataNodes();
        assertEquals(2, dataNodes.size());
        }

@Test public void dataNodes_2_oe() {
        Document doc = Jsoup.parse("<p>One</p><script>Two</script><style>Three</style>");
        List<DataNode> dataNodes = doc.select("p, script, style").dataNodes();
        // removed other assertion
        assertEquals("Two", dataNodes.get(0).getWholeData());
        }

@Test public void dataNodes_3_oe() {
        Document doc = Jsoup.parse("<p>One</p><script>Two</script><style>Three</style>");
        List<DataNode> dataNodes = doc.select("p, script, style").dataNodes();
        // removed other assertion
        // removed other assertion
        assertEquals("Three", dataNodes.get(1).getWholeData());
        }

@Test public void dataNodes_4_oe() {
        Document doc = Jsoup.parse("<p>One</p><script>Two</script><style>Three</style>");
        List<DataNode> dataNodes = doc.select("p, script, style").dataNodes();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        doc = Jsoup.parse("<head><script type=application/json><crux></script><script src=foo>Blah</script>");
        Elements script = doc.select("script[type=application/json]");
        List<DataNode> scriptNode = script.dataNodes();
        assertEquals(1, scriptNode.size());
        }

@Test public void dataNodes_5_oe() {
        Document doc = Jsoup.parse("<p>One</p><script>Two</script><style>Three</style>");
        List<DataNode> dataNodes = doc.select("p, script, style").dataNodes();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        doc = Jsoup.parse("<head><script type=application/json><crux></script><script src=foo>Blah</script>");
        Elements script = doc.select("script[type=application/json]");
        List<DataNode> scriptNode = script.dataNodes();
        // removed other assertion
        DataNode dataNode = scriptNode.get(0);
        assertEquals("<crux>", dataNode.getWholeData());
        }

@Test public void dataNodes_6_oe() {
        Document doc = Jsoup.parse("<p>One</p><script>Two</script><style>Three</style>");
        List<DataNode> dataNodes = doc.select("p, script, style").dataNodes();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        doc = Jsoup.parse("<head><script type=application/json><crux></script><script src=foo>Blah</script>");
        Elements script = doc.select("script[type=application/json]");
        List<DataNode> scriptNode = script.dataNodes();
        // removed other assertion
        DataNode dataNode = scriptNode.get(0);
        // removed other assertion

        // check if they're live
        dataNode.setWholeData("<cromulent>");
        assertEquals("<script type=\"application/json\"><cromulent></script>", script.outerHtml());
        }

@Test public void nodesEmpty_1_oe() {
        Document doc = Jsoup.parse("<p>");
        assertEquals(0, doc.select("form").textNodes().size());
        }

@Test public void classWithHyphen_1_oe() {
        Document doc = Jsoup.parse("<p class='tab-nav'>Check</p>");
        Elements els = doc.getElementsByClass("tab-nav");
        assertEquals(1, els.size());
        }

@Test public void classWithHyphen_2_oe() {
        Document doc = Jsoup.parse("<p class='tab-nav'>Check</p>");
        Elements els = doc.getElementsByClass("tab-nav");
        // removed other assertion
        assertEquals("Check", els.text());
        }

@Test public void siblings_1_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        assertEquals(2, els.size());
        }

@Test public void siblings_2_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        assertEquals(2, next.size());
        }

@Test public void siblings_3_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        assertEquals("5", next.first().text());
        }

@Test public void siblings_4_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        assertEquals("11", next.last().text());
        }

@Test public void siblings_5_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, els.next("p:contains(6)").size());
        }

@Test public void siblings_6_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        assertEquals(1, nextF.size());
        }

@Test public void siblings_7_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        assertEquals("5", nextF.first().text());
        }

@Test public void siblings_8_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        assertEquals(4, nextA.size());
        }

@Test public void siblings_9_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        assertEquals("5", nextA.first().text());
        }

@Test public void siblings_10_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        assertEquals("12", nextA.last().text());
        }

@Test public void siblings_11_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        assertEquals(1, nextAF.size());
        }

@Test public void siblings_12_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        assertEquals("6", nextAF.first().text());
        }

@Test public void siblings_13_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        assertEquals(2, prev.size());
        }

@Test public void siblings_14_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        // removed other assertion
        assertEquals("3", prev.first().text());
        }

@Test public void siblings_15_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        // removed other assertion
        // removed other assertion
        assertEquals("9", prev.last().text());
        }

@Test public void siblings_16_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, els.prev("p:contains(1)").size());
        }

@Test public void siblings_17_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements prevF = els.prev("p:contains(3)");
        assertEquals(1, prevF.size());
        }

@Test public void siblings_18_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements prevF = els.prev("p:contains(3)");
        // removed other assertion
        assertEquals("3", prevF.first().text());
        }

@Test public void siblings_19_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements prevF = els.prev("p:contains(3)");
        // removed other assertion
        // removed other assertion

        Elements prevA = els.prevAll();
        assertEquals(6, prevA.size());
        }

@Test public void siblings_20_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements prevF = els.prev("p:contains(3)");
        // removed other assertion
        // removed other assertion

        Elements prevA = els.prevAll();
        // removed other assertion
        assertEquals("3", prevA.first().text());
        }

@Test public void siblings_21_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements prevF = els.prev("p:contains(3)");
        // removed other assertion
        // removed other assertion

        Elements prevA = els.prevAll();
        // removed other assertion
        // removed other assertion
        assertEquals("7", prevA.last().text());
        }

@Test public void siblings_22_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements prevF = els.prev("p:contains(3)");
        // removed other assertion
        // removed other assertion

        Elements prevA = els.prevAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements prevAF = els.prevAll("p:contains(1)");
        assertEquals(1, prevAF.size());
        }

@Test public void siblings_23_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12</div>");

        Elements els = doc.select("p:eq(3)"); // gets p4 and p10
        // removed other assertion

        Elements next = els.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements nextF = els.next("p:contains(5)");
        // removed other assertion
        // removed other assertion

        Elements nextA = els.nextAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements nextAF = els.nextAll("p:contains(6)");
        // removed other assertion
        // removed other assertion

        Elements prev = els.prev();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        final Elements prevF = els.prev("p:contains(3)");
        // removed other assertion
        // removed other assertion

        Elements prevA = els.prevAll();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements prevAF = els.prevAll("p:contains(1)");
        // removed other assertion
        assertEquals("1", prevAF.first().text());
        }

@Test public void eachText_1_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12<p></p></div>");
        List<String> divText = doc.select("div").eachText();
        assertEquals(2, divText.size());
        }

@Test public void eachText_2_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12<p></p></div>");
        List<String> divText = doc.select("div").eachText();
        // removed other assertion
        assertEquals("1 2 3 4 5 6", divText.get(0));
        }

@Test public void eachText_3_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12<p></p></div>");
        List<String> divText = doc.select("div").eachText();
        // removed other assertion
        // removed other assertion
        assertEquals("7 8 9 10 11 12", divText.get(1));
        }

@Test public void eachText_4_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12<p></p></div>");
        List<String> divText = doc.select("div").eachText();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> pText = doc.select("p").eachText();
        Elements ps = doc.select("p");
        assertEquals(13, ps.size());
        }

@Test public void eachText_5_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12<p></p></div>");
        List<String> divText = doc.select("div").eachText();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> pText = doc.select("p").eachText();
        Elements ps = doc.select("p");
        // removed other assertion
        assertEquals(12,pText.size());// not 13,as last doesn't have text assertEquals("1",pText.get(0));
        }

@Test public void eachText_6_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12<p></p></div>");
        List<String> divText = doc.select("div").eachText();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> pText = doc.select("p").eachText();
        Elements ps = doc.select("p");
        // removed other assertion
        // removed other assertion
        assertEquals("2", pText.get(1));
        }

@Test public void eachText_7_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12<p></p></div>");
        List<String> divText = doc.select("div").eachText();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> pText = doc.select("p").eachText();
        Elements ps = doc.select("p");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", pText.get(4));
        }

@Test public void eachText_8_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12<p></p></div>");
        List<String> divText = doc.select("div").eachText();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> pText = doc.select("p").eachText();
        Elements ps = doc.select("p");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("7", pText.get(6));
        }

@Test public void eachText_9_oe() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3<p>4<p>5<p>6</div><div><p>7<p>8<p>9<p>10<p>11<p>12<p></p></div>");
        List<String> divText = doc.select("div").eachText();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> pText = doc.select("p").eachText();
        Elements ps = doc.select("p");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("12", pText.get(11));
        }

@Test public void eachAttr_1_oe() {
        Document doc = Jsoup.parse(
            "<div><a href='/foo'>1</a><a href='http://example.com/bar'>2</a><a href=''>3</a><a>4</a>",
            "http://example.com");

        List<String> hrefAttrs = doc.select("a").eachAttr("href");
        assertEquals(3, hrefAttrs.size());
        }

@Test public void eachAttr_2_oe() {
        Document doc = Jsoup.parse(
            "<div><a href='/foo'>1</a><a href='http://example.com/bar'>2</a><a href=''>3</a><a>4</a>",
            "http://example.com");

        List<String> hrefAttrs = doc.select("a").eachAttr("href");
        // removed other assertion
        assertEquals("/foo", hrefAttrs.get(0));
        }

@Test public void eachAttr_3_oe() {
        Document doc = Jsoup.parse(
            "<div><a href='/foo'>1</a><a href='http://example.com/bar'>2</a><a href=''>3</a><a>4</a>",
            "http://example.com");

        List<String> hrefAttrs = doc.select("a").eachAttr("href");
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/bar", hrefAttrs.get(1));
        }

@Test public void eachAttr_4_oe() {
        Document doc = Jsoup.parse(
            "<div><a href='/foo'>1</a><a href='http://example.com/bar'>2</a><a href=''>3</a><a>4</a>",
            "http://example.com");

        List<String> hrefAttrs = doc.select("a").eachAttr("href");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", hrefAttrs.get(2));
        }

@Test public void eachAttr_5_oe() {
        Document doc = Jsoup.parse(
            "<div><a href='/foo'>1</a><a href='http://example.com/bar'>2</a><a href=''>3</a><a>4</a>",
            "http://example.com");

        List<String> hrefAttrs = doc.select("a").eachAttr("href");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, doc.select("a").size());
        }

@Test public void eachAttr_6_oe() {
        Document doc = Jsoup.parse(
            "<div><a href='/foo'>1</a><a href='http://example.com/bar'>2</a><a href=''>3</a><a>4</a>",
            "http://example.com");

        List<String> hrefAttrs = doc.select("a").eachAttr("href");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> absAttrs = doc.select("a").eachAttr("abs:href");
        assertEquals(3, absAttrs.size());
        }

@Test public void eachAttr_7_oe() {
        Document doc = Jsoup.parse(
            "<div><a href='/foo'>1</a><a href='http://example.com/bar'>2</a><a href=''>3</a><a>4</a>",
            "http://example.com");

        List<String> hrefAttrs = doc.select("a").eachAttr("href");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> absAttrs = doc.select("a").eachAttr("abs:href");
        // removed other assertion
        assertEquals(3, absAttrs.size());
        }

@Test public void eachAttr_8_oe() {
        Document doc = Jsoup.parse(
            "<div><a href='/foo'>1</a><a href='http://example.com/bar'>2</a><a href=''>3</a><a>4</a>",
            "http://example.com");

        List<String> hrefAttrs = doc.select("a").eachAttr("href");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> absAttrs = doc.select("a").eachAttr("abs:href");
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/foo", absAttrs.get(0));
        }

@Test public void eachAttr_9_oe() {
        Document doc = Jsoup.parse(
            "<div><a href='/foo'>1</a><a href='http://example.com/bar'>2</a><a href=''>3</a><a>4</a>",
            "http://example.com");

        List<String> hrefAttrs = doc.select("a").eachAttr("href");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> absAttrs = doc.select("a").eachAttr("abs:href");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com/bar", absAttrs.get(1));
        }

@Test public void eachAttr_10_oe() {
        Document doc = Jsoup.parse(
            "<div><a href='/foo'>1</a><a href='http://example.com/bar'>2</a><a href=''>3</a><a>4</a>",
            "http://example.com");

        List<String> hrefAttrs = doc.select("a").eachAttr("href");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> absAttrs = doc.select("a").eachAttr("abs:href");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com", absAttrs.get(2));
        }

}
