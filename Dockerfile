FROM openjdk:17-jdk-slim

COPY target/crm-0.0.1-SNAPSHOT.jar crm-0.0.1-SNAPSHOT.jar.jar

ENTRYPOINT ["java", "-jar", "crm-0.0.1-SNAPSHOT.jar.jar"]
