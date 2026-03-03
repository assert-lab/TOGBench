package org.jsoup.integration;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.helper.W3CDom;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tests the URL connection. Not enabled by default, so tests don't require network connection.

 @author Jonathan Hedley, jonathan@hedley.net */
@Disabled // ignored by default so tests don't require network access. comment out to enable.
// todo: rebuild these into a local Jetty test server, so not reliant on the vagaries of the internet.
public class UrlConnectTest_OE25Dev {
    private static final String WEBSITE_WITH_INVALID_CERTIFICATE = "https://certs.cac.washington.edu/CAtest/";
    private static final String WEBSITE_WITH_SNI = "https://jsoup.org/";
    public static String browserUa = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36";

    @Test
    public void exceptOnUnknownContentType() {
        String url = "http://direct.jsoup.org/rez/osi_logo.png"; // not text/* but image/png, should throw
        boolean threw = false;
        try {
            Document doc = Jsoup.parse(new URL(url), 3000);
        } catch (UnsupportedMimeTypeException e) {
            threw = true;
            assertEquals("org.jsoup.UnsupportedMimeTypeException: Unhandled content type. Must be text/*, application/xml, or application/xhtml+xml. Mimetype=image/png, URL=http://direct.jsoup.org/rez/osi_logo.png", e.toString());
            assertEquals(url, e.getUrl());
            assertEquals("image/png", e.getMimeType());
        } catch (IOException e) {
        }
        assertTrue(threw);
    }

    private static String ihVal(String key, Document doc) {
        return doc.select("th:contains("+key+") + td").first().text();
    }

    /**
     * Verify that security disabling feature works properly.
     * <p/>
     * 1. try to hit url with invalid certificate and evaluate that exception is thrown
     *
     * @throws Exception
     */


    /**
     * Verify that requests to websites with SNI fail on jdk 1.6
     * <p/>
     * read for more details:
     * http://en.wikipedia.org/wiki/Server_Name_Indication
     *
     * Test is ignored independent from others as it requires JDK 1.6
     * @throws Exception
     */

    // The following tests were added to test specific domains if they work. All code paths
    // which make the following test green are tested in other unit or integration tests, so the following lines
    // could be deleted

    /*
     Proxy tests. Assumes local proxy running on 8888, without system propery set (so that specifying it is required).
     */

    @Test
    public void fetchBaidu_1_oe() throws IOException {
        Connection.Response res = Jsoup.connect("http://www.baidu.com/").timeout(10*1000).execute();
        Document doc = res.parse();

        assertEquals("GBK", doc.outputSettings().charset().displayName());
    }

    @Test
    public void fetchBaidu_2_oe() throws IOException {
        Connection.Response res = Jsoup.connect("http://www.baidu.com/").timeout(10*1000).execute();
        Document doc = res.parse();

        // removed other assertion
        assertEquals("GBK", res.charset());
    }

    @Test
    public void fetchBaidu_3_oe() throws IOException {
        Connection.Response res = Jsoup.connect("http://www.baidu.com/").timeout(10*1000).execute();
        Document doc = res.parse();

        // removed other assertion
        // removed other assertion
        assert(res.hasCookie("BAIDUID"));
    }

    @Test
    public void fetchBaidu_4_oe() throws IOException {
        Connection.Response res = Jsoup.connect("http://www.baidu.com/").timeout(10*1000).execute();
        Document doc = res.parse();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("text/html;charset=gbk", res.contentType());
    }

@Test
    public void ignoresContentTypeIfSoConfigured_1_oe() throws IOException {
        Document doc = Jsoup.connect("https://jsoup.org/rez/osi_logo.png").ignoreContentType(true).get();
        assertEquals("", doc.title()); // this will cause an ugly parse tree;
    }

@Test
    public void followsTempRedirect_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302.pl"); // http://jsoup.org
        Document doc = con.get();
        assertTrue(doc.title().contains("jsoup"));
    }

@Test
    public void followsNewTempRedirect_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/307.pl"); // http://jsoup.org
        Document doc = con.get();
        assertTrue(doc.title().contains("jsoup"));
    }

@Test
    public void followsNewTempRedirect_2_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/307.pl"); // http://jsoup.org
        Document doc = con.get();
        // removed other assertion
        assertEquals("https://jsoup.org/", con.response().url().toString());
    }

@Test
    public void postRedirectsFetchWithGet_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302.pl")
                .data("Argument", "Riposte")
                .method(Connection.Method.POST);
        Connection.Response res = con.execute();
        assertEquals("https://jsoup.org/", res.url().toExternalForm());
    }

@Test
    public void postRedirectsFetchWithGet_2_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302.pl")
                .data("Argument", "Riposte")
                .method(Connection.Method.POST);
        Connection.Response res = con.execute();
        // removed other assertion
        assertEquals(Connection.Method.GET, res.method());
    }

@Test
    public void followsRedirectToHttps_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302-secure.pl"); // https://www.google.com
        con.data("id", "5");
        Document doc = con.get();
        assertTrue(doc.title().contains("Google"));
    }

@Test
    public void followsRelativeRedirect_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302-rel.pl"); // to /tidy/
        Document doc = con.post();
        assertTrue(doc.title().contains("HTML Tidy Online"));
    }

@Test
    public void followsRelativeDotRedirect_1_oe() throws IOException {
        // redirects to "./ok.html", should resolve to http://direct.infohound.net/tools/ok.html
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302-rel-dot.pl"); // to ./ok.html
        Document doc = con.post();
        assertTrue(doc.title().contains("OK"));
    }

@Test
    public void followsRelativeDotRedirect_2_oe() throws IOException {
        // redirects to "./ok.html", should resolve to http://direct.infohound.net/tools/ok.html
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302-rel-dot.pl"); // to ./ok.html
        Document doc = con.post();
        // removed other assertion
        assertEquals(doc.location(), "http://direct.infohound.net/tools/ok.html");
    }

@Test
    public void followsRelativeDotRedirect2_1_oe() throws IOException {
        //redirects to "esportspenedes.cat/./ep/index.php", should resolve to "esportspenedes.cat/ep/index.php"
        Connection con = Jsoup.connect("http://esportspenedes.cat")  // note lack of trailing / - server should redir to / first, then to ./ep/...; but doesn't'
                .timeout(10000);
        Document doc = con.post();
        assertEquals(doc.location(), "http://esportspenedes.cat/ep/index.php");
    }

@Test
    public void followsRedirectsWithWithespaces_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://tinyurl.com/kgofxl8"); // to http://www.google.com/?q=white spaces
        Document doc = con.get();
        assertTrue(doc.title().contains("Google"));
    }

@Test
    public void gracefullyHandleBrokenLocationRedirect_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://aag-ye.com"); // has Location: http:/temp/AAG_New/en/index.php
        con.get(); // would throw exception on error
        assertTrue(true);
    }

@Test
    public void ignores500tExceptionIfSoConfigured_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/500.pl").ignoreHttpErrors(true);
        Connection.Response res = con.execute();
        Document doc = res.parse();
        assertEquals(500, res.statusCode());
    }

@Test
    public void ignores500tExceptionIfSoConfigured_2_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/500.pl").ignoreHttpErrors(true);
        Connection.Response res = con.execute();
        Document doc = res.parse();
        // removed other assertion
        assertEquals("Application Error", res.statusMessage());
    }

@Test
    public void ignores500tExceptionIfSoConfigured_3_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/500.pl").ignoreHttpErrors(true);
        Connection.Response res = con.execute();
        Document doc = res.parse();
        // removed other assertion
        // removed other assertion
        assertEquals("Woops", doc.select("h1").first().text());
    }

@Test
    public void ignores500WithNoContentExceptionIfSoConfigured_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/500-no-content.pl").ignoreHttpErrors(true);
        Connection.Response res = con.execute();
        Document doc = res.parse();
        assertEquals(500, res.statusCode());
    }

@Test
    public void ignores500WithNoContentExceptionIfSoConfigured_2_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/500-no-content.pl").ignoreHttpErrors(true);
        Connection.Response res = con.execute();
        Document doc = res.parse();
        // removed other assertion
        assertEquals("Application Error", res.statusMessage());
    }

@Test
    public void ignores200WithNoContentExceptionIfSoConfigured_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/200-no-content.pl").ignoreHttpErrors(true);
        Connection.Response res = con.execute();
        Document doc = res.parse();
        assertEquals(200, res.statusCode());
    }

@Test
    public void ignores200WithNoContentExceptionIfSoConfigured_2_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/200-no-content.pl").ignoreHttpErrors(true);
        Connection.Response res = con.execute();
        Document doc = res.parse();
        // removed other assertion
        assertEquals("All Good", res.statusMessage());
    }

@Test
    public void handles200WithNoContent_1_oe() throws IOException {
        Connection con = Jsoup
            .connect("http://direct.infohound.net/tools/200-no-content.pl")
            .userAgent(browserUa);
        Connection.Response res = con.execute();
        Document doc = res.parse();
        assertEquals(200, res.statusCode());
    }

@Test
    public void handles200WithNoContent_2_oe() throws IOException {
        Connection con = Jsoup
            .connect("http://direct.infohound.net/tools/200-no-content.pl")
            .userAgent(browserUa);
        Connection.Response res = con.execute();
        Document doc = res.parse();
        // removed other assertion

        con = Jsoup
            .connect("http://direct.infohound.net/tools/200-no-content.pl")
            .parser(Parser.xmlParser())
            .userAgent(browserUa);
        res = con.execute();
        doc = res.parse();
        assertEquals(200, res.statusCode());
    }

@Test
    public void doesntRedirectIfSoConfigured_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302.pl").followRedirects(false);
        Connection.Response res = con.execute();
        assertEquals(302, res.statusCode());
    }

@Test
    public void doesntRedirectIfSoConfigured_2_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302.pl").followRedirects(false);
        Connection.Response res = con.execute();
        // removed other assertion
        assertEquals("http://jsoup.org", res.header("Location"));
    }

@Test
    public void redirectsResponseCookieToNextResponse_1_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302-cookie.pl");
        Connection.Response res = con.execute();
        assertEquals("asdfg123",res.cookie("token"));// confirms that cookies set on 1st hit are presented in final result;
    }

@Test
    public void redirectsResponseCookieToNextResponse_2_oe() throws IOException {
        Connection con = Jsoup.connect("http://direct.infohound.net/tools/302-cookie.pl");
        Connection.Response res = con.execute();
        // removed other assertion
        Document doc = res.parse();
        assertEquals("token=asdfg123; uid=jhy", ihVal("HTTP_COOKIE", doc)); // confirms that redirected hit saw cookie;
    }

@Test
    public void maximumRedirects_1_oe() {
        boolean threw = false;
        try {
            Document doc = Jsoup.connect("http://direct.infohound.net/tools/loop.pl").get();
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Too many redirects"));
    }
    }

@Test
    public void maximumRedirects_2_oe() {
        boolean threw = false;
        try {
            Document doc = Jsoup.connect("http://direct.infohound.net/tools/loop.pl").get();
        } catch (IOException e) {
            // removed other assertion
            threw = true;
        }
        assertTrue(threw);
    }

@Test
    public void handlesDodgyCharset_1_oe() throws IOException {
        // tests that when we get back "UFT8", that it is recognised as unsupported, and falls back to default instead
        String url = "http://direct.infohound.net/tools/bad-charset.pl";
        Connection.Response res = Jsoup.connect(url).execute();
        assertEquals("text/html;charset=UFT8",res.header("Content-Type"));// from the header assertNull(res.charset());// tried to get from header,not supported,so returns null Document doc = res.parse();// would throw an error if charset unsupported assertTrue(doc.text().contains("Hello!"));
    }

@Test
    public void handlesDodgyCharset_2_oe() throws IOException {
        // tests that when we get back "UFT8", that it is recognised as unsupported, and falls back to default instead
        String url = "http://direct.infohound.net/tools/bad-charset.pl";
        Connection.Response res = Jsoup.connect(url).execute();
        // removed other assertion
        assertEquals("UTF-8", res.charset()); // set from default on parse;
    }

@Test
    public void testUnsafeFail_1_oe() throws Exception {
        String url = WEBSITE_WITH_INVALID_CERTIFICATE;
        assertThrows(IOException.class, () -> Jsoup.connect(url).execute());
    }

@Test
    public void testSNIFail_1_oe() throws Exception {
        assertThrows(IOException.class, () -> Jsoup.connect(WEBSITE_WITH_SNI).execute());
    }

@Test
    public void shouldWorkForCharsetInExtraAttribute_1_oe() throws IOException {
        Connection.Response res = Jsoup.connect("https://www.creditmutuel.com/groupe/fr/").execute();
        Document doc = res.parse(); // would throw an error if charset unsupported
        assertEquals("ISO-8859-1", res.charset());
    }

@Test
    public void shouldSelectFirstCharsetOnWeirdMultileCharsetsInMetaTags_1_oe() throws IOException {
        Connection.Response res = Jsoup.connect("http://aamo.info/").execute();
        res.parse(); // would throw an error if charset unsupported
        assertEquals("ISO-8859-1", res.charset());
    }

@Test
    public void shouldParseBrokenHtml5MetaCharsetTagCorrectly_1_oe() throws IOException {
        Connection.Response res = Jsoup.connect("http://9kuhkep.net").execute();
        res.parse(); // would throw an error if charset unsupported
        assertEquals("UTF-8", res.charset());
    }

@Test
    public void shouldEmptyMetaCharsetCorrectly_1_oe() throws IOException {
        Connection.Response res = Jsoup.connect("http://aastmultimedia.com").execute();
        res.parse(); // would throw an error if charset unsupported
        assertEquals("UTF-8", res.charset());
    }

@Test
    public void shouldWorkForDuplicateCharsetInTag_1_oe() throws IOException {
        Connection.Response res = Jsoup.connect("http://aaptsdassn.org").execute();
        Document doc = res.parse(); // would throw an error if charset unsupported
        assertEquals("ISO-8859-1", res.charset());
    }

@Test
    public void handles201Created_1_oe() throws IOException {
        Document doc = Jsoup.connect("http://direct.infohound.net/tools/201.pl").get(); // 201, location=jsoup
        assertEquals("https://jsoup.org/", doc.location());
    }

@Test
    public void fetchViaHttpProxy_1_oe() throws IOException {
        String url = "https://jsoup.org";
        Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("localhost", 8888));
        Document doc = Jsoup.connect(url).proxy(proxy).get();
        assertTrue(doc.title().contains("jsoup"));
    }

@Test
    public void fetchViaHttpProxySetByArgument_1_oe() throws IOException {
        String url = "https://jsoup.org";
        Document doc = Jsoup.connect(url).proxy("localhost", 8888).get();
        assertTrue(doc.title().contains("jsoup"));
    }

@Test
    public void invalidProxyFails_1_oe() {
        boolean caught = false;
        String url = "https://jsoup.org";
        try {
            Document doc = Jsoup.connect(url).proxy("localhost", 8889).get();
        } catch (IOException e) {
            caught = e instanceof ConnectException;
        }
        assertTrue(caught);
    }

@Test
    public void proxyGetAndSet_1_oe() throws IOException {
        String url = "https://jsoup.org";
        Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("localhost", 8889)); // invalid
        final Connection con = Jsoup.connect(url).proxy(proxy);

        assert con.request().proxy() == proxy;
    }

@Test
    public void proxyGetAndSet_2_oe() throws IOException {
        String url = "https://jsoup.org";
        Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("localhost", 8889)); // invalid
        final Connection con = Jsoup.connect(url).proxy(proxy);

        // removed other assertion
        con.request().proxy(null); // disable
        Document doc = con.get();
        assertTrue(doc.title().contains("jsoup")); // would fail if actually went via proxy;
    }

@Test
    public void throwsIfRequestBodyForGet_1_oe() throws IOException {
        boolean caught = false;
        String url = "https://jsoup.org";
        try {
            Document doc = Jsoup.connect(url).requestBody("fail").get();
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        assertTrue(caught);
    }

@Test
    public void canSpecifyResponseCharset_1_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        assertNull(res1.charset());// not set in headers;
    }

@Test
    public void canSpecifyResponseCharset_2_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        assertEquals("windows-1252",doc1.charset().displayName());// but determined at parse time;
    }

@Test
    public void canSpecifyResponseCharset_3_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        // removed other assertion
        assertEquals("Cost is €100",doc1.select("p").text());
    }

@Test
    public void canSpecifyResponseCharset_4_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        // removed other assertion
        // removed other assertion
        assertTrue(doc1.text().contains("€"));
    }

@Test
    public void canSpecifyResponseCharset_5_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, no override
        Connection.Response res2 = Jsoup.connect(noCharsetUrl).execute();
        assertNull(res2.charset());// not set in headers;
    }

@Test
    public void canSpecifyResponseCharset_6_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, no override
        Connection.Response res2 = Jsoup.connect(noCharsetUrl).execute();
        // removed other assertion
        final Document doc2 = res2.parse();
        assertEquals("UTF-8",doc2.charset().displayName());// so defaults to utf-8;
    }

@Test
    public void canSpecifyResponseCharset_7_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, no override
        Connection.Response res2 = Jsoup.connect(noCharsetUrl).execute();
        // removed other assertion
        final Document doc2 = res2.parse();
        // removed other assertion
        assertEquals("Cost is �100",doc2.select("p").text());
    }

@Test
    public void canSpecifyResponseCharset_8_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, no override
        Connection.Response res2 = Jsoup.connect(noCharsetUrl).execute();
        // removed other assertion
        final Document doc2 = res2.parse();
        // removed other assertion
        // removed other assertion
        assertTrue(doc2.text().contains("�"));
    }

@Test
    public void canSpecifyResponseCharset_9_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, no override
        Connection.Response res2 = Jsoup.connect(noCharsetUrl).execute();
        // removed other assertion
        final Document doc2 = res2.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, let's override
        Connection.Response res3 = Jsoup.connect(noCharsetUrl).execute();
        assertNull(res3.charset());// not set in headers;
    }

@Test
    public void canSpecifyResponseCharset_10_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, no override
        Connection.Response res2 = Jsoup.connect(noCharsetUrl).execute();
        // removed other assertion
        final Document doc2 = res2.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, let's override
        Connection.Response res3 = Jsoup.connect(noCharsetUrl).execute();
        // removed other assertion
        res3.charset("windows-1252");
        assertEquals("windows-1252",res3.charset());// read back;
    }

@Test
    public void canSpecifyResponseCharset_11_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, no override
        Connection.Response res2 = Jsoup.connect(noCharsetUrl).execute();
        // removed other assertion
        final Document doc2 = res2.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, let's override
        Connection.Response res3 = Jsoup.connect(noCharsetUrl).execute();
        // removed other assertion
        res3.charset("windows-1252");
        // removed other assertion
        final Document doc3 = res3.parse();
        assertEquals("windows-1252",doc3.charset().displayName());// from override assertEquals("Cost is €100",doc3.select("p").text());
    }

@Test
    public void canSpecifyResponseCharset_12_oe() throws IOException {
        // both these docs have <80> in there as euro/control char depending on charset
        String noCharsetUrl = "http://direct.infohound.net/tools/Windows-1252-nocharset.html";
        String charsetUrl = "http://direct.infohound.net/tools/Windows-1252-charset.html";

        // included in meta
        Connection.Response res1 = Jsoup.connect(charsetUrl).execute();
        // removed other assertion
        final Document doc1 = res1.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, no override
        Connection.Response res2 = Jsoup.connect(noCharsetUrl).execute();
        // removed other assertion
        final Document doc2 = res2.parse();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // no meta, let's override
        Connection.Response res3 = Jsoup.connect(noCharsetUrl).execute();
        // removed other assertion
        res3.charset("windows-1252");
        // removed other assertion
        final Document doc3 = res3.parse();
        // removed other assertion
        assertTrue(doc3.text().contains("€"));
    }

@Test
    public void handlesUnescapedRedirects_1_oe() throws IOException {
        // URL locations should be url safe (ascii) but are often not, so we should try to guess
        // in this case the location header is utf-8, but defined in spec as iso8859, so detect, convert, encode
        String url = "http://direct.infohound.net/tools/302-utf.pl";
        String urlEscaped = "http://direct.infohound.net/tools/test%F0%9F%92%A9.html";

        Connection.Response res = Jsoup.connect(url).execute();
        Document doc = res.parse();
        assertEquals(doc.body().text(), "\uD83D\uDCA9!");
    }

@Test
    public void handlesUnescapedRedirects_2_oe() throws IOException {
        // URL locations should be url safe (ascii) but are often not, so we should try to guess
        // in this case the location header is utf-8, but defined in spec as iso8859, so detect, convert, encode
        String url = "http://direct.infohound.net/tools/302-utf.pl";
        String urlEscaped = "http://direct.infohound.net/tools/test%F0%9F%92%A9.html";

        Connection.Response res = Jsoup.connect(url).execute();
        Document doc = res.parse();
        // removed other assertion
        assertEquals(doc.location(), urlEscaped);
    }

@Test
    public void handlesUnescapedRedirects_3_oe() throws IOException {
        // URL locations should be url safe (ascii) but are often not, so we should try to guess
        // in this case the location header is utf-8, but defined in spec as iso8859, so detect, convert, encode
        String url = "http://direct.infohound.net/tools/302-utf.pl";
        String urlEscaped = "http://direct.infohound.net/tools/test%F0%9F%92%A9.html";

        Connection.Response res = Jsoup.connect(url).execute();
        Document doc = res.parse();
        // removed other assertion
        // removed other assertion

        Connection.Response res2 = Jsoup.connect(url).followRedirects(false).execute();
        assertEquals("/tools/test\uD83D\uDCA9.html", res2.header("Location"));
    }

@Test public void handlesEscapesInRedirecct_1_oe() throws IOException {
        Document doc = Jsoup.connect("http://infohound.net/tools/302-escaped.pl").get();
        assertEquals("http://infohound.net/tools/q.pl?q=one%20two", doc.location());
        }

@Test public void handlesEscapesInRedirecct_2_oe() throws IOException {
        Document doc = Jsoup.connect("http://infohound.net/tools/302-escaped.pl").get();
        // removed other assertion

        doc = Jsoup.connect("http://infohound.net/tools/302-white.pl").get();
        assertEquals("http://infohound.net/tools/q.pl?q=one%20two", doc.location());
        }

@Test
    public void handlesUt8fInUrl_1_oe() throws IOException {
        String url = "http://direct.infohound.net/tools/test\uD83D\uDCA9.html";
        String urlEscaped = "http://direct.infohound.net/tools/test%F0%9F%92%A9.html";

        Connection.Response res = Jsoup.connect(url).execute();
        Document doc = res.parse();
        assertEquals("\uD83D\uDCA9!", doc.body().text());
    }

@Test
    public void handlesUt8fInUrl_2_oe() throws IOException {
        String url = "http://direct.infohound.net/tools/test\uD83D\uDCA9.html";
        String urlEscaped = "http://direct.infohound.net/tools/test%F0%9F%92%A9.html";

        Connection.Response res = Jsoup.connect(url).execute();
        Document doc = res.parse();
        // removed other assertion
        assertEquals(urlEscaped, doc.location());
    }

@Test
    public void inWildUtfRedirect_1_oe() throws IOException {
        Connection.Response res = Jsoup.connect("http://brabantn.ws/Q4F").execute();
        Document doc = res.parse();
        assertEquals("http://www.omroepbrabant.nl/?news/2474781303/Gestrande+ree+in+Oss+niet+verdoofd,+maar+doodgeschoten+%E2%80%98Dit+kan+gewoon+niet,+bizar%E2%80%99+[VIDEO].aspx",doc.location());
    }

@Test
    public void inWildUtfRedirect2_1_oe() throws IOException {
        Connection.Response res = Jsoup.connect("https://ssl.souq.com/sa-en/2724288604627/s").execute();
        Document doc = res.parse();
        assertEquals("https://saudi.souq.com/sa-en/%D8%AE%D8%B2%D9%86%D8%A9-%D8%A2%D9%85%D9%86%D8%A9-3-%D8%B7%D8%A8%D9%82%D8%A7%D8%AA-%D8%A8%D9%86%D8%B8%D8%A7%D9%85-%D9%82%D9%81%D9%84-%D8%A5%D9%84%D9%83%D8%AA%D8%B1%D9%88%D9%86%D9%8A-bsd11523-6831477/i/?ctype=dsrch",doc.location());
    }

@Test public void handlesEscapedRedirectUrls_1_oe() throws IOException {
        String url = "http://www.altalex.com/documents/news/2016/12/06/questioni-civilistiche-conseguenti-alla-depenalizzazione";
        // sends: Location:http://shop.wki.it/shared/sso/sso.aspx?sso=&url=http%3a%2f%2fwww.altalex.com%2fsession%2fset%2f%3freturnurl%3dhttp%253a%252f%252fwww.altalex.com%253a80%252fdocuments%252fnews%252f2016%252f12%252f06%252fquestioni-civilistiche-conseguenti-alla-depenalizzazione
        // then to: http://www.altalex.com/session/set/?returnurl=http%3a%2f%2fwww.altalex.com%3a80%2fdocuments%2fnews%2f2016%2f12%2f06%2fquestioni-civilistiche-conseguenti-alla-depenalizzazione&sso=RDRG6T684G4AK2E7U591UGR923
        // then : http://www.altalex.com:80/documents/news/2016/12/06/questioni-civilistiche-conseguenti-alla-depenalizzazione

        // bug is that jsoup goes to
        // 	GET /shared/sso/sso.aspx?sso=&url=http%253a%252f%252fwww.altalex.com%252fsession%252fset%252f%253freturnurl%253dhttp%25253a%25252f%25252fwww.altalex.com%25253a80%25252fdocuments%25252fnews%25252f2016%25252f12%25252f06%25252fquestioni-civilistiche-conseguenti-alla-depenalizzazione HTTP/1.1
        // i.e. double escaped

        Connection.Response res = Jsoup.connect(url)
                .proxy("localhost", 8888)
                .execute();
        Document doc = res.parse();
        assertEquals(200, res.statusCode());
        }

@Test public void handlesUnicodeInQuery_1_oe() throws IOException {
        Document doc = Jsoup.connect("https://www.google.pl/search?q=gąska").get();
        assertEquals("gąska - Szukaj w Google", doc.title());
        }

@Test public void handlesUnicodeInQuery_2_oe() throws IOException {
        Document doc = Jsoup.connect("https://www.google.pl/search?q=gąska").get();
        // removed other assertion

        doc = Jsoup.connect("http://mov-world.net/archiv/TV/A/%23No.Title/").get();
        assertEquals("Index of /archiv/TV/A/%23No.Title", doc.title());
        }

@Test public void handlesSuperDeepPage_1_oe() throws IOException {
        // https://github.com/jhy/jsoup/issues/955

        long start = System.currentTimeMillis();
        String url = "http://sv.stargate.wikia.com/wiki/M2J";
        Document doc = Jsoup.connect(url).get();
        assertEquals("M2J | Sv.stargate Wiki | FANDOM powered by Wikia", doc.title());
        }

@Test public void handlesSuperDeepPage_2_oe() throws IOException {
        // https://github.com/jhy/jsoup/issues/955

        long start = System.currentTimeMillis();
        String url = "http://sv.stargate.wikia.com/wiki/M2J";
        Document doc = Jsoup.connect(url).get();
        // removed other assertion
        assertEquals(110160, doc.select("dd").size());
        }

@Test public void handlesSuperDeepPage_3_oe() throws IOException {
        // https://github.com/jhy/jsoup/issues/955

        long start = System.currentTimeMillis();
        String url = "http://sv.stargate.wikia.com/wiki/M2J";
        Document doc = Jsoup.connect(url).get();
        // removed other assertion
        // removed other assertion
        // those are all <dl><dd> stacked in each other. wonder how that got generated?
        assertTrue(System.currentTimeMillis() - start < 1000);
        }

@Test public void handles966_1_oe() throws IOException {
        // http://szshb.nxszs.gov.cn/
        // https://github.com/jhy/jsoup/issues/966

        Document doc = Jsoup.connect("http://szshb.nxszs.gov.cn/").get();

        assertEquals("石嘴山市环境保护局", doc.title());
        }

@Test public void canRequestIdn_1_oe() throws IOException {
        String url = "https://räksmörgås.josefsson.org/";
        Document doc = Jsoup.connect(url).get();

        assertEquals("https://xn--rksmrgs-5wao1o.josefsson.org/", doc.location());
        }

@Test public void canRequestIdn_2_oe() throws IOException {
        String url = "https://räksmörgås.josefsson.org/";
        Document doc = Jsoup.connect(url).get();

        // removed other assertion
        assertTrue(doc.title().contains("Räksmörgås.josefßon.org"));
        }

}
