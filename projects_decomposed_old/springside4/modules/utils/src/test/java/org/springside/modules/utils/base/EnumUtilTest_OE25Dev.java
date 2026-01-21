package org.springside.modules.utils.base;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springside.modules.utils.collection.ListUtil;

public class EnumUtilTest_OE25Dev {

	public enum Options {
		A, B, C, D;
	}

	@Test
	public void test_1_oe() {
		assertThat(EnumUtil.generateBits(Options.class, Options.A)).isEqualTo(1);
	}

	@Test
	public void test_2_oe() {
		// removed other assertion
		assertThat(EnumUtil.generateBits(Options.class, Options.A, Options.B)).isEqualTo(3);
	}

	@Test
	public void test_3_oe() {
		// removed other assertion
		// removed other assertion

		assertThat(EnumUtil.generateBits(Options.class, ListUtil.newArrayList(Options.A))).isEqualTo(1);
	}

	@Test
	public void test_4_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(EnumUtil.generateBits(Options.class, ListUtil.newArrayList(Options.A, Options.B))).isEqualTo(3);
	}

	@Test
	public void test_5_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		assertThat(EnumUtil.processBits(Options.class, 3)).hasSize(2).containsExactly(Options.A, Options.B);
	}

	@Test
	public void test_6_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(EnumUtil.processBits(Options.class, EnumUtil.generateBits(Options.class, Options.A, Options.C, Options.D))).hasSize(3) .containsExactly(Options.A, Options.C, Options.D);
	}

}
