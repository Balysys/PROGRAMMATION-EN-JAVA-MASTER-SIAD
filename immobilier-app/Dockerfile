# Utiliser une image Java 11 officielle
FROM openjdk:11-jre-slim

# Copier le fichier WAR
COPY target/immobilier.war /usr/local/tomcat/webapps/ROOT.war

# Exposer le port
EXPOSE 8080

# Démarrer Tomcat intégré (via le WAR)
CMD ["java", "-jar", "/usr/local/tomcat/webapps/ROOT.war"]
