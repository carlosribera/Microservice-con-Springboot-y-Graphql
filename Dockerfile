FROM openjdk:21-slim

ADD target/*.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
# ENTRYPOINT ["sh", "-c", "echo SPRING_DATA_MONGODB_URI=$SPRING_DATA_MONGODB_URI && java -jar app.jar"]