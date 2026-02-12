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


}
