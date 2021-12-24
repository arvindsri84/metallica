package com.metaltraders.metallica.refdataservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.metaltraders.metallica.refdataservice.collections.Commodity;

public interface CommodityRepository extends MongoRepository<Commodity, String>{

	List<Commodity> findByCode(String code);
	
	@Query(value = "{'description': {$regex : ?0, $options: 'i'}}")
	List<Commodity> findByDescriptionRegex(String regexString);
	
}
