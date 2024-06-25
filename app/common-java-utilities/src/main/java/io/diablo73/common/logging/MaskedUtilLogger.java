package io.diablo73.common.logging;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.diablo73.common.constants.LoggerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

@Slf4j
public class MaskedUtilLogger {

	private static final ObjectMapper piiObjectMapper = new ObjectMapper()
			.configure(MapperFeature.USE_ANNOTATIONS, true)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
			.addMixIn(Object.class, PiiFilterMixIn.class);

	@JsonFilter(LoggerConstants.PII_FILTER)
	private static class PiiFilterMixIn {
	}

	private static final ObjectMapper objectMapper = new ObjectMapper()
			.configure(MapperFeature.USE_ANNOTATIONS, false);


	public static final Set<String> MASKED_FIELDS_LIST = Set.of("piiKey1", "piiKey2");

	private static boolean IS_PII_LOG_MASKING_ENABLED = false;

	private static final SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().addFilter(
			LoggerConstants.PII_FILTER, new CustomPropertyFilter(MASKED_FIELDS_LIST));

	@Value("${piiLogMaskingEnabled:false}")
	public void setPiiLogMaskingEnabled(boolean piiLogMaskingEnabled) {
		IS_PII_LOG_MASKING_ENABLED = piiLogMaskingEnabled;
	}


	public static String getLoggerString(Object o) {
		try {
			if (IS_PII_LOG_MASKING_ENABLED) {
				return piiObjectMapper.writer(simpleFilterProvider).writeValueAsString(o);
			} else {
				return objectMapper.writeValueAsString(o);
			}
		} catch (Exception e) {
			log.error("ERROR : Unable to parse object for logging");
			return o.toString();
		}
	}


	private static class CustomPropertyFilter implements PropertyFilter {

		private final Set<String> ignoredFields;

		public CustomPropertyFilter(Set<String> maskedFieldsSet) {
			ignoredFields = maskedFieldsSet;
		}

		@Override
		public void serializeAsField(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyWriter propertyWriter) throws Exception {
			if (!ignoredFields.contains(propertyWriter.getName())) {
				propertyWriter.serializeAsField(o, jsonGenerator, serializerProvider);
			}
		}

		@Override
		public void serializeAsElement(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyWriter propertyWriter) throws Exception {
		}

		@Override
		public void depositSchemaProperty(PropertyWriter propertyWriter, ObjectNode objectNode, SerializerProvider serializerProvider) throws JsonMappingException {
		}

		@Override
		public void depositSchemaProperty(PropertyWriter propertyWriter, JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException {
		}
	}
}
