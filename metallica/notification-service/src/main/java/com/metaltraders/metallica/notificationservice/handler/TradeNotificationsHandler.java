package com.metaltraders.metallica.notificationservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class TradeNotificationsHandler {

	private Logger logger = LoggerFactory.getLogger(TradeNotificationsHandler.class);

	@Autowired
	private SimpMessagingTemplate template;
	
	@RabbitListener(queues = "tradeServiceQueue")
	public void receive(Message message) {
		
		String routingkey = message.getMessageProperties().getReceivedRoutingKey();
		String body = new String(message.getBody());
		
		logger.info("With routing key {} received message {} ", routingkey,body);
		
		String notificationMessage = "";
		if(routingkey.equalsIgnoreCase("trade.created")){
			notificationMessage = "New trade ( trade id :" + body + ") created";
		}else if(routingkey.equalsIgnoreCase("trade.updated")){
			notificationMessage = "Trade ( trade id :" + body + ") has been updated";
		}else if(routingkey.equalsIgnoreCase("trade.deleted")){
			notificationMessage = "Trade ( trade id :" + body + ") has been deleted";
		}
		
		publish(notificationMessage);
	}
	
	
	public void publish(String message) {
		template.convertAndSend("/topic/trade", message);
	}	
	
	

}
