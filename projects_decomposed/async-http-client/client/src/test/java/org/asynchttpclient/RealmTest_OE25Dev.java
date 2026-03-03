/*
 * Copyright (c) 2010-2012 Sonatype, Inc. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package org.asynchttpclient;

import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.StringUtils;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static java.nio.charset.StandardCharsets.UTF_16;
import static org.asynchttpclient.Dsl.*;
import static org.testng.Assert.assertEquals;

public class RealmTest_OE25Dev {

  @Test
  public void testOldDigestEmptyString() throws Exception {
    testOldDigest("");
  }

  @Test
  public void testOldDigestNull() throws Exception {
    testOldDigest(null);
  }

  private void testOldDigest(String qop) throws Exception {
    String user = "user";
    String pass = "pass";
    String realm = "realm";
    String nonce = "nonce";
    String method = "GET";
    Uri uri = Uri.create("http://ahc.io/foo");
    Realm orig = digestAuthRealm(user, pass)
            .setNonce(nonce)
            .setUri(uri)
            .setMethodName(method)
            .setRealmName(realm)
            .setQop(qop)
            .build();

    String ha1 = getMd5(user + ":" + realm + ":" + pass);
    String ha2 = getMd5(method + ":" + uri.getPath());
    String expectedResponse = getMd5(ha1 + ":" + nonce + ":" + ha2);

    assertEquals(orig.getResponse(), expectedResponse);
  }

  private String getMd5(String what) throws Exception {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(what.getBytes(StandardCharsets.ISO_8859_1));
    byte[] hash = md.digest();
    return StringUtils.toHexString(hash);
  }

  @Test
  public void testClone_1_oe() {
    Realm orig = basicAuthRealm("user", "pass").setCharset(UTF_16)
            .setUsePreemptiveAuth(true)
            .setRealmName("realm")
            .setAlgorithm("algo").build();

    Realm clone = realm(orig).build();
    assertEquals(clone.getPrincipal(), orig.getPrincipal());
  }

  @Test
  public void testClone_2_oe() {
    Realm orig = basicAuthRealm("user", "pass").setCharset(UTF_16)
            .setUsePreemptiveAuth(true)
            .setRealmName("realm")
            .setAlgorithm("algo").build();

    Realm clone = realm(orig).build();
    assertEquals(clone.getPassword(), orig.getPassword());
  }

  @Test
  public void testClone_3_oe() {
    Realm orig = basicAuthRealm("user", "pass").setCharset(UTF_16)
            .setUsePreemptiveAuth(true)
            .setRealmName("realm")
            .setAlgorithm("algo").build();

    Realm clone = realm(orig).build();
    assertEquals(clone.getCharset(), orig.getCharset());
  }

  @Test
  public void testClone_4_oe() {
    Realm orig = basicAuthRealm("user", "pass").setCharset(UTF_16)
            .setUsePreemptiveAuth(true)
            .setRealmName("realm")
            .setAlgorithm("algo").build();

    Realm clone = realm(orig).build();
    assertEquals(clone.isUsePreemptiveAuth(), orig.isUsePreemptiveAuth());
  }

  @Test
  public void testClone_5_oe() {
    Realm orig = basicAuthRealm("user", "pass").setCharset(UTF_16)
            .setUsePreemptiveAuth(true)
            .setRealmName("realm")
            .setAlgorithm("algo").build();

    Realm clone = realm(orig).build();
    assertEquals(clone.getRealmName(), orig.getRealmName());
  }

  @Test
  public void testClone_6_oe() {
    Realm orig = basicAuthRealm("user", "pass").setCharset(UTF_16)
            .setUsePreemptiveAuth(true)
            .setRealmName("realm")
            .setAlgorithm("algo").build();

    Realm clone = realm(orig).build();
    assertEquals(clone.getAlgorithm(), orig.getAlgorithm());
  }

  @Test
  public void testClone_7_oe() {
    Realm orig = basicAuthRealm("user", "pass").setCharset(UTF_16)
            .setUsePreemptiveAuth(true)
            .setRealmName("realm")
            .setAlgorithm("algo").build();

    Realm clone = realm(orig).build();
    assertEquals(clone.getScheme(), orig.getScheme());
  }

  @Test
  public void testStrongDigest_1_oe() throws Exception {
    String user = "user";
    String pass = "pass";
    String realm = "realm";
    String nonce = "nonce";
    String method = "GET";
    Uri uri = Uri.create("http://ahc.io/foo");
    String qop = "auth";
    Realm orig = digestAuthRealm(user, pass)
            .setNonce(nonce)
            .setUri(uri)
            .setMethodName(method)
            .setRealmName(realm)
            .setQop(qop)
            .build();

    String nc = orig.getNc();
    String cnonce = orig.getCnonce();
    String ha1 = getMd5(user + ":" + realm + ":" + pass);
    String ha2 = getMd5(method + ":" + uri.getPath());
    String expectedResponse = getMd5(ha1 + ":" + nonce + ":" + nc + ":" + cnonce + ":" + qop + ":" + ha2);

    assertEquals(orig.getResponse(), expectedResponse);
  }

}
