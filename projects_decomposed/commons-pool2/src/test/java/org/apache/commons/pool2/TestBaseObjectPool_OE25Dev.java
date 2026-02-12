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

import org.junit.jupiter.api.Test;

/**
 */
public class TestBaseObjectPool_OE25Dev extends TestObjectPool {
    private static class TestObjectPool extends BaseObjectPool<Object> {
        @Override
        public Object borrowObject() {
            return null;
        }
        @Override
        public void invalidateObject(final Object obj) {
        }
        @Override
        public void returnObject(final Object obj) {
        }
    }

    private ObjectPool<String> pool;

    /**
     * @param n Ignored by this implemented. Used by sub-classes.
     *
     * @return the Nth object (zero indexed)
     */
    protected Object getNthObject(final int n) {
        if (this.getClass() != TestBaseObjectPool_OE25Dev.class) {
            fail("Subclasses of TestBaseObjectPool_OE25Dev must reimplement this method.");
        }
        throw new UnsupportedOperationException("BaseObjectPool isn't a complete implementation.");
    }

    protected boolean isFifo() {
        if (this.getClass() != TestBaseObjectPool_OE25Dev.class) {
            fail("Subclasses of TestBaseObjectPool_OE25Dev must reimplement this method.");
        }
        return false;
    }

    protected boolean isLifo() {
        if (this.getClass() != TestBaseObjectPool_OE25Dev.class) {
            fail("Subclasses of TestBaseObjectPool_OE25Dev must reimplement this method.");
        }
        return false;
    }

    /**
     * @param minCapacity Ignored by this implemented. Used by sub-classes.
     *
     * @return A newly created empty pool
     */
    protected ObjectPool<String> makeEmptyPool(final int minCapacity) {
        if (this.getClass() != TestBaseObjectPool_OE25Dev.class) {
            fail("Subclasses of TestBaseObjectPool_OE25Dev must reimplement this method.");
        }
        throw new UnsupportedOperationException("BaseObjectPool isn't a complete implementation.");
    }

    @Override
    protected ObjectPool<Object> makeEmptyPool(final PooledObjectFactory<Object> factory) {
        if (this.getClass() != TestBaseObjectPool_OE25Dev.class) {
            fail("Subclasses of TestBaseObjectPool_OE25Dev must reimplement this method.");
        }
        throw new UnsupportedOperationException("BaseObjectPool isn't a complete implementation.");
    }

    @Test
    public void testBaseAddObject() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException e) {
            return; // skip this test if unsupported
        }
        try {
            assertEquals(0, pool.getNumIdle());
            assertEquals(0, pool.getNumActive());
            pool.addObject();
            assertEquals(1, pool.getNumIdle());
            assertEquals(0, pool.getNumActive());
            final String obj = pool.borrowObject();
            assertEquals(getNthObject(0), obj);
            assertEquals(0, pool.getNumIdle());
            assertEquals(1, pool.getNumActive());
            pool.returnObject(obj);
            assertEquals(1, pool.getNumIdle());
            assertEquals(0, pool.getNumActive());
        } catch(final UnsupportedOperationException e) {
            return; // skip this test if one of those calls is unsupported
        } finally {
            pool.close();
        }
    }

    @Test
    public void testClose() {
        @SuppressWarnings("resource")
        final ObjectPool<Object> pool = new TestObjectPool();

        pool.close();
        pool.close(); // should not error as of Pool 2.0.
    }

    // tests
    @Test
    public void testUnsupportedOperations() throws Exception {
        if (!getClass().equals(TestBaseObjectPool_OE25Dev.class)) {
            return; // skip redundant tests
        }
        try (final ObjectPool<Object> pool = new TestObjectPool()) {

            assertTrue( pool.getNumIdle() < 0,"Negative expected.");
            assertTrue( pool.getNumActive() < 0,"Negative expected.");

            assertThrows(UnsupportedOperationException.class, pool::clear);
            assertThrows(UnsupportedOperationException.class, pool::addObject);
        }
    }

    @Test
    public void testBaseClosePool_1_oe() throws Exception {
        try {
            pool = makeEmptyPool(3);
        } catch(final UnsupportedOperationException e) {
            return; // skip this test if unsupported
        }
        final String obj = pool.borrowObject();
        pool.returnObject(obj);

        pool.close();
        assertThrows(IllegalStateException.class, pool::borrowObject);
    }

}
