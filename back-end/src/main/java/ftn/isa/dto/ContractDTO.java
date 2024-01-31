package ftn.isa.dto;

import ftn.isa.domain.Contract;
import ftn.isa.domain.ContractEquipment;
import ftn.isa.domain.ContractStatus;

import java.util.HashSet;
import java.util.Set;

public class ContractDTO {
    private String hospitalName;
    private Integer duration;
    private Integer dayOfMonth;
    private Integer hours;
    private Integer minutes;
    private ContractStatus status;
    private Set<ContractEquipmentDTO> contractEquipment = new HashSet<ContractEquipmentDTO>();

    public ContractDTO() {}

    public ContractDTO(Contract contract){
        this.hospitalName = contract.getHospitalName();
        this.duration = contract.getDuration();
        this.dayOfMonth = contract.getDayOfMonth();
        this.hours = contract.getHours();
        this.minutes = contract.getMinutes();
        this.status = contract.getStatus();
        this.contractEquipment = new HashSet<>();
        for (ContractEquipment ce:
             contract.getContractEquipment()) {
            this.contractEquipment.add(new ContractEquipmentDTO(ce));
        }
    }

    public ContractDTO(String hospitalName, Integer duration, Integer dayOfMonth, Integer hours, Integer minutes, ContractStatus status, Set<ContractEquipmentDTO> contractEquipment) {
        this.hospitalName = hospitalName;
        this.duration = duration;
        this.dayOfMonth = dayOfMonth;
        this.hours = hours;
        this.minutes = minutes;
        this.status = status;
        this.contractEquipment = contractEquipment;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public Set<ContractEquipmentDTO> getContractEquipment() {
        return contractEquipment;
    }

    public void setContractEquipment(Set<ContractEquipmentDTO> contractEquipment) {
        this.contractEquipment = contractEquipment;
    }
}
