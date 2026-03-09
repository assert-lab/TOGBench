package org.springside.modules.utils.number;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class UnitConverterTest_OE25Dev {

	@Test
	public void convertDurationMillis() {
		assertThat(UnitConverter.convertDurationMillis("12345")).isEqualTo(12345);
		assertThat(UnitConverter.convertDurationMillis("12S")).isEqualTo(12000);
		assertThat(UnitConverter.convertDurationMillis("12s")).isEqualTo(12000);
		assertThat(UnitConverter.convertDurationMillis("12ms")).isEqualTo(12);
		assertThat(UnitConverter.convertDurationMillis("12m")).isEqualTo(12 * 60 * 1000);
		assertThat(UnitConverter.convertDurationMillis("12h")).isEqualTo(12l * 60 * 60 * 1000);
		assertThat(UnitConverter.convertDurationMillis("12d")).isEqualTo(12l * 24 * 60 * 60 * 1000);

		try {
			assertThat(UnitConverter.convertDurationMillis("12a")).isEqualTo(12 * 60 * 1000);
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
		}
		
		try {
			assertThat(UnitConverter.convertDurationMillis("a12")).isEqualTo(12 * 60 * 1000);
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Test
	public void convertSizeBytes() {
		assertThat(UnitConverter.convertSizeBytes("12345")).isEqualTo(12345);
		assertThat(UnitConverter.convertSizeBytes("12b")).isEqualTo(12);
		assertThat(UnitConverter.convertSizeBytes("12k")).isEqualTo(12 * 1024);
		assertThat(UnitConverter.convertSizeBytes("12M")).isEqualTo(12 * 1024 * 1024);

		assertThat(UnitConverter.convertSizeBytes("12G")).isEqualTo(12l * 1024 * 1024 * 1024);
		assertThat(UnitConverter.convertSizeBytes("12T")).isEqualTo(12l * 1024 * 1024 * 1024 * 1024);

		try {
			UnitConverter.convertSizeBytes("12x");
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
		}
		
		try {
			UnitConverter.convertSizeBytes("a12");
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Test
	public void convertDurationMillis_1_oe() {
		assertThat(UnitConverter.convertDurationMillis("12345")).isEqualTo(12345);
	}

	@Test
	public void convertDurationMillis_2_oe() {
		assertThat(UnitConverter.convertDurationMillis("12S")).isEqualTo(12000);
	}

	@Test
	public void convertDurationMillis_3_oe() {
		assertThat(UnitConverter.convertDurationMillis("12s")).isEqualTo(12000);
	}

	@Test
	public void convertDurationMillis_4_oe() {
		assertThat(UnitConverter.convertDurationMillis("12ms")).isEqualTo(12);
	}

	@Test
	public void convertDurationMillis_5_oe() {
		assertThat(UnitConverter.convertDurationMillis("12m")).isEqualTo(12 * 60 * 1000);
	}

	@Test
	public void convertDurationMillis_6_oe() {
		assertThat(UnitConverter.convertDurationMillis("12h")).isEqualTo(12l * 60 * 60 * 1000);
	}

	@Test
	public void convertDurationMillis_7_oe() {
		assertThat(UnitConverter.convertDurationMillis("12d")).isEqualTo(12l * 24 * 60 * 60 * 1000);
	}

	@Test
	public void convertDurationMillis_10_oe() {

		try {
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void convertDurationMillis_13_oe() {

		try {
		} catch (Throwable t) {
		}
		
		try {
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void convertSizeBytes_1_oe() {
		assertThat(UnitConverter.convertSizeBytes("12345")).isEqualTo(12345);
	}

	@Test
	public void convertSizeBytes_2_oe() {
		assertThat(UnitConverter.convertSizeBytes("12b")).isEqualTo(12);
	}

	@Test
	public void convertSizeBytes_3_oe() {
		assertThat(UnitConverter.convertSizeBytes("12k")).isEqualTo(12 * 1024);
	}

	@Test
	public void convertSizeBytes_4_oe() {
		assertThat(UnitConverter.convertSizeBytes("12M")).isEqualTo(12 * 1024 * 1024);
	}

	@Test
	public void convertSizeBytes_5_oe() {

		assertThat(UnitConverter.convertSizeBytes("12G")).isEqualTo(12l * 1024 * 1024 * 1024);
	}

	@Test
	public void convertSizeBytes_6_oe() {

		assertThat(UnitConverter.convertSizeBytes("12T")).isEqualTo(12l * 1024 * 1024 * 1024 * 1024);
	}

	@Test
	public void convertSizeBytes_8_oe() {


		try {
			UnitConverter.convertSizeBytes("12x");
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void convertSizeBytes_10_oe() {


		try {
			UnitConverter.convertSizeBytes("12x");
		} catch (Throwable t) {
		}
		
		try {
			UnitConverter.convertSizeBytes("a12");
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

@Test
	public void convertDurationMillis_oe_101_oe() {
		try {
			fail("should fail");
		} catch (Throwable t) {
		}
    }

@Test
	public void convertDurationMillis_oe_102_oe() {
		try {
			fail("should fail");
		} catch (Throwable t) {
		}
    }

@Test
	public void convertSizeBytes_oe_101_oe() {
		try {
			UnitConverter.convertSizeBytes("12x");
			fail("should fail");
		} catch (Throwable t) {
		}
    }

@Test
	public void convertSizeBytes_oe_102_oe() {
		try {
			UnitConverter.convertSizeBytes("a12");
			fail("should fail");
		} catch (Throwable t) {
		}
    }

}
