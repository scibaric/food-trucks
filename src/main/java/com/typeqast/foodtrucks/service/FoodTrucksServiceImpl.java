package com.typeqast.foodtrucks.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.typeqast.foodtrucks.enums.Coordinates;
import com.typeqast.foodtrucks.exception.FoodTruckNotFoundException;
import com.typeqast.foodtrucks.model.FoodTruck;
import com.typeqast.foodtrucks.repository.FoodTrucksRepository;
import com.typeqast.foodtrucks.util.PositionUtil;

@Service
public class FoodTrucksServiceImpl implements FoodTrucksService {

	private final FoodTrucksRepository repository;
	
	public FoodTrucksServiceImpl(FoodTrucksRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<FoodTruck> findAll() {
		return repository.findAll();
	}

	@Override
	public FoodTruck findByLocationId(Long id) {
		Assert.notNull(id, "LocationId must not be null");
		return repository.findById(id).orElseThrow(
				() -> new FoodTruckNotFoundException(String.format("Food truck with id %s not found", id)));
	}

	@Override
	public List<FoodTruck> findAllCloseToMe(BigDecimal latitude, BigDecimal longitude) {
		Assert.notNull(latitude, "Latitude must not be null");
		Assert.isTrue(PositionUtil.positionIsValid(latitude, 90), "Latitude must be between -90 and 90 inclusive");
		Assert.notNull(longitude, "Longitude must not be null");
		Assert.isTrue(PositionUtil.positionIsValid(longitude, 180), "Longitude must be between -180 and 180 inclusive");
		
		return repository.findAll().stream()
				.filter(foodTruck -> PositionUtil.isPositionCloseToMe(latitude, foodTruck.getLatitude(), Coordinates.LATITUDE))
				.filter(foodTruck -> PositionUtil.isPositionCloseToMe(longitude, foodTruck.getLongitude(), Coordinates.LONGITUDE))
				.toList();
	}

}
