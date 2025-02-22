package com.re_ride.subscriptionms.route.response;

import com.re_ride.subscriptionms.route.Route;
import com.re_ride.subscriptionms.route.dto.RouteDTO;

public class RouteResponse {
    private RouteDTO routeDto;
    private String message;

    public RouteResponse(RouteDTO routeDto, String message) {
        this.routeDto = routeDto;
        this.message = message;
    }

    public RouteDTO getRouteDto() {
        return routeDto;
    }

    public void setRouteDto(RouteDTO routeDto) {
        this.routeDto = routeDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
