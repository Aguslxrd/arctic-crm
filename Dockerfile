# Usar una imagen de base con OpenJDK 17 (o la versión que estés utilizando)
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/crm-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 4300

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
