package com.re_ride.subscriptionms.ride.response;

import com.re_ride.subscriptionms.ride.Ride;

public class RideResponse {
    private Ride ride;
    private String message;

    public RideResponse(Ride ride, String message) {
        this.ride = ride;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }
}
