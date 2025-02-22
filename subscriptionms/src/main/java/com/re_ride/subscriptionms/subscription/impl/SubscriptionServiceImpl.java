package com.re_ride.subscriptionms.subscription.impl;

import com.re_ride.subscriptionms.subscription.Subscription;
import com.re_ride.subscriptionms.subscription.SubscriptionRepository;
import com.re_ride.subscriptionms.subscription.SubscriptionService;
import com.re_ride.subscriptionms.subscription.client.UserClient;
import com.re_ride.subscriptionms.subscription.dto.UserDTO;
import com.re_ride.subscriptionms.subscription.response.UserResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionRepository subscriptionRepository;
    private UserClient userClient;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserClient userClient) {
        this.subscriptionRepository = subscriptionRepository;
        this.userClient = userClient;
    }

    public UserDTO getUser(Long userId){
        UserResponse response = userClient.getUserById(userId);

        if(response == null || response.getUserDTO() == null || response.getUserDTO().getUserType().equals("DRIVER")) {
            return null;
        }

        return response.getUserDTO();
    }

    @Override
    public Subscription getSubscriptionByUserId(Long userId) {
        if(getUser(userId) == null){
            return null;
        }

        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public Subscription createSubscription(Long userId, Subscription subscription) {
        if(getUser(userId) == null){
            return null;
        }

        subscription.setUserId(userId);
        subscriptionRepository.save(subscription);

        return subscription;
    }

    @Override
    public Subscription updateSubscription(Long userId, Subscription subscription) {
        if(getUser(userId) == null){
            return null;
        }

        Subscription updatedSubscription = subscriptionRepository.findByUserId(userId);

        if(updatedSubscription != null){

            if(subscription.getPaymentId() != null){
                updatedSubscription.setPaymentId(subscription.getPaymentId());
            }

            if(subscription.getRouteId() != null){
                updatedSubscription.setRouteId(subscription.getRouteId());
            }

            if(subscription.getSubscriptionAmount() != null){
                updatedSubscription.setSubscriptionAmount(subscription.getSubscriptionAmount());
            }

            if(subscription.getSubscriptionAmount() != null){
                updatedSubscription.setSubscriptionAmount(subscription.getSubscriptionAmount());
            }

            if(subscription.getPickUpDate() != null){
                updatedSubscription.setPickUpDate(subscription.getPickUpDate());
            }

            if(subscription.getPickUpTime() != null){
                updatedSubscription.setPickUpTime(subscription.getPickUpTime());
            }

            if(subscription.getSubscriptionStatus() != null){
                updatedSubscription.setSubscriptionStatus(subscription.getSubscriptionStatus());
            }

            updatedSubscription.setUpdatedAt(LocalDateTime.now());

            subscriptionRepository.save(updatedSubscription);
            return updatedSubscription;
        }

        return null;
    }

    @Override
    public boolean deleteSubscription(Long userId) {
        if(getUser(userId) == null){
            return false;
        }

        Subscription subscription = subscriptionRepository.findByUserId(userId);

        if(subscription != null){
            subscriptionRepository.deleteById(subscription.getSubscriptionId());
            return true;
        }

        return false;
    }
}
