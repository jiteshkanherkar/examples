package org.jitesh.appstatistics.utils;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

public class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	public static <T> T convertJsonToObject(CollectionLikeType _cls, InputStream stream)
			throws JsonParseException, JsonMappingException, IOException {
		
		return (T) mapper.readValue(stream, _cls);
	}
}
