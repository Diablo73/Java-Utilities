package unitTests.utils;

import io.diablo73.common.utils.InetUtil;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * @author Diablo73
 * @version 1.0 <br> 10/03/2022
 * @since 27/02/2022
 */

public class InetUtilTests {

	private static final String ipv4 = "127.0.0.1";
	private static final String ipv6 = "0:0:0:0:0:0:0:1";

	@Test
	public void getServerNameTestCase() {
		Assert.assertFalse(InetUtil.getServerName().isEmpty());
	}

	@Test
	public void getServerNameExceptionTestCase() {
		MockedStatic<InetAddress> inetAddressMockedStatic = Mockito.mockStatic(InetAddress.class);
		UnknownHostException unknownHostException = new UnknownHostException("Exception output of InetUtilTests.getServerNameExceptionTestCase()!!!");
		inetAddressMockedStatic.when(InetAddress::getLocalHost).thenThrow(unknownHostException);
		Assert.assertTrue(InetUtil.getServerName().isEmpty());
		inetAddressMockedStatic.close();
	}

	@Test
	public void getLocalIPListTestCase() {
		Map<String, List<String>> actual = InetUtil.getLocalIPList();
		List<String> ipv4List = actual.get("ipv4");
		List<String> ipv6List = actual.get("ipv6");

		Assert.assertTrue(ipv4List.contains(ipv4));
		Assert.assertTrue(ipv6List.contains(ipv6));
	}

	@Test
	public void getLocalIPListExceptionTestCase() {
		MockedStatic<NetworkInterface> networkInterfaceMockedStatic = Mockito.mockStatic(NetworkInterface.class);
		SocketException socketException = new SocketException("Exception output of InetUtilTests.getLocalIPListExceptionTestCase()!!!");
		networkInterfaceMockedStatic.when(NetworkInterface::getNetworkInterfaces).thenThrow(socketException);

		Map<String, List<String>> actual = InetUtil.getLocalIPList();
		List<String> ipv4List = actual.get("ipv4");
		List<String> ipv6List = actual.get("ipv6");

		Assert.assertTrue(ipv4List.isEmpty());
		Assert.assertTrue(ipv6List.isEmpty());

		networkInterfaceMockedStatic.close();
	}

}
