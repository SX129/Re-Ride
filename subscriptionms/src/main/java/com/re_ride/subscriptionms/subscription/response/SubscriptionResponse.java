package com.re_ride.subscriptionms.subscription.response;

import com.re_ride.subscriptionms.subscription.Subscription;

public class SubscriptionResponse {
    private Subscription subscription;
    private String message;

    public SubscriptionResponse(Subscription subscription, String message) {
        this.subscription = subscription;
        this.message = message;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
