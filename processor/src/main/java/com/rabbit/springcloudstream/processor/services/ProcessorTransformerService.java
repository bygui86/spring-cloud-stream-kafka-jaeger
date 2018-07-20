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

		final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:yy");
		final String stringTimestamp = dateFormat.format(timestamp);
		log.info("Transform message {} from {} to {}", totalMsg, timestamp, stringTimestamp);
		totalMsg++;
		return stringTimestamp;
	}

}
