package com.re_ride.subscriptionms.subscription.response;

import com.re_ride.subscriptionms.subscription.dto.Payment;

public class PaymentResponse {
    private Payment payment;
    private String message;

    public PaymentResponse(Payment payment, String message) {
        this.payment = payment;
        this.message = message;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
