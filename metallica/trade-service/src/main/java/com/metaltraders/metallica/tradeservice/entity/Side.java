package com.metaltraders.metallica.tradeservice.entity;


public enum Side {

	BUY,

	SELL;

	public static Side get(String side) {

		if (BUY.name().equalsIgnoreCase(side)) {
			return BUY;
		} else if (SELL.name().equalsIgnoreCase(side)) {
			return SELL;
		} else {
			return null;
		}
	}
	
}
