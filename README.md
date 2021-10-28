# Projet Microservice - 

> 

## Version

| Branch                                                                                                       |  Version | Gestion                                               | 
| ------------------------------------------------------------------------------------------------------------ | ----- | ---------------------------------------------------- |
| main                                                                                                       | v0  | Intégration        | 
| siwar                                                                                                      | v1    | Panier     | 
| sabrine                                                                                                    | v1    | Post |
| samar                                                                                                      | v1    | Annonce             |
| chekib                                                                                                     | v1    | Produit              | 

## Features

- Vous pouvez acheter des animaux ou leurs accessoires 
- Vous pouvez obtenir des conseils à propos vos animaux
- Vous pouvez postulez des annonces 



## Overview

- [Instructions](#instructions)
  - [Features](#features)
  - [Overview](#overview)
  - [Travail Réalisé](#prisma-setup)
    - [1. Installation des dépendences Spring Boot\NodeJs](#1-install-dependencies)
    - [2. Configuration de Docker](#2-docker-comfiguration)
    - [3. MySqL with Docker](#3-MySql-with-docker)
    - [4. Configuration Eureka avec NodeJs](#4-eureka-with-node)
    - [5. Configuration Eureka avec Spring Boot](#5-eureka-with-spring)
    - [6. Configuration de Gateway Server](#6-gateway-server-configaration)
   
## Travail réalisé

### 1. Installation des dépendences Spring Boot\NodeJs

Installation des dépendence pour les microservices Comment, MicroService-Panier, Conseil, Microservice_Annonce, Microservice_produit:
Installation: Faire Maven update pour générer les dépendences


Installation des dépendence pour le microservice MicroService_user:

```bash
npm install
```

### 2. Configuration de Docker

Pour la configuration du docker, on a crée un fichier Dockerfile pour chaque microservices:
Dockerfile pour les microservices réalisé avec Spring Boot:

```bash
FROM openjdk:15
EXPOSE 9090
ADD /target/conseils-service-0.0.3-SNAPSHOT.jar conseils-service-0.0.3-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "conseils-service-0.0.3-SNAPSHOT.jar"]
```
Dockerfile pour les microservices réalisé avec NodeJs:

```bash
FROM node:14
RUN mkdir -p /usr/src/app 
WORKDIR /usr/src/app
COPY package*.json /usr/src/app
RUN npm install
COPY . /usr/src/app
EXPOSE 3000
CMD [ "npm", "start" ]
```

### 3. MySqL with Docker

Pour la communication avec la base de donnée du microservice comment, on a ajouté au fichier docker compose les configurations suivantes:

```bash
db:
    image: mysql:5.7
    container_name: db
    environment:
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: comment-microservice
      MYSQL_USER: user
      MYSQL_PASSWORD: password
    ports:
      - "3306:3306"
    volumes:
      - dbdata:/var/lib/mysql
  phpmyadmin:
    image: phpmyadmin/phpmyadmin
    container_name: pma
    links:
      - db
    environment:
      PMA_HOST: db
      PMA_PORT: 3306
      PMA_ARBITRARY: 1
    restart: always
    ports:
      - 8081:80
```

Lancer le docker avec la commande suivante:

```bash
docker-compose up
```


### 4. Configuration Eureka avec NodeJs

Création de nouveau fichier eurekaClient:
```bash
const Eureka = require('eureka-js-client').Eureka;
const eurekaHost = (process.env.EUREKA_CLIENT_SERVICEURL_DEFAULTZONE || 'discovery');
const eurekaPort = 8761;
const hostName = (process.env.HOSTNAME || 'discovery')
const ipAddr = 'discovery';

exports.registerWithEureka = function(appName, PORT) {
    const client = new Eureka({
    instance: {
      app: appName,
      hostName: hostName,
      ipAddr: ipAddr,
      port: {
        '$': PORT,
        '@enabled': 'true',
      },
      vipAddress: appName,
      dataCenterInfo: {
        '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
        name: 'MyOwn',
      },
    },
    //retry 10 time for 3 minute 20 seconds.
    eureka: {
      host: eurekaHost,
      port: eurekaPort,
      servicePath: '/eureka/apps/',
      maxRetries: 10,
      requestRetryDelay: 2000,
    },
  })
```

```bash
var eurekaclient=require('../eurekaclient')
eurekaclient.registerWithEureka('usermicroservice',port);
```

### 6.  Configuration Eureka avec Spring Boot

Créer un nouveau Projet Spring Boot et ajouter les dépendences dans le fichier pom.xml ensuite faire maven update pour le projet
```bash
<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>


```

Dans la classe main de votre projet configurer votrer serveur Eureka
```bash
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}

}
```
Dans le fichier application.proprietés:
```bash
spring.application.name=eureka-service
server.port=8761
eureka.client.fetch-registry=false
eureka.client.register-with-eureka=false
```
Dans les clients de seveur Eureka Il faut ajouter les dépendences suivantes dans le fichier pom.xml.
```bash
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
```
Il faut configurer les autres microservices en tant que des cllients Eureka .
```bash
eureka.client.server-url.default-zone=http://discovery:8761/eureka/
```
Dans la classe main de votre projet:
```bash
@SpringBootApplication
@EnableEurekaClient
public class ConseilApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConseilApplication.class, args);
	}
 ```
 ### 7.Configuration de Gateway Server
 Pour configurer votre gateway server il faut créer un nouveau projet SpringBoot,ensuite il faut ajouter les dépendences:
 ```bash
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-zuul</artifactId>
		</dependency>
 ```
 Dans le fichier application.proprieté faire les configurations suivantes.
 
 ```bash
spring.application.name= zuul-service
server.port= 8762

#Déclaration des microservices à utiliser
zuul.routes.annonce-endpoint.serviceId=annonce-microservice
zuul.routes.annonce-service.path=/annonce-microservice/*

zuul.routes.user-endpoint.serviceId=user-microservice
zuul.routes.user-service.path=/user-microservice/*

zuul.routes.panier-endpoint.serviceId=panier-service
zuul.routes.panier-service.path=/panier-service/*

zuul.routes.conseil-endpoint.serviceId=conseil-service
zuul.routes.conseil-service.path=/conseil-service/*

zuul.routes.produit-endpoint.serviceId=produit-service
zuul.routes.produit-service.path=/produit-service/*

zuul.routes.comments-endpoint.serviceId=comment-service
zuul.routes.comments-service.path=/comment-service/*
 ```

 
**[⬆ back to top](#overview)**


