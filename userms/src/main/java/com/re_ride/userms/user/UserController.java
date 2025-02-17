package com.re_ride.userms.user;

import com.re_ride.userms.user.dto.UserDTO;
import com.re_ride.userms.user.mapper.UserMapper;
import com.re_ride.userms.user.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //get all Users
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers()
                .stream()
                .map(UserMapper::mapUserDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    //get User by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
        User user = userService.getUserById(userId);

        if(user == null){
            return new ResponseEntity<>(new UserResponse(null, "User not found."), HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = UserMapper.mapUserDto(user);

        return new ResponseEntity<>(new UserResponse(userDTO, "User found."), HttpStatus.OK);
    }

    //get all riders
    @GetMapping("/riders")
    public ResponseEntity<List<UserDTO>> getAllRiders(){
        return new ResponseEntity<>(userService.getAllRiders()
                .stream()
                .map(UserMapper::mapUserDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    //create user
    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody User user){
        return new ResponseEntity<>(new UserResponse(UserMapper.mapUserDto(userService.createUser(user)), "User created successfully."), HttpStatus.CREATED);
    }

    //patch user
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUserById(@RequestBody User user, @PathVariable Long userId){
        User updatedUser = userService.updateUserById(userId, user);

        if(updatedUser == null){
            return new ResponseEntity<>(new UserResponse(null, "User not found."), HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = UserMapper.mapUserDto(updatedUser);

        return new ResponseEntity<>(new UserResponse(userDTO, "User updated successfully."), HttpStatus.OK);
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
