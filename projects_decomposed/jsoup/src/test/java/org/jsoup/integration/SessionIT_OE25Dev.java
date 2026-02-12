package org.jsoup.integration;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.UncheckedIOException;
import org.jsoup.integration.servlets.FileServlet;
import org.jsoup.integration.servlets.SlowRider;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Integration tests to test longer running Connection */
public class SessionIT_OE25Dev {
    @BeforeAll
    public static void setUp() {
        TestServer.start();
    }

    // test that we throw a nice clear exception if you try to multi-thread by forget .newRequest()


    static class ThreadCatcher implements Thread.UncaughtExceptionHandler {
        AtomicInteger exceptionCount = new AtomicInteger();
        AtomicInteger multiThreadExceptions = new AtomicInteger();

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            if (e instanceof IllegalArgumentException && e.getMessage().contains("Multiple threads"))
                multiThreadExceptions.incrementAndGet();
            else
                e.printStackTrace();
            exceptionCount.incrementAndGet();
        }
    }


}
