package com.github.scribejava.core.httpclient.multipart;

import com.github.scribejava.core.httpclient.HttpClient;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class MultipartUtilsTest_OE25Dev {

    @Test
    public void testValidCheckBoundarySyntax() {
        MultipartUtils.checkBoundarySyntax("0aA'()+_,-./:=?");
        MultipartUtils.checkBoundarySyntax("0aA'()+_,- ./:=?");
        MultipartUtils.checkBoundarySyntax(" 0aA'()+_,-./:=?");
        MultipartUtils.checkBoundarySyntax("1234567890123456789012345678901234567890123456789012345678901234567890");
    }

    @Test
    public void testNonValidLastWhiteSpaceCheckBoundarySyntax() {
        testNotValidBoundary("0aA'()+_,-./:=? ");
    }

    @Test
    public void testNonValidEmptyCheckBoundarySyntax() {
        testNotValidBoundary("");
    }

    @Test
    public void testNonValidIllegalSymbolCheckBoundarySyntax() {
        testNotValidBoundary("0aA'()+_;,-./:=? ");
    }

    @Test
    public void testNonValidTooLongCheckBoundarySyntax() {
        testNotValidBoundary("12345678901234567890123456789012345678901234567890123456789012345678901");
    }

    @Test
    public void testNonValidNullCheckBoundarySyntax() {
        testNotValidBoundary(null);
    }

    private static void testNotValidBoundary(final String boundary) {
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                MultipartUtils.checkBoundarySyntax(boundary);
            }
        });
        assertTrue(thrown.getMessage().startsWith("{'boundary'='" + boundary + "'} has invalid syntax. Should be '"));
    }


}
