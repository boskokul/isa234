package ftn.isa.dto;

import ftn.isa.domain.Appointment;
import ftn.isa.domain.ReservationStatus;

import java.time.LocalDateTime;

public class AppointmentInfoDTO {
    private Integer id;
    private String adminName;
    private LocalDateTime dateTime;
    private int duration;
    private ReservationStatus reservationStatus;
    private String reservationQrcode;

    public AppointmentInfoDTO(Appointment a){
        this.id = a.getId();
        this.adminName = a.getAdmin().getFirstName() + " " + a.getAdmin().getLastName();
        this.dateTime = a.getDateTime();
        this.duration = a.getDuration();
        this.reservationStatus = a.getReservation().getStatus();
        this.reservationQrcode = a.getReservation().getQrcode();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }
    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
    public String getReservationQrcode() {
        return reservationQrcode;
    }
    public void setReservationQrcode(String reservationQrcode) {
        this.reservationQrcode = reservationQrcode;
    }
}
