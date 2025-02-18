package com.re_ride.userms.driver.mapper;

import com.re_ride.userms.driver.Driver;
import com.re_ride.userms.driver.dto.DriverDTO;
import com.re_ride.userms.vehicle.Vehicle;

public class DriverMapper {
    public static DriverDTO mapDriverDto(Driver driver){
        DriverDTO driverDTO = new DriverDTO();

        driverDTO.setAvailabilityStatus(driver.getAvailabilityStatus());
        driverDTO.setRating(driver.getRating());

        driverDTO.setUserId(driver.getUserId());
        driverDTO.setFirstName(driver.getFirstName());
        driverDTO.setLastName(driver.getLastName());
        driverDTO.setEmail(driver.getEmail());
        driverDTO.setPhoneNumber(driver.getPhoneNumber());

        Vehicle vehicle = driver.getVehicle();

        if(vehicle != null){
            driverDTO.setVehicleId(vehicle.getVehicleId());
        }

        return driverDTO;
    }
}
