package com.re_ride.subscriptionms.route;

import com.re_ride.subscriptionms.route.dto.RouteDTO;
import com.re_ride.subscriptionms.route.mapper.RouteMapper;
import com.re_ride.subscriptionms.route.response.RouteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions/{subscriptionId}/route")
public class RouteController {
    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    //get route of subscription
    @GetMapping()
    public ResponseEntity<RouteResponse> getRouteBySubscriptionId(@PathVariable Long subscriptionId){
        Route route = routeService.getRouteBySubscriptionId(subscriptionId);

        if(route == null){
            return new ResponseEntity<>(new RouteResponse(null, "Route not found."), HttpStatus.NOT_FOUND);
        }

        RouteDTO routeDTO = RouteMapper.mapRouteDto(route);

        return new ResponseEntity<>(new RouteResponse(routeDTO, "Route found successfully."), HttpStatus.OK);
    }

    //create route
    @PostMapping()
    public ResponseEntity<RouteResponse> createRoute(@PathVariable Long subscriptionId, @RequestBody Route route){
        Route savedRoute = routeService.createRoute(subscriptionId, route);

        if(savedRoute == null){
            return new ResponseEntity<>(new RouteResponse(null, "Subscription not found."), HttpStatus.NOT_FOUND);
        }

        RouteDTO routeDTO = RouteMapper.mapRouteDto(savedRoute);

        return new ResponseEntity<>(new RouteResponse(routeDTO, "Route created successfully."), HttpStatus.CREATED);
    }

    //update route
    @PatchMapping()
    public ResponseEntity<RouteResponse> updateRoute(@PathVariable Long subscriptionId, @RequestBody Route route){
        Route updatedRoute = routeService.updateRoute(subscriptionId, route);

        if(updatedRoute == null){
            return new ResponseEntity<>(new RouteResponse(null, "Route not found."), HttpStatus.NOT_FOUND);
        }

        RouteDTO routeDTO = RouteMapper.mapRouteDto(updatedRoute);

        return new ResponseEntity<>(new RouteResponse(routeDTO, "Route updated successfully."), HttpStatus.OK);
    }

    //delete route
    @DeleteMapping()
    public ResponseEntity<String> deleteRoute(@PathVariable Long subscriptionId){
        if(routeService.deleteRoute(subscriptionId)){
            return new ResponseEntity<>("Route deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Route not found.", HttpStatus.NOT_FOUND);
    }

}
