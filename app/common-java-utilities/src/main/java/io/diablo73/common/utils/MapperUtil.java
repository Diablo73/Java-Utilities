package io.diablo73.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Diablo73
 * @version 1.0 <br> 27/02/2022
 * @since 27/02/2022
 */

public class MapperUtil {

	private static final ObjectMapper objectMapper = new ObjectMapper();


	public static <T> T convertMap2Object(Map<String, Object> map, Class<T> clazz) {

		return objectMapper.convertValue(map, clazz);
	}

	public static Map<String, Object> convertObject2Map(Object o) {

		return objectMapper.convertValue(o, Map.class);
	}

	public static Map<String, Object> convertJsonString2Map(String s) {

		try {
			return objectMapper.readValue(s, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}

	public static List<Map<String, String>> convertJsonString2List(String s) {

		try {
			return objectMapper.readValue(s, List.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static Object convertJsonString2Object(String s, Class<?> clazz) {

		try {
			return objectMapper.readValue(s, clazz);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}

	public static String convertObject2JsonString(Object o) {

		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "{}";
	}

	public static Object getFieldFromJsonString(String s, String field) {

		return convertJsonString2Map(s).get(field);
	}

}
