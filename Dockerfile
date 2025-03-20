# Usa una imagen base con Java 21 y Maven para la compilación
FROM eclipse-temurin:21-jdk AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos del proyecto al contenedor
COPY . .

# Da permisos de ejecución al Maven Wrapper
RUN chmod +x mvnw

# Construye el proyecto sin ejecutar pruebas
RUN ./mvnw -B -DskipTests clean package

# Usa una imagen más ligera para ejecutar el JAR
FROM eclipse-temurin:21-jre AS runtime

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado desde la fase de construcción
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto 8080 para que el servicio pueda ser accesible
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
