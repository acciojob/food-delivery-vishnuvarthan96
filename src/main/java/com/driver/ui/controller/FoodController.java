package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.driver.io.repository.FoodRepository;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.service.FoodService;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {
//	@Autowired
	FoodService foodService = new FoodServiceImpl();
	FoodRepository foodRepository;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
                 FoodDto foodDto=foodService.getFoodById(id);
				 FoodDetailsResponse foodDetailsResponse=new FoodDetailsResponse();
				 foodDetailsResponse.setFoodId(foodDto.getFoodId());
				 foodDetailsResponse.setFoodName(foodDto.getFoodName());
				 foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
				 foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());

		return foodDetailsResponse;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto=new FoodDto();
		foodDto.setFoodName(foodDetails.getFoodName());
		foodDto.setFoodCategory(foodDetails.getFoodCategory());
		foodDto.setFoodPrice(foodDetails.getFoodPrice());
		foodDto.setFoodId(String.valueOf(UUID.randomUUID()));
		 FoodDto foodDto1=foodService.createFood(foodDto);

		return null;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto=new FoodDto();
		foodDto.setFoodName(foodDetails.getFoodName());
		foodDto.setFoodCategory(foodDetails.getFoodCategory());
		foodDto.setFoodPrice(foodDetails.getFoodPrice());

    foodService.updateFoodDetails(id,foodDto);
	FoodDetailsResponse foodDetailsResponse=new FoodDetailsResponse();
	foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
	foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
	foodDetailsResponse.setFoodName(foodDto.getFoodName());
	foodDetailsResponse.setFoodId(id);
		return foodDetailsResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
     foodService.deleteFoodItem(id);
   OperationStatusModel operationStatusModel=new OperationStatusModel();
   operationStatusModel.setOperationName("delete");
   operationStatusModel.setOperationResult("success");
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
List<FoodDto> foodDto=foodService.getFoods();
List<FoodDetailsResponse> foodDetailsResponses=new ArrayList<>();
for(FoodDto foodDto1:foodDto){
	FoodDetailsResponse foodDetailsResponse=new FoodDetailsResponse();
	foodDetailsResponse.setFoodName(foodDto1.getFoodName());
	foodDetailsResponse.setFoodCategory(foodDto1.getFoodCategory());
	foodDetailsResponse.setFoodPrice(foodDto1.getFoodPrice());
	foodDetailsResponse.setFoodId(foodDto1.getFoodId());
	foodDetailsResponses.add(foodDetailsResponse);
}
		return foodDetailsResponses;
	}
}
