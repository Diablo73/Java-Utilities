package io.diablo73.common.logging.jsonTemplate.plugins;

import io.diablo73.common.constants.LoggerConstants;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.layout.template.json.resolver.*;

@Plugin(name = "MessageMaskPlugin", category = TemplateResolverFactory.CATEGORY)
public class MessageMaskPlugin implements EventResolverFactory {

	private static final MessageMaskPlugin INSTANCE = new MessageMaskPlugin();

	@Override
	public String getName() {
		return LoggerConstants.MESSAGE_MASK_PLUGIN_RESOLVER;
	}

	@Override
	public TemplateResolver<LogEvent> create(EventResolverContext context, TemplateResolverConfig config) {
		return PluginProvider.create(config);
	}

	@PluginFactory
	public static MessageMaskPlugin getInstance() {
		return INSTANCE;
	}
}
