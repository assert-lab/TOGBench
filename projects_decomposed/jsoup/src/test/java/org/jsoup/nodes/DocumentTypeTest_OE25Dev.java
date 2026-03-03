package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for the DocumentType node
 *
 * @author Jonathan Hedley, http://jonathanhedley.com/
 */
public class DocumentTypeTest_OE25Dev {
    @Test
    public void constructorValidationOkWithBlankName() {
        new DocumentType("","", "");
    }

    @Test
    public void constructorValidationOkWithBlankPublicAndSystemIds() {
        new DocumentType("html","", "");
    }

    private String htmlOutput(String in) {
        DocumentType type = (DocumentType) Jsoup.parse(in).childNode(0);
        return type.outerHtml();
    }

    private String xmlOutput(String in) {
        return Jsoup.parse(in, "", Parser.xmlParser()).childNode(0).outerHtml();
    }

    @Test
    public void constructorValidationThrowsExceptionOnNulls_1_oe() throws Exception {
        try {
    new DocumentType("html", null, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test public void outerHtmlGeneration_1_oe() {
        DocumentType html5 = new DocumentType("html", "", "");
        assertEquals("<!doctype html>", html5.outerHtml());
        }

    @Test public void outerHtmlGeneration_2_oe() {
        DocumentType html5 = new DocumentType("html", "", "");

        DocumentType publicDocType = new DocumentType("html", "-//IETF//DTD HTML//", "");
        assertEquals("<!DOCTYPE html PUBLIC \"-//IETF//DTD HTML//\">", publicDocType.outerHtml());
        }

    @Test public void outerHtmlGeneration_3_oe() {
        DocumentType html5 = new DocumentType("html", "", "");

        DocumentType publicDocType = new DocumentType("html", "-//IETF//DTD HTML//", "");

        DocumentType systemDocType = new DocumentType("html", "", "http://www.ibm.com/data/dtd/v11/ibmxhtml1-transitional.dtd");
        assertEquals("<!DOCTYPE html SYSTEM \"http://www.ibm.com/data/dtd/v11/ibmxhtml1-transitional.dtd\">", systemDocType.outerHtml());
        }

    @Test public void outerHtmlGeneration_4_oe() {
        DocumentType html5 = new DocumentType("html", "", "");

        DocumentType publicDocType = new DocumentType("html", "-//IETF//DTD HTML//", "");

        DocumentType systemDocType = new DocumentType("html", "", "http://www.ibm.com/data/dtd/v11/ibmxhtml1-transitional.dtd");

        DocumentType combo = new DocumentType("notHtml", "--public", "--system");
        assertEquals("<!DOCTYPE notHtml PUBLIC \"--public\" \"--system\">", combo.outerHtml());
        }

    @Test public void outerHtmlGeneration_5_oe() {
        DocumentType html5 = new DocumentType("html", "", "");

        DocumentType publicDocType = new DocumentType("html", "-//IETF//DTD HTML//", "");

        DocumentType systemDocType = new DocumentType("html", "", "http://www.ibm.com/data/dtd/v11/ibmxhtml1-transitional.dtd");

        DocumentType combo = new DocumentType("notHtml", "--public", "--system");
        assertEquals("notHtml", combo.name());
        }

    @Test public void outerHtmlGeneration_6_oe() {
        DocumentType html5 = new DocumentType("html", "", "");

        DocumentType publicDocType = new DocumentType("html", "-//IETF//DTD HTML//", "");

        DocumentType systemDocType = new DocumentType("html", "", "http://www.ibm.com/data/dtd/v11/ibmxhtml1-transitional.dtd");

        DocumentType combo = new DocumentType("notHtml", "--public", "--system");
        assertEquals("--public", combo.publicId());
        }

    @Test public void outerHtmlGeneration_7_oe() {
        DocumentType html5 = new DocumentType("html", "", "");

        DocumentType publicDocType = new DocumentType("html", "-//IETF//DTD HTML//", "");

        DocumentType systemDocType = new DocumentType("html", "", "http://www.ibm.com/data/dtd/v11/ibmxhtml1-transitional.dtd");

        DocumentType combo = new DocumentType("notHtml", "--public", "--system");
        assertEquals("--system", combo.systemId());
        }

    @Test public void testRoundTrip_1_oe() {
        String base = "<!DOCTYPE html>";
        assertEquals("<!doctype html>", htmlOutput(base));
        }

    @Test public void testRoundTrip_2_oe() {
        String base = "<!DOCTYPE html>";
        assertEquals(base, xmlOutput(base));
        }

    @Test public void testRoundTrip_3_oe() {
        String base = "<!DOCTYPE html>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
        assertEquals(publicDoc, htmlOutput(publicDoc));
        }

    @Test public void testRoundTrip_4_oe() {
        String base = "<!DOCTYPE html>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
        assertEquals(publicDoc, xmlOutput(publicDoc));
        }

    @Test public void testRoundTrip_5_oe() {
        String base = "<!DOCTYPE html>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";
        assertEquals(systemDoc, htmlOutput(systemDoc));
        }

    @Test public void testRoundTrip_6_oe() {
        String base = "<!DOCTYPE html>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";
        assertEquals(systemDoc, xmlOutput(systemDoc));
        }

    @Test public void testRoundTrip_7_oe() {
        String base = "<!DOCTYPE html>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";

        String legacyDoc = "<!DOCTYPE html SYSTEM \"about:legacy-compat\">";
        assertEquals(legacyDoc, htmlOutput(legacyDoc));
        }

    @Test public void testRoundTrip_8_oe() {
        String base = "<!DOCTYPE html>";

        String publicDoc = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";

        String systemDoc = "<!DOCTYPE html SYSTEM \"exampledtdfile.dtd\">";

        String legacyDoc = "<!DOCTYPE html SYSTEM \"about:legacy-compat\">";
        assertEquals(legacyDoc, xmlOutput(legacyDoc));
        }

}
