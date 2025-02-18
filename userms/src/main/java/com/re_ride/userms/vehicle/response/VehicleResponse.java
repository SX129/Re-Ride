package com.re_ride.userms.vehicle.response;

import com.re_ride.userms.vehicle.dto.VehicleDTO;

public class VehicleResponse {
    private VehicleDTO vehicleDTO;
    private String message;

    public VehicleResponse(VehicleDTO vehicleDTO, String message) {
        this.vehicleDTO = vehicleDTO;
        this.message = message;
    }

    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }

    public void setVehicleDTO(VehicleDTO vehicleDTO) {
        this.vehicleDTO = vehicleDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
