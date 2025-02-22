package com.re_ride.subscriptionms.route.impl;

import com.re_ride.subscriptionms.route.Route;
import com.re_ride.subscriptionms.route.RouteRepository;
import com.re_ride.subscriptionms.route.RouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    private RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route getRouteBySubscriptionId(Long subscriptionId) {
        return null;
    }

    @Override
    public Route createRoute(Long subscriptionId, Route route) {
        return null;
    }

    @Override
    public Route updateRoute(Long subscriptionId, Route route) {
        return null;
    }

    @Override
    public boolean deleteRoute(Long subscriptionId) {
        return false;
    }
}
