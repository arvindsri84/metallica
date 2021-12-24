package com.metaltraders.metallica.refdataservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.metaltraders.metallica.refdataservice.collections.Counterparty;

public interface CounterpartyRepository extends MongoRepository<Counterparty, String>{

	List<Counterparty> findByCode(String code);
	
	@Query(value = "{'description': {$regex : ?0, $options: 'i'}}")
	List<Counterparty> findByDescriptionRegex(String regexString);

	
}
