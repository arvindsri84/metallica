package com.metaltraders.metallica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.mongobee.Mongobee;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@Configuration
@EnableScheduling
public class MetallicaApplication {

	private static final Logger LOG = LoggerFactory.getLogger(MetallicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MetallicaApplication.class, args);
		LOG.info("Metallica MarketData Service started successfuly !!");
	}
	
	@Bean
	public Mongobee mongobee(Environment env){
	  Mongobee runner = new Mongobee();
	  runner.setDbName(env.getProperty("spring.data.mongodb.database"));
	  runner.setChangeLogsScanPackage("com.metaltraders.metallica.marketdataservice.changlogs"); 
	  return runner;
	}	
	
	
	 @Bean
	 public Exchange eventExchange() {
	   return new TopicExchange("eventExchange");
	 }

}
