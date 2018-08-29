package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	AppUser findByEmail(String email);

	AppUser findByMobileNumber(String mobileNumber);

}
