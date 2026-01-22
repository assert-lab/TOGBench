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

package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;


class ObjectsTest_OE25Dev {

    public static class TestableSupplier<O> implements Supplier<O> {
        private final Supplier<O> supplier;
        private boolean invoked;

        TestableSupplier(final Supplier<O> pSupplier) {
            this.supplier = pSupplier;
        }

        @Override
        public O get() {
            invoked = true;
            return supplier.get();
        }

        public boolean isInvoked() {
            return invoked;
        }
    }

    public static class TestableFailableSupplier<O, E extends Exception> implements FailableSupplier<O, E> {
        private final FailableSupplier<O, E> supplier;
        private boolean invoked;

        TestableFailableSupplier(final FailableSupplier<O, E> pSupplier) {
            this.supplier = pSupplier;
        }

        @Override
        public O get() throws E {
            invoked = true;
            return supplier.get();
        }

        public boolean isInvoked() {
            return invoked;
        }
    }

    @Test
    void testRequireNonNullObject_1_oe() {
        assertSame("foo", Objects.requireNonNull("foo"));
    }

    @Test
    void testRequireNonNullObject_3_oe() {
        // removed other assertion
        try {
            Objects.requireNonNull(null);
            // removed other assertion
        } catch (final NullPointerException e) {
            assertEquals("The value must not be null.", e.getMessage());
    }
    }

    @Test
    void testRequireNonNullObjectString_1_oe() {
        assertSame("foo", Objects.requireNonNull("foo", "bar"));
    }

    @Test
    void testRequireNonNullObjectString_3_oe() {
        // removed other assertion
        try {
            Objects.requireNonNull(null, "bar");
            // removed other assertion
        } catch (final NullPointerException e) {
            assertEquals("bar", e.getMessage());
    }
    }

    @Test
    void testRequireNonNullObjectSupplierString_1_oe() {
        final TestableSupplier<String> supplier = new TestableSupplier<>(() -> "bar");
        assertSame("foo", Objects.requireNonNull("foo", supplier));
    }

    @Test
    void testRequireNonNullObjectSupplierString_2_oe() {
        final TestableSupplier<String> supplier = new TestableSupplier<>(() -> "bar");
        // removed other assertion
        assertFalse(supplier.isInvoked());
    }

    @Test
    void testRequireNonNullObjectSupplierString_4_oe() {
        final TestableSupplier<String> supplier = new TestableSupplier<>(() -> "bar");
        // removed other assertion
        // removed other assertion
        try {
            Objects.requireNonNull(null, supplier);
            // removed other assertion
        } catch (final NullPointerException e) {
            assertEquals("bar", e.getMessage());
    }
    }

    @Test
    void testRequireNonNullObjectSupplierString_5_oe() {
        final TestableSupplier<String> supplier = new TestableSupplier<>(() -> "bar");
        // removed other assertion
        // removed other assertion
        try {
            Objects.requireNonNull(null, supplier);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            assertTrue(supplier.isInvoked());
    }
    }

    @Test
    void testRequireNonNullObjectFailableSupplierString_1_oe() {
        final TestableFailableSupplier<String, ?> supplier = new TestableFailableSupplier<>(() -> null);
        assertSame("foo", Objects.requireNonNull("foo", supplier));
    }

    @Test
    void testRequireNonNullObjectFailableSupplierString_2_oe() {
        final TestableFailableSupplier<String, ?> supplier = new TestableFailableSupplier<>(() -> null);
        // removed other assertion
        assertFalse(supplier.isInvoked());
    }

    @Test
    void testRequireNonNullObjectFailableSupplierString_4_oe() {
        final TestableFailableSupplier<String, ?> supplier = new TestableFailableSupplier<>(() -> null);
        // removed other assertion
        // removed other assertion
        try {
            Objects.requireNonNull(null, supplier);
            // removed other assertion
        } catch (final NullPointerException e) {
            assertEquals("The supplier must not return null.", e.getMessage());
    }
    }

    @Test
    void testRequireNonNullObjectFailableSupplierString_5_oe() {
        final TestableFailableSupplier<String, ?> supplier = new TestableFailableSupplier<>(() -> null);
        // removed other assertion
        // removed other assertion
        try {
            Objects.requireNonNull(null, supplier);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            assertTrue(supplier.isInvoked());
    }
    }

    @Test
    void testRequireNonNullObjectFailableSupplierString_7_oe() {
        final TestableFailableSupplier<String, ?> supplier = new TestableFailableSupplier<>(() -> null);
        // removed other assertion
        // removed other assertion
        try {
            Objects.requireNonNull(null, supplier);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            // removed other assertion
        }
        final TestableFailableSupplier<String, ?> supplier2 = new TestableFailableSupplier<>(() -> null);
        try {
            Objects.requireNonNull(null, supplier2);
            // removed other assertion
        } catch (final NullPointerException e) {
            assertEquals("The supplier must not return null.", e.getMessage());
    }
    }

    @Test
    void testRequireNonNullObjectFailableSupplierString_8_oe() {
        final TestableFailableSupplier<String, ?> supplier = new TestableFailableSupplier<>(() -> null);
        // removed other assertion
        // removed other assertion
        try {
            Objects.requireNonNull(null, supplier);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            // removed other assertion
        }
        final TestableFailableSupplier<String, ?> supplier2 = new TestableFailableSupplier<>(() -> null);
        try {
            Objects.requireNonNull(null, supplier2);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            assertTrue(supplier2.isInvoked());
    }
    }

    @Test
    void testRequireNonNullObjectFailableSupplierString_9_oe() {
        final TestableFailableSupplier<String, ?> supplier = new TestableFailableSupplier<>(() -> null);
        // removed other assertion
        // removed other assertion
        try {
            Objects.requireNonNull(null, supplier);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            // removed other assertion
        }
        final TestableFailableSupplier<String, ?> supplier2 = new TestableFailableSupplier<>(() -> null);
        try {
            Objects.requireNonNull(null, supplier2);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            // removed other assertion
        }
        final TestableFailableSupplier<String, ?> supplier3 = new TestableFailableSupplier<>(() -> "bar");
        assertSame("bar", Objects.requireNonNull(null, supplier3));
    }

    @Test
    void testRequireNonNullObjectFailableSupplierString_11_oe() {
        final TestableFailableSupplier<String, ?> supplier = new TestableFailableSupplier<>(() -> null);
        // removed other assertion
        // removed other assertion
        try {
            Objects.requireNonNull(null, supplier);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            // removed other assertion
        }
        final TestableFailableSupplier<String, ?> supplier2 = new TestableFailableSupplier<>(() -> null);
        try {
            Objects.requireNonNull(null, supplier2);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            // removed other assertion
        }
        final TestableFailableSupplier<String, ?> supplier3 = new TestableFailableSupplier<>(() -> "bar");
        // removed other assertion
        final RuntimeException rte = new RuntimeException();
        final TestableFailableSupplier<String, ?> supplier4 = new TestableFailableSupplier<>(() -> {
            throw rte;
        });
        try {
            Objects.requireNonNull(null, supplier4);
            // removed other assertion
        } catch (final RuntimeException e) {
            assertSame(rte, e);
    }
    }

    @Test
    void testRequireNonNullObjectFailableSupplierString_12_oe() {
        final TestableFailableSupplier<String, ?> supplier = new TestableFailableSupplier<>(() -> null);
        // removed other assertion
        // removed other assertion
        try {
            Objects.requireNonNull(null, supplier);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            // removed other assertion
        }
        final TestableFailableSupplier<String, ?> supplier2 = new TestableFailableSupplier<>(() -> null);
        try {
            Objects.requireNonNull(null, supplier2);
            // removed other assertion
        } catch (final NullPointerException e) {
            // removed other assertion
            // removed other assertion
        }
        final TestableFailableSupplier<String, ?> supplier3 = new TestableFailableSupplier<>(() -> "bar");
        // removed other assertion
        final RuntimeException rte = new RuntimeException();
        final TestableFailableSupplier<String, ?> supplier4 = new TestableFailableSupplier<>(() -> {
            throw rte;
        });
        try {
            Objects.requireNonNull(null, supplier4);
            // removed other assertion
        } catch (final RuntimeException e) {
            // removed other assertion
            assertTrue(supplier4.isInvoked());
    }
    }

}
