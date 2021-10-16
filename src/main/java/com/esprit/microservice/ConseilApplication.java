package com.esprit.microservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ConseilApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConseilApplication.class, args);
	}
	
	@Autowired
	private PostService postService;
	
	@Bean
	ApplicationRunner init() {
		return (args) -> {
			
			Date today = new Date();
			// save
			//postService.addPost(new Post("sabrine", "fliss",today,14),14);
			// fetch
			postService.retrieveAllPosts().forEach(System.out::println);

		};
	}
	
	

}


