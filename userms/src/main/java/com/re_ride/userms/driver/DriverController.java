package com.re_ride.userms.driver;

import com.re_ride.userms.driver.dto.DriverDTO;
import com.re_ride.userms.driver.mapper.DriverMapper;
import com.re_ride.userms.driver.response.DriverResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/drivers")
public class DriverController {
    private DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }

    //get all drivers
    @GetMapping()
    public ResponseEntity<List<DriverDTO>> getAllDrivers(){
        return new ResponseEntity<>(driverService.getAllDrivers()
                .stream()
                .map(DriverMapper :: mapDriverDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    //get driver by id
    @GetMapping("/{userId}")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable Long userId){
        Driver driver = driverService.getDriverById(userId);
        if(driver == null){
            return new ResponseEntity<>(new DriverResponse(null, "Driver not found."), HttpStatus.NOT_FOUND);
        }

        DriverDTO driverDTO = DriverMapper.mapDriverDto(driver);

        return new ResponseEntity<>(new DriverResponse(driverDTO, "Driver found."), HttpStatus.OK);
    }

    //get all available drivers
    @GetMapping("/available")
    public ResponseEntity<List<DriverDTO>> getAllAvailableDrivers(){
        return new ResponseEntity<>(driverService.getAllAvailableDrivers()
                .stream()
                .map(DriverMapper :: mapDriverDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    //create driver
    @PostMapping()
    public ResponseEntity<DriverResponse> createDriver(@RequestBody Driver driver){
        return new ResponseEntity<>(new DriverResponse(DriverMapper.mapDriverDto(driverService.createDriver(driver)), "Driver created successfully."), HttpStatus.CREATED);
    }

    //patch driver by id
    @PatchMapping("/{userId}")
    public ResponseEntity<DriverResponse> updateDriverById(@PathVariable Long userId, @RequestBody Driver driver){
        Driver updatedDriver = driverService.updateDriverById(userId, driver);

        if(updatedDriver == null){
            return new ResponseEntity<>(new DriverResponse(null, "Driver not found."), HttpStatus.NOT_FOUND);
        }

        DriverDTO driverDTO = DriverMapper.mapDriverDto(updatedDriver);

        return new ResponseEntity<>(new DriverResponse(driverDTO, "Driver updated successfully."), HttpStatus.OK);
    }

    //delete driver by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteDriverById(@PathVariable Long userId){
        if(driverService.deleteDriverById(userId)){
            return new ResponseEntity<>("Driver deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Driver not found.", HttpStatus.NOT_FOUND);
    }
}
