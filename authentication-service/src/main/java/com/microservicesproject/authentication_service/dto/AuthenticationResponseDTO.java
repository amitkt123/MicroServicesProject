package com.microservicesproject.authentication_service.dto;

import java.util.Date;

import com.microservicesproject.user_service.Enums.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {
	private String token;
	private String email;
	private Date expiration;
	private Role role;
	private String address;
	private String mobile;

	public AuthenticationResponseDTO(String token, UserDTO userDTO ) {
		this.token = token;
		this.email= userDTO.getEmail();
		this.role= userDTO.getRole();
		this.address = userDTO.getAddress();
		this.mobile = userDTO.getMobile();
		this.expiration = Jwts.parser().parseClaimsJws(token).getBody().getExpiration();
		
		 
	}

}
