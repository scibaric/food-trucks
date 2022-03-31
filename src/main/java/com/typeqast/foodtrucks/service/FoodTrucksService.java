package com.typeqast.foodtrucks.service;

import java.math.BigDecimal;
import java.util.List;

import com.typeqast.foodtrucks.model.FoodTruck;


public interface FoodTrucksService {
	
	FoodTruck findByLocationId(Long id);
	
	List<FoodTruck> findAll();
	
	List<FoodTruck> findAllCloseToMe(BigDecimal latitude, BigDecimal longitude);

}
