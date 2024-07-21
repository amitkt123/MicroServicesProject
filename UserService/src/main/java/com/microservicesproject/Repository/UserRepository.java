package com.microservicesproject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicesproject.Model.Users;


public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUsername(String Username);
	Optional<Users> findByEmail(String email);  
    Optional<Users> findByMobileNumber(String mobileNumber);
}
	