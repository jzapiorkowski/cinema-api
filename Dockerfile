FROM gradle:jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle build -x test

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/cinema-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java","-jar","cinema-0.0.1-SNAPSHOT.jar"]