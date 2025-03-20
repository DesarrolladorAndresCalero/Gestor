# Etapa de construcción
FROM amazoncorretto:21 AS build

WORKDIR /app
COPY . .

# Otorga permisos de ejecución a Maven Wrapper
RUN chmod +x mvnw

# Construye el proyecto sin ejecutar pruebas
RUN ./mvnw -B -DskipTests clean package

# Etapa de ejecución con una imagen más ligera
FROM amazoncorretto:21 AS runtime

WORKDIR /app

# Copia el archivo JAR generado en la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto 8080 para la aplicación
EXPOSE 8080

# Comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
