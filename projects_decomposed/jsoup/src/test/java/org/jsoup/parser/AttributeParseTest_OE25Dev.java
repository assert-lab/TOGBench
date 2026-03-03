package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 Test suite for attribute parser.

 @author Jonathan Hedley, jonathan@hedley.net */
public class AttributeParseTest_OE25Dev {

    @Test public void parsesRoughAttributeString_1_oe() {
        String html = "<a id=\"123\" class=\"baz = 'bar'\" style = 'border: 2px'qux zim foo = 12 mux=18 />";

        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals(7, attr.size());
        }

    @Test public void parsesRoughAttributeString_2_oe() {
        String html = "<a id=\"123\" class=\"baz = 'bar'\" style = 'border: 2px'qux zim foo = 12 mux=18 />";

        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals("123", attr.get("id"));
        }

    @Test public void parsesRoughAttributeString_3_oe() {
        String html = "<a id=\"123\" class=\"baz = 'bar'\" style = 'border: 2px'qux zim foo = 12 mux=18 />";

        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals("baz = 'bar'", attr.get("class"));
        }

    @Test public void parsesRoughAttributeString_4_oe() {
        String html = "<a id=\"123\" class=\"baz = 'bar'\" style = 'border: 2px'qux zim foo = 12 mux=18 />";

        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals("border: 2px", attr.get("style"));
        }

    @Test public void parsesRoughAttributeString_5_oe() {
        String html = "<a id=\"123\" class=\"baz = 'bar'\" style = 'border: 2px'qux zim foo = 12 mux=18 />";

        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals("", attr.get("qux"));
        }

    @Test public void parsesRoughAttributeString_6_oe() {
        String html = "<a id=\"123\" class=\"baz = 'bar'\" style = 'border: 2px'qux zim foo = 12 mux=18 />";

        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals("", attr.get("zim"));
        }

    @Test public void parsesRoughAttributeString_7_oe() {
        String html = "<a id=\"123\" class=\"baz = 'bar'\" style = 'border: 2px'qux zim foo = 12 mux=18 />";

        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals("12", attr.get("foo"));
        }

    @Test public void parsesRoughAttributeString_8_oe() {
        String html = "<a id=\"123\" class=\"baz = 'bar'\" style = 'border: 2px'qux zim foo = 12 mux=18 />";

        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals("18", attr.get("mux"));
        }

    @Test public void handlesNewLinesAndReturns_1_oe() {
        String html = "<a\r\nfoo='bar\r\nqux'\r\nbar\r\n=\r\ntwo>One</a>";
        Element el = Jsoup.parse(html).select("a").first();
        assertEquals(2, el.attributes().size());
        }

    @Test public void handlesNewLinesAndReturns_2_oe() {
        String html = "<a\r\nfoo='bar\r\nqux'\r\nbar\r\n=\r\ntwo>One</a>";
        Element el = Jsoup.parse(html).select("a").first();
        assertEquals("bar\r\nqux",el.attr("foo"));// currently preserves newlines in quoted attributes. todo confirm if should. assertEquals("two",el.attr("bar"));
        }

    @Test public void parsesEmptyString_1_oe() {
        String html = "<a />";
        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals(0, attr.size());
        }

    @Test public void canStartWithEq_1_oe() {
        String html = "<a =empty />";
        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals(1, attr.size());
        }

    @Test public void canStartWithEq_2_oe() {
        String html = "<a =empty />";
        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertTrue(attr.hasKey("=empty"));
        }

    @Test public void canStartWithEq_3_oe() {
        String html = "<a =empty />";
        Element el = Jsoup.parse(html).getElementsByTag("a").get(0);
        Attributes attr = el.attributes();
        assertEquals("", attr.get("=empty"));
        }

    @Test public void strictAttributeUnescapes_1_oe() {
        String html = "<a id=1 href='?foo=bar&mid&lt=true'>One</a> <a id=2 href='?foo=bar&lt;qux&lg=1'>Two</a>";
        Elements els = Jsoup.parse(html).select("a");
        assertEquals("?foo=bar&mid&lt=true", els.first().attr("href"));
        }

    @Test public void strictAttributeUnescapes_2_oe() {
        String html = "<a id=1 href='?foo=bar&mid&lt=true'>One</a> <a id=2 href='?foo=bar&lt;qux&lg=1'>Two</a>";
        Elements els = Jsoup.parse(html).select("a");
        assertEquals("?foo=bar<qux&lg=1", els.last().attr("href"));
        }

    @Test public void moreAttributeUnescapes_1_oe() {
        String html = "<a href='&wr_id=123&mid-size=true&ok=&wr'>Check</a>";
        Elements els = Jsoup.parse(html).select("a");
        assertEquals("&wr_id=123&mid-size=true&ok=&wr", els.first().attr("href"));
        }

    @Test public void parsesBooleanAttributes_1_oe() {
        String html = "<a normal=\"123\" boolean empty=\"\"></a>";
        Element el = Jsoup.parse(html).select("a").first();

        assertEquals("123", el.attr("normal"));
        }

    @Test public void parsesBooleanAttributes_2_oe() {
        String html = "<a normal=\"123\" boolean empty=\"\"></a>";
        Element el = Jsoup.parse(html).select("a").first();

        assertEquals("", el.attr("boolean"));
        }

    @Test public void parsesBooleanAttributes_3_oe() {
        String html = "<a normal=\"123\" boolean empty=\"\"></a>";
        Element el = Jsoup.parse(html).select("a").first();

        assertEquals("", el.attr("empty"));
        }

    @Test public void parsesBooleanAttributes_4_oe() {
        String html = "<a normal=\"123\" boolean empty=\"\"></a>";
        Element el = Jsoup.parse(html).select("a").first();


        List<Attribute> attributes = el.attributes().asList();
        assertEquals(3, attributes.size(), "There should be 3 attribute present");
        }

    @Test public void parsesBooleanAttributes_5_oe() {
        String html = "<a normal=\"123\" boolean empty=\"\"></a>";
        Element el = Jsoup.parse(html).select("a").first();


        List<Attribute> attributes = el.attributes().asList();

        assertEquals(html, el.outerHtml()); // vets boolean syntax;
        }

    @Test public void dropsSlashFromAttributeName_1_oe() {
        String html = "<img /onerror='doMyJob'/>";
        Document doc = Jsoup.parse(html);
        assertFalse(doc.select("img[onerror]").isEmpty(), "SelfClosingStartTag ignores last character");
        }

    @Test public void dropsSlashFromAttributeName_2_oe() {
        String html = "<img /onerror='doMyJob'/>";
        Document doc = Jsoup.parse(html);
        assertEquals("<img onerror=\"doMyJob\">", doc.body().html());
        }

    @Test public void dropsSlashFromAttributeName_3_oe() {
        String html = "<img /onerror='doMyJob'/>";
        Document doc = Jsoup.parse(html);

        doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img onerror=\"doMyJob\" />", doc.html());
        }

}
