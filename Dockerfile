FROM openjdk:15
EXPOSE 8085 
ADD /target/Annonce-0.0.1-SNAPSHOT.jar Annonce-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Annonce-0.0.1-SNAPSHOT.jar"]