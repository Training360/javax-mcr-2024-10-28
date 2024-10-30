FROM eclipse-temurin:21
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-cp", "app.jar", "hello.HelloWorld"]