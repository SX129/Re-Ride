package com.re_ride.subscriptionms.ride;

import java.util.List;

public interface RideService {
    List<Ride> getAllRidesByUserId(Long userId);

    Ride getRideById(Long userId, Long rideId);

    Ride createRide(Long userId, Ride ride);

    Ride updateRidebyId(Long userId, Long rideId, Ride ride);

    boolean deleteRide(Long userId, Long rideId);
}
