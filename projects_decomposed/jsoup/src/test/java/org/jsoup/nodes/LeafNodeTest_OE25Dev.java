package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeafNodeTest_OE25Dev {

    private boolean hasAnyAttributes(Node node) {
        final boolean[] found = new boolean[1];
        node.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                if (node.hasAttributes()) {
                    found[0] = true;
                    return FilterResult.STOP;
                } else {
                    return FilterResult.CONTINUE;
                }
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        });
        return found[0];
    }

@Test
    public void doesNotGetAttributesTooEasily_1_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        assertTrue(hasAnyAttributes(doc));// should have one - the base uri on the doc;
    }

@Test
    public void doesNotGetAttributesTooEasily_2_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        assertFalse(hasAnyAttributes(html));
    }

@Test
    public void doesNotGetAttributesTooEasily_3_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        assertFalse(hasAnyAttributes(html));
    }

@Test
    public void doesNotGetAttributesTooEasily_4_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        assertEquals(1, els.size());
    }

@Test
    public void doesNotGetAttributesTooEasily_5_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        assertFalse(hasAnyAttributes(html));
    }

@Test
    public void doesNotGetAttributesTooEasily_6_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        assertFalse(hasAnyAttributes(html));
    }

@Test
    public void doesNotGetAttributesTooEasily_7_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        // removed other assertion

        String id = p.id();
        assertEquals("", id);
    }

@Test
    public void doesNotGetAttributesTooEasily_8_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        // removed other assertion

        String id = p.id();
        // removed other assertion
        assertFalse(p.hasClass("Foobs"));
    }

@Test
    public void doesNotGetAttributesTooEasily_9_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        // removed other assertion

        String id = p.id();
        // removed other assertion
        // removed other assertion
        assertFalse(hasAnyAttributes(html));
    }

@Test
    public void doesNotGetAttributesTooEasily_10_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        // removed other assertion

        String id = p.id();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.addClass("Foobs");
        assertTrue(p.hasClass("Foobs"));
    }

@Test
    public void doesNotGetAttributesTooEasily_11_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        // removed other assertion

        String id = p.id();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.addClass("Foobs");
        // removed other assertion
        assertTrue(hasAnyAttributes(html));
    }

@Test
    public void doesNotGetAttributesTooEasily_12_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        // removed other assertion

        String id = p.id();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.addClass("Foobs");
        // removed other assertion
        // removed other assertion
        assertTrue(hasAnyAttributes(p));
    }

@Test
    public void doesNotGetAttributesTooEasily_13_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        // removed other assertion

        String id = p.id();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.addClass("Foobs");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Attributes attributes = p.attributes();
        assertTrue(attributes.hasKey("class"));
    }

@Test
    public void doesNotGetAttributesTooEasily_14_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        // removed other assertion

        String id = p.id();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.addClass("Foobs");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Attributes attributes = p.attributes();
        // removed other assertion
        p.clearAttributes();
        assertFalse(hasAnyAttributes(p));
    }

@Test
    public void doesNotGetAttributesTooEasily_15_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        // removed other assertion

        String id = p.id();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.addClass("Foobs");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Attributes attributes = p.attributes();
        // removed other assertion
        p.clearAttributes();
        // removed other assertion
        assertFalse(hasAnyAttributes(html));
    }

@Test
    public void doesNotGetAttributesTooEasily_16_oe() {
        // test to make sure we're not setting attributes on all nodes right away
        String body = "<p>One <!-- Two --> Three<![CDATA[Four]]></p>";
        Document doc = Jsoup.parse(body);
        // removed other assertion
        Element html = doc.child(0);
        // removed other assertion

        String s = doc.outerHtml();
        // removed other assertion

        Elements els = doc.select("p");
        Element p = els.first();
        // removed other assertion
        // removed other assertion

        els = doc.select("p.none");
        // removed other assertion

        String id = p.id();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        p.addClass("Foobs");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Attributes attributes = p.attributes();
        // removed other assertion
        p.clearAttributes();
        // removed other assertion
        // removed other assertion
        assertFalse(attributes.hasKey("class"));
    }

}
