package com.re_ride.subscriptionms.subscription;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.re_ride.subscriptionms.route.Route;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    public enum PickUpDate {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }

    public enum SubscriptionStatus {
        ACTIVE,
        CANCELLED,
        PAUSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    private Long userId;

    private Long paymentId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    @JsonBackReference
    private Route route;

    private BigDecimal subscriptionAmount;

    @Enumerated(EnumType.STRING)
    private PickUpDate pickUpDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public Subscription(){}

    public Subscription(Long subscriptionId, Route route, BigDecimal subscriptionAmount, PickUpDate pickUpDate, LocalTime pickUpTime, SubscriptionStatus subscriptionStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.subscriptionId = subscriptionId;
        this.route = route;
        this.subscriptionAmount = subscriptionAmount;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.subscriptionStatus = subscriptionStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }

        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void removeRoute() {
        this.route = null;
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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public BigDecimal getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(BigDecimal subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public PickUpDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(PickUpDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
