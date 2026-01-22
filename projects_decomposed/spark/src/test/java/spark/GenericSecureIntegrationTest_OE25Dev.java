package spark;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.util.SparkTestUtil;
import spark.util.SparkTestUtil.UrlResponse;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.patch;
import static spark.Spark.post;

public class GenericSecureIntegrationTest_OE25Dev {

    static SparkTestUtil testUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericSecureIntegrationTest_OE25Dev.class);

    @AfterClass
    public static void tearDown() {
        Spark.stop();
    }

    @BeforeClass
    public static void setup() {
        testUtil = new SparkTestUtil(4567);

        // note that the keystore stuff is retrieved from SparkTestUtil which
        // respects JVM params for keystore, password
        // but offers a default included store if not.
        Spark.secure(SparkTestUtil.getKeyStoreLocation(),
                     SparkTestUtil.getKeystorePassword(), null, null);

        before("/protected/*", (request, response) -> {
            halt(401, "Go Away!");
        });

        get("/hi", (request, response) -> "Hello World!");

        get("/ip", (request, response) -> request.ip());

        get("/:param", (request, response) -> "echo: " + request.params(":param"));

        get("/paramwithmaj/:paramWithMaj", (request, response) -> "echo: " + request.params(":paramWithMaj"));

        get("/", (request, response) -> "Hello Root!");

        post("/poster", (request, response) -> {
            String body = request.body();
            response.status(201); // created
            return "Body was: " + body;
        });

        patch("/patcher", (request, response) -> {
            String body = request.body();
            response.status(200);
            return "Body was: " + body;
        });

        after("/hi", (request, response) -> {
            response.header("after", "foobar");
        });

        Spark.awaitInitialization();
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        Assert.assertEquals(xForwardedFor, response.body);

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        Assert.assertNotEquals(xForwardedFor, response.body);
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        Assert.assertEquals(200, response.status);
        Assert.assertEquals("", response.body);
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        Assert.assertTrue(response.headers.get("after").contains("foobar"));
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        Assert.assertEquals(200, response.status);
        Assert.assertEquals("Hello Root!", response.body);
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        Assert.assertEquals(200, response.status);
        Assert.assertEquals("echo: shizzy", response.body);
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        Assert.assertEquals(200, response.status);
        Assert.assertEquals("echo: gunit", response.body);
    }

    @Test
    public void testEchoParamWithMaj() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/paramwithmaj/plop", null);
        Assert.assertEquals(200, response.status);
        Assert.assertEquals("echo: plop", response.body);
    }

    @Test
    public void testUnauthorized() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/protected/resource", null);
        Assert.assertTrue(urlResponse.status == 401);
    }

    @Test
    public void testNotFound() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/no/resource", null);
        Assert.assertTrue(urlResponse.status == 404);
    }

    @Test
    public void testPost() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("POST", "/poster", "Fo shizzy");
        LOGGER.info(response.body);
        Assert.assertEquals(201, response.status);
        Assert.assertTrue(response.body.contains("Fo shizzy"));
    }

    @Test
    public void testPatch() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("PATCH", "/patcher", "Fo shizzy");
        LOGGER.info(response.body);
        Assert.assertEquals(200, response.status);
        Assert.assertTrue(response.body.contains("Fo shizzy"));
    }

    @Test
    public void testGetHi_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testGetHi_2_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        Assert.assertEquals("Hello World!", response.body);
    }

    @Test
    public void testGetHi_3_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        Assert.assertEquals(xForwardedFor, response.body);
    }

    @Test
    public void testGetHi_4_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        Assert.assertNotEquals(xForwardedFor, response.body);
    }

    @Test
    public void testGetHi_5_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testGetHi_6_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        Assert.assertEquals("", response.body);
    }

    @Test
    public void testGetHi_7_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        Assert.assertTrue(response.headers.get("after").contains("foobar"));
    }

    @Test
    public void testGetHi_8_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testGetHi_9_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        Assert.assertEquals("Hello Root!", response.body);
    }

    @Test
    public void testGetHi_10_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testGetHi_11_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        Assert.assertEquals("echo: shizzy", response.body);
    }

    @Test
    public void testGetHi_12_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testGetHi_13_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        // removed other assertion
        Assert.assertEquals("echo: gunit", response.body);
    }

    @Test
    public void testGetHi_14_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParamWithMaj() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/paramwithmaj/plop", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testGetHi_15_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParamWithMaj() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/paramwithmaj/plop", null);
        // removed other assertion
        Assert.assertEquals("echo: plop", response.body);
    }

    @Test
    public void testGetHi_16_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParamWithMaj() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/paramwithmaj/plop", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testUnauthorized() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/protected/resource", null);
        Assert.assertTrue(urlResponse.status == 401);
    }

    @Test
    public void testGetHi_17_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParamWithMaj() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/paramwithmaj/plop", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testUnauthorized() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/protected/resource", null);
        // removed other assertion
    }

    @Test
    public void testNotFound() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/no/resource", null);
        Assert.assertTrue(urlResponse.status == 404);
    }

    @Test
    public void testGetHi_18_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParamWithMaj() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/paramwithmaj/plop", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testUnauthorized() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/protected/resource", null);
        // removed other assertion
    }

    @Test
    public void testNotFound() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/no/resource", null);
        // removed other assertion
    }

    @Test
    public void testPost() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("POST", "/poster", "Fo shizzy");
        LOGGER.info(response.body);
        Assert.assertEquals(201, response.status);
    }

    @Test
    public void testGetHi_19_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParamWithMaj() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/paramwithmaj/plop", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testUnauthorized() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/protected/resource", null);
        // removed other assertion
    }

    @Test
    public void testNotFound() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/no/resource", null);
        // removed other assertion
    }

    @Test
    public void testPost() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("POST", "/poster", "Fo shizzy");
        LOGGER.info(response.body);
        // removed other assertion
        Assert.assertTrue(response.body.contains("Fo shizzy"));
    }

    @Test
    public void testGetHi_20_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParamWithMaj() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/paramwithmaj/plop", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testUnauthorized() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/protected/resource", null);
        // removed other assertion
    }

    @Test
    public void testNotFound() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/no/resource", null);
        // removed other assertion
    }

    @Test
    public void testPost() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("POST", "/poster", "Fo shizzy");
        LOGGER.info(response.body);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testPatch() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("PATCH", "/patcher", "Fo shizzy");
        LOGGER.info(response.body);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testGetHi_21_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testXForwardedFor() throws Exception {
        final String xForwardedFor = "XXX.XXX.XXX.XXX";
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Forwarded-For", xForwardedFor);

        UrlResponse response = testUtil.doMethod("GET", "/ip", null, true, "text/html", headers);
        // removed other assertion

        response = testUtil.doMethod("GET", "/ip", null, true, "text/html", null);
        // removed other assertion
    }

    @Test
    public void testHiHead() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("HEAD", "/hi", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGetHiAfterFilter() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/hi", null);
        // removed other assertion
    }

    @Test
    public void testGetRoot() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam1() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/shizzy", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParam2() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/gunit", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testEchoParamWithMaj() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("GET", "/paramwithmaj/plop", null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testUnauthorized() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/protected/resource", null);
        // removed other assertion
    }

    @Test
    public void testNotFound() throws Exception {
        UrlResponse urlResponse = testUtil.doMethodSecure("GET", "/no/resource", null);
        // removed other assertion
    }

    @Test
    public void testPost() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("POST", "/poster", "Fo shizzy");
        LOGGER.info(response.body);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testPatch() throws Exception {
        UrlResponse response = testUtil.doMethodSecure("PATCH", "/patcher", "Fo shizzy");
        LOGGER.info(response.body);
        // removed other assertion
        Assert.assertTrue(response.body.contains("Fo shizzy"));
    }

}
