package com.security.service;

import java.util.List;

import com.security.dto.AppUserDto;
import com.security.dto.LoginResponse;
import com.security.model.AppUser;
import com.security.model.LoginData;

public interface UserService {

	public LoginData loadByUserName(String email);

	public LoginResponse login(AppUserDto dto);
	
	public LoginResponse register(AppUserDto dto);

	public List<AppUser> getAllUser();
	
}
