package com.security.service.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.security.dto.AppUserDto;
import com.security.dto.LoginResponse;
import com.security.enums.Role;
import com.security.enums.Status;
import com.security.model.AppUser;
import com.security.model.LoginData;
import com.security.repository.AppUserRepository;
import com.security.repository.LoginDataRepository;
import com.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private LoginDataRepository loginDataRepository;

	@Override
	public LoginData loadByUserName(String token) {
		return loginDataRepository.findByXAuthAndStatus(token, Status.LOGGEDIN);
	}

	@Override
	@Transactional
	public LoginResponse register(AppUserDto dto) {

		AppUser appUser = appUserRepository.findByEmail(dto.getEmail());
		Assert.isNull(appUser, dto.getEmail() + " already exist");
		appUser = appUserRepository.findByMobileNumber(dto.getMobileNumber());
		Assert.isNull(appUser, dto.getMobileNumber() + " already exist");

		appUser = new AppUser();

		appUser.setName(dto.getName());
		appUser.setEmail(dto.getEmail());
		appUser.setRole(Role.valueOf(dto.getRole()));
		appUser.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		appUser.setMobileNumber(dto.getMobileNumber());

		appUserRepository.save(appUser);

		return toLoginResponse(appUser);
	}
	
	private LoginResponse toLoginResponse(AppUser user) {
		
		LoginResponse response = new LoginResponse();
		
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setMobileNumber(user.getMobileNumber());
		response.setRole(user.getRole().name());
		response.setToken(getAuthToken());
		
		LoginData loginData = new LoginData();
		loginData.setxAuth(response.getToken());
		loginData.setAppUser(user);
		loginDataRepository.save(loginData);
		
		return response;
	}

	@Override
	public LoginResponse login(AppUserDto dto) {

		Assert.notNull(dto.getEmail(), "Email is nullt");
		Assert.notNull(dto.getPassword(), "Mobile number already exist");

		AppUser user = appUserRepository.findByEmail(dto.getEmail());
		Assert.notNull(user, "User name or password something went wrong!");

		Assert.isTrue((bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())),
				"User name or password something went wrong!");

		return toLoginResponse(user);
	}
	
	private String getAuthToken() {
		final String token = UUID.randomUUID().toString();
		if (this.loginDataRepository.findByXAuth(token) != null) {
			this.getAuthToken();
		}
		return token;
	}

	@Override
	public List<AppUser> getAllUser() {
		// TODO Auto-generated method stub
		return appUserRepository.findAll();
	}

}
