package com.blog.services;

import java.util.List;

import com.blog.payloads.UserDto;


public interface UserService {
	
// Interface k ander humko methods ko public likhne ki jarurat nhi hoti, usme methods autometically public and abstract ho jate hai 
	
	
	// 0  --> 
	UserDto registerNewUser(UserDto user);

	// 1. created user ---> ye hai normal user ko create karne k lie 
	UserDto createUser(UserDto user);
	
	// 2. updated user
	UserDto updateUser(UserDto user, Integer userId);
	
	// 3. get user by id
	UserDto getUserById(Integer userId); 
	
	// 4. get all users
	List<UserDto> getAllUsers();
	
	// 5. delete user
	void deleteUser(int userId);
	
}
