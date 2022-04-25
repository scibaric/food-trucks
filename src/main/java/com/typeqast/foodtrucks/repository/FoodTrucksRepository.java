package com.typeqast.foodtrucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.typeqast.foodtrucks.model.FoodTruck;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FoodTrucksRepository extends JpaRepository<FoodTruck, Long> {

    @Query(value = "SELECT * FROM FOOD_TRUCK"
            + " WHERE (ROUND(LATITUDE, 3) >= :latitude - 0.002 AND ROUND(LATITUDE, 3) <= :latitude + 0.002)"
            + " AND"
            + " (ROUND(LONGITUDE, 3) >= :longitude - 0.004 AND ROUND(LONGITUDE, 3) <= :longitude + 0.004)",
            nativeQuery = true)
    List<FoodTruck> findAllCloseToMe(
            @Param("latitude") BigDecimal latitude,
            @Param("longitude") BigDecimal longitude);
}
