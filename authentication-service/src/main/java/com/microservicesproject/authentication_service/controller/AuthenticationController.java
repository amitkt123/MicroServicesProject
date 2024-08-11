package com.microservicesproject.authentication_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservicesproject.authentication_service.dto.AuthenticationResponseDTO;
import com.microservicesproject.authentication_service.dto.LoginDTO;
import com.microservicesproject.authentication_service.service.AuthenticationService;
import com.microservicesproject.authentication_service.userservice_client.UserServiceClient;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private final AuthenticationService authenticationService;
	 

	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	 
	}

	/**
	 * Endpoint for user login.
	 * 
	 * @param loginRequest The login request containing username and password.
	 * @return JWT token if authentication is successful.
	 */
	@PostMapping("/login") 
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO userDTO) {
		String token = authenticationService.login(userDTO);
		return ResponseEntity.ok(new AuthenticationResponseDTO(token, userDTO));
	}
 

	/**
	 * Endpoint for refreshing JWT tokens.
	 * 
	 * @param refreshToken The refresh token provided by the user.
	 * @return New JWT token.
	 */
	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@RequestParam String refreshToken) {
		String newToken = authenticationService.refreshToken(refreshToken);
		return ResponseEntity.ok(new JwtResponse(newToken));
	}

	/**
	 * Endpoint for logging out.
	 * 
	 * @param token The JWT token to be invalidated.
	 * @return Confirmation message.
	 */
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestParam String token) {
		authenticationService.logout(token);
		return ResponseEntity.ok("User logged out successfully");
	}
}