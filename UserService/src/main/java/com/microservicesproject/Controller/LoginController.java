package com.microservicesproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservicesproject.DTO.UserRequestDTO;
import com.microservicesproject.Service.UserService;

@Controller
public class LoginController {

	@Autowired
	private final UserService userService;

	private boolean validateUser(String username, String password) {
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

		return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar && username.matches("[a-zA-Z0-9_\\-.]+");
	}

	@GetMapping("/login")
	public String showLoginPage() {

		return "login"; // Return the name of the HTML template (login.html) to render
	}

	@PostMapping("/perform_login")
	public String performLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		boolean isValidUser = validateUser(username, password);
		if (isValidUser) {
			return "redirect:/home";
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
	}

	@GetMapping("/signup")

	public String showSignupPage() {
		return "signup"; // Return the name of the HTML template (signup.html) to render
	}
	
	
	@PostMapping("/perform_signup")
	public String performSignup(@RequestBody UserRequestDTO userRequestDTO) {
		
		
		
		return new String(" ");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}