package com.rabbit.springcloudstream.source.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration("sourceStreamConfig")
@EnableBinding(Source.class)
public class SourceStreamConfig {

	// no-op
}
