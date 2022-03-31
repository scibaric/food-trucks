package com.typeqast.foodtrucks.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.typeqast.foodtrucks.model.FoodTruck;
import com.typeqast.foodtrucks.service.FoodTrucksService;

@RestController
@RequestMapping("/api/v1/food-trucks")
public class FoodTrucksController {
	
	private FoodTrucksService service;
	
	public FoodTrucksController(FoodTrucksService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<FoodTruck>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{locationId}")
	public ResponseEntity<FoodTruck> findByLocationId(@PathVariable Long locationId) {
		return ResponseEntity.ok(service.findByLocationId(locationId));
	}
	
	@GetMapping("/close-to-me")
	public ResponseEntity<List<FoodTruck>> findAllCloseToMe(
			@RequestParam(value = "latitude", required = true) BigDecimal latitude,
			@RequestParam(value = "longtitude", required = true) BigDecimal longtitude) {
		return ResponseEntity.ok(service.findAllCloseToMe(latitude, longtitude));
	}
}
