package io.diablo73.common.logging.jsonTemplate.plugins;

import io.diablo73.common.logging.jsonTemplate.processors.impls.StringReplacer;
import io.diablo73.common.logging.jsonTemplate.resolvers.MessageMaskResolver;
import io.diablo73.common.utils.MapperUtil;
import org.apache.logging.log4j.layout.template.json.resolver.TemplateResolverConfig;

public final class PluginProvider {

	public static MessageMaskResolver create(TemplateResolverConfig config) {
		return new MessageMaskResolver(new StringReplacer(
				MapperUtil.convertJsonString2Map(config.getString("replaceableRegex"))
		));
	}
}
