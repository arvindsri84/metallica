package com.metaltraders.metallica.tradeservice.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfig {
	
	 @Bean
	 public Exchange eventExchange() {
	   return new TopicExchange("eventExchange");
	 }

	 @Bean
	 public MessageConverter converter(){
		 return  new Jackson2JsonMessageConverter();
	 }
}
