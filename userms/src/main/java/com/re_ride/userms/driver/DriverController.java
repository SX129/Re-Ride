package com.re_ride.userms.driver;

import com.re_ride.userms.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/drivers")
public class DriverController {
    private DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }

    //get all drivers
    @GetMapping()
    public ResponseEntity<List<Driver>> getAllDrivers(){
        return new ResponseEntity<>(driverService.getAllDrivers(), HttpStatus.OK);
    }

    //get driver by id
    @GetMapping("/{userId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long userId){
        Driver driver = driverService.getDriverById(userId);
        if(driver != null){
            return new ResponseEntity<>(driverService.getDriverById(userId), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //get all available drivers
    @GetMapping("/available")
    public ResponseEntity<List<Driver>> getAllAvailableDrivers(){
        return new ResponseEntity<>(driverService.getAllAvailableDrivers(), HttpStatus.OK);
    }

    //create driver
    @PostMapping()
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver){
        return new ResponseEntity<>(driverService.createDriver(driver), HttpStatus.CREATED);
    }

    //patch driver by id
    @PatchMapping("/{userId}")
    public ResponseEntity<Driver> updateDriverById(@PathVariable Long userId, @RequestBody Driver driver){
        Driver updatedDriver = driverService.updateDriverById(userId, driver);

        if(updatedDriver != null){
            return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
