package io.diablo73.common.logging.jsonTemplate.processors;

import org.apache.logging.log4j.core.LogEvent;

public interface Replacer {

	String replace(String message, LogEvent logEvent);
}
