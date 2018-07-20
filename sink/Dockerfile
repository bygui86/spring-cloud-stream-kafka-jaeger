
FROM openjdk:8-jre-alpine
ADD target/ /opt/
WORKDIR /opt
EXPOSE 8083
ENTRYPOINT exec java -Xms128m -Xmx128m $JAVA_OPTS -jar spring-cloud-stream-sink-traced.jar
