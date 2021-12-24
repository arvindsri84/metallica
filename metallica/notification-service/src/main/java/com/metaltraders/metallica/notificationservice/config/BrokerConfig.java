package com.metaltraders.metallica.notificationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.metaltraders.metallica.notificationservice.handler.MarketDataNotificationshandler;
import com.metaltraders.metallica.notificationservice.handler.TradeNotificationsHandler;

@Configuration
public class BrokerConfig {

	@Bean
	public Exchange eventExchange() {
		return new TopicExchange("eventExchange");
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean("tradeQueue")
	public Queue tradeQueue() {
		return new Queue("tradeServiceQueue");
	}

	@Bean("marketDataQueue")
	public Queue marketDataQueue() {
		return new Queue("marketDataQueue");
	}
	
	@Bean("tradeBinding")
	public Binding tradeSubscriberBinding(@Qualifier("tradeQueue") Queue queue, Exchange eventExchange) {
		return BindingBuilder.bind(queue).to(eventExchange).with("trade.*")
				.noargs();
	}
	
	@Bean("marketDataBinding")
	public Binding marketDataSubscriberBinding(@Qualifier("marketDataQueue") Queue queue, Exchange eventExchange) {
		return BindingBuilder.bind(queue).to(eventExchange).with("marketdata.*")
				.noargs();
	}

	@Bean
	public TradeNotificationsHandler tradeEventHandler() {
		return new TradeNotificationsHandler();
	}
	
	@Bean
	public MarketDataNotificationshandler marketDataEventhandler() {
		return new MarketDataNotificationshandler();
	}
	
}
