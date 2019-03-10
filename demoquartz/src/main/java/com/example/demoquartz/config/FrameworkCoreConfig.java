package com.example.demoquartz.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.TimeZone;

/**
 *
 * @author by wolf on 2017/10/20.
 */
@Configuration
@Slf4j
public class FrameworkCoreConfig {

	private MessageSource messageSource;

	public FrameworkCoreConfig(MessageSource messageSource) {
		log.debug("init fable core config.");
		this.messageSource = messageSource;
	}

	@Bean
	public Env env(Environment environment) {
		return new Env(environment);
	}

	@Bean
	public ApplicationContextHolder applicationContextHolder() {
		return new ApplicationContextHolder();
	}


	// Jackson日期反序列化时区问题
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
		return jacksonObjectMapperBuilder ->
				jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone("GMT+8"));
	}

}
