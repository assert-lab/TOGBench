package org.springside.modules.utils.text;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;

public class MoreStringUtilTest_OE25Dev {

	@Test
	public void split_1_oe() {

		List<String> result = MoreStringUtil.split("192.168.0.1", '.', 4);
		assertThat(result).hasSize(4).containsSequence("192", "168", "0", "1");
	}

	@Test
	public void split_2_oe() {

		List<String> result = MoreStringUtil.split("192.168.0.1", '.', 4);

		result = MoreStringUtil.split("192.168..1", '.', 4);
		assertThat(result).hasSize(3).containsSequence("192", "168", "1");
	}

	@Test
	public void split_3_oe() {

		List<String> result = MoreStringUtil.split("192.168.0.1", '.', 4);

		result = MoreStringUtil.split("192.168..1", '.', 4);

		result = MoreStringUtil.split("192.168.0.", '.', 4);
		assertThat(result).hasSize(3).containsSequence("192", "168", "0");
	}

	@Test
	public void split_4_oe() {

		List<String> result = MoreStringUtil.split("192.168.0.1", '.', 4);

		result = MoreStringUtil.split("192.168..1", '.', 4);

		result = MoreStringUtil.split("192.168.0.", '.', 4);

		assertThat(MoreStringUtil.split(null, '.', 4)).isNull();
	}

	@Test
	public void split_5_oe() {

		List<String> result = MoreStringUtil.split("192.168.0.1", '.', 4);

		result = MoreStringUtil.split("192.168..1", '.', 4);

		result = MoreStringUtil.split("192.168.0.", '.', 4);


		assertThat(MoreStringUtil.split("", '.', 4)).hasSize(0);
	}

	@Test
	public void charMatch_1_oe() {
		String str = "abc";
		assertThat(MoreStringUtil.startWith(str, 'a')).isTrue();
	}

	@Test
	public void charMatch_2_oe() {
		String str = "abc";
		assertThat(MoreStringUtil.startWith(str, 'b')).isFalse();
	}

	@Test
	public void charMatch_3_oe() {
		String str = "abc";
		assertThat(MoreStringUtil.startWith(null, 'b')).isFalse();
	}

	@Test
	public void charMatch_4_oe() {
		String str = "abc";
		assertThat(MoreStringUtil.startWith("", 'b')).isFalse();
	}

	@Test
	public void charMatch_5_oe() {
		String str = "abc";
		
		assertThat(MoreStringUtil.endWith(str, 'c')).isTrue();
	}

	@Test
	public void charMatch_6_oe() {
		String str = "abc";
		
		assertThat(MoreStringUtil.endWith(str, 'b')).isFalse();
	}

	@Test
	public void charMatch_7_oe() {
		String str = "abc";
		
		assertThat(MoreStringUtil.endWith(null, 'b')).isFalse();
	}

	@Test
	public void charMatch_8_oe() {
		String str = "abc";
		
		assertThat(MoreStringUtil.endWith("", 'b')).isFalse();
	}

	@Test
	public void charMatch_9_oe() {
		String str = "abc";
		

		assertThat(MoreStringUtil.replaceFirst("abbc", 'b', 'c')).isEqualTo("acbc");
	}

	@Test
	public void charMatch_10_oe() {
		String str = "abc";
		

		assertThat(MoreStringUtil.replaceFirst("abcc", 'c', 'c')).isEqualTo("abcc");
	}

	@Test
	public void charMatch_11_oe() {
		String str = "abc";
		

		assertThat(MoreStringUtil.replaceFirst("", 'c', 'c')).isEqualTo("");
	}

	@Test
	public void charMatch_12_oe() {
		String str = "abc";
		

		assertThat(MoreStringUtil.replaceFirst(null, 'c', 'c')).isNull();
	}

	@Test
	public void charMatch_13_oe() {
		String str = "abc";
		

		
		assertThat(MoreStringUtil.replaceLast("abbc", 'b', 'c')).isEqualTo("abcc");
	}

	@Test
	public void charMatch_14_oe() {
		String str = "abc";
		

		
		assertThat(MoreStringUtil.replaceLast("abcc", 'c', 'c')).isEqualTo("abcc");
	}

	@Test
	public void charMatch_15_oe() {
		String str = "abc";
		

		
		assertThat(MoreStringUtil.replaceLast("", 'c', 'c')).isEqualTo("");
	}

	@Test
	public void charMatch_16_oe() {
		String str = "abc";
		

		
		assertThat(MoreStringUtil.replaceLast(null, 'c', 'c')).isNull();
	}

	@Test
	public void utf8EncodedLength_1_oe() {
		assertThat(MoreStringUtil.utf8EncodedLength("ab12")).isEqualTo(4);
	}

	@Test
	public void utf8EncodedLength_2_oe() {
		assertThat(MoreStringUtil.utf8EncodedLength("中文")).isEqualTo(6);
	}

}
