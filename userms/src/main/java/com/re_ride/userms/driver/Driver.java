package com.re_ride.userms.driver;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.re_ride.userms.user.User;
import com.re_ride.userms.vehicle.Vehicle;
import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Driver extends User {

    public enum AvailabilityStatus {
        AVAILABLE,
        BUSY,
        OFFLINE
    }

    private String licenseNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    private Vehicle vehicle;

    private Double rating;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    public Driver(String firstName, String lastName, String email, String password,
                  String phoneNumber, String licenseNumber, Vehicle vehicle,
                  Double rating, AvailabilityStatus availabilityStatus) {
        super(firstName, lastName, email, password, phoneNumber, UserType.DRIVER);
        this.licenseNumber = licenseNumber;
        this.vehicle = vehicle;
        this.rating = rating;
        this.availabilityStatus = availabilityStatus;
    }

    public Driver() {
        super.setUserType(UserType.DRIVER);
        this.availabilityStatus = AvailabilityStatus.AVAILABLE;
    }

    public void removeVehicle() {
        this.vehicle = null;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
