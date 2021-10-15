FROM openjdk:15
EXPOSE 8080
ADD /target/conseils-service-0.0.1-SNAPSHOT.jar conseils-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "conseils-service-0.0.1-SNAPSHOT.jar"]