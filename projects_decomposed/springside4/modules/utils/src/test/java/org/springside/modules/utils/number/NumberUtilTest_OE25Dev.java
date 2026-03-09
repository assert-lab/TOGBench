package org.springside.modules.utils.number;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class NumberUtilTest_OE25Dev {

	@Test
	public void toBytes() {
		byte[] bytes = NumberUtil.toBytes(1);
		assertThat(bytes).hasSize(4).containsSequence((byte) 0, (byte) 0, (byte) 0, (byte) 1);

		bytes = NumberUtil.toBytes(257);
		assertThat(bytes).containsSequence((byte) 0, (byte) 0, (byte) 1, (byte) 1);
		assertThat(NumberUtil.toInt(bytes)).isEqualTo(257);

		//long
		byte[] bytes2 = NumberUtil.toBytes(1L);
		assertThat(bytes2).hasSize(8);

		
		bytes2 = NumberUtil.toBytes(257L);
		assertThat(bytes2).containsSequence((byte) 0, (byte) 0, (byte) 1, (byte) 1);
		assertThat(NumberUtil.toLong(bytes2)).isEqualTo(257L);
		
		//dobule
		byte[] bytes3 = NumberUtil.toBytes(1.123d);
		assertThat(NumberUtil.toDouble(bytes3)).isEqualTo(1.123d);
		
		//toInt32
		assertThat(NumberUtil.toInt32(123l)).isEqualTo(123);
		
		try{
			NumberUtil.toInt32(Long.valueOf(Integer.MAX_VALUE+1l));
			fail("should fail here");
		}catch(Exception e){
			assertThat(e).isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Test
	public void isNumber() {
		assertThat(NumberUtil.isNumber("123")).isTrue();
		assertThat(NumberUtil.isNumber("-123.1")).isTrue();
		assertThat(NumberUtil.isNumber("-1a3.1")).isFalse();

		assertThat(NumberUtil.isHexNumber("0x12F")).isTrue();
		assertThat(NumberUtil.isHexNumber("-0x12A3")).isTrue();
		assertThat(NumberUtil.isHexNumber("12A3")).isFalse();
	}

	@Test
	public void toNumber() {
		assertThat(NumberUtil.toInt("122")).isEqualTo(122);
		assertThat(NumberUtil.toInt("12A")).isEqualTo(0);
		assertThat(NumberUtil.toInt((String) null)).isEqualTo(0);
		assertThat(NumberUtil.toInt("12A", 123)).isEqualTo(123);

		assertThat(NumberUtil.toLong("122")).isEqualTo(122L);
		assertThat(NumberUtil.toLong("12A")).isEqualTo(0L);
		assertThat(NumberUtil.toLong((String) null)).isEqualTo(0);
		assertThat(NumberUtil.toLong("12A", 123)).isEqualTo(123L);

		assertThat(NumberUtil.toDouble("122.1")).isEqualTo(122.1);
		assertThat(NumberUtil.toDouble("12A")).isEqualTo(0L);
		assertThat(NumberUtil.toDouble("12A", 123.1)).isEqualTo(123.1);

		assertThat(NumberUtil.toIntObject("122")).isEqualTo(122);
		assertThat(NumberUtil.toIntObject("12A")).isEqualTo(null);
		assertThat(NumberUtil.toIntObject("12A", 123)).isEqualTo(123);
		assertThat(NumberUtil.toIntObject(null, 123)).isEqualTo(123);
		assertThat(NumberUtil.toIntObject("", 123)).isEqualTo(123);

		assertThat(NumberUtil.toLongObject("122")).isEqualTo(122L);
		assertThat(NumberUtil.toLongObject("12A")).isEqualTo(null);
		assertThat(NumberUtil.toLongObject("12A", 123L)).isEqualTo(123L);
		assertThat(NumberUtil.toLongObject(null, 123L)).isEqualTo(123L);

		assertThat(NumberUtil.toDoubleObject("122.1")).isEqualTo(122.1);
		assertThat(NumberUtil.toDoubleObject("12A")).isEqualTo(null);
		assertThat(NumberUtil.toDoubleObject("12A", 123.1)).isEqualTo(123.1);

		assertThat(NumberUtil.hexToIntObject("0x10")).isEqualTo(16);
		assertThat(NumberUtil.hexToIntObject("0X100")).isEqualTo(256);
		assertThat(NumberUtil.hexToIntObject("-0x100")).isEqualTo(-256);
		assertThat(NumberUtil.hexToIntObject("0xHI")).isEqualTo(null);
		assertThat(NumberUtil.hexToIntObject(null)).isEqualTo(null);
		assertThat(NumberUtil.hexToIntObject("0xHI", 123)).isEqualTo(123);

		assertThat(NumberUtil.hexToLongObject("0x10")).isEqualTo(16L);
		assertThat(NumberUtil.hexToLongObject("0X100")).isEqualTo(256L);
		assertThat(NumberUtil.hexToLongObject("-0x100")).isEqualTo(-256L);
		assertThat(NumberUtil.hexToLongObject("0xHI")).isEqualTo(null);
		assertThat(NumberUtil.hexToLongObject(null)).isEqualTo(null);
		assertThat(NumberUtil.hexToLongObject("0xHI", 123L)).isEqualTo(123L);

	}

	@Test
	public void toStringTest() {
		assertThat(NumberUtil.toString(23)).isEqualTo("23");
		assertThat(NumberUtil.toString(new Integer(23))).isEqualTo("23");
		assertThat(NumberUtil.toString(23l)).isEqualTo("23");
		assertThat(NumberUtil.toString(new Long(23))).isEqualTo("23");
		assertThat(NumberUtil.toString(23l)).isEqualTo("23");
		
		assertThat(NumberUtil.toString(new Double(23.112d))).isEqualTo("23.112");
		assertThat(NumberUtil.toString(23.112d)).isEqualTo("23.112");
		assertThat(NumberUtil.to2DigitString(23.112d)).isEqualTo("23.11");
		assertThat(NumberUtil.to2DigitString(23.116d)).isEqualTo("23.12");

	}

	@Test
	public void toBytes_1_oe() {
		byte[] bytes = NumberUtil.toBytes(1);
		assertThat(bytes).hasSize(4).containsSequence((byte) 0, (byte) 0, (byte) 0, (byte) 1);
	}

	@Test
	public void toBytes_2_oe() {
		byte[] bytes = NumberUtil.toBytes(1);

		bytes = NumberUtil.toBytes(257);
		assertThat(bytes).containsSequence((byte) 0, (byte) 0, (byte) 1, (byte) 1);
	}

	@Test
	public void toBytes_3_oe() {
		byte[] bytes = NumberUtil.toBytes(1);

		bytes = NumberUtil.toBytes(257);
		assertThat(NumberUtil.toInt(bytes)).isEqualTo(257);
	}

	@Test
	public void toBytes_4_oe() {
		byte[] bytes = NumberUtil.toBytes(1);

		bytes = NumberUtil.toBytes(257);

		byte[] bytes2 = NumberUtil.toBytes(1L);
		assertThat(bytes2).hasSize(8);
	}

	@Test
	public void toBytes_5_oe() {
		byte[] bytes = NumberUtil.toBytes(1);

		bytes = NumberUtil.toBytes(257);

		byte[] bytes2 = NumberUtil.toBytes(1L);

		
		bytes2 = NumberUtil.toBytes(257L);
		assertThat(bytes2).containsSequence((byte) 0, (byte) 0, (byte) 1, (byte) 1);
	}

	@Test
	public void toBytes_6_oe() {
		byte[] bytes = NumberUtil.toBytes(1);

		bytes = NumberUtil.toBytes(257);

		byte[] bytes2 = NumberUtil.toBytes(1L);

		
		bytes2 = NumberUtil.toBytes(257L);
		assertThat(NumberUtil.toLong(bytes2)).isEqualTo(257L);
	}

	@Test
	public void toBytes_7_oe() {
		byte[] bytes = NumberUtil.toBytes(1);

		bytes = NumberUtil.toBytes(257);

		byte[] bytes2 = NumberUtil.toBytes(1L);

		
		bytes2 = NumberUtil.toBytes(257L);
		
		byte[] bytes3 = NumberUtil.toBytes(1.123d);
		assertThat(NumberUtil.toDouble(bytes3)).isEqualTo(1.123d);
	}

	@Test
	public void toBytes_8_oe() {
		byte[] bytes = NumberUtil.toBytes(1);

		bytes = NumberUtil.toBytes(257);

		byte[] bytes2 = NumberUtil.toBytes(1L);

		
		bytes2 = NumberUtil.toBytes(257L);
		
		byte[] bytes3 = NumberUtil.toBytes(1.123d);
		
		assertThat(NumberUtil.toInt32(123l)).isEqualTo(123);
	}

	@Test
	public void toBytes_10_oe() {
		byte[] bytes = NumberUtil.toBytes(1);

		bytes = NumberUtil.toBytes(257);

		byte[] bytes2 = NumberUtil.toBytes(1L);

		
		bytes2 = NumberUtil.toBytes(257L);
		
		byte[] bytes3 = NumberUtil.toBytes(1.123d);
		
		
		try{
			NumberUtil.toInt32(Long.valueOf(Integer.MAX_VALUE+1l));
			fail("should fail here");
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
		assertThat(NumberUtil.isNumber("-123.1")).isTrue();
	}

	@Test
	public void isNumber_3_oe() {
		assertThat(NumberUtil.isNumber("-1a3.1")).isFalse();
	}

	@Test
	public void isNumber_4_oe() {

		assertThat(NumberUtil.isHexNumber("0x12F")).isTrue();
	}

	@Test
	public void isNumber_5_oe() {

		assertThat(NumberUtil.isHexNumber("-0x12A3")).isTrue();
	}

	@Test
	public void isNumber_6_oe() {

		assertThat(NumberUtil.isHexNumber("12A3")).isFalse();
	}

	@Test
	public void toNumber_1_oe() {
		assertThat(NumberUtil.toInt("122")).isEqualTo(122);
	}

	@Test
	public void toNumber_2_oe() {
		assertThat(NumberUtil.toInt("12A")).isEqualTo(0);
	}

	@Test
	public void toNumber_3_oe() {
		assertThat(NumberUtil.toInt((String) null)).isEqualTo(0);
	}

	@Test
	public void toNumber_4_oe() {
		assertThat(NumberUtil.toInt("12A", 123)).isEqualTo(123);
	}

	@Test
	public void toNumber_5_oe() {

		assertThat(NumberUtil.toLong("122")).isEqualTo(122L);
	}

	@Test
	public void toNumber_6_oe() {

		assertThat(NumberUtil.toLong("12A")).isEqualTo(0L);
	}

	@Test
	public void toNumber_7_oe() {

		assertThat(NumberUtil.toLong((String) null)).isEqualTo(0);
	}

	@Test
	public void toNumber_8_oe() {

		assertThat(NumberUtil.toLong("12A", 123)).isEqualTo(123L);
	}

	@Test
	public void toNumber_9_oe() {


		assertThat(NumberUtil.toDouble("122.1")).isEqualTo(122.1);
	}

	@Test
	public void toNumber_10_oe() {


		assertThat(NumberUtil.toDouble("12A")).isEqualTo(0L);
	}

	@Test
	public void toNumber_11_oe() {


		assertThat(NumberUtil.toDouble("12A", 123.1)).isEqualTo(123.1);
	}

	@Test
	public void toNumber_12_oe() {



		assertThat(NumberUtil.toIntObject("122")).isEqualTo(122);
	}

	@Test
	public void toNumber_13_oe() {



		assertThat(NumberUtil.toIntObject("12A")).isEqualTo(null);
	}

	@Test
	public void toNumber_14_oe() {



		assertThat(NumberUtil.toIntObject("12A", 123)).isEqualTo(123);
	}

	@Test
	public void toNumber_15_oe() {



		assertThat(NumberUtil.toIntObject(null, 123)).isEqualTo(123);
	}

	@Test
	public void toNumber_16_oe() {



		assertThat(NumberUtil.toIntObject("", 123)).isEqualTo(123);
	}

	@Test
	public void toNumber_17_oe() {




		assertThat(NumberUtil.toLongObject("122")).isEqualTo(122L);
	}

	@Test
	public void toNumber_18_oe() {




		assertThat(NumberUtil.toLongObject("12A")).isEqualTo(null);
	}

	@Test
	public void toNumber_19_oe() {




		assertThat(NumberUtil.toLongObject("12A", 123L)).isEqualTo(123L);
	}

	@Test
	public void toNumber_20_oe() {




		assertThat(NumberUtil.toLongObject(null, 123L)).isEqualTo(123L);
	}

	@Test
	public void toNumber_21_oe() {





		assertThat(NumberUtil.toDoubleObject("122.1")).isEqualTo(122.1);
	}

	@Test
	public void toNumber_22_oe() {





		assertThat(NumberUtil.toDoubleObject("12A")).isEqualTo(null);
	}

	@Test
	public void toNumber_23_oe() {





		assertThat(NumberUtil.toDoubleObject("12A", 123.1)).isEqualTo(123.1);
	}

	@Test
	public void toNumber_24_oe() {






		assertThat(NumberUtil.hexToIntObject("0x10")).isEqualTo(16);
	}

	@Test
	public void toNumber_25_oe() {






		assertThat(NumberUtil.hexToIntObject("0X100")).isEqualTo(256);
	}

	@Test
	public void toNumber_26_oe() {






		assertThat(NumberUtil.hexToIntObject("-0x100")).isEqualTo(-256);
	}

	@Test
	public void toNumber_27_oe() {






		assertThat(NumberUtil.hexToIntObject("0xHI")).isEqualTo(null);
	}

	@Test
	public void toNumber_28_oe() {






		assertThat(NumberUtil.hexToIntObject(null)).isEqualTo(null);
	}

	@Test
	public void toNumber_29_oe() {






		assertThat(NumberUtil.hexToIntObject("0xHI", 123)).isEqualTo(123);
	}

	@Test
	public void toNumber_30_oe() {







		assertThat(NumberUtil.hexToLongObject("0x10")).isEqualTo(16L);
	}

	@Test
	public void toNumber_31_oe() {







		assertThat(NumberUtil.hexToLongObject("0X100")).isEqualTo(256L);
	}

	@Test
	public void toNumber_32_oe() {







		assertThat(NumberUtil.hexToLongObject("-0x100")).isEqualTo(-256L);
	}

	@Test
	public void toNumber_33_oe() {







		assertThat(NumberUtil.hexToLongObject("0xHI")).isEqualTo(null);
	}

	@Test
	public void toNumber_34_oe() {







		assertThat(NumberUtil.hexToLongObject(null)).isEqualTo(null);
	}

	@Test
	public void toNumber_35_oe() {







		assertThat(NumberUtil.hexToLongObject("0xHI", 123L)).isEqualTo(123L);
	}

	@Test
	public void toStringTest_1_oe() {
		assertThat(NumberUtil.toString(23)).isEqualTo("23");
	}

	@Test
	public void toStringTest_2_oe() {
		assertThat(NumberUtil.toString(new Integer(23))).isEqualTo("23");
	}

	@Test
	public void toStringTest_3_oe() {
		assertThat(NumberUtil.toString(23l)).isEqualTo("23");
	}

	@Test
	public void toStringTest_4_oe() {
		assertThat(NumberUtil.toString(new Long(23))).isEqualTo("23");
	}

	@Test
	public void toStringTest_5_oe() {
		assertThat(NumberUtil.toString(23l)).isEqualTo("23");
	}

	@Test
	public void toStringTest_6_oe() {
		
		assertThat(NumberUtil.toString(new Double(23.112d))).isEqualTo("23.112");
	}

	@Test
	public void toStringTest_7_oe() {
		
		assertThat(NumberUtil.toString(23.112d)).isEqualTo("23.112");
	}

	@Test
	public void toStringTest_8_oe() {
		
		assertThat(NumberUtil.to2DigitString(23.112d)).isEqualTo("23.11");
	}

	@Test
	public void toStringTest_9_oe() {
		
		assertThat(NumberUtil.to2DigitString(23.116d)).isEqualTo("23.12");
	}

@Test
	public void toBytes_oe_101_oe() {
		try{
			NumberUtil.toInt32(Long.valueOf(Integer.MAX_VALUE+1l));
			fail("should fail here");
		}catch(Exception e){
		}
    }

}
