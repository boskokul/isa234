package ftn.isa.dto;

import java.time.LocalDateTime;

public class ExtraordinaryAppointmentDTO {
    private Integer adminsId;
    private String adminName;
    private LocalDateTime dateTime;
    private int duration;

    public ExtraordinaryAppointmentDTO(Integer adminsId, String adminName, LocalDateTime dateTime, int duration) {
        this.adminsId = adminsId;
        this.adminName = adminName;
        this.dateTime = dateTime;
        this.duration = duration;
    }

    public Integer getAdminsId() {
        return adminsId;
    }

    public void setAdminsId(Integer adminsId) {
        this.adminsId = adminsId;
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
}
