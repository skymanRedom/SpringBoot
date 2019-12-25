package com.springcloud.springcloudgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springcloud.springcloudgateway.filter.AuthGatewayFilterFactory;

@Configuration
public class GatewayConfig {
	@Bean
    public AuthGatewayFilterFactory tokenFilter(){
        return new AuthGatewayFilterFactory();
    }
}
