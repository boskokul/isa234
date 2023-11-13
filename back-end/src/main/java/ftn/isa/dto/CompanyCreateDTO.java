package ftn.isa.dto;

import javax.persistence.Column;

import ftn.isa.domain.Company;

public class CompanyCreateDTO {
	private String name;
	private String description;
	private double averageGrade;
	private String adress;
	public CompanyCreateDTO(String name, String description, double averageGrade, String adress) {
		super();
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.adress = adress;
	}
	public CompanyCreateDTO(Company c) {
		this(c.getName(), c.getDescription(), c.getAverageGrade(), c.getAdress());
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
}
