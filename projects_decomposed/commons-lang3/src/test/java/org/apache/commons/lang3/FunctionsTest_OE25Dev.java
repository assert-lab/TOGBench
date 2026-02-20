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
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.Functions.FailableBiConsumer;
import org.apache.commons.lang3.Functions.FailableBiFunction;
import org.apache.commons.lang3.Functions.FailableCallable;
import org.apache.commons.lang3.Functions.FailableConsumer;
import org.apache.commons.lang3.Functions.FailableFunction;
import org.apache.commons.lang3.Functions.FailableSupplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FunctionsTest_OE25Dev {

    public static class CloseableObject {
        private boolean closed;

        public void close() {
            closed = true;
        }

        public boolean isClosed() {
            return closed;
        }

        public void reset() {
            closed = false;
        }

        public void run(final Throwable pTh) throws Throwable {
            if (pTh != null) {
                throw pTh;
            }
        }
    }

    public static class FailureOnOddInvocations {
        private static int invocations;

        static boolean failingBool() throws SomeException {
            throwOnOdd();
            return true;
        }

        static boolean testDouble(final double value) throws SomeException {
            throwOnOdd();
            return true;
        }

        static boolean testInt(final int value) throws SomeException {
            throwOnOdd();
            return true;
        }

        static boolean testLong(final long value) throws SomeException {
            throwOnOdd();
            return true;
        }

        private static void throwOnOdd() throws SomeException {
            final int i = ++invocations;
            if (i % 2 == 1) {
                throw new SomeException("Odd Invocation: " + i);
            }
        }

        FailureOnOddInvocations() throws SomeException {
            throwOnOdd();
        }

        boolean getAsBoolean() throws SomeException {
            throwOnOdd();
            return true;
        }
    }

    public static class SomeException extends Exception {

        private static final long serialVersionUID = -4965704778119283411L;

        private Throwable t;

        SomeException(final String message) {
            super(message);
        }

        public void setThrowable(final Throwable throwable) {
            t = throwable;
        }

        public void test() throws Throwable {
            if (t != null) {
                throw t;
            }
        }
    }

    public static class Testable<T, P> {
        private T acceptedObject;
        private P acceptedPrimitiveObject1;
        private P acceptedPrimitiveObject2;
        private Throwable throwable;

        Testable(final Throwable throwable) {
            this.throwable = throwable;
        }

        public T getAcceptedObject() {
            return acceptedObject;
        }

        public P getAcceptedPrimitiveObject1() {
            return acceptedPrimitiveObject1;
        }

        public P getAcceptedPrimitiveObject2() {
            return acceptedPrimitiveObject2;
        }

        public void setThrowable(final Throwable throwable) {
            this.throwable = throwable;
        }

        public void test() throws Throwable {
            test(throwable);
        }

        public Object test(final Object input1, final Object input2) throws Throwable {
            test(throwable);
            return acceptedObject;
        }

        public void test(final Throwable throwable) throws Throwable {
            if (throwable != null) {
                throw throwable;
            }
        }

        public boolean testAsBooleanPrimitive() throws Throwable {
            return testAsBooleanPrimitive(throwable);
        }

        public boolean testAsBooleanPrimitive(final Throwable throwable) throws Throwable {
            if (throwable != null) {
                throw throwable;
            }
            return false;
        }

        public double testAsDoublePrimitive() throws Throwable {
            return testAsDoublePrimitive(throwable);
        }

        public double testAsDoublePrimitive(final Throwable throwable) throws Throwable {
            if (throwable != null) {
                throw throwable;
            }
            return 0;
        }

        public Integer testAsInteger() throws Throwable {
            return testAsInteger(throwable);
        }

        public Integer testAsInteger(final Throwable throwable) throws Throwable {
            if (throwable != null) {
                throw throwable;
            }
            return 0;
        }

        public int testAsIntPrimitive() throws Throwable {
            return testAsIntPrimitive(throwable);
        }

        public int testAsIntPrimitive(final Throwable throwable) throws Throwable {
            if (throwable != null) {
                throw throwable;
            }
            return 0;
        }

        public long testAsLongPrimitive() throws Throwable {
            return testAsLongPrimitive(throwable);
        }

        public long testAsLongPrimitive(final Throwable throwable) throws Throwable {
            if (throwable != null) {
                throw throwable;
            }
            return 0;
        }

        public void testDouble(final double i) throws Throwable {
            test(throwable);
            acceptedPrimitiveObject1 = (P) ((Double) i);
        }

        public double testDoubleDouble(final double i, final double j) throws Throwable {
            test(throwable);
            acceptedPrimitiveObject1 = (P) ((Double) i);
            acceptedPrimitiveObject2 = (P) ((Double) j);
            return 3d;
        }

        public void testInt(final int i) throws Throwable {
            test(throwable);
            acceptedPrimitiveObject1 = (P) ((Integer) i);
        }

        public void testLong(final long i) throws Throwable {
            test(throwable);
            acceptedPrimitiveObject1 = (P) ((Long) i);
        }

        public void testObjDouble(final T object, final double i) throws Throwable {
            test(throwable);
            acceptedObject = object;
            acceptedPrimitiveObject1 = (P) ((Double) i);
        }

        public void testObjInt(final T object, final int i) throws Throwable {
            test(throwable);
            acceptedObject = object;
            acceptedPrimitiveObject1 = (P) ((Integer) i);
        }

        public void testObjLong(final T object, final long i) throws Throwable {
            test(throwable);
            acceptedObject = object;
            acceptedPrimitiveObject1 = (P) ((Long) i);
        }
    }

    @Test
    public void testConstructor() {
        // We allow this, which must have been an omission to make the ctor private.
        // We could make the ctor private in 4.0.
        new Functions();
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception. using the top level generic types
     * Object and Throwable.
     */
    @Test
    public void testThrows_FailableBiConsumer_Object_Throwable() {
        new Functions.FailableBiConsumer<Object, Object, Throwable>() {

            @Override
            public void accept(final Object object1, final Object object2) throws Throwable {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception using String and IOExceptions as
     * generic test types.
     */
    @Test
    public void testThrows_FailableBiConsumer_String_IOException() {
        new Functions.FailableBiConsumer<String, String, IOException>() {

            @Override
            public void accept(final String object1, final String object2) throws IOException {
                throw new IOException("test");

            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception. using the top level generic types
     * Object and Throwable.
     */
    @Test
    public void testThrows_FailableBiFunction_Object_Throwable() {
        new Functions.FailableBiFunction<Object, Object, Object, Throwable>() {

            @Override
            public Object apply(final Object input1, final Object input2) throws Throwable {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception using String and IOExceptions as
     * generic test types.
     */
    @Test
    public void testThrows_FailableBiFunction_String_IOException() {
        new Functions.FailableBiFunction<String, String, String, IOException>() {

            @Override
            public String apply(final String input1, final String input2) throws IOException {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception. using the top level generic types
     * Object and Throwable.
     */
    @Test
    public void testThrows_FailableBiPredicate_Object_Throwable() {
        new Functions.FailableBiPredicate<Object, Object, Throwable>() {

            @Override
            public boolean test(final Object object1, final Object object2) throws Throwable {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception using String and IOExceptions as
     * generic test types.
     */
    @Test
    public void testThrows_FailableBiPredicate_String_IOException() {
        new Functions.FailableBiPredicate<String, String, IOException>() {

            @Override
            public boolean test(final String object1, final String object2) throws IOException {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception. using the top level generic types
     * Object and Throwable.
     */
    @Test
    public void testThrows_FailableCallable_Object_Throwable() {
        new Functions.FailableCallable<Object, Throwable>() {

            @Override
            public Object call() throws Throwable {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception using String and IOExceptions as
     * generic test types.
     */
    @Test
    public void testThrows_FailableCallable_String_IOException() {
        new Functions.FailableCallable<String, IOException>() {

            @Override
            public String call() throws IOException {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception. using the top level generic types
     * Object and Throwable.
     */
    @Test
    public void testThrows_FailableConsumer_Object_Throwable() {
        new Functions.FailableConsumer<Object, Throwable>() {

            @Override
            public void accept(final Object object) throws Throwable {
                throw new IOException("test");

            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception using String and IOExceptions as
     * generic test types.
     */
    @Test
    public void testThrows_FailableConsumer_String_IOException() {
        new Functions.FailableConsumer<String, IOException>() {

            @Override
            public void accept(final String object) throws IOException {
                throw new IOException("test");

            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception. using the top level generic types
     * Object and Throwable.
     */
    @Test
    public void testThrows_FailableFunction_Object_Throwable() {
        new Functions.FailableFunction<Object, Object, Throwable>() {

            @Override
            public Object apply(final Object input) throws Throwable {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception using String and IOExceptions as
     * generic test types.
     */
    @Test
    public void testThrows_FailableFunction_String_IOException() {
        new Functions.FailableFunction<String, String, IOException>() {

            @Override
            public String apply(final String input) throws IOException {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception. using the top level generic types
     * Object and Throwable.
     */
    @Test
    public void testThrows_FailablePredicate_Object_Throwable() {
        new Functions.FailablePredicate<Object, Throwable>() {

            @Override
            public boolean test(final Object object) throws Throwable {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception using String and IOExceptions as
     * generic test types.
     */
    @Test
    public void testThrows_FailablePredicate_String_IOException() {
        new Functions.FailablePredicate<String, IOException>() {

            @Override
            public boolean test(final String object) throws IOException {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception. using the top level generic types
     * Object and Throwable.
     */
    @Test
    public void testThrows_FailableRunnable_Object_Throwable() {
        new Functions.FailableRunnable<Throwable>() {

            @Override
            public void run() throws Throwable {
                throw new IOException("test");

            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception using String and IOExceptions as
     * generic test types.
     */
    @Test
    public void testThrows_FailableRunnable_String_IOException() {
        new Functions.FailableRunnable<IOException>() {

            @Override
            public void run() throws IOException {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception. using the top level generic types
     * Object and Throwable.
     */
    @Test
    public void testThrows_FailableSupplier_Object_Throwable() {
        new Functions.FailableSupplier<Object, Throwable>() {

            @Override
            public Object get() throws Throwable {
                throw new IOException("test");
            }
        };
    }

    /**
     * Tests that our failable interface is properly defined to throw any exception using String and IOExceptions as
     * generic test types.
     */
    @Test
    public void testThrows_FailableSupplier_String_IOException() {
        new Functions.FailableSupplier<String, IOException>() {

            @Override
            public String get() throws IOException {
                throw new IOException("test");
            }
        };
    }

@Test
    public void testAcceptBiConsumer_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, ?> testable = new Testable<>(null);
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.accept(Testable::test, testable, ise));
    }

@Test
    public void testAcceptConsumer_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, ?> testable = new Testable<>(ise);
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.accept(Testable::test, testable));
    }

@Test
    public void testAcceptDoubleConsumer_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, Double> testable = new Testable<>(ise);
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.accept(testable::testDouble, 1d));
    }

@Test
    public void testAcceptDoubleConsumer_3_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, Double> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptDoubleConsumer_6_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, Double> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptIntConsumer_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, Integer> testable = new Testable<>(ise);
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.accept(testable::testInt, 1));
    }

@Test
    public void testAcceptIntConsumer_3_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, Integer> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptIntConsumer_6_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, Integer> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptLongConsumer_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, Long> testable = new Testable<>(ise);
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.accept(testable::testLong, 1L));
    }

@Test
    public void testAcceptLongConsumer_3_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, Long> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptLongConsumer_6_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, Long> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptObjDoubleConsumer_3_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Double> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedObject());
    }

@Test
    public void testAcceptObjDoubleConsumer_4_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Double> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptObjDoubleConsumer_7_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Double> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedObject());
    }

@Test
    public void testAcceptObjDoubleConsumer_8_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Double> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptObjIntConsumer_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Integer> testable = new Testable<>(ise);
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.accept(testable::testObjInt, "X", 1));
    }

@Test
    public void testAcceptObjIntConsumer_3_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Integer> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedObject());
    }

@Test
    public void testAcceptObjIntConsumer_4_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Integer> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptObjIntConsumer_7_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Integer> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedObject());
    }

@Test
    public void testAcceptObjIntConsumer_8_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Integer> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptObjLongConsumer_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Long> testable = new Testable<>(ise);
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.accept(testable::testObjLong, "X", 1L));
    }

@Test
    public void testAcceptObjLongConsumer_3_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Long> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedObject());
    }

@Test
    public void testAcceptObjLongConsumer_4_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Long> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testAcceptObjLongConsumer_7_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Long> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedObject());
    }

@Test
    public void testAcceptObjLongConsumer_8_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<String, Long> testable = new Testable<>(ise);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Error error = new OutOfMemoryError();
        testable.setThrowable(error);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(testable.getAcceptedPrimitiveObject1());
    }

@Test
    public void testApplyBiFunction_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, ?> testable = new Testable<>(null);
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.apply(Testable::testAsInteger, testable, ise));
    }

@Test
    public void testApplyFunction_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, ?> testable = new Testable<>(ise);
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.apply(Testable::testAsInteger, testable));
    }

@Test
    public void testAsCallable_1_oe() {
        FailureOnOddInvocations.invocations = 0;
        final FailableCallable<FailureOnOddInvocations, SomeException> failableCallable = FailureOnOddInvocations::new;
        final Callable<FailureOnOddInvocations> callable = Functions.asCallable(failableCallable);
        final UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, callable::call);
    }

@Test
    public void testAsConsumer_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, ?> testable = new Testable<>(ise);
        final Consumer<Testable<?, ?>> consumer = Functions.asConsumer(Testable::test);
        Throwable e = assertThrows(IllegalStateException.class, () -> consumer.accept(testable));
    }

@Test
    public void testAsRunnable_1_oe() {
        FailureOnOddInvocations.invocations = 0;
        final Runnable runnable = Functions.asRunnable(FailureOnOddInvocations::new);
        final UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, runnable::run);
    }

@Test
    public void testAsSupplier_1_oe() {
        FailureOnOddInvocations.invocations = 0;
        final FailableSupplier<FailureOnOddInvocations, Throwable> failableSupplier = FailureOnOddInvocations::new;
        final Supplier<FailureOnOddInvocations> supplier = Functions.asSupplier(failableSupplier);
        final UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, supplier::get);
    }

@Test
    public void testBiConsumer_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, ?> testable = new Testable<>(null);
        final FailableBiConsumer<Testable<?, ?>, Throwable, Throwable> failableBiConsumer = (t, th) -> {
            t.setThrowable(th);
            t.test();
        };
        final BiConsumer<Testable<?, ?>, Throwable> consumer = Functions.asBiConsumer(failableBiConsumer);
        Throwable e = assertThrows(IllegalStateException.class, () -> consumer.accept(testable, ise));
    }

@Test
    public void testBiFunction_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, ?> testable = new Testable<>(ise);
        final FailableBiFunction<Testable<?, ?>, Throwable, Integer, Throwable> failableBiFunction = (t, th) -> {
            t.setThrowable(th);
            return Integer.valueOf(t.testAsInteger());
        };
        final BiFunction<Testable<?, ?>, Throwable, Integer> biFunction = Functions.asBiFunction(failableBiFunction);
        Throwable e = assertThrows(IllegalStateException.class, () -> biFunction.apply(testable, ise));
    }

@Test
    @DisplayName("Test that asPredicate(FailableBiPredicate) is converted to -> BiPredicate ")
    public void testBiPredicate_1_oe() {
        FailureOnOddInvocations.invocations = 0;
        final Functions.FailableBiPredicate<Object, Object, Throwable> failableBiPredicate = (t1,
            t2) -> FailureOnOddInvocations.failingBool();
        final BiPredicate<?, ?> predicate = Functions.asBiPredicate(failableBiPredicate);
        final UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, () -> predicate.test(null, null));
    }

@Test
    public void testCallable_1_oe() {
        FailureOnOddInvocations.invocations = 0;
        final UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, () -> Functions.run(FailureOnOddInvocations::new));
    }

@Test
    public void testFunction_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, ?> testable = new Testable<>(ise);
        final FailableFunction<Throwable, Integer, Throwable> failableFunction = th -> {
            testable.setThrowable(th);
            return Integer.valueOf(testable.testAsInteger());
        };
        final Function<Throwable, Integer> function = Functions.asFunction(failableFunction);
        Throwable e = assertThrows(IllegalStateException.class, () -> function.apply(ise));
    }

@Test
    public void testGetFromSupplier_1_oe() {
        FailureOnOddInvocations.invocations = 0;
        final UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, () -> Functions.run(FailureOnOddInvocations::new));
    }

@Test
    public void testGetSupplier_1_oe() {
        final IllegalStateException ise = new IllegalStateException();
        final Testable<?, ?> testable = new Testable<>(ise);
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.get(testable::testAsInteger));
    }

@Test
    @DisplayName("Test that asPredicate(FailablePredicate) is converted to -> Predicate ")
    public void testPredicate_1_oe() {
        FailureOnOddInvocations.invocations = 0;
        final Functions.FailablePredicate<Object, Throwable> failablePredicate = t -> FailureOnOddInvocations
            .failingBool();
        final Predicate<?> predicate = Functions.asPredicate(failablePredicate);
        final UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, () -> predicate.test(null));
    }

@Test
    public void testRunnable_1_oe() {
        FailureOnOddInvocations.invocations = 0;
        final UndeclaredThrowableException e = assertThrows(UndeclaredThrowableException.class, () -> Functions.run(FailureOnOddInvocations::new));
    }

@Test
    public void testTryWithResources_1_oe() {
        final CloseableObject co = new CloseableObject();
        final FailableConsumer<Throwable, ? extends Throwable> consumer = co::run;
        final IllegalStateException ise = new IllegalStateException();
        Throwable e = assertThrows(IllegalStateException.class, () -> Functions.tryWithResources(() -> consumer.accept(ise), co::close));
    }

@Test
    public void testTryWithResources_7_oe() {
        final CloseableObject co = new CloseableObject();
        final FailableConsumer<Throwable, ? extends Throwable> consumer = co::run;
        final IllegalStateException ise = new IllegalStateException();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        co.reset();
        final Error error = new OutOfMemoryError();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        co.reset();
        final IOException ioe = new IOException("Unknown I/O error");
        final UncheckedIOException uioe = assertThrows(UncheckedIOException.class, () -> Functions.tryWithResources(() -> consumer.accept(ioe), co::close));
    }

}
