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
package org.asynchttpclient.extras.simple;

import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.Response;
import org.asynchttpclient.request.body.generator.FileBodyGenerator;
import org.asynchttpclient.request.body.generator.InputStreamBodyGenerator;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.uri.Uri;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.testng.Assert.*;

public class SimpleAsyncHttpClientTest_OE25Dev extends AbstractBasicTest {

  private final static String MY_MESSAGE = "my message";

  /**
   * See https://issues.sonatype.org/browse/AHC-5
   */

  @Test(expectedExceptions = IllegalStateException.class)
  public void testCloseMasterInvalidDerived() throws Throwable {
    SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl()).build();
    try (SimpleAsyncHttpClient derived = client.derive().build()) {
      client.close();

      try {
        derived.get().get();
        fail("Expected closed AHC");
      } catch (ExecutionException e) {
        throw e.getCause();
      }
    }

  }

  @Test
  public void inputStreamBodyConsumerTest_1_oe() throws Exception {

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setPooledConnectionIdleTimeout(100)
            .setMaxConnections(50)
            .setRequestTimeout(5 * 60 * 1000)
            .setUrl(getTargetUrl())
            .setHeader("Content-Type", "text/html").build()) {
      Future<Response> future = client.post(new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes())));

      Response response = future.get();
      assertEquals(response.getStatusCode(), 200);
  }
  }

  @Test
  public void inputStreamBodyConsumerTest_2_oe() throws Exception {

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setPooledConnectionIdleTimeout(100)
            .setMaxConnections(50)
            .setRequestTimeout(5 * 60 * 1000)
            .setUrl(getTargetUrl())
            .setHeader("Content-Type", "text/html").build()) {
      Future<Response> future = client.post(new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes())));

      Response response = future.get();
      assertEquals(response.getResponseBody(), MY_MESSAGE);
  }
  }

  @Test
  public void stringBuilderBodyConsumerTest_1_oe() throws Exception {

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setPooledConnectionIdleTimeout(100)
            .setMaxConnections(50)
            .setRequestTimeout(5 * 60 * 1000)
            .setUrl(getTargetUrl())
            .setHeader("Content-Type", "text/html").build()) {
      StringBuilder s = new StringBuilder();
      Future<Response> future = client.post(new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes())), new AppendableBodyConsumer(s));

      Response response = future.get();
      assertEquals(response.getStatusCode(), 200);
  }
  }

  @Test
  public void stringBuilderBodyConsumerTest_2_oe() throws Exception {

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setPooledConnectionIdleTimeout(100)
            .setMaxConnections(50)
            .setRequestTimeout(5 * 60 * 1000)
            .setUrl(getTargetUrl())
            .setHeader("Content-Type", "text/html").build()) {
      StringBuilder s = new StringBuilder();
      Future<Response> future = client.post(new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes())), new AppendableBodyConsumer(s));

      Response response = future.get();
      assertEquals(s.toString(), MY_MESSAGE);
  }
  }

  @Test
  public void byteArrayOutputStreamBodyConsumerTest_1_oe() throws Exception {

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setPooledConnectionIdleTimeout(100).setMaxConnections(50)
            .setRequestTimeout(5 * 60 * 1000)
            .setUrl(getTargetUrl())
            .setHeader("Content-Type", "text/html").build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);
      Future<Response> future = client.post(new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes())), new OutputStreamBodyConsumer(o));

      Response response = future.get();
      assertEquals(response.getStatusCode(), 200);
  }
  }

  @Test
  public void byteArrayOutputStreamBodyConsumerTest_2_oe() throws Exception {

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setPooledConnectionIdleTimeout(100).setMaxConnections(50)
            .setRequestTimeout(5 * 60 * 1000)
            .setUrl(getTargetUrl())
            .setHeader("Content-Type", "text/html").build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);
      Future<Response> future = client.post(new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes())), new OutputStreamBodyConsumer(o));

      Response response = future.get();
      assertEquals(o.toString(), MY_MESSAGE);
  }
  }

  @Test
  public void requestByteArrayOutputStreamBodyConsumerTest_1_oe() throws Exception {

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl()).build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);
      Future<Response> future = client.post(new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes())), new OutputStreamBodyConsumer(o));

      Response response = future.get();
      assertEquals(response.getStatusCode(), 200);
  }
  }

  @Test
  public void requestByteArrayOutputStreamBodyConsumerTest_2_oe() throws Exception {

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl()).build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);
      Future<Response> future = client.post(new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes())), new OutputStreamBodyConsumer(o));

      Response response = future.get();
      assertEquals(o.toString(), MY_MESSAGE);
  }
  }

  @Test
  public void testPutZeroBytesFileTest_1_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setPooledConnectionIdleTimeout(100)
            .setMaxConnections(50)
            .setRequestTimeout(5 * 1000)
            .setUrl(getTargetUrl() + "/testPutZeroBytesFileTest.txt")
            .setHeader("Content-Type", "text/plain").build()) {
      File tmpfile = File.createTempFile("testPutZeroBytesFile", ".tmp");
      tmpfile.deleteOnExit();

      Future<Response> future = client.put(new FileBodyGenerator(tmpfile));

      System.out.println("waiting for response");
      Response response = future.get();

      tmpfile.delete();

      assertEquals(response.getStatusCode(), 200);
  }
  }

  @Test
  public void testDerive_1_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().build()) {
      try (SimpleAsyncHttpClient derived = client.derive().build()) {
        assertNotSame(derived, client);
  }
  }
  }

  @Test
  public void testDeriveOverrideURL_1_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl("http://invalid.url").build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);

      InputStreamBodyGenerator generator = new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes()));
      OutputStreamBodyConsumer consumer = new OutputStreamBodyConsumer(o);

      try (SimpleAsyncHttpClient derived = client.derive().setUrl(getTargetUrl()).build()) {
        Future<Response> future = derived.post(generator, consumer);

        Response response = future.get();
        assertEquals(response.getStatusCode(), 200);
  }
  }
  }

  @Test
  public void testDeriveOverrideURL_2_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl("http://invalid.url").build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);

      InputStreamBodyGenerator generator = new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes()));
      OutputStreamBodyConsumer consumer = new OutputStreamBodyConsumer(o);

      try (SimpleAsyncHttpClient derived = client.derive().setUrl(getTargetUrl()).build()) {
        Future<Response> future = derived.post(generator, consumer);

        Response response = future.get();
        assertEquals(o.toString(), MY_MESSAGE);
  }
  }
  }

  @Test
  public void testSimpleTransferListener_12_oe() throws Exception {

    final List<Error> errors = Collections.synchronizedList(new ArrayList<>());

    SimpleAHCTransferListener listener = new SimpleAHCTransferListener() {

      public void onStatus(Uri uri, int statusCode, String statusText) {
        try {
        } catch (Error e) {
          errors.add(e);
          throw e;
        }
      }

      public void onHeaders(Uri uri, HttpHeaders headers) {
        try {
        } catch (Error e) {
          errors.add(e);
          throw e;
        }
      }

      public void onCompleted(Uri uri, int statusCode, String statusText) {
        try {
        } catch (Error e) {
          errors.add(e);
          throw e;
        }
      }

      public void onBytesSent(Uri uri, long amount, long current, long total) {
        try {
        } catch (Error e) {
          errors.add(e);
          throw e;
        }
      }

      public void onBytesReceived(Uri uri, long amount, long current, long total) {
        try {
        } catch (Error e) {
          errors.add(e);
          throw e;
        }
      }
    };

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setUrl(getTargetUrl())
            .setHeader("Custom", "custom")
            .setListener(listener).build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);

      InputStreamBodyGenerator generator = new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes()));
      OutputStreamBodyConsumer consumer = new OutputStreamBodyConsumer(o);

      Future<Response> future = client.post(generator, consumer);

      Response response = future.get();

      if (!errors.isEmpty()) {
        for (Error e : errors) {
          e.printStackTrace();
        }
        throw errors.get(0);
      }

      assertEquals(response.getStatusCode(), 200);
  }
  }

  @Test
  public void testSimpleTransferListener_13_oe() throws Exception {

    final List<Error> errors = Collections.synchronizedList(new ArrayList<>());

    SimpleAHCTransferListener listener = new SimpleAHCTransferListener() {

      public void onStatus(Uri uri, int statusCode, String statusText) {
        try {
        } catch (Error e) {
          errors.add(e);
          throw e;
        }
      }

      public void onHeaders(Uri uri, HttpHeaders headers) {
        try {
        } catch (Error e) {
          errors.add(e);
          throw e;
        }
      }

      public void onCompleted(Uri uri, int statusCode, String statusText) {
        try {
        } catch (Error e) {
          errors.add(e);
          throw e;
        }
      }

      public void onBytesSent(Uri uri, long amount, long current, long total) {
        try {
        } catch (Error e) {
          errors.add(e);
          throw e;
        }
      }

      public void onBytesReceived(Uri uri, long amount, long current, long total) {
        try {
        } catch (Error e) {
          errors.add(e);
          throw e;
        }
      }
    };

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setUrl(getTargetUrl())
            .setHeader("Custom", "custom")
            .setListener(listener).build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);

      InputStreamBodyGenerator generator = new InputStreamBodyGenerator(new ByteArrayInputStream(MY_MESSAGE.getBytes()));
      OutputStreamBodyConsumer consumer = new OutputStreamBodyConsumer(o);

      Future<Response> future = client.post(generator, consumer);

      Response response = future.get();

      if (!errors.isEmpty()) {
        for (Error e : errors) {
          e.printStackTrace();
        }
        throw errors.get(0);
      }

      assertEquals(o.toString(), MY_MESSAGE);
  }
  }

  @Test
  public void testNullUrl_1_oe() throws Exception {

    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().build()) {
      assertTrue(true);
  }
  }

  @Test
  public void testCloseDerivedValidMaster_1_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl()).build()) {
      try (SimpleAsyncHttpClient derived = client.derive().build()) {
        derived.get().get();
      }

      Response response = client.get().get();
      assertEquals(response.getStatusCode(), 200);
  }
  }

  @Test
  public void testMultiPartPut_1_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.put(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");

      assertTrue(contentType.contains("multipart/form-data"));
  }
  }

  @Test
  public void testMultiPartPut_2_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.put(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.startsWith("--" + boundary));
  }
  }

  @Test
  public void testMultiPartPut_3_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.put(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.trim().endsWith("--" + boundary + "--"));
  }
  }

  @Test
  public void testMultiPartPut_4_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.put(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.contains("Content-Disposition:"));
  }
  }

  @Test
  public void testMultiPartPut_5_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.put(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.contains("Content-Type: application/test"));
  }
  }

  @Test
  public void testMultiPartPut_6_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.put(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.contains("name=\"baPart"));
  }
  }

  @Test
  public void testMultiPartPut_7_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.put(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.contains("filename=\"fileName"));
  }
  }

  @Test
  public void testMultiPartPost_1_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.post(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");

      assertTrue(contentType.contains("multipart/form-data"));
  }
  }

  @Test
  public void testMultiPartPost_2_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.post(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.startsWith("--" + boundary));
  }
  }

  @Test
  public void testMultiPartPost_3_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.post(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.trim().endsWith("--" + boundary + "--"));
  }
  }

  @Test
  public void testMultiPartPost_4_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.post(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.contains("Content-Disposition:"));
  }
  }

  @Test
  public void testMultiPartPost_5_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.post(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.contains("Content-Type: application/test"));
  }
  }

  @Test
  public void testMultiPartPost_6_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.post(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.contains("name=\"baPart"));
  }
  }

  @Test
  public void testMultiPartPost_7_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder().setUrl(getTargetUrl() + "/multipart").build()) {
      Response response = client.post(new ByteArrayPart("baPart", "testMultiPart".getBytes(UTF_8), "application/test", UTF_8, "fileName")).get();

      String body = response.getResponseBody();
      String contentType = response.getHeader("X-Content-Type");


      String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);

      assertTrue(body.contains("filename=\"fileName"));
  }
  }

}
