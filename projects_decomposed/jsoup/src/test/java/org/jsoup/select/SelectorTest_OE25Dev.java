package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.MultiLocaleExtension.MultiLocaleTest;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests that the selector selects correctly.
 *
 * @author Jonathan Hedley, jonathan@hedley.net
 */
public class SelectorTest_OE25Dev {
    @Test public void testByTag() {
        // should be case insensitive
        Elements els = Jsoup.parse("<div id=1><div id=2><p>Hello</p></div></div><DIV id=3>").select("DIV");
        assertEquals(3, els.size());
        assertEquals("1", els.get(0).id());
        assertEquals("2", els.get(1).id());
        assertEquals("3", els.get(2).id());

        Elements none = Jsoup.parse("<div id=1><div id=2><p>Hello</p></div></div><div id=3>").select("span");
        assertEquals(0, none.size());
    }

    @Test public void testById() {
        Elements els = Jsoup.parse("<div><p id=foo>Hello</p><p id=foo>Foo two!</p></div>").select("#foo");
        assertEquals(2, els.size());
        assertEquals("Hello", els.get(0).text());
        assertEquals("Foo two!", els.get(1).text());

        Elements none = Jsoup.parse("<div id=1></div>").select("#foo");
        assertEquals(0, none.size());
    }

    @Test public void testByClass() {
        Elements els = Jsoup.parse("<p id=0 class='ONE two'><p id=1 class='one'><p id=2 class='two'>").select("P.One");
        assertEquals(2, els.size());
        assertEquals("0", els.get(0).id());
        assertEquals("1", els.get(1).id());

        Elements none = Jsoup.parse("<div class='one'></div>").select(".foo");
        assertEquals(0, none.size());

        Elements els2 = Jsoup.parse("<div class='One-Two'></div>").select(".one-two");
        assertEquals(1, els2.size());
    }

    @Test public void testByClassCaseInsensitive() {
        String html = "<p Class=foo>One <p Class=Foo>Two <p class=FOO>Three <p class=farp>Four";
        Elements elsFromClass = Jsoup.parse(html).select("P.Foo");
        Elements elsFromAttr = Jsoup.parse(html).select("p[class=foo]");

        assertEquals(elsFromAttr.size(), elsFromClass.size());
        assertEquals(3, elsFromClass.size());
        assertEquals("Two", elsFromClass.get(1).text());
    }


    @MultiLocaleTest
    public void testByAttribute(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        assertEquals(4, withTitle.size());

        Elements foo = doc.select("[TITLE=foo]");
        assertEquals(1, foo.size());

        Elements foo2 = doc.select("[title=\"foo\"]");
        assertEquals(1, foo2.size());

        Elements foo3 = doc.select("[title=\"Foo\"]");
        assertEquals(1, foo3.size());

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        assertEquals(1, dataName.size());
        assertEquals("with spaces", dataName.first().attr("data-name"));

        Elements not = doc.select("div[title!=bar]");
        assertEquals(5, not.size());
        assertEquals("Foo", not.first().attr("title"));

        Elements starts = doc.select("[title^=ba]");
        assertEquals(2, starts.size());
        assertEquals("Bar", starts.first().attr("title"));
        assertEquals("Balim", starts.last().attr("title"));

        Elements ends = doc.select("[title$=im]");
        assertEquals(2, ends.size());
        assertEquals("Balim", ends.first().attr("title"));
        assertEquals("SLIM", ends.last().attr("title"));

        Elements contains = doc.select("[title*=i]");
        assertEquals(2, contains.size());
        assertEquals("Balim", contains.first().attr("title"));
        assertEquals("SLIM", contains.last().attr("title"));
    }

    @Test public void testNamespacedTag() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        assertEquals(2, byTag.size());
        assertEquals("1", byTag.first().id());
        assertEquals("2", byTag.last().id());

        Elements byAttr = doc.select(".bold");
        assertEquals(1, byAttr.size());
        assertEquals("2", byAttr.last().id());

        Elements byTagAttr = doc.select("abc|def.bold");
        assertEquals(1, byTagAttr.size());
        assertEquals("2", byTagAttr.last().id());

        Elements byContains = doc.select("abc|def:contains(e)");
        assertEquals(2, byContains.size());
        assertEquals("1", byContains.first().id());
        assertEquals("2", byContains.last().id());
    }

    @Test public void testWildcardNamespacedTag() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        assertEquals(2, byTag.size());
        assertEquals("1", byTag.first().id());
        assertEquals("2", byTag.last().id());

        Elements byAttr = doc.select(".bold");
        assertEquals(1, byAttr.size());
        assertEquals("2", byAttr.last().id());

        Elements byTagAttr = doc.select("*|def.bold");
        assertEquals(1, byTagAttr.size());
        assertEquals("2", byTagAttr.last().id());

        Elements byContains = doc.select("*|def:contains(e)");
        assertEquals(2, byContains.size());
        assertEquals("1", byContains.first().id());
        assertEquals("2", byContains.last().id());
    }

    @Test public void testWildcardNamespacedXmlTag() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        assertEquals(2, byTag.size());
        assertEquals("1", byTag.first().id());
        assertEquals("2", byTag.last().id());

        Elements byAttr = doc.select(".bold");
        assertEquals(1, byAttr.size());
        assertEquals("2", byAttr.last().id());

        Elements byTagAttr = doc.select("*|Def.bold");
        assertEquals(1, byTagAttr.size());
        assertEquals("2", byTagAttr.last().id());

        Elements byContains = doc.select("*|Def:contains(e)");
        assertEquals(2, byContains.size());
        assertEquals("1", byContains.first().id());
        assertEquals("2", byContains.last().id());
    }

    @Test public void testWildCardNamespacedCaseVariations() {
        Document doc = Jsoup.parse("<One:Two>One</One:Two><three:four>Two</three:four>", "", Parser.xmlParser());
        Elements els1 = doc.select("One|Two");
        Elements els2 = doc.select("one|two");
        Elements els3 = doc.select("Three|Four");
        Elements els4 = doc.select("three|Four");

        assertEquals(els1, els2);
        assertEquals(els3, els4);
        assertEquals("One", els1.text());
        assertEquals(1, els1.size());
        assertEquals("Two", els3.text());
        assertEquals(1, els2.size());
    }

    @MultiLocaleTest
    public void testByAttributeStarting(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div id=1 ATTRIBUTE data-name=jsoup>Hello</div><p data-val=5 id=2>There</p><p id=3>No</p>");
        Elements withData = doc.select("[^data-]");
        assertEquals(2, withData.size());
        assertEquals("1", withData.first().id());
        assertEquals("2", withData.last().id());

        withData = doc.select("p[^data-]");
        assertEquals(1, withData.size());
        assertEquals("2", withData.first().id());

        assertEquals(1, doc.select("[^attrib]").size());
    }

    @Test public void testByAttributeRegex() {
        Document doc = Jsoup.parse("<p><img src=foo.png id=1><img src=bar.jpg id=2><img src=qux.JPEG id=3><img src=old.gif><img></p>");
        Elements imgs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
        assertEquals(3, imgs.size());
        assertEquals("1", imgs.get(0).id());
        assertEquals("2", imgs.get(1).id());
        assertEquals("3", imgs.get(2).id());
    }

    @Test public void testByAttributeRegexCharacterClass() {
        Document doc = Jsoup.parse("<p><img src=foo.png id=1><img src=bar.jpg id=2><img src=qux.JPEG id=3><img src=old.gif id=4></p>");
        Elements imgs = doc.select("img[src~=[o]]");
        assertEquals(2, imgs.size());
        assertEquals("1", imgs.get(0).id());
        assertEquals("4", imgs.get(1).id());
    }

    @Test public void testByAttributeRegexCombined() {
        Document doc = Jsoup.parse("<div><table class=x><td>Hello</td></table></div>");
        Elements els = doc.select("div table[class~=x|y]");
        assertEquals(1, els.size());
        assertEquals("Hello", els.text());
    }

    @Test public void testCombinedWithContains() {
        Document doc = Jsoup.parse("<p id=1>One</p><p>Two +</p><p>Three +</p>");
        Elements els = doc.select("p#1 + :contains(+)");
        assertEquals(1, els.size());
        assertEquals("Two +", els.text());
        assertEquals("p", els.first().tagName());
    }

    @Test public void testAllElements() {
        String h = "<div><p>Hello</p><p><b>there</b></p></div>";
        Document doc = Jsoup.parse(h);
        Elements allDoc = doc.select("*");
        Elements allUnderDiv = doc.select("div *");
        assertEquals(8, allDoc.size());
        assertEquals(3, allUnderDiv.size());
        assertEquals("p", allUnderDiv.first().tagName());
    }

    @Test public void testAllWithClass() {
        String h = "<p class=first>One<p class=first>Two<p>Three";
        Document doc = Jsoup.parse(h);
        Elements ps = doc.select("*.first");
        assertEquals(2, ps.size());
    }

    @Test public void testGroupOr() {
        String h = "<div title=foo /><div title=bar /><div /><p></p><img /><span title=qux>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("p,div,[title]");

        assertEquals(5, els.size());
        assertEquals("div", els.get(0).tagName());
        assertEquals("foo", els.get(0).attr("title"));
        assertEquals("div", els.get(1).tagName());
        assertEquals("bar", els.get(1).attr("title"));
        assertEquals("div", els.get(2).tagName());
        assertEquals(0,els.get(2).attr("title").length());// missing attributes come back as empty string assertFalse(els.get(2).hasAttr("title"));
        assertEquals("p", els.get(3).tagName());
        assertEquals("span", els.get(4).tagName());
    }

    @Test public void testGroupOrAttribute() {
        String h = "<div id=1 /><div id=2 /><div title=foo /><div title=bar />";
        Elements els = Jsoup.parse(h).select("[id],[title=foo]");

        assertEquals(3, els.size());
        assertEquals("1", els.get(0).id());
        assertEquals("2", els.get(1).id());
        assertEquals("foo", els.get(2).attr("title"));
    }

    @Test public void descendant() {
        String h = "<div class=head><p class=first>Hello</p><p>There</p></div><p>None</p>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("HEAD").first();

        Elements els = root.select(".head p");
        assertEquals(2, els.size());
        assertEquals("Hello", els.get(0).text());
        assertEquals("There", els.get(1).text());

        Elements p = root.select("p.first");
        assertEquals(1, p.size());
        assertEquals("Hello", p.get(0).text());

        Elements empty = root.select("p .first"); // self, not descend, should not match
        assertEquals(0, empty.size());

        Elements aboveRoot = root.select("body div.head");
        assertEquals(0, aboveRoot.size());
    }

    @Test public void and() {
        String h = "<div id=1 class='foo bar' title=bar name=qux><p class=foo title=bar>Hello</p></div";
        Document doc = Jsoup.parse(h);

        Elements div = doc.select("div.foo");
        assertEquals(1, div.size());
        assertEquals("div", div.first().tagName());

        Elements p = doc.select("div .foo"); // space indicates like "div *.foo"
        assertEquals(1, p.size());
        assertEquals("p", p.first().tagName());

        Elements div2 = doc.select("div#1.foo.bar[title=bar][name=qux]"); // very specific!
        assertEquals(1, div2.size());
        assertEquals("div", div2.first().tagName());

        Elements p2 = doc.select("div *.foo"); // space indicates like "div *.foo"
        assertEquals(1, p2.size());
        assertEquals("p", p2.first().tagName());
    }

    @Test public void deeperDescendant() {
        String h = "<div class=head><p><span class=first>Hello</div><div class=head><p class=first><span>Another</span><p>Again</div>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("head").first();

        Elements els = root.select("div p .first");
        assertEquals(1, els.size());
        assertEquals("Hello", els.first().text());
        assertEquals("span", els.first().tagName());

        Elements aboveRoot = root.select("body p .first");
        assertEquals(0, aboveRoot.size());
    }

    @Test public void parentChildElement() {
        String h = "<div id=1><div id=2><div id = 3></div></div></div><div id=4></div>";
        Document doc = Jsoup.parse(h);

        Elements divs = doc.select("div > div");
        assertEquals(2, divs.size());
        assertEquals("2",divs.get(0).id());// 2 is child of 1 
        assertEquals("3",divs.get(1).id());// 3 is child of 2 
        Elements div2 = doc.select("div#1 > div");
        assertEquals(1, div2.size());
        assertEquals("2", div2.get(0).id());
    }

    @Test public void parentWithClassChild() {
        String h = "<h1 class=foo><a href=1 /></h1><h1 class=foo><a href=2 class=bar /></h1><h1><a href=3 /></h1>";
        Document doc = Jsoup.parse(h);

        Elements allAs = doc.select("h1 > a");
        assertEquals(3, allAs.size());
        assertEquals("a", allAs.first().tagName());

        Elements fooAs = doc.select("h1.foo > a");
        assertEquals(2, fooAs.size());
        assertEquals("a", fooAs.first().tagName());

        Elements barAs = doc.select("h1.foo > a.bar");
        assertEquals(1, barAs.size());
    }

    @Test public void parentChildStar() {
        String h = "<div id=1><p>Hello<p><b>there</b></p></div><div id=2><span>Hi</span></div>";
        Document doc = Jsoup.parse(h);
        Elements divChilds = doc.select("div > *");
        assertEquals(3, divChilds.size());
        assertEquals("p", divChilds.get(0).tagName());
        assertEquals("p", divChilds.get(1).tagName());
        assertEquals("span", divChilds.get(2).tagName());
    }

    @Test public void multiChildDescent() {
        String h = "<div id=foo><h1 class=bar><a href=http://example.com/>One</a></h1></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("div#foo > h1.bar > a[href*=example]");
        assertEquals(1, els.size());
        assertEquals("a", els.first().tagName());
    }

    @Test public void caseInsensitive() {
        String h = "<dIv tItle=bAr><div>"; // mixed case so a simple toLowerCase() on value doesn't catch
        Document doc = Jsoup.parse(h);

        assertEquals(2, doc.select("DiV").size());
        assertEquals(1, doc.select("DiV[TiTLE]").size());
        assertEquals(1, doc.select("DiV[TiTLE=BAR]").size());
        assertEquals(0, doc.select("DiV[TiTLE=BARBARELLA]").size());
    }

    @Test public void adjacentSiblings() {
        String h = "<ol><li>One<li>Two<li>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("li + li");
        assertEquals(2, sibs.size());
        assertEquals("Two", sibs.get(0).text());
        assertEquals("Three", sibs.get(1).text());
    }

    @Test public void adjacentSiblingsWithId() {
        String h = "<ol><li id=1>One<li id=2>Two<li id=3>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("li#1 + li#2");
        assertEquals(1, sibs.size());
        assertEquals("Two", sibs.get(0).text());
    }

    @Test public void notAdjacent() {
        String h = "<ol><li id=1>One<li id=2>Two<li id=3>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("li#1 + li#3");
        assertEquals(0, sibs.size());
    }

    @Test public void mixCombinator() {
        String h = "<div class=foo><ol><li>One<li>Two<li>Three</ol></div>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("body > div.foo li + li");

        assertEquals(2, sibs.size());
        assertEquals("Two", sibs.get(0).text());
        assertEquals("Three", sibs.get(1).text());
    }

    @Test public void mixCombinatorGroup() {
        String h = "<div class=foo><ol><li>One<li>Two<li>Three</ol></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select(".foo > ol, ol > li + li");

        assertEquals(3, els.size());
        assertEquals("ol", els.get(0).tagName());
        assertEquals("Two", els.get(1).text());
        assertEquals("Three", els.get(2).text());
    }

    @Test public void generalSiblings() {
        String h = "<ol><li id=1>One<li id=2>Two<li id=3>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("#1 ~ #3");
        assertEquals(1, els.size());
        assertEquals("Three", els.first().text());
    }

    // for http://github.com/jhy/jsoup/issues#issue/10
    @Test public void testCharactersInIdAndClass() {
        // using CSS spec for identifiers (id and class): a-z0-9, -, _. NOT . (which is OK in html spec, but not css)
        String h = "<div><p id='a1-foo_bar'>One</p><p class='b2-qux_bif'>Two</p></div>";
        Document doc = Jsoup.parse(h);

        Element el1 = doc.getElementById("a1-foo_bar");
        assertEquals("One", el1.text());
        Element el2 = doc.getElementsByClass("b2-qux_bif").first();
        assertEquals("Two", el2.text());

        Element el3 = doc.select("#a1-foo_bar").first();
        assertEquals("One", el3.text());
        Element el4 = doc.select(".b2-qux_bif").first();
        assertEquals("Two", el4.text());
    }

    // for http://github.com/jhy/jsoup/issues#issue/13
    @Test public void testSupportsLeadingCombinator() {
        String h = "<div><p><span>One</span><span>Two</span></p></div>";
        Document doc = Jsoup.parse(h);

        Element p = doc.select("div > p").first();
        Elements spans = p.select("> span");
        assertEquals(2, spans.size());
        assertEquals("One", spans.first().text());

        // make sure doesn't get nested
        h = "<div id=1><div id=2><div id=3></div></div></div>";
        doc = Jsoup.parse(h);
        Element div = doc.select("div").select(" > div").first();
        assertEquals("2", div.id());
    }

    @Test public void testPseudoLessThan() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:lt(2)");
        assertEquals(3, ps.size());
        assertEquals("One", ps.get(0).text());
        assertEquals("Two", ps.get(1).text());
        assertEquals("Four", ps.get(2).text());
    }

    @Test public void testPseudoGreaterThan() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:gt(0)");
        assertEquals(2, ps.size());
        assertEquals("Two", ps.get(0).text());
        assertEquals("Three", ps.get(1).text());
    }

    @Test public void testPseudoEquals() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:eq(0)");
        assertEquals(2, ps.size());
        assertEquals("One", ps.get(0).text());
        assertEquals("Four", ps.get(1).text());

        Elements ps2 = doc.select("div:eq(0) p:eq(0)");
        assertEquals(1, ps2.size());
        assertEquals("One", ps2.get(0).text());
        assertEquals("p", ps2.get(0).tagName());
    }

    @Test public void testPseudoBetween() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:gt(0):lt(2)");
        assertEquals(1, ps.size());
        assertEquals("Two", ps.get(0).text());
    }

    @Test public void testPseudoCombined() {
        Document doc = Jsoup.parse("<div class='foo'><p>One</p><p>Two</p></div><div><p>Three</p><p>Four</p></div>");
        Elements ps = doc.select("div.foo p:gt(0)");
        assertEquals(1, ps.size());
        assertEquals("Two", ps.get(0).text());
    }

    @Test public void testPseudoHas() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        assertEquals(2, divs1.size());
        assertEquals("0", divs1.get(0).id());
        assertEquals("1", divs1.get(1).id());

        Elements divs2 = doc.select("div:has([class])");
        assertEquals(1, divs2.size());
        assertEquals("1", divs2.get(0).id());

        Elements divs3 = doc.select("div:has(span, p)");
        assertEquals(3, divs3.size());
        assertEquals("0", divs3.get(0).id());
        assertEquals("1", divs3.get(1).id());
        assertEquals("2", divs3.get(2).id());

        Elements els1 = doc.body().select(":has(p)");
        assertEquals(3,els1.size());// body,div,div assertEquals("body",els1.first().tagName());
        assertEquals("0", els1.get(1).id());
        assertEquals("2", els1.get(2).id());

        Elements els2 = doc.body().select(":has(> span)");
        assertEquals(2,els2.size());// p,div assertEquals("p",els2.first().tagName());
        assertEquals("1", els2.get(1).id());
    }

    @Test public void testNestedHas() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p></div> <div><p>Two</p></div>");
        Elements divs = doc.select("div:has(p:has(span))");
        assertEquals(1, divs.size());
        assertEquals("One", divs.first().text());

        // test matches in has
        divs = doc.select("div:has(p:matches((?i)two))");
        assertEquals(1, divs.size());
        assertEquals("div", divs.first().tagName());
        assertEquals("Two", divs.first().text());

        // test contains in has
        divs = doc.select("div:has(p:contains(two))");
        assertEquals(1, divs.size());
        assertEquals("div", divs.first().tagName());
        assertEquals("Two", divs.first().text());
    }

    @MultiLocaleTest
    public void testPseudoContains(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        assertEquals(3, ps1.size());

        Elements ps2 = doc.select("p:contains(the rain)");
        assertEquals(2, ps2.size());
        assertEquals("The Rain.", ps2.first().html());
        assertEquals("The <i>RAIN</i>.", ps2.last().html());

        Elements ps3 = doc.select("p:contains(the Rain):has(i)");
        assertEquals(1, ps3.size());
        assertEquals("light", ps3.first().className());

        Elements ps4 = doc.select(".light:contains(rain)");
        assertEquals(1, ps4.size());
        assertEquals("light", ps3.first().className());

        Elements ps5 = doc.select(":contains(rain)");
        assertEquals(8,ps5.size());// html,body,div,... 
        Elements ps6 = doc.select(":contains(RAIN)");
        assertEquals(8, ps6.size());
    }

    @Test public void testPsuedoContainsWithParentheses() {
        Document doc = Jsoup.parse("<div><p id=1>This (is good)</p><p id=2>This is bad)</p>");

        Elements ps1 = doc.select("p:contains(this (is good))");
        assertEquals(1, ps1.size());
        assertEquals("1", ps1.first().id());

        Elements ps2 = doc.select("p:contains(this is bad\\))");
        assertEquals(1, ps2.size());
        assertEquals("2", ps2.first().id());
    }

    @Test void containsWholeText() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser</div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeText( jsoup\n The HTML Parser)");
        Elements es2 = doc.select("p:containsWholeText(jsoup The HTML Parser)");
        assertEquals(1, es1.size());
        assertEquals(1, es2.size());
        assertEquals(ps.get(0), es1.first());
        assertEquals(ps.get(1), es2.first());

        assertEquals(0, doc.select("div:containsWholeText(jsoup the html parser)").size());
        assertEquals(0, doc.select("div:containsWholeText(jsoup\n the html parser)").size());

        doc = Jsoup.parse("<div><p></p><p> </p><p>.  </p>");
        Elements blanks = doc.select("p:containsWholeText(  )");
        assertEquals(1, blanks.size());
        assertEquals(".  ", blanks.first().wholeText());
    }

    @Test void containsWholeOwnText() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser<br></div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeOwnText( jsoup\n The  Parser)");
        Elements es2 = doc.select("p:containsWholeOwnText(jsoup The HTML Parser\n)");
        assertEquals(1, es1.size());
        assertEquals(1, es2.size());
        assertEquals(ps.get(0), es1.first());
        assertEquals(ps.get(1), es2.first());

        assertEquals(0, doc.select("div:containsWholeOwnText(jsoup the html parser)").size());
        assertEquals(0, doc.select("div:containsWholeOwnText(jsoup\n the  parser)").size());

        doc = Jsoup.parse("<div><p></p><p> </p><p>.  </p>");
        Elements blanks = doc.select("p:containsWholeOwnText(  )");
        assertEquals(1, blanks.size());
        assertEquals(".  ", blanks.first().wholeText());
    }

    @MultiLocaleTest
    public void containsOwn(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b> igor</p>");
        Elements ps = doc.select("p:containsOwn(Hello IGOR)");
        assertEquals(1, ps.size());
        assertEquals("1", ps.first().id());

        assertEquals(0, doc.select("p:containsOwn(there)").size());

        Document doc2 = Jsoup.parse("<p>Hello <b>there</b> IGOR</p>");
        assertEquals(1, doc2.select("p:containsOwn(igor)").size());

    }

    @Test public void testMatches() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        assertEquals(0, p1.size());

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        assertEquals(1, p2.size());
        assertEquals("1", p2.first().id());

        Elements p4 = doc.select("p:matches((?i)^rain$)"); // bounding
        assertEquals(1, p4.size());
        assertEquals("4", p4.first().id());

        Elements p5 = doc.select("p:matches(\\d+)");
        assertEquals(1, p5.size());
        assertEquals("2", p5.first().id());

        Elements p6 = doc.select("p:matches(\\w+\\s+\\(\\w+\\))"); // test bracket matching
        assertEquals(1, p6.size());
        assertEquals("3", p6.first().id());

        Elements p7 = doc.select("p:matches((?i)the):has(i)"); // multi
        assertEquals(1, p7.size());
        assertEquals("1", p7.first().id());
    }

    @Test public void matchesOwn() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b> now</p>");

        Elements p1 = doc.select("p:matchesOwn((?i)hello now)");
        assertEquals(1, p1.size());
        assertEquals("1", p1.first().id());

        assertEquals(0, doc.select("p:matchesOwn(there)").size());
    }

    @Test public void matchesWholeText() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3></p>");

        Elements p1 = doc.select("p:matchesWholeText((?i)hello there\n now)");
        assertEquals(1, p1.size());
        assertEquals("1", p1.first().id());

        assertEquals(1, doc.select("p:matchesWholeText(there\n now)").size());
        assertEquals(0, doc.select("p:matchesWholeText(There\n now)").size());

        Elements p2 = doc.select("p:matchesWholeText(^\\s+$)");
        assertEquals(1, p2.size());
        assertEquals("2", p2.first().id());

        Elements p3 = doc.select("p:matchesWholeText(^$)");
        assertEquals(1, p3.size());
        assertEquals("3", p3.first().id());
    }

    @Test public void matchesWholeOwnText() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3><i>Text</i></p>");

        Elements p1 = doc.select("p:matchesWholeOwnText((?i)hello \n now)");
        assertEquals(1, p1.size());
        assertEquals("1", p1.first().id());

        assertEquals(0, doc.select("p:matchesWholeOwnText(there\n now)").size());

        Elements p2 = doc.select("p:matchesWholeOwnText(^\\s+$)");
        assertEquals(1, p2.size());
        assertEquals("2", p2.first().id());

        Elements p3 = doc.select("p:matchesWholeOwnText(^$)");
        assertEquals(1, p3.size());
        assertEquals("3", p3.first().id());
    }

    @Test public void testRelaxedTags() {
        Document doc = Jsoup.parse("<abc_def id=1>Hello</abc_def> <abc-def id=2>There</abc-def>");

        Elements el1 = doc.select("abc_def");
        assertEquals(1, el1.size());
        assertEquals("1", el1.first().id());

        Elements el2 = doc.select("abc-def");
        assertEquals(1, el2.size());
        assertEquals("2", el2.first().id());
    }

    @Test public void notParas() {
        Document doc = Jsoup.parse("<p id=1>One</p> <p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.select("p:not([id=1])");
        assertEquals(2, el1.size());
        assertEquals("Two", el1.first().text());
        assertEquals("Three", el1.last().text());

        Elements el2 = doc.select("p:not(:has(span))");
        assertEquals(2, el2.size());
        assertEquals("One", el2.first().text());
        assertEquals("Two", el2.last().text());
    }

    @Test public void notAll() {
        Document doc = Jsoup.parse("<p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.body().select(":not(p)"); // should just be the span
        assertEquals(2, el1.size());
        assertEquals("body", el1.first().tagName());
        assertEquals("span", el1.last().tagName());
    }

    @Test public void notClass() {
        Document doc = Jsoup.parse("<div class=left>One</div><div class=right id=1><p>Two</p></div>");

        Elements el1 = doc.select("div:not(.left)");
        assertEquals(1, el1.size());
        assertEquals("1", el1.first().id());
    }

    @Test public void handlesCommasInSelector() {
        Document doc = Jsoup.parse("<p name='1,2'>One</p><div>Two</div><ol><li>123</li><li>Text</li></ol>");

        Elements ps = doc.select("[name=1,2]");
        assertEquals(1, ps.size());

        Elements containers = doc.select("div, li:matches([0-9,]+)");
        assertEquals(2, containers.size());
        assertEquals("div", containers.get(0).tagName());
        assertEquals("li", containers.get(1).tagName());
        assertEquals("123", containers.get(1).text());
    }

    @Test public void selectSupplementaryCharacter() {
        String s = new String(Character.toChars(135361));
        Document doc = Jsoup.parse("<div k" + s + "='" + s + "'>^" + s +"$/div>");
        assertEquals("div", doc.select("div[k" + s + "]").first().tagName());
        assertEquals("div", doc.select("div:containsOwn(" + s + ")").first().tagName());
    }

    @Test
    public void selectClassWithSpace() {
        final String html = "<div class=\"value\">class without space</div>\n"
                          + "<div class=\"value \">class with space</div>";

        Document doc = Jsoup.parse(html);

        Elements found = doc.select("div[class=value ]");
        assertEquals(2, found.size());
        assertEquals("class without space", found.get(0).text());
        assertEquals("class with space", found.get(1).text());

        found = doc.select("div[class=\"value \"]");
        assertEquals(2, found.size());
        assertEquals("class without space", found.get(0).text());
        assertEquals("class with space", found.get(1).text());

        found = doc.select("div[class=\"value\\ \"]");
        assertEquals(0, found.size());
    }

    @Test public void selectSameElements() {
        final String html = "<div>one</div><div>one</div>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("div");
        assertEquals(2, els.size());

        Elements subSelect = els.select(":contains(one)");
        assertEquals(2, subSelect.size());
    }

    @Test public void attributeWithBrackets() {
        String html = "<div data='End]'>One</div> <div data='[Another)]]'>Two</div>";
        Document doc = Jsoup.parse(html);
        assertEquals("One", doc.select("div[data='End]']").first().text());
        assertEquals("Two", doc.select("div[data='[Another)]]']").first().text());
        assertEquals("One", doc.select("div[data=\"End]\"]").first().text());
        assertEquals("Two", doc.select("div[data=\"[Another)]]\"]").first().text());
    }

    @MultiLocaleTest
    public void containsData(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        assertEquals(2,dataEls1.size());// body and script assertEquals(1,dataEls2.size());
        assertEquals(dataEls1.last(), dataEls2.first());
        assertEquals("<script>FUNCTION</script>", dataEls2.outerHtml());
        assertEquals(1, dataEls3.size());
        assertEquals("span", dataEls3.first().tagName());
        assertEquals(3, dataEls4.size());
        assertEquals("body", dataEls4.first().tagName());
        assertEquals("script", dataEls4.get(1).tagName());
        assertEquals("span", dataEls4.get(2).tagName());
        assertEquals(1, dataEls5.size());
    }

    @Test public void containsWithQuote() {
        String html = "<p>One'One</p><p>One'Two</p>";
        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p:contains(One\\'One)");
        assertEquals(1, els.size());
        assertEquals("One'One", els.text());
    }

    @Test public void selectFirst() {
        String html = "<p>One<p>Two<p>Three";
        Document doc = Jsoup.parse(html);
        assertEquals("One", doc.selectFirst("p").text());
    }

    @Test public void selectFirstWithAnd() {
        String html = "<p>One<p class=foo>Two<p>Three";
        Document doc = Jsoup.parse(html);
        assertEquals("Two", doc.selectFirst("p.foo").text());
    }

    @Test public void selectFirstWithOr() {
        String html = "<p>One<p>Two<p>Three<div>Four";
        Document doc = Jsoup.parse(html);
        assertEquals("One", doc.selectFirst("p, div").text());
    }

    @Test public void matchText() {
        String html = "<p>One<br>Two</p>";
        Document doc = Jsoup.parse(html);
        String origHtml = doc.html();

        Elements one = doc.select("p:matchText:first-child");
        assertEquals("One", one.first().text());

        Elements two = doc.select("p:matchText:last-child");
        assertEquals("Two", two.first().text());

        assertEquals(origHtml, doc.html());

        assertEquals("Two", doc.select("p:matchText + br + *").text());
    }

    @Test public void nthLastChildWithNoParent() {
        Element el = new Element("p").text("Orphan");
        Elements els = el.select("p:nth-last-child(1)");
        assertEquals(0, els.size());
    }

    @Test public void splitOnBr() {
        String html = "<div><p>One<br>Two<br>Three</p></div>";
        Document doc = Jsoup.parse(html);

        Elements els = doc.select("p:matchText");
        assertEquals(3, els.size());
        assertEquals("One", els.get(0).text());
        assertEquals("Two", els.get(1).text());
        assertEquals("Three", els.get(2).toString());
    }

    @Test public void matchTextAttributes() {
        Document doc = Jsoup.parse("<div><p class=one>One<br>Two<p class=two>Three<br>Four");
        Elements els = doc.select("p.two:matchText:last-child");

        assertEquals(1, els.size());
        assertEquals("Four", els.text());
    }

    @Test public void findBetweenSpan() {
        Document doc = Jsoup.parse("<p><span>One</span> Two <span>Three</span>");
        Elements els = doc.select("span ~ p:matchText"); // the Two becomes its own p, sibling of the span
        // todo - think this should really be 'p:matchText span ~ p'. The :matchText should behave as a modifier to expand the nodes.

        assertEquals(1, els.size());
        assertEquals("Two", els.text());
    }

    @Test public void startsWithBeginsWithSpace() {
        Document doc = Jsoup.parse("<small><a href=\" mailto:abc@def.net\">(abc@def.net)</a></small>");
        Elements els = doc.select("a[href^=' mailto']");

        assertEquals(1, els.size());
    }

    @Test public void endsWithEndsWithSpaces() {
        Document doc = Jsoup.parse("<small><a href=\" mailto:abc@def.net \">(abc@def.net)</a></small>");
        Elements els = doc.select("a[href$='.net ']");

        assertEquals(1, els.size());
    }

    // https://github.com/jhy/jsoup/issues/1257
    private final String mixedCase =
        "<html xmlns:n=\"urn:ns\"><n:mixedCase>text</n:mixedCase></html>";
    private final String lowercase =
        "<html xmlns:n=\"urn:ns\"><n:lowercase>text</n:lowercase></html>";

    @Test
    public void html_mixed_case_simple_name() {
        Document doc = Jsoup.parse(mixedCase, "", Parser.htmlParser());
        assertEquals(0, doc.select("mixedCase").size());
    }

    @Test
    public void html_mixed_case_wildcard_name() {
        Document doc = Jsoup.parse(mixedCase, "", Parser.htmlParser());
        assertEquals(1, doc.select("*|mixedCase").size());
    }

    @Test
    public void html_lowercase_simple_name() {
        Document doc = Jsoup.parse(lowercase, "", Parser.htmlParser());
        assertEquals(0, doc.select("lowercase").size());
    }

    @Test
    public void html_lowercase_wildcard_name() {
        Document doc = Jsoup.parse(lowercase, "", Parser.htmlParser());
        assertEquals(1, doc.select("*|lowercase").size());
    }

    @Test
    public void xml_mixed_case_simple_name() {
        Document doc = Jsoup.parse(mixedCase, "", Parser.xmlParser());
        assertEquals(0, doc.select("mixedCase").size());
    }

    @Test
    public void xml_mixed_case_wildcard_name() {
        Document doc = Jsoup.parse(mixedCase, "", Parser.xmlParser());
        assertEquals(1, doc.select("*|mixedCase").size());
    }

    @Test
    public void xml_lowercase_simple_name() {
        Document doc = Jsoup.parse(lowercase, "", Parser.xmlParser());
        assertEquals(0, doc.select("lowercase").size());
    }

    @Test
    public void xml_lowercase_wildcard_name() {
        Document doc = Jsoup.parse(lowercase, "", Parser.xmlParser());
        assertEquals(1, doc.select("*|lowercase").size());
    }

    @Test
    public void trimSelector() {
        // https://github.com/jhy/jsoup/issues/1274
        Document doc = Jsoup.parse("<p><span>Hello");
        Elements els = doc.select(" p span ");
        assertEquals(1, els.size());
        assertEquals("Hello", els.first().text());
    }

    @Test
    public void xmlWildcardNamespaceTest() {
        // https://github.com/jhy/jsoup/issues/1208
        Document doc = Jsoup.parse("<ns1:MyXmlTag>1111</ns1:MyXmlTag><ns2:MyXmlTag>2222</ns2:MyXmlTag>", "", Parser.xmlParser());
        Elements select = doc.select("*|MyXmlTag");
        assertEquals(2, select.size());
        assertEquals("1111", select.get(0).text());
        assertEquals("2222", select.get(1).text());
    }

    @Test
    public void childElements() {
        // https://github.com/jhy/jsoup/issues/1292
        String html = "<body><span id=1>One <span id=2>Two</span></span></body>";
        Document doc = Jsoup.parse(html);

        Element outer = doc.selectFirst("span");
        Element span = outer.selectFirst("span");
        Element inner = outer.selectFirst("* span");

        assertEquals("1", outer.id());
        assertEquals("1", span.id());
        assertEquals("2", inner.id());
        assertEquals(outer, span);
        assertNotEquals(outer, inner);
    }

    @Test
    public void selectFirstLevelChildrenOnly() {
        // testcase for https://github.com/jhy/jsoup/issues/984
        String html = "<div><span>One <span>Two</span></span> <span>Three <span>Four</span></span>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        assertNotNull(div);

        // want to select One and Three only - the first level children
        Elements spans = div.select(":root > span");
        assertEquals(2, spans.size());
        assertEquals("One Two", spans.get(0).text());
        assertEquals("Three Four", spans.get(1).text());
    }

    @Test
    public void wildcardNamespaceMatchesNoNamespace() {
        // https://github.com/jhy/jsoup/issues/1565
        String xml = "<package><meta>One</meta><opf:meta>Two</opf:meta></package>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Elements metaEls = doc.select("meta");
        assertEquals(1, metaEls.size());
        assertEquals("One", metaEls.get(0).text());

        Elements nsEls = doc.select("*|meta");
        assertEquals(2, nsEls.size());
        assertEquals("One", nsEls.get(0).text());
        assertEquals("Two", nsEls.get(1).text());
    }

    @Test void containsTextQueryIsNormalized() {
        Document doc = Jsoup.parse("<p><p id=1>Hello  there now<em>!</em>");
        Elements a = doc.select("p:contains(Hello   there  now!)");
        Elements b = doc.select(":containsOwn(hello   there  now)");
        Elements c = doc.select("p:contains(Hello there now)");
        Elements d = doc.select(":containsOwn(hello There now)");
        Elements e = doc.select("p:contains(HelloThereNow)");

        assertEquals(1, a.size());
        assertEquals(a, b);
        assertEquals(a, c);
        assertEquals(a, d);
        assertEquals(0, e.size());
        assertNotEquals(a, e);
    }

    @Test public void selectorExceptionNotStringFormatException() {
        Selector.SelectorParseException ex = new Selector.SelectorParseException("%&");
        assertEquals("%&", ex.getMessage());
    }

    @Test public void testByTag_1_oe() {
        // should be case insensitive
        Elements els = Jsoup.parse("<div id=1><div id=2><p>Hello</p></div></div><DIV id=3>").select("DIV");
        assertEquals(3, els.size());
        }

    @Test public void testByTag_2_oe() {
        // should be case insensitive
        Elements els = Jsoup.parse("<div id=1><div id=2><p>Hello</p></div></div><DIV id=3>").select("DIV");
        // removed other assertion
        assertEquals("1", els.get(0).id());
        }

    @Test public void testByTag_3_oe() {
        // should be case insensitive
        Elements els = Jsoup.parse("<div id=1><div id=2><p>Hello</p></div></div><DIV id=3>").select("DIV");
        // removed other assertion
        // removed other assertion
        assertEquals("2", els.get(1).id());
        }

    @Test public void testByTag_4_oe() {
        // should be case insensitive
        Elements els = Jsoup.parse("<div id=1><div id=2><p>Hello</p></div></div><DIV id=3>").select("DIV");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", els.get(2).id());
        }

    @Test public void testByTag_5_oe() {
        // should be case insensitive
        Elements els = Jsoup.parse("<div id=1><div id=2><p>Hello</p></div></div><DIV id=3>").select("DIV");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements none = Jsoup.parse("<div id=1><div id=2><p>Hello</p></div></div><div id=3>").select("span");
        assertEquals(0, none.size());
        }

    @Test public void testById_1_oe() {
        Elements els = Jsoup.parse("<div><p id=foo>Hello</p><p id=foo>Foo two!</p></div>").select("#foo");
        assertEquals(2, els.size());
        }

    @Test public void testById_2_oe() {
        Elements els = Jsoup.parse("<div><p id=foo>Hello</p><p id=foo>Foo two!</p></div>").select("#foo");
        // removed other assertion
        assertEquals("Hello", els.get(0).text());
        }

    @Test public void testById_3_oe() {
        Elements els = Jsoup.parse("<div><p id=foo>Hello</p><p id=foo>Foo two!</p></div>").select("#foo");
        // removed other assertion
        // removed other assertion
        assertEquals("Foo two!", els.get(1).text());
        }

    @Test public void testById_4_oe() {
        Elements els = Jsoup.parse("<div><p id=foo>Hello</p><p id=foo>Foo two!</p></div>").select("#foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements none = Jsoup.parse("<div id=1></div>").select("#foo");
        assertEquals(0, none.size());
        }

    @Test public void testByClass_1_oe() {
        Elements els = Jsoup.parse("<p id=0 class='ONE two'><p id=1 class='one'><p id=2 class='two'>").select("P.One");
        assertEquals(2, els.size());
        }

    @Test public void testByClass_2_oe() {
        Elements els = Jsoup.parse("<p id=0 class='ONE two'><p id=1 class='one'><p id=2 class='two'>").select("P.One");
        // removed other assertion
        assertEquals("0", els.get(0).id());
        }

    @Test public void testByClass_3_oe() {
        Elements els = Jsoup.parse("<p id=0 class='ONE two'><p id=1 class='one'><p id=2 class='two'>").select("P.One");
        // removed other assertion
        // removed other assertion
        assertEquals("1", els.get(1).id());
        }

    @Test public void testByClass_4_oe() {
        Elements els = Jsoup.parse("<p id=0 class='ONE two'><p id=1 class='one'><p id=2 class='two'>").select("P.One");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements none = Jsoup.parse("<div class='one'></div>").select(".foo");
        assertEquals(0, none.size());
        }

    @Test public void testByClass_5_oe() {
        Elements els = Jsoup.parse("<p id=0 class='ONE two'><p id=1 class='one'><p id=2 class='two'>").select("P.One");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements none = Jsoup.parse("<div class='one'></div>").select(".foo");
        // removed other assertion

        Elements els2 = Jsoup.parse("<div class='One-Two'></div>").select(".one-two");
        assertEquals(1, els2.size());
        }

    @Test public void testByClassCaseInsensitive_1_oe() {
        String html = "<p Class=foo>One <p Class=Foo>Two <p class=FOO>Three <p class=farp>Four";
        Elements elsFromClass = Jsoup.parse(html).select("P.Foo");
        Elements elsFromAttr = Jsoup.parse(html).select("p[class=foo]");

        assertEquals(elsFromAttr.size(), elsFromClass.size());
        }

    @Test public void testByClassCaseInsensitive_2_oe() {
        String html = "<p Class=foo>One <p Class=Foo>Two <p class=FOO>Three <p class=farp>Four";
        Elements elsFromClass = Jsoup.parse(html).select("P.Foo");
        Elements elsFromAttr = Jsoup.parse(html).select("p[class=foo]");

        // removed other assertion
        assertEquals(3, elsFromClass.size());
        }

    @Test public void testByClassCaseInsensitive_3_oe() {
        String html = "<p Class=foo>One <p Class=Foo>Two <p class=FOO>Three <p class=farp>Four";
        Elements elsFromClass = Jsoup.parse(html).select("P.Foo");
        Elements elsFromAttr = Jsoup.parse(html).select("p[class=foo]");

        // removed other assertion
        // removed other assertion
        assertEquals("Two", elsFromClass.get(1).text());
        }

    public void testByAttribute_1_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        assertEquals(4, withTitle.size());
    }

    public void testByAttribute_2_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        assertEquals(1, foo.size());
    }

    public void testByAttribute_3_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        assertEquals(1, foo2.size());
    }

    public void testByAttribute_4_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        assertEquals(1, foo3.size());
    }

    public void testByAttribute_5_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        assertEquals(1, dataName.size());
    }

    public void testByAttribute_6_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        assertEquals("with spaces", dataName.first().attr("data-name"));
    }

    public void testByAttribute_7_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        assertEquals(5, not.size());
    }

    public void testByAttribute_8_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        // removed other assertion
        assertEquals("Foo", not.first().attr("title"));
    }

    public void testByAttribute_9_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        // removed other assertion
        // removed other assertion

        Elements starts = doc.select("[title^=ba]");
        assertEquals(2, starts.size());
    }

    public void testByAttribute_10_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        // removed other assertion
        // removed other assertion

        Elements starts = doc.select("[title^=ba]");
        // removed other assertion
        assertEquals("Bar", starts.first().attr("title"));
    }

    public void testByAttribute_11_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        // removed other assertion
        // removed other assertion

        Elements starts = doc.select("[title^=ba]");
        // removed other assertion
        // removed other assertion
        assertEquals("Balim", starts.last().attr("title"));
    }

    public void testByAttribute_12_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        // removed other assertion
        // removed other assertion

        Elements starts = doc.select("[title^=ba]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ends = doc.select("[title$=im]");
        assertEquals(2, ends.size());
    }

    public void testByAttribute_13_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        // removed other assertion
        // removed other assertion

        Elements starts = doc.select("[title^=ba]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ends = doc.select("[title$=im]");
        // removed other assertion
        assertEquals("Balim", ends.first().attr("title"));
    }

    public void testByAttribute_14_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        // removed other assertion
        // removed other assertion

        Elements starts = doc.select("[title^=ba]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ends = doc.select("[title$=im]");
        // removed other assertion
        // removed other assertion
        assertEquals("SLIM", ends.last().attr("title"));
    }

    public void testByAttribute_15_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        // removed other assertion
        // removed other assertion

        Elements starts = doc.select("[title^=ba]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ends = doc.select("[title$=im]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements contains = doc.select("[title*=i]");
        assertEquals(2, contains.size());
    }

    public void testByAttribute_16_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        // removed other assertion
        // removed other assertion

        Elements starts = doc.select("[title^=ba]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ends = doc.select("[title$=im]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements contains = doc.select("[title*=i]");
        // removed other assertion
        assertEquals("Balim", contains.first().attr("title"));
    }

    public void testByAttribute_17_oe(Locale locale) {
        Locale.setDefault(locale);

        String h = "<div Title=Foo /><div Title=Bar /><div Style=Qux /><div title=Balim /><div title=SLIM />" +
                "<div data-name='with spaces'/>";
        Document doc = Jsoup.parse(h);

        Elements withTitle = doc.select("[title]");
        // removed other assertion

        Elements foo = doc.select("[TITLE=foo]");
        // removed other assertion

        Elements foo2 = doc.select("[title=\"foo\"]");
        // removed other assertion

        Elements foo3 = doc.select("[title=\"Foo\"]");
        // removed other assertion

        Elements dataName = doc.select("[data-name=\"with spaces\"]");
        // removed other assertion
        // removed other assertion

        Elements not = doc.select("div[title!=bar]");
        // removed other assertion
        // removed other assertion

        Elements starts = doc.select("[title^=ba]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ends = doc.select("[title$=im]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements contains = doc.select("[title*=i]");
        // removed other assertion
        // removed other assertion
        assertEquals("SLIM", contains.last().attr("title"));
    }

    @Test public void testNamespacedTag_1_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        assertEquals(2, byTag.size());
        }

    @Test public void testNamespacedTag_2_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        // removed other assertion
        assertEquals("1", byTag.first().id());
        }

    @Test public void testNamespacedTag_3_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        // removed other assertion
        // removed other assertion
        assertEquals("2", byTag.last().id());
        }

    @Test public void testNamespacedTag_4_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        assertEquals(1, byAttr.size());
        }

    @Test public void testNamespacedTag_5_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        assertEquals("2", byAttr.last().id());
        }

    @Test public void testNamespacedTag_6_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("abc|def.bold");
        assertEquals(1, byTagAttr.size());
        }

    @Test public void testNamespacedTag_7_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("abc|def.bold");
        // removed other assertion
        assertEquals("2", byTagAttr.last().id());
        }

    @Test public void testNamespacedTag_8_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("abc|def.bold");
        // removed other assertion
        // removed other assertion

        Elements byContains = doc.select("abc|def:contains(e)");
        assertEquals(2, byContains.size());
        }

    @Test public void testNamespacedTag_9_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("abc|def.bold");
        // removed other assertion
        // removed other assertion

        Elements byContains = doc.select("abc|def:contains(e)");
        // removed other assertion
        assertEquals("1", byContains.first().id());
        }

    @Test public void testNamespacedTag_10_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("abc|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("abc|def.bold");
        // removed other assertion
        // removed other assertion

        Elements byContains = doc.select("abc|def:contains(e)");
        // removed other assertion
        // removed other assertion
        assertEquals("2", byContains.last().id());
        }

    @Test public void testWildcardNamespacedTag_1_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        assertEquals(2, byTag.size());
        }

    @Test public void testWildcardNamespacedTag_2_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        // removed other assertion
        assertEquals("1", byTag.first().id());
        }

    @Test public void testWildcardNamespacedTag_3_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        // removed other assertion
        // removed other assertion
        assertEquals("2", byTag.last().id());
        }

    @Test public void testWildcardNamespacedTag_4_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        assertEquals(1, byAttr.size());
        }

    @Test public void testWildcardNamespacedTag_5_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        assertEquals("2", byAttr.last().id());
        }

    @Test public void testWildcardNamespacedTag_6_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("*|def.bold");
        assertEquals(1, byTagAttr.size());
        }

    @Test public void testWildcardNamespacedTag_7_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("*|def.bold");
        // removed other assertion
        assertEquals("2", byTagAttr.last().id());
        }

    @Test public void testWildcardNamespacedTag_8_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("*|def.bold");
        // removed other assertion
        // removed other assertion

        Elements byContains = doc.select("*|def:contains(e)");
        assertEquals(2, byContains.size());
        }

    @Test public void testWildcardNamespacedTag_9_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("*|def.bold");
        // removed other assertion
        // removed other assertion

        Elements byContains = doc.select("*|def:contains(e)");
        // removed other assertion
        assertEquals("1", byContains.first().id());
        }

    @Test public void testWildcardNamespacedTag_10_oe() {
        Document doc = Jsoup.parse("<div><abc:def id=1>Hello</abc:def></div> <abc:def class=bold id=2>There</abc:def>");
        Elements byTag = doc.select("*|def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("*|def.bold");
        // removed other assertion
        // removed other assertion

        Elements byContains = doc.select("*|def:contains(e)");
        // removed other assertion
        // removed other assertion
        assertEquals("2", byContains.last().id());
        }

    @Test public void testWildcardNamespacedXmlTag_1_oe() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        assertEquals(2, byTag.size());
        }

    @Test public void testWildcardNamespacedXmlTag_2_oe() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        // removed other assertion
        assertEquals("1", byTag.first().id());
        }

    @Test public void testWildcardNamespacedXmlTag_3_oe() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        // removed other assertion
        // removed other assertion
        assertEquals("2", byTag.last().id());
        }

    @Test public void testWildcardNamespacedXmlTag_4_oe() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        assertEquals(1, byAttr.size());
        }

    @Test public void testWildcardNamespacedXmlTag_5_oe() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        assertEquals("2", byAttr.last().id());
        }

    @Test public void testWildcardNamespacedXmlTag_6_oe() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("*|Def.bold");
        assertEquals(1, byTagAttr.size());
        }

    @Test public void testWildcardNamespacedXmlTag_7_oe() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("*|Def.bold");
        // removed other assertion
        assertEquals("2", byTagAttr.last().id());
        }

    @Test public void testWildcardNamespacedXmlTag_8_oe() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("*|Def.bold");
        // removed other assertion
        // removed other assertion

        Elements byContains = doc.select("*|Def:contains(e)");
        assertEquals(2, byContains.size());
        }

    @Test public void testWildcardNamespacedXmlTag_9_oe() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("*|Def.bold");
        // removed other assertion
        // removed other assertion

        Elements byContains = doc.select("*|Def:contains(e)");
        // removed other assertion
        assertEquals("1", byContains.first().id());
        }

    @Test public void testWildcardNamespacedXmlTag_10_oe() {
        Document doc = Jsoup.parse(
            "<div><Abc:Def id=1>Hello</Abc:Def></div> <Abc:Def class=bold id=2>There</abc:def>",
            "", Parser.xmlParser()
        );

        Elements byTag = doc.select("*|Def");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements byAttr = doc.select(".bold");
        // removed other assertion
        // removed other assertion

        Elements byTagAttr = doc.select("*|Def.bold");
        // removed other assertion
        // removed other assertion

        Elements byContains = doc.select("*|Def:contains(e)");
        // removed other assertion
        // removed other assertion
        assertEquals("2", byContains.last().id());
        }

    @Test public void testWildCardNamespacedCaseVariations_1_oe() {
        Document doc = Jsoup.parse("<One:Two>One</One:Two><three:four>Two</three:four>", "", Parser.xmlParser());
        Elements els1 = doc.select("One|Two");
        Elements els2 = doc.select("one|two");
        Elements els3 = doc.select("Three|Four");
        Elements els4 = doc.select("three|Four");

        assertEquals(els1, els2);
        }

    @Test public void testWildCardNamespacedCaseVariations_2_oe() {
        Document doc = Jsoup.parse("<One:Two>One</One:Two><three:four>Two</three:four>", "", Parser.xmlParser());
        Elements els1 = doc.select("One|Two");
        Elements els2 = doc.select("one|two");
        Elements els3 = doc.select("Three|Four");
        Elements els4 = doc.select("three|Four");

        // removed other assertion
        assertEquals(els3, els4);
        }

    @Test public void testWildCardNamespacedCaseVariations_3_oe() {
        Document doc = Jsoup.parse("<One:Two>One</One:Two><three:four>Two</three:four>", "", Parser.xmlParser());
        Elements els1 = doc.select("One|Two");
        Elements els2 = doc.select("one|two");
        Elements els3 = doc.select("Three|Four");
        Elements els4 = doc.select("three|Four");

        // removed other assertion
        // removed other assertion
        assertEquals("One", els1.text());
        }

    @Test public void testWildCardNamespacedCaseVariations_4_oe() {
        Document doc = Jsoup.parse("<One:Two>One</One:Two><three:four>Two</three:four>", "", Parser.xmlParser());
        Elements els1 = doc.select("One|Two");
        Elements els2 = doc.select("one|two");
        Elements els3 = doc.select("Three|Four");
        Elements els4 = doc.select("three|Four");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, els1.size());
        }

    @Test public void testWildCardNamespacedCaseVariations_5_oe() {
        Document doc = Jsoup.parse("<One:Two>One</One:Two><three:four>Two</three:four>", "", Parser.xmlParser());
        Elements els1 = doc.select("One|Two");
        Elements els2 = doc.select("one|two");
        Elements els3 = doc.select("Three|Four");
        Elements els4 = doc.select("three|Four");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Two", els3.text());
        }

    @Test public void testWildCardNamespacedCaseVariations_6_oe() {
        Document doc = Jsoup.parse("<One:Two>One</One:Two><three:four>Two</three:four>", "", Parser.xmlParser());
        Elements els1 = doc.select("One|Two");
        Elements els2 = doc.select("one|two");
        Elements els3 = doc.select("Three|Four");
        Elements els4 = doc.select("three|Four");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, els2.size());
        }

    public void testByAttributeStarting_1_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div id=1 ATTRIBUTE data-name=jsoup>Hello</div><p data-val=5 id=2>There</p><p id=3>No</p>");
        Elements withData = doc.select("[^data-]");
        assertEquals(2, withData.size());
    }

    public void testByAttributeStarting_2_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div id=1 ATTRIBUTE data-name=jsoup>Hello</div><p data-val=5 id=2>There</p><p id=3>No</p>");
        Elements withData = doc.select("[^data-]");
        // removed other assertion
        assertEquals("1", withData.first().id());
    }

    public void testByAttributeStarting_3_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div id=1 ATTRIBUTE data-name=jsoup>Hello</div><p data-val=5 id=2>There</p><p id=3>No</p>");
        Elements withData = doc.select("[^data-]");
        // removed other assertion
        // removed other assertion
        assertEquals("2", withData.last().id());
    }

    public void testByAttributeStarting_4_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div id=1 ATTRIBUTE data-name=jsoup>Hello</div><p data-val=5 id=2>There</p><p id=3>No</p>");
        Elements withData = doc.select("[^data-]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        withData = doc.select("p[^data-]");
        assertEquals(1, withData.size());
    }

    public void testByAttributeStarting_5_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div id=1 ATTRIBUTE data-name=jsoup>Hello</div><p data-val=5 id=2>There</p><p id=3>No</p>");
        Elements withData = doc.select("[^data-]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        withData = doc.select("p[^data-]");
        // removed other assertion
        assertEquals("2", withData.first().id());
    }

    public void testByAttributeStarting_6_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div id=1 ATTRIBUTE data-name=jsoup>Hello</div><p data-val=5 id=2>There</p><p id=3>No</p>");
        Elements withData = doc.select("[^data-]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        withData = doc.select("p[^data-]");
        // removed other assertion
        // removed other assertion

        assertEquals(1, doc.select("[^attrib]").size());
    }

    @Test public void testByAttributeRegex_1_oe() {
        Document doc = Jsoup.parse("<p><img src=foo.png id=1><img src=bar.jpg id=2><img src=qux.JPEG id=3><img src=old.gif><img></p>");
        Elements imgs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
        assertEquals(3, imgs.size());
        }

    @Test public void testByAttributeRegex_2_oe() {
        Document doc = Jsoup.parse("<p><img src=foo.png id=1><img src=bar.jpg id=2><img src=qux.JPEG id=3><img src=old.gif><img></p>");
        Elements imgs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
        // removed other assertion
        assertEquals("1", imgs.get(0).id());
        }

    @Test public void testByAttributeRegex_3_oe() {
        Document doc = Jsoup.parse("<p><img src=foo.png id=1><img src=bar.jpg id=2><img src=qux.JPEG id=3><img src=old.gif><img></p>");
        Elements imgs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
        // removed other assertion
        // removed other assertion
        assertEquals("2", imgs.get(1).id());
        }

    @Test public void testByAttributeRegex_4_oe() {
        Document doc = Jsoup.parse("<p><img src=foo.png id=1><img src=bar.jpg id=2><img src=qux.JPEG id=3><img src=old.gif><img></p>");
        Elements imgs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", imgs.get(2).id());
        }

    @Test public void testByAttributeRegexCharacterClass_1_oe() {
        Document doc = Jsoup.parse("<p><img src=foo.png id=1><img src=bar.jpg id=2><img src=qux.JPEG id=3><img src=old.gif id=4></p>");
        Elements imgs = doc.select("img[src~=[o]]");
        assertEquals(2, imgs.size());
        }

    @Test public void testByAttributeRegexCharacterClass_2_oe() {
        Document doc = Jsoup.parse("<p><img src=foo.png id=1><img src=bar.jpg id=2><img src=qux.JPEG id=3><img src=old.gif id=4></p>");
        Elements imgs = doc.select("img[src~=[o]]");
        // removed other assertion
        assertEquals("1", imgs.get(0).id());
        }

    @Test public void testByAttributeRegexCharacterClass_3_oe() {
        Document doc = Jsoup.parse("<p><img src=foo.png id=1><img src=bar.jpg id=2><img src=qux.JPEG id=3><img src=old.gif id=4></p>");
        Elements imgs = doc.select("img[src~=[o]]");
        // removed other assertion
        // removed other assertion
        assertEquals("4", imgs.get(1).id());
        }

    @Test public void testByAttributeRegexCombined_1_oe() {
        Document doc = Jsoup.parse("<div><table class=x><td>Hello</td></table></div>");
        Elements els = doc.select("div table[class~=x|y]");
        assertEquals(1, els.size());
        }

    @Test public void testByAttributeRegexCombined_2_oe() {
        Document doc = Jsoup.parse("<div><table class=x><td>Hello</td></table></div>");
        Elements els = doc.select("div table[class~=x|y]");
        // removed other assertion
        assertEquals("Hello", els.text());
        }

    @Test public void testCombinedWithContains_1_oe() {
        Document doc = Jsoup.parse("<p id=1>One</p><p>Two +</p><p>Three +</p>");
        Elements els = doc.select("p#1 + :contains(+)");
        assertEquals(1, els.size());
        }

    @Test public void testCombinedWithContains_2_oe() {
        Document doc = Jsoup.parse("<p id=1>One</p><p>Two +</p><p>Three +</p>");
        Elements els = doc.select("p#1 + :contains(+)");
        // removed other assertion
        assertEquals("Two +", els.text());
        }

    @Test public void testCombinedWithContains_3_oe() {
        Document doc = Jsoup.parse("<p id=1>One</p><p>Two +</p><p>Three +</p>");
        Elements els = doc.select("p#1 + :contains(+)");
        // removed other assertion
        // removed other assertion
        assertEquals("p", els.first().tagName());
        }

    @Test public void testAllElements_1_oe() {
        String h = "<div><p>Hello</p><p><b>there</b></p></div>";
        Document doc = Jsoup.parse(h);
        Elements allDoc = doc.select("*");
        Elements allUnderDiv = doc.select("div *");
        assertEquals(8, allDoc.size());
        }

    @Test public void testAllElements_2_oe() {
        String h = "<div><p>Hello</p><p><b>there</b></p></div>";
        Document doc = Jsoup.parse(h);
        Elements allDoc = doc.select("*");
        Elements allUnderDiv = doc.select("div *");
        // removed other assertion
        assertEquals(3, allUnderDiv.size());
        }

    @Test public void testAllElements_3_oe() {
        String h = "<div><p>Hello</p><p><b>there</b></p></div>";
        Document doc = Jsoup.parse(h);
        Elements allDoc = doc.select("*");
        Elements allUnderDiv = doc.select("div *");
        // removed other assertion
        // removed other assertion
        assertEquals("p", allUnderDiv.first().tagName());
        }

    @Test public void testAllWithClass_1_oe() {
        String h = "<p class=first>One<p class=first>Two<p>Three";
        Document doc = Jsoup.parse(h);
        Elements ps = doc.select("*.first");
        assertEquals(2, ps.size());
        }

    @Test public void testGroupOr_1_oe() {
        String h = "<div title=foo /><div title=bar /><div /><p></p><img /><span title=qux>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("p,div,[title]");

        assertEquals(5, els.size());
        }

    @Test public void testGroupOr_2_oe() {
        String h = "<div title=foo /><div title=bar /><div /><p></p><img /><span title=qux>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("p,div,[title]");

        // removed other assertion
        assertEquals("div", els.get(0).tagName());
        }

    @Test public void testGroupOr_3_oe() {
        String h = "<div title=foo /><div title=bar /><div /><p></p><img /><span title=qux>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("p,div,[title]");

        // removed other assertion
        // removed other assertion
        assertEquals("foo", els.get(0).attr("title"));
        }

    @Test public void testGroupOr_4_oe() {
        String h = "<div title=foo /><div title=bar /><div /><p></p><img /><span title=qux>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("p,div,[title]");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("div", els.get(1).tagName());
        }

    @Test public void testGroupOr_5_oe() {
        String h = "<div title=foo /><div title=bar /><div /><p></p><img /><span title=qux>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("p,div,[title]");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("bar", els.get(1).attr("title"));
        }

    @Test public void testGroupOr_6_oe() {
        String h = "<div title=foo /><div title=bar /><div /><p></p><img /><span title=qux>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("p,div,[title]");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("div", els.get(2).tagName());
        }

    @Test public void testGroupOr_7_oe() {
        String h = "<div title=foo /><div title=bar /><div /><p></p><img /><span title=qux>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("p,div,[title]");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,els.get(2).attr("title").length());// missing attributes come back as empty string assertFalse(els.get(2).hasAttr("title"));
        }

    @Test public void testGroupOr_8_oe() {
        String h = "<div title=foo /><div title=bar /><div /><p></p><img /><span title=qux>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("p,div,[title]");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("p", els.get(3).tagName());
        }

    @Test public void testGroupOr_9_oe() {
        String h = "<div title=foo /><div title=bar /><div /><p></p><img /><span title=qux>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("p,div,[title]");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("span", els.get(4).tagName());
        }

    @Test public void testGroupOrAttribute_1_oe() {
        String h = "<div id=1 /><div id=2 /><div title=foo /><div title=bar />";
        Elements els = Jsoup.parse(h).select("[id],[title=foo]");

        assertEquals(3, els.size());
        }

    @Test public void testGroupOrAttribute_2_oe() {
        String h = "<div id=1 /><div id=2 /><div title=foo /><div title=bar />";
        Elements els = Jsoup.parse(h).select("[id],[title=foo]");

        // removed other assertion
        assertEquals("1", els.get(0).id());
        }

    @Test public void testGroupOrAttribute_3_oe() {
        String h = "<div id=1 /><div id=2 /><div title=foo /><div title=bar />";
        Elements els = Jsoup.parse(h).select("[id],[title=foo]");

        // removed other assertion
        // removed other assertion
        assertEquals("2", els.get(1).id());
        }

    @Test public void testGroupOrAttribute_4_oe() {
        String h = "<div id=1 /><div id=2 /><div title=foo /><div title=bar />";
        Elements els = Jsoup.parse(h).select("[id],[title=foo]");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo", els.get(2).attr("title"));
        }

    @Test public void descendant_1_oe() {
        String h = "<div class=head><p class=first>Hello</p><p>There</p></div><p>None</p>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("HEAD").first();

        Elements els = root.select(".head p");
        assertEquals(2, els.size());
        }

    @Test public void descendant_2_oe() {
        String h = "<div class=head><p class=first>Hello</p><p>There</p></div><p>None</p>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("HEAD").first();

        Elements els = root.select(".head p");
        // removed other assertion
        assertEquals("Hello", els.get(0).text());
        }

    @Test public void descendant_3_oe() {
        String h = "<div class=head><p class=first>Hello</p><p>There</p></div><p>None</p>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("HEAD").first();

        Elements els = root.select(".head p");
        // removed other assertion
        // removed other assertion
        assertEquals("There", els.get(1).text());
        }

    @Test public void descendant_4_oe() {
        String h = "<div class=head><p class=first>Hello</p><p>There</p></div><p>None</p>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("HEAD").first();

        Elements els = root.select(".head p");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements p = root.select("p.first");
        assertEquals(1, p.size());
        }

    @Test public void descendant_5_oe() {
        String h = "<div class=head><p class=first>Hello</p><p>There</p></div><p>None</p>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("HEAD").first();

        Elements els = root.select(".head p");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements p = root.select("p.first");
        // removed other assertion
        assertEquals("Hello", p.get(0).text());
        }

    @Test public void descendant_6_oe() {
        String h = "<div class=head><p class=first>Hello</p><p>There</p></div><p>None</p>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("HEAD").first();

        Elements els = root.select(".head p");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements p = root.select("p.first");
        // removed other assertion
        // removed other assertion

        Elements empty = root.select("p .first"); // self, not descend, should not match
        assertEquals(0, empty.size());
        }

    @Test public void descendant_7_oe() {
        String h = "<div class=head><p class=first>Hello</p><p>There</p></div><p>None</p>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("HEAD").first();

        Elements els = root.select(".head p");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements p = root.select("p.first");
        // removed other assertion
        // removed other assertion

        Elements empty = root.select("p .first"); // self, not descend, should not match
        // removed other assertion

        Elements aboveRoot = root.select("body div.head");
        assertEquals(0, aboveRoot.size());
        }

    @Test public void and_1_oe() {
        String h = "<div id=1 class='foo bar' title=bar name=qux><p class=foo title=bar>Hello</p></div";
        Document doc = Jsoup.parse(h);

        Elements div = doc.select("div.foo");
        assertEquals(1, div.size());
        }

    @Test public void and_2_oe() {
        String h = "<div id=1 class='foo bar' title=bar name=qux><p class=foo title=bar>Hello</p></div";
        Document doc = Jsoup.parse(h);

        Elements div = doc.select("div.foo");
        // removed other assertion
        assertEquals("div", div.first().tagName());
        }

    @Test public void and_3_oe() {
        String h = "<div id=1 class='foo bar' title=bar name=qux><p class=foo title=bar>Hello</p></div";
        Document doc = Jsoup.parse(h);

        Elements div = doc.select("div.foo");
        // removed other assertion
        // removed other assertion

        Elements p = doc.select("div .foo"); // space indicates like "div *.foo"
        assertEquals(1, p.size());
        }

    @Test public void and_4_oe() {
        String h = "<div id=1 class='foo bar' title=bar name=qux><p class=foo title=bar>Hello</p></div";
        Document doc = Jsoup.parse(h);

        Elements div = doc.select("div.foo");
        // removed other assertion
        // removed other assertion

        Elements p = doc.select("div .foo"); // space indicates like "div *.foo"
        // removed other assertion
        assertEquals("p", p.first().tagName());
        }

    @Test public void and_5_oe() {
        String h = "<div id=1 class='foo bar' title=bar name=qux><p class=foo title=bar>Hello</p></div";
        Document doc = Jsoup.parse(h);

        Elements div = doc.select("div.foo");
        // removed other assertion
        // removed other assertion

        Elements p = doc.select("div .foo"); // space indicates like "div *.foo"
        // removed other assertion
        // removed other assertion

        Elements div2 = doc.select("div#1.foo.bar[title=bar][name=qux]"); // very specific!
        assertEquals(1, div2.size());
        }

    @Test public void and_6_oe() {
        String h = "<div id=1 class='foo bar' title=bar name=qux><p class=foo title=bar>Hello</p></div";
        Document doc = Jsoup.parse(h);

        Elements div = doc.select("div.foo");
        // removed other assertion
        // removed other assertion

        Elements p = doc.select("div .foo"); // space indicates like "div *.foo"
        // removed other assertion
        // removed other assertion

        Elements div2 = doc.select("div#1.foo.bar[title=bar][name=qux]"); // very specific!
        // removed other assertion
        assertEquals("div", div2.first().tagName());
        }

    @Test public void and_7_oe() {
        String h = "<div id=1 class='foo bar' title=bar name=qux><p class=foo title=bar>Hello</p></div";
        Document doc = Jsoup.parse(h);

        Elements div = doc.select("div.foo");
        // removed other assertion
        // removed other assertion

        Elements p = doc.select("div .foo"); // space indicates like "div *.foo"
        // removed other assertion
        // removed other assertion

        Elements div2 = doc.select("div#1.foo.bar[title=bar][name=qux]"); // very specific!
        // removed other assertion
        // removed other assertion

        Elements p2 = doc.select("div *.foo"); // space indicates like "div *.foo"
        assertEquals(1, p2.size());
        }

    @Test public void and_8_oe() {
        String h = "<div id=1 class='foo bar' title=bar name=qux><p class=foo title=bar>Hello</p></div";
        Document doc = Jsoup.parse(h);

        Elements div = doc.select("div.foo");
        // removed other assertion
        // removed other assertion

        Elements p = doc.select("div .foo"); // space indicates like "div *.foo"
        // removed other assertion
        // removed other assertion

        Elements div2 = doc.select("div#1.foo.bar[title=bar][name=qux]"); // very specific!
        // removed other assertion
        // removed other assertion

        Elements p2 = doc.select("div *.foo"); // space indicates like "div *.foo"
        // removed other assertion
        assertEquals("p", p2.first().tagName());
        }

    @Test public void deeperDescendant_1_oe() {
        String h = "<div class=head><p><span class=first>Hello</div><div class=head><p class=first><span>Another</span><p>Again</div>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("head").first();

        Elements els = root.select("div p .first");
        assertEquals(1, els.size());
        }

    @Test public void deeperDescendant_2_oe() {
        String h = "<div class=head><p><span class=first>Hello</div><div class=head><p class=first><span>Another</span><p>Again</div>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("head").first();

        Elements els = root.select("div p .first");
        // removed other assertion
        assertEquals("Hello", els.first().text());
        }

    @Test public void deeperDescendant_3_oe() {
        String h = "<div class=head><p><span class=first>Hello</div><div class=head><p class=first><span>Another</span><p>Again</div>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("head").first();

        Elements els = root.select("div p .first");
        // removed other assertion
        // removed other assertion
        assertEquals("span", els.first().tagName());
        }

    @Test public void deeperDescendant_4_oe() {
        String h = "<div class=head><p><span class=first>Hello</div><div class=head><p class=first><span>Another</span><p>Again</div>";
        Document doc = Jsoup.parse(h);
        Element root = doc.getElementsByClass("head").first();

        Elements els = root.select("div p .first");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements aboveRoot = root.select("body p .first");
        assertEquals(0, aboveRoot.size());
        }

    @Test public void parentChildElement_1_oe() {
        String h = "<div id=1><div id=2><div id = 3></div></div></div><div id=4></div>";
        Document doc = Jsoup.parse(h);

        Elements divs = doc.select("div > div");
        assertEquals(2, divs.size());
        }

    @Test public void parentChildElement_2_oe() {
        String h = "<div id=1><div id=2><div id = 3></div></div></div><div id=4></div>";
        Document doc = Jsoup.parse(h);

        Elements divs = doc.select("div > div");
        // removed other assertion
        assertEquals("2",divs.get(0).id());// 2 is child of 1;
        }

    @Test public void parentChildElement_3_oe() {
        String h = "<div id=1><div id=2><div id = 3></div></div></div><div id=4></div>";
        Document doc = Jsoup.parse(h);

        Elements divs = doc.select("div > div");
        // removed other assertion
        // removed other assertion
        assertEquals("3",divs.get(1).id());// 3 is child of 2;
        }

    @Test public void parentChildElement_4_oe() {
        String h = "<div id=1><div id=2><div id = 3></div></div></div><div id=4></div>";
        Document doc = Jsoup.parse(h);

        Elements divs = doc.select("div > div");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Elements div2 = doc.select("div#1 > div");
        assertEquals(1, div2.size());
        }

    @Test public void parentChildElement_5_oe() {
        String h = "<div id=1><div id=2><div id = 3></div></div></div><div id=4></div>";
        Document doc = Jsoup.parse(h);

        Elements divs = doc.select("div > div");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Elements div2 = doc.select("div#1 > div");
        // removed other assertion
        assertEquals("2", div2.get(0).id());
        }

    @Test public void parentWithClassChild_1_oe() {
        String h = "<h1 class=foo><a href=1 /></h1><h1 class=foo><a href=2 class=bar /></h1><h1><a href=3 /></h1>";
        Document doc = Jsoup.parse(h);

        Elements allAs = doc.select("h1 > a");
        assertEquals(3, allAs.size());
        }

    @Test public void parentWithClassChild_2_oe() {
        String h = "<h1 class=foo><a href=1 /></h1><h1 class=foo><a href=2 class=bar /></h1><h1><a href=3 /></h1>";
        Document doc = Jsoup.parse(h);

        Elements allAs = doc.select("h1 > a");
        // removed other assertion
        assertEquals("a", allAs.first().tagName());
        }

    @Test public void parentWithClassChild_3_oe() {
        String h = "<h1 class=foo><a href=1 /></h1><h1 class=foo><a href=2 class=bar /></h1><h1><a href=3 /></h1>";
        Document doc = Jsoup.parse(h);

        Elements allAs = doc.select("h1 > a");
        // removed other assertion
        // removed other assertion

        Elements fooAs = doc.select("h1.foo > a");
        assertEquals(2, fooAs.size());
        }

    @Test public void parentWithClassChild_4_oe() {
        String h = "<h1 class=foo><a href=1 /></h1><h1 class=foo><a href=2 class=bar /></h1><h1><a href=3 /></h1>";
        Document doc = Jsoup.parse(h);

        Elements allAs = doc.select("h1 > a");
        // removed other assertion
        // removed other assertion

        Elements fooAs = doc.select("h1.foo > a");
        // removed other assertion
        assertEquals("a", fooAs.first().tagName());
        }

    @Test public void parentWithClassChild_5_oe() {
        String h = "<h1 class=foo><a href=1 /></h1><h1 class=foo><a href=2 class=bar /></h1><h1><a href=3 /></h1>";
        Document doc = Jsoup.parse(h);

        Elements allAs = doc.select("h1 > a");
        // removed other assertion
        // removed other assertion

        Elements fooAs = doc.select("h1.foo > a");
        // removed other assertion
        // removed other assertion

        Elements barAs = doc.select("h1.foo > a.bar");
        assertEquals(1, barAs.size());
        }

    @Test public void parentChildStar_1_oe() {
        String h = "<div id=1><p>Hello<p><b>there</b></p></div><div id=2><span>Hi</span></div>";
        Document doc = Jsoup.parse(h);
        Elements divChilds = doc.select("div > *");
        assertEquals(3, divChilds.size());
        }

    @Test public void parentChildStar_2_oe() {
        String h = "<div id=1><p>Hello<p><b>there</b></p></div><div id=2><span>Hi</span></div>";
        Document doc = Jsoup.parse(h);
        Elements divChilds = doc.select("div > *");
        // removed other assertion
        assertEquals("p", divChilds.get(0).tagName());
        }

    @Test public void parentChildStar_3_oe() {
        String h = "<div id=1><p>Hello<p><b>there</b></p></div><div id=2><span>Hi</span></div>";
        Document doc = Jsoup.parse(h);
        Elements divChilds = doc.select("div > *");
        // removed other assertion
        // removed other assertion
        assertEquals("p", divChilds.get(1).tagName());
        }

    @Test public void parentChildStar_4_oe() {
        String h = "<div id=1><p>Hello<p><b>there</b></p></div><div id=2><span>Hi</span></div>";
        Document doc = Jsoup.parse(h);
        Elements divChilds = doc.select("div > *");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("span", divChilds.get(2).tagName());
        }

    @Test public void multiChildDescent_1_oe() {
        String h = "<div id=foo><h1 class=bar><a href=http://example.com/>One</a></h1></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("div#foo > h1.bar > a[href*=example]");
        assertEquals(1, els.size());
        }

    @Test public void multiChildDescent_2_oe() {
        String h = "<div id=foo><h1 class=bar><a href=http://example.com/>One</a></h1></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("div#foo > h1.bar > a[href*=example]");
        // removed other assertion
        assertEquals("a", els.first().tagName());
        }

    @Test public void caseInsensitive_1_oe() {
        String h = "<dIv tItle=bAr><div>"; // mixed case so a simple toLowerCase() on value doesn't catch
        Document doc = Jsoup.parse(h);

        assertEquals(2, doc.select("DiV").size());
        }

    @Test public void caseInsensitive_2_oe() {
        String h = "<dIv tItle=bAr><div>"; // mixed case so a simple toLowerCase() on value doesn't catch
        Document doc = Jsoup.parse(h);

        // removed other assertion
        assertEquals(1, doc.select("DiV[TiTLE]").size());
        }

    @Test public void caseInsensitive_3_oe() {
        String h = "<dIv tItle=bAr><div>"; // mixed case so a simple toLowerCase() on value doesn't catch
        Document doc = Jsoup.parse(h);

        // removed other assertion
        // removed other assertion
        assertEquals(1, doc.select("DiV[TiTLE=BAR]").size());
        }

    @Test public void caseInsensitive_4_oe() {
        String h = "<dIv tItle=bAr><div>"; // mixed case so a simple toLowerCase() on value doesn't catch
        Document doc = Jsoup.parse(h);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, doc.select("DiV[TiTLE=BARBARELLA]").size());
        }

    @Test public void adjacentSiblings_1_oe() {
        String h = "<ol><li>One<li>Two<li>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("li + li");
        assertEquals(2, sibs.size());
        }

    @Test public void adjacentSiblings_2_oe() {
        String h = "<ol><li>One<li>Two<li>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("li + li");
        // removed other assertion
        assertEquals("Two", sibs.get(0).text());
        }

    @Test public void adjacentSiblings_3_oe() {
        String h = "<ol><li>One<li>Two<li>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("li + li");
        // removed other assertion
        // removed other assertion
        assertEquals("Three", sibs.get(1).text());
        }

    @Test public void adjacentSiblingsWithId_1_oe() {
        String h = "<ol><li id=1>One<li id=2>Two<li id=3>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("li#1 + li#2");
        assertEquals(1, sibs.size());
        }

    @Test public void adjacentSiblingsWithId_2_oe() {
        String h = "<ol><li id=1>One<li id=2>Two<li id=3>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("li#1 + li#2");
        // removed other assertion
        assertEquals("Two", sibs.get(0).text());
        }

    @Test public void notAdjacent_1_oe() {
        String h = "<ol><li id=1>One<li id=2>Two<li id=3>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("li#1 + li#3");
        assertEquals(0, sibs.size());
        }

    @Test public void mixCombinator_1_oe() {
        String h = "<div class=foo><ol><li>One<li>Two<li>Three</ol></div>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("body > div.foo li + li");

        assertEquals(2, sibs.size());
        }

    @Test public void mixCombinator_2_oe() {
        String h = "<div class=foo><ol><li>One<li>Two<li>Three</ol></div>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("body > div.foo li + li");

        // removed other assertion
        assertEquals("Two", sibs.get(0).text());
        }

    @Test public void mixCombinator_3_oe() {
        String h = "<div class=foo><ol><li>One<li>Two<li>Three</ol></div>";
        Document doc = Jsoup.parse(h);
        Elements sibs = doc.select("body > div.foo li + li");

        // removed other assertion
        // removed other assertion
        assertEquals("Three", sibs.get(1).text());
        }

    @Test public void mixCombinatorGroup_1_oe() {
        String h = "<div class=foo><ol><li>One<li>Two<li>Three</ol></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select(".foo > ol, ol > li + li");

        assertEquals(3, els.size());
        }

    @Test public void mixCombinatorGroup_2_oe() {
        String h = "<div class=foo><ol><li>One<li>Two<li>Three</ol></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select(".foo > ol, ol > li + li");

        // removed other assertion
        assertEquals("ol", els.get(0).tagName());
        }

    @Test public void mixCombinatorGroup_3_oe() {
        String h = "<div class=foo><ol><li>One<li>Two<li>Three</ol></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select(".foo > ol, ol > li + li");

        // removed other assertion
        // removed other assertion
        assertEquals("Two", els.get(1).text());
        }

    @Test public void mixCombinatorGroup_4_oe() {
        String h = "<div class=foo><ol><li>One<li>Two<li>Three</ol></div>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select(".foo > ol, ol > li + li");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Three", els.get(2).text());
        }

    @Test public void generalSiblings_1_oe() {
        String h = "<ol><li id=1>One<li id=2>Two<li id=3>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("#1 ~ #3");
        assertEquals(1, els.size());
        }

    @Test public void generalSiblings_2_oe() {
        String h = "<ol><li id=1>One<li id=2>Two<li id=3>Three</ol>";
        Document doc = Jsoup.parse(h);
        Elements els = doc.select("#1 ~ #3");
        // removed other assertion
        assertEquals("Three", els.first().text());
        }

    @Test public void testCharactersInIdAndClass_1_oe() {
        // using CSS spec for identifiers (id and class): a-z0-9, -, _. NOT . (which is OK in html spec, but not css)
        String h = "<div><p id='a1-foo_bar'>One</p><p class='b2-qux_bif'>Two</p></div>";
        Document doc = Jsoup.parse(h);

        Element el1 = doc.getElementById("a1-foo_bar");
        assertEquals("One", el1.text());
        }

    @Test public void testCharactersInIdAndClass_2_oe() {
        // using CSS spec for identifiers (id and class): a-z0-9, -, _. NOT . (which is OK in html spec, but not css)
        String h = "<div><p id='a1-foo_bar'>One</p><p class='b2-qux_bif'>Two</p></div>";
        Document doc = Jsoup.parse(h);

        Element el1 = doc.getElementById("a1-foo_bar");
        // removed other assertion
        Element el2 = doc.getElementsByClass("b2-qux_bif").first();
        assertEquals("Two", el2.text());
        }

    @Test public void testCharactersInIdAndClass_3_oe() {
        // using CSS spec for identifiers (id and class): a-z0-9, -, _. NOT . (which is OK in html spec, but not css)
        String h = "<div><p id='a1-foo_bar'>One</p><p class='b2-qux_bif'>Two</p></div>";
        Document doc = Jsoup.parse(h);

        Element el1 = doc.getElementById("a1-foo_bar");
        // removed other assertion
        Element el2 = doc.getElementsByClass("b2-qux_bif").first();
        // removed other assertion

        Element el3 = doc.select("#a1-foo_bar").first();
        assertEquals("One", el3.text());
        }

    @Test public void testCharactersInIdAndClass_4_oe() {
        // using CSS spec for identifiers (id and class): a-z0-9, -, _. NOT . (which is OK in html spec, but not css)
        String h = "<div><p id='a1-foo_bar'>One</p><p class='b2-qux_bif'>Two</p></div>";
        Document doc = Jsoup.parse(h);

        Element el1 = doc.getElementById("a1-foo_bar");
        // removed other assertion
        Element el2 = doc.getElementsByClass("b2-qux_bif").first();
        // removed other assertion

        Element el3 = doc.select("#a1-foo_bar").first();
        // removed other assertion
        Element el4 = doc.select(".b2-qux_bif").first();
        assertEquals("Two", el4.text());
        }

    @Test public void testSupportsLeadingCombinator_1_oe() {
        String h = "<div><p><span>One</span><span>Two</span></p></div>";
        Document doc = Jsoup.parse(h);

        Element p = doc.select("div > p").first();
        Elements spans = p.select("> span");
        assertEquals(2, spans.size());
        }

    @Test public void testSupportsLeadingCombinator_2_oe() {
        String h = "<div><p><span>One</span><span>Two</span></p></div>";
        Document doc = Jsoup.parse(h);

        Element p = doc.select("div > p").first();
        Elements spans = p.select("> span");
        // removed other assertion
        assertEquals("One", spans.first().text());
        }

    @Test public void testSupportsLeadingCombinator_3_oe() {
        String h = "<div><p><span>One</span><span>Two</span></p></div>";
        Document doc = Jsoup.parse(h);

        Element p = doc.select("div > p").first();
        Elements spans = p.select("> span");
        // removed other assertion
        // removed other assertion

        // make sure doesn't get nested
        h = "<div id=1><div id=2><div id=3></div></div></div>";
        doc = Jsoup.parse(h);
        Element div = doc.select("div").select(" > div").first();
        assertEquals("2", div.id());
        }

    @Test public void testPseudoLessThan_1_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:lt(2)");
        assertEquals(3, ps.size());
        }

    @Test public void testPseudoLessThan_2_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:lt(2)");
        // removed other assertion
        assertEquals("One", ps.get(0).text());
        }

    @Test public void testPseudoLessThan_3_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:lt(2)");
        // removed other assertion
        // removed other assertion
        assertEquals("Two", ps.get(1).text());
        }

    @Test public void testPseudoLessThan_4_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:lt(2)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Four", ps.get(2).text());
        }

    @Test public void testPseudoGreaterThan_1_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:gt(0)");
        assertEquals(2, ps.size());
        }

    @Test public void testPseudoGreaterThan_2_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:gt(0)");
        // removed other assertion
        assertEquals("Two", ps.get(0).text());
        }

    @Test public void testPseudoGreaterThan_3_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:gt(0)");
        // removed other assertion
        // removed other assertion
        assertEquals("Three", ps.get(1).text());
        }

    @Test public void testPseudoEquals_1_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:eq(0)");
        assertEquals(2, ps.size());
        }

    @Test public void testPseudoEquals_2_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:eq(0)");
        // removed other assertion
        assertEquals("One", ps.get(0).text());
        }

    @Test public void testPseudoEquals_3_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:eq(0)");
        // removed other assertion
        // removed other assertion
        assertEquals("Four", ps.get(1).text());
        }

    @Test public void testPseudoEquals_4_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:eq(0)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ps2 = doc.select("div:eq(0) p:eq(0)");
        assertEquals(1, ps2.size());
        }

    @Test public void testPseudoEquals_5_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:eq(0)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ps2 = doc.select("div:eq(0) p:eq(0)");
        // removed other assertion
        assertEquals("One", ps2.get(0).text());
        }

    @Test public void testPseudoEquals_6_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:eq(0)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ps2 = doc.select("div:eq(0) p:eq(0)");
        // removed other assertion
        // removed other assertion
        assertEquals("p", ps2.get(0).tagName());
        }

    @Test public void testPseudoBetween_1_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:gt(0):lt(2)");
        assertEquals(1, ps.size());
        }

    @Test public void testPseudoBetween_2_oe() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</>p></div><div><p>Four</p>");
        Elements ps = doc.select("div p:gt(0):lt(2)");
        // removed other assertion
        assertEquals("Two", ps.get(0).text());
        }

    @Test public void testPseudoCombined_1_oe() {
        Document doc = Jsoup.parse("<div class='foo'><p>One</p><p>Two</p></div><div><p>Three</p><p>Four</p></div>");
        Elements ps = doc.select("div.foo p:gt(0)");
        assertEquals(1, ps.size());
        }

    @Test public void testPseudoCombined_2_oe() {
        Document doc = Jsoup.parse("<div class='foo'><p>One</p><p>Two</p></div><div><p>Three</p><p>Four</p></div>");
        Elements ps = doc.select("div.foo p:gt(0)");
        // removed other assertion
        assertEquals("Two", ps.get(0).text());
        }

    @Test public void testPseudoHas_1_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        assertEquals(2, divs1.size());
        }

    @Test public void testPseudoHas_2_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        assertEquals("0", divs1.get(0).id());
        }

    @Test public void testPseudoHas_3_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        assertEquals("1", divs1.get(1).id());
        }

    @Test public void testPseudoHas_4_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        assertEquals(1, divs2.size());
        }

    @Test public void testPseudoHas_5_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        // removed other assertion
        assertEquals("1", divs2.get(0).id());
        }

    @Test public void testPseudoHas_6_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        // removed other assertion
        // removed other assertion

        Elements divs3 = doc.select("div:has(span, p)");
        assertEquals(3, divs3.size());
        }

    @Test public void testPseudoHas_7_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        // removed other assertion
        // removed other assertion

        Elements divs3 = doc.select("div:has(span, p)");
        // removed other assertion
        assertEquals("0", divs3.get(0).id());
        }

    @Test public void testPseudoHas_8_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        // removed other assertion
        // removed other assertion

        Elements divs3 = doc.select("div:has(span, p)");
        // removed other assertion
        // removed other assertion
        assertEquals("1", divs3.get(1).id());
        }

    @Test public void testPseudoHas_9_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        // removed other assertion
        // removed other assertion

        Elements divs3 = doc.select("div:has(span, p)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", divs3.get(2).id());
        }

    @Test public void testPseudoHas_10_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        // removed other assertion
        // removed other assertion

        Elements divs3 = doc.select("div:has(span, p)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements els1 = doc.body().select(":has(p)");
        assertEquals(3,els1.size());// body,div,div assertEquals("body",els1.first().tagName());
        }

    @Test public void testPseudoHas_11_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        // removed other assertion
        // removed other assertion

        Elements divs3 = doc.select("div:has(span, p)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements els1 = doc.body().select(":has(p)");
        // removed other assertion
        assertEquals("0", els1.get(1).id());
        }

    @Test public void testPseudoHas_12_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        // removed other assertion
        // removed other assertion

        Elements divs3 = doc.select("div:has(span, p)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements els1 = doc.body().select(":has(p)");
        // removed other assertion
        // removed other assertion
        assertEquals("2", els1.get(2).id());
        }

    @Test public void testPseudoHas_13_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        // removed other assertion
        // removed other assertion

        Elements divs3 = doc.select("div:has(span, p)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements els1 = doc.body().select(":has(p)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements els2 = doc.body().select(":has(> span)");
        assertEquals(2,els2.size());// p,div assertEquals("p",els2.first().tagName());
        }

    @Test public void testPseudoHas_14_oe() {
        Document doc = Jsoup.parse("<div id=0><p><span>Hello</span></p></div> <div id=1><span class=foo>There</span></div> <div id=2><p>Not</p></div>");

        Elements divs1 = doc.select("div:has(span)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements divs2 = doc.select("div:has([class])");
        // removed other assertion
        // removed other assertion

        Elements divs3 = doc.select("div:has(span, p)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements els1 = doc.body().select(":has(p)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements els2 = doc.body().select(":has(> span)");
        // removed other assertion
        assertEquals("1", els2.get(1).id());
        }

    @Test public void testNestedHas_1_oe() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p></div> <div><p>Two</p></div>");
        Elements divs = doc.select("div:has(p:has(span))");
        assertEquals(1, divs.size());
        }

    @Test public void testNestedHas_2_oe() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p></div> <div><p>Two</p></div>");
        Elements divs = doc.select("div:has(p:has(span))");
        // removed other assertion
        assertEquals("One", divs.first().text());
        }

    @Test public void testNestedHas_3_oe() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p></div> <div><p>Two</p></div>");
        Elements divs = doc.select("div:has(p:has(span))");
        // removed other assertion
        // removed other assertion

        // test matches in has
        divs = doc.select("div:has(p:matches((?i)two))");
        assertEquals(1, divs.size());
        }

    @Test public void testNestedHas_4_oe() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p></div> <div><p>Two</p></div>");
        Elements divs = doc.select("div:has(p:has(span))");
        // removed other assertion
        // removed other assertion

        // test matches in has
        divs = doc.select("div:has(p:matches((?i)two))");
        // removed other assertion
        assertEquals("div", divs.first().tagName());
        }

    @Test public void testNestedHas_5_oe() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p></div> <div><p>Two</p></div>");
        Elements divs = doc.select("div:has(p:has(span))");
        // removed other assertion
        // removed other assertion

        // test matches in has
        divs = doc.select("div:has(p:matches((?i)two))");
        // removed other assertion
        // removed other assertion
        assertEquals("Two", divs.first().text());
        }

    @Test public void testNestedHas_6_oe() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p></div> <div><p>Two</p></div>");
        Elements divs = doc.select("div:has(p:has(span))");
        // removed other assertion
        // removed other assertion

        // test matches in has
        divs = doc.select("div:has(p:matches((?i)two))");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test contains in has
        divs = doc.select("div:has(p:contains(two))");
        assertEquals(1, divs.size());
        }

    @Test public void testNestedHas_7_oe() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p></div> <div><p>Two</p></div>");
        Elements divs = doc.select("div:has(p:has(span))");
        // removed other assertion
        // removed other assertion

        // test matches in has
        divs = doc.select("div:has(p:matches((?i)two))");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test contains in has
        divs = doc.select("div:has(p:contains(two))");
        // removed other assertion
        assertEquals("div", divs.first().tagName());
        }

    @Test public void testNestedHas_8_oe() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p></div> <div><p>Two</p></div>");
        Elements divs = doc.select("div:has(p:has(span))");
        // removed other assertion
        // removed other assertion

        // test matches in has
        divs = doc.select("div:has(p:matches((?i)two))");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test contains in has
        divs = doc.select("div:has(p:contains(two))");
        // removed other assertion
        // removed other assertion
        assertEquals("Two", divs.first().text());
        }

    public void testPseudoContains_1_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        assertEquals(3, ps1.size());
    }

    public void testPseudoContains_2_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        // removed other assertion

        Elements ps2 = doc.select("p:contains(the rain)");
        assertEquals(2, ps2.size());
    }

    public void testPseudoContains_3_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        // removed other assertion

        Elements ps2 = doc.select("p:contains(the rain)");
        // removed other assertion
        assertEquals("The Rain.", ps2.first().html());
    }

    public void testPseudoContains_4_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        // removed other assertion

        Elements ps2 = doc.select("p:contains(the rain)");
        // removed other assertion
        // removed other assertion
        assertEquals("The <i>RAIN</i>.", ps2.last().html());
    }

    public void testPseudoContains_5_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        // removed other assertion

        Elements ps2 = doc.select("p:contains(the rain)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ps3 = doc.select("p:contains(the Rain):has(i)");
        assertEquals(1, ps3.size());
    }

    public void testPseudoContains_6_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        // removed other assertion

        Elements ps2 = doc.select("p:contains(the rain)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ps3 = doc.select("p:contains(the Rain):has(i)");
        // removed other assertion
        assertEquals("light", ps3.first().className());
    }

    public void testPseudoContains_7_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        // removed other assertion

        Elements ps2 = doc.select("p:contains(the rain)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ps3 = doc.select("p:contains(the Rain):has(i)");
        // removed other assertion
        // removed other assertion

        Elements ps4 = doc.select(".light:contains(rain)");
        assertEquals(1, ps4.size());
    }

    public void testPseudoContains_8_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        // removed other assertion

        Elements ps2 = doc.select("p:contains(the rain)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ps3 = doc.select("p:contains(the Rain):has(i)");
        // removed other assertion
        // removed other assertion

        Elements ps4 = doc.select(".light:contains(rain)");
        // removed other assertion
        assertEquals("light", ps3.first().className());
    }

    public void testPseudoContains_9_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        // removed other assertion

        Elements ps2 = doc.select("p:contains(the rain)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ps3 = doc.select("p:contains(the Rain):has(i)");
        // removed other assertion
        // removed other assertion

        Elements ps4 = doc.select(".light:contains(rain)");
        // removed other assertion
        // removed other assertion

        Elements ps5 = doc.select(":contains(rain)");
        assertEquals(8,ps5.size());// html,body,div,...;
    }

    public void testPseudoContains_10_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<div><p>The Rain.</p> <p class=light>The <i>RAIN</i>.</p> <p>Rain, the.</p></div>");

        Elements ps1 = doc.select("p:contains(Rain)");
        // removed other assertion

        Elements ps2 = doc.select("p:contains(the rain)");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements ps3 = doc.select("p:contains(the Rain):has(i)");
        // removed other assertion
        // removed other assertion

        Elements ps4 = doc.select(".light:contains(rain)");
        // removed other assertion
        // removed other assertion

        Elements ps5 = doc.select(":contains(rain)");
        // removed other assertion
        Elements ps6 = doc.select(":contains(RAIN)");
        assertEquals(8, ps6.size());
    }

    @Test public void testPsuedoContainsWithParentheses_1_oe() {
        Document doc = Jsoup.parse("<div><p id=1>This (is good)</p><p id=2>This is bad)</p>");

        Elements ps1 = doc.select("p:contains(this (is good))");
        assertEquals(1, ps1.size());
        }

    @Test public void testPsuedoContainsWithParentheses_2_oe() {
        Document doc = Jsoup.parse("<div><p id=1>This (is good)</p><p id=2>This is bad)</p>");

        Elements ps1 = doc.select("p:contains(this (is good))");
        // removed other assertion
        assertEquals("1", ps1.first().id());
        }

    @Test public void testPsuedoContainsWithParentheses_3_oe() {
        Document doc = Jsoup.parse("<div><p id=1>This (is good)</p><p id=2>This is bad)</p>");

        Elements ps1 = doc.select("p:contains(this (is good))");
        // removed other assertion
        // removed other assertion

        Elements ps2 = doc.select("p:contains(this is bad\\))");
        assertEquals(1, ps2.size());
        }

    @Test public void testPsuedoContainsWithParentheses_4_oe() {
        Document doc = Jsoup.parse("<div><p id=1>This (is good)</p><p id=2>This is bad)</p>");

        Elements ps1 = doc.select("p:contains(this (is good))");
        // removed other assertion
        // removed other assertion

        Elements ps2 = doc.select("p:contains(this is bad\\))");
        // removed other assertion
        assertEquals("2", ps2.first().id());
        }

    @Test void containsWholeText_1_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser</div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeText( jsoup\n The HTML Parser)");
        Elements es2 = doc.select("p:containsWholeText(jsoup The HTML Parser)");
        assertEquals(1, es1.size());
        }

    @Test void containsWholeText_2_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser</div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeText( jsoup\n The HTML Parser)");
        Elements es2 = doc.select("p:containsWholeText(jsoup The HTML Parser)");
        // removed other assertion
        assertEquals(1, es2.size());
        }

    @Test void containsWholeText_3_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser</div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeText( jsoup\n The HTML Parser)");
        Elements es2 = doc.select("p:containsWholeText(jsoup The HTML Parser)");
        // removed other assertion
        // removed other assertion
        assertEquals(ps.get(0), es1.first());
        }

    @Test void containsWholeText_4_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser</div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeText( jsoup\n The HTML Parser)");
        Elements es2 = doc.select("p:containsWholeText(jsoup The HTML Parser)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ps.get(1), es2.first());
        }

    @Test void containsWholeText_5_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser</div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeText( jsoup\n The HTML Parser)");
        Elements es2 = doc.select("p:containsWholeText(jsoup The HTML Parser)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, doc.select("div:containsWholeText(jsoup the html parser)").size());
        }

    @Test void containsWholeText_6_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser</div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeText( jsoup\n The HTML Parser)");
        Elements es2 = doc.select("p:containsWholeText(jsoup The HTML Parser)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, doc.select("div:containsWholeText(jsoup\n the html parser)").size());
        }

    @Test void containsWholeText_7_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser</div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeText( jsoup\n The HTML Parser)");
        Elements es2 = doc.select("p:containsWholeText(jsoup The HTML Parser)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        doc = Jsoup.parse("<div><p></p><p> </p><p>.  </p>");
        Elements blanks = doc.select("p:containsWholeText(  )");
        assertEquals(1, blanks.size());
        }

    @Test void containsWholeText_8_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser</div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeText( jsoup\n The HTML Parser)");
        Elements es2 = doc.select("p:containsWholeText(jsoup The HTML Parser)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        doc = Jsoup.parse("<div><p></p><p> </p><p>.  </p>");
        Elements blanks = doc.select("p:containsWholeText(  )");
        // removed other assertion
        assertEquals(".  ", blanks.first().wholeText());
        }

    @Test void containsWholeOwnText_1_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser<br></div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeOwnText( jsoup\n The  Parser)");
        Elements es2 = doc.select("p:containsWholeOwnText(jsoup The HTML Parser\n)");
        assertEquals(1, es1.size());
        }

    @Test void containsWholeOwnText_2_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser<br></div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeOwnText( jsoup\n The  Parser)");
        Elements es2 = doc.select("p:containsWholeOwnText(jsoup The HTML Parser\n)");
        // removed other assertion
        assertEquals(1, es2.size());
        }

    @Test void containsWholeOwnText_3_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser<br></div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeOwnText( jsoup\n The  Parser)");
        Elements es2 = doc.select("p:containsWholeOwnText(jsoup The HTML Parser\n)");
        // removed other assertion
        // removed other assertion
        assertEquals(ps.get(0), es1.first());
        }

    @Test void containsWholeOwnText_4_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser<br></div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeOwnText( jsoup\n The  Parser)");
        Elements es2 = doc.select("p:containsWholeOwnText(jsoup The HTML Parser\n)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ps.get(1), es2.first());
        }

    @Test void containsWholeOwnText_5_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser<br></div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeOwnText( jsoup\n The  Parser)");
        Elements es2 = doc.select("p:containsWholeOwnText(jsoup The HTML Parser\n)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, doc.select("div:containsWholeOwnText(jsoup the html parser)").size());
        }

    @Test void containsWholeOwnText_6_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser<br></div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeOwnText( jsoup\n The  Parser)");
        Elements es2 = doc.select("p:containsWholeOwnText(jsoup The HTML Parser\n)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, doc.select("div:containsWholeOwnText(jsoup\n the  parser)").size());
        }

    @Test void containsWholeOwnText_7_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser<br></div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeOwnText( jsoup\n The  Parser)");
        Elements es2 = doc.select("p:containsWholeOwnText(jsoup The HTML Parser\n)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        doc = Jsoup.parse("<div><p></p><p> </p><p>.  </p>");
        Elements blanks = doc.select("p:containsWholeOwnText(  )");
        assertEquals(1, blanks.size());
        }

    @Test void containsWholeOwnText_8_oe() {
        Document doc = Jsoup.parse("<div><p> jsoup\n The <i>HTML</i> Parser</p><p>jsoup The HTML Parser<br></div>");
        Elements ps = doc.select("p");

        Elements es1 = doc.select("p:containsWholeOwnText( jsoup\n The  Parser)");
        Elements es2 = doc.select("p:containsWholeOwnText(jsoup The HTML Parser\n)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        doc = Jsoup.parse("<div><p></p><p> </p><p>.  </p>");
        Elements blanks = doc.select("p:containsWholeOwnText(  )");
        // removed other assertion
        assertEquals(".  ", blanks.first().wholeText());
        }

    @Test public void testMatches_1_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        assertEquals(0, p1.size());
        }

    @Test public void testMatches_2_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        // removed other assertion

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        assertEquals(1, p2.size());
        }

    @Test public void testMatches_3_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        // removed other assertion

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        // removed other assertion
        assertEquals("1", p2.first().id());
        }

    @Test public void testMatches_4_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        // removed other assertion

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        // removed other assertion
        // removed other assertion

        Elements p4 = doc.select("p:matches((?i)^rain$)"); // bounding
        assertEquals(1, p4.size());
        }

    @Test public void testMatches_5_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        // removed other assertion

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        // removed other assertion
        // removed other assertion

        Elements p4 = doc.select("p:matches((?i)^rain$)"); // bounding
        // removed other assertion
        assertEquals("4", p4.first().id());
        }

    @Test public void testMatches_6_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        // removed other assertion

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        // removed other assertion
        // removed other assertion

        Elements p4 = doc.select("p:matches((?i)^rain$)"); // bounding
        // removed other assertion
        // removed other assertion

        Elements p5 = doc.select("p:matches(\\d+)");
        assertEquals(1, p5.size());
        }

    @Test public void testMatches_7_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        // removed other assertion

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        // removed other assertion
        // removed other assertion

        Elements p4 = doc.select("p:matches((?i)^rain$)"); // bounding
        // removed other assertion
        // removed other assertion

        Elements p5 = doc.select("p:matches(\\d+)");
        // removed other assertion
        assertEquals("2", p5.first().id());
        }

    @Test public void testMatches_8_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        // removed other assertion

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        // removed other assertion
        // removed other assertion

        Elements p4 = doc.select("p:matches((?i)^rain$)"); // bounding
        // removed other assertion
        // removed other assertion

        Elements p5 = doc.select("p:matches(\\d+)");
        // removed other assertion
        // removed other assertion

        Elements p6 = doc.select("p:matches(\\w+\\s+\\(\\w+\\))"); // test bracket matching
        assertEquals(1, p6.size());
        }

    @Test public void testMatches_9_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        // removed other assertion

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        // removed other assertion
        // removed other assertion

        Elements p4 = doc.select("p:matches((?i)^rain$)"); // bounding
        // removed other assertion
        // removed other assertion

        Elements p5 = doc.select("p:matches(\\d+)");
        // removed other assertion
        // removed other assertion

        Elements p6 = doc.select("p:matches(\\w+\\s+\\(\\w+\\))"); // test bracket matching
        // removed other assertion
        assertEquals("3", p6.first().id());
        }

    @Test public void testMatches_10_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        // removed other assertion

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        // removed other assertion
        // removed other assertion

        Elements p4 = doc.select("p:matches((?i)^rain$)"); // bounding
        // removed other assertion
        // removed other assertion

        Elements p5 = doc.select("p:matches(\\d+)");
        // removed other assertion
        // removed other assertion

        Elements p6 = doc.select("p:matches(\\w+\\s+\\(\\w+\\))"); // test bracket matching
        // removed other assertion
        // removed other assertion

        Elements p7 = doc.select("p:matches((?i)the):has(i)"); // multi
        assertEquals(1, p7.size());
        }

    @Test public void testMatches_11_oe() {
        Document doc = Jsoup.parse("<p id=1>The <i>Rain</i></p> <p id=2>There are 99 bottles.</p> <p id=3>Harder (this)</p> <p id=4>Rain</p>");

        Elements p1 = doc.select("p:matches(The rain)"); // no match, case sensitive
        // removed other assertion

        Elements p2 = doc.select("p:matches((?i)the rain)"); // case insense. should include root, html, body
        // removed other assertion
        // removed other assertion

        Elements p4 = doc.select("p:matches((?i)^rain$)"); // bounding
        // removed other assertion
        // removed other assertion

        Elements p5 = doc.select("p:matches(\\d+)");
        // removed other assertion
        // removed other assertion

        Elements p6 = doc.select("p:matches(\\w+\\s+\\(\\w+\\))"); // test bracket matching
        // removed other assertion
        // removed other assertion

        Elements p7 = doc.select("p:matches((?i)the):has(i)"); // multi
        // removed other assertion
        assertEquals("1", p7.first().id());
        }

    @Test public void matchesOwn_1_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b> now</p>");

        Elements p1 = doc.select("p:matchesOwn((?i)hello now)");
        assertEquals(1, p1.size());
        }

    @Test public void matchesOwn_2_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b> now</p>");

        Elements p1 = doc.select("p:matchesOwn((?i)hello now)");
        // removed other assertion
        assertEquals("1", p1.first().id());
        }

    @Test public void matchesOwn_3_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b> now</p>");

        Elements p1 = doc.select("p:matchesOwn((?i)hello now)");
        // removed other assertion
        // removed other assertion

        assertEquals(0, doc.select("p:matchesOwn(there)").size());
        }

    @Test public void matchesWholeText_1_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3></p>");

        Elements p1 = doc.select("p:matchesWholeText((?i)hello there\n now)");
        assertEquals(1, p1.size());
        }

    @Test public void matchesWholeText_2_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3></p>");

        Elements p1 = doc.select("p:matchesWholeText((?i)hello there\n now)");
        // removed other assertion
        assertEquals("1", p1.first().id());
        }

    @Test public void matchesWholeText_3_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3></p>");

        Elements p1 = doc.select("p:matchesWholeText((?i)hello there\n now)");
        // removed other assertion
        // removed other assertion

        assertEquals(1, doc.select("p:matchesWholeText(there\n now)").size());
        }

    @Test public void matchesWholeText_4_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3></p>");

        Elements p1 = doc.select("p:matchesWholeText((?i)hello there\n now)");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, doc.select("p:matchesWholeText(There\n now)").size());
        }

    @Test public void matchesWholeText_5_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3></p>");

        Elements p1 = doc.select("p:matchesWholeText((?i)hello there\n now)");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Elements p2 = doc.select("p:matchesWholeText(^\\s+$)");
        assertEquals(1, p2.size());
        }

    @Test public void matchesWholeText_6_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3></p>");

        Elements p1 = doc.select("p:matchesWholeText((?i)hello there\n now)");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Elements p2 = doc.select("p:matchesWholeText(^\\s+$)");
        // removed other assertion
        assertEquals("2", p2.first().id());
        }

    @Test public void matchesWholeText_7_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3></p>");

        Elements p1 = doc.select("p:matchesWholeText((?i)hello there\n now)");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Elements p2 = doc.select("p:matchesWholeText(^\\s+$)");
        // removed other assertion
        // removed other assertion

        Elements p3 = doc.select("p:matchesWholeText(^$)");
        assertEquals(1, p3.size());
        }

    @Test public void matchesWholeText_8_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3></p>");

        Elements p1 = doc.select("p:matchesWholeText((?i)hello there\n now)");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Elements p2 = doc.select("p:matchesWholeText(^\\s+$)");
        // removed other assertion
        // removed other assertion

        Elements p3 = doc.select("p:matchesWholeText(^$)");
        // removed other assertion
        assertEquals("3", p3.first().id());
        }

    @Test public void matchesWholeOwnText_1_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3><i>Text</i></p>");

        Elements p1 = doc.select("p:matchesWholeOwnText((?i)hello \n now)");
        assertEquals(1, p1.size());
        }

    @Test public void matchesWholeOwnText_2_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3><i>Text</i></p>");

        Elements p1 = doc.select("p:matchesWholeOwnText((?i)hello \n now)");
        // removed other assertion
        assertEquals("1", p1.first().id());
        }

    @Test public void matchesWholeOwnText_3_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3><i>Text</i></p>");

        Elements p1 = doc.select("p:matchesWholeOwnText((?i)hello \n now)");
        // removed other assertion
        // removed other assertion

        assertEquals(0, doc.select("p:matchesWholeOwnText(there\n now)").size());
        }

    @Test public void matchesWholeOwnText_4_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3><i>Text</i></p>");

        Elements p1 = doc.select("p:matchesWholeOwnText((?i)hello \n now)");
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Elements p2 = doc.select("p:matchesWholeOwnText(^\\s+$)");
        assertEquals(1, p2.size());
        }

    @Test public void matchesWholeOwnText_5_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3><i>Text</i></p>");

        Elements p1 = doc.select("p:matchesWholeOwnText((?i)hello \n now)");
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Elements p2 = doc.select("p:matchesWholeOwnText(^\\s+$)");
        // removed other assertion
        assertEquals("2", p2.first().id());
        }

    @Test public void matchesWholeOwnText_6_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3><i>Text</i></p>");

        Elements p1 = doc.select("p:matchesWholeOwnText((?i)hello \n now)");
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Elements p2 = doc.select("p:matchesWholeOwnText(^\\s+$)");
        // removed other assertion
        // removed other assertion

        Elements p3 = doc.select("p:matchesWholeOwnText(^$)");
        assertEquals(1, p3.size());
        }

    @Test public void matchesWholeOwnText_7_oe() {
        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b>\n now</p><p id=2> </p><p id=3><i>Text</i></p>");

        Elements p1 = doc.select("p:matchesWholeOwnText((?i)hello \n now)");
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Elements p2 = doc.select("p:matchesWholeOwnText(^\\s+$)");
        // removed other assertion
        // removed other assertion

        Elements p3 = doc.select("p:matchesWholeOwnText(^$)");
        // removed other assertion
        assertEquals("3", p3.first().id());
        }

    @Test public void testRelaxedTags_1_oe() {
        Document doc = Jsoup.parse("<abc_def id=1>Hello</abc_def> <abc-def id=2>There</abc-def>");

        Elements el1 = doc.select("abc_def");
        assertEquals(1, el1.size());
        }

    @Test public void testRelaxedTags_2_oe() {
        Document doc = Jsoup.parse("<abc_def id=1>Hello</abc_def> <abc-def id=2>There</abc-def>");

        Elements el1 = doc.select("abc_def");
        // removed other assertion
        assertEquals("1", el1.first().id());
        }

    @Test public void testRelaxedTags_3_oe() {
        Document doc = Jsoup.parse("<abc_def id=1>Hello</abc_def> <abc-def id=2>There</abc-def>");

        Elements el1 = doc.select("abc_def");
        // removed other assertion
        // removed other assertion

        Elements el2 = doc.select("abc-def");
        assertEquals(1, el2.size());
        }

    @Test public void testRelaxedTags_4_oe() {
        Document doc = Jsoup.parse("<abc_def id=1>Hello</abc_def> <abc-def id=2>There</abc-def>");

        Elements el1 = doc.select("abc_def");
        // removed other assertion
        // removed other assertion

        Elements el2 = doc.select("abc-def");
        // removed other assertion
        assertEquals("2", el2.first().id());
        }

    @Test public void notParas_1_oe() {
        Document doc = Jsoup.parse("<p id=1>One</p> <p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.select("p:not([id=1])");
        assertEquals(2, el1.size());
        }

    @Test public void notParas_2_oe() {
        Document doc = Jsoup.parse("<p id=1>One</p> <p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.select("p:not([id=1])");
        // removed other assertion
        assertEquals("Two", el1.first().text());
        }

    @Test public void notParas_3_oe() {
        Document doc = Jsoup.parse("<p id=1>One</p> <p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.select("p:not([id=1])");
        // removed other assertion
        // removed other assertion
        assertEquals("Three", el1.last().text());
        }

    @Test public void notParas_4_oe() {
        Document doc = Jsoup.parse("<p id=1>One</p> <p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.select("p:not([id=1])");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements el2 = doc.select("p:not(:has(span))");
        assertEquals(2, el2.size());
        }

    @Test public void notParas_5_oe() {
        Document doc = Jsoup.parse("<p id=1>One</p> <p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.select("p:not([id=1])");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements el2 = doc.select("p:not(:has(span))");
        // removed other assertion
        assertEquals("One", el2.first().text());
        }

    @Test public void notParas_6_oe() {
        Document doc = Jsoup.parse("<p id=1>One</p> <p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.select("p:not([id=1])");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Elements el2 = doc.select("p:not(:has(span))");
        // removed other assertion
        // removed other assertion
        assertEquals("Two", el2.last().text());
        }

    @Test public void notAll_1_oe() {
        Document doc = Jsoup.parse("<p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.body().select(":not(p)"); // should just be the span
        assertEquals(2, el1.size());
        }

    @Test public void notAll_2_oe() {
        Document doc = Jsoup.parse("<p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.body().select(":not(p)"); // should just be the span
        // removed other assertion
        assertEquals("body", el1.first().tagName());
        }

    @Test public void notAll_3_oe() {
        Document doc = Jsoup.parse("<p>Two</p> <p><span>Three</span></p>");

        Elements el1 = doc.body().select(":not(p)"); // should just be the span
        // removed other assertion
        // removed other assertion
        assertEquals("span", el1.last().tagName());
        }

    @Test public void notClass_1_oe() {
        Document doc = Jsoup.parse("<div class=left>One</div><div class=right id=1><p>Two</p></div>");

        Elements el1 = doc.select("div:not(.left)");
        assertEquals(1, el1.size());
        }

    @Test public void notClass_2_oe() {
        Document doc = Jsoup.parse("<div class=left>One</div><div class=right id=1><p>Two</p></div>");

        Elements el1 = doc.select("div:not(.left)");
        // removed other assertion
        assertEquals("1", el1.first().id());
        }

    @Test public void handlesCommasInSelector_1_oe() {
        Document doc = Jsoup.parse("<p name='1,2'>One</p><div>Two</div><ol><li>123</li><li>Text</li></ol>");

        Elements ps = doc.select("[name=1,2]");
        assertEquals(1, ps.size());
        }

    @Test public void handlesCommasInSelector_2_oe() {
        Document doc = Jsoup.parse("<p name='1,2'>One</p><div>Two</div><ol><li>123</li><li>Text</li></ol>");

        Elements ps = doc.select("[name=1,2]");
        // removed other assertion

        Elements containers = doc.select("div, li:matches([0-9,]+)");
        assertEquals(2, containers.size());
        }

    @Test public void handlesCommasInSelector_3_oe() {
        Document doc = Jsoup.parse("<p name='1,2'>One</p><div>Two</div><ol><li>123</li><li>Text</li></ol>");

        Elements ps = doc.select("[name=1,2]");
        // removed other assertion

        Elements containers = doc.select("div, li:matches([0-9,]+)");
        // removed other assertion
        assertEquals("div", containers.get(0).tagName());
        }

    @Test public void handlesCommasInSelector_4_oe() {
        Document doc = Jsoup.parse("<p name='1,2'>One</p><div>Two</div><ol><li>123</li><li>Text</li></ol>");

        Elements ps = doc.select("[name=1,2]");
        // removed other assertion

        Elements containers = doc.select("div, li:matches([0-9,]+)");
        // removed other assertion
        // removed other assertion
        assertEquals("li", containers.get(1).tagName());
        }

    @Test public void handlesCommasInSelector_5_oe() {
        Document doc = Jsoup.parse("<p name='1,2'>One</p><div>Two</div><ol><li>123</li><li>Text</li></ol>");

        Elements ps = doc.select("[name=1,2]");
        // removed other assertion

        Elements containers = doc.select("div, li:matches([0-9,]+)");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("123", containers.get(1).text());
        }

    @Test public void selectSupplementaryCharacter_1_oe() {
        String s = new String(Character.toChars(135361));
        Document doc = Jsoup.parse("<div k" + s + "='" + s + "'>^" + s +"$/div>");
        assertEquals("div", doc.select("div[k" + s + "]").first().tagName());
        }

    @Test public void selectSupplementaryCharacter_2_oe() {
        String s = new String(Character.toChars(135361));
        Document doc = Jsoup.parse("<div k" + s + "='" + s + "'>^" + s +"$/div>");
        // removed other assertion
        assertEquals("div", doc.select("div:containsOwn(" + s + ")").first().tagName());
        }

    @Test
    public void selectClassWithSpace_1_oe() {
        final String html = "<div class=\"value\">class without space</div>\n"
                          + "<div class=\"value \">class with space</div>";

        Document doc = Jsoup.parse(html);

        Elements found = doc.select("div[class=value ]");
        assertEquals(2, found.size());
    }

    @Test
    public void selectClassWithSpace_2_oe() {
        final String html = "<div class=\"value\">class without space</div>\n"
                          + "<div class=\"value \">class with space</div>";

        Document doc = Jsoup.parse(html);

        Elements found = doc.select("div[class=value ]");
        // removed other assertion
        assertEquals("class without space", found.get(0).text());
    }

    @Test
    public void selectClassWithSpace_3_oe() {
        final String html = "<div class=\"value\">class without space</div>\n"
                          + "<div class=\"value \">class with space</div>";

        Document doc = Jsoup.parse(html);

        Elements found = doc.select("div[class=value ]");
        // removed other assertion
        // removed other assertion
        assertEquals("class with space", found.get(1).text());
    }

    @Test
    public void selectClassWithSpace_4_oe() {
        final String html = "<div class=\"value\">class without space</div>\n"
                          + "<div class=\"value \">class with space</div>";

        Document doc = Jsoup.parse(html);

        Elements found = doc.select("div[class=value ]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        found = doc.select("div[class=\"value \"]");
        assertEquals(2, found.size());
    }

    @Test
    public void selectClassWithSpace_5_oe() {
        final String html = "<div class=\"value\">class without space</div>\n"
                          + "<div class=\"value \">class with space</div>";

        Document doc = Jsoup.parse(html);

        Elements found = doc.select("div[class=value ]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        found = doc.select("div[class=\"value \"]");
        // removed other assertion
        assertEquals("class without space", found.get(0).text());
    }

    @Test
    public void selectClassWithSpace_6_oe() {
        final String html = "<div class=\"value\">class without space</div>\n"
                          + "<div class=\"value \">class with space</div>";

        Document doc = Jsoup.parse(html);

        Elements found = doc.select("div[class=value ]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        found = doc.select("div[class=\"value \"]");
        // removed other assertion
        // removed other assertion
        assertEquals("class with space", found.get(1).text());
    }

    @Test
    public void selectClassWithSpace_7_oe() {
        final String html = "<div class=\"value\">class without space</div>\n"
                          + "<div class=\"value \">class with space</div>";

        Document doc = Jsoup.parse(html);

        Elements found = doc.select("div[class=value ]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        found = doc.select("div[class=\"value \"]");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        found = doc.select("div[class=\"value\\ \"]");
        assertEquals(0, found.size());
    }

    @Test public void selectSameElements_1_oe() {
        final String html = "<div>one</div><div>one</div>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("div");
        assertEquals(2, els.size());
        }

    @Test public void selectSameElements_2_oe() {
        final String html = "<div>one</div><div>one</div>";

        Document doc = Jsoup.parse(html);
        Elements els = doc.select("div");
        // removed other assertion

        Elements subSelect = els.select(":contains(one)");
        assertEquals(2, subSelect.size());
        }

    @Test public void attributeWithBrackets_1_oe() {
        String html = "<div data='End]'>One</div> <div data='[Another)]]'>Two</div>";
        Document doc = Jsoup.parse(html);
        assertEquals("One", doc.select("div[data='End]']").first().text());
        }

    @Test public void attributeWithBrackets_2_oe() {
        String html = "<div data='End]'>One</div> <div data='[Another)]]'>Two</div>";
        Document doc = Jsoup.parse(html);
        // removed other assertion
        assertEquals("Two", doc.select("div[data='[Another)]]']").first().text());
        }

    @Test public void attributeWithBrackets_3_oe() {
        String html = "<div data='End]'>One</div> <div data='[Another)]]'>Two</div>";
        Document doc = Jsoup.parse(html);
        // removed other assertion
        // removed other assertion
        assertEquals("One", doc.select("div[data=\"End]\"]").first().text());
        }

    @Test public void attributeWithBrackets_4_oe() {
        String html = "<div data='End]'>One</div> <div data='[Another)]]'>Two</div>";
        Document doc = Jsoup.parse(html);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Two", doc.select("div[data=\"[Another)]]\"]").first().text());
        }

    @Test public void containsWithQuote_1_oe() {
        String html = "<p>One'One</p><p>One'Two</p>";
        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p:contains(One\\'One)");
        assertEquals(1, els.size());
        }

    @Test public void containsWithQuote_2_oe() {
        String html = "<p>One'One</p><p>One'Two</p>";
        Document doc = Jsoup.parse(html);
        Elements els = doc.select("p:contains(One\\'One)");
        // removed other assertion
        assertEquals("One'One", els.text());
        }

    @Test public void selectFirst_1_oe() {
        String html = "<p>One<p>Two<p>Three";
        Document doc = Jsoup.parse(html);
        assertEquals("One", doc.selectFirst("p").text());
        }

    @Test public void selectFirstWithAnd_1_oe() {
        String html = "<p>One<p class=foo>Two<p>Three";
        Document doc = Jsoup.parse(html);
        assertEquals("Two", doc.selectFirst("p.foo").text());
        }

    @Test public void selectFirstWithOr_1_oe() {
        String html = "<p>One<p>Two<p>Three<div>Four";
        Document doc = Jsoup.parse(html);
        assertEquals("One", doc.selectFirst("p, div").text());
        }

    @Test public void matchText_1_oe() {
        String html = "<p>One<br>Two</p>";
        Document doc = Jsoup.parse(html);
        String origHtml = doc.html();

        Elements one = doc.select("p:matchText:first-child");
        assertEquals("One", one.first().text());
        }

    @Test public void matchText_2_oe() {
        String html = "<p>One<br>Two</p>";
        Document doc = Jsoup.parse(html);
        String origHtml = doc.html();

        Elements one = doc.select("p:matchText:first-child");
        // removed other assertion

        Elements two = doc.select("p:matchText:last-child");
        assertEquals("Two", two.first().text());
        }

    @Test public void matchText_3_oe() {
        String html = "<p>One<br>Two</p>";
        Document doc = Jsoup.parse(html);
        String origHtml = doc.html();

        Elements one = doc.select("p:matchText:first-child");
        // removed other assertion

        Elements two = doc.select("p:matchText:last-child");
        // removed other assertion

        assertEquals(origHtml, doc.html());
        }

    @Test public void matchText_4_oe() {
        String html = "<p>One<br>Two</p>";
        Document doc = Jsoup.parse(html);
        String origHtml = doc.html();

        Elements one = doc.select("p:matchText:first-child");
        // removed other assertion

        Elements two = doc.select("p:matchText:last-child");
        // removed other assertion

        // removed other assertion

        assertEquals("Two", doc.select("p:matchText + br + *").text());
        }

    @Test public void nthLastChildWithNoParent_1_oe() {
        Element el = new Element("p").text("Orphan");
        Elements els = el.select("p:nth-last-child(1)");
        assertEquals(0, els.size());
        }

    @Test public void splitOnBr_1_oe() {
        String html = "<div><p>One<br>Two<br>Three</p></div>";
        Document doc = Jsoup.parse(html);

        Elements els = doc.select("p:matchText");
        assertEquals(3, els.size());
        }

    @Test public void splitOnBr_2_oe() {
        String html = "<div><p>One<br>Two<br>Three</p></div>";
        Document doc = Jsoup.parse(html);

        Elements els = doc.select("p:matchText");
        // removed other assertion
        assertEquals("One", els.get(0).text());
        }

    @Test public void splitOnBr_3_oe() {
        String html = "<div><p>One<br>Two<br>Three</p></div>";
        Document doc = Jsoup.parse(html);

        Elements els = doc.select("p:matchText");
        // removed other assertion
        // removed other assertion
        assertEquals("Two", els.get(1).text());
        }

    @Test public void splitOnBr_4_oe() {
        String html = "<div><p>One<br>Two<br>Three</p></div>";
        Document doc = Jsoup.parse(html);

        Elements els = doc.select("p:matchText");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Three", els.get(2).toString());
        }

    @Test public void matchTextAttributes_1_oe() {
        Document doc = Jsoup.parse("<div><p class=one>One<br>Two<p class=two>Three<br>Four");
        Elements els = doc.select("p.two:matchText:last-child");

        assertEquals(1, els.size());
        }

    @Test public void matchTextAttributes_2_oe() {
        Document doc = Jsoup.parse("<div><p class=one>One<br>Two<p class=two>Three<br>Four");
        Elements els = doc.select("p.two:matchText:last-child");

        // removed other assertion
        assertEquals("Four", els.text());
        }

    @Test public void findBetweenSpan_1_oe() {
        Document doc = Jsoup.parse("<p><span>One</span> Two <span>Three</span>");
        Elements els = doc.select("span ~ p:matchText"); // the Two becomes its own p, sibling of the span
        // todo - think this should really be 'p:matchText span ~ p'. The :matchText should behave as a modifier to expand the nodes.

        assertEquals(1, els.size());
        }

    @Test public void findBetweenSpan_2_oe() {
        Document doc = Jsoup.parse("<p><span>One</span> Two <span>Three</span>");
        Elements els = doc.select("span ~ p:matchText"); // the Two becomes its own p, sibling of the span
        // todo - think this should really be 'p:matchText span ~ p'. The :matchText should behave as a modifier to expand the nodes.

        // removed other assertion
        assertEquals("Two", els.text());
        }

    @Test public void startsWithBeginsWithSpace_1_oe() {
        Document doc = Jsoup.parse("<small><a href=\" mailto:abc@def.net\">(abc@def.net)</a></small>");
        Elements els = doc.select("a[href^=' mailto']");

        assertEquals(1, els.size());
        }

    @Test public void endsWithEndsWithSpaces_1_oe() {
        Document doc = Jsoup.parse("<small><a href=\" mailto:abc@def.net \">(abc@def.net)</a></small>");
        Elements els = doc.select("a[href$='.net ']");

        assertEquals(1, els.size());
        }

    @Test
    public void html_mixed_case_simple_name_1_oe() {
        Document doc = Jsoup.parse(mixedCase, "", Parser.htmlParser());
        assertEquals(0, doc.select("mixedCase").size());
    }

    @Test
    public void html_mixed_case_wildcard_name_1_oe() {
        Document doc = Jsoup.parse(mixedCase, "", Parser.htmlParser());
        assertEquals(1, doc.select("*|mixedCase").size());
    }

    @Test
    public void html_lowercase_simple_name_1_oe() {
        Document doc = Jsoup.parse(lowercase, "", Parser.htmlParser());
        assertEquals(0, doc.select("lowercase").size());
    }

    @Test
    public void html_lowercase_wildcard_name_1_oe() {
        Document doc = Jsoup.parse(lowercase, "", Parser.htmlParser());
        assertEquals(1, doc.select("*|lowercase").size());
    }

    @Test
    public void xml_mixed_case_simple_name_1_oe() {
        Document doc = Jsoup.parse(mixedCase, "", Parser.xmlParser());
        assertEquals(0, doc.select("mixedCase").size());
    }

    @Test
    public void xml_mixed_case_wildcard_name_1_oe() {
        Document doc = Jsoup.parse(mixedCase, "", Parser.xmlParser());
        assertEquals(1, doc.select("*|mixedCase").size());
    }

    @Test
    public void xml_lowercase_simple_name_1_oe() {
        Document doc = Jsoup.parse(lowercase, "", Parser.xmlParser());
        assertEquals(0, doc.select("lowercase").size());
    }

    @Test
    public void xml_lowercase_wildcard_name_1_oe() {
        Document doc = Jsoup.parse(lowercase, "", Parser.xmlParser());
        assertEquals(1, doc.select("*|lowercase").size());
    }

    @Test
    public void trimSelector_1_oe() {
        // https://github.com/jhy/jsoup/issues/1274
        Document doc = Jsoup.parse("<p><span>Hello");
        Elements els = doc.select(" p span ");
        assertEquals(1, els.size());
    }

    @Test
    public void trimSelector_2_oe() {
        // https://github.com/jhy/jsoup/issues/1274
        Document doc = Jsoup.parse("<p><span>Hello");
        Elements els = doc.select(" p span ");
        // removed other assertion
        assertEquals("Hello", els.first().text());
    }

    @Test
    public void xmlWildcardNamespaceTest_1_oe() {
        // https://github.com/jhy/jsoup/issues/1208
        Document doc = Jsoup.parse("<ns1:MyXmlTag>1111</ns1:MyXmlTag><ns2:MyXmlTag>2222</ns2:MyXmlTag>", "", Parser.xmlParser());
        Elements select = doc.select("*|MyXmlTag");
        assertEquals(2, select.size());
    }

    @Test
    public void xmlWildcardNamespaceTest_2_oe() {
        // https://github.com/jhy/jsoup/issues/1208
        Document doc = Jsoup.parse("<ns1:MyXmlTag>1111</ns1:MyXmlTag><ns2:MyXmlTag>2222</ns2:MyXmlTag>", "", Parser.xmlParser());
        Elements select = doc.select("*|MyXmlTag");
        // removed other assertion
        assertEquals("1111", select.get(0).text());
    }

    @Test
    public void xmlWildcardNamespaceTest_3_oe() {
        // https://github.com/jhy/jsoup/issues/1208
        Document doc = Jsoup.parse("<ns1:MyXmlTag>1111</ns1:MyXmlTag><ns2:MyXmlTag>2222</ns2:MyXmlTag>", "", Parser.xmlParser());
        Elements select = doc.select("*|MyXmlTag");
        // removed other assertion
        // removed other assertion
        assertEquals("2222", select.get(1).text());
    }

    @Test
    public void childElements_1_oe() {
        // https://github.com/jhy/jsoup/issues/1292
        String html = "<body><span id=1>One <span id=2>Two</span></span></body>";
        Document doc = Jsoup.parse(html);

        Element outer = doc.selectFirst("span");
        Element span = outer.selectFirst("span");
        Element inner = outer.selectFirst("* span");

        assertEquals("1", outer.id());
    }

    @Test
    public void childElements_2_oe() {
        // https://github.com/jhy/jsoup/issues/1292
        String html = "<body><span id=1>One <span id=2>Two</span></span></body>";
        Document doc = Jsoup.parse(html);

        Element outer = doc.selectFirst("span");
        Element span = outer.selectFirst("span");
        Element inner = outer.selectFirst("* span");

        // removed other assertion
        assertEquals("1", span.id());
    }

    @Test
    public void childElements_3_oe() {
        // https://github.com/jhy/jsoup/issues/1292
        String html = "<body><span id=1>One <span id=2>Two</span></span></body>";
        Document doc = Jsoup.parse(html);

        Element outer = doc.selectFirst("span");
        Element span = outer.selectFirst("span");
        Element inner = outer.selectFirst("* span");

        // removed other assertion
        // removed other assertion
        assertEquals("2", inner.id());
    }

    @Test
    public void childElements_4_oe() {
        // https://github.com/jhy/jsoup/issues/1292
        String html = "<body><span id=1>One <span id=2>Two</span></span></body>";
        Document doc = Jsoup.parse(html);

        Element outer = doc.selectFirst("span");
        Element span = outer.selectFirst("span");
        Element inner = outer.selectFirst("* span");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(outer, span);
    }

    @Test
    public void childElements_5_oe() {
        // https://github.com/jhy/jsoup/issues/1292
        String html = "<body><span id=1>One <span id=2>Two</span></span></body>";
        Document doc = Jsoup.parse(html);

        Element outer = doc.selectFirst("span");
        Element span = outer.selectFirst("span");
        Element inner = outer.selectFirst("* span");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(outer, inner);
    }

    @Test
    public void selectFirstLevelChildrenOnly_1_oe() {
        // testcase for https://github.com/jhy/jsoup/issues/984
        String html = "<div><span>One <span>Two</span></span> <span>Three <span>Four</span></span>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        assertNotNull(div);
    }

    @Test
    public void selectFirstLevelChildrenOnly_2_oe() {
        // testcase for https://github.com/jhy/jsoup/issues/984
        String html = "<div><span>One <span>Two</span></span> <span>Three <span>Four</span></span>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        // removed other assertion

        // want to select One and Three only - the first level children
        Elements spans = div.select(":root > span");
        assertEquals(2, spans.size());
    }

    @Test
    public void selectFirstLevelChildrenOnly_3_oe() {
        // testcase for https://github.com/jhy/jsoup/issues/984
        String html = "<div><span>One <span>Two</span></span> <span>Three <span>Four</span></span>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        // removed other assertion

        // want to select One and Three only - the first level children
        Elements spans = div.select(":root > span");
        // removed other assertion
        assertEquals("One Two", spans.get(0).text());
    }

    @Test
    public void selectFirstLevelChildrenOnly_4_oe() {
        // testcase for https://github.com/jhy/jsoup/issues/984
        String html = "<div><span>One <span>Two</span></span> <span>Three <span>Four</span></span>";
        Document doc = Jsoup.parse(html);

        Element div = doc.selectFirst("div");
        // removed other assertion

        // want to select One and Three only - the first level children
        Elements spans = div.select(":root > span");
        // removed other assertion
        // removed other assertion
        assertEquals("Three Four", spans.get(1).text());
    }

    @Test
    public void wildcardNamespaceMatchesNoNamespace_1_oe() {
        // https://github.com/jhy/jsoup/issues/1565
        String xml = "<package><meta>One</meta><opf:meta>Two</opf:meta></package>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Elements metaEls = doc.select("meta");
        assertEquals(1, metaEls.size());
    }

    @Test
    public void wildcardNamespaceMatchesNoNamespace_2_oe() {
        // https://github.com/jhy/jsoup/issues/1565
        String xml = "<package><meta>One</meta><opf:meta>Two</opf:meta></package>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Elements metaEls = doc.select("meta");
        // removed other assertion
        assertEquals("One", metaEls.get(0).text());
    }

    @Test
    public void wildcardNamespaceMatchesNoNamespace_3_oe() {
        // https://github.com/jhy/jsoup/issues/1565
        String xml = "<package><meta>One</meta><opf:meta>Two</opf:meta></package>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Elements metaEls = doc.select("meta");
        // removed other assertion
        // removed other assertion

        Elements nsEls = doc.select("*|meta");
        assertEquals(2, nsEls.size());
    }

    @Test
    public void wildcardNamespaceMatchesNoNamespace_4_oe() {
        // https://github.com/jhy/jsoup/issues/1565
        String xml = "<package><meta>One</meta><opf:meta>Two</opf:meta></package>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Elements metaEls = doc.select("meta");
        // removed other assertion
        // removed other assertion

        Elements nsEls = doc.select("*|meta");
        // removed other assertion
        assertEquals("One", nsEls.get(0).text());
    }

    @Test
    public void wildcardNamespaceMatchesNoNamespace_5_oe() {
        // https://github.com/jhy/jsoup/issues/1565
        String xml = "<package><meta>One</meta><opf:meta>Two</opf:meta></package>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Elements metaEls = doc.select("meta");
        // removed other assertion
        // removed other assertion

        Elements nsEls = doc.select("*|meta");
        // removed other assertion
        // removed other assertion
        assertEquals("Two", nsEls.get(1).text());
    }

    @Test void containsTextQueryIsNormalized_1_oe() {
        Document doc = Jsoup.parse("<p><p id=1>Hello  there now<em>!</em>");
        Elements a = doc.select("p:contains(Hello   there  now!)");
        Elements b = doc.select(":containsOwn(hello   there  now)");
        Elements c = doc.select("p:contains(Hello there now)");
        Elements d = doc.select(":containsOwn(hello There now)");
        Elements e = doc.select("p:contains(HelloThereNow)");

        assertEquals(1, a.size());
        }

    @Test void containsTextQueryIsNormalized_2_oe() {
        Document doc = Jsoup.parse("<p><p id=1>Hello  there now<em>!</em>");
        Elements a = doc.select("p:contains(Hello   there  now!)");
        Elements b = doc.select(":containsOwn(hello   there  now)");
        Elements c = doc.select("p:contains(Hello there now)");
        Elements d = doc.select(":containsOwn(hello There now)");
        Elements e = doc.select("p:contains(HelloThereNow)");

        // removed other assertion
        assertEquals(a, b);
        }

    @Test void containsTextQueryIsNormalized_3_oe() {
        Document doc = Jsoup.parse("<p><p id=1>Hello  there now<em>!</em>");
        Elements a = doc.select("p:contains(Hello   there  now!)");
        Elements b = doc.select(":containsOwn(hello   there  now)");
        Elements c = doc.select("p:contains(Hello there now)");
        Elements d = doc.select(":containsOwn(hello There now)");
        Elements e = doc.select("p:contains(HelloThereNow)");

        // removed other assertion
        // removed other assertion
        assertEquals(a, c);
        }

    @Test void containsTextQueryIsNormalized_4_oe() {
        Document doc = Jsoup.parse("<p><p id=1>Hello  there now<em>!</em>");
        Elements a = doc.select("p:contains(Hello   there  now!)");
        Elements b = doc.select(":containsOwn(hello   there  now)");
        Elements c = doc.select("p:contains(Hello there now)");
        Elements d = doc.select(":containsOwn(hello There now)");
        Elements e = doc.select("p:contains(HelloThereNow)");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(a, d);
        }

    @Test void containsTextQueryIsNormalized_5_oe() {
        Document doc = Jsoup.parse("<p><p id=1>Hello  there now<em>!</em>");
        Elements a = doc.select("p:contains(Hello   there  now!)");
        Elements b = doc.select(":containsOwn(hello   there  now)");
        Elements c = doc.select("p:contains(Hello there now)");
        Elements d = doc.select(":containsOwn(hello There now)");
        Elements e = doc.select("p:contains(HelloThereNow)");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, e.size());
        }

    @Test void containsTextQueryIsNormalized_6_oe() {
        Document doc = Jsoup.parse("<p><p id=1>Hello  there now<em>!</em>");
        Elements a = doc.select("p:contains(Hello   there  now!)");
        Elements b = doc.select(":containsOwn(hello   there  now)");
        Elements c = doc.select("p:contains(Hello there now)");
        Elements d = doc.select(":containsOwn(hello There now)");
        Elements e = doc.select("p:contains(HelloThereNow)");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(a, e);
        }

    @Test public void selectorExceptionNotStringFormatException_1_oe() {
        Selector.SelectorParseException ex = new Selector.SelectorParseException("%&");
        assertEquals("%&", ex.getMessage());
        }

@MultiLocaleTest
    public void containsOwn_1_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b> igor</p>");
        Elements ps = doc.select("p:containsOwn(Hello IGOR)");
        assertEquals(1, ps.size());
    }

@MultiLocaleTest
    public void containsOwn_2_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b> igor</p>");
        Elements ps = doc.select("p:containsOwn(Hello IGOR)");
        // removed other assertion
        assertEquals("1", ps.first().id());
    }

@MultiLocaleTest
    public void containsOwn_3_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b> igor</p>");
        Elements ps = doc.select("p:containsOwn(Hello IGOR)");
        // removed other assertion
        // removed other assertion

        assertEquals(0, doc.select("p:containsOwn(there)").size());
    }

@MultiLocaleTest
    public void containsOwn_4_oe(Locale locale) {
        Locale.setDefault(locale);

        Document doc = Jsoup.parse("<p id=1>Hello <b>there</b> igor</p>");
        Elements ps = doc.select("p:containsOwn(Hello IGOR)");
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Document doc2 = Jsoup.parse("<p>Hello <b>there</b> IGOR</p>");
        assertEquals(1, doc2.select("p:containsOwn(igor)").size());
    }

@MultiLocaleTest
    public void containsData_1_oe(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        assertEquals(2,dataEls1.size());// body and script assertEquals(1,dataEls2.size());
    }

@MultiLocaleTest
    public void containsData_2_oe(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        // removed other assertion
        assertEquals(dataEls1.last(), dataEls2.first());
    }

@MultiLocaleTest
    public void containsData_3_oe(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        // removed other assertion
        // removed other assertion
        assertEquals("<script>FUNCTION</script>", dataEls2.outerHtml());
    }

@MultiLocaleTest
    public void containsData_4_oe(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, dataEls3.size());
    }

@MultiLocaleTest
    public void containsData_5_oe(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("span", dataEls3.first().tagName());
    }

@MultiLocaleTest
    public void containsData_6_oe(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, dataEls4.size());
    }

@MultiLocaleTest
    public void containsData_7_oe(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("body", dataEls4.first().tagName());
    }

@MultiLocaleTest
    public void containsData_8_oe(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("script", dataEls4.get(1).tagName());
    }

@MultiLocaleTest
    public void containsData_9_oe(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("span", dataEls4.get(2).tagName());
    }

@MultiLocaleTest
    public void containsData_10_oe(Locale locale) {
        Locale.setDefault(locale);

        String html = "<p>function</p><script>FUNCTION</script><style>item</style><span><!-- comments --></span>";
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        Elements dataEls1 = body.select(":containsData(function)");
        Elements dataEls2 = body.select("script:containsData(function)");
        Elements dataEls3 = body.select("span:containsData(comments)");
        Elements dataEls4 = body.select(":containsData(o)");
        Elements dataEls5 = body.select("style:containsData(ITEM)");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, dataEls5.size());
    }

}
