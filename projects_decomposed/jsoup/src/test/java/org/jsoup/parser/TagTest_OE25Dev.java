package org.jsoup.parser;

import org.jsoup.MultiLocaleExtension.MultiLocaleTest;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tag tests.
 @author Jonathan Hedley, jonathan@hedley.net */
public class TagTest_OE25Dev {

    @MultiLocaleTest
    public void canBeInsensitive(Locale locale) {
        Locale.setDefault(locale);

        Tag script1 = Tag.valueOf("script", ParseSettings.htmlDefault);
        Tag script2 = Tag.valueOf("SCRIPT", ParseSettings.htmlDefault);
        assertSame(script1, script2);
    }

@Test public void isCaseSensitive_1_oe() {
        Tag p1 = Tag.valueOf("P");
        Tag p2 = Tag.valueOf("p");
        assertNotEquals(p1, p2);
        }

@Test public void trims_1_oe() {
        Tag p1 = Tag.valueOf("p");
        Tag p2 = Tag.valueOf(" p ");
        assertEquals(p1, p2);
        }

@Test public void equality_1_oe() {
        Tag p1 = Tag.valueOf("p");
        Tag p2 = Tag.valueOf("p");
        assertEquals(p1, p2);
        }

@Test public void equality_2_oe() {
        Tag p1 = Tag.valueOf("p");
        Tag p2 = Tag.valueOf("p");
        // removed other assertion
        assertSame(p1, p2);
        }

@Test public void divSemantics_1_oe() {
        Tag div = Tag.valueOf("div");

        assertTrue(div.isBlock());
        }

@Test public void divSemantics_2_oe() {
        Tag div = Tag.valueOf("div");

        // removed other assertion
        assertTrue(div.formatAsBlock());
        }

@Test public void pSemantics_1_oe() {
        Tag p = Tag.valueOf("p");

        assertTrue(p.isBlock());
        }

@Test public void pSemantics_2_oe() {
        Tag p = Tag.valueOf("p");

        // removed other assertion
        assertFalse(p.formatAsBlock());
        }

@Test public void imgSemantics_1_oe() {
        Tag img = Tag.valueOf("img");
        assertTrue(img.isInline());
        }

@Test public void imgSemantics_2_oe() {
        Tag img = Tag.valueOf("img");
        // removed other assertion
        assertTrue(img.isSelfClosing());
        }

@Test public void imgSemantics_3_oe() {
        Tag img = Tag.valueOf("img");
        // removed other assertion
        // removed other assertion
        assertFalse(img.isBlock());
        }

@Test public void defaultSemantics_1_oe() {
        Tag foo = Tag.valueOf("FOO"); // not defined
        Tag foo2 = Tag.valueOf("FOO");

        assertEquals(foo, foo2);
        }

@Test public void defaultSemantics_2_oe() {
        Tag foo = Tag.valueOf("FOO"); // not defined
        Tag foo2 = Tag.valueOf("FOO");

        // removed other assertion
        assertTrue(foo.isInline());
        }

@Test public void defaultSemantics_3_oe() {
        Tag foo = Tag.valueOf("FOO"); // not defined
        Tag foo2 = Tag.valueOf("FOO");

        // removed other assertion
        // removed other assertion
        assertTrue(foo.formatAsBlock());
        }

@Test public void valueOfChecksNotNull_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> Tag.valueOf(null));
        }

@Test public void valueOfChecksNotEmpty_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> Tag.valueOf(" "));
        }

@Test public void knownTags_1_oe() {
        assertTrue(Tag.isKnownTag("div"));
        }

@Test public void knownTags_2_oe() {
        // removed other assertion
        assertFalse(Tag.isKnownTag("explain"));
        }

}
