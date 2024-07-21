package com.microservicesproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Return the name of the HTML template (login.html) to render
    }

    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup"; // Return the name of the HTML template (signup.html) to render
    }
}