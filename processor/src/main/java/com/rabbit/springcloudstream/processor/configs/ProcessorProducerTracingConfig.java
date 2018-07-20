package com.rabbit.springcloudstream.processor.configs;

import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.spring.TracingProducerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration("processorProducerTracingConfig")
// @AutoConfigureAfter(ProcessorConsumerConfig.class)
public class ProcessorProducerTracingConfig {

	// Declare Tracer bean
	// PLEASE NOTE: Tracer already defined from Jaeger: JaegerTracer

	// Declare ProducerFactory properties
	protected Map<String, Object> kafkaProducerConfigs() {

		log.debug("Loading kafka producer configs...");

		// TODO take configurations from properties
		final Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "processor");
		props.put(ProducerConfig.RETRIES_CONFIG, 3);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
		return props;
	}

	// Decorate ProducerFactory with TracingProducerFactory
	@Bean({"kafkaProducerFactory", "producerFactory"})
	@Resource(name = "tracer")
	public ProducerFactory<String, Long> kafkaProducerFactory(final Tracer tracer) {

		log.debug("Loading kafka producer factory...");

		return new TracingProducerFactory<>(
				// TODO separate creation of producerfactory
				new DefaultKafkaProducerFactory<>(
						kafkaProducerConfigs()
				),
				tracer
		);
	}

	// Use decorated ProducerFactory in KafkaTemplate
	@Bean("kafkaTemplate")
	@Resource(name = "kafkaProducerFactory")
	public KafkaTemplate<String, Long> kafkaTemplate(final ProducerFactory<String, Long> producerFactory) {

		log.debug("Loading kafka template...");

		final KafkaTemplate<String, Long> kafkaTemplate = new KafkaTemplate<>(producerFactory);

		// TODO take configurations from properties
		kafkaTemplate.setDefaultTopic("timestamp");
		return kafkaTemplate;
	}

}
