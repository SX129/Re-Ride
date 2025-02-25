package com.re_ride.userms.driver.impl;

import com.re_ride.userms.driver.Driver;
import com.re_ride.userms.driver.DriverRepository;
import com.re_ride.userms.driver.DriverService;
import com.re_ride.userms.user.UserRepository;
import com.re_ride.userms.user.messaging.RabbitMQConfig;
import com.re_ride.userms.user.messaging.UserEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository;
    private UserRepository userRepository;
    private RabbitTemplate rabbitTemplate;

    public DriverServiceImpl(DriverRepository driverRepository, UserRepository userRepository, RabbitTemplate rabbitTemplate){
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriverById(Long userId) {
        return driverRepository.findById(userId).orElse(null);
    }

    @Override
    public List<Driver> getAllAvailableDrivers() {
        return driverRepository.findAll()
                .stream()
                .filter(driver -> driver.getAvailabilityStatus() == Driver.AvailabilityStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

    @Override
    public Driver createDriver(Driver driver) {
        driverRepository.save(driver);

        UserEvent.UserType userType = UserEvent.UserType.valueOf(driver.getUserType().name());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                new UserEvent(driver.getUserId(), userType, driver.getFirstName(), driver.getEmail())
        );

        return driver;
    }

    @Override
    public Driver updateDriverById(Long userId, Driver driver) {
        Driver updatedDriver = getDriverById(userId);

        if(updatedDriver != null) {

            if(driver.getAvailabilityStatus() != null){
                updatedDriver.setAvailabilityStatus(driver.getAvailabilityStatus());
            }

            if(driver.getRating() != null){
                updatedDriver.setRating(driver.getRating());
            }

            if(driver.getLicenseNumber() != null){
                updatedDriver.setLicenseNumber(driver.getLicenseNumber());
            }

            driverRepository.save(updatedDriver);

            return updatedDriver;
        }

        return null;
    }

    @Override
    public boolean deleteDriverById(Long userId) {
        Driver driver = getDriverById(userId);
        if (driver != null) {
            driverRepository.deleteById(userId);
            userRepository.deleteById(userId);

            return true;
        }

        return false;
    }
}
