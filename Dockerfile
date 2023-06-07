FROM eclipse-temurin:17-jdk-alpine AS BUILDER

RUN mkdir /app_source
COPY . /app_source

WORKDIR /app_source

RUN chmod +x ./gradlew
RUN ./gradlew :adapter-in:web:copySwaggerUI
RUN ./gradlew :adapter-in:web:bootJar

FROM eclipse-temurin:17-jdk-alpine AS RUNNER

RUN mkdir /app

COPY --from=BUILDER /app_source/adapter-in/web/build/libs /app

WORKDIR /app

ENV TZ=Asia/Seoul

EXPOSE 8080
USER nobody

ARG PHASE
ARG AWS_SECRET_KEY
ARG GOOGLE_CLINET_ID
ARG SERVER_HOST

ENV ENV_PHASE=${PHASE}
ENV ENV_AWS_SECRET_KEY=${AWS_SECRET_KEY}
ENV ENV_GOOGLE_CLINET_ID=${GOOGLE_CLINET_ID}
ENV ENV_SERVER_HOST=${SERVER_HOST}

ENTRYPOINT java -jar \
  -Dspring.profiles.active=${ENV_PHASE:-dev} \
  -Dcloud.aws.credentials.secret-key=${ENV_AWS_SECRET_KEY} \
  -Dsocial.google.client-id=${ENV_GOOGLE_CLINET_ID} \
  -Dbol.server.host=${ENV_SERVER_HOST} \
  /app/*.jar
