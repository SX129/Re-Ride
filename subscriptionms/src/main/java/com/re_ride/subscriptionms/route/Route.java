package com.re_ride.subscriptionms.route;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.re_ride.subscriptionms.subscription.Subscription;
import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    private String startLocation;
    private String endLocation;
    private Double distance;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    public Route(){}

    public Route(String startLocation, String endLocation, Double distance, Subscription subscription) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.distance = distance;
        this.subscription = subscription;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
