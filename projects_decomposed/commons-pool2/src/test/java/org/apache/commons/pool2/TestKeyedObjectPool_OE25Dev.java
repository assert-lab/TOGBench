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
    public void testBaseBorrow_1_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        assertEquals(getNthObject(keya,0),pool.borrowObject(keya),"1");
    }

    @Test
    public void testBaseBorrow_2_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        assertEquals(getNthObject(keyb,0),pool.borrowObject(keyb),"2");
    }

    @Test
    public void testBaseBorrow_3_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        assertEquals(getNthObject(keyb,1),pool.borrowObject(keyb),"3");
    }

    @Test
    public void testBaseBorrow_4_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(getNthObject(keya,1),pool.borrowObject(keya),"4");
    }

    @Test
    public void testBaseBorrow_5_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(getNthObject(keyb,2),pool.borrowObject(keyb),"5");
    }

    @Test
    public void testBaseBorrow_6_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(getNthObject(keya,2),pool.borrowObject(keya),"6");
    }

    @Test
    public void testBaseBorrowReturn_1_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        Object obj0 = pool.borrowObject(keya);
        assertEquals(getNthObject(keya,0),obj0);
    }

    @Test
    public void testBaseBorrowReturn_2_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        Object obj1 = pool.borrowObject(keya);
        assertEquals(getNthObject(keya,1),obj1);
    }

    @Test
    public void testBaseBorrowReturn_3_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        Object obj2 = pool.borrowObject(keya);
        assertEquals(getNthObject(keya,2),obj2);
    }

    @Test
    public void testBaseBorrowReturn_4_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        Object obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj2);
        obj2 = pool.borrowObject(keya);
        assertEquals(getNthObject(keya,2),obj2);
    }

    @Test
    public void testBaseBorrowReturn_5_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        Object obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj2);
        obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj1);
        obj1 = pool.borrowObject(keya);
        assertEquals(getNthObject(keya,1),obj1);
    }

    @Test
    public void testBaseBorrowReturn_6_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        Object obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj2);
        obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj1);
        obj1 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj0);
        pool.returnObject(keya,obj2);
        obj2 = pool.borrowObject(keya);
        if (isLifo()) {
            assertEquals(getNthObject(keya,2),obj2);
    }
    }

    @Test
    public void testBaseBorrowReturn_7_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        Object obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj2);
        obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj1);
        obj1 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj0);
        pool.returnObject(keya,obj2);
        obj2 = pool.borrowObject(keya);
        if (isLifo()) {
            // removed other assertion
        }
        if (isFifo()) {
            assertEquals(getNthObject(keya,0),obj2);
    }
    }

    @Test
    public void testBaseBorrowReturn_8_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        Object obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj2);
        obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj1);
        obj1 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj0);
        pool.returnObject(keya,obj2);
        obj2 = pool.borrowObject(keya);
        if (isLifo()) {
            // removed other assertion
        }
        if (isFifo()) {
            // removed other assertion
        }
        obj0 = pool.borrowObject(keya);
        if (isLifo()) {
            assertEquals(getNthObject(keya,0),obj0);
    }
    }

    @Test
    public void testBaseBorrowReturn_9_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        Object obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj2);
        obj2 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj1);
        obj1 = pool.borrowObject(keya);
        // removed other assertion
        pool.returnObject(keya,obj0);
        pool.returnObject(keya,obj2);
        obj2 = pool.borrowObject(keya);
        if (isLifo()) {
            // removed other assertion
        }
        if (isFifo()) {
            // removed other assertion
        }
        obj0 = pool.borrowObject(keya);
        if (isLifo()) {
            // removed other assertion
        }
        if (isFifo()) {
            assertEquals(getNthObject(keya,2),obj0);
    }
    }

    @Test
    public void testBaseClear_1_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        assertEquals(0,pool.getNumActive(keya));
    }

    @Test
    public void testBaseClear_2_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseClear_3_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        assertEquals(2,pool.getNumActive(keya));
    }

    @Test
    public void testBaseClear_4_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseClear_5_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        pool.returnObject(keya,obj0);
        assertEquals(0,pool.getNumActive(keya));
    }

    @Test
    public void testBaseClear_6_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        pool.returnObject(keya,obj0);
        // removed other assertion
        assertEquals(2,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseClear_7_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        pool.returnObject(keya,obj0);
        // removed other assertion
        // removed other assertion
        pool.clear(keya);
        assertEquals(0,pool.getNumActive(keya));
    }

    @Test
    public void testBaseClear_8_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        pool.returnObject(keya,obj0);
        // removed other assertion
        // removed other assertion
        pool.clear(keya);
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseClear_9_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        pool.returnObject(keya,obj0);
        // removed other assertion
        // removed other assertion
        pool.clear(keya);
        // removed other assertion
        // removed other assertion
        final Object obj2 = pool.borrowObject(keya);
        assertEquals(getNthObject(keya,2),obj2);
    }

    @Test
    public void testBaseInvalidateObject_1_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        assertEquals(0,pool.getNumActive(keya));
    }

    @Test
    public void testBaseInvalidateObject_2_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseInvalidateObject_3_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        assertEquals(2,pool.getNumActive(keya));
    }

    @Test
    public void testBaseInvalidateObject_4_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseInvalidateObject_5_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.invalidateObject(keya,obj0);
        assertEquals(1,pool.getNumActive(keya));
    }

    @Test
    public void testBaseInvalidateObject_6_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.invalidateObject(keya,obj0);
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseInvalidateObject_7_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.invalidateObject(keya,obj0);
        // removed other assertion
        // removed other assertion
        pool.invalidateObject(keya,obj1);
        assertEquals(0,pool.getNumActive(keya));
    }

    @Test
    public void testBaseInvalidateObject_8_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.invalidateObject(keya,obj0);
        // removed other assertion
        // removed other assertion
        pool.invalidateObject(keya,obj1);
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_1_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        assertEquals(0,pool.getNumActive(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_2_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_3_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        assertEquals(1,pool.getNumActive(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_4_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_5_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        final Object obj1 = pool.borrowObject(keya);
        assertEquals(2,pool.getNumActive(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_6_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_7_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        assertEquals(1,pool.getNumActive(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_8_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        // removed other assertion
        assertEquals(1,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_9_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj0);
        assertEquals(0,pool.getNumActive(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_10_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj0);
        // removed other assertion
        assertEquals(2,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle_11_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj0);
        // removed other assertion
        // removed other assertion

        assertEquals(0,pool.getNumActive("xyzzy12345"));
    }

    @Test
    public void testBaseNumActiveNumIdle_12_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        // removed other assertion
        // removed other assertion
        final Object obj0 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        final Object obj1 = pool.borrowObject(keya);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj1);
        // removed other assertion
        // removed other assertion
        pool.returnObject(keya,obj0);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0,pool.getNumIdle("xyzzy12345"));
    }

    @Test
    public void testBaseNumActiveNumIdle2_1_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        assertEquals(0,pool.getNumActive());
    }

    @Test
    public void testBaseNumActiveNumIdle2_2_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        assertEquals(0,pool.getNumIdle());
    }

    @Test
    public void testBaseNumActiveNumIdle2_3_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        assertEquals(0,pool.getNumActive(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle2_4_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle2_5_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,pool.getNumActive(keyb));
    }

    @Test
    public void testBaseNumActiveNumIdle2_6_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keyb));
    }

    @Test
    public void testBaseNumActiveNumIdle2_7_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        assertEquals(2,pool.getNumActive());
    }

    @Test
    public void testBaseNumActiveNumIdle2_8_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        assertEquals(0,pool.getNumIdle());
    }

    @Test
    public void testBaseNumActiveNumIdle2_9_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        assertEquals(1,pool.getNumActive(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle2_10_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle2_11_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,pool.getNumActive(keyb));
    }

    @Test
    public void testBaseNumActiveNumIdle2_12_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keyb));
    }

    @Test
    public void testBaseNumActiveNumIdle2_13_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        assertEquals(4,pool.getNumActive());
    }

    @Test
    public void testBaseNumActiveNumIdle2_14_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        assertEquals(0,pool.getNumIdle());
    }

    @Test
    public void testBaseNumActiveNumIdle2_15_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        assertEquals(2,pool.getNumActive(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle2_16_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle2_17_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,pool.getNumActive(keyb));
    }

    @Test
    public void testBaseNumActiveNumIdle2_18_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,pool.getNumIdle(keyb));
    }

    @Test
    public void testBaseNumActiveNumIdle2_19_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        assertEquals(2,pool.getNumActive());
    }

    @Test
    public void testBaseNumActiveNumIdle2_20_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        assertEquals(2,pool.getNumIdle());
    }

    @Test
    public void testBaseNumActiveNumIdle2_21_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        // removed other assertion
        assertEquals(1,pool.getNumActive(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle2_22_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle2_23_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,pool.getNumActive(keyb));
    }

    @Test
    public void testBaseNumActiveNumIdle2_24_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,pool.getNumIdle(keyb));
    }

    @Test
    public void testBaseNumActiveNumIdle2_25_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA1);
        pool.returnObject(keyb,objB1);

        assertEquals(0,pool.getNumActive());
    }

    @Test
    public void testBaseNumActiveNumIdle2_26_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA1);
        pool.returnObject(keyb,objB1);

        // removed other assertion
        assertEquals(4,pool.getNumIdle());
    }

    @Test
    public void testBaseNumActiveNumIdle2_27_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA1);
        pool.returnObject(keyb,objB1);

        // removed other assertion
        // removed other assertion
        assertEquals(0,pool.getNumActive(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle2_28_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA1);
        pool.returnObject(keyb,objB1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,pool.getNumIdle(keya));
    }

    @Test
    public void testBaseNumActiveNumIdle2_29_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA1);
        pool.returnObject(keyb,objB1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0,pool.getNumActive(keyb));
    }

    @Test
    public void testBaseNumActiveNumIdle2_30_oe() throws Exception {
        try {
            pool = makeEmptyPool(6);
        } catch(final UnsupportedOperationException uoe) {
            return; // skip this test if unsupported
        }
        final Object keya = makeKey(0);
        final Object keyb = makeKey(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA0 = pool.borrowObject(keya);
        final Object objB0 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object objA1 = pool.borrowObject(keya);
        final Object objB1 = pool.borrowObject(keyb);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA0);
        pool.returnObject(keyb,objB0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        pool.returnObject(keya,objA1);
        pool.returnObject(keyb,objB1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,pool.getNumIdle(keyb));
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

        assertThrows(IllegalStateException.class, () -> pool.addObject(KEY), "A closed pool must throw an IllegalStateException when addObject is called.");
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

        assertThrows(IllegalStateException.class, () -> pool.borrowObject(KEY), "A closed pool must throw an IllegalStateException when borrowObject is called.");
    }

    @Test
    public void testClosedPoolBehavior_3_oe() throws Exception {
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

        // removed other assertion

        // The following should not throw exceptions just because the pool is closed.
        assertEquals( 0, pool.getNumIdle(KEY),"A closed pool shouldn't have any idle objects.");
    }

    @Test
    public void testClosedPoolBehavior_4_oe() throws Exception {
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

        // removed other assertion

        // The following should not throw exceptions just because the pool is closed.
        // removed other assertion
        assertEquals( 0, pool.getNumIdle(),"A closed pool shouldn't have any idle objects.");
    }

    @Test
    public void testClosedPoolBehavior_5_oe() throws Exception {
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

        // removed other assertion

        // The following should not throw exceptions just because the pool is closed.
        // removed other assertion
        // removed other assertion
        pool.getNumActive();
        pool.getNumActive(KEY);
        pool.returnObject(KEY, o1);
        assertEquals( 0, pool.getNumIdle(KEY),"returnObject should not add items back into the idle object pool for a closed pool.");
    }

    @Test
    public void testClosedPoolBehavior_6_oe() throws Exception {
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

        // removed other assertion

        // The following should not throw exceptions just because the pool is closed.
        // removed other assertion
        // removed other assertion
        pool.getNumActive();
        pool.getNumActive(KEY);
        pool.returnObject(KEY, o1);
        // removed other assertion
        assertEquals( 0, pool.getNumIdle(),"returnObject should not add items back into the idle object pool for a closed pool.");
    }

    @Test
    public void testKPOFAddObjectUsage_1_oe() throws Exception {
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
        assertEquals(expectedMethods, factory.getMethodCalls());
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
        assertThrows(PrivateException.class, () -> pool.addObject(KEY), "Expected addObject to propagate makeObject exception.");
    }

    @Test
    public void testKPOFAddObjectUsage_3_oe() throws Exception {
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
        assertEquals(expectedMethods, factory.getMethodCalls());
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
        assertThrows(PrivateException.class, () -> pool.addObject(KEY), "Expected addObject to propagate passivateObject exception.");
    }

    @Test
    public void testKPOFAddObjectUsage_5_oe() throws Exception {
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
        // removed other assertion
        expectedMethods.add(new MethodCall("makeObject", KEY).returned(ONE));
        expectedMethods.add(new MethodCall("passivateObject", KEY, ONE));
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testKPOFBorrowObjectUsages_1_oe() throws Exception {
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
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testKPOFBorrowObjectUsages_3_oe() throws Exception {
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
        assertEquals(expectedMethods, factory.getMethodCalls());
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
        assertThrows(NoSuchElementException.class, () -> pool.borrowObject(KEY));
    }

    @Test
    public void testKPOFBorrowObjectUsages_5_oe() throws Exception {
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
        assertEquals(expectedMethods, factory.getMethodCalls());
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
        assertThrows(NoSuchElementException.class, () -> pool.borrowObject(KEY));
    }

    @Test
    public void testKPOFBorrowObjectUsages_7_oe() throws Exception {
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
        // removed other assertion
        // Activate, then validate for idle instance
        expectedMethods.add(new MethodCall("activateObject", KEY, ZERO));
        expectedMethods.add(new MethodCall("validateObject", KEY, ZERO));
        // Make new instance, activate succeeds, validate fails
        expectedMethods.add(new MethodCall("makeObject", KEY).returned(ONE));
        expectedMethods.add(new MethodCall("activateObject", KEY, ONE));
        expectedMethods.add(new MethodCall("validateObject", KEY, ONE));
        TestObjectPool.removeDestroyObjectCall(factory.getMethodCalls());
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testKPOFInvalidateObjectUsages_1_oe() throws Exception {
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
        assertEquals(expectedMethods, factory.getMethodCalls());
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
        assertThrows(PrivateException.class, () -> pool.invalidateObject(KEY, obj2), "Expecting destroy exception to propagate");
    }

    @Test
    public void testKPOFInvalidateObjectUsages_3_oe() throws Exception {
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
        // removed other assertion
        Thread.sleep(250); // could be defered
        TestObjectPool.removeDestroyObjectCall(factory.getMethodCalls());
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testKPOFReturnObjectUsages_1_oe() throws Exception {
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

        // returned object should be passivated
        pool.returnObject(KEY, obj);
        expectedMethods.add(new MethodCall("passivateObject", KEY, obj));
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testKPOFReturnObjectUsages_2_oe() throws Exception {
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

        // returned object should be passivated
        pool.returnObject(KEY, obj);
        expectedMethods.add(new MethodCall("passivateObject", KEY, obj));
        // removed other assertion

        //// Test exception handling of returnObject
        reset(pool, factory, expectedMethods);

        // passivateObject should swallow exceptions and not add the object to the pool
        pool.addObject(KEY);
        pool.addObject(KEY);
        pool.addObject(KEY);
        assertEquals(3, pool.getNumIdle(KEY));
    }

    @Test
    public void testKPOFReturnObjectUsages_3_oe() throws Exception {
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

        // returned object should be passivated
        pool.returnObject(KEY, obj);
        expectedMethods.add(new MethodCall("passivateObject", KEY, obj));
        // removed other assertion

        //// Test exception handling of returnObject
        reset(pool, factory, expectedMethods);

        // passivateObject should swallow exceptions and not add the object to the pool
        pool.addObject(KEY);
        pool.addObject(KEY);
        pool.addObject(KEY);
        // removed other assertion
        obj = pool.borrowObject(KEY);
        obj = pool.borrowObject(KEY);
        assertEquals(1, pool.getNumIdle(KEY));
    }

    @Test
    public void testKPOFReturnObjectUsages_4_oe() throws Exception {
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

        // returned object should be passivated
        pool.returnObject(KEY, obj);
        expectedMethods.add(new MethodCall("passivateObject", KEY, obj));
        // removed other assertion

        //// Test exception handling of returnObject
        reset(pool, factory, expectedMethods);

        // passivateObject should swallow exceptions and not add the object to the pool
        pool.addObject(KEY);
        pool.addObject(KEY);
        pool.addObject(KEY);
        // removed other assertion
        obj = pool.borrowObject(KEY);
        obj = pool.borrowObject(KEY);
        // removed other assertion
        assertEquals(2, pool.getNumActive(KEY));
    }

    @Test
    public void testKPOFReturnObjectUsages_5_oe() throws Exception {
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

        // returned object should be passivated
        pool.returnObject(KEY, obj);
        expectedMethods.add(new MethodCall("passivateObject", KEY, obj));
        // removed other assertion

        //// Test exception handling of returnObject
        reset(pool, factory, expectedMethods);

        // passivateObject should swallow exceptions and not add the object to the pool
        pool.addObject(KEY);
        pool.addObject(KEY);
        pool.addObject(KEY);
        // removed other assertion
        obj = pool.borrowObject(KEY);
        obj = pool.borrowObject(KEY);
        // removed other assertion
        // removed other assertion
        clear(factory, expectedMethods);
        factory.setPassivateObjectFail(true);
        pool.returnObject(KEY, obj);
        expectedMethods.add(new MethodCall("passivateObject", KEY, obj));
        TestObjectPool.removeDestroyObjectCall(factory.getMethodCalls()); // The exact timing of destroyObject is flexible here.
        assertEquals(expectedMethods, factory.getMethodCalls());
    }

    @Test
    public void testKPOFReturnObjectUsages_6_oe() throws Exception {
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

        // returned object should be passivated
        pool.returnObject(KEY, obj);
        expectedMethods.add(new MethodCall("passivateObject", KEY, obj));
        // removed other assertion

        //// Test exception handling of returnObject
        reset(pool, factory, expectedMethods);

        // passivateObject should swallow exceptions and not add the object to the pool
        pool.addObject(KEY);
        pool.addObject(KEY);
        pool.addObject(KEY);
        // removed other assertion
        obj = pool.borrowObject(KEY);
        obj = pool.borrowObject(KEY);
        // removed other assertion
        // removed other assertion
        clear(factory, expectedMethods);
        factory.setPassivateObjectFail(true);
        pool.returnObject(KEY, obj);
        expectedMethods.add(new MethodCall("passivateObject", KEY, obj));
        TestObjectPool.removeDestroyObjectCall(factory.getMethodCalls()); // The exact timing of destroyObject is flexible here.
        // removed other assertion
        assertEquals(1,pool.getNumIdle(KEY));// Not added assertEquals(1,pool.getNumActive(KEY));// But not active reset(pool,factory,expectedMethods);
    }

}
