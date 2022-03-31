package com.typeqast.foodtrucks.enums;

public enum Coordinates {
	LATITUDE(0.002),
	LONGITUDE(0.004);
	
	private double value;
	
	Coordinates(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
}
