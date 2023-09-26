package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity user1=new UserEntity();
        user1.setEmail(user.getEmail());
        user1.setUserId(user.getUserId());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        userRepository.save(user1);
        user.setId(user1.getId());
        return user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity user=userRepository.findByEmail(email);
        UserDto dto=new UserDto();
        dto.setUserId(user.getUserId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
       return dto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity user=userRepository.findByUserId(userId);
        UserDto userDto=new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        return userDto;

    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity user1=userRepository.findByUserId(userId);
        user1.setUserId(user.getUserId());
        user1.setEmail(user.getEmail());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        userRepository.save(user1);
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
       UserEntity user=userRepository.findByUserId(userId);
       userRepository.delete(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> users=new ArrayList<>();
        for(UserEntity user: userRepository.findAll()){
            UserDto dto=new UserDto();
            dto.setUserId(user.getUserId());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            users.add(dto);
        }

        return users;
    }
}