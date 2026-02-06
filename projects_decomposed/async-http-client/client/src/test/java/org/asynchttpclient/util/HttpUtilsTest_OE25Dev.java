/*
 * Copyright (c) 2015 AsyncHttpClient Project. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at
 *     http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package org.asynchttpclient.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Param;
import org.asynchttpclient.Request;
import org.asynchttpclient.netty.util.ByteBufUtils;
import org.asynchttpclient.uri.Uri;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static java.nio.charset.StandardCharsets.*;
import static org.testng.Assert.*;

public class HttpUtilsTest_OE25Dev {

  private static String toUsAsciiString(ByteBuffer buf) {
    ByteBuf bb = Unpooled.wrappedBuffer(buf);
    try {
      return ByteBufUtils.byteBuf2String(US_ASCII, bb);
    } finally {
      bb.release();
    }
  }

  private void formUrlEncoding(Charset charset) throws Exception {
    String key = "key";
    String value = "中文";
    List<Param> params = new ArrayList<>();
    params.add(new Param(key, value));
    ByteBuffer ahcBytes = HttpUtils.urlEncodeFormParams(params, charset);
    String ahcString = toUsAsciiString(ahcBytes);
    String jdkString = key + "=" + URLEncoder.encode(value, charset.name());
    assertEquals(ahcString, jdkString);
  }

  @Test
  public void formUrlEncodingShouldSupportUtf8Charset() throws Exception {
    formUrlEncoding(UTF_8);
  }

  @Test
  public void formUrlEncodingShouldSupportNonUtf8Charset() throws Exception {
    formUrlEncoding(Charset.forName("GBK"));
  }

  @Test
  public void testExtractCharsetWithoutQuotes_1_oe() {
    Charset charset = HttpUtils.extractContentTypeCharsetAttribute("text/html; charset=iso-8859-1");
    assertEquals(charset, ISO_8859_1);
  }

  @Test
  public void testExtractCharsetWithSingleQuotes_1_oe() {
    Charset charset = HttpUtils.extractContentTypeCharsetAttribute("text/html; charset='iso-8859-1'");
    assertEquals(charset, ISO_8859_1);
  }

  @Test
  public void testExtractCharsetWithDoubleQuotes_1_oe() {
    Charset charset = HttpUtils.extractContentTypeCharsetAttribute("text/html; charset=\"iso-8859-1\"");
    assertEquals(charset, ISO_8859_1);
  }

  @Test
  public void testExtractCharsetWithDoubleQuotesAndSpaces_1_oe() {
    Charset charset = HttpUtils.extractContentTypeCharsetAttribute("text/html; charset= \"iso-8859-1\" ");
    assertEquals(charset, ISO_8859_1);
  }

  @Test
  public void testExtractCharsetFallsBackToUtf8_1_oe() {
    Charset charset = HttpUtils.extractContentTypeCharsetAttribute(APPLICATION_JSON.toString());
    assertNull(charset);
  }

  @Test
  public void testGetHostHeader_1_oe() {
    Uri uri = Uri.create("http://stackoverflow.com/questions/1057564/pretty-git-branch-graphs");
    String hostHeader = HttpUtils.hostHeader(uri);
    assertEquals(hostHeader, "stackoverflow.com", "Incorrect hostHeader returned");
  }

  @Test
  public void testDefaultFollowRedirect_1_oe() {
    Request request = Dsl.get("http://stackoverflow.com/questions/1057564").setVirtualHost("example.com").build();
    DefaultAsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().build();
    boolean followRedirect = HttpUtils.followRedirect(config, request);
    assertFalse(followRedirect, "Default value of redirect should be false");
  }

  @Test
  public void testGetFollowRedirectInRequest_1_oe() {
    Request request = Dsl.get("http://stackoverflow.com/questions/1057564").setFollowRedirect(true).build();
    DefaultAsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().build();
    boolean followRedirect = HttpUtils.followRedirect(config, request);
    assertTrue(followRedirect, "Follow redirect must be true as set in the request");
  }

  @Test
  public void testGetFollowRedirectInConfig_1_oe() {
    Request request = Dsl.get("http://stackoverflow.com/questions/1057564").build();
    DefaultAsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setFollowRedirect(true).build();
    boolean followRedirect = HttpUtils.followRedirect(config, request);
    assertTrue(followRedirect, "Follow redirect should be equal to value specified in config when not specified in request");
  }

  @Test
  public void testGetFollowRedirectPriorityGivenToRequest_1_oe() {
    Request request = Dsl.get("http://stackoverflow.com/questions/1057564").setFollowRedirect(false).build();
    DefaultAsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setFollowRedirect(true).build();
    boolean followRedirect = HttpUtils.followRedirect(config, request);
    assertFalse(followRedirect, "Follow redirect value set in request should be given priority");
  }

  @Test
  public void computeOriginForPlainUriWithImplicitPort_1_oe() {
    assertEquals(HttpUtils.originHeader(Uri.create("ws://foo.com/bar")), "http://foo.com");
  }

  @Test
  public void computeOriginForPlainUriWithDefaultPort_1_oe() {
    assertEquals(HttpUtils.originHeader(Uri.create("ws://foo.com:80/bar")), "http://foo.com");
  }

  @Test
  public void computeOriginForPlainUriWithNonDefaultPort_1_oe() {
    assertEquals(HttpUtils.originHeader(Uri.create("ws://foo.com:81/bar")), "http://foo.com:81");
  }

  @Test
  public void computeOriginForSecuredUriWithImplicitPort_1_oe() {
    assertEquals(HttpUtils.originHeader(Uri.create("wss://foo.com/bar")), "https://foo.com");
  }

  @Test
  public void computeOriginForSecuredUriWithDefaultPort_1_oe() {
    assertEquals(HttpUtils.originHeader(Uri.create("wss://foo.com:443/bar")), "https://foo.com");
  }

  @Test
  public void computeOriginForSecuredUriWithNonDefaultPort_1_oe() {
    assertEquals(HttpUtils.originHeader(Uri.create("wss://foo.com:444/bar")), "https://foo.com:444");
  }

}
