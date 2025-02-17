package com.re_ride.userms.vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();

    Vehicle getVehicleById(Long vehicleId);

    Vehicle createVehicle(Vehicle vehicle);

    Vehicle updateVehicleById(Long vehicleId, Vehicle vehicle);

    boolean deleteVehicleById(Long vehicleId);
}
