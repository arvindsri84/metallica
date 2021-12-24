package com.metaltraders.metallica.marketdataservice.repository;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "metalprice")
public class MetalPrice {
	
	@Id
	@JsonIgnore
	private String id;
	
	private String code;
	
	private String price;
	
	@JsonIgnore
	private Date date;
	
	private String priceMovDirection;
	
	@JsonIgnore
	private int documentIndex;

	
	public MetalPrice(){
		
	}
	
	
	
	public MetalPrice(String code, String price, Date date,
			String priceMovDirection, int documentIndex) {
		super();
		this.code = code;
		this.price = price;
		this.date = date;
		this.priceMovDirection = priceMovDirection;
		this.documentIndex = documentIndex;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPriceMovDirection() {
		return priceMovDirection;
	}

	public void setPriceMovDirection(String priceMovDirection) {
		this.priceMovDirection = priceMovDirection;
	}

	public int getDocumentIndex() {
		return documentIndex;
	}

	public void setDocumentIndex(int documentIndex) {
		this.documentIndex = documentIndex;
	}
	
	
	
	
	
	
}
