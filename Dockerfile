FROM maven:3.5.3-jdk-11 as BUILD
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn -DskipTests=true package
FROM openjdk:11-jre
EXPOSE 8082
COPY --from=BUILD /usr/src/app/target/*.jar /opt/target/app.jar
WORKDIR /opt/target
ENTRYPOINT ["java","-Dspring.profiles.active=deploy","-jar", "app.jar"]