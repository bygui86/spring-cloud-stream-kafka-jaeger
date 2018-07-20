package com.rabbit.springcloudstream.source.schedulers;

import com.rabbit.springcloudstream.source.services.SourcePublisherService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(value = AccessLevel.PROTECTED)
@Service("sourceSchedulerService")
public class SourceSchedulerService {

	@Resource(name = "sourcePublisherService")
	SourcePublisherService sourcePublisherService;

	int totalMsg = 1;

	@Scheduled(fixedRate = 3000, initialDelay = 5000)
	public void sendTimeMessage() {

		final Long timestamp = this.getSourcePublisherService().sendTimeMessage(totalMsg);
		log.info("Messager number {} - TimeStamp returned: {}", totalMsg, timestamp);
		totalMsg++;
	}

}
