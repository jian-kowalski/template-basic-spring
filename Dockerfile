FROM maven:3.8.1-openjdk-11 as build
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN mvn clean install -DspkipTests

FROM openjdk:11-jdk-slim-buster
ENV SPRING_PROFILES=default
EXPOSE 5000 9000
RUN mkdir /app
COPY --from=build /src/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
