package org.jsoup.integration;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.integration.servlets.CookieServlet;
import org.jsoup.integration.servlets.EchoServlet;
import org.jsoup.integration.servlets.FileServlet;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest_OE25Dev {
    @BeforeAll
    public static void setUp() {
        TestServer.start();
    }

    private static Elements keyEls(String key, Document doc) {
        return doc.select("th:contains(" + key + ") + td");
    }

    private static String keyText(String key, Document doc) {
        return doc.selectFirst("th:contains(" + key + ") + td").text();
    }

    // validate that only cookies set by cookie servlet get to the cookie servlet path
    private void assertCookieServlet(Document doc) {
        assertEquals(2,doc.select("table tr").size());// two of three sent to servlet(/ and /CookieServlet)
        Elements doc3Els = keyEls("One",doc);
        assertEquals(2, doc3Els.size());
        assertEquals("CookieServlet", doc3Els.get(0).text()); // ordered by most specific path
        assertEquals("Root", doc3Els.get(1).text()); // ordered by most specific path
    }

    // validate that only for echo servlet
    private void assertEchoServlet(Document doc) {
        Elements echoEls = keyEls("Cookie: One", doc);  // two of three sent to servlet (/ and /EchoServlet)
        assertEquals(2, echoEls.size());
        assertEquals("EchoServlet", echoEls.get(0).text()); // ordered by most specific path - /Echo
        assertEquals("Root", echoEls.get(1).text()); // ordered by most specific path - /
    }


}
