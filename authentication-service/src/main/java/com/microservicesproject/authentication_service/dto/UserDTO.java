package com.microservicesproject.authentication_service.dto;

import java.sql.Date;

import com.microservicesproject.user_service.Enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String email;
	private String username; 
	private String mobile;
	private String address;
	private Role role;
 
}
