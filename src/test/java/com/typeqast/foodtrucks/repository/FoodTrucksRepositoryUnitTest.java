package com.typeqast.foodtrucks.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.typeqast.foodtrucks.model.FoodTruck;

@DataJpaTest
public class FoodTrucksRepositoryUnitTest {
	
	@Autowired
	private FoodTrucksRepository repository;
	
	@Test
	void findAll_shouldReturnAllFoodTrucks() {
		List<FoodTruck> foodTrucks = repository.findAll();
		
		assertThat(foodTrucks).isNotEmpty();
	}

	@Test
	void findByLocationId_shouldReturnFoodTruck() {
		// given
		FoodTruck foodTrucks = repository.findById(1569145L).get();
		
		// then
		assertThat(foodTrucks).isNotNull();
		assertThat(foodTrucks.getLocationId()).isEqualTo(1569145L);
		assertThat(foodTrucks.getApplicant()).isEqualTo("Casita Vegana");
	}
	
	@Test
	void findByLocationId_shouldNotReturnFoodTruck() {
		// given
		FoodTruck foodTrucks = repository.findById(1L).orElse(null);
		
		// then
		assertThat(foodTrucks).isNull();
	}
}
