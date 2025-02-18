package com.re_ride.userms.vehicle;

import com.re_ride.userms.vehicle.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();

    Vehicle getVehicleById(Long vehicleId);

    Vehicle createVehicle(VehicleDTO vehicleDTO);

    Vehicle updateVehicleById(Long vehicleId, Vehicle vehicle);

    boolean deleteVehicleById(Long vehicleId);
}
