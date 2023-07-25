package io.diablo73.common.logging.jsonTemplate.processors.impls;

import io.diablo73.common.logging.jsonTemplate.processors.Replacer;
import org.apache.logging.log4j.core.LogEvent;

import java.util.Map;

public class StringReplacer implements Replacer {

	private final Map<String, Object> regexes;

	public StringReplacer(Map<String, Object> replaceRegex) {
		regexes = replaceRegex;
	}

	@Override
	public String replace(String message, LogEvent logEvent) {
		for (String regex : regexes.keySet()) {
			message = message.replaceAll(regex, String.valueOf(regexes.get(regex)));
		}
		return message;
	}
}
