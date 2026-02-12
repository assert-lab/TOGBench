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


}
