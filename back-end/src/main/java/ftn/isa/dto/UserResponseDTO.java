package ftn.isa.dto;

import ftn.isa.domain.RegisteredUser;

public class UserResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String country;
    private String phoneNumber;
    private String profession;
    private String companyInformation;
    public UserResponseDTO() {

    }
    public UserResponseDTO(RegisteredUser user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getCity(),
                user.getCountry(), user.getProfession(), user.getCompanyInformation(), user.getPhoneNumber());
    }
    public UserResponseDTO(Integer id, String firstName, String lastName, String email, String city, String country,
                           String profession, String companyInformation, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.country = country;
        this.profession = profession;
        this.companyInformation = companyInformation;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
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

    public String getCompanyInformation() {
        return companyInformation;
    }

    public void setCompanyInformation(String companyInformation) {
        this.companyInformation = companyInformation;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
