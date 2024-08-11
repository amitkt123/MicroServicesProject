package com.microservicesproject.user_service.Controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservicesproject.user_service.DTO.UserRequestDTO;
import com.microservicesproject.user_service.DTO.UserResponseDTO;
import com.microservicesproject.user_service.Service.UserService;
 
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserServiceController {

    @Autowired
    private UserService userService;
 
    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return userService.getUserbyEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO user) {
        Optional<UserResponseDTO> createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity< ? > updateUser(  @RequestBody UserRequestDTO userDetails) {
        try {
            Optional<UserResponseDTO>  updatedUser = userService.updateUser(userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}