package ftn.isa.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="companies", schema = "isa")
public class Company implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "averageGrade", nullable = false)
    private double averageGrade;
    
    @Column(name = "startTime", nullable = false)
    private LocalTime startTime;
    @Column(name = "endTime", nullable = false)
    private LocalTime endTime;

	@Column(name = "country", nullable = false)
	private String country;
	@Column(name = "city", nullable = false)
	private String city;
	@Column(name = "lat", nullable = false)
	private float lat;
	@Column(name = "lon", nullable = false)
	private float lon;
	@Column(name = "street", nullable = false)
	private String street;
	@Column(name = "houseNumber", nullable = false)
	private Integer houseNumber;

	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CompanyAdmin> companyAdmins = new HashSet<CompanyAdmin>();
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Rating> ratings = new HashSet<Rating>();
    
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Equipment> equipment = new HashSet<Equipment>();
    
    public Company() {
		// TODO Auto-generated constructor stub
    	super();
	}
    

	public Company(Integer id, String name, String description, double averageGrade, String country, String city,
			Set<Equipment> equipment) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.country = country;
		this.city = city;
		this.equipment = equipment;
	}

	public Company(Integer id, String name, String description, double averageGrade, LocalTime startTime,
				   LocalTime endTime, String country, String city, float lat, float lon,
				   String street, Integer houseNumber, Set<CompanyAdmin> companyAdmins,
				   Set<Rating> ratings, Set<Equipment> equipment) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.startTime = startTime;
		this.endTime = endTime;
		this.country = country;
		this.city = city;
		this.lat = lat;
		this.lon = lon;
		this.street = street;
		this.houseNumber = houseNumber;
		this.companyAdmins = companyAdmins;
		this.ratings = ratings;
		this.equipment = equipment;
	}

	public Company(Integer id, String name, String description, double averageGrade, LocalTime startTime,
				   LocalTime endTime, String country, String city, float lat, float lon, String street,
				   Integer houseNumber, Set<CompanyAdmin> companyAdmins, Set<Equipment> equipment) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.startTime = startTime;
		this.endTime = endTime;
		this.country = country;
		this.city = city;
		this.lat = lat;
		this.lon = lon;
		this.street = street;
		this.houseNumber = houseNumber;
		this.companyAdmins = companyAdmins;
		this.equipment = equipment;
	}

	public Company(Integer id, String name, String description, double averageGrade, LocalTime startTime, LocalTime endTime,
				   String country, String city, float lat, float lon, String street, Integer houseNumber) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.startTime = startTime;
		this.endTime = endTime;
		this.country = country;
		this.city = city;
		this.lat = lat;
		this.lon = lon;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Company(Integer id, String name, String description, double averageGrade, String country, String city) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.country = country;
		this.city = city;
	}


	public Company(String name, String description, double averageGrade, String country, String city) {
		super();
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.country = country;
		this.city = city;
	}
	
	
	
	public Company(Integer id, String name, String description, double averageGrade, LocalTime startTime,
			LocalTime endTime, String country, String city, Set<CompanyAdmin> companyAdmins, Set<Rating> ratings,
			Set<Equipment> equipment) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.startTime = startTime;
		this.endTime = endTime;
		this.country = country;
		this.city = city;
		this.companyAdmins = companyAdmins;
		this.ratings = ratings;
		this.equipment = equipment;
	}

	
	

	public LocalTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}


	public LocalTime getEndTime() {
		return endTime;
	}


	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}


	public Set<CompanyAdmin> getCompanyAdmins() {
		return companyAdmins;
	}


	public void setCompanyAdmins(Set<CompanyAdmin> companyAdmins) {
		this.companyAdmins = companyAdmins;
	}


	public Set<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(Set<Equipment> equipment) {
		this.equipment = equipment;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}


	public String getCountry() { return country; }

	public void setCountry(String country) { this.country = country; }

	public String getCity() {return city; }

	public void setCity(String city) { this.city = city; }

	public Set<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}
}
