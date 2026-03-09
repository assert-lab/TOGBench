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

import static org.junit.jupiter.api.Assertions.fail;

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
    public void test_getMessage_Throwable() {
        Throwable th = null;
        assertEquals("", ExceptionUtils.getMessage(th));

        th = new IllegalArgumentException("Base");
        assertEquals("IllegalArgumentException: Base", ExceptionUtils.getMessage(th));

        th = new ExceptionWithCause("Wrapper", th);
        assertEquals("ExceptionUtilsTest_OE25Dev.ExceptionWithCause: Wrapper", ExceptionUtils.getMessage(th));
    }

    @Test
    public void test_getRootCauseMessage_Throwable() {
        Throwable th = null;
        assertEquals("", ExceptionUtils.getRootCauseMessage(th));

        th = new IllegalArgumentException("Base");
        assertEquals("IllegalArgumentException: Base", ExceptionUtils.getRootCauseMessage(th));

        th = new ExceptionWithCause("Wrapper", th);
        assertEquals("IllegalArgumentException: Base", ExceptionUtils.getRootCauseMessage(th));
    }

    @Test
    public void testCatchTechniques() {
        IOException ioe = assertThrows(IOException.class, ExceptionUtilsTest_OE25Dev::throwsCheckedException);
        assertEquals(1, ExceptionUtils.getThrowableCount(ioe));

        ioe = assertThrows(IOException.class, ExceptionUtilsTest_OE25Dev::redeclareCheckedException);
        assertEquals(1, ExceptionUtils.getThrowableCount(ioe));
    }

    @Test
    public void testConstructor() {
        assertNotNull(new ExceptionUtils());
        final Constructor<?>[] cons = ExceptionUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(ExceptionUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(ExceptionUtils.class.getModifiers()));
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("deprecation") // Specifically tests the deprecated methods
    @Test
    public void testGetCause_Throwable() {
        assertSame(null, ExceptionUtils.getCause(null));
        assertSame(null, ExceptionUtils.getCause(withoutCause));
        assertSame(withoutCause, ExceptionUtils.getCause(nested));
        assertSame(nested, ExceptionUtils.getCause(withCause));
        assertSame(null, ExceptionUtils.getCause(jdkNoCause));
        assertSame(cyclicCause.getCause(), ExceptionUtils.getCause(cyclicCause));
        assertSame(cyclicCause.getCause().getCause(), ExceptionUtils.getCause(cyclicCause.getCause()));
        assertSame(cyclicCause.getCause(), ExceptionUtils.getCause(cyclicCause.getCause().getCause()));
        assertSame(withoutCause, ExceptionUtils.getCause(notVisibleException));
    }

    @SuppressWarnings("deprecation") // Specifically tests the deprecated methods
    @Test
    public void testGetCause_ThrowableArray() {
        assertSame(null, ExceptionUtils.getCause(null, null));
        assertSame(null, ExceptionUtils.getCause(null, new String[0]));

        // not known type, so match on supplied method names
        assertSame(nested,ExceptionUtils.getCause(withCause,null));// default names assertSame(null,ExceptionUtils.getCause(withCause,new String[0]));
        assertSame(null, ExceptionUtils.getCause(withCause, new String[]{null}));
        assertSame(nested, ExceptionUtils.getCause(withCause, new String[]{"getCause"}));

        // not known type, so match on supplied method names
        assertSame(null, ExceptionUtils.getCause(withoutCause, null));
        assertSame(null, ExceptionUtils.getCause(withoutCause, new String[0]));
        assertSame(null, ExceptionUtils.getCause(withoutCause, new String[]{null}));
        assertSame(null, ExceptionUtils.getCause(withoutCause, new String[]{"getCause"}));
        assertSame(null, ExceptionUtils.getCause(withoutCause, new String[]{"getTargetException"}));
    }

    @Test
    public void testGetRootCause_Throwable() {
        assertSame(null, ExceptionUtils.getRootCause(null));
        assertSame(withoutCause, ExceptionUtils.getRootCause(withoutCause));
        assertSame(withoutCause, ExceptionUtils.getRootCause(nested));
        assertSame(withoutCause, ExceptionUtils.getRootCause(withCause));
        assertSame(jdkNoCause, ExceptionUtils.getRootCause(jdkNoCause));
        assertSame(cyclicCause.getCause().getCause(), ExceptionUtils.getRootCause(cyclicCause));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testGetRootCauseStackTrace_Throwable() {
        assertEquals(0, ExceptionUtils.getRootCauseStackTrace(null).length);

        final Throwable cause = createExceptionWithCause();
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(cause);
        boolean match = false;
        for (final String element : stackTrace) {
            if (element.startsWith(ExceptionUtils.WRAPPED_MARKER)) {
                match = true;
                break;
            }
        }
        assertTrue(match);

        stackTrace = ExceptionUtils.getRootCauseStackTrace(withoutCause);
        match = false;
        for (final String element : stackTrace) {
            if (element.startsWith(ExceptionUtils.WRAPPED_MARKER)) {
                match = true;
                break;
            }
        }
        assertFalse(match);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testGetThrowableCount_Throwable() {
        assertEquals(0, ExceptionUtils.getThrowableCount(null));
        assertEquals(1, ExceptionUtils.getThrowableCount(withoutCause));
        assertEquals(2, ExceptionUtils.getThrowableCount(nested));
        assertEquals(3, ExceptionUtils.getThrowableCount(withCause));
        assertEquals(1, ExceptionUtils.getThrowableCount(jdkNoCause));
        assertEquals(3, ExceptionUtils.getThrowableCount(cyclicCause));
    }

    @Test
    public void testGetThrowableList_Throwable_jdkNoCause() {
        final List<?> throwables = ExceptionUtils.getThrowableList(jdkNoCause);
        assertEquals(1, throwables.size());
        assertSame(jdkNoCause, throwables.get(0));
    }

    @Test
    public void testGetThrowableList_Throwable_nested() {
        final List<?> throwables = ExceptionUtils.getThrowableList(nested);
        assertEquals(2, throwables.size());
        assertSame(nested, throwables.get(0));
        assertSame(withoutCause, throwables.get(1));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testGetThrowableList_Throwable_null() {
        final List<?> throwables = ExceptionUtils.getThrowableList(null);
        assertEquals(0, throwables.size());
    }

    @Test
    public void testGetThrowableList_Throwable_recursiveCause() {
        final List<?> throwables = ExceptionUtils.getThrowableList(cyclicCause);
        assertEquals(3, throwables.size());
        assertSame(cyclicCause, throwables.get(0));
        assertSame(cyclicCause.getCause(), throwables.get(1));
        assertSame(cyclicCause.getCause().getCause(), throwables.get(2));
    }

    @Test
    public void testGetThrowableList_Throwable_withCause() {
        final List<?> throwables = ExceptionUtils.getThrowableList(withCause);
        assertEquals(3, throwables.size());
        assertSame(withCause, throwables.get(0));
        assertSame(nested, throwables.get(1));
        assertSame(withoutCause, throwables.get(2));
    }

    @Test
    public void testGetThrowableList_Throwable_withoutCause() {
        final List<?> throwables = ExceptionUtils.getThrowableList(withoutCause);
        assertEquals(1, throwables.size());
        assertSame(withoutCause, throwables.get(0));
    }

    @Test
    public void testGetThrowables_Throwable_jdkNoCause() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(jdkNoCause);
        assertEquals(1, throwables.length);
        assertSame(jdkNoCause, throwables[0]);
    }

    @Test
    public void testGetThrowables_Throwable_nested() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(nested);
        assertEquals(2, throwables.length);
        assertSame(nested, throwables[0]);
        assertSame(withoutCause, throwables[1]);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testGetThrowables_Throwable_null() {
        assertEquals(0, ExceptionUtils.getThrowables(null).length);
    }

    @Test
    public void testGetThrowables_Throwable_recursiveCause() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(cyclicCause);
        assertEquals(3, throwables.length);
        assertSame(cyclicCause, throwables[0]);
        assertSame(cyclicCause.getCause(), throwables[1]);
        assertSame(cyclicCause.getCause().getCause(), throwables[2]);
    }

    @Test
    public void testGetThrowables_Throwable_withCause() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(withCause);
        assertEquals(3, throwables.length);
        assertSame(withCause, throwables[0]);
        assertSame(nested, throwables[1]);
        assertSame(withoutCause, throwables[2]);
    }

    @Test
    public void testGetThrowables_Throwable_withoutCause() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(withoutCause);
        assertEquals(1, throwables.length);
        assertSame(withoutCause, throwables[0]);
    }

    @Test
    public void testIndexOf_ThrowableClass() {
        assertEquals(-1, ExceptionUtils.indexOfThrowable(null, null));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(null, NestableException.class));

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, null));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, ExceptionWithCause.class));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, NestableException.class));
        assertEquals(0, ExceptionUtils.indexOfThrowable(withoutCause, ExceptionWithoutCause.class));

        assertEquals(-1, ExceptionUtils.indexOfThrowable(nested, null));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(nested, ExceptionWithCause.class));
        assertEquals(0, ExceptionUtils.indexOfThrowable(nested, NestableException.class));
        assertEquals(1, ExceptionUtils.indexOfThrowable(nested, ExceptionWithoutCause.class));

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, null));
        assertEquals(0, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class));
        assertEquals(1, ExceptionUtils.indexOfThrowable(withCause, NestableException.class));
        assertEquals(2, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithoutCause.class));

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, Exception.class));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, Throwable.class));
    }

    @Test
    public void testIndexOf_ThrowableClassInt() {
        assertEquals(-1, ExceptionUtils.indexOfThrowable(null, null, 0));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(null, NestableException.class, 0));

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, null));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, ExceptionWithCause.class, 0));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, NestableException.class, 0));
        assertEquals(0, ExceptionUtils.indexOfThrowable(withoutCause, ExceptionWithoutCause.class, 0));

        assertEquals(-1, ExceptionUtils.indexOfThrowable(nested, null, 0));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(nested, ExceptionWithCause.class, 0));
        assertEquals(0, ExceptionUtils.indexOfThrowable(nested, NestableException.class, 0));
        assertEquals(1, ExceptionUtils.indexOfThrowable(nested, ExceptionWithoutCause.class, 0));

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, null));
        assertEquals(0, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class, 0));
        assertEquals(1, ExceptionUtils.indexOfThrowable(withCause, NestableException.class, 0));
        assertEquals(2, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithoutCause.class, 0));

        assertEquals(0, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class, -1));
        assertEquals(0, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class, 0));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class, 1));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class, 9));

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, Exception.class, 0));
        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, Throwable.class, 0));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testIndexOfType_ThrowableClass() {
        assertEquals(-1, ExceptionUtils.indexOfType(null, null));
        assertEquals(-1, ExceptionUtils.indexOfType(null, NestableException.class));

        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, null));
        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, ExceptionWithCause.class));
        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, NestableException.class));
        assertEquals(0, ExceptionUtils.indexOfType(withoutCause, ExceptionWithoutCause.class));

        assertEquals(-1, ExceptionUtils.indexOfType(nested, null));
        assertEquals(-1, ExceptionUtils.indexOfType(nested, ExceptionWithCause.class));
        assertEquals(0, ExceptionUtils.indexOfType(nested, NestableException.class));
        assertEquals(1, ExceptionUtils.indexOfType(nested, ExceptionWithoutCause.class));

        assertEquals(-1, ExceptionUtils.indexOfType(withCause, null));
        assertEquals(0, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class));
        assertEquals(1, ExceptionUtils.indexOfType(withCause, NestableException.class));
        assertEquals(2, ExceptionUtils.indexOfType(withCause, ExceptionWithoutCause.class));

        assertEquals(0, ExceptionUtils.indexOfType(withCause, Exception.class));
        assertEquals(0, ExceptionUtils.indexOfType(withCause, Throwable.class));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt() {
        assertEquals(-1, ExceptionUtils.indexOfType(null, null, 0));
        assertEquals(-1, ExceptionUtils.indexOfType(null, NestableException.class, 0));

        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, null));
        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, ExceptionWithCause.class, 0));
        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, NestableException.class, 0));
        assertEquals(0, ExceptionUtils.indexOfType(withoutCause, ExceptionWithoutCause.class, 0));

        assertEquals(-1, ExceptionUtils.indexOfType(nested, null, 0));
        assertEquals(-1, ExceptionUtils.indexOfType(nested, ExceptionWithCause.class, 0));
        assertEquals(0, ExceptionUtils.indexOfType(nested, NestableException.class, 0));
        assertEquals(1, ExceptionUtils.indexOfType(nested, ExceptionWithoutCause.class, 0));

        assertEquals(-1, ExceptionUtils.indexOfType(withCause, null));
        assertEquals(0, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class, 0));
        assertEquals(1, ExceptionUtils.indexOfType(withCause, NestableException.class, 0));
        assertEquals(2, ExceptionUtils.indexOfType(withCause, ExceptionWithoutCause.class, 0));

        assertEquals(0, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class, -1));
        assertEquals(0, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class, 0));
        assertEquals(-1, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class, 1));
        assertEquals(-1, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class, 9));

        assertEquals(0, ExceptionUtils.indexOfType(withCause, Exception.class, 0));
        assertEquals(0, ExceptionUtils.indexOfType(withCause, Throwable.class, 0));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testPrintRootCauseStackTrace_Throwable() {
        ExceptionUtils.printRootCauseStackTrace(null);
        // could pipe system.err to a known stream, but not much point as
        // internally this method calls stream method anyway
    }

    //-----------------------------------------------------------------------

    @Test
    public void testPrintRootCauseStackTrace_ThrowableStream() {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintStream) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintStream(out));
        assertEquals(0, out.toString().length());

        out = new ByteArrayOutputStream(1024);
        assertThrows(
                NullPointerException.class,
                () -> ExceptionUtils.printRootCauseStackTrace(withCause, (PrintStream) null));

        out = new ByteArrayOutputStream(1024);
        final Throwable cause = createExceptionWithCause();
        ExceptionUtils.printRootCauseStackTrace(cause, new PrintStream(out));
        String stackTrace = out.toString();
        assertTrue(stackTrace.contains(ExceptionUtils.WRAPPED_MARKER));

        out = new ByteArrayOutputStream(1024);
        ExceptionUtils.printRootCauseStackTrace(withoutCause, new PrintStream(out));
        stackTrace = out.toString();
        assertFalse(stackTrace.contains(ExceptionUtils.WRAPPED_MARKER));
    }

    @Test
    public void testPrintRootCauseStackTrace_ThrowableWriter() {
        StringWriter writer = new StringWriter(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintWriter) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintWriter(writer));
        assertEquals(0, writer.getBuffer().length());

        writer = new StringWriter(1024);
        assertThrows(
                NullPointerException.class,
                () -> ExceptionUtils.printRootCauseStackTrace(withCause, (PrintWriter) null));

        writer = new StringWriter(1024);
        final Throwable cause = createExceptionWithCause();
        ExceptionUtils.printRootCauseStackTrace(cause, new PrintWriter(writer));
        String stackTrace = writer.toString();
        assertTrue(stackTrace.contains(ExceptionUtils.WRAPPED_MARKER));

        writer = new StringWriter(1024);
        ExceptionUtils.printRootCauseStackTrace(withoutCause, new PrintWriter(writer));
        stackTrace = writer.toString();
        assertFalse(stackTrace.contains(ExceptionUtils.WRAPPED_MARKER));
    }

    @Test
    public void testRemoveCommonFrames_ListList() {
        assertThrows(IllegalArgumentException.class, () -> ExceptionUtils.removeCommonFrames(null, null));
    }

    @Test
    public void testThrow() {
        final Exception expected = new InterruptedException();
        final Exception actual = assertThrows(Exception.class, () -> ExceptionUtils.rethrow(expected));
        assertSame(expected, actual);
    }

    @Test
    public void testThrowableOf_ThrowableClass() {
        assertEquals(null, ExceptionUtils.throwableOfThrowable(null, null));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(null, NestableException.class));

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, null));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, ExceptionWithCause.class));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, NestableException.class));
        assertEquals(withoutCause, ExceptionUtils.throwableOfThrowable(withoutCause, ExceptionWithoutCause.class));

        assertEquals(null, ExceptionUtils.throwableOfThrowable(nested, null));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(nested, ExceptionWithCause.class));
        assertEquals(nested, ExceptionUtils.throwableOfThrowable(nested, NestableException.class));
        assertEquals(nested.getCause(), ExceptionUtils.throwableOfThrowable(nested, ExceptionWithoutCause.class));

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, null));
        assertEquals(withCause, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class));
        assertEquals(withCause.getCause(), ExceptionUtils.throwableOfThrowable(withCause, NestableException.class));
        assertEquals(withCause.getCause().getCause(), ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithoutCause.class));

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, Exception.class));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, Throwable.class));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt() {
        assertEquals(null, ExceptionUtils.throwableOfThrowable(null, null, 0));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(null, NestableException.class, 0));

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, null));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, ExceptionWithCause.class, 0));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, NestableException.class, 0));
        assertEquals(withoutCause, ExceptionUtils.throwableOfThrowable(withoutCause, ExceptionWithoutCause.class, 0));

        assertEquals(null, ExceptionUtils.throwableOfThrowable(nested, null, 0));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(nested, ExceptionWithCause.class, 0));
        assertEquals(nested, ExceptionUtils.throwableOfThrowable(nested, NestableException.class, 0));
        assertEquals(nested.getCause(), ExceptionUtils.throwableOfThrowable(nested, ExceptionWithoutCause.class, 0));

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, null));
        assertEquals(withCause, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class, 0));
        assertEquals(withCause.getCause(), ExceptionUtils.throwableOfThrowable(withCause, NestableException.class, 0));
        assertEquals(withCause.getCause().getCause(), ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithoutCause.class, 0));

        assertEquals(withCause, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class, -1));
        assertEquals(withCause, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class, 0));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class, 1));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class, 9));

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, Exception.class, 0));
        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, Throwable.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClass() {
        assertEquals(null, ExceptionUtils.throwableOfType(null, null));
        assertEquals(null, ExceptionUtils.throwableOfType(null, NestableException.class));

        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, null));
        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, ExceptionWithCause.class));
        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, NestableException.class));
        assertEquals(withoutCause, ExceptionUtils.throwableOfType(withoutCause, ExceptionWithoutCause.class));

        assertEquals(null, ExceptionUtils.throwableOfType(nested, null));
        assertEquals(null, ExceptionUtils.throwableOfType(nested, ExceptionWithCause.class));
        assertEquals(nested, ExceptionUtils.throwableOfType(nested, NestableException.class));
        assertEquals(nested.getCause(), ExceptionUtils.throwableOfType(nested, ExceptionWithoutCause.class));

        assertEquals(null, ExceptionUtils.throwableOfType(withCause, null));
        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class));
        assertEquals(withCause.getCause(), ExceptionUtils.throwableOfType(withCause, NestableException.class));
        assertEquals(withCause.getCause().getCause(), ExceptionUtils.throwableOfType(withCause, ExceptionWithoutCause.class));

        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, Exception.class));
        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, Throwable.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt() {
        assertEquals(null, ExceptionUtils.throwableOfType(null, null, 0));
        assertEquals(null, ExceptionUtils.throwableOfType(null, NestableException.class, 0));

        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, null));
        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, ExceptionWithCause.class, 0));
        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, NestableException.class, 0));
        assertEquals(withoutCause, ExceptionUtils.throwableOfType(withoutCause, ExceptionWithoutCause.class, 0));

        assertEquals(null, ExceptionUtils.throwableOfType(nested, null, 0));
        assertEquals(null, ExceptionUtils.throwableOfType(nested, ExceptionWithCause.class, 0));
        assertEquals(nested, ExceptionUtils.throwableOfType(nested, NestableException.class, 0));
        assertEquals(nested.getCause(), ExceptionUtils.throwableOfType(nested, ExceptionWithoutCause.class, 0));

        assertEquals(null, ExceptionUtils.throwableOfType(withCause, null));
        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class, 0));
        assertEquals(withCause.getCause(), ExceptionUtils.throwableOfType(withCause, NestableException.class, 0));
        assertEquals(withCause.getCause().getCause(), ExceptionUtils.throwableOfType(withCause, ExceptionWithoutCause.class, 0));

        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class, -1));
        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class, 0));
        assertEquals(null, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class, 1));
        assertEquals(null, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class, 9));

        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, Exception.class, 0));
        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, Throwable.class, 0));
    }

    @Test
    public void testWrapAndUnwrapCheckedException() {
        final Throwable t = assertThrows(Throwable.class, () -> ExceptionUtils.wrapAndThrow(new IOException()));
        assertTrue(ExceptionUtils.hasCause(t, IOException.class));
    }

    @Test
    public void testWrapAndUnwrapError() {
        final Throwable t = assertThrows(Throwable.class, () -> ExceptionUtils.wrapAndThrow(new OutOfMemoryError()));
        assertTrue(ExceptionUtils.hasCause(t, Error.class));
    }

    @Test
    public void testWrapAndUnwrapRuntimeException() {
        final Throwable t = assertThrows(Throwable.class, () -> ExceptionUtils.wrapAndThrow(new IllegalArgumentException()));
        assertTrue(ExceptionUtils.hasCause(t, RuntimeException.class));
    }

    @Test
    public void testWrapAndUnwrapThrowable() {
        final Throwable t = assertThrows(Throwable.class, () -> ExceptionUtils.wrapAndThrow(new TestThrowable()));
        assertTrue(ExceptionUtils.hasCause(t, TestThrowable.class));
    }

    @Test
    @DisplayName("getStackFrames returns the string array of the stack frames when there is a real exception")
    public void testgetStackFramesNullArg() {
        final String[] actual = ExceptionUtils.getStackFrames((Throwable) null);
        assertEquals(0, actual.length);
    }

    @Test
    @DisplayName("getStackFrames returns empty string array when the argument is null")
    public void testgetStackFramesHappyPath() {
        final String[] actual = ExceptionUtils.getStackFrames(new Throwable() {
            // provide static stack trace to make test stable
            @Override
            public void printStackTrace(final PrintWriter s) {
                s.write("org.apache.commons.lang3.exception.ExceptionUtilsTest_OE25Dev$1\n" +
                    "\tat org.apache.commons.lang3.exception.ExceptionUtilsTest_OE25Dev.testgetStackFramesGappyPath(ExceptionUtilsTest_OE25Dev.java:706)\n" +
                    "\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                    "\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n" +
                    "\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n");
            }
        });

        assertArrayEquals(new String[]{
            "org.apache.commons.lang3.exception.ExceptionUtilsTest_OE25Dev$1",
            "\tat org.apache.commons.lang3.exception.ExceptionUtilsTest_OE25Dev.testgetStackFramesGappyPath(ExceptionUtilsTest_OE25Dev.java:706)",
            "\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)",
            "\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)",
            "\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)"
        }, actual);
    }

    @Test
    public void test_getMessage_Throwable_1_oe() {
        Throwable th = null;
        assertEquals("", ExceptionUtils.getMessage(th));
    }

    @Test
    public void test_getMessage_Throwable_2_oe() {
        Throwable th = null;

        th = new IllegalArgumentException("Base");
        assertEquals("IllegalArgumentException: Base", ExceptionUtils.getMessage(th));
    }

    @Test
    public void test_getRootCauseMessage_Throwable_1_oe() {
        Throwable th = null;
        assertEquals("", ExceptionUtils.getRootCauseMessage(th));
    }

    @Test
    public void test_getRootCauseMessage_Throwable_2_oe() {
        Throwable th = null;

        th = new IllegalArgumentException("Base");
        assertEquals("IllegalArgumentException: Base", ExceptionUtils.getRootCauseMessage(th));
    }

    @Test
    public void test_getRootCauseMessage_Throwable_3_oe() {
        Throwable th = null;

        th = new IllegalArgumentException("Base");

        th = new ExceptionWithCause("Wrapper", th);
        assertEquals("IllegalArgumentException: Base", ExceptionUtils.getRootCauseMessage(th));
    }

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new ExceptionUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        final Constructor<?>[] cons = ExceptionUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        final Constructor<?>[] cons = ExceptionUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        final Constructor<?>[] cons = ExceptionUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(ExceptionUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        final Constructor<?>[] cons = ExceptionUtils.class.getDeclaredConstructors();
        assertFalse(Modifier.isFinal(ExceptionUtils.class.getModifiers()));
    }

    @Test
    public void testGetCause_Throwable_1_oe() {
        assertSame(null, ExceptionUtils.getCause(null));
    }

    @Test
    public void testGetCause_Throwable_2_oe() {
        assertSame(null, ExceptionUtils.getCause(withoutCause));
    }

    @Test
    public void testGetCause_Throwable_3_oe() {
        assertSame(withoutCause, ExceptionUtils.getCause(nested));
    }

    @Test
    public void testGetCause_Throwable_4_oe() {
        assertSame(nested, ExceptionUtils.getCause(withCause));
    }

    @Test
    public void testGetCause_Throwable_5_oe() {
        assertSame(null, ExceptionUtils.getCause(jdkNoCause));
    }

    @Test
    public void testGetCause_Throwable_6_oe() {
        assertSame(cyclicCause.getCause(), ExceptionUtils.getCause(cyclicCause));
    }

    @Test
    public void testGetCause_Throwable_7_oe() {
        assertSame(cyclicCause.getCause().getCause(), ExceptionUtils.getCause(cyclicCause.getCause()));
    }

    @Test
    public void testGetCause_Throwable_8_oe() {
        assertSame(cyclicCause.getCause(), ExceptionUtils.getCause(cyclicCause.getCause().getCause()));
    }

    @Test
    public void testGetCause_Throwable_9_oe() {
        assertSame(withoutCause, ExceptionUtils.getCause(notVisibleException));
    }

    @Test
    public void testGetCause_ThrowableArray_1_oe() {
        assertSame(null, ExceptionUtils.getCause(null, null));
    }

    @Test
    public void testGetCause_ThrowableArray_2_oe() {
        assertSame(null, ExceptionUtils.getCause(null, new String[0]));
    }

    @Test
    public void testGetCause_ThrowableArray_3_oe() {

        assertSame(nested,ExceptionUtils.getCause(withCause,null));// default names assertSame(null,ExceptionUtils.getCause(withCause,new String[0]));
    }

    @Test
    public void testGetCause_ThrowableArray_4_oe() {

        assertSame(null, ExceptionUtils.getCause(withCause, new String[]{null}));
    }

    @Test
    public void testGetCause_ThrowableArray_5_oe() {

        assertSame(nested, ExceptionUtils.getCause(withCause, new String[]{"getCause"}));
    }

    @Test
    public void testGetCause_ThrowableArray_6_oe() {


        assertSame(null, ExceptionUtils.getCause(withoutCause, null));
    }

    @Test
    public void testGetCause_ThrowableArray_7_oe() {


        assertSame(null, ExceptionUtils.getCause(withoutCause, new String[0]));
    }

    @Test
    public void testGetCause_ThrowableArray_8_oe() {


        assertSame(null, ExceptionUtils.getCause(withoutCause, new String[]{null}));
    }

    @Test
    public void testGetCause_ThrowableArray_9_oe() {


        assertSame(null, ExceptionUtils.getCause(withoutCause, new String[]{"getCause"}));
    }

    @Test
    public void testGetCause_ThrowableArray_10_oe() {


        assertSame(null, ExceptionUtils.getCause(withoutCause, new String[]{"getTargetException"}));
    }

    @Test
    public void testGetRootCause_Throwable_1_oe() {
        assertSame(null, ExceptionUtils.getRootCause(null));
    }

    @Test
    public void testGetRootCause_Throwable_2_oe() {
        assertSame(withoutCause, ExceptionUtils.getRootCause(withoutCause));
    }

    @Test
    public void testGetRootCause_Throwable_3_oe() {
        assertSame(withoutCause, ExceptionUtils.getRootCause(nested));
    }

    @Test
    public void testGetRootCause_Throwable_4_oe() {
        assertSame(withoutCause, ExceptionUtils.getRootCause(withCause));
    }

    @Test
    public void testGetRootCause_Throwable_5_oe() {
        assertSame(jdkNoCause, ExceptionUtils.getRootCause(jdkNoCause));
    }

    @Test
    public void testGetRootCause_Throwable_6_oe() {
        assertSame(cyclicCause.getCause().getCause(), ExceptionUtils.getRootCause(cyclicCause));
    }

    @Test
    public void testGetRootCauseStackTrace_Throwable_1_oe() {
        assertEquals(0, ExceptionUtils.getRootCauseStackTrace(null).length);
    }

    @Test
    public void testGetRootCauseStackTrace_Throwable_2_oe() {

        final Throwable cause = createExceptionWithCause();
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(cause);
        boolean match = false;
        for (final String element : stackTrace) {
            if (element.startsWith(ExceptionUtils.WRAPPED_MARKER)) {
                match = true;
                break;
            }
        }
        assertTrue(match);
    }

    @Test
    public void testGetRootCauseStackTrace_Throwable_3_oe() {

        final Throwable cause = createExceptionWithCause();
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(cause);
        boolean match = false;
        for (final String element : stackTrace) {
            if (element.startsWith(ExceptionUtils.WRAPPED_MARKER)) {
                match = true;
                break;
            }
        }

        stackTrace = ExceptionUtils.getRootCauseStackTrace(withoutCause);
        match = false;
        for (final String element : stackTrace) {
            if (element.startsWith(ExceptionUtils.WRAPPED_MARKER)) {
                match = true;
                break;
            }
        }
        assertFalse(match);
    }

    @Test
    public void testGetThrowableCount_Throwable_1_oe() {
        assertEquals(0, ExceptionUtils.getThrowableCount(null));
    }

    @Test
    public void testGetThrowableCount_Throwable_2_oe() {
        assertEquals(1, ExceptionUtils.getThrowableCount(withoutCause));
    }

    @Test
    public void testGetThrowableCount_Throwable_3_oe() {
        assertEquals(2, ExceptionUtils.getThrowableCount(nested));
    }

    @Test
    public void testGetThrowableCount_Throwable_4_oe() {
        assertEquals(3, ExceptionUtils.getThrowableCount(withCause));
    }

    @Test
    public void testGetThrowableCount_Throwable_5_oe() {
        assertEquals(1, ExceptionUtils.getThrowableCount(jdkNoCause));
    }

    @Test
    public void testGetThrowableCount_Throwable_6_oe() {
        assertEquals(3, ExceptionUtils.getThrowableCount(cyclicCause));
    }

    @Test
    public void testGetThrowableList_Throwable_jdkNoCause_1_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(jdkNoCause);
        assertEquals(1, throwables.size());
    }

    @Test
    public void testGetThrowableList_Throwable_jdkNoCause_2_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(jdkNoCause);
        assertSame(jdkNoCause, throwables.get(0));
    }

    @Test
    public void testGetThrowableList_Throwable_nested_1_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(nested);
        assertEquals(2, throwables.size());
    }

    @Test
    public void testGetThrowableList_Throwable_nested_2_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(nested);
        assertSame(nested, throwables.get(0));
    }

    @Test
    public void testGetThrowableList_Throwable_nested_3_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(nested);
        assertSame(withoutCause, throwables.get(1));
    }

    @Test
    public void testGetThrowableList_Throwable_null_1_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(null);
        assertEquals(0, throwables.size());
    }

    @Test
    public void testGetThrowableList_Throwable_recursiveCause_1_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(cyclicCause);
        assertEquals(3, throwables.size());
    }

    @Test
    public void testGetThrowableList_Throwable_recursiveCause_2_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(cyclicCause);
        assertSame(cyclicCause, throwables.get(0));
    }

    @Test
    public void testGetThrowableList_Throwable_recursiveCause_3_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(cyclicCause);
        assertSame(cyclicCause.getCause(), throwables.get(1));
    }

    @Test
    public void testGetThrowableList_Throwable_recursiveCause_4_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(cyclicCause);
        assertSame(cyclicCause.getCause().getCause(), throwables.get(2));
    }

    @Test
    public void testGetThrowableList_Throwable_withCause_1_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(withCause);
        assertEquals(3, throwables.size());
    }

    @Test
    public void testGetThrowableList_Throwable_withCause_2_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(withCause);
        assertSame(withCause, throwables.get(0));
    }

    @Test
    public void testGetThrowableList_Throwable_withCause_3_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(withCause);
        assertSame(nested, throwables.get(1));
    }

    @Test
    public void testGetThrowableList_Throwable_withCause_4_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(withCause);
        assertSame(withoutCause, throwables.get(2));
    }

    @Test
    public void testGetThrowableList_Throwable_withoutCause_1_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(withoutCause);
        assertEquals(1, throwables.size());
    }

    @Test
    public void testGetThrowableList_Throwable_withoutCause_2_oe() {
        final List<?> throwables = ExceptionUtils.getThrowableList(withoutCause);
        assertSame(withoutCause, throwables.get(0));
    }

    @Test
    public void testGetThrowables_Throwable_jdkNoCause_1_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(jdkNoCause);
        assertEquals(1, throwables.length);
    }

    @Test
    public void testGetThrowables_Throwable_jdkNoCause_2_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(jdkNoCause);
        assertSame(jdkNoCause, throwables[0]);
    }

    @Test
    public void testGetThrowables_Throwable_nested_1_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(nested);
        assertEquals(2, throwables.length);
    }

    @Test
    public void testGetThrowables_Throwable_nested_2_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(nested);
        assertSame(nested, throwables[0]);
    }

    @Test
    public void testGetThrowables_Throwable_nested_3_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(nested);
        assertSame(withoutCause, throwables[1]);
    }

    @Test
    public void testGetThrowables_Throwable_null_1_oe() {
        assertEquals(0, ExceptionUtils.getThrowables(null).length);
    }

    @Test
    public void testGetThrowables_Throwable_recursiveCause_1_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(cyclicCause);
        assertEquals(3, throwables.length);
    }

    @Test
    public void testGetThrowables_Throwable_recursiveCause_2_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(cyclicCause);
        assertSame(cyclicCause, throwables[0]);
    }

    @Test
    public void testGetThrowables_Throwable_recursiveCause_3_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(cyclicCause);
        assertSame(cyclicCause.getCause(), throwables[1]);
    }

    @Test
    public void testGetThrowables_Throwable_recursiveCause_4_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(cyclicCause);
        assertSame(cyclicCause.getCause().getCause(), throwables[2]);
    }

    @Test
    public void testGetThrowables_Throwable_withCause_1_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(withCause);
        assertEquals(3, throwables.length);
    }

    @Test
    public void testGetThrowables_Throwable_withCause_2_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(withCause);
        assertSame(withCause, throwables[0]);
    }

    @Test
    public void testGetThrowables_Throwable_withCause_3_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(withCause);
        assertSame(nested, throwables[1]);
    }

    @Test
    public void testGetThrowables_Throwable_withCause_4_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(withCause);
        assertSame(withoutCause, throwables[2]);
    }

    @Test
    public void testGetThrowables_Throwable_withoutCause_1_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(withoutCause);
        assertEquals(1, throwables.length);
    }

    @Test
    public void testGetThrowables_Throwable_withoutCause_2_oe() {
        final Throwable[] throwables = ExceptionUtils.getThrowables(withoutCause);
        assertSame(withoutCause, throwables[0]);
    }

    @Test
    public void testIndexOf_ThrowableClass_1_oe() {
        assertEquals(-1, ExceptionUtils.indexOfThrowable(null, null));
    }

    @Test
    public void testIndexOf_ThrowableClass_2_oe() {
        assertEquals(-1, ExceptionUtils.indexOfThrowable(null, NestableException.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_3_oe() {

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, null));
    }

    @Test
    public void testIndexOf_ThrowableClass_4_oe() {

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, ExceptionWithCause.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_5_oe() {

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, NestableException.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_6_oe() {

        assertEquals(0, ExceptionUtils.indexOfThrowable(withoutCause, ExceptionWithoutCause.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_7_oe() {


        assertEquals(-1, ExceptionUtils.indexOfThrowable(nested, null));
    }

    @Test
    public void testIndexOf_ThrowableClass_8_oe() {


        assertEquals(-1, ExceptionUtils.indexOfThrowable(nested, ExceptionWithCause.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_9_oe() {


        assertEquals(0, ExceptionUtils.indexOfThrowable(nested, NestableException.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_10_oe() {


        assertEquals(1, ExceptionUtils.indexOfThrowable(nested, ExceptionWithoutCause.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_11_oe() {



        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, null));
    }

    @Test
    public void testIndexOf_ThrowableClass_12_oe() {



        assertEquals(0, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_13_oe() {



        assertEquals(1, ExceptionUtils.indexOfThrowable(withCause, NestableException.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_14_oe() {



        assertEquals(2, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithoutCause.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_15_oe() {




        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, Exception.class));
    }

    @Test
    public void testIndexOf_ThrowableClass_16_oe() {




        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, Throwable.class));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_1_oe() {
        assertEquals(-1, ExceptionUtils.indexOfThrowable(null, null, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_2_oe() {
        assertEquals(-1, ExceptionUtils.indexOfThrowable(null, NestableException.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_3_oe() {

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, null));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_4_oe() {

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_5_oe() {

        assertEquals(-1, ExceptionUtils.indexOfThrowable(withoutCause, NestableException.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_6_oe() {

        assertEquals(0, ExceptionUtils.indexOfThrowable(withoutCause, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_7_oe() {


        assertEquals(-1, ExceptionUtils.indexOfThrowable(nested, null, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_8_oe() {


        assertEquals(-1, ExceptionUtils.indexOfThrowable(nested, ExceptionWithCause.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_9_oe() {


        assertEquals(0, ExceptionUtils.indexOfThrowable(nested, NestableException.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_10_oe() {


        assertEquals(1, ExceptionUtils.indexOfThrowable(nested, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_11_oe() {



        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, null));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_12_oe() {



        assertEquals(0, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_13_oe() {



        assertEquals(1, ExceptionUtils.indexOfThrowable(withCause, NestableException.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_14_oe() {



        assertEquals(2, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_15_oe() {




        assertEquals(0, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class, -1));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_16_oe() {




        assertEquals(0, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_17_oe() {




        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class, 1));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_18_oe() {




        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, ExceptionWithCause.class, 9));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_19_oe() {





        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, Exception.class, 0));
    }

    @Test
    public void testIndexOf_ThrowableClassInt_20_oe() {





        assertEquals(-1, ExceptionUtils.indexOfThrowable(withCause, Throwable.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClass_1_oe() {
        assertEquals(-1, ExceptionUtils.indexOfType(null, null));
    }

    @Test
    public void testIndexOfType_ThrowableClass_2_oe() {
        assertEquals(-1, ExceptionUtils.indexOfType(null, NestableException.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_3_oe() {

        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, null));
    }

    @Test
    public void testIndexOfType_ThrowableClass_4_oe() {

        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, ExceptionWithCause.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_5_oe() {

        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, NestableException.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_6_oe() {

        assertEquals(0, ExceptionUtils.indexOfType(withoutCause, ExceptionWithoutCause.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_7_oe() {


        assertEquals(-1, ExceptionUtils.indexOfType(nested, null));
    }

    @Test
    public void testIndexOfType_ThrowableClass_8_oe() {


        assertEquals(-1, ExceptionUtils.indexOfType(nested, ExceptionWithCause.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_9_oe() {


        assertEquals(0, ExceptionUtils.indexOfType(nested, NestableException.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_10_oe() {


        assertEquals(1, ExceptionUtils.indexOfType(nested, ExceptionWithoutCause.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_11_oe() {



        assertEquals(-1, ExceptionUtils.indexOfType(withCause, null));
    }

    @Test
    public void testIndexOfType_ThrowableClass_12_oe() {



        assertEquals(0, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_13_oe() {



        assertEquals(1, ExceptionUtils.indexOfType(withCause, NestableException.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_14_oe() {



        assertEquals(2, ExceptionUtils.indexOfType(withCause, ExceptionWithoutCause.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_15_oe() {




        assertEquals(0, ExceptionUtils.indexOfType(withCause, Exception.class));
    }

    @Test
    public void testIndexOfType_ThrowableClass_16_oe() {




        assertEquals(0, ExceptionUtils.indexOfType(withCause, Throwable.class));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_1_oe() {
        assertEquals(-1, ExceptionUtils.indexOfType(null, null, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_2_oe() {
        assertEquals(-1, ExceptionUtils.indexOfType(null, NestableException.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_3_oe() {

        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, null));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_4_oe() {

        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_5_oe() {

        assertEquals(-1, ExceptionUtils.indexOfType(withoutCause, NestableException.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_6_oe() {

        assertEquals(0, ExceptionUtils.indexOfType(withoutCause, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_7_oe() {


        assertEquals(-1, ExceptionUtils.indexOfType(nested, null, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_8_oe() {


        assertEquals(-1, ExceptionUtils.indexOfType(nested, ExceptionWithCause.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_9_oe() {


        assertEquals(0, ExceptionUtils.indexOfType(nested, NestableException.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_10_oe() {


        assertEquals(1, ExceptionUtils.indexOfType(nested, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_11_oe() {



        assertEquals(-1, ExceptionUtils.indexOfType(withCause, null));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_12_oe() {



        assertEquals(0, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_13_oe() {



        assertEquals(1, ExceptionUtils.indexOfType(withCause, NestableException.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_14_oe() {



        assertEquals(2, ExceptionUtils.indexOfType(withCause, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_15_oe() {




        assertEquals(0, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class, -1));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_16_oe() {




        assertEquals(0, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_17_oe() {




        assertEquals(-1, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class, 1));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_18_oe() {




        assertEquals(-1, ExceptionUtils.indexOfType(withCause, ExceptionWithCause.class, 9));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_19_oe() {





        assertEquals(0, ExceptionUtils.indexOfType(withCause, Exception.class, 0));
    }

    @Test
    public void testIndexOfType_ThrowableClassInt_20_oe() {





        assertEquals(0, ExceptionUtils.indexOfType(withCause, Throwable.class, 0));
    }

    @Test
    public void testPrintRootCauseStackTrace_ThrowableStream_1_oe() {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintStream) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintStream(out));
        assertEquals(0, out.toString().length());
    }

    @Test
    public void testPrintRootCauseStackTrace_ThrowableStream_2_oe() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintStream) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintStream(out));

        out = new ByteArrayOutputStream(1024);
        try {
    ExceptionUtils.printRootCauseStackTrace(withCause, (PrintStream) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPrintRootCauseStackTrace_ThrowableStream_3_oe() {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintStream) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintStream(out));

        out = new ByteArrayOutputStream(1024);

        out = new ByteArrayOutputStream(1024);
        final Throwable cause = createExceptionWithCause();
        ExceptionUtils.printRootCauseStackTrace(cause, new PrintStream(out));
        String stackTrace = out.toString();
        assertTrue(stackTrace.contains(ExceptionUtils.WRAPPED_MARKER));
    }

    @Test
    public void testPrintRootCauseStackTrace_ThrowableStream_4_oe() {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintStream) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintStream(out));

        out = new ByteArrayOutputStream(1024);

        out = new ByteArrayOutputStream(1024);
        final Throwable cause = createExceptionWithCause();
        ExceptionUtils.printRootCauseStackTrace(cause, new PrintStream(out));
        String stackTrace = out.toString();

        out = new ByteArrayOutputStream(1024);
        ExceptionUtils.printRootCauseStackTrace(withoutCause, new PrintStream(out));
        stackTrace = out.toString();
        assertFalse(stackTrace.contains(ExceptionUtils.WRAPPED_MARKER));
    }

    @Test
    public void testPrintRootCauseStackTrace_ThrowableWriter_1_oe() {
        StringWriter writer = new StringWriter(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintWriter) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintWriter(writer));
        assertEquals(0, writer.getBuffer().length());
    }

    @Test
    public void testPrintRootCauseStackTrace_ThrowableWriter_2_oe() throws Exception {
        StringWriter writer = new StringWriter(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintWriter) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintWriter(writer));

        writer = new StringWriter(1024);
        try {
    ExceptionUtils.printRootCauseStackTrace(withCause, (PrintWriter) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPrintRootCauseStackTrace_ThrowableWriter_3_oe() {
        StringWriter writer = new StringWriter(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintWriter) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintWriter(writer));

        writer = new StringWriter(1024);

        writer = new StringWriter(1024);
        final Throwable cause = createExceptionWithCause();
        ExceptionUtils.printRootCauseStackTrace(cause, new PrintWriter(writer));
        String stackTrace = writer.toString();
        assertTrue(stackTrace.contains(ExceptionUtils.WRAPPED_MARKER));
    }

    @Test
    public void testPrintRootCauseStackTrace_ThrowableWriter_4_oe() {
        StringWriter writer = new StringWriter(1024);
        ExceptionUtils.printRootCauseStackTrace(null, (PrintWriter) null);
        ExceptionUtils.printRootCauseStackTrace(null, new PrintWriter(writer));

        writer = new StringWriter(1024);

        writer = new StringWriter(1024);
        final Throwable cause = createExceptionWithCause();
        ExceptionUtils.printRootCauseStackTrace(cause, new PrintWriter(writer));
        String stackTrace = writer.toString();

        writer = new StringWriter(1024);
        ExceptionUtils.printRootCauseStackTrace(withoutCause, new PrintWriter(writer));
        stackTrace = writer.toString();
        assertFalse(stackTrace.contains(ExceptionUtils.WRAPPED_MARKER));
    }

    @Test
    public void testRemoveCommonFrames_ListList_1_oe() throws Exception {
        try {
    ExceptionUtils.removeCommonFrames(null, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testThrow_1_oe() throws Exception {
        final Exception expected = new InterruptedException();
        try {
    ExceptionUtils.rethrow(expected);
    fail("Exception");
} catch (Exception e) {
}
    }

    @Test
    public void testThrowableOf_ThrowableClass_1_oe() {
        assertEquals(null, ExceptionUtils.throwableOfThrowable(null, null));
    }

    @Test
    public void testThrowableOf_ThrowableClass_2_oe() {
        assertEquals(null, ExceptionUtils.throwableOfThrowable(null, NestableException.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_3_oe() {

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, null));
    }

    @Test
    public void testThrowableOf_ThrowableClass_4_oe() {

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, ExceptionWithCause.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_5_oe() {

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, NestableException.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_6_oe() {

        assertEquals(withoutCause, ExceptionUtils.throwableOfThrowable(withoutCause, ExceptionWithoutCause.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_7_oe() {


        assertEquals(null, ExceptionUtils.throwableOfThrowable(nested, null));
    }

    @Test
    public void testThrowableOf_ThrowableClass_8_oe() {


        assertEquals(null, ExceptionUtils.throwableOfThrowable(nested, ExceptionWithCause.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_9_oe() {


        assertEquals(nested, ExceptionUtils.throwableOfThrowable(nested, NestableException.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_10_oe() {


        assertEquals(nested.getCause(), ExceptionUtils.throwableOfThrowable(nested, ExceptionWithoutCause.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_11_oe() {



        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, null));
    }

    @Test
    public void testThrowableOf_ThrowableClass_12_oe() {



        assertEquals(withCause, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_13_oe() {



        assertEquals(withCause.getCause(), ExceptionUtils.throwableOfThrowable(withCause, NestableException.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_14_oe() {



        assertEquals(withCause.getCause().getCause(), ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithoutCause.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_15_oe() {




        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, Exception.class));
    }

    @Test
    public void testThrowableOf_ThrowableClass_16_oe() {




        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, Throwable.class));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_1_oe() {
        assertEquals(null, ExceptionUtils.throwableOfThrowable(null, null, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_2_oe() {
        assertEquals(null, ExceptionUtils.throwableOfThrowable(null, NestableException.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_3_oe() {

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, null));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_4_oe() {

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_5_oe() {

        assertEquals(null, ExceptionUtils.throwableOfThrowable(withoutCause, NestableException.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_6_oe() {

        assertEquals(withoutCause, ExceptionUtils.throwableOfThrowable(withoutCause, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_7_oe() {


        assertEquals(null, ExceptionUtils.throwableOfThrowable(nested, null, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_8_oe() {


        assertEquals(null, ExceptionUtils.throwableOfThrowable(nested, ExceptionWithCause.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_9_oe() {


        assertEquals(nested, ExceptionUtils.throwableOfThrowable(nested, NestableException.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_10_oe() {


        assertEquals(nested.getCause(), ExceptionUtils.throwableOfThrowable(nested, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_11_oe() {



        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, null));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_12_oe() {



        assertEquals(withCause, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_13_oe() {



        assertEquals(withCause.getCause(), ExceptionUtils.throwableOfThrowable(withCause, NestableException.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_14_oe() {



        assertEquals(withCause.getCause().getCause(), ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_15_oe() {




        assertEquals(withCause, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class, -1));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_16_oe() {




        assertEquals(withCause, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_17_oe() {




        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class, 1));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_18_oe() {




        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, ExceptionWithCause.class, 9));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_19_oe() {





        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, Exception.class, 0));
    }

    @Test
    public void testThrowableOf_ThrowableClassInt_20_oe() {





        assertEquals(null, ExceptionUtils.throwableOfThrowable(withCause, Throwable.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_1_oe() {
        assertEquals(null, ExceptionUtils.throwableOfType(null, null));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_2_oe() {
        assertEquals(null, ExceptionUtils.throwableOfType(null, NestableException.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_3_oe() {

        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, null));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_4_oe() {

        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, ExceptionWithCause.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_5_oe() {

        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, NestableException.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_6_oe() {

        assertEquals(withoutCause, ExceptionUtils.throwableOfType(withoutCause, ExceptionWithoutCause.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_7_oe() {


        assertEquals(null, ExceptionUtils.throwableOfType(nested, null));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_8_oe() {


        assertEquals(null, ExceptionUtils.throwableOfType(nested, ExceptionWithCause.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_9_oe() {


        assertEquals(nested, ExceptionUtils.throwableOfType(nested, NestableException.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_10_oe() {


        assertEquals(nested.getCause(), ExceptionUtils.throwableOfType(nested, ExceptionWithoutCause.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_11_oe() {



        assertEquals(null, ExceptionUtils.throwableOfType(withCause, null));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_12_oe() {



        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_13_oe() {



        assertEquals(withCause.getCause(), ExceptionUtils.throwableOfType(withCause, NestableException.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_14_oe() {



        assertEquals(withCause.getCause().getCause(), ExceptionUtils.throwableOfType(withCause, ExceptionWithoutCause.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_15_oe() {




        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, Exception.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClass_16_oe() {




        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, Throwable.class));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_1_oe() {
        assertEquals(null, ExceptionUtils.throwableOfType(null, null, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_2_oe() {
        assertEquals(null, ExceptionUtils.throwableOfType(null, NestableException.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_3_oe() {

        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, null));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_4_oe() {

        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_5_oe() {

        assertEquals(null, ExceptionUtils.throwableOfType(withoutCause, NestableException.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_6_oe() {

        assertEquals(withoutCause, ExceptionUtils.throwableOfType(withoutCause, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_7_oe() {


        assertEquals(null, ExceptionUtils.throwableOfType(nested, null, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_8_oe() {


        assertEquals(null, ExceptionUtils.throwableOfType(nested, ExceptionWithCause.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_9_oe() {


        assertEquals(nested, ExceptionUtils.throwableOfType(nested, NestableException.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_10_oe() {


        assertEquals(nested.getCause(), ExceptionUtils.throwableOfType(nested, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_11_oe() {



        assertEquals(null, ExceptionUtils.throwableOfType(withCause, null));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_12_oe() {



        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_13_oe() {



        assertEquals(withCause.getCause(), ExceptionUtils.throwableOfType(withCause, NestableException.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_14_oe() {



        assertEquals(withCause.getCause().getCause(), ExceptionUtils.throwableOfType(withCause, ExceptionWithoutCause.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_15_oe() {




        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class, -1));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_16_oe() {




        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_17_oe() {




        assertEquals(null, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class, 1));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_18_oe() {




        assertEquals(null, ExceptionUtils.throwableOfType(withCause, ExceptionWithCause.class, 9));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_19_oe() {





        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, Exception.class, 0));
    }

    @Test
    public void testThrowableOfType_ThrowableClassInt_20_oe() {





        assertEquals(withCause, ExceptionUtils.throwableOfType(withCause, Throwable.class, 0));
    }

    @Test
    public void testWrapAndUnwrapCheckedException_1_oe() throws Exception {
        try {
    ExceptionUtils.wrapAndThrow(new IOException());
    fail("Throwable");
} catch (Throwable e) {
}
    }

    @Test
    public void testWrapAndUnwrapError_1_oe() throws Exception {
        try {
    ExceptionUtils.wrapAndThrow(new OutOfMemoryError());
    fail("Throwable");
} catch (Throwable e) {
}
    }

    @Test
    public void testWrapAndUnwrapRuntimeException_1_oe() throws Exception {
        try {
    ExceptionUtils.wrapAndThrow(new IllegalArgumentException());
    fail("Throwable");
} catch (Throwable e) {
}
    }

    @Test
    public void testWrapAndUnwrapThrowable_1_oe() throws Exception {
        try {
    ExceptionUtils.wrapAndThrow(new TestThrowable());
    fail("Throwable");
} catch (Throwable e) {
}
    }

    @Test
    @DisplayName("getStackFrames returns the string array of the stack frames when there is a real exception")
    public void testgetStackFramesNullArg_1_oe() {
        final String[] actual = ExceptionUtils.getStackFrames((Throwable) null);
        assertEquals(0, actual.length);
    }

    @Test
    @DisplayName("getStackFrames returns empty string array when the argument is null")
    public void testgetStackFramesHappyPath_1_oe() {
        final String[] actual = ExceptionUtils.getStackFrames(new Throwable() {
            @Override
            public void printStackTrace(final PrintWriter s) {
                s.write("org.apache.commons.lang3.exception.ExceptionUtilsTest$1\n" +
                    "\tat org.apache.commons.lang3.exception.ExceptionUtilsTest.testgetStackFramesGappyPath(ExceptionUtilsTest.java:706)\n" +
                    "\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                    "\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n" +
                    "\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n");
            }
        });

        assertArrayEquals(new String[]{ "org.apache.commons.lang3.exception.ExceptionUtilsTest$1", "\tat org.apache.commons.lang3.exception.ExceptionUtilsTest.testgetStackFramesGappyPath(ExceptionUtilsTest.java:706)", "\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)", "\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)", "\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)" }, actual);
    }

}
