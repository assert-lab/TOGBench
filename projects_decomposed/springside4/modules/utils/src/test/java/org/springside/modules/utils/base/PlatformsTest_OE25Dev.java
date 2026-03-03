package org.springside.modules.utils.base;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class PlatformsTest_OE25Dev {

	@Test
	public void PlatformTest_1_oe() {

		if (Platforms.IS_WINDOWS) {
			assertThat(Platforms.FILE_PATH_SEPARATOR).isEqualTo("\\");
	}
	}

	@Test
	public void PlatformTest_2_oe() {

		if (Platforms.IS_WINDOWS) {
			assertThat(Platforms.FILE_PATH_SEPARATOR_CHAR).isEqualTo('\\');
	}
	}

	@Test
	public void PlatformTest_3_oe() {

		if (Platforms.IS_WINDOWS) {
		} else {
			assertThat(Platforms.FILE_PATH_SEPARATOR).isEqualTo("/");
	}
	}

	@Test
	public void PlatformTest_4_oe() {

		if (Platforms.IS_WINDOWS) {
		} else {
			assertThat(Platforms.FILE_PATH_SEPARATOR_CHAR).isEqualTo('/');
	}
	}

	@Test
	public void PlatformTest_5_oe() {

		if (Platforms.IS_WINDOWS) {
		} else {
		}

		System.out.println("OS_NAME:" + Platforms.OS_NAME);
		System.out.println("OS_VERSION:" + Platforms.OS_VERSION);
		System.out.println("OS_ARCH:" + Platforms.OS_ARCH);
		System.out.println("JAVA_SPECIFICATION_VERSION:" + Platforms.JAVA_SPECIFICATION_VERSION);
		System.out.println("JAVA_VERSION:" + Platforms.JAVA_VERSION);
		System.out.println("JAVA_HOME:" + Platforms.JAVA_HOME);
		System.out.println("USER_HOME:" + Platforms.USER_HOME);
		System.out.println("TMP_DIR:" + Platforms.TMP_DIR);
		System.out.println("WORKING_DIR:" + Platforms.WORKING_DIR);

		if (Platforms.IS_JAVA7) {
			assertThat(Platforms.IS_ATLEASET_JAVA6).isTrue();
	}
	}

	@Test
	public void PlatformTest_6_oe() {

		if (Platforms.IS_WINDOWS) {
		} else {
		}

		System.out.println("OS_NAME:" + Platforms.OS_NAME);
		System.out.println("OS_VERSION:" + Platforms.OS_VERSION);
		System.out.println("OS_ARCH:" + Platforms.OS_ARCH);
		System.out.println("JAVA_SPECIFICATION_VERSION:" + Platforms.JAVA_SPECIFICATION_VERSION);
		System.out.println("JAVA_VERSION:" + Platforms.JAVA_VERSION);
		System.out.println("JAVA_HOME:" + Platforms.JAVA_HOME);
		System.out.println("USER_HOME:" + Platforms.USER_HOME);
		System.out.println("TMP_DIR:" + Platforms.TMP_DIR);
		System.out.println("WORKING_DIR:" + Platforms.WORKING_DIR);

		if (Platforms.IS_JAVA7) {
			assertThat(Platforms.IS_ATLEASET_JAVA7).isTrue();
	}
	}

	@Test
	public void PlatformTest_7_oe() {

		if (Platforms.IS_WINDOWS) {
		} else {
		}

		System.out.println("OS_NAME:" + Platforms.OS_NAME);
		System.out.println("OS_VERSION:" + Platforms.OS_VERSION);
		System.out.println("OS_ARCH:" + Platforms.OS_ARCH);
		System.out.println("JAVA_SPECIFICATION_VERSION:" + Platforms.JAVA_SPECIFICATION_VERSION);
		System.out.println("JAVA_VERSION:" + Platforms.JAVA_VERSION);
		System.out.println("JAVA_HOME:" + Platforms.JAVA_HOME);
		System.out.println("USER_HOME:" + Platforms.USER_HOME);
		System.out.println("TMP_DIR:" + Platforms.TMP_DIR);
		System.out.println("WORKING_DIR:" + Platforms.WORKING_DIR);

		if (Platforms.IS_JAVA7) {
			assertThat(Platforms.IS_ATLEASET_JAVA8).isFalse();
	}
	}

	@Test
	public void PlatformTest_8_oe() {

		if (Platforms.IS_WINDOWS) {
		} else {
		}

		System.out.println("OS_NAME:" + Platforms.OS_NAME);
		System.out.println("OS_VERSION:" + Platforms.OS_VERSION);
		System.out.println("OS_ARCH:" + Platforms.OS_ARCH);
		System.out.println("JAVA_SPECIFICATION_VERSION:" + Platforms.JAVA_SPECIFICATION_VERSION);
		System.out.println("JAVA_VERSION:" + Platforms.JAVA_VERSION);
		System.out.println("JAVA_HOME:" + Platforms.JAVA_HOME);
		System.out.println("USER_HOME:" + Platforms.USER_HOME);
		System.out.println("TMP_DIR:" + Platforms.TMP_DIR);
		System.out.println("WORKING_DIR:" + Platforms.WORKING_DIR);

		if (Platforms.IS_JAVA7) {
		}
		
		if (Platforms.IS_JAVA8) {
			assertThat(Platforms.IS_ATLEASET_JAVA6).isTrue();
	}
	}

	@Test
	public void PlatformTest_9_oe() {

		if (Platforms.IS_WINDOWS) {
		} else {
		}

		System.out.println("OS_NAME:" + Platforms.OS_NAME);
		System.out.println("OS_VERSION:" + Platforms.OS_VERSION);
		System.out.println("OS_ARCH:" + Platforms.OS_ARCH);
		System.out.println("JAVA_SPECIFICATION_VERSION:" + Platforms.JAVA_SPECIFICATION_VERSION);
		System.out.println("JAVA_VERSION:" + Platforms.JAVA_VERSION);
		System.out.println("JAVA_HOME:" + Platforms.JAVA_HOME);
		System.out.println("USER_HOME:" + Platforms.USER_HOME);
		System.out.println("TMP_DIR:" + Platforms.TMP_DIR);
		System.out.println("WORKING_DIR:" + Platforms.WORKING_DIR);

		if (Platforms.IS_JAVA7) {
		}
		
		if (Platforms.IS_JAVA8) {
			assertThat(Platforms.IS_ATLEASET_JAVA7).isTrue();
	}
	}

	@Test
	public void PlatformTest_10_oe() {

		if (Platforms.IS_WINDOWS) {
		} else {
		}

		System.out.println("OS_NAME:" + Platforms.OS_NAME);
		System.out.println("OS_VERSION:" + Platforms.OS_VERSION);
		System.out.println("OS_ARCH:" + Platforms.OS_ARCH);
		System.out.println("JAVA_SPECIFICATION_VERSION:" + Platforms.JAVA_SPECIFICATION_VERSION);
		System.out.println("JAVA_VERSION:" + Platforms.JAVA_VERSION);
		System.out.println("JAVA_HOME:" + Platforms.JAVA_HOME);
		System.out.println("USER_HOME:" + Platforms.USER_HOME);
		System.out.println("TMP_DIR:" + Platforms.TMP_DIR);
		System.out.println("WORKING_DIR:" + Platforms.WORKING_DIR);

		if (Platforms.IS_JAVA7) {
		}
		
		if (Platforms.IS_JAVA8) {
			assertThat(Platforms.IS_ATLEASET_JAVA8).isTrue();
	}
	}

	@Test
	public void PlatformTest_11_oe() {

		if (Platforms.IS_WINDOWS) {
		} else {
		}

		System.out.println("OS_NAME:" + Platforms.OS_NAME);
		System.out.println("OS_VERSION:" + Platforms.OS_VERSION);
		System.out.println("OS_ARCH:" + Platforms.OS_ARCH);
		System.out.println("JAVA_SPECIFICATION_VERSION:" + Platforms.JAVA_SPECIFICATION_VERSION);
		System.out.println("JAVA_VERSION:" + Platforms.JAVA_VERSION);
		System.out.println("JAVA_HOME:" + Platforms.JAVA_HOME);
		System.out.println("USER_HOME:" + Platforms.USER_HOME);
		System.out.println("TMP_DIR:" + Platforms.TMP_DIR);
		System.out.println("WORKING_DIR:" + Platforms.WORKING_DIR);

		if (Platforms.IS_JAVA7) {
		}
		
		if (Platforms.IS_JAVA8) {
		}
		
		System.out.println("pid:"+ Platforms.getPid());
		assertThat(Platforms.getPid()).isNotEqualTo(-1);
	}

}
