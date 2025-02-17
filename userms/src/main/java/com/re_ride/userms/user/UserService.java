package com.re_ride.userms.user;

import java.util.List;

public interface UserService{
    List<User> getAllUsers();
    User getUserById(Long userId);
    List<User> getAllRiders();
    List<User> getAllDrivers();
    User createUser(User user);
    User updateUserById(Long userId, User user);
    String deleteUserById(Long userId);
}
