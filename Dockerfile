FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

COPY ./target/classes /app

CMD ["java", "-cp", "/app", "com.example.App"]