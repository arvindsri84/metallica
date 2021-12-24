package com.metaltraders.metallica.notificationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
public class MetallicaApplication {

	private static final Logger LOG = LoggerFactory.getLogger(MetallicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MetallicaApplication.class, args);
		LOG.info("Metallica MarketData Service started successfuly !!");

	}
}
