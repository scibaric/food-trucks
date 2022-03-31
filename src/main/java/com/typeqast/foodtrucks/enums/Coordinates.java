package com.typeqast.foodtrucks.enums;

public enum Coordinates {
	LATITUDE(0.002),
	LONGTITUDE(0.004);
	
	private double value;
	
	private Coordinates(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
}
