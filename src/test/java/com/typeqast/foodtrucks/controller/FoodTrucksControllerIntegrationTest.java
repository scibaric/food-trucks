package com.typeqast.foodtrucks.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FoodTrucksControllerIntegrationTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void findAll_shouldReturnAllFoodTrucks() throws Exception {
		mockMvc.perform(get("/api/v1/food-trucks"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*").isNotEmpty());
		
	}
	
	@Test
	void findByLocationId_shouldReturnOneResult() throws Exception {
		mockMvc.perform(get("/api/v1/food-trucks/1569152"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.applicant").value("Datam SF LLC dba Anzu To You"))
			.andExpect(jsonPath("$.facilityType").value("Truck"));
		
	}
	
	@Test
	void findByLocationId_shouldReturnNoResult() throws Exception {
		mockMvc.perform(get("/api/v1/food-trucks/1"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.message").value("Food truck with id 1 not found"));
		
	}
	
	@Test
	void findAllCloseToMe_shouldReturnAllFoodTrucksCloseToMe() throws Exception {
		mockMvc.perform(get("/api/v1/food-trucks/close-to-me")
				.param("latitude", "37.792")
				.param("longitude", "-122.401"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*").isArray())
			.andExpect(jsonPath("$.*").isNotEmpty());
	}
	
	@Test
	void findAllCloseToMe_shouldReturnEmptyList() throws Exception {
		mockMvc.perform(get("/api/v1/food-trucks/close-to-me")
				.param("latitude", "40.785")
				.param("longitude", "-122.401"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*").isEmpty());
	}
	
	@ParameterizedTest
	@CsvSource({
		"-95, 122, Latitude must be between -90 and 90 inclusive",
		"96, 122, Latitude must be between -90 and 90 inclusive",
		"37, -182, Longitude must be between -180 and 180 inclusive",
		"37, 187, Longitude must be between -180 and 180 inclusive"
	})
	void findAllCloseToMe_shouldReturnErrorMessages(BigDecimal latitude, BigDecimal longitude, String message) throws Exception {
		mockMvc.perform(get("/api/v1/food-trucks/close-to-me")
				.param("latitude", latitude.toString())
				.param("longitude", longitude.toString()))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message").value(message));
	}
	
	@Test
	void findAllCloseToMe_whenLatitudeIsMissing_shouldReturnErrorMessage() throws Exception {
		mockMvc.perform(get("/api/v1/food-trucks/close-to-me")
				.param("latitude", "37.792"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	void findAllCloseToMe_whenLongitudeIsMissing_shouldReturnErrorMessage() throws Exception {
		mockMvc.perform(get("/api/v1/food-trucks/close-to-me")
				.param("longitude", "-122.401"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}
}
