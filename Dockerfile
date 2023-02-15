FROM maven:3.8.7-openjdk-18 as build
WORKDIR /build
COPY . .
RUN mvn clean package

FROM openjdk:18
WORKDIR /app
COPY --from=build ./build/target/*.jar ./application.jar
EXPOSE 8080

ENTRYPOINT java -jar application.jar