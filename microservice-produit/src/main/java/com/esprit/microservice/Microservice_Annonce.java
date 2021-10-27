package com.esprit.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient
public class Microservice_Annonce {

	public static void main(String[] args) {
		SpringApplication.run(Microservice_Annonce.class, args);
		System.out.println("done");
	}
@Autowired
AnnonceRepository annonceRepository;

@Bean
ApplicationRunner init() {
	return (args) -> {
		// save
		//annonceRepository.save(new Annonce("Annonce chien","Voici annonce2","mona","2"));
		//annonceRepository.save(new Candidat("Sarra", "ab", "sa@esprit.tn"));
		//annonceRepository.save(new Candidat("Mohamed", "ba", "mo@esprit.tn"));
		// fetch
		//annonceRepository.findAll().forEach(System.out::println);

	};
}

}
