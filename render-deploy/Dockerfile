FROM eclipse-temurin:11-jre-alpine
WORKDIR /app
COPY immobilier.war app.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]
