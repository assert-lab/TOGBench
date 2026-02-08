package com.github.scribejava.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class OAuthEncoderTest_OE25Dev {

    public void shouldThrowExceptionIfStringToEncodeIsNull() {
        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                OAuthEncoder.encode(null);
            }
        });
    }

    public void shouldThrowExceptionIfStringToDecodeIsNull() {
        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                OAuthEncoder.decode(null);
            }
        });
    }

    @Test
    public void shouldPercentEncodeString_1_oe() {
        final String plain = "this is a test &^";
        final String encoded = "this%20is%20a%20test%20%26%5E";
        assertEquals(encoded, OAuthEncoder.encode(plain));
    }

    @Test
    public void shouldFormURLDecodeString_1_oe() {
        final String encoded = "this+is+a+test+%26%5E";
        final String plain = "this is a test &^";
        assertEquals(plain, OAuthEncoder.decode(encoded));
    }

    @Test
    public void shouldPercentEncodeAllSpecialCharacters_1_oe() {
        final String plain = "!*'();:@&=+$,/?#[]";
        final String encoded = "%21%2A%27%28%29%3B%3A%40%26%3D%2B%24%2C%2F%3F%23%5B%5D";
        assertEquals(encoded, OAuthEncoder.encode(plain));
    }

    @Test
    public void shouldPercentEncodeAllSpecialCharacters_2_oe() {
        final String plain = "!*'();:@&=+$,/?#[]";
        final String encoded = "%21%2A%27%28%29%3B%3A%40%26%3D%2B%24%2C%2F%3F%23%5B%5D";
        // removed other assertion
        assertEquals(plain, OAuthEncoder.decode(encoded));
    }

    @Test
    public void shouldNotPercentEncodeReservedCharacters_1_oe() {
        final String plain = "abcde123456-._~";
        final String encoded = plain;
        assertEquals(encoded, OAuthEncoder.encode(plain));
    }

    @Test
    public void shouldPercentEncodeCorrectlyTwitterCodingExamples_1_oe() {
        // These tests are part of the Twitter dev examples here
        // -> https://dev.twitter.com/docs/auth/percent-encoding-parameters
        final String[] sources = {"Ladies + Gentlemen", "An encoded string!", "Dogs, Cats & Mice"};
        final String[] encoded = {"Ladies%20%2B%20Gentlemen", "An%20encoded%20string%21",
            "Dogs%2C%20Cats%20%26%20Mice"};

        for (int i = 0; i < sources.length; i++) {
            assertEquals(encoded[i], OAuthEncoder.encode(sources[i]));
    }
    }

}
