package com.metaltraders.metallica.marketdataservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetalPriceRepository extends MongoRepository<MetalPrice, String> {

	MetalPrice findByDocumentIndex(int documentIndex);
	
}
