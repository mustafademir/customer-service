FROM openjdk:8-jdk-alpine
MAINTAINER Mustafa Demir <mustafademirbm@gmail.com>
ADD target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/customer-service.jar"]
