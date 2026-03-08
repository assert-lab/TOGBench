package com.github.scribejava.core.extractors;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuth2AccessTokenErrorResponse;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth2.OAuth2Error;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.function.ThrowingRunnable;

public class OAuth2AccessTokenJsonExtractorTest_OE25Dev {

    private final OAuth2AccessTokenJsonExtractor extractor = OAuth2AccessTokenJsonExtractor.instance();

    public void shouldThrowExceptionIfForNullParameters() throws IOException {
        try (Response response = ok(null)) {
            assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
                @Override
                public void run() throws Throwable {
                    extractor.extract(response);
                }
            });
        }
    }

    public void shouldThrowExceptionIfForEmptyStrings() throws IOException {
        final String responseBody = "";
        try (Response response = ok(responseBody)) {
            assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
                @Override
                public void run() throws Throwable {
                    extractor.extract(response);
                }
            });
        }
    }

    private static Response ok(String body) {
        return new Response(200, /* message */ null, /* headers */ Collections.<String, String>emptyMap(), body);
    }

    private static Response error(String body) {
        return new Response(400, /* message */ null, /* headers */ Collections.<String, String>emptyMap(), body);
    }

    @Test
    public void shouldParseResponse_1_oe() throws IOException {
        final String responseBody = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T3X\", "
                + "\"token_type\":\"example\"}";
        final OAuth2AccessToken token;
        try (Response response = ok(responseBody)) {
            token = extractor.extract(response);
        }
        assertEquals("I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T3X", token.getAccessToken());
    }

    @Test
    public void shouldParseScopeFromResponse_1_oe() throws IOException {
        final String responseBody = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T4X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1\"}";
        final OAuth2AccessToken token;
        try (Response response = ok(responseBody)) {
            token = extractor.extract(response);
        }
        assertEquals("I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T4X", token.getAccessToken());
    }

    @Test
    public void shouldParseScopeFromResponse_2_oe() throws IOException {
        final String responseBody = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T4X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1\"}";
        final OAuth2AccessToken token;
        try (Response response = ok(responseBody)) {
            token = extractor.extract(response);
        }
        // removed other assertion
        assertEquals("s1", token.getScope());
    }

    @Test
    public void shouldParseScopeFromResponse_3_oe() throws IOException {
        final String responseBody = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T4X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1\"}";
        final OAuth2AccessToken token;
        try (Response response = ok(responseBody)) {
            token = extractor.extract(response);
        }
        // removed other assertion
        // removed other assertion

        final String responseBody2 = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T5X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1 s2\"}";
        final OAuth2AccessToken token2;
        try (Response response = ok(responseBody2)) {
            token2 = extractor.extract(response);
        }
        assertEquals("I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T5X", token2.getAccessToken());
    }

    @Test
    public void shouldParseScopeFromResponse_4_oe() throws IOException {
        final String responseBody = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T4X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1\"}";
        final OAuth2AccessToken token;
        try (Response response = ok(responseBody)) {
            token = extractor.extract(response);
        }
        // removed other assertion
        // removed other assertion

        final String responseBody2 = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T5X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1 s2\"}";
        final OAuth2AccessToken token2;
        try (Response response = ok(responseBody2)) {
            token2 = extractor.extract(response);
        }
        // removed other assertion
        assertEquals("s1 s2", token2.getScope());
    }

    @Test
    public void shouldParseScopeFromResponse_5_oe() throws IOException {
        final String responseBody = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T4X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1\"}";
        final OAuth2AccessToken token;
        try (Response response = ok(responseBody)) {
            token = extractor.extract(response);
        }
        // removed other assertion
        // removed other assertion

        final String responseBody2 = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T5X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1 s2\"}";
        final OAuth2AccessToken token2;
        try (Response response = ok(responseBody2)) {
            token2 = extractor.extract(response);
        }
        // removed other assertion
        // removed other assertion

        final String responseBody3 = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T6X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s3 s4\", "
                + "\"refresh_token\":\"refresh_token1\"}";
        final OAuth2AccessToken token3;
        try (Response response = ok(responseBody3)) {
            token3 = extractor.extract(response);
        }
        assertEquals("I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T6X", token3.getAccessToken());
    }

    @Test
    public void shouldParseScopeFromResponse_6_oe() throws IOException {
        final String responseBody = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T4X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1\"}";
        final OAuth2AccessToken token;
        try (Response response = ok(responseBody)) {
            token = extractor.extract(response);
        }
        // removed other assertion
        // removed other assertion

        final String responseBody2 = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T5X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1 s2\"}";
        final OAuth2AccessToken token2;
        try (Response response = ok(responseBody2)) {
            token2 = extractor.extract(response);
        }
        // removed other assertion
        // removed other assertion

        final String responseBody3 = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T6X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s3 s4\", "
                + "\"refresh_token\":\"refresh_token1\"}";
        final OAuth2AccessToken token3;
        try (Response response = ok(responseBody3)) {
            token3 = extractor.extract(response);
        }
        // removed other assertion
        assertEquals("s3 s4", token3.getScope());
    }

    @Test
    public void shouldParseScopeFromResponse_7_oe() throws IOException {
        final String responseBody = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T4X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1\"}";
        final OAuth2AccessToken token;
        try (Response response = ok(responseBody)) {
            token = extractor.extract(response);
        }
        // removed other assertion
        // removed other assertion

        final String responseBody2 = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T5X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s1 s2\"}";
        final OAuth2AccessToken token2;
        try (Response response = ok(responseBody2)) {
            token2 = extractor.extract(response);
        }
        // removed other assertion
        // removed other assertion

        final String responseBody3 = "{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T6X\", "
                + "\"token_type\":\"example\","
                + "\"scope\":\"s3 s4\", "
                + "\"refresh_token\":\"refresh_token1\"}";
        final OAuth2AccessToken token3;
        try (Response response = ok(responseBody3)) {
            token3 = extractor.extract(response);
        }
        // removed other assertion
        // removed other assertion
        assertEquals("refresh_token1", token3.getRefreshToken());
    }

    @Test
    public void testEscapedJsonInResponse_1_oe() throws IOException {
        final String responseBody = "{ \"access_token\":\"I0122HKLEM2\\/MV3ABKFTDT3T5X\","
                + "\"token_type\":\"example\"}";
        final OAuth2AccessToken token;
        try (Response response = ok(responseBody)) {
            token = extractor.extract(response);
        }
        assertEquals("I0122HKLEM2/MV3ABKFTDT3T5X", token.getAccessToken());
    }

@Test
    public void shouldThrowExceptionIfResponseIsError_1_oe() throws IOException {
        final String responseBody = "{"
                + "\"error_description\":\"unknown, invalid, or expired refresh token\","
                + "\"error\":\"invalid_grant\""
                + "}";
        try (Response response = error(responseBody)) {
            final OAuth2AccessTokenErrorResponse oaer = assertThrows(OAuth2AccessTokenErrorResponse.class, new ThrowingRunnable() { @Override public void run() throws Throwable { extractor.extract(response); } });
    }
    }

}
