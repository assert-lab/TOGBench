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

    @Test
    public void testPathScopedCookies_1_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        assertEquals(0,doc1.select("table tr").size());// none sent to servlet;
    }

    @Test
    public void testPathScopedCookies_2_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        assertEquals(0,doc2.select("table tr").size());// none sent to servlet - we just got them!;
    }

    @Test
    public void testPathScopedCookies_3_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 
        assertEquals(1,cookies.size());
    }

    @Test
    public void testPathScopedCookies_4_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 
        assertEquals("Root", cookies.get("One"));
    }

    @Test
    public void testPathScopedCookies_7_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 


        Connection con3 = session.newRequest();
        Document doc3 = con3.get();

        Document echo = session.newRequest().url(EchoServlet.Url).get();
        assertEquals(userAgent,keyText("User-Agent",echo));// check that customer user agent sent on session arrived;
    }

    @Test
    public void testPathScopedCookies_8_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 


        Connection con3 = session.newRequest();
        Document doc3 = con3.get();

        Document echo = session.newRequest().url(EchoServlet.Url).get();
        Document doc4 = Jsoup.newSession().url(CookieServlet.Url).get();
        assertEquals(0,doc4.select("table tr").size());// none sent to servlet;
    }

    @Test
    public void testPathScopedCookies_9_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 


        Connection con3 = session.newRequest();
        Document doc3 = con3.get();

        Document echo = session.newRequest().url(EchoServlet.Url).get();
        Document doc4 = Jsoup.newSession().url(CookieServlet.Url).get();
        Document doc5 = session.newRequest().cookie("Bar","Qux").get();
        Elements doc5Bar = keyEls("Bar", doc5);
        assertEquals("Qux", doc5Bar.first().text());
    }

    @Test
    public void testPathScopedCookiesOnRedirect_1_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();

        assertEquals(EchoServlet.Url, doc1.location());
    }

    @Test
    public void testCanChangeParsers_1_oe() throws IOException {
        Connection session = Jsoup.newSession().parser(Parser.xmlParser());

        String xmlUrl = FileServlet.urlTo("/htmltests/xml-test.xml");
        String xmlVal = "<doc><val>One<val>Two</val>Three</val></doc>\n";

        Document doc1 = session.newRequest().url(xmlUrl).get();
        assertEquals(xmlVal,doc1.html());// not HTML normed,used XML parser;
    }

    @Test
    public void testCanChangeParsers_2_oe() throws IOException {
        Connection session = Jsoup.newSession().parser(Parser.xmlParser());

        String xmlUrl = FileServlet.urlTo("/htmltests/xml-test.xml");
        String xmlVal = "<doc><val>One<val>Two</val>Three</val></doc>\n";

        Document doc1 = session.newRequest().url(xmlUrl).get();
        Document doc2 = session.newRequest().parser(Parser.htmlParser()).url(xmlUrl).get();
        assertTrue(doc2.html().startsWith("<html>"));
    }

    @Test
    public void testCanChangeParsers_3_oe() throws IOException {
        Connection session = Jsoup.newSession().parser(Parser.xmlParser());

        String xmlUrl = FileServlet.urlTo("/htmltests/xml-test.xml");
        String xmlVal = "<doc><val>One<val>Two</val>Three</val></doc>\n";

        Document doc1 = session.newRequest().url(xmlUrl).get();
        Document doc2 = session.newRequest().parser(Parser.htmlParser()).url(xmlUrl).get();

        Document doc3 = session.newRequest().url(xmlUrl).get();
        assertEquals(xmlVal, doc3.html()); // did not blow away xml default;
    }

    @Test
    public void testPathScopedCookies_5_oe_1_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 


        Connection con3 = session.newRequest();
        Document doc3 = con3.get();
                final Document doc0 = doc3;
        assertEquals(2,doc0.select("table tr").size());// two of three sent to servlet(/ and /CookieServlet);
    }

    @Test
    public void testPathScopedCookies_5_oe_2_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 


        Connection con3 = session.newRequest();
        Document doc3 = con3.get();
                final Document doc0 = doc3;
                Elements doc3Els0 = keyEls("One",doc0);
                assertEquals(2, doc3Els0.size());
    }

    @Test
    public void testPathScopedCookies_5_oe_3_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 


        Connection con3 = session.newRequest();
        Document doc3 = con3.get();
                final Document doc0 = doc3;
                Elements doc3Els0 = keyEls("One",doc0);
                assertEquals("CookieServlet", doc3Els0.get(0).text()); // ordered by most specific path;
    }

    @Test
    public void testPathScopedCookies_5_oe_4_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 


        Connection con3 = session.newRequest();
        Document doc3 = con3.get();
                final Document doc0 = doc3;
                Elements doc3Els0 = keyEls("One",doc0);
                assertEquals("Root", doc3Els0.get(1).text()); // ordered by most specific path;
    }

    @Test
    public void testPathScopedCookies_6_oe_1_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 


        Connection con3 = session.newRequest();
        Document doc3 = con3.get();

        Document echo = session.newRequest().url(EchoServlet.Url).get();
                final Document doc0 = echo;
        Elements echoEls0 = keyEls("Cookie: One", doc0);  // two of three sent to servlet (/ and /EchoServlet)
                assertEquals(2, echoEls0.size());
    }

    @Test
    public void testPathScopedCookies_6_oe_2_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 


        Connection con3 = session.newRequest();
        Document doc3 = con3.get();

        Document echo = session.newRequest().url(EchoServlet.Url).get();
                final Document doc0 = echo;
        Elements echoEls0 = keyEls("Cookie: One", doc0);  // two of three sent to servlet (/ and /EchoServlet)
                assertEquals("EchoServlet", echoEls0.get(0).text()); // ordered by most specific path - /Echo;
    }

    @Test
    public void testPathScopedCookies_6_oe_3_oe() throws IOException {
        final Connection session = Jsoup.newSession();
        final String userAgent = "Jsoup Testalot v0.1";

        session.userAgent(userAgent);
        session.url(CookieServlet.Url);

        Connection con1 = session.newRequest();
        Document doc1 = con1.get();
        Connection con2 = session.newRequest().data(CookieServlet.SetCookiesParam,"1");
        Document doc2 = con2.get();
        Map<String,String> cookies = con2.response().cookies();// simple cookie response,all named "One",so should be first sent 


        Connection con3 = session.newRequest();
        Document doc3 = con3.get();

        Document echo = session.newRequest().url(EchoServlet.Url).get();
                final Document doc0 = echo;
        Elements echoEls0 = keyEls("Cookie: One", doc0);  // two of three sent to servlet (/ and /EchoServlet)
                assertEquals("Root", echoEls0.get(1).text()); // ordered by most specific path - /;
    }

    @Test
    public void testPathScopedCookiesOnRedirect_2_oe_1_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();

                final Document doc0 = doc1;
        Elements echoEls0 = keyEls("Cookie: One", doc0);  // two of three sent to servlet (/ and /EchoServlet)
                assertEquals(2, echoEls0.size());
    }

    @Test
    public void testPathScopedCookiesOnRedirect_2_oe_2_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();

                final Document doc0 = doc1;
        Elements echoEls0 = keyEls("Cookie: One", doc0);  // two of three sent to servlet (/ and /EchoServlet)
                assertEquals("EchoServlet", echoEls0.get(0).text()); // ordered by most specific path - /Echo;
    }

    @Test
    public void testPathScopedCookiesOnRedirect_2_oe_3_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();

                final Document doc0 = doc1;
        Elements echoEls0 = keyEls("Cookie: One", doc0);  // two of three sent to servlet (/ and /EchoServlet)
                assertEquals("Root", echoEls0.get(1).text()); // ordered by most specific path - / // checks we only have /echo cookies;
    }

    @Test
    public void testPathScopedCookiesOnRedirect_3_oe_1_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();


        Document doc2 = session.newRequest()
            .url(EchoServlet.Url)
            .get();
                final Document doc0 = doc2;
        Elements echoEls0 = keyEls("Cookie: One", doc0);  // two of three sent to servlet (/ and /EchoServlet)
                assertEquals(2, echoEls0.size());
    }

    @Test
    public void testPathScopedCookiesOnRedirect_3_oe_2_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();


        Document doc2 = session.newRequest()
            .url(EchoServlet.Url)
            .get();
                final Document doc0 = doc2;
        Elements echoEls0 = keyEls("Cookie: One", doc0);  // two of three sent to servlet (/ and /EchoServlet)
                assertEquals("EchoServlet", echoEls0.get(0).text()); // ordered by most specific path - /Echo;
    }

    @Test
    public void testPathScopedCookiesOnRedirect_3_oe_3_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();


        Document doc2 = session.newRequest()
            .url(EchoServlet.Url)
            .get();
                final Document doc0 = doc2;
        Elements echoEls0 = keyEls("Cookie: One", doc0);  // two of three sent to servlet (/ and /EchoServlet)
                assertEquals("Root", echoEls0.get(1).text()); // ordered by most specific path - / // test retained in session;
    }

    @Test
    public void testPathScopedCookiesOnRedirect_4_oe_1_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();


        Document doc2 = session.newRequest()
            .url(EchoServlet.Url)
            .get();

        Document doc3 = session.newRequest()
            .url(CookieServlet.Url)
            .get();
                final Document doc0 = doc3;
        assertEquals(2,doc0.select("table tr").size());// two of three sent to servlet(/ and /CookieServlet);
    }

    @Test
    public void testPathScopedCookiesOnRedirect_4_oe_2_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();


        Document doc2 = session.newRequest()
            .url(EchoServlet.Url)
            .get();

        Document doc3 = session.newRequest()
            .url(CookieServlet.Url)
            .get();
                final Document doc0 = doc3;
                Elements doc3Els0 = keyEls("One",doc0);
                assertEquals(2, doc3Els0.size());
    }

    @Test
    public void testPathScopedCookiesOnRedirect_4_oe_3_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();


        Document doc2 = session.newRequest()
            .url(EchoServlet.Url)
            .get();

        Document doc3 = session.newRequest()
            .url(CookieServlet.Url)
            .get();
                final Document doc0 = doc3;
                Elements doc3Els0 = keyEls("One",doc0);
                assertEquals("CookieServlet", doc3Els0.get(0).text()); // ordered by most specific path;
    }

    @Test
    public void testPathScopedCookiesOnRedirect_4_oe_4_oe() throws IOException {
        Connection session = Jsoup.newSession();

        Document doc1 = session.newRequest()
            .url(CookieServlet.Url)
            .data(CookieServlet.LocationParam, EchoServlet.Url)
            .data(CookieServlet.SetCookiesParam, "1")
            .get();


        Document doc2 = session.newRequest()
            .url(EchoServlet.Url)
            .get();

        Document doc3 = session.newRequest()
            .url(CookieServlet.Url)
            .get();
                final Document doc0 = doc3;
                Elements doc3Els0 = keyEls("One",doc0);
                assertEquals("Root", doc3Els0.get(1).text()); // ordered by most specific path // and so were the /cookie cookies;
    }

}
