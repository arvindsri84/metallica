package com.metaltraders.metallica.tradeservice.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.metaltraders.metallica.tradeservice.entity.Trade;
import com.metaltraders.metallica.tradeservice.entity.TradeStatus;
import com.metaltraders.metallica.tradeservice.repository.TradeRepository;

@Service
public class TradeService {

	@Autowired
	private TradeRepository respository;

	@Autowired
	private RabbitTemplate brokerTemplate;
	
	@Autowired
	private Exchange exchange;

	public List<Trade> findMatchingTrades(final Trade trade,final Date startDate,final Date endDate, final Principal principle) {
		
		Example<Trade> example = Example.of(trade);
		Iterable<Trade> fetchedTrades = respository.findAll(example);
		
		List<Trade> trades= ((List<Trade>)fetchedTrades).stream().filter( t-> {
			
			if( startDate == null && endDate == null){
				return true;
			}else if(endDate != null){
				return t.getTradeDate().compareTo(endDate) <= 0;
			}else if(startDate != null){
				return t.getTradeDate().compareTo(startDate) >= 0;
			}else{
				return t.getTradeDate().compareTo(endDate) <= 0 && t.getTradeDate().compareTo(startDate) >= 0;
			}
		}).collect(Collectors.toList());
		
		return trades;
	}
	
	
	public Trade createTrade(Trade trade, Principal principle) {
		validateTradeDetails(trade);

		trade.setStatus(TradeStatus.OPEN);
		trade = respository.save(trade);

		// publish the event to broker
		brokerTemplate.convertAndSend(exchange.getName(),"trade.created", trade.getTradeId());
		return trade;
	}

	public Trade updateTrade(long tradeId,Trade trade, Principal principle) {
		validateTradeDetails(trade);
		
		Trade tradeEntity = respository.findOne(tradeId);
		if(tradeEntity == null){
			throw new ValidationException("No trade found with for trade id : " + tradeId);
		}
		trade.setTradeId(tradeId);
		copyModifiedFields(tradeEntity,trade);
		trade = respository.save(tradeEntity);

		// publish the event to broker
		brokerTemplate.convertAndSend(exchange.getName(),"trade.updated", tradeId);
		return trade;
	}
	
	private void copyModifiedFields(Trade entity, Trade trade) {
		if(trade.getTradeDate() != null){
			entity.setTradeDate(trade.getTradeDate());
		}
		
		if(isNotBlank(trade.getComodity())){
			entity.setComodity(trade.getComodity());
		}
		
		if(isNotBlank(trade.getCounterparty())){
			entity.setCounterparty(trade.getCounterparty());
		}

		if(isNotBlank(trade.getLocation())){
			entity.setLocation(trade.getLocation());
		}
		
		if(trade.getPrice() != null){
			entity.setPrice(trade.getPrice());
		}
		
		if(trade.getQuatity() != null){
			entity.setLocation(trade.getLocation());
		}
	}


	private boolean isNotBlank(String str) {
		return str != null && str.trim().length() > 0;
	}


	public void deleteTrade(long tradeId, Principal principle) {

		if (respository.exists(tradeId)) {
			respository.delete(tradeId);
			// publish the event to broker
			brokerTemplate.convertAndSend(exchange.getName(), "trade.deleted",tradeId);
		} else {
			throw new ValidationException("No trade found with for trade id : " + tradeId);

		}
	}
	
	private void validateTradeDetails(Trade trade) throws ValidationException{
		
	}

}
