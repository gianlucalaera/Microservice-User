FROM openjdk:17
ARG JAR_FILE
COPY ${JAR_FILE} kbe-rest-brewery-user.jar
ENTRYPOINT ["java","-jar","/kbe-rest-brewery-user.jar"]
EXPOSE 8082