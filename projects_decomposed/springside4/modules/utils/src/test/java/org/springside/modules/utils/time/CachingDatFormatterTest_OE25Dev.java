package org.springside.modules.utils.time;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;

import org.junit.Test;

public class CachingDatFormatterTest_OE25Dev {

	@Test
	public void test_1_oe() {
		Date date = new Date(116, 10, 1, 12, 23, 44);

		CachingDateFormatter formatter = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT);
		assertThat(formatter.format(date.getTime())).isEqualTo("2016-11-01 12:23:44.000");
	}

	@Test
	public void test_2_oe() {
		Date date = new Date(116, 10, 1, 12, 23, 44);

		CachingDateFormatter formatter = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT);
		// removed other assertion
		assertThat(formatter.format(date.getTime())).isEqualTo("2016-11-01 12:23:44.000");
	}

	@Test
	public void test_3_oe() {
		Date date = new Date(116, 10, 1, 12, 23, 44);

		CachingDateFormatter formatter = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT);
		// removed other assertion
		// removed other assertion
		assertThat(formatter.format(date.getTime() + 2)).isEqualTo("2016-11-01 12:23:44.002");
	}

	@Test
	public void test_4_oe() {
		Date date = new Date(116, 10, 1, 12, 23, 44);

		CachingDateFormatter formatter = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT);
		// removed other assertion
		// removed other assertion
		// removed other assertion

		CachingDateFormatter formatterOnSecond = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND);
		assertThat(formatterOnSecond.format(date.getTime())).isEqualTo("2016-11-01 12:23:44");
	}

	@Test
	public void test_5_oe() {
		Date date = new Date(116, 10, 1, 12, 23, 44);

		CachingDateFormatter formatter = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT);
		// removed other assertion
		// removed other assertion
		// removed other assertion

		CachingDateFormatter formatterOnSecond = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND);
		// removed other assertion
		assertThat(formatterOnSecond.format(date.getTime())).isEqualTo("2016-11-01 12:23:44");
	}

	@Test
	public void test_6_oe() {
		Date date = new Date(116, 10, 1, 12, 23, 44);

		CachingDateFormatter formatter = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT);
		// removed other assertion
		// removed other assertion
		// removed other assertion

		CachingDateFormatter formatterOnSecond = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND);
		// removed other assertion
		// removed other assertion
		assertThat(formatterOnSecond.format(date.getTime() + 2)).isEqualTo("2016-11-01 12:23:44");
	}

	@Test
	public void test_7_oe() {
		Date date = new Date(116, 10, 1, 12, 23, 44);

		CachingDateFormatter formatter = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT);
		// removed other assertion
		// removed other assertion
		// removed other assertion

		CachingDateFormatter formatterOnSecond = new CachingDateFormatter(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND);
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(formatterOnSecond.format(date.getTime() + 1000)).isEqualTo("2016-11-01 12:23:45");
	}

}
