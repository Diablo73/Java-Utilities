package io.diablo73.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @author Diablo73
 * @version 1.0 <br> 27/02/2022
 * @since 27/02/2022
 */

public class InetUtil {

	public static String getServerName() {
		InetAddress inetAddress;
		String hostName = StringUtils.EMPTY;
		try {
			inetAddress = InetAddress.getLocalHost();
			hostName = inetAddress.getHostName();
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
		return hostName;
	}

	public static Map<String, List<String>> getLocalIPList() {
		List<String> ipv4List = new ArrayList<>();
		List<String> ipv6List = new ArrayList<>();
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			NetworkInterface networkInterface;
			Enumeration<InetAddress> inetAddresses;
			InetAddress inetAddress;
			String ip;
			while (networkInterfaces.hasMoreElements()) {
				networkInterface = networkInterfaces.nextElement();
				inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					inetAddress = inetAddresses.nextElement();
					if (inetAddress instanceof Inet4Address) {
						ip = inetAddress.getHostAddress();
						ipv4List.add(ip);
					} else if (inetAddress instanceof Inet6Address) {
						ip = inetAddress.getHostAddress();
						ipv6List.add(ip);
					}
				}
			}
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		}
		return Map.of(
				"ipv4", ipv4List,
				"ipv6", ipv6List);
	}

}
