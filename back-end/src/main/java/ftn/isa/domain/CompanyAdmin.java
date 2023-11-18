package ftn.isa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="companys_admins")
public class CompanyAdmin extends BaseUser{
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId", nullable = true)
    private Company company;
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<Appointment>();
    
    public CompanyAdmin() {
    	super();
    }
    public CompanyAdmin(Integer id, String firstName, String lastName, String email, String city, String country, String phoneNumber, String password, Role role) {
        super(id, firstName, lastName, email, city, country, phoneNumber, password, role);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
