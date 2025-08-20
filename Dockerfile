# --- ESTÁGIO 1: BUILD ---
FROM maven:3.8-amazoncorretto-17 AS build
WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn clean package -DskipTests

# --- ESTÁGIO 2: RUN ---
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copia qualquer JAR gerado para app.jar
COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080

# Executa a aplicação com o perfil "prod"
ENTRYPOINT ["java","-jar","app.jar", "--spring.profiles.active=prod"]
