package com.re_ride.userms.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping()
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long vehicleId){
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        if(vehicle == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle){
        return new ResponseEntity<>(vehicleService.createVehicle(vehicle), HttpStatus.CREATED);
    }

    @PatchMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable Long vehicleId, @RequestBody Vehicle vehicle){
        Vehicle updatedVehicle = vehicleService.updateVehicleById(vehicleId, vehicle);

        if(updatedVehicle == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable Long vehicleId){
        if(vehicleService.deleteVehicleById(vehicleId)){
            return new ResponseEntity<>("Vehicle deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Vehicle not found.", HttpStatus.NOT_FOUND);
    }
}
