package com.re_ride.userms.driver;

import com.re_ride.userms.user.User;
import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Driver extends User {
    public enum AvailabilityStatus {
        AVAILABLE,
        BUSY,
        OFFLINE
    }

    private String licenseNumber;
    private Long vehicleId;
    private Double rating;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    public Driver(String firstName, String lastName, String email, String password,
                  String phoneNumber, String licenseNumber, Long vehicleId,
                  Double rating, AvailabilityStatus availabilityStatus) {
        super(firstName, lastName, email, password, phoneNumber, UserType.DRIVER);
        this.licenseNumber = licenseNumber;
        this.vehicleId = vehicleId;
        this.rating = rating;
        this.availabilityStatus = availabilityStatus;
    }

    public Driver() {
        super.setUserType(UserType.DRIVER); // Default to DRIVER
    }
}
