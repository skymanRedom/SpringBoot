package com.springcloud.springcloudgateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.springcloud.springcloudgateway.filter.UserLoadBalancerClientFilter;

@Configuration
public class LoadBalancedBean {

	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	    factory.setReadTimeout(2000);
	    factory.setConnectTimeout(5000);
	    return new RestTemplate(factory);
        //return new RestTemplate();
    }
 
    @Bean
    public UserLoadBalancerClientFilter userLoadBalanceClientFilter(LoadBalancerClient client, LoadBalancerProperties properties) {
        return new UserLoadBalancerClientFilter(client, properties);
    }
}
