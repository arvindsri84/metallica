package com.metaltraders.metallica.notificationservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class MarketDataNotificationshandler {

	private Logger logger = LoggerFactory.getLogger(MarketDataNotificationshandler.class);

	@Autowired
	private SimpMessagingTemplate template;

	
	@RabbitListener(queues = "marketDataQueue")
	public void receive(Message message) {
		String routingkey = message.getMessageProperties().getReceivedRoutingKey();
		String body = new String(message.getBody());
		
		logger.info("With routing key {} received message {} ", routingkey,body);
		publish(body);
	}
	
	public void publish(String message) {
		template.convertAndSend("/topic/market", message);
	}	

}
