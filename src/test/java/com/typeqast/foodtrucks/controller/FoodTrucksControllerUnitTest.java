package com.typeqast.foodtrucks.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.typeqast.foodtrucks.exception.FoodTruckNotFoundException;
import com.typeqast.foodtrucks.model.FoodTruck;
import com.typeqast.foodtrucks.service.FoodTrucksService;

@ExtendWith(SpringExtension.class)
public class FoodTrucksControllerUnitTest {
	
	private FoodTrucksController controller;
	
	@MockBean
	private FoodTrucksService service;
	
	@BeforeEach
	void setUp() {
		controller = new FoodTrucksController(service);
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
		when(service.findAll()).thenReturn(list);
		ResponseEntity<List<FoodTruck>> response = controller.findAll();
		List<FoodTruck> newList = response.getBody();
		
		
		//then
		assertThat(response).isNotNull();
		assertThat(response.hasBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(newList).isNotEmpty();
		assertThat(newList.size()).isEqualTo(3);
		verify(service).findAll();
	}
	
	@Test
	void findAll_shouldReturnEmptyList() {
		// given
		List<FoodTruck> list = new ArrayList<>();
		
		// when
		when(service.findAll()).thenReturn(list);
		ResponseEntity<List<FoodTruck>> response = controller.findAll();
		List<FoodTruck> newList = response.getBody();
		
		
		//then
		assertThat(response.hasBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(newList).isEmpty();
		verify(service).findAll();
	}
	
	@Test
	void findByLocationId_shouldReturnOneResult() {
		// given
		Long locationId = 1L;
		FoodTruck foodTruck = new FoodTruck(1L, 
				"San Pancho's Tacos", 
				"Truck", 
				"APPROVED", 
				"Tacos: Burritos: Quesadillas: Tortas: Nachos: Hot Dogs:Soda: Water: Fruit Drinks");
		
		// when
		when(service.findByLocationId(locationId)).thenReturn(foodTruck);
		ResponseEntity<FoodTruck> response = controller.findByLocationId(locationId);
		FoodTruck ft = response.getBody();
		
		//then
		assertThat(response).isNotNull();
		assertThat(response.hasBody());
		assertThat(response.getStatusCode())
			.isEqualTo(HttpStatus.OK);
		assertThat(ft)
			.isNotNull();
		assertThat(ft.getLocationId())
			.isEqualTo(locationId);
		assertThat(ft.getFacilityType())
			.isEqualTo("Truck");
		verify(service).findByLocationId(locationId);
	}

	@ParameterizedTest
	@MethodSource("provideValuesMessagesAndExceptions")
	<T extends RuntimeException> void findByLocationId_shouldThrowException(Long locationId, String message, T t) {
		// when
		when(service.findByLocationId(locationId)).thenThrow(t);
		
		//then
		assertThatThrownBy(() -> controller.findByLocationId(locationId))
			.isInstanceOf(t.getClass())
			.hasMessage(message);
		
		verify(service).findByLocationId(locationId);
	}
	
	private static Stream<Arguments> provideValuesMessagesAndExceptions() {
		return Stream.of(
				Arguments.of(null, "LocationId must not be null", new IllegalArgumentException("LocationId must not be null")),
				Arguments.of(1L, "Food truck with id 1 not found", new FoodTruckNotFoundException("Food truck with id 1 not found")));
	}
	
	@Test
	void findAllCloseToMe_ShouldReturnAllFoodTrucksCloseToMe() {
		// given
		BigDecimal latitude = new BigDecimal(37.785);
		BigDecimal longtitude = new BigDecimal(-122.435);
		List<FoodTruck> list = new ArrayList<FoodTruck>();
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
		
		//when
		when(service.findAllCloseToMe(latitude, longtitude)).thenReturn(list);
		ResponseEntity<List<FoodTruck>> response = controller.findAllCloseToMe(latitude, longtitude);
		List<FoodTruck> foodTrucks = response.getBody();
		
		// then
		assertThat(response).isNotNull();
		assertThat(response.hasBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(foodTrucks).isNotNull();
		assertThat(foodTrucks).isNotEmpty();
		assertThat(foodTrucks.size()).isEqualTo(3);
		verify(service).findAllCloseToMe(latitude, longtitude);
	}
	
	@Test
	void findAllCloseToMe_ShouldReturnEmptyList() {
		// given
		BigDecimal latitude = new BigDecimal(40.785);
		BigDecimal longtitude = new BigDecimal(-122.435);
		List<FoodTruck> list = new ArrayList<FoodTruck>();
		
		//when
		when(service.findAllCloseToMe(latitude, longtitude)).thenReturn(list);
		ResponseEntity<List<FoodTruck>> response = controller.findAllCloseToMe(latitude, longtitude);
		List<FoodTruck> foodTrucks = response.getBody();
		
		// then
		assertThat(response).isNotNull();
		assertThat(response.hasBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(foodTrucks).isEmpty();
		verify(service).findAllCloseToMe(latitude, longtitude);
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		"null, 122, Latitude must not be null",
		"-95, 122, Latitude must be between -90 and 90 inclusive",
		"96, 122, Latitude must be between -90 and 90 inclusive",
		"37, null, Longtitude must not be null",
		"37, -182, Longtitude must be between -180 and 180 inclusive",
		"37, 187, Longtitude must be between -180 and 180 inclusive"
	}, nullValues = "null")
	void findAllCloseToMe_shouldThrowException(BigDecimal latitude, BigDecimal longtitude, String message) {
		// when
		doThrow(new IllegalArgumentException(message)).when(service).findAllCloseToMe(latitude, longtitude);
		
		// then
		assertThatThrownBy(() -> controller.findAllCloseToMe(latitude, longtitude))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(message);
		
		verify(service).findAllCloseToMe(latitude, longtitude);
	}
}
