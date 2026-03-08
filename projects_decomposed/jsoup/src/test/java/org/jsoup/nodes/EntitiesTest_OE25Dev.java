package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.jsoup.nodes.Document.OutputSettings;
import static org.jsoup.nodes.Entities.EscapeMode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntitiesTest_OE25Dev {

    @Test public void escape_1_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        assertEquals("Hello &amp;&lt;&gt; &Aring; &aring; &#x3c0; &#x65b0; there &frac34; &copy; &raquo;", escapedAscii);
        }

    @Test public void escape_2_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        // removed other assertion
        assertEquals("Hello &amp;&lt;&gt; &angst; &aring; &pi; &#x65b0; there &frac34; &copy; &raquo;", escapedAsciiFull);
        }

    @Test public void escape_3_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        // removed other assertion
        // removed other assertion
        assertEquals("Hello &amp;&lt;&gt; &#xc5; &#xe5; &#x3c0; &#x65b0; there &#xbe; &#xa9; &#xbb;", escapedAsciiXhtml);
        }

    @Test public void escape_4_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Hello &amp;&lt;&gt; Å å π 新 there ¾ © »", escapedUtfFull);
        }

    @Test public void escape_5_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Hello &amp;&lt;&gt; Å å π 新 there ¾ © »", escapedUtfMin);
        }

    @Test public void escape_6_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // odd that it's defined as aring in base but angst in full

        // round trip
        assertEquals(text, Entities.unescape(escapedAscii));
        }

    @Test public void escape_7_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // odd that it's defined as aring in base but angst in full

        // round trip
        // removed other assertion
        assertEquals(text, Entities.unescape(escapedAsciiFull));
        }

    @Test public void escape_8_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // odd that it's defined as aring in base but angst in full

        // round trip
        // removed other assertion
        // removed other assertion
        assertEquals(text, Entities.unescape(escapedAsciiXhtml));
        }

    @Test public void escape_9_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // odd that it's defined as aring in base but angst in full

        // round trip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(text, Entities.unescape(escapedUtfFull));
        }

    @Test public void escape_10_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // odd that it's defined as aring in base but angst in full

        // round trip
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(text, Entities.unescape(escapedUtfMin));
        }

    @Test public void escapedSupplementary_1_oe() {
        String text = "\uD835\uDD59";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        assertEquals("&#x1d559;", escapedAscii);
        }

    @Test public void escapedSupplementary_2_oe() {
        String text = "\uD835\uDD59";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        // removed other assertion
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        assertEquals("&hopf;", escapedAsciiFull);
        }

    @Test public void escapedSupplementary_3_oe() {
        String text = "\uD835\uDD59";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        // removed other assertion
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        // removed other assertion
        String escapedUtf= Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        assertEquals(text, escapedUtf);
        }

    @Test public void unescapeMultiChars_1_oe() {
        String text = "&NestedGreaterGreater; &nGg; &nGt; &nGtv; &Gt; &gg;"; // gg is not combo, but 8811 could conflict with NestedGreaterGreater or others
        String un = "≫ ⋙̸ ≫⃒ ≫̸ ≫ ≫";
        assertEquals(un, Entities.unescape(text));
        }

    @Test public void unescapeMultiChars_2_oe() {
        String text = "&NestedGreaterGreater; &nGg; &nGt; &nGtv; &Gt; &gg;"; // gg is not combo, but 8811 could conflict with NestedGreaterGreater or others
        String un = "≫ ⋙̸ ≫⃒ ≫̸ ≫ ≫";
        // removed other assertion
        String escaped = Entities.escape(un, new OutputSettings().charset("ascii").escapeMode(extended));
        assertEquals("&Gt; &Gg;&#x338; &Gt;&#x20d2; &Gt;&#x338; &Gt; &Gt;", escaped);
        }

    @Test public void unescapeMultiChars_3_oe() {
        String text = "&NestedGreaterGreater; &nGg; &nGt; &nGtv; &Gt; &gg;"; // gg is not combo, but 8811 could conflict with NestedGreaterGreater or others
        String un = "≫ ⋙̸ ≫⃒ ≫̸ ≫ ≫";
        // removed other assertion
        String escaped = Entities.escape(un, new OutputSettings().charset("ascii").escapeMode(extended));
        // removed other assertion
        assertEquals(un, Entities.unescape(escaped));
        }

    @Test public void xhtml_1_oe() {
        assertEquals(38, xhtml.codepointForName("amp"));
        }

    @Test public void xhtml_2_oe() {
        // removed other assertion
        assertEquals(62, xhtml.codepointForName("gt"));
        }

    @Test public void xhtml_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(60, xhtml.codepointForName("lt"));
        }

    @Test public void xhtml_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(34, xhtml.codepointForName("quot"));
        }

    @Test public void xhtml_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("amp", xhtml.nameForCodepoint(38));
        }

    @Test public void xhtml_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("gt", xhtml.nameForCodepoint(62));
        }

    @Test public void xhtml_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("lt", xhtml.nameForCodepoint(60));
        }

    @Test public void xhtml_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("quot", xhtml.nameForCodepoint(34));
        }

    @Test public void getByName_1_oe() {
        assertEquals("≫⃒", Entities.getByName("nGt"));
        }

    @Test public void getByName_2_oe() {
        // removed other assertion
        assertEquals("fj", Entities.getByName("fjlig"));
        }

    @Test public void getByName_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("≫", Entities.getByName("gg"));
        }

    @Test public void getByName_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("©", Entities.getByName("copy"));
        }

    @Test public void escapeSupplementaryCharacter_1_oe() {
        String text = new String(Character.toChars(135361));
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        assertEquals("&#x210c1;", escapedAscii);
        }

    @Test public void escapeSupplementaryCharacter_2_oe() {
        String text = new String(Character.toChars(135361));
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        // removed other assertion
        String escapedUtf = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(base));
        assertEquals(text, escapedUtf);
        }

    @Test public void notMissingMultis_1_oe() {
        String text = "&nparsl;";
        String un = "\u2AFD\u20E5";
        assertEquals(un, Entities.unescape(text));
        }

    @Test public void notMissingSupplementals_1_oe() {
        String text = "&npolint; &qfr;";
        String un = "⨔ \uD835\uDD2E"; // 𝔮
        assertEquals(un, Entities.unescape(text));
        }

    @Test public void unescape_1_oe() {
        String text = "Hello &AElig; &amp;&LT&gt; &reg &angst; &angst &#960; &#960 &#x65B0; there &! &frac34; &copy; &COPY;";
        assertEquals("Hello Æ &<> ® Å &angst π π 新 there &! ¾ © ©", Entities.unescape(text));
        }

    @Test public void unescape_2_oe() {
        String text = "Hello &AElig; &amp;&LT&gt; &reg &angst; &angst &#960; &#960 &#x65B0; there &! &frac34; &copy; &COPY;";
        // removed other assertion

        assertEquals("&0987654321; &unknown", Entities.unescape("&0987654321; &unknown"));
        }

    @Test public void strictUnescape_1_oe() { // for attributes, enforce strict unescaping (must look like &#xxx; , not just &#xxx)
        String text = "Hello &amp= &amp;";
        assertEquals("Hello &amp= &", Entities.unescape(text, true));
        }

    @Test public void strictUnescape_2_oe() { // for attributes, enforce strict unescaping (must look like &#xxx; , not just &#xxx)
        String text = "Hello &amp= &amp;";
        // removed other assertion
        assertEquals("Hello &= &", Entities.unescape(text));
        }

    @Test public void strictUnescape_3_oe() { // for attributes, enforce strict unescaping (must look like &#xxx; , not just &#xxx)
        String text = "Hello &amp= &amp;";
        // removed other assertion
        // removed other assertion
        assertEquals("Hello &= &", Entities.unescape(text, false));
        }

    @Test public void caseSensitive_2_oe() {
        String unescaped = "Ü ü & &";
        // removed other assertion

        String escaped = "&Uuml; &uuml; &amp; &AMP";
        assertEquals("Ü ü & &", Entities.unescape(escaped));
        }

    @Test public void quoteReplacements_1_oe() {
        String escaped = "&#92; &#36;";
        String unescaped = "\\ $";

        assertEquals(unescaped, Entities.unescape(escaped));
        }

    @Test public void letterDigitEntities_1_oe() {
        String html = "<p>&sup1;&sup2;&sup3;&frac14;&frac12;&frac34;</p>";
        Document doc = Jsoup.parse(html);
        doc.outputSettings().charset("ascii");
        Element p = doc.select("p").first();
        assertEquals("&sup1;&sup2;&sup3;&frac14;&frac12;&frac34;", p.html());
        }

    @Test public void letterDigitEntities_2_oe() {
        String html = "<p>&sup1;&sup2;&sup3;&frac14;&frac12;&frac34;</p>";
        Document doc = Jsoup.parse(html);
        doc.outputSettings().charset("ascii");
        Element p = doc.select("p").first();
        // removed other assertion
        assertEquals("¹²³¼½¾", p.text());
        }

    @Test public void letterDigitEntities_3_oe() {
        String html = "<p>&sup1;&sup2;&sup3;&frac14;&frac12;&frac34;</p>";
        Document doc = Jsoup.parse(html);
        doc.outputSettings().charset("ascii");
        Element p = doc.select("p").first();
        // removed other assertion
        // removed other assertion
        doc.outputSettings().charset("UTF-8");
        assertEquals("¹²³¼½¾", p.html());
        }

    @Test public void noSpuriousDecodes_1_oe() {
        String string = "http://www.foo.com?a=1&num_rooms=1&children=0&int=VA&b=2";
        assertEquals(string, Entities.unescape(string));
        }

    @Test public void escapesGtInXmlAttributesButNotInHtml_1_oe() {
        // https://github.com/jhy/jsoup/issues/528 - < is OK in HTML attribute values, but not in XML


        String docHtml = "<a title='<p>One</p>'>One</a>";
        Document doc = Jsoup.parse(docHtml);
        Element element = doc.select("a").first();

        doc.outputSettings().escapeMode(base);
        assertEquals("<a title=\"<p>One</p>\">One</a>", element.outerHtml());
        }

    @Test public void escapesGtInXmlAttributesButNotInHtml_2_oe() {
        // https://github.com/jhy/jsoup/issues/528 - < is OK in HTML attribute values, but not in XML


        String docHtml = "<a title='<p>One</p>'>One</a>";
        Document doc = Jsoup.parse(docHtml);
        Element element = doc.select("a").first();

        doc.outputSettings().escapeMode(base);
        // removed other assertion

        doc.outputSettings().escapeMode(xhtml);
        assertEquals("<a title=\"&lt;p>One&lt;/p>\">One</a>", element.outerHtml());
        }

    @Test public void controlCharactersAreEscaped_1_oe() {
        // https://github.com/jhy/jsoup/issues/1556
        // we escape ascii control characters in both HTML and XML for compatibility. Required in XML and probably
        // easier to read in HTML
        String input = "<a foo=\"&#x1b;esc&#x7;bell\">Text &#x1b; &#x7;</a>";
        Document doc = Jsoup.parse(input);
        assertEquals(input, doc.body().html());
        }

    @Test public void controlCharactersAreEscaped_2_oe() {
        // https://github.com/jhy/jsoup/issues/1556
        // we escape ascii control characters in both HTML and XML for compatibility. Required in XML and probably
        // easier to read in HTML
        String input = "<a foo=\"&#x1b;esc&#x7;bell\">Text &#x1b; &#x7;</a>";
        Document doc = Jsoup.parse(input);
        // removed other assertion

        Document xml = Jsoup.parse(input, "", Parser.xmlParser());
        assertEquals(input, xml.html());
        }

}
