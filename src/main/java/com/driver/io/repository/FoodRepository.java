package com.driver.io.repository;

import com.driver.shared.dto.FoodDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.driver.io.entity.FoodEntity;

@Repository
public interface FoodRepository extends CrudRepository<FoodEntity, Long> {
	FoodEntity findByFoodId(String foodId);


}
