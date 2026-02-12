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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link ConcurrentUtils}.
 */
public class ConcurrentUtilsTest_OE25Dev {
    /**
     * Tests creating a ConcurrentException with a runtime exception as cause.
     */

    /**
     * Tests creating a ConcurrentException with an error as cause.
     */

    /**
     * Tests creating a ConcurrentException with null as cause.
     */

    /**
     * Tries to create a ConcurrentRuntimeException with a runtime as cause.
     */

    /**
     * Tries to create a ConcurrentRuntimeException with an error as cause.
     */

    /**
     * Tries to create a ConcurrentRuntimeException with null as cause.
     */

    /**
     * Tests extractCause() for a null exception.
     */

    /**
     * Tests extractCause() if the cause of the passed in exception is null.
     */

    /**
     * Tests extractCause() if the cause is an error.
     */

    /**
     * Tests extractCause() if the cause is an unchecked exception.
     */

    /**
     * Tests extractCause() if the cause is a checked exception.
     */

    /**
     * Tests extractCauseUnchecked() for a null exception.
     */

    /**
     * Tests extractCauseUnchecked() if the cause of the passed in exception is null.
     */

    /**
     * Tests extractCauseUnchecked() if the cause is an error.
     */

    /**
     * Tests extractCauseUnchecked() if the cause is an unchecked exception.
     */

    /**
     * Tests extractCauseUnchecked() if the cause is a checked exception.
     */

    /**
     * Tests handleCause() if the cause is an error.
     */

    /**
     * Tests handleCause() if the cause is an unchecked exception.
     */

    /**
     * Tests handleCause() if the cause is a checked exception.
     */

    /**
     * Tests handleCause() for a null parameter or a null cause. In this case
     * the method should do nothing. We can only test that no exception is
     * thrown.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */
    @Test
    public void testHandleCauseNull() throws ConcurrentException {
        ConcurrentUtils.handleCause(null);
        ConcurrentUtils.handleCause(new ExecutionException("Test", null));
    }

    /**
     * Tests handleCauseUnchecked() if the cause is an error.
     */

    /**
     * Tests handleCauseUnchecked() if the cause is an unchecked exception.
     */

    /**
     * Tests handleCauseUnchecked() if the cause is a checked exception.
     */

    /**
     * Tests handleCauseUnchecked() for a null parameter or a null cause. In
     * this case the method should do nothing. We can only test that no
     * exception is thrown.
     */
    @Test
    public void testHandleCauseUncheckedNull() {
        ConcurrentUtils.handleCauseUnchecked(null);
        ConcurrentUtils.handleCauseUnchecked(new ExecutionException("Test",
                null));
    }

    //-----------------------------------------------------------------------
    /**
     * Tests initialize() for a null argument.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests a successful initialize() operation.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests initializeUnchecked() for a null argument.
     */

    /**
     * Tests creating ConcurrentRuntimeException with no arguments.
     */

    /**
     * Tests a successful initializeUnchecked() operation.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests whether exceptions are correctly handled by initializeUnchecked().
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    //-----------------------------------------------------------------------
    /**
     * Tests constant future.
     *
     * @throws java.lang.Exception so we don't have to catch it
     */

    /**
     * Tests constant future.
     *
     * @throws java.lang.Exception so we don't have to catch it
     */

    //-----------------------------------------------------------------------
    /**
     * Tests putIfAbsent() if the map contains the key in question.
     */

    /**
     * Tests putIfAbsent() if the map does not contain the key in question.
     */

    /**
     * Tests putIfAbsent() if a null map is passed in.
     */

    /**
     * Tests createIfAbsent() if the key is found in the map.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests createIfAbsent() if the map does not contain the key in question.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests createIfAbsent() if a null map is passed in.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests createIfAbsent() if a null initializer is passed in.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    /**
     * Tests createIfAbsentUnchecked() if no exception is thrown.
     */

    /**
     * Tests createIfAbsentUnchecked() if an exception is thrown.
     *
     * @throws org.apache.commons.lang3.concurrent.ConcurrentException so we don't have to catch it
     */

    @Test
    public void testConcurrentExceptionCauseUnchecked_1_oe() throws Exception {
        try {
    new ConcurrentException(new RuntimeException());
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConcurrentExceptionCauseError_1_oe() throws Exception {
        try {
    new ConcurrentException("An error", new Error());
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConcurrentExceptionCauseNull_1_oe() throws Exception {
        try {
    new ConcurrentException(null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConcurrentRuntimeExceptionCauseUnchecked_1_oe() throws Exception {
        try {
    new ConcurrentRuntimeException(new RuntimeException());
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConcurrentRuntimeExceptionCauseError_1_oe() throws Exception {
        try {
    new ConcurrentRuntimeException("An error", new Error());
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConcurrentRuntimeExceptionCauseNull_1_oe() throws Exception {
        try {
    new ConcurrentRuntimeException(null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testExtractCauseError_1_oe() {
        final Error err = new AssertionError("Test");
        final AssertionError e =
                assertThrows(AssertionError.class, () -> ConcurrentUtils.extractCause(new ExecutionException(err)));
    }

    @Test
    public void testExtractCauseUncheckedException_1_oe() throws Exception {
        final RuntimeException rex = new RuntimeException("Test");
        try {
    ConcurrentUtils.extractCause(new ExecutionException(rex));
    org.junit.jupiter.api.Assertions.fail("RuntimeException");
} catch (RuntimeException e) {
}
    }

    @Test
    public void testExtractCauseUncheckedError_1_oe() {
        final Error err = new AssertionError("Test");
        final Error e = assertThrows(Error.class, () -> ConcurrentUtils.extractCauseUnchecked(new ExecutionException(err)));
    }

    @Test
    public void testExtractCauseUncheckedUncheckedException_1_oe() {
        final RuntimeException rex = new RuntimeException("Test");
        final RuntimeException r =
                assertThrows(RuntimeException.class, () -> ConcurrentUtils.extractCauseUnchecked(new ExecutionException(rex)));
    }

    @Test
    public void testHandleCauseError_1_oe() {
        final Error err = new AssertionError("Test");
        final Error e = assertThrows(Error.class, () -> ConcurrentUtils.handleCause(new ExecutionException(err)));
    }

    @Test
    public void testHandleCauseUncheckedException_1_oe() {
        final RuntimeException rex = new RuntimeException("Test");
        final RuntimeException r =
                assertThrows(RuntimeException.class, () -> ConcurrentUtils.handleCause(new ExecutionException(rex)));
    }

    @Test
    public void testHandleCauseChecked_1_oe() {
        final Exception ex = new Exception("Test");
        final ConcurrentException cex =
                assertThrows(ConcurrentException.class, () -> ConcurrentUtils.handleCause(new ExecutionException(ex)));
    }

    @Test
    public void testHandleCauseUncheckedError_1_oe() {
        final Error err = new AssertionError("Test");
        final Error e = assertThrows(Error.class, () -> ConcurrentUtils.handleCauseUnchecked(new ExecutionException(err)));
    }

    @Test
    public void testHandleCauseUncheckedUncheckedException_1_oe() {
        final RuntimeException rex = new RuntimeException("Test");
        final RuntimeException r =
                assertThrows(RuntimeException.class, () -> ConcurrentUtils.handleCauseUnchecked(new ExecutionException(rex)));
    }

    @Test
    public void testHandleCauseUncheckedChecked_1_oe() {
        final Exception ex = new Exception("Test");
        final ConcurrentRuntimeException crex =
                assertThrows(ConcurrentRuntimeException.class, () -> ConcurrentUtils.handleCauseUnchecked(new ExecutionException(ex)));
    }

    @Test
    public void testInitializeUncheckedEx_1_oe() throws ConcurrentException {
        @SuppressWarnings("unchecked")
        final
        ConcurrentInitializer<Object> init = EasyMock
                .createMock(ConcurrentInitializer.class);
        final Exception cause = new Exception();
        EasyMock.expect(init.get()).andThrow(new ConcurrentException(cause));
        EasyMock.replay(init);
        final ConcurrentRuntimeException crex =
                assertThrows(ConcurrentRuntimeException.class, () -> ConcurrentUtils.initializeUnchecked(init));
    }

    @Test
    public void testCreateIfAbsentUncheckedException_1_oe()
            throws ConcurrentException {
        @SuppressWarnings("unchecked")
        final
        ConcurrentInitializer<Integer> init = EasyMock
                .createMock(ConcurrentInitializer.class);
        final Exception ex = new Exception();
        EasyMock.expect(init.get()).andThrow(new ConcurrentException(ex));
        EasyMock.replay(init);
        final ConcurrentRuntimeException crex =
                assertThrows( ConcurrentRuntimeException.class, () -> ConcurrentUtils.createIfAbsentUnchecked(new ConcurrentHashMap<>(), "test", init));
    }

}
