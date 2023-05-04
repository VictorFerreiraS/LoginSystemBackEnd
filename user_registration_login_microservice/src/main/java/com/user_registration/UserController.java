package com.user_registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository repository;

    // User Register
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register-user")
    public void registerUser(@RequestBody UserRegistrationRequest userRequest) {
        log.info("new user registration {}", userRequest);
        userService.registerUser(userRequest);
    }

    @GetMapping(path = "{email}")
    @CrossOrigin(origins = "http://localhost:8080/api/v1/users/")
    public Optional<User> getUser(@PathVariable("email") String email) {
        Optional<User> userByEmail = repository.findByEmail(email);
        return userByEmail;
    }

}