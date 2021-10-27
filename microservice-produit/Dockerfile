<<<<<<< HEAD
FROM node:14

RUN mkdir -p /usr/src/app 

WORKDIR /usr/src/app

COPY package*.json /usr/src/app

RUN npm install

COPY . /usr/src/app

EXPOSE 3000

CMD [ "npm", "start" ]
=======
FROM openjdk:15
EXPOSE 8085 
ADD /target/Annonce-0.0.1-SNAPSHOT.jar Annonce-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Annonce-0.0.1-SNAPSHOT.jar"]
>>>>>>> origin/samar
