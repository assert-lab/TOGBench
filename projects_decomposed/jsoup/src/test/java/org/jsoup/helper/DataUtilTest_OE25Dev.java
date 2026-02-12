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


}
