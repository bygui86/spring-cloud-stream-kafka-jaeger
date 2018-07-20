package com.rabbit.springcloudstream.sink.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration("sinkStreamConfig")
@EnableBinding(Sink.class)
public class SinkStreamConfig {

	// no-op
}
