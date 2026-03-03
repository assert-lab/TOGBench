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

package org.apache.commons.pool2.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.TrackedUse;
import org.apache.commons.pool2.Waiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TestCase for AbandonedObjectPool
 */
public class TestAbandonedObjectPool_OE25Dev {

    class ConcurrentBorrower extends Thread {
        private final ArrayList<PooledTestObject> borrowed;

        public ConcurrentBorrower(final ArrayList<PooledTestObject> borrowed) {
            this.borrowed = borrowed;
        }

        @Override
        public void run() {
            try {
                borrowed.add(pool.borrowObject());
            } catch (final Exception e) {
                // expected in most cases
            }
        }
    }
    class ConcurrentReturner extends Thread {
        private final PooledTestObject returned;
        public ConcurrentReturner(final PooledTestObject obj) {
            returned = obj;
        }
        @Override
        public void run() {
            try {
                sleep(20);
                pool.returnObject(returned);
            } catch (final Exception e) {
                // ignore
            }
        }
    }

    private static class SimpleFactory implements PooledObjectFactory<PooledTestObject> {

        private final long destroyLatency;
        private final long validateLatency;

        public SimpleFactory() {
            destroyLatency = 0;
            validateLatency = 0;
        }

        public SimpleFactory(final long destroyLatency, final long validateLatency) {
            this.destroyLatency = destroyLatency;
            this.validateLatency = validateLatency;
        }

        @Override
        public void activateObject(final PooledObject<PooledTestObject> obj) {
            obj.getObject().setActive(true);
        }

        @Override
        public void destroyObject(final PooledObject<PooledTestObject> obj) throws Exception {
            destroyObject(obj, DestroyMode.NORMAL);
        }

        @Override
        public void destroyObject(final PooledObject<PooledTestObject> obj, final DestroyMode destroyMode) throws Exception {
            obj.getObject().setActive(false);
            // while destroying instances, yield control to other threads
            // helps simulate threading errors
            Thread.yield();
            if (destroyLatency != 0) {
                Thread.sleep(destroyLatency);
            }
            obj.getObject().destroy(destroyMode);
        }

        @Override
        public PooledObject<PooledTestObject> makeObject() {
            return new DefaultPooledObject<>(new PooledTestObject());
        }

        @Override
        public void passivateObject(final PooledObject<PooledTestObject> obj) {
            obj.getObject().setActive(false);
        }

        @Override
        public boolean validateObject(final PooledObject<PooledTestObject> obj) {
            Waiter.sleepQuietly(validateLatency);
            return true;
        }
    }

    private GenericObjectPool<PooledTestObject> pool;

    private AbandonedConfig abandonedConfig;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() throws Exception {
        abandonedConfig = new AbandonedConfig();

        // Uncomment the following line to enable logging:
        // abandonedConfig.setLogAbandoned(true);

        abandonedConfig.setRemoveAbandonedOnBorrow(true);
        // One second Duration.
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        assertEquals(TestConstants.ONE_SECOND_DURATION, abandonedConfig.getRemoveAbandonedTimeoutDuration());
        assertEquals(1,abandonedConfig.getRemoveAbandonedTimeout());// in seconds. abandonedConfig.setRemoveAbandonedTimeout(1);
        assertEquals(TestConstants.ONE_SECOND_DURATION, abandonedConfig.getRemoveAbandonedTimeoutDuration());
        assertEquals(1,abandonedConfig.getRemoveAbandonedTimeout());// in seconds. pool = new GenericObjectPool<>(new SimpleFactory(),new GenericObjectPoolConfig<>(),abandonedConfig);
    }

    @AfterEach
    public void tearDown() throws Exception {
        final ObjectName jmxName = pool.getJmxName();
        final String poolName = Objects.toString(jmxName, null);
        pool.clear();
        pool.close();
        pool = null;

        final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        final Set<ObjectName> result = mbs.queryNames(new ObjectName(
                "org.apache.commoms.pool2:type=GenericObjectPool,*"), null);
        // There should be no registered pools at this point
        final int registeredPoolCount = result.size();
        final StringBuilder msg = new StringBuilder("Current pool is: ");
        msg.append(poolName);
        msg.append("  Still open pools are: ");
        for (final ObjectName name : result) {
            // Clean these up ready for the next test
            msg.append(name.toString());
            msg.append(" created via\n");
            msg.append(mbs.getAttribute(name, "CreationStackTrace"));
            msg.append('\n');
            mbs.unregisterMBean(name);
        }
        assertEquals( 0, registeredPoolCount,msg.toString());
    }

    /**
     * Verify that an object that gets flagged as abandoned and is subsequently
     * invalidated is only destroyed (and pool counter decremented) once.
     *
     * @throws Exception May occur in some failure modes
     */

    /**
     * Verify that an object that gets flagged as abandoned and is subsequently returned
     * is destroyed instead of being returned to the pool (and possibly later destroyed
     * inappropriately).
     *
     * @throws Exception May occur in some failure modes
     */

    /**
     * Tests fix for Bug 28579, a bug in AbandonedObjectPool that causes numActive to go negative
     * in GenericObjectPool
     *
     * @throws Exception May occur in some failure modes
     */

    /**
     * Verify that an object that the evictor identifies as abandoned while it
     * is in process of being returned to the pool is not destroyed.
     *
     * @throws Exception May occur in some failure modes
     */

    /**
     * JIRA: POOL-300
     */

    /**
     * Test case for https://issues.apache.org/jira/browse/DBCP-260.
     * Borrow and abandon all the available objects then attempt to borrow one
     * further object which should block until the abandoned objects are
     * removed. We don't want the test to block indefinitely when it fails so
     * use maxWait be check we don't actually have to wait that long.
     *
     * @throws Exception May occur in some failure modes
     */

    @Test
    public void testAbandonedInvalidate_1_oe() throws Exception {
        abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        pool.close();  // Unregister pool created by setup
        pool = new GenericObjectPool<>(
                new SimpleFactory(200, 0),
                new GenericObjectPoolConfig<>(), abandonedConfig);
        final int n = 10;
        pool.setMaxTotal(n);
        pool.setBlockWhenExhausted(false);
        pool.setTimeBetweenEvictionRuns(Duration.ofMillis(500));
        PooledTestObject obj = null;
        for (int i = 0; i < 5; i++) {
            obj = pool.borrowObject();
        }
        Thread.sleep(1000);          // abandon checked out instances and let evictor start
        pool.invalidateObject(obj);  // Should not trigger another destroy / decrement
        Thread.sleep(2000);          // give evictor time to finish destroys
        assertEquals(0, pool.getNumActive());
    }

    @Test
    public void testAbandonedInvalidate_2_oe() throws Exception {
        abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        pool.close();  // Unregister pool created by setup
        pool = new GenericObjectPool<>(
                new SimpleFactory(200, 0),
                new GenericObjectPoolConfig<>(), abandonedConfig);
        final int n = 10;
        pool.setMaxTotal(n);
        pool.setBlockWhenExhausted(false);
        pool.setTimeBetweenEvictionRuns(Duration.ofMillis(500));
        PooledTestObject obj = null;
        for (int i = 0; i < 5; i++) {
            obj = pool.borrowObject();
        }
        Thread.sleep(1000);          // abandon checked out instances and let evictor start
        pool.invalidateObject(obj);  // Should not trigger another destroy / decrement
        Thread.sleep(2000);          // give evictor time to finish destroys
        assertEquals(5, pool.getDestroyedCount());
    }

    @Test
    public void testAbandonedReturn_1_oe() throws Exception {
        abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnBorrow(true);
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        pool.close();  // Unregister pool created by setup
        pool = new GenericObjectPool<>(
                new SimpleFactory(200, 0),
                new GenericObjectPoolConfig<>(), abandonedConfig);
        final int n = 10;
        pool.setMaxTotal(n);
        pool.setBlockWhenExhausted(false);
        PooledTestObject obj = null;
        for (int i = 0; i < n - 2; i++) {
            obj = pool.borrowObject();
        }
        Objects.requireNonNull(obj, "Unable to borrow object from pool");
        final int deadMansHash = obj.hashCode();
        final ConcurrentReturner returner = new ConcurrentReturner(obj);
        Thread.sleep(2000);  // abandon checked out instances
        returner.start();    // short delay, then return instance
        assertTrue(pool.borrowObject().hashCode() != deadMansHash);
    }

    @Test
    public void testAbandonedReturn_2_oe() throws Exception {
        abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnBorrow(true);
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        pool.close();  // Unregister pool created by setup
        pool = new GenericObjectPool<>(
                new SimpleFactory(200, 0),
                new GenericObjectPoolConfig<>(), abandonedConfig);
        final int n = 10;
        pool.setMaxTotal(n);
        pool.setBlockWhenExhausted(false);
        PooledTestObject obj = null;
        for (int i = 0; i < n - 2; i++) {
            obj = pool.borrowObject();
        }
        Objects.requireNonNull(obj, "Unable to borrow object from pool");
        final int deadMansHash = obj.hashCode();
        final ConcurrentReturner returner = new ConcurrentReturner(obj);
        Thread.sleep(2000);  // abandon checked out instances
        returner.start();    // short delay, then return instance
        assertEquals(0, pool.getNumIdle());
    }

    @Test
    public void testAbandonedReturn_3_oe() throws Exception {
        abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnBorrow(true);
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        pool.close();  // Unregister pool created by setup
        pool = new GenericObjectPool<>(
                new SimpleFactory(200, 0),
                new GenericObjectPoolConfig<>(), abandonedConfig);
        final int n = 10;
        pool.setMaxTotal(n);
        pool.setBlockWhenExhausted(false);
        PooledTestObject obj = null;
        for (int i = 0; i < n - 2; i++) {
            obj = pool.borrowObject();
        }
        Objects.requireNonNull(obj, "Unable to borrow object from pool");
        final int deadMansHash = obj.hashCode();
        final ConcurrentReturner returner = new ConcurrentReturner(obj);
        Thread.sleep(2000);  // abandon checked out instances
        returner.start();    // short delay, then return instance
        assertEquals(1, pool.getNumActive());
    }

    @Test
    public void testConcurrentInvalidation_1_oe() throws Exception {
        final int POOL_SIZE = 30;
        pool.setMaxTotal(POOL_SIZE);
        pool.setMaxIdle(POOL_SIZE);
        pool.setBlockWhenExhausted(false);

        final ArrayList<PooledTestObject> vec = new ArrayList<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            vec.add(pool.borrowObject());
        }

        for (final PooledTestObject element : vec) {
            element.setAbandoned(true);
        }

        final int CONCURRENT_BORROWS = 5;
        final Thread[] threads = new Thread[CONCURRENT_BORROWS];
        for (int i = 0; i < CONCURRENT_BORROWS; i++) {
            threads[i] = new ConcurrentBorrower(vec);
            threads[i].start();
        }

        for (int i = 0; i < CONCURRENT_BORROWS; i++) {
            threads[i].join();
        }

        for (final PooledTestObject pto : vec) {
            if (pto.isActive()) {
                pool.returnObject(pto);
            }
        }

        assertEquals(0, pool.getNumActive(), "numActive should have been 0, was " + pool.getNumActive());
    }

    public void testDestroyModeAbandoned_1_oe() throws Exception {
        abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        pool.close();  // Unregister pool created by setup
        pool = new GenericObjectPool<>(
             new SimpleFactory(0, 0),
             new GenericObjectPoolConfig<>(), abandonedConfig);
        pool.setTimeBetweenEvictionRuns(Duration.ofMillis(50));
        final PooledTestObject obj = pool.borrowObject();
        Thread.sleep(100);
        assertTrue(obj.isDetached());
    }

    public void testDestroyModeNormal_1_oe() throws Exception {
        abandonedConfig = new AbandonedConfig();
        pool.close();  // Unregister pool created by setup
        pool = new GenericObjectPool<>(new SimpleFactory(0, 0));
        pool.setMaxIdle(0);
        final PooledTestObject obj = pool.borrowObject();
        pool.returnObject(obj);
        assertTrue(obj.isDestroyed());
    }

    public void testDestroyModeNormal_2_oe() throws Exception {
        abandonedConfig = new AbandonedConfig();
        pool.close();  // Unregister pool created by setup
        pool = new GenericObjectPool<>(new SimpleFactory(0, 0));
        pool.setMaxIdle(0);
        final PooledTestObject obj = pool.borrowObject();
        pool.returnObject(obj);
        assertFalse(obj.isDetached());
    }

    @Test
    public void testRemoveAbandonedWhileReturning_1_oe() throws Exception {
        abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        pool.close();  // Unregister pool created by setup
        pool = new GenericObjectPool<>(
             new SimpleFactory(0, 1000),
             new GenericObjectPoolConfig<>(), abandonedConfig);
        final int n = 10;
        pool.setMaxTotal(n);
        pool.setBlockWhenExhausted(false);
        pool.setTimeBetweenEvictionRuns(Duration.ofMillis(500));
        pool.setTestOnReturn(true);
        final PooledTestObject obj = pool.borrowObject();
        Thread.sleep(50);       // abandon obj
        pool.returnObject(obj); // evictor will run during validation
        final PooledTestObject obj2 = pool.borrowObject();
        assertEquals(obj, obj2);          // should get original back;
    }

    @Test
    public void testRemoveAbandonedWhileReturning_2_oe() throws Exception {
        abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        pool.close();  // Unregister pool created by setup
        pool = new GenericObjectPool<>(
             new SimpleFactory(0, 1000),
             new GenericObjectPoolConfig<>(), abandonedConfig);
        final int n = 10;
        pool.setMaxTotal(n);
        pool.setBlockWhenExhausted(false);
        pool.setTimeBetweenEvictionRuns(Duration.ofMillis(500));
        pool.setTestOnReturn(true);
        final PooledTestObject obj = pool.borrowObject();
        Thread.sleep(50);       // abandon obj
        pool.returnObject(obj); // evictor will run during validation
        final PooledTestObject obj2 = pool.borrowObject();
        assertFalse(obj2.isDestroyed());  // and not destroyed;
    }

    @Test
    public void testStackTrace_1_oe() throws Exception {
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        abandonedConfig.setLogAbandoned(true);
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BufferedOutputStream bos = new BufferedOutputStream(baos);
        final PrintWriter pw = new PrintWriter(bos);
        abandonedConfig.setLogWriter(pw);
        pool.setAbandonedConfig(abandonedConfig);
        pool.setTimeBetweenEvictionRuns(Duration.ofMillis(100));
        final PooledTestObject o1 = pool.borrowObject();
        Thread.sleep(2000);
        assertTrue(o1.isDestroyed());
    }

    @Test
    public void testStackTrace_2_oe() throws Exception {
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        abandonedConfig.setLogAbandoned(true);
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BufferedOutputStream bos = new BufferedOutputStream(baos);
        final PrintWriter pw = new PrintWriter(bos);
        abandonedConfig.setLogWriter(pw);
        pool.setAbandonedConfig(abandonedConfig);
        pool.setTimeBetweenEvictionRuns(Duration.ofMillis(100));
        final PooledTestObject o1 = pool.borrowObject();
        Thread.sleep(2000);
        bos.flush();
        assertTrue(baos.toString().indexOf("Pooled object") >= 0);
    }

    @Test
    public void testWhenExhaustedBlock_1_oe() throws Exception {
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        pool.setAbandonedConfig(abandonedConfig);
        pool.setTimeBetweenEvictionRuns(Duration.ofMillis(500));

        pool.setMaxTotal(1);

        @SuppressWarnings("unused") // This is going to be abandoned
        final PooledTestObject o1 = pool.borrowObject();

        final long startMillis = System.currentTimeMillis();
        final PooledTestObject o2 = pool.borrowObject(5000);
        final long endMillis = System.currentTimeMillis();

        pool.returnObject(o2);

        assertTrue(endMillis - startMillis < 5000);
    }

}

