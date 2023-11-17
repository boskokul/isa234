package ftn.isa.dto;

import java.util.Set;

import ftn.isa.domain.Company;
import ftn.isa.domain.Equipment;
import ftn.isa.domain.EquipmentType;

public class EquipmentDTO {
	private Integer id;
    private String name;
    private String description;

	private EquipmentType type;
    
    

	public EquipmentDTO(Equipment e) {
		this.id = e.getId();
		this.name = e.getName();
		this.description = e.getDescription();
		this.type = e.getType();
	}
    
	public EquipmentDTO(Integer id, String name, String description, EquipmentType type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
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
