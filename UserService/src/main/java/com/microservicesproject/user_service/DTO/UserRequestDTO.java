package com.microservicesproject.user_service.DTO;

import com.microservicesproject.user_service.Enums.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	@NotBlank
	private String usernameString;

	@NotBlank
	private String passString;

	private String useremailString;

	private String mobString;

	private String addressString;

	private Role role;
}
