FROM openjdk:8
EXPOSE 8097
ADD  /target/Panier-0.0.1-SNAPSHOT.jar  microservice-panier.jar
ENTRYPOINT ["java","-jar","microservice-panier.jar"]

