# Dockerfile (service_client)
FROM eclipse-temurin:21-jre

# optionnel : non root
# RUN useradd -ms /bin/bash appuser
WORKDIR /app

# On copie le JAR déjà construit (voir étape 3)
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Port exposé dans le conteneur
EXPOSE 8080

# On laisse la place pour passer JAVA_OPTS si besoin
ENV JAVA_OPTS=""

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
