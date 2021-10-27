package com.esprit.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

import com.esprit.filter.ErrorFilter;
import com.esprit.filter.PostFilter;
import com.esprit.filter.PreFilter;
import com.esprit.filter.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients



public class GatewayServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServer1Application.class, args);
	}
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}


	public ServerCodecConfigurer serverCodecConfigurer() {
	   return ServerCodecConfigurer.create();
	}
	   @Bean
	   
	    public RouteLocator gatewayRoutes() {
	       /* return builder.routes()
	                .route(r -> r.path("/annonce/**")
	                        .uri("http://localhost:8085/")
	                        .id("annonce"))

	                .route(r -> r.path("/consumer/**")
	                        .uri("http://localhost:8082/")
	                        .id("consumerModule"))
	                .build();*/
		   return builder.routes().build();
	    }
	
}


