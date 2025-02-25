package com.re_ride.subscriptionms.ride;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "rides")
public class Ride {

    public enum RideStatus {
        PENDING,
        COMPLETED,
        CANCELLED,
        MISSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideId;

    private Long subscriptionId;

    private Long routeId;

    private Long driverId;

    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime dropOffTime;

    private Integer estimatedDuration;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    public Ride(){
        this.rideStatus = RideStatus.PENDING;
        this.date = LocalDate.now();
    }

    public Ride(Long subscriptionId, Long routeId, Long driverId, Long userId, LocalDate date, LocalTime pickUpTime, LocalTime dropOffTime, Integer estimatedDuration) {
        this.subscriptionId = subscriptionId;
        this.routeId = routeId;
        this.driverId = driverId;
        this.userId = userId;
        this.date = date;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.estimatedDuration = estimatedDuration;
        this.rideStatus = RideStatus.PENDING;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public LocalTime getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(LocalTime dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
}
