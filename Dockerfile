FROM openjdk:15-jdk-alpine

ADD target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=production -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]