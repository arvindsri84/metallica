package com.metaltraders.metallica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class MetallicaApplication {

	private static final Logger LOG = LoggerFactory.getLogger(MetallicaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MetallicaApplication.class, args);
		LOG.info("Metallica Zuul Proxy started successfuly !!");
	}
}
