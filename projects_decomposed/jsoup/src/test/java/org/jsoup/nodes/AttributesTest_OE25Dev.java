package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Attributes.
 *
 * @author Jonathan Hedley
 */
public class AttributesTest_OE25Dev {

    @Test
    public void html_1_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        assertEquals(3, a.size());
    }

    @Test
    public void html_2_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        assertTrue(a.hasKey("Tot"));
    }

    @Test
    public void html_3_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        assertTrue(a.hasKey("Hello"));
    }

    @Test
    public void html_4_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(a.hasKey("data-name"));
    }

    @Test
    public void html_5_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(a.hasKey("tot"));
    }

    @Test
    public void html_6_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(a.hasKeyIgnoreCase("tot"));
    }

    @Test
    public void html_7_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("There", a.getIgnoreCase("hEllo"));
    }

    @Test
    public void html_8_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, String> dataset = a.dataset();
        assertEquals(1, dataset.size());
    }

    @Test
    public void html_9_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, String> dataset = a.dataset();
        // removed other assertion
        assertEquals("Jsoup", dataset.get("name"));
    }

    @Test
    public void html_10_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, String> dataset = a.dataset();
        // removed other assertion
        // removed other assertion
        assertEquals("", a.get("tot"));
    }

    @Test
    public void html_11_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, String> dataset = a.dataset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a&p", a.get("Tot"));
    }

    @Test
    public void html_12_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, String> dataset = a.dataset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a&p", a.getIgnoreCase("tot"));
    }

    @Test
    public void html_13_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, String> dataset = a.dataset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(" Tot=\"a&amp;p\" Hello=\"There\" data-name=\"Jsoup\"", a.html());
    }

    @Test
    public void html_14_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, String> dataset = a.dataset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(a.html(), a.toString());
    }

    @Test
    public void testIteratorRemovable_1_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");
        assertTrue(a.hasKey("Tot"));
    }

    @Test
    public void testIteratorRemovable_2_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");
        // removed other assertion

        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        assertEquals("Tot", attr.getKey());
    }

    @Test
    public void testIteratorRemovable_3_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");
        // removed other assertion

        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        // removed other assertion
        iterator.remove();
        assertEquals(2, a.size());
    }

    @Test
    public void testIteratorRemovable_4_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");
        // removed other assertion

        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        // removed other assertion
        iterator.remove();
        // removed other assertion
        attr = iterator.next();
        assertEquals("Hello", attr.getKey());
    }

    @Test
    public void testIteratorRemovable_5_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");
        // removed other assertion

        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        // removed other assertion
        iterator.remove();
        // removed other assertion
        attr = iterator.next();
        // removed other assertion
        assertEquals("There", attr.getValue());
    }

    @Test
    public void testIteratorRemovable_6_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");
        // removed other assertion

        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        // removed other assertion
        iterator.remove();
        // removed other assertion
        attr = iterator.next();
        // removed other assertion
        // removed other assertion

        // make sure that's flowing to the underlying attributes object
        assertEquals(2, a.size());
    }

    @Test
    public void testIteratorRemovable_7_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");
        // removed other assertion

        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        // removed other assertion
        iterator.remove();
        // removed other assertion
        attr = iterator.next();
        // removed other assertion
        // removed other assertion

        // make sure that's flowing to the underlying attributes object
        // removed other assertion
        assertEquals("There", a.get("Hello"));
    }

    @Test
    public void testIteratorRemovable_8_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");
        a.put("data-name", "Jsoup");
        // removed other assertion

        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        // removed other assertion
        iterator.remove();
        // removed other assertion
        attr = iterator.next();
        // removed other assertion
        // removed other assertion

        // make sure that's flowing to the underlying attributes object
        // removed other assertion
        // removed other assertion
        assertFalse(a.hasKey("Tot"));
    }

    @Test
    public void testIteratorUpdateable_1_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");

        assertFalse(a.hasKey("Foo"));
    }

    @Test
    public void testIteratorUpdateable_2_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");

        // removed other assertion
        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        attr.setKey("Foo");
        attr = iterator.next();
        attr.setKey("Bar");
        attr.setValue("Qux");

        assertEquals("a&p", a.get("Foo"));
    }

    @Test
    public void testIteratorUpdateable_3_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");

        // removed other assertion
        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        attr.setKey("Foo");
        attr = iterator.next();
        attr.setKey("Bar");
        attr.setValue("Qux");

        // removed other assertion
        assertEquals("Qux", a.get("Bar"));
    }

    @Test
    public void testIteratorUpdateable_4_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");

        // removed other assertion
        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        attr.setKey("Foo");
        attr = iterator.next();
        attr.setKey("Bar");
        attr.setValue("Qux");

        // removed other assertion
        // removed other assertion
        assertFalse(a.hasKey("Tot"));
    }

    @Test
    public void testIteratorUpdateable_5_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("Hello", "There");

        // removed other assertion
        Iterator<Attribute> iterator = a.iterator();
        Attribute attr = iterator.next();
        attr.setKey("Foo");
        attr = iterator.next();
        attr.setKey("Bar");
        attr.setValue("Qux");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(a.hasKey("Hello"));
    }

    @Test public void testIteratorHasNext_1_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "1");
        a.put("Hello", "2");
        a.put("data-name", "3");

        int seen = 0;
        for (Attribute attribute : a) {
            seen++;
            assertEquals(String.valueOf(seen), attribute.getValue());
        }
        }

    @Test public void testIteratorHasNext_2_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "1");
        a.put("Hello", "2");
        a.put("data-name", "3");

        int seen = 0;
        for (Attribute attribute : a) {
            seen++;
            // removed other assertion
        }
        assertEquals(3, seen);
        }

    @Test
    public void testIterator_1_oe() {
        Attributes a = new Attributes();
        String[][] datas = {{"Tot", "raul"},
            {"Hello", "pismuth"},
            {"data-name", "Jsoup"}};
        for (String[] atts : datas) {
            a.put(atts[0], atts[1]);
        }

        Iterator<Attribute> iterator = a.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testIterator_2_oe() {
        Attributes a = new Attributes();
        String[][] datas = {{"Tot", "raul"},
            {"Hello", "pismuth"},
            {"data-name", "Jsoup"}};
        for (String[] atts : datas) {
            a.put(atts[0], atts[1]);
        }

        Iterator<Attribute> iterator = a.iterator();
        // removed other assertion
        int i = 0;
        for (Attribute attribute : a) {
            assertEquals(datas[i][0], attribute.getKey());
    }
    }

    @Test
    public void testIterator_3_oe() {
        Attributes a = new Attributes();
        String[][] datas = {{"Tot", "raul"},
            {"Hello", "pismuth"},
            {"data-name", "Jsoup"}};
        for (String[] atts : datas) {
            a.put(atts[0], atts[1]);
        }

        Iterator<Attribute> iterator = a.iterator();
        // removed other assertion
        int i = 0;
        for (Attribute attribute : a) {
            // removed other assertion
            assertEquals(datas[i][1], attribute.getValue());
    }
    }

    @Test
    public void testIterator_4_oe() {
        Attributes a = new Attributes();
        String[][] datas = {{"Tot", "raul"},
            {"Hello", "pismuth"},
            {"data-name", "Jsoup"}};
        for (String[] atts : datas) {
            a.put(atts[0], atts[1]);
        }

        Iterator<Attribute> iterator = a.iterator();
        // removed other assertion
        int i = 0;
        for (Attribute attribute : a) {
            // removed other assertion
            // removed other assertion
            i++;
        }
        assertEquals(datas.length, i);
    }

    @Test
    public void testIteratorSkipsInternal_1_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put("Two", "Two");
        a.put(Attributes.internalKey("another"), "example.com");

        Iterator<Attribute> it = a.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void testIteratorSkipsInternal_2_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put("Two", "Two");
        a.put(Attributes.internalKey("another"), "example.com");

        Iterator<Attribute> it = a.iterator();
        // removed other assertion
        assertEquals("One", it.next().getKey());
    }

    @Test
    public void testIteratorSkipsInternal_3_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put("Two", "Two");
        a.put(Attributes.internalKey("another"), "example.com");

        Iterator<Attribute> it = a.iterator();
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

    @Test
    public void testIteratorSkipsInternal_4_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put("Two", "Two");
        a.put(Attributes.internalKey("another"), "example.com");

        Iterator<Attribute> it = a.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Two", it.next().getKey());
    }

    @Test
    public void testIteratorSkipsInternal_5_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put("Two", "Two");
        a.put(Attributes.internalKey("another"), "example.com");

        Iterator<Attribute> it = a.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorSkipsInternal_6_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put("Two", "Two");
        a.put(Attributes.internalKey("another"), "example.com");

        Iterator<Attribute> it = a.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        int seen = 0;
        for (Attribute attribute : a) {
            seen++;
        }
        assertEquals(2, seen);
    }

    @Test
    public void testListSkipsInternal_1_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put("Two", "Two");
        a.put(Attributes.internalKey("another"), "example.com");

        List<Attribute> attributes = a.asList();
        assertEquals(2, attributes.size());
    }

    @Test
    public void testListSkipsInternal_2_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put("Two", "Two");
        a.put(Attributes.internalKey("another"), "example.com");

        List<Attribute> attributes = a.asList();
        // removed other assertion
        assertEquals("One", attributes.get(0).getKey());
    }

    @Test
    public void testListSkipsInternal_3_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put("Two", "Two");
        a.put(Attributes.internalKey("another"), "example.com");

        List<Attribute> attributes = a.asList();
        // removed other assertion
        // removed other assertion
        assertEquals("Two", attributes.get(1). getKey());
    }

    @Test public void htmlSkipsInternals_1_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put("Two", "Two");
        a.put(Attributes.internalKey("another"), "example.com");

        assertEquals(" One=\"One\" Two=\"Two\"", a.html());
        }

    @Test
    public void testIteratorEmpty_1_oe() {
        Attributes a = new Attributes();

        Iterator<Attribute> iterator = a.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void removeCaseSensitive_1_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("tot", "one");
        a.put("Hello", "There");
        a.put("hello", "There");
        a.put("data-name", "Jsoup");

        assertEquals(5, a.size());
    }

    @Test
    public void removeCaseSensitive_2_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("tot", "one");
        a.put("Hello", "There");
        a.put("hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        a.remove("Tot");
        a.remove("Hello");
        assertEquals(3, a.size());
    }

    @Test
    public void removeCaseSensitive_3_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("tot", "one");
        a.put("Hello", "There");
        a.put("hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        a.remove("Tot");
        a.remove("Hello");
        // removed other assertion
        assertTrue(a.hasKey("tot"));
    }

    @Test
    public void removeCaseSensitive_4_oe() {
        Attributes a = new Attributes();
        a.put("Tot", "a&p");
        a.put("tot", "one");
        a.put("Hello", "There");
        a.put("hello", "There");
        a.put("data-name", "Jsoup");

        // removed other assertion
        a.remove("Tot");
        a.remove("Hello");
        // removed other assertion
        // removed other assertion
        assertFalse(a.hasKey("Tot"));
    }

    @Test
    public void testSetKeyConsistency_1_oe() {
        Attributes a = new Attributes();
        a.put("a", "a");
        for(Attribute at : a) {
            at.setKey("b");
        }
        assertFalse(a.hasKey("a"), "Attribute 'a' not correctly removed");
    }

    @Test
    public void testSetKeyConsistency_2_oe() {
        Attributes a = new Attributes();
        a.put("a", "a");
        for(Attribute at : a) {
            at.setKey("b");
        }
        // removed other assertion
        assertTrue(a.hasKey("b"), "Attribute 'b' not present after renaming");
    }

    @Test
    public void testBoolean_1_oe() {
        Attributes ats = new Attributes();
        ats.put("a", "a");
        ats.put("B", "b");
        ats.put("c", null);

        assertTrue(ats.hasDeclaredValueForKey("a"));
    }

    @Test
    public void testBoolean_2_oe() {
        Attributes ats = new Attributes();
        ats.put("a", "a");
        ats.put("B", "b");
        ats.put("c", null);

        // removed other assertion
        assertFalse(ats.hasDeclaredValueForKey("A"));
    }

    @Test
    public void testBoolean_3_oe() {
        Attributes ats = new Attributes();
        ats.put("a", "a");
        ats.put("B", "b");
        ats.put("c", null);

        // removed other assertion
        // removed other assertion
        assertTrue(ats.hasDeclaredValueForKeyIgnoreCase("A"));
    }

    @Test
    public void testBoolean_4_oe() {
        Attributes ats = new Attributes();
        ats.put("a", "a");
        ats.put("B", "b");
        ats.put("c", null);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(ats.hasDeclaredValueForKey("c"));
    }

    @Test
    public void testBoolean_5_oe() {
        Attributes ats = new Attributes();
        ats.put("a", "a");
        ats.put("B", "b");
        ats.put("c", null);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(ats.hasDeclaredValueForKey("C"));
    }

    @Test
    public void testBoolean_6_oe() {
        Attributes ats = new Attributes();
        ats.put("a", "a");
        ats.put("B", "b");
        ats.put("c", null);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(ats.hasDeclaredValueForKeyIgnoreCase("C"));
    }

    @Test public void testSizeWhenHasInternal_1_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put("Two", "Two");
        assertEquals(2, a.size());
        }

    @Test public void testSizeWhenHasInternal_2_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put("Two", "Two");
        // removed other assertion

        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put(Attributes.internalKey("another"), "example.com");
        a.put(Attributes.internalKey("last"), "example.com");
        a.remove(Attributes.internalKey("last"));

        assertEquals(4, a.size());
        }

    @Test public void testSizeWhenHasInternal_3_oe() {
        Attributes a = new Attributes();
        a.put("One", "One");
        a.put("Two", "Two");
        // removed other assertion

        a.put(Attributes.internalKey("baseUri"), "example.com");
        a.put(Attributes.internalKey("another"), "example.com");
        a.put(Attributes.internalKey("last"), "example.com");
        a.remove(Attributes.internalKey("last"));

        // removed other assertion
        assertEquals(2, a.asList().size()); // excluded from lists;
        }

    @Test public void testBooleans_1_oe() {
        // want unknown=null, and known like async=null, async="", and async=async to collapse
        String html = "<a foo bar=\"\" async=async qux=qux defer=deferring ismap inert=\"\">";
        Element el = Jsoup.parse(html).selectFirst("a");
        assertEquals(" foo bar=\"\" async qux=\"qux\" defer=\"deferring\" ismap inert", el.attributes().html());
        }

    @Test public void booleanNullAttributesConsistent_1_oe() {
        Attributes attributes = new Attributes();
        attributes.put("key", null);
        Attribute attribute = attributes.iterator().next();

        assertEquals("key", attribute.html());
        }

    @Test public void booleanNullAttributesConsistent_2_oe() {
        Attributes attributes = new Attributes();
        attributes.put("key", null);
        Attribute attribute = attributes.iterator().next();

        // removed other assertion
        assertEquals(" key", attributes.html());
        }

    @Test public void booleanEmptyString_1_oe() {
        Attributes attributes = new Attributes();
        attributes.put("checked", "");
        Attribute attribute = attributes.iterator().next();

        assertEquals("checked", attribute.html());
        }

    @Test public void booleanEmptyString_2_oe() {
        Attributes attributes = new Attributes();
        attributes.put("checked", "");
        Attribute attribute = attributes.iterator().next();

        // removed other assertion
        assertEquals(" checked", attributes.html());
        }

    @Test public void booleanCaseInsensitive_1_oe() {
        Attributes attributes = new Attributes();
        attributes.put("checked", "CHECKED");
        Attribute attribute = attributes.iterator().next();

        assertEquals("checked", attribute.html());
        }

    @Test public void booleanCaseInsensitive_2_oe() {
        Attributes attributes = new Attributes();
        attributes.put("checked", "CHECKED");
        Attribute attribute = attributes.iterator().next();

        // removed other assertion
        assertEquals(" checked", attributes.html());
        }

    @Test public void equalsIsOrderInsensitive_1_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes two = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes three = new Attributes()
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key1", "Val1");

        Attributes four = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key4", "Val4");

        assertEquals(one, one.clone());
        }

    @Test public void equalsIsOrderInsensitive_2_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes two = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes three = new Attributes()
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key1", "Val1");

        Attributes four = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key4", "Val4");

        // removed other assertion
        assertEquals(one, two);
        }

    @Test public void equalsIsOrderInsensitive_3_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes two = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes three = new Attributes()
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key1", "Val1");

        Attributes four = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key4", "Val4");

        // removed other assertion
        // removed other assertion
        assertEquals(two, two);
        }

    @Test public void equalsIsOrderInsensitive_4_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes two = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes three = new Attributes()
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key1", "Val1");

        Attributes four = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key4", "Val4");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(one, three);
        }

    @Test public void equalsIsOrderInsensitive_5_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes two = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes three = new Attributes()
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key1", "Val1");

        Attributes four = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key4", "Val4");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(two, three);
        }

    @Test public void equalsIsOrderInsensitive_6_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes two = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes three = new Attributes()
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key1", "Val1");

        Attributes four = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key4", "Val4");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(three, three);
        }

    @Test public void equalsIsOrderInsensitive_7_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes two = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes three = new Attributes()
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key1", "Val1");

        Attributes four = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key4", "Val4");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(three, three.clone());
        }

    @Test public void equalsIsOrderInsensitive_8_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes two = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes three = new Attributes()
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key1", "Val1");

        Attributes four = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key4", "Val4");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(four, four);
        }

    @Test public void equalsIsOrderInsensitive_9_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes two = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes three = new Attributes()
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key1", "Val1");

        Attributes four = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key4", "Val4");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(four, four.clone());
        }

    @Test public void equalsIsOrderInsensitive_10_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes two = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);

        Attributes three = new Attributes()
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key1", "Val1");

        Attributes four = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null)
            .add("Key4", "Val4");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(one, four);
        }

    @Test void cloneAttributes_1_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);
        Attributes two = one.clone();
        assertEquals(3, two.size());
        }

    @Test void cloneAttributes_2_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);
        Attributes two = one.clone();
        // removed other assertion
        assertEquals("Val2", two.get("Key2"));
        }

    @Test void cloneAttributes_3_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);
        Attributes two = one.clone();
        // removed other assertion
        // removed other assertion
        assertEquals(one, two);
        }

    @Test void cloneAttributes_4_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);
        Attributes two = one.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        two.add("Key4", "Val4");
        assertEquals(4, two.size());
        }

    @Test void cloneAttributes_5_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);
        Attributes two = one.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        two.add("Key4", "Val4");
        // removed other assertion
        assertEquals(3, one.size());
        }

    @Test void cloneAttributes_6_oe() {
        Attributes one = new Attributes()
            .add("Key1", "Val1")
            .add("Key2", "Val2")
            .add("Key3", null);
        Attributes two = one.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        two.add("Key4", "Val4");
        // removed other assertion
        // removed other assertion
        assertNotEquals(one, two);
        }

}
