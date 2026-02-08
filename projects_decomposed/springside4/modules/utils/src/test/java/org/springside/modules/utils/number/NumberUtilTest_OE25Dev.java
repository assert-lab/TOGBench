package org.springside.modules.utils.number;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class NumberUtilTest_OE25Dev {

	@Test
	public void toBytes_1_oe() {
		byte[] bytes = NumberUtil.toBytes(1);
		assertThat(bytes).hasSize(4).containsSequence((byte) 0, (byte) 0, (byte) 0, (byte) 1);
	}

	@Test
	public void toBytes_2_oe() {
		byte[] bytes = NumberUtil.toBytes(1);
		// removed other assertion

		bytes = NumberUtil.toBytes(257);
		assertThat(bytes).containsSequence((byte) 0, (byte) 0, (byte) 1, (byte) 1);
	}

	@Test
	public void toBytes_3_oe() {
		byte[] bytes = NumberUtil.toBytes(1);
		// removed other assertion

		bytes = NumberUtil.toBytes(257);
		// removed other assertion
		assertThat(NumberUtil.toInt(bytes)).isEqualTo(257);
	}

	@Test
	public void toBytes_4_oe() {
		byte[] bytes = NumberUtil.toBytes(1);
		// removed other assertion

		bytes = NumberUtil.toBytes(257);
		// removed other assertion
		// removed other assertion

		//long
		byte[] bytes2 = NumberUtil.toBytes(1L);
		assertThat(bytes2).hasSize(8);
	}

	@Test
	public void toBytes_5_oe() {
		byte[] bytes = NumberUtil.toBytes(1);
		// removed other assertion

		bytes = NumberUtil.toBytes(257);
		// removed other assertion
		// removed other assertion

		//long
		byte[] bytes2 = NumberUtil.toBytes(1L);
		// removed other assertion

		
		bytes2 = NumberUtil.toBytes(257L);
		assertThat(bytes2).containsSequence((byte) 0, (byte) 0, (byte) 1, (byte) 1);
	}

	@Test
	public void toBytes_6_oe() {
		byte[] bytes = NumberUtil.toBytes(1);
		// removed other assertion

		bytes = NumberUtil.toBytes(257);
		// removed other assertion
		// removed other assertion

		//long
		byte[] bytes2 = NumberUtil.toBytes(1L);
		// removed other assertion

		
		bytes2 = NumberUtil.toBytes(257L);
		// removed other assertion
		assertThat(NumberUtil.toLong(bytes2)).isEqualTo(257L);
	}

	@Test
	public void toBytes_7_oe() {
		byte[] bytes = NumberUtil.toBytes(1);
		// removed other assertion

		bytes = NumberUtil.toBytes(257);
		// removed other assertion
		// removed other assertion

		//long
		byte[] bytes2 = NumberUtil.toBytes(1L);
		// removed other assertion

		
		bytes2 = NumberUtil.toBytes(257L);
		// removed other assertion
		// removed other assertion
		
		//dobule
		byte[] bytes3 = NumberUtil.toBytes(1.123d);
		assertThat(NumberUtil.toDouble(bytes3)).isEqualTo(1.123d);
	}

	@Test
	public void toBytes_8_oe() {
		byte[] bytes = NumberUtil.toBytes(1);
		// removed other assertion

		bytes = NumberUtil.toBytes(257);
		// removed other assertion
		// removed other assertion

		//long
		byte[] bytes2 = NumberUtil.toBytes(1L);
		// removed other assertion

		
		bytes2 = NumberUtil.toBytes(257L);
		// removed other assertion
		// removed other assertion
		
		//dobule
		byte[] bytes3 = NumberUtil.toBytes(1.123d);
		// removed other assertion
		
		//toInt32
		assertThat(NumberUtil.toInt32(123l)).isEqualTo(123);
	}

	@Test
	public void toBytes_10_oe() {
		byte[] bytes = NumberUtil.toBytes(1);
		// removed other assertion

		bytes = NumberUtil.toBytes(257);
		// removed other assertion
		// removed other assertion

		//long
		byte[] bytes2 = NumberUtil.toBytes(1L);
		// removed other assertion

		
		bytes2 = NumberUtil.toBytes(257L);
		// removed other assertion
		// removed other assertion
		
		//dobule
		byte[] bytes3 = NumberUtil.toBytes(1.123d);
		// removed other assertion
		
		//toInt32
		// removed other assertion
		
		try{
			NumberUtil.toInt32(Long.valueOf(Integer.MAX_VALUE+1l));
			// removed other assertion
		}catch(Exception e){
			assertThat(e).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void isNumber_1_oe() {
		assertThat(NumberUtil.isNumber("123")).isTrue();
	}

	@Test
	public void isNumber_2_oe() {
		// removed other assertion
		assertThat(NumberUtil.isNumber("-123.1")).isTrue();
	}

	@Test
	public void isNumber_3_oe() {
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.isNumber("-1a3.1")).isFalse();
	}

	@Test
	public void isNumber_4_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(NumberUtil.isHexNumber("0x12F")).isTrue();
	}

	@Test
	public void isNumber_5_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(NumberUtil.isHexNumber("-0x12A3")).isTrue();
	}

	@Test
	public void isNumber_6_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.isHexNumber("12A3")).isFalse();
	}

	@Test
	public void toNumber_1_oe() {
		assertThat(NumberUtil.toInt("122")).isEqualTo(122);
	}

	@Test
	public void toNumber_2_oe() {
		// removed other assertion
		assertThat(NumberUtil.toInt("12A")).isEqualTo(0);
	}

	@Test
	public void toNumber_3_oe() {
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toInt((String) null)).isEqualTo(0);
	}

	@Test
	public void toNumber_4_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toInt("12A", 123)).isEqualTo(123);
	}

	@Test
	public void toNumber_5_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(NumberUtil.toLong("122")).isEqualTo(122L);
	}

	@Test
	public void toNumber_6_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(NumberUtil.toLong("12A")).isEqualTo(0L);
	}

	@Test
	public void toNumber_7_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toLong((String) null)).isEqualTo(0);
	}

	@Test
	public void toNumber_8_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toLong("12A", 123)).isEqualTo(123L);
	}

	@Test
	public void toNumber_9_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(NumberUtil.toDouble("122.1")).isEqualTo(122.1);
	}

	@Test
	public void toNumber_10_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(NumberUtil.toDouble("12A")).isEqualTo(0L);
	}

	@Test
	public void toNumber_11_oe() {
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
		assertThat(NumberUtil.toDouble("12A", 123.1)).isEqualTo(123.1);
	}

	@Test
	public void toNumber_12_oe() {
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
		// removed other assertion

		assertThat(NumberUtil.toIntObject("122")).isEqualTo(122);
	}

	@Test
	public void toNumber_13_oe() {
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
		// removed other assertion

		// removed other assertion
		assertThat(NumberUtil.toIntObject("12A")).isEqualTo(null);
	}

	@Test
	public void toNumber_14_oe() {
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
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toIntObject("12A", 123)).isEqualTo(123);
	}

	@Test
	public void toNumber_15_oe() {
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
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toIntObject(null, 123)).isEqualTo(123);
	}

	@Test
	public void toNumber_16_oe() {
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
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toIntObject("", 123)).isEqualTo(123);
	}

	@Test
	public void toNumber_17_oe() {
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
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(NumberUtil.toLongObject("122")).isEqualTo(122L);
	}

	@Test
	public void toNumber_18_oe() {
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
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(NumberUtil.toLongObject("12A")).isEqualTo(null);
	}

	@Test
	public void toNumber_19_oe() {
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
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toLongObject("12A", 123L)).isEqualTo(123L);
	}

	@Test
	public void toNumber_20_oe() {
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
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toLongObject(null, 123L)).isEqualTo(123L);
	}

	@Test
	public void toNumber_21_oe() {
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

		assertThat(NumberUtil.toDoubleObject("122.1")).isEqualTo(122.1);
	}

	@Test
	public void toNumber_22_oe() {
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

		// removed other assertion
		assertThat(NumberUtil.toDoubleObject("12A")).isEqualTo(null);
	}

	@Test
	public void toNumber_23_oe() {
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

		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toDoubleObject("12A", 123.1)).isEqualTo(123.1);
	}

	@Test
	public void toNumber_24_oe() {
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

		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(NumberUtil.hexToIntObject("0x10")).isEqualTo(16);
	}

	@Test
	public void toNumber_25_oe() {
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

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(NumberUtil.hexToIntObject("0X100")).isEqualTo(256);
	}

	@Test
	public void toNumber_26_oe() {
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

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.hexToIntObject("-0x100")).isEqualTo(-256);
	}

	@Test
	public void toNumber_27_oe() {
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

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.hexToIntObject("0xHI")).isEqualTo(null);
	}

	@Test
	public void toNumber_28_oe() {
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

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.hexToIntObject(null)).isEqualTo(null);
	}

	@Test
	public void toNumber_29_oe() {
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

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.hexToIntObject("0xHI", 123)).isEqualTo(123);
	}

	@Test
	public void toNumber_30_oe() {
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

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(NumberUtil.hexToLongObject("0x10")).isEqualTo(16L);
	}

	@Test
	public void toNumber_31_oe() {
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
		assertThat(NumberUtil.hexToLongObject("0X100")).isEqualTo(256L);
	}

	@Test
	public void toNumber_32_oe() {
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
		// removed other assertion
		assertThat(NumberUtil.hexToLongObject("-0x100")).isEqualTo(-256L);
	}

	@Test
	public void toNumber_33_oe() {
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
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.hexToLongObject("0xHI")).isEqualTo(null);
	}

	@Test
	public void toNumber_34_oe() {
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
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.hexToLongObject(null)).isEqualTo(null);
	}

	@Test
	public void toNumber_35_oe() {
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
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.hexToLongObject("0xHI", 123L)).isEqualTo(123L);
	}

	@Test
	public void toStringTest_1_oe() {
		assertThat(NumberUtil.toString(23)).isEqualTo("23");
	}

	@Test
	public void toStringTest_2_oe() {
		// removed other assertion
		assertThat(NumberUtil.toString(new Integer(23))).isEqualTo("23");
	}

	@Test
	public void toStringTest_3_oe() {
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toString(23l)).isEqualTo("23");
	}

	@Test
	public void toStringTest_4_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toString(new Long(23))).isEqualTo("23");
	}

	@Test
	public void toStringTest_5_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.toString(23l)).isEqualTo("23");
	}

	@Test
	public void toStringTest_6_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		assertThat(NumberUtil.toString(new Double(23.112d))).isEqualTo("23.112");
	}

	@Test
	public void toStringTest_7_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		assertThat(NumberUtil.toString(23.112d)).isEqualTo("23.112");
	}

	@Test
	public void toStringTest_8_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.to2DigitString(23.112d)).isEqualTo("23.11");
	}

	@Test
	public void toStringTest_9_oe() {
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(NumberUtil.to2DigitString(23.116d)).isEqualTo("23.12");
	}

}
