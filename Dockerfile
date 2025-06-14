FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/*.jar app.jar

ARG PROFILE=prod
ENV SPRING_PROFILES_ACTIVE=${PROFILE}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]