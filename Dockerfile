FROM openjdk:17.0.2
COPY nutritym-backend/target/nutritym-backend-1.0.0.jar /app/target/nutritymbackend.jar
ENTRYPOINT ["java", "-jar", "/app/target/nutritymbackend.jar"]