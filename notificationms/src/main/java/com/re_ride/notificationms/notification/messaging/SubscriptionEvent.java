package com.re_ride.notificationms.notification.messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

public class SubscriptionEvent implements Serializable {
    public enum SubscriptionStatus {
        ACTIVE,
        CANCELLED,
        PAUSED
    }

    private Long subscriptionId;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus;

    @JsonCreator
    public SubscriptionEvent(@JsonProperty("subscriptionId") Long subscriptionId,
                             @JsonProperty("userId") Long userId,
                             @JsonProperty("subscriptionStatus") SubscriptionStatus subscriptionStatus) {
        this.subscriptionId = subscriptionId;
        this.userId = userId;
        this.subscriptionStatus = subscriptionStatus;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }
}
