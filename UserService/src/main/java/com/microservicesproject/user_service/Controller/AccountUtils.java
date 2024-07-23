package com.microservicesproject.user_service.Controller;

import org.springframework.stereotype.Component;

@Component
public class AccountUtils {
	 
	public boolean validateUser(String username, String password) {

		if (username == null || username.length() < 3 || username.length() > 15) {
			return false;
		}
		if (password == null || password.length() < 8) {
			return false;
		}
		boolean hasUpperCase = !password.equals(password.toLowerCase());
		boolean hasLowerCase = !password.equals(password.toUpperCase());
		boolean hasDigit = password.matches(".*\\d.*");
		boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

		boolean val =  hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar && username.matches("[a-zA-Z0-9_\\-.]+");
		if(val==false)
			System.out.println("validation failed");
		else 
			System.out.println("Validation success");
		return val;
	}
	
}
