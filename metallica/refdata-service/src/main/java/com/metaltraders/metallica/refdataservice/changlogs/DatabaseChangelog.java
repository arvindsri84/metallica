package com.metaltraders.metallica.refdataservice.changlogs;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.metaltraders.metallica.refdataservice.collections.Commodity;
import com.metaltraders.metallica.refdataservice.collections.Counterparty;
import com.metaltraders.metallica.refdataservice.collections.Location;

@ChangeLog
public class DatabaseChangelog {

	@ChangeSet(order = "001", id = "initialseeddata", author = "Arvind")
	public void initialiseSeedData(MongoTemplate mongoTemplate) {
		mongoTemplate.save(new Commodity("AL", "Aluminium"));
		mongoTemplate.save(new Commodity("ZN", "Zinc"));
		mongoTemplate.save(new Commodity("CU", "Copper"));
		mongoTemplate.save(new Commodity("AU", "Golde"));
		mongoTemplate.save(new Commodity("AG", "Silver"));

		mongoTemplate.save(new Location("LN", "London"));
		mongoTemplate.save(new Location("NY", "New York"));
		mongoTemplate.save(new Location("SG", "Singapore"));
		mongoTemplate.save(new Location("DN", "Denver"));
		mongoTemplate.save(new Location("DL", "Delhi"));

		mongoTemplate.save(new Counterparty("Lorem", "Lorem"));
		mongoTemplate.save(new Counterparty("Ipsum", "Ipsum"));
		mongoTemplate.save(new Counterparty("Dolor", "Dolor"));
		mongoTemplate.save(new Counterparty("Amet", "Amet"));
		
	
	}

}