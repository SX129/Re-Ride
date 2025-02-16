package com.re_ride.userms.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    //get all Users
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return null;
    }

    //get User by ID
    //TODO: create DTO
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        return null;
    }

    //get all riders
    //TODO: create DTO
    @GetMapping("/riders")
    public ResponseEntity<List<User>> getAllRiders(){
        return null;
    }

    //get all drivers
    //TODO: create DTO
    @GetMapping("/drivers")
    public ResponseEntity<List<User>> getAllDrivers(){
        return null;
    }

    //create user
    @PostMapping()
    public ResponseEntity<User> createUser(){
        return null;
    }

    //patch user
    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable Long userId){
        return null;
    }

    //delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        return null;
    }
}
