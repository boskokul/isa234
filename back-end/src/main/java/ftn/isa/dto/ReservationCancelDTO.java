package ftn.isa.dto;

public class ReservationCancelDTO {

    private int appointmentId;
    private int userId;

    public ReservationCancelDTO() {
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
