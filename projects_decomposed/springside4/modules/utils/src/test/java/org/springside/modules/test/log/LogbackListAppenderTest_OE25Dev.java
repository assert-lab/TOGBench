/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.modules.test.log;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackListAppenderTest_OE25Dev {

	@Test
	public void normal() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest_OE25Dev.class);

		// null
		assertThat(appender.getFirstLog()).isNull();
		assertThat(appender.getLastLog()).isNull();
		assertThat(appender.getFirstMessage()).isNull();
		assertThat(appender.getFirstMessage()).isNull();

		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest_OE25Dev.class);
		logger.warn(testString1);
		logger.warn(testString2);

		// getFirstLog/getLastLog
		assertThat(appender.getFirstLog().getMessage()).isEqualTo(testString1);
		assertThat(appender.getLastLog().getMessage()).isEqualTo(testString2);

		assertThat(appender.getFirstMessage()).isEqualTo(testString1);
		assertThat(appender.getLastMessage()).isEqualTo(testString2);

		// getAllLogs
		assertThat(appender.getLogsCount()).isEqualTo(2);
		assertThat(appender.getAllLogs()).hasSize(2);
		assertThat(appender.getAllLogs().get(1).getMessage()).isEqualTo(testString2);

		// clearLogs
		appender.clearLogs();
		assertThat(appender.getFirstLog()).isNull();
		assertThat(appender.getLastLog()).isNull();
	}

	@Test
	public void addAndRemoveAppender() {
		String testString = "Hello";
		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest_OE25Dev.class);
		LogbackListAppender appender = new LogbackListAppender();
		// class
		appender.addToLogger(LogbackListAppenderTest_OE25Dev.class);
		logger.warn(testString);
		assertThat(appender.getFirstLog()).isNotNull();

		appender.clearLogs();
		appender.removeFromLogger(LogbackListAppenderTest_OE25Dev.class);
		logger.warn(testString);
		assertThat(appender.getFirstLog()).isNull();

		// name
		appender.clearLogs();
		appender.addToLogger("org.springside.modules.test.log");
		logger.warn(testString);
		assertThat(appender.getFirstLog()).isNotNull();

		appender.clearLogs();
		appender.removeFromLogger("org.springside.modules.test.log");
		logger.warn(testString);
		assertThat(appender.getFirstLog()).isNull();
	}

	@Test
	public void normal_1_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);

		assertThat(appender.getFirstLog()).isNull();
	}

	@Test
	public void normal_2_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);

		assertThat(appender.getLastLog()).isNull();
	}

	@Test
	public void normal_3_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);

		assertThat(appender.getFirstMessage()).isNull();
	}

	@Test
	public void normal_4_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);

		assertThat(appender.getFirstMessage()).isNull();
	}

	@Test
	public void normal_5_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);


		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		logger.warn(testString1);
		logger.warn(testString2);

		assertThat(appender.getFirstLog().getMessage()).isEqualTo(testString1);
	}

	@Test
	public void normal_6_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);


		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		logger.warn(testString1);
		logger.warn(testString2);

		assertThat(appender.getLastLog().getMessage()).isEqualTo(testString2);
	}

	@Test
	public void normal_7_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);


		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		logger.warn(testString1);
		logger.warn(testString2);


		assertThat(appender.getFirstMessage()).isEqualTo(testString1);
	}

	@Test
	public void normal_8_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);


		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		logger.warn(testString1);
		logger.warn(testString2);


		assertThat(appender.getLastMessage()).isEqualTo(testString2);
	}

	@Test
	public void normal_9_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);


		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		logger.warn(testString1);
		logger.warn(testString2);



		assertThat(appender.getLogsCount()).isEqualTo(2);
	}

	@Test
	public void normal_10_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);


		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		logger.warn(testString1);
		logger.warn(testString2);



		assertThat(appender.getAllLogs()).hasSize(2);
	}

	@Test
	public void normal_11_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);


		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		logger.warn(testString1);
		logger.warn(testString2);



		assertThat(appender.getAllLogs().get(1).getMessage()).isEqualTo(testString2);
	}

	@Test
	public void normal_12_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);


		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		logger.warn(testString1);
		logger.warn(testString2);




		appender.clearLogs();
		assertThat(appender.getFirstLog()).isNull();
	}

	@Test
	public void normal_13_oe() {
		String testString1 = "Hello";
		String testString2 = "World";
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);


		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		logger.warn(testString1);
		logger.warn(testString2);




		appender.clearLogs();
		assertThat(appender.getLastLog()).isNull();
	}

	@Test
	public void addAndRemoveAppender_1_oe() {
		String testString = "Hello";
		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);
		logger.warn(testString);
		assertThat(appender.getFirstLog()).isNotNull();
	}

	@Test
	public void addAndRemoveAppender_2_oe() {
		String testString = "Hello";
		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);
		logger.warn(testString);

		appender.clearLogs();
		appender.removeFromLogger(LogbackListAppenderTest.class);
		logger.warn(testString);
		assertThat(appender.getFirstLog()).isNull();
	}

	@Test
	public void addAndRemoveAppender_3_oe() {
		String testString = "Hello";
		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);
		logger.warn(testString);

		appender.clearLogs();
		appender.removeFromLogger(LogbackListAppenderTest.class);
		logger.warn(testString);

		appender.clearLogs();
		appender.addToLogger("org.springside.modules.test.log");
		logger.warn(testString);
		assertThat(appender.getFirstLog()).isNotNull();
	}

	@Test
	public void addAndRemoveAppender_4_oe() {
		String testString = "Hello";
		Logger logger = LoggerFactory.getLogger(LogbackListAppenderTest.class);
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(LogbackListAppenderTest.class);
		logger.warn(testString);

		appender.clearLogs();
		appender.removeFromLogger(LogbackListAppenderTest.class);
		logger.warn(testString);

		appender.clearLogs();
		appender.addToLogger("org.springside.modules.test.log");
		logger.warn(testString);

		appender.clearLogs();
		appender.removeFromLogger("org.springside.modules.test.log");
		logger.warn(testString);
		assertThat(appender.getFirstLog()).isNull();
	}

}
