package com.github.scribejava.httpclient.apache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.Test;

import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.model.OAuthAsyncRequestCallback;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import static org.junit.Assert.assertThrows;
import org.junit.function.ThrowingRunnable;

public class OAuthAsyncCompletionHandlerTest_OE25Dev {

    private static final AllGoodResponseConverter ALL_GOOD_RESPONSE_CONVERTER = new AllGoodResponseConverter();
    private static final ExceptionResponseConverter EXCEPTION_RESPONSE_CONVERTER = new ExceptionResponseConverter();
    private static final OAuthExceptionResponseConverter OAUTH_EXCEPTION_RESPONSE_CONVERTER
            = new OAuthExceptionResponseConverter();

    private OAuthAsyncCompletionHandler<String> handler;
    private TestCallback callback;

    private static class TestCallback implements OAuthAsyncRequestCallback<String> {

        private Throwable throwable;
        private String response;

        @Override
        public void onCompleted(String response) {
            this.response = response;
        }

        @Override
        public void onThrowable(Throwable throwable) {
            this.throwable = throwable;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public String getResponse() {
            return response;
        }

    }

    @Before
    public void setUp() {
        callback = new TestCallback();
    }

    private static class AllGoodResponseConverter implements OAuthRequest.ResponseConverter<String> {

        @Override
        public String convert(Response response) throws IOException {
            response.close();
            return "All good";
        }
    }

    private static class ExceptionResponseConverter implements OAuthRequest.ResponseConverter<String> {

        @Override
        public String convert(Response response) throws IOException {
            response.close();
            throw new IOException("Failed to convert");
        }
    }

    private static class OAuthExceptionResponseConverter implements OAuthRequest.ResponseConverter<String> {

        @Override
        public String convert(Response response) throws IOException {
            response.close();
            throw new OAuthException("bad oauth");
        }
    }

    @Test
    public void shouldReleaseLatchOnSuccess_1_oe() throws Exception {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        assertNotNull(callback.getResponse());
    }

    @Test
    public void shouldReleaseLatchOnSuccess_2_oe() throws Exception {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        // removed other assertion
        assertNull(callback.getThrowable());
    }

    @Test
    public void shouldReleaseLatchOnSuccess_3_oe() throws Exception {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        // removed other assertion
        // removed other assertion
        // verify latch is released
        assertEquals("All good", handler.getResult());
    }

    @Test
    public void shouldReleaseLatchOnIOException_1_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, EXCEPTION_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        assertNull(callback.getResponse());
    }

    @Test
    public void shouldReleaseLatchOnIOException_2_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, EXCEPTION_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        // removed other assertion
        assertNotNull(callback.getThrowable());
    }

    @Test
    public void shouldReleaseLatchOnIOException_3_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, EXCEPTION_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        // removed other assertion
        // removed other assertion
        assertTrue(callback.getThrowable() instanceof IOException);
    }

    @Test
    public void shouldReleaseLatchOnIOException_4_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, EXCEPTION_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify latch is released
        assertThrows(ExecutionException.class, new ThrowingRunnable() { @Override public void run() throws Throwable { handler.getResult(); } });
    }

    @Test
    public void shouldReportOAuthException_1_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, OAUTH_EXCEPTION_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        assertNull(callback.getResponse());
    }

    @Test
    public void shouldReportOAuthException_2_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, OAUTH_EXCEPTION_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        // removed other assertion
        assertNotNull(callback.getThrowable());
    }

    @Test
    public void shouldReportOAuthException_3_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, OAUTH_EXCEPTION_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        // removed other assertion
        // removed other assertion
        assertTrue(callback.getThrowable() instanceof OAuthException);
    }

    @Test
    public void shouldReportOAuthException_4_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, OAUTH_EXCEPTION_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.completed(response);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify latch is released
        assertThrows(ExecutionException.class, new ThrowingRunnable() { @Override public void run() throws Throwable { handler.getResult(); } });
    }

    @Test
    public void shouldReleaseLatchOnCancel_1_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.cancelled();
        assertNull(callback.getResponse());
    }

    @Test
    public void shouldReleaseLatchOnCancel_2_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.cancelled();
        // removed other assertion
        assertNotNull(callback.getThrowable());
    }

    @Test
    public void shouldReleaseLatchOnCancel_3_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.cancelled();
        // removed other assertion
        // removed other assertion
        assertTrue(callback.getThrowable() instanceof CancellationException);
    }

    @Test
    public void shouldReleaseLatchOnCancel_4_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.cancelled();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify latch is released
        assertThrows(ExecutionException.class, new ThrowingRunnable() { @Override public void run() throws Throwable { handler.getResult(); } });
    }

    @Test
    public void shouldReleaseLatchOnFailure_1_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.failed(new RuntimeException());
        assertNull(callback.getResponse());
    }

    @Test
    public void shouldReleaseLatchOnFailure_2_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.failed(new RuntimeException());
        // removed other assertion
        assertNotNull(callback.getThrowable());
    }

    @Test
    public void shouldReleaseLatchOnFailure_3_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.failed(new RuntimeException());
        // removed other assertion
        // removed other assertion
        assertTrue(callback.getThrowable() instanceof RuntimeException);
    }

    @Test
    public void shouldReleaseLatchOnFailure_4_oe() {
        handler = new OAuthAsyncCompletionHandler<>(callback, ALL_GOOD_RESPONSE_CONVERTER);
        final HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("4", 1, 1), 200, "ok"));
        final BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(new byte[0]));
        response.setEntity(entity);
        handler.failed(new RuntimeException());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify latch is released
        assertThrows(ExecutionException.class, new ThrowingRunnable() { @Override public void run() throws Throwable { handler.getResult(); } });
    }

}
