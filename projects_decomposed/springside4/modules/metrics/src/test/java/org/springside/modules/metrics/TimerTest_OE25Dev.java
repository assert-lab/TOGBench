/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.modules.metrics;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springside.modules.metrics.metric.Counter;
import org.springside.modules.metrics.metric.Timer;
import org.springside.modules.metrics.metric.TimerMetric;
import org.springside.modules.metrics.metric.Timer.TimerContext;
import org.springside.modules.metrics.utils.Clock.MockClock;

public class TimerTest_OE25Dev {

	@Test
	public void normal() {
		MockClock clock = new MockClock();
		Timer.clock = clock;
		Counter.clock = clock;
		Timer timer = new Timer(new Double[] { 90d });

		TimerContext timerContext = timer.start();
		clock.increaseTime(200);
		timerContext.stop();

		TimerContext timer2 = timer.start();
		clock.increaseTime(300);
		timer2.stop();

		TimerMetric metric = timer.calculateMetric();

		assertThat(metric.counterMetric.totalCount).isEqualTo(2);
		assertThat(metric.counterMetric.avgRate).isEqualTo(4);
		assertThat(metric.counterMetric.latestCount).isEqualTo(2);
		assertThat(metric.counterMetric.latestRate).isEqualTo(4);

		assertThat(metric.histogramMetric.min).isEqualTo(200);
		assertThat(metric.histogramMetric.avg).isEqualTo(250);
		assertThat(metric.histogramMetric.pcts.get(90d)).isEqualTo(300);
	}

	@Test
	public void normal_1_oe() {
		MockClock clock = new MockClock();
		Timer.clock = clock;
		Counter.clock = clock;
		Timer timer = new Timer(new Double[] { 90d });

		TimerContext timerContext = timer.start();
		clock.increaseTime(200);
		timerContext.stop();

		TimerContext timer2 = timer.start();
		clock.increaseTime(300);
		timer2.stop();

		TimerMetric metric = timer.calculateMetric();

		assertThat(metric.counterMetric.totalCount).isEqualTo(2);
	}

	@Test
	public void normal_2_oe() {
		MockClock clock = new MockClock();
		Timer.clock = clock;
		Counter.clock = clock;
		Timer timer = new Timer(new Double[] { 90d });

		TimerContext timerContext = timer.start();
		clock.increaseTime(200);
		timerContext.stop();

		TimerContext timer2 = timer.start();
		clock.increaseTime(300);
		timer2.stop();

		TimerMetric metric = timer.calculateMetric();

		// removed other assertion
		assertThat(metric.counterMetric.avgRate).isEqualTo(4);
	}

	@Test
	public void normal_3_oe() {
		MockClock clock = new MockClock();
		Timer.clock = clock;
		Counter.clock = clock;
		Timer timer = new Timer(new Double[] { 90d });

		TimerContext timerContext = timer.start();
		clock.increaseTime(200);
		timerContext.stop();

		TimerContext timer2 = timer.start();
		clock.increaseTime(300);
		timer2.stop();

		TimerMetric metric = timer.calculateMetric();

		// removed other assertion
		// removed other assertion
		assertThat(metric.counterMetric.latestCount).isEqualTo(2);
	}

	@Test
	public void normal_4_oe() {
		MockClock clock = new MockClock();
		Timer.clock = clock;
		Counter.clock = clock;
		Timer timer = new Timer(new Double[] { 90d });

		TimerContext timerContext = timer.start();
		clock.increaseTime(200);
		timerContext.stop();

		TimerContext timer2 = timer.start();
		clock.increaseTime(300);
		timer2.stop();

		TimerMetric metric = timer.calculateMetric();

		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(metric.counterMetric.latestRate).isEqualTo(4);
	}

	@Test
	public void normal_5_oe() {
		MockClock clock = new MockClock();
		Timer.clock = clock;
		Counter.clock = clock;
		Timer timer = new Timer(new Double[] { 90d });

		TimerContext timerContext = timer.start();
		clock.increaseTime(200);
		timerContext.stop();

		TimerContext timer2 = timer.start();
		clock.increaseTime(300);
		timer2.stop();

		TimerMetric metric = timer.calculateMetric();

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(metric.histogramMetric.min).isEqualTo(200);
	}

	@Test
	public void normal_6_oe() {
		MockClock clock = new MockClock();
		Timer.clock = clock;
		Counter.clock = clock;
		Timer timer = new Timer(new Double[] { 90d });

		TimerContext timerContext = timer.start();
		clock.increaseTime(200);
		timerContext.stop();

		TimerContext timer2 = timer.start();
		clock.increaseTime(300);
		timer2.stop();

		TimerMetric metric = timer.calculateMetric();

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(metric.histogramMetric.avg).isEqualTo(250);
	}

	@Test
	public void normal_7_oe() {
		MockClock clock = new MockClock();
		Timer.clock = clock;
		Counter.clock = clock;
		Timer timer = new Timer(new Double[] { 90d });

		TimerContext timerContext = timer.start();
		clock.increaseTime(200);
		timerContext.stop();

		TimerContext timer2 = timer.start();
		clock.increaseTime(300);
		timer2.stop();

		TimerMetric metric = timer.calculateMetric();

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(metric.histogramMetric.pcts.get(90d)).isEqualTo(300);
	}

}
