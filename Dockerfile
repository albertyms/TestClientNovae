FROM maven:3.3-jdk-8
VOLUME /tmp
EXPOSE 8082
ADD target/ClientNovae-0.0.1-SNAPSHOT.jar ClientNovae-0.0.1.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ClientNovae-0.0.1.jar"]