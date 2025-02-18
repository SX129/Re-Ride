package com.re_ride.userms.vehicle;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.re_ride.userms.driver.Driver;
import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    public enum VehicleType {
        SEDAN,
        SUV
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private Driver driver;

    private String make;
    private String model;
    private Integer year;
    private String licensePlate;
    private String color;
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public Vehicle() {}

    public Vehicle(Driver driver, String make, String model, Integer year, String licensePlate, String color, Integer capacity, VehicleType vehicleType) {
        this.driver = driver;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.color = color;
        this.capacity = capacity;
        this.vehicleType = vehicleType;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
