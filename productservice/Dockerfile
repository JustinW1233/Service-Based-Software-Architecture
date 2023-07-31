FROM maven:3.9-sapmachine-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#FROM tomcat:8-jdk8-openjdk
FROM tomcat:11.0.0-jdk17
COPY --from=build /home/app/target/ProductServiceAPI.war /usr/local/tomcat/webapps/ROOT.war

ENV SERVER_PORT=8080
ENV MONGO_HOST=ProductServiceDBMongo
ENV MONGO_PORT=27017
ENV MONGO_DB=ProductService
ENV EUREKA_HOST=SEN300EurekaRegistry

# ADD ./target/EcommerceProductServiceAPI.war /usr/local/tomcat/webapps/ROOT.war
