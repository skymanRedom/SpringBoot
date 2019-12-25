package com.springcloud.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringCloudGatewayApplication {

	//GatewayControllerEndpoint all routes/routefilters/... controller
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayApplication.class, args);
	}
	
	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/springGateway/simple/**")
                          .filters(
                                f -> f.stripPrefix(1)//去掉前缀：http://localhost:8080/springGateway/simple/1 -->http://localhost:7900/simple/1
                           )
                           .uri("http://localhost:7900/")
                )
                .build();
    }

}
