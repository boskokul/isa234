package ftn.isa.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="equipments", schema = "isa")
public class Equipment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;

	@Column(name = "type", nullable = false)
	private EquipmentType type;
    
    @ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "company_equipment", joinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"))
	private Set<Company> companies = new HashSet<Company>();
    
    public Equipment() {
    	super();
    }
    
	public Equipment(Integer id, String name, String description, EquipmentType type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
	}

	public Equipment(String name, String description, EquipmentType type) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
	}
	
	public Equipment(Integer id, String name, String description, Set<Company> companies, EquipmentType type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.companies = companies;
		this.type = type;
	}

	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
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

	public EquipmentType getType() {
		return type;
	}

	public void setType(EquipmentType type) {
		this.type = type;
	}
}
