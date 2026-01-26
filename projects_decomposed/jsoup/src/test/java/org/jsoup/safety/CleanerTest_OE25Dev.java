package org.jsoup.safety;

import org.jsoup.Jsoup;
import org.jsoup.MultiLocaleExtension.MultiLocaleTest;
import org.jsoup.TextUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.Range;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tests for the cleaner.

 @author Jonathan Hedley, jonathan@hedley.net */
public class CleanerTest_OE25Dev {
    @Test public void simpleBehaviourTest() {
        String h = "<div><p class=foo><a href='http://evil.com'>Hello <b id=bar>there</b>!</a></div>";
        String cleanHtml = Jsoup.clean(h, Safelist.simpleText());

        assertEquals("Hello <b>there</b>!", TextUtil.stripNewlines(cleanHtml));
    }

    @Test public void simpleBehaviourTest2() {
        String h = "Hello <b>there</b>!";
        String cleanHtml = Jsoup.clean(h, Safelist.simpleText());

        assertEquals("Hello <b>there</b>!", TextUtil.stripNewlines(cleanHtml));
    }

    @Test public void basicBehaviourTest() {
        String h = "<div><p><a href='javascript:sendAllMoney()'>Dodgy</a> <A HREF='HTTP://nice.com'>Nice</a></p><blockquote>Hello</blockquote>";
        String cleanHtml = Jsoup.clean(h, Safelist.basic());

        assertEquals("<p><a rel=\"nofollow\">Dodgy</a> <a href=\"http://nice.com\" rel=\"nofollow\">Nice</a></p><blockquote>Hello</blockquote>",
                TextUtil.stripNewlines(cleanHtml));
    }

    @Test public void basicWithImagesTest() {
        String h = "<div><p><img src='http://example.com/' alt=Image></p><p><img src='ftp://ftp.example.com'></p></div>";
        String cleanHtml = Jsoup.clean(h, Safelist.basicWithImages());
        assertEquals("<p><img src=\"http://example.com/\" alt=\"Image\"></p><p><img></p>", TextUtil.stripNewlines(cleanHtml));
    }

    @Test public void testRelaxed() {
        String h = "<h1>Head</h1><table><tr><td>One<td>Two</td></tr></table>";
        String cleanHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<h1>Head</h1><table><tbody><tr><td>One</td><td>Two</td></tr></tbody></table>", TextUtil.stripNewlines(cleanHtml));
    }

    @Test public void testRemoveTags() {
        String h = "<div><p><A HREF='HTTP://nice.com'>Nice</a></p><blockquote>Hello</blockquote>";
        String cleanHtml = Jsoup.clean(h, Safelist.basic().removeTags("a"));

        assertEquals("<p>Nice</p><blockquote>Hello</blockquote>", TextUtil.stripNewlines(cleanHtml));
    }

    @Test public void testRemoveAttributes() {
        String h = "<div><p>Nice</p><blockquote cite='http://example.com/quotations'>Hello</blockquote>";
        String cleanHtml = Jsoup.clean(h, Safelist.basic().removeAttributes("blockquote", "cite"));

        assertEquals("<p>Nice</p><blockquote>Hello</blockquote>", TextUtil.stripNewlines(cleanHtml));
    }

    @Test public void testRemoveEnforcedAttributes() {
        String h = "<div><p><A HREF='HTTP://nice.com'>Nice</a></p><blockquote>Hello</blockquote>";
        String cleanHtml = Jsoup.clean(h, Safelist.basic().removeEnforcedAttribute("a", "rel"));

        assertEquals("<p><a href=\"http://nice.com\">Nice</a></p><blockquote>Hello</blockquote>",
                TextUtil.stripNewlines(cleanHtml));
    }

    @Test public void testRemoveProtocols() {
        String h = "<p>Contact me <a href='mailto:info@example.com'>here</a></p>";
        String cleanHtml = Jsoup.clean(h, Safelist.basic().removeProtocols("a", "href", "ftp", "mailto"));

        assertEquals("<p>Contact me <a rel=\"nofollow\">here</a></p>",
                TextUtil.stripNewlines(cleanHtml));
    }

    @MultiLocaleTest
    public void safeListedProtocolShouldBeRetained(Locale locale) {
        Locale.setDefault(locale);

        Safelist safelist = Safelist.none()
                .addTags("a")
                .addAttributes("a", "href")
                .addProtocols("a", "href", "something");

        String cleanHtml = Jsoup.clean("<a href=\"SOMETHING://x\"></a>", safelist);

        assertEquals("<a href=\"SOMETHING://x\"></a>", TextUtil.stripNewlines(cleanHtml));
    }

    @Test public void testDropComments() {
        String h = "<p>Hello<!-- no --></p>";
        String cleanHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<p>Hello</p>", cleanHtml);
    }

    @Test public void testDropXmlProc() {
        String h = "<?import namespace=\"xss\"><p>Hello</p>";
        String cleanHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<p>Hello</p>", cleanHtml);
    }

    @Test public void testDropScript() {
        String h = "<SCRIPT SRC=//ha.ckers.org/.j><SCRIPT>alert(/XSS/.source)</SCRIPT>";
        String cleanHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("", cleanHtml);
    }

    @Test public void testDropImageScript() {
        String h = "<IMG SRC=\"javascript:alert('XSS')\">";
        String cleanHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<img>", cleanHtml);
    }

    @Test public void testCleanJavascriptHref() {
        String h = "<A HREF=\"javascript:document.location='http://www.google.com/'\">XSS</A>";
        String cleanHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<a>XSS</a>", cleanHtml);
    }

    @Test public void testCleanAnchorProtocol() {
        String validAnchor = "<a href=\"#valid\">Valid anchor</a>";
        String invalidAnchor = "<a href=\"#anchor with spaces\">Invalid anchor</a>";

        // A Safelist that does not allow anchors will strip them out.
        String cleanHtml = Jsoup.clean(validAnchor, Safelist.relaxed());
        assertEquals("<a>Valid anchor</a>", cleanHtml);

        cleanHtml = Jsoup.clean(invalidAnchor, Safelist.relaxed());
        assertEquals("<a>Invalid anchor</a>", cleanHtml);

        // A Safelist that allows them will keep them.
        Safelist relaxedWithAnchor = Safelist.relaxed().addProtocols("a", "href", "#");

        cleanHtml = Jsoup.clean(validAnchor, relaxedWithAnchor);
        assertEquals(validAnchor, cleanHtml);

        // An invalid anchor is never valid.
        cleanHtml = Jsoup.clean(invalidAnchor, relaxedWithAnchor);
        assertEquals("<a>Invalid anchor</a>", cleanHtml);
    }

    @Test public void testDropsUnknownTags() {
        String h = "<p><custom foo=true>Test</custom></p>";
        String cleanHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<p>Test</p>", cleanHtml);
    }

    @Test public void testHandlesEmptyAttributes() {
        String h = "<img alt=\"\" src= unknown=''>";
        String cleanHtml = Jsoup.clean(h, Safelist.basicWithImages());
        assertEquals("<img alt=\"\">", cleanHtml);
    }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue(Jsoup.isValid(ok, Safelist.basic()));
        assertTrue(Jsoup.isValid(ok1, Safelist.basic()));
        assertFalse(Jsoup.isValid(nok1, Safelist.basic()));
        assertFalse(Jsoup.isValid(nok2, Safelist.basic()));
        assertFalse(Jsoup.isValid(nok3, Safelist.basic()));
        assertFalse(Jsoup.isValid(nok4, Safelist.basic()));
        assertFalse(Jsoup.isValid(nok5, Safelist.basic()));
        assertFalse(Jsoup.isValid(nok6, Safelist.basic()));
        assertFalse(Jsoup.isValid(ok, Safelist.none()));
        assertFalse(Jsoup.isValid(nok7, Safelist.basic()));
    }

    @Test public void testIsValidDocument() {
        String ok = "<html><head></head><body><p>Hello</p></body><html>";
        String nok = "<html><head><script>woops</script><title>Hello</title></head><body><p>Hello</p></body><html>";

        Safelist relaxed = Safelist.relaxed();
        Cleaner cleaner = new Cleaner(relaxed);
        Document okDoc = Jsoup.parse(ok);
        assertTrue(cleaner.isValid(okDoc));
        assertFalse(cleaner.isValid(Jsoup.parse(nok)));
        assertFalse(new Cleaner(Safelist.none()).isValid(okDoc));
    }

    @Test public void resolvesRelativeLinks() {
        String html = "<a href='/foo'>Link</a><img src='/bar'>";
        String clean = Jsoup.clean(html, "http://example.com/", Safelist.basicWithImages());
        assertEquals("<a href=\"http://example.com/foo\" rel=\"nofollow\">Link</a><img src=\"http://example.com/bar\">", clean);
    }

    @Test public void preservesRelativeLinksIfConfigured() {
        String html = "<a href='/foo'>Link</a><img src='/bar'> <img src='javascript:alert()'>";
        String clean = Jsoup.clean(html, "http://example.com/", Safelist.basicWithImages().preserveRelativeLinks(true));
        assertEquals("<a href=\"/foo\" rel=\"nofollow\">Link</a><img src=\"/bar\"> <img>", clean);
    }

    @Test public void dropsUnresolvableRelativeLinks() {
        String html = "<a href='/foo'>Link</a>";
        String clean = Jsoup.clean(html, Safelist.basic());
        assertEquals("<a rel=\"nofollow\">Link</a>", clean);
    }

    @Test void dropsConcealedJavascriptProtocolWhenRelativesLinksEnabled() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(true);
        String html = "<a href=\"&#0013;ja&Tab;va&Tab;script&#0010;:alert(1)\">Link</a>";
        String clean = Jsoup.clean(html, "https://", safelist);
        assertEquals("<a rel=\"nofollow\">Link</a>", clean);

        String colon = "<a href=\"ja&Tab;va&Tab;script&colon;alert(1)\">Link</a>";
        String cleanColon = Jsoup.clean(colon, "https://", safelist);
        assertEquals("<a rel=\"nofollow\">Link</a>", cleanColon);
    }

    @Test void dropsConcealedJavascriptProtocolWhenRelativesLinksDisabled() {
        Safelist safelist = Safelist.basic().preserveRelativeLinks(false);
        String html = "<a href=\"ja&Tab;vas&#0013;cript:alert(1)\">Link</a>";
        String clean = Jsoup.clean(html, "https://", safelist);
        assertEquals("<a rel=\"nofollow\">Link</a>", clean);
    }

    @Test public void handlesCustomProtocols() {
        String html = "<img src='cid:12345' /> <img src='data:gzzt' />";
        String dropped = Jsoup.clean(html, Safelist.basicWithImages());
        assertEquals("<img> <img>", dropped);

        String preserved = Jsoup.clean(html, Safelist.basicWithImages().addProtocols("img", "src", "cid", "data"));
        assertEquals("<img src=\"cid:12345\"> <img src=\"data:gzzt\">", preserved);
    }

    @Test public void handlesAllPseudoTag() {
        String html = "<p class='foo' src='bar'><a class='qux'>link</a></p>";
        Safelist safelist = new Safelist()
                .addAttributes(":all", "class")
                .addAttributes("p", "style")
                .addTags("p", "a");

        String clean = Jsoup.clean(html, safelist);
        assertEquals("<p class=\"foo\"><a class=\"qux\">link</a></p>", clean);
    }

    @Test public void addsTagOnAttributesIfNotSet() {
        String html = "<p class='foo' src='bar'>One</p>";
        Safelist safelist = new Safelist()
            .addAttributes("p", "class");
        // ^^ safelist does not have explicit tag add for p, inferred from add attributes.
        String clean = Jsoup.clean(html, safelist);
        assertEquals("<p class=\"foo\">One</p>", clean);
    }

    @Test public void supplyOutputSettings() {
        // test that one can override the default document output settings
        Document.OutputSettings os = new Document.OutputSettings();
        os.prettyPrint(false);
        os.escapeMode(Entities.EscapeMode.extended);
        os.charset("ascii");

        String html = "<div><p>&bernou;</p></div>";
        String customOut = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed(), os);
        String defaultOut = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed());
        assertNotSame(defaultOut, customOut);

        assertEquals("<div><p>&Bscr;</p></div>", customOut); // entities now prefers shorted names if aliased
        assertEquals("<div>\n" +
            " <p>ℬ</p>\n" +
            "</div>", defaultOut);

        os.charset("ASCII");
        os.escapeMode(Entities.EscapeMode.base);
        String customOut2 = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed(), os);
        assertEquals("<div><p>&#x212c;</p></div>", customOut2);
    }

    @Test public void handlesFramesets() {
        String dirty = "<html><head><script></script><noscript></noscript></head><frameset><frame src=\"foo\" /><frame src=\"foo\" /></frameset></html>";
        String clean = Jsoup.clean(dirty, Safelist.basic());
        assertEquals("", clean); // nothing good can come out of that

        Document dirtyDoc = Jsoup.parse(dirty);
        Document cleanDoc = new Cleaner(Safelist.basic()).clean(dirtyDoc);
        assertNotNull(cleanDoc);
        assertEquals(0, cleanDoc.body().childNodeSize());
    }

    @Test public void cleansInternationalText() {
        assertEquals("привет", Jsoup.clean("привет", Safelist.none()));
    }

    @Test
    public void bailsIfRemovingProtocolThatsNotSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            // a case that came up on the email list
            Safelist w = Safelist.none();

            // note no add tag, and removing protocol without adding first
            w.addAttributes("a", "href");
            w.removeProtocols("a", "href", "javascript"); // with no protocols enforced, this was a noop. Now validates.
        });
    }

    @Test public void handlesControlCharactersAfterTagName() {
        String html = "<a/\06>";
        String clean = Jsoup.clean(html, Safelist.basic());
        assertEquals("<a rel=\"nofollow\"></a>", clean);
    }

    @Test public void handlesAttributesWithNoValue() {
        // https://github.com/jhy/jsoup/issues/973
        String clean = Jsoup.clean("<a href>Clean</a>", Safelist.basic());

        assertEquals("<a rel=\"nofollow\">Clean</a>", clean);
    }

    @Test public void handlesNoHrefAttribute() {
        String dirty = "<a>One</a> <a href>Two</a>";
        Safelist relaxedWithAnchor = Safelist.relaxed().addProtocols("a", "href", "#");
        String clean = Jsoup.clean(dirty, relaxedWithAnchor);
        assertEquals("<a>One</a> <a>Two</a>", clean);
    }

    @Test public void handlesNestedQuotesInAttribute() {
        // https://github.com/jhy/jsoup/issues/1243 - no repro
        String orig = "<div style=\"font-family: 'Calibri'\">Will (not) fail</div>";
        Safelist allow = Safelist.relaxed()
            .addAttributes("div", "style");

        String clean = Jsoup.clean(orig, allow);
        boolean isValid = Jsoup.isValid(orig, allow);

        assertEquals(orig, TextUtil.stripNewlines(clean)); // only difference is pretty print wrap & indent
        assertTrue(isValid);
    }

    @Test public void copiesOutputSettings() {
        Document orig = Jsoup.parse("<p>test<br></p>");
        orig.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        orig.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        Safelist safelist = Safelist.none().addTags("p", "br");

        Document result = new Cleaner(safelist).clean(orig);
        assertEquals(Document.OutputSettings.Syntax.xml, result.outputSettings().syntax());
        assertEquals("<p>test<br /></p>", result.body().html());
    }

    @Test void preservesSourcePositionViaUserData() {
        Document orig = Jsoup.parse("<script>xss</script>\n <p>Hello</p>", Parser.htmlParser().setTrackPosition(true));
        Element p = orig.expectFirst("p");
        Range origRange = p.sourceRange();
        assertEquals("2,2:22-2,5:25", origRange.toString());

        Document clean = new Cleaner(Safelist.relaxed()).clean(orig);
        Element cleanP = clean.expectFirst("p");
        Range cleanRange = cleanP.sourceRange();
        assertEquals(cleanRange, origRange);
        assertEquals(clean.endSourceRange(), orig.endSourceRange());
    }

    @Test public void simpleBehaviourTest() {
        String h = "<div><p class=foo><a href='http://evil.com'>Hello <b id=bar>there</b>!</a></div>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.simpleText());

        assertEquals("Hello <b>there</b>!", TextUtil.stripNewlines(cleanHtml));
        }

    @Test public void simpleBehaviourTest2() {
        String h = "Hello <b>there</b>!";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.simpleText());

        assertEquals("Hello <b>there</b>!", TextUtil.stripNewlines(cleanHtml));
        }

    @Test public void basicBehaviourTest() {
        String h = "<div><p><a href='javascript:sendAllMoney()'>Dodgy</a> <A HREF='HTTP://nice.com'>Nice</a></p><blockquote>Hello</blockquote>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.basic());

        assertEquals("<p><a rel=\"nofollow\">Dodgy</a> <a href=\"http://nice.com\" rel=\"nofollow\">Nice</a></p><blockquote>Hello</blockquote>", TextUtil.stripNewlines(cleanHtml));
        }

    @Test public void basicWithImagesTest() {
        String h = "<div><p><img src='http://example.com/' alt=Image></p><p><img src='ftp://ftp.example.com'></p></div>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.basicWithImages());
        assertEquals("<p><img src=\"http://example.com/\" alt=\"Image\"></p><p><img></p>", TextUtil.stripNewlines(cleanHtml));
        }

    @Test public void testRelaxed() {
        String h = "<h1>Head</h1><table><tr><td>One<td>Two</td></tr></table>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<h1>Head</h1><table><tbody><tr><td>One</td><td>Two</td></tr></tbody></table>", TextUtil.stripNewlines(cleanHtml));
        }

    @Test public void testRemoveTags() {
        String h = "<div><p><A HREF='HTTP://nice.com'>Nice</a></p><blockquote>Hello</blockquote>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.basic().removeTags("a"));

        assertEquals("<p>Nice</p><blockquote>Hello</blockquote>", TextUtil.stripNewlines(cleanHtml));
        }

    @Test public void testRemoveAttributes() {
        String h = "<div><p>Nice</p><blockquote cite='http://example.com/quotations'>Hello</blockquote>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.basic().removeAttributes("blockquote", "cite"));

        assertEquals("<p>Nice</p><blockquote>Hello</blockquote>", TextUtil.stripNewlines(cleanHtml));
        }

    @Test public void testRemoveEnforcedAttributes() {
        String h = "<div><p><A HREF='HTTP://nice.com'>Nice</a></p><blockquote>Hello</blockquote>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.basic().removeEnforcedAttribute("a", "rel"));

        assertEquals("<p><a href=\"http://nice.com\">Nice</a></p><blockquote>Hello</blockquote>", TextUtil.stripNewlines(cleanHtml));
        }

    @Test public void testRemoveProtocols() {
        String h = "<p>Contact me <a href='mailto:info@example.com'>here</a></p>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.basic().removeProtocols("a", "href", "ftp", "mailto"));

        assertEquals("<p>Contact me <a rel=\"nofollow\">here</a></p>", TextUtil.stripNewlines(cleanHtml));
        }

    @Test public void testDropComments() {
        String h = "<p>Hello<!-- no --></p>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<p>Hello</p>", cleanHtml);
        }

    @Test public void testDropXmlProc() {
        String h = "<?import namespace=\"xss\"><p>Hello</p>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<p>Hello</p>", cleanHtml);
        }

    @Test public void testDropScript() {
        String h = "<SCRIPT SRC=//ha.ckers.org/.j><SCRIPT>alert(/XSS/.source)</SCRIPT>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("", cleanHtml);
        }

    @Test public void testDropImageScript() {
        String h = "<IMG SRC=\"javascript:alert('XSS')\">";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<img>", cleanHtml);
        }

    @Test public void testCleanJavascriptHref() {
        String h = "<A HREF=\"javascript:document.location='http://www.google.com/'\">XSS</A>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<a>XSS</a>", cleanHtml);
        }

    @Test public void testCleanAnchorProtocol() {
        String validAnchor = "<a href=\"#valid\">Valid anchor</a>";
        String invalidAnchor = "<a href=\"#anchor with spaces\">Invalid anchor</a>";

        // A Safelist that does not allow anchors will strip them out.
        String clean_1_oeHtml = Jsoup.clean(validAnchor, Safelist.relaxed());
        assertEquals("<a>Valid anchor</a>", cleanHtml);
        }

    @Test public void testCleanAnchorProtocol() {
        String validAnchor = "<a href=\"#valid\">Valid anchor</a>";
        String invalidAnchor = "<a href=\"#anchor with spaces\">Invalid anchor</a>";

        // A Safelist that does not allow anchors will strip them out.
        String clean_2_oeHtml = Jsoup.clean(validAnchor, Safelist.relaxed());
        // removed other assertion

        cleanHtml = Jsoup.clean(invalidAnchor, Safelist.relaxed());
        assertEquals("<a>Invalid anchor</a>", cleanHtml);
        }

    @Test public void testCleanAnchorProtocol() {
        String validAnchor = "<a href=\"#valid\">Valid anchor</a>";
        String invalidAnchor = "<a href=\"#anchor with spaces\">Invalid anchor</a>";

        // A Safelist that does not allow anchors will strip them out.
        String clean_3_oeHtml = Jsoup.clean(validAnchor, Safelist.relaxed());
        // removed other assertion

        cleanHtml = Jsoup.clean(invalidAnchor, Safelist.relaxed());
        // removed other assertion

        // A Safelist that allows them will keep them.
        Safelist relaxedWithAnchor = Safelist.relaxed().addProtocols("a", "href", "#");

        cleanHtml = Jsoup.clean(validAnchor, relaxedWithAnchor);
        assertEquals(validAnchor, cleanHtml);
        }

    @Test public void testCleanAnchorProtocol() {
        String validAnchor = "<a href=\"#valid\">Valid anchor</a>";
        String invalidAnchor = "<a href=\"#anchor with spaces\">Invalid anchor</a>";

        // A Safelist that does not allow anchors will strip them out.
        String clean_4_oeHtml = Jsoup.clean(validAnchor, Safelist.relaxed());
        // removed other assertion

        cleanHtml = Jsoup.clean(invalidAnchor, Safelist.relaxed());
        // removed other assertion

        // A Safelist that allows them will keep them.
        Safelist relaxedWithAnchor = Safelist.relaxed().addProtocols("a", "href", "#");

        cleanHtml = Jsoup.clean(validAnchor, relaxedWithAnchor);
        // removed other assertion

        // An invalid anchor is never valid.
        cleanHtml = Jsoup.clean(invalidAnchor, relaxedWithAnchor);
        assertEquals("<a>Invalid anchor</a>", cleanHtml);
        }

    @Test public void testDropsUnknownTags() {
        String h = "<p><custom foo=true>Test</custom></p>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.relaxed());
        assertEquals("<p>Test</p>", cleanHtml);
        }

    @Test public void testHandlesEmptyAttributes() {
        String h = "<img alt=\"\" src= unknown=''>";
        String clean_1_oeHtml = Jsoup.clean(h, Safelist.basicWithImages());
        assertEquals("<img alt=\"\">", cleanHtml);
        }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue_1_oe(Jsoup.isValid(ok, Safelist.basic()));
        }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue_2_oe(Jsoup.isValid(ok, Safelist.basic()));
        assertTrue(Jsoup.isValid(ok1, Safelist.basic()));
        }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue_3_oe(Jsoup.isValid(ok, Safelist.basic()));
        // removed other assertion
        assertFalse(Jsoup.isValid(nok1, Safelist.basic()));
        }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue_4_oe(Jsoup.isValid(ok, Safelist.basic()));
        // removed other assertion
        // removed other assertion
        assertFalse(Jsoup.isValid(nok2, Safelist.basic()));
        }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue_5_oe(Jsoup.isValid(ok, Safelist.basic()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Jsoup.isValid(nok3, Safelist.basic()));
        }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue_6_oe(Jsoup.isValid(ok, Safelist.basic()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Jsoup.isValid(nok4, Safelist.basic()));
        }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue_7_oe(Jsoup.isValid(ok, Safelist.basic()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Jsoup.isValid(nok5, Safelist.basic()));
        }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue_8_oe(Jsoup.isValid(ok, Safelist.basic()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Jsoup.isValid(nok6, Safelist.basic()));
        }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue_9_oe(Jsoup.isValid(ok, Safelist.basic()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Jsoup.isValid(ok, Safelist.none()));
        }

    @Test public void testIsValidBodyHtml() {
        String ok = "<p>Test <b><a href='http://example.com/' rel='nofollow'>OK</a></b></p>";
        String ok1 = "<p>Test <b><a href='http://example.com/'>OK</a></b></p>"; // missing enforced is OK because still needs run thru cleaner
        String nok1 = "<p><script></script>Not <b>OK</b></p>";
        String nok2 = "<p align=right>Test Not <b>OK</b></p>";
        String nok3 = "<!-- comment --><p>Not OK</p>"; // comments and the like will be cleaned
        String nok4 = "<html><head>Foo</head><body><b>OK</b></body></html>"; // not body html
        String nok5 = "<p>Test <b><a href='http://example.com/' rel='nofollowme'>OK</a></b></p>";
        String nok6 = "<p>Test <b><a href='http://example.com/'>OK</b></p>"; // missing close tag
        String nok7 = "</div>What";
        assertTrue_10_oe(Jsoup.isValid(ok, Safelist.basic()));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Jsoup.isValid(nok7, Safelist.basic()));
        }

    @Test public void testIsValidDocument() {
        String ok = "<html><head></head><body><p>Hello</p></body><html>";
        String nok = "<html><head><script>woops</script><title>Hello</title></head><body><p>Hello</p></body><html>";

        Safelist relaxed_1_oe = Safelist.relaxed();
        Cleaner cleaner = new Cleaner(relaxed);
        Document okDoc = Jsoup.parse(ok);
        assertTrue(cleaner.isValid(okDoc));
        }

    @Test public void testIsValidDocument() {
        String ok = "<html><head></head><body><p>Hello</p></body><html>";
        String nok = "<html><head><script>woops</script><title>Hello</title></head><body><p>Hello</p></body><html>";

        Safelist relaxed_2_oe = Safelist.relaxed();
        Cleaner cleaner = new Cleaner(relaxed);
        Document okDoc = Jsoup.parse(ok);
        // removed other assertion
        assertFalse(cleaner.isValid(Jsoup.parse(nok)));
        }

    @Test public void testIsValidDocument() {
        String ok = "<html><head></head><body><p>Hello</p></body><html>";
        String nok = "<html><head><script>woops</script><title>Hello</title></head><body><p>Hello</p></body><html>";

        Safelist relaxed_3_oe = Safelist.relaxed();
        Cleaner cleaner = new Cleaner(relaxed);
        Document okDoc = Jsoup.parse(ok);
        // removed other assertion
        // removed other assertion
        assertFalse(new Cleaner(Safelist.none()).isValid(okDoc));
        }

    @Test public void resolvesRelativeLinks() {
        String html = "<a href='/foo'>Link</a><img src='/bar'>";
        String clean_1_oe = Jsoup.clean(html, "http://example.com/", Safelist.basicWithImages());
        assertEquals("<a href=\"http://example.com/foo\" rel=\"nofollow\">Link</a><img src=\"http://example.com/bar\">", clean);
        }

    @Test public void preservesRelativeLinksIfConfigured() {
        String html = "<a href='/foo'>Link</a><img src='/bar'> <img src='javascript:alert()'>";
        String clean_1_oe = Jsoup.clean(html, "http://example.com/", Safelist.basicWithImages().preserveRelativeLinks(true));
        assertEquals("<a href=\"/foo\" rel=\"nofollow\">Link</a><img src=\"/bar\"> <img>", clean);
        }

    @Test public void dropsUnresolvableRelativeLinks() {
        String html = "<a href='/foo'>Link</a>";
        String clean_1_oe = Jsoup.clean(html, Safelist.basic());
        assertEquals("<a rel=\"nofollow\">Link</a>", clean);
        }

    @Test void dropsConcealedJavascriptProtocolWhenRelativesLinksEnabled() {
        Safelist safelist = Safelist.basic_1_oe().preserveRelativeLinks(true);
        String html = "<a href=\"&#0013;ja&Tab;va&Tab;script&#0010;:alert(1)\">Link</a>";
        String clean = Jsoup.clean(html, "https://", safelist);
        assertEquals("<a rel=\"nofollow\">Link</a>", clean);
        }

    @Test void dropsConcealedJavascriptProtocolWhenRelativesLinksEnabled() {
        Safelist safelist = Safelist.basic_2_oe().preserveRelativeLinks(true);
        String html = "<a href=\"&#0013;ja&Tab;va&Tab;script&#0010;:alert(1)\">Link</a>";
        String clean = Jsoup.clean(html, "https://", safelist);
        // removed other assertion

        String colon = "<a href=\"ja&Tab;va&Tab;script&colon;alert(1)\">Link</a>";
        String cleanColon = Jsoup.clean(colon, "https://", safelist);
        assertEquals("<a rel=\"nofollow\">Link</a>", cleanColon);
        }

    @Test void dropsConcealedJavascriptProtocolWhenRelativesLinksDisabled() {
        Safelist safelist = Safelist.basic_1_oe().preserveRelativeLinks(false);
        String html = "<a href=\"ja&Tab;vas&#0013;cript:alert(1)\">Link</a>";
        String clean = Jsoup.clean(html, "https://", safelist);
        assertEquals("<a rel=\"nofollow\">Link</a>", clean);
        }

    @Test public void handlesCustomProtocols() {
        String html = "<img src='cid:12345' /> <img src='data:gzzt' />";
        String dropped = Jsoup.clean_1_oe(html, Safelist.basicWithImages());
        assertEquals("<img> <img>", dropped);
        }

    @Test public void handlesCustomProtocols() {
        String html = "<img src='cid:12345' /> <img src='data:gzzt' />";
        String dropped = Jsoup.clean_2_oe(html, Safelist.basicWithImages());
        // removed other assertion

        String preserved = Jsoup.clean(html, Safelist.basicWithImages().addProtocols("img", "src", "cid", "data"));
        assertEquals("<img src=\"cid:12345\"> <img src=\"data:gzzt\">", preserved);
        }

    @Test public void handlesAllPseudoTag() {
        String html = "<p class='foo' src='bar'><a class='qux'>link</a></p>";
        Safelist_1_oe safelist = new Safelist()
                .addAttributes(":all", "class")
                .addAttributes("p", "style")
                .addTags("p", "a");

        String clean = Jsoup.clean(html, safelist);
        assertEquals("<p class=\"foo\"><a class=\"qux\">link</a></p>", clean);
        }

    @Test public void addsTagOnAttributesIfNotSet() {
        String html = "<p class='foo' src='bar'>One</p>";
        Safelist_1_oe safelist = new Safelist()
            .addAttributes("p", "class");
        // ^^ safelist does not have explicit tag add for p, inferred from add attributes.
        String clean = Jsoup.clean(html, safelist);
        assertEquals("<p class=\"foo\">One</p>", clean);
        }

    @Test public void supplyOutputSettings() {
        // test that one can override the default document output settings
        Document.OutputSettings_1_oe os = new Document.OutputSettings();
        os.prettyPrint(false);
        os.escapeMode(Entities.EscapeMode.extended);
        os.charset("ascii");

        String html = "<div><p>&bernou;</p></div>";
        String customOut = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed(), os);
        String defaultOut = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed());
        assertNotSame(defaultOut, customOut);
        }

    @Test public void supplyOutputSettings() {
        // test that one can override the default document output settings
        Document.OutputSettings_2_oe os = new Document.OutputSettings();
        os.prettyPrint(false);
        os.escapeMode(Entities.EscapeMode.extended);
        os.charset("ascii");

        String html = "<div><p>&bernou;</p></div>";
        String customOut = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed(), os);
        String defaultOut = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed());
        // removed other assertion

        assertEquals("<div><p>&Bscr;</p></div>", customOut); // entities now prefers shorted names if aliased;
        }

    @Test public void supplyOutputSettings() {
        // test that one can override the default document output settings
        Document.OutputSettings_3_oe os = new Document.OutputSettings();
        os.prettyPrint(false);
        os.escapeMode(Entities.EscapeMode.extended);
        os.charset("ascii");

        String html = "<div><p>&bernou;</p></div>";
        String customOut = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed(), os);
        String defaultOut = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed());
        // removed other assertion

        // removed other assertion
        assertEquals("<div>\n" + " <p>ℬ</p>\n" + "</div>", defaultOut);
        }

    @Test public void supplyOutputSettings() {
        // test that one can override the default document output settings
        Document.OutputSettings_4_oe os = new Document.OutputSettings();
        os.prettyPrint(false);
        os.escapeMode(Entities.EscapeMode.extended);
        os.charset("ascii");

        String html = "<div><p>&bernou;</p></div>";
        String customOut = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed(), os);
        String defaultOut = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed());
        // removed other assertion

        // removed other assertion
        // removed other assertion

        os.charset("ASCII");
        os.escapeMode(Entities.EscapeMode.base);
        String customOut2 = Jsoup.clean(html, "http://foo.com/", Safelist.relaxed(), os);
        assertEquals("<div><p>&#x212c;</p></div>", customOut2);
        }

    @Test public void handlesFramesets() {
        String dirty = "<html><head><script></script><noscript></noscript></head><frameset><frame src=\"foo\" /><frame src=\"foo\" /></frameset></html>";
        String clean_1_oe = Jsoup.clean(dirty, Safelist.basic());
        assertEquals("", clean); // nothing good can come out of that;
        }

    @Test public void handlesFramesets() {
        String dirty = "<html><head><script></script><noscript></noscript></head><frameset><frame src=\"foo\" /><frame src=\"foo\" /></frameset></html>";
        String clean_2_oe = Jsoup.clean(dirty, Safelist.basic());
        // removed other assertion

        Document dirtyDoc = Jsoup.parse(dirty);
        Document cleanDoc = new Cleaner(Safelist.basic()).clean(dirtyDoc);
        assertNotNull(cleanDoc);
        }

    @Test public void handlesFramesets() {
        String dirty = "<html><head><script></script><noscript></noscript></head><frameset><frame src=\"foo\" /><frame src=\"foo\" /></frameset></html>";
        String clean_3_oe = Jsoup.clean(dirty, Safelist.basic());
        // removed other assertion

        Document dirtyDoc = Jsoup.parse(dirty);
        Document cleanDoc = new Cleaner(Safelist.basic()).clean(dirtyDoc);
        // removed other assertion
        assertEquals(0, cleanDoc.body().childNodeSize());
        }

    @Test public void cleansInternationalText() {
        assertEquals_1_oe("привет", Jsoup.clean("привет", Safelist.none()));
        }

    @Test
    public void testScriptTagInSafeList_1_oe() {
        Safelist safelist = Safelist.relaxed();
        safelist.addTags( "script" );
        assertTrue( Jsoup.isValid("Hello<script>alert('Doh')</script>World !", safelist) );
    }

    @Test public void handlesControlCharactersAfterTagName() {
        String html = "<a/\06>";
        String clean_1_oe = Jsoup.clean(html, Safelist.basic());
        assertEquals("<a rel=\"nofollow\"></a>", clean);
        }

    @Test public void handlesAttributesWithNoValue() {
        // https://github.com/jhy/jsoup/issues/973
        String clean_1_oe = Jsoup.clean("<a href>Clean</a>", Safelist.basic());

        assertEquals("<a rel=\"nofollow\">Clean</a>", clean);
        }

    @Test public void handlesNoHrefAttribute() {
        String dirty = "<a>One</a> <a href>Two</a>";
        Safelist relaxed_1_oeWithAnchor = Safelist.relaxed().addProtocols("a", "href", "#");
        String clean = Jsoup.clean(dirty, relaxedWithAnchor);
        assertEquals("<a>One</a> <a>Two</a>", clean);
        }

    @Test public void handlesNestedQuotesInAttribute() {
        // https://github.com/jhy/jsoup/issues/1243 - no repro
        String orig = "<div style=\"font-family: 'Calibri'\">Will (not) fail</div>";
        Safelist allow = Safelist.relaxed_1_oe()
            .addAttributes("div", "style");

        String clean = Jsoup.clean(orig, allow);
        boolean isValid = Jsoup.isValid(orig, allow);

        assertEquals(orig, TextUtil.stripNewlines(clean)); // only difference is pretty print wrap & indent;
        }

    @Test public void handlesNestedQuotesInAttribute() {
        // https://github.com/jhy/jsoup/issues/1243 - no repro
        String orig = "<div style=\"font-family: 'Calibri'\">Will (not) fail</div>";
        Safelist allow = Safelist.relaxed_2_oe()
            .addAttributes("div", "style");

        String clean = Jsoup.clean(orig, allow);
        boolean isValid = Jsoup.isValid(orig, allow);

        // removed other assertion
        assertTrue(isValid);
        }

    @Test public void copiesOutputSettings() {
        Document orig = Jsoup.parse_1_oe("<p>test<br></p>");
        orig.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        orig.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        Safelist safelist = Safelist.none().addTags("p", "br");

        Document result = new Cleaner(safelist).clean(orig);
        assertEquals(Document.OutputSettings.Syntax.xml, result.outputSettings().syntax());
        }

    @Test public void copiesOutputSettings() {
        Document orig = Jsoup.parse_2_oe("<p>test<br></p>");
        orig.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        orig.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        Safelist safelist = Safelist.none().addTags("p", "br");

        Document result = new Cleaner(safelist).clean(orig);
        // removed other assertion
        assertEquals("<p>test<br /></p>", result.body().html());
        }

    @Test void preservesSourcePositionViaUserData() {
        Document orig = Jsoup.parse_1_oe("<script>xss</script>\n <p>Hello</p>", Parser.htmlParser().setTrackPosition(true));
        Element p = orig.expectFirst("p");
        Range origRange = p.sourceRange();
        assertEquals("2,2:22-2,5:25", origRange.toString());
        }

    @Test void preservesSourcePositionViaUserData() {
        Document orig = Jsoup.parse_2_oe("<script>xss</script>\n <p>Hello</p>", Parser.htmlParser().setTrackPosition(true));
        Element p = orig.expectFirst("p");
        Range origRange = p.sourceRange();
        // removed other assertion

        Document clean = new Cleaner(Safelist.relaxed()).clean(orig);
        Element cleanP = clean.expectFirst("p");
        Range cleanRange = cleanP.sourceRange();
        assertEquals(cleanRange, origRange);
        }

    @Test void preservesSourcePositionViaUserData() {
        Document orig = Jsoup.parse_3_oe("<script>xss</script>\n <p>Hello</p>", Parser.htmlParser().setTrackPosition(true));
        Element p = orig.expectFirst("p");
        Range origRange = p.sourceRange();
        // removed other assertion

        Document clean = new Cleaner(Safelist.relaxed()).clean(orig);
        Element cleanP = clean.expectFirst("p");
        Range cleanRange = cleanP.sourceRange();
        // removed other assertion
        assertEquals(clean.endSourceRange(), orig.endSourceRange());
        }

}
