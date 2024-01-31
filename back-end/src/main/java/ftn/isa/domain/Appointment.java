package ftn.isa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="appointments", schema = "isa")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_admin_id", nullable = true)
    private CompanyAdmin admin;
    @OneToOne(mappedBy = "appointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Reservation reservation;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "isExtraordinary", nullable = true)
    private Boolean isExtraordinary;

    public Appointment(){}
    public Appointment(Integer id, CompanyAdmin admin, LocalDateTime dateTime, int duration) {
        this.id = id;
        this.admin = admin;
        this.dateTime = dateTime;
        this.duration = duration;
    }

    public Appointment(Integer id, CompanyAdmin admin, Reservation reservation, LocalDateTime dateTime, int duration) {
		super();
		this.id = id;
		this.admin = admin;
		this.reservation = reservation;
		this.dateTime = dateTime;
		this.duration = duration;
	}
    
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CompanyAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(CompanyAdmin admin) {
        this.admin = admin;
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

    public Boolean isExtraordinary() {
        return isExtraordinary;
    }

    public void setExtraordinary(Boolean extraordinary) {
        isExtraordinary = extraordinary;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
