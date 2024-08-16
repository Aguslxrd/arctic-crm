FROM openjdk:21.0.4
ARG JAR_FILE=target/crm-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} backend_crm_arcticnod.jar
EXPOSE 4300
ENTRYPOINT ["java", "-jar", "backend_crm_a rcticnod.jar"]