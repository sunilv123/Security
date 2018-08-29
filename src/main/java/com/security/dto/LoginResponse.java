package com.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LoginResponse {
		
		private Long id;
		
		private String name;
		
		private String email;
		
		private String mobileNumber;
		
		private String role;

		private String password;
		
		private String token;

		public Long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public String getRole() {
			return role;
		}

		public String getPassword() {
			return password;
		}

		public String getToken() {
			return token;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setToken(String token) {
			this.token = token;
		}
}
