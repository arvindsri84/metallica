package com.metaltraders.metallica.marketdataservice.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaltraders.metallica.marketdataservice.repository.MetalPrice;
import com.metaltraders.metallica.marketdataservice.repository.MetalPriceRepository;

@Component
public class PublishPrices {

	private Logger logger = LoggerFactory.getLogger(PublishPrices.class);

	@Autowired
	private MetalPriceRepository repository;
	
	
	@Autowired
	private RabbitTemplate brokerTemplate;
	
	@Autowired
	private Exchange exchange;

	private static final ObjectMapper mapper = new ObjectMapper();

	@Scheduled(fixedRate = 10000)
	public void publishLatestPrices() {
		// Just randomly pick up from repository and publish
		int index = (int)( Math.random() * 1000 );
		MetalPrice metalPrices = repository.findByDocumentIndex(index);

		// publish the event to broker
		try {
			brokerTemplate.convertAndSend(exchange.getName(),"marketdata.metal", mapper.writeValueAsString(metalPrices));
		} catch (AmqpException | JsonProcessingException e) {
			logger.error("Error occured while publishing the message", e);
		}
	}	

}
