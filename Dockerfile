FROM openjdk:17-jdk-slim
COPY target/userFile-0.0.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
