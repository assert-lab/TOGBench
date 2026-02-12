package org.jsoup.integration;

import org.jsoup.Jsoup;
import org.jsoup.helper.DataUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseErrorList;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.zip.GZIPInputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test: parses from real-world example HTML.
 *
 * @author Jonathan Hedley, jonathan@hedley.net
 */
public class ParseTest_OE25Dev {


    public static File getFile(String resourceName) {
        try {
            URL resource = ParseTest_OE25Dev.class.getResource(resourceName);
            return resource != null ? new File(resource.toURI()) : new File("/404");
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    public static InputStream inputStreamFrom(String s) {
        return new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
    }

    public static String getFileAsString(File file) throws IOException {
        byte[] bytes;
        if (file.getName().endsWith(".gz")) {
            InputStream stream = new GZIPInputStream(new FileInputStream(file));
            ByteBuffer byteBuffer = DataUtil.readToByteBuffer(stream, 0);
            bytes = byteBuffer.array();
        } else {
            bytes = Files.readAllBytes(file.toPath());
        }
        return new String(bytes);
    }


}
