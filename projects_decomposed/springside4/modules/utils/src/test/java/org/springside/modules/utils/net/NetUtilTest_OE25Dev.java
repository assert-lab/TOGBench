package org.springside.modules.utils.net;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import javax.net.ServerSocketFactory;

import org.junit.Test;
import org.mockito.internal.util.io.IOUtil;

public class NetUtilTest_OE25Dev {

	@Test
	public void localhost_1_oe() {
		assertThat(NetUtil.getLocalHost()).isNotEqualTo("127.0.0.1");
	}

	@Test
	public void localhost_2_oe() {
		// removed other assertion
		assertThat(NetUtil.getLocalAddress().getHostAddress()).isNotEqualTo("127.0.0.1");
	}

	@Test
	public void portDetect_1_oe() throws UnknownHostException, IOException {
		int port = NetUtil.findRandomAvailablePort(20000, 20100);
		assertThat(port).isBetween(20000, 20100);
	}

	@Test
	public void portDetect_2_oe() throws UnknownHostException, IOException {
		int port = NetUtil.findRandomAvailablePort(20000, 20100);
		// removed other assertion
		System.out.println("random port:" + port);

		assertThat(NetUtil.isPortAvailable(port)).isTrue();
	}

	@Test
	public void portDetect_3_oe() throws UnknownHostException, IOException {
		int port = NetUtil.findRandomAvailablePort(20000, 20100);
		// removed other assertion
		System.out.println("random port:" + port);

		// removed other assertion

		int port2 = NetUtil.findAvailablePortFrom(port);
		assertThat(port2).isEqualTo(port);
	}

	@Test
	public void portDetect_4_oe() throws UnknownHostException, IOException {
		int port = NetUtil.findRandomAvailablePort(20000, 20100);
		// removed other assertion
		System.out.println("random port:" + port);

		// removed other assertion

		int port2 = NetUtil.findAvailablePortFrom(port);
		// removed other assertion

		int port3 = NetUtil.findRandomAvailablePort();

		assertThat(port3).isBetween(NetUtil.PORT_RANGE_MIN, NetUtil.PORT_RANGE_MAX);
	}

}
