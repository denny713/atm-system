FROM openjdk:17-jdk-alpine AS build
WORKDIR "/app"
COPY . .
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]