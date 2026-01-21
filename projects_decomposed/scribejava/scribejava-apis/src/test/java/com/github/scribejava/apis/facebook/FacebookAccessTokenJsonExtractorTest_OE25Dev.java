package com.github.scribejava.apis.facebook;

import com.github.scribejava.core.model.Response;
import java.io.IOException;
import java.util.Collections;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class FacebookAccessTokenJsonExtractorTest_OE25Dev {

    private final FacebookAccessTokenJsonExtractor extractor = FacebookAccessTokenJsonExtractor.instance();

    private static Response error(String body) {
        return new Response(400, /* message */ null, /* headers */ Collections.<String, String>emptyMap(), body);
    }

    @Test
    public void shouldThrowExceptionIfResponseIsError_1_oe() throws IOException {
        final String body = "{\"error\":"
                + "{\"message\":\"This authorization code has been used.\","
                + "\"type\":\"OAuthException\","
                + "\"code\":100,"
                + "\"fbtrace_id\":\"DtxvtGRaxbB\"}}";
        try (Response response = error(body)) {

            final FacebookAccessTokenErrorResponse fateR = assertThrows(FacebookAccessTokenErrorResponse.class, new ThrowingRunnable() { @Override public void run() throws Throwable { extractor.extract(response); } });
    }
    }

    @Test
    public void shouldThrowExceptionIfResponseIsError_2_oe() throws IOException {
        final String body = "{\"error\":"
                + "{\"message\":\"This authorization code has been used.\","
                + "\"type\":\"OAuthException\","
                + "\"code\":100,"
                + "\"fbtrace_id\":\"DtxvtGRaxbB\"}}";
        try (Response response = error(body)) {

            // removed other assertion

            assertEquals("This authorization code has been used.", fateR.getErrorMessage());
    }
    }

    @Test
    public void shouldThrowExceptionIfResponseIsError_3_oe() throws IOException {
        final String body = "{\"error\":"
                + "{\"message\":\"This authorization code has been used.\","
                + "\"type\":\"OAuthException\","
                + "\"code\":100,"
                + "\"fbtrace_id\":\"DtxvtGRaxbB\"}}";
        try (Response response = error(body)) {

            // removed other assertion

            // removed other assertion
            assertEquals("OAuthException", fateR.getType());
    }
    }

    @Test
    public void shouldThrowExceptionIfResponseIsError_4_oe() throws IOException {
        final String body = "{\"error\":"
                + "{\"message\":\"This authorization code has been used.\","
                + "\"type\":\"OAuthException\","
                + "\"code\":100,"
                + "\"fbtrace_id\":\"DtxvtGRaxbB\"}}";
        try (Response response = error(body)) {

            // removed other assertion

            // removed other assertion
            // removed other assertion
            assertEquals(100, fateR.getCodeInt());
    }
    }

    @Test
    public void shouldThrowExceptionIfResponseIsError_5_oe() throws IOException {
        final String body = "{\"error\":"
                + "{\"message\":\"This authorization code has been used.\","
                + "\"type\":\"OAuthException\","
                + "\"code\":100,"
                + "\"fbtrace_id\":\"DtxvtGRaxbB\"}}";
        try (Response response = error(body)) {

            // removed other assertion

            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("DtxvtGRaxbB", fateR.getFbtraceId());
    }
    }

    @Test
    public void shouldThrowExceptionIfResponseIsError_6_oe() throws IOException {
        final String body = "{\"error\":"
                + "{\"message\":\"This authorization code has been used.\","
                + "\"type\":\"OAuthException\","
                + "\"code\":100,"
                + "\"fbtrace_id\":\"DtxvtGRaxbB\"}}";
        try (Response response = error(body)) {

            // removed other assertion

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(body, fateR.getResponse().getBody());
    }
    }

}
