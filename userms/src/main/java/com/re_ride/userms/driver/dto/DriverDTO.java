package com.re_ride.userms.driver.dto;

import com.re_ride.userms.driver.Driver;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class DriverDTO {
    private Long vehicleId;
    private Double rating;
    private Driver.AvailabilityStatus availabilityStatus;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Driver.AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(Driver.AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
