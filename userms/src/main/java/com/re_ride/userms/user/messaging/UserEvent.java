package com.re_ride.userms.user.messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

public class UserEvent implements Serializable {
    public enum UserType {
        RIDER,
        DRIVER
    }

    private Long userId;
    private String firstName;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @JsonCreator
    public UserEvent(
            @JsonProperty("userId") Long userId,
            @JsonProperty("userType") UserType userType,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("email") String email
    ) {
        this.userId = userId;
        this.userType = userType;
        this.firstName = firstName;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
