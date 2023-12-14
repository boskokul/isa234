package ftn.isa.dto;

import java.time.LocalDateTime;

public class AppointmentCreateDTO {
    private Integer adminsId;
    private LocalDateTime dateTime;
    private int duration;
	public AppointmentCreateDTO(Integer adminsId, LocalDateTime dateTime, int duration) {
		super();
		this.adminsId = adminsId;
		this.dateTime = dateTime;
		this.duration = duration;
	}
	public Integer getAdminsId() {
		return adminsId;
	}
	public void setAdminsId(Integer adminsId) {
		this.adminsId = adminsId;
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
