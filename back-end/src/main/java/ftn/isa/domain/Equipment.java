package ftn.isa.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
    @Column(name = "amount", nullable = false)
	private Integer amount;


	@OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Reservation> reservations = new HashSet<Reservation>();
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId", nullable = true)
    private Company company;
    
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
	
	public Equipment(Integer id, String name, String description, Company company, EquipmentType type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.company = company;
		this.type = type;
	}

	public Equipment(Integer id, String name, String description, EquipmentType type, Integer amount,
			Set<Reservation> reservations, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.amount = amount;
		this.reservations = reservations;
		this.company = company;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public Set<Reservation> getreservations() {
		return reservations;
	}

	public void setreservations(Set<Reservation> reservations) {
		this.reservations = reservations;
  }

	public EquipmentType getType() {
		return type;
	}

	public void setType(EquipmentType type) {
		this.type = type;
	}
}
