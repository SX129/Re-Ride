package com.re_ride.subscriptionms.route.mapper;

import com.re_ride.subscriptionms.route.Route;
import com.re_ride.subscriptionms.route.dto.RouteDTO;

public class RouteMapper {
    public static RouteDTO mapRouteDto(Route route){
        RouteDTO routeDTO = new RouteDTO();

        routeDTO.setDistance(route.getDistance());
        routeDTO.setStartLocation(route.getStartLocation());
        routeDTO.setEndLocation(route.getEndLocation());
        routeDTO.setRouteId(route.getRouteId());
        routeDTO.setSubscriptionId(route.getSubscription().getSubscriptionId());

        return routeDTO;
    }
}
