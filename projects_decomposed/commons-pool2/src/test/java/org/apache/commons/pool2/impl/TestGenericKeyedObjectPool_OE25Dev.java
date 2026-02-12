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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.TestKeyedObjectPool;
import org.apache.commons.pool2.VisitTracker;
import org.apache.commons.pool2.VisitTrackerFactory;
import org.apache.commons.pool2.Waiter;
import org.apache.commons.pool2.WaiterFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 */
public class TestGenericKeyedObjectPool_OE25Dev extends TestKeyedObjectPool {

    private static class DaemonThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(final Runnable r) {
            final Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    }

    private static class DummyFactory
            extends BaseKeyedPooledObjectFactory<Object,Object> {
        @Override
        public Object create(final Object key) throws Exception {
            return null;
        }
        @Override
        public PooledObject<Object> wrap(final Object value) {
            return new DefaultPooledObject<>(value);
        }
    }

    /**
     * Factory that creates HashSets.  Note that this means
     *  0) All instances are initially equal (not discernible by equals)
     *  1) Instances are mutable and mutation can cause change in identity / hashcode.
     */
    private static final class HashSetFactory
            extends BaseKeyedPooledObjectFactory<String, HashSet<String>> {
        @Override
        public HashSet<String> create(final String key) throws Exception {
            return new HashSet<>();
        }
        @Override
        public PooledObject<HashSet<String>> wrap(final HashSet<String> value) {
            return new DefaultPooledObject<>(value);
        }
    }

    /**
     * Attempts to invalidate an object, swallowing IllegalStateException.
     */
    static class InvalidateThread implements Runnable {
        private final String obj;
        private final KeyedObjectPool<String, String> pool;
        private final String key;
        private boolean done;
        public InvalidateThread(final KeyedObjectPool<String, String> pool, final String key, final String obj) {
            this.obj = obj;
            this.pool = pool;
            this.key = key;
        }
        public boolean complete() {
            return done;
        }
        @Override
        public void run() {
            try {
                pool.invalidateObject(key, obj);
            } catch (final IllegalStateException ex) {
                // Ignore
            } catch (final Exception ex) {
                fail("Unexpected exception " + ex.toString());
            } finally {
                done = true;
            }
        }
    }

    private static class ObjectFactory
        extends BaseKeyedPooledObjectFactory<Integer, Object> {

        @Override
        public Object create(final Integer key)
            throws Exception {
            return new Object();
        }

        @Override
        public PooledObject<Object> wrap(final Object value) {
            return new DefaultPooledObject<>(value);
        }
    }
    public static class SimpleFactory<K> implements KeyedPooledObjectFactory<K,String> {
        volatile int counter;
        final boolean valid;
        int activeCount;
        int validateCounter;
        boolean evenValid = true;
        boolean oddValid = true;
        boolean enableValidation;

        long destroyLatency;
        long makeLatency;
        long validateLatency;
        volatile int maxTotalPerKey = Integer.MAX_VALUE;
        boolean exceptionOnPassivate;
        boolean exceptionOnActivate;
        boolean exceptionOnDestroy;
        boolean exceptionOnValidate;

        boolean exceptionOnCreate;

        public SimpleFactory() {
            this(true);
        }

        public SimpleFactory(final boolean valid) {
            this.valid = valid;
        }

        @Override
        public void activateObject(final K key, final PooledObject<String> obj) throws Exception {
            if (exceptionOnActivate && !(validateCounter++%2 == 0 ? evenValid : oddValid)) {
                throw new Exception();
            }
        }
        @Override
        public void destroyObject(final K key, final PooledObject<String> obj) throws Exception {
            doWait(destroyLatency);
            synchronized(this) {
                activeCount--;
            }
            if (exceptionOnDestroy) {
                throw new Exception();
            }
        }

        private void doWait(final long latency) {
            Waiter.sleepQuietly(latency);
        }

        @Override
        public PooledObject<String> makeObject(final K key) throws Exception {
            if (exceptionOnCreate) {
                throw new Exception();
            }
            doWait(makeLatency);
            String out = null;
            synchronized(this) {
                activeCount++;
                if (activeCount > maxTotalPerKey) {
                    throw new IllegalStateException(
                        "Too many active instances: " + activeCount);
                }
                out = String.valueOf(key) + String.valueOf(counter++);
            }
            return new DefaultPooledObject<>(out);
        }
        @Override
        public void passivateObject(final K key, final PooledObject<String> obj) throws Exception {
            if (exceptionOnPassivate) {
                throw new Exception();
            }
        }
        public void setDestroyLatency(final long destroyLatency) {
            this.destroyLatency = destroyLatency;
        }
        void setEvenValid(final boolean valid) {
            evenValid = valid;
        }
        public void setMakeLatency(final long makeLatency) {
            this.makeLatency = makeLatency;
        }
        public void setMaxTotalPerKey(final int maxTotalPerKey) {
            this.maxTotalPerKey = maxTotalPerKey;
        }
        public void setThrowExceptionOnActivate(final boolean b) {
            exceptionOnActivate = b;
        }
        public void setThrowExceptionOnDestroy(final boolean b) {
            exceptionOnDestroy = b;
        }
        public void setThrowExceptionOnPassivate(final boolean b) {
            exceptionOnPassivate = b;
        }
        public void setThrowExceptionOnValidate(final boolean b) {
            exceptionOnValidate = b;
        }
        void setValid(final boolean valid) {
            evenValid = valid;
            oddValid = valid;
        }
        public void setValidateLatency(final long validateLatency) {
            this.validateLatency = validateLatency;
        }
        public void setValidationEnabled(final boolean b) {
            enableValidation = b;
        }

        @Override
        public boolean validateObject(final K key, final PooledObject<String> obj) {
            doWait(validateLatency);
            if (exceptionOnValidate) {
                throw new RuntimeException("validation failed");
            }
            if (enableValidation) {
                return validateCounter++%2 == 0 ? evenValid : oddValid;
            }
            return valid;
        }
    }
    private static class SimplePerKeyFactory
            extends BaseKeyedPooledObjectFactory<Object,Object> {
        final ConcurrentHashMap<Object,AtomicInteger> map =
                new ConcurrentHashMap<>();
        @Override
        public Object create(final Object key) throws Exception {
            int counter = 0;
            final AtomicInteger Counter = map.get(key);
            if(null != Counter) {
                counter = Counter.incrementAndGet();
            } else {
                map.put(key, new AtomicInteger(0));
                counter = 0;
            }
            return String.valueOf(key) + String.valueOf(counter);
        }
        @Override
        public PooledObject<Object> wrap(final Object value) {
            return new DefaultPooledObject<>(value);
        }
    }
    /*
     * Very simple test thread that just tries to borrow an object from
     * the provided pool with the specified key and returns it
     */
    static class SimpleTestThread<T> implements Runnable {
        private final KeyedObjectPool<String,T> pool;
        private final String key;

        public SimpleTestThread(final KeyedObjectPool<String,T> pool, final String key) {
            this.pool = pool;
            this.key = key;
        }

        @Override
        public void run() {
            try {
                pool.returnObject(key, pool.borrowObject(key));
            } catch (final Exception e) {
                // Ignore
            }
        }
    }
    /*
     * DefaultEvictionPolicy modified to add latency
     */
    private static class SlowEvictionPolicy<T> extends DefaultEvictionPolicy<T> {
        private final long delay;

        /**
         * Constructs SlowEvictionPolicy with the given delay in ms
         *
         * @param delay number of ms of latency to inject in evict
         */
        public SlowEvictionPolicy(final long delay) {
            this.delay = delay;
        }

        @Override
        public boolean evict(final EvictionConfig config, final PooledObject<T> underTest,
                final int idleCount) {
            Waiter.sleepQuietly(delay);
            return super.evict(config, underTest, idleCount);
        }
    }

    static class TestThread<T> implements Runnable {
        private final java.util.Random random = new java.util.Random();

        /** GKOP to hit */
        private final KeyedObjectPool<String,T> pool;
        /** number of borrow/return iterations */
        private final int iter;
        /** delay before borrow */
        private final int startDelay;
        /** delay before return */
        private final int holdTime;
        /** whether or not delays are random (with max = configured values) */
        private final boolean randomDelay;
        /** expected object */
        private final T expectedObject;
        /** key used in borrow / return sequence - null means random */
        private final String key;

        private volatile boolean complete;
        private volatile boolean failed;
        private volatile Exception exception;

        public TestThread(final KeyedObjectPool<String,T> pool) {
            this(pool, 100, 50, 50, true, null, null);
        }

        public TestThread(final KeyedObjectPool<String,T> pool, final int iter) {
            this(pool, iter, 50, 50, true, null, null);
        }

        public TestThread(final KeyedObjectPool<String,T> pool, final int iter, final int delay) {
            this(pool, iter, delay, delay, true, null, null);
        }

        public TestThread(final KeyedObjectPool<String,T> pool, final int iter, final int startDelay,
            final int holdTime, final boolean randomDelay, final T expectedObject, final String key) {
            this.pool = pool;
            this.iter = iter;
            this.startDelay = startDelay;
            this.holdTime = holdTime;
            this.randomDelay = randomDelay;
            this.expectedObject = expectedObject;
            this.key = key;

        }

        public boolean complete() {
            return complete;
        }

        public boolean failed() {
            return failed;
        }

        @Override
        public void run() {
            for(int i=0;i<iter;i++) {
                final String actualKey = key == null ? String.valueOf(random.nextInt(3)) : key;
                Waiter.sleepQuietly(randomDelay ? random.nextInt(startDelay) : startDelay);
                T obj = null;
                try {
                    obj = pool.borrowObject(actualKey);
                } catch(final Exception e) {
                    exception = e;
                    failed = true;
                    complete = true;
                    break;
                }

                if (expectedObject != null && !expectedObject.equals(obj)) {
                    exception = new Exception("Expected: "+expectedObject+ " found: "+obj);
                    failed = true;
                    complete = true;
                    break;
                }

                Waiter.sleepQuietly(randomDelay ? random.nextInt(holdTime) : holdTime);
                try {
                    pool.returnObject(actualKey,obj);
                } catch(final Exception e) {
                    exception = e;
                    failed = true;
                    complete = true;
                    break;
                }
            }
            complete = true;
        }
    }

    /*
     * Very simple test thread that just tries to borrow an object from
     * the provided pool with the specified key and returns it after a wait
     */
    static class WaitingTestThread extends Thread {
        private final KeyedObjectPool<String,String> pool;
        private final String key;
        private final long pause;
        private Throwable thrown;

        private long preBorrowMillis; // just before borrow
        private long postBorrowMillis; //  borrow returned
        private long postReturnMillis; // after object was returned
        private long endedMillis;
        private String objectId;

        public WaitingTestThread(final KeyedObjectPool<String,String> pool, final String key, final long pause) {
            this.pool = pool;
            this.key = key;
            this.pause = pause;
            this.thrown = null;
        }

        @Override
        public void run() {
            try {
                preBorrowMillis = System.currentTimeMillis();
                final String obj = pool.borrowObject(key);
                objectId = obj;
                postBorrowMillis = System.currentTimeMillis();
                Thread.sleep(pause);
                pool.returnObject(key, obj);
                postReturnMillis = System.currentTimeMillis();
            } catch (final Exception e) {
                thrown = e;
            } finally{
                endedMillis = System.currentTimeMillis();
            }
        }
    }

    private static final Integer KEY_ZERO = Integer.valueOf(0);

    private static final Integer KEY_ONE = Integer.valueOf(1);

    private static final Integer KEY_TWO = Integer.valueOf(2);

    private static final boolean DISPLAY_THREAD_DETAILS=
        Boolean.parseBoolean(System.getProperty("TestGenericKeyedObjectPool_OE25Dev.display.thread.details", "false"));
    // To pass this to a Maven test, use:
    // mvn test -DargLine="-DTestGenericKeyedObjectPool.display.thread.details=true"
    // @see https://issues.apache.org/jira/browse/SUREFIRE-121

    /** setUp(): {@code new GenericKeyedObjectPool<String,String>(factory)} */
    private GenericKeyedObjectPool<String,String> gkoPool;

    /** setUp(): {@code new SimpleFactory<String>()} */
    private SimpleFactory<String> simpleFactory;

    private void checkEvictionOrder(final boolean lifo) throws Exception {
        final SimpleFactory<Integer> intFactory = new SimpleFactory<>();
        try (final GenericKeyedObjectPool<Integer, String> intPool = new GenericKeyedObjectPool<>(intFactory)) {
            intPool.setNumTestsPerEvictionRun(2);
            intPool.setMinEvictableIdleTime(Duration.ofMillis(100));
            intPool.setLifo(lifo);

            for (int i = 0; i < 3; i++) {
                final Integer key = Integer.valueOf(i);
                for (int j = 0; j < 5; j++) {
                    intPool.addObject(key);
                }
            }

            // Make all evictable
            Thread.sleep(200);

            /*
             * Initial state (Key, Object) pairs in order of age:
             *
             * (0,0), (0,1), (0,2), (0,3), (0,4) (1,5), (1,6), (1,7), (1,8), (1,9) (2,10), (2,11), (2,12), (2,13),
             * (2,14)
             */

            intPool.evict(); // Kill (0,0),(0,1)
            assertEquals(3, intPool.getNumIdle(KEY_ZERO));
            final String objZeroA = intPool.borrowObject(KEY_ZERO);
            assertTrue(lifo ? objZeroA.equals("04") : objZeroA.equals("02"));
            assertEquals(2, intPool.getNumIdle(KEY_ZERO));
            final String objZeroB = intPool.borrowObject(KEY_ZERO);
            assertEquals("03", objZeroB);
            assertEquals(1, intPool.getNumIdle(KEY_ZERO));

            intPool.evict(); // Kill remaining 0 survivor and (1,5)
            assertEquals(0, intPool.getNumIdle(KEY_ZERO));
            assertEquals(4, intPool.getNumIdle(KEY_ONE));
            final String objOneA = intPool.borrowObject(KEY_ONE);
            assertTrue(lifo ? objOneA.equals("19") : objOneA.equals("16"));
            assertEquals(3, intPool.getNumIdle(KEY_ONE));
            final String objOneB = intPool.borrowObject(KEY_ONE);
            assertTrue(lifo ? objOneB.equals("18") : objOneB.equals("17"));
            assertEquals(2, intPool.getNumIdle(KEY_ONE));

            intPool.evict(); // Kill remaining 1 survivors
            assertEquals(0, intPool.getNumIdle(KEY_ONE));
            intPool.evict(); // Kill (2,10), (2,11)
            assertEquals(3, intPool.getNumIdle(KEY_TWO));
            final String objTwoA = intPool.borrowObject(KEY_TWO);
            assertTrue(lifo ? objTwoA.equals("214") : objTwoA.equals("212"));
            assertEquals(2, intPool.getNumIdle(KEY_TWO));
            intPool.evict(); // All dead now
            assertEquals(0, intPool.getNumIdle(KEY_TWO));

            intPool.evict(); // Should do nothing - make sure no exception
            // Currently 2 zero, 2 one and 1 two active. Return them
            intPool.returnObject(KEY_ZERO, objZeroA);
            intPool.returnObject(KEY_ZERO, objZeroB);
            intPool.returnObject(KEY_ONE, objOneA);
            intPool.returnObject(KEY_ONE, objOneB);
            intPool.returnObject(KEY_TWO, objTwoA);
            // Remove all idle objects
            intPool.clear();

            // Reload
            intPool.setMinEvictableIdleTime(Duration.ofMillis(500));
            intFactory.counter = 0; // Reset counter
            for (int i = 0; i < 3; i++) {
                final Integer key = Integer.valueOf(i);
                for (int j = 0; j < 5; j++) {
                    intPool.addObject(key);
                }
                Thread.sleep(200);
            }

            // 0's are evictable, others not
            intPool.evict(); // Kill (0,0),(0,1)
            assertEquals(3, intPool.getNumIdle(KEY_ZERO));
            intPool.evict(); // Kill (0,2),(0,3)
            assertEquals(1, intPool.getNumIdle(KEY_ZERO));
            intPool.evict(); // Kill (0,4), leave (1,5)
            assertEquals(0, intPool.getNumIdle(KEY_ZERO));
            assertEquals(5, intPool.getNumIdle(KEY_ONE));
            assertEquals(5, intPool.getNumIdle(KEY_TWO));
            intPool.evict(); // (1,6), (1,7)
            assertEquals(5, intPool.getNumIdle(KEY_ONE));
            assertEquals(5, intPool.getNumIdle(KEY_TWO));
            intPool.evict(); // (1,8), (1,9)
            assertEquals(5, intPool.getNumIdle(KEY_ONE));
            assertEquals(5, intPool.getNumIdle(KEY_TWO));
            intPool.evict(); // (2,10), (2,11)
            assertEquals(5, intPool.getNumIdle(KEY_ONE));
            assertEquals(5, intPool.getNumIdle(KEY_TWO));
            intPool.evict(); // (2,12), (2,13)
            assertEquals(5, intPool.getNumIdle(KEY_ONE));
            assertEquals(5, intPool.getNumIdle(KEY_TWO));
            intPool.evict(); // (2,14), (1,5)
            assertEquals(5, intPool.getNumIdle(KEY_ONE));
            assertEquals(5, intPool.getNumIdle(KEY_TWO));
            Thread.sleep(200); // Ones now timed out
            intPool.evict(); // kill (1,6), (1,7) - (1,5) missed
            assertEquals(3, intPool.getNumIdle(KEY_ONE));
            assertEquals(5, intPool.getNumIdle(KEY_TWO));
            final String obj = intPool.borrowObject(KEY_ONE);
            if (lifo) {
                assertEquals("19", obj);
            } else {
                assertEquals("15", obj);
            }
        }
    }

    private void checkEvictorVisiting(final boolean lifo) throws Exception {
        VisitTrackerFactory<Integer> trackerFactory = new VisitTrackerFactory<>();
        try (GenericKeyedObjectPool<Integer, VisitTracker<Integer>> intPool = new GenericKeyedObjectPool<>(
                trackerFactory)) {
            intPool.setNumTestsPerEvictionRun(2);
            intPool.setMinEvictableIdleTime(Duration.ofMillis(-1));
            intPool.setTestWhileIdle(true);
            intPool.setLifo(lifo);
            intPool.setTestOnReturn(false);
            intPool.setTestOnBorrow(false);
            for (int i = 0; i < 3; i++) {
                trackerFactory.resetId();
                final Integer key = Integer.valueOf(i);
                for (int j = 0; j < 8; j++) {
                    intPool.addObject(key);
                }
            }
            intPool.evict(); // Visit oldest 2 - 00 and 01
            VisitTracker<Integer> obj = intPool.borrowObject(KEY_ZERO);
            intPool.returnObject(KEY_ZERO, obj);
            obj = intPool.borrowObject(KEY_ZERO);
            intPool.returnObject(KEY_ZERO, obj);
            // borrow, return, borrow, return
            // FIFO will move 0 and 1 to end - 2,3,4,5,6,7,0,1
            // LIFO, 7 out, then in, then out, then in - 7,6,5,4,3,2,1,0
            intPool.evict(); // Should visit 02 and 03 in either case
            for (int i = 0; i < 8; i++) {
                final VisitTracker<Integer> tracker = intPool.borrowObject(KEY_ZERO);
                if (tracker.getId() >= 4) {
                    assertEquals( 0, tracker.getValidateCount(),"Unexpected instance visited " + tracker.getId());
                } else {
                    assertEquals(1,tracker.getValidateCount(),"Instance " + tracker.getId()+ " visited wrong number of times.");
                }
            }
            // 0's are all out

            intPool.setNumTestsPerEvictionRun(3);

            intPool.evict(); // 10, 11, 12
            intPool.evict(); // 13, 14, 15

            obj = intPool.borrowObject(KEY_ONE);
            intPool.returnObject(KEY_ONE, obj);
            obj = intPool.borrowObject(KEY_ONE);
            intPool.returnObject(KEY_ONE, obj);
            obj = intPool.borrowObject(KEY_ONE);
            intPool.returnObject(KEY_ONE, obj);
            // borrow, return, borrow, return
            // FIFO 3,4,5,^,6,7,0,1,2
            // LIFO 7,6,^,5,4,3,2,1,0
            // In either case, pointer should be at 6
            intPool.evict();
            // LIFO - 16, 17, 20
            // FIFO - 16, 17, 10
            intPool.evict();
            // LIFO - 21, 22, 23
            // FIFO - 11, 12, 20
            intPool.evict();
            // LIFO - 24, 25, 26
            // FIFO - 21, 22, 23
            intPool.evict();
            // LIFO - 27, 10, 11
            // FIFO - 24, 25, 26
            for (int i = 0; i < 8; i++) {
                final VisitTracker<Integer> tracker = intPool.borrowObject(KEY_ONE);
                if ((lifo && tracker.getId() > 1) || (!lifo && tracker.getId() > 2)) {
                    assertEquals(1,tracker.getValidateCount(),"Instance " + tracker.getId()+ " visited wrong number of times.");
                } else {
                    assertEquals(2,tracker.getValidateCount(),"Instance " + tracker.getId()+ " visited wrong number of times.");
                }
            }
        }

        // Randomly generate some pools with random numTests
        // and make sure evictor cycles through elements appropriately
        final int[] smallPrimes = { 2, 3, 5, 7 };
        final Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        for (int i = 0; i < smallPrimes.length; i++) {
            for (int j = 0; j < 5; j++) {// Try the tests a few times
                // Can't use clear as some objects are still active so create
                // a new pool
                trackerFactory = new VisitTrackerFactory<>();
                try (GenericKeyedObjectPool<Integer, VisitTracker<Integer>> intPool = new GenericKeyedObjectPool<>(
                        trackerFactory)) {
                    intPool.setMaxIdlePerKey(-1);
                    intPool.setMaxTotalPerKey(-1);
                    intPool.setNumTestsPerEvictionRun(smallPrimes[i]);
                    intPool.setMinEvictableIdleTime(Duration.ofMillis(-1));
                    intPool.setTestWhileIdle(true);
                    intPool.setLifo(lifo);
                    intPool.setTestOnReturn(false);
                    intPool.setTestOnBorrow(false);

                    final int zeroLength = 10 + random.nextInt(20);
                    for (int k = 0; k < zeroLength; k++) {
                        intPool.addObject(KEY_ZERO);
                    }
                    final int oneLength = 10 + random.nextInt(20);
                    for (int k = 0; k < oneLength; k++) {
                        intPool.addObject(KEY_ONE);
                    }
                    final int twoLength = 10 + random.nextInt(20);
                    for (int k = 0; k < twoLength; k++) {
                        intPool.addObject(KEY_TWO);
                    }

                    // Choose a random number of evictor runs
                    final int runs = 10 + random.nextInt(50);
                    for (int k = 0; k < runs; k++) {
                        intPool.evict();
                    }

                    // Total instances in pool
                    final int totalInstances = zeroLength + oneLength + twoLength;

                    // Number of times evictor should have cycled through pools
                    final int cycleCount = (runs * intPool.getNumTestsPerEvictionRun()) / totalInstances;

                    // Look at elements and make sure they are visited cycleCount
                    // or cycleCount + 1 times
                    VisitTracker<Integer> tracker = null;
                    int visitCount = 0;
                    for (int k = 0; k < zeroLength; k++) {
                        tracker = intPool.borrowObject(KEY_ZERO);
                        visitCount = tracker.getValidateCount();
                        if (visitCount < cycleCount || visitCount > cycleCount + 1) {
                            fail(formatSettings("ZERO","runs",runs,"lifo",lifo,"i",i,"j",j,"k",k,"visitCount",visitCount,"cycleCount",cycleCount,"totalInstances",totalInstances,zeroLength,oneLength,twoLength));
                        }
                    }
                    for (int k = 0; k < oneLength; k++) {
                        tracker = intPool.borrowObject(KEY_ONE);
                        visitCount = tracker.getValidateCount();
                        if (visitCount < cycleCount || visitCount > cycleCount + 1) {
                            fail(formatSettings("ONE","runs",runs,"lifo",lifo,"i",i,"j",j,"k",k,"visitCount",visitCount,"cycleCount",cycleCount,"totalInstances",totalInstances,zeroLength,oneLength,twoLength));
                        }
                    }
                    final int[] visits = new int[twoLength];
                    for (int k = 0; k < twoLength; k++) {
                        tracker = intPool.borrowObject(KEY_TWO);
                        visitCount = tracker.getValidateCount();
                        visits[k] = visitCount;
                        if (visitCount < cycleCount || visitCount > cycleCount + 1) {
                            final StringBuilder sb = new StringBuilder("Visits:");
                            for (int l = 0; l <= k; l++) {
                                sb.append(visits[l]).append(' ');
                            }
                            fail(formatSettings("TWO " + sb.toString(),"runs",runs,"lifo",lifo,"i",i,"j",j,"k",k,"visitCount",visitCount,"cycleCount",cycleCount,"totalInstances",totalInstances,zeroLength,oneLength,twoLength));
                        }
                    }
                }
            }
        }
    }

    private String formatSettings(final String title, final String s, final int i, final String s0, final boolean b0, final String s1, final int i1, final String s2, final int i2, final String s3, final int i3,
            final String s4, final int i4, final String s5, final int i5, final String s6, final int i6, final int zeroLength, final int oneLength, final int twoLength){
        final StringBuilder sb = new StringBuilder(80);
        sb.append(title).append(' ');
        sb.append(s).append('=').append(i).append(' ');
        sb.append(s0).append('=').append(b0).append(' ');
        sb.append(s1).append('=').append(i1).append(' ');
        sb.append(s2).append('=').append(i2).append(' ');
        sb.append(s3).append('=').append(i3).append(' ');
        sb.append(s4).append('=').append(i4).append(' ');
        sb.append(s5).append('=').append(i5).append(' ');
        sb.append(s6).append('=').append(i6).append(' ');
        sb.append("Lengths=").append(zeroLength).append(',').append(oneLength).append(',').append(twoLength).append(' ');
        return sb.toString();
    }

    private String getExceptionTrace(final Throwable t){
        final StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    @Override
    protected Object getNthObject(final Object key, final int n) {
        return String.valueOf(key) + String.valueOf(n);
    }


    @Override
    protected boolean isFifo() {
        return false;
    }

    @Override
    protected boolean isLifo() {
        return true;
    }

    @Override
    protected KeyedObjectPool<Object,Object> makeEmptyPool(final int minCapacity) {
        final KeyedPooledObjectFactory<Object,Object> perKeyFactory =
                new SimplePerKeyFactory();
        final GenericKeyedObjectPool<Object,Object> perKeyPool =
            new GenericKeyedObjectPool<>(perKeyFactory);
        perKeyPool.setMaxTotalPerKey(minCapacity);
        perKeyPool.setMaxIdlePerKey(minCapacity);
        return perKeyPool;
    }

    @Override
    protected KeyedObjectPool<Object,Object> makeEmptyPool(final KeyedPooledObjectFactory<Object,Object> fac) {
        return new GenericKeyedObjectPool<>(fac);
    }

    @Override
    protected Object makeKey(final int n) {
        return String.valueOf(n);
    }

    /**
     * Kicks off {@code numThreads} test threads, each of which will go
     * through {@code iterations} borrow-return cycles with random delay
     * times &lt;= delay in between.
     *
     * @param <T>           Type of object in pool
     * @param numThreads    Number of test threads
     * @param iterations    Number of iterations for each thread
     * @param delay         Maximum delay between iterations
     * @param gkopPool      The keyed object pool to use
     */
    public <T> void runTestThreads(final int numThreads, final int iterations, final int delay, final GenericKeyedObjectPool<String,T> gkopPool) {
        final ArrayList<TestThread<T>> threads = new ArrayList<>();
        for(int i=0;i<numThreads;i++) {
            final TestThread<T> testThread = new TestThread<>(gkopPool, iterations, delay);
            threads.add(testThread);
            final Thread t = new Thread(testThread);
            t.start();
        }
        for (final TestThread<T> testThread : threads) {
            while(!(testThread.complete())) {
                Waiter.sleepQuietly(500L);
            }
            if(testThread.failed()) {
                fail("Thread failed: " + threads.indexOf(testThread)+ "\n" + getExceptionTrace(testThread.exception));
            }
        }
    }

    @BeforeEach
    public void setUp() throws Exception {
        simpleFactory = new SimpleFactory<>();
        gkoPool = new GenericKeyedObjectPool<>(simpleFactory);
    }

    @AfterEach
    public void tearDownJmx() throws Exception {
        super.tearDown();
        final ObjectName jmxName = gkoPool.getJmxName();
        final String poolName = Objects.toString(jmxName, null);
        gkoPool.clear();
        gkoPool.close();
        gkoPool = null;
        simpleFactory = null;

        final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        final Set<ObjectName> result = mbs.queryNames(new ObjectName(
                "org.apache.commoms.pool2:type=GenericKeyedObjectPool,*"),
                null);
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
        assertEquals( 0, registeredPoolCount, msg.toString());
    }

    /*
     * Note: This test relies on timing for correct execution. There *should* be
     * enough margin for this to work correctly on most (all?) systems but be
     * aware of this if you see a failure of this test.
     */
    @SuppressWarnings({
        "rawtypes"
    })

    /**
     * POOL-192
     * Verify that clear(key) does not leak capacity.
     *
     * @throws Exception May occur in some failure modes
     */


    /**
     * Test to make sure that clearOldest does not destroy instances that have been checked out.
     *
     * @throws Exception May occur in some failure modes
     */
    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testClearOldest() throws Exception {
        // Make destroy have some latency so clearOldest takes some time
        final WaiterFactory<String> waiterFactory = new WaiterFactory<>(0, 20, 0, 0, 0, 0, 50, 5, 0);
        try (final GenericKeyedObjectPool<String, Waiter> waiterPool = new GenericKeyedObjectPool<>(waiterFactory)) {
            waiterPool.setMaxTotalPerKey(5);
            waiterPool.setMaxTotal(50);
            waiterPool.setLifo(false);
            // Load the pool with idle instances - 5 each for 10 keys
            for (int i = 0; i < 10; i++) {
                final String key = Integer.toString(i);
                for (int j = 0; j < 5; j++) {
                    waiterPool.addObject(key);
                }
                // Make sure order is maintained
                Thread.sleep(20);
            }
            // Now set up a race - one thread wants a new instance, triggering clearOldest
            // Other goes after an element on death row
            // See if we end up with dead man walking
            final SimpleTestThread<Waiter> t2 = new SimpleTestThread<>(waiterPool, "51");
            final Thread thread2 = new Thread(t2);
            thread2.start(); // Triggers clearOldest, killing all of the 0's and the 2 oldest 1's
            Thread.sleep(50); // Wait for clearOldest to kick off, but not long enough to reach the 1's
            final Waiter waiter = waiterPool.borrowObject("1");
            Thread.sleep(200); // Wait for execution to happen
            waiterPool.returnObject("1", waiter); // Will throw IllegalStateException if dead
        }
    }

    // POOL-259
    @Test
    public void testClientWaitStats() throws Exception {
        final SimpleFactory<String> factory = new SimpleFactory<>();
        // Give makeObject a little latency
        factory.setMakeLatency(200);
        try (final GenericKeyedObjectPool<String, String> pool = new GenericKeyedObjectPool<>(factory,
                new GenericKeyedObjectPoolConfig<>())) {
            final String s = pool.borrowObject("one");
            // First borrow waits on create, so wait time should be at least 200 ms
            // Allow 100ms error in clock times
            assertTrue(pool.getMaxBorrowWaitTimeMillis() >= 100);
            assertTrue(pool.getMeanBorrowWaitTimeMillis() >= 100);
            pool.returnObject("one", s);
            pool.borrowObject("one");
            // Second borrow does not have to wait on create, average should be about 100
            assertTrue(pool.getMaxBorrowWaitTimeMillis() > 100);
            assertTrue(pool.getMeanBorrowWaitTimeMillis() < 200);
            assertTrue(pool.getMeanBorrowWaitTimeMillis() > 20);
        }
    }

    /**
     * POOL-231 - verify that concurrent invalidates of the same object do not
     * corrupt pool destroyCount.
     *
     * @throws Exception May occur in some failure modes
     */

    @SuppressWarnings("deprecation")
    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testConstructors() {

        // Make constructor arguments all different from defaults
        final int maxTotalPerKey = 1;
        final int minIdle = 2;
        final Duration maxWaitDuration = Duration.ofMillis(3);
        final long maxWaitMillis = maxWaitDuration.toMillis();
        final int maxIdle = 4;
        final int maxTotal = 5;
        final long minEvictableIdleTimeMillis = 6;
        final int numTestsPerEvictionRun = 7;
        final boolean testOnBorrow = true;
        final boolean testOnReturn = true;
        final boolean testWhileIdle = true;
        final long timeBetweenEvictionRunsMillis = 8;
        final boolean blockWhenExhausted = false;
        final boolean lifo = false;
        final KeyedPooledObjectFactory<Object, Object> dummyFactory = new DummyFactory();

        try (GenericKeyedObjectPool<Object, Object> objPool = new GenericKeyedObjectPool<>(dummyFactory)) {
            assertEquals(GenericKeyedObjectPoolConfig.DEFAULT_MAX_TOTAL_PER_KEY, objPool.getMaxTotalPerKey());
            assertEquals(GenericKeyedObjectPoolConfig.DEFAULT_MAX_IDLE_PER_KEY, objPool.getMaxIdlePerKey());
            assertEquals(BaseObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS, objPool.getMaxWaitMillis());
            assertEquals(GenericKeyedObjectPoolConfig.DEFAULT_MIN_IDLE_PER_KEY, objPool.getMinIdlePerKey());
            assertEquals(GenericKeyedObjectPoolConfig.DEFAULT_MAX_TOTAL, objPool.getMaxTotal());
            //
            assertEquals(BaseObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS,objPool.getMinEvictableIdleTimeMillis());
            assertEquals(BaseObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME,objPool.getMinEvictableIdleTime());
            assertEquals(BaseObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME,objPool.getMinEvictableIdleDuration());
            //
            assertEquals(BaseObjectPoolConfig.DEFAULT_NUM_TESTS_PER_EVICTION_RUN, objPool.getNumTestsPerEvictionRun());
            assertEquals(Boolean.valueOf(BaseObjectPoolConfig.DEFAULT_TEST_ON_BORROW),Boolean.valueOf(objPool.getTestOnBorrow()));
            assertEquals(Boolean.valueOf(BaseObjectPoolConfig.DEFAULT_TEST_ON_RETURN),Boolean.valueOf(objPool.getTestOnReturn()));
            assertEquals(Boolean.valueOf(BaseObjectPoolConfig.DEFAULT_TEST_WHILE_IDLE),Boolean.valueOf(objPool.getTestWhileIdle()));
            //
            assertEquals(BaseObjectPoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS,objPool.getDurationBetweenEvictionRuns());
            assertEquals(BaseObjectPoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS,objPool.getTimeBetweenEvictionRunsMillis());
            assertEquals(BaseObjectPoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS,objPool.getTimeBetweenEvictionRuns());
            //
            assertEquals(Boolean.valueOf(BaseObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED),Boolean.valueOf(objPool.getBlockWhenExhausted()));
            assertEquals(Boolean.valueOf(BaseObjectPoolConfig.DEFAULT_LIFO), Boolean.valueOf(objPool.getLifo()));
        }

        final GenericKeyedObjectPoolConfig<Object> config = new GenericKeyedObjectPoolConfig<>();
        config.setLifo(lifo);
        config.setMaxTotalPerKey(maxTotalPerKey);
        config.setMaxIdlePerKey(maxIdle);
        config.setMinIdlePerKey(minIdle);
        config.setMaxTotal(maxTotal);
        config.setMaxWait(maxWaitDuration);
        config.setMinEvictableIdleTime(Duration.ofMillis(minEvictableIdleTimeMillis));
        config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setTestWhileIdle(testWhileIdle);
        config.setTimeBetweenEvictionRuns(Duration.ofMillis(timeBetweenEvictionRunsMillis));
        config.setBlockWhenExhausted(blockWhenExhausted);
        try (GenericKeyedObjectPool<Object, Object> objPool = new GenericKeyedObjectPool<>(dummyFactory, config)) {
            assertEquals(maxTotalPerKey, objPool.getMaxTotalPerKey());
            assertEquals(maxIdle, objPool.getMaxIdlePerKey());
            assertEquals(maxWaitDuration, objPool.getMaxWaitDuration());
            assertEquals(maxWaitMillis, objPool.getMaxWaitMillis());
            assertEquals(minIdle, objPool.getMinIdlePerKey());
            assertEquals(maxTotal, objPool.getMaxTotal());
            assertEquals(minEvictableIdleTimeMillis, objPool.getMinEvictableIdleDuration().toMillis());
            assertEquals(minEvictableIdleTimeMillis, objPool.getMinEvictableIdleTimeMillis());
            assertEquals(minEvictableIdleTimeMillis, objPool.getMinEvictableIdleTime().toMillis());
            assertEquals(numTestsPerEvictionRun, objPool.getNumTestsPerEvictionRun());
            assertEquals(Boolean.valueOf(testOnBorrow), Boolean.valueOf(objPool.getTestOnBorrow()));
            assertEquals(Boolean.valueOf(testOnReturn), Boolean.valueOf(objPool.getTestOnReturn()));
            assertEquals(Boolean.valueOf(testWhileIdle), Boolean.valueOf(objPool.getTestWhileIdle()));
            assertEquals(timeBetweenEvictionRunsMillis, objPool.getDurationBetweenEvictionRuns().toMillis());
            assertEquals(timeBetweenEvictionRunsMillis, objPool.getTimeBetweenEvictionRunsMillis());
            assertEquals(timeBetweenEvictionRunsMillis, objPool.getTimeBetweenEvictionRuns().toMillis());
            assertEquals(Boolean.valueOf(blockWhenExhausted), Boolean.valueOf(objPool.getBlockWhenExhausted()));
            assertEquals(Boolean.valueOf(lifo), Boolean.valueOf(objPool.getLifo()));
        }
    }

    /**
     * JIRA: POOL-270 - make sure constructor correctly sets run
     * frequency of evictor timer.
     */
    @Test
    public void testContructorEvictionConfig() throws Exception {
        final GenericKeyedObjectPoolConfig<String> config = new GenericKeyedObjectPoolConfig<>();
        config.setTimeBetweenEvictionRuns(Duration.ofMillis(500));
        config.setMinEvictableIdleTime(Duration.ofMillis(50));
        config.setNumTestsPerEvictionRun(5);
        try (final GenericKeyedObjectPool<String, String> p = new GenericKeyedObjectPool<>(simpleFactory, config)) {
            for (int i = 0; i < 5; i++) {
                p.addObject("one");
            }
            Waiter.sleepQuietly(100);
            assertEquals(5, p.getNumIdle("one"));
            Waiter.sleepQuietly(500);
            assertEquals(0, p.getNumIdle("one"));
        }
    }

    /**
     * Verifies that when a factory's makeObject produces instances that are not
     * discernible by equals, the pool can handle them.
     *
     * JIRA: POOL-283
     */
    @Test
    public void testEqualsIndiscernible() throws Exception {
        final HashSetFactory factory = new HashSetFactory();
        try (final GenericKeyedObjectPool<String, HashSet<String>> pool = new GenericKeyedObjectPool<>(factory,
                new GenericKeyedObjectPoolConfig<>())) {
            final HashSet<String> s1 = pool.borrowObject("a");
            final HashSet<String> s2 = pool.borrowObject("a");
            pool.returnObject("a", s1);
            pool.returnObject("a", s2);
        }
    }

    /**
     * Test to make sure evictor visits least recently used objects first,
     * regardless of FIFO/LIFO
     *
     * JIRA: POOL-86
     *
     * @throws Exception May occur in some failure modes
     */
    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testEvictionOrder() throws Exception {
        checkEvictionOrder(false);
        checkEvictionOrder(true);
    }

    // POOL-326
    @Test
    public void testEvictorClearOldestRace() throws Exception {
        gkoPool.setMinEvictableIdleTime(Duration.ofMillis(100));
        gkoPool.setNumTestsPerEvictionRun(1);

        // Introduce latency between when evictor starts looking at an instance and when
        // it decides to destroy it
        gkoPool.setEvictionPolicy(new SlowEvictionPolicy<>(1000));

        // Borrow an instance
        final String val = gkoPool.borrowObject("foo");

        // Add another idle one
        gkoPool.addObject("foo");

        // Sleep long enough so idle one is eligible for eviction
        Thread.sleep(1000);

        // Start evictor and race with clearOldest
        gkoPool.setTimeBetweenEvictionRuns(Duration.ofMillis(10));

        // Wait for evictor to start
        Thread.sleep(100);
        gkoPool.clearOldest();

        // Wait for slow evictor to complete
        Thread.sleep(1500);

        // See if we get NPE on return (POOL-326)
        gkoPool.returnObject("foo", val);
    }

    /**
     * Verifies that the evictor visits objects in expected order
     * and frequency.
     *
     * @throws Exception May occur in some failure modes
     */
    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testEvictorVisiting() throws Exception {
        checkEvictorVisiting(true);
        checkEvictorVisiting(false);
    }

    /**
     * Verify that threads waiting on a depleted pool get served when a checked out object is
     * invalidated.
     *
     * JIRA: POOL-240
     *
     * @throws Exception May occur in some failure modes
     */
    @Test
    public void testInvalidateFreesCapacity() throws Exception {
        final SimpleFactory<String> factory = new SimpleFactory<>();
        try (final GenericKeyedObjectPool<String, String> pool = new GenericKeyedObjectPool<>(factory)) {
            pool.setMaxTotalPerKey(2);
            pool.setMaxWaitMillis(500);
            // Borrow an instance and hold if for 5 seconds
            final WaitingTestThread thread1 = new WaitingTestThread(pool, "one", 5000);
            thread1.start();
            // Borrow another instance
            final String obj = pool.borrowObject("one");
            // Launch another thread - will block, but fail in 500 ms
            final WaitingTestThread thread2 = new WaitingTestThread(pool, "one", 100);
            thread2.start();
            // Invalidate the object borrowed by this thread - should allow thread2 to create
            Thread.sleep(20);
            pool.invalidateObject("one", obj);
            Thread.sleep(600); // Wait for thread2 to timeout
            if (thread2.thrown != null) {
                fail(thread2.thrown.toString());
            }
        }
    }

    /**
     * Verify that threads blocked waiting on a depleted pool get served when a checked out instance
     * is invalidated.
     *
     * JIRA: POOL-240
     *
     * @throws Exception May occur in some failure modes
     */
    @Test
    public void testInvalidateWaiting()
            throws Exception {

        final GenericKeyedObjectPoolConfig<Object> config = new GenericKeyedObjectPoolConfig<>();
        config.setMaxTotal(2);
        config.setBlockWhenExhausted(true);
        config.setMinIdlePerKey(0);
        config.setMaxWait(Duration.ofMillis(-1));
        config.setNumTestsPerEvictionRun(Integer.MAX_VALUE); // always test all idle objects
        config.setTestOnBorrow(true);
        config.setTestOnReturn(false);
        config.setTestWhileIdle(true);
        config.setTimeBetweenEvictionRuns(Duration.ofMillis(-1));

        try (final GenericKeyedObjectPool<Integer, Object> pool = new GenericKeyedObjectPool<>(new ObjectFactory(),
                config)) {

            // Allocate both objects with this thread
            pool.borrowObject(Integer.valueOf(1)); // object1
            final Object object2 = pool.borrowObject(Integer.valueOf(1));

            // Cause a thread to block waiting for an object
            final ExecutorService executorService = Executors.newSingleThreadExecutor(new DaemonThreadFactory());
            final Semaphore signal = new Semaphore(0);
            final Future<Exception> result = executorService.submit(() -> {
                try {
                    signal.release();
                    final Object object3 = pool.borrowObject(Integer.valueOf(1));
                    pool.returnObject(Integer.valueOf(1), object3);
                    signal.release();
                } catch (final Exception e1) {
                    return e1;
                } catch (final Throwable e2) {
                    return new Exception(e2);
                }

                return null;
            });

            // Wait for the thread to start
            assertTrue(signal.tryAcquire(5, TimeUnit.SECONDS));

            // Attempt to ensure that test thread is blocked waiting for an object
            Thread.sleep(500);

            pool.invalidateObject(Integer.valueOf(1), object2);

            assertTrue(signal.tryAcquire(2, TimeUnit.SECONDS),"Call to invalidateObject did not unblock pool waiters.");

            if (result.get() != null) {
                throw new AssertionError(result.get());
            }
        }
    }

    /**
     * Ensure the pool is registered.
     */

    /**
     * Verifies that threads that get parked waiting for keys not in use
     * when the pool is at maxTotal eventually get served.
     *
     * @throws Exception May occur in some failure modes
     */
    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testLivenessPerKey() throws Exception {
        gkoPool.setMaxIdlePerKey(3);
        gkoPool.setMaxTotal(3);
        gkoPool.setMaxTotalPerKey(3);
        gkoPool.setMaxWaitMillis(3000);  // Really a timeout for the test

        // Check out and briefly hold 3 "1"s
        final WaitingTestThread t1 = new WaitingTestThread(gkoPool, "1", 100);
        final WaitingTestThread t2 = new WaitingTestThread(gkoPool, "1", 100);
        final WaitingTestThread t3 = new WaitingTestThread(gkoPool, "1", 100);
        t1.start();
        t2.start();
        t3.start();

        // Try to get a "2" while all capacity is in use.
        // Thread will park waiting on empty queue. Verify it gets served.
        gkoPool.borrowObject("2");
    }

    /**
     * Verify that factory exceptions creating objects do not corrupt per key create count.
     *
     * JIRA: POOL-243
     *
     * @throws Exception May occur in some failure modes
     */
    @Test
    public void testMakeObjectException() throws Exception {
        final SimpleFactory<String> factory = new SimpleFactory<>();
        try (final GenericKeyedObjectPool<String, String> pool = new GenericKeyedObjectPool<>(factory)) {
            pool.setMaxTotalPerKey(1);
            pool.setBlockWhenExhausted(false);
            factory.exceptionOnCreate = true;
            assertThrows(Exception.class, () -> pool.borrowObject("One"));
            factory.exceptionOnCreate = false;
            pool.borrowObject("One");
        }
    }

    /**
     * Test case for POOL-180.
     */
    @Test
    @Timeout(value = 200000, unit = TimeUnit.MILLISECONDS)
    public void testMaxActivePerKeyExceeded() {
        final WaiterFactory<String> waiterFactory = new WaiterFactory<>(0, 20, 0, 0, 0, 0, 8, 5, 0);
        // TODO Fix this. Can't use local pool since runTestThreads uses the
        // protected pool field
        try (final GenericKeyedObjectPool<String, Waiter> waiterPool = new GenericKeyedObjectPool<>(waiterFactory)) {
            waiterPool.setMaxTotalPerKey(5);
            waiterPool.setMaxTotal(8);
            waiterPool.setTestOnBorrow(true);
            waiterPool.setMaxIdlePerKey(5);
            waiterPool.setMaxWaitMillis(-1);
            runTestThreads(20, 300, 250, waiterPool);
        }
    }

    /**
     * Verifies that maxTotal is not exceeded when factory destroyObject
     * has high latency, testOnReturn is set and there is high incidence of
     * validation failures.
     */
    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testMaxTotalInvariant() {
        final int maxTotal = 15;
        simpleFactory.setEvenValid(false);     // Every other validation fails
        simpleFactory.setDestroyLatency(100);  // Destroy takes 100 ms
        simpleFactory.setMaxTotalPerKey(maxTotal);  // (makes - destroys) bound
        simpleFactory.setValidationEnabled(true);
        gkoPool.setMaxTotal(maxTotal);
        gkoPool.setMaxIdlePerKey(-1);
        gkoPool.setTestOnReturn(true);
        gkoPool.setMaxWaitMillis(10000L);
        runTestThreads(5, 10, 50, gkoPool);
    }

    /**
     * Verifies that if a borrow of a new key is blocked because maxTotal has
     * been reached, that borrow continues once another object is returned.
     *
     * JIRA: POOL-310
     */


    /*
     * Test multi-threaded pool access.
     * Multiple keys, multiple threads, but maxActive only allows half the threads to succeed.
     *
     * This test was prompted by Continuum build failures in the Commons DBCP test case:
     * TestSharedPoolDataSource.testMultipleThreads2()
     * Let's see if the this fails on Continuum too!
     */

    /**
     * Verifies that returning an object twice (without borrow in between) causes ISE
     * but does not re-validate or re-passivate the instance.
     *
     * JIRA: POOL-285
     */
    @Test
    public void testMultipleReturn() throws Exception {
        final WaiterFactory<String> factory = new WaiterFactory<>(0, 0, 0, 0, 0, 0);
        try (final GenericKeyedObjectPool<String, Waiter> pool = new GenericKeyedObjectPool<>(factory)) {
            pool.setTestOnReturn(true);
            final Waiter waiter = pool.borrowObject("a");
            pool.returnObject("a", waiter);
            assertEquals(1, waiter.getValidationCount());
            assertEquals(1, waiter.getPassivationCount());
            try {
                pool.returnObject("a", waiter);
                fail("Expecting IllegalStateException from multiple return");
            } catch (final IllegalStateException ex) {
                // Exception is expected, now check no repeat validation/passivation
                assertEquals(1, waiter.getValidationCount());
                assertEquals(1, waiter.getPassivationCount());
            }
        }
    }

    /**
     * Verifies that when a borrowed object is mutated in a way that does not
     * preserve equality and hashcode, the pool can recognized it on return.
     *
     * JIRA: POOL-284
     */
    @Test
    public void testMutable() throws Exception {
        final HashSetFactory factory = new HashSetFactory();
        try (final GenericKeyedObjectPool<String, HashSet<String>> pool = new GenericKeyedObjectPool<>(factory,
                new GenericKeyedObjectPoolConfig<>())) {
            final HashSet<String> s1 = pool.borrowObject("a");
            final HashSet<String> s2 = pool.borrowObject("a");
            s1.add("One");
            s2.add("One");
            pool.returnObject("a", s1);
            pool.returnObject("a", s2);
        }
    }

    @Test
    public void testReturnObjectThrowsIllegalStateException() {
        try (final GenericKeyedObjectPool<String, String> pool = new GenericKeyedObjectPool<>(new SimpleFactory<>())) {
            assertThrows(IllegalStateException.class,
                    () ->  pool.returnObject("Foo", "Bar"));
        }
    }

    /**
     * JIRA: POOL-287
     *
     * Verify that when an attempt is made to borrow an instance from the pool
     * while the evictor is visiting it, there is no capacity leak.
     *
     * Test creates the scenario described in POOL-287.
     */
    @Test
    public void testReturnToHead() throws Exception {
        final SimpleFactory<String> factory = new SimpleFactory<>();
        factory.setValidateLatency(100);
        factory.setValid(true); // Validation always succeeds
        try (final GenericKeyedObjectPool<String, String> pool = new GenericKeyedObjectPool<>(factory)) {
            pool.setMaxWaitMillis(1000);
            pool.setTestWhileIdle(true);
            pool.setMaxTotalPerKey(2);
            pool.setNumTestsPerEvictionRun(1);
            pool.setTimeBetweenEvictionRuns(Duration.ofMillis(500));

            // Load pool with two objects
            pool.addObject("one"); // call this o1
            pool.addObject("one"); // call this o2
            // Default is LIFO, so "one" pool is now [o2, o1] in offer order.
            // Evictor will visit in oldest-to-youngest order, so o1 then o2

            Thread.sleep(800); // Wait for first eviction run to complete

            // At this point, one eviction run should have completed, visiting o1
            // and eviction cursor should be pointed at o2, which is the next offered instance
            Thread.sleep(250); // Wait for evictor to start
            final String o1 = pool.borrowObject("one"); // o2 is under eviction, so this will return o1
            final String o2 = pool.borrowObject("one"); // Once validation completes, o2 should be offered
            pool.returnObject("one", o1);
            pool.returnObject("one", o2);
        }
    }

    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testThreaded1() {
        gkoPool.setMaxTotalPerKey(15);
        gkoPool.setMaxIdlePerKey(15);
        gkoPool.setMaxWaitMillis(1000L);
        runTestThreads(20, 100, 50, gkoPool);
    }


    // Pool-361

    /**
     * Verify that threads waiting on a depleted pool get served when a returning object fails
     * validation.
     *
     * JIRA: POOL-240
     *
     * @throws Exception May occur in some failure modes
     */
    @Test
    public void testValidationFailureOnReturnFreesCapacity()
            throws Exception {
        final SimpleFactory<String> factory = new SimpleFactory<>();
        factory.setValid(false); // Validate will always fail
        factory.setValidationEnabled(true);
        try (final GenericKeyedObjectPool<String, String> pool = new GenericKeyedObjectPool<>(factory)) {
            pool.setMaxTotalPerKey(2);
            pool.setMaxWaitMillis(1500);
            pool.setTestOnReturn(true);
            pool.setTestOnBorrow(false);
            // Borrow an instance and hold if for 5 seconds
            final WaitingTestThread thread1 = new WaitingTestThread(pool, "one", 5000);
            thread1.start();
            // Borrow another instance and return it after 500 ms (validation will fail)
            final WaitingTestThread thread2 = new WaitingTestThread(pool, "one", 500);
            thread2.start();
            Thread.sleep(50);
            // Try to borrow an object
            final String obj = pool.borrowObject("one");
            pool.returnObject("one", obj);
        }
    }

    // POOL-276

    /**
     * POOL-189
     *
     * @throws Exception May occur in some failure modes
     */

    @Test
    public void testConstructorNullFactory_1_oe() throws Exception {
        // add dummy assert (won't be invoked because of IAE) to avoid "unused" warning
        try {
    new GenericKeyedObjectPool<>(null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testExceptionInValidationDuringEviction_1_oe() throws Exception {
        gkoPool.setMaxIdlePerKey(1);
        gkoPool.setMinEvictableIdleTime(Duration.ZERO);
        gkoPool.setTestWhileIdle(true);

        final String obj = gkoPool.borrowObject("one");
        gkoPool.returnObject("one", obj);

        simpleFactory.setThrowExceptionOnValidate(true);
        assertThrows(RuntimeException.class, gkoPool::evict);
    }

    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testExceptionOnActivateDuringBorrow_5_oe() throws Exception {
        final String obj1 = gkoPool.borrowObject("one");
        final String obj2 = gkoPool.borrowObject("one");
        gkoPool.returnObject("one", obj1);
        gkoPool.returnObject("one", obj2);
        simpleFactory.setThrowExceptionOnActivate(true);
        simpleFactory.setEvenValid(false);
        // Activation will now throw every other time
        // First attempt throws, but loop continues and second succeeds
        final String obj = gkoPool.borrowObject("one");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        gkoPool.returnObject("one", obj);
        simpleFactory.setValid(false);
        // Validation will now fail on activation when borrowObject returns
        // an idle instance, and then when attempting to create a new instance
        try {
    gkoPool.borrowObject("one");
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testExceptionOnDestroyDuringBorrow_1_oe() throws Exception {
        simpleFactory.setThrowExceptionOnDestroy(true);
        simpleFactory.setValidationEnabled(true);
        gkoPool.setTestOnBorrow(true);
        gkoPool.borrowObject("one");
        simpleFactory.setValid(false); // Make validation fail on next borrow attempt
        try {
    gkoPool.borrowObject("one");
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testMaxTotal_4_oe() throws Exception {
        gkoPool.setMaxTotalPerKey(2);
        gkoPool.setMaxTotal(3);
        gkoPool.setBlockWhenExhausted(false);

        final String o1 = gkoPool.borrowObject("a");
        // removed other assertion
        final String o2 = gkoPool.borrowObject("a");
        // removed other assertion
        final String o3 = gkoPool.borrowObject("b");
        // removed other assertion
        try {
    gkoPool.borrowObject("c");
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testMaxTotalPerKey_1_oe() throws Exception {
        gkoPool.setMaxTotalPerKey(3);
        gkoPool.setBlockWhenExhausted(false);

        gkoPool.borrowObject("");
        gkoPool.borrowObject("");
        gkoPool.borrowObject("");
        try {
    gkoPool.borrowObject("");
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testMaxTotalPerKeyZero_1_oe() throws Exception {
        gkoPool.setMaxTotalPerKey(0);
        gkoPool.setBlockWhenExhausted(false);

        try {
    gkoPool.borrowObject("a");
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

    @Test
    @Timeout(value = 60000, unit = TimeUnit.MILLISECONDS)
    public void testMaxTotalZero_1_oe() throws Exception {
        gkoPool.setMaxTotal(0);
        gkoPool.setBlockWhenExhausted(false);

        try {
    gkoPool.borrowObject("a");
    org.junit.jupiter.api.Assertions.fail("NoSuchElementException");
} catch (NoSuchElementException e) {
}
    }

}


