package com.re_ride.subscriptionms.route.impl;

import com.re_ride.subscriptionms.route.Route;
import com.re_ride.subscriptionms.route.RouteRepository;
import com.re_ride.subscriptionms.route.RouteService;
import com.re_ride.subscriptionms.subscription.Subscription;
import com.re_ride.subscriptionms.subscription.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    private RouteRepository routeRepository;
    private SubscriptionRepository subscriptionRepository;

    public RouteServiceImpl(RouteRepository routeRepository, SubscriptionRepository subscriptionRepository) {
        this.routeRepository = routeRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Route getRouteBySubscriptionId(Long subscriptionId) {
        return routeRepository.findBySubscription_SubscriptionId(subscriptionId);
    }

    @Override
    public Route createRoute(Long subscriptionId, Route route) {
        if (routeRepository.existsBySubscription_SubscriptionId(subscriptionId)) {
            System.out.println("Route already exists for this subscription id.");
            return null;
        }

        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);

        if(subscription == null){
            System.out.println("Invalid subscription id.");
            return null;
        }

        route.setSubscription(subscription);
        routeRepository.save(route);

        subscription.setRoute(route);
        subscriptionRepository.save(subscription);

        return route;
    }

    @Override
    public Route updateRoute(Long subscriptionId, Route route) {
        Route updatedRoute = getRouteBySubscriptionId(subscriptionId);

        if(updatedRoute != null){

            if(route.getDistance() != null){
                updatedRoute.setDistance(route.getDistance());
            }

            if(route.getEndLocation() != null){
                updatedRoute.setEndLocation(route.getEndLocation());
            }

            if(route.getStartLocation() != null){
                updatedRoute.setStartLocation(route.getStartLocation());
            }

            routeRepository.save(updatedRoute);
            return updatedRoute;
        }

        return null;
    }

    @Override
    public boolean deleteRoute(Long subscriptionId) {
        Route route = getRouteBySubscriptionId(subscriptionId);

        if(route != null){
            Subscription subscription = route.getSubscription();

            if(subscription != null){
                subscription.removeRoute();
                subscriptionRepository.save(subscription);
            }

            routeRepository.deleteById(route.getRouteId());
            return true;
        }

        return false;
    }
}
