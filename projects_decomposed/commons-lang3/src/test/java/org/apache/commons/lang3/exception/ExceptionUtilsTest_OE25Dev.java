/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.commons.lang3.test.NotVisibleExceptionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests {@link org.apache.commons.lang3.exception.ExceptionUtils}.
 *
 * @since 1.0
 */
public class ExceptionUtilsTest_OE25Dev {

    /**
     * Provides a method with a well known chained/nested exception
     * name which matches the full signature (e.g. has a return value
     * of {@code Throwable}.
     */
    private static class ExceptionWithCause extends Exception {
        private static final long serialVersionUID = 1L;

        private Throwable cause;

        ExceptionWithCause(final String str, final Throwable cause) {
            super(str);
            setCause(cause);
        }

        ExceptionWithCause(final Throwable cause) {
            setCause(cause);
        }

        @Override
        public Throwable getCause() {
            return cause;
        }

        public void setCause(final Throwable cause) {
            this.cause = cause;
        }
    }
    /**
     * Provides a method with a well known chained/nested exception
     * name which does not match the full signature (e.g. lacks a
     * return value of {@code Throwable}.
     */
    private static class ExceptionWithoutCause extends Exception {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("unused")
        public void getTargetException() {
            // noop
        }
    }
    // Temporary classes to allow the nested exception code to be removed
    // prior to a rewrite of this test class.
    private static class NestableException extends Exception {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("unused")
        NestableException() {
        }

        NestableException(final Throwable t) {
            super(t);
        }
    }
    public static class TestThrowable extends Throwable {
        private static final long serialVersionUID = 1L;
    }
    private static int redeclareCheckedException() {
        return throwsCheckedException();
    }
    private static int throwsCheckedException() {
        try {
            throw new IOException();
        } catch (final Exception e) {
            return ExceptionUtils.<Integer>rethrow(e);
        }
    }


    private NestableException nested;


    private Throwable withCause;

    private Throwable withoutCause;

    private Throwable jdkNoCause;

    //-----------------------------------------------------------------------

    private ExceptionWithCause cyclicCause;

    private Throwable notVisibleException;

    private Throwable createExceptionWithCause() {
        try {
            try {
                throw new ExceptionWithCause(createExceptionWithoutCause());
            } catch (final Throwable t) {
                throw new ExceptionWithCause(t);
            }
        } catch (final Throwable t) {
            return t;
        }
    }

    //-----------------------------------------------------------------------
    private Throwable createExceptionWithoutCause() {
        try {
            throw new ExceptionWithoutCause();
        } catch (final Throwable t) {
            return t;
        }
    }

    @BeforeEach
    public void setUp() {
        withoutCause = createExceptionWithoutCause();
        nested = new NestableException(withoutCause);
        withCause = new ExceptionWithCause(nested);
        jdkNoCause = new NullPointerException();
        final ExceptionWithCause a = new ExceptionWithCause(null);
        final ExceptionWithCause b = new ExceptionWithCause(a);
        a.setCause(b);
        cyclicCause = new ExceptionWithCause(a);
        notVisibleException = NotVisibleExceptionFactory.createException(withoutCause);
    }

    @AfterEach
    public void tearDown() {
        withoutCause = null;
        nested = null;
        withCause = null;
        jdkNoCause = null;
        cyclicCause = null;
        notVisibleException = null;
    }

    @Test
    public void testCatchTechniques() {
        IOException ioe = assertThrows(IOException.class, ExceptionUtilsTest_OE25Dev::throwsCheckedException);
        assertEquals(1, ExceptionUtils.getThrowableCount(ioe));

        ioe = assertThrows(IOException.class, ExceptionUtilsTest_OE25Dev::redeclareCheckedException);
        assertEquals(1, ExceptionUtils.getThrowableCount(ioe));
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    @Test
    public void testPrintRootCauseStackTrace_Throwable() {
        ExceptionUtils.printRootCauseStackTrace(null);
        // could pipe system.err to a known stream, but not much point as
        // internally this method calls stream method anyway
    }

    //-----------------------------------------------------------------------

    @Test
    public void testPrintRootCauseStackTrace_ThrowableStream_2_oe() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintStream) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintStream(out));
        // removed other assertion

        out = new ByteArrayOutputStream(1024);
        try {
    ExceptionUtils.printRootCauseStackTrace(withCause, (PrintStream) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPrintRootCauseStackTrace_ThrowableWriter_2_oe() throws Exception {
        StringWriter writer = new StringWriter(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintWriter) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintWriter(writer));
        // removed other assertion

        writer = new StringWriter(1024);
        try {
    ExceptionUtils.printRootCauseStackTrace(withCause, (PrintWriter) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testRemoveCommonFrames_ListList_1_oe() throws Exception {
        try {
    ExceptionUtils.removeCommonFrames(null, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testThrow_1_oe() {
        final Exception expected = new InterruptedException();
        final Exception actual = assertThrows(Exception.class, () -> ExceptionUtils.rethrow(expected));
    }

    @Test
    public void testWrapAndUnwrapCheckedException_1_oe() {
        final Throwable t = assertThrows(Throwable.class, () -> ExceptionUtils.wrapAndThrow(new IOException()));
    }

    @Test
    public void testWrapAndUnwrapError_1_oe() {
        final Throwable t = assertThrows(Throwable.class, () -> ExceptionUtils.wrapAndThrow(new OutOfMemoryError()));
    }

    @Test
    public void testWrapAndUnwrapRuntimeException_1_oe() {
        final Throwable t = assertThrows(Throwable.class, () -> ExceptionUtils.wrapAndThrow(new IllegalArgumentException()));
    }

    @Test
    public void testWrapAndUnwrapThrowable_1_oe() {
        final Throwable t = assertThrows(Throwable.class, () -> ExceptionUtils.wrapAndThrow(new TestThrowable()));
    }

}
