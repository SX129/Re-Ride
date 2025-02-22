package com.re_ride.subscriptionms.route.response;

import com.re_ride.subscriptionms.route.Route;

public class RouteResponse {
    private Route route;
    private String message;

    public RouteResponse(Route route, String message) {
        this.route = route;
        this.message = message;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
