package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.AppConstants;
import com.security.dto.GenericResponse;
import com.security.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/get-all-user")
	public GenericResponse getAllUser()throws Exception {
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, userService.getAllUser());
	}
	
	
}
