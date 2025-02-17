package com.re_ride.userms.driver;

import com.re_ride.userms.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    //get all drivers
    @GetMapping()
    public ResponseEntity<List<Driver>> getAllDrivers(){
        return null;
    }

    //get driver by id
    @GetMapping("/{userId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long userId){
        return null;
    }

    //create driver
    @PostMapping()
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver){
        return null;
    }

    //patch driver by id
    @PatchMapping("/{userId}")
    public ResponseEntity<Driver> updateDriverById(@PathVariable Long userId, @RequestBody Driver driver){
        return null;
    }

    //delete driver by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteDriverById(@PathVariable Long userId){
        return null;
    }
}
