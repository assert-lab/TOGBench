package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.jsoup.nodes.Document.OutputSettings;
import static org.jsoup.nodes.Entities.EscapeMode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntitiesTest_OE25Dev {
    @Test public void escape() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        assertEquals("Hello &amp;&lt;&gt; &Aring; &aring; &#x3c0; &#x65b0; there &frac34; &copy; &raquo;", escapedAscii);
        assertEquals("Hello &amp;&lt;&gt; &angst; &aring; &pi; &#x65b0; there &frac34; &copy; &raquo;", escapedAsciiFull);
        assertEquals("Hello &amp;&lt;&gt; &#xc5; &#xe5; &#x3c0; &#x65b0; there &#xbe; &#xa9; &#xbb;", escapedAsciiXhtml);
        assertEquals("Hello &amp;&lt;&gt; Å å π 新 there ¾ © »", escapedUtfFull);
        assertEquals("Hello &amp;&lt;&gt; Å å π 新 there ¾ © »", escapedUtfMin);
        // odd that it's defined as aring in base but angst in full

        // round trip
        assertEquals(text, Entities.unescape(escapedAscii));
        assertEquals(text, Entities.unescape(escapedAsciiFull));
        assertEquals(text, Entities.unescape(escapedAsciiXhtml));
        assertEquals(text, Entities.unescape(escapedUtfFull));
        assertEquals(text, Entities.unescape(escapedUtfMin));
    }

    @Test public void escapedSupplementary() {
        String text = "\uD835\uDD59";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        assertEquals("&#x1d559;", escapedAscii);
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        assertEquals("&hopf;", escapedAsciiFull);
        String escapedUtf= Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        assertEquals(text, escapedUtf);
    }

    @Test public void unescapeMultiChars() {
        String text = "&NestedGreaterGreater; &nGg; &nGt; &nGtv; &Gt; &gg;"; // gg is not combo, but 8811 could conflict with NestedGreaterGreater or others
        String un = "≫ ⋙̸ ≫⃒ ≫̸ ≫ ≫";
        assertEquals(un, Entities.unescape(text));
        String escaped = Entities.escape(un, new OutputSettings().charset("ascii").escapeMode(extended));
        assertEquals("&Gt; &Gg;&#x338; &Gt;&#x20d2; &Gt;&#x338; &Gt; &Gt;", escaped);
        assertEquals(un, Entities.unescape(escaped));
    }

    @Test public void xhtml() {
        assertEquals(38, xhtml.codepointForName("amp"));
        assertEquals(62, xhtml.codepointForName("gt"));
        assertEquals(60, xhtml.codepointForName("lt"));
        assertEquals(34, xhtml.codepointForName("quot"));

        assertEquals("amp", xhtml.nameForCodepoint(38));
        assertEquals("gt", xhtml.nameForCodepoint(62));
        assertEquals("lt", xhtml.nameForCodepoint(60));
        assertEquals("quot", xhtml.nameForCodepoint(34));
    }

    @Test public void getByName() {
        assertEquals("≫⃒", Entities.getByName("nGt"));
        assertEquals("fj", Entities.getByName("fjlig"));
        assertEquals("≫", Entities.getByName("gg"));
        assertEquals("©", Entities.getByName("copy"));
    }

    @Test public void escapeSupplementaryCharacter() {
        String text = new String(Character.toChars(135361));
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        assertEquals("&#x210c1;", escapedAscii);
        String escapedUtf = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(base));
        assertEquals(text, escapedUtf);
    }

    @Test public void notMissingMultis() {
        String text = "&nparsl;";
        String un = "\u2AFD\u20E5";
        assertEquals(un, Entities.unescape(text));
    }

    @Test public void notMissingSupplementals() {
        String text = "&npolint; &qfr;";
        String un = "⨔ \uD835\uDD2E"; // 𝔮
        assertEquals(un, Entities.unescape(text));
    }

    @Test public void unescape() {
        String text = "Hello &AElig; &amp;&LT&gt; &reg &angst; &angst &#960; &#960 &#x65B0; there &! &frac34; &copy; &COPY;";
        assertEquals("Hello Æ &<> ® Å &angst π π 新 there &! ¾ © ©", Entities.unescape(text));

        assertEquals("&0987654321; &unknown", Entities.unescape("&0987654321; &unknown"));
    }

    @Test public void strictUnescape() { // for attributes, enforce strict unescaping (must look like &#xxx; , not just &#xxx)
        String text = "Hello &amp= &amp;";
        assertEquals("Hello &amp= &", Entities.unescape(text, true));
        assertEquals("Hello &= &", Entities.unescape(text));
        assertEquals("Hello &= &", Entities.unescape(text, false));
    }


    @Test public void caseSensitive() {
        String unescaped = "Ü ü & &";
        assertEquals("&Uuml;&uuml;&amp;&amp;",Entities.escape(unescaped,new OutputSettings().charset("ascii").escapeMode(extended)));

        String escaped = "&Uuml; &uuml; &amp; &AMP";
        assertEquals("Ü ü & &", Entities.unescape(escaped));
    }

    @Test public void quoteReplacements() {
        String escaped = "&#92; &#36;";
        String unescaped = "\\ $";

        assertEquals(unescaped, Entities.unescape(escaped));
    }

    @Test public void letterDigitEntities() {
        String html = "<p>&sup1;&sup2;&sup3;&frac14;&frac12;&frac34;</p>";
        Document doc = Jsoup.parse(html);
        doc.outputSettings().charset("ascii");
        Element p = doc.select("p").first();
        assertEquals("&sup1;&sup2;&sup3;&frac14;&frac12;&frac34;", p.html());
        assertEquals("¹²³¼½¾", p.text());
        doc.outputSettings().charset("UTF-8");
        assertEquals("¹²³¼½¾", p.html());
    }

    @Test public void noSpuriousDecodes() {
        String string = "http://www.foo.com?a=1&num_rooms=1&children=0&int=VA&b=2";
        assertEquals(string, Entities.unescape(string));
    }

    @Test public void escapesGtInXmlAttributesButNotInHtml() {
        // https://github.com/jhy/jsoup/issues/528 - < is OK in HTML attribute values, but not in XML


        String docHtml = "<a title='<p>One</p>'>One</a>";
        Document doc = Jsoup.parse(docHtml);
        Element element = doc.select("a").first();

        doc.outputSettings().escapeMode(base);
        assertEquals("<a title=\"<p>One</p>\">One</a>", element.outerHtml());

        doc.outputSettings().escapeMode(xhtml);
        assertEquals("<a title=\"&lt;p>One&lt;/p>\">One</a>", element.outerHtml());
    }

    @Test public void controlCharactersAreEscaped() {
        // https://github.com/jhy/jsoup/issues/1556
        // we escape ascii control characters in both HTML and XML for compatibility. Required in XML and probably
        // easier to read in HTML
        String input = "<a foo=\"&#x1b;esc&#x7;bell\">Text &#x1b; &#x7;</a>";
        Document doc = Jsoup.parse(input);
        assertEquals(input, doc.body().html());

        Document xml = Jsoup.parse(input, "", Parser.xmlParser());
        assertEquals(input, xml.html());
    }

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

        assertEquals("Hello &amp;&lt;&gt; &angst; &aring; &pi; &#x65b0; there &frac34; &copy; &raquo;", escapedAsciiFull);
        }

    @Test public void escape_3_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        assertEquals("Hello &amp;&lt;&gt; &#xc5; &#xe5; &#x3c0; &#x65b0; there &#xbe; &#xa9; &#xbb;", escapedAsciiXhtml);
        }

    @Test public void escape_4_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        assertEquals("Hello &amp;&lt;&gt; Å å π 新 there ¾ © »", escapedUtfFull);
        }

    @Test public void escape_5_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));

        assertEquals("Hello &amp;&lt;&gt; Å å π 新 there ¾ © »", escapedUtfMin);
        }

    @Test public void escape_6_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));


        assertEquals(text, Entities.unescape(escapedAscii));
        }

    @Test public void escape_7_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));


        assertEquals(text, Entities.unescape(escapedAsciiFull));
        }

    @Test public void escape_8_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));


        assertEquals(text, Entities.unescape(escapedAsciiXhtml));
        }

    @Test public void escape_9_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));


        assertEquals(text, Entities.unescape(escapedUtfFull));
        }

    @Test public void escape_10_oe() {
        String text = "Hello &<> Å å π 新 there ¾ © »";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        String escapedAsciiXhtml = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(xhtml));
        String escapedUtfFull = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(extended));
        String escapedUtfMin = Entities.escape(text, new OutputSettings().charset("UTF-8").escapeMode(xhtml));


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
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
        assertEquals("&hopf;", escapedAsciiFull);
        }

    @Test public void escapedSupplementary_3_oe() {
        String text = "\uD835\uDD59";
        String escapedAscii = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(base));
        String escapedAsciiFull = Entities.escape(text, new OutputSettings().charset("ascii").escapeMode(extended));
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
        String escaped = Entities.escape(un, new OutputSettings().charset("ascii").escapeMode(extended));
        assertEquals("&Gt; &Gg;&#x338; &Gt;&#x20d2; &Gt;&#x338; &Gt; &Gt;", escaped);
        }

    @Test public void unescapeMultiChars_3_oe() {
        String text = "&NestedGreaterGreater; &nGg; &nGt; &nGtv; &Gt; &gg;"; // gg is not combo, but 8811 could conflict with NestedGreaterGreater or others
        String un = "≫ ⋙̸ ≫⃒ ≫̸ ≫ ≫";
        String escaped = Entities.escape(un, new OutputSettings().charset("ascii").escapeMode(extended));
        assertEquals(un, Entities.unescape(escaped));
        }

    @Test public void xhtml_1_oe() {
        assertEquals(38, xhtml.codepointForName("amp"));
        }

    @Test public void xhtml_2_oe() {
        assertEquals(62, xhtml.codepointForName("gt"));
        }

    @Test public void xhtml_3_oe() {
        assertEquals(60, xhtml.codepointForName("lt"));
        }

    @Test public void xhtml_4_oe() {
        assertEquals(34, xhtml.codepointForName("quot"));
        }

    @Test public void xhtml_5_oe() {

        assertEquals("amp", xhtml.nameForCodepoint(38));
        }

    @Test public void xhtml_6_oe() {

        assertEquals("gt", xhtml.nameForCodepoint(62));
        }

    @Test public void xhtml_7_oe() {

        assertEquals("lt", xhtml.nameForCodepoint(60));
        }

    @Test public void xhtml_8_oe() {

        assertEquals("quot", xhtml.nameForCodepoint(34));
        }

    @Test public void getByName_1_oe() {
        assertEquals("≫⃒", Entities.getByName("nGt"));
        }

    @Test public void getByName_2_oe() {
        assertEquals("fj", Entities.getByName("fjlig"));
        }

    @Test public void getByName_3_oe() {
        assertEquals("≫", Entities.getByName("gg"));
        }

    @Test public void getByName_4_oe() {
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

        assertEquals("&0987654321; &unknown", Entities.unescape("&0987654321; &unknown"));
        }

    @Test public void strictUnescape_1_oe() { // for attributes, enforce strict unescaping (must look like &#xxx; , not just &#xxx)
        String text = "Hello &amp= &amp;";
        assertEquals("Hello &amp= &", Entities.unescape(text, true));
        }

    @Test public void strictUnescape_2_oe() { // for attributes, enforce strict unescaping (must look like &#xxx; , not just &#xxx)
        String text = "Hello &amp= &amp;";
        assertEquals("Hello &= &", Entities.unescape(text));
        }

    @Test public void strictUnescape_3_oe() { // for attributes, enforce strict unescaping (must look like &#xxx; , not just &#xxx)
        String text = "Hello &amp= &amp;";
        assertEquals("Hello &= &", Entities.unescape(text, false));
        }

    @Test public void caseSensitive_2_oe() {
        String unescaped = "Ü ü & &";

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
        assertEquals("¹²³¼½¾", p.text());
        }

    @Test public void letterDigitEntities_3_oe() {
        String html = "<p>&sup1;&sup2;&sup3;&frac14;&frac12;&frac34;</p>";
        Document doc = Jsoup.parse(html);
        doc.outputSettings().charset("ascii");
        Element p = doc.select("p").first();
        doc.outputSettings().charset("UTF-8");
        assertEquals("¹²³¼½¾", p.html());
        }

    @Test public void noSpuriousDecodes_1_oe() {
        String string = "http://www.foo.com?a=1&num_rooms=1&children=0&int=VA&b=2";
        assertEquals(string, Entities.unescape(string));
        }

    @Test public void escapesGtInXmlAttributesButNotInHtml_1_oe() {


        String docHtml = "<a title='<p>One</p>'>One</a>";
        Document doc = Jsoup.parse(docHtml);
        Element element = doc.select("a").first();

        doc.outputSettings().escapeMode(base);
        assertEquals("<a title=\"<p>One</p>\">One</a>", element.outerHtml());
        }

    @Test public void escapesGtInXmlAttributesButNotInHtml_2_oe() {


        String docHtml = "<a title='<p>One</p>'>One</a>";
        Document doc = Jsoup.parse(docHtml);
        Element element = doc.select("a").first();

        doc.outputSettings().escapeMode(base);

        doc.outputSettings().escapeMode(xhtml);
        assertEquals("<a title=\"&lt;p>One&lt;/p>\">One</a>", element.outerHtml());
        }

    @Test public void controlCharactersAreEscaped_1_oe() {
        String input = "<a foo=\"&#x1b;esc&#x7;bell\">Text &#x1b; &#x7;</a>";
        Document doc = Jsoup.parse(input);
        assertEquals(input, doc.body().html());
        }

    @Test public void controlCharactersAreEscaped_2_oe() {
        String input = "<a foo=\"&#x1b;esc&#x7;bell\">Text &#x1b; &#x7;</a>";
        Document doc = Jsoup.parse(input);

        Document xml = Jsoup.parse(input, "", Parser.xmlParser());
        assertEquals(input, xml.html());
        }

}
