package ftn.isa.dto;

import ftn.isa.domain.CompanyAdmin;
import ftn.isa.domain.SystemAdmin;

public class SysAdminResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String city;
    private String country;
    private String phoneNumber;
    private Boolean verified;
    public String getFirstName() {
        return firstName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SysAdminResponseDTO() {}
    public SysAdminResponseDTO(SystemAdmin cA) {
        super();
        this.id = cA.getId();
        this.firstName = cA.getFirstName();
        this.lastName = cA.getLastName();
        this.email = cA.getEmail();
        this.city = cA.getCity();
        this.country = cA.getCountry();
        this.phoneNumber = cA.getPhoneNumber();
        this.password = cA.getPassword();
    }
}
