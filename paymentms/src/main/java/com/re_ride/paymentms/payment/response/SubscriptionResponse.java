package com.re_ride.paymentms.payment.response;

import com.re_ride.paymentms.payment.dto.SubscriptionDTO;
import com.re_ride.paymentms.payment.dto.UserDTO;

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
