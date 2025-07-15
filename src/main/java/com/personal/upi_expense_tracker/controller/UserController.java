package com.personal.upi_expense_tracker.controller;

import com.personal.upi_expense_tracker.model.User;
import com.personal.upi_expense_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public Optional<User> getUserById(@PathVariable int userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        return userService.register(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
    }
}
