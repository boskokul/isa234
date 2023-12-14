package ftn.isa.domain;

import javax.persistence.*;

@Entity
@Table(name="reservations", schema = "isa")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;
    @Column(name = "amount", nullable = false)
    private Integer amount;

    public Reservation(Integer id, Appointment appointment, ReservationStatus status) {
        this.id = id;
        this.appointment = appointment;
        this.status = status;
    }

    public Reservation(Integer id, Appointment appointment, RegisteredUser registeredUser, ReservationStatus status,
			Equipment equipment, Integer amount) {
		super();
		this.id = id;
		this.appointment = appointment;
		this.registeredUser = registeredUser;
		this.status = status;
		this.equipment = equipment;
		this.amount = amount;
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

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
