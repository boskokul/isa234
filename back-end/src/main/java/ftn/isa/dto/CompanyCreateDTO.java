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
	private int startHour;
	private int startMinute;
	private int endHour;
	private int endMinute;
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

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}
}
