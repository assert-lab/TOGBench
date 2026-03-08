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

import org.asynchttpclient.AbstractBasicTest;
import org.asynchttpclient.Response;
import org.asynchttpclient.extras.simple.SimpleAsyncHttpClient.ErrorDocumentBehaviour;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Future;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Benjamin Hanzelmann
 */
public class SimpleAsyncClientErrorBehaviourTest_OE25Dev extends AbstractBasicTest {

  @Override
  public AbstractHandler configureHandler() {
    return new AbstractHandler() {

      public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(404);
        baseRequest.setHandled(true);
      }
    };
  }

  @Test
  public void testAccumulateErrorBody_1_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setUrl(getTargetUrl() + "/nonexistent")
            .setErrorDocumentBehaviour(ErrorDocumentBehaviour.ACCUMULATE).build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);
      Future<Response> future = client.get(new OutputStreamBodyConsumer(o));

      System.out.println("waiting for response");
      Response response = future.get();
      assertEquals(response.getStatusCode(), 404);
  }
  }

  @Test
  public void testAccumulateErrorBody_2_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setUrl(getTargetUrl() + "/nonexistent")
            .setErrorDocumentBehaviour(ErrorDocumentBehaviour.ACCUMULATE).build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);
      Future<Response> future = client.get(new OutputStreamBodyConsumer(o));

      System.out.println("waiting for response");
      Response response = future.get();
      // removed other assertion
      assertEquals(o.toString(), "");
  }
  }

  @Test
  public void testAccumulateErrorBody_3_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setUrl(getTargetUrl() + "/nonexistent")
            .setErrorDocumentBehaviour(ErrorDocumentBehaviour.ACCUMULATE).build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);
      Future<Response> future = client.get(new OutputStreamBodyConsumer(o));

      System.out.println("waiting for response");
      Response response = future.get();
      // removed other assertion
      // removed other assertion
      assertTrue(response.getResponseBody().startsWith("<html>"));
  }
  }

  @Test
  public void testOmitErrorBody_1_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setUrl(getTargetUrl() + "/nonexistent")
            .setErrorDocumentBehaviour(ErrorDocumentBehaviour.OMIT).build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);
      Future<Response> future = client.get(new OutputStreamBodyConsumer(o));

      System.out.println("waiting for response");
      Response response = future.get();
      assertEquals(response.getStatusCode(), 404);
  }
  }

  @Test
  public void testOmitErrorBody_2_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setUrl(getTargetUrl() + "/nonexistent")
            .setErrorDocumentBehaviour(ErrorDocumentBehaviour.OMIT).build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);
      Future<Response> future = client.get(new OutputStreamBodyConsumer(o));

      System.out.println("waiting for response");
      Response response = future.get();
      // removed other assertion
      assertEquals(o.toString(), "");
  }
  }

  @Test
  public void testOmitErrorBody_3_oe() throws Exception {
    try (SimpleAsyncHttpClient client = new SimpleAsyncHttpClient.Builder()
            .setUrl(getTargetUrl() + "/nonexistent")
            .setErrorDocumentBehaviour(ErrorDocumentBehaviour.OMIT).build()) {
      ByteArrayOutputStream o = new ByteArrayOutputStream(10);
      Future<Response> future = client.get(new OutputStreamBodyConsumer(o));

      System.out.println("waiting for response");
      Response response = future.get();
      // removed other assertion
      // removed other assertion
      assertEquals(response.getResponseBody(), "");
  }
  }

}
