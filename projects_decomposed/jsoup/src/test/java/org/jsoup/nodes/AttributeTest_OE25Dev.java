package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttributeTest_OE25Dev {

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

@Test public void testWithSupplementaryCharacterInAttributeKeyAndValue_1_oe() {
        String s = new String(Character.toChars(135361));
        Attribute attr = new Attribute(s, "A" + s + "B");
        assertEquals(s + "=\"A" + s + "B\"", attr.html());
        }

@Test public void testWithSupplementaryCharacterInAttributeKeyAndValue_2_oe() {
        String s = new String(Character.toChars(135361));
        Attribute attr = new Attribute(s, "A" + s + "B");
        // removed other assertion
        assertEquals(attr.html(), attr.toString());
        }

@Test public void validatesKeysNotEmpty_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> new Attribute(" ", "Check"));
        }

@Test public void validatesKeysNotEmptyViaSet_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> { Attribute attr = new Attribute("One", "Check"); attr.setKey(" "); });
        }

@Test public void booleanAttributesAreEmptyStringValues_1_oe() {
        Document doc = Jsoup.parse("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        assertEquals("", attributes.get("hidden"));
        }

@Test public void booleanAttributesAreEmptyStringValues_2_oe() {
        Document doc = Jsoup.parse("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        // removed other assertion

        Attribute first = attributes.iterator().next();
        assertEquals("hidden", first.getKey());
        }

@Test public void booleanAttributesAreEmptyStringValues_3_oe() {
        Document doc = Jsoup.parse("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        // removed other assertion

        Attribute first = attributes.iterator().next();
        // removed other assertion
        assertEquals("", first.getValue());
        }

@Test public void booleanAttributesAreEmptyStringValues_4_oe() {
        Document doc = Jsoup.parse("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        // removed other assertion

        Attribute first = attributes.iterator().next();
        // removed other assertion
        // removed other assertion
        assertFalse(first.hasDeclaredValue());
        }

@Test public void booleanAttributesAreEmptyStringValues_5_oe() {
        Document doc = Jsoup.parse("<div hidden>");
        Attributes attributes = doc.body().child(0).attributes();
        // removed other assertion

        Attribute first = attributes.iterator().next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Attribute.isBooleanAttribute(first.getKey()));
        }

@Test public void settersOnOrphanAttribute_1_oe() {
        Attribute attr = new Attribute("one", "two");
        attr.setKey("three");
        String oldVal = attr.setValue("four");
        assertEquals("two", oldVal);
        }

@Test public void settersOnOrphanAttribute_2_oe() {
        Attribute attr = new Attribute("one", "two");
        attr.setKey("three");
        String oldVal = attr.setValue("four");
        // removed other assertion
        assertEquals("three", attr.getKey());
        }

@Test public void settersOnOrphanAttribute_3_oe() {
        Attribute attr = new Attribute("one", "two");
        attr.setKey("three");
        String oldVal = attr.setValue("four");
        // removed other assertion
        // removed other assertion
        assertEquals("four", attr.getValue());
        }

@Test public void settersOnOrphanAttribute_4_oe() {
        Attribute attr = new Attribute("one", "two");
        attr.setKey("three");
        String oldVal = attr.setValue("four");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(attr.parent);
        }

@Test public void hasValue_1_oe() {
        Attribute a1 = new Attribute("one", "");
        Attribute a2 = new Attribute("two", null);
        Attribute a3 = new Attribute("thr", "thr");

        assertTrue(a1.hasDeclaredValue());
        }

@Test public void hasValue_2_oe() {
        Attribute a1 = new Attribute("one", "");
        Attribute a2 = new Attribute("two", null);
        Attribute a3 = new Attribute("thr", "thr");

        // removed other assertion
        assertFalse(a2.hasDeclaredValue());
        }

@Test public void hasValue_3_oe() {
        Attribute a1 = new Attribute("one", "");
        Attribute a2 = new Attribute("two", null);
        Attribute a3 = new Attribute("thr", "thr");

        // removed other assertion
        // removed other assertion
        assertTrue(a3.hasDeclaredValue());
        }

@Test public void canSetValueToNull_1_oe() {
        Attribute attr = new Attribute("one", "val");
        String oldVal = attr.setValue(null);
        assertEquals("one", attr.html());
        }

@Test public void canSetValueToNull_2_oe() {
        Attribute attr = new Attribute("one", "val");
        String oldVal = attr.setValue(null);
        // removed other assertion
        assertEquals("val", oldVal);
        }

@Test public void canSetValueToNull_3_oe() {
        Attribute attr = new Attribute("one", "val");
        String oldVal = attr.setValue(null);
        // removed other assertion
        // removed other assertion

        oldVal = attr.setValue("foo");
        assertEquals("", oldVal); // string, not null;
        }

@Test void booleanAttributesAreNotCaseSensitive_1_oe() {
        // https://github.com/jhy/jsoup/issues/1656
        assertTrue(Attribute.isBooleanAttribute("required"));
        }

@Test void booleanAttributesAreNotCaseSensitive_2_oe() {
        // https://github.com/jhy/jsoup/issues/1656
        // removed other assertion
        assertTrue(Attribute.isBooleanAttribute("REQUIRED"));
        }

@Test void booleanAttributesAreNotCaseSensitive_3_oe() {
        // https://github.com/jhy/jsoup/issues/1656
        // removed other assertion
        // removed other assertion
        assertTrue(Attribute.isBooleanAttribute("rEQUIREd"));
        }

@Test void booleanAttributesAreNotCaseSensitive_4_oe() {
        // https://github.com/jhy/jsoup/issues/1656
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Attribute.isBooleanAttribute("random string"));
        }

@Test void booleanAttributesAreNotCaseSensitive_5_oe() {
        // https://github.com/jhy/jsoup/issues/1656
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String html = "<a href=autofocus REQUIRED>One</a>";
        Document doc = Jsoup.parse(html);
        assertEquals("<a href=\"autofocus\" required>One</a>", doc.selectFirst("a").outerHtml());
        }

@Test void booleanAttributesAreNotCaseSensitive_6_oe() {
        // https://github.com/jhy/jsoup/issues/1656
        // removed other assertion
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
