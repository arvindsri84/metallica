package com.metaltraders.metallica.marketdataservice.changlogs;

import java.text.DecimalFormat;
import java.util.Date;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.metaltraders.metallica.marketdataservice.repository.MetalPrice;

@ChangeLog
public class DatabaseChangelog {

	private static DecimalFormat df2 = new DecimalFormat(".###");
	
	@ChangeSet(order = "001", id = "initialseeddata", author = "Arvind")
	public void initialiseSeedData(MongoTemplate mongoTemplate) {
		
		String[] codes = new String[]{"AL","CU","ZN","AU","AG"};
		String[] direction = new String[]{"U","D"};
		
		for(int i = 1; i <= 1000; i++ ){
			mongoTemplate.save(new MetalPrice(codes[randomInt() % 5],df2.format(randomDouble() * 1000), new Date(),direction[ randomInt() % 2],i));
		}
	}

	
	double randomDouble(){
		return Math.random();
	}

	int randomInt(){
		return (int)(Math.random() * 10);
	}

}