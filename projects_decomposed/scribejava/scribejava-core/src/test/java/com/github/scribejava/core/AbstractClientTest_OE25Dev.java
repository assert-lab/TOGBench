package com.github.scribejava.core;

import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.model.OAuthAsyncRequestCallback;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth.OAuthService;
import com.github.scribejava.core.utils.StreamUtils;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractClientTest_OE25Dev {

    private OAuthService oAuthService;

    private static class TestCallback implements OAuthAsyncRequestCallback<Response> {

        private Response response;

        @Override
        public void onCompleted(Response response) {
            this.response = response;
        }

        @Override
        public void onThrowable(Throwable throwable) {
        }

        public Response getResponse() {
            return response;
        }
    }

    @Before
    public void setUp() {
        oAuthService = new OAuth20Service(null, "test", "test", null, null, null, System.out, null, null,
                createNewClient());
    }

    @After
    public void shutDown() throws Exception {
        oAuthService.close();
    }

    protected abstract HttpClient createNewClient();

    @Test
    public void shouldSendGetRequest_1_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendGetRequest";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.GET, baseUrl.toString());

        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            assertEquals(expectedResponseBody, response.getBody());
    }
    }

    @Test
    public void shouldSendGetRequest_2_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendGetRequest";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.GET, baseUrl.toString());

        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
    }

    @Test
    public void shouldSendPostWithApplicationXWwwFormUrlencodedRequestContentTypeHeader_1_oe() throws Exception {
        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse());
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        oAuthService.execute(request, null).get(30, TimeUnit.SECONDS).close();

        final RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
    }

    @Test
    public void shouldSendPostWithApplicationXWwwFormUrlencodedRequestContentTypeHeader_2_oe() throws Exception {
        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse());
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        oAuthService.execute(request, null).get(30, TimeUnit.SECONDS).close();

        final RecordedRequest recordedRequest = server.takeRequest();
        // removed other assertion
        assertEquals(HttpClient.DEFAULT_CONTENT_TYPE, recordedRequest.getHeader(HttpClient.CONTENT_TYPE));
    }

    @Test
    public void shouldSendPostRequestWithEmptyBody_1_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            assertEquals(expectedResponseBody, response.getBody());
    }
    }

    @Test
    public void shouldSendPostRequestWithEmptyBody_2_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
    }

    @Test
    public void shouldSendPostRequestWithEmptyBody_3_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        // removed other assertion
        assertEquals(expectedRequestBody, recordedRequest.getBody().readUtf8());
    }

    @Test
    public void shouldSendPostRequestWithEmptyBody_4_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        // removed other assertion
        // removed other assertion
        assertEquals(HttpClient.DEFAULT_CONTENT_TYPE, recordedRequest.getHeader(HttpClient.CONTENT_TYPE));
    }

    @Test
    public void shouldSendPostRequestWithStringBody_1_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "request body";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.setPayload(expectedRequestBody);
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            assertEquals(expectedResponseBody, response.getBody());
    }
    }

    @Test
    public void shouldSendPostRequestWithStringBody_2_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "request body";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.setPayload(expectedRequestBody);
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
    }

    @Test
    public void shouldSendPostRequestWithStringBody_3_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "request body";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.setPayload(expectedRequestBody);
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        // removed other assertion
        assertEquals(expectedRequestBody, recordedRequest.getBody().readUtf8());
    }

    @Test
    public void shouldSendPostRequestWithStringBody_4_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "request body";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.setPayload(expectedRequestBody);
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        // removed other assertion
        // removed other assertion
        final String contentTypeHeader = recordedRequest.getHeader(HttpClient.CONTENT_TYPE);
        assertNotNull(contentTypeHeader);
    }

    @Test
    public void shouldSendPostRequestWithStringBody_5_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "request body";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.setPayload(expectedRequestBody);
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        // removed other assertion
        // removed other assertion
        final String contentTypeHeader = recordedRequest.getHeader(HttpClient.CONTENT_TYPE);
        // removed other assertion
        assertTrue(contentTypeHeader.startsWith(HttpClient.DEFAULT_CONTENT_TYPE));
    }

    @Test
    public void shouldSendPostRequestWithByteBodyBody_1_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "request body";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.setPayload(expectedRequestBody.getBytes());
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            assertEquals(expectedResponseBody, response.getBody());
    }
    }

    @Test
    public void shouldSendPostRequestWithByteBodyBody_2_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "request body";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.setPayload(expectedRequestBody.getBytes());
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
    }

    @Test
    public void shouldSendPostRequestWithByteBodyBody_3_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "request body";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.setPayload(expectedRequestBody.getBytes());
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        // removed other assertion
        assertEquals(expectedRequestBody, recordedRequest.getBody().readUtf8());
    }

    @Test
    public void shouldSendPostRequestWithByteBodyBody_4_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBody = "request body";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.setPayload(expectedRequestBody.getBytes());
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        // removed other assertion
        // removed other assertion
        assertEquals(HttpClient.DEFAULT_CONTENT_TYPE, recordedRequest.getHeader(HttpClient.CONTENT_TYPE));
    }

    @Test
    public void shouldSendPostRequestWithBodyParamsBody_1_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBodyParamName = "request_body_param_name";
        final String expectedRequestBodyParamValue = "request_body_param_value";
        final String expectedRequestBody = expectedRequestBodyParamName + '=' + expectedRequestBodyParamValue;

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.addBodyParameter(expectedRequestBodyParamName, expectedRequestBodyParamValue);
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            assertEquals(expectedResponseBody, response.getBody());
    }
    }

    @Test
    public void shouldSendPostRequestWithBodyParamsBody_2_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBodyParamName = "request_body_param_name";
        final String expectedRequestBodyParamValue = "request_body_param_value";
        final String expectedRequestBody = expectedRequestBodyParamName + '=' + expectedRequestBodyParamValue;

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.addBodyParameter(expectedRequestBodyParamName, expectedRequestBodyParamValue);
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
    }

    @Test
    public void shouldSendPostRequestWithBodyParamsBody_3_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBodyParamName = "request_body_param_name";
        final String expectedRequestBodyParamValue = "request_body_param_value";
        final String expectedRequestBody = expectedRequestBodyParamName + '=' + expectedRequestBodyParamValue;

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.addBodyParameter(expectedRequestBodyParamName, expectedRequestBodyParamValue);
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        // removed other assertion
        assertEquals(expectedRequestBody, recordedRequest.getBody().readUtf8());
    }

    @Test
    public void shouldSendPostRequestWithBodyParamsBody_4_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldSendPostRequest";
        final String expectedRequestBodyParamName = "request_body_param_name";
        final String expectedRequestBodyParamValue = "request_body_param_value";
        final String expectedRequestBody = expectedRequestBodyParamName + '=' + expectedRequestBodyParamValue;

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl.toString());
        request.addBodyParameter(expectedRequestBodyParamName, expectedRequestBodyParamValue);
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        // removed other assertion
        // removed other assertion
        assertEquals(HttpClient.DEFAULT_CONTENT_TYPE, recordedRequest.getHeader(HttpClient.CONTENT_TYPE));
    }

    @Test
    public void shouldReadResponseStream_1_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldReadResponseStream";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.GET, baseUrl.toString());
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            assertEquals(expectedResponseBody, StreamUtils.getStreamContents(response.getStream()));
    }
    }

    @Test
    public void shouldReadResponseStream_2_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldReadResponseStream";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.GET, baseUrl.toString());
        try (Response response = oAuthService.execute(request, null).get(30, TimeUnit.SECONDS)) {
            // removed other assertion
        }

        final RecordedRequest recordedRequest = server.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
    }

    @Test
    public void shouldCallCallback_1_oe() throws Exception {
        final String expectedResponseBody = "response body for test shouldCallCallback";

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(expectedResponseBody));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.GET, baseUrl.toString());

        final TestCallback callback = new TestCallback();
        oAuthService.execute(request, callback).get();

        assertEquals(expectedResponseBody, callback.getResponse().getBody());
    }

    @Test
    public void shouldPassErrors_1_oe() throws Exception {

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(500));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.GET, baseUrl.toString());

        final TestCallback callback = new TestCallback();
        try (Response response = oAuthService.execute(request, callback).get()) {

            assertEquals(500, response.getCode());
    }
    }

    @Test
    public void shouldPassErrors_2_oe() throws Exception {

        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(500));
        server.start();

        final HttpUrl baseUrl = server.url("/testUrl");

        final OAuthRequest request = new OAuthRequest(Verb.GET, baseUrl.toString());

        final TestCallback callback = new TestCallback();
        try (Response response = oAuthService.execute(request, callback).get()) {

            // removed other assertion
            assertEquals(500, callback.getResponse().getCode());
    }
    }

}
