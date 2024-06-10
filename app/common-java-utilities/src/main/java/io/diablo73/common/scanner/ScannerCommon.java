package io.diablo73.common.scanner;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class ScannerCommon {

	private static final Scanner scanner = new Scanner(System.in);

	public static int scanInt(String prompt) {
		log.info(prompt + " : ");
		return scanner.nextInt();
	}

	public static double scanDouble(String prompt) {
		log.info(prompt + " : ");
		return scanner.nextDouble();
	}

	public static short scanShort(String prompt) {
		log.info(prompt + " : ");
		return scanner.nextShort();
	}

	public static float scanFloat(String prompt) {
		log.info(prompt + " : ");
		return scanner.nextFloat();
	}
}
