package ftn.isa.dto;

import ftn.isa.domain.Reservation;
import ftn.isa.domain.ReservationStatus;

import java.time.LocalDateTime;

public class ReservationDTO {
    private int id;
    private ReservationStatus status;
    private int appointmentId;
    private int userId;
    private String usersName;
    private String usersEmail;

    private LocalDateTime dateTime;

    public ReservationDTO(){}
    public ReservationDTO(Reservation reservation)
    {
        id = reservation.getId();
        status = reservation.getStatus();
        appointmentId = reservation.getAppointment().getId();
        dateTime = reservation.getAppointment().getDateTime();
        userId = reservation.getRegisteredUser().getId();
        usersName = reservation.getRegisteredUser().getFirstName() + " " + reservation.getRegisteredUser().getLastName();
        usersEmail = reservation.getRegisteredUser().getEmail();
    }

    public String getUsersEmail() {
        return usersEmail;
    }

    public void setUsersEmail(String usersEmail) {
        this.usersEmail = usersEmail;
    }
    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

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
