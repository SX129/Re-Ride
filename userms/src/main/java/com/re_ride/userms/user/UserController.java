package com.re_ride.userms.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //get all Users
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    //get User by ID
    //TODO: create DTO
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        User user = userService.getUserById(userId);

        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //get all riders
    //TODO: create DTO
    @GetMapping("/riders")
    public ResponseEntity<List<User>> getAllRiders(){
        return new ResponseEntity<>(userService.getAllRiders(), HttpStatus.OK);
    }

    //get all drivers
    //TODO: create DTO
    @GetMapping("/drivers")
    public ResponseEntity<List<User>> getAllDrivers(){
        return new ResponseEntity<>(userService.getAllDrivers(), HttpStatus.OK);
    }

    //create user
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    //patch user
    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable Long userId){
        User updatedUser = userService.updateUserById(userId, user);

        if(updatedUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        if(userService.deleteUserById(userId)){
            return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }
}
