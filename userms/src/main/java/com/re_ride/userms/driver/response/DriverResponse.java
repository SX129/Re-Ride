package com.re_ride.userms.driver.response;

import com.re_ride.userms.driver.Driver;
import com.re_ride.userms.driver.dto.DriverDTO;

public class DriverResponse {
    private DriverDTO driverDTO;
    private String message;

    public DriverResponse(DriverDTO driverDTO, String message) {
        this.driverDTO = driverDTO;
        this.message = message;
    }

    public DriverDTO getDriverDTO() {
        return driverDTO;
    }

    public void setDriverDTO(DriverDTO driverDTO) {
        this.driverDTO = driverDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
