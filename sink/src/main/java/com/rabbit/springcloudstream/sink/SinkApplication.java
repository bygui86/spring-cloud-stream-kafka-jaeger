package com.rabbit.springcloudstream.sink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rabbit.springcloudstream.sink"})
public class SinkApplication {

	public static void main(final String[] args) {

		SpringApplication.run(SinkApplication.class, args);
	}
}
