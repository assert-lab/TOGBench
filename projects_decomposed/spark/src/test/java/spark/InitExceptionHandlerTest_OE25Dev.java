package spark;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static spark.Service.ignite;

public class InitExceptionHandlerTest_OE25Dev {

    private static int NON_VALID_PORT = Integer.MAX_VALUE;
    private static Service service;
    private static String errorMessage = "";

    @BeforeClass
    public static void setUpClass() throws Exception {
        service = ignite();
        service.port(NON_VALID_PORT);
        service.initExceptionHandler((e) -> errorMessage = "Custom init error");
        service.init();
        service.awaitInitialization();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        service.stop();
    }

    @Test
    public void testInitExceptionHandler_1_oe() throws Exception {
        Assert.assertEquals("Custom init error", errorMessage);
    }

}
