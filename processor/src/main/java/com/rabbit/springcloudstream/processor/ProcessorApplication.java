package com.rabbit.springcloudstream.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rabbit.springcloudstream.processor"})
public class ProcessorApplication {

	public static void main(final String[] args) {

		SpringApplication.run(ProcessorApplication.class, args);
	}
}
