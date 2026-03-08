package org.springside.modules.utils.io;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springside.modules.utils.number.RandomUtil;

public class FileTreeWalkerTest_OE25Dev {

	@Test
	public void listFile() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		assertThat(all).hasSize(1);

		List<File> files = FileTreeWalker.listFile(tmpDir);
		assertThat(files).hasSize(0);

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		assertThat(all).hasSize(5);

		files = FileTreeWalker.listFile(tmpDir);
		assertThat(files).hasSize(3);

		//extension
		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		assertThat(files).hasSize(2);

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");
		assertThat(files).hasSize(0);

		//wildcard
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		assertThat(files).hasSize(2);
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");
		assertThat(files).hasSize(0);

		//regex
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		assertThat(files).hasSize(2);
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tp");
		assertThat(files).hasSize(0);
		
		
		//antpath
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "**/*.tmp");
		assertThat(files).hasSize(2);
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*/*.tmp");
		assertThat(files).hasSize(1);
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*.tp");
		assertThat(files).hasSize(0);

		FileUtil.deleteDir(tmpDir);

		assertThat(FileUtil.isDirExists(tmpDir)).isFalse();

	}

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
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		assertThat(files).hasSize(0);
	}

	@Test
	public void listFile_3_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

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
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		files = FileTreeWalker.listFile(tmpDir);
		assertThat(files).hasSize(3);
	}

	@Test
	public void listFile_5_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		//extension
		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		assertThat(files).hasSize(2);
	}

	@Test
	public void listFile_6_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		//extension
		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		// removed other assertion

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");
		assertThat(files).hasSize(0);
	}

	@Test
	public void listFile_7_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		//extension
		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		// removed other assertion

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");
		// removed other assertion

		//wildcard
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		assertThat(files).hasSize(2);
	}

	@Test
	public void listFile_8_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		//extension
		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		// removed other assertion

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");
		// removed other assertion

		//wildcard
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		// removed other assertion
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");
		assertThat(files).hasSize(0);
	}

	@Test
	public void listFile_9_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		//extension
		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		// removed other assertion

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");
		// removed other assertion

		//wildcard
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		// removed other assertion
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");
		// removed other assertion

		//regex
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		assertThat(files).hasSize(2);
	}

	@Test
	public void listFile_10_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		//extension
		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		// removed other assertion

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");
		// removed other assertion

		//wildcard
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		// removed other assertion
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");
		// removed other assertion

		//regex
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		// removed other assertion
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tp");
		assertThat(files).hasSize(0);
	}

	@Test
	public void listFile_11_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		//extension
		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		// removed other assertion

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");
		// removed other assertion

		//wildcard
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		// removed other assertion
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");
		// removed other assertion

		//regex
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		// removed other assertion
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tp");
		// removed other assertion
		
		
		//antpath
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "**/*.tmp");
		assertThat(files).hasSize(2);
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*/*.tmp");
		assertThat(files).hasSize(1);
	}

	@Test
	public void listFile_12_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		//extension
		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		// removed other assertion

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");
		// removed other assertion

		//wildcard
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		// removed other assertion
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");
		// removed other assertion

		//regex
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		// removed other assertion
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tp");
		// removed other assertion
		
		
		//antpath
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "**/*.tmp");
		assertThat(files).hasSize(2);
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*/*.tmp");
		// removed other assertion
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*.tp");
		assertThat(files).hasSize(0);
	}

	@Test
	public void listFile_13_oe() throws IOException {
		File tmpDir = FileUtil.createTempDir();

		List<File> all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		List<File> files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".tmp");
		FileUtil.touch(FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt()) + ".abc");

		String childDir = FilePathUtil.contact(tmpDir.getAbsolutePath(), "tmp-" + RandomUtil.nextInt());
		FileUtil.makesureDirExists(childDir);

		FileUtil.touch(FilePathUtil.contact(childDir, "tmp-" + RandomUtil.nextInt()) + ".tmp");

		all = FileTreeWalker.listAll(tmpDir);
		// removed other assertion

		files = FileTreeWalker.listFile(tmpDir);
		// removed other assertion

		//extension
		files = FileTreeWalker.listFileWithExtension(tmpDir, "tmp");
		// removed other assertion

		files = FileTreeWalker.listFileWithExtension(tmpDir, "tp");
		// removed other assertion

		//wildcard
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tmp");
		// removed other assertion
		files = FileTreeWalker.listFileWithWildcardFileName(tmpDir, "*.tp");
		// removed other assertion

		//regex
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tmp");
		// removed other assertion
		files = FileTreeWalker.listFileWithRegexFileName(tmpDir, ".*\\.tp");
		// removed other assertion
		
		
		//antpath
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "**/*.tmp");
		assertThat(files).hasSize(2);
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*/*.tmp");
		// removed other assertion
		
		files = FileTreeWalker.listFileWithAntPath(tmpDir, "*.tp");
		// removed other assertion

		FileUtil.deleteDir(tmpDir);

		assertThat(FileUtil.isDirExists(tmpDir)).isFalse();
	}

}
