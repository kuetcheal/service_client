# ---- Build JAR
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests package

# ---- Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app
# renomme proprement le JAR produit par Maven
ARG APP_JAR=app.jar
COPY --from=build /app/target/*.jar /app/${APP_JAR}
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
