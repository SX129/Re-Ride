package com.re_ride.subscriptionms.ride;

import com.re_ride.subscriptionms.ride.response.RideResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/rides")
public class RideController {
    private RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    //get all rides by userId
    @GetMapping()
    public ResponseEntity<List<Ride>> getAllRidesByUserId(@PathVariable Long userId){
        List<Ride> rides = rideService.getAllRidesByUserId(userId);

        if(rides == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    //get ride by rideId
    @GetMapping("/{rideId}")
    public ResponseEntity<RideResponse> getRideById(@PathVariable Long userId, @PathVariable Long rideId){
        Ride ride = rideService.getRideById(userId, rideId);

        if(ride == null){
            return new ResponseEntity<>(new RideResponse(null, "Unable to find ride for user."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RideResponse(ride, "Ride found successfully."), HttpStatus.OK);
    }

    //create ride
    @PostMapping()
    public ResponseEntity<RideResponse> createRide(@PathVariable Long userId, @RequestBody Ride ride){
        Ride savedRide = rideService.createRide(userId, ride);

        if(savedRide == null){
            return new ResponseEntity<>(new RideResponse(null, "Unable to create ride."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RideResponse(savedRide, "Ride created successfully."), HttpStatus.CREATED);
    }

    //update ride
    @PatchMapping("/{rideId}")
    public ResponseEntity<RideResponse> updateRideById(@PathVariable Long userId, @PathVariable Long rideId, @RequestBody Ride ride){
        Ride updatedRide = rideService.updateRidebyId(userId, rideId, ride);

        if(updatedRide == null){
            return new ResponseEntity<>(new RideResponse(null, "Unable to update ride."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RideResponse(updatedRide, "Ride updated successfully."), HttpStatus.OK);
    }

    //delete ride
    @DeleteMapping("/{rideId}")
    public ResponseEntity<String> deleteRide(@PathVariable Long userId, @PathVariable Long rideId){
        if(rideService.deleteRide(userId, rideId)){
            return new ResponseEntity<>("Ride deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Unable to delete ride.", HttpStatus.NOT_FOUND);
    }
}
