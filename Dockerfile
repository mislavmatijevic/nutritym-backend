## Build
FROM openjdk:11-ea-11-jdk-slim as build
WORKDIR /build/nutritym-backend
COPY . /build/
RUN ["./mvnw", "install", "-DskipTests"]

## Deploy
FROM alpine:3.15
RUN apk --no-cache add openjdk11 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64/
COPY --from=build /build/nutritym-backend/target/nutritym-backend-1.0.0.jar /
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "nutritym-backend-1.0.0.jar"]

# This container's enviroment variables:
#        - SERVER.PORT=8080
#        - SPRING.DATASOURCE.URL=jdbc:postgresql://db:5432/compose-postgres
#        - SPRING.DATASOURCE.USERNAME=compose-postgres
#        - SPRING.DATASOURCE.PASSWORD=compose-postgres
#        - SPRING.JPA.DEFERDATASOURCEINITIALIZATION=TRUE
#        - SPRING.DATASOURCE.DRIVERCLASSNAME=org.postgresql.Driver
#        - SPRING.JPA.PROPERTIES.HIBERNATE.DIALECT=org.hibernate.dialect.PostgreSQLDialect
#        - SPRING.JPA.GENERATE-DDL=true
#        - SPRING.JPA.SHOW-SQL=true
#        - SPRING.DATASOURCE.SQL-SCRIPT-ENCODING=UTF-8
#        - SPRING.JPA.HIBERNATE.DDL-AUTO=update
#        - SPRING.DATASOURCE.INITIALIZATION-MODE=always
#        - SPRING.DATASOURCE.HIKARI.AUTO-COMMIT=false

# Postgres' containers enviroment variables:
#        - POSTGRES_USER=compose-postgres
#        - POSTGRES_PASSWORD=compose-postgres