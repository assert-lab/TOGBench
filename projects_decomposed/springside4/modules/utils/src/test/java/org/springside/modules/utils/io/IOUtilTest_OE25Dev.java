package org.springside.modules.utils.io;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;
import org.springside.modules.utils.io.type.StringBuilderWriter;
import org.springside.modules.utils.text.Charsets;

public class IOUtilTest_OE25Dev {

	@Test
	public void read_1_oe() throws IOException {
		assertThat(IOUtil.toString(ResourceUtil.asStream("test.txt"))).isEqualTo("ABCDEFG\nABC");
	}

	@Test
	public void read_2_oe() throws IOException {
		// removed other assertion
		assertThat(IOUtil.toLines(ResourceUtil.asStream("test.txt"))).hasSize(2).containsExactly("ABCDEFG", "ABC");
	}

	@Test
	public void write_1_oe() throws IOException {
		StringBuilderWriter sw = new StringBuilderWriter();
		IOUtil.write("hahahaha", sw);
		assertThat(sw.toString()).isEqualTo("hahahaha");
	}

	@Test
	public void write_2_oe() throws IOException {
		StringBuilderWriter sw = new StringBuilderWriter();
		IOUtil.write("hahahaha", sw);
		// removed other assertion

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtil.write("hahahaha", out);
		assertThat(new String(out.toByteArray(), Charsets.UTF_8)).isEqualTo("hahahaha");
	}

}
