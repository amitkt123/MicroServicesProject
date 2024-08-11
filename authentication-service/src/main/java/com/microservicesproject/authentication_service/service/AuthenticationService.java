package com.microservicesproject.authentication_service.service;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicesproject.authentication_service.dto.AuthenticationResponseDTO;
import com.microservicesproject.authentication_service.dto.LoginDTO;
import com.microservicesproject.authentication_service.dto.UserDTO;
import com.microservicesproject.authentication_service.jwt_utilty.JwtUtility;
import com.microservicesproject.user_service.DTO.UserResponseDTO;

@Service
public class AuthenticationService  {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JwtUtility jwtUtility;
 
    private final String BASE_URI ="lb://user_service//api/users/" ; 

    public boolean validatePassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
    private UserDTO mapUserToAuthenticationResponseDTO(UserResponseDTO userResponseDTO) {
    	UserDTO responseDTO = new  UserDTO();
    	responseDTO.setAddress(userResponseDTO.getAddressString());
    	responseDTO.setEmail(userResponseDTO.getEmailString());
    	responseDTO.setMobile(userResponseDTO.getMobilString());
    	responseDTO.setRole(userResponseDTO.getRole());
    	responseDTO.setUsername(userResponseDTO.getUsername());
    	
    	
    }
    //using rest template to fetch the user details and validate
    public boolean authenticateUser(LoginDTO user){
       String URI = BASE_URI + user.getEmail();
       UserResponseDTO userResponseDTO =  restTemplate.getForObject(URI, UserResponseDTO.class);
       if(userResponseDTO== null) {
    	   return false;
       }  
       return  validatePassword(user.getPassword(), userResponseDTO.getPassString());
    }
    
    public String login(LoginDTO userDTO) throws RuntimeException{
    	if(authenticateUser(userDTO)) {
    		return jwtUtility.createJwtToken(userDTO.getEmail());
    	}
    	else {
    		throw new RuntimeException("the user doesn't have permissions and privileges to access ");
    	}
    	 
    }
    
    
    
}
