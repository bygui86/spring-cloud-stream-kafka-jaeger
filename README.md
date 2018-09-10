# Spring Cloud Stream + Kafka + Jaeger

## topics
* spring cloud stream
* spring cloud stream kafka
* opentracing
* opentracing jaeger

---

## links
* https://github.com/opentracing-contrib/java-spring-messaging
* https://github.com/opentracing-contrib/java-kafka-client

---

## TODOs
Try version >= 0.31.0 of the jaeger-client-java (https://github.com/jaegertracing/jaeger-client-java/) to test if the concurrency issue on logs is solved
in order to enable again the property: opentracing.spring.cloud.log.enabled = true

---

## issues
* https://github.com/opentracing-contrib/java-spring-cloud/issues/5
* https://github.com/jaegertracing/jaeger-client-java/issues/334
* https://github.com/jaegertracing/jaeger-client-java/pull/500

---

## repos
* https://mvnrepository.com/artifact/io.opentracing.contrib/opentracing-spring-jaeger-starter/0.2.0
