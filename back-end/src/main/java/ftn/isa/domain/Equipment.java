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
	@Column(name = "freeAmount", nullable = false)
	private Integer freeAmount;
	@Column(name = "reservedAmount", nullable = false)
	private Integer reservedAmount;
	@OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ReservationItem> reservations = new HashSet<ReservationItem>();
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
		this.freeAmount = 0;
		this.reservedAmount = 0;
	}

	public Equipment(String name, String description, EquipmentType type) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.freeAmount = 0;
		this.reservedAmount = 0;
	}
	
	public Equipment(Integer id, String name, String description, Company company, EquipmentType type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.company = company;
		this.type = type;
		this.freeAmount = 0;
		this.reservedAmount = 0;
	}

	public Equipment(Integer id, String name, String description, EquipmentType type, Integer amount,
					 Integer freeAmount, Integer reservedAmount, Set<ReservationItem> reservations, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.freeAmount = freeAmount;
		this.reservedAmount = reservedAmount;
		this.reservations = reservations;
		this.company = company;
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
	public Integer getFreeAmount() { return freeAmount;	}
	public void setFreeAmount(Integer freeAmount) {
		if(freeAmount < 0){
			throw new IllegalArgumentException("freeAmount can not be negative");
		}
		this.freeAmount = freeAmount;
	}
	public Integer getReservedAmount() { return reservedAmount;	}
	public void setReservedAmount(Integer reservedAmount) {
		if(reservedAmount < 0){
			throw new IllegalArgumentException("reservedAmount can not be negative");
		}
		this.reservedAmount = reservedAmount;
	}
	public Set<ReservationItem> getreservations() {
		return reservations;
	}

	public void setreservations(Set<ReservationItem> reservations) {
		this.reservations = reservations;
  }

	public EquipmentType getType() {
		return type;
	}

	public void setType(EquipmentType type) {
		this.type = type;
	}
}
