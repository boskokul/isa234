package ftn.isa.dto;

import java.util.Set;

import ftn.isa.domain.Company;
import ftn.isa.domain.Equipment;

public class EquipmentDTO {
	private Integer id;
    private String name;
    private String description;
    
    

	public EquipmentDTO(Equipment e) {
		this.id = e.getId();
		this.name = e.getName();
		this.description = e.getDescription();
	}
    
	public EquipmentDTO(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
    
    
}
