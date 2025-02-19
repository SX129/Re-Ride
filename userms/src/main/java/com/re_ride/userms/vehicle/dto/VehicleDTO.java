package com.re_ride.userms.vehicle.dto;

import com.re_ride.userms.vehicle.Vehicle;

public class VehicleDTO {
    private Long userId;
    private Long vehicleId;
    private String make;
    private String model;
    private Integer year;
    private String licensePlate;
    private String color;
    private Integer capacity;
    private Vehicle.VehicleType vehicleType;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Vehicle.VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Vehicle.VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
