FROM eclipse-temurin:21-alpine
COPY build/libs/*.jar /app/api.jar
COPY build/libs/proxy /app/proxy
CMD ["java", "-jar", "/app/api.jar"]
