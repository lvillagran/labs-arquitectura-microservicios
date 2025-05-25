# Imagen base con Java
FROM openjdk:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el jar compilado al contenedor
COPY target/sprint-01-springboot-mongodb-docker-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar el jar
ENTRYPOINT ["java", "-jar", "app.jar"]