package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import com.zaxxer.hikari.util.FastList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) {
       FoodEntity f=new FoodEntity();
       f.setFoodId(food.getFoodId());
       f.setFoodName(food.getFoodName());
       f.setFoodCategory(food.getFoodCategory());
       f.setFoodPrice(food.getFoodPrice());
        foodRepository.save(f);
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity food=foodRepository.findByFoodId(foodId);
        FoodDto foodDto=new FoodDto();
        foodDto.setFoodName(food.getFoodName());
        foodDto.setFoodId(food.getFoodId());
        foodDto.setFoodCategory(food.getFoodCategory());
        foodDto.setId(food.getId());
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity food=foodRepository.findByFoodId(foodId);
        food.setFoodName(foodDetails.getFoodName());
        food.setFoodPrice(foodDetails.getFoodPrice());
        food.setFoodCategory(foodDetails.getFoodCategory());
        foodRepository.save(food);

        return foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
    FoodEntity food=foodRepository.findByFoodId(id);
    foodRepository.delete(food);
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodDto> foodDtos=new ArrayList<>();
        for(FoodEntity food:foodRepository.findAll()){
            FoodDto foodDto=new FoodDto();
            foodDto.setFoodPrice(food.getFoodPrice());
            foodDto.setFoodCategory(food.getFoodCategory());
            foodDto.setFoodName(food.getFoodName());
            foodDto.setId(food.getId());
            foodDto.setFoodId(food.getFoodId());
            foodDtos.add(foodDto);
        }
        return foodDtos;
    }
}