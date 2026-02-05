package com.github.scribejava.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class OAuthRequestTest_OE25Dev {

    private OAuthRequest request;

    @Before
    public void setUp() {
        request = new OAuthRequest(Verb.GET, "http://example.com");
    }

    public void shouldThrowExceptionIfParameterIsNotOAuth() {
        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                request.addOAuthParameter("otherParam", "value");
            }
        });
    }

@Test
    public void shouldAddOAuthParamters_1_oe() {
        request.addOAuthParameter(OAuthConstants.TOKEN, "token");
        request.addOAuthParameter(OAuthConstants.NONCE, "nonce");
        request.addOAuthParameter(OAuthConstants.TIMESTAMP, "ts");
        request.addOAuthParameter(OAuthConstants.SCOPE, "feeds");
        request.addOAuthParameter(OAuthConstants.REALM, "some-realm");

        assertEquals(5, request.getOauthParameters().size());
    }

@Test
    public void shouldNotSentHeaderTwice_1_oe() {
        assertTrue(request.getHeaders().isEmpty());
    }

@Test
    public void shouldNotSentHeaderTwice_2_oe() {
        // removed other assertion
        request.addHeader("HEADER-NAME", "first");
        request.addHeader("header-name", "middle");
        request.addHeader("Header-Name", "last");

        assertEquals(1, request.getHeaders().size());
    }

@Test
    public void shouldNotSentHeaderTwice_3_oe() {
        // removed other assertion
        request.addHeader("HEADER-NAME", "first");
        request.addHeader("header-name", "middle");
        request.addHeader("Header-Name", "last");

        // removed other assertion

        assertTrue(request.getHeaders().containsKey("HEADER-NAME"));
    }

@Test
    public void shouldNotSentHeaderTwice_4_oe() {
        // removed other assertion
        request.addHeader("HEADER-NAME", "first");
        request.addHeader("header-name", "middle");
        request.addHeader("Header-Name", "last");

        // removed other assertion

        // removed other assertion
        assertTrue(request.getHeaders().containsKey("header-name"));
    }

@Test
    public void shouldNotSentHeaderTwice_5_oe() {
        // removed other assertion
        request.addHeader("HEADER-NAME", "first");
        request.addHeader("header-name", "middle");
        request.addHeader("Header-Name", "last");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(request.getHeaders().containsKey("Header-Name"));
    }

@Test
    public void shouldNotSentHeaderTwice_6_oe() {
        // removed other assertion
        request.addHeader("HEADER-NAME", "first");
        request.addHeader("header-name", "middle");
        request.addHeader("Header-Name", "last");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("last", request.getHeaders().get("HEADER-NAME"));
    }

@Test
    public void shouldNotSentHeaderTwice_7_oe() {
        // removed other assertion
        request.addHeader("HEADER-NAME", "first");
        request.addHeader("header-name", "middle");
        request.addHeader("Header-Name", "last");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("last", request.getHeaders().get("header-name"));
    }

@Test
    public void shouldNotSentHeaderTwice_8_oe() {
        // removed other assertion
        request.addHeader("HEADER-NAME", "first");
        request.addHeader("header-name", "middle");
        request.addHeader("Header-Name", "last");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("last", request.getHeaders().get("Header-Name"));
    }

}
