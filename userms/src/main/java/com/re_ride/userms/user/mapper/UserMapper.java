package com.re_ride.userms.user.mapper;

import com.re_ride.userms.user.User;
import com.re_ride.userms.user.dto.UserDTO;

public class UserMapper {

    public static UserDTO mapUserDto(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setUserType(String.valueOf(user.getUserType()));

        return userDTO;
    }
}
