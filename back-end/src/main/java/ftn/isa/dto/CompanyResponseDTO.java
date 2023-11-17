package ftn.isa.dto;

import ftn.isa.domain.Company;

public class CompanyResponseDTO {
	private Integer id;
    private String name;
    private String description;
    private double averageGrade;
    private String adress;
	public CompanyResponseDTO(Integer id, String name, String description, double averageGrade, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.adress = adress;
	}
	public CompanyResponseDTO(Company c) {
		this(c.getId(), c.getName(), c.getDescription(), c.getAverageGrade(), c.getAdress());
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
    
}