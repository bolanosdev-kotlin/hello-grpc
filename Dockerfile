FROM openjdk:17-jdk-slim

RUN echo $(java --version)
COPY docker-entrypoint.sh /
COPY target/hello-grpc-1.0.0.jar /

EXPOSE 6565

ENTRYPOINT /docker-entrypoint.sh

