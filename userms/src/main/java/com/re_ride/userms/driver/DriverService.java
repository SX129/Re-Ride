package com.re_ride.userms.driver;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DriverService {
    List<Driver> getAllDrivers();
    Driver getDriverById(Long userId);

    List<Driver> getAllAvailableDrivers();

    Driver createDriver(Driver driver);

    Driver updateDriverById(Long userId, Driver driver);

    boolean deleteDriverById(Long userId);
}
