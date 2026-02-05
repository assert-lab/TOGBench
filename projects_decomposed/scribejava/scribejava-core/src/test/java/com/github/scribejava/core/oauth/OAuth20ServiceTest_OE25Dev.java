package com.github.scribejava.core.oauth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.base64.Base64;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuth2Authorization;
import com.github.scribejava.core.model.OAuthConstants;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

public class OAuth20ServiceTest_OE25Dev {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

@Test
    public void shouldProduceCorrectRequestSync_1_oe() throws IOException, InterruptedException, ExecutionException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrant("user1", "password1");
        assertNotNull(token);
    }

@Test
    public void shouldProduceCorrectRequestSync_2_oe() throws IOException, InterruptedException, ExecutionException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrant("user1", "password1");
        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        assertEquals(OAuth20ServiceUnit.TOKEN, response.get(OAuthConstants.ACCESS_TOKEN).asText());
    }

@Test
    public void shouldProduceCorrectRequestSync_3_oe() throws IOException, InterruptedException, ExecutionException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrant("user1", "password1");
        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        // removed other assertion
        assertEquals(OAuth20ServiceUnit.EXPIRES, response.get("expires_in").asInt());
    }

@Test
    public void shouldProduceCorrectRequestSync_4_oe() throws IOException, InterruptedException, ExecutionException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrant("user1", "password1");
        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        // removed other assertion
        // removed other assertion

        final String authorize = Base64.encode(
                String.format("%s:%s", service.getApiKey(), service.getApiSecret()).getBytes(Charset.forName("UTF-8")));

        assertEquals(OAuthConstants.BASIC + ' ' + authorize, response.get(OAuthConstants.HEADER).asText());
    }

@Test
    public void shouldProduceCorrectRequestSync_5_oe() throws IOException, InterruptedException, ExecutionException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrant("user1", "password1");
        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        // removed other assertion
        // removed other assertion

        final String authorize = Base64.encode(
                String.format("%s:%s", service.getApiKey(), service.getApiSecret()).getBytes(Charset.forName("UTF-8")));

        // removed other assertion

        assertEquals("user1", response.get("query-username").asText());
    }

@Test
    public void shouldProduceCorrectRequestSync_6_oe() throws IOException, InterruptedException, ExecutionException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrant("user1", "password1");
        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        // removed other assertion
        // removed other assertion

        final String authorize = Base64.encode(
                String.format("%s:%s", service.getApiKey(), service.getApiSecret()).getBytes(Charset.forName("UTF-8")));

        // removed other assertion

        // removed other assertion
        assertEquals("password1", response.get("query-password").asText());
    }

@Test
    public void shouldProduceCorrectRequestSync_7_oe() throws IOException, InterruptedException, ExecutionException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrant("user1", "password1");
        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        // removed other assertion
        // removed other assertion

        final String authorize = Base64.encode(
                String.format("%s:%s", service.getApiKey(), service.getApiSecret()).getBytes(Charset.forName("UTF-8")));

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("password", response.get("query-grant_type").asText());
    }

@Test
    public void shouldProduceCorrectRequestAsync_1_oe() throws ExecutionException, InterruptedException, IOException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrantAsync("user1", "password1").get();

        assertNotNull(token);
    }

@Test
    public void shouldProduceCorrectRequestAsync_2_oe() throws ExecutionException, InterruptedException, IOException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrantAsync("user1", "password1").get();

        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        assertEquals(OAuth20ServiceUnit.TOKEN, response.get(OAuthConstants.ACCESS_TOKEN).asText());
    }

@Test
    public void shouldProduceCorrectRequestAsync_3_oe() throws ExecutionException, InterruptedException, IOException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrantAsync("user1", "password1").get();

        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        // removed other assertion
        assertEquals(OAuth20ServiceUnit.EXPIRES, response.get("expires_in").asInt());
    }

@Test
    public void shouldProduceCorrectRequestAsync_4_oe() throws ExecutionException, InterruptedException, IOException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrantAsync("user1", "password1").get();

        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        // removed other assertion
        // removed other assertion

        final String authorize = Base64.encode(
                String.format("%s:%s", service.getApiKey(), service.getApiSecret()).getBytes(Charset.forName("UTF-8")));

        assertEquals(OAuthConstants.BASIC + ' ' + authorize, response.get(OAuthConstants.HEADER).asText());
    }

@Test
    public void shouldProduceCorrectRequestAsync_5_oe() throws ExecutionException, InterruptedException, IOException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrantAsync("user1", "password1").get();

        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        // removed other assertion
        // removed other assertion

        final String authorize = Base64.encode(
                String.format("%s:%s", service.getApiKey(), service.getApiSecret()).getBytes(Charset.forName("UTF-8")));

        // removed other assertion

        assertEquals("user1", response.get("query-username").asText());
    }

@Test
    public void shouldProduceCorrectRequestAsync_6_oe() throws ExecutionException, InterruptedException, IOException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrantAsync("user1", "password1").get();

        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        // removed other assertion
        // removed other assertion

        final String authorize = Base64.encode(
                String.format("%s:%s", service.getApiKey(), service.getApiSecret()).getBytes(Charset.forName("UTF-8")));

        // removed other assertion

        // removed other assertion
        assertEquals("password1", response.get("query-password").asText());
    }

@Test
    public void shouldProduceCorrectRequestAsync_7_oe() throws ExecutionException, InterruptedException, IOException {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        final OAuth2AccessToken token = service.getAccessTokenPasswordGrantAsync("user1", "password1").get();

        // removed other assertion

        final JsonNode response = OBJECT_MAPPER.readTree(token.getRawResponse());

        // removed other assertion
        // removed other assertion

        final String authorize = Base64.encode(
                String.format("%s:%s", service.getApiKey(), service.getApiSecret()).getBytes(Charset.forName("UTF-8")));

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("password", response.get("query-grant_type").asText());
    }

@Test
    public void testOAuthExtractAuthorization_1_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        assertEquals("SplxlOB", authorization.getCode());
    }

@Test
    public void testOAuthExtractAuthorization_2_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        assertEquals("xyz", authorization.getState());
    }

@Test
    public void testOAuthExtractAuthorization_3_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        assertEquals("SplxlOB", authorization.getCode());
    }

@Test
    public void testOAuthExtractAuthorization_4_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        assertEquals("xyz", authorization.getState());
    }

@Test
    public void testOAuthExtractAuthorization_5_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        assertEquals("SplxlOB", authorization.getCode());
    }

@Test
    public void testOAuthExtractAuthorization_6_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        assertEquals("xyz", authorization.getState());
    }

@Test
    public void testOAuthExtractAuthorization_7_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        assertEquals("SplxlOB", authorization.getCode());
    }

@Test
    public void testOAuthExtractAuthorization_8_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        assertEquals("xyz", authorization.getState());
    }

@Test
    public void testOAuthExtractAuthorization_9_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        assertEquals("SplxlOB", authorization.getCode());
    }

@Test
    public void testOAuthExtractAuthorization_10_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        assertEquals(null, authorization.getState());
    }

@Test
    public void testOAuthExtractAuthorization_11_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB");
        assertEquals("SplxlOB", authorization.getCode());
    }

@Test
    public void testOAuthExtractAuthorization_12_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB");
        // removed other assertion
        assertEquals(null, authorization.getState());
    }

@Test
    public void testOAuthExtractAuthorization_13_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=");
        assertEquals(null, authorization.getCode());
    }

@Test
    public void testOAuthExtractAuthorization_14_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=");
        // removed other assertion
        assertEquals(null, authorization.getState());
    }

@Test
    public void testOAuthExtractAuthorization_15_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code");
        assertEquals(null, authorization.getCode());
    }

@Test
    public void testOAuthExtractAuthorization_16_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code");
        // removed other assertion
        assertEquals(null, authorization.getState());
    }

@Test
    public void testOAuthExtractAuthorization_17_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?");
        assertEquals(null, authorization.getCode());
    }

@Test
    public void testOAuthExtractAuthorization_18_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?");
        // removed other assertion
        assertEquals(null, authorization.getState());
    }

@Test
    public void testOAuthExtractAuthorization_19_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb");
        assertEquals(null, authorization.getCode());
    }

@Test
    public void testOAuthExtractAuthorization_20_oe() {
        final OAuth20Service service = new ServiceBuilder("your_api_key")
                .apiSecret("your_api_secret")
                .build(new OAuth20ApiUnit());

        OAuth2Authorization authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=xyz");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?key=value&state=xyz&code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?state=xyz&code=SplxlOB&key=value&");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB&state=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=SplxlOB");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code=");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?code");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb?");
        // removed other assertion
        // removed other assertion

        authorization = service.extractAuthorization("https://cl.ex.com/cb");
        // removed other assertion
        assertEquals(null, authorization.getState());
    }

}
