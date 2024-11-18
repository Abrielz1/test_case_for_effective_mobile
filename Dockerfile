FROM openjdk:17-jdk-slim
LABEL authors="Abriel"
WORKDIR /app
COPY target/test-case-0.0.1-SNAPSHOT.jarc test-case.jar
CMD ["java","-jar","test-case.jar"]