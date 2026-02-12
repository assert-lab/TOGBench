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
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ThreadFactory;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@code BasicThreadFactory}.
 */
public class BasicThreadFactoryTest_OE25Dev {
    /** Constant for the test naming pattern. */
    private static final String PATTERN = "testThread-%d";

    /** The builder for creating a thread factory. */
    private BasicThreadFactory.Builder builder;

    @BeforeEach
    public void setUp() {
        builder = new BasicThreadFactory.Builder();
    }

    /**
     * Tests the default options of a thread factory.
     *
     * @param factory the factory to be checked
     */
    private void checkFactoryDefaults(final BasicThreadFactory factory) {
        assertNull(factory.getNamingPattern(), "Got a naming pattern");
        assertNull(factory.getUncaughtExceptionHandler(), "Got an exception handler");
        assertNull(factory.getPriority(), "Got a priority");
        assertNull(factory.getDaemonFlag(), "Got a daemon flag");
        assertNotNull(factory.getWrappedFactory(), "No wrapped factory");
    }

    /**
     * Tests the default values used by the builder.
     */
    @Test
    public void testBuildDefaults() {
        final BasicThreadFactory factory = builder.build();
        checkFactoryDefaults(factory);
    }

    /**
     * Tries to set a null naming pattern.
     */

    /**
     * Tries to set a null wrapped factory.
     */

    /**
     * Tries to set a null exception handler.
     */

    /**
     * Tests the reset() method of the builder.
     */

    /**
     * Tests whether reset() is automatically called after build().
     */
    @Test
    public void testBuilderResetAfterBuild() {
        builder.wrappedFactory(EasyMock.createNiceMock(ThreadFactory.class))
                .namingPattern(PATTERN).daemon(true).build();
        checkFactoryDefaults(builder.build());
    }

    /**
     * Tests whether the naming pattern is applied to new threads.
     */

    /**
     * Tests whether the thread name is not modified if no naming pattern is
     * set.
     */

    /**
     * Helper method for testing whether the daemon flag is taken into account.
     *
     * @param flag the value of the flag
     */
    private void checkDaemonFlag(final boolean flag) {
        final ThreadFactory wrapped = EasyMock.createMock(ThreadFactory.class);
        final Runnable r = EasyMock.createMock(Runnable.class);
        final Thread t = new Thread();
        EasyMock.expect(wrapped.newThread(r)).andReturn(t);
        EasyMock.replay(wrapped, r);
        final BasicThreadFactory factory = builder.wrappedFactory(wrapped).daemon(
                flag).build();
        assertSame(t, factory.newThread(r), "Wrong thread");
        assertEquals(flag, t.isDaemon(), "Wrong daemon flag");
        EasyMock.verify(wrapped, r);
    }

    /**
     * Tests whether daemon threads can be created.
     */
    @Test
    public void testNewThreadDaemonTrue() {
        checkDaemonFlag(true);
    }

    /**
     * Tests whether the daemon status of new threads can be turned off.
     */
    @Test
    public void testNewThreadDaemonFalse() {
        checkDaemonFlag(false);
    }

    /**
     * Tests whether the daemon flag is not touched on newly created threads if
     * it is not specified.
     */

    /**
     * Tests whether the priority is set on newly created threads.
     */

    /**
     * Tests whether the original priority is not changed if no priority is
     * specified.
     */

    /**
     * Tests whether the exception handler is set if one is provided.
     */

    /**
     * Tests whether the original exception handler is not touched if none is
     * specified.
     */

    @Test
    public void testBuildNamingPatternNull_1_oe() throws Exception {
        try {
    builder.namingPattern(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testBuildWrappedFactoryNull_1_oe() throws Exception {
        try {
    builder.wrappedFactory(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testBuildUncaughtExceptionHandlerNull_1_oe() throws Exception {
        try {
    builder.uncaughtExceptionHandler(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
