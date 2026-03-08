package org.springside.modules.utils.io;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springside.modules.utils.base.Platforms;

import com.google.common.io.Files;

public class FilePathUtilTest_OE25Dev {

	char sep = Platforms.FILE_PATH_SEPARATOR_CHAR;

	@Test
	public void pathName_1_oe() {
		String filePath = FilePathUtil.contact(sep + "abc", "ef");
		assertThat(filePath).isEqualTo(FilePathUtil.normalizePath("/abc/ef"));
	}

	@Test
	public void pathName_2_oe() {
		String filePath = FilePathUtil.contact(sep + "abc", "ef");
		// removed other assertion

		String filePath2 = FilePathUtil.contact(sep + "stuv" + sep, "xy");
		assertThat(filePath2).isEqualTo(FilePathUtil.normalizePath("/stuv/xy"));
	}

	@Test
	public void pathName_3_oe() {
		String filePath = FilePathUtil.contact(sep + "abc", "ef");
		// removed other assertion

		String filePath2 = FilePathUtil.contact(sep + "stuv" + sep, "xy");
		// removed other assertion

		assertThat(FilePathUtil.simplifyPath("../dd/../abc")).isEqualTo("../abc");
	}

	@Test
	public void pathName_4_oe() {
		String filePath = FilePathUtil.contact(sep + "abc", "ef");
		// removed other assertion

		String filePath2 = FilePathUtil.contact(sep + "stuv" + sep, "xy");
		// removed other assertion

		// removed other assertion
		assertThat(FilePathUtil.simplifyPath("../../dd/../abc")).isEqualTo("../../abc");
	}

	@Test
	public void pathName_5_oe() {
		String filePath = FilePathUtil.contact(sep + "abc", "ef");
		// removed other assertion

		String filePath2 = FilePathUtil.contact(sep + "stuv" + sep, "xy");
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(FilePathUtil.simplifyPath("./abc")).isEqualTo("abc");
	}

	@Test
	public void pathName_6_oe() {
		String filePath = FilePathUtil.contact(sep + "abc", "ef");
		// removed other assertion

		String filePath2 = FilePathUtil.contact(sep + "stuv" + sep, "xy");
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(FilePathUtil.getParentPath(FilePathUtil.normalizePath("/abc/dd/efg/"))).isEqualTo(FilePathUtil.normalizePath("/abc/dd/"));
	}

	@Test
	public void pathName_7_oe() {
		String filePath = FilePathUtil.contact(sep + "abc", "ef");
		// removed other assertion

		String filePath2 = FilePathUtil.contact(sep + "stuv" + sep, "xy");
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion

		// removed other assertion

		assertThat(FilePathUtil.getParentPath(FilePathUtil.normalizePath("/abc/dd/efg.txt"))).isEqualTo(FilePathUtil.normalizePath("/abc/dd/"));
	}

	@Test
	public void getJarPath_1_oe() {
		System.out.println("the jar file contains Files.class" + FilePathUtil.getJarPath(Files.class));
		assertThat(FilePathUtil.getJarPath(Files.class)).endsWith("guava-20.0.jar");
	}

}
