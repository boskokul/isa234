package ftn.isa.dto;

import ftn.isa.domain.User;

public class UserUpdateDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private Integer phoneNumber;
    private String profession;
    private String companyInformation;

    public UserUpdateDTO() {

    }

    public UserUpdateDTO(Integer id, String firstName, String lastName, String city, String country, Integer phoneNumber, String profession, String companyInformation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
        this.companyInformation = companyInformation;
    }

    public UserUpdateDTO(User user){
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getCity(), user.getCountry(), user.getPhoneNumber(),
                user.getProfession(), user.getCompanyInformation());
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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}
