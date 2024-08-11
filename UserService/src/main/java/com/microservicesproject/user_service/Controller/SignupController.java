package com.microservicesproject.user_service.Controller;

//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
    // @Autowired
    // private UserService userService; // Assume you have a UserService to handle business logic
    // @Autowired
    // private AccountUtils utils;
    
    // @GetMapping("/signup")
    // public String showSignupPage() {
    	
    // 	return "signup";
    // }
    
    // @PostMapping("/perform_signup")
    // public ModelAndView performSignup(@ModelAttribute UserRequestDTO signupRequestDTO) {
        
    //     signupRequestDTO.setRole(Role.CUSTOMER); // Assuming Role is an enum with USER_ROLE
        
    //     System.out.println(signupRequestDTO.getUseremailString());
    //     if (utils.validateUser(signupRequestDTO.getUsernameString(), signupRequestDTO.getPassString())) {
    //         Optional<Users> successful = userService.createUser(signupRequestDTO);
    //         if (!successful.isEmpty()) {
    //         	System.out.println("Singup Successfull");
    //             return new ModelAndView("SignupSuccess");
    //         } else {
    //         	System.out.println("failed");
    //             ModelAndView modelAndView = new ModelAndView("signup");
    //             modelAndView.addObject("error", "User creation failed. Please try again.");
    //             return modelAndView;
    //         }
    //     } else {
    //     	System.out.println("Failed");
    //         ModelAndView modelAndView = new ModelAndView("signup");
    //         modelAndView.addObject("error", "Invalid username or password. Please try again.");
    //         return modelAndView;
    //     }
    // }
//}
