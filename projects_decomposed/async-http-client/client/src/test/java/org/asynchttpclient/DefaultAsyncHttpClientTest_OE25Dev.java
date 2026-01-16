package org.asynchttpclient;

import io.netty.util.Timer;
import org.asynchttpclient.cookie.CookieEvictionTask;
import org.asynchttpclient.cookie.CookieStore;
import org.asynchttpclient.cookie.ThreadSafeCookieStore;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.asynchttpclient.Dsl.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class DefaultAsyncHttpClientTest_OE25Dev {

  @Test
  public void testDisablingCookieStore() throws IOException {
    AsyncHttpClientConfig config = config()
      .setCookieStore(null).build();
    try (AsyncHttpClient client = asyncHttpClient(config)) {
      //
    }
  }

  @Test
  public void testWithSharedNettyTimerShouldScheduleCookieEvictionOnlyOnce_1_oe() throws IOException {
    Timer nettyTimerMock = mock(Timer.class);
    CookieStore cookieStore = new ThreadSafeCookieStore();
    AsyncHttpClientConfig config = config().setNettyTimer(nettyTimerMock).setCookieStore(cookieStore).build();

    try (AsyncHttpClient client1 = asyncHttpClient(config)) {
      try (AsyncHttpClient client2 = asyncHttpClient(config)) {
        assertEquals(cookieStore.count(), 2);
  }
  }
  }

  @Test
  public void testWitDefaultConfigShouldScheduleCookieEvictionForEachAHC_1_oe() throws IOException {
    AsyncHttpClientConfig config1 = config().build();
    try (AsyncHttpClient client1 = asyncHttpClient(config1)) {
      AsyncHttpClientConfig config2 = config().build();
      try (AsyncHttpClient client2 = asyncHttpClient(config2)) {
        assertEquals(config1.getCookieStore().count(), 1);
  }
  }
  }

  @Test
  public void testWitDefaultConfigShouldScheduleCookieEvictionForEachAHC_2_oe() throws IOException {
    AsyncHttpClientConfig config1 = config().build();
    try (AsyncHttpClient client1 = asyncHttpClient(config1)) {
      AsyncHttpClientConfig config2 = config().build();
      try (AsyncHttpClient client2 = asyncHttpClient(config2)) {
        // removed other assertion
        assertEquals(config2.getCookieStore().count(), 1);
  }
  }
  }

  @Test
  public void testWithSharedCookieStoreButNonSharedTimerShouldScheduleCookieEvictionForFirstAHC_1_oe() throws IOException {
    CookieStore cookieStore = new ThreadSafeCookieStore();
    Timer nettyTimerMock1 = mock(Timer.class);
    AsyncHttpClientConfig config1 = config()
      .setCookieStore(cookieStore).setNettyTimer(nettyTimerMock1).build();

    try (AsyncHttpClient client1 = asyncHttpClient(config1)) {
      Timer nettyTimerMock2 = mock(Timer.class);
      AsyncHttpClientConfig config2 = config()
        .setCookieStore(cookieStore).setNettyTimer(nettyTimerMock2).build();
      try (AsyncHttpClient client2 = asyncHttpClient(config2)) {
        assertEquals(config1.getCookieStore().count(), 2);
  }
  }
  }

  @Test
  public void testWithSharedCookieStoreButNonSharedTimerShouldScheduleCookieEvictionForFirstAHC_2_oe() throws IOException {
    CookieStore cookieStore = new ThreadSafeCookieStore();
    Timer nettyTimerMock1 = mock(Timer.class);
    AsyncHttpClientConfig config1 = config()
      .setCookieStore(cookieStore).setNettyTimer(nettyTimerMock1).build();

    try (AsyncHttpClient client1 = asyncHttpClient(config1)) {
      Timer nettyTimerMock2 = mock(Timer.class);
      AsyncHttpClientConfig config2 = config()
        .setCookieStore(cookieStore).setNettyTimer(nettyTimerMock2).build();
      try (AsyncHttpClient client2 = asyncHttpClient(config2)) {
        // removed other assertion
        verify(nettyTimerMock1, times(1)).newTimeout(any(CookieEvictionTask.class), anyLong(), any(TimeUnit.class));
        verify(nettyTimerMock2, never()).newTimeout(any(CookieEvictionTask.class), anyLong(), any(TimeUnit.class));
      }
    }

    Timer nettyTimerMock3 = mock(Timer.class);
    AsyncHttpClientConfig config3 = config()
      .setCookieStore(cookieStore).setNettyTimer(nettyTimerMock3).build();

    try (AsyncHttpClient client2 = asyncHttpClient(config3)) {
      assertEquals(config1.getCookieStore().count(), 1);
  }
  }

  @Test
  public void testWithSharedCookieStoreButNonSharedTimerShouldReScheduleCookieEvictionWhenFirstInstanceGetClosed_1_oe() throws IOException {
    CookieStore cookieStore = new ThreadSafeCookieStore();
    Timer nettyTimerMock1 = mock(Timer.class);
    AsyncHttpClientConfig config1 = config()
      .setCookieStore(cookieStore).setNettyTimer(nettyTimerMock1).build();

    try (AsyncHttpClient client1 = asyncHttpClient(config1)) {
      assertEquals(config1.getCookieStore().count(), 1);
  }
  }

  @Test
  public void testWithSharedCookieStoreButNonSharedTimerShouldReScheduleCookieEvictionWhenFirstInstanceGetClosed_2_oe() throws IOException {
    CookieStore cookieStore = new ThreadSafeCookieStore();
    Timer nettyTimerMock1 = mock(Timer.class);
    AsyncHttpClientConfig config1 = config()
      .setCookieStore(cookieStore).setNettyTimer(nettyTimerMock1).build();

    try (AsyncHttpClient client1 = asyncHttpClient(config1)) {
      // removed other assertion
      verify(nettyTimerMock1, times(1)).newTimeout(any(CookieEvictionTask.class), anyLong(), any(TimeUnit.class));
    }

    assertEquals(config1.getCookieStore().count(), 0);
  }

  @Test
  public void testWithSharedCookieStoreButNonSharedTimerShouldReScheduleCookieEvictionWhenFirstInstanceGetClosed_3_oe() throws IOException {
    CookieStore cookieStore = new ThreadSafeCookieStore();
    Timer nettyTimerMock1 = mock(Timer.class);
    AsyncHttpClientConfig config1 = config()
      .setCookieStore(cookieStore).setNettyTimer(nettyTimerMock1).build();

    try (AsyncHttpClient client1 = asyncHttpClient(config1)) {
      // removed other assertion
      verify(nettyTimerMock1, times(1)).newTimeout(any(CookieEvictionTask.class), anyLong(), any(TimeUnit.class));
    }

    // removed other assertion

    Timer nettyTimerMock2 = mock(Timer.class);
    AsyncHttpClientConfig config2 = config()
      .setCookieStore(cookieStore).setNettyTimer(nettyTimerMock2).build();

    try (AsyncHttpClient client2 = asyncHttpClient(config2)) {
      assertEquals(config1.getCookieStore().count(), 1);
  }
  }

}
