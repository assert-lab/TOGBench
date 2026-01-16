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
    public void testBooleanStates_1_oe() {
        final StopWatch watch = new StopWatch();
        assertFalse(watch.isStarted());
    }

    @Test
    public void testBooleanStates_2_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        assertFalse(watch.isSuspended());
    }

    @Test
    public void testBooleanStates_3_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion
        assertTrue(watch.isStopped());
    }

    @Test
    public void testBooleanStates_4_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.start();
        assertTrue(watch.isStarted());
    }

    @Test
    public void testBooleanStates_5_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.start();
        // removed other assertion
        assertFalse(watch.isSuspended());
    }

    @Test
    public void testBooleanStates_6_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.start();
        // removed other assertion
        // removed other assertion
        assertFalse(watch.isStopped());
    }

    @Test
    public void testBooleanStates_7_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.suspend();
        assertTrue(watch.isStarted());
    }

    @Test
    public void testBooleanStates_8_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.suspend();
        // removed other assertion
        assertTrue(watch.isSuspended());
    }

    @Test
    public void testBooleanStates_9_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.suspend();
        // removed other assertion
        // removed other assertion
        assertFalse(watch.isStopped());
    }

    @Test
    public void testBooleanStates_10_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.suspend();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.stop();
        assertFalse(watch.isStarted());
    }

    @Test
    public void testBooleanStates_11_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.suspend();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.stop();
        // removed other assertion
        assertFalse(watch.isSuspended());
    }

    @Test
    public void testBooleanStates_12_oe() {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.start();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.suspend();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        watch.stop();
        // removed other assertion
        // removed other assertion
        assertTrue(watch.isStopped());
    }

    @Test
    public void testFormatSplitTime_1_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        Thread.sleep(MIN_SLEEP_MILLISECONDS);
        watch.split();
        final String formatSplitTime = watch.formatSplitTime();
        assertNotEquals(ZERO_TIME_ELAPSED, formatSplitTime);
    }

    @Test
    public void testFormatSplitTime_2_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        Thread.sleep(MIN_SLEEP_MILLISECONDS);
        watch.split();
        final String formatSplitTime = watch.formatSplitTime();
        // removed other assertion
        assertTrue(formatSplitTime.startsWith(ZERO_HOURS_PREFIX));
    }

    @Test
    public void testFormatSplitTimeWithMessage_1_oe() throws InterruptedException {
        final StopWatch watch = new StopWatch(MESSAGE);
        watch.start();
        Thread.sleep(MIN_SLEEP_MILLISECONDS);
        watch.split();
        final String formatSplitTime = watch.formatSplitTime();
        assertFalse(formatSplitTime.startsWith(MESSAGE), formatSplitTime);
    }

    @Test
    public void testFormatSplitTimeWithMessage_2_oe() throws InterruptedException {
        final StopWatch watch = new StopWatch(MESSAGE);
        watch.start();
        Thread.sleep(MIN_SLEEP_MILLISECONDS);
        watch.split();
        final String formatSplitTime = watch.formatSplitTime();
        // removed other assertion
        assertTrue(formatSplitTime.startsWith(ZERO_HOURS_PREFIX));
    }

    @Test
    public void testFormatTime_1_oe() {
        final StopWatch watch = StopWatch.create();
        final String formatTime = watch.formatTime();
        assertEquals(ZERO_TIME_ELAPSED, formatTime);
    }

    @Test
    public void testFormatTime_2_oe() {
        final StopWatch watch = StopWatch.create();
        final String formatTime = watch.formatTime();
        // removed other assertion
        assertTrue(formatTime.startsWith(ZERO_HOURS_PREFIX));
    }

    @Test
    public void testFormatTimeWithMessage_1_oe() {
        final StopWatch watch = new StopWatch(MESSAGE);
        final String formatTime = watch.formatTime();
        assertFalse(formatTime.startsWith(MESSAGE), formatTime);
    }

    @Test
    public void testGetStartTime_1_oe() {
        final long beforeStopWatchMillis = System.currentTimeMillis();
        final StopWatch watch = new StopWatch();
        assertThrows(IllegalStateException.class, watch::getStartTime, "Calling getStartTime on an unstarted StopWatch should throw an exception");
    }

    @Test
    public void testGetStartTime_2_oe() {
        final long beforeStopWatchMillis = System.currentTimeMillis();
        final StopWatch watch = new StopWatch();
        // removed other assertion
        watch.start();

        watch.getStartTime();
        assertTrue(watch.getStartTime() >= beforeStopWatchMillis);
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

    @Test
    public void testLang315_1_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_200);
        watch.suspend();
        final long suspendTime = watch.getTime();
        sleepQuietly(MILLIS_200);
        watch.stop();
        final long totalTime = watch.getTime();
        assertEquals(suspendTime, totalTime);
    }

    @Test
    public void testMessage_1_oe() {
        assertNull(StopWatch.create().getMessage());
    }

    @Test
    public void testMessage_2_oe() {
        // removed other assertion
        final StopWatch stopWatch = new StopWatch(MESSAGE);
        assertEquals(MESSAGE, stopWatch.getMessage());
    }

    @Test
    public void testMessage_3_oe() {
        // removed other assertion
        final StopWatch stopWatch = new StopWatch(MESSAGE);
        // removed other assertion
        assertTrue(stopWatch.toString().startsWith(MESSAGE));
    }

    @Test
    public void testMessage_4_oe() {
        // removed other assertion
        final StopWatch stopWatch = new StopWatch(MESSAGE);
        // removed other assertion
        // removed other assertion
        stopWatch.start();
        stopWatch.split();
        assertTrue(stopWatch.toSplitString().startsWith(MESSAGE));
    }

    @Test
    public void testStopTimeSimple_1_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        final long testStartMillis = System.currentTimeMillis();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long testEndMillis = System.currentTimeMillis();
        final long stopTime = watch.getStopTime();
        assertEquals(stopTime, watch.getStopTime());
    }

    @Test
    public void testStopTimeSimple_2_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        final long testStartMillis = System.currentTimeMillis();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long testEndMillis = System.currentTimeMillis();
        final long stopTime = watch.getStopTime();
        // removed other assertion

        assertTrue(stopTime >= testStartMillis);
    }

    @Test
    public void testStopTimeSimple_3_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        final long testStartMillis = System.currentTimeMillis();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long testEndMillis = System.currentTimeMillis();
        final long stopTime = watch.getStopTime();
        // removed other assertion

        // removed other assertion
        assertTrue(stopTime <= testEndMillis);
    }

    @Test
    public void testStopWatchGetWithTimeUnit_1_oe() {
        // Create a mock StopWatch with a time of 2:59:01.999
        // @formatter:off
        final StopWatch watch = createMockStopWatch(
            TimeUnit.HOURS.toNanos(2)
                    + TimeUnit.MINUTES.toNanos(59)
                    + TimeUnit.SECONDS.toNanos(1)
                    + TimeUnit.MILLISECONDS.toNanos(999));
        // @formatter:on

        assertEquals(2L, watch.getTime(TimeUnit.HOURS));
    }

    @Test
    public void testStopWatchGetWithTimeUnit_2_oe() {
        // Create a mock StopWatch with a time of 2:59:01.999
        // @formatter:off
        final StopWatch watch = createMockStopWatch(
            TimeUnit.HOURS.toNanos(2)
                    + TimeUnit.MINUTES.toNanos(59)
                    + TimeUnit.SECONDS.toNanos(1)
                    + TimeUnit.MILLISECONDS.toNanos(999));
        // @formatter:on

        // removed other assertion
        assertEquals(179L, watch.getTime(TimeUnit.MINUTES));
    }

    @Test
    public void testStopWatchGetWithTimeUnit_3_oe() {
        // Create a mock StopWatch with a time of 2:59:01.999
        // @formatter:off
        final StopWatch watch = createMockStopWatch(
            TimeUnit.HOURS.toNanos(2)
                    + TimeUnit.MINUTES.toNanos(59)
                    + TimeUnit.SECONDS.toNanos(1)
                    + TimeUnit.MILLISECONDS.toNanos(999));
        // @formatter:on

        // removed other assertion
        // removed other assertion
        assertEquals(10741L, watch.getTime(TimeUnit.SECONDS));
    }

    @Test
    public void testStopWatchGetWithTimeUnit_4_oe() {
        // Create a mock StopWatch with a time of 2:59:01.999
        // @formatter:off
        final StopWatch watch = createMockStopWatch(
            TimeUnit.HOURS.toNanos(2)
                    + TimeUnit.MINUTES.toNanos(59)
                    + TimeUnit.SECONDS.toNanos(1)
                    + TimeUnit.MILLISECONDS.toNanos(999));
        // @formatter:on

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10741999L, watch.getTime(TimeUnit.MILLISECONDS));
    }

    @Test
    public void testStopWatchSimple_1_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long time = watch.getTime();
        assertEquals(time, watch.getTime());
    }

    @Test
    public void testStopWatchSimple_2_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long time = watch.getTime();
        // removed other assertion

        assertTrue(time >= 500);
    }

    @Test
    public void testStopWatchSimple_3_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long time = watch.getTime();
        // removed other assertion

        // removed other assertion
        assertTrue(time < 700);
    }

    @Test
    public void testStopWatchSimple_4_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long time = watch.getTime();
        // removed other assertion

        // removed other assertion
        // removed other assertion

        watch.reset();
        assertEquals(0, watch.getTime());
    }

    @Test
    public void testStopWatchSimpleGet_1_oe() throws InterruptedException {
        final StopWatch watch = new StopWatch();
        assertEquals(0, watch.getTime());
    }

    @Test
    public void testStopWatchSimpleGet_2_oe() throws InterruptedException {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        assertEquals(ZERO_TIME_ELAPSED, watch.toString());
    }

    @Test
    public void testStopWatchSimpleGet_3_oe() throws InterruptedException {
        final StopWatch watch = new StopWatch();
        // removed other assertion
        // removed other assertion

        watch.start();
        sleepQuietly(MILLIS_550);
        assertTrue(watch.getTime() < 2000);
    }

    @Test
    public void testStopWatchSplit_1_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.split();
        final long splitTime = watch.getSplitTime();
        final String splitStr = watch.toSplitString();
        sleepQuietly(MILLIS_550);
        watch.unsplit();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long totalTime = watch.getTime();

        assertEquals(splitStr.length(), 12, "Formatted split string not the correct length");
    }

    @Test
    public void testStopWatchSplit_2_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.split();
        final long splitTime = watch.getSplitTime();
        final String splitStr = watch.toSplitString();
        sleepQuietly(MILLIS_550);
        watch.unsplit();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long totalTime = watch.getTime();

        // removed other assertion
        assertTrue(splitTime >= 500);
    }

    @Test
    public void testStopWatchSplit_3_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.split();
        final long splitTime = watch.getSplitTime();
        final String splitStr = watch.toSplitString();
        sleepQuietly(MILLIS_550);
        watch.unsplit();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long totalTime = watch.getTime();

        // removed other assertion
        // removed other assertion
        assertTrue(splitTime < 700);
    }

    @Test
    public void testStopWatchSplit_4_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.split();
        final long splitTime = watch.getSplitTime();
        final String splitStr = watch.toSplitString();
        sleepQuietly(MILLIS_550);
        watch.unsplit();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long totalTime = watch.getTime();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(totalTime >= 1500);
    }

    @Test
    public void testStopWatchSplit_5_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.split();
        final long splitTime = watch.getSplitTime();
        final String splitStr = watch.toSplitString();
        sleepQuietly(MILLIS_550);
        watch.unsplit();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long totalTime = watch.getTime();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(totalTime < 1900);
    }

    @Test
    public void testStopWatchStatic_1_oe() {
        final StopWatch watch = StopWatch.createStarted();
        assertTrue(watch.isStarted());
    }

    @Test
    public void testStopWatchSuspend_1_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        final long testStartMillis = System.currentTimeMillis();
        sleepQuietly(MILLIS_550);
        watch.suspend();
        final long testSuspendMillis = System.currentTimeMillis();
        final long suspendTime = watch.getTime();
        final long stopTime = watch.getStopTime();

        assertTrue(testStartMillis <= stopTime);
    }

    @Test
    public void testStopWatchSuspend_2_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        final long testStartMillis = System.currentTimeMillis();
        sleepQuietly(MILLIS_550);
        watch.suspend();
        final long testSuspendMillis = System.currentTimeMillis();
        final long suspendTime = watch.getTime();
        final long stopTime = watch.getStopTime();

        // removed other assertion
        assertTrue(testSuspendMillis <= stopTime);
    }

    @Test
    public void testStopWatchSuspend_3_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        final long testStartMillis = System.currentTimeMillis();
        sleepQuietly(MILLIS_550);
        watch.suspend();
        final long testSuspendMillis = System.currentTimeMillis();
        final long suspendTime = watch.getTime();
        final long stopTime = watch.getStopTime();

        // removed other assertion
        // removed other assertion

        sleepQuietly(MILLIS_550);
        watch.resume();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long totalTime = watch.getTime();

        assertTrue(suspendTime >= 500);
    }

    @Test
    public void testStopWatchSuspend_4_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        final long testStartMillis = System.currentTimeMillis();
        sleepQuietly(MILLIS_550);
        watch.suspend();
        final long testSuspendMillis = System.currentTimeMillis();
        final long suspendTime = watch.getTime();
        final long stopTime = watch.getStopTime();

        // removed other assertion
        // removed other assertion

        sleepQuietly(MILLIS_550);
        watch.resume();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long totalTime = watch.getTime();

        // removed other assertion
        assertTrue(suspendTime < 700);
    }

    @Test
    public void testStopWatchSuspend_5_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        final long testStartMillis = System.currentTimeMillis();
        sleepQuietly(MILLIS_550);
        watch.suspend();
        final long testSuspendMillis = System.currentTimeMillis();
        final long suspendTime = watch.getTime();
        final long stopTime = watch.getStopTime();

        // removed other assertion
        // removed other assertion

        sleepQuietly(MILLIS_550);
        watch.resume();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long totalTime = watch.getTime();

        // removed other assertion
        // removed other assertion
        assertTrue(totalTime >= 1000);
    }

    @Test
    public void testStopWatchSuspend_6_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        final long testStartMillis = System.currentTimeMillis();
        sleepQuietly(MILLIS_550);
        watch.suspend();
        final long testSuspendMillis = System.currentTimeMillis();
        final long suspendTime = watch.getTime();
        final long stopTime = watch.getStopTime();

        // removed other assertion
        // removed other assertion

        sleepQuietly(MILLIS_550);
        watch.resume();
        sleepQuietly(MILLIS_550);
        watch.stop();
        final long totalTime = watch.getTime();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(totalTime < 1300);
    }

    @Test
    public void testToSplitString_1_oe() throws InterruptedException {
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.split();
        final String splitStr = watch.toSplitString();
        assertEquals(splitStr.length(), 12, "Formatted split string not the correct length");
    }

    @Test
    public void testToSplitStringWithMessage_1_oe() throws InterruptedException {
        final StopWatch watch = new StopWatch(MESSAGE);
        watch.start();
        sleepQuietly(MILLIS_550);
        watch.split();
        final String splitStr = watch.toSplitString();
        assertEquals(splitStr.length(), 12 + MESSAGE.length() + 1, "Formatted split string not the correct length");
    }

    @Test
    public void testToString_1_oe() throws InterruptedException {
        //
        final StopWatch watch = StopWatch.createStarted();
        sleepQuietly(MILLIS_550);
        watch.split();
        final String splitStr = watch.toString();
        assertEquals(splitStr.length(), 12, "Formatted split string not the correct length");
    }

    @Test
    public void testToStringWithMessage_1_oe() throws InterruptedException {
        assertTrue(new StopWatch(MESSAGE).toString().startsWith(MESSAGE));
    }

    @Test
    public void testToStringWithMessage_2_oe() throws InterruptedException {
        // removed other assertion
        //
        final StopWatch watch = new StopWatch(MESSAGE);
        watch.start();
        sleepQuietly(MILLIS_550);
        watch.split();
        final String splitStr = watch.toString();
        assertEquals(splitStr.length(), 12 + MESSAGE.length() + 1, "Formatted split string not the correct length");
    }

}
