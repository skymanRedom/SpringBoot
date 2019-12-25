package com.springcloud.springcloudgateway.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:api.properties")
@ConfigurationProperties(prefix = "api")
public class RouteDefines {

	public Map<String, String> methods = new HashMap<>();

	public Map<String, String> getMethods() {
		return methods;
	}

	public void setMethods(Map<String, String> methods) {
		this.methods = methods;
	}
}
