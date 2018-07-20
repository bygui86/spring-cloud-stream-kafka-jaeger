package com.rabbit.springcloudstream.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rabbit.springcloudstream.source"})
public class SourceApplication {

	public static void main(final String[] args) {

		SpringApplication.run(SourceApplication.class, args);
	}

}
