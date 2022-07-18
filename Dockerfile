FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu as build

WORKDIR /app

COPY ./pom.xml /app/pom.xml
COPY ./mvnw /app/mvnw
COPY ./.mvn /app/.mvn

RUN ./mvnw dependency:go-offline

COPY . /app

RUN ./mvnw package

FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

COPY --from=build /app /app

CMD ["java", "-cp", "/app/target/classes", "com.example.App"]