package com.re_ride.subscriptionms.route;

public interface RouteService {
    Route getRouteBySubscriptionId(Long subscriptionId);

    Route createRoute(Long subscriptionId, Route route);

    Route updateRoute(Long subscriptionId, Route route);

    boolean deleteRoute(Long subscriptionId);
}
