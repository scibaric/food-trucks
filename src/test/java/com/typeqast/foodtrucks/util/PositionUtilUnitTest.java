package com.typeqast.foodtrucks.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
}
