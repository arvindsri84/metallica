package com.metaltraders.metallica.refdataservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.metaltraders.metallica.refdataservice.collections.Location;

public interface LocationRespository extends MongoRepository<Location, String>{

	List<Location> findByCode(String code);
	
	@Query(value = "{'description': {$regex : ?0, $options: 'i'}}")
	List<Location> findByDescriptionRegex(String regexString);
	
	
	
}
