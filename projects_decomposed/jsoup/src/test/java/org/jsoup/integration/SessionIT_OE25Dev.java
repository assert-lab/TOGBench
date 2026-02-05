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

@Test
    public void multiThread_2_oe() throws InterruptedException {
        int numThreads = 20;
        int numThreadLoops = 5;
        String[] urls = {
            FileServlet.urlTo("/htmltests/smh-biz-article-1.html.gz"),
            FileServlet.urlTo("/htmltests/news-com-au-home.html.gz"),
            FileServlet.urlTo("/htmltests/google-ipod.html.gz"),
            FileServlet.urlTo("/htmltests/large.html"),
        };
        String[] titles = {
            "The board’s next fear: the female quota",
            "News.com.au | News from Australia and around the world online | NewsComAu",
            "ipod - Google Search",
            "Large HTML"
        };
        ThreadCatcher catcher = new ThreadCatcher();

        Connection session = Jsoup.newSession();

        Thread[] threads = new Thread[numThreads];
        for (int threadNum = 0; threadNum < numThreads; threadNum++) {
            Thread thread = new Thread(() -> {
                for (int loop = 0; loop < numThreadLoops; loop++) {
                    for (int i = 0; i < urls.length; i++) {
                        try {
                            Document doc = session.newRequest().url(urls[i]).get();
                            // removed other assertion
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    }
                }
            });
            thread.setName("Runner-" + threadNum);
            thread.start();
            thread.setUncaughtExceptionHandler(catcher);
            threads[threadNum] = thread;
        }

        // now join them all
        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(0, catcher.exceptionCount.get());
    }

@Test
    public void multiThreadWithoutNewRequestBlowsUp_2_oe() throws InterruptedException {
        int numThreads = 20;
        String url = SlowRider.Url + "?" + SlowRider.MaxTimeParam + "=10000"; // this makes sure that the first req is still executing whilst the others run
        String title = "Slow Rider";

        ThreadCatcher catcher = new ThreadCatcher();
        Connection session = Jsoup.newSession();

        Thread[] threads = new Thread[numThreads];
        for (int threadNum = 0; threadNum < numThreads; threadNum++) {
            Thread thread = new Thread(() -> {
                try {
                    Document doc = session.url(url).get();
                    // removed other assertion
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
            thread.setName("Runner-" + threadNum);
            thread.start();
            thread.setUncaughtExceptionHandler(catcher);
            threads[threadNum] = thread;
        }

        // now join them all
        for (Thread thread : threads) {
            thread.join();
        }

        // only one should have passed, rest should have blown up (assuming the started whilst other was running)
        assertEquals(numThreads - 1, catcher.multiThreadExceptions.get());
    }

@Test
    public void multiThreadWithoutNewRequestBlowsUp_3_oe() throws InterruptedException {
        int numThreads = 20;
        String url = SlowRider.Url + "?" + SlowRider.MaxTimeParam + "=10000"; // this makes sure that the first req is still executing whilst the others run
        String title = "Slow Rider";

        ThreadCatcher catcher = new ThreadCatcher();
        Connection session = Jsoup.newSession();

        Thread[] threads = new Thread[numThreads];
        for (int threadNum = 0; threadNum < numThreads; threadNum++) {
            Thread thread = new Thread(() -> {
                try {
                    Document doc = session.url(url).get();
                    // removed other assertion
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
            thread.setName("Runner-" + threadNum);
            thread.start();
            thread.setUncaughtExceptionHandler(catcher);
            threads[threadNum] = thread;
        }

        // now join them all
        for (Thread thread : threads) {
            thread.join();
        }

        // only one should have passed, rest should have blown up (assuming the started whilst other was running)
        // removed other assertion
        assertEquals(numThreads - 1, catcher.exceptionCount.get());
    }

}
