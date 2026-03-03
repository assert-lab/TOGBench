package org.springside.modules.utils.io;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springside.modules.utils.number.RandomUtil;

public class FileTreeWalkerTest_OE25Dev {

	@Test
	public void listFile_1_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		assertThat(all).hasSize(1);
	}

	@Test
	public void listFile_2_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);
		assertThat(files).hasSize(0);
	}

	@Test
	public void listFile_3_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		assertThat(all).hasSize(5);
	}

	@Test
	public void listFile_4_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);

		files = FileTreeWalker.listFile(tmpDir);
		assertThat(files).hasSize(3);
	}

	@Test
	public void listFile_5_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);

		files = FileTreeWalker.listFile(tmpDir);

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		assertThat(files).hasSize(2);
	}

	@Test
	public void listFile_6_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);

		files = FileTreeWalker.listFile(tmpDir);

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");
		assertThat(files).hasSize(0);
	}

	@Test
	public void listFile_7_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);

		files = FileTreeWalker.listFile(tmpDir);

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");

		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		assertThat(files).hasSize(2);
	}

	@Test
	public void listFile_8_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);

		files = FileTreeWalker.listFile(tmpDir);

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");

		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");
		assertThat(files).hasSize(0);
	}

	@Test
	public void listFile_9_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);

		files = FileTreeWalker.listFile(tmpDir);

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");

		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");

		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		assertThat(files).hasSize(2);
	}

	@Test
	public void listFile_10_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);

		files = FileTreeWalker.listFile(tmpDir);

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");

		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");

		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tp");
		assertThat(files).hasSize(0);
	}

	@Test
	public void listFile_11_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);

		files = FileTreeWalker.listFile(tmpDir);

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");

		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");

		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tp");
		
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "**/*.tmp");
		assertThat(files).hasSize(2);
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*/*.tmp");
		assertThat(files).hasSize(1);
	}

	@Test
	public void listFile_12_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);

		files = FileTreeWalker.listFile(tmpDir);

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");

		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");

		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tp");
		
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "**/*.tmp");
		assertThat(files).hasSize(2);
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*/*.tmp");
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*.tp");
		assertThat(files).hasSize(0);
	}

	@Test
	public void listFile_13_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);

		List<File> files = FileTreeWalker.listFile(tmpDir);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);

		files = FileTreeWalker.listFile(tmpDir);

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");

		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");

		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tp");
		
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "**/*.tmp");
		assertThat(files).hasSize(2);
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*/*.tmp");
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*.tp");

		FileUtil.deleteDir(tmpDir);

		assertThat(FileUtil.isDirExists(tmpDir)).isFalse();
	}

}
