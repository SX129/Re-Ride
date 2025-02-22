package com.re_ride.subscriptionms.subscription.response;

import com.re_ride.subscriptionms.subscription.Subscription;
import com.re_ride.subscriptionms.subscription.dto.SubscriptionDTO;

public class SubscriptionResponse {
    private SubscriptionDTO subscriptionDTO;
    private String message;

    public SubscriptionResponse(SubscriptionDTO subscriptionDTO, String message) {
        this.subscriptionDTO = subscriptionDTO;
        this.message = message;
    }

    public SubscriptionDTO getSubscriptionDTO() {
        return subscriptionDTO;
    }

    public void setSubscriptionDTO(SubscriptionDTO subscriptionDTO) {
        this.subscriptionDTO = subscriptionDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
