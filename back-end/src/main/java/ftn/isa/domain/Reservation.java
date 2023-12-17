package ftn.isa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="reservation", schema = "isa")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private RegisteredUser registeredUser;
    @Column(name = "status", nullable = false)
    private ReservationStatus status;
    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
    private Set<ReservationItem> reservationItems = new HashSet<ReservationItem>();


    public Reservation(){}

    public Reservation(Integer id, Appointment appointment, ReservationStatus status) {
        this.id = id;
        this.appointment = appointment;
        this.status = status;
    }

    public Reservation(Integer id, Appointment appointment, RegisteredUser registeredUser, ReservationStatus status) {
		super();
		this.id = id;
		this.appointment = appointment;
		this.registeredUser = registeredUser;
		this.status = status;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    
    public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
