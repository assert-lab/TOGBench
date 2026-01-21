/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.modules.utils.base;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.springside.modules.utils.base.ExceptionUtil.CloneableException;
import org.springside.modules.utils.base.ExceptionUtil.CloneableRuntimeException;
import org.springside.modules.utils.base.ExceptionUtil.UncheckedException;

public class ExceptionUtilTest_OE25Dev {

	private static RuntimeException TIMEOUT_EXCEPTION = ExceptionUtil.setStackTrace(new RuntimeException("Timeout"),
			ExceptionUtilTest.class, "hello");

	private static CloneableException TIMEOUT_EXCEPTION2 = new CloneableException("Timeout")
			.setStackTrace(ExceptionUtilTest.class, "hello");

	private static CloneableRuntimeException TIMEOUT_EXCEPTION3 = new CloneableRuntimeException("Timeout")
			.setStackTrace(ExceptionUtilTest.class, "hello");

	@Test
	public void getStackTraceAsString() {
		Exception exception = new Exception("my exception");
		RuntimeException runtimeException = new RuntimeException(exception);

		String stack = ExceptionUtil.stackTraceText(runtimeException);
		System.out.println(stack);
	}

	@Test
	public void clearStackTrace() {
		IOException ioexception = new IOException("my exception");
		RuntimeException runtimeException = new RuntimeException(ioexception);

		System.out.println(ExceptionUtil.stackTraceText(ExceptionUtil.clearStackTrace(runtimeException)));

	}

	@Test
	public void unchecked_2_oe() {
		// convert Exception to RuntimeException with cause
		Exception exception = new Exception("my exception");
		try {
			ExceptionUtil.unchecked(exception);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t.getCause()).isSameAs(exception);
	}
	}

	@Test
	public void unchecked_4_oe() {
		// convert Exception to RuntimeException with cause
		Exception exception = new Exception("my exception");
		try {
			ExceptionUtil.unchecked(exception);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// do nothing of Error
		Error error = new LinkageError();
		try {
			ExceptionUtil.unchecked(error);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isSameAs(error);
	}
	}

	@Test
	public void unchecked_6_oe() {
		// convert Exception to RuntimeException with cause
		Exception exception = new Exception("my exception");
		try {
			ExceptionUtil.unchecked(exception);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// do nothing of Error
		Error error = new LinkageError();
		try {
			ExceptionUtil.unchecked(error);
			// removed other assertion
		} catch (Throwable t) {
			// removed other assertion
		}

		// do nothing of RuntimeException
		RuntimeException runtimeException = new RuntimeException("haha");
		try {
			ExceptionUtil.unchecked(runtimeException);
			// removed other assertion
		} catch (Throwable t) {
			assertThat(t).isSameAs(runtimeException);
	}
	}

	@Test
	public void unwrap_1_oe() {
		RuntimeException re = new RuntimeException("my runtime");
		assertThat(ExceptionUtil.unwrap(re)).isSameAs(re);
	}

	@Test
	public void unwrap_2_oe() {
		RuntimeException re = new RuntimeException("my runtime");
		// removed other assertion

		ExecutionException ee = new ExecutionException(re);
		assertThat(ExceptionUtil.unwrap(ee)).isSameAs(re);
	}

	@Test
	public void unwrap_3_oe() {
		RuntimeException re = new RuntimeException("my runtime");
		// removed other assertion

		ExecutionException ee = new ExecutionException(re);
		// removed other assertion

		InvocationTargetException ie = new InvocationTargetException(re);
		assertThat(ExceptionUtil.unwrap(ie)).isSameAs(re);
	}

	@Test
	public void unwrap_4_oe() {
		RuntimeException re = new RuntimeException("my runtime");
		// removed other assertion

		ExecutionException ee = new ExecutionException(re);
		// removed other assertion

		InvocationTargetException ie = new InvocationTargetException(re);
		// removed other assertion
		
		Exception e = new Exception("my exception");
		ExecutionException ee2 = new ExecutionException(e);
		try{
		ExceptionUtil.uncheckedAndWrap(ee2);
		}catch (Throwable t) {
			assertThat(t).isInstanceOf(UncheckedException.class).hasCauseExactlyInstanceOf(Exception.class);
	}
	}

	@Test
	public void isCausedBy_1_oe() {
		IOException ioexception = new IOException("my exception");
		IllegalStateException illegalStateException = new IllegalStateException(ioexception);
		RuntimeException runtimeException = new RuntimeException(illegalStateException);

		assertThat(ExceptionUtil.isCausedBy(runtimeException, IOException.class)).isTrue();
	}

	@Test
	public void isCausedBy_2_oe() {
		IOException ioexception = new IOException("my exception");
		IllegalStateException illegalStateException = new IllegalStateException(ioexception);
		RuntimeException runtimeException = new RuntimeException(illegalStateException);

		// removed other assertion
		assertThat(ExceptionUtil.isCausedBy(runtimeException, IllegalStateException.class, IOException.class)).isTrue();
	}

	@Test
	public void isCausedBy_3_oe() {
		IOException ioexception = new IOException("my exception");
		IllegalStateException illegalStateException = new IllegalStateException(ioexception);
		RuntimeException runtimeException = new RuntimeException(illegalStateException);

		// removed other assertion
		// removed other assertion
		assertThat(ExceptionUtil.isCausedBy(runtimeException, Exception.class)).isTrue();
	}

	@Test
	public void isCausedBy_4_oe() {
		IOException ioexception = new IOException("my exception");
		IllegalStateException illegalStateException = new IllegalStateException(ioexception);
		RuntimeException runtimeException = new RuntimeException(illegalStateException);

		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(ExceptionUtil.isCausedBy(runtimeException, IllegalAccessException.class)).isFalse();
	}

	@Test
	public void getRootCause_1_oe() {
		IOException ioexception = new IOException("my exception");
		IllegalStateException illegalStateException = new IllegalStateException(ioexception);
		RuntimeException runtimeException = new RuntimeException(illegalStateException);

		assertThat(ExceptionUtil.getRootCause(runtimeException)).isSameAs(ioexception);
	}

	@Test
	public void getRootCause_2_oe() {
		IOException ioexception = new IOException("my exception");
		IllegalStateException illegalStateException = new IllegalStateException(ioexception);
		RuntimeException runtimeException = new RuntimeException(illegalStateException);

		// removed other assertion
		// 无cause
		assertThat(ExceptionUtil.getRootCause(ioexception)).isSameAs(ioexception);
	}

	@Test
	public void buildMessage_1_oe() {
		IOException ioexception = new IOException("my exception");
		assertThat(ExceptionUtil.toStringWithShortName(ioexception)).isEqualTo("IOException: my exception");
	}

	@Test
	public void buildMessage_2_oe() {
		IOException ioexception = new IOException("my exception");
		// removed other assertion
		assertThat(ExceptionUtil.toStringWithShortName(null)).isEqualTo("");
	}

	@Test
	public void buildMessage_3_oe() {
		IOException ioexception = new IOException("my exception");
		// removed other assertion
		// removed other assertion

		RuntimeException runtimeExcetpion = new RuntimeException("my runtimeException", ioexception);
		assertThat(ExceptionUtil.toStringWithRootCause(runtimeExcetpion)) .isEqualTo("RuntimeException: my runtimeException; <---IOException: my exception");
	}

	@Test
	public void buildMessage_4_oe() {
		IOException ioexception = new IOException("my exception");
		// removed other assertion
		// removed other assertion

		RuntimeException runtimeExcetpion = new RuntimeException("my runtimeException", ioexception);
		// removed other assertion

		assertThat(ExceptionUtil.toStringWithRootCause(null)).isEqualTo("");
	}

	@Test
	public void buildMessage_5_oe() {
		IOException ioexception = new IOException("my exception");
		// removed other assertion
		// removed other assertion

		RuntimeException runtimeExcetpion = new RuntimeException("my runtimeException", ioexception);
		// removed other assertion

		// removed other assertion
		// 无cause
		assertThat(ExceptionUtil.toStringWithRootCause(ioexception)).isEqualTo("IOException: my exception");
	}

	@Test
	public void staticException_1_oe() {
		assertThat(ExceptionUtil.stackTraceText(TIMEOUT_EXCEPTION)).hasLineCount(2) .contains("java.lang.RuntimeException: Timeout") .contains("at org.springside.modules.utils.base.ExceptionUtilTest.hello(Unknown Source)");
	}

	@Test
	public void staticException_2_oe() {
		// removed other assertion

		assertThat(ExceptionUtil.stackTraceText(TIMEOUT_EXCEPTION2)).hasLineCount(2) .contains("org.springside.modules.utils.base.ExceptionUtil$CloneableException: Timeout") .contains("at org.springside.modules.utils.base.ExceptionUtilTest.hello(Unknown Source)");
	}

	@Test
	public void staticException_3_oe() {
		// removed other assertion

		// removed other assertion

		CloneableException timeoutException = TIMEOUT_EXCEPTION2.clone("Timeout for 30ms");
		assertThat(ExceptionUtil.stackTraceText(timeoutException)).hasLineCount(2) .contains("org.springside.modules.utils.base.ExceptionUtil$CloneableException: Timeout for 30ms") .contains("at org.springside.modules.utils.base.ExceptionUtilTest.hello(Unknown Source)");
	}

	@Test
	public void staticException_4_oe() {
		// removed other assertion

		// removed other assertion

		CloneableException timeoutException = TIMEOUT_EXCEPTION2.clone("Timeout for 30ms");
		// removed other assertion

		assertThat(ExceptionUtil.stackTraceText(TIMEOUT_EXCEPTION3)).hasLineCount(2) .contains("org.springside.modules.utils.base.ExceptionUtil$CloneableRuntimeException: Timeout") .contains("at org.springside.modules.utils.base.ExceptionUtilTest.hello(Unknown Source)");
	}

	@Test
	public void staticException_5_oe() {
		// removed other assertion

		// removed other assertion

		CloneableException timeoutException = TIMEOUT_EXCEPTION2.clone("Timeout for 30ms");
		// removed other assertion

		// removed other assertion

		CloneableRuntimeException timeoutRuntimeException = TIMEOUT_EXCEPTION3.clone("Timeout for 40ms");
		assertThat(ExceptionUtil.stackTraceText(timeoutRuntimeException)).hasLineCount(2) .contains("org.springside.modules.utils.base.ExceptionUtil$CloneableRuntimeException: Timeout for 40ms") .contains("at org.springside.modules.utils.base.ExceptionUtilTest.hello(Unknown Source)");
	}

}
