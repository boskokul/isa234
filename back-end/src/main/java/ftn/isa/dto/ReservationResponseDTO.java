package ftn.isa.dto;

import ftn.isa.domain.ReservationStatus;

public class ReservationResponseDTO {
    private int id;
    private ReservationStatus status;
    private int appointmentId;
    private int userId;
    public ReservationResponseDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
