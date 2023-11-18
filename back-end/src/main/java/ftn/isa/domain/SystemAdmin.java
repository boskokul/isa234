package ftn.isa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="system_admins")
public class SystemAdmin extends BaseUser{
    @OneToMany(mappedBy = "systemAdmin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Complaint> complaints = new HashSet<Complaint>();

    public SystemAdmin(Integer id, String firstName, String lastName, String email, String city, String country, String phoneNumber, String password, Role role) {
        super(id, firstName, lastName, email, city, country, phoneNumber, password, role);
    }

    public Set<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }
}
