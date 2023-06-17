package unitTests.printBeautify;

import io.diablo73.common.banner.GraffitiBanner;
import io.diablo73.common.constants.ConsoleColorConstants;
import io.diablo73.common.utils.MathUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Diablo73
 * @version 1.0 <br> 11/03/2022
 * @since 10/03/2022
 */

public class PrintBeautify {

	private static final String HORIZONTAL_BORDER = "-";
	private static int i = 0;

	public static void printBeautify(Map<String, List<Long>> algorithmsMapList, int size, String msg) {

		Map<String, Double> algorithmsMapAverage = new HashMap<>();
		for (Map.Entry<String, List<Long>> algo : algorithmsMapList.entrySet()) {
			algorithmsMapAverage.put(algo.getKey(), MathUtil.averageByLoop(algo.getValue()));
		}
		i = 0;

		String border = String.format("+%24s+%13s+", HORIZONTAL_BORDER.repeat(26), HORIZONTAL_BORDER.repeat(14));

		StringBuilder printStringBuilder = new StringBuilder();
		printStringBuilder.append(String.format("\n%73s\n", border));

		printStringBuilder.append(ConsoleColorConstants.BLUE_BOLD_BRIGHT);
		printStringBuilder.append(String.format("%73s\n", String.format("| %24s | %12s |", "Algorithm", "Time taken")));
		printStringBuilder.append(String.format("%73s\n", String.format("| %24s | %12s |", "( size = " + size + " )", "( in ms )")));
		printStringBuilder.append(ConsoleColorConstants.RESET);

		printStringBuilder.append(String.format("%73s\n", border));

		algorithmsMapAverage.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
				.forEach(
						k -> append(printStringBuilder, k, algorithmsMapAverage.keySet().size()));

		printStringBuilder.append(String.format("%73s\n\n", border));

		GraffitiBanner.createGraffiti(msg);
		System.out.format(printStringBuilder.toString());
	}

	private static void append(StringBuilder printStringBuilder, Map.Entry<String, Double> k, int size) {
		if (i == 0) {
			printStringBuilder.append(ConsoleColorConstants.GREEN_BRIGHT);
			printStringBuilder.append(String.format("%73s\n", String.format("| %24s | %12.3f |", k.getKey(), k.getValue())));
			printStringBuilder.append(ConsoleColorConstants.RESET);
		} else if (i == size - 1) {
			printStringBuilder.append(ConsoleColorConstants.RED_BRIGHT);
			printStringBuilder.append(String.format("%73s\n", String.format("| %24s | %12.3f |", k.getKey(), k.getValue())));
			printStringBuilder.append(ConsoleColorConstants.RESET);
		} else {
			printStringBuilder.append(String.format("%73s\n", String.format("| %24s | %12.3f |", k.getKey(), k.getValue())));
		}
		i++;
	}
}
