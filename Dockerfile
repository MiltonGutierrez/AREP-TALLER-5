FROM openjdk:17

WORKDIR /property_app

COPY /target/App-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]