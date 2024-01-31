package ftn.isa.dto;

import ftn.isa.domain.Contract;
import ftn.isa.domain.ContractEquipment;

import javax.persistence.*;

public class ContractEquipmentDTO {
    private String Name;
    private Integer Quantity;

    public ContractEquipmentDTO() {}

    public ContractEquipmentDTO(ContractEquipment ce){
        this.Name = ce.getName();
        this.Quantity = ce.getQuantity();
    }

    public ContractEquipmentDTO(String name, Integer quantity) {
        Name = name;
        Quantity = quantity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
