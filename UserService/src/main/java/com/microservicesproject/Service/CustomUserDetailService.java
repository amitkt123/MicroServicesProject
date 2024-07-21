package com.microservicesproject.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservicesproject.Model.Users;
import com.microservicesproject.Repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> userOptional = repo.findByUsername(username);
		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException("User Name Not Found in the database");
		}

		return new User(userOptional.get().getUsername(), userOptional.get().getPassword(), null);

	}

}
