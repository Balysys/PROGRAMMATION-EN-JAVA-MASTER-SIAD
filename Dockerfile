# On utilise l'image officielle Tomcat avec Java 17
FROM tomcat:9.0-jdk17-temurin

# On supprime l'application par défaut de Tomcat pour éviter les conflits
RUN rm -rf /usr/local/tomcat/webapps/*

# On copie ton application (le fichier .war) dans le bon dossier
# Remplace "immobilier-app/target/immobilier.war" par le chemin de ton fichier .war
COPY immobilier-app/target/immobilier.war /usr/local/tomcat/webapps/ROOT.war

# On expose le port 8080 pour que Render le détecte
EXPOSE 8080

# On démarre Tomcat
CMD ["catalina.sh", "run"]
