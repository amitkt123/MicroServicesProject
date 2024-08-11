package com.microservicesproject.user_service.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservicesproject.user_service.Enums.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserResponseDTO {
	@NotBlank
    @JsonProperty("username")
    private String username;
    @JsonProperty("role")
    private Role role;
    @JsonProperty
    private String mobilString;
    @JsonProperty
    private String passString;
    @JsonProperty
    private String emailString;
    @JsonProperty("dateCreated")
    private LocalDateTime createdDate;
    @JsonProperty("dateModified")
    private LocalDateTime lastModifiedDate;
    @JsonProperty
    private String addressString;
}