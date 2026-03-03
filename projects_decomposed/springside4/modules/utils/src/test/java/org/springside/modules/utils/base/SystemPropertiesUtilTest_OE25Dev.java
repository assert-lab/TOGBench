package org.springside.modules.utils.base;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springside.modules.utils.base.SystemPropertiesUtil.PropertiesListener;
import org.springside.modules.utils.number.RandomUtil;

public class SystemPropertiesUtilTest_OE25Dev {

	public static class TestPropertiesListener extends PropertiesListener {

		public TestPropertiesListener(String propertyName) {
			super(propertyName);
		}

		public String newValue;

		@Override
		public void onChange(String propertyName, String value) {
			newValue = value;
		}

	};

	@Test
	public void systemProperty_1_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);
		assertThat(result0).isNull();
	}

	@Test
	public void systemProperty_2_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);
		assertThat(result1).isNull();
	}

	@Test
	public void systemProperty_3_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);
		assertThat(result3).isTrue();
	}

	@Test
	public void systemProperty_4_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);
		assertThat(result5).isTrue();
	}

	@Test
	public void systemProperty_5_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);
		assertThat(result6).isNull();
	}

	@Test
	public void systemProperty_6_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);
		assertThat(result6).isEqualTo(1);
	}

	@Test
	public void systemProperty_7_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.setProperty(name, "2");
		result6 = SystemPropertiesUtil.getInteger(name, 1);
		assertThat(result6).isEqualTo(2);
	}

	@Test
	public void systemProperty_8_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.setProperty(name, "2");
		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.clearProperty(name);

		Long result7 = SystemPropertiesUtil.getLong(name);
		assertThat(result7).isNull();
	}

	@Test
	public void systemProperty_9_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.setProperty(name, "2");
		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.clearProperty(name);

		Long result7 = SystemPropertiesUtil.getLong(name);

		result7 = SystemPropertiesUtil.getLong(name, 1L);
		assertThat(result7).isEqualTo(1L);
	}

	@Test
	public void systemProperty_10_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.setProperty(name, "2");
		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.clearProperty(name);

		Long result7 = SystemPropertiesUtil.getLong(name);

		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.setProperty(name, "2");
		result7 = SystemPropertiesUtil.getLong(name, 1L);
		assertThat(result7).isEqualTo(2L);
	}

	@Test
	public void systemProperty_11_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.setProperty(name, "2");
		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.clearProperty(name);

		Long result7 = SystemPropertiesUtil.getLong(name);

		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.setProperty(name, "2");
		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.clearProperty(name);

		Double result8 = SystemPropertiesUtil.getDouble(name);
		assertThat(result8).isNull();
	}

	@Test
	public void systemProperty_12_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.setProperty(name, "2");
		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.clearProperty(name);

		Long result7 = SystemPropertiesUtil.getLong(name);

		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.setProperty(name, "2");
		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.clearProperty(name);

		Double result8 = SystemPropertiesUtil.getDouble(name);

		result8 = SystemPropertiesUtil.getDouble(name, 1.1);
		assertThat(result8).isEqualTo(1.1);
	}

	@Test
	public void systemProperty_13_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.setProperty(name, "2");
		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.clearProperty(name);

		Long result7 = SystemPropertiesUtil.getLong(name);

		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.setProperty(name, "2");
		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.clearProperty(name);

		Double result8 = SystemPropertiesUtil.getDouble(name);

		result8 = SystemPropertiesUtil.getDouble(name, 1.1);

		System.setProperty(name, "2.1");
		result8 = SystemPropertiesUtil.getDouble(name, 1.1);
		assertThat(result8).isEqualTo(2.1);
	}

	@Test
	public void systemProperty_14_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.setProperty(name, "2");
		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.clearProperty(name);

		Long result7 = SystemPropertiesUtil.getLong(name);

		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.setProperty(name, "2");
		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.clearProperty(name);

		Double result8 = SystemPropertiesUtil.getDouble(name);

		result8 = SystemPropertiesUtil.getDouble(name, 1.1);

		System.setProperty(name, "2.1");
		result8 = SystemPropertiesUtil.getDouble(name, 1.1);

		System.clearProperty(name);

		String result9 = SystemPropertiesUtil.getString(name);
		assertThat(result9).isNull();
	}

	@Test
	public void systemProperty_15_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.setProperty(name, "2");
		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.clearProperty(name);

		Long result7 = SystemPropertiesUtil.getLong(name);

		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.setProperty(name, "2");
		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.clearProperty(name);

		Double result8 = SystemPropertiesUtil.getDouble(name);

		result8 = SystemPropertiesUtil.getDouble(name, 1.1);

		System.setProperty(name, "2.1");
		result8 = SystemPropertiesUtil.getDouble(name, 1.1);

		System.clearProperty(name);

		String result9 = SystemPropertiesUtil.getString(name);

		result9 = SystemPropertiesUtil.getString(name, "1.1");
		assertThat(result9).isEqualTo("1.1");
	}

	@Test
	public void systemProperty_16_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		Boolean result0 = SystemPropertiesUtil.getBoolean(name);

		Boolean result1 = SystemPropertiesUtil.getBoolean(name, null);

		Boolean result3 = SystemPropertiesUtil.getBoolean(name, Boolean.TRUE);

		System.setProperty(name, "true");

		Boolean result5 = SystemPropertiesUtil.getBoolean(name, Boolean.FALSE);

		System.clearProperty(name);

		Integer result6 = SystemPropertiesUtil.getInteger(name);

		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.setProperty(name, "2");
		result6 = SystemPropertiesUtil.getInteger(name, 1);

		System.clearProperty(name);

		Long result7 = SystemPropertiesUtil.getLong(name);

		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.setProperty(name, "2");
		result7 = SystemPropertiesUtil.getLong(name, 1L);

		System.clearProperty(name);

		Double result8 = SystemPropertiesUtil.getDouble(name);

		result8 = SystemPropertiesUtil.getDouble(name, 1.1);

		System.setProperty(name, "2.1");
		result8 = SystemPropertiesUtil.getDouble(name, 1.1);

		System.clearProperty(name);

		String result9 = SystemPropertiesUtil.getString(name);

		result9 = SystemPropertiesUtil.getString(name, "1.1");

		System.setProperty(name, "2.1");
		result9 = SystemPropertiesUtil.getString(name, "1.1");
		assertThat(result9).isEqualTo("2.1");
	}

	@Test
	public void stringSystemProperty_1_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		String result = SystemPropertiesUtil.getString(name, envName, "123");
		assertThat(result).isEqualTo("123");
	}

	@Test
	public void stringSystemProperty_2_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		String result = SystemPropertiesUtil.getString(name, envName, "123");

		String result2 = SystemPropertiesUtil.getString(name, "HOME", "123");
		assertThat(result2).isNotEqualTo("123");
	}

	@Test
	public void stringSystemProperty_3_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		String result = SystemPropertiesUtil.getString(name, envName, "123");

		String result2 = SystemPropertiesUtil.getString(name, "HOME", "123");

		System.setProperty(name, "456");
		String result3 = SystemPropertiesUtil.getString(name, envName, "123");
		assertThat(result3).isEqualTo("456");
	}

	@Test
	public void stringSystemProperty_5_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		String result = SystemPropertiesUtil.getString(name, envName, "123");

		String result2 = SystemPropertiesUtil.getString(name, "HOME", "123");

		System.setProperty(name, "456");
		String result3 = SystemPropertiesUtil.getString(name, envName, "123");

		try {
			String result4 = SystemPropertiesUtil.getString(name, name, "123");
		} catch (Exception e) {
			assertThat(e).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void intSystemProperty_1_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		int result = SystemPropertiesUtil.getInteger(name, envName, 123);
		assertThat(result).isEqualTo(123);
	}

	@Test
	public void intSystemProperty_2_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		int result = SystemPropertiesUtil.getInteger(name, envName, 123);


		System.setProperty(name, "456");
		int result3 = SystemPropertiesUtil.getInteger(name, envName, 123);
		assertThat(result3).isEqualTo(456);
	}

	@Test
	public void longSystemProperty_1_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		long result = SystemPropertiesUtil.getLong(name, envName, 123L);
		assertThat(result).isEqualTo(123L);
	}

	@Test
	public void longSystemProperty_2_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		long result = SystemPropertiesUtil.getLong(name, envName, 123L);


		System.setProperty(name, "456");
		long result3 = SystemPropertiesUtil.getLong(name, envName, 123L);
		assertThat(result3).isEqualTo(456L);
	}

	@Test
	public void doubleSystemProperty_1_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		double result = SystemPropertiesUtil.getDouble(name, envName, 123d);
		assertThat(result).isEqualTo(123d);
	}

	@Test
	public void doubleSystemProperty_2_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		double result = SystemPropertiesUtil.getDouble(name, envName, 123d);


		System.setProperty(name, "456");
		double result3 = SystemPropertiesUtil.getDouble(name, envName, 123d);
		assertThat(result3).isEqualTo(456d);
	}

	@Test
	public void booleanSystemProperty_1_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		boolean result = SystemPropertiesUtil.getBoolean(name, envName, true);
		assertThat(result).isTrue();
	}

	@Test
	public void booleanSystemProperty_2_oe() {
		String name = "ss.test" + RandomUtil.nextInt();
		String envName = "ss_test" + RandomUtil.nextInt();

		boolean result = SystemPropertiesUtil.getBoolean(name, envName, true);


		System.setProperty(name, "true");
		boolean result3 = SystemPropertiesUtil.getBoolean(name, envName, false);
		assertThat(result3).isTrue();
	}

	@Test
	public void listenableProperties_1_oe() {
		String name = "ss.test" + RandomUtil.nextInt();

		TestPropertiesListener listener = new TestPropertiesListener(name);
		SystemPropertiesUtil.registerSystemPropertiesListener(listener);

		System.setProperty(name, "haha");

		assertThat(listener.newValue).isEqualTo("haha");
	}

}
