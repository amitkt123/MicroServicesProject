package com.microservicesproject.user_service.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.microservicesproject.user_service.DTO.UserRequestDTO;
import com.microservicesproject.user_service.Enums.Role;
import com.microservicesproject.user_service.Model.Users;
import com.microservicesproject.user_service.Service.UserService;

@Controller
@RequestMapping()
public class SignupController {

    @Autowired
    private UserService userService; // Assume you have a UserService to handle business logic
    @Autowired
    private AccountUtils utils;
    
    @GetMapping("/signup")
    public String showSignupPage() {
    	
    	return "signup";
    }
    
    @PostMapping("/perform_signup")
    public ModelAndView performSignup(@ModelAttribute UserRequestDTO signupRequestDTO) {
        
        signupRequestDTO.setRole(Role.CUSTOMER); // Assuming Role is an enum with USER_ROLE
        
        System.out.println(signupRequestDTO.getUseremailString());
        if (utils.validateUser(signupRequestDTO.getUsernameString(), signupRequestDTO.getPassString())) {
            Optional<Users> successful = userService.createUser(signupRequestDTO);
            if (!successful.isEmpty()) {
            	System.out.println("Singup Successfull");
                return new ModelAndView("SignupSuccess");
            } else {
            	System.out.println("failed");
                ModelAndView modelAndView = new ModelAndView("signup");
                modelAndView.addObject("error", "User creation failed. Please try again.");
                return modelAndView;
            }
        } else {
        	System.out.println("Failed");
            ModelAndView modelAndView = new ModelAndView("signup");
            modelAndView.addObject("error", "Invalid username or password. Please try again.");
            return modelAndView;
        }
    }
}
