package com.rabbit.springcloudstream.sink.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;


@Slf4j
@Service("sinkListenerService")
public class SinkListenerService {

	int totalMsg = 1;

	@StreamListener(Sink.INPUT)
	public void loggerSink(final String date) {

		log.info("Received message {}: {}", totalMsg, date);
		totalMsg++;
	}

}
