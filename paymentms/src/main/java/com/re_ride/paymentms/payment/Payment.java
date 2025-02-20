package com.re_ride.paymentms.payment;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    public enum PaymentType {
        CARD,
        PAYPAL
    }

    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
}
