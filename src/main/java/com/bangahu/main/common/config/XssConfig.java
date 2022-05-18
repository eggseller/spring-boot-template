package com.bangahu.main.common.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.bangahu.main.common.util.web.HTMLCharacterEscapes;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class XssConfig {
	private final ObjectMapper objMapper = new ObjectMapper();

	@Bean
	public MappingJackson2HttpMessageConverter jsonEscapeConvert() {
		ObjectMapper mapper = objMapper.copy();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
		return new MappingJackson2HttpMessageConverter(mapper);
	}
}