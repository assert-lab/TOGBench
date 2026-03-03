package org.springside.modules.utils.text;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class TextValidatorTest_OE25Dev {

	@Test
	public void isMobileSimple_1_oe() {
		assertThat(TextValidator.isMobileSimple(null)).isFalse();
	}

	@Test
	public void isMobileSimple_2_oe() {
		assertThat(TextValidator.isMobileSimple("")).isFalse();
	}

	@Test
	public void isMobileSimple_3_oe() {
		assertThat(TextValidator.isMobileSimple("1234a")).isFalse();
	}

	@Test
	public void isMobileSimple_4_oe() {
		assertThat(TextValidator.isMobileSimple("1234561")).isFalse();
	}

	@Test
	public void isMobileSimple_5_oe() {
		assertThat(TextValidator.isMobileSimple("11170998762")).isTrue();
	}

	@Test
	public void isMobileExact_1_oe() {
		assertThat(TextValidator.isMobileExact("1234a")).isFalse();
	}

	@Test
	public void isMobileExact_2_oe() {
		assertThat(TextValidator.isMobileExact("11170998762")).isFalse();
	}

	@Test
	public void isMobileExact_3_oe() {
		assertThat(TextValidator.isMobileExact("13970998762")).isTrue();
	}

	@Test
	public void isTel_1_oe() {
		assertThat(TextValidator.isTel("8802973a")).isFalse();
	}

	@Test
	public void isTel_2_oe() {
		assertThat(TextValidator.isTel("8908222222")).isFalse();
	}

	@Test
	public void isTel_3_oe() {
		assertThat(TextValidator.isTel("89081")).isFalse();
	}

	@Test
	public void isTel_4_oe() {

		assertThat(TextValidator.isTel("89019739")).isTrue();
	}

	@Test
	public void isTel_5_oe() {

		assertThat(TextValidator.isTel("020-89019739")).isTrue();
	}

	@Test
	public void isIdCard_1_oe() {
		assertThat(TextValidator.isIdCard("440101198987754ab")).isFalse();
	}

	@Test
	public void isIdCard_2_oe() {
		assertThat(TextValidator.isIdCard("440101198987754122")).isFalse();
	}

	@Test
	public void isIdCard_3_oe() {
		assertThat(TextValidator.isIdCard("440101891232451")).isFalse();
	}

	@Test
	public void isIdCard_4_oe() {

		assertThat(TextValidator.isIdCard("440101198909204518")).isTrue();
	}

	@Test
	public void isIdCard_5_oe() {

		assertThat(TextValidator.isIdCard("440101891231451")).isTrue();
	}

	@Test
	public void isEmail_1_oe() {
		assertThat(TextValidator.isEmail("abc")).isFalse();
	}

	@Test
	public void isEmail_2_oe() {
		assertThat(TextValidator.isEmail("abc@a")).isFalse();
	}

	@Test
	public void isEmail_3_oe() {
		assertThat(TextValidator.isEmail("中文@a.com")).isFalse();
	}

	@Test
	public void isEmail_4_oe() {

		assertThat(TextValidator.isEmail("abc@abc.com")).isTrue();
	}

	@Test
	public void isUrl_1_oe() {
		assertThat(TextValidator.isUrl("abc.com")).isFalse();
	}

	@Test
	public void isUrl_2_oe() {
		assertThat(TextValidator.isUrl("http://abc.c om")).isFalse();
	}

	@Test
	public void isUrl_3_oe() {
		assertThat(TextValidator.isUrl("http2://abc.com")).isFalse();
	}

	@Test
	public void isUrl_4_oe() {

		assertThat(TextValidator.isUrl("http://abc.com")).isTrue();
	}

	@Test
	public void isDate_1_oe() {
		assertThat(TextValidator.isDate("2011-02-29")).isFalse();
	}

	@Test
	public void isDate_2_oe() {
		assertThat(TextValidator.isDate("201a-02-30")).isFalse();
	}

	@Test
	public void isDate_3_oe() {
		assertThat(TextValidator.isDate("2011-0211")).isFalse();
	}

	@Test
	public void isDate_4_oe() {

		assertThat(TextValidator.isDate("2011-03-11")).isTrue();
	}

	@Test
	public void isDate_5_oe() {

		assertThat(TextValidator.isDate("2012-02-29")).isTrue();
	}

	@Test
	public void isIp_1_oe() {
		assertThat(TextValidator.isIp("192.168.0.300")).isFalse();
	}

	@Test
	public void isIp_2_oe() {
		assertThat(TextValidator.isIp("192.168.300.1")).isFalse();
	}

	@Test
	public void isIp_3_oe() {
		assertThat(TextValidator.isIp("192.168.300")).isFalse();
	}

	@Test
	public void isIp_4_oe() {
		assertThat(TextValidator.isIp("192.168.A3.1")).isFalse();
	}

	@Test
	public void isIp_5_oe() {

		assertThat(TextValidator.isIp("192.168.0.1")).isTrue();
	}

}
