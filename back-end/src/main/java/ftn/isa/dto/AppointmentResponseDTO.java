package ftn.isa.dto;

import java.time.LocalDateTime;

import ftn.isa.domain.Appointment;

public class AppointmentResponseDTO {
	private Integer id;
    private String adminName;
    private LocalDateTime dateTime;
    private int duration;
    
    public AppointmentResponseDTO(Appointment a) {
		super();
		this.id = a.getId();
		this.adminName = a.getAdmin().getFirstName() + " " + a.getAdmin().getLastName();
		this.dateTime = a.getDateTime();
		this.duration = a.getDuration();
	}
    
	public AppointmentResponseDTO(Integer id, String adminName, LocalDateTime dateTime, int duration) {
		super();
		this.id = id;
		this.adminName = adminName;
		this.dateTime = dateTime;
		this.duration = duration;
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
}
