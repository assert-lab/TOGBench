package org.jsoup.helper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.MultiLocaleExtension.MultiLocaleTest;
import org.jsoup.integration.ParseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HttpConnectionTest_OE25Dev {
    /* most actual network http connection tests are in integration */

    @Test public void handlesHeaderEncodingOnRequest() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("xxx", "é");
    }

    @Test public void canCreateEmptyConnection_1_oe() {
        HttpConnection con = new HttpConnection();
        assertEquals(Connection.Method.GET, con.request().method());
        }

    @Test public void canCreateEmptyConnection_2_oe() throws Exception {
        HttpConnection con = new HttpConnection();
        // removed other assertion
        try {
     URL url = con.request().url();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void throwsExceptionOnResponseWithoutExecute_1_oe() throws Exception {
        try {
     Connection con = HttpConnection.connect("http://example.com"); con.response();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void throwsExceptionOnParseWithoutExecute_1_oe() throws Exception {
        try {
     Connection con = HttpConnection.connect("http://example.com"); con.response().parse();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void throwsExceptionOnBodyWithoutExecute_1_oe() throws Exception {
        try {
     Connection con = HttpConnection.connect("http://example.com"); con.response().body();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void throwsExceptionOnBodyAsBytesWithoutExecute_1_oe() throws Exception {
        try {
     Connection con = HttpConnection.connect("http://example.com"); con.response().bodyAsBytes();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void headers_1_oe() {
        Connection con = HttpConnection.connect("http://example.com");
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "text/html");
        headers.put("Connection", "keep-alive");
        headers.put("Host", "http://example.com");
        con.headers(headers);
        assertEquals("text/html", con.request().header("content-type"));
        }

    @Test public void headers_2_oe() {
        Connection con = HttpConnection.connect("http://example.com");
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "text/html");
        headers.put("Connection", "keep-alive");
        headers.put("Host", "http://example.com");
        con.headers(headers);
        // removed other assertion
        assertEquals("keep-alive", con.request().header("Connection"));
        }

    @Test public void headers_3_oe() {
        Connection con = HttpConnection.connect("http://example.com");
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "text/html");
        headers.put("Connection", "keep-alive");
        headers.put("Host", "http://example.com");
        con.headers(headers);
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com", con.request().header("Host"));
        }

    @Test public void sameHeadersCombineWithComma_1_oe() {
        Map<String, List<String>> headers = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add("no-cache");
        values.add("no-store");
        headers.put("Cache-Control", values);
        HttpConnection.Response res = new HttpConnection.Response();
        res.processResponseHeaders(headers);
        assertEquals("no-cache, no-store", res.header("Cache-Control"));
        }

    @Test public void multipleHeaders_1_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        assertTrue(req.hasHeader("Accept"));
        }

    @Test public void multipleHeaders_2_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        assertTrue(req.hasHeader("ACCEpt"));
        }

    @Test public void multipleHeaders_3_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        assertEquals("Something, Everything", req.header("accept"));
        }

    @Test public void multipleHeaders_4_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(req.hasHeader("fOO"));
        }

    @Test public void multipleHeaders_5_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Bar", req.header("foo"));
        }

    @Test public void multipleHeaders_6_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        assertEquals(2, accept.size());
        }

    @Test public void multipleHeaders_7_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        assertEquals("Something", accept.get(0));
        }

    @Test public void multipleHeaders_8_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        // removed other assertion
        assertEquals("Everything", accept.get(1));
        }

    @Test public void multipleHeaders_9_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, List<String>> headers = req.multiHeaders();
        assertEquals(accept, headers.get("Accept"));
        }

    @Test public void multipleHeaders_10_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, List<String>> headers = req.multiHeaders();
        // removed other assertion
        assertEquals("Bar", headers.get("Foo").get(0));
        }

    @Test public void multipleHeaders_11_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, List<String>> headers = req.multiHeaders();
        // removed other assertion
        // removed other assertion

        assertTrue(req.hasHeader("Accept"));
        }

    @Test public void multipleHeaders_12_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, List<String>> headers = req.multiHeaders();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(req.hasHeaderWithValue("accept", "Something"));
        }

    @Test public void multipleHeaders_13_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, List<String>> headers = req.multiHeaders();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(req.hasHeaderWithValue("accept", "Everything"));
        }

    @Test public void multipleHeaders_14_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, List<String>> headers = req.multiHeaders();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(req.hasHeaderWithValue("accept", "Something for nothing"));
        }

    @Test public void multipleHeaders_15_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, List<String>> headers = req.multiHeaders();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        req.removeHeader("accept");
        headers = req.multiHeaders();
        assertEquals("Bar", headers.get("Foo").get(0));
        }

    @Test public void multipleHeaders_16_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, List<String>> headers = req.multiHeaders();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        req.removeHeader("accept");
        headers = req.multiHeaders();
        // removed other assertion
        assertFalse(req.hasHeader("Accept"));
        }

    @Test public void multipleHeaders_17_oe() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("Accept", "Something");
        req.addHeader("Accept", "Everything");
        req.addHeader("Foo", "Bar");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<String> accept = req.headers("accept");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<String, List<String>> headers = req.multiHeaders();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        req.removeHeader("accept");
        headers = req.multiHeaders();
        // removed other assertion
        // removed other assertion
        assertNull(headers.get("Accept"));
        }

    @Test public void ignoresEmptySetCookies_1_oe() {
        // prep http response header map
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Set-Cookie", Collections.emptyList());
        HttpConnection.Response res = new HttpConnection.Response();
        res.processResponseHeaders(headers);
        assertEquals(0, res.cookies().size());
        }

    @Test public void ignoresEmptyCookieNameAndVals_1_oe() {
        // prep http response header map
        Map<String, List<String>> headers = new HashMap<>();
        List<String> cookieStrings = new ArrayList<>();
        cookieStrings.add(null);
        cookieStrings.add("");
        cookieStrings.add("one");
        cookieStrings.add("two=");
        cookieStrings.add("three=;");
        cookieStrings.add("four=data; Domain=.example.com; Path=/");

        headers.put("Set-Cookie", cookieStrings);
        HttpConnection.Response res = new HttpConnection.Response();
        res.processResponseHeaders(headers);
        assertEquals(4, res.cookies().size());
        }

    @Test public void ignoresEmptyCookieNameAndVals_2_oe() {
        // prep http response header map
        Map<String, List<String>> headers = new HashMap<>();
        List<String> cookieStrings = new ArrayList<>();
        cookieStrings.add(null);
        cookieStrings.add("");
        cookieStrings.add("one");
        cookieStrings.add("two=");
        cookieStrings.add("three=;");
        cookieStrings.add("four=data; Domain=.example.com; Path=/");

        headers.put("Set-Cookie", cookieStrings);
        HttpConnection.Response res = new HttpConnection.Response();
        res.processResponseHeaders(headers);
        // removed other assertion
        assertEquals("", res.cookie("one"));
        }

    @Test public void ignoresEmptyCookieNameAndVals_3_oe() {
        // prep http response header map
        Map<String, List<String>> headers = new HashMap<>();
        List<String> cookieStrings = new ArrayList<>();
        cookieStrings.add(null);
        cookieStrings.add("");
        cookieStrings.add("one");
        cookieStrings.add("two=");
        cookieStrings.add("three=;");
        cookieStrings.add("four=data; Domain=.example.com; Path=/");

        headers.put("Set-Cookie", cookieStrings);
        HttpConnection.Response res = new HttpConnection.Response();
        res.processResponseHeaders(headers);
        // removed other assertion
        // removed other assertion
        assertEquals("", res.cookie("two"));
        }

    @Test public void ignoresEmptyCookieNameAndVals_4_oe() {
        // prep http response header map
        Map<String, List<String>> headers = new HashMap<>();
        List<String> cookieStrings = new ArrayList<>();
        cookieStrings.add(null);
        cookieStrings.add("");
        cookieStrings.add("one");
        cookieStrings.add("two=");
        cookieStrings.add("three=;");
        cookieStrings.add("four=data; Domain=.example.com; Path=/");

        headers.put("Set-Cookie", cookieStrings);
        HttpConnection.Response res = new HttpConnection.Response();
        res.processResponseHeaders(headers);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", res.cookie("three"));
        }

    @Test public void ignoresEmptyCookieNameAndVals_5_oe() {
        // prep http response header map
        Map<String, List<String>> headers = new HashMap<>();
        List<String> cookieStrings = new ArrayList<>();
        cookieStrings.add(null);
        cookieStrings.add("");
        cookieStrings.add("one");
        cookieStrings.add("two=");
        cookieStrings.add("three=;");
        cookieStrings.add("four=data; Domain=.example.com; Path=/");

        headers.put("Set-Cookie", cookieStrings);
        HttpConnection.Response res = new HttpConnection.Response();
        res.processResponseHeaders(headers);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("data", res.cookie("four"));
        }

    @Test public void connectWithUrl_1_oe() throws MalformedURLException {
        Connection con = HttpConnection.connect(new URL("http://example.com"));
        assertEquals("http://example.com", con.request().url().toExternalForm());
        }

    @Test public void throwsOnMalformedUrl_1_oe() throws Exception {
        try {
    HttpConnection.connect("bzzt");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void userAgent_1_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        assertEquals(HttpConnection.DEFAULT_UA, con.request().header("User-Agent"));
        }

    @Test public void userAgent_2_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        // removed other assertion
        con.userAgent("Mozilla");
        assertEquals("Mozilla", con.request().header("User-Agent"));
        }

    @Test public void timeout_1_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        assertEquals(30 * 1000, con.request().timeout());
        }

    @Test public void timeout_2_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        // removed other assertion
        con.timeout(1000);
        assertEquals(1000, con.request().timeout());
        }

    @Test public void referrer_1_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        con.referrer("http://foo.com");
        assertEquals("http://foo.com", con.request().header("Referer"));
        }

    @Test public void method_1_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        assertEquals(Connection.Method.GET, con.request().method());
        }

    @Test public void method_2_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        // removed other assertion
        con.method(Connection.Method.POST);
        assertEquals(Connection.Method.POST, con.request().method());
        }

    @Test public void throwsOnOddData_1_oe() throws Exception {
        try {
     Connection con = HttpConnection.connect("http://example.com/"); con.data("Name", "val", "what");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void data_1_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        con.data("Name", "Val", "Foo", "bar");
        Collection<Connection.KeyVal> values = con.request().data();
        Object[] data =  values.toArray();
        Connection.KeyVal one = (Connection.KeyVal) data[0];
        Connection.KeyVal two = (Connection.KeyVal) data[1];
        assertEquals("Name", one.key());
        }

    @Test public void data_2_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        con.data("Name", "Val", "Foo", "bar");
        Collection<Connection.KeyVal> values = con.request().data();
        Object[] data =  values.toArray();
        Connection.KeyVal one = (Connection.KeyVal) data[0];
        Connection.KeyVal two = (Connection.KeyVal) data[1];
        // removed other assertion
        assertEquals("Val", one.value());
        }

    @Test public void data_3_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        con.data("Name", "Val", "Foo", "bar");
        Collection<Connection.KeyVal> values = con.request().data();
        Object[] data =  values.toArray();
        Connection.KeyVal one = (Connection.KeyVal) data[0];
        Connection.KeyVal two = (Connection.KeyVal) data[1];
        // removed other assertion
        // removed other assertion
        assertEquals("Foo", two.key());
        }

    @Test public void data_4_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        con.data("Name", "Val", "Foo", "bar");
        Collection<Connection.KeyVal> values = con.request().data();
        Object[] data =  values.toArray();
        Connection.KeyVal one = (Connection.KeyVal) data[0];
        Connection.KeyVal two = (Connection.KeyVal) data[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("bar", two.value());
        }

    @Test public void cookie_1_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        con.cookie("Name", "Val");
        assertEquals("Val", con.request().cookie("Name"));
        }

    @Test public void inputStream_1_oe() {
        Connection.KeyVal kv = HttpConnection.KeyVal.create("file", "thumb.jpg", ParseTest.inputStreamFrom("Check"));
        assertEquals("file", kv.key());
        }

    @Test public void inputStream_2_oe() {
        Connection.KeyVal kv = HttpConnection.KeyVal.create("file", "thumb.jpg", ParseTest.inputStreamFrom("Check"));
        // removed other assertion
        assertEquals("thumb.jpg", kv.value());
        }

    @Test public void inputStream_3_oe() {
        Connection.KeyVal kv = HttpConnection.KeyVal.create("file", "thumb.jpg", ParseTest.inputStreamFrom("Check"));
        // removed other assertion
        // removed other assertion
        assertTrue(kv.hasInputStream());
        }

    @Test public void inputStream_4_oe() {
        Connection.KeyVal kv = HttpConnection.KeyVal.create("file", "thumb.jpg", ParseTest.inputStreamFrom("Check"));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        kv = HttpConnection.KeyVal.create("one", "two");
        assertEquals("one", kv.key());
        }

    @Test public void inputStream_5_oe() {
        Connection.KeyVal kv = HttpConnection.KeyVal.create("file", "thumb.jpg", ParseTest.inputStreamFrom("Check"));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        kv = HttpConnection.KeyVal.create("one", "two");
        // removed other assertion
        assertEquals("two", kv.value());
        }

    @Test public void inputStream_6_oe() {
        Connection.KeyVal kv = HttpConnection.KeyVal.create("file", "thumb.jpg", ParseTest.inputStreamFrom("Check"));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        kv = HttpConnection.KeyVal.create("one", "two");
        // removed other assertion
        // removed other assertion
        assertFalse(kv.hasInputStream());
        }

    @Test public void requestBody_1_oe() {
        Connection con = HttpConnection.connect("http://example.com/");
        con.requestBody("foo");
        assertEquals("foo", con.request().requestBody());
        }

    @Test public void encodeUrl_1_oe() throws MalformedURLException {
        URL url1 = new URL("http://test.com/?q=white space");
        URL url2 = HttpConnection.encodeUrl(url1);
        assertEquals("http://test.com/?q=white%20space", url2.toExternalForm());
        }

    @Test public void noUrlThrowsValidationError_1_oe() throws IOException {
        HttpConnection con = new HttpConnection();
        boolean threw = false;
        try {
            con.execute();
        } catch (IllegalArgumentException e) {
            threw = true;
            assertEquals("URL not set. Make sure to call #url(...) before executing the request.", e.getMessage());
        }
        }

    @Test public void noUrlThrowsValidationError_2_oe() throws IOException {
        HttpConnection con = new HttpConnection();
        boolean threw = false;
        try {
            con.execute();
        } catch (IllegalArgumentException e) {
            threw = true;
            // removed other assertion
        }
        assertTrue(threw);
        }

    @Test public void supportsInternationalDomainNames_1_oe() throws MalformedURLException {
        String idn = "https://www.测试.测试/foo.html?bar";
        String puny = "https://www.xn--0zwm56d.xn--0zwm56d/foo.html?bar";

        Connection con = Jsoup.connect(idn);
        assertEquals(puny, con.request().url().toExternalForm());
        }

    @Test public void supportsInternationalDomainNames_2_oe() throws MalformedURLException {
        String idn = "https://www.测试.测试/foo.html?bar";
        String puny = "https://www.xn--0zwm56d.xn--0zwm56d/foo.html?bar";

        Connection con = Jsoup.connect(idn);
        // removed other assertion

        HttpConnection.Request req = new HttpConnection.Request();
        req.url(new URL(idn));
        assertEquals(puny, req.url().toExternalForm());
        }

    @Test public void validationErrorsOnExecute_1_oe() throws IOException {
        Connection con = new HttpConnection();
        boolean urlThrew = false;
        try {
            con.execute();
        } catch (IllegalArgumentException e) {
            urlThrew = e.getMessage().contains("URL");
        }
        assertTrue(urlThrew);
        }

    @Test void testMalformedException_1_oe() {
        boolean threw = false;
        try {
            Jsoup.connect("jsoup.org/test");
        } catch (IllegalArgumentException e) {
            threw = true;
            assertEquals("The supplied URL, 'jsoup.org/test', is malformed. Make sure it is an absolute URL, and starts with 'http://' or 'https://'. See https://jsoup.org/cookbook/extracting-data/working-with-urls", e.getMessage());
        }
        }

    @Test void testMalformedException_2_oe() {
        boolean threw = false;
        try {
            Jsoup.connect("jsoup.org/test");
        } catch (IllegalArgumentException e) {
            threw = true;
            // removed other assertion
        }
        assertTrue(threw);
        }

@MultiLocaleTest
    public void caseInsensitiveHeaders_1_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        assertTrue(res.hasHeader("Accept-Encoding"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_2_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        assertTrue(res.hasHeader("accept-encoding"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_3_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        // removed other assertion
        assertTrue(res.hasHeader("accept-Encoding"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_4_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(res.hasHeader("ACCEPT-ENCODING"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_5_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("gzip", res.header("accept-Encoding"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_6_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("gzip", res.header("ACCEPT-ENCODING"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_7_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("text/html", res.header("Content-Type"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_8_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("http://example.com", res.header("Referrer"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_9_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        res.removeHeader("Content-Type");
        assertFalse(res.hasHeader("content-type"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_10_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        res.removeHeader("Content-Type");
        // removed other assertion

        res.removeHeader("ACCEPT-ENCODING");
        assertFalse(res.hasHeader("Accept-Encoding"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_11_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        res.removeHeader("Content-Type");
        // removed other assertion

        res.removeHeader("ACCEPT-ENCODING");
        // removed other assertion

        res.header("ACCEPT-ENCODING", "deflate");
        assertEquals("deflate", res.header("Accept-Encoding"));
    }

@MultiLocaleTest
    public void caseInsensitiveHeaders_12_oe(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        res.removeHeader("Content-Type");
        // removed other assertion

        res.removeHeader("ACCEPT-ENCODING");
        // removed other assertion

        res.header("ACCEPT-ENCODING", "deflate");
        // removed other assertion
        assertEquals("deflate", res.header("accept-Encoding"));
    }

}
