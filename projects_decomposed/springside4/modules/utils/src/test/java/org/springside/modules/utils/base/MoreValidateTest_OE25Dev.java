package org.springside.modules.utils.base;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class MoreValidateTest_OE25Dev {

	@Test
	public void test_1_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		assertThat(a).isEqualTo(0);
	}

	@Test
	public void test_2_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);
		assertThat(a).isEqualTo(1);
	}

	@Test
	public void test_3_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);
		assertThat(a).isEqualTo(1);
	}

	@Test
	public void test_4_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		assertThat(c).isEqualTo(0);
	}

	@Test
	public void test_5_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));
		assertThat(c).isEqualTo(21);
	}

	@Test
	public void test_6_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));
		assertThat(c).isEqualTo(1);
	}

	@Test
	public void test_7_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);
		assertThat(b).isEqualTo(0);
	}

	@Test
	public void test_8_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);
		assertThat(b).isEqualTo(11);
	}

	@Test
	public void test_9_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);
		assertThat(b).isEqualTo(1);
	}

	@Test
	public void test_10_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);
		assertThat(e).isEqualTo(0);
	}

	@Test
	public void test_11_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);
		assertThat(e).isEqualTo(11);
	}

	@Test
	public void test_12_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);
		assertThat(e).isEqualTo(1.1);
	}

	@Test
	public void test_13_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));
		assertThat(d).isEqualTo(0);
	}

	@Test
	public void test_14_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));
		assertThat(d).isEqualTo(1);
	}

	@Test
	public void test_15_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));
		assertThat(d).isEqualTo(11);
	}

	@Test
	public void test_17_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_19_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_21_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_23_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_25_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_27_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0l);
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_29_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_31_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_33_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_35_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_37_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(-1));
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_39_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(0));
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_41_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(0));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -9999.2d);
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_43_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(0));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -9999.2d);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1.2d);
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void test_45_oe() {
		int a = MoreValidate.nonNegative("x", 0);
		a = MoreValidate.nonNegative("x", 1);

		a = MoreValidate.positive("x", 1);

		Integer c = MoreValidate.nonNegative("x", Integer.valueOf(0));
		c = MoreValidate.nonNegative("x", Integer.valueOf(21));

		c = MoreValidate.positive("x", Integer.valueOf(1));

		long b = MoreValidate.nonNegative("x", 0l);

		b = MoreValidate.nonNegative("x", 11l);

		b = MoreValidate.positive("x", 1l);

		double e = MoreValidate.nonNegative("x", 0l);

		e = MoreValidate.nonNegative("x", 11d);

		e = MoreValidate.positive("x", 1.1d);

		Long d = MoreValidate.nonNegative("x", Long.valueOf(0));

		d = MoreValidate.positive("x", Long.valueOf(1));

		d = MoreValidate.nonNegative("x", Long.valueOf(11));

		try {
			MoreValidate.nonNegative("x", -1);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", -1);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", 0);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -1l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1l);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0l);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", Long.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Long.valueOf(0));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", Integer.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(-1));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", Integer.valueOf(0));
		} catch (Throwable t) {
		}

		try {
			MoreValidate.nonNegative("x", -9999.2d);
		} catch (Throwable t) {
		}

		try {
			MoreValidate.positive("x", -1.2d);
		} catch (Throwable t) {
		}
		try {
			MoreValidate.positive("x", 0d);
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

}
