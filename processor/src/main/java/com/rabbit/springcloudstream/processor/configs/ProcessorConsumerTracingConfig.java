package com.rabbit.springcloudstream.processor.configs;

import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.spring.TracingConsumerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration("processorTracingConfig")
// @AutoConfigureAfter(ProcessorStreamConfig.class)
public class ProcessorConsumerTracingConfig {

	// Declare Tracer bean
	// PLEASE NOTE: Tracer already defined from Jaeger: JaegerTracer

	// Declare ConsumerFactory properties
	protected Map<String, Object> kafkaConsumerConfigs() {

		log.debug("Creating consumer configurations...");

		// TODO take configurations from properties
		final Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, "sink");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "timestamp-processors");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
		return props;
	}

	// Decorate ConsumerFactory with TracingConsumerFactory
	@Bean({"kafkaConsumerFactory", "consumerFactory"})
	@Resource(name = "tracer")
	public ConsumerFactory<String, Long> kafkaConsumerFactory(final Tracer tracer) {

		log.debug("Loading kafka producer factory...");

		return new TracingConsumerFactory<>(
				// TODO separate creation of consumerfactory
				new DefaultKafkaConsumerFactory<>(
						kafkaConsumerConfigs()
				),
				tracer
		);
	}

	// Use decorated ConsumerFactory to override creation of bean ConcurrentKafkaListenerContainerFactory
	@Bean(name = "kafkaListenerContainerFactory")
	@Resource(name = "kafkaConsumerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, Long> kafkaListenerContainerFactory(final ConsumerFactory<String, Long> consumerFactory) {

		log.debug("Loading kafka listener container factory...");

		final ConcurrentKafkaListenerContainerFactory<String, Long> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		kafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
		return kafkaListenerContainerFactory;
	}

	// coming from classes:
	// KafkaAnnotationDrivenConfiguration
	// ConcurrentKafkaListenerContainerFactoryConfigurer

	// @Bean(name = "kafkaListenerContainerFactory")
	// @Autowired
	// public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
	// 		@Qualifier("kafkaListenerContainerFactoryConfigurer") final ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
	// 		@Qualifier("kafkaConsumerFactory") final ConsumerFactory kafkaConsumerFactory) {
	//
	// 	final ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
	// 	configurer.configure(factory, kafkaConsumerFactory);
	// 	return factory;
	// }

}
