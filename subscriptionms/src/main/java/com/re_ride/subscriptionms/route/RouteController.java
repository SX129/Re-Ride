package com.re_ride.subscriptionms.route;

import com.re_ride.subscriptionms.route.response.RouteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions/{subscriptionId}/route")
public class RouteController {
    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    //get route of subscription
    @GetMapping()
    public ResponseEntity<RouteResponse> getRouteBySubscriptionId(@PathVariable Long subscriptionId){
        return null;
    }

    //create route
    @PostMapping()
    public ResponseEntity<RouteResponse> createRoute(@PathVariable Long subscriptionId, @RequestBody Route route){
        return null;
    }

    //update route
    @PatchMapping()
    public ResponseEntity<RouteResponse> updateRoute(@PathVariable Long subscriptionId, @RequestBody Route route){
        return null;
    }

    //delete route
    @DeleteMapping()
    public ResponseEntity<String> deleteRoute(@PathVariable Long subscriptionId){
        return null;
    }
}
