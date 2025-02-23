package com.re_ride.subscriptionms.ride.impl;

import com.re_ride.subscriptionms.ride.Ride;
import com.re_ride.subscriptionms.ride.RideRepository;
import com.re_ride.subscriptionms.ride.RideService;
import com.re_ride.subscriptionms.subscription.Subscription;
import com.re_ride.subscriptionms.subscription.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideServiceImpl implements RideService {
    private RideRepository rideRepository;
    private SubscriptionRepository subscriptionRepository;

    public RideServiceImpl(RideRepository rideRepository, SubscriptionRepository subscriptionRepository) {
        this.rideRepository = rideRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    private Subscription getValidSubscription(Long userId){
        Subscription subscription = subscriptionRepository.findByUserId(userId);

        if(subscription != null && subscription.getSubscriptionStatus() == Subscription.SubscriptionStatus.ACTIVE){
            return subscription;
        }

        return null;
    }

    @Override
    public List<Ride> getAllRidesByUserId(Long userId) {
        Subscription subscription = getValidSubscription(userId);

        if(subscription == null){
            return null;
        }

        return rideRepository.findByUserId(userId);
    }

    @Override
    public Ride getRideById(Long userId, Long rideId) {
        Subscription subscription = getValidSubscription(userId);

        if(subscription == null){
            return null;
        }

        return rideRepository.findById(rideId).orElse(null);
    }

    @Override
    public Ride createRide(Long userId, Ride ride) {
        Subscription subscription = getValidSubscription(userId);

        if(subscription == null){
            return null;
        }
        System.out.println("TEST");
        System.out.println(subscription.getSubscriptionId());

        ride.setUserId(userId);
        ride.setSubscriptionId(subscription.getSubscriptionId());
        ride.setRouteId(subscription.getRoute().getRouteId());

        rideRepository.save(ride);

        return ride;
    }

    @Override
    public Ride updateRidebyId(Long userId, Long rideId, Ride ride) {
        Subscription subscription = getValidSubscription(userId);

        if(subscription == null){
            return null;
        }

        Ride updatedRide = rideRepository.findById(rideId).orElse(null);

        if(updatedRide != null && updatedRide.getUserId() == userId){

            if(ride.getPickUpTime() != null){
                updatedRide.setPickUpTime(ride.getPickUpTime());
            }

            if(ride.getDropOffTime() != null){
                updatedRide.setDropOffTime(ride.getDropOffTime());
            }

            if(ride.getRideStatus() != null){
                updatedRide.setRideStatus(ride.getRideStatus());
            }

            if(ride.getEstimatedDuration() != null){
                updatedRide.setEstimatedDuration(ride.getEstimatedDuration());
            }

            rideRepository.save(updatedRide);

            return updatedRide;
        }

        return null;
    }

    @Override
    public boolean deleteRide(Long userId, Long rideId) {
        Ride ride = rideRepository.findById(rideId).orElse(null);

        if(ride != null && ride.getUserId() == userId){
            rideRepository.deleteById(rideId);
            return true;
        }

        return false;
    }
}
