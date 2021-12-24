package com.metaltraders.metallica.tradeservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.metaltraders.metallica.tradeservice.entity.Trade;

public interface TradeRepository extends CrudRepository<Trade, Long>, QueryByExampleExecutor<Trade>{

	
	
}
