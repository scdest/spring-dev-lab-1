FROM amazoncorretto:17.0.7

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} com.springdev.lab1.app.jar

ENTRYPOINT ["java","-jar","/com.springdev.lab1.app.jar"]