# Etapa de compilación (Builder)
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar el archivo de configuración Maven y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Compilar y empaquetar la aplicación (saltando tests para agilizar)
RUN mvn clean package -DskipTests

# Etapa de ejecución (Runtime)
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el JAR generado desde la etapa de builder
COPY --from=builder /app/target/BiCIAM-1.0-SNAPSHOT.jar app.jar

# Comando de entrada
# Se asume que el JAR tiene configurado el Main-Class en su manifiesto.
# Si no, usar: ENTRYPOINT ["java", "-cp", "app.jar", "nombre.paquete.MainClass"]
ENTRYPOINT ["java", "-jar", "app.jar"]
