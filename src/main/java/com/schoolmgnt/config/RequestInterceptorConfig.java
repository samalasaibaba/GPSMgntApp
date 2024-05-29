package com.schoolmgnt.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestInterceptorConfig implements WebMvcConfigurer {

	private static final Logger logger = LogManager.getLogger(RequestInterceptorConfig.class);

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("Inside the RequestInterceptorConfig");
		registry.addInterceptor(new RequestInterceptor());
	}

}
