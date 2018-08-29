package com.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.security.enums.Status;

@Entity
public class LoginData extends BaseEntity{

	@Column(unique=true, nullable=false)
	private String xAuth;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.LOGGEDIN;
	
	@ManyToOne
	@NotNull
	private AppUser appUser;

	public String getxAuth() {
		return xAuth;
	}

	public Status getStatus() {
		return status;
	}

	public void setxAuth(String xAuth) {
		this.xAuth = xAuth;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
}
