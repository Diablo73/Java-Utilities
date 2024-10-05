package io.diablo73.common.utils.fileUtils;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OpenCsvUtil {

	public static List<List<String>> readAllLines(String filePath) {
		try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
			List<List<String>> csvContent = new ArrayList<>();
			for (String[] array : csvReader.readAll()) {
				csvContent.add(new ArrayList<>(List.of(array)));
			}
			return csvContent;
		} catch (Exception e) {
			log.error(String.valueOf(e));
		}
		return new ArrayList<>();
	}
}
