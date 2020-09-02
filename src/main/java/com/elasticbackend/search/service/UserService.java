package com.elasticbackend.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elasticbackend.search.dto.UserDto;
import com.elasticbackend.search.repo.UserRepo;

@Service
public class UserService{
	
	@Autowired
	UserRepo userRepository;

	
	public UserDto validateUser(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName,password);
	}

	
	public void saveUser(UserDto userDto) {
		userRepository.save(userDto);
	}
	
}
