/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.modules.utils.text;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class EncodeUtilTest_OE25Dev {

	@Test
	public void hexEncode_1_oe() {
		String input = "haha,i am a very long message";
		String result = EncodeUtil.encodeHex(input.getBytes());
		assertThat(new String(EncodeUtil.decodeHex(result), Charsets.UTF_8)).isEqualTo(input);
	}

	@Test
	public void hexEncode_2_oe() {
		String input = "haha,i am a very long message";
		String result = EncodeUtil.encodeHex(input.getBytes());

		byte[] bytes = new byte[] { 1, 2, 15, 17 };
		result = EncodeUtil.encodeHex(bytes);
		assertThat(result).isEqualTo("01020F11");
	}

	@Test
	public void hexEncode_3_oe() {
		String input = "haha,i am a very long message";
		String result = EncodeUtil.encodeHex(input.getBytes());

		byte[] bytes = new byte[] { 1, 2, 15, 17 };
		result = EncodeUtil.encodeHex(bytes);

		input = "01020F11";
		assertThat(EncodeUtil.decodeHex(input)).hasSize(4).containsSequence((byte) 1, (byte) 2, (byte) 15, (byte) 17);
	}

	@Test
	public void hexEncode_5_oe() {
		String input = "haha,i am a very long message";
		String result = EncodeUtil.encodeHex(input.getBytes());

		byte[] bytes = new byte[] { 1, 2, 15, 17 };
		result = EncodeUtil.encodeHex(bytes);

		input = "01020F11";

		try {
			input = "01020G11";
			EncodeUtil.decodeHex(input);
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void base64Encode_1_oe() {
		String input = "haha,i am a very long message";
		String result = EncodeUtil.encodeBase64(input.getBytes());
		assertThat(new String(EncodeUtil.decodeBase64(result), Charsets.UTF_8)).isEqualTo(input);
	}

	@Test
	public void base64Encode_2_oe() {
		String input = "haha,i am a very long message";
		String result = EncodeUtil.encodeBase64(input.getBytes());

		byte[] bytes = new byte[] { 5 };
		result = EncodeUtil.encodeBase64(bytes);
		assertThat(result).isEqualTo("BQ==");
	}

	@Test
	public void base64Encode_3_oe() {
		String input = "haha,i am a very long message";
		String result = EncodeUtil.encodeBase64(input.getBytes());

		byte[] bytes = new byte[] { 5 };
		result = EncodeUtil.encodeBase64(bytes);

		bytes = new byte[] { 1, 2, 15, 17, 127 };
		result = EncodeUtil.encodeBase64(bytes);
		assertThat(result).isEqualTo("AQIPEX8=");
	}

	@Test
	public void base64UrlSafeEncode_1_oe() {
		String input = "haha,i am a very long message";
		String result = EncodeUtil.encodeBase64UrlSafe(input.getBytes());
		assertThat(new String(EncodeUtil.decodeBase64UrlSafe(result), Charsets.UTF_8)).isEqualTo(input);
	}

	@Test
	public void base64UrlSafeEncode_4_oe() {
		String input = "haha,i am a very long message";
		String result = EncodeUtil.encodeBase64UrlSafe(input.getBytes());

		try {
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

}
