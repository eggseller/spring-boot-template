package com.bangahu.main.common.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Getter;


@ConfigurationProperties("properties.message")
@ConstructorBinding @AllArgsConstructor @Getter
public class AppProps {

	private final String test;

}
