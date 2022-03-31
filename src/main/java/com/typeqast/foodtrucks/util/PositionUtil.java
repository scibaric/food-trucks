package com.typeqast.foodtrucks.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.typeqast.foodtrucks.enums.Coordinates;

public class PositionUtil {
	

	public static boolean positionIsValid(BigDecimal position, int pos) {
		BigDecimal abs = position.abs();
		return abs.compareTo(new BigDecimal(pos)) <= 0;
	}
	
	public static boolean isPositionCloseToMe(BigDecimal position, String foodTruckPosition, Coordinates coordinates) {
		BigDecimal ftPos = new BigDecimal(foodTruckPosition).abs().setScale(3, RoundingMode.HALF_UP);
		BigDecimal buffer = new BigDecimal(coordinates.getValue()).setScale(3, RoundingMode.HALF_UP);
		BigDecimal upperLimit = position.abs().add(buffer).setScale(3, RoundingMode.HALF_UP);
		BigDecimal lowerLimit = position.abs().subtract(buffer).setScale(3, RoundingMode.HALF_UP);
		
		return upperLimit.compareTo(ftPos) >= 0 && lowerLimit.compareTo(ftPos) <= 0;
	} 

}
