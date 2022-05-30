package com.bangahu.main.common.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties("properties.message")
public class AppProps {
	final String test;
	final String error;
	final Job job;
	
	@Getter
	@AllArgsConstructor
	@ConfigurationProperties("properties.message.job")
	public static class Job {
		final String title;
		final String period;
		final Tasks tasks;
		
		@Getter
		@AllArgsConstructor
		@ConfigurationProperties("properties.message.job.tasks")
		public static class Tasks {
			final String clean;
			final String wash;
		}
	}

}
