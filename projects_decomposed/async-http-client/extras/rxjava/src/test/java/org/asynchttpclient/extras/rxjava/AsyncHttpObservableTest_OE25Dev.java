/*
 * Copyright (c) 2015 AsyncHttpClient Project. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package org.asynchttpclient.extras.rxjava;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.testng.annotations.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.List;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class AsyncHttpObservableTest_OE25Dev {

  @Test
  public void testToObservableNoError_1_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.toObservable(() -> client.prepareGet("https://gatling.io"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      assertNotNull(responses);
  }
  }

  @Test
  public void testToObservableNoError_2_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.toObservable(() -> client.prepareGet("https://gatling.io"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      // removed other assertion
      assertEquals(responses.size(), 1);
  }
  }

  @Test
  public void testToObservableNoError_3_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.toObservable(() -> client.prepareGet("https://gatling.io"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      // removed other assertion
      // removed other assertion
      assertEquals(responses.get(0).getStatusCode(), 200);
  }
  }

  @Test
  public void testToObservableError_1_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.toObservable(() -> client.prepareGet("https://gatling.io/ttfn"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      assertNotNull(responses);
  }
  }

  @Test
  public void testToObservableError_2_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.toObservable(() -> client.prepareGet("https://gatling.io/ttfn"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      // removed other assertion
      assertEquals(responses.size(), 1);
  }
  }

  @Test
  public void testToObservableError_3_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.toObservable(() -> client.prepareGet("https://gatling.io/ttfn"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      // removed other assertion
      // removed other assertion
      assertEquals(responses.get(0).getStatusCode(), 404);
  }
  }

  @Test
  public void testObserveNoError_1_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.observe(() -> client.prepareGet("https://gatling.io"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      assertNotNull(responses);
  }
  }

  @Test
  public void testObserveNoError_2_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.observe(() -> client.prepareGet("https://gatling.io"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      // removed other assertion
      assertEquals(responses.size(), 1);
  }
  }

  @Test
  public void testObserveNoError_3_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.observe(() -> client.prepareGet("https://gatling.io"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      // removed other assertion
      // removed other assertion
      assertEquals(responses.get(0).getStatusCode(), 200);
  }
  }

  @Test
  public void testObserveError_1_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.observe(() -> client.prepareGet("https://gatling.io/ttfn"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      assertNotNull(responses);
  }
  }

  @Test
  public void testObserveError_2_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.observe(() -> client.prepareGet("https://gatling.io/ttfn"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      // removed other assertion
      assertEquals(responses.size(), 1);
  }
  }

  @Test
  public void testObserveError_3_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.observe(() -> client.prepareGet("https://gatling.io/ttfn"));
      o1.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      // removed other assertion
      // removed other assertion
      assertEquals(responses.get(0).getStatusCode(), 404);
  }
  }

  @Test
  public void testObserveMultiple_1_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.observe(() -> client.prepareGet("https://gatling.io"));
      Observable<Response> o2 = AsyncHttpObservable.observe(() -> client.prepareGet("http://www.wisc.edu").setFollowRedirect(true));
      Observable<Response> o3 = AsyncHttpObservable.observe(() -> client.prepareGet("http://www.umn.edu").setFollowRedirect(true));
      Observable<Response> all = Observable.merge(o1, o2, o3);
      all.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      assertNotNull(responses);
  }
  }

  @Test
  public void testObserveMultiple_2_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.observe(() -> client.prepareGet("https://gatling.io"));
      Observable<Response> o2 = AsyncHttpObservable.observe(() -> client.prepareGet("http://www.wisc.edu").setFollowRedirect(true));
      Observable<Response> o3 = AsyncHttpObservable.observe(() -> client.prepareGet("http://www.umn.edu").setFollowRedirect(true));
      Observable<Response> all = Observable.merge(o1, o2, o3);
      all.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      // removed other assertion
      assertEquals(responses.size(), 3);
  }
  }

  @Test
  public void testObserveMultiple_3_oe() {
    final TestSubscriber<Response> tester = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      Observable<Response> o1 = AsyncHttpObservable.observe(() -> client.prepareGet("https://gatling.io"));
      Observable<Response> o2 = AsyncHttpObservable.observe(() -> client.prepareGet("http://www.wisc.edu").setFollowRedirect(true));
      Observable<Response> o3 = AsyncHttpObservable.observe(() -> client.prepareGet("http://www.umn.edu").setFollowRedirect(true));
      Observable<Response> all = Observable.merge(o1, o2, o3);
      all.subscribe(tester);
      tester.awaitTerminalEvent();
      tester.assertTerminalEvent();
      tester.assertNoErrors();
      tester.assertCompleted();
      List<Response> responses = tester.getOnNextEvents();
      // removed other assertion
      // removed other assertion
      for (Response response : responses) {
        assertEquals(response.getStatusCode(), 200);
  }
  }
  }

}
