package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.integration.ParseTest;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.jsoup.integration.ParseTest.getFile;
import static org.junit.jupiter.api.Assertions.*;

public class DataUtilTest_OE25Dev {

    private InputStream stream(String data) {
        return new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
    }

    private InputStream stream(String data, String charset) {
        return new ByteArrayInputStream(data.getBytes(Charset.forName(charset)));
    }

    // an input stream to give a range of output sizes, that changes on each read
    static class VaryingReadInputStream extends InputStream {
        final InputStream in;
        int stride = 0;

        VaryingReadInputStream(InputStream in) {
            this.in = in;
        }

        public int read() throws IOException {
            return in.read();
        }

        public int read(byte[] b) throws IOException {
            return in.read(b, 0, Math.min(b.length, ++stride));
        }

        public int read(byte[] b, int off, int len) throws IOException {
            return in.read(b, off, Math.min(len, ++stride));
        }
    }

@Test
    public void testCharset_1_oe() {
        assertEquals("utf-8", DataUtil.getCharsetFromContentType("text/html;charset=utf-8 "));
    }

@Test
    public void testCharset_2_oe() {
        // removed other assertion
        assertEquals("UTF-8", DataUtil.getCharsetFromContentType("text/html; charset=UTF-8"));
    }

@Test
    public void testCharset_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("ISO-8859-1", DataUtil.getCharsetFromContentType("text/html; charset=ISO-8859-1"));
    }

@Test
    public void testCharset_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(DataUtil.getCharsetFromContentType("text/html"));
    }

@Test
    public void testCharset_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(DataUtil.getCharsetFromContentType(null));
    }

@Test
    public void testCharset_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(DataUtil.getCharsetFromContentType("text/html;charset=Unknown"));
    }

@Test
    public void testQuotedCharset_1_oe() {
        assertEquals("utf-8", DataUtil.getCharsetFromContentType("text/html; charset=\"utf-8\""));
    }

@Test
    public void testQuotedCharset_2_oe() {
        // removed other assertion
        assertEquals("UTF-8", DataUtil.getCharsetFromContentType("text/html;charset=\"UTF-8\""));
    }

@Test
    public void testQuotedCharset_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("ISO-8859-1", DataUtil.getCharsetFromContentType("text/html; charset=\"ISO-8859-1\""));
    }

@Test
    public void testQuotedCharset_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(DataUtil.getCharsetFromContentType("text/html; charset=\"Unsupported\""));
    }

@Test
    public void testQuotedCharset_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("UTF-8", DataUtil.getCharsetFromContentType("text/html; charset='UTF-8'"));
    }

@Test
    public void discardsSpuriousByteOrderMark_1_oe() throws IOException {
        String html = "\uFEFF<html><head><title>One</title></head><body>Two</body></html>";
        Document doc = DataUtil.parseInputStream(stream(html), "UTF-8", "http://foo.com/", Parser.htmlParser());
        assertEquals("One", doc.head().text());
    }

@Test
    public void discardsSpuriousByteOrderMarkWhenNoCharsetSet_1_oe() throws IOException {
        String html = "\uFEFF<html><head><title>One</title></head><body>Two</body></html>";
        Document doc = DataUtil.parseInputStream(stream(html), null, "http://foo.com/", Parser.htmlParser());
        assertEquals("One", doc.head().text());
    }

@Test
    public void discardsSpuriousByteOrderMarkWhenNoCharsetSet_2_oe() throws IOException {
        String html = "\uFEFF<html><head><title>One</title></head><body>Two</body></html>";
        Document doc = DataUtil.parseInputStream(stream(html), null, "http://foo.com/", Parser.htmlParser());
        // removed other assertion
        assertEquals("UTF-8", doc.outputSettings().charset().displayName());
    }

@Test
    public void shouldNotThrowExceptionOnEmptyCharset_1_oe() {
        assertNull(DataUtil.getCharsetFromContentType("text/html; charset="));
    }

@Test
    public void shouldNotThrowExceptionOnEmptyCharset_2_oe() {
        // removed other assertion
        assertNull(DataUtil.getCharsetFromContentType("text/html; charset=;"));
    }

@Test
    public void shouldSelectFirstCharsetOnWeirdMultileCharsetsInMetaTags_1_oe() {
        assertEquals("ISO-8859-1", DataUtil.getCharsetFromContentType("text/html; charset=ISO-8859-1, charset=1251"));
    }

@Test
    public void shouldCorrectCharsetForDuplicateCharsetString_1_oe() {
        assertEquals("iso-8859-1", DataUtil.getCharsetFromContentType("text/html; charset=charset=iso-8859-1"));
    }

@Test
    public void shouldReturnNullForIllegalCharsetNames_1_oe() {
        assertNull(DataUtil.getCharsetFromContentType("text/html; charset=$HJKDF§$/("));
    }

@Test
    public void generatesMimeBoundaries_1_oe() {
        String m1 = DataUtil.mimeBoundary();
        String m2 = DataUtil.mimeBoundary();

        assertEquals(DataUtil.boundaryLength, m1.length());
    }

@Test
    public void generatesMimeBoundaries_2_oe() {
        String m1 = DataUtil.mimeBoundary();
        String m2 = DataUtil.mimeBoundary();

        // removed other assertion
        assertEquals(DataUtil.boundaryLength, m2.length());
    }

@Test
    public void generatesMimeBoundaries_3_oe() {
        String m1 = DataUtil.mimeBoundary();
        String m2 = DataUtil.mimeBoundary();

        // removed other assertion
        // removed other assertion
        assertNotSame(m1, m2);
    }

@Test
    public void wrongMetaCharsetFallback_1_oe() throws IOException {
        String html = "<html><head><meta charset=iso-8></head><body></body></html>";

        Document doc = DataUtil.parseInputStream(stream(html), null, "http://example.com", Parser.htmlParser());

        final String expected = "<html>\n" +
                " <head>\n" +
                "  <meta charset=\"iso-8\">\n" +
                " </head>\n" +
                " <body></body>\n" +
                "</html>";

        assertEquals(expected, doc.toString());
    }

@Test
    public void secondMetaElementWithContentTypeContainsCharsetParameter_1_oe() throws Exception {
        String html = "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html\">" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=euc-kr\">" +
                "</head><body>한국어</body></html>";

        Document doc = DataUtil.parseInputStream(stream(html, "euc-kr"), null, "http://example.com", Parser.htmlParser());

        assertEquals("한국어", doc.body().text());
    }

@Test
    public void firstMetaElementWithCharsetShouldBeUsedForDecoding_1_oe() throws Exception {
        String html = "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=koi8-u\">" +
                "</head><body>Übergrößenträger</body></html>";

        Document doc = DataUtil.parseInputStream(stream(html, "iso-8859-1"), null, "http://example.com", Parser.htmlParser());

        assertEquals("Übergrößenträger", doc.body().text());
    }

@Test
    public void parseSequenceInputStream_1_oe() throws IOException {
        // https://github.com/jhy/jsoup/pull/1671
        File in = getFile("/htmltests/medium.html");
        String fileContent = new String(Files.readAllBytes(in.toPath()));
        int halfLength = fileContent.length() / 2;
        String firstPart = fileContent.substring(0, halfLength);
        String secondPart = fileContent.substring(halfLength);
        SequenceInputStream sequenceStream = new SequenceInputStream(
                stream(firstPart),
                stream(secondPart)
        );
        Document doc = DataUtil.parseInputStream(sequenceStream, null, "", Parser.htmlParser());
        assertEquals(fileContent, doc.outerHtml());
    }

@Test
    public void supportsBOMinFiles_1_oe() throws IOException {
        // test files from http://www.i18nl10n.com/korean/utftest/
        File in = getFile("/bomtests/bom_utf16be.html");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        assertTrue(doc.title().contains("UTF-16BE"));
    }

@Test
    public void supportsBOMinFiles_2_oe() throws IOException {
        // test files from http://www.i18nl10n.com/korean/utftest/
        File in = getFile("/bomtests/bom_utf16be.html");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        assertTrue(doc.text().contains("가각갂갃간갅"));
    }

@Test
    public void supportsBOMinFiles_3_oe() throws IOException {
        // test files from http://www.i18nl10n.com/korean/utftest/
        File in = getFile("/bomtests/bom_utf16be.html");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf16le.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        assertTrue(doc.title().contains("UTF-16LE"));
    }

@Test
    public void supportsBOMinFiles_4_oe() throws IOException {
        // test files from http://www.i18nl10n.com/korean/utftest/
        File in = getFile("/bomtests/bom_utf16be.html");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf16le.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        assertTrue(doc.text().contains("가각갂갃간갅"));
    }

@Test
    public void supportsBOMinFiles_5_oe() throws IOException {
        // test files from http://www.i18nl10n.com/korean/utftest/
        File in = getFile("/bomtests/bom_utf16be.html");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf16le.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf32be.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        assertTrue(doc.title().contains("UTF-32BE"));
    }

@Test
    public void supportsBOMinFiles_6_oe() throws IOException {
        // test files from http://www.i18nl10n.com/korean/utftest/
        File in = getFile("/bomtests/bom_utf16be.html");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf16le.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf32be.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        assertTrue(doc.text().contains("가각갂갃간갅"));
    }

@Test
    public void supportsBOMinFiles_7_oe() throws IOException {
        // test files from http://www.i18nl10n.com/korean/utftest/
        File in = getFile("/bomtests/bom_utf16be.html");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf16le.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf32be.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf32le.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        assertTrue(doc.title().contains("UTF-32LE"));
    }

@Test
    public void supportsBOMinFiles_8_oe() throws IOException {
        // test files from http://www.i18nl10n.com/korean/utftest/
        File in = getFile("/bomtests/bom_utf16be.html");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf16le.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf32be.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        // removed other assertion

        in = getFile("/bomtests/bom_utf32le.html");
        doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        assertTrue(doc.text().contains("가각갂갃간갅"));
    }

@Test
    public void supportsUTF8BOM_1_oe() throws IOException {
        File in = getFile("/bomtests/bom_utf8.html");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        assertEquals("OK", doc.head().select("title").text());
    }

@Test
    public void noExtraNULLBytes_1_oe() throws IOException {
    	final byte[] b = "<html><head><meta charset=\"UTF-8\"></head><body><div><u>ü</u>ü</div></body></html>".getBytes(StandardCharsets.UTF_8);
    	
    	Document doc = Jsoup.parse(new ByteArrayInputStream(b), null, "");
    	assertFalse( doc.outerHtml().contains("\u0000") );
    }

@Test
    public void supportsZippedUTF8BOM_1_oe() throws IOException {
        File in = getFile("/bomtests/bom_utf8.html.gz");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        assertEquals("OK", doc.head().select("title").text());
    }

@Test
    public void supportsZippedUTF8BOM_2_oe() throws IOException {
        File in = getFile("/bomtests/bom_utf8.html.gz");
        Document doc = Jsoup.parse(in, null, "http://example.com");
        // removed other assertion
        assertEquals("There is a UTF8 BOM at the top (before the XML decl). If not read correctly, will look like a non-joining space.", doc.body().text());
    }

@Test
    public void supportsXmlCharsetDeclaration_1_oe() throws IOException {
        String encoding = "iso-8859-1";
        InputStream soup = new ByteArrayInputStream((
                "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>" +
                        "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" +
                        "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">Hellö Wörld!</html>"
        ).getBytes(Charset.forName(encoding)));

        Document doc = Jsoup.parse(soup, null, "");
        assertEquals("Hellö Wörld!", doc.body().text());
    }

@Test
    public void lLoadsGzipFile_1_oe() throws IOException {
        File in = getFile("/htmltests/gzip.html.gz");
        Document doc = Jsoup.parse(in, null);
        assertEquals("Gzip test", doc.title());
    }

@Test
    public void lLoadsGzipFile_2_oe() throws IOException {
        File in = getFile("/htmltests/gzip.html.gz");
        Document doc = Jsoup.parse(in, null);
        // removed other assertion
        assertEquals("This is a gzipped HTML file.", doc.selectFirst("p").text());
    }

@Test
    public void loadsZGzipFile_1_oe() throws IOException {
        // compressed on win, with z suffix
        File in = getFile("/htmltests/gzip.html.z");
        Document doc = Jsoup.parse(in, null);
        assertEquals("Gzip test", doc.title());
    }

@Test
    public void loadsZGzipFile_2_oe() throws IOException {
        // compressed on win, with z suffix
        File in = getFile("/htmltests/gzip.html.z");
        Document doc = Jsoup.parse(in, null);
        // removed other assertion
        assertEquals("This is a gzipped HTML file.", doc.selectFirst("p").text());
    }

@Test
    public void handlesFakeGzipFile_1_oe() throws IOException {
        File in = getFile("/htmltests/fake-gzip.html.gz");
        Document doc = Jsoup.parse(in, null);
        assertEquals("This is not gzipped", doc.title());
    }

@Test
    public void handlesFakeGzipFile_2_oe() throws IOException {
        File in = getFile("/htmltests/fake-gzip.html.gz");
        Document doc = Jsoup.parse(in, null);
        // removed other assertion
        assertEquals("And should still be readable.", doc.selectFirst("p").text());
    }

@Test
    void handlesChunkedInputStream_1_oe() throws IOException {
        File inputFile = ParseTest.getFile("/htmltests/large.html");
        String input = ParseTest.getFileAsString(inputFile);
        VaryingReadInputStream stream = new VaryingReadInputStream(ParseTest.inputStreamFrom(input));

        Document expected = Jsoup.parse(input, "https://example.com");
        Document doc = Jsoup.parse(stream, null, "https://example.com");
        assertTrue(doc.hasSameValue(expected));
    }

@Test
    void handlesUnlimitedRead_1_oe() throws IOException {
        File inputFile = ParseTest.getFile("/htmltests/large.html");
        String input = ParseTest.getFileAsString(inputFile);
        VaryingReadInputStream stream = new VaryingReadInputStream(ParseTest.inputStreamFrom(input));

        ByteBuffer byteBuffer = DataUtil.readToByteBuffer(stream, 0);
        String read = new String(byteBuffer.array());

        assertEquals(input, read);
    }

}
