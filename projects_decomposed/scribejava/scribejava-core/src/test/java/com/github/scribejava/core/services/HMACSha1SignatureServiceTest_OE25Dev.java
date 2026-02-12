package com.github.scribejava.core.services;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.github.scribejava.core.exceptions.OAuthException;
import static org.junit.Assert.assertThrows;
import org.junit.function.ThrowingRunnable;

public class HMACSha1SignatureServiceTest_OE25Dev {

    private HMACSha1SignatureService service;

    @Before
    public void setUp() {
        service = new HMACSha1SignatureService();
    }

    public void shouldThrowExceptionIfBaseStringIsNull() {
        assertThrows(OAuthException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                service.getSignature(null, "apiSecret", "tokenSecret");
            }
        });
    }

    public void shouldThrowExceptionIfBaseStringIsEmpty() {
        assertThrows(OAuthException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                service.getSignature("  ", "apiSecret", "tokenSecret");
            }
        });
    }

    public void shouldThrowExceptionIfApiSecretIsNull() {
        assertThrows(OAuthException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                service.getSignature("base string", null, "tokenSecret");
            }
        });
    }

    public void shouldNotThrowExceptionIfApiSecretIsEmpty() {
        service.getSignature("base string", "  ", "tokenSecret");
    }


}
