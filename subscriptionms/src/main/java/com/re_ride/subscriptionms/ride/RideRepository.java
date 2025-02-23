package com.re_ride.subscriptionms.ride;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByUserId(Long userId);
}
