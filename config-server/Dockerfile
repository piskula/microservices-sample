FROM openjdk:10-slim
RUN apt-get update && apt-get install -y netcat
COPY wait-for.sh /opt/wait-for.sh
RUN ["chmod", "+x", "/opt/wait-for.sh"]
VOLUME /tmp
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
