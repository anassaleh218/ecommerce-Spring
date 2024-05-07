FROM alpine:3.19.1

RUN apk --no-cache add openjdk21 && \
    adduser -D -u 1000 appuser && \
    rm -rf /var/cache/apk/*

WORKDIR /app

COPY --chown=appuser:appuser target/my-application.jar /app/my-application.jar

USER appuser

ENTRYPOINT ["java", "-jar", "my-application.jar"]
