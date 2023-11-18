package ftn.isa.domain;


import javax.persistence.*;
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
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "averageGrade", nullable = false)
    private double averageGrade;
    @Column(name = "adress", nullable = false)
    private String adress;

	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> admins = new HashSet<User>();
	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CompanyAdmin> companyAdmins = new HashSet<CompanyAdmin>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "company_equipment", joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"))
	private Set<Equipment> equipment = new HashSet<Equipment>();
    
    public Company() {
		// TODO Auto-generated constructor stub
    	super();
	}
    

	public Company(Integer id, String name, String description, double averageGrade, String adress,
			Set<Equipment> equipment) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.adress = adress;
		this.equipment = equipment;
	}


	public Company(Integer id, String name, String description, double averageGrade, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.adress = adress;
	}


	public Company(String name, String description, double averageGrade, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageGrade = averageGrade;
		this.adress = adress;
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

	public String getAdress() {
		return adress;
	}

	public Set<User> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<User> admins) {
		this.admins = admins;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

}
