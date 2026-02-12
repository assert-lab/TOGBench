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
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Abstract test case for {@link ObjectPool} implementations.
 */
public abstract class TestKeyedObjectPool_OE25Dev {

    protected static class FailingKeyedPooledObjectFactory implements KeyedPooledObjectFactory<Object,Object> {
        private final List<MethodCall> methodCalls = new ArrayList<>();
        private int count;
        private boolean makeObjectFail;
        private boolean activateObjectFail;
        private boolean validateObjectFail;
        private boolean passivateObjectFail;
        private boolean destroyObjectFail;

        public FailingKeyedPooledObjectFactory() {
        }

        @Override
        public void activateObject(final Object key, final PooledObject<Object> obj) throws Exception {
            methodCalls.add(new MethodCall("activateObject", key, obj.getObject()));
            if (activateObjectFail) {
                throw new PrivateException("activateObject");
            }
        }

        @Override
        public void destroyObject(final Object key, final PooledObject<Object> obj) throws Exception {
            methodCalls.add(new MethodCall("destroyObject", key, obj.getObject()));
            if (destroyObjectFail) {
                throw new PrivateException("destroyObject");
            }
        }

        public int getCurrentCount() {
            return count;
        }

        public List<MethodCall> getMethodCalls() {
            return methodCalls;
        }

        public boolean isActivateObjectFail() {
            return activateObjectFail;
        }

        public boolean isDestroyObjectFail() {
            return destroyObjectFail;
        }

        public boolean isMakeObjectFail() {
            return makeObjectFail;
        }

        public boolean isPassivateObjectFail() {
            return passivateObjectFail;
        }

        public boolean isValidateObjectFail() {
            return validateObjectFail;
        }

        @Override
        public PooledObject<Object> makeObject(final Object key) throws Exception {
            final MethodCall call = new MethodCall("makeObject", key);
            methodCalls.add(call);
            final int originalCount = this.count++;
            if (makeObjectFail) {
                throw new PrivateException("makeObject");
            }
            // Deliberate choice to create new object in case future unit test
            // checks for a specific object
            final Integer obj = Integer.valueOf(originalCount);
            call.setReturned(obj);
            return new DefaultPooledObject<>(obj);
        }

        @Override
        public void passivateObject(final Object key, final PooledObject<Object> obj) throws Exception {
            methodCalls.add(new MethodCall("passivateObject", key, obj.getObject()));
            if (passivateObjectFail) {
                throw new PrivateException("passivateObject");
            }
        }

        public void reset() {
            count = 0;
            getMethodCalls().clear();
            setMakeObjectFail(false);
            setActivateObjectFail(false);
            setValidateObjectFail(false);
            setPassivateObjectFail(false);
            setDestroyObjectFail(false);
        }

        public void setActivateObjectFail(final boolean activateObjectFail) {
            this.activateObjectFail = activateObjectFail;
        }

        public void setCurrentCount(final int count) {
            this.count = count;
        }

        public void setDestroyObjectFail(final boolean destroyObjectFail) {
            this.destroyObjectFail = destroyObjectFail;
        }

        public void setMakeObjectFail(final boolean makeObjectFail) {
            this.makeObjectFail = makeObjectFail;
        }

        public void setPassivateObjectFail(final boolean passivateObjectFail) {
            this.passivateObjectFail = passivateObjectFail;
        }

        public void setValidateObjectFail(final boolean validateObjectFail) {
            this.validateObjectFail = validateObjectFail;
        }

        @Override
        public boolean validateObject(final Object key, final PooledObject<Object> obj) {
            final MethodCall call = new MethodCall("validateObject", key, obj.getObject());
            methodCalls.add(call);
            if (validateObjectFail) {
                throw new PrivateException("validateObject");
            }
            final boolean r = true;
            call.returned(Boolean.valueOf(r));
            return r;
        }
    }

    private static class TestFactory
            extends BaseKeyedPooledObjectFactory<Object,Object> {
        @Override
        public Object create(final Object key) throws Exception {
            return new Object();
        }
        @Override
        public PooledObject<Object> wrap(final Object value) {
            return new DefaultPooledObject<>(value);
        }
    }

    protected static final String KEY = "key";

    private KeyedObjectPool<Object,Object> pool;

    // Deliberate choice to create a new object in case future unit tests check
    // for a specific object.
    private final Integer ZERO = Integer.valueOf(0);

    private final Integer ONE = Integer.valueOf(1);

    private void clear(final FailingKeyedPooledObjectFactory factory, final List<MethodCall> expectedMethods) {
        factory.getMethodCalls().clear();
        expectedMethods.clear();
    }

    /**
     * Return what we expect to be the n<sup>th</sup>
     * object (zero indexed) created by the pool
     * for the given key.
     * @param key Key for the object to be obtained
     * @param n   index of the object to be obtained
     *
     * @return the requested object
     */
    protected abstract Object getNthObject(Object key, int n);

    protected abstract boolean isFifo();

    protected abstract boolean isLifo();

    /**
     * Creates an {@link KeyedObjectPool} instance
     * that can contain at least <i>minCapacity</i>
     * idle and active objects, or
     * throw {@link IllegalArgumentException}
     * if such a pool cannot be created.
     * @param minCapacity Minimum capacity of the pool to create
     *
     * @return the newly created keyed object pool
     */
    protected abstract KeyedObjectPool<Object,Object> makeEmptyPool(int minCapacity);

    /**
     * Creates an {@code KeyedObjectPool} with the specified factory.
     * The pool should be in a default configuration and conform to the expected
     * behaviors described in {@link KeyedObjectPool}.
     * Generally speaking there should be no limits on the various object counts.
     *
     * @param factory Factory to use to associate with the pool
     * @return The newly created empty pool
     */
    protected abstract KeyedObjectPool<Object, Object> makeEmptyPool(KeyedPooledObjectFactory<Object, Object> factory);

    protected abstract Object makeKey(int n);

    private void reset(final KeyedObjectPool<Object,Object> pool, final FailingKeyedPooledObjectFactory factory, final List<MethodCall> expectedMethods) throws Exception {
        pool.clear();
        clear(factory, expectedMethods);
        factory.reset();
    }

    @AfterEach
    public void tearDown() {
        pool = null;
    }

    @Test
    public void testBaseAddObject() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object key = makeKey(0);
        try {
            assertEquals(0,pool.getNumIdle());
            assertEquals(0,pool.getNumActive());
            assertEquals(0,pool.getNumIdle(key));
            assertEquals(0,pool.getNumActive(key));
            pool.addObject(key);
            assertEquals(1,pool.getNumIdle());
            assertEquals(0,pool.getNumActive());
            assertEquals(1,pool.getNumIdle(key));
            assertEquals(0,pool.getNumActive(key));
            final Object obj = pool.borrowObject(key);
            assertEquals(getNthObject(key,0),obj);
            assertEquals(0,pool.getNumIdle());
            assertEquals(1,pool.getNumActive());
            assertEquals(0,pool.getNumIdle(key));
            assertEquals(1,pool.getNumActive(key));
            pool.returnObject(key,obj);
            assertEquals(1,pool.getNumIdle());
            assertEquals(0,pool.getNumActive());
            assertEquals(1,pool.getNumIdle(key));
            assertEquals(0,pool.getNumActive(key));
        } catch(final UnsupportedOperationException e) {
            return; // skip this test if one of those calls is unsupported
        } finally {
            pool.close();
        }
    }

    @Test
    public void testKPOFClearUsages() throws Exception {
        final FailingKeyedPooledObjectFactory factory = new FailingKeyedPooledObjectFactory();
        final KeyedObjectPool<Object,Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        /// Test correct behavior code paths
        pool.addObjects(KEY, 5);
        pool.clear();

        //// Test exception handling clear should swallow destroy object failures
        reset(pool, factory, expectedMethods);
        factory.setDestroyObjectFail(true);
        pool.addObjects(KEY, 5);
        pool.clear();
        pool.close();
    }


    @Test
    public void testKPOFCloseUsages() throws Exception {
        final FailingKeyedPooledObjectFactory factory = new FailingKeyedPooledObjectFactory();
        KeyedObjectPool<Object, Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch (final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        /// Test correct behavior code paths
        pool.addObjects(KEY, 5);
        pool.close();

        //// Test exception handling close should swallow failures
        try (final KeyedObjectPool<Object, Object> pool2 = makeEmptyPool(factory)) {
            reset(pool2, factory, expectedMethods);
            factory.setDestroyObjectFail(true);
            pool2.addObjects(KEY, 5);
        }
    }

    @Test
    public void testToString() {
        final FailingKeyedPooledObjectFactory factory =
                new FailingKeyedPooledObjectFactory();
        try (final KeyedObjectPool<Object,Object> pool = makeEmptyPool(factory)) {
            pool.toString();
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
    }

    @Test
    public void testClosedPoolBehavior_1_oe() throws Exception {
        final KeyedObjectPool<Object,Object> pool;
        try {
            pool = makeEmptyPool(new TestFactory());
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }

        final Object o1 = pool.borrowObject(KEY);
        final Object o2 = pool.borrowObject(KEY);

        pool.close();

        try {
    pool.addObject(KEY);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException: A closed pool must throw an IllegalStateException when addObject is called.");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testClosedPoolBehavior_2_oe() throws Exception {
        final KeyedObjectPool<Object,Object> pool;
        try {
            pool = makeEmptyPool(new TestFactory());
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }

        final Object o1 = pool.borrowObject(KEY);
        final Object o2 = pool.borrowObject(KEY);

        pool.close();

        // removed other assertion

        try {
    pool.borrowObject(KEY);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException: A closed pool must throw an IllegalStateException when borrowObject is called.");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testKPOFAddObjectUsage_2_oe() throws Exception {
        final FailingKeyedPooledObjectFactory factory = new FailingKeyedPooledObjectFactory();
        final KeyedObjectPool<Object,Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        // addObject should make a new object, passivate it and put it in the pool
        pool.addObject(KEY);
        expectedMethods.add(new MethodCall("makeObject", KEY).returned(ZERO));
        expectedMethods.add(new MethodCall("passivateObject", KEY, ZERO));
        // removed other assertion

        //// Test exception handling of addObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from addObject
        factory.setMakeObjectFail(true);
        try {
    pool.addObject(KEY);
    org.junit.jupiter.api.Assertions.fail("PrivateException: Expected addObject to propagate makeObject exception.");
} catch (PrivateException e) {
}
    }

    @Test
    public void testKPOFAddObjectUsage_4_oe() throws Exception {
        final FailingKeyedPooledObjectFactory factory = new FailingKeyedPooledObjectFactory();
        final KeyedObjectPool<Object,Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();

        // addObject should make a new object, passivate it and put it in the pool
        pool.addObject(KEY);
        expectedMethods.add(new MethodCall("makeObject", KEY).returned(ZERO));
        expectedMethods.add(new MethodCall("passivateObject", KEY, ZERO));
        // removed other assertion

        //// Test exception handling of addObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from addObject
        factory.setMakeObjectFail(true);
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject", KEY));
        // removed other assertion

        clear(factory, expectedMethods);

        // passivateObject Exceptions should be propagated to client code from addObject
        factory.setMakeObjectFail(false);
        factory.setPassivateObjectFail(true);
        try {
    pool.addObject(KEY);
    org.junit.jupiter.api.Assertions.fail("PrivateException: Expected addObject to propagate passivateObject exception.");
} catch (PrivateException e) {
}
    }

    @Test
    public void testKPOFBorrowObjectUsages_4_oe() throws Exception {
        final FailingKeyedPooledObjectFactory factory = new FailingKeyedPooledObjectFactory();
        final KeyedObjectPool<Object,Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        if (pool instanceof GenericKeyedObjectPool) {
            ((GenericKeyedObjectPool<Object,Object>) pool).setTestOnBorrow(true);
        }

        /// Test correct behavior code paths

        // existing idle object should be activated and validated
        pool.addObject(KEY);
        clear(factory, expectedMethods);
        obj = pool.borrowObject(KEY);
        expectedMethods.add(new MethodCall("activateObject", KEY, ZERO));
        expectedMethods.add(new MethodCall("validateObject", KEY, ZERO).returned(Boolean.TRUE));
        // removed other assertion
        pool.returnObject(KEY, obj);

        //// Test exception handling of borrowObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from borrowObject
        factory.setMakeObjectFail(true);
        try {
            obj = pool.borrowObject(KEY);
            // removed other assertion
        } catch (final PrivateException pe) {
            // expected
        }
        expectedMethods.add(new MethodCall("makeObject", KEY));
        // removed other assertion


        // when activateObject fails in borrowObject, a new object should be borrowed/created
        reset(pool, factory, expectedMethods);
        pool.addObject(KEY);
        clear(factory, expectedMethods);

        factory.setActivateObjectFail(true);
        expectedMethods.add(new MethodCall("activateObject", KEY, obj));
        try {
    pool.borrowObject(KEY);
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testKPOFBorrowObjectUsages_6_oe() throws Exception {
        final FailingKeyedPooledObjectFactory factory = new FailingKeyedPooledObjectFactory();
        final KeyedObjectPool<Object,Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        if (pool instanceof GenericKeyedObjectPool) {
            ((GenericKeyedObjectPool<Object,Object>) pool).setTestOnBorrow(true);
        }

        /// Test correct behavior code paths

        // existing idle object should be activated and validated
        pool.addObject(KEY);
        clear(factory, expectedMethods);
        obj = pool.borrowObject(KEY);
        expectedMethods.add(new MethodCall("activateObject", KEY, ZERO));
        expectedMethods.add(new MethodCall("validateObject", KEY, ZERO).returned(Boolean.TRUE));
        // removed other assertion
        pool.returnObject(KEY, obj);

        //// Test exception handling of borrowObject
        reset(pool, factory, expectedMethods);

        // makeObject Exceptions should be propagated to client code from borrowObject
        factory.setMakeObjectFail(true);
        try {
            obj = pool.borrowObject(KEY);
            // removed other assertion
        } catch (final PrivateException pe) {
            // expected
        }
        expectedMethods.add(new MethodCall("makeObject", KEY));
        // removed other assertion


        // when activateObject fails in borrowObject, a new object should be borrowed/created
        reset(pool, factory, expectedMethods);
        pool.addObject(KEY);
        clear(factory, expectedMethods);

        factory.setActivateObjectFail(true);
        expectedMethods.add(new MethodCall("activateObject", KEY, obj));
        // removed other assertion
        // After idle object fails validation, new on is created and activation
        // fails again for the new one.
        expectedMethods.add(new MethodCall("makeObject", KEY).returned(ONE));
        expectedMethods.add(new MethodCall("activateObject", KEY, ONE));
        TestObjectPool.removeDestroyObjectCall(factory.getMethodCalls()); // The exact timing of destroyObject is flexible here.
        // removed other assertion

        // when validateObject fails in borrowObject, a new object should be borrowed/created
        reset(pool, factory, expectedMethods);
        pool.addObject(KEY);
        clear(factory, expectedMethods);

        factory.setValidateObjectFail(true);
        // testOnBorrow is on, so this will throw when the newly created instance
        // fails validation
        try {
    pool.borrowObject(KEY);
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    public void testKPOFInvalidateObjectUsages_2_oe() throws Exception {
        final FailingKeyedPooledObjectFactory factory = new FailingKeyedPooledObjectFactory();
        final KeyedObjectPool<Object,Object> pool;
        try {
            pool = makeEmptyPool(factory);
        } catch(final UnsupportedOperationException uoe) {
            return; // test not supported
        }
        final List<MethodCall> expectedMethods = new ArrayList<>();
        Object obj;

        /// Test correct behavior code paths

        obj = pool.borrowObject(KEY);
        clear(factory, expectedMethods);

        // invalidated object should be destroyed
        pool.invalidateObject(KEY, obj);
        expectedMethods.add(new MethodCall("destroyObject", KEY, obj));
        // removed other assertion

        //// Test exception handling of invalidateObject
        reset(pool, factory, expectedMethods);
        final Object obj2 = pool.borrowObject(KEY);
        clear(factory, expectedMethods);
        factory.setDestroyObjectFail(true);
        try {
    pool.invalidateObject(KEY, obj2);
    org.junit.jupiter.api.Assertions.fail("PrivateException: Expecting destroy exception to propagate");
} catch (PrivateException e) {
}
    }

}
