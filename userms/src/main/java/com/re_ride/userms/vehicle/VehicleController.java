package com.re_ride.userms.vehicle;

import com.re_ride.userms.driver.Driver;
import com.re_ride.userms.driver.DriverRepository;
import com.re_ride.userms.vehicle.dto.VehicleDTO;
import com.re_ride.userms.vehicle.mapper.VehicleMapper;
import com.re_ride.userms.vehicle.response.VehicleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping()
    public ResponseEntity<List<VehicleDTO>> getAllVehicles(){
        return new ResponseEntity<>(vehicleService.getAllVehicles()
                .stream()
                .map(VehicleMapper :: mapVehicleDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable Long vehicleId){
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        if(vehicle == null){
            return new ResponseEntity<>(new VehicleResponse(null, "Vehicle not found."), HttpStatus.NOT_FOUND);
        }

        VehicleDTO vehicleDTO = VehicleMapper.mapVehicleDto(vehicle);

        return new ResponseEntity<>(new VehicleResponse(vehicleDTO, "Vehicle found."), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleService.createVehicle(vehicleDTO);

        if(vehicle == null){
            return new ResponseEntity<>(new VehicleResponse(null, "Vehicle not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new VehicleResponse(vehicleDTO, "Vehicle created successfully."), HttpStatus.OK);
    }

    @PatchMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponse> updateVehicleById(@PathVariable Long vehicleId, @RequestBody Vehicle vehicle){
        Vehicle updatedVehicle = vehicleService.updateVehicleById(vehicleId, vehicle);

        if(updatedVehicle == null){
            return new ResponseEntity<>(new VehicleResponse(null, "Vehicle not found."), HttpStatus.NOT_FOUND);
        }

        VehicleDTO vehicleDTO = VehicleMapper.mapVehicleDto(updatedVehicle);

        return new ResponseEntity<>(new VehicleResponse(vehicleDTO, "Vehicle updated successfully."), HttpStatus.OK);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable Long vehicleId){
        if(vehicleService.deleteVehicleById(vehicleId)){
            return new ResponseEntity<>("Vehicle deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Vehicle not found.", HttpStatus.NOT_FOUND);
    }
}
