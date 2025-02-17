package com.re_ride.userms.user.response;

import com.re_ride.userms.user.dto.UserDTO;

public class UserResponse {
    private UserDTO userDTO;
    private String message;

    public UserResponse(UserDTO userDTO, String message) {
        this.userDTO = userDTO;
        this.message = message;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
