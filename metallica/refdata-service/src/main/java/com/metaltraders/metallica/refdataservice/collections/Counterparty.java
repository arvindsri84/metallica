package com.metaltraders.metallica.refdataservice.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counterparty")
public class Counterparty {

	@Id
	private String id;

	private String code;

	private String description;

	
	public Counterparty() {}
	
	
	public Counterparty(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
