package org.springside.modules.utils.net;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class IPUtilTest_OE25Dev {

	@Test
	public void stringAndInt() {

		assertThat(IPUtil.ipv4StringToInt("192.168.0.1")).isEqualTo(-1062731775);
		assertThat(IPUtil.ipv4StringToInt("192.168.0.2")).isEqualTo(-1062731774);

		assertThat(IPUtil.intToIpv4String(-1062731775)).isEqualTo("192.168.0.1");
		assertThat(IPUtil.intToIpv4String(-1062731774)).isEqualTo("192.168.0.2");
	}

	@Test
	public void inetAddress() {

		assertThat(IPUtil.fromInt(-1062731775).getHostAddress()).isEqualTo("192.168.0.1");
		assertThat(IPUtil.fromInt(-1062731774).getHostAddress()).isEqualTo("192.168.0.2");

		assertThat(IPUtil.fromIpString("192.168.0.1").getHostAddress()).isEqualTo("192.168.0.1");
		assertThat(IPUtil.fromIpString("192.168.0.2").getHostAddress()).isEqualTo("192.168.0.2");
		assertThat(IPUtil.fromIpv4String("192.168.0.1").getHostAddress()).isEqualTo("192.168.0.1");
		assertThat(IPUtil.fromIpv4String("192.168.0.2").getHostAddress()).isEqualTo("192.168.0.2");

		assertThat(IPUtil.toInt(IPUtil.fromIpString("192.168.0.1"))).isEqualTo(-1062731775);
	}

	@Test
	public void stringAndInt_1_oe() {

		assertThat(IPUtil.ipv4StringToInt("192.168.0.1")).isEqualTo(-1062731775);
	}

	@Test
	public void stringAndInt_2_oe() {

		// removed other assertion
		assertThat(IPUtil.ipv4StringToInt("192.168.0.2")).isEqualTo(-1062731774);
	}

	@Test
	public void stringAndInt_3_oe() {

		// removed other assertion
		// removed other assertion

		assertThat(IPUtil.intToIpv4String(-1062731775)).isEqualTo("192.168.0.1");
	}

	@Test
	public void stringAndInt_4_oe() {

		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(IPUtil.intToIpv4String(-1062731774)).isEqualTo("192.168.0.2");
	}

	@Test
	public void inetAddress_1_oe() {

		assertThat(IPUtil.fromInt(-1062731775).getHostAddress()).isEqualTo("192.168.0.1");
	}

	@Test
	public void inetAddress_2_oe() {

		// removed other assertion
		assertThat(IPUtil.fromInt(-1062731774).getHostAddress()).isEqualTo("192.168.0.2");
	}

	@Test
	public void inetAddress_3_oe() {

		// removed other assertion
		// removed other assertion

		assertThat(IPUtil.fromIpString("192.168.0.1").getHostAddress()).isEqualTo("192.168.0.1");
	}

	@Test
	public void inetAddress_4_oe() {

		// removed other assertion
		// removed other assertion

		// removed other assertion
		assertThat(IPUtil.fromIpString("192.168.0.2").getHostAddress()).isEqualTo("192.168.0.2");
	}

	@Test
	public void inetAddress_5_oe() {

		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		assertThat(IPUtil.fromIpv4String("192.168.0.1").getHostAddress()).isEqualTo("192.168.0.1");
	}

	@Test
	public void inetAddress_6_oe() {

		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		assertThat(IPUtil.fromIpv4String("192.168.0.2").getHostAddress()).isEqualTo("192.168.0.2");
	}

	@Test
	public void inetAddress_7_oe() {

		// removed other assertion
		// removed other assertion

		// removed other assertion
		// removed other assertion
		// removed other assertion
		// removed other assertion

		assertThat(IPUtil.toInt(IPUtil.fromIpString("192.168.0.1"))).isEqualTo(-1062731775);
	}

}
