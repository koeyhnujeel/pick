# 빌드 스테이지
FROM eclipse-temurin:17 AS builder
WORKDIR /src/source
COPY . .
RUN ./gradlew bootJar

# 실행 스테이지
FROM eclipse-temurin:17
WORKDIR /opt/app
COPY --from=builder /src/source/build/libs/*.jar /opt/app/pick-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "pick-app.jar"]
