package com.re_ride.subscriptionms.subscription;

public interface SubscriptionService {
    Subscription getSubscriptionByUserId(Long userId);
    Subscription createSubscription(Long userId, Subscription subscription);
    Subscription updateSubscription(Long userId, Subscription subscription);
    boolean deleteSubscription(Long userId);
}