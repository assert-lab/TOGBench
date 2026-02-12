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


}
