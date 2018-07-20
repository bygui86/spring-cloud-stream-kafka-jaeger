package com.rabbit.springcloudstream.source.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.Publisher;
import org.springframework.stereotype.Service;

import java.util.Date;


@Slf4j
@Service("sourcePublisherService")
public class SourcePublisherService {

	@Publisher(channel = Source.OUTPUT)
	public Long sendTimeMessage(final int messageNumber) {

		final long timestamp = new Date().getTime();
		log.info("Sending message number {}: {}", messageNumber, timestamp);
		return timestamp;
	}

}
