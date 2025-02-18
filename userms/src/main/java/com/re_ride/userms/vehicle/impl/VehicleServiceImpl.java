package com.re_ride.userms.vehicle.impl;

import com.re_ride.userms.driver.Driver;
import com.re_ride.userms.driver.DriverRepository;
import com.re_ride.userms.vehicle.Vehicle;
import com.re_ride.userms.vehicle.VehicleRepository;
import com.re_ride.userms.vehicle.VehicleService;
import com.re_ride.userms.vehicle.dto.VehicleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;
    private DriverRepository driverRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, DriverRepository driverRepository){
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
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
    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        Driver driver = driverRepository.findById(vehicleDTO.getUserId()).orElse(null);

        if(driver == null || driver.getVehicle() != null){
            return null;
        }

        Vehicle vehicle = new Vehicle(
                driver,
                vehicleDTO.getMake(),
                vehicleDTO.getModel(),
                vehicleDTO.getYear(),
                vehicleDTO.getLicensePlate(),
                vehicleDTO.getColor(),
                vehicleDTO.getCapacity(),
                vehicleDTO.getVehicleType()
        );

        vehicleRepository.save(vehicle);

        driver.setVehicle(vehicle);
        driverRepository.save(driver);

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
        Vehicle vehicle = getVehicleById(vehicleId);
        if (vehicle != null) {
            Driver driver = vehicle.getDriver();

            if (driver != null) {
                driver.removeVehicle();
                driverRepository.save(driver);
            }

            vehicleRepository.deleteById(vehicleId);
            return true;
        }
        return false;
    }
}
