package com.typeqast.foodtrucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.typeqast.foodtrucks.model.FoodTruck;

public interface FoodTrucksRepository extends JpaRepository<FoodTruck, Long> {
	
}
