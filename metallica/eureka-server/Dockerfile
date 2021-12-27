FROM adoptopenjdk/openjdk8:latest
MAINTAINER Arvidn Srivastava
COPY ./target/eureka-server-*.jar /opt/apps/eureka-server.jar
ENTRYPOINT ["java", "-jar", "/opt/apps/eureka-server.jar"]
