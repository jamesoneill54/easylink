FROM openjdk:11-jdk

ADD target/easylink-0.0.1-SNAPSHOT.jar .
ENTRYPOINT java -jar easylink-0.0.1-SNAPSHOT.jar
EXPOSE 8081