FROM maven:3.8-openjdk-11 AS build
WORKDIR /app
COPY immobilier-app /app
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
COPY --from=build /app/target/immobilier.war /app.war
EXPOSE 8080
CMD ["java", "-jar", "/app.war"]
