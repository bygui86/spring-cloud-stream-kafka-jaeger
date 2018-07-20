package com.rabbit.springcloudstream.processor.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Slf4j
@Service("processorTransformerService")
public class ProcessorTransformerService {

	int totalMsg = 1;

	@Transformer(
			inputChannel = Processor.INPUT,
			outputChannel = Processor.OUTPUT
	)
	public Object transform(final Long timestamp) {

		log.info("Transform message {}: {}", totalMsg, timestamp);
		totalMsg++;

		final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:yy");
		return dateFormat.format(timestamp);
	}

}
