package com.typeqast.foodtrucks.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.typeqast.foodtrucks.exception.FoodTruckNotFoundException;
import com.typeqast.foodtrucks.model.FoodTruck;
import com.typeqast.foodtrucks.repository.FoodTrucksRepository;

@ExtendWith(SpringExtension.class)
public class FoodTrucksServiceUnitTest {
	
	private FoodTrucksService service;
	
	@MockBean
	private FoodTrucksRepository repository;
	
	@BeforeEach
	void setUp() {
		service = new FoodTrucksServiceImpl(repository);
	}

	@Test
	void findAll_shouldReturnAllFoodTrucks() {
		// given
		List<FoodTruck> list = new ArrayList<>();
		list.add(new FoodTruck(1L, 
				"San Pancho's Tacos", 
				"Truck", 
				"APPROVED", 
				"Tacos: Burritos: Quesadillas: Tortas: Nachos: Hot Dogs:Soda: Water: Fruit Drinks"));
		list.add(new FoodTruck(2L, 
				"Cochinita", 
				"Truck", 
				"APPROVED", 
				"Mexican Food: Yucatan Food: Street Food"));
		list.add(new FoodTruck(3L, 
				"Scotch Bonnet", 
				"Truck", 
				"EXPIRED", 
				"Jerk chicken: curry chicken: curry goat: curry dhal: Burritos: "
				+ "Fish: Ox tails: rice: beans: veggies."));
		
		// when
		when(repository.findAll()).thenReturn(list);
		List<FoodTruck> foodTrucks = service.findAll();
		
		// then
		assertThat(foodTrucks).isNotEmpty();
		assertThat(foodTrucks.size()).isEqualTo(3);
		verify(repository).findAll();
	}
	
	@Test
	void findAll_shouldReturnEmptyList() {
		// given
		List<FoodTruck> list = new ArrayList<>();
		
		// when
		when(repository.findAll()).thenReturn(list);
		List<FoodTruck> foodTrucks = service.findAll();
		
		// then
		assertThat(foodTrucks).isEmpty();
		verify(repository).findAll();
	}
	
	@Test
	void findByLocationId_shouldOneResult() {
		// given
		Long locationId = 1L;
		FoodTruck foodTruck = new FoodTruck(1L, 
				"San Pancho's Tacos", 
				"Truck", 
				"APPROVED", 
				"Tacos: Burritos: Quesadillas: Tortas: Nachos: Hot Dogs:Soda: Water: Fruit Drinks");
		
		// when
		when(repository.findById(locationId)).thenReturn(Optional.of(foodTruck));
		FoodTruck ft = service.findByLocationId(locationId);
		
		//then
		assertThat(ft)
			.isNotNull();
		assertThat(ft.getLocationId())
			.isEqualTo(locationId);
		assertThat(ft.getFacilityType())
			.isEqualTo("Truck");
		verify(repository).findById(locationId);
	}
	
	@ParameterizedTest
	@MethodSource("provideValuesMessagesAndExceptions")
	<T extends RuntimeException> void findByLocationId_shouldThrowException(Long locationId, String message, T t) {		
		Exception e = assertThrows(t.getClass(), () -> service.findByLocationId(locationId));
		assertEquals(message, e.getMessage());
	}
	
	private static Stream<Arguments> provideValuesMessagesAndExceptions() {
		return Stream.of(
				Arguments.of(null, "LocationId must not be null", new IllegalArgumentException("LocationId must not be null")),
				Arguments.of(1L, "Food truck with id 1 not found", new FoodTruckNotFoundException("Food truck with id 1 not found")));
	}
	
	@Test
	void findAllCloseToMe_shouldReturnFoodTrucksCloseToMe() {
		//given
		BigDecimal latitude = new BigDecimal(37.785);
		BigDecimal longitude = new BigDecimal(-122.435);
		List<FoodTruck> list = new ArrayList<>();
		list.add(new FoodTruck(1L, 
				"San Pancho's Tacos", 
				"Truck", 
				"APPROVED", 
				"Tacos: Burritos: Quesadillas: Tortas: Nachos: Hot Dogs:Soda: Water: Fruit Drinks",
				"37.786",
				"-122.434"));
		list.add(new FoodTruck(2L, 
				"Cochinita", 
				"Truck", 
				"APPROVED", 
				"Mexican Food: Yucatan Food: Street Food",
				"37.787",
				"-122.438"));
		list.add(new FoodTruck(3L, 
				"Scotch Bonnet", 
				"Truck", 
				"EXPIRED", 
				"Jerk chicken: curry chicken: curry goat: curry dhal: Burritos: "
				+ "Fish: Ox tails: rice: beans: veggies.",
				"37.784",
				"-122.431"));
		
		// when
		when(repository.findAllCloseToMe(latitude, longitude)).thenReturn(list);
		List<FoodTruck> foodTrucks = service.findAllCloseToMe(latitude, longitude);
		
		// then
		assertThat(foodTrucks).isNotEmpty();
		assertThat(foodTrucks.size()).isEqualTo(3);
		verify(repository).findAllCloseToMe(latitude, longitude);
	}
	
	@Test
	void findAllCloseToMe_shouldReturnEmptyList() {
		//given
		BigDecimal latitude = new BigDecimal(40.785);
		BigDecimal longitude = new BigDecimal(-122.435);
		List<FoodTruck> list = new ArrayList<>();
		
		// when
		when(repository.findAllCloseToMe(latitude, longitude)).thenReturn(list);
		List<FoodTruck> foodTrucks = service.findAllCloseToMe(latitude, longitude);
		
		// then
		assertThat(foodTrucks).isEmpty();
		verify(repository).findAllCloseToMe(latitude, longitude);
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		"null, 122, Latitude must not be null",
		"-95, 122, Latitude must be between -90 and 90 inclusive",
		"96, 122, Latitude must be between -90 and 90 inclusive",
		"37, null, Longitude must not be null",
		"37, -182, Longitude must be between -180 and 180 inclusive",
		"37, 187, Longitude must be between -180 and 180 inclusive"
	}, nullValues = "null")
	void findAllCloseToMe_shouldThrowException(BigDecimal latitude, BigDecimal longitude, String message) {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> service.findAllCloseToMe(latitude, longitude));
		
		assertEquals(message, e.getMessage());
	}
}
