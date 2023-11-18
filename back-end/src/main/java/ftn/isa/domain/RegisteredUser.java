package ftn.isa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="registered_users")
public class RegisteredUser extends BaseUser {
    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<Reservation>();
    @Column(name = "profession", nullable = false)
    private String profession;
    @Column(name = "companyInfo", nullable = false)
    private String companyInformation;

    public RegisteredUser(Integer id, String firstName, String lastName, String email, String city, String country, Integer phoneNumber, String password, Role role, String profession, String companyInformation) {
        super(id, firstName, lastName, email, city, country, phoneNumber, password, role);
        this.profession = profession;
        this.companyInformation = companyInformation;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompanyInformation() {
        return companyInformation;
    }

    public void setCompanyInformation(String companyInformation) {
        this.companyInformation = companyInformation;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
