package org.springside.modules.utils.io;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class URLResourceTest_OE25Dev {

	@Test
	public void file() throws IOException {
		File file = FileUtil.createTempFile();
		FileUtil.write("haha", file);
		try {
			File file2 = URLResourceUtil.asFile("file://" + file.getAbsolutePath());
			assertThat(FileUtil.toString(file2)).isEqualTo("haha");

			File file2NotExist = URLResourceUtil.asFile("file://" + file.getAbsolutePath() + ".noexist");

			File file3 = URLResourceUtil.asFile(file.getAbsolutePath());
			assertThat(FileUtil.toString(file3)).isEqualTo("haha");
			File file3NotExist = URLResourceUtil.asFile(file.getAbsolutePath() + ".noexist");

		} finally {
			FileUtil.deleteFile(file);
		}

	}

	@Test
	public void resource_1_oe() throws IOException {
		File file = URLResourceUtil.asFile("classpath://application.properties");
		assertThat(FileUtil.toString(file)).isEqualTo("springside.min=1\nspringside.max=10");
	}

	@Test
	public void resource_2_oe() throws IOException {
		File file = URLResourceUtil.asFile("classpath://application.properties");

		InputStream is = URLResourceUtil.asStream("classpath://application.properties");
		assertThat(IOUtil.toString(is)).isEqualTo("springside.min=1\nspringside.max=10");
	}

	@Test
	public void resource_4_oe() throws IOException {
		File file = URLResourceUtil.asFile("classpath://application.properties");

		InputStream is = URLResourceUtil.asStream("classpath://application.properties");
		IOUtil.closeQuietly(is);

		try {
			URLResourceUtil.asFile("classpath://notexist.properties");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

	@Test
	public void resource_6_oe() throws IOException {
		File file = URLResourceUtil.asFile("classpath://application.properties");

		InputStream is = URLResourceUtil.asStream("classpath://application.properties");
		IOUtil.closeQuietly(is);

		try {
			URLResourceUtil.asFile("classpath://notexist.properties");
		} catch (Throwable t) {
		}

		try {
			URLResourceUtil.asStream("classpath://notexist.properties");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
	}
	}

}
