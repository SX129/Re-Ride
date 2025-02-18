package com.re_ride.userms.driver.mapper;

import com.re_ride.userms.driver.Driver;
import com.re_ride.userms.driver.dto.DriverDTO;

public class DriverMapper {
    public static DriverDTO mapDriverDto(Driver driver){
        DriverDTO driverDTO = new DriverDTO();

        driverDTO.setAvailabilityStatus(driver.getAvailabilityStatus());
        driverDTO.setRating(driver.getRating());

        driverDTO.setVehicleId(driver.getVehicle().getVehicleId());

        driverDTO.setUserId(driver.getUserId());
        driverDTO.setFirstName(driver.getFirstName());
        driverDTO.setLastName(driver.getLastName());
        driverDTO.setEmail(driver.getEmail());
        driverDTO.setPhoneNumber(driver.getPhoneNumber());

        return driverDTO;
    }
}
