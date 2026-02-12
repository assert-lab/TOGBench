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
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.Waiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TestCase for AbandonedObjectPool
 */
public class TestAbandonedKeyedObjectPool_OE25Dev {

    class ConcurrentBorrower extends Thread {
        private final ArrayList<PooledTestObject> borrowed;

        public ConcurrentBorrower(final ArrayList<PooledTestObject> borrowed) {
            this.borrowed = borrowed;
        }

        @Override
        public void run() {
            try {
                borrowed.add(pool.borrowObject(0));
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
                pool.returnObject(0, returned);
            } catch (final Exception e) {
                // ignore
            }
        }
    }

    private static class SimpleFactory implements KeyedPooledObjectFactory<Integer,PooledTestObject> {

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
        public void activateObject(final Integer key, final PooledObject<PooledTestObject> obj) {
            obj.getObject().setActive(true);
        }

        @Override
        public void destroyObject(final Integer key, final PooledObject<PooledTestObject> obj) throws Exception {
            destroyObject(key, obj, DestroyMode.NORMAL);
        }

        @Override
        public void destroyObject(final Integer key, final PooledObject<PooledTestObject> obj, final DestroyMode destroyMode) throws Exception {
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
        public PooledObject<PooledTestObject> makeObject(final Integer key) {
            return new DefaultPooledObject<>(new PooledTestObject());
        }

        @Override
        public void passivateObject(final Integer key, final PooledObject<PooledTestObject> obj) {
            obj.getObject().setActive(false);
        }

        @Override
        public boolean validateObject(final Integer key, final PooledObject<PooledTestObject> obj) {
            Waiter.sleepQuietly(validateLatency);
            return true;
        }
    }

    private GenericKeyedObjectPool<Integer,PooledTestObject> pool;

    private AbandonedConfig abandonedConfig;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        abandonedConfig = new AbandonedConfig();

        // Uncomment the following line to enable logging:
        // abandonedConfig.setLogAbandoned(true);

        // One second Duration.
        abandonedConfig.setRemoveAbandonedTimeout(TestConstants.ONE_SECOND_DURATION);
        assertEquals(TestConstants.ONE_SECOND_DURATION, abandonedConfig.getRemoveAbandonedTimeoutDuration());
        assertEquals(1, abandonedConfig.getRemoveAbandonedTimeout());
        // One second int (not millis).
        abandonedConfig.setRemoveAbandonedTimeout(1);
        assertEquals(TestConstants.ONE_SECOND_DURATION, abandonedConfig.getRemoveAbandonedTimeoutDuration());
        assertEquals(1, abandonedConfig.getRemoveAbandonedTimeout());

        pool = new GenericKeyedObjectPool<>(
               new SimpleFactory(),
               new GenericKeyedObjectPoolConfig<>(),
               abandonedConfig);
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
                "org.apache.commoms.pool2:type=GenericKeyedObjectPool,*"), null);
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
     * in GenericKeyedObjectPool
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


}

