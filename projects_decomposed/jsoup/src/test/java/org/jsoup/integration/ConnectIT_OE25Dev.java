package org.jsoup.integration;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.integration.servlets.FileServlet;
import org.jsoup.integration.servlets.SlowRider;
import org.jsoup.internal.ConstrainableInputStream;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Failsafe integration tests for Connect methods. These take a bit longer to run, so included as Integ, not Unit, tests.
 */
public class ConnectIT_OE25Dev {
    // Slow Rider tests.

    @Test
    public void remainingAfterFirstRead() throws IOException {
        int bufferSize = 5 * 1024;
        int capSize = 100 * 1024;

        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        ConstrainableInputStream stream;
        try (BufferedInputStream inputStream = Jsoup.connect(url).maxBodySize(capSize)
            .execute().bodyStream()) {

            assertTrue(inputStream instanceof ConstrainableInputStream);
            stream = (ConstrainableInputStream) inputStream;

            // simulates parse which does a limited read first
            stream.mark(bufferSize);
            ByteBuffer firstBytes = stream.readToByteBuffer(bufferSize);

            byte[] array = firstBytes.array();
            String firstText = new String(array, StandardCharsets.UTF_8);
            assertTrue(firstText.startsWith("<html><head><title>Large"));
            assertEquals(bufferSize, array.length);

            boolean fullyRead = stream.read() == -1;
            assertFalse(fullyRead);

            // reset and read again
            stream.reset();
            ByteBuffer fullRead = stream.readToByteBuffer(0);
            byte[] fullArray = fullRead.array();
            assertEquals(capSize, fullArray.length);
            String fullText = new String(fullArray, StandardCharsets.UTF_8);
            assertTrue(fullText.startsWith(firstText));
        }
    }

    @Test
    public void noLimitAfterFirstRead() throws IOException {
        int bufferSize = 5 * 1024;

        String url = FileServlet.urlTo("/htmltests/large.html"); // 280 K
        ConstrainableInputStream stream;
        try (BufferedInputStream inputStream = Jsoup.connect(url).execute().bodyStream()) {
            assertTrue(inputStream instanceof ConstrainableInputStream);
            stream = (ConstrainableInputStream) inputStream;

            // simulates parse which does a limited read first
            stream.mark(bufferSize);
            ByteBuffer firstBytes = stream.readToByteBuffer(bufferSize);
            byte[] array = firstBytes.array();
            String firstText = new String(array, StandardCharsets.UTF_8);
            assertTrue(firstText.startsWith("<html><head><title>Large"));
            assertEquals(bufferSize, array.length);

            // reset and read fully
            stream.reset();
            ByteBuffer fullRead = stream.readToByteBuffer(0);
            byte[] fullArray = fullRead.array();
            assertEquals(280735, fullArray.length);
            String fullText = new String(fullArray, StandardCharsets.UTF_8);
            assertTrue(fullText.startsWith(firstText));
        }
    }


}
