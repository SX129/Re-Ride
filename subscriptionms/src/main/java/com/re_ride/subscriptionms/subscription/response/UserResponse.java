package com.re_ride.subscriptionms.subscription.response;

import com.re_ride.subscriptionms.subscription.dto.UserDTO;

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
