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
package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link MultiBackgroundInitializer}.
 */
public class MultiBackgroundInitializerTest_OE25Dev {
    /** Constant for the names of the child initializers. */
    private static final String CHILD_INIT = "childInitializer";

    /** The initializer to be tested. */
    private MultiBackgroundInitializer initializer;

    @BeforeEach
    public void setUp() {
        initializer = new MultiBackgroundInitializer();
    }

    /**
     * Tests whether a child initializer has been executed. Optionally the
     * expected executor service can be checked, too.
     *
     * @param child the child initializer
     * @param expExec the expected executor service (null if the executor should
     * not be checked)
     * @throws ConcurrentException if an error occurs
     */
    private void checkChild(final BackgroundInitializer<?> child,
            final ExecutorService expExec) throws ConcurrentException {
        final ChildBackgroundInitializer cinit = (ChildBackgroundInitializer) child;
        final Integer result = cinit.get();
        assertEquals(1, result.intValue(), "Wrong result");
        assertEquals(1, cinit.initializeCalls, "Wrong number of executions");
        if (expExec != null) {
            assertEquals(expExec, cinit.currentExecutor, "Wrong executor service");
        }
    }

    /**
     * Tests addInitializer() if a null name is passed in. This should cause an
     * exception.
     */

    /**
     * Tests addInitializer() if a null initializer is passed in. This should
     * cause an exception.
     */

    /**
     * Tests the background processing if there are no child initializers.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Helper method for testing the initialize() method. This method can
     * operate with both an external and a temporary executor service.
     *
     * @return the result object produced by the initializer
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */
    private MultiBackgroundInitializer.MultiBackgroundInitializerResults checkInitialize()
            throws ConcurrentException {
        final int count = 5;
        for (int i = 0; i < count; i++) {
            initializer.addInitializer(CHILD_INIT + i,
                    new ChildBackgroundInitializer());
        }
        initializer.start();
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        assertEquals(count, res.initializerNames().size(), "Wrong number of child initializers");
        for (int i = 0; i < count; i++) {
            final String key = CHILD_INIT + i;
            assertTrue(res.initializerNames().contains(key), "Name not found: " + key);
            assertEquals(Integer.valueOf(1), res.getResultObject(key), "Wrong result object");
            assertFalse(res.isException(key), "Exception flag");
            assertNull(res.getException(key), "Got an exception");
            checkChild(res.getInitializer(key), initializer.getActiveExecutor());
        }
        return res;
    }

    /**
     * Tests background processing if a temporary executor is used.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests background processing if an external executor service is provided.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */
    @Test
    public void testInitializeExternalExec() throws ConcurrentException, InterruptedException {
        final ExecutorService exec = Executors.newCachedThreadPool();
        try {
            initializer = new MultiBackgroundInitializer(exec);
            checkInitialize();
            assertEquals(exec, initializer.getActiveExecutor(), "Wrong executor");
            assertFalse(exec.isShutdown(), "Executor was shutdown");
        } finally {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    /**
     * Tests the behavior of initialize() if a child initializer has a specific
     * executor service. Then this service should not be overridden.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */
    @Test
    public void testInitializeChildWithExecutor() throws ConcurrentException, InterruptedException {
        final String initExec = "childInitializerWithExecutor";
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        try {
            final ChildBackgroundInitializer c1 = new ChildBackgroundInitializer();
            final ChildBackgroundInitializer c2 = new ChildBackgroundInitializer();
            c2.setExternalExecutor(exec);
            initializer.addInitializer(CHILD_INIT, c1);
            initializer.addInitializer(initExec, c2);
            initializer.start();
            initializer.get();
            checkChild(c1, initializer.getActiveExecutor());
            checkChild(c2, exec);
        } finally {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    /**
     * Tries to add another child initializer after the start() method has been
     * called. This should not be allowed.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tries to query an unknown child initializer from the results object. This
     * should cause an exception.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tries to query the results of an unknown child initializer from the
     * results object. This should cause an exception.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tries to query the exception of an unknown child initializer from the
     * results object. This should cause an exception.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tries to query the exception flag of an unknown child initializer from
     * the results object. This should cause an exception.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests that the set with the names of the initializers cannot be modified.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests the behavior of the initializer if one of the child initializers
     * throws a runtime exception.
     */

    /**
     * Tests the behavior of the initializer if one of the child initializers
     * throws a checked exception.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests the isSuccessful() method of the result object if no child
     * initializer has thrown an exception.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests the isSuccessful() method of the result object if at least one
     * child initializer has thrown an exception.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests whether MultiBackgroundInitializers can be combined in a nested
     * way.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * A concrete implementation of {@code BackgroundInitializer} used for
     * defining background tasks for {@code MultiBackgroundInitializer}.
     */
    private static class ChildBackgroundInitializer extends
            BackgroundInitializer<Integer> {
        /** Stores the current executor service. */
        volatile ExecutorService currentExecutor;

        /** A counter for the invocations of initialize(). */
        volatile int initializeCalls;

        /** An exception to be thrown by initialize(). */
        Exception ex;

        /**
         * Records this invocation. Optionally throws an exception.
         */
        @Override
        protected Integer initialize() throws Exception {
            currentExecutor = getActiveExecutor();
            initializeCalls++;

            if (ex != null) {
                throw ex;
            }

            return Integer.valueOf(initializeCalls);
        }
    }

    @Test
    public void testAddInitializerNullName_1_oe() {
        assertThrows(NullPointerException.class, () -> initializer.addInitializer(null, new ChildBackgroundInitializer()));
    }

    @Test
    public void testAddInitializerNullInit_1_oe() {
        assertThrows(NullPointerException.class, () -> initializer.addInitializer(CHILD_INIT, null));
    }

    @Test
    public void testInitializeNoChildren_1_oe() throws ConcurrentException {
        assertTrue(initializer.start(), "Wrong result of start()");
    }

    @Test
    public void testInitializeNoChildren_2_oe() throws ConcurrentException {
        // removed other assertion
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        assertTrue(res.initializerNames().isEmpty(), "Got child initializers");
    }

    @Test
    public void testInitializeNoChildren_3_oe() throws ConcurrentException {
        // removed other assertion
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        // removed other assertion
        assertTrue(initializer.getActiveExecutor().isShutdown(), "Executor not shutdown");
    }

    @Test
    public void testInitializeTempExec_1_oe() throws ConcurrentException {
        checkInitialize();
        assertTrue(initializer.getActiveExecutor().isShutdown(), "Executor not shutdown");
    }

    @Test
    public void testAddInitializerAfterStart_1_oe() throws ConcurrentException {
        initializer.start();
        assertThrows( IllegalStateException.class, () -> initializer.addInitializer(CHILD_INIT, new ChildBackgroundInitializer()), "Could add initializer after start()!");
    }

    @Test
    public void testResultGetInitializerUnknown_1_oe() throws ConcurrentException {
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = checkInitialize();
        assertThrows(NoSuchElementException.class, () -> res.getInitializer("unknown"));
    }

    @Test
    public void testResultGetResultObjectUnknown_1_oe() throws ConcurrentException {
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = checkInitialize();
        assertThrows(NoSuchElementException.class, () -> res.getResultObject("unknown"));
    }

    @Test
    public void testResultGetExceptionUnknown_1_oe() throws ConcurrentException {
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = checkInitialize();
        assertThrows(NoSuchElementException.class, () -> res.getException("unknown"));
    }

    @Test
    public void testResultIsExceptionUnknown_1_oe() throws ConcurrentException {
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = checkInitialize();
        assertThrows(NoSuchElementException.class, () -> res.isException("unknown"));
    }

    @Test
    public void testResultInitializerNamesModify_1_oe() throws ConcurrentException {
        checkInitialize();
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        final Iterator<String> it = res.initializerNames().iterator();
        it.next();
        assertThrows(UnsupportedOperationException.class, it::remove);
    }

    @Test
    public void testInitializeRuntimeEx_1_oe() {
        final ChildBackgroundInitializer child = new ChildBackgroundInitializer();
        child.ex = new RuntimeException();
        initializer.addInitializer(CHILD_INIT, child);
        initializer.start();
        final Exception ex = assertThrows(Exception.class, initializer::get);
    }

    @Test
    public void testInitializeEx_1_oe() throws ConcurrentException {
        final ChildBackgroundInitializer child = new ChildBackgroundInitializer();
        child.ex = new Exception();
        initializer.addInitializer(CHILD_INIT, child);
        initializer.start();
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        assertTrue(res.isException(CHILD_INIT), "No exception flag");
    }

    @Test
    public void testInitializeEx_2_oe() throws ConcurrentException {
        final ChildBackgroundInitializer child = new ChildBackgroundInitializer();
        child.ex = new Exception();
        initializer.addInitializer(CHILD_INIT, child);
        initializer.start();
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        // removed other assertion
        assertNull(res.getResultObject(CHILD_INIT), "Got a results object");
    }

    @Test
    public void testInitializeEx_3_oe() throws ConcurrentException {
        final ChildBackgroundInitializer child = new ChildBackgroundInitializer();
        child.ex = new Exception();
        initializer.addInitializer(CHILD_INIT, child);
        initializer.start();
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        // removed other assertion
        // removed other assertion
        final ConcurrentException cex = res.getException(CHILD_INIT);
        assertEquals(child.ex, cex.getCause(), "Wrong cause");
    }

    @Test
    public void testInitializeResultsIsSuccessfulTrue_1_oe()
            throws ConcurrentException {
        final ChildBackgroundInitializer child = new ChildBackgroundInitializer();
        initializer.addInitializer(CHILD_INIT, child);
        initializer.start();
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        assertTrue(res.isSuccessful(), "Wrong success flag");
    }

    @Test
    public void testInitializeResultsIsSuccessfulFalse_1_oe()
            throws ConcurrentException {
        final ChildBackgroundInitializer child = new ChildBackgroundInitializer();
        child.ex = new Exception();
        initializer.addInitializer(CHILD_INIT, child);
        initializer.start();
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        assertFalse(res.isSuccessful(), "Wrong success flag");
    }

    @Test
    public void testInitializeNested_1_oe() throws ConcurrentException {
        final String nameMulti = "multiChildInitializer";
        initializer
                .addInitializer(CHILD_INIT, new ChildBackgroundInitializer());
        final MultiBackgroundInitializer mi2 = new MultiBackgroundInitializer();
        final int count = 3;
        for (int i = 0; i < count; i++) {
            mi2
                    .addInitializer(CHILD_INIT + i,
                            new ChildBackgroundInitializer());
        }
        initializer.addInitializer(nameMulti, mi2);
        initializer.start();
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        final ExecutorService exec = initializer.getActiveExecutor();
        checkChild(res.getInitializer(CHILD_INIT), exec);
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res2 = (MultiBackgroundInitializer.MultiBackgroundInitializerResults) res
                .getResultObject(nameMulti);
        assertEquals(count, res2.initializerNames().size(), "Wrong number of initializers");
    }

    @Test
    public void testInitializeNested_2_oe() throws ConcurrentException {
        final String nameMulti = "multiChildInitializer";
        initializer
                .addInitializer(CHILD_INIT, new ChildBackgroundInitializer());
        final MultiBackgroundInitializer mi2 = new MultiBackgroundInitializer();
        final int count = 3;
        for (int i = 0; i < count; i++) {
            mi2
                    .addInitializer(CHILD_INIT + i,
                            new ChildBackgroundInitializer());
        }
        initializer.addInitializer(nameMulti, mi2);
        initializer.start();
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res = initializer
                .get();
        final ExecutorService exec = initializer.getActiveExecutor();
        checkChild(res.getInitializer(CHILD_INIT), exec);
        final MultiBackgroundInitializer.MultiBackgroundInitializerResults res2 = (MultiBackgroundInitializer.MultiBackgroundInitializerResults) res
                .getResultObject(nameMulti);
        // removed other assertion
        for (int i = 0; i < count; i++) {
            checkChild(res2.getInitializer(CHILD_INIT + i), exec);
        }
        assertTrue(exec.isShutdown(), "Executor not shutdown");
    }

}
