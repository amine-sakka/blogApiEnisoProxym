FROM maven:3.5-jdk-8
FROM java:8
WORKDIR /app
COPY target/*.jar /app/blogApiProjectAc.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","blogApiProjectAc.jar"]
