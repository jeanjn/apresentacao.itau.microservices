package com.storeapi2.helpers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CORSFilter extends WebMvcConfigurationSupport  {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}
}