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

package org.apache.commons.pool2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.TestGenericKeyedObjectPool;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

/**
 * Unit tests for {@link PoolUtils}.
 *
 * TODO Replace our own mocking with a mocking library like Mockito.
 */
public class TestPoolUtils_OE25Dev {

    private static class MethodCallLogger implements InvocationHandler {
        private final List<String> calledMethods;

        MethodCallLogger(final List<String> calledMethods) {
            this.calledMethods = calledMethods;
        }

        @Override
        public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
            if (calledMethods == null) {
                return null;
            }
            calledMethods.add(method.getName());
            if (boolean.class.equals(method.getReturnType())) {
                return Boolean.FALSE;
            }
            if (int.class.equals(method.getReturnType())) {
                return Integer.valueOf(0);
            }
            if (long.class.equals(method.getReturnType())) {
                return Long.valueOf(0);
            }
            if (Object.class.equals(method.getReturnType())) {
                return new Object();
            }
            if (PooledObject.class.equals(method.getReturnType())) {
                return new DefaultPooledObject<>(new Object());
            }
            return null;
        }
    }

    /** Period between checks for minIdle tests. Increase this if you happen to get too many false failures. */
    private static final int CHECK_PERIOD = 300;

    /** Times to let the minIdle check run. */
    private static final int CHECK_COUNT = 4;

    /** Sleep time to let the minIdle tests run CHECK_COUNT times. */
    private static final int CHECK_SLEEP_PERIOD = CHECK_PERIOD * (CHECK_COUNT - 1) + CHECK_PERIOD / 2;

    @SuppressWarnings("unchecked")
    private static <T> T createProxy(final Class<T> clazz, final InvocationHandler handler) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, handler);
    }

    private static <T> T createProxy(final Class<T> clazz, final List<String> logger) {
        return createProxy(clazz, new MethodCallLogger(logger));
    }

    private static List<String> invokeEveryMethod(final KeyedObjectPool<Object,Object> kop) throws Exception {
        kop.addObject(null);
        kop.borrowObject(null);
        kop.clear();
        kop.clear(null);
        kop.close();
        kop.getNumActive();
        kop.getNumActive(null);
        kop.getNumIdle();
        kop.getNumIdle(null);
        kop.invalidateObject(null, new Object());
        kop.returnObject(null, new Object());
        kop.toString();

        return Arrays.asList("addObject", "borrowObject", "clear", "clear", "close", "getNumActive", "getNumActive",
                "getNumIdle", "getNumIdle", "invalidateObject", "returnObject", "toString");
    }

    private static <K, V> List<String> invokeEveryMethod(final KeyedPooledObjectFactory<K, V> kpof) throws Exception {
        kpof.activateObject(null, null);
        kpof.destroyObject(null, null);
        kpof.makeObject(null);
        kpof.passivateObject(null, null);
        kpof.validateObject(null, null);
        kpof.toString();

        return Arrays.asList("activateObject", "destroyObject", "makeObject", "passivateObject", "validateObject", "toString");
    }

    private static List<String> invokeEveryMethod(final ObjectPool<Object> op) throws Exception {
        op.addObject();
        op.borrowObject();
        op.clear();
        op.close();
        op.getNumActive();
        op.getNumIdle();
        op.invalidateObject(new Object());
        op.returnObject(new Object());
        op.toString();

        return Arrays.asList("addObject", "borrowObject", "clear", "close", "getNumActive", "getNumIdle", "invalidateObject",
                "returnObject", "toString");
    }

    private static <T> List<String> invokeEveryMethod(final PooledObjectFactory<T> pof) throws Exception {
        pof.activateObject(null);
        pof.destroyObject(null);
        pof.makeObject();
        pof.passivateObject(null);
        pof.validateObject(null);
        pof.toString();

        return Arrays.asList("activateObject", "destroyObject", "makeObject", "passivateObject", "validateObject", "toString");
    }

    @Test
    public void testCheckMinIdleKeyedObjectPoolKeys() throws Exception {
        // Because this isn't deterministic and you can get false failures, try more than once.
        AssertionFailedError afe = null;
        int triesLeft = 3;
        do {
            afe = null;
            final List<String> calledMethods = new ArrayList<>();
            try (@SuppressWarnings("unchecked")
            final KeyedObjectPool<String, Object> pool = createProxy(KeyedObjectPool.class, calledMethods)) {
                final Collection<String> keys = new ArrayList<>(2);
                keys.add("one");
                keys.add("two");
                // checks minIdle immediately
                final Map<String, TimerTask> tasks = PoolUtils.checkMinIdle(pool, keys, 1, CHECK_PERIOD);

                Thread.sleep(CHECK_SLEEP_PERIOD); // will check CHECK_COUNT more times.
                for (final TimerTask task : tasks.values()) {
                    task.cancel();
                }

                final List<String> expectedMethods = new ArrayList<>();
                for (int i = 0; i < CHECK_COUNT * keys.size(); i++) {
                    expectedMethods.add("getNumIdle");
                    expectedMethods.add("addObject");
                }
                assertEquals(expectedMethods, calledMethods); // may fail because of the thread scheduler
            } catch (final AssertionFailedError e) {
                afe = e;
            }
        } while (--triesLeft > 0 && afe != null);
        if (afe != null) {
            throw afe;
        }
    }

    @Test
    public void testCheckMinIdleKeyedObjectPoolKeysNulls() {
        try (@SuppressWarnings("unchecked")
        final KeyedObjectPool<Object, Object> pool = createProxy(KeyedObjectPool.class, (List<String>) null)) {
            assertThrows(IllegalArgumentException.class, () -> PoolUtils.checkMinIdle(pool, (Collection<?>) null, 1, 1),
                    "PoolUtils.checkMinIdle(KeyedObjectPool,Collection,int,long) must not accept null keys.");
        }

        try (@SuppressWarnings("unchecked")
        final KeyedObjectPool<Object, Object> pool = createProxy(KeyedObjectPool.class, (List<String>) null)) {
            PoolUtils.checkMinIdle(pool, (Collection<?>) Collections.emptyList(), 1, 1);
        } catch (final IllegalArgumentException iae) {
            fail("PoolUtils.checkMinIdle(KeyedObjectPool,Collection,int,long) must accept empty lists.");
        }
    }

    @Test
    public void testErodingObjectPoolDefaultFactor() {
        try (@SuppressWarnings("unchecked")
        final ObjectPool<Object> internalPool = createProxy(ObjectPool.class, (arg0, arg1, arg2) -> null);
                final ObjectPool<Object> pool = PoolUtils.erodingPool(internalPool)) {
            final String expectedToString = "ErodingObjectPool{factor=ErodingFactor{factor=1.0, idleHighWaterMark=1}, pool=" +
                    internalPool + "}";
            // The factor is not exposed, but will be printed in the toString() method
            // In this case since we didn't pass one, the default 1.0f will be printed
            assertEquals(expectedToString, pool.toString());
        }
    }

    @Test
    public void testErodingPoolKeyedObjectPoolDefaultFactor() {
        try (@SuppressWarnings("unchecked")
        final KeyedObjectPool<Object, Object> internalPool = createProxy(KeyedObjectPool.class,
                (arg0, arg1, arg2) -> null);
                final KeyedObjectPool<Object, Object> pool = PoolUtils.erodingPool(internalPool)) {
            final String expectedToString = "ErodingKeyedObjectPool{factor=ErodingFactor{factor=1.0, idleHighWaterMark=1}, keyedPool=" +
                    internalPool + "}";
            // The factor is not exposed, but will be printed in the toString() method
            // In this case since we didn't pass one, the default 1.0f will be printed
            assertEquals(expectedToString, pool.toString());
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testPrefillKeyedObjectPoolCollection() throws Exception {
        try (@SuppressWarnings("unchecked")
        final KeyedObjectPool<String, String> pool = createProxy(KeyedObjectPool.class, (List<String>) null)) {
            assertThrows(IllegalArgumentException.class, () -> PoolUtils.prefill(pool, (Collection<String>) null, 1),
                    "PoolUtils.prefill(KeyedObjectPool,Collection,int) must not accept null keys.");
        }

        final List<String> calledMethods = new ArrayList<>();
        try (@SuppressWarnings("unchecked")
            final KeyedObjectPool<String, Object> pool = createProxy(KeyedObjectPool.class, calledMethods)) {

            final Set<String> keys = new HashSet<>();
            PoolUtils.prefill(pool, keys, 0);
            final List<String> expectedMethods = new ArrayList<>();
            expectedMethods.add("addObjects");
            assertEquals(expectedMethods, calledMethods);

            calledMethods.clear();
            keys.add("one");
            keys.add("two");
            keys.add("three");
            final int count = 3;
            PoolUtils.prefill(pool, keys, count);
            assertEquals(expectedMethods, calledMethods);
        }
    }

    /**
     * Tests the {@link PoolUtils} timer holder.
     */

@Test
    public void testCheckMinIdleKeyedObjectPool_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.checkMinIdle(null, new Object(), 1, 1), "PoolUtils.checkMinIdle(KeyedObjectPool,Object,int,long) must not allow null pool.");
    }

@Test
    public void testCheckMinIdleObjectPool_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.checkMinIdle(null, 1, 1), "PoolUtils.checkMinIdle(ObjectPool,,) must not allow null pool.");
    }

@Test
    public void testCheckRethrow_1_oe() {
        try {
            PoolUtils.checkRethrow(new Exception());
        } catch (final Throwable t) {
            fail("PoolUtils.checkRethrow(Throwable) must rethrow only ThreadDeath and VirtualMachineError.");
    }
    }

@Test
    public void testCheckRethrow_3_oe() {
        try {
            PoolUtils.checkRethrow(new Exception());
        } catch (final Throwable t) {
            // removed other assertion
        }
        try {
            PoolUtils.checkRethrow(new ThreadDeath());
            // removed other assertion
        } catch (final ThreadDeath td) {
            // expected
        } catch (final Throwable t) {
            fail("PoolUtils.checkRethrow(Throwable) must rethrow only ThreadDeath and VirtualMachineError.");
    }
    }

@Test
    public void testCheckRethrow_5_oe() {
        try {
            PoolUtils.checkRethrow(new Exception());
        } catch (final Throwable t) {
            // removed other assertion
        }
        try {
            PoolUtils.checkRethrow(new ThreadDeath());
            // removed other assertion
        } catch (final ThreadDeath td) {
            // expected
        } catch (final Throwable t) {
            // removed other assertion
        }
        try {
            PoolUtils.checkRethrow(new InternalError()); // InternalError extends VirtualMachineError
            // removed other assertion
        } catch (final VirtualMachineError td) {
            // expected
        } catch (final Throwable t) {
            fail("PoolUtils.checkRethrow(Throwable) must rethrow only ThreadDeath and VirtualMachineError.");
    }
    }

@Test
    public void testErodingPerKeyKeyedObjectPool_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool((KeyedObjectPool<Object, Object>) null, 1f, true), "PoolUtils.erodingPool(KeyedObjectPool) must not allow a null pool.");
    }

@Test
    public void testErodingPerKeyKeyedObjectPool_2_oe() throws Exception {
        // removed other assertion

        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool((KeyedObjectPool<Object, Object>) null, 0f, true), "PoolUtils.erodingPool(ObjectPool, float, boolean) must not allow a non-positive factor.");
    }

@Test
    public void testErodingPerKeyKeyedObjectPool_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool((KeyedObjectPool<Object, Object>) null, 1f, true), "PoolUtils.erodingPool(KeyedObjectPool, float, boolean) must not allow a null pool.");
    }

@Test
    public void testErodingPoolKeyedObjectPool_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool((KeyedObjectPool<Object, Object>) null), "PoolUtils.erodingPool(KeyedObjectPool) must not allow a null pool.");
    }

@Test
    public void testErodingPoolKeyedObjectPool_2_oe() throws Exception {
        // removed other assertion

        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool((KeyedObjectPool<Object, Object>) null, 1f), "PoolUtils.erodingPool(KeyedObjectPool, float) must not allow a null pool.");
    }

@Test
    public void testErodingPoolKeyedObjectPool_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool((KeyedObjectPool<Object, Object>) null, 1f, true), "PoolUtils.erodingPool(KeyedObjectPool, float, boolean) must not allow a null pool.");
    }

@Test
    public void testErodingPoolKeyedObjectPool_4_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        final List<String> calledMethods = new ArrayList<>();
        final InvocationHandler handler = new MethodCallLogger(calledMethods) {
            @Override
            public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                Object o = super.invoke(proxy, method, args);
                if (o instanceof Integer) {
                    // so getNumActive/getNumIdle are not zero.
                    o = Integer.valueOf(1);
                }
                return o;
            }
        };

        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool(createProxy(KeyedObjectPool.class, handler), 0f), "PoolUtils.erodingPool(ObjectPool, float) must not allow a non-positive factor.");
    }

@Test
    public void testErodingPoolKeyedObjectPool_5_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        final List<String> calledMethods = new ArrayList<>();
        final InvocationHandler handler = new MethodCallLogger(calledMethods) {
            @Override
            public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                Object o = super.invoke(proxy, method, args);
                if (o instanceof Integer) {
                    // so getNumActive/getNumIdle are not zero.
                    o = Integer.valueOf(1);
                }
                return o;
            }
        };

        // removed other assertion

        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool(createProxy(KeyedObjectPool.class, handler), 0f, false), "PoolUtils.erodingPool(ObjectPool, float, boolean) must not allow a non-positive factor.");
    }

@Test
    public void testErodingPoolObjectPool_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool((ObjectPool<Object>) null), "PoolUtils.erodingPool(ObjectPool) must not allow a null pool.");
    }

@Test
    public void testErodingPoolObjectPool_2_oe() throws Exception {
        // removed other assertion

        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool((ObjectPool<Object>) null, 1f), "PoolUtils.erodingPool(ObjectPool, float) must not allow a null pool.");
    }

@Test
    public void testErodingPoolObjectPool_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        final List<String> calledMethods = new ArrayList<>();
        final InvocationHandler handler = new MethodCallLogger(calledMethods) {
            @Override
            public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                Object o = super.invoke(proxy, method, args);
                if (o instanceof Integer) {
                    // so getNumActive/getNumIdle are not zero.
                    o = Integer.valueOf(1);
                }
                return o;
            }
        };

        assertThrows(IllegalArgumentException.class, () -> PoolUtils.erodingPool(createProxy(ObjectPool.class, handler), -1f), "PoolUtils.erodingPool(ObjectPool, float) must not allow a non-positive factor.");
    }

@Test
    public void testJavaBeanInstantiation_1_oe() {
        assertNotNull(new PoolUtils());
    }

@Test
    public void testPrefillKeyedObjectPool_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.prefill(null, new Object(), 1), "PoolUtils.prefill(KeyedObjectPool,Object,int) must not accept null pool.");
    }

@Test
    public void testPrefillObjectPool_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.prefill(null, 1), "PoolUtils.prefill(ObjectPool,int) must not allow null pool.");
    }

@Test
    public void testSynchronizedPoolableFactoryKeyedPoolableObjectFactory_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.synchronizedKeyedPooledFactory((KeyedPooledObjectFactory<Object, Object>) null), "PoolUtils.synchronizedPoolableFactory(KeyedPoolableObjectFactory) must not allow a null factory.");
    }

@Test
    public void testSynchronizedPoolableFactoryKeyedPoolableObjectFactory_2_oe() throws Exception {
        // removed other assertion

        final List<String> calledMethods = new ArrayList<>();
        @SuppressWarnings("unchecked")
        final KeyedPooledObjectFactory<Object, Object> kpof = createProxy(KeyedPooledObjectFactory.class, calledMethods);

        final KeyedPooledObjectFactory<Object, Object> skpof = PoolUtils.synchronizedKeyedPooledFactory(kpof);
        final List<String> expectedMethods = invokeEveryMethod(skpof);
        assertEquals(expectedMethods, calledMethods);
    }

@Test
    public void testSynchronizedPoolableFactoryPoolableObjectFactory_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.synchronizedPooledFactory((PooledObjectFactory<Object>) null), "PoolUtils.synchronizedPoolableFactory(PoolableObjectFactory) must not allow a null factory.");
    }

@Test
    public void testSynchronizedPoolableFactoryPoolableObjectFactory_2_oe() throws Exception {
        // removed other assertion

        final List<String> calledMethods = new ArrayList<>();
        @SuppressWarnings("unchecked")
        final PooledObjectFactory<Object> pof = createProxy(PooledObjectFactory.class, calledMethods);

        final PooledObjectFactory<Object> spof = PoolUtils.synchronizedPooledFactory(pof);
        final List<String> expectedMethods = invokeEveryMethod(spof);
        assertEquals(expectedMethods, calledMethods);
    }

@Test
    public void testSynchronizedPoolKeyedObjectPool_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.synchronizedPool((KeyedObjectPool<Object, Object>) null), "PoolUtils.synchronizedPool(KeyedObjectPool) must not allow a null pool.");
    }

@Test
    public void testSynchronizedPoolObjectPool_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> PoolUtils.synchronizedPool((ObjectPool<Object>) null), "PoolUtils.synchronizedPool(ObjectPool) must not allow a null pool.");
    }

@Test
    public void testTimerHolder_1_oe() {
        final PoolUtils.TimerHolder h = new PoolUtils.TimerHolder();
        assertNotNull(h);
    }

@Test
    public void testTimerHolder_2_oe() {
        final PoolUtils.TimerHolder h = new PoolUtils.TimerHolder();
        // removed other assertion
        assertNotNull(PoolUtils.TimerHolder.MIN_IDLE_TIMER);
    }

}
