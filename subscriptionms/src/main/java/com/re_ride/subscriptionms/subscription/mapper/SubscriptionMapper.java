package com.re_ride.subscriptionms.subscription.mapper;

import com.re_ride.subscriptionms.route.Route;
import com.re_ride.subscriptionms.subscription.Subscription;
import com.re_ride.subscriptionms.subscription.dto.SubscriptionDTO;

public class SubscriptionMapper {
    public static SubscriptionDTO mapSubscriptionDto(Subscription subscription) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();

        subscriptionDTO.setSubscriptionId(subscription.getSubscriptionId());
        subscriptionDTO.setUserId(subscription.getUserId());
        subscriptionDTO.setPaymentId(subscription.getPaymentId());
        subscriptionDTO.setSubscriptionAmount(subscription.getSubscriptionAmount());
        subscriptionDTO.setPickUpDate(SubscriptionDTO.PickUpDate.valueOf(subscription.getPickUpDate().name()));
        subscriptionDTO.setPickUpTime(subscription.getPickUpTime());
        subscriptionDTO.setSubscriptionStatus(SubscriptionDTO.SubscriptionStatus.valueOf(subscription.getSubscriptionStatus().name()));
        subscriptionDTO.setCreatedAt(subscription.getCreatedAt());
        subscriptionDTO.setUpdatedAt(subscription.getUpdatedAt());

        Route route = subscription.getRoute();

        if(route != null){
            subscriptionDTO.setRouteId(subscription.getRoute().getRouteId());
        }

        return subscriptionDTO;
    }
}