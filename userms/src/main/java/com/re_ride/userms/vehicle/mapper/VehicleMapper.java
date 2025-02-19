package com.re_ride.userms.vehicle.mapper;

import com.re_ride.userms.vehicle.Vehicle;
import com.re_ride.userms.vehicle.dto.VehicleDTO;

public class VehicleMapper {
    public static VehicleDTO mapVehicleDto(Vehicle vehicle){
        VehicleDTO vehicleDTO = new VehicleDTO();

        vehicleDTO.setMake(vehicle.getMake());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setVehicleType(vehicle.getVehicleType());
        vehicleDTO.setCapacity(vehicle.getCapacity());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setYear(vehicle.getYear());
        vehicleDTO.setLicensePlate(vehicle.getLicensePlate());

        vehicleDTO.setUserId(vehicle.getDriver().getUserId());
        vehicleDTO.setVehicleId(vehicle.getVehicleId());

        return vehicleDTO;
    }
}
