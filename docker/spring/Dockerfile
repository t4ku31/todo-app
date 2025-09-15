# 開発用
FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# ボリュームで /app にソースが入る前提で、Maven で起動
CMD ["mvn", "spring-boot:run"]