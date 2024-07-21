package com.microservicesproject.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservicesproject.Enums.Role;

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
	@JsonProperty("name")
	String usernameString;
	@NotBlank
	@JsonProperty("password")
	String passString;
	@JsonProperty("email")
	String useremailString;
	@JsonProperty("mobile")
	private String mobString;
	@JsonProperty
	private String addressString;
	@JsonProperty("role")
	Role role;
}
