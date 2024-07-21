package com.microservicesproject.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservicesproject.Enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserResponseDTO {
	
    @JsonProperty("username")
    private String username;
    @JsonProperty("role")
    private Role role;
    @JsonProperty
    private String mobilString;
    
    @JsonProperty
    private String emailString;
    @JsonProperty("dateCreated")
    private LocalDateTime createdDate;
    @JsonProperty("dateModified")
    private LocalDateTime lastModifiedDate;
}