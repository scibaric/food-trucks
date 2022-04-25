package com.typeqast.foodtrucks.util;

import java.math.BigDecimal;

public class PositionUtil {

	public static boolean positionIsValid(BigDecimal position, int pos) {
		BigDecimal abs = position.abs();
		return abs.compareTo(new BigDecimal(pos)) <= 0;
	}

}
