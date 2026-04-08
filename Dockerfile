# Utiliser l'image Tomcat officielle avec Java 11
FROM tomcat:9.0-jdk11-openjdk-slim

# Supprimer l'application par défaut de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copier le fichier WAR (à la racine du dépôt)
COPY immobilier-app/target/immobilier.war /usr/local/tomcat/webapps/ROOT.war

# Exposer le port 8080
EXPOSE 8080

# Démarrer Tomcat
CMD ["catalina.sh", "run"]
