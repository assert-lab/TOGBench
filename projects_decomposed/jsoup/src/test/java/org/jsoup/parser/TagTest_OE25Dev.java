package org.jsoup.parser;

import org.jsoup.MultiLocaleExtension.MultiLocaleTest;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tag tests.
 @author Jonathan Hedley, jonathan@hedley.net */
public class TagTest_OE25Dev {

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
        assertSame(p1, p2);
        }

    @Test public void divSemantics_1_oe() {
        Tag div = Tag.valueOf("div");

        assertTrue(div.isBlock());
        }

    @Test public void divSemantics_2_oe() {
        Tag div = Tag.valueOf("div");

        assertTrue(div.formatAsBlock());
        }

    @Test public void pSemantics_1_oe() {
        Tag p = Tag.valueOf("p");

        assertTrue(p.isBlock());
        }

    @Test public void pSemantics_2_oe() {
        Tag p = Tag.valueOf("p");

        assertFalse(p.formatAsBlock());
        }

    @Test public void imgSemantics_1_oe() {
        Tag img = Tag.valueOf("img");
        assertTrue(img.isInline());
        }

    @Test public void imgSemantics_2_oe() {
        Tag img = Tag.valueOf("img");
        assertTrue(img.isSelfClosing());
        }

    @Test public void imgSemantics_3_oe() {
        Tag img = Tag.valueOf("img");
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

        assertTrue(foo.isInline());
        }

    @Test public void defaultSemantics_3_oe() {
        Tag foo = Tag.valueOf("FOO"); // not defined
        Tag foo2 = Tag.valueOf("FOO");

        assertTrue(foo.formatAsBlock());
        }

    @Test public void valueOfChecksNotNull_1_oe() throws Exception {
        try {
    Tag.valueOf(null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void valueOfChecksNotEmpty_1_oe() throws Exception {
        try {
    Tag.valueOf(" ");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void knownTags_1_oe() {
        assertTrue(Tag.isKnownTag("div"));
        }

    @Test public void knownTags_2_oe() {
        assertFalse(Tag.isKnownTag("explain"));
        }

@MultiLocaleTest
    public void canBeInsensitive_1_oe(Locale locale) {
        Locale.setDefault(locale);

        Tag script1 = Tag.valueOf("script", ParseSettings.htmlDefault);
        Tag script2 = Tag.valueOf("SCRIPT", ParseSettings.htmlDefault);
        assertSame(script1, script2);
    }

}
