package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
//	@Autowired
	UserService userService = new UserServiceImpl();

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto userDto=userService.getUserByUserId(id);
		UserResponse userResponse=new UserResponse();
		userResponse.setUserId(userDto.getUserId());
		userResponse.setEmail(userDto.getEmail());
		userResponse.setFirstName(userDto.getFirstName());
		userResponse.setLastName(userDto.getLastName());

		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto=new UserDto();
        userDto.setEmail(userDetails.getEmail());
		userDto.setFirstName(userDetails.getFirstName());
		userDto.setLastName(userDetails.getLastName());
		userDto.setUserId(String.valueOf(UUID.randomUUID()));
		 userService.createUser(userDto);
		UserResponse userResponse=new UserResponse();
		userResponse.setUserId(userDto.getUserId());
		userResponse.setFirstName(userDto.getFirstName());
		userResponse.setLastName(userDto.getLastName());
		userResponse.setEmail(userDto.getEmail());

		return userResponse;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
       UserDto userDto=new UserDto();
	   userDto.setEmail(userDetails.getEmail());
	   userDto.setFirstName(userDetails.getFirstName());
	   userDto.setLastName(userDetails.getLastName());
	   userDto.setUserId(id);
	   userService.updateUser(id,userDto);
	   UserResponse userResponse=new UserResponse();
	   userResponse.setEmail(userDto.getEmail());
	   userResponse.setUserId(userDto.getUserId());
	   userResponse.setFirstName(userDto.getFirstName());
	   userResponse.setLastName(userDto.getLastName());

		return userResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
     userService.deleteUser(id);
	 OperationStatusModel operationStatusModel=new OperationStatusModel();
	 operationStatusModel.setOperationResult("success");
	 operationStatusModel.setOperationName("Deletion");
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
      List<UserDto> user=userService.getUsers();
	  List<UserResponse> res=new ArrayList<>();
	  for(UserDto userDto:user){
		  UserResponse userResponse=new UserResponse();
		  userResponse.setUserId(userDto.getUserId());
		  userResponse.setFirstName(userDto.getFirstName());
		  userResponse.setLastName(userDto.getLastName());
		  userResponse.setEmail(userDto.getEmail());
		  res.add(userResponse);
	  }
		return res;
	}
	
}
