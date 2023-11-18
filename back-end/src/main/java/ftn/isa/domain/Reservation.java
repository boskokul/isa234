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

    public Reservation(Integer id, Appointment appointment, ReservationStatus status) {
        this.id = id;
        this.appointment = appointment;
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

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        status = status;
    }
}
