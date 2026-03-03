package org.springside.modules.utils.io;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.jar.JarFile;

import org.junit.Test;

import com.google.common.io.Files;

public class ResourceUtilTest_OE25Dev {
	

	@Test
	public void test_1_oe() throws IOException {
		assertThat(ResourceUtil.toString("test.txt")).contains("ABCDEFG");
	}

	@Test
	public void test_2_oe() throws IOException {
		assertThat(ResourceUtil.toString(ResourceUtilTest.class, "/test.txt")).contains("ABCDEFG");
	}

	@Test
	public void test_3_oe() throws IOException {
		assertThat(ResourceUtil.toLines("test.txt")).containsExactly("ABCDEFG", "ABC");
	}

	@Test
	public void test_4_oe() throws IOException {
		assertThat(ResourceUtil.toLines(ResourceUtilTest.class, "/test.txt")).containsExactly("ABCDEFG", "ABC");
	}

	@Test
	public void test_5_oe() throws IOException {

		System.out.println(ResourceUtil.asUrl("META-INF/MANIFEST.MF"));
		assertThat(ResourceUtil.toString("META-INF/MANIFEST.MF")).contains("Manifest");
	}

	@Test
	public void test_6_oe() throws IOException {

		System.out.println(ResourceUtil.asUrl("META-INF/MANIFEST.MF"));

		assertThat(ResourceUtil.getResources("META-INF/MANIFEST.MF").size()).isGreaterThan(1);
	}

	@Test
	public void test_7_oe() throws IOException {

		System.out.println(ResourceUtil.asUrl("META-INF/MANIFEST.MF"));


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
		assertThat(guavaFile.getEntry("/META-INF/MANIFEST.MF")).isNull();
	}

}
