FROM gradle:jdk21 AS build

WORKDIR /app

COPY gradle/ ./gradle/
COPY gradlew gradlew.bat ./
COPY build.gradle settings.gradle ./

RUN chmod +x ./gradlew

COPY beribturing-accent/ ./beribturing-accent/
COPY beribturing-aggregate/ ./beribturing-aggregate/
COPY beribturing-boot/ ./beribturing-boot/
COPY beribturing-facade/ ./beribturing-facade/
COPY beribturing-feature/ ./beribturing-feature/
COPY beribturing-provision/ ./beribturing-provision/
COPY beribturing-store-jpa/ ./beribturing-store-jpa/
COPY gradle/wrapper/ ./gradle/wrapper/

RUN ./gradlew build -x test

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/beribturing-boot/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
