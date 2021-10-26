package com.esprit.microservice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class CommentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentApplication.class, args);
	}
	
	@Autowired
	private CommentService commentService;
	
	@Bean
	ApplicationRunner init() {
		return (args) -> {
			
			Date today = new Date();
			// save
			//commentService.addComment(new Comment("sabrine",today,14),14);
			// fetch
			commentService.retrieveAllComments().forEach(System.out::println);

		};
	}

}
