--------- cut here -------
FROM openjdk:11-jdk
LABEL maintainer="lulzimbulica@gmail.com"
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
--------- cut here -------