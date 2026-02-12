package org.jsoup.integration;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.integration.servlets.*;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.jsoup.helper.HttpConnection.CONTENT_TYPE;
import static org.jsoup.helper.HttpConnection.MULTIPART_FORM_DATA;
import static org.jsoup.integration.UrlConnectTest.browserUa;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Jsoup.connect against a local server.
 */
public class ConnectTest_OE25Dev {
    private static String echoUrl;

    @BeforeAll
    public static void setUp() {
        TestServer.start();
        echoUrl = EchoServlet.Url;
    }

    private static String ihVal(String key, Document doc) {
        final Element first = doc.select("th:contains(" + key + ") + td").first();
        return first != null ? first.text() : null;
    }

    @Test
    public void throwsExceptionOn404() {
        String url = EchoServlet.Url;
        Connection con = Jsoup.connect(url).header(EchoServlet.CodeParam, "404");

        boolean threw = false;
        try {
            Document doc = con.get();
        } catch (HttpStatusException e) {
            threw = true;
            assertEquals("org.jsoup.HttpStatusException: HTTP error fetching URL. Status=404, URL=[" + e.getUrl() + "]", e.toString());
            assertTrue(e.getUrl().startsWith(url));
            assertEquals(404, e.getStatusCode());
        } catch (IOException e) {
        }
        assertTrue(threw);
    }

    /**
     * Tests upload of content to a remote service.
     */

    @Test
    public void parseParseThrowsValidates() {
        assertThrows(IllegalArgumentException.class, () -> {
            Connection.Response res = Jsoup.connect(echoUrl).execute();
            Document doc = res.parse();
            assertTrue(doc.title().contains("Environment"));
            Document doc2 = res.parse(); // should blow up because the response input stream has been drained
        });
    }

    @Test
    public void testBinaryContentTypeThrowsException() {
        Connection con = Jsoup.connect(FileServlet.urlTo("/htmltests/thumb.jpg"));
        con.data(FileServlet.ContentTypeParam, "image/jpeg");

        boolean threw = false;
        try {
            con.execute();
            Document doc = con.response().parse();
        } catch (IOException e) {
            threw = true;
            assertEquals("Unhandled content type. Must be text/*, application/xml, or application/*+xml", e.getMessage());
        }
        assertTrue(threw);
    }

    @Test public void testParseRss() throws IOException {
        // test that we switch automatically to xml, and we support application/rss+xml
        Connection con = Jsoup.connect(FileServlet.urlTo("/htmltests/test-rss.xml"));
        con.data(FileServlet.ContentTypeParam, "application/rss+xml");
        Document doc = con.get();
        Element title = doc.selectFirst("title");
        assertNotNull(title);
        assertEquals("jsoup RSS news", title.text());
        assertEquals("channel", title.parent().nodeName());
        assertEquals("",doc.title());// the document title is unset,this tag is channel>title,not html>head>title assertEquals(3,doc.select("link").size());
        assertEquals("application/rss+xml", con.response().contentType());
        assertTrue(doc.parser().getTreeBuilder() instanceof XmlTreeBuilder);
        assertEquals(Document.OutputSettings.Syntax.xml, doc.outputSettings().syntax());
    }

    @Test
    public void canFetchBinaryAsBytes() throws IOException {
        Connection.Response res = Jsoup.connect(FileServlet.urlTo("/htmltests/thumb.jpg"))
            .data(FileServlet.ContentTypeParam, "image/jpeg")
            .ignoreContentType(true)
            .execute();

        byte[] bytes = res.bodyAsBytes();
        assertEquals(1052, bytes.length);
    }

    @Test
    public void handlesUnknownEscapesAcrossBuffer() throws IOException {
        String localPath = "/htmltests/escapes-across-buffer.html";
        String localUrl = FileServlet.urlTo(localPath);

        Document docFromLocalServer = Jsoup.connect(localUrl).get();
        Document docFromFileRead = Jsoup.parse(ParseTest.getFile(localPath), "UTF-8");

        String text = docFromLocalServer.body().text();
        assertEquals(14766, text.length());
        assertEquals(text, docFromLocalServer.body().text());
        assertEquals(text, docFromFileRead.body().text());
    }

    /**
     * Test fetching a form, and submitting it with a file attached.
     */
    @Test
    public void postHtmlFile() throws IOException {
        Document index = Jsoup.connect(FileServlet.urlTo("/htmltests/upload-form.html")).get();
        List<FormElement> forms = index.select("[name=tidy]").forms();
        assertEquals(1, forms.size());
        FormElement form = forms.get(0);
        Connection post = form.submit();

        File uploadFile = ParseTest.getFile("/htmltests/google-ipod.html.gz");
        FileInputStream stream = new FileInputStream(uploadFile);

        Connection.KeyVal fileData = post.data("_file");
        assertNotNull(fileData);
        fileData.value("check.html");
        fileData.inputStream(stream);

        Connection.Response res;
        try {
            res = post.execute();
        } finally {
            stream.close();
        }

        Document doc = res.parse();
        assertEquals(ihVal("Method",doc),"POST");// from form action assertEquals(ihVal("Part _file Filename",doc),"check.html");
        assertEquals(ihVal("Part _file Name", doc), "_file");
        assertEquals(ihVal("_function", doc), "tidy");
    }

    @Test
    public void fetchHandlesXml() throws IOException {
        String[] types = {"text/xml", "application/xml", "application/rss+xml", "application/xhtml+xml"};
        for (String type : types) {
            fetchHandlesXml(type);
        }
    }

    void fetchHandlesXml(String contentType) throws IOException {
        // should auto-detect xml and use XML parser, unless explicitly requested the html parser
        String xmlUrl = FileServlet.urlTo("/htmltests/xml-test.xml");
        Connection con = Jsoup.connect(xmlUrl);
        con.data(FileServlet.ContentTypeParam, contentType);
        Document doc = con.get();
        Connection.Request req = con.request();
        assertTrue(req.parser().getTreeBuilder() instanceof XmlTreeBuilder);
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>\n", doc.outerHtml());
        assertEquals(con.response().contentType(), contentType);
    }

    @Test
    public void fetchHandlesXmlAsHtmlWhenParserSet() throws IOException {
        // should auto-detect xml and use XML parser, unless explicitly requested the html parser
        String xmlUrl = FileServlet.urlTo("/htmltests/xml-test.xml");
        Connection con = Jsoup.connect(xmlUrl).parser(Parser.htmlParser());
        con.data(FileServlet.ContentTypeParam, "application/xml");
        Document doc = con.get();
        Connection.Request req = con.request();
        assertTrue(req.parser().getTreeBuilder() instanceof HtmlTreeBuilder);
        assertEquals("<html> <head></head> <body> <doc> <val> One <val> Two </val>Three </val> </doc> </body> </html>", StringUtil.normaliseWhitespace(doc.outerHtml()));
    }

    @Test
    public void combinesSameHeadersWithComma() throws IOException {
        // http://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.2
        Connection con = Jsoup.connect(echoUrl);
        con.get();

        Connection.Response res = con.response();
        assertEquals("text/html;charset=utf-8", res.header("Content-Type"));
        assertEquals("no-cache, no-store", res.header("Cache-Control"));

        List<String> header = res.headers("Cache-Control");
        assertEquals(2, header.size());
        assertEquals("no-cache", header.get(0));
        assertEquals("no-store", header.get(1));
    }

    @Test
    public void sendHeadRequest() throws IOException {
        String url = FileServlet.urlTo("/htmltests/xml-test.xml");
        Connection con = Jsoup.connect(url)
            .method(Connection.Method.HEAD)
            .data(FileServlet.ContentTypeParam, "text/xml");
        final Connection.Response response = con.execute();
        assertEquals("text/xml", response.header("Content-Type"));
        assertEquals("",response.body());// head ought to have no body 
        Document doc = response.parse();
        assertEquals("", doc.text());
    }

    @Test
    public void fetchToW3c() throws IOException {
        String url = FileServlet.urlTo("/htmltests/upload-form.html");
        Document doc = Jsoup.connect(url).get();

        W3CDom dom = new W3CDom();
        org.w3c.dom.Document wDoc = dom.fromJsoup(doc);
        assertEquals(url, wDoc.getDocumentURI());
        String html = dom.asString(wDoc);
        assertTrue(html.contains("Upload"));
    }

    @Test
    public void baseHrefCorrectAfterHttpEquiv() throws IOException {
        // https://github.com/jhy/jsoup/issues/440
        Connection.Response res = Jsoup.connect(FileServlet.urlTo("/htmltests/charset-base.html")).execute();
        Document doc = res.parse();
        assertEquals("http://example.com/foo.jpg", doc.select("img").first().absUrl("src"));
    }

    @Test
    public void maxBodySize() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K

        Connection.Response defaultRes = Jsoup.connect(url).execute();
        Connection.Response smallRes = Jsoup.connect(url).maxBodySize(50 * 1024).execute(); // crops
        Connection.Response mediumRes = Jsoup.connect(url).maxBodySize(200 * 1024).execute(); // crops
        Connection.Response largeRes = Jsoup.connect(url).maxBodySize(300 * 1024).execute(); // does not crop
        Connection.Response unlimitedRes = Jsoup.connect(url).maxBodySize(0).execute();

        int actualDocText = 269535;
        assertEquals(actualDocText, defaultRes.parse().text().length());
        assertEquals(49165, smallRes.parse().text().length());
        assertEquals(196577, mediumRes.parse().text().length());
        assertEquals(actualDocText, largeRes.parse().text().length());
        assertEquals(actualDocText, unlimitedRes.parse().text().length());
    }

    @Test public void repeatable() throws IOException {
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        Connection con = Jsoup.connect(url).parser(Parser.xmlParser());
        Document doc1 = con.get();
        Document doc2 = con.get();
        assertEquals("Large HTML", doc1.title());
        assertEquals("Large HTML", doc2.title());
    }

    @Test
    public void maxBodySizeInReadToByteBuffer() throws IOException {
        // https://github.com/jhy/jsoup/issues/1774
        // when calling readToByteBuffer, contents were not buffered up
        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K

        Connection.Response defaultRes = Jsoup.connect(url).execute();
        Connection.Response smallRes = Jsoup.connect(url).maxBodySize(50 * 1024).execute(); // crops
        Connection.Response mediumRes = Jsoup.connect(url).maxBodySize(200 * 1024).execute(); // crops
        Connection.Response largeRes = Jsoup.connect(url).maxBodySize(300 * 1024).execute(); // does not crop
        Connection.Response unlimitedRes = Jsoup.connect(url).maxBodySize(0).execute();

        int actualDocText = 280735;
        assertEquals(actualDocText, defaultRes.body().length());
        assertEquals(50 * 1024, smallRes.body().length());
        assertEquals(200 * 1024, mediumRes.body().length());
        assertEquals(actualDocText, largeRes.body().length());
        assertEquals(actualDocText, unlimitedRes.body().length());
    }

    @Test
    public void bodyAfterParseThrowsValidationError_1_oe() throws Exception {
        try {
     Connection.Response res = Jsoup.connect(echoUrl).execute(); Document doc = res.parse(); String body = res.body();
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
