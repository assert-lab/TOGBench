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

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class MemoizerTest_OE25Dev {

    private Computable<Integer, Integer> computable;

    @BeforeEach
    public void setUpComputableMock() {
        computable = EasyMock.mock(Computable.class);
    }

    @Test
    public void testOnlyCallComputableOnceIfDoesNotThrowException_1_oe() throws Exception {
        final Integer input = 1;
        final Memoizer<Integer, Integer> memoizer = new Memoizer<>(computable);
        expect(computable.compute(input)).andReturn(input);
        replay(computable);

        assertEquals(input, memoizer.compute(input), "Should call computable first time");
    }

    @Test
    public void testOnlyCallComputableOnceIfDoesNotThrowException_2_oe() throws Exception {
        final Integer input = 1;
        final Memoizer<Integer, Integer> memoizer = new Memoizer<>(computable);
        expect(computable.compute(input)).andReturn(input);
        replay(computable);

        // removed other assertion
        assertEquals(input, memoizer.compute(input), "Should not call the computable the second time");
    }

    @Test
    public void testDefaultBehaviourNotToRecalculateExecutionExceptions_1_oe() throws Exception {
        final Integer input = 1;
        final Memoizer<Integer, Integer> memoizer = new Memoizer<>(computable);
        final InterruptedException interruptedException = new InterruptedException();
        expect(computable.compute(input)).andThrow(interruptedException);
        replay(computable);

        try {
    memoizer.compute(input);
    fail("Throwable");
} catch (Throwable e) {
}
    }

    @Test
    public void testDefaultBehaviourNotToRecalculateExecutionExceptions_2_oe() throws Exception {
        final Integer input = 1;
        final Memoizer<Integer, Integer> memoizer = new Memoizer<>(computable);
        final InterruptedException interruptedException = new InterruptedException();
        expect(computable.compute(input)).andThrow(interruptedException);
        replay(computable);

        // removed other assertion
        try {
    memoizer.compute(input);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testDoesNotRecalculateWhenSetToFalse_1_oe() throws Exception {
        final Integer input = 1;
        final Memoizer<Integer, Integer> memoizer = new Memoizer<>(computable, false);
        final InterruptedException interruptedException = new InterruptedException();
        expect(computable.compute(input)).andThrow(interruptedException);
        replay(computable);

        try {
    memoizer.compute(input);
    fail("Throwable");
} catch (Throwable e) {
}
    }

    @Test
    public void testDoesNotRecalculateWhenSetToFalse_2_oe() throws Exception {
        final Integer input = 1;
        final Memoizer<Integer, Integer> memoizer = new Memoizer<>(computable, false);
        final InterruptedException interruptedException = new InterruptedException();
        expect(computable.compute(input)).andThrow(interruptedException);
        replay(computable);

        // removed other assertion
        try {
    memoizer.compute(input);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testDoesRecalculateWhenSetToTrue_1_oe() throws Exception {
        final Integer input = 1;
        final Integer answer = 3;
        final Memoizer<Integer, Integer> memoizer = new Memoizer<>(computable, true);
        final InterruptedException interruptedException = new InterruptedException();
        expect(computable.compute(input)).andThrow(interruptedException).andReturn(answer);
        replay(computable);

        try {
    memoizer.compute(input);
    fail("Throwable");
} catch (Throwable e) {
}
    }

    @Test
    public void testWhenComputableThrowsRuntimeException_1_oe() throws Exception {
        final Integer input = 1;
        final Memoizer<Integer, Integer> memoizer = new Memoizer<>(computable);
        final RuntimeException runtimeException = new RuntimeException("Some runtime exception");
        expect(computable.compute(input)).andThrow(runtimeException);
        replay(computable);

        try {
    memoizer.compute(input);
    fail("RuntimeException");
} catch (RuntimeException e) {
}
    }

    @Test
    public void testWhenComputableThrowsError_1_oe() throws Exception {
        final Integer input = 1;
        final Memoizer<Integer, Integer> memoizer = new Memoizer<>(computable);
        final Error error = new Error();
        expect(computable.compute(input)).andThrow(error);
        replay(computable);

        try {
    memoizer.compute(input);
    fail("Error");
} catch (Error e) {
}
    }

}
