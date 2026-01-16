package spark;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.util.SparkTestUtil;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.post;

public class BodyAvailabilityTest_OE25Dev {

    private static final Logger LOGGER = LoggerFactory.getLogger(BodyAvailabilityTest.class);

    private static final String BODY_CONTENT = "the body content";
    
    private static SparkTestUtil testUtil;

    private final int HTTP_OK = 200;
    
    private static String beforeBody = null;
    private static String routeBody = null;
    private static String afterBody = null;

    @AfterClass
    public static void tearDown() {
        Spark.stop();

        beforeBody = null;
        routeBody = null;
        afterBody = null;
    }

    @BeforeClass
    public static void setup() {
        LOGGER.debug("setup()");

        testUtil = new SparkTestUtil(4567);

        beforeBody = null;
        routeBody = null;
        afterBody = null;

        before("/hello", (req, res) -> {
            LOGGER.debug("before-req.body() = " + req.body());
            beforeBody = req.body();
        });

        post("/hello", (req, res) -> {
            LOGGER.debug("get-req.body() = " + req.body());
            routeBody = req.body();
            return req.body();
        });

        after("/hello", (req, res) -> {
            LOGGER.debug("after-before-req.body() = " + req.body());
            afterBody = req.body();
        });

        Spark.awaitInitialization();
    }

    @Test
    public void testPost_1_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/hello", BODY_CONTENT);
        LOGGER.info(response.body);
        Assert.assertEquals(HTTP_OK, response.status);
    }

    @Test
    public void testPost_2_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/hello", BODY_CONTENT);
        LOGGER.info(response.body);
        // removed other assertion
        Assert.assertTrue(response.body.contains(BODY_CONTENT));
    }

    @Test
    public void testPost_3_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/hello", BODY_CONTENT);
        LOGGER.info(response.body);
        // removed other assertion
        // removed other assertion

        Assert.assertEquals(BODY_CONTENT, beforeBody);
    }

    @Test
    public void testPost_4_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/hello", BODY_CONTENT);
        LOGGER.info(response.body);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assert.assertEquals(BODY_CONTENT, routeBody);
    }

    @Test
    public void testPost_5_oe() throws Exception {
        SparkTestUtil.UrlResponse response = testUtil.doMethod("POST", "/hello", BODY_CONTENT);
        LOGGER.info(response.body);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assert.assertEquals(BODY_CONTENT, afterBody);
    }

}