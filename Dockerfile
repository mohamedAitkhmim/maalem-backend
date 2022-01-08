FROM maven:3.6.3-jdk-8 as BUILD
ADD repository.tar.gz /usr/share/maven/ref/
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn -s /usr/share/maven/ref/settings-docker.xml package
FROM openjdk:8-jre
COPY --from=BUILD /usr/src/app/target/*.jar /opt/target/app.jar
WORKDIR /opt/target
ENTRYPOINT ["java","-Dspring.profiles.active=deploy","-jar","app.jar"]
#FROM adoptopenjdk/openjdk11:ubi
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-Dspring.profiles.active=deploy","-jar","/app.jar"]
