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
package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ThreadUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;

/**
 * TestCase for StopWatch.
 */
public class StopWatchTest_OE25Dev {

    private static final Duration MILLIS_200 = Duration.ofMillis(200);
    private static final Duration MILLIS_550 = Duration.ofMillis(550);
    private static final String MESSAGE = "Baking cookies";
    private static final int MIN_SLEEP_MILLISECONDS = 20;
    private static final String ZERO_HOURS_PREFIX = "00:";
    private static final String ZERO_TIME_ELAPSED = "00:00:00.000";

    /**
     * <p>
     * Creates a suspended StopWatch object which appears to have elapsed for the requested amount of time in
     * nanoseconds.
     * <p>
     * <p>
     *
     * <pre>
     * // Create a mock StopWatch with a time of 2:59:01.999
     * final long nanos = TimeUnit.HOURS.toNanos(2)
     *         + TimeUnit.MINUTES.toNanos(59)
     *         + TimeUnit.SECONDS.toNanos(1)
     *         + TimeUnit.MILLISECONDS.toNanos(999);
     * final StopWatch watch = createMockStopWatch(nanos);
     * </pre>
     *
     * @param nanos Time in nanoseconds to have elapsed on the stop watch
     * @return StopWatch in a suspended state with the elapsed time
     */
    private StopWatch createMockStopWatch(final long nanos) {
        final StopWatch watch = StopWatch.createStarted();
        watch.suspend();
        try {
            final long currentNanos = System.nanoTime();
            FieldUtils.writeField(watch, "startTimeNanos", currentNanos - nanos, true);
            FieldUtils.writeField(watch, "stopTimeNanos", currentNanos, true);
        } catch (final IllegalAccessException e) {
            return null;
        }
        return watch;
    }

    private void sleepQuietly(final Duration duration) throws InterruptedException {
        ThreadUtils.sleep(duration);
    }

    // test bad states

    @Test
    public void testBadStates_1_oe() {
        final StopWatch watch = new StopWatch();
        assertThrows(IllegalStateException.class, watch::stop, "Calling stop on an unstarted StopWatch should throw an exception. ");
    }

    @Test
    public void testBadStates_2_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion

        assertThrows(IllegalStateException.class, watch::suspend, "Calling suspend on an unstarted StopWatch should throw an exception. ");
    }

    @Test
    public void testBadStates_3_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion

        // removed other assertion

        assertThrows(IllegalStateException.class, watch::split, "Calling split on a non-running StopWatch should throw an exception. ");
    }

    @Test
    public void testBadStates_4_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows(IllegalStateException.class, watch::unsplit, "Calling unsplit on an unsplit StopWatch should throw an exception. ");
    }

    @Test
    public void testBadStates_5_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows(IllegalStateException.class, watch::resume, "Calling resume on an unsuspended StopWatch should throw an exception. ");
    }

    @Test
    public void testBadStates_6_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        watch.start();

        assertThrows(IllegalStateException.class, watch::start, "Calling start on a started StopWatch should throw an exception. ");
    }

    @Test
    public void testBadStates_7_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        watch.start();

        // removed other assertion

        assertThrows(IllegalStateException.class, watch::unsplit, "Calling unsplit on an unsplit StopWatch should throw an exception. ");
    }

    @Test
    public void testBadStates_8_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        watch.start();

        // removed other assertion

        // removed other assertion

        assertThrows(IllegalStateException.class, watch::getSplitTime, "Calling getSplitTime on an unsplit StopWatch should throw an exception. ");
    }

    @Test
    public void testBadStates_9_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        watch.start();

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows(IllegalStateException.class, watch::resume, "Calling resume on an unsuspended StopWatch should throw an exception. ");
    }

    @Test
    public void testBadStates_10_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        watch.start();

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        watch.stop();

        assertThrows(IllegalStateException.class, watch::start, "Calling start on a stopped StopWatch should throw an exception as it needs to be reset. ");
    }

    @Test
    public void testGetStartTime_1_oe() {
        final long beforeStopWatchMillis = System.currentTimeMillis();
        final StopWatch watch = new StopWatch();
        assertThrows(IllegalStateException.class, watch::getStartTime, "Calling getStartTime on an unstarted StopWatch should throw an exception");
    }

    @Test
    public void testGetStartTime_3_oe() {
        final long beforeStopWatchMillis = System.currentTimeMillis();
        final StopWatch watch = new StopWatch();
        // removed other assertion
        watch.start();

        watch.getStartTime();
        // removed other assertion

        watch.reset();
        assertThrows(IllegalStateException.class, watch::getStartTime, "Calling getStartTime on a reset, but unstarted StopWatch should throw an exception");
    }

}
