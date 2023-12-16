package ftn.isa.dto;

import ftn.isa.domain.EquipmentType;

public class EquipmentCreateDTO {
    private String name;
    private String description;
	private EquipmentType type;
	private Integer amount;
	private Integer companyId;
	
	public EquipmentCreateDTO(String name, String description, EquipmentType type, Integer amount, Integer companyId) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.amount = amount;
		this.companyId = companyId;
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
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	

}
