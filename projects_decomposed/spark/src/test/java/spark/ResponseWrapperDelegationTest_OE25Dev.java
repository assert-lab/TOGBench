package spark;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.util.SparkTestUtil;
import spark.util.SparkTestUtil.UrlResponse;

import java.io.IOException;

import static spark.Spark.*;

public class ResponseWrapperDelegationTest_OE25Dev {

    static SparkTestUtil testUtil;

    @AfterClass
    public static void tearDown() {
        Spark.stop();
    }

    @BeforeClass
    public static void setup() throws IOException {
        testUtil = new SparkTestUtil(4567);

        get("/204", (q, a) -> {
            a.status(204);
            return "";
        });

        after("/204", (q, a) -> {
            if (a.status() == 204) {
                a.status(200);
                a.body("ok");
            }
        });

        get("/json", (q, a) -> {
            a.type("application/json");
            return "{\"status\": \"ok\"}";
        });

        after("/json", (q, a) -> {
            if ("application/json".equalsIgnoreCase(a.type())) {
                a.type("text/plain");
            }
        });

        exception(Exception.class, (exception, q, a) -> {
            exception.printStackTrace();
        });

        Spark.awaitInitialization();
    }

    @Test
    public void filters_can_detect_response_status() throws Exception {
        UrlResponse response = testUtil.get("/204");
        Assert.assertEquals(200, response.status);
        Assert.assertEquals("ok", response.body);
    }

    @Test
    public void filters_can_detect_content_type() throws Exception {
        UrlResponse response = testUtil.get("/json");
        Assert.assertEquals(200, response.status);
        Assert.assertEquals("{\"status\": \"ok\"}", response.body);
        Assert.assertEquals("text/plain", response.headers.get("Content-Type"));
    }

    @Test
    public void filters_can_detect_response_status_1_oe() throws Exception {
        UrlResponse response = testUtil.get("/204");
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void filters_can_detect_response_status_2_oe() throws Exception {
        UrlResponse response = testUtil.get("/204");
        Assert.assertEquals("ok", response.body);
    }

    @Test
    public void filters_can_detect_content_type_1_oe() throws Exception {
        UrlResponse response = testUtil.get("/json");
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void filters_can_detect_content_type_2_oe() throws Exception {
        UrlResponse response = testUtil.get("/json");
        Assert.assertEquals("{\"status\": \"ok\"}", response.body);
    }

    @Test
    public void filters_can_detect_content_type_3_oe() throws Exception {
        UrlResponse response = testUtil.get("/json");
        Assert.assertEquals("text/plain", response.headers.get("Content-Type"));
    }

}
