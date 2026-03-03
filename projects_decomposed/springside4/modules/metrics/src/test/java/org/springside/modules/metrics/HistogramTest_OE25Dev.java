/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.modules.metrics;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springside.modules.metrics.metric.Histogram;
import org.springside.modules.metrics.metric.HistogramMetric;

public class HistogramTest_OE25Dev {

	@Test
	public void normal_1_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		for (int i = 1; i <= 100; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();

		assertThat(metric.min).isEqualTo(1);
	}

	@Test
	public void normal_2_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		for (int i = 1; i <= 100; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();

		assertThat(metric.max).isEqualTo(100);
	}

	@Test
	public void normal_3_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		for (int i = 1; i <= 100; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();

		assertThat(metric.avg).isEqualTo(50.5);
	}

	@Test
	public void normal_4_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		for (int i = 1; i <= 100; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();

		assertThat(metric.pcts.get(90d)).isEqualTo(90);
	}

	@Test
	public void normal_5_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		for (int i = 1; i <= 100; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();

		assertThat(metric.pcts.get(95d)).isEqualTo(95);
	}

	@Test
	public void normal_6_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		for (int i = 1; i <= 100; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();


		for (int i = 1; i <= 100; i++) {
			histogram.update(i * 2);
		}

		metric = histogram.calculateMetric();

		assertThat(metric.min).isEqualTo(2);
	}

	@Test
	public void normal_7_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		for (int i = 1; i <= 100; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();


		for (int i = 1; i <= 100; i++) {
			histogram.update(i * 2);
		}

		metric = histogram.calculateMetric();

		assertThat(metric.max).isEqualTo(200);
	}

	@Test
	public void normal_8_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		for (int i = 1; i <= 100; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();


		for (int i = 1; i <= 100; i++) {
			histogram.update(i * 2);
		}

		metric = histogram.calculateMetric();

		assertThat(metric.avg).isEqualTo(101);
	}

	@Test
	public void normal_9_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		for (int i = 1; i <= 100; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();


		for (int i = 1; i <= 100; i++) {
			histogram.update(i * 2);
		}

		metric = histogram.calculateMetric();

		assertThat(metric.pcts.get(90d)).isEqualTo(180);
	}

	@Test
	public void normal_10_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		for (int i = 1; i <= 100; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();


		for (int i = 1; i <= 100; i++) {
			histogram.update(i * 2);
		}

		metric = histogram.calculateMetric();

		assertThat(metric.pcts.get(95d)).isEqualTo(190);
	}

	@Test
	public void fewData_1_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		histogram.update(1);
		HistogramMetric metric = histogram.calculateMetric();
		assertThat(metric.pcts.get(90d)).isEqualTo(1);
	}

	@Test
	public void fewData_2_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		histogram.update(1);
		HistogramMetric metric = histogram.calculateMetric();
		assertThat(metric.pcts.get(95d)).isEqualTo(1);
	}

	@Test
	public void fewData_3_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		histogram.update(1);
		HistogramMetric metric = histogram.calculateMetric();

		for (int i = 1; i <= 3; i++) {
			histogram.update(i);
		}
		metric = histogram.calculateMetric();

		assertThat(metric.min).isEqualTo(1);
	}

	@Test
	public void fewData_4_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		histogram.update(1);
		HistogramMetric metric = histogram.calculateMetric();

		for (int i = 1; i <= 3; i++) {
			histogram.update(i);
		}
		metric = histogram.calculateMetric();

		assertThat(metric.max).isEqualTo(3);
	}

	@Test
	public void fewData_5_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		histogram.update(1);
		HistogramMetric metric = histogram.calculateMetric();

		for (int i = 1; i <= 3; i++) {
			histogram.update(i);
		}
		metric = histogram.calculateMetric();

		assertThat(metric.avg).isEqualTo(2);
	}

	@Test
	public void fewData_6_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		histogram.update(1);
		HistogramMetric metric = histogram.calculateMetric();

		for (int i = 1; i <= 3; i++) {
			histogram.update(i);
		}
		metric = histogram.calculateMetric();

		assertThat(metric.pcts.get(90d)).isEqualTo(3);
	}

	@Test
	public void fewData_7_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		histogram.update(1);
		HistogramMetric metric = histogram.calculateMetric();

		for (int i = 1; i <= 3; i++) {
			histogram.update(i);
		}
		metric = histogram.calculateMetric();

		assertThat(metric.pcts.get(95d)).isEqualTo(3);
	}

	@Test
	public void emptyMesures_1_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		HistogramMetric metric = histogram.calculateMetric();

		assertThat(metric.min).isZero();
	}

	@Test
	public void emptyMesures_2_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		HistogramMetric metric = histogram.calculateMetric();

		assertThat(metric.max).isZero();
	}

	@Test
	public void emptyMesures_3_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		HistogramMetric metric = histogram.calculateMetric();

		assertThat(metric.avg).isZero();
	}

	@Test
	public void emptyMesures_4_oe() {
		Histogram histogram = new Histogram(90d, 95d);

		HistogramMetric metric = histogram.calculateMetric();

		assertThat(metric.pcts.get(90d)).isZero();
	}

	@Test()
	public void emptyPcts_1_oe() {
		Histogram histogram = new Histogram();
		for (int i = 1; i <= 3; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();
		assertThat(metric.max).isEqualTo(3);
	}

	@Test()
	public void emptyPcts_2_oe() {
		Histogram histogram = new Histogram();
		for (int i = 1; i <= 3; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();
		assertThat(metric.pcts).isEmpty();
	}

	@Test()
	public void emptyPcts_3_oe() {
		Histogram histogram = new Histogram();
		for (int i = 1; i <= 3; i++) {
			histogram.update(i);
		}

		HistogramMetric metric = histogram.calculateMetric();
		assertThat(metric.pcts.get(90d)).isNull();
	}

}
