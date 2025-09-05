# ====== build ======
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -B -DskipTests package

# ====== runtime ======
FROM eclipse-temurin:21-jre
WORKDIR /app
ENV JAVA_OPTS=""
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8074
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
