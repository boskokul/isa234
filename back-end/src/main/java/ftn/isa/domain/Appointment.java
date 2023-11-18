package ftn.isa.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="appointments", schema = "isa")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_admin_id", nullable = true)
    private CompanyAdmin admin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;
    @OneToOne(mappedBy = "appointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Reservation reservation;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "duration", nullable = false)
    private int duration;
    public Appointment(){}
    public Appointment(Integer id, CompanyAdmin admin, Equipment equipment, LocalDateTime dateTime, int duration) {
        this.id = id;
        this.admin = admin;
        this.equipment = equipment;
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

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
