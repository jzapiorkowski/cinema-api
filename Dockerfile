FROM gradle:jdk17-alpine AS build
WORKDIR /app
ENV POSTGRES_PASSWORD=$POSTGRES_PASSWORD
ENV POSTGRES_USER=$POSTGRES_USER
ENV POSTGRES_URL=$POSTGRES_URL
COPY . .
RUN gradle build -x test

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/cinema-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","cinema-0.0.1-SNAPSHOT.jar"]