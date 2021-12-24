package com.metaltraders.metallica.tradeservice.entity;

public enum TradeStatus {
	
	OPEN,
	
	NOMINATED;
	
	
	public static TradeStatus get(String status) {

		if (NOMINATED.name().equalsIgnoreCase(status)) {
			return NOMINATED;
		} else if (NOMINATED.name().equalsIgnoreCase(status)) {
			return NOMINATED;
		} else {
			return null;
		}
	}
}
