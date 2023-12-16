package ftn.isa.dto;

import ftn.isa.domain.Equipment;
import ftn.isa.domain.EquipmentType;

public class EquipmentDTO {
	private Integer id;
    private String name;
    private String description;
	private EquipmentType type;
    private int freeAmount;
	private int reservedAmount;

	private Integer amount;
   
	public EquipmentDTO(Equipment e) {
		this.id = e.getId();
		this.name = e.getName();
		this.description = e.getDescription();
		this.type = e.getType();
		this.freeAmount = e.getFreeAmount();
		this.reservedAmount = e.getReservedAmount();
		this.amount = e.getFreeAmount();
	}
    
	
	public EquipmentDTO(Integer id, String name, String description, EquipmentType type, Integer amount) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.amount = amount;
	}



	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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

	public int getFreeAmount() {
		return freeAmount;
	}

	public void setFreeAmount(int freeAmount) {
		this.freeAmount = freeAmount;
	}

	public int getReservedAmount() {
		return reservedAmount;
	}

	public void setReservedAmount(int reservedAmount) {
		this.reservedAmount = reservedAmount;
	}
}
