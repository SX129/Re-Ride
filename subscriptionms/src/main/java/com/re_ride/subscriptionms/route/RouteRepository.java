package com.re_ride.subscriptionms.route;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findBySubscription_SubscriptionId(Long subscriptionId);
    boolean existsBySubscription_SubscriptionId(Long subscriptionId);
}
