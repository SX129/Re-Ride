package com.re_ride.paymentms.payment.messaging;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class PaymentEvent implements Serializable {
    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED
    }

    private Long paymentId;
    private Long userId;
    private Long subscriptionId;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @JsonCreator
    public PaymentEvent(@JsonProperty("paymentId") Long paymentId,
                        @JsonProperty("userId") Long userId,
                        @JsonProperty("subscriptionId") Long subscriptionId,
                        @JsonProperty("totalAmount") BigDecimal totalAmount,
                        @JsonProperty("paymentStatus") PaymentStatus paymentStatus) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.subscriptionId = subscriptionId;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
