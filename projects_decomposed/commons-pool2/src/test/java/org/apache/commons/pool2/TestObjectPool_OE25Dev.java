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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;
import org.junit.jupiter.api.Test;

/**
 * Abstract test case for {@link ObjectPool} implementations.
 */
public abstract class TestObjectPool_OE25Dev {

    private static void clear(final MethodCallPoolableObjectFactory factory, final List<MethodCall> expectedMethods) {
        factory.getMethodCalls().clear();
        expectedMethods.clear();
    }

    static void removeDestroyObjectCall(final List<MethodCall> calls) {
        calls.removeIf(call -> "destroyObject".equals(call.getName()));
    }

    private static void reset(final ObjectPool<Object> pool, final MethodCallPoolableObjectFactory factory, final List<MethodCall> expectedMethods) throws Exception {
        pool.clear();
        clear(factory, expectedMethods);
        factory.reset();
    }
    // Deliberate choice to create a new object in case future unit tests check
    // for a specific object.
    private final Integer ZERO = Integer.valueOf(0);

    private final Integer ONE = Integer.valueOf(1);

    /**
     * Create an {@code ObjectPool} with the specified factory.
     * The pool should be in a default configuration and conform to the expected
     * behaviors described in {@link ObjectPool}.
     * Generally speaking there should be no limits on the various object counts.
     *
     * @param factory The factory to be used by the object pool
     *
     * @return the newly created empty pool
     *
     * @throws UnsupportedOperationException if the pool being tested does not
     *                                       follow pool contracts.
     */
    protected abstract ObjectPool<Object> makeEmptyPool(PooledObjectFactory<Object> factory) throws UnsupportedOperationException;

    @Test
    public void testPOFClearUsages() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        /// Test correct behavior code paths
        pool.addObjects(5);
        pool.clear();

        //// Test exception handling clear should swallow destroy object failures
        reset(pool, factory, expectedMethods);
        factory.setDestroyObjectFail(true);
        pool.addObjects(5);
        pool.clear();
        pool.close();
    }

    @Test
    public void testPOFCloseUsages() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        /// Test correct behavior code paths
        pool.addObjects(5);
        pool.close();


        //// Test exception handling close should swallow failures
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        reset(pool, factory, expectedMethods);
        factory.setDestroyObjectFail(true);
        pool.addObjects(5);
        pool.close();
    }

    @Test
    public void testToString() {
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(new MethodCallPoolableObjectFactory());
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        pool.toString();
        pool.close();
    }

    @Test
    public void testClosedPoolBehavior_1_oe() throws Exception {
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(new MethodCallPoolableObjectFactory());
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final Object o1 = pool.borrowObject();
        final Object o2 = pool.borrowObject();

        pool.close();

        assertThrows(IllegalStateException.class, pool::addObject, "A closed pool must throw an IllegalStateException when addObject is called.");
    }

    @Test
    public void testClosedPoolBehavior_2_oe() throws Exception {
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(new MethodCallPoolableObjectFactory());
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final Object o1 = pool.borrowObject();
        final Object o2 = pool.borrowObject();

        pool.close();

        // removed other assertion

        assertThrows(IllegalStateException.class, pool::borrowObject, "A closed pool must throw an IllegalStateException when borrowObject is called.");
    }

    @Test
    public void testClosedPoolBehavior_3_oe() throws Exception {
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(new MethodCallPoolableObjectFactory());
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final Object o1 = pool.borrowObject();
        final Object o2 = pool.borrowObject();

        pool.close();

        // removed other assertion

        // removed other assertion

        // The following should not throw exceptions just because the pool is closed.
        if (pool.getNumIdle() >= 0) {
            assertEquals( 0, pool.getNumIdle(),"A closed pool shouldn't have any idle objects.");
    }
    }

    @Test
    public void testClosedPoolBehavior_4_oe() throws Exception {
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(new MethodCallPoolableObjectFactory());
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final Object o1 = pool.borrowObject();
        final Object o2 = pool.borrowObject();

        pool.close();

        // removed other assertion

        // removed other assertion

        // The following should not throw exceptions just because the pool is closed.
        if (pool.getNumIdle() >= 0) {
            // removed other assertion
        }
        if (pool.getNumActive() >= 0) {
            assertEquals( 2, pool.getNumActive(),"A closed pool should still keep count of active objects.");
    }
    }

    @Test
    public void testClosedPoolBehavior_5_oe() throws Exception {
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(new MethodCallPoolableObjectFactory());
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final Object o1 = pool.borrowObject();
        final Object o2 = pool.borrowObject();

        pool.close();

        // removed other assertion

        // removed other assertion

        // The following should not throw exceptions just because the pool is closed.
        if (pool.getNumIdle() >= 0) {
            // removed other assertion
        }
        if (pool.getNumActive() >= 0) {
            // removed other assertion
        }
        pool.returnObject(o1);
        if (pool.getNumIdle() >= 0) {
            assertEquals( 0, pool.getNumIdle(),"returnObject should not add items back into the idle object pool for a closed pool.");
    }
    }

    @Test
    public void testClosedPoolBehavior_6_oe() throws Exception {
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(new MethodCallPoolableObjectFactory());
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final Object o1 = pool.borrowObject();
        final Object o2 = pool.borrowObject();

        pool.close();

        // removed other assertion

        // removed other assertion

        // The following should not throw exceptions just because the pool is closed.
        if (pool.getNumIdle() >= 0) {
            // removed other assertion
        }
        if (pool.getNumActive() >= 0) {
            // removed other assertion
        }
        pool.returnObject(o1);
        if (pool.getNumIdle() >= 0) {
            // removed other assertion
        }
        if (pool.getNumActive() >= 0) {
            assertEquals( 1, pool.getNumActive(),"A closed pool should still keep count of active objects.");
    }
    }

    @Test
    public void testClosedPoolBehavior_7_oe() throws Exception {
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(new MethodCallPoolableObjectFactory());
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final Object o1 = pool.borrowObject();
        final Object o2 = pool.borrowObject();

        pool.close();

        // removed other assertion

        // removed other assertion

        // The following should not throw exceptions just because the pool is closed.
        if (pool.getNumIdle() >= 0) {
            // removed other assertion
        }
        if (pool.getNumActive() >= 0) {
            // removed other assertion
        }
        pool.returnObject(o1);
        if (pool.getNumIdle() >= 0) {
            // removed other assertion
        }
        if (pool.getNumActive() >= 0) {
            // removed other assertion
        }
        pool.invalidateObject(o2);
        if (pool.getNumIdle() >= 0) {
            assertEquals( 0, pool.getNumIdle(),"invalidateObject must not add items back into the idle object pool.");
    }
    }

    @Test
    public void testClosedPoolBehavior_8_oe() throws Exception {
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(new MethodCallPoolableObjectFactory());
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final Object o1 = pool.borrowObject();
        final Object o2 = pool.borrowObject();

        pool.close();

        // removed other assertion

        // removed other assertion

        // The following should not throw exceptions just because the pool is closed.
        if (pool.getNumIdle() >= 0) {
            // removed other assertion
        }
        if (pool.getNumActive() >= 0) {
            // removed other assertion
        }
        pool.returnObject(o1);
        if (pool.getNumIdle() >= 0) {
            // removed other assertion
        }
        if (pool.getNumActive() >= 0) {
            // removed other assertion
        }
        pool.invalidateObject(o2);
        if (pool.getNumIdle() >= 0) {
            // removed other assertion
        }
        if (pool.getNumActive() >= 0) {
            assertEquals( 0, pool.getNumActive(),"A closed pool should still keep count of active objects.");
    }
    }

    @Test
    public void testPOFAddObjectUsage_1_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        assertEquals(0, pool.getNumActive());
    }

    @Test
    public void testPOFAddObjectUsage_2_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        // removed other assertion
        assertEquals(0, pool.getNumIdle());
    }

    @Test
    public void testPOFAddObjectUsage_3_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // addObject should make a new object, passivate it and put it in the pool
        pool.addObject();
        assertEquals(0, pool.getNumActive());
    }

    @Test
    public void testPOFAddObjectUsage_4_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // addObject should make a new object, passivate it and put it in the pool
        pool.addObject();
        // removed other assertion
        assertEquals(1, pool.getNumIdle());
    }

    @Test
    public void testPOFAddObjectUsage_5_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // addObject should make a new object, passivate it and put it in the pool
        pool.addObject();
        // removed other assertion
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject").returned(ZERO));
        // StackObjectPool, SoftReferenceObjectPool also validate on add
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", ZERO).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", ZERO));
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testPOFAddObjectUsage_6_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // addObject should make a new object, passivate it and put it in the pool
        pool.addObject();
        // removed other assertion
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject").returned(ZERO));
        // StackObjectPool, SoftReferenceObjectPool also validate on add
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", ZERO).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", ZERO));
        // removed other assertion

        //// Test exception handling of addObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from addObject
        factory.setMakeObjectFail(true);
        assertThrows(PrivateException.class, pool::addObject, "Expected addObject to propagate makeObject exception.");
    }

    @Test
    public void testPOFAddObjectUsage_7_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // addObject should make a new object, passivate it and put it in the pool
        pool.addObject();
        // removed other assertion
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject").returned(ZERO));
        // StackObjectPool, SoftReferenceObjectPool also validate on add
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", ZERO).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", ZERO));
        // removed other assertion

        //// Test exception handling of addObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from addObject
        factory.setMakeObjectFail(true);
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject"));
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testPOFAddObjectUsage_8_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // addObject should make a new object, passivate it and put it in the pool
        pool.addObject();
        // removed other assertion
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject").returned(ZERO));
        // StackObjectPool, SoftReferenceObjectPool also validate on add
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", ZERO).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", ZERO));
        // removed other assertion

        //// Test exception handling of addObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from addObject
        factory.setMakeObjectFail(true);
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject"));
        // removed other assertion

        clear(factory, expectedMethods);

        // passivateObject Exceptions should be propagated to client code from addObject
        factory.setMakeObjectFail(false);
        factory.setPassivateObjectFail(true);
        assertThrows(PrivateException.class, pool::addObject, "Expected addObject to propagate passivateObject exception.");
    }

    @Test
    public void testPOFAddObjectUsage_9_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // addObject should make a new object, passivate it and put it in the pool
        pool.addObject();
        // removed other assertion
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject").returned(ZERO));
        // StackObjectPool, SoftReferenceObjectPool also validate on add
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", ZERO).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", ZERO));
        // removed other assertion

        //// Test exception handling of addObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from addObject
        factory.setMakeObjectFail(true);
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject"));
        // removed other assertion

        clear(factory, expectedMethods);

        // passivateObject Exceptions should be propagated to client code from addObject
        factory.setMakeObjectFail(false);
        factory.setPassivateObjectFail(true);
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject").returned(ONE));
        // StackObjectPool, SofReferenceObjectPool also validate on add
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall("validateObject", ONE).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", ONE));
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testPOFBorrowObjectUsages_1_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        if (pool instanceof GenericObjectPool) {
            ((GenericObjectPool<Object>) pool).setTestOnBorrow(true);
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        // existing idle object should be activated and validated
        pool.addObject();
        clear(factory, expectedMethods);
        obj = pool.borrowObject();
        expectedMethods.add(new MethodCall("activateObject", ZERO));
        expectedMethods.add(new MethodCall("validateObject", ZERO).returned(Boolean.TRUE));
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testPOFBorrowObjectUsages_2_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        if (pool instanceof GenericObjectPool) {
            ((GenericObjectPool<Object>) pool).setTestOnBorrow(true);
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        // existing idle object should be activated and validated
        pool.addObject();
        clear(factory, expectedMethods);
        obj = pool.borrowObject();
        expectedMethods.add(new MethodCall("activateObject", ZERO));
        expectedMethods.add(new MethodCall("validateObject", ZERO).returned(Boolean.TRUE));
        // removed other assertion
        pool.returnObject(obj);

        //// Test exception handling of borrowObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from borrowObject
        factory.setMakeObjectFail(true);
        assertThrows(PrivateException.class, pool::borrowObject, "Expected borrowObject to propagate makeObject exception.");
    }

    @Test
    public void testPOFBorrowObjectUsages_3_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        if (pool instanceof GenericObjectPool) {
            ((GenericObjectPool<Object>) pool).setTestOnBorrow(true);
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        // existing idle object should be activated and validated
        pool.addObject();
        clear(factory, expectedMethods);
        obj = pool.borrowObject();
        expectedMethods.add(new MethodCall("activateObject", ZERO));
        expectedMethods.add(new MethodCall("validateObject", ZERO).returned(Boolean.TRUE));
        // removed other assertion
        pool.returnObject(obj);

        //// Test exception handling of borrowObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from borrowObject
        factory.setMakeObjectFail(true);
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject"));
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testPOFBorrowObjectUsages_4_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        if (pool instanceof GenericObjectPool) {
            ((GenericObjectPool<Object>) pool).setTestOnBorrow(true);
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        // existing idle object should be activated and validated
        pool.addObject();
        clear(factory, expectedMethods);
        obj = pool.borrowObject();
        expectedMethods.add(new MethodCall("activateObject", ZERO));
        expectedMethods.add(new MethodCall("validateObject", ZERO).returned(Boolean.TRUE));
        // removed other assertion
        pool.returnObject(obj);

        //// Test exception handling of borrowObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from borrowObject
        factory.setMakeObjectFail(true);
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject"));
        // removed other assertion


        // when activateObject fails in borrowObject, a new object should be borrowed/created
        reset(pool, factory, expectedMethods);
        pool.addObject();
        clear(factory, expectedMethods);

        factory.setActivateObjectFail(true);
        expectedMethods.add(new MethodCall("activateObject", obj));
        // Expected NoSuchElementException - newly created object will also fail to activate
        assertThrows(NoSuchElementException.class, pool::borrowObject, "Expecting NoSuchElementException");
    }

    @Test
    public void testPOFBorrowObjectUsages_5_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        if (pool instanceof GenericObjectPool) {
            ((GenericObjectPool<Object>) pool).setTestOnBorrow(true);
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        // existing idle object should be activated and validated
        pool.addObject();
        clear(factory, expectedMethods);
        obj = pool.borrowObject();
        expectedMethods.add(new MethodCall("activateObject", ZERO));
        expectedMethods.add(new MethodCall("validateObject", ZERO).returned(Boolean.TRUE));
        // removed other assertion
        pool.returnObject(obj);

        //// Test exception handling of borrowObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from borrowObject
        factory.setMakeObjectFail(true);
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject"));
        // removed other assertion


        // when activateObject fails in borrowObject, a new object should be borrowed/created
        reset(pool, factory, expectedMethods);
        pool.addObject();
        clear(factory, expectedMethods);

        factory.setActivateObjectFail(true);
        expectedMethods.add(new MethodCall("activateObject", obj));
        // Expected NoSuchElementException - newly created object will also fail to activate
        // removed other assertion
        // Idle object fails activation, new one created, also fails
        expectedMethods.add(new MethodCall("makeObject").returned(ONE));
        expectedMethods.add(new MethodCall("activateObject", ONE));
        removeDestroyObjectCall(factory.getMethodCalls()); // The exact timing of destroyObject is flexible here.
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testPOFBorrowObjectUsages_6_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        if (pool instanceof GenericObjectPool) {
            ((GenericObjectPool<Object>) pool).setTestOnBorrow(true);
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        // existing idle object should be activated and validated
        pool.addObject();
        clear(factory, expectedMethods);
        obj = pool.borrowObject();
        expectedMethods.add(new MethodCall("activateObject", ZERO));
        expectedMethods.add(new MethodCall("validateObject", ZERO).returned(Boolean.TRUE));
        // removed other assertion
        pool.returnObject(obj);

        //// Test exception handling of borrowObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from borrowObject
        factory.setMakeObjectFail(true);
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject"));
        // removed other assertion


        // when activateObject fails in borrowObject, a new object should be borrowed/created
        reset(pool, factory, expectedMethods);
        pool.addObject();
        clear(factory, expectedMethods);

        factory.setActivateObjectFail(true);
        expectedMethods.add(new MethodCall("activateObject", obj));
        // Expected NoSuchElementException - newly created object will also fail to activate
        // removed other assertion
        // Idle object fails activation, new one created, also fails
        expectedMethods.add(new MethodCall("makeObject").returned(ONE));
        expectedMethods.add(new MethodCall("activateObject", ONE));
        removeDestroyObjectCall(factory.getMethodCalls()); // The exact timing of destroyObject is flexible here.
        // removed other assertion

        // when validateObject fails in borrowObject, a new object should be borrowed/created
        reset(pool, factory, expectedMethods);
        pool.addObject();
        clear(factory, expectedMethods);

        factory.setValidateObjectFail(true);
        expectedMethods.add(new MethodCall("activateObject", ZERO));
        expectedMethods.add(new MethodCall("validateObject", ZERO));
        // Expected NoSuchElementException - newly created object will also fail to validate
        assertThrows(NoSuchElementException.class, pool::borrowObject, "Expecting NoSuchElementException");
    }

    @Test
    public void testPOFBorrowObjectUsages_7_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        if (pool instanceof GenericObjectPool) {
            ((GenericObjectPool<Object>) pool).setTestOnBorrow(true);
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        // existing idle object should be activated and validated
        pool.addObject();
        clear(factory, expectedMethods);
        obj = pool.borrowObject();
        expectedMethods.add(new MethodCall("activateObject", ZERO));
        expectedMethods.add(new MethodCall("validateObject", ZERO).returned(Boolean.TRUE));
        // removed other assertion
        pool.returnObject(obj);

        //// Test exception handling of borrowObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from borrowObject
        factory.setMakeObjectFail(true);
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject"));
        // removed other assertion


        // when activateObject fails in borrowObject, a new object should be borrowed/created
        reset(pool, factory, expectedMethods);
        pool.addObject();
        clear(factory, expectedMethods);

        factory.setActivateObjectFail(true);
        expectedMethods.add(new MethodCall("activateObject", obj));
        // Expected NoSuchElementException - newly created object will also fail to activate
        // removed other assertion
        // Idle object fails activation, new one created, also fails
        expectedMethods.add(new MethodCall("makeObject").returned(ONE));
        expectedMethods.add(new MethodCall("activateObject", ONE));
        removeDestroyObjectCall(factory.getMethodCalls()); // The exact timing of destroyObject is flexible here.
        // removed other assertion

        // when validateObject fails in borrowObject, a new object should be borrowed/created
        reset(pool, factory, expectedMethods);
        pool.addObject();
        clear(factory, expectedMethods);

        factory.setValidateObjectFail(true);
        expectedMethods.add(new MethodCall("activateObject", ZERO));
        expectedMethods.add(new MethodCall("validateObject", ZERO));
        // Expected NoSuchElementException - newly created object will also fail to validate
        // removed other assertion
        // Idle object is activated, but fails validation.
        // New instance is created, activated and then fails validation
        expectedMethods.add(new MethodCall("makeObject").returned(ONE));
        expectedMethods.add(new MethodCall("activateObject", ONE));
        expectedMethods.add(new MethodCall("validateObject", ONE));
        removeDestroyObjectCall(factory.getMethodCalls()); // The exact timing of destroyObject is flexible here.
        // Second activate and validate are missing from expectedMethods
        assertTrue(factory.getMethodCalls().containsAll(expectedMethods));
    }

    @Test
    public void testPOFInvalidateObjectUsages_1_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        obj = pool.borrowObject();
        clear(factory, expectedMethods);

        // invalidated object should be destroyed
        pool.invalidateObject(obj);
        expectedMethods.add(new MethodCall("destroyObject", obj));
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testPOFInvalidateObjectUsages_2_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        obj = pool.borrowObject();
        clear(factory, expectedMethods);

        // invalidated object should be destroyed
        pool.invalidateObject(obj);
        expectedMethods.add(new MethodCall("destroyObject", obj));
        // removed other assertion

        //// Test exception handling of invalidateObject
        reset(pool, factory, expectedMethods);
        final Object obj2 = pool.borrowObject();
        clear(factory, expectedMethods);
        factory.setDestroyObjectFail(true);
        assertThrows(PrivateException.class, () -> pool.invalidateObject(obj2));
    }

    @Test
    public void testPOFInvalidateObjectUsages_3_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        obj = pool.borrowObject();
        clear(factory, expectedMethods);

        // invalidated object should be destroyed
        pool.invalidateObject(obj);
        expectedMethods.add(new MethodCall("destroyObject", obj));
        // removed other assertion

        //// Test exception handling of invalidateObject
        reset(pool, factory, expectedMethods);
        final Object obj2 = pool.borrowObject();
        clear(factory, expectedMethods);
        factory.setDestroyObjectFail(true);
        // removed other assertion
        Thread.sleep(250); // could be deferred
        removeDestroyObjectCall(factory.getMethodCalls());
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testPOFReturnObjectUsages_1_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths
        obj = pool.borrowObject();
        clear(factory, expectedMethods);

        // returned object should be passivated
        pool.returnObject(obj);
        // StackObjectPool, SoftReferenceObjectPool also validate on return
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", obj).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", obj));
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testPOFReturnObjectUsages_2_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths
        obj = pool.borrowObject();
        clear(factory, expectedMethods);

        // returned object should be passivated
        pool.returnObject(obj);
        // StackObjectPool, SoftReferenceObjectPool also validate on return
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", obj).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", obj));
        // removed other assertion

        //// Test exception handling of returnObject
        reset(pool, factory, expectedMethods);
        pool.addObject();
        pool.addObject();
        pool.addObject();
        assertEquals(3, pool.getNumIdle());
    }

    @Test
    public void testPOFReturnObjectUsages_3_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths
        obj = pool.borrowObject();
        clear(factory, expectedMethods);

        // returned object should be passivated
        pool.returnObject(obj);
        // StackObjectPool, SoftReferenceObjectPool also validate on return
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", obj).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", obj));
        // removed other assertion

        //// Test exception handling of returnObject
        reset(pool, factory, expectedMethods);
        pool.addObject();
        pool.addObject();
        pool.addObject();
        // removed other assertion
        // passivateObject should swallow exceptions and not add the object to the pool
        obj = pool.borrowObject();
        pool.borrowObject();
        assertEquals(1, pool.getNumIdle());
    }

    @Test
    public void testPOFReturnObjectUsages_4_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths
        obj = pool.borrowObject();
        clear(factory, expectedMethods);

        // returned object should be passivated
        pool.returnObject(obj);
        // StackObjectPool, SoftReferenceObjectPool also validate on return
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", obj).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", obj));
        // removed other assertion

        //// Test exception handling of returnObject
        reset(pool, factory, expectedMethods);
        pool.addObject();
        pool.addObject();
        pool.addObject();
        // removed other assertion
        // passivateObject should swallow exceptions and not add the object to the pool
        obj = pool.borrowObject();
        pool.borrowObject();
        // removed other assertion
        assertEquals(2, pool.getNumActive());
    }

    @Test
    public void testPOFReturnObjectUsages_5_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths
        obj = pool.borrowObject();
        clear(factory, expectedMethods);

        // returned object should be passivated
        pool.returnObject(obj);
        // StackObjectPool, SoftReferenceObjectPool also validate on return
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", obj).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", obj));
        // removed other assertion

        //// Test exception handling of returnObject
        reset(pool, factory, expectedMethods);
        pool.addObject();
        pool.addObject();
        pool.addObject();
        // removed other assertion
        // passivateObject should swallow exceptions and not add the object to the pool
        obj = pool.borrowObject();
        pool.borrowObject();
        // removed other assertion
        // removed other assertion
        clear(factory, expectedMethods);
        factory.setPassivateObjectFail(true);
        pool.returnObject(obj);
        // StackObjectPool, SoftReferenceObjectPool also validate on return
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall("validateObject", obj).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", obj));
        removeDestroyObjectCall(factory.getMethodCalls()); // The exact timing of destroyObject is flexible here.
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testPOFReturnObjectUsages_6_oe() throws Exception {
        final MethodCallPoolableObjectFactory factory = new MethodCallPoolableObjectFactory();
        final ObjectPool<Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths
        obj = pool.borrowObject();
        clear(factory, expectedMethods);

        // returned object should be passivated
        pool.returnObject(obj);
        // StackObjectPool, SoftReferenceObjectPool also validate on return
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall(
                    "validateObject", obj).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", obj));
        // removed other assertion

        //// Test exception handling of returnObject
        reset(pool, factory, expectedMethods);
        pool.addObject();
        pool.addObject();
        pool.addObject();
        // removed other assertion
        // passivateObject should swallow exceptions and not add the object to the pool
        obj = pool.borrowObject();
        pool.borrowObject();
        // removed other assertion
        // removed other assertion
        clear(factory, expectedMethods);
        factory.setPassivateObjectFail(true);
        pool.returnObject(obj);
        // StackObjectPool, SoftReferenceObjectPool also validate on return
        if (pool instanceof SoftReferenceObjectPool) {
            expectedMethods.add(new MethodCall("validateObject", obj).returned(Boolean.TRUE));
        }
        expectedMethods.add(new MethodCall("passivateObject", obj));
        removeDestroyObjectCall(factory.getMethodCalls()); // The exact timing of destroyObject is flexible here.
        // removed other assertion
        assertEquals(1,pool.getNumIdle());// Not returned assertEquals(1,pool.getNumActive());// But not in active count reset(pool,factory,expectedMethods);
    }

}
