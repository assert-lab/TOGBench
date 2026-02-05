package org.springside.modules.utils.concurrent;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.springside.modules.utils.base.ExceptionUtil;

public class BasicFutureTest_OE25Dev {

	public static class MyFuture<T> extends BasicFuture<T> {

		@Override
		protected void onCompleted(T result) {
			System.out.println("onCompleted:" + result);
		}

		@Override
		protected void onFailed(Exception ex) {
			System.out.println("onFailed:" + ex.getMessage());
		}

		@Override
		protected void onCancelled() {
			System.out.println("onCancelled");
		}
	}

	private static class Tasks {

		public static void success(MyFuture<String> future) {
			future.completed("haha");
		}

		public static void fail(MyFuture<String> future) {
			future.failed(new RuntimeException("wuwu"));
		}

		public static void cancel(MyFuture<String> future) {
			future.cancel(true);
		}
	}

	@Test
	public void test_1_oe() throws InterruptedException, ExecutionException {
		MyFuture<String> future = new MyFuture<String>();
		Tasks.success(future);
		String result = future.get();
		assertThat(result).isEqualTo("haha");
	}

	@Test
	public void test_3_oe() throws InterruptedException, ExecutionException {
		MyFuture<String> future = new MyFuture<String>();
		Tasks.success(future);
		String result = future.get();
		// removed other assertion

		// 无人设置返回值
		try {
			MyFuture<String> future2 = new MyFuture<String>();
			future2.get(10, TimeUnit.MILLISECONDS);
			// removed other assertion
		} catch (TimeoutException e) {
			assertThat(e).isInstanceOf(TimeoutException.class);
	}
	}

	@Test
	public void test_6_oe() throws InterruptedException, ExecutionException {
		MyFuture<String> future = new MyFuture<String>();
		Tasks.success(future);
		String result = future.get();
		// removed other assertion

		// 无人设置返回值
		try {
			MyFuture<String> future2 = new MyFuture<String>();
			future2.get(10, TimeUnit.MILLISECONDS);
			// removed other assertion
		} catch (TimeoutException e) {
			// removed other assertion
		}

		// 失败
		try {
			MyFuture<String> future3 = new MyFuture<String>();
			// removed other assertion
			future3.get();
			// removed other assertion
		} catch (Throwable t) {
			assertThat(ExceptionUtil.unwrap(t)).hasMessage("wuwu");
	}
	}

	@Test
	public void test_7_oe() throws InterruptedException, ExecutionException {
		MyFuture<String> future = new MyFuture<String>();
		Tasks.success(future);
		String result = future.get();
		// removed other assertion

		// 无人设置返回值
		try {
			MyFuture<String> future2 = new MyFuture<String>();
			future2.get(10, TimeUnit.MILLISECONDS);
			// removed other assertion
		} catch (TimeoutException e) {
			// removed other assertion
		}

		// 失败
		try {
			MyFuture<String> future3 = new MyFuture<String>();
			// removed other assertion
			future3.get();
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// 取消
		MyFuture<String> future4 = new MyFuture<String>();
		Tasks.cancel(future4);
		String result4 = future4.get();
		assertThat(result4).isNull();
	}

	@Test
	public void test_8_oe() throws InterruptedException, ExecutionException {
		MyFuture<String> future = new MyFuture<String>();
		Tasks.success(future);
		String result = future.get();
		// removed other assertion

		// 无人设置返回值
		try {
			MyFuture<String> future2 = new MyFuture<String>();
			future2.get(10, TimeUnit.MILLISECONDS);
			// removed other assertion
		} catch (TimeoutException e) {
			// removed other assertion
		}

		// 失败
		try {
			MyFuture<String> future3 = new MyFuture<String>();
			// removed other assertion
			future3.get();
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// 取消
		MyFuture<String> future4 = new MyFuture<String>();
		Tasks.cancel(future4);
		String result4 = future4.get();
		// removed other assertion
		assertThat(future4.isCancelled()).isTrue();
	}

}
