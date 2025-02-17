package com.re_ride.userms.user;

import java.util.List;

public interface UserService{
    List<User> getAllUsers();
    User getUserById(Long userId);
    List<User> getAllRiders();
    User createUser(User user);
    User updateUserById(Long userId, User user);
    boolean deleteUserById(Long userId);
}
