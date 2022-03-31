package com.typeqast.foodtrucks.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.typeqast.foodtrucks.enums.Coordinates;

public class PositionUtilUnitTest {
	
	@ParameterizedTest
	@CsvSource({
		"80.04, 90, true",
		"-80.04, 90, true",
		"105.507, 90, false",
		"-97.456, 90, false",
		"150.404, 180, true",
		"-80.123, 180, true",
		"200.543, 180, false",
		"-185.967, 180, false"
	})
	void positionIsValidTest(BigDecimal position, int pos, boolean flag) {
		assertThat(PositionUtil.positionIsValid(position, pos)).isEqualTo(flag);
	}
	
	@ParameterizedTest
	@CsvSource({
		"-80.043, -80.041, LATITUDE, true",
		"-80.043, -80.045, LATITUDE, true",
		"-80.043, -80.040, LATITUDE, false",
		"-80.043, -80.046, LATITUDE, false",
		"120.508, 120.512, LONGTITUDE, true",
		"120.508, 120.504, LONGTITUDE, true",
		"120.508, 120.513, LONGTITUDE, false",
		"120.508, 120.503, LONGTITUDE, false",
	})
	void isPositionCloseToMeTest(BigDecimal position, String foodTruckPosition, 
			Coordinates coordinates, boolean flag) {
		
		assertThat(PositionUtil.isPositionCloseToMe(position, foodTruckPosition, coordinates))
			.isEqualTo(flag);
	}
}
