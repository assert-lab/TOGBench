package org.springside.modules.utils.io;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.jar.JarFile;

import org.junit.Test;

import com.google.common.io.Files;

public class ResourceUtilTest_OE25Dev {

	@Test
	public void test() throws IOException {
		// getResoruce
		assertThat(ResourceUtil.toString("test.txt")).contains("ABCDEFG");
		assertThat(ResourceUtil.toString(ResourceUtilTest_OE25Dev.class, "/test.txt")).contains("ABCDEFG");
		assertThat(ResourceUtil.toLines("test.txt")).containsExactly("ABCDEFG", "ABC");
		assertThat(ResourceUtil.toLines(ResourceUtilTest_OE25Dev.class, "/test.txt")).containsExactly("ABCDEFG", "ABC");

		// getResoruce 处理重复的资源
		System.out.println(ResourceUtil.asUrl("META-INF/MANIFEST.MF"));
		assertThat(ResourceUtil.toString("META-INF/MANIFEST.MF")).contains("Manifest");

		// getResources
		assertThat(ResourceUtil.getResources("META-INF/MANIFEST.MF").size()).isGreaterThan(1);

		System.out.println(ResourceUtil.getResources("META-INF/MANIFEST.MF"));

		assertThat(ResourceUtil.getResources("META-INF/MANIFEST.MF",ResourceUtilTest_OE25Dev.class.getClassLoader()).size()).isGreaterThan(1);

	}

	@Test
	public void resourceNameTest() throws IOException{
		JarFile guavaFile = new JarFile(FilePathUtil.getJarPath(Files.class));
		assertThat(guavaFile.getEntry("META-INF/MANIFEST.MF")).isNotNull();
		assertThat(guavaFile.getEntry("/META-INF/MANIFEST.MF")).isNull();
		guavaFile.close();
	}
	

	@Test
	public void test_1_oe() throws IOException {
		// getResoruce
		assertThat(ResourceUtil.toString("test.txt")).contains("ABCDEFG");
	}

	@Test
	public void test_2_oe() throws IOException {
		// getResoruce
		// removed other assertion
		assertThat(ResourceUtil.toString(ResourceUtilTest.class, "/test.txt")).contains("ABCDEFG");
	}

	@Test
	public void test_3_oe() throws IOException {
		// getResoruce
		// removed other assertion
		// removed other assertion
		assertThat(ResourceUtil.toLines("test.txt")).containsExactly("ABCDEFG", "ABC");
	}

	@Test
	public void test_4_oe() throws IOException {
		// getResoruce
		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(ResourceUtil.toLines(ResourceUtilTest.class, "/test.txt")).containsExactly("ABCDEFG", "ABC");
	}

	@Test
	public void test_5_oe() throws IOException {
		// getResoruce
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// getResoruce 处理重复的资源
		System.out.println(ResourceUtil.asUrl("META-INF/MANIFEST.MF"));
		assertThat(ResourceUtil.toString("META-INF/MANIFEST.MF")).contains("Manifest");
	}

	@Test
	public void test_6_oe() throws IOException {
		// getResoruce
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// getResoruce 处理重复的资源
		System.out.println(ResourceUtil.asUrl("META-INF/MANIFEST.MF"));
		// removed other assertion

		// getResources
		assertThat(ResourceUtil.getResources("META-INF/MANIFEST.MF").size()).isGreaterThan(1);
	}

	@Test
	public void test_7_oe() throws IOException {
		// getResoruce
		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		// getResoruce 处理重复的资源
		System.out.println(ResourceUtil.asUrl("META-INF/MANIFEST.MF"));
		// removed other assertion

		// getResources
		// removed other assertion

		System.out.println(ResourceUtil.getResources("META-INF/MANIFEST.MF"));

		assertThat(ResourceUtil.getResources("META-INF/MANIFEST.MF",ResourceUtilTest.class.getClassLoader()).size()).isGreaterThan(1);
	}

	@Test
	public void resourceNameTest_1_oe() throws IOException{
		JarFile guavaFile = new JarFile(FilePathUtil.getJarPath(Files.class));
		assertThat(guavaFile.getEntry("META-INF/MANIFEST.MF")).isNotNull();
	}

	@Test
	public void resourceNameTest_2_oe() throws IOException{
		JarFile guavaFile = new JarFile(FilePathUtil.getJarPath(Files.class));
		// removed other assertion
		assertThat(guavaFile.getEntry("/META-INF/MANIFEST.MF")).isNull();
	}

}
