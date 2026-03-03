/*
 * Copyright 2016 - Per Wendel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.util.SparkTestUtil;

import static spark.Spark.get;
import static spark.Spark.redirect;

/**
 * Tests the redirect utility methods in {@link spark.Redirect}
 */
public class RedirectTest_OE25Dev {

    private static final String REDIRECTED = "Redirected";

    private static SparkTestUtil testUtil;

    @BeforeClass
    public static void setup() throws IOException {
        testUtil = new SparkTestUtil(4567);
        testUtil.setFollowRedirectStrategy(301, 302); // don't set the others to be able to verify affect of Redirect.Status

        get("/hello", (request, response) -> REDIRECTED);

        redirect.get("/hi", "/hello");
        redirect.post("/hi", "/hello");
        redirect.put("/hi", "/hello");
        redirect.delete("/hi", "/hello");
        redirect.any("/any", "/hello");

        redirect.get("/hiagain", "/hello", Redirect.Status.USE_PROXY);
        redirect.post("/hiagain", "/hello", Redirect.Status.USE_PROXY);
        redirect.put("/hiagain", "/hello", Redirect.Status.USE_PROXY);
        redirect.delete("/hiagain", "/hello", Redirect.Status.USE_PROXY);
        redirect.any("/anyagain", "/hello", Redirect.Status.USE_PROXY);

        Spark.awaitInitialization();
    }

    @Test
    public void testRedirectGet_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("GET", "/hi", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testRedirectGet_2_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("GET", "/hi", null);
        Assert.assertEquals(REDIRECTED, response.body);
    }

    @Test
    public void testRedirectPost_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/hi", "");
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testRedirectPost_2_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/hi", "");
        Assert.assertEquals(REDIRECTED, response.body);
    }

    @Test
    public void testRedirectPut_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("PUT", "/hi", "");
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testRedirectPut_2_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("PUT", "/hi", "");
        Assert.assertEquals(REDIRECTED, response.body);
    }

    @Test
    public void testRedirectDelete_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("DELETE", "/hi", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testRedirectDelete_2_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("DELETE", "/hi", null);
        Assert.assertEquals(REDIRECTED, response.body);
    }

    @Test
    public void testRedirectAnyGet_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("GET", "/any", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testRedirectAnyGet_2_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("GET", "/any", null);
        Assert.assertEquals(REDIRECTED, response.body);
    }

    @Test
    public void testRedirectAnyPut_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("PUT", "/any", "");
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testRedirectAnyPut_2_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("PUT", "/any", "");
        Assert.assertEquals(REDIRECTED, response.body);
    }

    @Test
    public void testRedirectAnyPost_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/any", "");
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testRedirectAnyPost_2_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/any", "");
        Assert.assertEquals(REDIRECTED, response.body);
    }

    @Test
    public void testRedirectAnyDelete_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("DELETE", "/any", "");
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testRedirectAnyDelete_2_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("DELETE", "/any", "");
        Assert.assertEquals(REDIRECTED, response.body);
    }

    @Test
    public void testRedirectGetWithSpecificCode_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("GET", "/hiagain", null);
        Assert.assertEquals(Redirect.Status.USE_PROXY.intValue(), response.status);
    }

    @Test
    public void testRedirectPostWithSpecificCode_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/hiagain", "");
        Assert.assertEquals(Redirect.Status.USE_PROXY.intValue(), response.status);
    }

    @Test
    public void testRedirectPutWithSpecificCode_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("PUT", "/hiagain", "");
        Assert.assertEquals(Redirect.Status.USE_PROXY.intValue(), response.status);
    }

    @Test
    public void testRedirectDeleteWithSpecificCode_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("DELETE", "/hiagain", null);
        Assert.assertEquals(Redirect.Status.USE_PROXY.intValue(), response.status);
    }

    @Test
    public void testRedirectAnyGetWithSpecificCode_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("GET", "/anyagain", null);
        Assert.assertEquals(Redirect.Status.USE_PROXY.intValue(), response.status);
    }

    @Test
    public void testRedirectAnyPostWithSpecificCode_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/anyagain", "");
        Assert.assertEquals(Redirect.Status.USE_PROXY.intValue(), response.status);
    }

    @Test
    public void testRedirectAnyPutWithSpecificCode_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("PUT", "/anyagain", "");
        Assert.assertEquals(Redirect.Status.USE_PROXY.intValue(), response.status);
    }

    @Test
    public void testRedirectAnyDeleteWithSpecificCode_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("DELETE", "/anyagain", null);
        Assert.assertEquals(Redirect.Status.USE_PROXY.intValue(), response.status);
    }

}
