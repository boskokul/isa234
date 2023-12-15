package ftn.isa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="registered_users")
public class RegisteredUser extends BaseUser {
	@Column(name = "profession", nullable = false)
    private String profession;
    @Column(name = "company_info", nullable = false)
    private String companyInformation;
    @Column(name = "category", nullable = false)
    private Category category;
    @Column(name = "penal_points", nullable = false)
    private Integer penalPoints;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Rating> ratings = new HashSet<Rating>();
    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<Reservation>();

    @OneToMany(mappedBy = "user")
    private Set<SecureToken> tokens;

    public RegisteredUser(){
        super();
        this.category = Category.Regular;
        this.penalPoints = 0;
    }
    public RegisteredUser(Integer id, String firstName, String lastName, String email, String city, String country, String phoneNumber, String password, Role role, String profession, String companyInformation, boolean verified) {
        super(id, firstName, lastName, email, city, country, phoneNumber, password, role, verified);
        this.profession = profession;
        this.companyInformation = companyInformation;
        this.category = Category.Regular;
        this.penalPoints = 0;
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

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getPenalPoints() {
        return penalPoints;
    }

    public void setPenalPoints(Integer penalPoints) {
        this.penalPoints = penalPoints;
    }

}
