package ftn.isa.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="complaints", schema = "isa")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private RegisteredUser registeredUser;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "systemadmin_id")
    private SystemAdmin systemAdmin;// onaj koji odgovara na zalbu
    @Column(name = "response")
    private String response;

    public Complaint(Integer id, RegisteredUser registeredUser, String text, SystemAdmin systemAdmin, String response) {
        this.id = id;
        this.registeredUser = registeredUser;
        this.text = text;
        this.systemAdmin = systemAdmin;
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SystemAdmin getSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(SystemAdmin systemAdmin) {
        this.systemAdmin = systemAdmin;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
