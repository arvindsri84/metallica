package com.metaltraders.metallica.tradeservice.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metaltraders.metallica.tradeservice.entity.Side;
import com.metaltraders.metallica.tradeservice.entity.Trade;
import com.metaltraders.metallica.tradeservice.service.TradeService;

@RestController
@RequestMapping("/api/trade")
public class TradeResource {

	@Autowired
	private TradeService service;
	
	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Trade>> getTrades(
			@RequestParam(value = "toDate",required = false) @DateTimeFormat(pattern= Trade.DATE_FORMAT) Date toDate,
			@RequestParam(value = "fromDate",required = false) @DateTimeFormat(pattern= Trade.DATE_FORMAT ) Date fromDate,
			@RequestParam(value = "comodity",required = false) String comodity,
			@RequestParam(value = "side",required = false) String side,
			@RequestParam(value = "counterparty",required = false) String counterparty,
			@RequestParam(value = "location",required = false) String location) {

		Trade trade = new Trade();
		trade.setComodity(comodity);
		trade.setSide(Side.get(side));
		trade.setCounterparty(counterparty);
		trade.setLocation(location);
		
		List<Trade> trades = service.findMatchingTrades(trade, toDate, fromDate, null);
		return new ResponseEntity<List<Trade>>(trades,HttpStatus.OK);
	}
	
	
	@RequestMapping(path = "", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Trade> create(@RequestBody Trade trade) {
		Trade createdTrade = service.createTrade(trade, null);
		return new ResponseEntity<Trade>(createdTrade,HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/{tradeId}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Trade> update(@PathVariable(value = "tradeId") long tradeId,@RequestBody Trade trade) {
		service.updateTrade(tradeId, trade, null);
		return new ResponseEntity<Trade>(trade,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{tradeId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(value = "tradeId") long tradeId) {
		service.deleteTrade(tradeId, null);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
