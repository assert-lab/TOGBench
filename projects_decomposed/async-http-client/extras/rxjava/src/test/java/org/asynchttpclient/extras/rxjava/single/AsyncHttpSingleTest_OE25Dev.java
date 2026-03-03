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
package org.asynchttpclient.extras.rxjava.single;

import org.asynchttpclient.*;
import org.asynchttpclient.extras.rxjava.UnsubscribedException;
import org.asynchttpclient.handler.ProgressAsyncHandler;
import org.mockito.InOrder;
import org.testng.annotations.Test;
import rx.Single;
import rx.exceptions.CompositeException;
import rx.observers.TestSubscriber;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class AsyncHttpSingleTest_OE25Dev {

  @Test(expectedExceptions = {NullPointerException.class})
  public void testFailsOnNullRequest() {
    AsyncHttpSingle.create((BoundRequestBuilder) null);
  }

  @Test(expectedExceptions = {NullPointerException.class})
  public void testFailsOnNullHandlerSupplier() {
    AsyncHttpSingle.create(mock(BoundRequestBuilder.class), null);
  }

  @Test
  public void testNewRequestForEachSubscription() {
    final BoundRequestBuilder builder = mock(BoundRequestBuilder.class);

    final Single<?> underTest = AsyncHttpSingle.create(builder);
    underTest.subscribe(new TestSubscriber<>());
    underTest.subscribe(new TestSubscriber<>());

    verify(builder, times(2)).execute(any());
    verifyNoMoreInteractions(builder);
  }

  @Test
  public void testErrorInOnThrowablePropagation_3_oe() {

    final RuntimeException processingException = new RuntimeException("processing");
    final RuntimeException thrownException = new RuntimeException("thrown");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doThrow(thrownException).when(handler).onThrowable(processingException);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onThrowable(processingException);
        return mock(Future.class);
      } catch (final Throwable t) {
        throw new AssertionError(t);
      }
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verify(handler).onThrowable(processingException);
    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();

    final List<Throwable> errorEvents = subscriber.getOnErrorEvents();
    assertEquals(errorEvents.size(), 1);
  }

  @Test
  public void testErrorInOnThrowablePropagation_4_oe() {

    final RuntimeException processingException = new RuntimeException("processing");
    final RuntimeException thrownException = new RuntimeException("thrown");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doThrow(thrownException).when(handler).onThrowable(processingException);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onThrowable(processingException);
        return mock(Future.class);
      } catch (final Throwable t) {
        throw new AssertionError(t);
      }
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verify(handler).onThrowable(processingException);
    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();

    final List<Throwable> errorEvents = subscriber.getOnErrorEvents();
    assertThat(errorEvents.get(0), is(instanceOf(CompositeException.class)));
  }

  @Test
  public void testErrorInOnThrowablePropagation_5_oe() {

    final RuntimeException processingException = new RuntimeException("processing");
    final RuntimeException thrownException = new RuntimeException("thrown");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doThrow(thrownException).when(handler).onThrowable(processingException);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onThrowable(processingException);
        return mock(Future.class);
      } catch (final Throwable t) {
        throw new AssertionError(t);
      }
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verify(handler).onThrowable(processingException);
    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();

    final List<Throwable> errorEvents = subscriber.getOnErrorEvents();
    final CompositeException error = (CompositeException) errorEvents.get(0);
    assertEquals(error.getExceptions(), Arrays.asList(processingException, thrownException));
  }

  @Test
  public void testUnsubscribe_1_oe() throws Exception {
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    final Future<?> future = mock(Future.class);
    final AtomicReference<AsyncHandler<?>> bridgeRef = new AtomicReference<>();

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      bridgeRef.set(bridge);
      return future;
    }, () -> handler);

    underTest.subscribe().unsubscribe();
    verify(future).cancel(true);
    verifyZeroInteractions(handler);

    assertThat(bridgeRef.get().onStatusReceived(null), is(AsyncHandler.State.ABORT));
  }

@Test
  public void testSuccessfulCompletion_2_oe() throws Exception {

    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {

        bridge.onStatusReceived(null);
        verify(handler).onStatusReceived(null);

        bridge.onHeadersReceived(null);
        verify(handler).onHeadersReceived(null);

        bridge.onBodyPartReceived(null);
        verify(handler).onBodyPartReceived(null);

        bridge.onTrailingHeadersReceived(null);
        verify(handler).onTrailingHeadersReceived(null);

        bridge.onCompleted();
        verify(handler).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();
    subscriber.assertTerminalEvent();
  }

@Test
  public void testSuccessfulCompletion_3_oe() throws Exception {

    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {

        bridge.onStatusReceived(null);
        verify(handler).onStatusReceived(null);

        bridge.onHeadersReceived(null);
        verify(handler).onHeadersReceived(null);

        bridge.onBodyPartReceived(null);
        verify(handler).onBodyPartReceived(null);

        bridge.onTrailingHeadersReceived(null);
        verify(handler).onTrailingHeadersReceived(null);

        bridge.onCompleted();
        verify(handler).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();
    subscriber.assertNoErrors();
  }

@Test
  public void testSuccessfulCompletion_4_oe() throws Exception {

    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {

        bridge.onStatusReceived(null);
        verify(handler).onStatusReceived(null);

        bridge.onHeadersReceived(null);
        verify(handler).onHeadersReceived(null);

        bridge.onBodyPartReceived(null);
        verify(handler).onBodyPartReceived(null);

        bridge.onTrailingHeadersReceived(null);
        verify(handler).onTrailingHeadersReceived(null);

        bridge.onCompleted();
        verify(handler).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();
    subscriber.assertCompleted();
  }

@Test
  public void testSuccessfulCompletion_5_oe() throws Exception {

    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {

        bridge.onStatusReceived(null);
        verify(handler).onStatusReceived(null);

        bridge.onHeadersReceived(null);
        verify(handler).onHeadersReceived(null);

        bridge.onBodyPartReceived(null);
        verify(handler).onBodyPartReceived(null);

        bridge.onTrailingHeadersReceived(null);
        verify(handler).onTrailingHeadersReceived(null);

        bridge.onCompleted();
        verify(handler).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();
    subscriber.assertValue(handler);
  }

@Test
  public void testSuccessfulCompletionWithProgress_2_oe() throws Exception {

    @SuppressWarnings("unchecked") final ProgressAsyncHandler<Object> handler = mock(ProgressAsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);
    final InOrder inOrder = inOrder(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {

        final ProgressAsyncHandler<?> progressBridge = (ProgressAsyncHandler<?>) bridge;

        progressBridge.onHeadersWritten();
        inOrder.verify(handler).onHeadersWritten();

        progressBridge.onContentWriteProgress(60, 40, 100);
        inOrder.verify(handler).onContentWriteProgress(60, 40, 100);

        progressBridge.onContentWritten();
        inOrder.verify(handler).onContentWritten();

        progressBridge.onStatusReceived(null);
        inOrder.verify(handler).onStatusReceived(null);

        progressBridge.onHeadersReceived(null);
        inOrder.verify(handler).onHeadersReceived(null);

        progressBridge.onBodyPartReceived(null);
        inOrder.verify(handler).onBodyPartReceived(null);

        bridge.onTrailingHeadersReceived(null);
        verify(handler).onTrailingHeadersReceived(null);

        progressBridge.onCompleted();
        inOrder.verify(handler).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    inOrder.verifyNoMoreInteractions();

    subscriber.awaitTerminalEvent();
    subscriber.assertTerminalEvent();
  }

@Test
  public void testSuccessfulCompletionWithProgress_3_oe() throws Exception {

    @SuppressWarnings("unchecked") final ProgressAsyncHandler<Object> handler = mock(ProgressAsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);
    final InOrder inOrder = inOrder(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {

        final ProgressAsyncHandler<?> progressBridge = (ProgressAsyncHandler<?>) bridge;

        progressBridge.onHeadersWritten();
        inOrder.verify(handler).onHeadersWritten();

        progressBridge.onContentWriteProgress(60, 40, 100);
        inOrder.verify(handler).onContentWriteProgress(60, 40, 100);

        progressBridge.onContentWritten();
        inOrder.verify(handler).onContentWritten();

        progressBridge.onStatusReceived(null);
        inOrder.verify(handler).onStatusReceived(null);

        progressBridge.onHeadersReceived(null);
        inOrder.verify(handler).onHeadersReceived(null);

        progressBridge.onBodyPartReceived(null);
        inOrder.verify(handler).onBodyPartReceived(null);

        bridge.onTrailingHeadersReceived(null);
        verify(handler).onTrailingHeadersReceived(null);

        progressBridge.onCompleted();
        inOrder.verify(handler).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    inOrder.verifyNoMoreInteractions();

    subscriber.awaitTerminalEvent();
    subscriber.assertNoErrors();
  }

@Test
  public void testSuccessfulCompletionWithProgress_4_oe() throws Exception {

    @SuppressWarnings("unchecked") final ProgressAsyncHandler<Object> handler = mock(ProgressAsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);
    final InOrder inOrder = inOrder(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {

        final ProgressAsyncHandler<?> progressBridge = (ProgressAsyncHandler<?>) bridge;

        progressBridge.onHeadersWritten();
        inOrder.verify(handler).onHeadersWritten();

        progressBridge.onContentWriteProgress(60, 40, 100);
        inOrder.verify(handler).onContentWriteProgress(60, 40, 100);

        progressBridge.onContentWritten();
        inOrder.verify(handler).onContentWritten();

        progressBridge.onStatusReceived(null);
        inOrder.verify(handler).onStatusReceived(null);

        progressBridge.onHeadersReceived(null);
        inOrder.verify(handler).onHeadersReceived(null);

        progressBridge.onBodyPartReceived(null);
        inOrder.verify(handler).onBodyPartReceived(null);

        bridge.onTrailingHeadersReceived(null);
        verify(handler).onTrailingHeadersReceived(null);

        progressBridge.onCompleted();
        inOrder.verify(handler).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    inOrder.verifyNoMoreInteractions();

    subscriber.awaitTerminalEvent();
    subscriber.assertCompleted();
  }

@Test
  public void testSuccessfulCompletionWithProgress_5_oe() throws Exception {

    @SuppressWarnings("unchecked") final ProgressAsyncHandler<Object> handler = mock(ProgressAsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);
    final InOrder inOrder = inOrder(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {

        final ProgressAsyncHandler<?> progressBridge = (ProgressAsyncHandler<?>) bridge;

        progressBridge.onHeadersWritten();
        inOrder.verify(handler).onHeadersWritten();

        progressBridge.onContentWriteProgress(60, 40, 100);
        inOrder.verify(handler).onContentWriteProgress(60, 40, 100);

        progressBridge.onContentWritten();
        inOrder.verify(handler).onContentWritten();

        progressBridge.onStatusReceived(null);
        inOrder.verify(handler).onStatusReceived(null);

        progressBridge.onHeadersReceived(null);
        inOrder.verify(handler).onHeadersReceived(null);

        progressBridge.onBodyPartReceived(null);
        inOrder.verify(handler).onBodyPartReceived(null);

        bridge.onTrailingHeadersReceived(null);
        verify(handler).onTrailingHeadersReceived(null);

        progressBridge.onCompleted();
        inOrder.verify(handler).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    inOrder.verifyNoMoreInteractions();

    subscriber.awaitTerminalEvent();
    subscriber.assertValue(handler);
  }

@Test
  public void testErrorPropagation_1_oe() throws Exception {

    final RuntimeException expectedException = new RuntimeException("expected");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);
    final InOrder inOrder = inOrder(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onStatusReceived(null);
        inOrder.verify(handler).onStatusReceived(null);

        bridge.onHeadersReceived(null);
        inOrder.verify(handler).onHeadersReceived(null);

        bridge.onBodyPartReceived(null);
        inOrder.verify(handler).onBodyPartReceived(null);

        bridge.onThrowable(expectedException);
        inOrder.verify(handler).onThrowable(expectedException);

        bridge.onCompleted();
        inOrder.verify(handler, never()).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    inOrder.verifyNoMoreInteractions();

    subscriber.awaitTerminalEvent();
    subscriber.assertTerminalEvent();
  }

@Test
  public void testErrorPropagation_2_oe() throws Exception {

    final RuntimeException expectedException = new RuntimeException("expected");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);
    final InOrder inOrder = inOrder(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onStatusReceived(null);
        inOrder.verify(handler).onStatusReceived(null);

        bridge.onHeadersReceived(null);
        inOrder.verify(handler).onHeadersReceived(null);

        bridge.onBodyPartReceived(null);
        inOrder.verify(handler).onBodyPartReceived(null);

        bridge.onThrowable(expectedException);
        inOrder.verify(handler).onThrowable(expectedException);

        bridge.onCompleted();
        inOrder.verify(handler, never()).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    inOrder.verifyNoMoreInteractions();

    subscriber.awaitTerminalEvent();
    subscriber.assertNoValues();
  }

@Test
  public void testErrorPropagation_3_oe() throws Exception {

    final RuntimeException expectedException = new RuntimeException("expected");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    when(handler.onCompleted()).thenReturn(handler);
    final InOrder inOrder = inOrder(handler);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onStatusReceived(null);
        inOrder.verify(handler).onStatusReceived(null);

        bridge.onHeadersReceived(null);
        inOrder.verify(handler).onHeadersReceived(null);

        bridge.onBodyPartReceived(null);
        inOrder.verify(handler).onBodyPartReceived(null);

        bridge.onThrowable(expectedException);
        inOrder.verify(handler).onThrowable(expectedException);

        bridge.onCompleted();
        inOrder.verify(handler, never()).onCompleted();
      } catch (final Throwable t) {
        bridge.onThrowable(t);
      }

      return mock(Future.class);
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    inOrder.verifyNoMoreInteractions();

    subscriber.awaitTerminalEvent();
    subscriber.assertError(expectedException);
  }

@Test
  public void testErrorInOnCompletedPropagation_1_oe() throws Exception {

    final RuntimeException expectedException = new RuntimeException("expected");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    when(handler.onCompleted()).thenThrow(expectedException);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onCompleted();
        return mock(Future.class);
      } catch (final Throwable t) {
        throw new AssertionError(t);
      }
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verify(handler).onCompleted();
    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();
    subscriber.assertTerminalEvent();
  }

@Test
  public void testErrorInOnCompletedPropagation_2_oe() throws Exception {

    final RuntimeException expectedException = new RuntimeException("expected");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    when(handler.onCompleted()).thenThrow(expectedException);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onCompleted();
        return mock(Future.class);
      } catch (final Throwable t) {
        throw new AssertionError(t);
      }
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verify(handler).onCompleted();
    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();
    subscriber.assertNoValues();
  }

@Test
  public void testErrorInOnCompletedPropagation_3_oe() throws Exception {

    final RuntimeException expectedException = new RuntimeException("expected");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    when(handler.onCompleted()).thenThrow(expectedException);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onCompleted();
        return mock(Future.class);
      } catch (final Throwable t) {
        throw new AssertionError(t);
      }
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verify(handler).onCompleted();
    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();
    subscriber.assertError(expectedException);
  }

@Test
  public void testErrorInOnThrowablePropagation_1_oe() {

    final RuntimeException processingException = new RuntimeException("processing");
    final RuntimeException thrownException = new RuntimeException("thrown");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doThrow(thrownException).when(handler).onThrowable(processingException);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onThrowable(processingException);
        return mock(Future.class);
      } catch (final Throwable t) {
        throw new AssertionError(t);
      }
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verify(handler).onThrowable(processingException);
    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();
    subscriber.assertTerminalEvent();
  }

@Test
  public void testErrorInOnThrowablePropagation_2_oe() {

    final RuntimeException processingException = new RuntimeException("processing");
    final RuntimeException thrownException = new RuntimeException("thrown");
    @SuppressWarnings("unchecked") final AsyncHandler<Object> handler = mock(AsyncHandler.class);
    doThrow(thrownException).when(handler).onThrowable(processingException);

    final Single<?> underTest = AsyncHttpSingle.create(bridge -> {
      try {
        bridge.onThrowable(processingException);
        return mock(Future.class);
      } catch (final Throwable t) {
        throw new AssertionError(t);
      }
    }, () -> handler);

    final TestSubscriber<Object> subscriber = new TestSubscriber<>();
    underTest.subscribe(subscriber);

    verify(handler).onThrowable(processingException);
    verifyNoMoreInteractions(handler);

    subscriber.awaitTerminalEvent();
    subscriber.assertNoValues();
  }

@Test
  public void testAbort_1_oe() throws Exception {
    final TestSubscriber<Response> subscriber = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      final Single<Response> underTest = AsyncHttpSingle.create(client.prepareGet("http://gatling.io"),
              () -> new AsyncCompletionHandlerBase() {
                @Override
                public State onStatusReceived(HttpResponseStatus status) {
                  return State.ABORT;
                }
              });

      underTest.subscribe(subscriber);
      subscriber.awaitTerminalEvent();
    }

    subscriber.assertTerminalEvent();
  }

@Test
  public void testAbort_2_oe() throws Exception {
    final TestSubscriber<Response> subscriber = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      final Single<Response> underTest = AsyncHttpSingle.create(client.prepareGet("http://gatling.io"),
              () -> new AsyncCompletionHandlerBase() {
                @Override
                public State onStatusReceived(HttpResponseStatus status) {
                  return State.ABORT;
                }
              });

      underTest.subscribe(subscriber);
      subscriber.awaitTerminalEvent();
    }

    subscriber.assertNoErrors();
  }

@Test
  public void testAbort_3_oe() throws Exception {
    final TestSubscriber<Response> subscriber = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      final Single<Response> underTest = AsyncHttpSingle.create(client.prepareGet("http://gatling.io"),
              () -> new AsyncCompletionHandlerBase() {
                @Override
                public State onStatusReceived(HttpResponseStatus status) {
                  return State.ABORT;
                }
              });

      underTest.subscribe(subscriber);
      subscriber.awaitTerminalEvent();
    }

    subscriber.assertCompleted();
  }

@Test
  public void testAbort_4_oe() throws Exception {
    final TestSubscriber<Response> subscriber = new TestSubscriber<>();

    try (AsyncHttpClient client = asyncHttpClient()) {
      final Single<Response> underTest = AsyncHttpSingle.create(client.prepareGet("http://gatling.io"),
              () -> new AsyncCompletionHandlerBase() {
                @Override
                public State onStatusReceived(HttpResponseStatus status) {
                  return State.ABORT;
                }
              });

      underTest.subscribe(subscriber);
      subscriber.awaitTerminalEvent();
    }

    subscriber.assertValue(null);
  }

}
