package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Token queue tests.
 */
public class TokenQueueTest_OE25Dev {

    @Test public void testNestedQuotes() {
        validateNestedQuotes("<html><body><a id=\"identifier\" onclick=\"func('arg')\" /></body></html>", "a[onclick*=\"('arg\"]");
        validateNestedQuotes("<html><body><a id=\"identifier\" onclick=func('arg') /></body></html>", "a[onclick*=\"('arg\"]");
        validateNestedQuotes("<html><body><a id=\"identifier\" onclick='func(\"arg\")' /></body></html>", "a[onclick*='(\"arg']");
        validateNestedQuotes("<html><body><a id=\"identifier\" onclick=func(\"arg\") /></body></html>", "a[onclick*='(\"arg']");
    }

    private static void validateNestedQuotes(String html, String selector) {
        assertEquals("#identifier", Jsoup.parse(html).select(selector).first().cssSelector());
    }


}
