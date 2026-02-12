/*
 * Copyright (c) 2014 Kevin Sawicki <kevinsawicki@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
package com.github.kevinsawicki.http;

import static com.github.kevinsawicki.http.HttpRequest.CHARSET_UTF8;
import static com.github.kevinsawicki.http.HttpRequest.delete;
import static com.github.kevinsawicki.http.HttpRequest.encode;
import static com.github.kevinsawicki.http.HttpRequest.get;
import static com.github.kevinsawicki.http.HttpRequest.head;
import static com.github.kevinsawicki.http.HttpRequest.options;
import static com.github.kevinsawicki.http.HttpRequest.post;
import static com.github.kevinsawicki.http.HttpRequest.put;
import static com.github.kevinsawicki.http.HttpRequest.trace;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static java.net.HttpURLConnection.HTTP_NOT_MODIFIED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;
import com.github.kevinsawicki.http.HttpRequest.ConnectionFactory;
import com.github.kevinsawicki.http.HttpRequest.UploadProgress;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPOutputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.util.B64Code;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests of {@link HttpRequest}
 */
public class HttpRequestTest_OE25Dev extends ServerTestCase {

  private static String url;

  private static RequestHandler handler;

  /**
   * Set up server
   *
   * @throws Exception
   */
  @BeforeClass
  public static void startServer() throws Exception {
    url = setUp(new RequestHandler() {

      @Override
      public void handle(String target, Request baseRequest,
          HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException {
        if (handler != null)
          handler.handle(target, baseRequest, request, response);
      }

      @Override
      public void handle(Request request, HttpServletResponse response) {
        if (handler != null)
          handler.handle(request, response);
      }
    });
  }

  /**
   * Clear handler
   */
  @After
  public void clearHandler() {
    handler = null;
  }

  /**
   * Create request with malformed URL
   */
  @Test(expected = HttpRequestException.class)
  public void malformedStringUrl() {
    get("\\m/");
  }

  /**
   * Create request with malformed URL
   */

  /**
   * Set request buffer size to negative value
   */
  @Test(expected = IllegalArgumentException.class)
  public void negativeBufferSize() {
    get("http://localhost").bufferSize(-1);
  }

  /**
   * Make a GET request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a GET request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a GET request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a GET request with a URL that needs encoding
   *
   * @throws Exception
   */

  /**
   * Make a GET request with a URL that needs encoding
   *
   * @throws Exception
   */

  /**
   * Make a GET request with a URL that needs encoding
   *
   * @throws Exception
   */

  /**
   * Make a DELETE request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a DELETE request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make an OPTIONS request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make an OPTIONS request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a HEAD request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a HEAD request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a PUT request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a PUT request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a PUT request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a TRACE request with an empty body response
   *
   * @throws Exception
   */

  /**
   * Make a POST request with an empty request body
   *
   * @throws Exception
   */

  /**
   * Make a POST request with an empty request body
   *
   * @throws Exception
   */

  /**
   * Make a POST request with a non-empty request body
   *
   * @throws Exception
   */

  /**
   * Make a POST request with a non-empty request body
   *
   * @throws Exception
   */

  /**
   * Make a POST request with multiple files in the body
   *
   * @throws Exception
   */

  /**
   * Make a POST request with a non-empty request body
   *
   * @throws Exception
   */

  /**
   * Make a POST request with a non-empty request body
   *
   * @throws Exception
   */

  /**
   * Make a post with an explicit set of the content length
   *
   * @throws Exception
   */

  /**
   * Make a post of form data
   *
   * @throws Exception
   */

  /**
   * Make a post of form data
   *
   * @throws Exception
   */

  /**
   * Make a post with an empty form data map
   *
   * @throws Exception
   */

  /**
   * Make a post in chunked mode
   *
   * @throws Exception
   */

  /**
   * Make a GET request for a non-empty response body
   *
   * @throws Exception
   */

  /**
   * Make a GET request with a response that includes a charset parameter
   *
   * @throws Exception
   */

  /**
   * Make a GET request with a response that includes a charset parameter
   *
   * @throws Exception
   */

  /**
   * Make a GET request with basic authentication specified
   *
   * @throws Exception
   */

  /**
   * Make a GET request with basic proxy authentication specified
   *
   * @throws Exception
   */

  /**
   * Make a GET and get response as a input stream reader
   *
   * @throws Exception
   */

  /**
   * Make a POST and send request using a writer
   *
   * @throws Exception
   */

  /**
   * Make a GET and get response as a buffered reader
   *
   * @throws Exception
   */

  /**
   * Make a GET and get response as a input stream reader
   *
   * @throws Exception
   */

  /**
   * Make a GET and get response body as byte array
   *
   * @throws Exception
   */

  /**
   * Make a GET request that returns an error string
   *
   * @throws Exception
   */

  /**
   * Make a GET request that returns an empty error string
   *
   * @throws Exception
   */

  /**
   * Verify 'Server' header
   *
   * @throws Exception
   */

  /**
   * Verify 'Expires' header
   *
   * @throws Exception
   */

  /**
   * Verify 'Last-Modified' header
   *
   * @throws Exception
   */

  /**
   * Verify 'Date' header
   *
   * @throws Exception
   */

  /**
   * Verify 'ETag' header
   *
   * @throws Exception
   */

  /**
   * Verify 'Location' header
   *
   * @throws Exception
   */

  /**
   * Verify 'Content-Encoding' header
   *
   * @throws Exception
   */

  /**
   * Verify 'Content-Type' header
   *
   * @throws Exception
   */

  /**
   * Verify 'Content-Type' header
   *
   * @throws Exception
   */

  /**
   * Verify 'Content-Type' header
   *
   * @throws Exception
   */

  /**
   * Verify 'Content-Type' header
   *
   * @throws Exception
   */

  /**
   * Verify 'Cache-Control' header
   *
   * @throws Exception
   */

  /**
   * Verify setting headers
   *
   * @throws Exception
   */

  /**
   * Verify setting headers
   *
   * @throws Exception
   */

  /**
   * Verify getting all headers
   *
   * @throws Exception
   */

  /**
   * Verify setting number header
   *
   * @throws Exception
   */

  /**
   * Verify 'User-Agent' request header
   *
   * @throws Exception
   */

  /**
   * Verify 'Accept' request header
   *
   * @throws Exception
   */

  /**
   * Verify 'Accept' request header when calling
   * {@link HttpRequest#acceptJson()}
   *
   * @throws Exception
   */

  /**
   * Verify 'If-None-Match' request header
   *
   * @throws Exception
   */

  /**
   * Verify 'Accept-Charset' request header
   *
   * @throws Exception
   */

  /**
   * Verify 'Accept-Encoding' request header
   *
   * @throws Exception
   */

  /**
   * Verify 'If-Modified-Since' request header
   *
   * @throws Exception
   */

  /**
   * Verify 'Referer' header
   *
   * @throws Exception
   */

  /**
   * Verify multipart with file, stream, number, and string parameters
   *
   * @throws Exception
   */

  /**
   * Verify multipart with content type part header
   *
   * @throws Exception
   */

  /**
   * Verify response in {@link Appendable}
   *
   * @throws Exception
   */

  /**
   * Verify response in {@link Writer}
   *
   * @throws Exception
   */

  /**
   * Verify response via a {@link PrintStream}
   *
   * @throws Exception
   */

  /**
   * Verify response in {@link File}
   *
   * @throws Exception
   */

  /**
   * Verify certificate and host helpers on HTTPS connection
   *
   * @throws Exception
   */

  /**
   * Verify certificate and host helpers ignore non-HTTPS connection
   *
   * @throws Exception
   */

  /**
   * Verify hostname verifier is set and accepts all
   */

  /**
   * Verify single hostname verifier is created across all calls
   */

  /**
   * Verify single SSL socket factory is created across all calls
   */

  /**
   * Send a stream that throws an exception when read from
   *
   * @throws Exception
   */
  @Test
  public void sendErrorReadStream() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        try {
          response.getWriter().print("content");
        } catch (IOException e) {
          fail();
        }
      }
    };
    final IOException readCause = new IOException();
    final IOException closeCause = new IOException();
    InputStream stream = new InputStream() {

      public int read() throws IOException {
        throw readCause;
      }

      public void close() throws IOException {
        throw closeCause;
      }
    };
    try {
      post(url).send(stream);
      fail("Exception not thrown");
    } catch (HttpRequestException e) {
      assertEquals(readCause, e.getCause());
    }
  }

  /**
   * Send a stream that throws an exception when read from
   *
   * @throws Exception
   */

  /**
   * Make a GET request and get the code using an {@link AtomicInteger}
   *
   * @throws Exception
   */

  /**
   * Make a GET request and get the body using an {@link AtomicReference}
   *
   * @throws Exception
   */

  /**
   * Make a GET request and get the body using an {@link AtomicReference}
   *
   * @throws Exception
   */


  /**
   * Make a GET request that should be compressed
   *
   * @throws Exception
   */

  /**
   * Make a GET request that should be compressed but isn't
   *
   * @throws Exception
   */

  /**
   * Get header with multiple response values
   *
   * @throws Exception
   */

  /**
   * Get header values when not set in response
   *
   * @throws Exception
   */

  /**
   * Get header parameter value
   *
   * @throws Exception
   */

  /**
   * Get header parameter value
   *
   * @throws Exception
   */

  /**
   * Get header parameter value
   *
   * @throws Exception
   */

  /**
   * Get header parameter value
   *
   * @throws Exception
   */

  /**
   * Get header parameter value
   *
   * @throws Exception
   */

  /**
   * Get header parameter value
   *
   * @throws Exception
   */

  /**
   * Get header parameter value
   *
   * @throws Exception
   */

  /**
   * Get header parameter value
   *
   * @throws Exception
   */

  /**
   * Get header parameter values
   *
   * @throws Exception
   */

  /**
   * Get header parameter values
   *
   * @throws Exception
   */

  /**
   * Get header parameter values
   *
   * @throws Exception
   */

  /**
   * Verify getting date header with default value
   *
   * @throws Exception
   */

  /**
   * Verify getting date header with default value
   *
   * @throws Exception
   */

  /**
   * Verify getting int header with default value
   *
   * @throws Exception
   */

  /**
   * Verify getting int header with default value
   *
   * @throws Exception
   */

  /**
   * Verify sending form data as a sequence of {@link Entry} objects
   *
   * @throws Exception
   */

  /**
   * Verify sending form data where entry value is null
   *
   * @throws Exception
   */

  /**
   * Verify POST with query parameters
   *
   * @throws Exception
   */

  /**
   * Verify POST with query parameters
   *
   * @throws Exception
   */

  /**
   * Verify POST with escaped query parameters
   *
   * @throws Exception
   */

  /**
   * Verify POST with escaped query parameters
   *
   * @throws Exception
   */

  /**
   * Verify POST with numeric query parameters
   *
   * @throws Exception
   */

  /**
   * Verify GET with query parameters
   *
   * @throws Exception
   */

  /**
   * Verify GET with query parameters
   *
   * @throws Exception
   */

  /**
   * Verify GET with escaped query parameters
   *
   * @throws Exception
   */

  /**
   * Verify GET with escaped query parameters
   *
   * @throws Exception
   */

  /**
   * Verify DELETE with query parameters
   *
   * @throws Exception
   */

  /**
   * Verify DELETE with query parameters
   *
   * @throws Exception
   */

  /**
   * Verify DELETE with escaped query parameters
   *
   * @throws Exception
   */

  /**
   * Verify DELETE with escaped query parameters
   *
   * @throws Exception
   */

  /**
   * Verify PUT with query parameters
   *
   * @throws Exception
   */

  /**
   * Verify PUT with query parameters
   *
   * @throws Exception
   */

  /**
   * Verify PUT with escaped query parameters
   *
   * @throws Exception
   */

  /**
   * Verify PUT with escaped query parameters
   *
   * @throws Exception
   */

  /**
   * Verify HEAD with query parameters
   *
   * @throws Exception
   */

  /**
   * Verify HEAD with query parameters
   *
   * @throws Exception
   */

  /**
   * Verify HEAD with escaped query parameters
   *
   * @throws Exception
   */

  /**
   * Verify HEAD with escaped query parameters
   *
   * @throws Exception
   */

  /**
   * Append with base URL with no path
   *
   * @throws Exception
   */

  /**
   * Append with base URL with no path
   *
   * @throws Exception
   */

  /**
   * Append with base URL with path
   *
   * @throws Exception
   */

  /**
   * Append with base URL with path
   *
   * @throws Exception
   */

  /**
   * Append multiple params
   *
   * @throws Exception
   */

  /**
   * Append multiple params
   *
   * @throws Exception
   */

  /**
   * Append null params
   *
   * @throws Exception
   */

  /**
   * Append null params
   *
   * @throws Exception
   */

  /**
   * Append empty params
   *
   * @throws Exception
   */

  /**
   * Append empty params
   *
   * @throws Exception
   */

  /**
   * Append params with null values
   *
   * @throws Exception
   */

  /**
   * Append params with null values
   *
   * @throws Exception
   */

  /**
   * Try to append with wrong number of arguments
   */
  @Test(expected = IllegalArgumentException.class)
  public void appendOddNumberOfParams() {
    HttpRequest.append("http://test.com", "1");
  }

  /**
   * Append with base URL already containing a '?'
   */

  /**
   * Append with base URL already containing a '?'
   */

  /**
   * Append with base URL already containing a '?'
   */

  /**
   * Append with base URL already containing a '?'
   */

  /**
   * Append array parameter
   *
   * @throws Exception
   */

  /**
   * Append list parameter
   *
   * @throws Exception
   */

  /**
   * Get a 500
   *
   * @throws Exception
   */

  /**
   * Get a 400
   *
   * @throws Exception
   */

  /**
   * Get a 304
   *
   * @throws Exception
   */

  /**
   * Verify data is sent when receiving response without first calling
   * {@link HttpRequest#code()}
   *
   * @throws Exception
   */

  /**
   * Verify data is send when receiving response headers without first calling
   * {@link HttpRequest#code()}
   *
   * @throws Exception
   */

  /**
   * Verify data is send when receiving response date header without first
   * calling {@link HttpRequest#code()}
   *
   * @throws Exception
   */

  /**
   * Verify data is send when receiving response integer header without first
   * calling {@link HttpRequest#code()}
   *
   * @throws Exception
   */

  /**
   * Verify custom connection factory
   */

  /**
   * Verify setting a null connection factory restores to the default one
   */

  /**
   * Verify reading response body for empty 200
   *
   * @throws Exception
   */

  /**
   * Verify reading response body for empty 400
   *
   * @throws Exception
   */

  /**
   * Verify reading response body for non-empty 400
   *
   * @throws Exception
   */

  /**
   * Verify progress callback when sending a file
   *
   * @throws Exception
   */
  @Test
  public void uploadProgressSend() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    final File file = File.createTempFile("post", ".txt");
    new FileWriter(file).append("hello").close();

    final AtomicLong tx = new AtomicLong(0);
    UploadProgress progress = new UploadProgress() {
      public void onUpload(long transferred, long total) {
        assertEquals(file.length(), total);
        assertEquals(tx.incrementAndGet(), transferred);
      }
    };
    post(url).bufferSize(1).progress(progress).send(file).code();
    assertEquals(file.length(), tx.get());
  }

  /**
   * Verify progress callback when sending from an InputStream
   *
   * @throws Exception
   */
  @Test
  public void uploadProgressSendInputStream() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    File file = File.createTempFile("post", ".txt");
    new FileWriter(file).append("hello").close();
    InputStream input = new FileInputStream(file);
    final AtomicLong tx = new AtomicLong(0);
    UploadProgress progress = new UploadProgress() {
      public void onUpload(long transferred, long total) {
        assertEquals(-1, total);
        assertEquals(tx.incrementAndGet(), transferred);
      }
    };
    post(url).bufferSize(1).progress(progress).send(input).code();
    assertEquals(file.length(), tx.get());
  }

  /**
   * Verify progress callback when sending from a byte array
   *
   * @throws Exception
   */
  @Test
  public void uploadProgressSendByteArray() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };

    final byte[] bytes = "hello".getBytes(CHARSET_UTF8);
    final AtomicLong tx = new AtomicLong(0);
    UploadProgress progress = new UploadProgress() {
      public void onUpload(long transferred, long total) {
        assertEquals(bytes.length, total);
        assertEquals(tx.incrementAndGet(), transferred);
      }
    };
    post(url).bufferSize(1).progress(progress).send(bytes).code();
    assertEquals(bytes.length, tx.get());
  }

  /**
   * Verify progress callback when sending from a Reader
   *
   * @throws Exception
   */
  @Test
  public void uploadProgressSendReader() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };

    final AtomicLong tx = new AtomicLong(0);
    UploadProgress progress = new UploadProgress() {
      public void onUpload(long transferred, long total) {
        assertEquals(-1, total);
        assertEquals(tx.incrementAndGet(), transferred);
      }
    };
    File file = File.createTempFile("post", ".txt");
    new FileWriter(file).append("hello").close();
    post(url).progress(progress).bufferSize(1).send(new FileReader(file)).code();
    assertEquals(file.length(), tx.get());
  }

  /**
   * Verify progress callback doesn't cause an exception when it's null
   *
   * @throws Exception
   */

  @Test
  public void malformedStringUrlCause_2_oe() {
    try {
      delete("\\m/");
      // removed other assertion
    } catch (HttpRequestException e) {
      assertNotNull(e.getCause());
  }
  }

  @Test
  public void getEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    assertNotNull(request.getConnection());
  }

  @Test
  public void getEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertEquals(30000,request.readTimeout(30000).getConnection().getReadTimeout());
  }

  @Test
  public void getEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    assertEquals(50000,request.connectTimeout(50000).getConnection().getConnectTimeout());
  }

  @Test
  public void getEmpty_4_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals(2500, request.bufferSize(2500).bufferSize());
  }

  @Test
  public void getEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.ignoreCloseExceptions(false).ignoreCloseExceptions());
  }

  @Test
  public void getEmpty_6_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.useCaches(false).getConnection().getUseCaches());
  }

  @Test
  public void getEmpty_7_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    assertTrue(request.ok());
  }

  @Test
  public void getEmpty_8_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    assertFalse(request.created());
  }

  @Test
  public void getEmpty_9_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    assertFalse(request.badRequest());
  }

  @Test
  public void getEmpty_10_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.serverError());
  }

  @Test
  public void getEmpty_11_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void getEmpty_12_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.notModified());
  }

  @Test
  public void getEmpty_13_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("GET", method.get());
  }

  @Test
  public void getEmpty_14_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("OK", request.message());
  }

  @Test
  public void getEmpty_15_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void getEmpty_16_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void getEmpty_17_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertNotNull(request.toString());
  }

  @Test
  public void getEmpty_18_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.toString().length() == 0);
  }

  @Test
  public void getEmpty_19_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals(request, request.disconnect());
  }

  @Test
  public void getEmpty_20_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertTrue(request.isBodyEmpty());
  }

  @Test
  public void getEmpty_21_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals(request.url().toString(), url);
  }

  @Test
  public void getEmpty_22_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("GET", request.method());
  }

  @Test
  public void getUrlEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    assertNotNull(request.getConnection());
  }

  @Test
  public void getUrlEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    assertTrue(request.ok());
  }

  @Test
  public void getUrlEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    assertFalse(request.created());
  }

  @Test
  public void getUrlEmpty_4_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    assertFalse(request.noContent());
  }

  @Test
  public void getUrlEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.badRequest());
  }

  @Test
  public void getUrlEmpty_6_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.serverError());
  }

  @Test
  public void getUrlEmpty_7_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void getUrlEmpty_8_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("GET", method.get());
  }

  @Test
  public void getUrlEmpty_9_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("OK", request.message());
  }

  @Test
  public void getUrlEmpty_10_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void getUrlEmpty_11_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void getNoContent_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    assertNotNull(request.getConnection());
  }

  @Test
  public void getNoContent_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    assertFalse(request.ok());
  }

  @Test
  public void getNoContent_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    assertFalse(request.created());
  }

  @Test
  public void getNoContent_4_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    assertTrue(request.noContent());
  }

  @Test
  public void getNoContent_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.badRequest());
  }

  @Test
  public void getNoContent_6_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.serverError());
  }

  @Test
  public void getNoContent_7_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void getNoContent_8_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("GET", method.get());
  }

  @Test
  public void getNoContent_9_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("No Content", request.message());
  }

  @Test
  public void getNoContent_10_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals(HTTP_NO_CONTENT, code);
  }

  @Test
  public void getNoContent_11_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_NO_CONTENT);
      }
    };
    HttpRequest request = get(new URL(url));
    // removed other assertion
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void getUrlEncodedWithSpace_1_oe() throws Exception {
    String unencoded = "/a resource";
    final AtomicReference<String> path = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        path.set(request.getPathInfo());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(encode(url + unencoded));
    assertTrue(request.ok());
  }

  @Test
  public void getUrlEncodedWithUnicode_1_oe() throws Exception {
    String unencoded = "/\u00DF";
    final AtomicReference<String> path = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        path.set(request.getPathInfo());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(encode(url + unencoded));
    assertTrue(request.ok());
  }

  @Test
  public void getUrlEncodedWithPercent_1_oe() throws Exception {
    String unencoded = "/%";
    final AtomicReference<String> path = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        path.set(request.getPathInfo());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(encode(url + unencoded));
    assertTrue(request.ok());
  }

  @Test
  public void getUrlEncodedWithPercent_2_oe() throws Exception {
    String unencoded = "/%";
    final AtomicReference<String> path = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        path.set(request.getPathInfo());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(encode(url + unencoded));
    // removed other assertion
    assertEquals(unencoded, path.get());
  }

  @Test
  public void deleteEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url);
    assertNotNull(request.getConnection());
  }

  @Test
  public void deleteEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url);
    // removed other assertion
    assertTrue(request.ok());
  }

  @Test
  public void deleteEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url);
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void deleteEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void deleteEmpty_6_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("DELETE", request.method());
  }

  @Test
  public void deleteUrlEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(new URL(url));
    assertNotNull(request.getConnection());
  }

  @Test
  public void deleteUrlEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(new URL(url));
    // removed other assertion
    assertTrue(request.ok());
  }

  @Test
  public void deleteUrlEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(new URL(url));
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void deleteUrlEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(new URL(url));
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void optionsEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = options(url);
    assertNotNull(request.getConnection());
  }

  @Test
  public void optionsEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = options(url);
    // removed other assertion
    assertTrue(request.ok());
  }

  @Test
  public void optionsEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = options(url);
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void optionsEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = options(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void optionsUrlEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = options(new URL(url));
    assertNotNull(request.getConnection());
  }

  @Test
  public void optionsUrlEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = options(new URL(url));
    // removed other assertion
    assertTrue(request.ok());
  }

  @Test
  public void optionsUrlEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = options(new URL(url));
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void optionsUrlEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = options(new URL(url));
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void headEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url);
    assertNotNull(request.getConnection());
  }

  @Test
  public void headEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url);
    // removed other assertion
    assertTrue(request.ok());
  }

  @Test
  public void headEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url);
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void headEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void headUrlEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(new URL(url));
    assertNotNull(request.getConnection());
  }

  @Test
  public void headUrlEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(new URL(url));
    // removed other assertion
    assertTrue(request.ok());
  }

  @Test
  public void headUrlEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(new URL(url));
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void headUrlEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(new URL(url));
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void putEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url);
    assertNotNull(request.getConnection());
  }

  @Test
  public void putEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url);
    // removed other assertion
    assertTrue(request.ok());
  }

  @Test
  public void putEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url);
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void putEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void putUrlEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(new URL(url));
    assertNotNull(request.getConnection());
  }

  @Test
  public void putUrlEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(new URL(url));
    // removed other assertion
    assertTrue(request.ok());
  }

  @Test
  public void putUrlEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(new URL(url));
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void putUrlEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(new URL(url));
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void traceEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = trace(url);
    assertNotNull(request.getConnection());
  }

  @Test
  public void traceEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = trace(url);
    // removed other assertion
    assertTrue(request.ok());
  }

  @Test
  public void traceEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = trace(url);
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void traceEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = trace(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void traceUrlEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = trace(new URL(url));
    assertNotNull(request.getConnection());
  }

  @Test
  public void traceUrlEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = trace(new URL(url));
    // removed other assertion
    assertTrue(request.ok());
  }

  @Test
  public void traceUrlEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = trace(new URL(url));
    // removed other assertion
    // removed other assertion
    assertFalse(request.notFound());
  }

  @Test
  public void traceUrlEmpty_5_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = trace(new URL(url));
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void postEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_CREATED);
      }
    };
    HttpRequest request = post(url);
    int code = request.code();
    assertEquals("POST", method.get());
  }

  @Test
  public void postEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_CREATED);
      }
    };
    HttpRequest request = post(url);
    int code = request.code();
    // removed other assertion
    assertFalse(request.ok());
  }

  @Test
  public void postEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_CREATED);
      }
    };
    HttpRequest request = post(url);
    int code = request.code();
    // removed other assertion
    // removed other assertion
    assertTrue(request.created());
  }

  @Test
  public void postEmpty_4_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_CREATED);
      }
    };
    HttpRequest request = post(url);
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals(HTTP_CREATED, code);
  }

  @Test
  public void postUrlEmpty_1_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_CREATED);
      }
    };
    HttpRequest request = post(new URL(url));
    int code = request.code();
    assertEquals("POST", method.get());
  }

  @Test
  public void postUrlEmpty_2_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_CREATED);
      }
    };
    HttpRequest request = post(new URL(url));
    int code = request.code();
    // removed other assertion
    assertFalse(request.ok());
  }

  @Test
  public void postUrlEmpty_3_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_CREATED);
      }
    };
    HttpRequest request = post(new URL(url));
    int code = request.code();
    // removed other assertion
    // removed other assertion
    assertTrue(request.created());
  }

  @Test
  public void postUrlEmpty_4_oe() throws Exception {
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        response.setStatus(HTTP_CREATED);
      }
    };
    HttpRequest request = post(new URL(url));
    int code = request.code();
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals(HTTP_CREATED, code);
  }

  @Test
  public void postNonEmptyString_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    int code = post(url).send("hello").code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postNonEmptyString_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    int code = post(url).send("hello").code();
    // removed other assertion
    assertEquals("hello", body.get());
  }

  @Test
  public void postNonEmptyFile_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    File file = File.createTempFile("post", ".txt");
    new FileWriter(file).append("hello").close();
    int code = post(url).send(file).code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postNonEmptyFile_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    File file = File.createTempFile("post", ".txt");
    new FileWriter(file).append("hello").close();
    int code = post(url).send(file).code();
    // removed other assertion
    assertEquals("hello", body.get());
  }

  @Test
  public void postMultipleFiles_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };

    File file1 = File.createTempFile("post", ".txt");
    new FileWriter(file1).append("hello").close();

    File file2 = File.createTempFile("post", ".txt");
    new FileWriter(file2).append(" world").close();

    int code = post(url).send(file1).send(file2).code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postMultipleFiles_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };

    File file1 = File.createTempFile("post", ".txt");
    new FileWriter(file1).append("hello").close();

    File file2 = File.createTempFile("post", ".txt");
    new FileWriter(file2).append(" world").close();

    int code = post(url).send(file1).send(file2).code();
    // removed other assertion
    assertEquals("hello world", body.get());
  }

  @Test
  public void postNonEmptyReader_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    File file = File.createTempFile("post", ".txt");
    new FileWriter(file).append("hello").close();
    int code = post(url).send(new FileReader(file)).code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postNonEmptyReader_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    File file = File.createTempFile("post", ".txt");
    new FileWriter(file).append("hello").close();
    int code = post(url).send(new FileReader(file)).code();
    // removed other assertion
    assertEquals("hello", body.get());
  }

  @Test
  public void postNonEmptyByteArray_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    byte[] bytes = "hello".getBytes(CHARSET_UTF8);
    int code = post(url).contentLength(Integer.toString(bytes.length))
        .send(bytes).code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postNonEmptyByteArray_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    byte[] bytes = "hello".getBytes(CHARSET_UTF8);
    int code = post(url).contentLength(Integer.toString(bytes.length))
        .send(bytes).code();
    // removed other assertion
    assertEquals("hello", body.get());
  }

  @Test
  public void postWithLength_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<Integer> length = new AtomicReference<Integer>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        length.set(request.getContentLength());
        response.setStatus(HTTP_OK);
      }
    };
    String data = "hello";
    int sent = data.getBytes().length;
    int code = post(url).contentLength(sent).send(data).code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postWithLength_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<Integer> length = new AtomicReference<Integer>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        length.set(request.getContentLength());
        response.setStatus(HTTP_OK);
      }
    };
    String data = "hello";
    int sent = data.getBytes().length;
    int code = post(url).contentLength(sent).send(data).code();
    // removed other assertion
    assertEquals(sent, length.get().intValue());
  }

  @Test
  public void postWithLength_3_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<Integer> length = new AtomicReference<Integer>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        length.set(request.getContentLength());
        response.setStatus(HTTP_OK);
      }
    };
    String data = "hello";
    int sent = data.getBytes().length;
    int code = post(url).contentLength(sent).send(data).code();
    // removed other assertion
    // removed other assertion
    assertEquals(data, body.get());
  }

  @Test
  public void postForm_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<String> contentType = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        contentType.set(request.getContentType());
        response.setStatus(HTTP_OK);
      }
    };
    Map<String, String> data = new LinkedHashMap<String, String>();
    data.put("name", "user");
    data.put("number", "100");
    int code = post(url).form(data).form("zip", "12345").code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postForm_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<String> contentType = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        contentType.set(request.getContentType());
        response.setStatus(HTTP_OK);
      }
    };
    Map<String, String> data = new LinkedHashMap<String, String>();
    data.put("name", "user");
    data.put("number", "100");
    int code = post(url).form(data).form("zip", "12345").code();
    // removed other assertion
    assertEquals("name=user&number=100&zip=12345", body.get());
  }

  @Test
  public void postFormWithNoCharset_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<String> contentType = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        contentType.set(request.getContentType());
        response.setStatus(HTTP_OK);
      }
    };
    Map<String, String> data = new LinkedHashMap<String, String>();
    data.put("name", "user");
    data.put("number", "100");
    int code = post(url).form(data, null).form("zip", "12345").code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postFormWithNoCharset_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<String> contentType = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        contentType.set(request.getContentType());
        response.setStatus(HTTP_OK);
      }
    };
    Map<String, String> data = new LinkedHashMap<String, String>();
    data.put("name", "user");
    data.put("number", "100");
    int code = post(url).form(data, null).form("zip", "12345").code();
    // removed other assertion
    assertEquals("name=user&number=100&zip=12345", body.get());
  }

  @Test
  public void postFormWithNoCharset_3_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<String> contentType = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        contentType.set(request.getContentType());
        response.setStatus(HTTP_OK);
      }
    };
    Map<String, String> data = new LinkedHashMap<String, String>();
    data.put("name", "user");
    data.put("number", "100");
    int code = post(url).form(data, null).form("zip", "12345").code();
    // removed other assertion
    // removed other assertion
    assertEquals("application/x-www-form-urlencoded", contentType.get());
  }

  @Test
  public void postEmptyForm_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    int code = post(url).form(new HashMap<String, String>()).code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postEmptyForm_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    int code = post(url).form(new HashMap<String, String>()).code();
    // removed other assertion
    assertEquals("", body.get());
  }

  @Test
  public void chunkPost_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<String> encoding = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
        encoding.set(request.getHeader("Transfer-Encoding"));
      }
    };
    String data = "hello";
    int code = post(url).chunk(2).send(data).code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void chunkPost_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<String> encoding = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
        encoding.set(request.getHeader("Transfer-Encoding"));
      }
    };
    String data = "hello";
    int code = post(url).chunk(2).send(data).code();
    // removed other assertion
    assertEquals(data, body.get());
  }

  @Test
  public void chunkPost_3_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    final AtomicReference<String> encoding = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
        encoding.set(request.getHeader("Transfer-Encoding"));
      }
    };
    String data = "hello";
    int code = post(url).chunk(2).send(data).code();
    // removed other assertion
    // removed other assertion
    assertEquals("chunked", encoding.get());
  }

  @Test
  public void getNonEmptyString_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    assertEquals(HTTP_OK, request.code());
  }

  @Test
  public void getNonEmptyString_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertEquals("hello", request.body());
  }

  @Test
  public void getNonEmptyString_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    assertEquals("hello".getBytes().length, request.contentLength());
  }

  @Test
  public void getNonEmptyString_4_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertFalse(request.isBodyEmpty());
  }

  @Test
  public void getWithResponseCharset_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setContentType("text/html; charset=UTF-8");
      }
    };
    HttpRequest request = get(url);
    assertEquals(HTTP_OK, request.code());
  }

  @Test
  public void getWithResponseCharset_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setContentType("text/html; charset=UTF-8");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertEquals(CHARSET_UTF8, request.charset());
  }

  @Test
  public void getWithResponseCharsetAsSecondParam_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setContentType("text/html; param1=val1; charset=UTF-8");
      }
    };
    HttpRequest request = get(url);
    assertEquals(HTTP_OK, request.code());
  }

  @Test
  public void getWithResponseCharsetAsSecondParam_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setContentType("text/html; param1=val1; charset=UTF-8");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertEquals(CHARSET_UTF8, request.charset());
  }

  @Test
  public void basicAuthentication_1_oe() throws Exception {
    final AtomicReference<String> user = new AtomicReference<String>();
    final AtomicReference<String> password = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        String auth = request.getHeader("Authorization");
        auth = auth.substring(auth.indexOf(' ') + 1);
        try {
          auth = B64Code.decode(auth, CHARSET_UTF8);
        } catch (UnsupportedEncodingException e) {
          throw new RuntimeException(e);
        }
        int colon = auth.indexOf(':');
        user.set(auth.substring(0, colon));
        password.set(auth.substring(colon + 1));
        response.setStatus(HTTP_OK);
      }
    };
    assertTrue(get(url).basic("user", "p4ssw0rd").ok());
  }

  @Test
  public void basicProxyAuthentication_1_oe() throws Exception {
    final AtomicBoolean finalHostReached = new AtomicBoolean(false);
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        finalHostReached.set(true);
        response.setStatus(HTTP_OK);
      }
    };
    assertTrue(get(url).useProxy("localhost", proxyPort).proxyBasic("user", "p4ssw0rd").ok());
  }

  @Test
  public void basicProxyAuthentication_2_oe() throws Exception {
    final AtomicBoolean finalHostReached = new AtomicBoolean(false);
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        finalHostReached.set(true);
        response.setStatus(HTTP_OK);
      }
    };
    // removed other assertion
    assertEquals("user", proxyUser.get());
  }

  @Test
  public void basicProxyAuthentication_3_oe() throws Exception {
    final AtomicBoolean finalHostReached = new AtomicBoolean(false);
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        finalHostReached.set(true);
        response.setStatus(HTTP_OK);
      }
    };
    // removed other assertion
    // removed other assertion
    assertEquals("p4ssw0rd", proxyPassword.get());
  }

  @Test
  public void getReader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getReader_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    BufferedReader reader = new BufferedReader(request.reader());
    assertEquals("hello", reader.readLine());
  }

  @Test
  public void sendWithWriter_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };

    HttpRequest request = post(url);
    request.writer().append("hello").close();
    assertTrue(request.ok());
  }

  @Test
  public void getBufferedReader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getBufferedReader_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    BufferedReader reader = request.bufferedReader();
    assertEquals("hello", reader.readLine());
  }

  @Test
  public void getReaderWithCharset_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getReaderWithCharset_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    BufferedReader reader = new BufferedReader(request.reader(CHARSET_UTF8));
    assertEquals("hello", reader.readLine());
  }

  @Test
  public void getBytes_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getBytes_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        write("hello");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertTrue(Arrays.equals("hello".getBytes(), request.bytes()));
  }

  @Test
  public void getError_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        write("error");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.notFound());
  }

  @Test
  public void getError_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        write("error");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertEquals("error", request.body());
  }

  @Test
  public void noError_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void noError_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertEquals("", request.body());
  }

  @Test
  public void serverHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("Server", "aserver");
      }
    };
    assertEquals("aserver", get(url).server());
  }

  @Test
  public void expiresHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setDateHeader("Expires", 1234000);
      }
    };
    assertEquals(1234000, get(url).expires());
  }

  @Test
  public void lastModifiedHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setDateHeader("Last-Modified", 555000);
      }
    };
    assertEquals(555000, get(url).lastModified());
  }

  @Test
  public void dateHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setDateHeader("Date", 66000);
      }
    };
    assertEquals(66000, get(url).date());
  }

  @Test
  public void eTagHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("ETag", "abcd");
      }
    };
    assertEquals("abcd", get(url).eTag());
  }

  @Test
  public void locationHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("Location", "http://nowhere");
      }
    };
    assertEquals("http://nowhere", get(url).location());
  }

  @Test
  public void contentEncodingHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("Content-Encoding", "gzip");
      }
    };
    assertEquals("gzip", get(url).contentEncoding());
  }

  @Test
  public void contentTypeHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("Content-Type", "text/html");
      }
    };
    assertEquals("text/html", get(url).contentType());
  }

  @Test
  public void requestContentType_1_oe() throws Exception {
    final AtomicReference<String> contentType = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        contentType.set(request.getContentType());
        response.setStatus(HTTP_OK);
      }
    };
    assertTrue(post(url).contentType("text/html", "UTF-8").ok());
  }

  @Test
  public void requestContentTypeNullCharset_1_oe() throws Exception {
    final AtomicReference<String> contentType = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        contentType.set(request.getContentType());
        response.setStatus(HTTP_OK);
      }
    };
    assertTrue(post(url).contentType("text/html", null).ok());
  }

  @Test
  public void requestContentTypeEmptyCharset_1_oe() throws Exception {
    final AtomicReference<String> contentType = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        contentType.set(request.getContentType());
        response.setStatus(HTTP_OK);
      }
    };
    assertTrue(post(url).contentType("text/html", "").ok());
  }

  @Test
  public void cacheControlHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("Cache-Control", "no-cache");
      }
    };
    assertEquals("no-cache", get(url).cacheControl());
  }

  @Test
  public void headers_1_oe() throws Exception {
    final AtomicReference<String> h1 = new AtomicReference<String>();
    final AtomicReference<String> h2 = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        h1.set(request.getHeader("h1"));
        h2.set(request.getHeader("h2"));
      }
    };
    Map<String, String> headers = new HashMap<String, String>();
    headers.put("h1", "v1");
    headers.put("h2", "v2");
    assertTrue(get(url).headers(headers).ok());
  }

  @Test
  public void emptyHeaders_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };
    assertTrue(get(url).headers(Collections.<String, String> emptyMap()).ok());
  }

  @Test
  public void getAllHeaders_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "a");
        response.setHeader("b", "b");
        response.addHeader("a", "another");
      }
    };
    Map<String, List<String>> headers = get(url).headers();
    assertEquals(headers.size(), 5);
  }

  @Test
  public void getAllHeaders_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "a");
        response.setHeader("b", "b");
        response.addHeader("a", "another");
      }
    };
    Map<String, List<String>> headers = get(url).headers();
    // removed other assertion
    assertEquals(headers.get("a").size(), 2);
  }

  @Test
  public void getAllHeaders_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "a");
        response.setHeader("b", "b");
        response.addHeader("a", "another");
      }
    };
    Map<String, List<String>> headers = get(url).headers();
    // removed other assertion
    // removed other assertion
    assertTrue(headers.get("b").get(0).equals("b"));
  }

  @Test
  public void numberHeader_1_oe() throws Exception {
    final AtomicReference<String> h1 = new AtomicReference<String>();
    final AtomicReference<String> h2 = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        h1.set(request.getHeader("h1"));
        h2.set(request.getHeader("h2"));
      }
    };
    assertTrue(get(url).header("h1", 5).header("h2", (Number) null).ok());
  }

  @Test
  public void userAgentHeader_1_oe() throws Exception {
    final AtomicReference<String> header = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        header.set(request.getHeader("User-Agent"));
      }
    };
    assertTrue(get(url).userAgent("browser 1.0").ok());
  }

  @Test
  public void acceptHeader_1_oe() throws Exception {
    final AtomicReference<String> header = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        header.set(request.getHeader("Accept"));
      }
    };
    assertTrue(get(url).accept("application/json").ok());
  }

  @Test
  public void acceptJson_1_oe() throws Exception {
    final AtomicReference<String> header = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        header.set(request.getHeader("Accept"));
      }
    };
    assertTrue(get(url).acceptJson().ok());
  }

  @Test
  public void ifNoneMatchHeader_1_oe() throws Exception {
    final AtomicReference<String> header = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        header.set(request.getHeader("If-None-Match"));
      }
    };
    assertTrue(get(url).ifNoneMatch("eid").ok());
  }

  @Test
  public void acceptCharsetHeader_1_oe() throws Exception {
    final AtomicReference<String> header = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        header.set(request.getHeader("Accept-Charset"));
      }
    };
    assertTrue(get(url).acceptCharset(CHARSET_UTF8).ok());
  }

  @Test
  public void acceptEncodingHeader_1_oe() throws Exception {
    final AtomicReference<String> header = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        header.set(request.getHeader("Accept-Encoding"));
      }
    };
    assertTrue(get(url).acceptEncoding("compress").ok());
  }

  @Test
  public void ifModifiedSinceHeader_1_oe() throws Exception {
    final AtomicLong header = new AtomicLong();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        header.set(request.getDateHeader("If-Modified-Since"));
      }
    };
    assertTrue(get(url).ifModifiedSince(5000).ok());
  }

  @Test
  public void refererHeader_1_oe() throws Exception {
    final AtomicReference<String> referer = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        referer.set(request.getHeader("Referer"));
        response.setStatus(HTTP_OK);
      }
    };
    assertTrue(post(url).referer("http://heroku.com").ok());
  }

  @Test
  public void postMultipart_2_oe() throws Exception {
    final StringBuilder body = new StringBuilder();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        char[] buffer = new char[8192];
        int read;
        try {
          while ((read = request.getReader().read(buffer)) != -1)
            body.append(buffer, 0, read);
        } catch (IOException e) {
          // removed other assertion
        }
      }
    };
    File file = File.createTempFile("body", ".txt");
    File file2 = File.createTempFile("body", ".txt");
    new FileWriter(file).append("content1").close();
    new FileWriter(file2).append("content4").close();
    HttpRequest request = post(url);
    request.part("description", "content2");
    request.part("size", file.length());
    request.part("body", file.getName(), file);
    request.part("file", file2);
    request.part("stream", new ByteArrayInputStream("content3".getBytes()));
    assertTrue(request.ok());
  }

  @Test
  public void postMultipartWithContentType_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        body.set(new String(read()));
      }
    };
    HttpRequest request = post(url);
    request.part("body", null, "application/json", "contents");
    assertTrue(request.ok());
  }

  @Test
  public void receiveAppendable_2_oe() throws Exception {
    final StringBuilder body = new StringBuilder();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        try {
          response.getWriter().print("content");
        } catch (IOException e) {
          // removed other assertion
        }
      }
    };
    assertTrue(post(url).receive(body).ok());
  }

  @Test
  public void receiveWriter_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        try {
          response.getWriter().print("content");
        } catch (IOException e) {
          // removed other assertion
        }
      }
    };
    StringWriter writer = new StringWriter();
    assertTrue(post(url).receive(writer).ok());
  }

  @Test
  public void receivePrintStream_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        try {
          response.getWriter().print("content");
        } catch (IOException e) {
          // removed other assertion
        }
      }
    };
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream stream = new PrintStream(output, true, CHARSET_UTF8);
    assertTrue(post(url).receive(stream).ok());
  }

  @Test
  public void receiveFile_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        try {
          response.getWriter().print("content");
        } catch (IOException e) {
          // removed other assertion
        }
      }
    };
    File output = File.createTempFile("output", ".txt");
    assertTrue(post(url).receive(output).ok());
  }

  @Test
  public void httpsTrust_1_oe() throws Exception {
    assertNotNull(get("https://localhost").trustAllCerts().trustAllHosts());
  }

  @Test
  public void httpTrust_1_oe() throws Exception {
    assertNotNull(get("http://localhost").trustAllCerts().trustAllHosts());
  }

  @Test
  public void verifierAccepts_1_oe() {
    HttpRequest request = get("https://localhost");
    HttpsURLConnection connection = (HttpsURLConnection) request
        .getConnection();
    request.trustAllHosts();
    assertNotNull(connection.getHostnameVerifier());
  }

  @Test
  public void verifierAccepts_2_oe() {
    HttpRequest request = get("https://localhost");
    HttpsURLConnection connection = (HttpsURLConnection) request
        .getConnection();
    request.trustAllHosts();
    // removed other assertion
    assertTrue(connection.getHostnameVerifier().verify(null, null));
  }

  @Test
  public void singleVerifier_1_oe() {
    HttpRequest request1 = get("https://localhost").trustAllHosts();
    HttpRequest request2 = get("https://localhost").trustAllHosts();
    assertNotNull(((HttpsURLConnection)request1.getConnection()).getHostnameVerifier());
  }

  @Test
  public void singleVerifier_2_oe() {
    HttpRequest request1 = get("https://localhost").trustAllHosts();
    HttpRequest request2 = get("https://localhost").trustAllHosts();
    // removed other assertion
    assertNotNull(((HttpsURLConnection)request2.getConnection()).getHostnameVerifier());
  }

  @Test
  public void singleVerifier_3_oe() {
    HttpRequest request1 = get("https://localhost").trustAllHosts();
    HttpRequest request2 = get("https://localhost").trustAllHosts();
    // removed other assertion
    // removed other assertion
    assertEquals(((HttpsURLConnection)request1.getConnection()).getHostnameVerifier(),((HttpsURLConnection)request2.getConnection()).getHostnameVerifier());
  }

  @Test
  public void singleSslSocketFactory_1_oe() {
    HttpRequest request1 = get("https://localhost").trustAllCerts();
    HttpRequest request2 = get("https://localhost").trustAllCerts();
    assertNotNull(((HttpsURLConnection)request1.getConnection()).getSSLSocketFactory());
  }

  @Test
  public void singleSslSocketFactory_2_oe() {
    HttpRequest request1 = get("https://localhost").trustAllCerts();
    HttpRequest request2 = get("https://localhost").trustAllCerts();
    // removed other assertion
    assertNotNull(((HttpsURLConnection)request2.getConnection()).getSSLSocketFactory());
  }

  @Test
  public void singleSslSocketFactory_3_oe() {
    HttpRequest request1 = get("https://localhost").trustAllCerts();
    HttpRequest request2 = get("https://localhost").trustAllCerts();
    // removed other assertion
    // removed other assertion
    assertEquals(((HttpsURLConnection)request1.getConnection()).getSSLSocketFactory(),((HttpsURLConnection)request2.getConnection()).getSSLSocketFactory());
  }

  @Test
  public void sendErrorCloseStream_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        try {
          response.getWriter().print("content");
        } catch (IOException e) {
          // removed other assertion
        }
      }
    };
    final IOException closeCause = new IOException();
    InputStream stream = new InputStream() {

      public int read() throws IOException {
        return -1;
      }

      public void close() throws IOException {
        throw closeCause;
      }
    };
    try {
      post(url).ignoreCloseExceptions(false).send(stream);
      // removed other assertion
    } catch (HttpRequestException e) {
      assertEquals(closeCause, e.getCause());
  }
  }

  @Test
  public void getToOutputCode_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };
    AtomicInteger code = new AtomicInteger(0);
    get(url).code(code);
    assertEquals(HTTP_OK, code.get());
  }

  @Test
  public void getToOutputBody_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        try {
          response.getWriter().print("hello world");
        } catch (IOException e) {
          // removed other assertion
        }
      }
    };
    AtomicReference<String> body = new AtomicReference<String>(null);
    get(url).body(body);
    assertEquals("hello world", body.get());
  }

  @Test
  public void getToOutputBodyWithCharset_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        try {
          response.getWriter().print("hello world");
        } catch (IOException e) {
          // removed other assertion
        }
      }
    };
    AtomicReference<String> body = new AtomicReference<String>(null);
    get(url).body(body, CHARSET_UTF8);
    assertEquals("hello world", body.get());
  }

  @Test
  public void getGzipped_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        if (!"gzip".equals(request.getHeader("Accept-Encoding")))
          return;

        response.setHeader("Content-Encoding", "gzip");
        GZIPOutputStream output;
        try {
          output = new GZIPOutputStream(response.getOutputStream());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        try {
          output.write("hello compressed".getBytes(CHARSET_UTF8));
        } catch (IOException e) {
          throw new RuntimeException(e);
        } finally {
          try {
            output.close();
          } catch (IOException ignored) {
            // Ignored
          }
        }
      }
    };
    HttpRequest request = get(url).acceptGzipEncoding().uncompress(true);
    assertTrue(request.ok());
  }

  @Test
  public void getGzipped_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        if (!"gzip".equals(request.getHeader("Accept-Encoding")))
          return;

        response.setHeader("Content-Encoding", "gzip");
        GZIPOutputStream output;
        try {
          output = new GZIPOutputStream(response.getOutputStream());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        try {
          output.write("hello compressed".getBytes(CHARSET_UTF8));
        } catch (IOException e) {
          throw new RuntimeException(e);
        } finally {
          try {
            output.close();
          } catch (IOException ignored) {
            // Ignored
          }
        }
      }
    };
    HttpRequest request = get(url).acceptGzipEncoding().uncompress(true);
    // removed other assertion
    assertEquals("hello compressed", request.body(CHARSET_UTF8));
  }

  @Test
  public void getNonGzippedWithUncompressEnabled_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        if (!"gzip".equals(request.getHeader("Accept-Encoding")))
          return;

        write("hello not compressed");
      }
    };
    HttpRequest request = get(url).acceptGzipEncoding().uncompress(true);
    assertTrue(request.ok());
  }

  @Test
  public void getNonGzippedWithUncompressEnabled_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        if (!"gzip".equals(request.getHeader("Accept-Encoding")))
          return;

        write("hello not compressed");
      }
    };
    HttpRequest request = get(url).acceptGzipEncoding().uncompress(true);
    // removed other assertion
    assertEquals("hello not compressed", request.body(CHARSET_UTF8));
  }

  @Test
  public void getHeaders_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.addHeader("a", "1");
        response.addHeader("a", "2");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getHeaders_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.addHeader("a", "1");
        response.addHeader("a", "2");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    String[] values = request.headers("a");
    assertNotNull(values);
  }

  @Test
  public void getHeaders_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.addHeader("a", "1");
        response.addHeader("a", "2");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    String[] values = request.headers("a");
    // removed other assertion
    assertEquals(2, values.length);
  }

  @Test
  public void getHeaders_4_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.addHeader("a", "1");
        response.addHeader("a", "2");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    String[] values = request.headers("a");
    // removed other assertion
    // removed other assertion
    assertTrue(Arrays.asList(values).contains("1"));
  }

  @Test
  public void getHeaders_5_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.addHeader("a", "1");
        response.addHeader("a", "2");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    String[] values = request.headers("a");
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertTrue(Arrays.asList(values).contains("2"));
  }

  @Test
  public void getEmptyHeaders_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getEmptyHeaders_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    String[] values = request.headers("a");
    assertNotNull(values);
  }

  @Test
  public void getEmptyHeaders_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    String[] values = request.headers("a");
    // removed other assertion
    assertEquals(0, values.length);
  }

  @Test
  public void getSingleParameter_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=d");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getSingleParameter_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=d");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertEquals("d", request.parameter("a", "c"));
  }

  @Test
  public void getMultipleParameters_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=d;e=f");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getMultipleParameters_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=d;e=f");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertEquals("d", request.parameter("a", "c"));
  }

  @Test
  public void getMultipleParameters_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=d;e=f");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    assertEquals("f", request.parameter("a", "e"));
  }

  @Test
  public void getSingleParameterQuoted_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=\"d\"");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getSingleParameterQuoted_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=\"d\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertEquals("d", request.parameter("a", "c"));
  }

  @Test
  public void getMultipleParametersQuoted_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=\"d\";e=\"f\"");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getMultipleParametersQuoted_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=\"d\";e=\"f\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertEquals("d", request.parameter("a", "c"));
  }

  @Test
  public void getMultipleParametersQuoted_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=\"d\";e=\"f\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    assertEquals("f", request.parameter("a", "e"));
  }

  @Test
  public void getMissingParameter_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=d");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getMissingParameter_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=d");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertNull(request.parameter("a", "e"));
  }

  @Test
  public void getParameterFromMissingHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=d");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getParameterFromMissingHeader_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=d");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertNull(request.parameter("b", "c"));
  }

  @Test
  public void getParameterFromMissingHeader_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=d");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    assertTrue(request.parameters("b").isEmpty());
  }

  @Test
  public void getEmptyParameter_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getEmptyParameter_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertNull(request.parameter("a", "c"));
  }

  @Test
  public void getEmptyParameter_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;c=");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    assertTrue(request.parameters("a").isEmpty());
  }

  @Test
  public void getEmptyParameters_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getEmptyParameters_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertNull(request.parameter("a", "c"));
  }

  @Test
  public void getEmptyParameters_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "b;");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    // removed other assertion
    assertTrue(request.parameters("a").isEmpty());
  }

  @Test
  public void getParameters_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value;b=c;d=e");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getParameters_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value;b=c;d=e");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    assertNotNull(params);
  }

  @Test
  public void getParameters_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value;b=c;d=e");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    // removed other assertion
    assertEquals(2, params.size());
  }

  @Test
  public void getParameters_4_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value;b=c;d=e");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    // removed other assertion
    // removed other assertion
    assertEquals("c", params.get("b"));
  }

  @Test
  public void getParameters_5_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value;b=c;d=e");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("e", params.get("d"));
  }

  @Test
  public void getQuotedParameters_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value;b=\"c\";d=\"e\"");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getQuotedParameters_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value;b=\"c\";d=\"e\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    assertNotNull(params);
  }

  @Test
  public void getQuotedParameters_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value;b=\"c\";d=\"e\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    // removed other assertion
    assertEquals(2, params.size());
  }

  @Test
  public void getQuotedParameters_4_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value;b=\"c\";d=\"e\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    // removed other assertion
    // removed other assertion
    assertEquals("c", params.get("b"));
  }

  @Test
  public void getQuotedParameters_5_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value;b=\"c\";d=\"e\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("e", params.get("d"));
  }

  @Test
  public void getMixQuotedParameters_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value; b=c; d=\"e\"");
      }
    };
    HttpRequest request = get(url);
    assertTrue(request.ok());
  }

  @Test
  public void getMixQuotedParameters_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value; b=c; d=\"e\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    assertNotNull(params);
  }

  @Test
  public void getMixQuotedParameters_3_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value; b=c; d=\"e\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    // removed other assertion
    assertEquals(2, params.size());
  }

  @Test
  public void getMixQuotedParameters_4_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value; b=c; d=\"e\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    // removed other assertion
    // removed other assertion
    assertEquals("c", params.get("b"));
  }

  @Test
  public void getMixQuotedParameters_5_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("a", "value; b=c; d=\"e\"");
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    Map<String, String> params = request.parameters("a");
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("e", params.get("d"));
  }

  @Test
  public void missingDateHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };
    assertEquals(1234L, get(url).dateHeader("missing", 1234L));
  }

  @Test
  public void malformedDateHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("malformed", "not a date");
      }
    };
    assertEquals(1234L, get(url).dateHeader("malformed", 1234L));
  }

  @Test
  public void missingIntHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };
    assertEquals(4321, get(url).intHeader("missing", 4321));
  }

  @Test
  public void malformedIntHeader_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
        response.setHeader("malformed", "not an integer");
      }
    };
    assertEquals(4321, get(url).intHeader("malformed", 4321));
  }

  @Test
  public void postFormAsEntries_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    Map<String, String> data = new LinkedHashMap<String, String>();
    data.put("name", "user");
    data.put("number", "100");
    HttpRequest request = post(url);
    for (Entry<String, String> entry : data.entrySet())
      request.form(entry);
    int code = request.code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postFormAsEntries_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    Map<String, String> data = new LinkedHashMap<String, String>();
    data.put("name", "user");
    data.put("number", "100");
    HttpRequest request = post(url);
    for (Entry<String, String> entry : data.entrySet())
      request.form(entry);
    int code = request.code();
    // removed other assertion
    assertEquals("name=user&number=100", body.get());
  }

  @Test
  public void postFormEntryWithNullValue_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    Map<String, String> data = new LinkedHashMap<String, String>();
    data.put("name", null);
    HttpRequest request = post(url);
    for (Entry<String, String> entry : data.entrySet())
      request.form(entry);
    int code = request.code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void postFormEntryWithNullValue_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    Map<String, String> data = new LinkedHashMap<String, String>();
    data.put("name", null);
    HttpRequest request = post(url);
    for (Entry<String, String> entry : data.entrySet())
      request.form(entry);
    int code = request.code();
    // removed other assertion
    assertEquals("name=", body.get());
  }

  @Test
  public void postWithMappedQueryParams_1_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "user");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, inputParams, false);
    assertTrue(request.ok());
  }

  @Test
  public void postWithVaragsQueryParams_1_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, false, "name", "user", "number", "100");
    assertTrue(request.ok());
  }

  @Test
  public void postWithEscapedMappedQueryParams_1_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "us er");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, inputParams, true);
    assertTrue(request.ok());
  }

  @Test
  public void postWithEscapedVarargsQueryParams_1_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, true, "name", "us er", "number", "100");
    assertTrue(request.ok());
  }

  @Test
  public void postWithEscapedVarargsQueryParams_2_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, true, "name", "us er", "number", "100");
    // removed other assertion
    assertEquals("POST", method.get());
  }

  @Test
  public void postWithEscapedVarargsQueryParams_3_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, true, "name", "us er", "number", "100");
    // removed other assertion
    // removed other assertion
    assertEquals("us er", outputParams.get("name"));
  }

  @Test
  public void postWithEscapedVarargsQueryParams_4_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, true, "name", "us er", "number", "100");
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("100", outputParams.get("number"));
  }

  @Test
  public void postWithNumericQueryParams_1_oe() throws Exception {
    Map<Object, Object> inputParams = new HashMap<Object, Object>();
    inputParams.put(1, 2);
    inputParams.put(3, 4);
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("1", request.getParameter("1"));
        outputParams.put("3", request.getParameter("3"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, inputParams, false);
    assertTrue(request.ok());
  }

  @Test
  public void postWithNumericQueryParams_2_oe() throws Exception {
    Map<Object, Object> inputParams = new HashMap<Object, Object>();
    inputParams.put(1, 2);
    inputParams.put(3, 4);
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("1", request.getParameter("1"));
        outputParams.put("3", request.getParameter("3"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, inputParams, false);
    // removed other assertion
    assertEquals("POST", method.get());
  }

  @Test
  public void postWithNumericQueryParams_3_oe() throws Exception {
    Map<Object, Object> inputParams = new HashMap<Object, Object>();
    inputParams.put(1, 2);
    inputParams.put(3, 4);
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("1", request.getParameter("1"));
        outputParams.put("3", request.getParameter("3"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, inputParams, false);
    // removed other assertion
    // removed other assertion
    assertEquals("2", outputParams.get("1"));
  }

  @Test
  public void postWithNumericQueryParams_4_oe() throws Exception {
    Map<Object, Object> inputParams = new HashMap<Object, Object>();
    inputParams.put(1, 2);
    inputParams.put(3, 4);
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("1", request.getParameter("1"));
        outputParams.put("3", request.getParameter("3"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = post(url, inputParams, false);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("4", outputParams.get("3"));
  }

  @Test
  public void getWithMappedQueryParams_1_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "user");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url, inputParams, false);
    assertTrue(request.ok());
  }

  @Test
  public void getWithVarargsQueryParams_1_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url, false, "name", "user", "number", "100");
    assertTrue(request.ok());
  }

  @Test
  public void getWithEscapedMappedQueryParams_1_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "us er");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url, inputParams, true);
    assertTrue(request.ok());
  }

  @Test
  public void getWithEscapedVarargsQueryParams_1_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = get(url, true, "name", "us er", "number", "100");
    assertTrue(request.ok());
  }

  @Test
  public void deleteWithMappedQueryParams_1_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "user");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url, inputParams, false);
    assertTrue(request.ok());
  }

  @Test
  public void deleteWithVarargsQueryParams_1_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url, false, "name", "user", "number", "100");
    assertTrue(request.ok());
  }

  @Test
  public void deleteWithEscapedMappedQueryParams_1_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "us er");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url, inputParams, true);
    assertTrue(request.ok());
  }

  @Test
  public void deleteWithEscapedMappedQueryParams_3_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "us er");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url, inputParams, true);
    // removed other assertion
    // removed other assertion
    assertEquals("us er", outputParams.get("name"));
  }

  @Test
  public void deleteWithEscapedMappedQueryParams_4_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "us er");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url, inputParams, true);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("100", outputParams.get("number"));
  }

  @Test
  public void deleteWithEscapedVarargsQueryParams_1_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url, true, "name", "us er", "number", "100");
    assertTrue(request.ok());
  }

  @Test
  public void deleteWithEscapedVarargsQueryParams_3_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url, true, "name", "us er", "number", "100");
    // removed other assertion
    // removed other assertion
    assertEquals("us er", outputParams.get("name"));
  }

  @Test
  public void deleteWithEscapedVarargsQueryParams_4_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = delete(url, true, "name", "us er", "number", "100");
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("100", outputParams.get("number"));
  }

  @Test
  public void putWithMappedQueryParams_1_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "user");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url, inputParams, false);
    assertTrue(request.ok());
  }

  @Test
  public void putWithVarargsQueryParams_1_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url, false, "name", "user", "number", "100");
    assertTrue(request.ok());
  }

  @Test
  public void putWithVarargsQueryParams_2_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url, false, "name", "user", "number", "100");
    // removed other assertion
    assertEquals("PUT", method.get());
  }

  @Test
  public void putWithVarargsQueryParams_3_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url, false, "name", "user", "number", "100");
    // removed other assertion
    // removed other assertion
    assertEquals("user", outputParams.get("name"));
  }

  @Test
  public void putWithVarargsQueryParams_4_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url, false, "name", "user", "number", "100");
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("100", outputParams.get("number"));
  }

  @Test
  public void putWithEscapedMappedQueryParams_1_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "us er");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url, inputParams, true);
    assertTrue(request.ok());
  }

  @Test
  public void putWithEscapedMappedQueryParams_4_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "us er");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url, inputParams, true);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("100", outputParams.get("number"));
  }

  @Test
  public void putWithEscapedVarargsQueryParams_1_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = put(url, true, "name", "us er", "number", "100");
    assertTrue(request.ok());
  }

  @Test
  public void headWithMappedQueryParams_1_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "user");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url, inputParams, false);
    assertTrue(request.ok());
  }

  @Test
  public void headWithMappedQueryParams_2_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "user");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url, inputParams, false);
    // removed other assertion
    assertEquals("HEAD", method.get());
  }

  @Test
  public void headWithMappedQueryParams_3_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "user");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url, inputParams, false);
    // removed other assertion
    // removed other assertion
    assertEquals("user", outputParams.get("name"));
  }

  @Test
  public void headWithMappedQueryParams_4_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "user");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url, inputParams, false);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("100", outputParams.get("number"));
  }

  @Test
  public void headWithVaragsQueryParams_1_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url, false, "name", "user", "number", "100");
    assertTrue(request.ok());
  }

  @Test
  public void headWithEscapedMappedQueryParams_1_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "us er");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url, inputParams, true);
    assertTrue(request.ok());
  }

  @Test
  public void headWithEscapedMappedQueryParams_3_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "us er");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url, inputParams, true);
    // removed other assertion
    // removed other assertion
    assertEquals("us er", outputParams.get("name"));
  }

  @Test
  public void headWithEscapedMappedQueryParams_4_oe() throws Exception {
    Map<String, String> inputParams = new HashMap<String, String>();
    inputParams.put("name", "us er");
    inputParams.put("number", "100");
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url, inputParams, true);
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals("100", outputParams.get("number"));
  }

  @Test
  public void headWithEscapedVarargsQueryParams_1_oe() throws Exception {
    final Map<String, String> outputParams = new HashMap<String, String>();
    final AtomicReference<String> method = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        method.set(request.getMethod());
        outputParams.put("name", request.getParameter("name"));
        outputParams.put("number", request.getParameter("number"));
        response.setStatus(HTTP_OK);
      }
    };
    HttpRequest request = head(url, true, "name", "us er", "number", "100");
    assertTrue(request.ok());
  }

  @Test
  public void appendMappedQueryParamsWithNoPath_1_oe() throws Exception {
    assertEquals("http://test.com/?a=b",HttpRequest.append("http://test.com",Collections.singletonMap("a","b")));
  }

  @Test
  public void appendVarargsQueryParmasWithNoPath_1_oe() throws Exception {
    assertEquals("http://test.com/?a=b",HttpRequest.append("http://test.com","a","b"));
  }

  @Test
  public void appendMappedQueryParamsWithPath_1_oe() throws Exception {
    assertEquals("http://test.com/segment1?a=b",HttpRequest.append("http://test.com/segment1",Collections.singletonMap("a","b")));
  }

  @Test
  public void appendMappedQueryParamsWithPath_2_oe() throws Exception {
    // removed other assertion
    assertEquals("http://test.com/?a=b",HttpRequest.append("http://test.com/",Collections.singletonMap("a","b")));
  }

  @Test
  public void appendVarargsQueryParamsWithPath_1_oe() throws Exception {
    assertEquals("http://test.com/segment1?a=b",HttpRequest.append("http://test.com/segment1","a","b"));
  }

  @Test
  public void appendVarargsQueryParamsWithPath_2_oe() throws Exception {
    // removed other assertion
    assertEquals("http://test.com/?a=b",HttpRequest.append("http://test.com/","a","b"));
  }

  @Test
  public void appendMultipleMappedQueryParams_1_oe() throws Exception {
    Map<String, Object> params = new LinkedHashMap<String, Object>();
    params.put("a", "b");
    params.put("c", "d");
    assertEquals("http://test.com/1?a=b&c=d",HttpRequest.append("http://test.com/1",params));
  }

  @Test
  public void appendMultipleVarargsQueryParams_1_oe() throws Exception {
    assertEquals("http://test.com/1?a=b&c=d",HttpRequest.append("http://test.com/1","a","b","c","d"));
  }

  @Test
  public void appendNullMappedQueryParams_1_oe() throws Exception {
    assertEquals("http://test.com/1",HttpRequest.append("http://test.com/1",(Map<?,?>)null));
  }

  @Test
  public void appendNullVaragsQueryParams_1_oe() throws Exception {
    assertEquals("http://test.com/1",HttpRequest.append("http://test.com/1",(Object[])null));
  }

  @Test
  public void appendEmptyMappedQueryParams_1_oe() throws Exception {
    assertEquals("http://test.com/1",HttpRequest.append("http://test.com/1",Collections.<String,String> emptyMap()));
  }

  @Test
  public void appendEmptyVarargsQueryParams_1_oe() throws Exception {
    assertEquals("http://test.com/1",HttpRequest.append("http://test.com/1",new Object[0]));
  }

  @Test
  public void appendWithNullMappedQueryParamValues_1_oe() throws Exception {
    Map<String, Object> params = new LinkedHashMap<String, Object>();
    params.put("a", null);
    params.put("b", null);
    assertEquals("http://test.com/1?a=&b=",HttpRequest.append("http://test.com/1",params));
  }

  @Test
  public void appendWithNullVaragsQueryParamValues_1_oe() throws Exception {
    assertEquals("http://test.com/1?a=&b=",HttpRequest.append("http://test.com/1","a",null,"b",null));
  }

  @Test
  public void appendMappedQueryParamsWithExistingQueryStart_1_oe() {
    assertEquals("http://test.com/1?a=b",HttpRequest.append("http://test.com/1?",Collections.singletonMap("a","b")));
  }

  @Test
  public void appendVarargsQueryParamsWithExistingQueryStart_1_oe() {
    assertEquals("http://test.com/1?a=b",HttpRequest.append("http://test.com/1?","a","b"));
  }

  @Test
  public void appendMappedQueryParamsWithExistingParams_1_oe() {
    assertEquals("http://test.com/1?a=b&c=d",HttpRequest.append("http://test.com/1?a=b",Collections.singletonMap("c","d")));
  }

  @Test
  public void appendMappedQueryParamsWithExistingParams_2_oe() {
    // removed other assertion
    assertEquals("http://test.com/1?a=b&c=d",HttpRequest.append("http://test.com/1?a=b&",Collections.singletonMap("c","d")));
  }

  @Test
  public void appendWithVarargsQueryParamsWithExistingParams_1_oe() {
    assertEquals("http://test.com/1?a=b&c=d",HttpRequest.append("http://test.com/1?a=b","c","d"));
  }

  @Test
  public void appendWithVarargsQueryParamsWithExistingParams_2_oe() {
    // removed other assertion
    assertEquals("http://test.com/1?a=b&c=d",HttpRequest.append("http://test.com/1?a=b&","c","d"));
  }

  @Test
  public void appendArrayQueryParams_1_oe() throws Exception {
    assertEquals( "http://test.com/?foo[]=bar&foo[]=baz", HttpRequest.append("http://test.com", Collections.singletonMap("foo", new String[] { "bar", "baz" })));
  }

  @Test
  public void appendArrayQueryParams_2_oe() throws Exception {
    // removed other assertion
    assertEquals( "http://test.com/?a[]=1&a[]=2", HttpRequest.append("http://test.com", Collections.singletonMap("a", new int[] { 1, 2 })));
  }

  @Test
  public void appendArrayQueryParams_3_oe() throws Exception {
    // removed other assertion
    // removed other assertion
    assertEquals( "http://test.com/?a[]=1", HttpRequest.append("http://test.com", Collections.singletonMap("a", new int[] { 1 })));
  }

  @Test
  public void appendArrayQueryParams_4_oe() throws Exception {
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals( "http://test.com/?", HttpRequest.append("http://test.com", Collections.singletonMap("a", new int[] { })));
  }

  @Test
  public void appendArrayQueryParams_5_oe() throws Exception {
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals( "http://test.com/?foo[]=bar&foo[]=baz&a[]=1&a[]=2", HttpRequest.append("http://test.com", "foo", new String[] { "bar", "baz" }, "a", new int[] { 1, 2 }));
  }

  @Test
  public void appendListQueryParams_1_oe() throws Exception {
    assertEquals( "http://test.com/?foo[]=bar&foo[]=baz", HttpRequest.append("http://test.com", Collections.singletonMap("foo", Arrays.asList(new String[] { "bar", "baz" }))));
  }

  @Test
  public void appendListQueryParams_2_oe() throws Exception {
    // removed other assertion
    assertEquals( "http://test.com/?a[]=1&a[]=2", HttpRequest.append("http://test.com", Collections.singletonMap("a", Arrays.asList(new Integer[] { 1, 2 }))));
  }

  @Test
  public void appendListQueryParams_3_oe() throws Exception {
    // removed other assertion
    // removed other assertion
    assertEquals( "http://test.com/?a[]=1", HttpRequest.append("http://test.com", Collections.singletonMap("a", Arrays.asList(new Integer[] { 1 }))));
  }

  @Test
  public void appendListQueryParams_4_oe() throws Exception {
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals( "http://test.com/?", HttpRequest.append("http://test.com", Collections.singletonMap("a", Arrays.asList(new Integer[] { }))));
  }

  @Test
  public void appendListQueryParams_5_oe() throws Exception {
    // removed other assertion
    // removed other assertion
    // removed other assertion
    // removed other assertion
    assertEquals( "http://test.com/?foo[]=bar&foo[]=baz&a[]=1&a[]=2", HttpRequest.append("http://test.com", "foo", Arrays.asList(new String[] { "bar", "baz" }), "a", Arrays.asList(new Integer[] { 1, 2 })));
  }

  @Test
  public void serverErrorCode_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_INTERNAL_ERROR);
      }
    };
    HttpRequest request = get(url);
    assertNotNull(request);
  }

  @Test
  public void serverErrorCode_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_INTERNAL_ERROR);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertTrue(request.serverError());
  }

  @Test
  public void badRequestCode_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_BAD_REQUEST);
      }
    };
    HttpRequest request = get(url);
    assertNotNull(request);
  }

  @Test
  public void badRequestCode_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_BAD_REQUEST);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertTrue(request.badRequest());
  }

  @Test
  public void notModifiedCode_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_NOT_MODIFIED);
      }
    };
    HttpRequest request = get(url);
    assertNotNull(request);
  }

  @Test
  public void notModifiedCode_2_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_NOT_MODIFIED);
      }
    };
    HttpRequest request = get(url);
    // removed other assertion
    assertTrue(request.notModified());
  }

  @Test
  public void sendReceiveWithoutCode_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        try {
          response.getWriter().write("world");
        } catch (IOException ignored) {
          // Ignored
        }
        response.setStatus(HTTP_OK);
      }
    };

    HttpRequest request = post(url).ignoreCloseExceptions(false);
    assertEquals("world", request.send("hello").body());
  }

  @Test
  public void sendHeadersWithoutCode_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setHeader("h1", "v1");
        response.setHeader("h2", "v2");
        response.setStatus(HTTP_OK);
      }
    };

    HttpRequest request = post(url).ignoreCloseExceptions(false);
    Map<String, List<String>> headers = request.send("hello").headers();
    assertEquals("v1", headers.get("h1").get(0));
  }

  @Test
  public void sendHeadersWithoutCode_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setHeader("h1", "v1");
        response.setHeader("h2", "v2");
        response.setStatus(HTTP_OK);
      }
    };

    HttpRequest request = post(url).ignoreCloseExceptions(false);
    Map<String, List<String>> headers = request.send("hello").headers();
    // removed other assertion
    assertEquals("v2", headers.get("h2").get(0));
  }

  @Test
  public void sendHeadersWithoutCode_3_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setHeader("h1", "v1");
        response.setHeader("h2", "v2");
        response.setStatus(HTTP_OK);
      }
    };

    HttpRequest request = post(url).ignoreCloseExceptions(false);
    Map<String, List<String>> headers = request.send("hello").headers();
    // removed other assertion
    // removed other assertion
    assertEquals("hello", body.get());
  }

  @Test
  public void sendDateHeaderWithoutCode_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setDateHeader("Date", 1000);
        response.setStatus(HTTP_OK);
      }
    };

    HttpRequest request = post(url).ignoreCloseExceptions(false);
    assertEquals(1000, request.send("hello").date());
  }

  @Test
  public void sendIntHeaderWithoutCode_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setIntHeader("Width", 9876);
        response.setStatus(HTTP_OK);
      }
    };

    HttpRequest request = post(url).ignoreCloseExceptions(false);
    assertEquals(9876, request.send("hello").intHeader("Width"));
  }

  @Test
  public void customConnectionFactory_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };

    ConnectionFactory factory = new ConnectionFactory() {

      public HttpURLConnection create(URL otherUrl) throws IOException {
        return (HttpURLConnection) new URL(url).openConnection();
      }

      public HttpURLConnection create(URL url, Proxy proxy) throws IOException {
        throw new IOException();
      }
    };

    HttpRequest.setConnectionFactory(factory);
    int code = get("http://not/a/real/url").code();
    assertEquals(200, code);
  }

  @Test
  public void nullConnectionFactory_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_OK);
      }
    };

    HttpRequest.setConnectionFactory(null);
    int code = get(url).code();
    assertEquals(200, code);
  }

  @Test
  public void streamOfEmptyOkResponse_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(200);
      }
    };
    assertEquals("", get(url).body());
  }

  @Test
  public void bodyOfEmptyErrorResponse_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_BAD_REQUEST);
      }
    };
    assertEquals("", get(url).body());
  }

  @Test
  public void bodyOfNonEmptyErrorResponse_1_oe() throws Exception {
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        response.setStatus(HTTP_BAD_REQUEST);
        try {
          response.getWriter().write("error");
        } catch (IOException ignored) {
          // Ignored
        }
      }
    };
    assertEquals("error", get(url).body());
  }

  @Test
  public void nullUploadProgress_1_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    File file = File.createTempFile("post", ".txt");
    new FileWriter(file).append("hello").close();
    int code = post(url).progress(null).send(file).code();
    assertEquals(HTTP_OK, code);
  }

  @Test
  public void nullUploadProgress_2_oe() throws Exception {
    final AtomicReference<String> body = new AtomicReference<String>();
    handler = new RequestHandler() {

      @Override
      public void handle(Request request, HttpServletResponse response) {
        body.set(new String(read()));
        response.setStatus(HTTP_OK);
      }
    };
    File file = File.createTempFile("post", ".txt");
    new FileWriter(file).append("hello").close();
    int code = post(url).progress(null).send(file).code();
    // removed other assertion
    assertEquals("hello", body.get());
  }

}
