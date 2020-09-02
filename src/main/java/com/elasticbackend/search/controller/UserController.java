package com.elasticbackend.search.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elasticbackend.search.dto.UserDto;
import com.elasticbackend.search.service.UserService;


@RestController
@RequestMapping("/api/CDR")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ResponseEntity<UserDto> validateUser(@RequestParam(value="login.username",required=false) String userName,
			@RequestParam(value="login.password",required=false) String password) {
		
		UserDto userDto = userService.validateUser(userName,password);
		
		if(null != userDto) {
			LOGGER.info("User exists with provided username and password");
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		} else {
			LOGGER.info("User doesn't exist with provided username and password");
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/User")
	public void saveUser(@RequestBody UserDto userDto) {
		userService.saveUser(userDto);
	}

}
