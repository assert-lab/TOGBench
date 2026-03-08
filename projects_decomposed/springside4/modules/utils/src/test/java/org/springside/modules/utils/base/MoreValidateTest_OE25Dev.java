package org.springside.modules.utils.base;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class MoreValidateTest_OE25Dev {

	@Test
	public void test_1_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		assertThat(a).isEqualTo(0);
	}

	@Test
	public void test_2_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		assertThat(a).isEqualTo(1);
	}

	@Test
	public void test_3_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		assertThat(a).isEqualTo(1);
	}

	@Test
	public void test_4_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		assertThat(c).isEqualTo(0);
	}

	@Test
	public void test_5_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		assertThat(c).isEqualTo(21);
	}

	@Test
	public void test_6_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		assertThat(c).isEqualTo(1);
	}

	@Test
	public void test_7_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		assertThat(b).isEqualTo(0);
	}

	@Test
	public void test_8_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		assertThat(b).isEqualTo(11);
	}

	@Test
	public void test_9_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		assertThat(b).isEqualTo(1);
	}

	@Test
	public void test_10_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		assertThat(e).isEqualTo(0);
	}

	@Test
	public void test_11_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		assertThat(e).isEqualTo(11);
	}

	@Test
	public void test_12_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		assertThat(e).isEqualTo(1.1);
	}

	@Test
	public void test_13_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		assertThat(d).isEqualTo(0);
	}

	@Test
	public void test_14_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		assertThat(d).isEqualTo(1);
	}

	@Test
	public void test_15_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		assertThat(d).isEqualTo(11);
	}

	@Test
	public void test_17_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_19_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_21_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_23_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_25_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_27_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0l);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_29_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Long
		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_31_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Long
		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_33_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Long
		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_35_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Long
		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Integer
		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_37_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Long
		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Integer
		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_39_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Long
		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Integer
		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_41_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Long
		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Integer
		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// double
		try {
			MoreValidate.nonNegative("x", -9999.2d);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_43_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Long
		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Integer
		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// double
		try {
			MoreValidate.nonNegative("x", -9999.2d);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1.2d);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_45_oe() {
		// int
		int a = MoreValidate.nonNegative("x", 0);
		// removed other assertion
		a = MoreValidate.nonNegative("x", 1);
		// removed other assertion

		a = MoreValidate.positive("x", 1);
		// removed other assertion

		// Integer
		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		// removed other assertion
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		// removed other assertion

		c = MoreValidate.positive("x", Integer.valueOf(1));
		// removed other assertion

		// long
		long b = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		b = MoreValidate.nonNegative("x", 11l);
		// removed other assertion

		b = MoreValidate.positive("x", 1l);
		// removed other assertion

		double e = MoreValidate.nonNegative("x", 0l);
		// removed other assertion

		e = MoreValidate.nonNegative("x", 11d);
		// removed other assertion

		e = MoreValidate.positive("x", 1.1d);
		// removed other assertion

		// Long
		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		// removed other assertion

		d = MoreValidate.positive("x", Long.valueOf(1));
		// removed other assertion

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		// removed other assertion

		// int
		try {
			MoreValidate.nonNegative("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", -1);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", 0);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// long
		try {
			MoreValidate.nonNegative("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0l);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Long
		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// Integer
		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(-1));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(0));
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// double
		try {
			MoreValidate.nonNegative("x", -9999.2d);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		try {
			MoreValidate.positive("x", -1.2d);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}
		try {
			MoreValidate.positive("x", 0d);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

}
