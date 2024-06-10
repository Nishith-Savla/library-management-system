package com.group35.library_management_system.controller;

import com.group35.library_management_system.model.User;
import com.group35.library_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // Indicates that this class is a Spring MVC controller
@CrossOrigin
@RequestMapping("/api/users") // Maps HTTP requests to /api/users to this class
public class UserController {

    // Injects the UserRepository dependency - the recommended way is to use constructors :)
    @Autowired
    private UserRepository userRepository;


    @GetMapping // Maps HTTP GET requests to /api/users to this method
    public List<User> getAllUsers() {
        /*
            Fetches and returns all User entities from the database
         */
        return userRepository.findAll();
    }
}
