package org.springside.modules.utils.base;

import static org.assertj.core.api.Assertions.*;

import java.util.Properties;

import org.junit.Test;

public class PropertiesUtilTest_OE25Dev {

	@Test
	public void loadProperties() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		assertThat(p1.get("springside.min")).isEqualTo("1");
		assertThat(p1.get("springside.max")).isEqualTo("10");

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		assertThat(PropertiesUtil.getInt(p2, "springside.min", 0)).isEqualTo(1);
		assertThat(PropertiesUtil.getInt(p2, "springside.max", 0)).isEqualTo(10);
		assertThat(PropertiesUtil.getInt(p2, "springside.maxA", 0)).isEqualTo(0);

		assertThat(PropertiesUtil.getLong(p2, "springside.min", 0L)).isEqualTo(1);
		assertThat(PropertiesUtil.getLong(p2, "springside.max", 0L)).isEqualTo(10);
		assertThat(PropertiesUtil.getLong(p2, "springside.maxA", 0L)).isEqualTo(0);
		
		assertThat(PropertiesUtil.getDouble(p2, "springside.min", 0d)).isEqualTo(1);
		assertThat(PropertiesUtil.getDouble(p2, "springside.max", 0d)).isEqualTo(10);
		assertThat(PropertiesUtil.getDouble(p2, "springside.maxA", 0d)).isEqualTo(0);
		
		assertThat(PropertiesUtil.getString(p2, "springside.min", "")).isEqualTo("1");
		assertThat(PropertiesUtil.getString(p2, "springside.max", "")).isEqualTo("10");
		assertThat(PropertiesUtil.getString(p2, "springside.maxA", "")).isEqualTo("");

		assertThat(PropertiesUtil.getBoolean(p2, "isOpen", false)).isTrue();
	}

	@Test
	public void loadProperties_1_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		assertThat(p1.get("springside.min")).isEqualTo("1");
	}

	@Test
	public void loadProperties_2_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		assertThat(p1.get("springside.max")).isEqualTo("10");
	}

	@Test
	public void loadProperties_3_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		assertThat(PropertiesUtil.getInt(p2, "springside.min", 0)).isEqualTo(1);
	}

	@Test
	public void loadProperties_4_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		assertThat(PropertiesUtil.getInt(p2, "springside.max", 0)).isEqualTo(10);
	}

	@Test
	public void loadProperties_5_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		assertThat(PropertiesUtil.getInt(p2, "springside.maxA", 0)).isEqualTo(0);
	}

	@Test
	public void loadProperties_6_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(PropertiesUtil.getLong(p2, "springside.min", 0L)).isEqualTo(1);
	}

	@Test
	public void loadProperties_7_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(PropertiesUtil.getLong(p2, "springside.max", 0L)).isEqualTo(10);
	}

	@Test
	public void loadProperties_8_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(PropertiesUtil.getLong(p2, "springside.maxA", 0L)).isEqualTo(0);
	}

	@Test
	public void loadProperties_9_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		assertThat(PropertiesUtil.getDouble(p2, "springside.min", 0d)).isEqualTo(1);
	}

	@Test
	public void loadProperties_10_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		assertThat(PropertiesUtil.getDouble(p2, "springside.max", 0d)).isEqualTo(10);
	}

	@Test
	public void loadProperties_11_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		// removed other assertion
		assertThat(PropertiesUtil.getDouble(p2, "springside.maxA", 0d)).isEqualTo(0);
	}

	@Test
	public void loadProperties_12_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		assertThat(PropertiesUtil.getString(p2, "springside.min", "")).isEqualTo("1");
	}

	@Test
	public void loadProperties_13_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		assertThat(PropertiesUtil.getString(p2, "springside.max", "")).isEqualTo("10");
	}

	@Test
	public void loadProperties_14_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		// removed other assertion
		assertThat(PropertiesUtil.getString(p2, "springside.maxA", "")).isEqualTo("");
	}

	@Test
	public void loadProperties_15_oe() {
		Properties p1 = PropertiesUtil.loadFromFile("classpath://application.properties");
		// removed other assertion
		// removed other assertion

		Properties p2 = PropertiesUtil.loadFromString("springside.min=1\nspringside.max=10\nisOpen=true");
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		// removed other assertion
		// removed other assertion
		
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(PropertiesUtil.getBoolean(p2, "isOpen", false)).isTrue();
	}

}
