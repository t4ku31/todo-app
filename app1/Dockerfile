FROM maven:3.9.3-eclipse-temurin-17
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.fork=false"]
