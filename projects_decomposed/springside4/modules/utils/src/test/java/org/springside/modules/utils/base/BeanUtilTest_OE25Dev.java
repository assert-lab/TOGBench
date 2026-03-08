package org.springside.modules.utils.base;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class BeanUtilTest_OE25Dev {

	@Test
	public void test_1_oe() {
		assertThat(BooleanUtil.toBoolean("True")).isTrue();
	}

	@Test
	public void test_2_oe() {
		// removed other assertion
		assertThat(BooleanUtil.toBoolean("tre")).isFalse();
	}

	@Test
	public void test_3_oe() {
		// removed other assertion
		// removed other assertion
		assertThat(BooleanUtil.toBoolean(null)).isFalse();
	}

	@Test
	public void test_4_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(BooleanUtil.toBooleanObject("True")).isTrue();
	}

	@Test
	public void test_5_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(BooleanUtil.toBooleanObject("tre")).isFalse();
	}

	@Test
	public void test_6_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(BooleanUtil.toBooleanObject(null)).isNull();
	}

	@Test
	public void test_7_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(BooleanUtil.parseGeneralString("1", false)).isFalse();
	}

	@Test
	public void test_8_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(BooleanUtil.parseGeneralString("y", false)).isTrue();
	}

	@Test
	public void test_9_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(BooleanUtil.parseGeneralString("y")).isTrue();
	}

	@Test
	public void test_10_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(BooleanUtil.parseGeneralString("x")).isNull();
	}

	@Test
	public void logic_1_oe() {
		assertThat(BooleanUtil.negate(Boolean.TRUE)).isFalse();
	}

	@Test
	public void logic_2_oe() {
		// removed other assertion
		assertThat(BooleanUtil.negate(Boolean.FALSE)).isTrue();
	}

	@Test
	public void logic_3_oe() {
		// removed other assertion
		// removed other assertion

		assertThat(BooleanUtil.negate(true)).isFalse();
	}

	@Test
	public void logic_4_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(BooleanUtil.negate(false)).isTrue();
	}

	@Test
	public void logic_5_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		assertThat(BooleanUtil.or(true, true, false)).isTrue();
	}

	@Test
	public void logic_6_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(BooleanUtil.or(false, false, false)).isFalse();
	}

	@Test
	public void logic_7_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		assertThat(BooleanUtil.and(true, true, false)).isFalse();
	}

	@Test
	public void logic_8_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(BooleanUtil.and(true, true, true)).isTrue();
	}

}
