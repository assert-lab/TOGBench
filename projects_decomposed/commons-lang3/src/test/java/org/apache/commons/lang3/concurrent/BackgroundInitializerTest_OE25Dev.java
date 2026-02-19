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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.ThreadUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class BackgroundInitializerTest_OE25Dev {
    /**
     * Helper method for checking whether the initialize() method was correctly
     * called. start() must already have been invoked.
     *
     * @param init the initializer to test
     */
    private void checkInitialize(final BackgroundInitializerTestImpl init) throws ConcurrentException {
        final Integer result = init.get();
        assertEquals(1, result.intValue(), "Wrong result");
        assertEquals(1, init.initializeCalls, "Wrong number of invocations");
        assertNotNull(init.getFuture(), "No future");
    }

    /**
     * Tests whether initialize() is invoked.
     */
    @Test
    public void testInitialize() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        checkInitialize(init);
    }

    /**
     * Tries to obtain the executor before start(). It should not have been
     * initialized yet.
     */

    /**
     * Tests whether an external executor is correctly detected.
     */
    @Test
    public void testGetActiveExecutorExternal() throws InterruptedException, ConcurrentException {
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        try {
            final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl(
                    exec);
            init.start();
            assertSame(exec, init.getActiveExecutor(), "Wrong executor");
            checkInitialize(init);
        } finally {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    /**
     * Tests getActiveExecutor() for a temporary executor.
     */

    /**
     * Tests the execution of the background task if a temporary executor has to
     * be created.
     */

    /**
     * Tests whether an external executor can be set using the
     * setExternalExecutor() method.
     */
    @Test
    public void testSetExternalExecutor() throws ConcurrentException {
        final ExecutorService exec = Executors.newCachedThreadPool();
        try {
            final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
            init.setExternalExecutor(exec);
            assertEquals(exec, init.getExternalExecutor(), "Wrong executor service");
            assertTrue(init.start(), "Wrong result of start()");
            assertSame(exec, init.getActiveExecutor(), "Wrong active executor");
            checkInitialize(init);
            assertFalse(exec.isShutdown(), "Executor was shutdown");
        } finally {
            exec.shutdown();
        }
    }

    /**
     * Tests that setting an executor after start() causes an exception.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException because the test implementation may throw it
     */
    @Test
    public void testSetExternalExecutorAfterStart() throws ConcurrentException, InterruptedException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        try {
            assertThrows(IllegalStateException.class, () -> init.setExternalExecutor(exec));
            init.get();
        } finally {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    /**
     * Tests invoking start() multiple times. Only the first invocation should
     * have an effect.
     */

    /**
     * Tests calling get() before start(). This should cause an exception.
     */

    /**
     * Tests the get() method if background processing causes a runtime
     * exception.
     */

    /**
     * Tests the get() method if background processing causes a checked
     * exception.
     */

    /**
     * Tests the get() method if waiting for the initialization is interrupted.
     *
     * @throws java.lang.InterruptedException because we're making use of Java's concurrent API
     */

    /**
     * Tests isStarted() before start() was called.
     */

    /**
     * Tests isStarted() after start().
     */

    /**
     * Tests isStarted() after the background task has finished.
     */

    /**
     * A concrete implementation of BackgroundInitializer. It also overloads
     * some methods that simplify testing.
     */
    private static class BackgroundInitializerTestImpl extends
            BackgroundInitializer<Integer> {
        /** An exception to be thrown by initialize(). */
        Exception ex;

        /** A flag whether the background task should sleep a while. */
        boolean shouldSleep;

        /** The number of invocations of initialize(). */
        volatile int initializeCalls;

        BackgroundInitializerTestImpl() {
        }

        BackgroundInitializerTestImpl(final ExecutorService exec) {
            super(exec);
        }

        /**
         * Records this invocation. Optionally throws an exception or sleeps a
         * while.
         *
         * @throws Exception in case of an error
         */
        @Override
        protected Integer initialize() throws Exception {
            if (ex != null) {
                throw ex;
            }
            if (shouldSleep) {
                ThreadUtils.sleep(Duration.ofMinutes(1));
            }
            return Integer.valueOf(++initializeCalls);
        }
    }

    @Test
    public void testGetActiveExecutorBeforeStart_1_oe() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        assertNull(init.getActiveExecutor(), "Got an executor");
    }

    @Test
    public void testGetActiveExecutorTemp_1_oe() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        assertNotNull(init.getActiveExecutor(), "No active executor");
    }

    @Test
    public void testInitializeTempExecutor_1_oe() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        assertTrue(init.start(), "Wrong result of start()");
    }

    @Test
    public void testStartMultipleTimes_1_oe() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        assertTrue(init.start(), "Wrong result for start()");
    }

    @Test
    public void testGetBeforeStart_1_oe() throws Exception {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        try {
    init.get();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testGetRuntimeException_1_oe() throws Exception {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        final RuntimeException rex = new RuntimeException();
        init.ex = rex;
        init.start();
        try {
    init.get();
    fail("Exception");
} catch (Exception e) {
}
    }

    @Test
    public void testGetCheckedException_1_oe() throws Exception {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        final Exception ex = new Exception();
        init.ex = ex;
        init.start();
        try {
    init.get();
    fail("ConcurrentException");
} catch (ConcurrentException e) {
}
    }

    @Test
    public void testGetInterruptedException_2_oe() throws InterruptedException {
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl(
                exec);
        final CountDownLatch latch1 = new CountDownLatch(1);
        init.shouldSleep = true;
        init.start();
        final AtomicReference<InterruptedException> iex = new AtomicReference<>();
        final Thread getThread = new Thread() {
            @Override
            public void run() {
                try {
                    init.get();
                } catch (final ConcurrentException cex) {
                    if (cex.getCause() instanceof InterruptedException) {
                        iex.set((InterruptedException) cex.getCause());
                    }
                } finally {
                    // removed other assertion
                    latch1.countDown();
                }
            }
        };
        getThread.start();
        getThread.interrupt();
        latch1.await();
        exec.shutdownNow();
        exec.awaitTermination(1, TimeUnit.SECONDS);
        assertNotNull(iex.get(), "No interrupted exception");
    }

    @Test
    public void testIsStartedFalse_1_oe() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        assertFalse(init.isStarted(), "Already started");
    }

    @Test
    public void testIsStartedTrue_1_oe() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        assertTrue(init.isStarted(), "Not started");
    }

    @Test
    public void testIsStartedAfterGet_1_oe() throws ConcurrentException {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        checkInitialize(init);
        assertTrue(init.isStarted(), "Not started");
    }

}
