FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/test-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]