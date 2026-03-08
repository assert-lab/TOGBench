/*
 * Copyright (c) 2014 AsyncHttpClient Project. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at
 *     http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package org.asynchttpclient;

import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by grenville on 9/25/16.
 */
public class ClientStatsTest_OE25Dev extends AbstractBasicTest {

  private final static String hostname = "localhost";

  @Test
  public void testClientStatus_1_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      assertEquals(emptyStats.toString(), "There are 0 total connections, 0 are active and 0 are idle.");
  }
  }

  @Test
  public void testClientStatus_2_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      assertEquals(emptyStats.getTotalActiveConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatus_3_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(emptyStats.getTotalIdleConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatus_4_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(emptyStats.getTotalConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatus_5_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertNull(emptyStats.getStatsPerHost().get(hostname));
  }
  }

  @Test
  public void testClientStatus_6_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      assertEquals(activeStats.toString(), "There are 5 total connections, 5 are active and 0 are idle.");
  }
  }

  @Test
  public void testClientStatus_7_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      assertEquals(activeStats.getTotalActiveConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatus_8_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(activeStats.getTotalIdleConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatus_9_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(activeStats.getTotalConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatus_10_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(activeStats.getStatsPerHost().get(hostname).getHostConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatus_11_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      assertEquals(idleStats.toString(), "There are 5 total connections, 0 are active and 5 are idle.");
  }
  }

  @Test
  public void testClientStatus_12_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      assertEquals(idleStats.getTotalActiveConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatus_13_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(idleStats.getTotalIdleConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatus_14_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(idleStats.getTotalConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatus_15_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(idleStats.getStatsPerHost().get(hostname).getHostConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatus_16_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      assertEquals(activeCachedStats.toString(), "There are 5 total connections, 3 are active and 2 are idle.");
  }
  }

  @Test
  public void testClientStatus_17_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      assertEquals(activeCachedStats.getTotalActiveConnectionCount(), 3);
  }
  }

  @Test
  public void testClientStatus_18_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(activeCachedStats.getTotalIdleConnectionCount(), 2);
  }
  }

  @Test
  public void testClientStatus_19_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(activeCachedStats.getTotalConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatus_20_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(activeCachedStats.getStatsPerHost().get(hostname).getHostConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatus_21_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      assertEquals(idleCachedStats.toString(), "There are 3 total connections, 0 are active and 3 are idle.");
  }
  }

  @Test
  public void testClientStatus_22_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      assertEquals(idleCachedStats.getTotalActiveConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatus_23_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(idleCachedStats.getTotalIdleConnectionCount(), 3);
  }
  }

  @Test
  public void testClientStatus_24_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(idleCachedStats.getTotalConnectionCount(), 3);
  }
  }

  @Test
  public void testClientStatus_25_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(idleCachedStats.getStatsPerHost().get(hostname).getHostConnectionCount(), 3);
  }
  }

  @Test
  public void testClientStatus_26_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      Thread.sleep(5000);

      final ClientStats timeoutStats = client.getClientStats();

      assertEquals(timeoutStats.toString(), "There are 0 total connections, 0 are active and 0 are idle.");
  }
  }

  @Test
  public void testClientStatus_27_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      Thread.sleep(5000);

      final ClientStats timeoutStats = client.getClientStats();

      // removed other assertion
      assertEquals(timeoutStats.getTotalActiveConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatus_28_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      Thread.sleep(5000);

      final ClientStats timeoutStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(timeoutStats.getTotalIdleConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatus_29_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      Thread.sleep(5000);

      final ClientStats timeoutStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(timeoutStats.getTotalConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatus_30_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(true).setPooledConnectionIdleTimeout(5000))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      Thread.sleep(5000);

      final ClientStats timeoutStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertNull(timeoutStats.getStatsPerHost().get(hostname));
  }
  }

  @Test
  public void testClientStatusNoKeepalive_1_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      assertEquals(emptyStats.toString(), "There are 0 total connections, 0 are active and 0 are idle.");
  }
  }

  @Test
  public void testClientStatusNoKeepalive_2_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      assertEquals(emptyStats.getTotalActiveConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_3_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(emptyStats.getTotalIdleConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_4_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(emptyStats.getTotalConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_5_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertNull(emptyStats.getStatsPerHost().get(hostname));
  }
  }

  @Test
  public void testClientStatusNoKeepalive_6_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      assertEquals(activeStats.toString(), "There are 5 total connections, 5 are active and 0 are idle.");
  }
  }

  @Test
  public void testClientStatusNoKeepalive_7_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      assertEquals(activeStats.getTotalActiveConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_8_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(activeStats.getTotalIdleConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_9_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(activeStats.getTotalConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_10_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(activeStats.getStatsPerHost().get(hostname).getHostConnectionCount(), 5);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_11_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      assertEquals(idleStats.toString(), "There are 0 total connections, 0 are active and 0 are idle.");
  }
  }

  @Test
  public void testClientStatusNoKeepalive_12_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      assertEquals(idleStats.getTotalActiveConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_13_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(idleStats.getTotalIdleConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_14_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(idleStats.getTotalConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_15_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertNull(idleStats.getStatsPerHost().get(hostname));
  }
  }

  @Test
  public void testClientStatusNoKeepalive_16_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      assertEquals(activeCachedStats.toString(), "There are 3 total connections, 3 are active and 0 are idle.");
  }
  }

  @Test
  public void testClientStatusNoKeepalive_17_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      assertEquals(activeCachedStats.getTotalActiveConnectionCount(), 3);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_18_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(activeCachedStats.getTotalIdleConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_19_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(activeCachedStats.getTotalConnectionCount(), 3);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_20_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(activeCachedStats.getStatsPerHost().get(hostname).getHostConnectionCount(), 3);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_21_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      assertEquals(idleCachedStats.toString(), "There are 0 total connections, 0 are active and 0 are idle.");
  }
  }

  @Test
  public void testClientStatusNoKeepalive_22_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      assertEquals(idleCachedStats.getTotalActiveConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_23_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      assertEquals(idleCachedStats.getTotalIdleConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_24_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals(idleCachedStats.getTotalConnectionCount(), 0);
  }
  }

  @Test
  public void testClientStatusNoKeepalive_25_oe() throws Throwable {
    try (final AsyncHttpClient client = asyncHttpClient(config().setKeepAlive(false))) {
      final String url = getTargetUrl();

      final ClientStats emptyStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      final List<ListenableFuture<Response>> futures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(5)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      futures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // Let's make sure the active count is correct when reusing cached connections.

      final List<ListenableFuture<Response>> repeatedFutures =
              Stream.generate(() -> client.prepareGet(url).setHeader("LockThread", "6").execute())
                      .limit(3)
                      .collect(Collectors.toList());

      Thread.sleep(2000);

      final ClientStats activeCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      repeatedFutures.forEach(future -> future.toCompletableFuture().join());

      Thread.sleep(1000);

      final ClientStats idleCachedStats = client.getClientStats();

      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertNull(idleCachedStats.getStatsPerHost().get(hostname));
  }
  }

}
