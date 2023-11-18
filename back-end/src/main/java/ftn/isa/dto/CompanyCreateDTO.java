package ftn.isa.dto;

import javax.persistence.Column;

import ftn.isa.domain.Company;

public class CompanyCreateDTO {
	private String name;
	private Integer id;
	private String description;
	private double averageGrade;
	private String country;
	private String city;
	public CompanyCreateDTO(Integer id, String name, String description, double averageGrade, String country, String city) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.country = country;
		this.city = city;
	}
	public CompanyCreateDTO(Company c) {
		this(c.getId(), c.getName(), c.getDescription(), c.getAverageGrade(), c.getCountry(), c.getCity());
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
