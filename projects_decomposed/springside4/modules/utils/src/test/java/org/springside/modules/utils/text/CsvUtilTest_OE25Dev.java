package org.springside.modules.utils.text;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class CsvUtilTest_OE25Dev {

	@Test
	public void toCsvString_1_oe() {
		assertThat(CsvUtil.toCsvString(1, 2)).isEqualTo("1,2");
	}

	@Test
	public void toCsvString_2_oe() {

		assertThat(CsvUtil.toCsvString(1, 2, 3, 4)).isEqualTo("1,2,3,4");
	}

	@Test
	public void toCsvString_3_oe() {


		assertThat(CsvUtil.toCsvString(1, "2")).isEqualTo("1,2");
	}

	@Test
	public void toCsvString_4_oe() {



		assertThat(CsvUtil.toCsvString(1, "A BC")).isEqualTo("1,A BC");
	}

	@Test
	public void toCsvString_5_oe() {




		assertThat(CsvUtil.toCsvString(1, "A,BC")).isEqualTo("1,\"A,BC\"");
	}

	@Test
	public void toCsvString_6_oe() {





		assertThat(CsvUtil.toCsvString(1, "A\"BC")).isEqualTo("1,\"A\"\"BC\"");
	}

	@Test
	public void toCsvString_7_oe() {






		assertThat(CsvUtil.toCsvString(1, "A,\"a\"BC")).isEqualTo("1,\"A,\"\"a\"\"BC\"");
	}

	@Test
	public void fromCsvString_1_oe() {
		assertThat(CsvUtil.fromCsvString("1,2")).hasSize(2).contains("1").contains("2");
	}

	@Test
	public void fromCsvString_2_oe() {
		assertThat(CsvUtil.fromCsvString("1,A BC")).hasSize(2).contains("1").contains("A BC");
	}

	@Test
	public void fromCsvString_3_oe() {
		assertThat(CsvUtil.fromCsvString("1,\"A,BC\"")).hasSize(2).contains("1").contains("A,BC");
	}

	@Test
	public void fromCsvString_4_oe() {
		assertThat(CsvUtil.fromCsvString("1,\"A,\"\"a\"\"BC\"")).hasSize(2).contains("1").contains("A,\"a\"BC");
	}

	@Test
	public void fromCsvString_5_oe() {

		assertThat(CsvUtil.fromCsvString("1,\"A,\"a\"\"BC\"")).hasSize(2).contains("1").contains("A,\"a\"BC");
	}

	@Test
	public void fromCsvString_6_oe() {

		assertThat(CsvUtil.fromCsvString("1,ABC\"")).hasSize(2).contains("1").contains("ABC\"");
	}

}
