package spark.servlet;

import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Spark;
import spark.util.SparkTestUtil;
import spark.util.SparkTestUtil.UrlResponse;

public class ServletTest_OE25Dev {

    private static final String SOMEPATH = "/somepath";
    private static final int PORT = 9393;
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletTest.class);

    private static SparkTestUtil testUtil;

    @AfterClass
    public static void tearDown() {
        Spark.stop();
        if (MyApp.tmpExternalFile != null) {
            LOGGER.debug("tearDown().deleting: " + MyApp.tmpExternalFile);
            MyApp.tmpExternalFile.delete();
        }
    }

    @BeforeClass
    public static void setup() throws InterruptedException {
        testUtil = new SparkTestUtil(PORT);

        final Server server = new Server();
        ServerConnector connector = new ServerConnector(server);

        // Set some timeout options to make debugging easier.
        connector.setIdleTimeout(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(PORT);
        server.setConnectors(new Connector[] {connector});

        WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath(SOMEPATH);
        bb.setWar("src/test/webapp");

        server.setHandler(bb);
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LOGGER.info(">>> STARTING EMBEDDED JETTY SERVER for jUnit testing of SparkFilter");
                    server.start();
                    latch.countDown();
                    System.in.read();
                    LOGGER.info(">>> STOPPING EMBEDDED JETTY SERVER");
                    server.stop();
                    server.join();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(100);
                }
            }
        }).start();

        latch.await();
    }

    @Test
    public void testGetHi_1_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/hi", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testGetHi_2_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/hi", null);
        // removed other assertion
        Assert.assertEquals("Hello World!", response.body);
    }

    @Test
    public void testHiHead_1_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("HEAD", SOMEPATH + "/hi", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testHiHead_2_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("HEAD", SOMEPATH + "/hi", null);
        // removed other assertion
        Assert.assertEquals("", response.body);
    }

    @Test
    public void testGetHiAfterFilter_1_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/hi", null);
        Assert.assertTrue(response.headers.get("after").contains("foobar"));
    }

    @Test
    public void testGetRoot_1_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testGetRoot_2_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/", null);
        // removed other assertion
        Assert.assertEquals("Hello Root!", response.body);
    }

    @Test
    public void testEchoParam1_1_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/shizzy", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testEchoParam1_2_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/shizzy", null);
        // removed other assertion
        Assert.assertEquals("echo: shizzy", response.body);
    }

    @Test
    public void testEchoParam2_1_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/gunit", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testEchoParam2_2_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/gunit", null);
        // removed other assertion
        Assert.assertEquals("echo: gunit", response.body);
    }

    @Test
    public void testUnauthorized_1_oe() throws Exception {
        UrlResponse urlResponse = testUtil.doMethod("GET", SOMEPATH + "/protected/resource", null);
        Assert.assertTrue(urlResponse.status == 401);
    }

    @Test
    public void testNotFound_1_oe() throws Exception {
        UrlResponse urlResponse = testUtil.doMethod("GET", SOMEPATH + "/no/resource", null);
        Assert.assertTrue(urlResponse.status == 404);
    }

    @Test
    public void testPost_1_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("POST", SOMEPATH + "/poster", "Fo shizzy");
        Assert.assertEquals(201, response.status);
    }

    @Test
    public void testPost_2_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("POST", SOMEPATH + "/poster", "Fo shizzy");
        // removed other assertion
        Assert.assertTrue(response.body.contains("Fo shizzy"));
    }

    @Test
    public void testStaticResource_1_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/css/style.css", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testStaticResource_2_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/css/style.css", null);
        // removed other assertion
        Assert.assertTrue(response.body.contains("Content of css file"));
    }

    @Test
    public void testStaticWelcomeResource_1_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/pages/", null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testStaticWelcomeResource_2_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/pages/", null);
        // removed other assertion
        Assert.assertTrue(response.body.contains("<html><body>Hello Static World!</body></html>"));
    }

    @Test
    public void testExternalStaticFile_1_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/" + MyApp.EXTERNAL_FILE, null);
        Assert.assertEquals(200, response.status);
    }

    @Test
    public void testExternalStaticFile_2_oe() throws Exception {
        UrlResponse response = testUtil.doMethod("GET", SOMEPATH + "/" + MyApp.EXTERNAL_FILE, null);
        // removed other assertion
        Assert.assertEquals("Content of external file", response.body);
    }

}
