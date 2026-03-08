package org.springside.modules.utils.base;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springside.modules.utils.collection.ListUtil;

public class ObjectUtilTest_OE25Dev {

	@Test
	public void hashCodeTest_1_oe() {
		assertThat(ObjectUtil.hashCode("a", "b") - ObjectUtil.hashCode("a", "a")).isEqualTo(1);
	}

	@Test
	public void toPrettyString_1_oe() {
		assertThat(ObjectUtil.toPrettyString(null)).isEqualTo("null");
	}

	@Test
	public void toPrettyString_2_oe() {
		// removed other assertion
		assertThat(ObjectUtil.toPrettyString(1)).isEqualTo("1");
	}

	@Test
	public void toPrettyString_3_oe() {
		// removed other assertion
		// removed other assertion

		assertThat(ObjectUtil.toPrettyString(new int[] { 1, 2 })).isEqualTo("[1, 2]");
	}

	@Test
	public void toPrettyString_4_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(ObjectUtil.toPrettyString(new long[] { 1, 2 })).isEqualTo("[1, 2]");
	}

	@Test
	public void toPrettyString_5_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(ObjectUtil.toPrettyString(new double[] { 1.1d, 2.2d })).isEqualTo("[1.1, 2.2]");
	}

	@Test
	public void toPrettyString_6_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(ObjectUtil.toPrettyString(new float[] { 1.1f, 2.2f })).isEqualTo("[1.1, 2.2]");
	}

	@Test
	public void toPrettyString_7_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(ObjectUtil.toPrettyString(new boolean[] { true, false })).isEqualTo("[true, false]");
	}

	@Test
	public void toPrettyString_8_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(ObjectUtil.toPrettyString(new short[] { 1, 2 })).isEqualTo("[1, 2]");
	}

	@Test
	public void toPrettyString_9_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(ObjectUtil.toPrettyString(new byte[] { 1, 2 })).isEqualTo("[1, 2]");
	}

	@Test
	public void toPrettyString_10_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		assertThat(ObjectUtil.toPrettyString(new Integer[] { 1, 2 })).isEqualTo("[1, 2]");
	}

	@Test
	public void toPrettyString_11_oe() {
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		assertThat(ObjectUtil.toPrettyString(ListUtil.newArrayList("1", "2"))).isEqualTo("{1,2}");
	}

}
