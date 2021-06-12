FROM gradle:6.8.3-jdk11 AS build
COPY  . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle assemble
FROM openjdk:11-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
LABEL org.opencontainers.image.source="https://github.com/facundobocalandro/jibber-jabber-posts"
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production","/app/spring-boot-application.jar"]