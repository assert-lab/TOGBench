/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.modules.utils.security;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springside.modules.utils.text.EncodeUtil;


public class CryptoUtilTest_OE25Dev {
	@Test
	public void mac() {
		String input = "foo message";

		// key可为任意字符串
		// byte[] key = "a foo key".getBytes();
		byte[] key = CryptoUtil.generateHmacSha1Key();
		assertThat(key).hasSize(20);

		byte[] macResult = CryptoUtil.hmacSha1(input.getBytes(), key);
		System.out.println("hmac-sha1 key in hex      :" + EncodeUtil.encodeHex(key));
		System.out.println("hmac-sha1 in hex result   :" + EncodeUtil.encodeHex(macResult));

		assertThat(CryptoUtil.isMacValid(macResult, input.getBytes(), key)).isTrue();
	}

	@Test
	public void aes() {
		byte[] key = CryptoUtil.generateAesKey();
		assertThat(key).hasSize(16);
		String input = "foo message";

		byte[] encryptResult = CryptoUtil.aesEncrypt(input.getBytes(), key);
		String descryptResult = CryptoUtil.aesDecrypt(encryptResult, key);

		System.out.println("aes key in hex            :" + EncodeUtil.encodeHex(key));
		System.out.println("aes encrypt in hex result :" + EncodeUtil.encodeHex(encryptResult));
		assertThat(descryptResult).isEqualTo(input);
	}

	@Test
	public void aesWithIV() {
		byte[] key = CryptoUtil.generateAesKey();
		byte[] iv = CryptoUtil.generateIV();
		assertThat(key).hasSize(16);
		assertThat(iv).hasSize(16);
		String input = "foo message";

		byte[] encryptResult = CryptoUtil.aesEncrypt(input.getBytes(), key, iv);
		String descryptResult = CryptoUtil.aesDecrypt(encryptResult, key, iv);

		System.out.println("aes key in hex            :" + EncodeUtil.encodeHex(key));
		System.out.println("iv in hex                 :" + EncodeUtil.encodeHex(iv));
		System.out.println("aes encrypt in hex result :" + EncodeUtil.encodeHex(encryptResult));
		assertThat(descryptResult).isEqualTo(input);
	}

	@Test
	public void mac_1_oe() {
		String input = "foo message";

		byte[] key = CryptoUtil.generateHmacSha1Key();
		assertThat(key).hasSize(20);
	}

	@Test
	public void mac_2_oe() {
		String input = "foo message";

		byte[] key = CryptoUtil.generateHmacSha1Key();

		byte[] macResult = CryptoUtil.hmacSha1(input.getBytes(), key);
		System.out.println("hmac-sha1 key in hex      :" + EncodeUtil.encodeHex(key));
		System.out.println("hmac-sha1 in hex result   :" + EncodeUtil.encodeHex(macResult));

		assertThat(CryptoUtil.isMacValid(macResult, input.getBytes(), key)).isTrue();
	}

	@Test
	public void aes_1_oe() {
		byte[] key = CryptoUtil.generateAesKey();
		assertThat(key).hasSize(16);
	}

	@Test
	public void aes_2_oe() {
		byte[] key = CryptoUtil.generateAesKey();
		String input = "foo message";

		byte[] encryptResult = CryptoUtil.aesEncrypt(input.getBytes(), key);
		String descryptResult = CryptoUtil.aesDecrypt(encryptResult, key);

		System.out.println("aes key in hex            :" + EncodeUtil.encodeHex(key));
		System.out.println("aes encrypt in hex result :" + EncodeUtil.encodeHex(encryptResult));
		assertThat(descryptResult).isEqualTo(input);
	}

	@Test
	public void aesWithIV_1_oe() {
		byte[] key = CryptoUtil.generateAesKey();
		byte[] iv = CryptoUtil.generateIV();
		assertThat(key).hasSize(16);
	}

	@Test
	public void aesWithIV_2_oe() {
		byte[] key = CryptoUtil.generateAesKey();
		byte[] iv = CryptoUtil.generateIV();
		assertThat(iv).hasSize(16);
	}

	@Test
	public void aesWithIV_3_oe() {
		byte[] key = CryptoUtil.generateAesKey();
		byte[] iv = CryptoUtil.generateIV();
		String input = "foo message";

		byte[] encryptResult = CryptoUtil.aesEncrypt(input.getBytes(), key, iv);
		String descryptResult = CryptoUtil.aesDecrypt(encryptResult, key, iv);

		System.out.println("aes key in hex            :" + EncodeUtil.encodeHex(key));
		System.out.println("iv in hex                 :" + EncodeUtil.encodeHex(iv));
		System.out.println("aes encrypt in hex result :" + EncodeUtil.encodeHex(encryptResult));
		assertThat(descryptResult).isEqualTo(input);
	}

}
