package com.microservicesproject.user_service.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.microservicesproject.user_service.DTO.UserRequestDTO;
import com.microservicesproject.user_service.DTO.UserResponseDTO;
import com.microservicesproject.user_service.Enums.Role;
import com.microservicesproject.user_service.Model.Users;
import com.microservicesproject.user_service.Repository.UserRepository;

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
		user.setAddress(userRequestDTO.getAddressString());
		return user;

	}
	 @SuppressWarnings("unused")
	private UserRequestDTO mapUserToRequestDTO(Users user){
		UserRequestDTO requestDTO = new UserRequestDTO();
		requestDTO.setAddressString(user.getAddress());    
		requestDTO.setMobString(user.getMobileNumber());
		requestDTO.setPassString(hashPassword(user.getPassword()));
		requestDTO.setRole(user.getRoles().contains(Role.CUSTOMER) ? Role.CUSTOMER : Role.ADMIN);
		requestDTO.setUseremailString(user.getEmail());
		requestDTO.setUsernameString(user.getUsername());
		requestDTO.setAddressString(user.getAddress());
		return requestDTO;

	 }
	@SuppressWarnings("unused")
	private Users mapResponseDTOToUser(UserResponseDTO responseDTO) {
		Users user = new Users();
		user.setUsername(responseDTO.getUsername());
		user.setMobileNumber(responseDTO.getMobilString());
		user.setEmail(responseDTO.getEmailString());
		user.setRoles(Set.of(responseDTO.getRole()));
		user.setPassword(hashPassword(responseDTO.getPassString()));
		user.setAddress(responseDTO.getAddressString());
		return user;
	}
	private UserResponseDTO mapUserToResponseDTO(Users user){
		UserResponseDTO responseDTO = new UserResponseDTO();
		responseDTO.setEmailString(user.getEmail());
		responseDTO.setMobilString(user.getMobileNumber());
		responseDTO.setRole(user.getRoles().contains(Role.ADMIN) ? Role.ADMIN : Role.CUSTOMER);
		responseDTO.setUsername(user.getUsername());
		responseDTO.setLastModifiedDate(LocalDateTime.now());
		responseDTO.setAddressString(user.getAddress());
		return responseDTO;
	}

	public Optional<UserResponseDTO> createUser(UserRequestDTO userDto) {
		// Check if a user with the   email already exists
		if (!repository.findByEmail(userDto.getUseremailString()).isEmpty()) {
			return Optional.empty();
		}
		Users newUser = mapUserDtoUsers(userDto);
		Users savedUser = repository.save(newUser);
		return Optional.of(mapUserToResponseDTO(savedUser));
	}
	
	public Optional<UserResponseDTO> updateUser(UserRequestDTO userRequestDTO){
		 // Find the existing user by email
		 Optional<Users> existingUserOpt = repository.findByEmail(userRequestDTO.getUseremailString());
    
		 if (existingUserOpt.isEmpty()) {
			 return Optional.empty();
		 }
		 
		 // Get the existing user
		 Users existingUser = existingUserOpt.get();
		 
		 // Update only the necessary fields
		 existingUser.setUsername(userRequestDTO.getUsernameString());
		 existingUser.setMobileNumber(userRequestDTO.getMobString());
		 existingUser.setPassword(hashPassword(userRequestDTO.getPassString()));
		 existingUser.setRoles(Set.of(userRequestDTO.getRole()));
		 
		 // Save the updated user to the repository
		 Users updatedUser = repository.save(existingUser);
		 
		 return Optional.of(mapUserToResponseDTO(updatedUser));
	}

	public void deleteUser(Long Id){
		//check if the user exists
		Users user = repository.findById(Id)
		.orElseThrow(() -> new RuntimeException("User not found with id " + Id));
		repository.delete(user);
	}

	public Optional<UserResponseDTO> getUserbyEmail(String email){
		Optional<Users> user =  repository.findByEmail(email);
		return Optional.of(mapUserToResponseDTO(user.get()));
	}
	

}
