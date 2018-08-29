package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.enums.Status;
import com.security.model.LoginData;

public interface LoginDataRepository extends JpaRepository<LoginData, Long>{

	LoginData findByXAuth(String token);

	LoginData findByXAuthAndStatus(String token, Status status);
}
