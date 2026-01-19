package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttributeTest_OE25Dev {

    @Test public void testWithSupplementaryCharacterInAttributeKeyAndValue() {
        String s = new String(Character.toChars(135361));
        Attribute attr = new Attribute(s, "A" + s + "B");
        assertEquals(s + "=\"A" + s + "B\"", attr.html());
        assertEquals(attr.html(), attr.toString());
    }

    @Test public void validatesKeysNotEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Attribute(" ", "Check"));
    }

    @Test public void validatesKeysNotEmptyViaSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            Attribute attr = new Attribute("One", "Check");
            attr.setKey(" ");
        });
    }

    @Test public void booleanAttributesAreEmptyStringValues() {
        Document doc = Jsoup.parse("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        assertEquals("", attributes.get("hidden"));

        Attribute first = attributes.iterator().next();
        assertEquals("hidden", first.getKey());
        assertEquals("", first.getValue());
        assertFalse(first.hasDeclaredValue());
        assertTrue(Attribute.isBooleanAttribute(first.getKey()));
    }

    @Test public void settersOnOrphanAttribute() {
        Attribute attr = new Attribute("one", "two");
        attr.setKey("three");
        String oldVal = attr.setValue("four");
        assertEquals("two", oldVal);
        assertEquals("three", attr.getKey());
        assertEquals("four", attr.getValue());
        assertNull(attr.parent);
    }

    @Test public void hasValue() {
        Attribute a1 = new Attribute("one", "");
        Attribute a2 = new Attribute("two", null);
        Attribute a3 = new Attribute("thr", "thr");

        assertTrue(a1.hasDeclaredValue());
        assertFalse(a2.hasDeclaredValue());
        assertTrue(a3.hasDeclaredValue());
    }

    @Test public void canSetValueToNull() {
        Attribute attr = new Attribute("one", "val");
        String oldVal = attr.setValue(null);
        assertEquals("one", attr.html());
        assertEquals("val", oldVal);

        oldVal = attr.setValue("foo");
        assertEquals("", oldVal); // string, not null
    }

    @Test void booleanAttributesAreNotCaseSensitive() {
        // https://github.com/jhy/jsoup/issues/1656
        assertTrue(Attribute.isBooleanAttribute("required"));
        assertTrue(Attribute.isBooleanAttribute("REQUIRED"));
        assertTrue(Attribute.isBooleanAttribute("rEQUIREd"));
        assertFalse(Attribute.isBooleanAttribute("random string"));

        String html = "<a href=autofocus REQUIRED>One</a>";
        Document doc = Jsoup.parse(html);
        assertEquals("<a href=\"autofocus\" required>One</a>", doc.selectFirst("a").outerHtml());

        Document doc2 = Jsoup.parse(html, Parser.htmlParser().settings(ParseSettings.preserveCase));
        assertEquals("<a href=\"autofocus\" REQUIRED>One</a>", doc2.selectFirst("a").outerHtml());
    }

    @Test
    public void html_1_oe() {
        Attribute attr = new Attribute("key", "value &");
        assertEquals("key=\"value &amp;\"", attr.html());
    }

    @Test
    public void html_2_oe() {
        Attribute attr = new Attribute("key", "value &");
        // removed other assertion
        assertEquals(attr.html(), attr.toString());
    }

    @Test public void testWithSupplementaryCharacterInAttributeKeyAndValue() {
        String_1_oe s = new String(Character.toChars(135361));
        Attribute attr = new Attribute(s, "A" + s + "B");
        assertEquals(s + "=\"A" + s + "B\"", attr.html());
        }

    @Test public void testWithSupplementaryCharacterInAttributeKeyAndValue() {
        String_2_oe s = new String(Character.toChars(135361));
        Attribute attr = new Attribute(s, "A" + s + "B");
        // removed other assertion
        assertEquals(attr.html(), attr.toString());
        }

    @Test public void validatesKeysNotEmptyViaSet() {
        assertThrows_1_oe(IllegalArgumentException.class, () -> { Attribute attr = new Attribute("One", "Check"); attr.setKey(" "); });
        }

    @Test public void booleanAttributesAreEmptyStringValues() {
        Document doc = Jsoup.parse_1_oe("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        assertEquals("", attributes.get("hidden"));
        }

    @Test public void booleanAttributesAreEmptyStringValues() {
        Document doc = Jsoup.parse_2_oe("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        // removed other assertion

        Attribute first = attributes.iterator().next();
        assertEquals("hidden", first.getKey());
        }

    @Test public void booleanAttributesAreEmptyStringValues() {
        Document doc = Jsoup.parse_3_oe("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        // removed other assertion

        Attribute first = attributes.iterator().next();
        // removed other assertion
        assertEquals("", first.getValue());
        }

    @Test public void booleanAttributesAreEmptyStringValues() {
        Document doc = Jsoup.parse_4_oe("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        // removed other assertion

        Attribute first = attributes.iterator().next();
        // removed other assertion
        // removed other assertion
        assertFalse(first.hasDeclaredValue());
        }

    @Test public void booleanAttributesAreEmptyStringValues() {
        Document doc = Jsoup.parse_5_oe("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        // removed other assertion

        Attribute first = attributes.iterator().next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Attribute.isBooleanAttribute(first.getKey()));
        }

    @Test public void hasValue() {
        Attribute_1_oe a1 = new Attribute("one", "");
        Attribute a2 = new Attribute("two", null);
        Attribute a3 = new Attribute("thr", "thr");

        assertTrue(a1.hasDeclaredValue());
        }

    @Test public void hasValue() {
        Attribute_2_oe a1 = new Attribute("one", "");
        Attribute a2 = new Attribute("two", null);
        Attribute a3 = new Attribute("thr", "thr");

        // removed other assertion
        assertFalse(a2.hasDeclaredValue());
        }

    @Test public void hasValue() {
        Attribute_3_oe a1 = new Attribute("one", "");
        Attribute a2 = new Attribute("two", null);
        Attribute a3 = new Attribute("thr", "thr");

        // removed other assertion
        // removed other assertion
        assertTrue(a3.hasDeclaredValue());
        }

    @Test void booleanAttributesAreNotCaseSensitive() {
        // https://github.com/jhy/jsoup/issues/1656
        assertTrue_1_oe(Attribute.isBooleanAttribute("required"));
        }

    @Test void booleanAttributesAreNotCaseSensitive() {
        // https://github.com/jhy/jsoup/issues/1656
        assertTrue_2_oe(Attribute.isBooleanAttribute("required"));
        assertTrue(Attribute.isBooleanAttribute("REQUIRED"));
        }

    @Test void booleanAttributesAreNotCaseSensitive() {
        // https://github.com/jhy/jsoup/issues/1656
        assertTrue_3_oe(Attribute.isBooleanAttribute("required"));
        // removed other assertion
        assertTrue(Attribute.isBooleanAttribute("rEQUIREd"));
        }

    @Test void booleanAttributesAreNotCaseSensitive() {
        // https://github.com/jhy/jsoup/issues/1656
        assertTrue_4_oe(Attribute.isBooleanAttribute("required"));
        // removed other assertion
        // removed other assertion
        assertFalse(Attribute.isBooleanAttribute("random string"));
        }

    @Test void booleanAttributesAreNotCaseSensitive() {
        // https://github.com/jhy/jsoup/issues/1656
        assertTrue_5_oe(Attribute.isBooleanAttribute("required"));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String html = "<a href=autofocus REQUIRED>One</a>";
        Document doc = Jsoup.parse(html);
        assertEquals("<a href=\"autofocus\" required>One</a>", doc.selectFirst("a").outerHtml());
        }

    @Test void booleanAttributesAreNotCaseSensitive() {
        // https://github.com/jhy/jsoup/issues/1656
        assertTrue_6_oe(Attribute.isBooleanAttribute("required"));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String html = "<a href=autofocus REQUIRED>One</a>";
        Document doc = Jsoup.parse(html);
        // removed other assertion

        Document doc2 = Jsoup.parse(html, Parser.htmlParser().settings(ParseSettings.preserveCase));
        assertEquals("<a href=\"autofocus\" REQUIRED>One</a>", doc2.selectFirst("a").outerHtml());
        }

}
