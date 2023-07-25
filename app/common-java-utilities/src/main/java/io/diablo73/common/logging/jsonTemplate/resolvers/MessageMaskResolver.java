package io.diablo73.common.logging.jsonTemplate.resolvers;

import io.diablo73.common.logging.jsonTemplate.processors.Replacer;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolver;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public class MessageMaskResolver implements EventResolver {

	private final Replacer replacer;

	public MessageMaskResolver(Replacer replacer) {
		this.replacer = replacer;
	}

	@Override
	public void resolve(LogEvent value, JsonWriter jsonWriter) {
		jsonWriter.writeString(replacer.replace(value.getMessage().getFormattedMessage(), value));
	}
}
