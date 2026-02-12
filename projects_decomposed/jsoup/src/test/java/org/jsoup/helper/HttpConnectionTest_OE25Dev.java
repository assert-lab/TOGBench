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

    @MultiLocaleTest
    public void caseInsensitiveHeaders(Locale locale) {
        Locale.setDefault(locale);

        Connection.Response res = new HttpConnection.Response();
        res.header("Accept-Encoding", "gzip");
        res.header("content-type", "text/html");
        res.header("refErrer", "http://example.com");

        assertTrue(res.hasHeader("Accept-Encoding"));
        assertTrue(res.hasHeader("accept-encoding"));
        assertTrue(res.hasHeader("accept-Encoding"));
        assertTrue(res.hasHeader("ACCEPT-ENCODING"));

        assertEquals("gzip", res.header("accept-Encoding"));
        assertEquals("gzip", res.header("ACCEPT-ENCODING"));
        assertEquals("text/html", res.header("Content-Type"));
        assertEquals("http://example.com", res.header("Referrer"));

        res.removeHeader("Content-Type");
        assertFalse(res.hasHeader("content-type"));

        res.removeHeader("ACCEPT-ENCODING");
        assertFalse(res.hasHeader("Accept-Encoding"));

        res.header("ACCEPT-ENCODING", "deflate");
        assertEquals("deflate", res.header("Accept-Encoding"));
        assertEquals("deflate", res.header("accept-Encoding"));
    }

    @Test public void handlesHeaderEncodingOnRequest() {
        Connection.Request req = new HttpConnection.Request();
        req.addHeader("xxx", "é");
    }

    @Test public void canCreateEmptyConnection_2_oe() throws Exception {
        HttpConnection con = new HttpConnection();
        // removed other assertion
        try {
     URL url = con.request().url();
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void throwsExceptionOnResponseWithoutExecute_1_oe() throws Exception {
        try {
     Connection con = HttpConnection.connect("http://example.com"); con.response();
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void throwsExceptionOnParseWithoutExecute_1_oe() throws Exception {
        try {
     Connection con = HttpConnection.connect("http://example.com"); con.response().parse();
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void throwsExceptionOnBodyWithoutExecute_1_oe() throws Exception {
        try {
     Connection con = HttpConnection.connect("http://example.com"); con.response().body();
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void throwsExceptionOnBodyAsBytesWithoutExecute_1_oe() throws Exception {
        try {
     Connection con = HttpConnection.connect("http://example.com"); con.response().bodyAsBytes();
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void throwsOnMalformedUrl_1_oe() throws Exception {
        try {
    HttpConnection.connect("bzzt");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

    @Test public void throwsOnOddData_1_oe() throws Exception {
        try {
     Connection con = HttpConnection.connect("http://example.com/"); con.data("Name", "val", "what");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
        }

}
