package com.microservicesproject.Service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.microservicesproject.DTO.UserRequestDTO;
import com.microservicesproject.Model.Users;
import com.microservicesproject.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;

	public String hashPassword(String plainPassword) {
	    return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	
	private Users mapUserDtoUsers(UserRequestDTO userRequestDTO) {
		Users user = new Users();
		user.setEmail(userRequestDTO.getUseremailString());
		user.setMobileNumber(userRequestDTO.getMobString());
		user.setPassword(hashPassword(userRequestDTO.getPassString()));
		user.setRoles(Set.of(userRequestDTO.getRole()));
		user.setUsername(userRequestDTO.getUsernameString());
		return user;

	}

	public Optional<Users> createUser(UserRequestDTO userDto) {
		// no Such user exists, create a user
		if (repository.findByUsername(userDto.getUsernameString()).isEmpty()
				&& repository.findByEmail(userDto.getUseremailString()).isEmpty()) {
			
			return Optional.of(mapUserDtoUsers(userDto));
		}
		return Optional.of(null);

	}

}
