FROM bellsoft/liberica-openjre-alpine:18.0.1.1-2
COPY build/libs/*.jar /app/api.jar
COPY build/libs/proxy /app/proxy
CMD ["/usr/lib/jvm/jre/bin/java", "-jar", "/app/api.jar"]
