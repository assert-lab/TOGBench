package org.springside.modules.utils.io;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springside.modules.utils.base.Platforms;
import org.springside.modules.utils.number.RandomUtil;
import org.springside.modules.utils.text.Charsets;

public class FileUtilTest_OE25Dev {

	@Test
	public void readWrite() throws IOException {
		File file = FileUtil.createTempFile("abc", ".tmp");
		try {
			String content = "haha\nhehe";
			FileUtil.write(content, file);

			String result = FileUtil.toString(file);
			assertThat(result).isEqualTo(content);
			List<String> lines = FileUtil.toLines(file);
			assertThat(lines).containsExactly("haha", "hehe");

			FileUtil.append("kaka", file);
			assertThat(new String(FileUtil.toByteArray(file), Charsets.UTF_8)).isEqualTo("haha\nhehekaka");
		} finally {
			FileUtil.deleteFile(file);
		}
	}

	@Test
	public void opFiles_1_oe() throws IOException {
		File file = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));
		FileUtil.touch(file);
		assertThat(FileUtil.isFileExists(file)).isTrue();
	}

	@Test
	public void opFiles_2_oe() throws IOException {
		File file = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));
		FileUtil.touch(file);
		FileUtil.touch(file);

		String content = "haha\nhehe";
		FileUtil.write(content, file);
		assertThat(FileUtil.toString(file)).isEqualTo(content);
	}

	@Test
	public void opFiles_3_oe() throws IOException {
		File file = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));
		FileUtil.touch(file);
		FileUtil.touch(file);

		String content = "haha\nhehe";
		FileUtil.write(content, file);
		
		File newFile = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));
		File newFile2 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));

		FileUtil.copyFile(file, newFile);
		assertThat(FileUtil.isFileExists(newFile)).isTrue();
	}

	@Test
	public void opFiles_4_oe() throws IOException {
		File file = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));
		FileUtil.touch(file);
		FileUtil.touch(file);

		String content = "haha\nhehe";
		FileUtil.write(content, file);
		
		File newFile = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));
		File newFile2 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));

		FileUtil.copyFile(file, newFile);
		assertThat(FileUtil.toString(newFile)).isEqualTo(content);
	}

	@Test
	public void opFiles_5_oe() throws IOException {
		File file = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));
		FileUtil.touch(file);
		FileUtil.touch(file);

		String content = "haha\nhehe";
		FileUtil.write(content, file);
		
		File newFile = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));
		File newFile2 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testFile" + RandomUtil.nextInt()));

		FileUtil.copyFile(file, newFile);

		FileUtil.moveFile(newFile, newFile2);
		assertThat(FileUtil.toString(newFile2)).isEqualTo("haha\nhehe");
	}

	@Test
	public void opDir_1_oe() throws IOException {
		String fileName = "testFile" + RandomUtil.nextInt();
		File dir = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir"));

		File file = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir", fileName));
		String content = "haha\nhehe";
		FileUtil.makesureDirExists(dir);
		FileUtil.write(content, file);

		File dir2 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir2"));
		FileUtil.copyDir(dir, dir2);
		File file2 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir2", fileName));
		assertThat(FileUtil.toString(file2)).isEqualTo("haha\nhehe");
	}

	@Test
	public void opDir_2_oe() throws IOException {
		String fileName = "testFile" + RandomUtil.nextInt();
		File dir = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir"));

		File file = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir", fileName));
		String content = "haha\nhehe";
		FileUtil.makesureDirExists(dir);
		FileUtil.write(content, file);

		File dir2 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir2"));
		FileUtil.copyDir(dir, dir2);
		File file2 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir2", fileName));

		File dir3 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir3"));
		FileUtil.moveDir(dir, dir3);
		File file3 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir3", fileName));
		assertThat(FileUtil.toString(file3)).isEqualTo("haha\nhehe");
	}

	@Test
	public void opDir_3_oe() throws IOException {
		String fileName = "testFile" + RandomUtil.nextInt();
		File dir = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir"));

		File file = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir", fileName));
		String content = "haha\nhehe";
		FileUtil.makesureDirExists(dir);
		FileUtil.write(content, file);

		File dir2 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir2"));
		FileUtil.copyDir(dir, dir2);
		File file2 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir2", fileName));

		File dir3 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir3"));
		FileUtil.moveDir(dir, dir3);
		File file3 = new File(FilePathUtil.contact(Platforms.TMP_DIR, "testDir3", fileName));
		assertThat(FileUtil.isDirExists(dir)).isFalse();
	}

	@Test
	public void fileExist_1_oe() throws IOException {
		assertThat(FileUtil.isDirExists(Platforms.TMP_DIR)).isTrue();
	}

	@Test
	public void fileExist_2_oe() throws IOException {
		assertThat(FileUtil.isDirExists(Platforms.TMP_DIR + RandomUtil.nextInt())).isFalse();
	}

	@Test
	public void getName_1_oe() {

		assertThat(FileUtil.getFileName(FilePathUtil.normalizePath("/a/d/b/abc.txt"))).isEqualTo("abc.txt");
	}

	@Test
	public void getName_2_oe() {

		assertThat(FileUtil.getFileName("abc.txt")).isEqualTo("abc.txt");
	}

	@Test
	public void getName_3_oe() {


		assertThat(FileUtil.getFileExtension(FilePathUtil.normalizePath("a/d/b/abc.txt"))).isEqualTo("txt");
	}

	@Test
	public void getName_4_oe() {


		assertThat(FileUtil.getFileExtension(FilePathUtil.normalizePath("/a/d/b/abc"))).isEqualTo("");
	}

	@Test
	public void getName_5_oe() {


		assertThat(FileUtil.getFileExtension(FilePathUtil.normalizePath("/a/d/b/abc."))).isEqualTo("");
	}

}
