package com.re_ride.userms.vehicle.impl;

import com.re_ride.userms.vehicle.Vehicle;
import com.re_ride.userms.vehicle.VehicleRepository;
import com.re_ride.userms.vehicle.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElse(null);
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle updateVehicleById(Long vehicleId, Vehicle vehicle) {
        Vehicle updatedVehicle = getVehicleById(vehicleId);

        if(updatedVehicle != null){

            if(vehicle.getMake() != null){
                updatedVehicle.setMake(vehicle.getMake());
            }

            if(vehicle.getModel() != null){
                updatedVehicle.setModel(vehicle.getModel());
            }

            if(vehicle.getYear() != null){
                updatedVehicle.setYear(vehicle.getYear());
            }

            if(vehicle.getLicensePlate() != null){
                updatedVehicle.setLicensePlate(vehicle.getLicensePlate());
            }

            if(vehicle.getColor() != null){
                updatedVehicle.setColor(vehicle.getColor());
            }

            if(vehicle.getCapacity() != null){
                updatedVehicle.setCapacity(vehicle.getCapacity());
            }

            if(vehicle.getVehicleType() != null){
                updatedVehicle.setVehicleType(vehicle.getVehicleType());
            }

            vehicleRepository.save(updatedVehicle);
            return updatedVehicle;
        }

        return null;
    }

    @Override
    public boolean deleteVehicleById(Long vehicleId) {
        if(getVehicleById(vehicleId) != null){
            vehicleRepository.deleteById(vehicleId);
            return true;
        }

        return false;
    }
}
