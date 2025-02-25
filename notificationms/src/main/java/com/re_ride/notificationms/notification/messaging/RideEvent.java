package com.re_ride.notificationms.notification.messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

public class RideEvent implements Serializable {
    public enum RideStatus {
        PENDING,
        COMPLETED,
        CANCELLED,
        MISSED
    }

    private Long rideId;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @JsonCreator
    public RideEvent(@JsonProperty("rideId") Long rideId,
                     @JsonProperty("userId") Long userId,
                     @JsonProperty("rideStatus") RideStatus rideStatus) {
        this.rideId = rideId;
        this.userId = userId;
        this.rideStatus = rideStatus;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }
}

