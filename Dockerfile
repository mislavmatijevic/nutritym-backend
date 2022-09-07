## Build
FROM alpine:3.16 as build
COPY nutritym-backend /build
WORKDIR /build
RUN apk --no-cache add openjdk11-jdk && \
    chmod 700 mvnw                   && \
    ./mvnw install -DskipTests

## Deploy
FROM alpine:3.16
RUN apk --no-cache add openjdk11-jre-headless
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 \
    SPRING.DATASOURCE.URL=jdbc:postgresql://Nutritym-DB:5432/compose-postgres \
    SPRING.DATASOURCE.USERNAME=compose-postgres \
    SPRING.DATASOURCE.PASSWORD=compose-postgres \
    \
    SERVER.PORT=8080 \
    \
    SPRING.JPA.DEFERDATASOURCEINITIALIZATION=TRUE \
    SPRING.DATASOURCE.DRIVERCLASSNAME=org.postgresql.Driver \
    SPRING.JPA.PROPERTIES.HIBERNATE.DIALECT=org.hibernate.dialect.PostgreSQLDialect \
    SPRING.JPA.GENERATE-DDL=true \
    SPRING.JPA.SHOW-SQL=true \
    SPRING.DATASOURCE.SQL-SCRIPT-ENCODING=UTF-8 \
    SPRING.JPA.HIBERNATE.DDL-AUTO=update \
    SPRING.DATASOURCE.INITIALIZATION-MODE=always \
    SPRING.DATASOURCE.HIKARI.AUTO-COMMIT=false
COPY --from=build /build/target/nutritym-backend-1.0.0.jar /
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "nutritym-backend-1.0.0.jar"]


# Postgres' containers enviroment variables:
#        - POSTGRES_USER=compose-postgres
#        - POSTGRES_PASSWORD=compose-postgres