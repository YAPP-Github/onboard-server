FROM eclipse-temurin:17-jdk-alpine AS BUILDER

ARG PHASE

RUN mkdir /app_source
COPY . /app_source

WORKDIR /app_source

RUN chmod +x ./gradlew
RUN ./gradlew :adapter-in:web:bootJar

FROM eclipse-temurin:17-jdk-alpine AS RUNNER

RUN mkdir /app

COPY --from=BUILDER /app_source/adapter-in/web/build/libs /app

WORKDIR /app

ENV TZ=Asia/Seoul

EXPOSE 8080
USER nobody
ENTRYPOINT java \
  -Dspring.profiles.active=${PHASE:-dev} \
  -jar /app/*.jar
