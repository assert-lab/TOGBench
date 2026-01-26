package org.jsoup.parser;

import org.jsoup.MultiLocaleExtension.MultiLocaleTest;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tag tests.
 @author Jonathan Hedley, jonathan@hedley.net */
public class TagTest_OE25Dev {
    @Test public void isCaseSensitive() {
        Tag p1 = Tag.valueOf("P");
        Tag p2 = Tag.valueOf("p");
        assertNotEquals(p1, p2);
    }

    @MultiLocaleTest
    public void canBeInsensitive(Locale locale) {
        Locale.setDefault(locale);

        Tag script1 = Tag.valueOf("script", ParseSettings.htmlDefault);
        Tag script2 = Tag.valueOf("SCRIPT", ParseSettings.htmlDefault);
        assertSame(script1, script2);
    }

    @Test public void trims() {
        Tag p1 = Tag.valueOf("p");
        Tag p2 = Tag.valueOf(" p ");
        assertEquals(p1, p2);
    }

    @Test public void equality() {
        Tag p1 = Tag.valueOf("p");
        Tag p2 = Tag.valueOf("p");
        assertEquals(p1, p2);
        assertSame(p1, p2);
    }

    @Test public void divSemantics() {
        Tag div = Tag.valueOf("div");

        assertTrue(div.isBlock());
        assertTrue(div.formatAsBlock());
    }

    @Test public void pSemantics() {
        Tag p = Tag.valueOf("p");

        assertTrue(p.isBlock());
        assertFalse(p.formatAsBlock());
    }

    @Test public void imgSemantics() {
        Tag img = Tag.valueOf("img");
        assertTrue(img.isInline());
        assertTrue(img.isSelfClosing());
        assertFalse(img.isBlock());
    }

    @Test public void defaultSemantics() {
        Tag foo = Tag.valueOf("FOO"); // not defined
        Tag foo2 = Tag.valueOf("FOO");

        assertEquals(foo, foo2);
        assertTrue(foo.isInline());
        assertTrue(foo.formatAsBlock());
    }

    @Test public void valueOfChecksNotNull() {
        assertThrows(IllegalArgumentException.class, () -> Tag.valueOf(null));
    }

    @Test public void valueOfChecksNotEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Tag.valueOf(" "));
    }

    @Test public void knownTags() {
        assertTrue(Tag.isKnownTag("div"));
        assertFalse(Tag.isKnownTag("explain"));
    }

    @Test public void isCaseSensitive() {
        Tag p1 = Tag.valueOf_1_oe("P");
        Tag p2 = Tag.valueOf("p");
        assertNotEquals(p1, p2);
        }

    @Test public void trims() {
        Tag p1 = Tag.valueOf_1_oe("p");
        Tag p2 = Tag.valueOf(" p ");
        assertEquals(p1, p2);
        }

    @Test public void equality() {
        Tag p1 = Tag.valueOf_1_oe("p");
        Tag p2 = Tag.valueOf("p");
        assertEquals(p1, p2);
        }

    @Test public void equality() {
        Tag p1 = Tag.valueOf_2_oe("p");
        Tag p2 = Tag.valueOf("p");
        // removed other assertion
        assertSame(p1, p2);
        }

    @Test public void divSemantics() {
        Tag div = Tag.valueOf_1_oe("div");

        assertTrue(div.isBlock());
        }

    @Test public void divSemantics() {
        Tag div = Tag.valueOf_2_oe("div");

        // removed other assertion
        assertTrue(div.formatAsBlock());
        }

    @Test public void pSemantics() {
        Tag p = Tag.valueOf_1_oe("p");

        assertTrue(p.isBlock());
        }

    @Test public void pSemantics() {
        Tag p = Tag.valueOf_2_oe("p");

        // removed other assertion
        assertFalse(p.formatAsBlock());
        }

    @Test public void imgSemantics() {
        Tag img = Tag.valueOf_1_oe("img");
        assertTrue(img.isInline());
        }

    @Test public void imgSemantics() {
        Tag img = Tag.valueOf_2_oe("img");
        // removed other assertion
        assertTrue(img.isSelfClosing());
        }

    @Test public void imgSemantics() {
        Tag img = Tag.valueOf_3_oe("img");
        // removed other assertion
        // removed other assertion
        assertFalse(img.isBlock());
        }

    @Test public void defaultSemantics() {
        Tag foo = Tag.valueOf_1_oe("FOO"); // not defined
        Tag foo2 = Tag.valueOf("FOO");

        assertEquals(foo, foo2);
        }

    @Test public void defaultSemantics() {
        Tag foo = Tag.valueOf_2_oe("FOO"); // not defined
        Tag foo2 = Tag.valueOf("FOO");

        // removed other assertion
        assertTrue(foo.isInline());
        }

    @Test public void defaultSemantics() {
        Tag foo = Tag.valueOf_3_oe("FOO"); // not defined
        Tag foo2 = Tag.valueOf("FOO");

        // removed other assertion
        // removed other assertion
        assertTrue(foo.formatAsBlock());
        }

    @Test public void valueOfChecksNotNull() {
        assertThrows_1_oe(IllegalArgumentException.class, () -> Tag.valueOf(null));
        }

    @Test public void valueOfChecksNotEmpty() {
        assertThrows_1_oe(IllegalArgumentException.class, () -> Tag.valueOf(" "));
        }

    @Test public void knownTags() {
        assertTrue_1_oe(Tag.isKnownTag("div"));
        }

    @Test public void knownTags() {
        assertTrue_2_oe(Tag.isKnownTag("div"));
        assertFalse(Tag.isKnownTag("explain"));
        }

}
