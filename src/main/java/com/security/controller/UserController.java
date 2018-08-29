package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.AppConstants;
import com.security.dto.AppUserDto;
import com.security.dto.GenericResponse;
import com.security.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public GenericResponse register(@RequestBody AppUserDto dto)throws Exception {
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, userService.register(dto));
	}
	
	@PostMapping("/login")
	public GenericResponse login(@RequestBody AppUserDto dto)throws Exception {
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, userService.login(dto));
	}
	
}
