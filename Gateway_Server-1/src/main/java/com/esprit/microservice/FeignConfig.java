package com.esprit.microservice;

import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfig {
	
	public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("user", "password");
	}}
