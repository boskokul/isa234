package ftn.isa.dto;

import java.time.LocalTime;

import javax.persistence.Column;

import ftn.isa.domain.Company;

public class CompanyResponseDTO {
	private Integer id;
    private String name;
    private String description;
    private double averageGrade;
    private String country;
	private String city;
    private LocalTime startTime;
    private LocalTime endTime;
	private float lat;
	private float lon;
	private String street;
	private Integer houseNumber;
	public CompanyResponseDTO(Integer id, String name, String description, double averageGrade, String country,
			String city, LocalTime startTime, LocalTime endTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.country = country;
		this.city = city;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public CompanyResponseDTO(Company c) {
		this(c.getId(), c.getName(), c.getDescription(), c.getAverageGrade(), c.getCountry(), c.getCity(),
				c.getStartTime(), c.getEndTime(), c.getLat(), c.getLon(), c.getStreet(), c.getHouseNumber());
	}

	public CompanyResponseDTO(Integer id, String name, String description, double averageGrade, String country, String city,
							  LocalTime startTime, LocalTime endTime, float lat, float lon, String street, Integer houseNumber) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.country = country;
		this.city = city;
		this.startTime = startTime;
		this.endTime = endTime;
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
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
}
